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

/** Generated Model for DD_Freight
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_Freight extends PO implements I_DD_Freight, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190302L;

    /** Standard Constructor */
    public X_DD_Freight (Properties ctx, int DD_Freight_ID, String trxName)
    {
      super (ctx, DD_Freight_ID, trxName);
      /** if (DD_Freight_ID == 0)
        {
			setC_DocType_ID (0);
			setDatePromised (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setDD_Freight_ID (0);
			setDocAction (null);
// CO
			setIsApproved (false);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_DD_Freight (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Freight[")
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

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Date Promised.
		@param DatePromised 
		Date of the Document
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		set_Value (COLUMNNAME_DatePromised, DatePromised);
	}

	/** Get Document Date Promised.
		@return Date Promised of the Document
	  */
	public Timestamp getDatePromised () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePromised);
	}

	/** Set Date Finish Schedule.
		@param DateFinishSchedule 
		Scheduled Finish date for this Order
	  */
	public void setDateFinishSchedule (Timestamp DateFinishSchedule)
	{
		set_Value (COLUMNNAME_DateFinishSchedule, DateFinishSchedule);
	}

	/** Get Date Finish Schedule.
		@return Scheduled Finish date for this Order
	  */
	public Timestamp getDateFinishSchedule () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFinishSchedule);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date Start Schedule.
		@param DateStartSchedule 
		Scheduled start date for this Order
	  */
	public void setDateStartSchedule (Timestamp DateStartSchedule)
	{
		set_Value (COLUMNNAME_DateStartSchedule, DateStartSchedule);
	}

	/** Get Date Start Schedule.
		@return Scheduled start date for this Order
	  */
	public Timestamp getDateStartSchedule () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStartSchedule);
	}

	/** Set Driver.
		@param DD_Driver_ID Driver	  */
	public void setDD_Driver_ID (int DD_Driver_ID)
	{
		if (DD_Driver_ID < 1) 
			set_Value (COLUMNNAME_DD_Driver_ID, null);
		else 
			set_Value (COLUMNNAME_DD_Driver_ID, Integer.valueOf(DD_Driver_ID));
	}

	/** Get Driver.
		@return Driver	  */
	public int getDD_Driver_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Driver_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set  Freight Order.
		@param DD_Freight_ID  Freight Order	  */
	public void setDD_Freight_ID (int DD_Freight_ID)
	{
		if (DD_Freight_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Freight_ID, Integer.valueOf(DD_Freight_ID));
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

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Duration.
		@param Duration 
		Normal Duration in Duration Unit
	  */
	public void setDuration (int Duration)
	{
		set_Value (COLUMNNAME_Duration, Integer.valueOf(Duration));
	}

	/** Get Duration.
		@return Normal Duration in Duration Unit
	  */
	public int getDuration () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duration);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Freight Amount.
		@param FreightAmt 
		Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt)
	{
		set_Value (COLUMNNAME_FreightAmt, FreightAmt);
	}

	/** Get Freight Amount.
		@return Freight Amount 
	  */
	public BigDecimal getFreightAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FreightAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** FreightCostRule AD_Reference_ID=153 */
	public static final int FREIGHTCOSTRULE_AD_Reference_ID=153;
	/** Freight included = I */
	public static final String FREIGHTCOSTRULE_FreightIncluded = "I";
	/** Fix price = F */
	public static final String FREIGHTCOSTRULE_FixPrice = "F";
	/** Calculated = C */
	public static final String FREIGHTCOSTRULE_Calculated = "C";
	/** Line = L */
	public static final String FREIGHTCOSTRULE_Line = "L";
	/** Set Freight Cost Rule.
		@param FreightCostRule 
		Method for charging Freight
	  */
	public void setFreightCostRule (String FreightCostRule)
	{

		set_Value (COLUMNNAME_FreightCostRule, FreightCostRule);
	}

	/** Get Freight Cost Rule.
		@return Method for charging Freight
	  */
	public String getFreightCostRule () 
	{
		return (String)get_Value(COLUMNNAME_FreightCostRule);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Length.
		@param Length Length	  */
	public void setLength (BigDecimal Length)
	{
		set_Value (COLUMNNAME_Length, Length);
	}

	/** Get Length.
		@return Length	  */
	public BigDecimal getLength () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Length);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_FreightCategory getM_FreightCategory() throws RuntimeException
    {
		return (org.compiere.model.I_M_FreightCategory)MTable.get(getCtx(), org.compiere.model.I_M_FreightCategory.Table_Name)
			.getPO(getM_FreightCategory_ID(), get_TrxName());	}

	/** Set Freight Category.
		@param M_FreightCategory_ID 
		Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID)
	{
		if (M_FreightCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_FreightCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_FreightCategory_ID, Integer.valueOf(M_FreightCategory_ID));
	}

	/** Get Freight Category.
		@return Category of the Freight
	  */
	public int getM_FreightCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_FreightCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Shipper getM_Shipper() throws RuntimeException
    {
		return (org.compiere.model.I_M_Shipper)MTable.get(getCtx(), org.compiere.model.I_M_Shipper.Table_Name)
			.getPO(getM_Shipper_ID(), get_TrxName());	}

	/** Set Shipper.
		@param M_Shipper_ID 
		Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID)
	{
		if (M_Shipper_ID < 1) 
			set_Value (COLUMNNAME_M_Shipper_ID, null);
		else 
			set_Value (COLUMNNAME_M_Shipper_ID, Integer.valueOf(M_Shipper_ID));
	}

	/** Get Shipper.
		@return Method or manner of product delivery
	  */
	public int getM_Shipper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Shipper_ID);
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

	/** Set uuid.
		@param uuid uuid	  */
	public void setuuid (String uuid)
	{
		set_Value (COLUMNNAME_uuid, uuid);
	}

	/** Get uuid.
		@return uuid	  */
	public String getuuid () 
	{
		return (String)get_Value(COLUMNNAME_uuid);
	}
	
	/** Set Volume.
	@param Volume 
	Volume of a product
  */
public void setVolume (BigDecimal Volume)
{
	set_Value (COLUMNNAME_Volume, Volume);
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
	set_Value (COLUMNNAME_Weight, Weight);
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


/** Set Stops.
@param Stops 
Stops
*/
public void setStops (BigDecimal Stops)
{
set_Value (COLUMNNAME_Stops, Stops);
}

/** Get Stops.
@return Stops
*/
public BigDecimal getStops () 
{
BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Stops);
if (bd == null)
	 return Env.ZERO;
return bd;
}
}