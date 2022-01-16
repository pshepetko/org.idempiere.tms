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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDOtrLine;
import org.idempiere.tms.model.MDDRouteLine_BP;
import org.idempiere.tms.model.MDDVehicle;

/**
 * Process create Transport Unit
 */
public class  VehicleAssignment extends CustomProcess  {

	private String		processVerNo = "[v.2.00] ";
	private int 		p_DD_Vehicle_ID=0;
	private int 		p_DD_VehicleType_ID=0;
	private int 		vh_count=0;	
	private  int count_vh=1;
	private int 		p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	private MDDVehicle  vh =null;
	
	private String whereClause ="";
	
	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	private boolean p_byRoute;
	
	private String debug ="";
	
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
				else if (name.equals("byRoute"))
				{    
					p_byRoute = para[i].getParameterAsBoolean();        
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
		if (p_byRoute)
		{
			assignRoutes();
		}
		
		assignVH();
		
		return processVerNo+result+count_vh+"|";
	}
	
	public void assignRoutes()  {
		ArrayList <Object> parameters = new ArrayList<Object>();	
		
		StringBuffer whereClause = new StringBuffer(" AD_Client_ID=? AND DD_Route_ID IS NULL ");
		parameters.add(getAD_Client_ID());

		List <MDDOtrLine> otrls = new Query(getCtx(), MDDOtrLine.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(parameters)
										.setOrderBy(" tu_code")
										.list(); 
			for(MDDOtrLine otrl : otrls)
			{
				//search by BP for debug
			int route_bp_id = DB.getSQLValue(get_TrxName(), 
					  "SELECT COALESCE(r.DD_RouteLine_BP_ID,0)  FROM "
					  + "(SELECT MIN(DD_RouteLine_BP_ID) AS DD_RouteLine_BP_ID  FROM DD_RouteLine_BP "
					+ "WHERE C_BPartner_ID=?) AS r;",otrl.getDD_OTR().getC_BPartner_ID());
				if (route_bp_id >0) {
					MDDRouteLine_BP m_otrl_bp = new MDDRouteLine_BP(Env.getCtx(),route_bp_id,get_TrxName());     
					
					//update route
					otrl.setDD_Route_ID(m_otrl_bp.getDD_RouteLine().getDD_Route_ID());
					otrl.setSeqNo(m_otrl_bp.getDD_RouteLine().getSeqNo());
					otrl.saveEx();
				}
			 
			}
	}
	
	public void assignVH()  {

		int last_route_id=0;
		Date last_date=null;
		BigDecimal vh_weight = Env.ZERO;
		BigDecimal vh_volume = Env.ZERO;
		ArrayList <Object> parameters = new ArrayList<Object>();	
		
		StringBuffer whereClause = new StringBuffer(" AD_Client_ID=? ");
		parameters.add(getAD_Client_ID());

		List <MDDOtrLine> otrls = new Query(getCtx(), MDDOtrLine.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(parameters)
										.setOrderBy(" DatePromised, DD_Route_ID , TU_Code ")
										.list(); 
			for(MDDOtrLine otrl : otrls)
			{	
				if (count_vh==0 ) {
				vh =new MDDVehicle(getCtx(), getVehicle(otrl.getDD_OTR().getDatePromised()), get_TrxName());
				last_route_id=otrl.getDD_Route_ID();
				last_date = new Date (otrl.getDatePromised().getTime());
				count_vh++;
				}
				
				if (last_route_id != otrl.getDD_Route_ID() || last_date.before(new Date (otrl.getDatePromised().getTime()))) {
					vh_weight = Env.ZERO;
					vh_volume = Env.ZERO;
					vh =new MDDVehicle(getCtx(), getVehicle(otrl.getDD_OTR().getDatePromised()), get_TrxName());	
					count_vh++;
				}
				
				vh_weight = vh_weight.add(otrl.getWeight());
				vh_volume = vh_volume.add(otrl.getVolume());
				
				
				if (vh_weight.compareTo(vh.getMaximumWeight())<=0) 
				{			
					otrl.setDD_Vehicle_ID(vh.get_ID());
					otrl.saveEx();
					last_route_id=otrl.getDD_Route_ID();
					last_date = new Date (otrl.getDatePromised().getTime());
					count_vh++;
				} else {
					vh =new MDDVehicle(getCtx(), getVehicle(otrl.getDD_OTR().getDatePromised()), get_TrxName());	
					otrl.setDD_Vehicle_ID(vh.get_ID());
					otrl.saveEx();
					count_vh++;

					vh_weight = otrl.getWeight();
					vh_volume = (otrl.getVolume());
					last_route_id=otrl.getDD_Route_ID();
					last_date = new Date (otrl.getDatePromised().getTime());
				}
				
				 vh_count++;
			}
	
		
	}
	
	
	public void calculateWeight1(MDDOtrLine m_otrl, MDDVehicle m_vehicle)
	{
		String fu_name="";	
		
		BigDecimal fu_qty = m_otrl.getQtyOrdered().subtract(m_otrl.getQtyDelivered());
		BigDecimal fu_volume = m_otrl.getWeight();
		BigDecimal fu_weight = m_otrl.getVolume();
		
		if(//fu_volume.setScale(3, RoundingMode.DOWN).compareTo(m_vehicle.getMaximumVolume()) >0 ||
				fu_weight.setScale(3, RoundingMode.DOWN).compareTo(m_vehicle.getMaximumWeight()) >0){	
			//then maxweight				
			BigDecimal qty_tu_volume = m_vehicle.getMaximumVolume().divide( fu_volume,0, RoundingMode.HALF_UP);
			BigDecimal qty_tu_weight = m_vehicle.getMaximumWeight().divide(fu_weight,0, RoundingMode.HALF_UP);				
			
			int qty_tu = 0;
			if (qty_tu_volume.intValue()>qty_tu_weight.intValue())
				qty_tu = qty_tu_volume.intValue();
			else 
				qty_tu = qty_tu_weight.intValue();					
			
			BigDecimal vh_weight2=fu_weight; 					BigDecimal vh_weight3=Env.ZERO;
//			BigDecimal vh_volume2=fu_volume;					
			BigDecimal vh_volume3=Env.ZERO;
			
			//add pallets
			for (int i = 0; i < qty_tu; i++) {	
				if(vh_weight2.compareTo(m_vehicle.getMaximumWeight())==1)
					vh_weight3=m_vehicle.getMaximumWeight();
				else 
					vh_weight3=vh_weight2;
								
				vh_volume3=vh_weight3.divide(m_otrl.getDD_OTR().getM_Product().getWeight(),2, RoundingMode.HALF_UP).multiply(m_otrl.getDD_OTR().getM_Product().getVolume());

					vh_weight2=vh_weight2.subtract(m_vehicle.getMaximumWeight());						
					vh_count++;  //fu_no++;
			}		
		} else	{//<then max			
			vh_count++;
				}
	}
	
	public int getVehicle(Timestamp DatePromised)
 	{	
		//Issue #3: Useless Parameter cause error (VehicleAssignment)
		if (p_DD_Vehicle_ID>0) 
			return p_DD_Vehicle_ID; 
		else
			return DB.getSQLValue(get_TrxName(), 
					"SELECT v.DD_Vehicle_ID FROM DD_Vehicle v WHERE AD_Client_ID=? AND DD_VehicleType_ID=? "
							+ " AND v.DD_Vehicle_ID NOT IN "
							+ "(SELECT DISTINCT(otrl.DD_Vehicle_ID) FROM DD_OTRLine otrl"
							+ " LEFT JOIN DD_Otr otr ON otr.DD_OTR_ID=otrl.DD_OTR_ID "
							+ " WHERE otrl.DD_Vehicle_ID IS NOT NULL "
							+ " AND CAST(otr.DatePromised AS date)=CAST('"+DatePromised+"' AS date))"
							+ " ORDER BY v.Value FETCH FIRST 1 ROWS ONLY;", getAD_Client_ID(),p_DD_VehicleType_ID );
 	}
}
