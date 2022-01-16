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

/** Generated Interface for DD_GPS_Device
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_DD_GPS_Device 
{

    /** TableName=DD_GPS_Device */
    public static final String Table_Name = "DD_GPS_Device";

    /** AD_Table_ID=1000045 */
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

    /** Column name DD_GPS_Device_uuid */
    public static final String COLUMNNAME_DD_GPS_Device_uuid = "DD_GPS_Device_uuid";

	/** Set DD_GPS_Device_uuid	  */
	public void setDD_GPS_Device_uuid (String DD_GPS_Device_uuid);

	/** Get DD_GPS_Device_uuid	  */
	public String getDD_GPS_Device_uuid();

    /** Column name DD_GPS_Position_ID */
    public static final String COLUMNNAME_DD_GPS_Position_ID = "DD_GPS_Position_ID";

	/** Set Positions	  */
	public void setDD_GPS_Position_ID (int DD_GPS_Position_ID);

	/** Get Positions	  */
	public int getDD_GPS_Position_ID();

	public I_DD_GPS_Position getDD_GPS_Position() throws RuntimeException;

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

    /** Column name Disabled */
    public static final String COLUMNNAME_Disabled = "Disabled";

	/** Set Disabled	  */
	public void setDisabled (boolean Disabled);

	/** Get Disabled	  */
	public boolean isDisabled();

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

    /** Column name LastUpdate */
    public static final String COLUMNNAME_LastUpdate = "LastUpdate";

	/** Set Last Update	  */
	public void setLastUpdate (Timestamp LastUpdate);

	/** Get Last Update	  */
	public Timestamp getLastUpdate();

    /** Column name Model */
    public static final String COLUMNNAME_Model = "Model";

	/** Set Model	  */
	public void setModel (String Model);

	/** Get Model	  */
	public String getModel();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
