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

/** Generated Model for DD_GPS_Position
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_DD_GPS_Position extends PO implements I_DD_GPS_Position, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211121L;

    /** Standard Constructor */
    public X_DD_GPS_Position (Properties ctx, int DD_GPS_Position_ID, String trxName)
    {
      super (ctx, DD_GPS_Position_ID, trxName);
      /** if (DD_GPS_Position_ID == 0)
        {
			setDD_GPS_Device_ID (0);
			setDD_GPS_Position_ID (0);
			setDD_Route_ID (0);
			setDeviceTime (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_DD_GPS_Position (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_DD_GPS_Position[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_DD_GPS_Device getDD_GPS_Device() throws RuntimeException
    {
		return (I_DD_GPS_Device)MTable.get(getCtx(), I_DD_GPS_Device.Table_Name)
			.getPO(getDD_GPS_Device_ID(), get_TrxName());	}

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

	/** Set DD_Positions_UU.
		@param DD_GPS_Position_UU DD_Positions_UU	  */
	public void setDD_GPS_Position_UU (String DD_GPS_Position_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DD_GPS_Position_UU, DD_GPS_Position_UU);
	}

	/** Get DD_Positions_UU.
		@return DD_Positions_UU	  */
	public String getDD_GPS_Position_UU () 
	{
		return (String)get_Value(COLUMNNAME_DD_GPS_Position_UU);
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

	/** Set Device Time.
		@param DeviceTime Device Time	  */
	public void setDeviceTime (Timestamp DeviceTime)
	{
		set_Value (COLUMNNAME_DeviceTime, DeviceTime);
	}

	/** Get Device Time.
		@return Device Time	  */
	public Timestamp getDeviceTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DeviceTime);
	}

	/** Set Geo Type.
		@param GeoType Geo Type	  */
	public void setGeoType (String GeoType)
	{
		set_Value (COLUMNNAME_GeoType, GeoType);
	}

	/** Get Geo Type.
		@return Geo Type	  */
	public String getGeoType () 
	{
		return (String)get_Value(COLUMNNAME_GeoType);
	}

	/** Set Latitude.
		@param Latitude Latitude	  */
	public void setLatitude (String Latitude)
	{
		set_Value (COLUMNNAME_Latitude, Latitude);
	}

	/** Get Latitude.
		@return Latitude	  */
	public String getLatitude () 
	{
		return (String)get_Value(COLUMNNAME_Latitude);
	}

	/** Set Longitude.
		@param Longitude Longitude	  */
	public void setLongitude (String Longitude)
	{
		set_Value (COLUMNNAME_Longitude, Longitude);
	}

	/** Get Longitude.
		@return Longitude	  */
	public String getLongitude () 
	{
		return (String)get_Value(COLUMNNAME_Longitude);
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

	/** Set Source.
		@param Source Source	  */
	public void setSource (String Source)
	{
		set_Value (COLUMNNAME_Source, Source);
	}

	/** Get Source.
		@return Source	  */
	public String getSource () 
	{
		return (String)get_Value(COLUMNNAME_Source);
	}

	/** Set Speed.
		@param Speed Speed	  */
	public void setSpeed (int Speed)
	{
		set_Value (COLUMNNAME_Speed, Integer.valueOf(Speed));
	}

	/** Get Speed.
		@return Speed	  */
	public int getSpeed () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Speed);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}