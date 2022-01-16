package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDOtrLine extends X_DD_OtrLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDOtrLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDOtrLine(Properties ctx, int DD_OtrLine_ID, String trxName) {
		super(ctx, DD_OtrLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
