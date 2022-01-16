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
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.model.MDDCalculationMethod;
import org.idempiere.tms.model.MDDCalculationMethodLine;
import org.idempiere.tms.model.MDDFreight;
import org.idempiere.tms.model.MDDFreightAgreement;
import org.idempiere.tms.model.MDDFreightCost;


/**  
 * Process Freight Cost Calculation
 */
public class FreightCostCalculation extends CustomProcess  {

	private String		processVerNo = "[v.1.01] ";
	private int 		fc_count=0;	
	private int 		fc_count_err=0;
	
	private int p_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());

	/** Date Drom				*/
	private Timestamp	p_DateFrom = null;
	/** Date To					*/
	private Timestamp	p_DateTo = null;
	
	private String whereClause ="";
	
	
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
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
			
			if (p_DateFrom!=null && p_DateTo!=null) 
				whereClause ="  AD_Client_ID="+p_AD_Client_ID+" AND CAST(DatePromised AS date) BETWEEN CAST('"+p_DateFrom+"' AS date) AND CAST('"+p_DateTo+"' AS date) ";
			else 
				whereClause =" AD_Client_ID="+p_AD_Client_ID+" AND EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE T_Selection.AD_PInstance_ID= " +getAD_PInstance_ID()+ " AND T_Selection.T_Selection_ID= DD_Freight_ID) ";

	}
	
	@Override
	protected String doIt() throws Exception {

		String result_msg="Freight Cost Calculation:";
		String warning_msg="";
		BigDecimal FreightAmt = Env.ZERO;
		BigDecimal FreightQty = Env.ZERO;
				
		String sql = "SELECT DD_Freight_ID FROM DD_Freight WHERE "+whereClause +";";

 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{										
							MDDFreight FOrder =new MDDFreight(getCtx(), rs.getInt(1), get_TrxName());	
							if (FOrder.getM_Shipper_ID()>0)
							{
								//delete old cost
								DB.executeUpdateEx("DELETE FROM DD_Freight_Cost WHERE DD_Freight_ID="+FOrder.get_ID(),get_TrxName());
								
								List <MDDFreightAgreement> fas = new Query(getCtx(), MDDFreightAgreement.Table_Name, MDDFreightAgreement.COLUMNNAME_M_Shipper_ID+"="+FOrder.getM_Shipper_ID(), get_TrxName())
										.setOrderBy(" ORDER BY line ")
										.list(); 
								for(MDDFreightAgreement fa : fas)
								{									
										MDDCalculationMethod cm =new MDDCalculationMethod(getCtx(), fa.getDD_Calculation_Method_ID(), get_TrxName());	
	
										List <MDDCalculationMethodLine> cmls = new Query(getCtx(), MDDCalculationMethodLine.Table_Name, MDDCalculationMethodLine.COLUMNNAME_DD_Calculation_Method_ID+"="+fa.getDD_Calculation_Method_ID(), get_TrxName())
												.setOrderBy(" ORDER BY line ")
												.list(); 
										for(MDDCalculationMethodLine cml : cmls)
										{
				 							//create Freight Cost
					 						MDDFreightCost FOrderCost = new MDDFreightCost (Env.getCtx(),0,get_TrxName());
					 							FOrderCost.setDD_Freight_ID(FOrder.get_ID());
						 						FOrderCost.setC_Charge_ID(cm.getC_Charge_ID());	
						 						
						 						//based Length of Route
						 						if (cml.getC_UOM().getUOMType().equals("LE")) //UOMType=Length
						 						{
						 							FreightQty = FOrder.getLength();

						 						}
						 						//based Weight
						 						else if (cml.getC_UOM().getUOMType().equals("WE")) //UOMType=Weight
						 						{
						 							FreightQty = FOrder.getWeight();
						 						}
						 						//based Duration
						 						else if (cml.getC_UOM().getUOMType().equals("TM")) //UOMType=Time
						 						{
						 							FreightQty = new BigDecimal(FOrder.getDuration());
						 						}
						 						//based Stops
						 						else if (cml.getC_UOM().getUOMType().equals("QA")) //UOMType=Quantity
						 						{
						 							FreightQty = FOrder.getStops();
						 						}
						 						//based Volume
						 						else if (cml.getC_UOM().getUOMType().equals("VD") || cml.getC_UOM().getUOMType().equals("VL")) //UOMType=Volume Dry or Volume Liquid

						 						{
						 							FreightQty = FOrder.getVolume();	
						 						}
						 						else
						 						{
						 							FreightQty = Env.ZERO;
							 					 	log.warning("Error because not exist UOMType for UOM="+cml.getC_UOM().getName()+" for "+cm.getName());
						 						}		
						 						
						 						if (FreightQty.compareTo(Env.ZERO) >= 1)
						 						{
							 						//FOrderCost.setQty(FreightQty);
						 							//FOrderCost.setPrice(cml.getRate().divide(cml.getQty()).multiply(cm.getC_Charge().getChargeAmt()));
						 							FOrderCost.setAmt((FreightQty
						 									.divide(cml.getQty()))
						 									.multiply(cml.getRate()
						 									.multiply(cm.getC_Charge().getChargeAmt())));
							 					 	FOrderCost.setDescription(cm.getName());
							 					 	
							 					 	if (cm.getC_Charge().getChargeAmt().compareTo(Env.ONE)==0)
							 					 	{
							 					 		FOrderCost.setQty(FreightQty);
							 					 		FOrderCost.setPrice(cml.getRate().divide(cml.getQty()).multiply(cm.getC_Charge().getChargeAmt()));
							 					 	} else
							 					 	{	
							 					 		FOrderCost.setQty(FOrderCost.getAmt().divide(cm.getC_Charge().getChargeAmt()));
							 					 		FOrderCost.setPrice(cm.getC_Charge().getChargeAmt());
							 					 	}
							 					 	
							 						FOrderCost.saveEx();
							 						FreightAmt=FreightAmt.add(FOrderCost.getAmt());
							 						FreightQty=Env.ZERO;
						 						}
										}

								}
								fc_count++;
								
	 							FOrder.setFreightCostRule("C");
								FOrder.setFreightAmt(FreightAmt);
	 							FOrder.saveEx();
	 							FreightAmt=Env.ZERO;
							}
							else	
							{
							log.warning("FreightCostCalculationERROR: Shipper is not exist in Freight Order No:"+FOrder.getDocumentNo()+"!");
							fc_count_err++;
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
			if (fc_count_err>0) 
				warning_msg=" For detail you can see Warning log!";
			
		return processVerNo+result_msg+fc_count+" [error:"+fc_count_err+"]."+warning_msg;
	} 
 
	 
}
