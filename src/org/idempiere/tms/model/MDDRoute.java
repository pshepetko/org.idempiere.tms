package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDRoute extends X_DD_Route{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDRoute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDRoute(Properties ctx, int DD_Route_ID, String trxName) {
		super(ctx, DD_Route_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
