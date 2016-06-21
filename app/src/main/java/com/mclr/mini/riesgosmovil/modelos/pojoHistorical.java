package com.mclr.mini.riesgosmovil.modelos;

public class pojoHistorical {
	private String propertyID;
	private String buildingName;
	private String buildingNumber;
	private String managedBy;
	private double longitude;
	private double latitude;
	private double altitude;
	private String ZIP;
	private String address;
	private String colony;
	private String stateName;
	private String town;
	private String city;
	
	private String state;
	private String postalCodeID;
	private String postalCode;
	private String waterBody;
	private String[] siniestrality;
	private String[] siniestralityDesc;
	private String rlBuildingValue;
	private String rnBuildingValue;
	private String rlComputingValue;
	private String rnComputingValue;
	private String rlElectronicValue;
	private String rnElectronicValue;
	private String rlBooksValue;
	private String rnBooksValue;
	private String rlCDsValue;
	private String rnCDsValue;
	private String rlStorageValue;
	private String rnStorageValue;
	private String rlFurnituresValue;
	private String rnFurnituresValue;
	private String rlItemsValue;
	private String rnItemsValue;
	private boolean floodRisk;
	private boolean hurricanRisk;
	private boolean rockFallRisk;
	private boolean earthQuakeRisk;
	private boolean slidingRisk;

	private int status;
	private int finding;

