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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDFreight;
import org.idempiere.tms.model.MDDFreightAgreement;
import org.idempiere.tms.model.MDDFreightCost;
import org.idempiere.tms.model.MDDOTR;



/**
 * Process create m_inv
 */
public class createInvoiceFromFO extends CustomProcess  {

	private String				processVerNo = "[v.1.00] ";
	private int p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());

	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	private String whereClause ="";
	
//	private boolean p_isConsolidateDocument;
	private int 		p_C_DocType_ID=0;
	
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
				
				else if (name.equals("C_DocType_ID"))
					p_C_DocType_ID =  para[i].getParameterAsInt();
				
//				else if (name.equals("ConsolidateDocument")) 
//					p_isConsolidateDocument = para[i].getParameterAsBoolean();  
									
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
						
			if (p_DateFrom!=null && p_DateTo!=null) 
				whereClause =" otr.AD_Client_ID="+p_AD_Client_ID+" AND CAST(otr.DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ";
			else 
				whereClause =" otr.AD_Client_ID="+p_AD_Client_ID+" AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= " +getAD_PInstance_ID()+ " AND T_Selection.T_Selection_ID=otr.DD_OTR_ID) ";
	}
	

	@Override
	protected String doIt() throws Exception {
		int count_inv=0;
		String sql = "SELECT DISTINCT ON(otr.DatePromised,otr.M_Warehouse_ID,fol.DD_Freight_ID) "
				+ " otr.DD_OTR_ID, "//1
				+ " fol.DD_Freight_ID "//2
				+ " FROM DD_OTR otr "
				+ " LEFT JOIN DD_FreightLine fol ON fol.DD_FreightLine_ID=otr.DD_FreightLine_ID "
				+ " WHERE " +whereClause
				+ " AND otr.DD_FreightLine_ID IS NOT NULL;";

 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{
 		 			MDDFreight m_fo = new MDDFreight(Env.getCtx(),rs.getInt(2),get_TrxName());	
 		 			//create invoice header
 		 			MInvoice m_inv = createInvoiceHeader (m_fo);
 		 			
 		 			//create invoice line
 		 			createInvoiceline(m_fo,m_inv);
 		 			
 					count_inv++;
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
			
		return  processVerNo+" Invoices created:"+count_inv;
	}
	
	
	/**
	 * 	Create Invoice Line from Shipment
	 *	@param Freight Order 
	 */
	private MInvoice createInvoiceHeader(MDDFreight m_fo)
	{
			MInvoice m_inv = new MInvoice (getCtx(), 0, get_TrxName());
			m_inv.setAD_Org_ID(m_fo.getAD_Org_ID());
			m_inv.setIsSOTrx(false);
			m_inv.setDateInvoiced(m_fo.getDatePromised());
			m_inv.setDateAcct(m_fo.getDatePromised());
			m_inv.setC_BPartner_ID(m_fo.getC_BPartner_ID());
			m_inv.setPaymentRule(MInvoice.PAYMENTRULE_OnCredit);
			m_inv.setC_DocType_ID(p_C_DocType_ID);
			m_inv.setC_DocTypeTarget_ID(p_C_DocType_ID);
			m_inv.setDocStatus("DR");
			m_inv.setDocAction("CO");
			m_inv.setDescription("Created from Freight Order No:" + m_fo.getDocumentNo());
			m_inv.saveEx();	
			return m_inv;
	}
	
	/**
	 * 	Create Invoice Line from Shipment
	 *	@param Freight Order Header
	 *	@param Invoice Header
	 */
	private void createInvoiceline(MDDFreight m_fo, MInvoice m_inv)
	{		
		List <MDDFreightCost> fcosts = new Query(getCtx(), MDDFreightCost.Table_Name, MDDFreightCost.COLUMNNAME_DD_Freight_ID+"="+m_fo.getDD_Freight_ID(), get_TrxName())
				.setOrderBy(" ORDER BY DD_Freight_Cost_ID ")
				.list(); 
		for(MDDFreightCost fcost : fcosts)
		{
			MInvoiceLine m_invline = new MInvoiceLine (m_inv);
			m_invline.setC_Charge_ID(fcost.getC_Charge_ID());
			m_invline.setQtyInvoiced(fcost.getQty());
			m_invline.setQtyEntered(fcost.getQty());
			m_invline.setPrice(fcost.getPrice());
			m_invline.setLineNetAmt();			
			m_invline.setLine(10);
			
			if (!m_invline.save())
				throw new IllegalStateException("Could not create Invoice Line (o)");
			if (log.isLoggable(Level.FINE)) log.fine(m_invline.toString());
		}			
	}
	
}
