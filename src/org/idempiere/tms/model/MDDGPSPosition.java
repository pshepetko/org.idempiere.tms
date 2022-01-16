package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDGPSPosition extends X_DD_GPS_Position{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDGPSPosition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDGPSPosition(Properties ctx, int DD_GPS_Position_ID, String trxName) {
		super(ctx, DD_GPS_Position_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
