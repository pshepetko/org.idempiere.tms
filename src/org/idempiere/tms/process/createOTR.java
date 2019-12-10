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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import org.compiere.model.MOrderLine;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MDDOrderLine;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDFreight;
import org.idempiere.tms.model.MDDOTR;

/**
 * Process create OTR
 */
public class createOTR extends CustomProcess  {

	private String				processVerNo = "[v.1.03] ";
 	private int p_AD_Client_ID= Env.getAD_Client_ID(Env.getCtx());

	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	private boolean p_isSO;
	private boolean p_isPO;
	private boolean p_isDO;
	private boolean p_DeleteOld;
	private String p_DeliveryViaRule;
	
	MOrderLine m_oline =null;
	MDDOrderLine m_doline =null;
	
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
				else if (name.equals("isSO"))
				{    
					p_isSO = para[i].getParameterAsBoolean();        
				}
				else if (name.equals("isPO"))
				{    
					p_isPO = para[i].getParameterAsBoolean();        
				}
				else if (name.equals("isDO"))
				{    
					p_isDO = para[i].getParameterAsBoolean();        
				}
				else if (name.equals("DeleteOld"))
				{    
					p_DeleteOld = para[i].getParameterAsBoolean();        
				}
				else if (name.equals("DeliveryViaRule"))  
				{					
					p_DeliveryViaRule =  para[i].getParameterAsString();
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
		String result="Created OTRs lines ";
		
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


		
		//delete all records- for debug only
		if (p_DeleteOld)
		{
			StringBuffer whereClause = new StringBuffer(" AD_Client_ID="+p_AD_Client_ID+" AND CAST(DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");
			StringBuffer sql_delete = new StringBuffer("DELETE FROM DD_OTR WHERE "+whereClause.toString()+ ";"); 			
			sql_delete.append("DELETE FROM DD_Freight_Stop WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
			sql_delete.append("DELETE FROM DD_Freight_Cost WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
			sql_delete.append("DELETE FROM DD_FreightLine WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
			sql_delete.append("DELETE FROM DD_Freight WHERE DD_Freight_ID IN (SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause.toString()+ ");");
			DB.executeUpdateEx(sql_delete.toString(),get_TrxName());
			
			//Delete Freight Order with Draft Status 
			//StringBuffer whereClausePO = new StringBuffer(whereClause.toString()+" AND DocStatus='DR';");
			//deletePO(MDDFreight.Table_Name, whereClausePO.toString());

		}
		
		if (p_isSO || p_isPO)
		{
			sb_sql.append("SELECT l.C_OrderLine_ID, 0 AS DD_OrderLine_ID ");//1
			sb_sql.append(" FROM C_OrderLine l ");
			sb_sql.append(" LEFT JOIN C_Order o ON o.C_Order_ID=l.C_Order_ID ");
			sb_sql.append(" WHERE o.DocStatus IN ('CO','CL') ");
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
				sb_sql.append("UNION ALL "); 
			
				sb_sql.append("SELECT  0 AS C_OrderLine_ID, l.DD_OrderLine_ID "); 
				sb_sql.append("FROM DD_OrderLine l "); 
				sb_sql.append("LEFT JOIN DD_Order o ON o.DD_Order_ID=l.DD_Order_ID "); 
				sb_sql.append("WHERE o.DocStatus IN ('DR','IP','CO','CL')");
				sb_sql.append(" AND l.AD_Client_ID="+p_AD_Client_ID+" AND CAST(l.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ");

				if (p_DeliveryViaRule!=null)
					sb_sql.append(" AND o.DeliveryViaRule='"+p_DeliveryViaRule+"' ");
			}
		
		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sb_sql.toString(), get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{
 					if (rs.getInt(1)>0)
					{
						m_oline = new MOrderLine(Env.getCtx(),rs.getInt(1),get_TrxName());
						
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
					else if (rs.getInt(2)>0)
					{
						m_doline = new MDDOrderLine(Env.getCtx(),rs.getInt(2),get_TrxName());
						
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
		
		
		return processVerNo+result+count+" from SO:"+count_so+"/PO:"+count_po+"/DO:"+count_do;
	}
 /*
	private void deletePO(String tableName, String whereClause ) throws SQLException
	{
		// TODO: refactor this method and move it to org.compiere.model.Query class
		POResultSet<PO> rs = new Query(getCtx(), tableName, whereClause, get_TrxName())
									.scroll();
		try {
			while(rs.hasNext()) {
				rs.next().deleteEx(true);
			}
		}
		finally {
			rs.close();
		}
		commitEx();
	}
*/	
}
