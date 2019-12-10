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

/** Generated Model for DD_FreightLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DD_FreightLine extends PO implements I_DD_FreightLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190302L;

    /** Standard Constructor */
    public X_DD_FreightLine (Properties ctx, int DD_FreightLine_ID, String trxName)
    {
      super (ctx, DD_FreightLine_ID, trxName);
      /** if (DD_FreightLine_ID == 0)
        {
			setDD_FreightLine_ID (0);
			setFreightAmt (Env.ZERO);
			setLine (0);
			setweight_uom_id (0);
        } */
    }

    /** Load Constructor */
    public X_DD_FreightLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_FreightLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
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

	public I_DD_Freight getDD_Freight() throws RuntimeException
    {
		return (I_DD_Freight)MTable.get(getCtx(), I_DD_Freight.Table_Name)
			.getPO(getDD_Freight_ID(), get_TrxName());	}

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

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Freight getM_Freight() throws RuntimeException
    {
		return (org.compiere.model.I_M_Freight)MTable.get(getCtx(), org.compiere.model.I_M_Freight.Table_Name)
			.getPO(getM_Freight_ID(), get_TrxName());	}

	/** Set Freight.
		@param M_Freight_ID 
		Freight Rate
	  */
	public void setM_Freight_ID (int M_Freight_ID)
	{
		if (M_Freight_ID < 1) 
			set_Value (COLUMNNAME_M_Freight_ID, null);
		else 
			set_Value (COLUMNNAME_M_Freight_ID, Integer.valueOf(M_Freight_ID));
	}

	/** Get Freight.
		@return Freight Rate
	  */
	public int getM_Freight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Freight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Ship Date.
		@param ShipDate 
		Shipment Date/Time
	  */
	public void setShipDate (Timestamp ShipDate)
	{
		set_Value (COLUMNNAME_ShipDate, ShipDate);
	}

	/** Get Ship Date.
		@return Shipment Date/Time
	  */
	public Timestamp getShipDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShipDate);
	}

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TotalAmt, TotalAmt);
	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
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

	public org.compiere.model.I_C_UOM getvolume_uom() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getvolume_uom_id(), get_TrxName());	}

	/** Set Volume Unit of Measure.
		@param volume_uom_id Volume Unit of Measure	  */
	public void setvolume_uom_id (int volume_uom_id)
	{
		set_Value (COLUMNNAME_volume_uom_id, Integer.valueOf(volume_uom_id));
	}

	/** Get Volume Unit of Measure.
		@return Volume Unit of Measure	  */
	public int getvolume_uom_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_volume_uom_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_UOM getweight_uom() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getweight_uom_id(), get_TrxName());	}

	/** Set Weight Unit of Measure.
		@param weight_uom_id Weight Unit of Measure	  */
	public void setweight_uom_id (int weight_uom_id)
	{
		set_Value (COLUMNNAME_weight_uom_id, Integer.valueOf(weight_uom_id));
	}

	/** Get Weight Unit of Measure.
		@return Weight Unit of Measure	  */
	public int getweight_uom_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_weight_uom_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}