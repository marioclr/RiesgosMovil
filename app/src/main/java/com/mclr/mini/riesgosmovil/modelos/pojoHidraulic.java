package com.mclr.mini.riesgosmovil.modelos;

import java.math.BigDecimal;

public class pojoHidraulic {
	private String propertyID;
	private String name;
	private String soilType;
	private String buildingDate;
	private String drinkingWaterNet;
	private String drainNet;
	private String closestWaterBody;
	private boolean floodFormer;
	private boolean hurricaneFormer;
	private boolean collapseFormer;
	private boolean earthQuackeFormer;
	private boolean landSlideFormer;
	private double latidude;
	private double longitude;
	private double altitude;
	private String postalCodeID;
	private String postalCode;
	private String address;
	private String colony;
	private String stateID;
	private String stateName;
	private String town;
	private String city;
	private BigDecimal annualMaintenance;
	private BigDecimal renewalValue;
	private BigDecimal civilWorksValue;
	private BigDecimal equipmentMachineryValue;
	private BigDecimal itemsRenualValue;
	private String itemsDetail;
	private String foundationType;
	private String sturcturaltype;
	private String wallsType;
	private String catchmentType;
	private String waterTreatmentPlantType;
	private String treatedWaterStorageType;
	private String pipesType;
	private String channelsType;
	private String crossType;
	private String damType;
	private String pumpingStation;
	private String gatesTypes;
	private String sewerNet;
	private String intakeType;
	private double sewers;
	private double colectors;
	private double interceptors;
	private double wasteWaterTreamtentPlants;
	private double spouts;
	private double guttersTrenchCaceres;
	private double hoppers;
	private double manholes;
	private double sewersPumpingStation;
	private double driveLines;
	private double sewerContainer;
	private String hidraulicComponents;
	private String nonHidraulicComponents;
	private String structuralMedia;
	private BigDecimal[] siniestralityValues;
	private String[] siniestralityDesccriptions;

	private int status;
	private int finding;

	public pojoHidraulic(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		name = "";
		stateName = "";
		city = "";
		floodFormer = false;
		hurricaneFormer = false;
		collapseFormer = false;
		earthQuackeFormer = false;
		landSlideFormer = false;
		hidraulicComponents = "";
		nonHidraulicComponents = "";
		structuralMedia = "";
		foundationType = "";
		sturcturaltype = "";
		wallsType = "";
		catchmentType = "";
		waterTreatmentPlantType = "";
		treatedWaterStorageType = "";
		pipesType = "";
		channelsType = "";
		crossType = "";
		damType = "";
		pumpingStation = "";
		gatesTypes = "";
		sewerNet = "";
		intakeType = "";
		soilType = "";
		buildingDate = "";
		drinkingWaterNet = "";
		drainNet = "";
		closestWaterBody = "";
		postalCodeID = "";
		postalCode = "";
		address = "";
		colony = "";
		stateID = "";
		town = "";
		latidude = 0;
		longitude = 0;
		altitude = 0;
		annualMaintenance = new BigDecimal("0");
		renewalValue = new BigDecimal("0");
		civilWorksValue = new BigDecimal("0");
		equipmentMachineryValue = new BigDecimal("0");
		itemsRenualValue = new BigDecimal("0");
		itemsDetail = "";
		sewers = 0;
		colectors = 0;
		interceptors = 0;
		wasteWaterTreamtentPlants = 0;
		spouts = 0;
		guttersTrenchCaceres = 0;
		hoppers = 0;
		manholes = 0;
		sewersPumpingStation = 0;
		driveLines = 0;
		sewerContainer = 0;
		siniestralityValues = new BigDecimal[10];
		siniestralityDesccriptions = new String[10];
		for (int i=0;i<10;i++){
			siniestralityValues[i] = new BigDecimal("0");
			siniestralityDesccriptions[i] = "";
		}


	}
	
