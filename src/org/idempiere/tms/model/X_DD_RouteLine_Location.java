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

/** Generated Model for DD_RouteLine_Location
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_DD_RouteLine_Location extends PO implements I_DD_RouteLine_Location, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211018L;

    /** Standard Constructor */
    public X_DD_RouteLine_Location (Properties ctx, int DD_RouteLine_Location_ID, String trxName)
    {
      super (ctx, DD_RouteLine_Location_ID, trxName);
      /** if (DD_RouteLine_Location_ID == 0)
        {
			setDD_RouteLine_ID (0);
			setDD_RouteLine_Location_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_RouteLine_Location (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_DD_RouteLine_Location[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Location getC_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DD_RouteLine getDD_RouteLine() throws RuntimeException
    {
		return (I_DD_RouteLine)MTable.get(getCtx(), I_DD_RouteLine.Table_Name)
			.getPO(getDD_RouteLine_ID(), get_TrxName());	}

	/** Set Route Lines .
		@param DD_RouteLine_ID Route Lines 	  */
	public void setDD_RouteLine_ID (int DD_RouteLine_ID)
	{
		if (DD_RouteLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_RouteLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_RouteLine_ID, Integer.valueOf(DD_RouteLine_ID));
	}

	/** Get Route Lines .
		@return Route Lines 	  */
	public int getDD_RouteLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_RouteLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Route Line for Location.
		@param DD_RouteLine_Location_ID Route Line for Location	  */
	public void setDD_RouteLine_Location_ID (int DD_RouteLine_Location_ID)
	{
		if (DD_RouteLine_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_RouteLine_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_RouteLine_Location_ID, Integer.valueOf(DD_RouteLine_Location_ID));
	}

	/** Get Route Line for Location.
		@return Route Line for Location	  */
	public int getDD_RouteLine_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_RouteLine_Location_ID);
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

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority)
	{
		set_Value (COLUMNNAME_Priority, Integer.valueOf(Priority));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Priority);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}