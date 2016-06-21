package com.mclr.mini.riesgosmovil.modelos;

public class pojoAquaculture {
	private String propertyID;
	private String buildingName;
	private String centerType;
	private double longitude;
	private double latitude;
	private double altitude;
	private String ZIP;
	private String address;
	private String colony;
	private String state;
	private String city;
	private String stateName;
	private String town;
	private String postalCodeID;
	private String postalCode;

	private String structureType;
	private String foundationType;
	private String wallsType;
	private String blackSmithType;
	private String glasseryType;
	private String fachadeType;
	private String specialFacilities;
	
	private String[] siniestrality;
	private String[] siniestralityDesc;
	
	private String rlBuildingValue;
	private String rnBuildingValue;
	private String rnBuildingDescription;
	private String rlTankRehabValue;
	private String rnTankRehabValue;
	private String rnTankRehabDescription;
	private String rlCancelValue;
	private String rnCancelValue;
	private String rnCancelDescription;
	private String rlMeshValue;
	private String rnMeshValue;
	private String rnMeshDescription;
	private String rlPaintingValue;
	private String rnPaintingValue;
	private String rnPaintingDescription;
	private String rlRoofsValue;
	private String rnRoofsValue;
	private String rnRoofsDescription;
	private String rlFeedNetCDValue;
	private String rnFeedNetCDValue;
	private String rnFeedNetCDDescription;
	private String rlWaterNetCDValue;
	private String rnWaterNetCDValue;
	private String rnWaterNetCDDescription;
	private String rlElectricNetCDValue;
	private String rnElectricNetCDValue;
	private String rnElectricNetCDDescription;
	private String rlSanitaryNetValue;
	private String rnSanitaryNetValue;
	private String sanitaryNetDescription;
	private String rlSanitaryIntallValue;
	private String rnSanitaryIntallValue;
	private String sanitaryInstalDescription;
	private String rlSanitaryEMNetValue;
	private String rnSanitaryEMNetValue;
	private String EMNetDescription;

	private int status;
	private int finding;

	public pojoAquaculture(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		buildingName = "";
		centerType = "";
		ZIP = "";
		address = "";
		colony = "";
		state = "";
		stateName = "";
		city = "";
		town = "";
		postalCodeID = "";
		postalCode = "";
		structureType = "";
		foundationType = "";
		wallsType = "";
		blackSmithType = "";
		glasseryType = "";
		fachadeType = "";
		specialFacilities = "";
		sanitaryNetDescription = "";
		sanitaryInstalDescription = "";
		EMNetDescription = "";
		rlBuildingValue = "0.0";
		rnBuildingValue = "0.0";
		rlTankRehabValue = "0.0";
		rnTankRehabValue = "0.0";
		rlCancelValue = "0.0";
		rnCancelValue = "0.0";
		rlMeshValue = "0.0";
		rnMeshValue = "0.0";
		rlPaintingValue = "0.0";
		rnPaintingValue = "0.0";
		rlRoofsValue = "0.0";
		rnRoofsValue = "0.0";
		rlFeedNetCDValue = "0.0";
		rnFeedNetCDValue = "0.0";
		rlWaterNetCDValue = "0.0";
		rnWaterNetCDValue = "0.0";
		rlElectricNetCDValue = "0.0";
		rnElectricNetCDValue = "0.0";
		rlSanitaryNetValue = "0.0";
		rnSanitaryNetValue = "0.0";
		rlSanitaryIntallValue = "0.0";
		rnSanitaryIntallValue = "0.0";
		rlSanitaryEMNetValue = "0.0";
		rnSanitaryEMNetValue = "0.0";
		rnBuildingDescription = "";
		rnTankRehabDescription = "";
		rnCancelDescription = "";
		rnMeshDescription = "";
		rnPaintingDescription = "";
		rnRoofsDescription = "";
		rnFeedNetCDDescription = "";
		rnWaterNetCDDescription = "";
		rnElectricNetCDDescription = "";
		sanitaryNetDescription = "";
		sanitaryInstalDescription = "";
		EMNetDescription = "";
		longitude = 0;
		latitude = 0;
		altitude = 0;
		siniestrality = new String[10];
		siniestralityDesc = new String[10];
		for (int i=0; i<10;i++){
			siniestrality[i] = "0.0";
			siniestralityDesc[i] = "";
		}
	}

	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * @return the centerType
	 */
	public String getCenterType() {
		return centerType;
	}

