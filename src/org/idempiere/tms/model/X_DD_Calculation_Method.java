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
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DD_Calculation_Method
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_Calculation_Method extends PO implements I_DD_Calculation_Method, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190428L;

    /** Standard Constructor */
    public X_DD_Calculation_Method (Properties ctx, int DD_Calculation_Method_ID, String trxName)
    {
      super (ctx, DD_Calculation_Method_ID, trxName);
      /** if (DD_Calculation_Method_ID == 0)
        {
			setDD_Calculation_Method_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_Calculation_Method (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Calculation_Method[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
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

	/** Set Calculation Method.
		@param DD_Calculation_Method_ID Calculation Method	  */
	public void setDD_Calculation_Method_ID (int DD_Calculation_Method_ID)
	{
		if (DD_Calculation_Method_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Calculation_Method_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Calculation_Method_ID, Integer.valueOf(DD_Calculation_Method_ID));
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

	/** Set dd_calculation_method_uuid.
		@param dd_calculation_method_uuid dd_calculation_method_uuid	  */
	public void setdd_calculation_method_uuid (String dd_calculation_method_uuid)
	{
		set_Value (COLUMNNAME_dd_calculation_method_uuid, dd_calculation_method_uuid);
	}

	/** Get dd_calculation_method_uuid.
		@return dd_calculation_method_uuid	  */
	public String getdd_calculation_method_uuid () 
	{
		return (String)get_Value(COLUMNNAME_dd_calculation_method_uuid);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}