package com.mclr.mini.riesgosmovil.modelos;

public class pojoWasteDisposal {
	private String name;
	private String propertyID;
	private String wasteDisposalType;
	private double extension;
	private boolean earthQuakeRisk;
	private boolean collapseRisk;
	private boolean fleedRisk;
	private boolean hurricaneRisk;
	private boolean landSlideRisk;
	
	private boolean accessWays;
	private boolean innerWays;
	private boolean perimeterFence;
	private boolean securityRoom;
	private boolean bascule;
	private boolean waterElectricityDrain;
	private boolean sanitaryServices;
	private boolean cushioningStrip;
	private boolean offices;
	private boolean medicalServices;
	
	
	private double startLatitude;
	private double startLongitude;
	private double startAltitude;
	private double endLatitude;
	private double endLongitude;
	private double endAltitude;
	private String ZIP;
	private String address;
	private String colony;
	private String state;
	private String stateName;
	private String city;
	private String postalCode;
	private String stateID;
	private String town;
	private String postalCodeID;

	private String renewalValue;
	private String civilWorksValue;
	private String machineryEquipmentValue;
	private String annualMaintenanceAmount;
	private String foundationType;
	private String structuralType;
	private String wallsType;
	private String[] siniestralityValues;
	private String[] siniestralityDescription;

	private int status;
	private int finding;

	public pojoWasteDisposal(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		postalCodeID = "";
		name = "";
		wasteDisposalType = "";
		stateID = "";
		town = "";
		foundationType = "";
		structuralType = "";
		wallsType = "";
		earthQuakeRisk = false;
		collapseRisk = false;
		fleedRisk = false;
		hurricaneRisk = false;
		landSlideRisk = false;
		accessWays = false;
		innerWays = false;
		perimeterFence = false;
		securityRoom = false;
		bascule = false;
		waterElectricityDrain = false;
		sanitaryServices = false;
		cushioningStrip = false;
		offices = false;
		medicalServices = false;
		extension = 0;
		startLatitude = 0;
		startLongitude = 0;
		startAltitude = 0;
		endLatitude = 0;
		endLongitude = 0;
		endAltitude = 0;
		ZIP = "";
		address = "";
		colony = "";
		state = "";
		stateName = "";
		city = "";
		town = "";
		postalCodeID = "";
		postalCode = "";
		renewalValue = "0.0";
		civilWorksValue = "0.0";
		machineryEquipmentValue = "0.0";
		annualMaintenanceAmount = "0.0";
		siniestralityValues = new String[10];
		siniestralityDescription =  new String[10];

		for (int i=0;i<10;i++){
			siniestralityValues[i] = "0.0";
			siniestralityDescription[i] = "";
		}
	}

	/**
	 * @return the wasteDisposalType
	 */
	public String getWasteDisposalType() {
		return wasteDisposalType;
	}

	/**
	 * @param wasteDisposalType the wasteDisposalType to set
	 */
	public void setWasteDisposalType(String wasteDisposalType) {
		this.wasteDisposalType = wasteDisposalType;
	}

	/**
	 * @return the estension
	 */
	public double getExtension() {
		return extension;
	}

	/**
	 * @param estension the estension to set
	 */
	public void setExtension(double extension) {
		this.extension = extension;
	}

	/**
	 * @return the earthQuakeRisk
	 */
	public boolean isEarthQuakeRisk() {
		return earthQuakeRisk;
	}

	/**
	 * @param earthQuakeRisk the earthQuakeRisk to set
	 */
	public void setEarthQuakeRisk(boolean earthQuakeRisk) {
		this.earthQuakeRisk = earthQuakeRisk;
	}

	/**
	 * @return the collapseRisk
	 */
	public boolean isCollapseRisk() {
		return collapseRisk;
	}

	/**
	 * @param collapseRisk the collapseRisk to set
	 */
	public void setCollapseRisk(boolean collapseRisk) {
		this.collapseRisk = collapseRisk;
	}

