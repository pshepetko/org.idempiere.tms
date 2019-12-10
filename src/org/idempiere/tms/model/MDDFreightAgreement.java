package org.idempiere.tms.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDDFreightAgreement extends X_DD_Freight_Agreement{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78512127568095470L;


	public MDDFreightAgreement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


	public MDDFreightAgreement(Properties ctx, int DD_Freight_Agreement_ID, String trxName) {
		super(ctx, DD_Freight_Agreement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
