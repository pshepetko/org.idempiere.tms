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
import java.util.logging.Level;

import org.compiere.model.MInOutLine;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrderLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MDDOrderLine;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDOTR;

/**
 * Process create OTR
 */
public class createOTR extends CustomProcess  {

	private String				processVerNo = "[v.2.05] ";
 	private int p_AD_Client_ID= Env.getAD_Client_ID(Env.getCtx());

	/** Date From				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
 	private boolean p_isSO;
 	private boolean p_isPO;
 	private boolean p_isDO;
 	private boolean p_isMS;
 	private boolean p_isMR;
 	private boolean p_isMM;
	private boolean p_DeleteOld;
	private String p_DeliveryViaRule;
	
	private String p_DocType=null;
		
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
				else if (name.equals("DeliveryViaRule"))  
				{					
					p_DeliveryViaRule =  para[i].getParameterAsString();
				}	
				
				else if (name.equals("C_DocType_ID"))  
				{					
					p_DocType =  para[i].getParameterAsString();
				}
				else if (name.equals("DeleteOld"))
				{    
					p_DeleteOld = para[i].getParameterAsBoolean();        
				}
				
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
			
	}

	@Override
	protected String doIt() throws Exception {
		
		StringBuffer sb_sql = new StringBuffer();
		int count=0;
		int count_so=0;
		int count_po=0;
		int count_do=0;
		int count_ms=0;
		int count_mr=0;
		int count_mm=0;
		String result=processVerNo;
		
		//delete all records- for debug only
		if (p_DeleteOld)
		{
			deleteOldRecords();
		}

		if (p_DocType != null) {
			String[] DocTypeArray = p_DocType.split(",");
			for (String number : DocTypeArray) {
				if (number.trim().equals("SOO")) p_isSO=true; 
				if (number.trim().equals("POO")) p_isPO=true;
				if (number.trim().equals("DOO")) p_isDO=true;
				
 				if (number.trim().equals("MMS")) p_isMS=true; 
 				if (number.trim().equals("MMR")) p_isMR=true;
 				if (number.trim().equals("MMM")) p_isMM=true;
			}
			
			int AD_Org_ID = 0;
			Timestamp DateOrdered = null;
			Timestamp DatePromised = null;
			int C_BPartner_ID = 0;
			int M_Warehouse_ID = 0;
			int M_WarehouseSource_ID = 0;
			int M_Product_ID = 0;
			int C_UOM_ID = 0;
			BigDecimal QtyOrdered = null;
			BigDecimal QtyDelivered = null;
			
			
			String ordertype="";
			int locationFrom_ID=0;
			int locationTo_ID=0;

			
			if (p_isSO || p_isPO)
			{
				sb_sql.append("SELECT l.C_OrderLine_ID, 0 AS DD_OrderLine_ID, 0 AS M_InOutLine_ID, 0 AS M_MovementLine_ID   ");//1
				sb_sql.append(" FROM C_OrderLine l ");
				sb_sql.append(" LEFT JOIN C_Order o ON o.C_Order_ID=l.C_Order_ID ");
				sb_sql.append(" WHERE o.DocStatus IN ('IP','CO','CL') ");
				sb_sql.append(" AND l.AD_Client_ID="+p_AD_Client_ID+" AND CAST(l.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");
		
				//add SO
				if (p_isSO && !p_isPO)
					sb_sql.append(" AND o.issotrx='Y' ");
				//add PO
				else if (p_isPO && !p_isSO)
					sb_sql.append(" AND o.issotrx='N' ");
				else if (p_isPO && p_isSO)
					sb_sql.append(" AND (o.issotrx='N' OR o.issotrx='Y') ");
				
				if (p_DeliveryViaRule!=null)
					sb_sql.append(" AND o.DeliveryViaRule='"+p_DeliveryViaRule+"' ");
			}	
			
			//add DO
			if  (p_isDO)
				{
				if (p_isSO || p_isPO)
					sb_sql.append(" UNION ALL "); 
				
					sb_sql.append("SELECT 0 AS C_OrderLine_ID, l.DD_OrderLine_ID, 0 AS M_InOutLine_ID, 0 AS M_MovementLine_ID "); 
					sb_sql.append("FROM DD_OrderLine l "); 
					sb_sql.append("LEFT JOIN DD_Order o ON o.DD_Order_ID=l.DD_Order_ID "); 
					sb_sql.append("WHERE o.DocStatus IN ('DR','IP','CO','CL')");
					sb_sql.append(" AND l.AD_Client_ID="+p_AD_Client_ID+" AND CAST(l.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");
	
					if (p_DeliveryViaRule!=null)
						sb_sql.append(" AND o.DeliveryViaRule='"+p_DeliveryViaRule+"' ");
				}

			// add Material InOut
			if (p_isMS || p_isMR)
			{
				if (p_isSO || p_isPO || p_isDO)
				    sb_sql.append(" UNION ALL "); 
				
				sb_sql.append("SELECT 0 AS C_OrderLine_ID, 0 AS DD_OrderLine_ID, iol.M_InOutLine_ID AS M_InOutLine_ID, 0 AS M_MovementLine_ID "); 
				sb_sql.append("FROM M_InOutLine iol "); 
				sb_sql.append("LEFT JOIN M_InOut io ON io.M_InOut_ID=iol.M_InOut_ID "); 
				sb_sql.append("WHERE io.DocStatus IN ('DR','IP','CO','CL')");
				sb_sql.append(" AND iol.AD_Client_ID="+p_AD_Client_ID+" AND CAST(io.MovementDate AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");

				//add SO
				if (p_isMS && !p_isMR)
					sb_sql.append(" AND io.issotrx='Y' ");
				//add PO
				else if (p_isMR && !p_isMS)
					sb_sql.append(" AND io.issotrx='N' ");
				else if (p_isMR && p_isMS)
					sb_sql.append(" AND (io.issotrx='N' OR io.issotrx='Y') ");
				
				if (p_DeliveryViaRule!=null)
					sb_sql.append(" AND io.DeliveryViaRule='"+p_DeliveryViaRule+"' ");
			}
			
			//add Material Movement
			if  (p_isMM)
				{
				if (p_isSO || p_isPO || p_isDO || p_isMS || p_isMR)
					sb_sql.append(" UNION ALL "); 
				
					sb_sql.append("SELECT 0 AS C_OrderLine_ID, 0 AS DD_OrderLine_ID, 0 AS M_InOutLine_ID, ml.M_MovementLine_ID AS M_MovementLine_ID "); 
					sb_sql.append("FROM M_MovementLine ml "); 
					sb_sql.append("LEFT JOIN M_Movement m ON m.M_Movement_ID=ml.M_Movement_ID "); 
					sb_sql.append("WHERE m.DocStatus IN ('DR','IP','CO','CL')");
					sb_sql.append(" AND ml.AD_Client_ID="+p_AD_Client_ID+" AND CAST(m.MovementDate AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");
	
					if (p_DeliveryViaRule!=null)
						sb_sql.append(" AND m.DeliveryViaRule='"+p_DeliveryViaRule+"' ");
				}

 		PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement(sb_sql.toString(), get_TrxName());
					rs = pstmt.executeQuery ();
					while(rs.next ())
					{
	 					if (rs.getInt(1)>0) // add SO/PO
						{
	 						MOrderLine m_oline = new MOrderLine(Env.getCtx(),rs.getInt(1),get_TrxName());
							
							AD_Org_ID = m_oline.getAD_Org_ID();
							DateOrdered = m_oline.getDateOrdered();
							DatePromised = m_oline.getDatePromised();
							C_BPartner_ID = m_oline.getC_BPartner_ID();
							M_Product_ID = m_oline.getM_Product_ID();
							C_UOM_ID = m_oline.getC_UOM_ID();
							QtyOrdered = m_oline.getQtyOrdered();
							QtyDelivered = m_oline.getQtyDelivered();
			 				
							if (m_oline.getC_Order().isSOTrx())
		 	 				{		
								M_Warehouse_ID = 0;
								M_WarehouseSource_ID = m_oline.getC_Order().getM_Warehouse_ID();
								locationFrom_ID=m_oline.getM_Warehouse().getC_Location_ID();
		 	 					locationTo_ID=m_oline.getC_BPartner_Location().getC_Location_ID();
		 	 					ordertype="SOO";
		 	 					count_so++;
		 	 				}
							else
							{
								M_Warehouse_ID =  m_oline.getC_Order().getM_Warehouse_ID();
								M_WarehouseSource_ID =0;
		 	 					locationFrom_ID=m_oline.getC_BPartner_Location().getC_Location_ID();
		 	 					locationTo_ID=m_oline.getM_Warehouse().getC_Location_ID();
		 	 					ordertype="POO";
		 	 					count_po++;
							}
						}
						else if (rs.getInt(2)>0) //add DO
						{
							MDDOrderLine m_doline = new MDDOrderLine(Env.getCtx(),rs.getInt(2),get_TrxName());
							
							AD_Org_ID = m_doline.getAD_Org_ID();
							DateOrdered = m_doline.getDateOrdered();
							DatePromised = m_doline.getDatePromised();
							C_BPartner_ID = m_doline.getDD_Order().getC_BPartner_ID();
							M_WarehouseSource_ID = m_doline.getM_Locator().getM_Warehouse_ID();
							M_Warehouse_ID = m_doline.getM_LocatorTo().getM_Warehouse_ID();
							M_Product_ID = m_doline.getM_Product_ID();
							C_UOM_ID = m_doline.getC_UOM_ID();
							QtyOrdered = m_doline.getQtyOrdered();
							QtyDelivered = m_doline.getQtyDelivered();					
							locationFrom_ID=m_doline.getM_Locator().getM_Warehouse().getC_Location_ID();
		 	 				locationTo_ID=m_doline.getM_LocatorTo().getM_Warehouse().getC_Location_ID();
		 	 				ordertype="DOO";
		 					count_do++;
						}
	 					
						else if (rs.getInt(3)>0) //add InOut
						{
							MInOutLine m_inoutline = new MInOutLine(Env.getCtx(),rs.getInt(3),get_TrxName());				
							
							AD_Org_ID = m_inoutline.getAD_Org_ID();
							DateOrdered = m_inoutline.getM_InOut().getMovementDate();
							DatePromised = m_inoutline.getM_InOut().getMovementDate();
							C_BPartner_ID = m_inoutline.getM_InOut().getC_BPartner_ID();							
							M_Product_ID = m_inoutline.getM_Product_ID();
							C_UOM_ID = m_inoutline.getC_UOM_ID();
							QtyOrdered = m_inoutline.getQtyEntered();
							QtyDelivered = Env.ZERO;
			 				
							if (m_inoutline.getM_InOut().isSOTrx())
		 	 				{		
								M_Warehouse_ID = 0;
								M_WarehouseSource_ID = m_inoutline.getM_InOut().getM_Warehouse_ID();
								locationFrom_ID=m_inoutline.getM_InOut().getM_Warehouse().getC_Location_ID();
		 	 					locationTo_ID=m_inoutline.getM_InOut().getC_BPartner_Location().getC_Location_ID();
		 	 					ordertype="MMS";
		 	 					count_ms++;
		 	 				}
							else
							{
								M_Warehouse_ID =  m_inoutline.getM_InOut().getM_Warehouse_ID();
								M_WarehouseSource_ID =0;
		 	 					locationFrom_ID=m_inoutline.getM_InOut().getC_BPartner_Location().getC_Location_ID();
		 	 					locationTo_ID=m_inoutline.getM_InOut().getM_Warehouse().getC_Location_ID();
		 	 					ordertype="MMR";
		 	 					count_mr++;
							}
		 					
						}
						else if (rs.getInt(4)>0) //add Material Movement
						{
							MMovementLine m_movementline = new MMovementLine(Env.getCtx(),rs.getInt(4),get_TrxName());
							
							AD_Org_ID = m_movementline.getAD_Org_ID();
							DateOrdered = m_movementline.getM_Movement().getMovementDate();
							DatePromised = m_movementline.getM_Movement().getMovementDate();
							C_BPartner_ID = m_movementline.getM_Movement().getC_BPartner_ID();
							M_WarehouseSource_ID = m_movementline.getM_Locator().getM_Warehouse_ID();
							M_Warehouse_ID = m_movementline.getM_LocatorTo().getM_Warehouse_ID();
							M_Product_ID = m_movementline.getM_Product_ID();
							C_UOM_ID = m_movementline.getM_Product().getC_UOM_ID();
							QtyOrdered = m_movementline.getMovementQty();
							QtyDelivered = Env.ZERO;					
							locationFrom_ID=m_movementline.getM_Locator().getM_Warehouse().getC_Location_ID();
		 	 				locationTo_ID=m_movementline.getM_LocatorTo().getM_Warehouse().getC_Location_ID();
		 	 				ordertype="MMM";
		 					count_mm++;
						}
						
						MDDOTR m_otr = new MDDOTR(Env.getCtx(),0,get_TrxName());
						m_otr.setAD_Org_ID(AD_Org_ID);
						m_otr.setDateOrdered(DateOrdered);
						m_otr.setDatePromised(DatePromised);
						m_otr.setC_BPartner_ID(C_BPartner_ID);
						m_otr.setM_Warehouse_ID(M_Warehouse_ID);
						m_otr.setM_WarehouseSource_ID(M_WarehouseSource_ID);
						m_otr.setM_Product_ID(M_Product_ID);
						m_otr.setC_UOM_ID(C_UOM_ID);
						m_otr.setQtyOrdered(QtyOrdered);
						m_otr.setQtyDelivered(QtyDelivered);
						m_otr.setC_OrderLine_ID(rs.getInt(1));
						m_otr.setDD_OrderLine_ID(rs.getInt(2));
						m_otr.setM_InOutLine_ID(rs.getInt(3));
						m_otr.setM_MovementLine_ID(rs.getInt(4));
						m_otr.setOrderType(ordertype);
						m_otr.setDescription("line created by TMS process.");
						m_otr.setC_LocFrom_ID(locationFrom_ID);
						m_otr.setC_LocTo_ID(locationTo_ID);
						m_otr.saveEx();	
						count++;
					}
				}
				catch (Exception e)
				{
					log.log (Level.SEVERE, sb_sql.toString(), e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; 	pstmt = null;
				}
  		//-----------------------
			result+=" Created O/DTRs lines "+count+" from SO:"+count_so+"/PO:"+count_po+"/DO:"+count_do+"/MS:"+count_ms+"/MR:"+count_mr+"/MM:"+count_mm;	
		//if isEmpty		
		} else 
			result+=" Error: Document Type is empty!";
		
		return  result;
	}
  
	private void deleteOldRecords() 
	{
		StringBuffer whereClause = new StringBuffer(" AD_Client_ID="+p_AD_Client_ID+" AND CAST(DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");
		StringBuffer sql_delete = new StringBuffer("DELETE FROM DD_OTRLine WHERE DD_OTR_ID IN (SELECT DD_OTR_ID FROM DD_OTR WHERE "+whereClause.toString()+ ");"); 
		sql_delete.append("DELETE FROM DD_OTR WHERE "+whereClause.toString()+ ";"); 			
		sql_delete.append("DELETE FROM DD_Freight_Stop WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
		sql_delete.append("DELETE FROM DD_Freight_Cost WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
		sql_delete.append("DELETE FROM DD_FreightLine WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
		sql_delete.append("DELETE FROM DD_Freight WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
		DB.executeUpdateEx(sql_delete.toString(),get_TrxName());
	}
}
