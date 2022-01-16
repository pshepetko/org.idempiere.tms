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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DD_OtrLine
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_DD_OtrLine extends PO implements I_DD_OtrLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211024L;

    /** Standard Constructor */
    public X_DD_OtrLine (Properties ctx, int DD_OtrLine_ID, String trxName)
    {
      super (ctx, DD_OtrLine_ID, trxName);
      /** if (DD_OtrLine_ID == 0)
        {
			setDD_OTR_ID (0);
			setDD_OtrLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_OtrLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_DD_OtrLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date Promised.
		@param DatePromised 
		Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		set_ValueNoCheck (COLUMNNAME_DatePromised, DatePromised);
	}

	/** Get Date Promised.
		@return Date Order was promised
	  */
	public Timestamp getDatePromised () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePromised);
	}

	public I_DD_FreightLine getDD_FreightLine() throws RuntimeException
    {
		return (I_DD_FreightLine)MTable.get(getCtx(), I_DD_FreightLine.Table_Name)
			.getPO(getDD_FreightLine_ID(), get_TrxName());	}

	/** Set Order Freight Line ID.
		@param DD_FreightLine_ID Order Freight Line ID	  */
	public void setDD_FreightLine_ID (int DD_FreightLine_ID)
	{
		if (DD_FreightLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_FreightLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_FreightLine_ID, Integer.valueOf(DD_FreightLine_ID));
	}

	/** Get Order Freight Line ID.
		@return Order Freight Line ID	  */
	public int getDD_FreightLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_FreightLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DD_OTR getDD_OTR() throws RuntimeException
    {
		return (I_DD_OTR)MTable.get(getCtx(), I_DD_OTR.Table_Name)
			.getPO(getDD_OTR_ID(), get_TrxName());	}

	/** Set Order Transportation Requirement.
		@param DD_OTR_ID Order Transportation Requirement	  */
	public void setDD_OTR_ID (int DD_OTR_ID)
	{
		if (DD_OTR_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_OTR_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_OTR_ID, Integer.valueOf(DD_OTR_ID));
	}

	/** Get Order Transportation Requirement.
		@return Order Transportation Requirement	  */
	public int getDD_OTR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_OTR_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Transportation Requirement Line.
		@param DD_OtrLine_ID Order Transportation Requirement Line	  */
	public void setDD_OtrLine_ID (int DD_OtrLine_ID)
	{
		if (DD_OtrLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_OtrLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_OtrLine_ID, Integer.valueOf(DD_OtrLine_ID));
	}

	/** Get Order Transportation Requirement Line.
		@return Order Transportation Requirement Line	  */
	public int getDD_OtrLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_OtrLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set dd_otrline_uuid.
		@param dd_otrline_uuid dd_otrline_uuid	  */
	public void setdd_otrline_uuid (String dd_otrline_uuid)
	{
		set_Value (COLUMNNAME_dd_otrline_uuid, dd_otrline_uuid);
	}

	/** Get dd_otrline_uuid.
		@return dd_otrline_uuid	  */
	public String getdd_otrline_uuid () 
	{
		return (String)get_Value(COLUMNNAME_dd_otrline_uuid);
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

	public I_DD_TransportUnit getDD_TransportUnit() throws RuntimeException
    {
		return (I_DD_TransportUnit)MTable.get(getCtx(), I_DD_TransportUnit.Table_Name)
			.getPO(getDD_TransportUnit_ID(), get_TrxName());	}

	/** Set Transport Unit.
		@param DD_TransportUnit_ID Transport Unit	  */
	public void setDD_TransportUnit_ID (int DD_TransportUnit_ID)
	{
		if (DD_TransportUnit_ID < 1) 
			set_Value (COLUMNNAME_DD_TransportUnit_ID, null);
		else 
			set_Value (COLUMNNAME_DD_TransportUnit_ID, Integer.valueOf(DD_TransportUnit_ID));
	}

	/** Get Transport Unit.
		@return Transport Unit	  */
	public int getDD_TransportUnit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_TransportUnit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DD_Vehicle getDD_Vehicle() throws RuntimeException
    {
		return (I_DD_Vehicle)MTable.get(getCtx(), I_DD_Vehicle.Table_Name)
			.getPO(getDD_Vehicle_ID(), get_TrxName());	}

	/** Set Vehicle.
		@param DD_Vehicle_ID Vehicle	  */
	public void setDD_Vehicle_ID (int DD_Vehicle_ID)
	{
		if (DD_Vehicle_ID < 1) 
			set_Value (COLUMNNAME_DD_Vehicle_ID, null);
		else 
			set_Value (COLUMNNAME_DD_Vehicle_ID, Integer.valueOf(DD_Vehicle_ID));
	}

	/** Get Vehicle.
		@return Vehicle	  */
	public int getDD_Vehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Vehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Percent by Volume.
		@param Persent_Volume Percent by Volume	  */
	public void setPersent_Volume (BigDecimal Persent_Volume)
	{
		set_Value (COLUMNNAME_Persent_Volume, Persent_Volume);
	}

	/** Get Percent by Volume.
		@return Percent by Volume	  */
	public BigDecimal getPersent_Volume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Persent_Volume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Percent by  Weight.
		@param Persent_Weight Percent by  Weight	  */
	public void setPersent_Weight (BigDecimal Persent_Weight)
	{
		set_Value (COLUMNNAME_Persent_Weight, Persent_Weight);
	}

	/** Get Percent by  Weight.
		@return Percent by  Weight	  */
	public BigDecimal getPersent_Weight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Persent_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Delivered Quantity.
		@param QtyDelivered 
		Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyDelivered, QtyDelivered);
	}

	/** Get Delivered Quantity.
		@return Delivered Quantity
	  */
	public BigDecimal getQtyDelivered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyDelivered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set TU  Code.
		@param tu_code TU  Code	  */
	public void settu_code (String tu_code)
	{
		set_Value (COLUMNNAME_tu_code, tu_code);
	}

	/** Get TU  Code.
		@return TU  Code	  */
	public String gettu_code () 
	{
		return (String)get_Value(COLUMNNAME_tu_code);
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