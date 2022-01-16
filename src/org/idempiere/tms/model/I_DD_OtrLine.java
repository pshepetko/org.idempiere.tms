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

/** Generated Interface for DD_OtrLine
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_DD_OtrLine 
{

    /** TableName=DD_OtrLine */
    public static final String Table_Name = "DD_OtrLine";

    /** AD_Table_ID=1000031 */
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

    /** Column name DatePromised */
    public static final String COLUMNNAME_DatePromised = "DatePromised";

	/** Set Date Promised.
	  * Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised);

	/** Get Date Promised.
	  * Date Order was promised
	  */
	public Timestamp getDatePromised();

    /** Column name DD_FreightLine_ID */
    public static final String COLUMNNAME_DD_FreightLine_ID = "DD_FreightLine_ID";

	/** Set Order Freight Line ID	  */
	public void setDD_FreightLine_ID (int DD_FreightLine_ID);

	/** Get Order Freight Line ID	  */
	public int getDD_FreightLine_ID();

	public I_DD_FreightLine getDD_FreightLine() throws RuntimeException;

    /** Column name DD_OTR_ID */
    public static final String COLUMNNAME_DD_OTR_ID = "DD_OTR_ID";

	/** Set Order Transportation Requirement	  */
	public void setDD_OTR_ID (int DD_OTR_ID);

	/** Get Order Transportation Requirement	  */
	public int getDD_OTR_ID();

	public I_DD_OTR getDD_OTR() throws RuntimeException;

    /** Column name DD_OtrLine_ID */
    public static final String COLUMNNAME_DD_OtrLine_ID = "DD_OtrLine_ID";

	/** Set Order Transportation Requirement Line	  */
	public void setDD_OtrLine_ID (int DD_OtrLine_ID);

	/** Get Order Transportation Requirement Line	  */
	public int getDD_OtrLine_ID();

    /** Column name dd_otrline_uuid */
    public static final String COLUMNNAME_dd_otrline_uuid = "dd_otrline_uuid";

	/** Set dd_otrline_uuid	  */
	public void setdd_otrline_uuid (String dd_otrline_uuid);

	/** Get dd_otrline_uuid	  */
	public String getdd_otrline_uuid();

    /** Column name DD_Route_ID */
    public static final String COLUMNNAME_DD_Route_ID = "DD_Route_ID";

	/** Set Route	  */
	public void setDD_Route_ID (int DD_Route_ID);

	/** Get Route	  */
	public int getDD_Route_ID();

	public I_DD_Route getDD_Route() throws RuntimeException;

    /** Column name DD_TransportUnit_ID */
    public static final String COLUMNNAME_DD_TransportUnit_ID = "DD_TransportUnit_ID";

	/** Set Transport Unit	  */
	public void setDD_TransportUnit_ID (int DD_TransportUnit_ID);

	/** Get Transport Unit	  */
	public int getDD_TransportUnit_ID();

	public I_DD_TransportUnit getDD_TransportUnit() throws RuntimeException;

    /** Column name DD_Vehicle_ID */
    public static final String COLUMNNAME_DD_Vehicle_ID = "DD_Vehicle_ID";

	/** Set Vehicle	  */
	public void setDD_Vehicle_ID (int DD_Vehicle_ID);

	/** Get Vehicle	  */
	public int getDD_Vehicle_ID();

	public I_DD_Vehicle getDD_Vehicle() throws RuntimeException;

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

    /** Column name Persent_Volume */
    public static final String COLUMNNAME_Persent_Volume = "Persent_Volume";

	/** Set Percent by Volume	  */
	public void setPersent_Volume (BigDecimal Persent_Volume);

	/** Get Percent by Volume	  */
	public BigDecimal getPersent_Volume();

    /** Column name Persent_Weight */
    public static final String COLUMNNAME_Persent_Weight = "Persent_Weight";

	/** Set Percent by  Weight	  */
	public void setPersent_Weight (BigDecimal Persent_Weight);

	/** Get Percent by  Weight	  */
	public BigDecimal getPersent_Weight();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Quantity.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Quantity.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name tu_code */
    public static final String COLUMNNAME_tu_code = "tu_code";

	/** Set TU  Code	  */
	public void settu_code (String tu_code);

	/** Get TU  Code	  */
	public String gettu_code();

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

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (BigDecimal Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public BigDecimal getVolume();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();
}
