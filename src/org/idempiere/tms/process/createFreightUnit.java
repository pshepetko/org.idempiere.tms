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

import java.math.RoundingMode;


/**
 * Process create Freight Unit
 */
public class createFreightUnit extends CustomProcess  {

	private String		processVerNo = "[v.1.02] ";
	private BigDecimal  fu_volume=null;
	private BigDecimal  fu_weight=null;
	private int 		p_DD_TransportUnit_ID=0;
	private int 		fu_count=0;	
	private int 		fu_no=1;
	private int p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	private boolean p_isFUtoTU;
	
	private String whereClause ="";
	
	MDDTransportUnit tu =null;
	
	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	private String debug="";

	@Override
	protected void prepare() {
			//	Parameter
			ProcessInfoParameter[] para = getParameter();
			for (int i = 0; i < para.length; i++)
			{
				String name = para[i].getParameterName();
				if (para[i].getParameter() == null && para[i].getParameter_To() == null)
					;
				else if (name.equals("DD_TransportUnit_ID"))
					p_DD_TransportUnit_ID =  para[i].getParameterAsInt();
				else if (name.equals("isFUtoTU"))
				{    
					p_isFUtoTU = para[i].getParameterAsBoolean();        
				}
				else if (name.equals("DatePromised"))
				{
					p_DateFrom = (Timestamp)para[i].getParameter();
					p_DateTo = (Timestamp)para[i].getParameter_To();
				}
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
			
			if (p_DateFrom!=null && p_DateTo!=null) 
				whereClause =" otr.AD_Client_ID="+p_AD_Client_ID+" AND CAST(otr.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ";
			else 
				whereClause =" otr.AD_Client_ID="+p_AD_Client_ID+" AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= " +getAD_PInstance_ID()+ " AND T_Selection.T_Selection_ID=otr.DD_OTR_ID) ";
	}

	@Override
	protected String doIt() throws Exception {
		int count=1;
		String result="Created Freight Units: ";
		
		// check TU
	       if (p_DD_TransportUnit_ID>0)
	    	   tu =new MDDTransportUnit(getCtx(), p_DD_TransportUnit_ID, get_TrxName());
	       else
	    	   return "Transport Unit didn't select!";
		
		String sql = "SELECT DISTINCT ON(otr.DatePromised,otr.M_Warehouse_ID,bl.C_SalesRegion_ID,otr.C_BPartner_ID, cr.DD_Compartment_ID) "
				+ " otr.DatePromised,"//1
				+ " otr.M_Warehouse_ID, "//2
				+ " otr.C_BPartner_ID, "//3
				+ " SUM (qtyOrdered),"//4
				+ " COALESCE(cr.DD_Compartment_ID,0) AS DD_Compartment_ID, "//5
				+ " otr.M_WarehouseSource_ID, "//6
				+ " otr.OrderType "//7
				+ " FROM DD_OTR otr "
				+ " LEFT JOIN c_bpartner_location bl ON bl.c_bpartner_ID=otr.c_bpartner_ID "   
				+ " LEFT JOIN c_location l ON l.c_location_ID=bl.c_location_ID "
				+ " LEFT JOIN m_product p ON p.m_product_ID=otr.m_product_ID "  
				+ "	LEFT JOIN M_FreightCategory fc ON fc.M_FreightCategory_ID=p.M_FreightCategory_ID "   
				+ "	LEFT JOIN  DD_Compartment cr ON cr.Value=fc.Value "   
				+ "	LEFT JOIN DD_CompartmentAssignment ca ON ca.DD_TransportUnit_ID="+p_DD_TransportUnit_ID+" AND ca.DD_Compartment_ID=cr.DD_Compartment_ID " 
				+ " WHERE "+whereClause
				+ " GROUP BY otr.DatePromised,otr.M_Warehouse_ID,bl.C_SalesRegion_ID,otr.C_BPartner_ID, cr.DD_Compartment_ID, otr.M_WarehouseSource_ID,  otr.OrderType;";

 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{		
				
					result+=updateFU(rs.getDate(1), 
							(rs.getString(7).equals("POO") ? rs.getInt(2) : rs.getInt(6)), 
								rs.getInt(3), tu.getVolume(),tu.getWeight(),rs.getInt(5),rs.getString(7));

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

		return processVerNo+result+fu_count;
	}
 
	public String updateFU(Date DatePromised, int M_Warehouse_ID,int C_BPartner_ID, BigDecimal tu_volume, BigDecimal tu_weight,int DD_Compartment_ID, String OrderType)
 	{
		fu_volume=Env.ZERO;
		fu_weight=Env.ZERO;
		String fu_name="";
		String otr_id="";
		int count=0;
		
		if (getLastFU()>0) fu_no=getLastFU()+1; else fu_no=1;
		
		String sql = "SELECT otr.M_Product_ID,"//1
				+ " (otr.qtyOrdered-otr.qtyDelivered),"//2
				+ " otr.DD_Otr_ID,"//3
				+ " COALESCE(cr.DD_Compartment_ID,0) AS DD_Compartment_ID,"//4
				+ " cr.MinimumVolume,"//5
				+ " cr.MaximumVolume,"//6
				+ " COALESCE(ca.seqno,1) AS seqno "//7
				+ " FROM DD_OTR otr "
				+ " LEFT JOIN m_product p ON p.m_product_ID=otr.m_product_ID " 
				+ " LEFT JOIN M_FreightCategory fc ON fc.M_FreightCategory_ID=p.M_FreightCategory_ID "   
				+ " LEFT JOIN  DD_Compartment cr ON cr.Value=fc.Value "   
				+ " LEFT JOIN DD_CompartmentAssignment ca ON ca.DD_TransportUnit_ID="+p_DD_TransportUnit_ID+" AND ca.DD_Compartment_ID=cr.DD_Compartment_ID "
				+ " WHERE (otr.qtyOrdered-otr.qtyDelivered) >0 AND "+whereClause
			    + " AND otr.DatePromised =? "
			    + " AND "+(OrderType.equals("POO") ? "otr.M_Warehouse_ID" : "otr.M_WarehouseSource_ID")+"=? "
			    + " AND otr.C_BPartner_ID=? AND (cr.DD_Compartment_ID=? OR cr.DD_Compartment_ID IS NULL);";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setDate(1, DatePromised);
			pstmt.setInt(2, M_Warehouse_ID);
			pstmt.setInt(3, C_BPartner_ID);
			pstmt.setInt(4, DD_Compartment_ID);
			rs = pstmt.executeQuery ();
			while(rs.next ())
			{
 				MProduct product =new MProduct(getCtx(), rs.getInt(1), get_TrxName());

			if (tu_volume.setScale(3, RoundingMode.DOWN).compareTo(
					fu_volume.add(rs.getBigDecimal(2).multiply(product.getVolume().setScale(3, RoundingMode.DOWN))))<=0
					|| tu_weight.setScale(3, RoundingMode.DOWN).compareTo(
							fu_weight.add(rs.getBigDecimal(2).multiply(product.getWeight().setScale(3, RoundingMode.DOWN))))<=0
					|| rs.isLast()
					)
				{
				
					if (rs.isLast())
					{
		 				fu_volume= fu_volume.add(rs.getBigDecimal(2).multiply(product.getVolume().setScale(3, RoundingMode.DOWN)));
			 			fu_weight= fu_weight.add(rs.getBigDecimal(2).multiply(product.getWeight().setScale(3, RoundingMode.DOWN)));					 
			 			otr_id+=(count==0 ? ""+rs.getInt(3) : ","+rs.getInt(3)) ;

			 			 fu_name="10"+M_Warehouse_ID+String.format("%07d", fu_no); 
					}

					if (otr_id.length()>0)
			 				{
								StringBuffer sql_update = new StringBuffer();
								sql_update.append( "UPDATE DD_OTR SET fu='"+fu_name+"'"); 
								sql_update.append(", DD_FreightUnit_ID="+p_DD_TransportUnit_ID);
								sql_update.append(", minimumvolume=  "+ fu_volume.setScale(3, RoundingMode.DOWN));
								sql_update.append(", minimumweight= "+ fu_weight.setScale(3, RoundingMode.DOWN));	
								sql_update.append(" WHERE DD_Otr_ID IN("+ otr_id+ ")  AND fu IS NULL;");											
							 	 DB.executeUpdateEx(sql_update.toString() , get_TrxName());	

							 	fu_no++;
							fu_volume=Env.ZERO;
							fu_weight=Env.ZERO;				
							count=0; otr_id="";fu_count++;
			 				}
				}
 				
 				fu_volume= fu_volume.add(rs.getBigDecimal(2).multiply(product.getVolume().setScale(3, RoundingMode.DOWN)));
	 			fu_weight= fu_weight.add(rs.getBigDecimal(2).multiply(product.getWeight().setScale(3, RoundingMode.DOWN)));
				 
	 			 otr_id+=(count==0 ? ""+rs.getInt(3) : ","+rs.getInt(3)) ;

	 			 fu_name="10"+M_Warehouse_ID+String.format("%07d", fu_no);
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
	return  "";
 	}
	
	public int getLastFU()
 	{	
	return DB.getSQLValue(get_TrxName(), "SELECT MAX(cast(substring(FU,10,16)as int)) FROM DD_OTR WHERE AD_Client_ID="+p_AD_Client_ID+";");
 	}
}
