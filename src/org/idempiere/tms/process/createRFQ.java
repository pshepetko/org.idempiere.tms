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
import java.util.logging.Level;

import org.compiere.model.MRfQ;
import org.compiere.model.MRfQTopic;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.idempiere.tms.base.CustomProcess;

/**  
 * Process create Request For Quotation (RFQ)
 */
public class createRFQ extends CustomProcess  {

	private String		processVerNo = "[v.1.00] ";
	private int 		p_C_RfQ_Topic_ID=0;
	private int 		p_C_BP_Group_ID=0;
	private int 		rfq_count=1;	

	@Override
	protected void prepare() {
			//	Parameter
			ProcessInfoParameter[] para = getParameter();
			for (int i = 0; i < para.length; i++)
			{
				String name = para[i].getParameterName();
				if (para[i].getParameter() == null && para[i].getParameter_To() == null)
					;
				else if (name.equals("C_RfQ_Topic_ID"))
					p_C_RfQ_Topic_ID =  para[i].getParameterAsInt();
				else if (name.equals("C_BP_Group_ID"))
					p_C_BP_Group_ID =  para[i].getParameterAsInt();
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
	}
	
	@Override
	protected String doIt() throws Exception {
		int count=1;
		String tu_last="";
		String result="Created RFQs: ";

		MRfQTopic rfqtopic =new MRfQTopic(getCtx(), p_C_RfQ_Topic_ID, get_TrxName());
		
		String sql = "SELECT DISTINCT ON(otr.DatePromised,otr.M_Warehouse_ID,otr.tu) "
				+ " otr.DatePromised," //1
				+ " otr.M_Warehouse_ID, "//2
				+ " otr.C_BPartner_ID ,"//3
				+ " otr.fu,"//4
				+ " otr.volume,"//5
				+ " otr.weight, "//6
				+ " otr.tu, "//7
				+ " otr.c_LocFrom_ID,"//8
				+ " otr.c_LocTo_ID, "//9
				+ " otr.AD_Org_ID, "//10
				+ " otr.DD_OTR_ID "//11
				+ " FROM DD_OTR otr "
				+ " WHERE EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= " +getAD_PInstance_ID()
				+ " AND T_Selection.T_Selection_ID=otr.DD_OTR_ID);";

 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{
		 			if (!tu_last.equals(rs.getString(7))) 
		 				{
							MRfQ rfq =new MRfQ(getCtx(), 0, get_TrxName());	
							rfq.setAD_Org_ID(rs.getInt(10));
							rfq.setName("Daily Meal delivery");
							rfq.setSalesRep_ID(1000213);//hard code for dev
							rfq.setC_RfQ_Topic_ID(p_C_RfQ_Topic_ID);
							rfq.setQuoteType("S");
							rfq.setDateWorkStart(rs.getTimestamp(1));
							rfq.setDateResponse(rs.getTimestamp(1));
							rfq.setC_Currency_ID(120);
							rfq.setDescription("Meal delivery [TU:"+rs.getString(7)+"]");//" V:"+rs.getBigDecimal(5)+"|W:"+rs.getBigDecimal(6)+"]");
							rfq.saveEx();
							rfq_count++;
		 				}
		 			tu_last=rs.getString(7);
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
		return processVerNo+result+(rfq_count-1);
	} 
 
	 
}
