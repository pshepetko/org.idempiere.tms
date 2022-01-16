package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDGPSDevice extends X_DD_GPS_Device{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDGPSDevice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDGPSDevice(Properties ctx, int DD_GPS_Device_ID, String trxName) {
		super(ctx, DD_GPS_Device_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
