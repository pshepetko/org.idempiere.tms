/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.idempiere.tms.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DD_Freight_Cost
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_Freight_Cost extends PO implements I_DD_Freight_Cost, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190304L;

    /** Standard Constructor */
    public X_DD_Freight_Cost (Properties ctx, int DD_Freight_Cost_ID, String trxName)
    {
      super (ctx, DD_Freight_Cost_ID, trxName);
      /** if (DD_Freight_Cost_ID == 0)
        {
			setDD_Freight_Cost_ID (0);
			setDD_Freight_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_DD_Freight_Cost (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_DD_Freight_Cost[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amt 
		Amount
	  */
	public void setAmt (BigDecimal Amt)
	{
		set_Value (COLUMNNAME_Amt, Amt);
	}

	/** Get Amount.
		@return Amount
	  */
	public BigDecimal getAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Freight Cost.
		@param DD_Freight_Cost_ID Order Freight Cost	  */
	public void setDD_Freight_Cost_ID (int DD_Freight_Cost_ID)
	{
		if (DD_Freight_Cost_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_Cost_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_Cost_ID, Integer.valueOf(DD_Freight_Cost_ID));
	}

	/** Get Order Freight Cost.
		@return Order Freight Cost	  */
	public int getDD_Freight_Cost_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Freight_Cost_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set dd_freight_cost_uuid.
		@param dd_freight_cost_uuid dd_freight_cost_uuid	  */
	public void setdd_freight_cost_uuid (String dd_freight_cost_uuid)
	{
		set_Value (COLUMNNAME_dd_freight_cost_uuid, dd_freight_cost_uuid);
	}

	/** Get dd_freight_cost_uuid.
		@return dd_freight_cost_uuid	  */
	public String getdd_freight_cost_uuid () 
	{
		return (String)get_Value(COLUMNNAME_dd_freight_cost_uuid);
	}

	public I_DD_Freight getDD_Freight() throws RuntimeException
    {
		return (I_DD_Freight)MTable.get(getCtx(), I_DD_Freight.Table_Name)
			.getPO(getDD_Freight_ID(), get_TrxName());	}

	/** Set  Freight Order.
		@param DD_Freight_ID  Freight Order	  */
	public void setDD_Freight_ID (int DD_Freight_ID)
	{
		if (DD_Freight_ID < 1) 
			set_Value (COLUMNNAME_DD_Freight_ID, null);
		else 
			set_Value (COLUMNNAME_DD_Freight_ID, Integer.valueOf(DD_Freight_ID));
	}

	/** Get  Freight Order.
		@return  Freight Order	  */
	public int getDD_Freight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Freight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}