	public pojoHistorical(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		buildingName = "";
		buildingNumber = "";
		managedBy = "";
		longitude = 0;
		latitude = 0;
		altitude = 0;
		ZIP = "";
		address = "";
		colony = "";
		stateName = "";
		town = "";
		city = "";
		postalCodeID = "";
		postalCode = "";
		waterBody = "";
		siniestrality = new String[10];
		siniestralityDesc = new String[10];
		floodRisk = false;
		hurricanRisk = false;
		rockFallRisk = false;
		earthQuakeRisk = false;
		slidingRisk = false;
		for (int i=0;i<10;i++){
			siniestrality[i] = new String("0.0");
			siniestralityDesc[i] = "";
		}
		
		rlBuildingValue = "0.0";
		rnBuildingValue = "0.0";
		rlComputingValue = "0.0";
		rnComputingValue = "0.0";
		rlElectronicValue = "0.0";
		rnElectronicValue = "0.0";
		rlBooksValue = "0.0";
		rnBooksValue = "0.0";
		rlCDsValue = "0.0";
		rnCDsValue = "0.0";
		rlStorageValue = "0.0";
		rnStorageValue = "0.0";
		rlFurnituresValue = "0.0";
		rnFurnituresValue = "0.0";
		rlItemsValue = "0.0";
		rnItemsValue = "0.0";

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
	 * @return the buildingNumber
	 */
	public String getBuildingNumber() {
		return buildingNumber;
	}

	/**
	 * @param buildingNumber the buildingNumber to set
	 */
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	/**
	 * @return the managedBy
	 */
	public String getManagedBy() {
		return managedBy;
	}

	/**
	 * @param managedBy the managedBy to set
	 */
	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
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
	 * @return the waterBody
	 */
	public String getWaterBody() {
		return waterBody;
	}

	/**
	 * @param waterBody the waterBody to set
	 */
	public void setWaterBody(String waterBody) {
		this.waterBody = waterBody;
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
	 * @return the rlComputingValue
	 */
	public String getRlComputingValue() {
		return rlComputingValue;
	}

	/**
	 * @param rlComputingValue the rlComputingValue to set
	 */
	public void setRlComputingValue(String rlComputingValue) {
		this.rlComputingValue = rlComputingValue;
	}

	/**
	 * @return the rnComputingValue
	 */
	public String getRnComputingValue() {
		return rnComputingValue;
	}

	/**
	 * @param rnComputingValue the rnComputingValue to set
	 */
	public void setRnComputingValue(String rnComputingValue) {
		this.rnComputingValue = rnComputingValue;
	}

	/**
	 * @return the rlElectronicValue
	 */
	public String getRlElectronicValue() {
		return rlElectronicValue;
	}

	/**
	 * @param rlElectronicValue the rlElectronicValue to set
	 */
	public void setRlElectronicValue(String rlElectronicValue) {
		this.rlElectronicValue = rlElectronicValue;
	}

	/**
	 * @return the rnElectronicValue
	 */
	public String getRnElectronicValue() {
		return rnElectronicValue;
	}

	/**
	 * @param rnElectronicValue the rnElectronicValue to set
	 */
	public void setRnElectronicValue(String rnElectronicValue) {
		this.rnElectronicValue = rnElectronicValue;
	}

	/**
	 * @return the rlBooksValue
	 */
	public String getRlBooksValue() {
		return rlBooksValue;
	}

	/**
	 * @param rlBooksValue the rlBooksValue to set
	 */
	public void setRlBooksValue(String rlBooksValue) {
		this.rlBooksValue = rlBooksValue;
	}

	/**
	 * @return the rnBooksValue
	 */
	public String getRnBooksValue() {
		return rnBooksValue;
	}

	/**
	 * @param rnBooksValue the rnBooksValue to set
	 */
	public void setRnBooksValue(String rnBooksValue) {
		this.rnBooksValue = rnBooksValue;
	}

	/**
	 * @return the rlCDsValue
	 */
	public String getRlCDsValue() {
		return rlCDsValue;
	}

	/**
	 * @param rlCDsValue the rlCDsValue to set
	 */
	public void setRlCDsValue(String rlCDsValue) {
		this.rlCDsValue = rlCDsValue;
	}

	/**
	 * @return the rnCDsValue
	 */
	public String getRnCDsValue() {
		return rnCDsValue;
	}

	/**
	 * @param rnCDsValue the rnCDsValue to set
	 */
	public void setRnCDsValue(String rnCDsValue) {
		this.rnCDsValue = rnCDsValue;
	}

	/**
	 * @return the rlStorageValue
	 */
	public String getRlStorageValue() {
		return rlStorageValue;
	}

	/**
	 * @param rlStorageValue the rlStorageValue to set
	 */
	public void setRlStorageValue(String rlStorageValue) {
		this.rlStorageValue = rlStorageValue;
	}

	/**
	 * @return the rnStorageValue
	 */
	public String getRnStorageValue() {
		return rnStorageValue;
	}

	/**
	 * @param rnStorageValue the rnStorageValue to set
	 */
	public void setRnStorageValue(String rnStorageValue) {
		this.rnStorageValue = rnStorageValue;
	}

	/**
	 * @return the rlFurnituresValue
	 */
	public String getRlFurnituresValue() {
		return rlFurnituresValue;
	}

	/**
	 * @param rlFurnituresValue the rlFurnituresValue to set
	 */
	public void setRlFurnituresValue(String rlFurnituresValue) {
		this.rlFurnituresValue = rlFurnituresValue;
	}

	/**
	 * @return the rnFurnituresValue
	 */
	public String getRnFurnituresValue() {
		return rnFurnituresValue;
	}

	/**
	 * @param rnFurnituresValue the rnFurnituresValue to set
	 */
	public void setRnFurnituresValue(String rnFurnituresValue) {
		this.rnFurnituresValue = rnFurnituresValue;
	}

	/**
	 * @return the rlItemsValue
	 */
	public String getRlItemsValue() {
		return rlItemsValue;
	}

	/**
	 * @param rlItemsValue the rlItemsValue to set
	 */
	public void setRlItemsValue(String rlItemsValue) {
		this.rlItemsValue = rlItemsValue;
	}

	/**
	 * @return the rnItemsValue
	 */
	public String getRnItemsValue() {
		return rnItemsValue;
	}

	/**
	 * @param rnItemsValue the rnItemsValue to set
	 */
	public void setRnItemsValue(String rnItemsValue) {
		this.rnItemsValue = rnItemsValue;
	}

	/**
	 * @return the floodRisk
	 */
	public boolean hasFloodRisk() {
		return floodRisk;
	}

	/**
	 * @param floodRisk the floodRisk to set
	 */
	public void setFloodRisk(boolean floodRisk) {
		this.floodRisk = floodRisk;
	}

	/**
	 * @return the hurricanRisk
	 */
	public boolean hasHurricanRisk() {
		return hurricanRisk;
	}

	/**
	 * @param hurricanRisk the hurricanRisk to set
	 */
	public void setHurricanRisk(boolean hurricanRisk) {
		this.hurricanRisk = hurricanRisk;
	}

	/**
	 * @return the rockFallRisk
	 */
	public boolean hasRockFallRisk() {
		return rockFallRisk;
	}

	/**
	 * @param rockFallRisk the rockFallRisk to set
	 */
	public void setRockFallRisk(boolean rockFallRisk) {
		this.rockFallRisk = rockFallRisk;
	}

	/**
	 * @return the earthQuakeRisk
	 */
	public boolean hasEarthQuakeRisk() {
		return earthQuakeRisk;
	}

	/**
	 * @param earthQuakeRisk the earthQuakeRisk to set
	 */
	public void setEarthQuakeRisk(boolean earthQuakeRisk) {
		this.earthQuakeRisk = earthQuakeRisk;
	}

	/**
	 * @return the slidingRisk
	 */
	public boolean hasSlidingRisk() {
		return slidingRisk;
	}

	/**
	 * @param slidingRisk the slidingRisk to set
	 */
	public void setSlidingRisk(boolean slidingRisk) {
		this.slidingRisk = slidingRisk;
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
