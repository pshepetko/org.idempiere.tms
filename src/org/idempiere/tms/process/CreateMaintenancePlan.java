package org.idempiere.tms.process;

import org.compiere.model.MAsset;
import org.compiere.model.MForecast;
import org.compiere.model.MForecastLine;
import org.compiere.model.MPeriod;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.model.MPPProductPlanning;
import org.idempiere.tms.base.CustomProcess;
import org.idempiere.tms.util.TimestampUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;


/**
 *	Process CreateForecastLine4eAM
 *	
 *  @author Peter Shepetko
 *  @version $Id: CreateForecastLine4eAM.java,v 1.0 2017/12/20 pshepetko Exp $
 *  
 * 
 */
public class CreateMaintenancePlan extends CustomProcess
{
 	private int p_AD_Client_ID= Env.getAD_Client_ID(Env.getCtx());
 	private int p_AD_User_ID= Env.getAD_User_ID(Env.getCtx());
	private int p_A_Asset_ID=0;
	private int p_M_Forecast_ID=0;
	private Timestamp p_DateValue = null;
	private int p_PlanningHorizon=0;
	private Boolean p_DeleteOld=false;
	private MForecast forecast = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para:getParameter())
		{
			String name = para.getParameterName();
			
			if (para.getParameter() == null)
				;
			else if (name.equals("PlanningHorizon"))
			{
				p_PlanningHorizon = para.getParameterAsInt();
			}	
			else if (name.equals("A_Asset_ID"))
			{
				p_A_Asset_ID = para.getParameterAsInt();
			}
			else if (name.equals("M_Forecast_ID"))
			{
				p_M_Forecast_ID = para.getParameterAsInt();
			}
			else if (name.equals("DeleteOld"))
			{    
				p_DeleteOld = para.getParameterAsBoolean();        
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}
	
	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		if (p_DateValue==null)  p_DateValue=TimestampUtil.now();
		
		String _result="[v1.01] <br>"; 
		String pmrule=null;
		Timestamp MaintenanceDate = null;
		Timestamp FinishMaintenanceDate = null;
		Timestamp NextMaintenanceDate = null;
		Timestamp LastMaintenanceDate = null;
		int LastMaintenanceUnit=0;
		int rate = 0;
		int rate_planning_end = 0;
		int rate_current = 0;
		int useunits=0;
		int counter1=1;
		int counter2=1;
		int counter_D=0;
		int counter_M=0;
		int counter_L=0;
		int unitscycles=0;
		int unit_current=0;
		
		MAsset asset = new MAsset(getCtx(),p_A_Asset_ID, get_TrxName());
		forecast = new MForecast(getCtx(),p_M_Forecast_ID, get_TrxName());
		
		if (p_DeleteOld)
		{
		DB.executeUpdateEx("DELETE FROM pp_mrp WHERE  m_forecastline_ID IN "+
				 " (SELECT m_forecastline_ID FROM m_forecastline WHERE m_product_ID="+asset.getM_Product_ID()+" AND M_Forecast_ID="+p_M_Forecast_ID+");", get_TrxName());
		commitEx();

		DB.executeUpdateEx(" DELETE FROM m_forecastline WHERE m_product_ID="+asset.getM_Product_ID()+" AND M_Forecast_ID="+p_M_Forecast_ID+";", get_TrxName());
		commitEx();
		}
				
		String sql_so = "SELECT a_asset_id, asset_maintenance_rule,  " + 
				" nextmaintenencedate, pp_product_planning_id, rate, unitscycles,  " + 
				" description, c_uom_id, validfrom, validto, a_asset_maintenance_id " + 
				" FROM a_asset_maintenance "
				+ " WHERE isActive='Y' AND A_Asset_ID="+p_A_Asset_ID
				+" AND a_asset_maintenance_id NOT IN (SELECT a_asset_maintenancerelated_id FROM a_asset_maintenance_related WHERE isActive='Y');";//related check
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql_so, null);
				rs = pstmt.executeQuery ();
				while(rs.next ())
				{
					MPPProductPlanning pp =new MPPProductPlanning(getCtx(),rs.getInt(4), get_TrxName());			
					
					pmrule=rs.getString("asset_maintenance_rule");
					if (pmrule.equals("D"))
					{
						FinishMaintenanceDate =TimeUtil.addDays(asset.getLastMaintenanceDate(), p_PlanningHorizon);
						MaintenanceDate =TimeUtil.addDays(asset.getLastMaintenanceDate(), rs.getInt("UnitsCycles"));//getPMRuleInt(p_A_Asset_ID, "UnitsCycles"));
					    while(MaintenanceDate.compareTo(FinishMaintenanceDate)<=0) {
							MForecastLine fcl =new MForecastLine(getCtx(),0, get_TrxName());
							fcl.setAD_Org_ID(pp.getAD_Org_ID());
							fcl.setM_Forecast_ID(p_M_Forecast_ID);
							fcl.setM_Product_ID(asset.getM_Product_ID());
							fcl.setM_Warehouse_ID(pp.getM_Warehouse_ID());
							fcl.setQty(Env.ONE);
							fcl.setC_Period_ID(getPeriodID(MaintenanceDate));
							fcl.setDatePromised(MaintenanceDate);
						//	fcl.set_ValueOfColumn("Description", "D");
							fcl.set_ValueOfColumn("pp_product_planning_id",  rs.getInt("pp_product_planning_id"));
//							fcl.set_ValueOfColumn("a_asset_maintenance_id",  rs.getInt("a_asset_maintenance_id"));

							fcl.saveEx(); 
							
							commitEx();				
							
							counter_D++;
							MaintenanceDate =TimeUtil.addDays(MaintenanceDate,rs.getInt("UnitsCycles"));//getPMRuleInt(p_A_Asset_ID, "UnitsCycles"));	
					      }
						_result+=" Created "+counter_D+" Forecat Line(s) for "+asset.getName()+" based Date Rules!<br>";	
					}
					
					if (pmrule.equals("M"))
					{
						useunits=asset.getUseUnits();
						rate=rs.getInt("Rate");
						unitscycles=rs.getInt("UnitsCycles");//getPMRuleInt(p_A_Asset_ID, "UnitsCycles");
						LastMaintenanceUnit=asset.getLastMaintenanceUnit();
						LastMaintenanceDate=asset.getLastMaintenanceDate();
						rate_planning_end=LastMaintenanceUnit+(p_PlanningHorizon*rate);
						rate_current=LastMaintenanceUnit+rate;
					    while(rate_current<=rate_planning_end) {
					    	unit_current=rate*counter2;

					    	if (unit_current>=unitscycles)
					    	{
			 					MForecastLine fcl =new MForecastLine(getCtx(),0, get_TrxName());
			 					fcl.setAD_Org_ID(pp.getAD_Org_ID());
			 					fcl.setM_Forecast_ID(p_M_Forecast_ID);
								fcl.setM_Product_ID(asset.getM_Product_ID());
								fcl.setM_Warehouse_ID(pp.getM_Warehouse_ID());
								fcl.setQty(Env.ONE);
								fcl.setC_Period_ID(getPeriodID(TimeUtil.addDays(LastMaintenanceDate,counter1)));
								fcl.setDatePromised(TimeUtil.addDays(LastMaintenanceDate,counter1));
								fcl.set_ValueOfColumn("pp_product_planning_id",  rs.getInt("pp_product_planning_id"));
//								fcl.set_ValueOfColumn("a_asset_maintenance_id",  rs.getInt("a_asset_maintenance_id"));
								fcl.saveEx();   
								
			 					unit_current=0;
			 					counter2=0;
			 					counter_M++;
					    	}
								
							rate_current=rate_current+rate;	
					    	counter1++;counter2++;
					      }
						_result+=" Created "+counter_M+" Forecat Line(s) for "+asset.getName()+" based Meter Rules!<br>";	
						rate_current=0;	counter1=0;counter2=0;
					}
					
					if (pmrule.equals("L"))
					{
						NextMaintenanceDate =	rs.getTimestamp("NextMaintenenceDate");
						if (NextMaintenanceDate!=null)
						{
						MForecastLine fcl =new MForecastLine(getCtx(),0, get_TrxName());
						fcl.setAD_Org_ID(pp.getAD_Org_ID());
						fcl.setM_Forecast_ID(p_M_Forecast_ID);
						fcl.setM_Product_ID(asset.getM_Product_ID());
						fcl.setM_Warehouse_ID(pp.getM_Warehouse_ID());
						fcl.setQty(Env.ONE);
						fcl.setC_Period_ID(getPeriodID(NextMaintenanceDate));
						fcl.setDatePromised(NextMaintenanceDate);
					//	fcl.set_ValueOfColumn("Description", "L");
						fcl.set_ValueOfColumn("pp_product_planning_id",  rs.getInt("pp_product_planning_id"));
//						fcl.set_ValueOfColumn("a_asset_maintenance_id",  rs.getInt("a_asset_maintenance_id"));
						fcl.saveEx();
						counter_L++;
						_result+=" Created "+counter_L+" Forecat Line(s) for "+asset.getName()+" based List Dates!<br>";	
						}
					}
									
					//-----------
				}
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql_so, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; 	pstmt = null;
			}
		
		
        return _result;
	}

	public int getPeriodID(Timestamp  MaintenanceDate) 	{
		return   DB.getSQLValue (get_TrxName(), 
				 "SELECT C_Period_ID FROM C_Period WHERE isActive='Y' AND AD_Client_ID="+p_AD_Client_ID +
				 " AND '"+MaintenanceDate+"'  BETWEEN  StartDate  AND  EndDate ;");
	}
 
}	