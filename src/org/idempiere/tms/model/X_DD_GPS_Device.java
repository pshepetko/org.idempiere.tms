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

/** Generated Model for DD_GPS_Device
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_DD_GPS_Device extends PO implements I_DD_GPS_Device, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211121L;

    /** Standard Constructor */
    public X_DD_GPS_Device (Properties ctx, int DD_GPS_Device_ID, String trxName)
    {
      super (ctx, DD_GPS_Device_ID, trxName);
      /** if (DD_GPS_Device_ID == 0)
        {
			setDD_GPS_Device_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DD_GPS_Device (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_DD_GPS_Device[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set GPS Device.
		@param DD_GPS_Device_ID GPS Device	  */
	public void setDD_GPS_Device_ID (int DD_GPS_Device_ID)
	{
		if (DD_GPS_Device_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_GPS_Device_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_GPS_Device_ID, Integer.valueOf(DD_GPS_Device_ID));
	}

	/** Get GPS Device.
		@return GPS Device	  */
	public int getDD_GPS_Device_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_GPS_Device_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DD_GPS_Device_uuid.
		@param DD_GPS_Device_uuid DD_GPS_Device_uuid	  */
	public void setDD_GPS_Device_uuid (String DD_GPS_Device_uuid)
	{
		set_Value (COLUMNNAME_DD_GPS_Device_uuid, DD_GPS_Device_uuid);
	}

	/** Get DD_GPS_Device_uuid.
		@return DD_GPS_Device_uuid	  */
	public String getDD_GPS_Device_uuid () 
	{
		return (String)get_Value(COLUMNNAME_DD_GPS_Device_uuid);
	}

	public I_DD_GPS_Position getDD_GPS_Position() throws RuntimeException
    {
		return (I_DD_GPS_Position)MTable.get(getCtx(), I_DD_GPS_Position.Table_Name)
			.getPO(getDD_GPS_Position_ID(), get_TrxName());	}

	/** Set Positions.
		@param DD_GPS_Position_ID Positions	  */
	public void setDD_GPS_Position_ID (int DD_GPS_Position_ID)
	{
		if (DD_GPS_Position_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_GPS_Position_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_GPS_Position_ID, Integer.valueOf(DD_GPS_Position_ID));
	}

	/** Get Positions.
		@return Positions	  */
	public int getDD_GPS_Position_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_GPS_Position_ID);
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

	/** Set Device ID.
		@param DeviceID Device ID	  */
	public void setDeviceID (int DeviceID)
	{
		set_Value (COLUMNNAME_DeviceID, Integer.valueOf(DeviceID));
	}

	/** Get Device ID.
		@return Device ID	  */
	public int getDeviceID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DeviceID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disabled.
		@param Disabled Disabled	  */
	public void setDisabled (boolean Disabled)
	{
		set_Value (COLUMNNAME_Disabled, Boolean.valueOf(Disabled));
	}

	/** Get Disabled.
		@return Disabled	  */
	public boolean isDisabled () 
	{
		Object oo = get_Value(COLUMNNAME_Disabled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Last Update.
		@param LastUpdate Last Update	  */
	public void setLastUpdate (Timestamp LastUpdate)
	{
		set_ValueNoCheck (COLUMNNAME_LastUpdate, LastUpdate);
	}

	/** Get Last Update.
		@return Last Update	  */
	public Timestamp getLastUpdate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastUpdate);
	}

	/** Set Model.
		@param Model Model	  */
	public void setModel (String Model)
	{
		set_Value (COLUMNNAME_Model, Model);
	}

	/** Get Model.
		@return Model	  */
	public String getModel () 
	{
		return (String)get_Value(COLUMNNAME_Model);
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

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_ValueNoCheck (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
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