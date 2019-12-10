/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.idempiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.util.Env;
import org.idempiere.tms.model.MDDFreight;

/**
 *  Post Freight Order
 *  <pre>
 *  Table:              DD_Freight
 *  Document Types:     ODD
 *  </pre>
 *  @author victor.perez@e-evolution.com http://www.e-evolution.com
 */
public class Doc_DDFreight extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_DDFreight (MAcctSchema ass, ResultSet rs, String trxName)
	{
		super(ass, MDDFreight.class, rs,null , trxName);//MDocType.DOCBASETYPE_ARInvoice
	}   //Doc Cost Collector
	
	/**
	 *  Load Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails()
	{	
		MDDFreight order = (MDDFreight)getPO();
		setDateDoc(order.getDateOrdered());
		return STATUS_Posted;
	}   //  loadDocumentDetails

	/**
	 *  Get Balance
	 *  @return Zero (always balanced)
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance
	
	/**
	 *  Create Facts (the accounting logic) for
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{		
		return null;
	}   //  createFact
}   //  Doc Cost Collector