	/**
	 * @return the fleedRisk
	 */
	public boolean isFleedRisk() {
		return fleedRisk;
	}

	/**
	 * @param fleedRisk the fleedRisk to set
	 */
	public void setFleedRisk(boolean fleedRisk) {
		this.fleedRisk = fleedRisk;
	}

	/**
	 * @return the hurricaneRisk
	 */
	public boolean isHurricaneRisk() {
		return hurricaneRisk;
	}

	/**
	 * @param hurricaneRisk the hurricaneRisk to set
	 */
	public void setHurricaneRisk(boolean hurricaneRisk) {
		this.hurricaneRisk = hurricaneRisk;
	}

	/**
	 * @return the landSlideRisk
	 */
	public boolean isLandSlideRisk() {
		return landSlideRisk;
	}

	/**
	 * @param landSlideRisk the landSlideRisk to set
	 */
	public void setLandSlideRisk(boolean landSlideRisk) {
		this.landSlideRisk = landSlideRisk;
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
	 * @return the renewalValue
	 */
	public String getRenewalValue() {
		return renewalValue;
	}

	/**
	 * @param renewalValue the renewalValue to set
	 */
	public void setRenewalValue(String renewalValue) {
		this.renewalValue = renewalValue;
	}

	/**
	 * @return the civilWorksValue
	 */
	public String getCivilWorksValue() {
		return civilWorksValue;
	}

	/**
	 * @param civilWorksValue the civilWorksValue to set
	 */
	public void setCivilWorksValue(String civilWorksValue) {
		this.civilWorksValue = civilWorksValue;
	}

	/**
	 * @return the machineryEquipmentValue
	 */
	public String getMachineryEquipmentValue() {
		return machineryEquipmentValue;
	}

	/**
	 * @param machineryEquipmentValue the machineryEquipmentValue to set
	 */
	public void setMachineryEquipmentValue(String machineryEquipmentValue) {
		this.machineryEquipmentValue = machineryEquipmentValue;
	}

	/**
	 * @return the annualMaintenanceAmount
	 */
	public String getAnnualMaintenanceAmount() {
		return annualMaintenanceAmount;
	}

	/**
	 * @param annualMaintenanceAmount the annualMaintenanceAmount to set
	 */
	public void setAnnualMaintenanceAmount(String annualMaintenanceAmount) {
		this.annualMaintenanceAmount = annualMaintenanceAmount;
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
	 * @return the structuralType
	 */
	public String getStructuralType() {
		return structuralType;
	}

	/**
	 * @param structuralType the structuralType to set
	 */
	public void setStructuralType(String structuralType) {
		this.structuralType = structuralType;
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
	 * @return the siniestralityValues
	 */
	public String[] getSiniestralityValues() {
		return siniestralityValues;
	}

	/**
	 * @param siniestralityValues the siniestralityValues to set
	 */
	public void setSiniestralityValues(String[] siniestralityValues) {
		this.siniestralityValues = siniestralityValues;
	}

	/**
	 * @return the siniestralityDescription
	 */
	public String[] getSiniestralityDescription() {
		return siniestralityDescription;
	}

	/**
	 * @param siniestralityDescription the siniestralityDescription to set
	 */
	public void setSiniestralityDescription(String[] siniestralityDescription) {
		this.siniestralityDescription = siniestralityDescription;
	}

	/**
	 * @return the startLatitude
	 */
	public double getStartLatitude() {
		return startLatitude;
	}

	/**
	 * @param startLatitude the startLatitude to set
	 */
	public void setStartLatitude(double startLatitude) {
		this.startLatitude = startLatitude;
	}

	/**
	 * @return the startLongitude
	 */
	public double getStartLongitude() {
		return startLongitude;
	}

	/**
	 * @param startLongitude the startLongitude to set
	 */
	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}

	/**
	 * @return the startAltitude
	 */
	public double getStartAltitude() {
		return startAltitude;
	}

	/**
	 * @param startAltitude the startAltitude to set
	 */
	public void setStartAltitude(double startAltitude) {
		this.startAltitude = startAltitude;
	}

	/**
	 * @return the endLatitude
	 */
	public double getEndLatitude() {
		return endLatitude;
	}

	/**
	 * @param endLatitude the endLatitude to set
	 */
	public void setEndLatitude(double endLatitude) {
		this.endLatitude = endLatitude;
	}

	/**
	 * @return the endLongitude
	 */
	public double getEndLongitude() {
		return endLongitude;
	}

	/**
	 * @param endLongitude the endLongitude to set
	 */
	public void setEndLongitude(double endLongitude) {
		this.endLongitude = endLongitude;
	}

	/**
	 * @return the endAltitude
	 */
	public double getEndAltitude() {
		return endAltitude;
	}

	/**
	 * @param endAltitude the endAltitude to set
	 */
	public void setEndAltitude(double endAltitude) {
		this.endAltitude = endAltitude;
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
	 * @return the accessWays
	 */
	public boolean isAccessWays() {
		return accessWays;
	}

	/**
	 * @param accessWays the accessWays to set
	 */
	public void setAccessWays(boolean accessWays) {
		this.accessWays = accessWays;
	}

	/**
	 * @return the innerWays
	 */
	public boolean isInnerWays() {
		return innerWays;
	}

	/**
	 * @param innerWays the innerWays to set
	 */
	public void setInnerWays(boolean innerWays) {
		this.innerWays = innerWays;
	}

	/**
	 * @return the perimeterFence
	 */
	public boolean isPerimeterFence() {
		return perimeterFence;
	}

	/**
	 * @param perimeterFence the perimeterFence to set
	 */
	public void setPerimeterFence(boolean perimeterFence) {
		this.perimeterFence = perimeterFence;
	}

	/**
	 * @return the securityRoom
	 */
	public boolean isSecurityRoom() {
		return securityRoom;
	}

	/**
	 * @param securityRoom the securityRoom to set
	 */
	public void setSecurityRoom(boolean securityRoom) {
		this.securityRoom = securityRoom;
	}

	/**
	 * @return the bascule
	 */
	public boolean isBascule() {
		return bascule;
	}

	/**
	 * @param bascule the bascule to set
	 */
	public void setBascule(boolean bascule) {
		this.bascule = bascule;
	}

	/**
	 * @return the waterElectricityDrain
	 */
	public boolean isWaterElectricityDrain() {
		return waterElectricityDrain;
	}

	/**
	 * @param waterElectricityDrain the waterElectricityDrain to set
	 */
	public void setWaterElectricityDrain(boolean waterElectricityDrain) {
		this.waterElectricityDrain = waterElectricityDrain;
	}

	/**
	 * @return the sanitaryServices
	 */
	public boolean isSanitaryServices() {
		return sanitaryServices;
	}

	/**
	 * @param sanitaryServices the sanitaryServices to set
	 */
	public void setSanitaryServices(boolean sanitaryServices) {
		this.sanitaryServices = sanitaryServices;
	}

	/**
	 * @return the cushioningStrip
	 */
	public boolean isCushioningStrip() {
		return cushioningStrip;
	}

	/**
	 * @param cushioningStrip the cushioningStrip to set
	 */
	public void setCushioningStrip(boolean cushioningStrip) {
		this.cushioningStrip = cushioningStrip;
	}

	/**
	 * @return the offices
	 */
	public boolean isOffices() {
		return offices;
	}

	/**
	 * @param offices the offices to set
	 */
	public void setOffices(boolean offices) {
		this.offices = offices;
	}

	/**
	 * @return the medicalServices
	 */
	public boolean isMedicalServices() {
		return medicalServices;
	}

	/**
	 * @param medicalServices the medicalServices to set
	 */
	public void setMedicalServices(boolean medicalServices) {
		this.medicalServices = medicalServices;
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
