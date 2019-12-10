/**
 * This file is part of iDempiere ERP <http://www.idempiere.org>.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Copyright (c) 2016 Saúl Piña <sauljabin@gmail.com>.
 */

package org.idempiere.tms.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDTransportUnit;
import org.idempiere.tms.model.MDDVehicle;

import java.math.RoundingMode;


/**
 * Process create Transport Unit
 */
public class  VehicleAssignment extends CustomProcess  {

	private String		processVerNo = "[v.1.01] ";
	private BigDecimal  vh_volume=null;
	private BigDecimal  vh_weight=null;
	private int 		p_DD_Vehicle_ID=0;
	private int 		p_DD_VehicleType_ID=0;
	private int 		vh_count=0;	
	private int 		vh_no=1;
	private int 		p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	private MDDVehicle  vh =null;
	
	private String whereClause ="";
	
	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	@Override
	protected void prepare() {
			//	Parameter
			ProcessInfoParameter[] para = getParameter();
			for (int i = 0; i < para.length; i++)
			{
				String name = para[i].getParameterName();
				if (para[i].getParameter() == null && para[i].getParameter_To() == null)
					;
				else if (name.equals("DD_Vehicle_ID"))
					p_DD_Vehicle_ID =  para[i].getParameterAsInt();
				
				else if (name.equals("DD_VehicleType_ID"))
					p_DD_VehicleType_ID =  para[i].getParameterAsInt();
				
				else if (name.equals("DatePromised"))
				{
					p_DateFrom = (Timestamp)para[i].getParameter();
					p_DateTo = (Timestamp)para[i].getParameter_To();
				}
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
			
			if (p_DateFrom!=null && p_DateTo!=null) {
				whereClause =" otr.AD_Client_ID="+p_AD_Client_ID+" AND CAST(otr.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ";
			}
			else 
				whereClause =" otr.AD_Client_ID="+p_AD_Client_ID+" AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= " +getAD_PInstance_ID()+ " AND T_Selection.T_Selection_ID=otr.DD_OTR_ID)";
	}
	@Override
	protected String doIt() throws Exception {
		int count=1;
		String result="Vehicles assigned:";
		// check TU
//	       if (p_DD_Vehicle_ID>0)
//	    	   vh =new MDDVehicle(getCtx(), p_DD_Vehicle_ID, get_TrxName());
//	       else
//	    	   return "Vehicle didn't select!";

	       assignVH();
		
		return processVerNo+result+vh_count;
	}
	
 
	protected String assignVH()  {
		int count=1;
		String result="Vehicle assigned: ";
		       
		String sql = "SELECT DISTINCT ON(otr.DatePromised,otr.M_Warehouse_ID,bl.C_SalesRegion_ID,otr.C_BPartner_ID) "
				+ " otr.DatePromised,"//1
				+ " otr.M_Warehouse_ID,"//2
				+ " otr.C_BPartner_ID, "//3
				+ " otr.M_WarehouseSource_ID, "//4
				+ " otr.OrderType "//5
				+ " FROM DD_OTR otr "
				+ " LEFT JOIN c_bpartner_location bl ON bl.c_bpartner_ID=otr.c_bpartner_ID "   
				+ " LEFT JOIN c_location l ON l.c_location_ID=bl.c_location_ID " 
				+ " WHERE " + whereClause
				+ " ORDER BY otr.datepromised,otr.M_Warehouse_ID,bl.C_SalesRegion_ID;";

 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{		
					result+=updateVH(rs.getDate(1), 
							(rs.getString(5).equals("POO") ? rs.getInt(2) : rs.getInt(4)),
							rs.getInt(3), rs.getString(5));

					count++;				
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; 	pstmt = null;
			}
			
		return processVerNo+result+vh_count;
	}
 
	public String updateVH(Date DatePromised, int M_Warehouse_ID,int C_BPartner_ID, String OrderType) 
 	{
		vh_volume=Env.ZERO;
		vh_weight=Env.ZERO;
		String tu_name="";
		int count=0;
		String debug="";
		String otr_id="";
			
		String sql = "SELECT DISTINCT ON(otr.DatePromised,otr.M_Warehouse_ID,l.address5,otr.C_BPartner_ID, otr.fu)"
				+ " otr.M_Product_ID, " //1
				+ " otr.M_Warehouse_ID, "  //2
				+ " otr.minimumvolume ,"  //3
				+ " otr.minimumweight, "  //4
				+ " otr.fu " //5
				+ " FROM DD_OTR otr "
				+ " LEFT JOIN c_bpartner_location bl ON bl.c_bpartner_ID=otr.c_bpartner_ID "   
				+ " LEFT JOIN c_location l ON l.c_location_ID=bl.c_location_ID " 
				+ " WHERE "+whereClause
			    + " AND otr.DatePromised =? "
			    + " AND "+(OrderType.equals("POO") ? "otr.M_Warehouse_ID" : "otr.M_WarehouseSource_ID")+"=? ";
			     //AND otr.C_BPartner_ID=?;";		
 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setDate(1, DatePromised);
			pstmt.setInt(2, M_Warehouse_ID);
			//pstmt.setInt(3, C_BPartner_ID);
			rs = pstmt.executeQuery ();
			while(rs.next ())
			{
				vh =new MDDVehicle(getCtx(), getVehicle_ID(DatePromised), get_TrxName());
				if (vh.getVolume().setScale(3, RoundingMode.DOWN).compareTo(
					vh_volume.add(rs.getBigDecimal(3).setScale(3, RoundingMode.DOWN)))<0				
						||vh.getWeight().setScale(3, RoundingMode.DOWN).compareTo(
							vh_weight.add(rs.getBigDecimal(4).setScale(3, RoundingMode.DOWN)))<0					
					|| rs.isLast()
					)
				{
					
 					if (rs.isLast())
					{
		 				vh_volume= vh_volume.add(rs.getBigDecimal(3).setScale(3, RoundingMode.DOWN));
			 			vh_weight= vh_weight.add(rs.getBigDecimal(4).setScale(3, RoundingMode.DOWN));
			 			otr_id+=(count==0 ? "'"+rs.getString(5)+"'" : ",'"+rs.getString(5)+"'") ;
					}
 				
					 if (otr_id.length()>0)
			 				{
								StringBuffer sql_update = new StringBuffer();
								sql_update.append( "UPDATE DD_OTR SET tu='"+tu_name+"'"); 
								sql_update.append(",DD_Vehicle_ID="+vh.get_ID());
								sql_update.append(",volume= "+ vh_volume.setScale(3, RoundingMode.DOWN));
								sql_update.append(",weight="+ vh_weight.setScale(3, RoundingMode.DOWN));	
								sql_update.append(" WHERE fu IN ("+ otr_id+ ") AND DD_Vehicle_ID IS NULL AND AD_Client_ID="+p_AD_Client_ID+";");											
							 	DB.executeUpdateEx(sql_update.toString() , get_TrxName());	

							vh_no++;
							vh_volume=Env.ZERO;
							vh_weight=Env.ZERO;				
							count=0; otr_id=""; vh_count++;
			 				}
				}
 				
 				vh_volume= vh_volume.add(rs.getBigDecimal(3).setScale(3, RoundingMode.DOWN));
	 			vh_weight= vh_weight.add(rs.getBigDecimal(4).setScale(3, RoundingMode.DOWN));
	 			otr_id+=(count==0 ? "'"+rs.getString(5)+"'" : ",'"+rs.getString(5)+"'") ; 
	 			
				//create TU name --hard coding for debug
	 			tu_name="90"+rs.getInt(2)+String.format("%07d", vh_no); 	
	 			
	 			count++;
			}
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; 	pstmt = null;
		}
		//-----------------------
	return  debug;
 	}
	
	public int getVehicle_ID(Date DatePromised)
 	{	
		//Issue #3: Useless Parameter cause error (VehicleAssignment)
		if (p_DD_Vehicle_ID>0) 
			return p_DD_Vehicle_ID; 
		else
			return DB.getSQLValue(get_TrxName(), 
					"SELECT MIN(v.DD_Vehicle_ID) FROM DD_Vehicle v WHERE AD_Client_ID=? AND DD_VehicleType_ID=? "
							+ " AND v.DD_Vehicle_ID NOT IN (SELECT DISTINCT(otr.DD_Vehicle_ID) FROM DD_OTR otr "
							+ " WHERE otr.DD_Vehicle_ID IS NOT NULL AND CAST(otr.DatePromised AS date)=CAST('"+DatePromised+"' AS date));"
							,p_AD_Client_ID,p_DD_VehicleType_ID);
 	}
}
