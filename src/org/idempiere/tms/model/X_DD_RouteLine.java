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

/** Generated Model for DD_RouteLine
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_DD_RouteLine extends PO implements I_DD_RouteLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211022L;

    /** Standard Constructor */
    public X_DD_RouteLine (Properties ctx, int DD_RouteLine_ID, String trxName)
    {
      super (ctx, DD_RouteLine_ID, trxName);
      /** if (DD_RouteLine_ID == 0)
        {
			setDD_Route_ID (0);
			setDD_RouteLine_ID (0);
			setisbp (false);
			setiscoordinates (false);
			setislocation (false);
			setissr (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_DD_RouteLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_DD_RouteLine[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public I_DD_Route getDD_Route() throws RuntimeException
    {
		return (I_DD_Route)MTable.get(getCtx(), I_DD_Route.Table_Name)
			.getPO(getDD_Route_ID(), get_TrxName());	}

	/** Set Route.
		@param DD_Route_ID Route	  */
	public void setDD_Route_ID (int DD_Route_ID)
	{
		if (DD_Route_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Route_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Route_ID, Integer.valueOf(DD_Route_ID));
	}

	/** Get Route.
		@return Route	  */
	public int getDD_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Route_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	/** Set is BPartner?.
		@param isbp is BPartner?	  */
	public void setisbp (boolean isbp)
	{
		set_Value (COLUMNNAME_isbp, Boolean.valueOf(isbp));
	}

	/** Get is BPartner?.
		@return is BPartner?	  */
	public boolean isbp () 
	{
		Object oo = get_Value(COLUMNNAME_isbp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set isCoordinates.
		@param iscoordinates isCoordinates	  */
	public void setiscoordinates (boolean iscoordinates)
	{
		set_Value (COLUMNNAME_iscoordinates, Boolean.valueOf(iscoordinates));
	}

	/** Get isCoordinates.
		@return isCoordinates	  */
	public boolean iscoordinates () 
	{
		Object oo = get_Value(COLUMNNAME_iscoordinates);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set isLocation.
		@param islocation isLocation	  */
	public void setislocation (boolean islocation)
	{
		set_Value (COLUMNNAME_islocation, Boolean.valueOf(islocation));
	}

	/** Get isLocation.
		@return isLocation	  */
	public boolean islocation () 
	{
		Object oo = get_Value(COLUMNNAME_islocation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is Sales Region ?.
		@param issr is Sales Region ?	  */
	public void setissr (boolean issr)
	{
		set_Value (COLUMNNAME_issr, Boolean.valueOf(issr));
	}

	/** Get is Sales Region ?.
		@return is Sales Region ?	  */
	public boolean issr () 
	{
		Object oo = get_Value(COLUMNNAME_issr);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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