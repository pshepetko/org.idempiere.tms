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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DD_Freight_Agreement
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_Freight_Agreement extends PO implements I_DD_Freight_Agreement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190428L;

    /** Standard Constructor */
    public X_DD_Freight_Agreement (Properties ctx, int DD_Freight_Agreement_ID, String trxName)
    {
      super (ctx, DD_Freight_Agreement_ID, trxName);
      /** if (DD_Freight_Agreement_ID == 0)
        {
			setDD_Freight_Agreement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_Freight_Agreement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Freight_Agreement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date From.
		@param DateFrom 
		Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom)
	{
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
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

	/** Set Freight Agreement.
		@param DD_Freight_Agreement_ID Freight Agreement	  */
	public void setDD_Freight_Agreement_ID (int DD_Freight_Agreement_ID)
	{
		if (DD_Freight_Agreement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_Agreement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_Agreement_ID, Integer.valueOf(DD_Freight_Agreement_ID));
	}

	/** Get Freight Agreement.
		@return Freight Agreement	  */
	public int getDD_Freight_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Freight_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DD_Freight_Agreement_UU.
		@param DD_Freight_Agreement_UU DD_Freight_Agreement_UU	  */
	public void setDD_Freight_Agreement_UU (String DD_Freight_Agreement_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DD_Freight_Agreement_UU, DD_Freight_Agreement_UU);
	}

	/** Get DD_Freight_Agreement_UU.
		@return DD_Freight_Agreement_UU	  */
	public String getDD_Freight_Agreement_UU () 
	{
		return (String)get_Value(COLUMNNAME_DD_Freight_Agreement_UU);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	public org.compiere.model.I_M_Shipper getM_Shipper() throws RuntimeException
    {
		return (org.compiere.model.I_M_Shipper)MTable.get(getCtx(), org.compiere.model.I_M_Shipper.Table_Name)
			.getPO(getM_Shipper_ID(), get_TrxName());	}

	/** Set Shipper.
		@param M_Shipper_ID 
		Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID)
	{
		if (M_Shipper_ID < 1) 
			set_Value (COLUMNNAME_M_Shipper_ID, null);
		else 
			set_Value (COLUMNNAME_M_Shipper_ID, Integer.valueOf(M_Shipper_ID));
	}

	/** Get Shipper.
		@return Method or manner of product delivery
	  */
	public int getM_Shipper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Shipper_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}