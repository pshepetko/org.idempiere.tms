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
package org.idempiere.tms.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DD_GPS_Position
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_DD_GPS_Position 
{

    /** TableName=DD_GPS_Position */
    public static final String Table_Name = "DD_GPS_Position";

    /** AD_Table_ID=1000044 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DD_GPS_Device_ID */
    public static final String COLUMNNAME_DD_GPS_Device_ID = "DD_GPS_Device_ID";

	/** Set GPS Device	  */
	public void setDD_GPS_Device_ID (int DD_GPS_Device_ID);

	/** Get GPS Device	  */
	public int getDD_GPS_Device_ID();

	public I_DD_GPS_Device getDD_GPS_Device() throws RuntimeException;

    /** Column name DD_GPS_Position_ID */
    public static final String COLUMNNAME_DD_GPS_Position_ID = "DD_GPS_Position_ID";

	/** Set Positions	  */
	public void setDD_GPS_Position_ID (int DD_GPS_Position_ID);

	/** Get Positions	  */
	public int getDD_GPS_Position_ID();

    /** Column name DD_GPS_Position_UU */
    public static final String COLUMNNAME_DD_GPS_Position_UU = "DD_GPS_Position_UU";

	/** Set DD_Positions_UU	  */
	public void setDD_GPS_Position_UU (String DD_GPS_Position_UU);

	/** Get DD_Positions_UU	  */
	public String getDD_GPS_Position_UU();

    /** Column name DD_Route_ID */
    public static final String COLUMNNAME_DD_Route_ID = "DD_Route_ID";

	/** Set Route	  */
	public void setDD_Route_ID (int DD_Route_ID);

	/** Get Route	  */
	public int getDD_Route_ID();

	public I_DD_Route getDD_Route() throws RuntimeException;

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DeviceID */
    public static final String COLUMNNAME_DeviceID = "DeviceID";

	/** Set Device ID	  */
	public void setDeviceID (int DeviceID);

	/** Get Device ID	  */
	public int getDeviceID();

    /** Column name DeviceTime */
    public static final String COLUMNNAME_DeviceTime = "DeviceTime";

	/** Set Device Time	  */
	public void setDeviceTime (Timestamp DeviceTime);

	/** Get Device Time	  */
	public Timestamp getDeviceTime();

    /** Column name GeoType */
    public static final String COLUMNNAME_GeoType = "GeoType";

	/** Set Geo Type	  */
	public void setGeoType (String GeoType);

	/** Get Geo Type	  */
	public String getGeoType();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Latitude */
    public static final String COLUMNNAME_Latitude = "Latitude";

	/** Set Latitude	  */
	public void setLatitude (String Latitude);

	/** Get Latitude	  */
	public String getLatitude();

    /** Column name Longitude */
    public static final String COLUMNNAME_Longitude = "Longitude";

	/** Set Longitude	  */
	public void setLongitude (String Longitude);

	/** Get Longitude	  */
	public String getLongitude();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Source */
    public static final String COLUMNNAME_Source = "Source";

	/** Set Source	  */
	public void setSource (String Source);

	/** Get Source	  */
	public String getSource();

    /** Column name Speed */
    public static final String COLUMNNAME_Speed = "Speed";

	/** Set Speed	  */
	public void setSpeed (int Speed);

	/** Get Speed	  */
	public int getSpeed();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
