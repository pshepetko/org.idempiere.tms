/**
 * This file is part of iDempiere ERP <http://www.idempiere.org>.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Copyright (c) 2016 Saúl Piña <sauljabin@gmail.com>.
 */

package org.idempiere.tms.component;

import org.idempiere.tms.base.CustomModelFactory;

/**
 * Model Factory
 */
public class TMSModelFactory extends CustomModelFactory {

	/**
	 * For initialize class. Register the models to build
	 * 
	 * <pre>
	 * protected void initialize() {
	 * 	registerTableModel(MTableExample.Table_Name, MTableExample.class);
	 * }
	 * </pre>
	 */
	@Override
	protected void initialize() {
		registerTableModel(org.idempiere.tms.model.MDDCompartment.Table_Name, org.idempiere.tms.model.MDDCompartment.class);
		registerTableModel(org.idempiere.tms.model.MDDCompartmentAssignment.Table_Name, org.idempiere.tms.model.MDDCompartmentAssignment.class);
		registerTableModel(org.idempiere.tms.model.MDDCompatibilityGroup.Table_Name, org.idempiere.tms.model.MDDCompatibilityGroup.class);
		registerTableModel(org.idempiere.tms.model.MDDDriver.Table_Name, org.idempiere.tms.model.MDDDriver.class);
		registerTableModel(org.idempiere.tms.model.MDDDriverAssignment.Table_Name, org.idempiere.tms.model.MDDDriverAssignment.class);
		registerTableModel(org.idempiere.tms.model.MDDFreight.Table_Name, org.idempiere.tms.model.MDDFreight.class);
		registerTableModel(org.idempiere.tms.model.MDDFreightLine.Table_Name, org.idempiere.tms.model.MDDFreightLine.class);
		registerTableModel(org.idempiere.tms.model.MDDLicense.Table_Name, org.idempiere.tms.model.MDDLicense.class);
		registerTableModel(org.idempiere.tms.model.MDDLicenseAssignment.Table_Name, org.idempiere.tms.model.MDDLicenseAssignment.class);
		registerTableModel(org.idempiere.tms.model.MDDLicenseType.Table_Name, org.idempiere.tms.model.MDDLicenseType.class);
		registerTableModel(org.idempiere.tms.model.MDDRequirement.Table_Name, org.idempiere.tms.model.MDDRequirement.class);
		registerTableModel(org.idempiere.tms.model.MDDRequirementAssignment.Table_Name, org.idempiere.tms.model.MDDRequirementAssignment.class);
		registerTableModel(org.idempiere.tms.model.MDDRequirementType.Table_Name, org.idempiere.tms.model.MDDRequirementType.class);
		registerTableModel(org.idempiere.tms.model.MDDTransportAssignment.Table_Name, org.idempiere.tms.model.MDDTransportAssignment.class);
		registerTableModel(org.idempiere.tms.model.MDDTransportUnit.Table_Name, org.idempiere.tms.model.MDDTransportUnit.class);
		registerTableModel(org.idempiere.tms.model.MDDTransportUnitType.Table_Name, org.idempiere.tms.model.MDDTransportUnitType.class);
		registerTableModel(org.idempiere.tms.model.MDDVehicle.Table_Name, org.idempiere.tms.model.MDDVehicle.class);
		registerTableModel(org.idempiere.tms.model.MDDVehicleAssignment.Table_Name, org.idempiere.tms.model.MDDVehicleAssignment.class);
		registerTableModel(org.idempiere.tms.model.MDDVehicleType.Table_Name, org.idempiere.tms.model.MDDVehicleType.class);

		registerTableModel(org.idempiere.tms.model.MDDOTR.Table_Name, org.idempiere.tms.model.MDDOTR.class);	
		registerTableModel(org.idempiere.tms.model.MDDFreightStop.Table_Name, org.idempiere.tms.model.MDDFreightStop.class);
		registerTableModel(org.idempiere.tms.model.MDDFreightCost.Table_Name, org.idempiere.tms.model.MDDFreightCost.class);
		
		registerTableModel(org.idempiere.tms.model.MDDCalculationMethod.Table_Name, org.idempiere.tms.model.MDDCalculationMethod.class);
		registerTableModel(org.idempiere.tms.model.MDDCalculationMethodLine.Table_Name, org.idempiere.tms.model.MDDCalculationMethodLine.class);
		registerTableModel(org.idempiere.tms.model.MDDFreightAgreement.Table_Name, org.idempiere.tms.model.MDDFreightAgreement.class);
	}

}
