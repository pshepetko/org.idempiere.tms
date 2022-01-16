package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDRouteLine extends X_DD_RouteLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDRouteLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDRouteLine(Properties ctx, int DD_RouteLine_ID, String trxName) {
		super(ctx, DD_RouteLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
