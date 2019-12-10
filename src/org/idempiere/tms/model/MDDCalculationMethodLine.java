package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDCalculationMethodLine extends X_DD_Calculation_MethodLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDCalculationMethodLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDCalculationMethodLine(Properties ctx, int DD_Calculation_MethodLine_ID, String trxName) {
		super(ctx, DD_Calculation_MethodLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
