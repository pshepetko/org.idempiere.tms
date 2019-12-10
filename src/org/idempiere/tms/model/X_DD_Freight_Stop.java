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

/** Generated Model for DD_Freight_Stop
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_Freight_Stop extends PO implements I_DD_Freight_Stop, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190302L;

    /** Standard Constructor */
    public X_DD_Freight_Stop (Properties ctx, int DD_Freight_Stop_ID, String trxName)
    {
      super (ctx, DD_Freight_Stop_ID, trxName);
      /** if (DD_Freight_Stop_ID == 0)
        {
			setDD_Freight_ID (0);
			setDD_Freight_Stop_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_DD_Freight_Stop (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Freight_Stop[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Location getC_LocFrom() throws RuntimeException
    {
		return (org.compiere.model.I_C_Location)MTable.get(getCtx(), org.compiere.model.I_C_Location.Table_Name)
			.getPO(getC_LocFrom_ID(), get_TrxName());	}

	/** Set Location From.
		@param C_LocFrom_ID 
		Location that inventory was moved from
	  */
	public void setC_LocFrom_ID (int C_LocFrom_ID)
	{
		if (C_LocFrom_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_LocFrom_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_LocFrom_ID, Integer.valueOf(C_LocFrom_ID));
	}

	/** Get Location From.
		@return Location that inventory was moved from
	  */
	public int getC_LocFrom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocFrom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Location getC_LocTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_Location)MTable.get(getCtx(), org.compiere.model.I_C_Location.Table_Name)
			.getPO(getC_LocTo_ID(), get_TrxName());	}

	/** Set Location To.
		@param C_LocTo_ID 
		Location that inventory was moved to
	  */
	public void setC_LocTo_ID (int C_LocTo_ID)
	{
		if (C_LocTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_LocTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_LocTo_ID, Integer.valueOf(C_LocTo_ID));
	}

	/** Get Location To.
		@return Location that inventory was moved to
	  */
	public int getC_LocTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Order Freight Stops.
		@param DD_Freight_Stop_ID Order Freight Stops	  */
	public void setDD_Freight_Stop_ID (int DD_Freight_Stop_ID)
	{
		if (DD_Freight_Stop_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_Stop_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_Stop_ID, Integer.valueOf(DD_Freight_Stop_ID));
	}

	/** Get Order Freight Stops.
		@return Order Freight Stops	  */
	public int getDD_Freight_Stop_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Freight_Stop_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set dd_freight_stop_uuid.
		@param dd_freight_stop_uuid dd_freight_stop_uuid	  */
	public void setdd_freight_stop_uuid (String dd_freight_stop_uuid)
	{
		set_Value (COLUMNNAME_dd_freight_stop_uuid, dd_freight_stop_uuid);
	}

	/** Get dd_freight_stop_uuid.
		@return dd_freight_stop_uuid	  */
	public String getdd_freight_stop_uuid () 
	{
		return (String)get_Value(COLUMNNAME_dd_freight_stop_uuid);
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

	/** Set % Utilization.
		@param PercentUtilization % Utilization	  */
	public void setPercentUtilization (BigDecimal PercentUtilization)
	{
		set_Value (COLUMNNAME_PercentUtilization, PercentUtilization);
	}

	/** Get % Utilization.
		@return % Utilization	  */
	public BigDecimal getPercentUtilization () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PercentUtilization);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Point.
		@param Point_ID 
		Point 
	  */
	public void setPoint_ID (int Point_ID)
	{
		if (Point_ID < 1) 
			set_Value (COLUMNNAME_Point_ID, null);
		else 
			set_Value (COLUMNNAME_Point_ID, Integer.valueOf(Point_ID));
	}

	/** Get Point.
		@return Point 
	  */
	public int getPoint_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Point_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Volume.
		@param Volume 
		Volume of a product
	  */
	public void setVolume (BigDecimal Volume)
	{
		set_ValueNoCheck (COLUMNNAME_Volume, Volume);
	}

	/** Get Volume.
		@return Volume of a product
	  */
	public BigDecimal getVolume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Volume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (BigDecimal Weight)
	{
		set_ValueNoCheck (COLUMNNAME_Weight, Weight);
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public BigDecimal getWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}