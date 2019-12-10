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

/** Generated Model for DD_OTR
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_OTR extends PO implements I_DD_OTR, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190302L;

    /** Standard Constructor */
    public X_DD_OTR (Properties ctx, int DD_OTR_ID, String trxName)
    {
      super (ctx, DD_OTR_ID, trxName);
      /** if (DD_OTR_ID == 0)
        {
			setdd_otr_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_OTR (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_OTR[")
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

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_DD_TransportUnit getDD_FreightUnit() throws RuntimeException
    {
		return (I_DD_TransportUnit)MTable.get(getCtx(), I_DD_TransportUnit.Table_Name)
			.getPO(getDD_FreightUnit_ID(), get_TrxName());	}

	/** Set Freight Unit.
		@param DD_FreightUnit_ID Freight Unit	  */
	public void setDD_FreightUnit_ID (int DD_FreightUnit_ID)
	{
		if (DD_FreightUnit_ID < 1) 
			set_Value (COLUMNNAME_DD_FreightUnit_ID, null);
		else 
			set_Value (COLUMNNAME_DD_FreightUnit_ID, Integer.valueOf(DD_FreightUnit_ID));
	}

	/** Get Freight Unit.
		@return Freight Unit	  */
	public int getDD_FreightUnit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_FreightUnit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_OrderLine getDD_OrderLine() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_OrderLine)MTable.get(getCtx(), org.eevolution.model.I_DD_OrderLine.Table_Name)
			.getPO(getDD_OrderLine_ID(), get_TrxName());	}

	/** Set Distribution Order Line.
		@param DD_OrderLine_ID Distribution Order Line	  */
	public void setDD_OrderLine_ID (int DD_OrderLine_ID)
	{
		if (DD_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_OrderLine_ID, Integer.valueOf(DD_OrderLine_ID));
	}

	/** Get Distribution Order Line.
		@return Distribution Order Line	  */
	public int getDD_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Transportation Requirement.
		@param dd_otr_ID Order Transportation Requirement	  */
	public void setdd_otr_ID (int dd_otr_ID)
	{
		if (dd_otr_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_dd_otr_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_dd_otr_ID, Integer.valueOf(dd_otr_ID));
	}

	/** Get Order Transportation Requirement.
		@return Order Transportation Requirement	  */
	public int getdd_otr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_dd_otr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set dd_otr_uuid.
		@param dd_otr_uuid dd_otr_uuid	  */
	public void setdd_otr_uuid (String dd_otr_uuid)
	{
		set_Value (COLUMNNAME_dd_otr_uuid, dd_otr_uuid);
	}

	/** Get dd_otr_uuid.
		@return dd_otr_uuid	  */
	public String getdd_otr_uuid () 
	{
		return (String)get_Value(COLUMNNAME_dd_otr_uuid);
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

	/** Set FU.
		@param FU 
		Freight Unit 
	  */
	public void setFU (String FU)
	{
		set_ValueNoCheck (COLUMNNAME_FU, FU);
	}

	/** Get FU.
		@return Freight Unit 
	  */
	public String getFU () 
	{
		return (String)get_Value(COLUMNNAME_FU);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_WarehouseSource() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_WarehouseSource_ID(), get_TrxName());	}

	/** Set Source Warehouse.
		@param M_WarehouseSource_ID 
		Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
	{
		if (M_WarehouseSource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_WarehouseSource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_WarehouseSource_ID, Integer.valueOf(M_WarehouseSource_ID));
	}

	/** Get Source Warehouse.
		@return Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Minimum Volume.
		@param minimumvolume Minimum Volume	  */
	public void setminimumvolume (BigDecimal minimumvolume)
	{
		set_Value (COLUMNNAME_minimumvolume, minimumvolume);
	}

	/** Get Minimum Volume.
		@return Minimum Volume	  */
	public BigDecimal getminimumvolume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_minimumvolume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Weight.
		@param minimumweight Minimum Weight	  */
	public void setminimumweight (BigDecimal minimumweight)
	{
		set_Value (COLUMNNAME_minimumweight, minimumweight);
	}

	/** Get Minimum Weight.
		@return Minimum Weight	  */
	public BigDecimal getminimumweight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_minimumweight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Type.
		@param OrderType 
		Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)
	  */
	public void setOrderType (String OrderType)
	{
		set_ValueNoCheck (COLUMNNAME_OrderType, OrderType);
	}

	/** Get Order Type.
		@return Type of Order: MRP records grouped by source (Sales Order, Purchase Order, Distribution Order, Requisition)
	  */
	public String getOrderType () 
	{
		return (String)get_Value(COLUMNNAME_OrderType);
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

	/** Set TU.
		@param TU 
		Transport Unit
	  */
	public void setTU (String TU)
	{
		set_ValueNoCheck (COLUMNNAME_TU, TU);
	}

	/** Get TU.
		@return Transport Unit
	  */
	public String getTU () 
	{
		return (String)get_Value(COLUMNNAME_TU);
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