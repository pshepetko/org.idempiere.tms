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
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.idempiere.tms.base.CustomProcess;

/**  
 * Process create SO
 */
public class createSO extends CustomProcess  {

	private String		processVerNo = "[v.1.01] ";
	private int 		p_M_Product_ID=0;
	private int 		so_count=0;	
	private int 		soline_count=0;	
	private int 		p_PlanningHorizon=0;
	private Timestamp currentDate = Env.getContextAsDate(Env.getCtx(), "#Date");
	
	@Override
	protected void prepare() {
			//	Parameter
			ProcessInfoParameter[] para = getParameter();
			for (int i = 0; i < para.length; i++)
			{
				String name = para[i].getParameterName();
				if (para[i].getParameter() == null && para[i].getParameter_To() == null)
					;
				else if (name.equals("M_Product_ID"))
					p_M_Product_ID =  para[i].getParameterAsInt();
				else if (name.equals("PlanningHorizon"))
					p_PlanningHorizon =  para[i].getParameterAsInt();
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
	}
	
	@Override
	protected String doIt() throws Exception {
		String result="Created Sales Orders: "; 
		Random rand = new Random();
		int range_min=19;
		int range_max=199;
		
 		//-----------------------
 		for (int i = 1; i < 9; i++) {	
 
 		// Create Order	
		MOrder m_order = new MOrder(getCtx(), 0 , get_TrxName());
		m_order.setAD_Org_ID(12);//hard code for demo
		m_order.setDateOrdered(currentDate);
		m_order.setDatePromised(currentDate);
		m_order.setIsSOTrx(true);
		m_order.setC_DocTypeTarget_ID(132);//hard code for demo
		
		m_order.setC_BPartner_ID(getBP(i));//hard code for demo
/*		
		// BP select
		if (i==1) {
			updateBP(112);
			m_order.setC_BPartner_ID(112);//hard code for demo
		}
		else if (i==2) {
			updateBP(117);
			m_order.setC_BPartner_ID(117);//hard code for demo
		}
		else if (i==3) {
			updateBP(118);
			m_order.setC_BPartner_ID(118);//hard code for demo
		}
*/		
		m_order.setAD_User_ID(100);//hard code for demo
		m_order.setM_Warehouse_ID(104);//hard code for demo
		m_order.setDescription("This document was creted for test TMS plugin only!");
		m_order.setDocStatus(MOrder.STATUS_InProgress);
		m_order.setDocAction(MOrder.DOCACTION_Complete);
		m_order.saveEx();
		so_count++;
		// Create OrderLines		
	 		for (int ii = 0; ii < p_PlanningHorizon; ii++) {	
				MOrderLine m_oline = new MOrderLine(m_order);
				m_oline.setDatePromised(TimeUtil.addDays(currentDate, ii));
				m_oline.setM_Product_ID(p_M_Product_ID, true);
				m_oline.setQty(BigDecimal.valueOf(range_min +  rand.nextInt(range_max - range_min + 1)));
				m_oline.setPrice();
				m_oline.setTax();
				m_oline.saveEx();	
				soline_count++;
	 		}
 	}
		return  processVerNo+result+(so_count)+" Lines:"+soline_count;
	} 
	
	protected void updateBP(int bp_id) {
		MBPartner m_bp = new MBPartner(getCtx(), bp_id , get_TrxName());
		 m_bp.setSOCreditStatus("X");
		 m_bp.saveEx();
	}
	 	
	public int getBP(int count) {
    String bp_val=null;
    int bp_id=0;
		switch (count) {
        case  (6):
        	bp_val = "C&W";
            break;
        case (7):
        	bp_val = "JoeBlock";
            break;
        case (8):
        	bp_val = "Standard";
            break;
            
        default:
        	bp_val = "customer"+count;
            break;
    }		bp_id=DB.getSQLValue(get_TrxName(), "SELECT C_BPartner_ID FROM C_BPartner WHERE value='"+bp_val+"';");
		updateBP(bp_id);	
 return bp_id;
	}
	 
}
