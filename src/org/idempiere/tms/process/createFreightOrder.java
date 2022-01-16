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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import org.compiere.model.MCharge;
import org.compiere.model.MShipper;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDFreight;
import org.idempiere.tms.model.MDDFreightLine;
import org.idempiere.tms.model.MDDFreightStop;
import org.idempiere.tms.model.MDDOtrLine;

/**
 * Process create Freight Order
 */
public class createFreightOrder extends CustomProcess  {

	private String		processVerNo = "[v.1.03] ";
	private int p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	
	private MDDFreight FOrder = null;
	
	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	private String whereClause ="";
	
	
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
				else if (name.equals("DatePromised"))
				{
					p_DateFrom = (Timestamp)para[i].getParameter();
					p_DateTo = (Timestamp)para[i].getParameter_To();
				}
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
			
			if (p_DateFrom!=null && p_DateTo!=null) 
				whereClause =" otrl.AD_Client_ID="+p_AD_Client_ID+" AND CAST(otr.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ";
			else 
				whereClause =" otrl.AD_Client_ID="+p_AD_Client_ID+" AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= " +getAD_PInstance_ID()+ " AND T_Selection.T_Selection_ID=otr.DD_OTR_ID) ";
	}
	
	
	@Override
	protected String doIt() throws Exception {
		String result="Created Freight Orders: ";
		int vh_last=0;
		int bp_last=0;
		int route_last=0;
		Date date_last=null;
		
		int count_frOrder=0;
		int count_FOrderLine=0;
		int count_FOrderStops=0;
		
		BigDecimal fo_weight_amt = Env.ZERO;
		BigDecimal fo_volume_amt = Env.ZERO;
		
		int doctype=getDocumentType(p_AD_Client_ID);//code for dev
//		int shipper=getShipper(p_AD_Client_ID);//code for dev

		int charge_id=getCharge(p_AD_Client_ID);//code for dev		
		MCharge charge = new MCharge (Env.getCtx(),charge_id,get_TrxName()); 						


		String sql = "SELECT DISTINCT ON(otrl.DatePromised,otrl.DD_Route_ID,otrl.DD_Vehicle_ID,otrl.tu_code)  "  
				+ " otrl.DD_OTRLine_ID "//1
				+ " FROM DD_OTRLine  otrl"
				+ " LEFT JOIN DD_OTR otr ON otr.DD_OTR_ID = otrl.DD_OTR_ID "
				+ " WHERE "+whereClause
				+ " ORDER BY otrl.DatePromised,otrl.DD_Vehicle_ID;";
		
 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{					
					MDDOtrLine m_otrl = new MDDOtrLine(Env.getCtx(),rs.getInt(1),get_TrxName());
					//--------------------

 		 			if (vh_last != m_otrl.getDD_Vehicle_ID() || date_last.before(new Date(m_otrl.getDatePromised().getTime()))) 
 		 				{	 	
		 		 					//update FO header
		 		 				if (!rs.isFirst()) {
		 		 						updateFOHeader(FOrder, fo_volume_amt, fo_weight_amt, new BigDecimal(count_FOrderStops));
		 								fo_weight_amt = Env.ZERO; fo_volume_amt = Env.ZERO; count_FOrderStops=0;
		 		 					}
 		 				
 							//create Freight Order for RouteList
							FOrder = new MDDFreight (Env.getCtx(),0,get_TrxName());
 							FOrder.setAD_Org_ID(m_otrl.getAD_Org_ID());
 							FOrder.setDatePromised(m_otrl.getDatePromised());
 							FOrder.setDateOrdered(m_otrl.getDatePromised());
 							FOrder.setC_DocType_ID(doctype);
 							
 							int snipper_id = getShipper(p_AD_Client_ID,m_otrl.getDD_Vehicle_ID());
 							if (snipper_id>0) {								
	 							FOrder.setM_Shipper_ID(snipper_id); 
	 							MShipper m_shipper = new MShipper(Env.getCtx(),snipper_id,get_TrxName());
	 							if (m_shipper.getC_BPartner_ID()>0)
	 								FOrder.setC_BPartner_ID(m_shipper.getC_BPartner_ID());
 							}
 							FOrder.setFreightAmt(Env.ONE);
 							FOrder.setDD_Vehicle_ID(m_otrl.getDD_Vehicle_ID());
 							
 							if (getDriver(m_otrl.getDD_Vehicle_ID())>0)
 								FOrder.setDD_Driver_ID(getDriver(m_otrl.getDD_Vehicle_ID()));
 							FOrder.setDateStartSchedule(TimeUtil.addMinutess(m_otrl.getDatePromised(),540+(count_frOrder*60)));
 							FOrder.setDateFinishSchedule(TimeUtil.addMinutess(m_otrl.getDatePromised(),900+((count_frOrder-1)*60)));
 							FOrder.setLength(Env.ONE);//hard code for debug
 							FOrder.setDuration(1);//hard code for debug
 							FOrder.setWeight(Env.ONE);//hard code for debug
 							FOrder.setVolume(Env.ONE);//hard code for debug
 							FOrder.setStops(Env.ONE);//hard code for debug
 							FOrder.setIsApproved(true);
 							FOrder.setFreightAmt(Env.ONE);
 							FOrder.saveEx();
 								
 								//update description
 							FOrder.setDescription(FOrder.getDocumentNo()+" Date:"+
 										new SimpleDateFormat("dd.MM.yyyy").format(FOrder.getDateOrdered())+
 										" Route:"+m_otrl.getDD_Route().getName()+
 										" Vehicle:"+m_otrl.getDD_Vehicle().getName());								
 							FOrder.saveEx();
 												  						
 								count_frOrder++;
 								count_FOrderLine=0;
		 				} 																				
 		 						MDDFreightLine FOrderLine = new MDDFreightLine (Env.getCtx(),0,get_TrxName());
 								FOrderLine.setDD_Freight_ID(FOrder.get_ID());
 								FOrderLine.setAD_Org_ID(FOrder.getAD_Org_ID());
 								FOrderLine.setShipDate(FOrder.getDatePromised());
 								FOrderLine.setWeight(m_otrl.getWeight());
 								FOrderLine.setweight_uom_id(m_otrl.getDD_TransportUnit().getWeight_UOM_ID());
 								FOrderLine.setVolume(m_otrl.getVolume());
 								FOrderLine.setvolume_uom_id(m_otrl.getDD_TransportUnit().getVolume_UOM_ID());
 								FOrderLine.setC_Charge_ID(charge.get_ID());
 								FOrderLine.setFreightAmt(charge.getChargeAmt());
 								FOrderLine.setTotalAmt(Env.ZERO);
 								FOrderLine.setLine(count_FOrderLine);
 								FOrderLine.setDD_TransportUnit_ID(m_otrl.getDD_TransportUnit_ID());
 								FOrderLine.setTU(m_otrl.gettu_code());
 								FOrderLine.setC_LocFrom_ID(m_otrl.getDD_OTR().getC_LocFrom_ID());
 								FOrderLine.setC_LocTo_ID(m_otrl.getDD_OTR().getC_LocTo_ID());
 								FOrderLine.setM_Product_ID(m_otrl.getDD_OTR().getM_Product_ID());
 								FOrderLine.setQtyOrdered(m_otrl.getQtyOrdered());
 								FOrderLine.setQtyDelivered(m_otrl.getQtyDelivered());
 								FOrderLine.setC_OrderLine_ID(m_otrl.getDD_OTR().getC_OrderLine_ID());
 								FOrderLine.saveEx();	
 								
 								
								fo_volume_amt = fo_volume_amt.add(m_otrl.getVolume());
 								fo_weight_amt  = fo_weight_amt.add(m_otrl.getWeight());

 								
 	 							//update DD_FreightLine_ID in DD_OTRLine
 								m_otrl.setDD_FreightLine_ID(FOrderLine.get_ID());
 								m_otrl.saveEx();	
 								
 								if (bp_last != m_otrl.getDD_OTR().getC_BPartner_ID() || count_FOrderLine == 0)
 								{
	 	 							MDDFreightStop FOrderStop = new MDDFreightStop (Env.getCtx(),0,get_TrxName());
	 	 							FOrderStop.setDD_Freight_ID(FOrder.get_ID());
	 	 							FOrderStop.setAD_Org_ID(FOrder.getAD_Org_ID());
	 	 							FOrderStop.setC_LocFrom_ID(m_otrl.getDD_OTR().getC_LocFrom_ID());
	 	 							FOrderStop.setC_LocTo_ID(m_otrl.getDD_OTR().getC_LocTo_ID());
	 	 							FOrderStop.setC_BPartner_ID(m_otrl.getDD_OTR().getC_BPartner_ID());
	 	 						 	FOrderStop.setSequence(new BigDecimal(m_otrl.getSeqNo()));
	 	 							FOrderStop.saveEx();
	 	 							
	 	 							count_FOrderStops++;
 								}
 							count_FOrderLine++;						
 				 			vh_last = m_otrl.getDD_Vehicle_ID();
 				 			bp_last = m_otrl.getDD_OTR().getC_BPartner_ID();
 				 			route_last = m_otrl.getDD_Route_ID();
 				 			date_last =  new Date(m_otrl.getDatePromised().getTime());  
 				 			
	 		 					//update FO header
		 		 				if (rs.isLast()) {
		 		 						updateFOHeader(FOrder, fo_volume_amt, fo_weight_amt, new BigDecimal(count_FOrderStops));
		 		 					}
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
		
		return processVerNo+result+count_frOrder +"|"+debug ;
	} 
	
	public void updateFOHeader(MDDFreight fo, BigDecimal volume_amt, BigDecimal weight_amt, BigDecimal stops_amt)
 	{			
			fo.setVolume(volume_amt);
			fo.setWeight(weight_amt);
			fo.setStops(stops_amt);
			fo.saveEx();  	
	}	
	
	public int getDocumentType(int AD_Client_ID)
 	{	
	return DB.getSQLValue (get_TrxName(), "SELECT MAX(C_DocType_ID) FROM C_DocType WHERE name='Freight Order' AND AD_Client_ID=?;",AD_Client_ID);
 	}
	
	public int getShipper(int AD_Client_ID, int DD_Vehicle_ID)
 	{	
	return DB.getSQLValue (get_TrxName(), "SELECT  MIN(sn.M_Shipper_ID) FROM M_Shipper sn "
			+ "LEFT JOIN DD_VehicleAssignment va ON va.M_Shipper_ID=sn.M_Shipper_ID  "
			+ "WHERE sn.AD_Client_ID=? AND va.DD_Vehicle_ID=? "
			+ "GROUP BY va.DD_Vehicle_ID", AD_Client_ID, DD_Vehicle_ID);
 	}
	
	public int getCharge(int AD_Client_ID)
 	{	
	return DB.getSQLValue (get_TrxName(), "SELECT MAX(C_Charge_ID) FROM C_Charge WHERE name LIKE '%Freight%' AND AD_Client_ID=?;",AD_Client_ID);
 	}
	
	public int getDriver(int DD_Vehicle_ID)
 	{	
	return DB.getSQLValue (get_TrxName(), 
			"SELECT COALESCE((SELECT MIN(DD_Driver_ID) FROM DD_DriverAssignment WHERE isActive='Y' AND AD_Client_ID=? AND DD_Vehicle_ID=?),0);"
			,getAD_Client_ID(),DD_Vehicle_ID);
 	}
}
