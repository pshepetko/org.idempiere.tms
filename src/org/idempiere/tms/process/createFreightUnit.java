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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDOTR;
import org.idempiere.tms.model.MDDOtrLine;
import org.idempiere.tms.model.MDDTransportUnit;

import java.math.RoundingMode;


/**
 * Process create Freight Unit
 */
public class createFreightUnit extends CustomProcess  {

	private String		processVerNo = "[v.2.00] ";
	private BigDecimal  fu_volume=null;
	private BigDecimal  fu_weight=null;
	private int 		p_DD_TransportUnit_ID=0;
	private int 		fu_count=0;	
	private int 		fu_no=1;

	
	private MDDTransportUnit tu =null;
	private MDDOTR otr =null;
	
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
				else if (name.equals("DatePromised"))
				{
					p_DateFrom = (Timestamp)para[i].getParameter();
					p_DateTo = (Timestamp)para[i].getParameter_To();
				}
				else
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}

	@Override
	protected String doIt() throws Exception {
		fu_count=0;
		String result="Created Freight Units: ";
	
		// check TU
	       if (p_DD_TransportUnit_ID>0)
	    	   tu =new MDDTransportUnit(getCtx(), p_DD_TransportUnit_ID, get_TrxName());
	       else
	    	   return "Transport Unit didn't select!";
	       
			ArrayList <Object> parameters = new ArrayList<Object>();	
			
			StringBuffer whereClause = new StringBuffer(" AD_Client_ID=? ");
			parameters.add(getAD_Client_ID());
			
			if (p_DateFrom!=null && p_DateTo!=null) 
			{

				whereClause.append(" AND DatePromised BETWEEN ? AND ? ");
				parameters.add(p_DateFrom);
				parameters.add(p_DateTo);
			}
			else 
			{
				whereClause.append(" AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=DD_OTR_ID) ");	
				parameters.add(getAD_PInstance_ID());
			}


			List <MDDOTR> otrs = new Query(getCtx(), MDDOTR.Table_Name, whereClause.toString(), get_TrxName())
											.setParameters(parameters)
											.setOrderBy(" ORDER BY datepromised ")
											.list(); 
				for(MDDOTR otr : otrs)
				{	
					createFU(otr.getDD_OTR_ID());				
				}
		
		return processVerNo+result+fu_count+"|"+debug;
	}
	
	public void createFU(int otr_id)
	{
		String fu_name="";	
		fu_volume=Env.ZERO;
		fu_weight=Env.ZERO;
		
		otr  =new MDDOTR(getCtx(), otr_id, get_TrxName());
	
		BigDecimal fu_qty = otr.getQtyOrdered().subtract(otr.getQtyDelivered());
		BigDecimal fu_volume = fu_qty.multiply(otr.getM_Product().getVolume().setScale(3, RoundingMode.DOWN));
		BigDecimal fu_weight = fu_qty.multiply(otr.getM_Product().getWeight().setScale(3, RoundingMode.DOWN));

		
		tu = new MDDTransportUnit(getCtx(), p_DD_TransportUnit_ID, get_TrxName());
		
		if(fu_volume.setScale(3, RoundingMode.DOWN).compareTo(tu.getMaximumVolume()) >0 ||
				fu_weight.setScale(3, RoundingMode.DOWN).compareTo(tu.getMaximumWeight()) >0){
			
			//>then max					
			//calc pallet qty
			BigDecimal qty_pallet_volume = fu_volume.divide(tu.getMaximumVolume(),0, RoundingMode.UP);
			BigDecimal qty_pallet_weight = fu_weight.divide(tu.getMaximumWeight(),0, RoundingMode.UP);				
			
			int qty_pallet = 0;
			if (qty_pallet_volume.intValue()>qty_pallet_weight.intValue())
				qty_pallet = qty_pallet_volume.intValue();
			else
				qty_pallet = qty_pallet_weight.intValue();					
			
			BigDecimal fu_weight2=fu_weight; 					BigDecimal fu_weight3=Env.ZERO;
//			BigDecimal fu_volume2=fu_volume;					
			BigDecimal fu_volume3=Env.ZERO;
			
			//add pallets
			for (int i = 0; i < qty_pallet; i++) {
				// get LastFU
				fu_no=getLastFU();
				fu_name="10"+otr.getM_WarehouseSource_ID()+String.format("%07d", fu_no+1);						
			
				if(fu_weight2.compareTo(tu.getMaximumWeight())==1)
					fu_weight3=tu.getMaximumWeight();
				else 
					fu_weight3=fu_weight2;
								
				fu_volume3=fu_weight3.divide(otr.getM_Product().getWeight(),2, RoundingMode.HALF_UP).multiply(otr.getM_Product().getVolume());

//				if(fu_volume2.compareTo(tu.getMaximumVolume())==1)
//					fu_volume3=tu.getMaximumVolume();
//				else 
//					fu_volume3=fu_volume2;
				
			addOTRLine(fu_name,
						fu_weight3.divide(otr.getM_Product().getWeight(),2, RoundingMode.HALF_UP),
						fu_volume3.setScale(3, RoundingMode.DOWN),
						fu_weight3.setScale(3, RoundingMode.DOWN),
						((fu_volume3.divide(tu.getMaximumVolume(),2, RoundingMode.HALF_DOWN)).multiply(Env.ONEHUNDRED)).setScale(0, RoundingMode.DOWN),
						((fu_weight3.divide(tu.getMaximumWeight(),2, RoundingMode.HALF_DOWN)).multiply(Env.ONEHUNDRED)).setScale(0, RoundingMode.DOWN));

					fu_weight2=fu_weight2.subtract(tu.getMaximumWeight());						
					fu_count++;  //fu_no++;
			}		
		} else	{//<then max
						
			fu_no=getLastFU();
			fu_name="10"+otr.getM_WarehouseSource_ID()+String.format("%07d", fu_no+1);
			
			addOTRLine(fu_name,otr.getQtyOrdered(),fu_volume,fu_weight,
					((fu_volume.divide(tu.getMaximumVolume(),2, RoundingMode.HALF_DOWN)).multiply(Env.ONEHUNDRED)).setScale(0, RoundingMode.DOWN),
					((fu_weight.divide(tu.getMaximumWeight(),2, RoundingMode.HALF_DOWN)).multiply(Env.ONEHUNDRED)).setScale(0, RoundingMode.DOWN));
			
			fu_count++;
				}
	}

	public String addOTRLine(String fu_Name,BigDecimal fu_Qty, BigDecimal fu_Volume,BigDecimal fu_Weight,BigDecimal Persent_Volume,BigDecimal Persent_Weight)
 	{
		MDDOtrLine m_otrline = new MDDOtrLine(Env.getCtx(),0,get_TrxName());
		m_otrline.setAD_Org_ID(otr.getAD_Org_ID());
		m_otrline.setDD_OTR_ID(otr.get_ID());
		m_otrline.setDatePromised(otr.getDatePromised());
		m_otrline.setQtyOrdered(fu_Qty);
		m_otrline.setQtyDelivered(Env.ZERO);//for debug
		m_otrline.setDD_TransportUnit_ID(p_DD_TransportUnit_ID);
		m_otrline.settu_code(fu_Name);
		m_otrline.setVolume(fu_Volume);
		m_otrline.setWeight(fu_Weight);
		m_otrline.setPersent_Volume(Persent_Volume);
		m_otrline.setPersent_Weight(Persent_Weight);
		m_otrline.saveEx();
		return "";
 	}
		
	public int getLastFU()
 	{	
	return DB.getSQLValue(get_TrxName(), "SELECT COALESCE(MAX(CAST(SUBSTRING(tu_code,10,16)AS INT)),0) FROM DD_OTRLine WHERE AD_Client_ID="+getAD_Client_ID()+";");
 	}
		
	
}
