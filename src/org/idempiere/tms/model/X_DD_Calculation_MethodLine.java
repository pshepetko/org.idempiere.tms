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

/** Generated Model for DD_Calculation_MethodLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_Calculation_MethodLine extends PO implements I_DD_Calculation_MethodLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190428L;

    /** Standard Constructor */
    public X_DD_Calculation_MethodLine (Properties ctx, int DD_Calculation_MethodLine_ID, String trxName)
    {
      super (ctx, DD_Calculation_MethodLine_ID, trxName);
      /** if (DD_Calculation_MethodLine_ID == 0)
        {
			setDD_Calculation_Method_ID (0);
			setDD_Calculation_MethodLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_Calculation_MethodLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Calculation_MethodLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DD_Calculation_Method getDD_Calculation_Method() throws RuntimeException
    {
		return (I_DD_Calculation_Method)MTable.get(getCtx(), I_DD_Calculation_Method.Table_Name)
			.getPO(getDD_Calculation_Method_ID(), get_TrxName());	}

	/** Set Calculation Method.
		@param DD_Calculation_Method_ID Calculation Method	  */
	public void setDD_Calculation_Method_ID (int DD_Calculation_Method_ID)
	{
		if (DD_Calculation_Method_ID < 1) 
			set_Value (COLUMNNAME_DD_Calculation_Method_ID, null);
		else 
			set_Value (COLUMNNAME_DD_Calculation_Method_ID, Integer.valueOf(DD_Calculation_Method_ID));
	}

	/** Get Calculation Method.
		@return Calculation Method	  */
	public int getDD_Calculation_Method_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Calculation_Method_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Freight Calculation Method Line.
		@param DD_Calculation_MethodLine_ID Freight Calculation Method Line	  */
	public void setDD_Calculation_MethodLine_ID (int DD_Calculation_MethodLine_ID)
	{
		if (DD_Calculation_MethodLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Calculation_MethodLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Calculation_MethodLine_ID, Integer.valueOf(DD_Calculation_MethodLine_ID));
	}

	/** Get Freight Calculation Method Line.
		@return Freight Calculation Method Line	  */
	public int getDD_Calculation_MethodLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Calculation_MethodLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set dd_calculation_methodline_uuid.
		@param dd_calculation_methodline_uuid dd_calculation_methodline_uuid	  */
	public void setdd_calculation_methodline_uuid (String dd_calculation_methodline_uuid)
	{
		set_Value (COLUMNNAME_dd_calculation_methodline_uuid, dd_calculation_methodline_uuid);
	}

	/** Get dd_calculation_methodline_uuid.
		@return dd_calculation_methodline_uuid	  */
	public String getdd_calculation_methodline_uuid () 
	{
		return (String)get_Value(COLUMNNAME_dd_calculation_methodline_uuid);
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

	/** Set Is Qty Percentage.
		@param IsQtyPercentage 
		Indicate that this component is based in % Quantity
	  */
	public void setIsQtyPercentage (boolean IsQtyPercentage)
	{
		set_Value (COLUMNNAME_IsQtyPercentage, Boolean.valueOf(IsQtyPercentage));
	}

	/** Get Is Qty Percentage.
		@return Indicate that this component is based in % Quantity
	  */
	public boolean isQtyPercentage () 
	{
		Object oo = get_Value(COLUMNNAME_IsQtyPercentage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Operation AD_Reference_ID=205 */
	public static final int OPERATION_AD_Reference_ID=205;
	/**  = = == */
	public static final String OPERATION_Eq = "==";
	/** >= = >= */
	public static final String OPERATION_GtEq = ">=";
	/** > = >> */
	public static final String OPERATION_Gt = ">>";
	/** < = << */
	public static final String OPERATION_Le = "<<";
	/**  ~ = ~~ */
	public static final String OPERATION_Like = "~~";
	/** <= = <= */
	public static final String OPERATION_LeEq = "<=";
	/** |<x>| = AB */
	public static final String OPERATION_X = "AB";
	/** sql = SQ */
	public static final String OPERATION_Sql = "SQ";
	/** != = != */
	public static final String OPERATION_NotEq = "!=";
	/** Set Operation.
		@param Operation 
		Compare Operation
	  */
	public void setOperation (String Operation)
	{

		set_Value (COLUMNNAME_Operation, Operation);
	}

	/** Get Operation.
		@return Compare Operation
	  */
	public String getOperation () 
	{
		return (String)get_Value(COLUMNNAME_Operation);
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

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}