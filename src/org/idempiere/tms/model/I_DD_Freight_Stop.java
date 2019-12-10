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

/** Generated Interface for DD_Freight_Stop
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_DD_Freight_Stop 
{

    /** TableName=DD_Freight_Stop */
    public static final String Table_Name = "DD_Freight_Stop";

    /** AD_Table_ID=1000236 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_LocFrom_ID */
    public static final String COLUMNNAME_C_LocFrom_ID = "C_LocFrom_ID";

	/** Set Location From.
	  * Location that inventory was moved from
	  */
	public void setC_LocFrom_ID (int C_LocFrom_ID);

	/** Get Location From.
	  * Location that inventory was moved from
	  */
	public int getC_LocFrom_ID();

	public org.compiere.model.I_C_Location getC_LocFrom() throws RuntimeException;

    /** Column name C_LocTo_ID */
    public static final String COLUMNNAME_C_LocTo_ID = "C_LocTo_ID";

	/** Set Location To.
	  * Location that inventory was moved to
	  */
	public void setC_LocTo_ID (int C_LocTo_ID);

	/** Get Location To.
	  * Location that inventory was moved to
	  */
	public int getC_LocTo_ID();

	public org.compiere.model.I_C_Location getC_LocTo() throws RuntimeException;

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

    /** Column name DD_Freight_ID */
    public static final String COLUMNNAME_DD_Freight_ID = "DD_Freight_ID";

	/** Set  Freight Order	  */
	public void setDD_Freight_ID (int DD_Freight_ID);

	/** Get  Freight Order	  */
	public int getDD_Freight_ID();

	public I_DD_Freight getDD_Freight() throws RuntimeException;

    /** Column name DD_Freight_Stop_ID */
    public static final String COLUMNNAME_DD_Freight_Stop_ID = "DD_Freight_Stop_ID";

	/** Set Order Freight Stops	  */
	public void setDD_Freight_Stop_ID (int DD_Freight_Stop_ID);

	/** Get Order Freight Stops	  */
	public int getDD_Freight_Stop_ID();

    /** Column name dd_freight_stop_uuid */
    public static final String COLUMNNAME_dd_freight_stop_uuid = "dd_freight_stop_uuid";

	/** Set dd_freight_stop_uuid	  */
	public void setdd_freight_stop_uuid (String dd_freight_stop_uuid);

	/** Get dd_freight_stop_uuid	  */
	public String getdd_freight_stop_uuid();

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

    /** Column name PercentUtilization */
    public static final String COLUMNNAME_PercentUtilization = "PercentUtilization";

	/** Set % Utilization	  */
	public void setPercentUtilization (BigDecimal PercentUtilization);

	/** Get % Utilization	  */
	public BigDecimal getPercentUtilization();

    /** Column name Point_ID */
    public static final String COLUMNNAME_Point_ID = "Point_ID";

	/** Set Point.
	  * Point 
	  */
	public void setPoint_ID (int Point_ID);

	/** Get Point.
	  * Point 
	  */
	public int getPoint_ID();

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

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();

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
