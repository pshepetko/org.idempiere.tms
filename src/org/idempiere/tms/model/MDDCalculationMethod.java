package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDCalculationMethod extends X_DD_Calculation_Method{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDCalculationMethod(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDCalculationMethod(Properties ctx, int DD_Calculation_Method_ID, String trxName) {
		super(ctx, DD_Calculation_Method_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