	/**
	 * @return the soilType
	 */
	public String getSoilType() {
		return soilType;
	}
	/**
	 * @param soilType the soilType to set
	 */
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	/**
	 * @return the buildingDate
	 */
	public String getBuildingDate() {
		return buildingDate;
	}
	/**
	 * @param buildingDate the buildingDate to set
	 */
	public void setBuildingDate(String buildingDate) {
		this.buildingDate = buildingDate;
	}
	/**
	 * @return the drinkingWaterNet
	 */
	public String getDrinkingWaterNet() {
		return drinkingWaterNet;
	}
	/**
	 * @param drinkingWaterNet the drinkingWaterNet to set
	 */
	public void setDrinkingWaterNet(String drinkingWaterNet) {
		this.drinkingWaterNet = drinkingWaterNet;
	}
	/**
	 * @return the drainNet
	 */
	public String getDrainNet() {
		return drainNet;
	}
	/**
	 * @param drainNet the drainNet to set
	 */
	public void setDrainNet(String drainNet) {
		this.drainNet = drainNet;
	}
	/**
	 * @return the closestWaterBody
	 */
	public String getClosestWaterBody() {
		return closestWaterBody;
	}
	/**
	 * @param closestWaterBody the closestWaterBody to set
	 */
	public void setClosestWaterBody(String closestWaterBody) {
		this.closestWaterBody = closestWaterBody;
	}
	/**
	 * @return the floodFormer
	 */
	public boolean isFloodFormer() {
		return floodFormer;
	}
	/**
	 * @param floodFormer the floodFormer to set
	 */
	public void setFloodFormer(boolean floodFormer) {
		this.floodFormer = floodFormer;
	}
	/**
	 * @return the hurricaneFormer
	 */
	public boolean isHurricaneFormer() {
		return hurricaneFormer;
	}
	/**
	 * @param hurricaneFormer the hurricaneFormer to set
	 */
	public void setHurricaneFormer(boolean hurricaneFormer) {
		this.hurricaneFormer = hurricaneFormer;
	}
	/**
	 * @return the collapseFormer
	 */
	public boolean isCollapseFormer() {
		return collapseFormer;
	}
	/**
	 * @param collapseFormer the collapseFormer to set
	 */
	public void setCollapseFormer(boolean collapseFormer) {
		this.collapseFormer = collapseFormer;
	}
	/**
	 * @return the earthQuackeFormer
	 */
	public boolean isEarthQuackeFormer() {
		return earthQuackeFormer;
	}
	/**
	 * @param earthQuackeFormer the earthQuackeFormer to set
	 */
	public void setEarthQuackeFormer(boolean earthQuackeFormer) {
		this.earthQuackeFormer = earthQuackeFormer;
	}
	/**
	 * @return the landSlideFormer
	 */
	public boolean isLandSlideFormer() {
		return landSlideFormer;
	}
	/**
	 * @param landSlideFormer the landSlideFormer to set
	 */
	public void setLandSlideFormer(boolean landSlideFormer) {
		this.landSlideFormer = landSlideFormer;
	}
	/**
	 * @return the latidude
	 */
	public double getLatidude() {
		return latidude;
	}
	/**
	 * @param latidude the latidude to set
	 */
	public void setLatidude(double latidude) {
		this.latidude = latidude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}
	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	/**
	 * @return the postalCodeID
	 */
	public String getPostalCodeID() {
		return postalCodeID;
	}
	/**
	 * @param postalCodeID the postalCodeID to set
	 */
	public void setPostalCodeID(String postalCodeID) {
		this.postalCodeID = postalCodeID;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the colony
	 */
	public String getColony() {
		return colony;
	}
	/**
	 * @param colony the colony to set
	 */
	public void setColony(String colony) {
		this.colony = colony;
	}
	/**
	 * @return the stateID
	 */
	public String getStateID() {
		return stateID;
	}
	/**
	 * @param stateID the stateID to set
	 */
	public void setStateID(String stateID) {
		this.stateID = stateID;
	}
	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}
	/**
	 * @return the annualMaintenance
	 */
	public BigDecimal getAnnualMaintenance() {
		return annualMaintenance;
	}
	/**
	 * @param annualMaintenance the annualMaintenance to set
	 */
	public void setAnnualMaintenance(BigDecimal annualMaintenance) {
		this.annualMaintenance = annualMaintenance;
	}
	/**
	 * @return the renewalValue
	 */
	public BigDecimal getRenewalValue() {
		return renewalValue;
	}
	/**
	 * @param renewalValue the renewalValue to set
	 */
	public void setRenewalValue(BigDecimal renewalValue) {
		this.renewalValue = renewalValue;
	}
	/**
	 * @return the civilWorksValue
	 */
	public BigDecimal getCivilWorksValue() {
		return civilWorksValue;
	}
	/**
	 * @param civilWorksValue the civilWorksValue to set
	 */
	public void setCivilWorksValue(BigDecimal civilWorksValue) {
		this.civilWorksValue = civilWorksValue;
	}
	/**
	 * @return the equipmentMachineryValue
	 */
	public BigDecimal getEquipmentMachineryValue() {
		return equipmentMachineryValue;
	}
	/**
	 * @param equipmentMachineryValue the equipmentMachineryValue to set
	 */
	public void setEquipmentMachineryValue(BigDecimal equipmentMachineryValue) {
		this.equipmentMachineryValue = equipmentMachineryValue;
	}
	/**
	 * @return the itemsRenualValue
	 */
	public BigDecimal getItemsRenualValue() {
		return itemsRenualValue;
	}
	/**
	 * @param itemsRenualValue the itemsRenualValue to set
	 */
	public void setItemsRenualValue(BigDecimal itemsRenualValue) {
		this.itemsRenualValue = itemsRenualValue;
	}
	/**
	 * @return the foundationType
	 */
	public String getFoundationType() {
		return foundationType;
	}
	/**
	 * @param foundationType the foundationType to set
	 */
	public void setFoundationType(String foundationType) {
		this.foundationType = foundationType;
	}
	/**
	 * @return the sturcturaltype
	 */
	public String getSturcturaltype() {
		return sturcturaltype;
	}
	/**
	 * @param sturcturaltype the sturcturaltype to set
	 */
	public void setSturcturaltype(String sturcturaltype) {
		this.sturcturaltype = sturcturaltype;
	}
	/**
	 * @return the wallsType
	 */
	public String getWallsType() {
		return wallsType;
	}
	/**
	 * @param wallsType the wallsType to set
	 */
	public void setWallsType(String wallsType) {
		this.wallsType = wallsType;
	}
	/**
	 * @return the catchmentType
	 */
	public String getCatchmentType() {
		return catchmentType;
	}
	/**
	 * @param catchmentType the catchmentType to set
	 */
	public void setCatchmentType(String catchmentType) {
		this.catchmentType = catchmentType;
	}
	/**
	 * @return the waterTreatmentPlantType
	 */
	public String getWaterTreatmentPlantType() {
		return waterTreatmentPlantType;
	}
	/**
	 * @param waterTreatmentPlantType the waterTreatmentPlantType to set
	 */
	public void setWaterTreatmentPlantType(String waterTreatmentPlantType) {
		this.waterTreatmentPlantType = waterTreatmentPlantType;
	}
	/**
	 * @return the treatedWaterStorageType
	 */
	public String getTreatedWaterStorageType() {
		return treatedWaterStorageType;
	}
	/**
	 * @param treatedWaterStorageType the treatedWaterStorageType to set
	 */
	public void setTreatedWaterStorageType(String treatedWaterStorageType) {
		this.treatedWaterStorageType = treatedWaterStorageType;
	}
	/**
	 * @return the pipesType
	 */
	public String getPipesType() {
		return pipesType;
	}
	/**
	 * @param pipesType the pipesType to set
	 */
	public void setPipesType(String pipesType) {
		this.pipesType = pipesType;
	}
	/**
	 * @return the channelsType
	 */
	public String getChannelsType() {
		return channelsType;
	}
	/**
	 * @param channelsType the channelsType to set
	 */
	public void setChannelsType(String channelsType) {
		this.channelsType = channelsType;
	}
	/**
	 * @return the crossType
	 */
	public String getCrossType() {
		return crossType;
	}
	/**
	 * @param crossType the crossType to set
	 */
	public void setCrossType(String crossType) {
		this.crossType = crossType;
	}
	/**
	 * @return the damType
	 */
	public String getDamType() {
		return damType;
	}
	/**
	 * @param damType the damType to set
	 */
	public void setDamType(String damType) {
		this.damType = damType;
	}
	/**
	 * @return the pumpingStation
	 */
	public String getPumpingStation() {
		return pumpingStation;
	}
	/**
	 * @param pumpingStation the pumpingStation to set
	 */
	public void setPumpingStation(String pumpingStation) {
		this.pumpingStation = pumpingStation;
	}
	/**
	 * @return the gatesTypes
	 */
	public String getGatesTypes() {
		return gatesTypes;
	}
	/**
	 * @param gatesTypes the gatesTypes to set
	 */
	public void setGatesTypes(String gatesTypes) {
		this.gatesTypes = gatesTypes;
	}
	/**
	 * @return the sewerNet
	 */
	public String getSewerNet() {
		return sewerNet;
	}
	/**
	 * @param sewerNet the sewerNet to set
	 */
	public void setSewerNet(String sewerNet) {
		this.sewerNet = sewerNet;
	}
	/**
	 * @return the intakeType
	 */
	public String getIntakeType() {
		return intakeType;
	}
	/**
	 * @param intakeType the intakeType to set
	 */
	public void setIntakeType(String intakeType) {
		this.intakeType = intakeType;
	}
	/**
	 * @return the sewers
	 */
	public double getSewers() {
		return sewers;
	}
	/**
	 * @param sewers the sewers to set
	 */
	public void setSewers(double sewers) {
		this.sewers = sewers;
	}
	/**
	 * @return the colectors
	 */
	public double getColectors() {
		return colectors;
	}
	/**
	 * @param colectors the colectors to set
	 */
	public void setColectors(double colectors) {
		this.colectors = colectors;
	}
	/**
	 * @return the interceptors
	 */
	public double getInterceptors() {
		return interceptors;
	}
	/**
	 * @param interceptors the interceptors to set
	 */
	public void setInterceptors(double interceptors) {
		this.interceptors = interceptors;
	}
	/**
	 * @return the wasteWaterTreamtentPlants
	 */
	public double getWasteWaterTreamtentPlants() {
		return wasteWaterTreamtentPlants;
	}
	/**
	 * @param wasteWaterTreamtentPlants the wasteWaterTreamtentPlants to set
	 */
	public void setWasteWaterTreamtentPlants(double wasteWaterTreamtentPlants) {
		this.wasteWaterTreamtentPlants = wasteWaterTreamtentPlants;
	}
	/**
	 * @return the spouts
	 */
	public double getSpouts() {
		return spouts;
	}
	/**
	 * @param spouts the spouts to set
	 */
	public void setSpouts(double spouts) {
		this.spouts = spouts;
	}
	/**
	 * @return the guttersTrenchCaceres
	 */
	public double getGuttersTrenchCaceres() {
		return guttersTrenchCaceres;
	}
	/**
	 * @param guttersTrenchCaceres the guttersTrenchCaceres to set
	 */
	public void setGuttersTrenchCaceres(double guttersTrenchCaceres) {
		this.guttersTrenchCaceres = guttersTrenchCaceres;
	}
	/**
	 * @return the hoppers
	 */
	public double getHoppers() {
		return hoppers;
	}
	/**
	 * @param hoppers the hoppers to set
	 */
	public void setHoppers(double hoppers) {
		this.hoppers = hoppers;
	}
	/**
	 * @return the manholes
	 */
	public double getManholes() {
		return manholes;
	}
	/**
	 * @param manholes the manholes to set
	 */
	public void setManholes(double manholes) {
		this.manholes = manholes;
	}
	/**
	 * @return the sewersPumpingStation
	 */
	public double getSewersPumpingStation() {
		return sewersPumpingStation;
	}
	/**
	 * @param sewersPumpingStation the sewersPumpingStation to set
	 */
	public void setSewersPumpingStation(double sewersPumpingStation) {
		this.sewersPumpingStation = sewersPumpingStation;
	}
	/**
	 * @return the driveLines
	 */
	public double getDriveLines() {
		return driveLines;
	}
	/**
	 * @param driveLines the driveLines to set
	 */
	public void setDriveLines(double driveLines) {
		this.driveLines = driveLines;
	}
	/**
	 * @return the sewerContainer
	 */
	public double getSewerContainer() {
		return sewerContainer;
	}
	/**
	 * @param sewerContainer the sewerContainer to set
	 */
	public void setSewerContainer(double sewerContainer) {
		this.sewerContainer = sewerContainer;
	}
	/**
	 * @return the hidraulicComponents
	 */
	public String getHidraulicComponents() {
		return hidraulicComponents;
	}
	/**
	 * @param hidraulicComponents the hidraulicComponents to set
	 */
	public void setHidraulicComponents(String hidraulicComponents) {
		this.hidraulicComponents = hidraulicComponents;
	}
	/**
	 * @return the nonHidraulicComponents
	 */
	public String getNonHidraulicComponents() {
		return nonHidraulicComponents;
	}
	/**
	 * @param nonHidraulicComponents the nonHidraulicComponents to set
	 */
	public void setNonHidraulicComponents(String nonHidraulicComponents) {
		this.nonHidraulicComponents = nonHidraulicComponents;
	}
	/**
	 * @return the structuralMedia
	 */
	public String getStructuralMedia() {
		return structuralMedia;
	}
	/**
	 * @param structuralMedia the structuralMedia to set
	 */
	public void setStructuralMedia(String structuralMedia) {
		this.structuralMedia = structuralMedia;
	}
	/**
	 * @return the siniestralityValues
	 */
	public BigDecimal[] getSiniestralityValues() {
		return siniestralityValues;
	}
	/**
	 * @param siniestralityValues the siniestralityValues to set
	 */
	public void setSiniestralityValues(BigDecimal[] siniestralityValues) {
		this.siniestralityValues = siniestralityValues;
	}
	/**
	 * @return the siniestralityDesccriptions
	 */
	public String[] getSiniestralityDesccriptions() {
		return siniestralityDesccriptions;
	}
	/**
	 * @param siniestralityDesccriptions the siniestralityDesccriptions to set
	 */
	public void setSiniestralityDesccriptions(String[] siniestralityDesccriptions) {
		this.siniestralityDesccriptions = siniestralityDesccriptions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the propertyID
	 */
	public String getPropertyID() {
		return propertyID;
	}

	/**
	 * @param propertyID the propertyID to set
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	/**
	 * @return the itemsDetail
	 */
	public String getItemsDetail() {
		return itemsDetail;
	}

	/**
	 * @param itemsDetail the itemsDetail to set
	 */
	public void setItemsDetail(String itemsDetail) {
		this.itemsDetail = itemsDetail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFinding() {
		return finding;
	}

	public void setFinding(int finding) {
		this.finding = finding;
	}

}