	/**
	 * @param centerType the centerType to set
	 */
	public void setCenterType(String centerType) {
		this.centerType = centerType;
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
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
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
	 * @return the zIP
	 */
	public String getZIP() {
		return ZIP;
	}

	/**
	 * @param zIP the zIP to set
	 */
	public void setZIP(String zIP) {
		ZIP = zIP;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the structureType
	 */
	public String getStructureType() {
		return structureType;
	}

	/**
	 * @param structureType the structureType to set
	 */
	public void setStructureType(String structureType) {
		this.structureType = structureType;
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
	 * @return the blackSmithType
	 */
	public String getBlackSmithType() {
		return blackSmithType;
	}

	/**
	 * @param blackSmithType the blackSmithType to set
	 */
	public void setBlackSmithType(String blackSmithType) {
		this.blackSmithType = blackSmithType;
	}

	/**
	 * @return the glasseryType
	 */
	public String getGlasseryType() {
		return glasseryType;
	}

	/**
	 * @param glasseryType the glasseryType to set
	 */
	public void setGlasseryType(String glasseryType) {
		this.glasseryType = glasseryType;
	}

	/**
	 * @return the fachadeType
	 */
	public String getFachadeType() {
		return fachadeType;
	}

	/**
	 * @param fachadeType the fachadeType to set
	 */
	public void setFachadeType(String fachadeType) {
		this.fachadeType = fachadeType;
	}

	/**
	 * @return the specialFacilities
	 */
	public String getSpecialFacilities() {
		return specialFacilities;
	}

	/**
	 * @param specialFacilities the specialFacilities to set
	 */
	public void setSpecialFacilities(String specialFacilities) {
		this.specialFacilities = specialFacilities;
	}

	/**
	 * @return the rlBuildingValue
	 */
	public String getRlBuildingValue() {
		return rlBuildingValue;
	}

	/**
	 * @param rlBuildingValue the rlBuildingValue to set
	 */
	public void setRlBuildingValue(String rlBuildingValue) {
		this.rlBuildingValue = rlBuildingValue;
	}

	/**
	 * @return the rnBuildingValue
	 */
	public String getRnBuildingValue() {
		return rnBuildingValue;
	}

	/**
	 * @param rnBuildingValue the rnBuildingValue to set
	 */
	public void setRnBuildingValue(String rnBuildingValue) {
		this.rnBuildingValue = rnBuildingValue;
	}

	/**
	 * @return the rnBuildingDescription
	 */
	public String getRnBuildingDescription() {
		return rnBuildingDescription;
	}

	/**
	 * @param rnBuildingDescription the rnBuildingDescription to set
	 */
	public void setRnBuildingDescription(String rnBuildingDescription) {
		this.rnBuildingDescription = rnBuildingDescription;
	}

	/**
	 * @return the rlTankRehabValue
	 */
	public String getRlTankRehabValue() {
		return rlTankRehabValue;
	}

	/**
	 * @param rlTankRehabValue the rlTankRehabValue to set
	 */
	public void setRlTankRehabValue(String rlTankRehabValue) {
		this.rlTankRehabValue = rlTankRehabValue;
	}

	/**
	 * @return the rnTankRehabValue
	 */
	public String getRnTankRehabValue() {
		return rnTankRehabValue;
	}

	/**
	 * @param rnTankRehabValue the rnTankRehabValue to set
	 */
	public void setRnTankRehabValue(String rnTankRehabValue) {
		this.rnTankRehabValue = rnTankRehabValue;
	}

	/**
	 * @return the rnTankRehabDescription
	 */
	public String getRnTankRehabDescription() {
		return rnTankRehabDescription;
	}

	/**
	 * @param rnTankRehabDescription the rnTankRehabDescription to set
	 */
	public void setRnTankRehabDescription(String rnTankRehabDescription) {
		this.rnTankRehabDescription = rnTankRehabDescription;
	}

	/**
	 * @return the rlCancelValue
	 */
	public String getRlCancelValue() {
		return rlCancelValue;
	}

	/**
	 * @param rlCancelValue the rlCancelValue to set
	 */
	public void setRlCancelValue(String rlCancelValue) {
		this.rlCancelValue = rlCancelValue;
	}

	/**
	 * @return the rnCancelValue
	 */
	public String getRnCancelValue() {
		return rnCancelValue;
	}

	/**
	 * @param rnCancelValue the rnCancelValue to set
	 */
	public void setRnCancelValue(String rnCancelValue) {
		this.rnCancelValue = rnCancelValue;
	}

	/**
	 * @return the rnCancelDescription
	 */
	public String getRnCancelDescription() {
		return rnCancelDescription;
	}

	/**
	 * @param rnCancelDescription the rnCancelDescription to set
	 */
	public void setRnCancelDescription(String rnCancelDescription) {
		this.rnCancelDescription = rnCancelDescription;
	}

	/**
	 * @return the rlMeshValue
	 */
	public String getRlMeshValue() {
		return rlMeshValue;
	}

	/**
	 * @param rlMeshValue the rlMeshValue to set
	 */
	public void setRlMeshValue(String rlMeshValue) {
		this.rlMeshValue = rlMeshValue;
	}

	/**
	 * @return the rnMeshValue
	 */
	public String getRnMeshValue() {
		return rnMeshValue;
	}

	/**
	 * @param rnMeshValue the rnMeshValue to set
	 */
	public void setRnMeshValue(String rnMeshValue) {
		this.rnMeshValue = rnMeshValue;
	}

	/**
	 * @return the rnMeshDescription
	 */
	public String getRnMeshDescription() {
		return rnMeshDescription;
	}

	/**
	 * @param rnMeshDescription the rnMeshDescription to set
	 */
	public void setRnMeshDescription(String rnMeshDescription) {
		this.rnMeshDescription = rnMeshDescription;
	}

	/**
	 * @return the rlPaintingValue
	 */
	public String getRlPaintingValue() {
		return rlPaintingValue;
	}

	/**
	 * @param rlPaintingValue the rlPaintingValue to set
	 */
	public void setRlPaintingValue(String rlPaintingValue) {
		this.rlPaintingValue = rlPaintingValue;
	}

	/**
	 * @return the rnPaintingValue
	 */
	public String getRnPaintingValue() {
		return rnPaintingValue;
	}

	/**
	 * @param rnPaintingValue the rnPaintingValue to set
	 */
	public void setRnPaintingValue(String rnPaintingValue) {
		this.rnPaintingValue = rnPaintingValue;
	}

	/**
	 * @return the rnPaintingDescription
	 */
	public String getRnPaintingDescription() {
		return rnPaintingDescription;
	}

	/**
	 * @param rnPaintingDescription the rnPaintingDescription to set
	 */
	public void setRnPaintingDescription(String rnPaintingDescription) {
		this.rnPaintingDescription = rnPaintingDescription;
	}

	/**
	 * @return the rlRoofsValue
	 */
	public String getRlRoofsValue() {
		return rlRoofsValue;
	}

	/**
	 * @param rlRoofsValue the rlRoofsValue to set
	 */
	public void setRlRoofsValue(String rlRoofsValue) {
		this.rlRoofsValue = rlRoofsValue;
	}

	/**
	 * @return the rnRoofsValue
	 */
	public String getRnRoofsValue() {
		return rnRoofsValue;
	}

	/**
	 * @param rnRoofsValue the rnRoofsValue to set
	 */
	public void setRnRoofsValue(String rnRoofsValue) {
		this.rnRoofsValue = rnRoofsValue;
	}

	/**
	 * @return the rnRoofsDescription
	 */
	public String getRnRoofsDescription() {
		return rnRoofsDescription;
	}

	/**
	 * @param rnRoofsDescription the rnRoofsDescription to set
	 */
	public void setRnRoofsDescription(String rnRoofsDescription) {
		this.rnRoofsDescription = rnRoofsDescription;
	}

	/**
	 * @return the rlFeedNetCDValue
	 */
	public String getRlFeedNetCDValue() {
		return rlFeedNetCDValue;
	}

	/**
	 * @param rlFeedNetCDValue the rlFeedNetCDValue to set
	 */
	public void setRlFeedNetCDValue(String rlFeedNetCDValue) {
		this.rlFeedNetCDValue = rlFeedNetCDValue;
	}

	/**
	 * @return the rnFeedNetCDValue
	 */
	public String getRnFeedNetCDValue() {
		return rnFeedNetCDValue;
	}

	/**
	 * @param rnFeedNetCDValue the rnFeedNetCDValue to set
	 */
	public void setRnFeedNetCDValue(String rnFeedNetCDValue) {
		this.rnFeedNetCDValue = rnFeedNetCDValue;
	}

	/**
	 * @return the rnFeedNetCDDescription
	 */
	public String getRnFeedNetCDDescription() {
		return rnFeedNetCDDescription;
	}

	/**
	 * @param rnFeedNetCDDescription the rnFeedNetCDDescription to set
	 */
	public void setRnFeedNetCDDescription(String rnFeedNetCDDescription) {
		this.rnFeedNetCDDescription = rnFeedNetCDDescription;
	}

	/**
	 * @return the rlWaterNetCDValue
	 */
	public String getRlWaterNetCDValue() {
		return rlWaterNetCDValue;
	}

	/**
	 * @param rlWaterNetCDValue the rlWaterNetCDValue to set
	 */
	public void setRlWaterNetCDValue(String rlWaterNetCDValue) {
		this.rlWaterNetCDValue = rlWaterNetCDValue;
	}

	/**
	 * @return the rnWaterNetCDValue
	 */
	public String getRnWaterNetCDValue() {
		return rnWaterNetCDValue;
	}

	/**
	 * @param rnWaterNetCDValue the rnWaterNetCDValue to set
	 */
	public void setRnWaterNetCDValue(String rnWaterNetCDValue) {
		this.rnWaterNetCDValue = rnWaterNetCDValue;
	}

	/**
	 * @return the rnWaterNetCDDescription
	 */
	public String getRnWaterNetCDDescription() {
		return rnWaterNetCDDescription;
	}

	/**
	 * @param rnWaterNetCDDescription the rnWaterNetCDDescription to set
	 */
	public void setRnWaterNetCDDescription(String rnWaterNetCDDescription) {
		this.rnWaterNetCDDescription = rnWaterNetCDDescription;
	}

	/**
	 * @return the rlElectricNetCDValue
	 */
	public String getRlElectricNetCDValue() {
		return rlElectricNetCDValue;
	}

	/**
	 * @param rlElectricNetCDValue the rlElectricNetCDValue to set
	 */
	public void setRlElectricNetCDValue(String rlElectricNetCDValue) {
		this.rlElectricNetCDValue = rlElectricNetCDValue;
	}

	/**
	 * @return the rnElectricNetCDValue
	 */
	public String getRnElectricNetCDValue() {
		return rnElectricNetCDValue;
	}

	/**
	 * @param rnElectricNetCDValue the rnElectricNetCDValue to set
	 */
	public void setRnElectricNetCDValue(String rnElectricNetCDValue) {
		this.rnElectricNetCDValue = rnElectricNetCDValue;
	}

	/**
	 * @return the rnElectricNetCDDescription
	 */
	public String getRnElectricNetCDDescription() {
		return rnElectricNetCDDescription;
	}

	/**
	 * @param rnElectricNetCDDescription the rnElectricNetCDDescription to set
	 */
	public void setRnElectricNetCDDescription(String rnElectricNetCDDescription) {
		this.rnElectricNetCDDescription = rnElectricNetCDDescription;
	}

	/**
	 * @return the rlSanitaryNetValue
	 */
	public String getRlSanitaryNetValue() {
		return rlSanitaryNetValue;
	}

	/**
	 * @param rlSanitaryNetValue the rlSanitaryNetValue to set
	 */
	public void setRlSanitaryNetValue(String rlSanitaryNetValue) {
		this.rlSanitaryNetValue = rlSanitaryNetValue;
	}

	/**
	 * @return the rnSanitaryIntallValue
	 */
	public String getRnSanitaryIntallValue() {
		return rnSanitaryIntallValue;
	}

	/**
	 * @param rnSanitaryIntallValue the rnSanitaryIntallValue to set
	 */
	public void setRnSanitaryIntallValue(String rnSanitaryIntallValue) {
		this.rnSanitaryIntallValue = rnSanitaryIntallValue;
	}

	/**
	 * @return the siniestrality
	 */
	public String[] getSiniestrality() {
		return siniestrality;
	}

	/**
	 * @param siniestrality the siniestrality to set
	 */
	public void setSiniestrality(String[] siniestrality) {
		this.siniestrality = siniestrality;
	}

	/**
	 * @return the siniestralityDesc
	 */
	public String[] getSiniestralityDesc() {
		return siniestralityDesc;
	}

	/**
	 * @param siniestralityDesc the siniestralityDesc to set
	 */
	public void setSiniestralityDesc(String[] siniestralityDesc) {
		this.siniestralityDesc = siniestralityDesc;
	}

	/**
	 * @return the rnSanitaryNetValue
	 */
	public String getRnSanitaryNetValue() {
		return rnSanitaryNetValue;
	}

	/**
	 * @param rnSanitaryNetValue the rnSanitaryNetValue to set
	 */
	public void setRnSanitaryNetValue(String rnSanitaryNetValue) {
		this.rnSanitaryNetValue = rnSanitaryNetValue;
	}

	/**
	 * @return the sanitaryNetDescription
	 */
	public String getSanitaryNetDescription() {
		return sanitaryNetDescription;
	}

	/**
	 * @param sanitaryNetDescription the sanitaryNetDescription to set
	 */
	public void setSanitaryNetDescription(String sanitaryNetDescription) {
		this.sanitaryNetDescription = sanitaryNetDescription;
	}

	/**
	 * @return the rlSanitaryIntallValue
	 */
	public String getRlSanitaryIntallValue() {
		return rlSanitaryIntallValue;
	}

	/**
	 * @param rlSanitaryIntallValue the rlSanitaryIntallValue to set
	 */
	public void setRlSanitaryIntallValue(String rlSanitaryIntallValue) {
		this.rlSanitaryIntallValue = rlSanitaryIntallValue;
	}

	/**
	 * @return the sanitaryInstalDescription
	 */
	public String getSanitaryInstalDescription() {
		return sanitaryInstalDescription;
	}

	/**
	 * @param sanitaryInstalDescription the sanitaryInstalDescription to set
	 */
	public void setSanitaryInstalDescription(String sanitaryInstalDescription) {
		this.sanitaryInstalDescription = sanitaryInstalDescription;
	}

	/**
	 * @return the rlSanitaryEMNetValue
	 */
	public String getRlSanitaryEMNetValue() {
		return rlSanitaryEMNetValue;
	}

	/**
	 * @param rlSanitaryEMNetValue the rlSanitaryEMNetValue to set
	 */
	public void setRlSanitaryEMNetValue(String rlSanitaryEMNetValue) {
		this.rlSanitaryEMNetValue = rlSanitaryEMNetValue;
	}

	/**
	 * @return the rnSanitaryEMNetValue
	 */
	public String getRnSanitaryEMNetValue() {
		return rnSanitaryEMNetValue;
	}

	/**
	 * @param rnSanitaryEMNetValue the rnSanitaryEMNetValue to set
	 */
	public void setRnSanitaryEMNetValue(String rnSanitaryEMNetValue) {
		this.rnSanitaryEMNetValue = rnSanitaryEMNetValue;
	}

	/**
	 * @return the eMNetDescription
	 */
	public String getEMNetDescription() {
		return EMNetDescription;
	}

	/**
	 * @param eMNetDescription the eMNetDescription to set
	 */
	public void setEMNetDescription(String eMNetDescription) {
		EMNetDescription = eMNetDescription;
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
