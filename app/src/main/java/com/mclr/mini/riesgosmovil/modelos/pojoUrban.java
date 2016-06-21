package com.mclr.mini.riesgosmovil.modelos;

import java.math.BigDecimal;

public class pojoUrban {
	public String propertyID;
	private String ZIP;
	private String address;
	private String colony;
	private String state;
	private String stateName;
	private String city;
	private String postalCode;
	private String postalCodeID;
	public boolean fleedRisk;
	public boolean hurricaneRisk;
	public boolean rockFallingRisk;
	public boolean earthQuakeRisk;
	public boolean landSlidingRisk;
	public String propertyName;
	public String infrastructureType;
	public String stateID;
	public String town;
	public String foundationType;
	public String structuralType;
	public String wallsType;
	public BigDecimal[] siniestralityAmount;
	public String[] siniestralityDescription;
	public int lanesNumber;
	public double startLatitude;
	public double startLongitude;
	public double startAltitude;
	public double endLatitude;
	public double endLongitude;
	public double endAltitude;
	public BigDecimal avgKmReconstructionValue;
	public BigDecimal stmtReposicionValue;
	public BigDecimal minorBridgesAvgRepositionValue;
	public BigDecimal preventionAvgAmount;
	public BigDecimal correctiveAvgAmount;

	private int status;
	private int finding;

	public pojoUrban(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		postalCodeID = "";
		ZIP = "";
		address = "";
		colony = "";
		state = "";
		stateName = "";
		city = "";
		postalCode = "";
		fleedRisk = false;
		hurricaneRisk = false;
		rockFallingRisk = false;
		earthQuakeRisk = false;
		landSlidingRisk = false;
		
		propertyName = "";
		infrastructureType = "";
		stateID = "";
		town = "";
		foundationType = "";
		structuralType = "";
		wallsType = "";
		
		siniestralityAmount =  new BigDecimal[10];
		siniestralityDescription = new String[10];
		
		for (int i=0;i<10;i++){
			siniestralityAmount[i]= new BigDecimal("0.0");
			siniestralityDescription[i] = "";
		}
		
		lanesNumber = 0;
		
		startLatitude = 0;
		startLongitude = 0;
		startAltitude = 0;
		endLatitude = 0;
		endLongitude = 0;
		endAltitude = 0;
		avgKmReconstructionValue = new BigDecimal("0");
		stmtReposicionValue = new BigDecimal("0");
		minorBridgesAvgRepositionValue = new BigDecimal("0");
		preventionAvgAmount = new BigDecimal("0");
		correctiveAvgAmount = new BigDecimal("0");
	}



	/**
	 * @return the fleed
	 */
	public boolean hasFleedRisk() {
		return fleedRisk;
	}



	/**
	 * @param fleed the fleed to set
	 */
	public void setFleedRisk(boolean fleedRisk) {
		this.fleedRisk = fleedRisk;
	}



	/**
	 * @return the hurricane
	 */
	public boolean hasHurricaneRisk() {
		return hurricaneRisk;
	}



	/**
	 * @param hurricane the hurricane to set
	 */
	public void setHurricaneRisk(boolean hurricaneRisk) {
		this.hurricaneRisk = hurricaneRisk;
	}



	/**
	 * @return the rockFalling
	 */
	public boolean hasRockFallingRisk() {
		return rockFallingRisk;
	}



	/**
	 * @param rockFalling the rockFalling to set
	 */
	public void setRockFallingRisk(boolean rockFallingRisk) {
		this.rockFallingRisk = rockFallingRisk;
	}



	/**
	 * @return the earthQuake
	 */
	public boolean hasEarthQuakeRisk() {
		return earthQuakeRisk;
	}



	/**
	 * @param earthQuake the earthQuake to set
	 */
	public void setEarthQuakeRisk(boolean earthQuakeRisk) {
		this.earthQuakeRisk = earthQuakeRisk;
	}



	/**
	 * @return the landSliding
	 */
	public boolean hasLandSlidingRisk() {
		return landSlidingRisk;
	}



	/**
	 * @param landSliding the landSliding to set
	 */
	public void setLandSlidingRisk(boolean landSlidingRisk) {
		this.landSlidingRisk = landSlidingRisk;
	}



	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}



	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}



	/**
	 * @return the infrastructureType
	 */
	public String getInfrastructureType() {
		return infrastructureType;
	}



	/**
	 * @param infrastructureType the infrastructureType to set
	 */
	public void setInfrastructureType(String infrastructureType) {
		this.infrastructureType = infrastructureType;
	}



	/**
	 * @return the lanesNumber
	 */
	public int getLanesNumber() {
		return lanesNumber;
	}



	/**
	 * @param lanesNumber the lanesNumber to set
	 */
	public void setLanesNumber(int lanesNumber) {
		this.lanesNumber = lanesNumber;
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
	 * @return the siniestralityAmount
	 */
	public BigDecimal[] getSiniestralityAmount() {
		return siniestralityAmount;
	}



	/**
	 * @param siniestralityAmount the siniestralityAmount to set
	 */
	public void setSiniestralityAmount(BigDecimal[] siniestralityAmount) {
		this.siniestralityAmount = siniestralityAmount;
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
	 * @return the avgKmReconstructionValue
	 */
	public BigDecimal getAvgKmReconstructionValue() {
		return avgKmReconstructionValue;
	}



	/**
	 * @param avgKmReconstructionValue the avgKmReconstructionValue to set
	 */
	public void setAvgKmReconstructionValue(BigDecimal avgKmReconstructionValue) {
		this.avgKmReconstructionValue = avgKmReconstructionValue;
	}



	/**
	 * @return the stmtReposicionValue
	 */
	public BigDecimal getStmtReposicionValue() {
		return stmtReposicionValue;
	}



	/**
	 * @param stmtReposicionValue the stmtReposicionValue to set
	 */
	public void setStmtReposicionValue(BigDecimal stmtReposicionValue) {
		this.stmtReposicionValue = stmtReposicionValue;
	}



	/**
	 * @return the minorBridgesAvgRepositionValue
	 */
	public BigDecimal getMinorBridgesAvgRepositionValue() {
		return minorBridgesAvgRepositionValue;
	}



	/**
	 * @param minorBridgesAvgRepositionValue the minorBridgesAvgRepositionValue to set
	 */
	public void setMinorBridgesAvgRepositionValue(
			BigDecimal minorBridgesAvgRepositionValue) {
		this.minorBridgesAvgRepositionValue = minorBridgesAvgRepositionValue;
	}



	/**
	 * @return the preventionAvgAmount
	 */
	public BigDecimal getPreventionAvgAmount() {
		return preventionAvgAmount;
	}



	/**
	 * @param preventionAvgAmount the preventionAvgAmount to set
	 */
	public void setPreventionAvgAmount(BigDecimal preventionAvgAmount) {
		this.preventionAvgAmount = preventionAvgAmount;
	}



	/**
	 * @return the correctiveAvgAmount
	 */
	public BigDecimal getCorrectiveAvgAmount() {
		return correctiveAvgAmount;
	}



	/**
	 * @param correctiveAvgAmount the correctiveAvgAmount to set
	 */
	public void setCorrectiveAvgAmount(BigDecimal correctiveAvgAmount) {
		this.correctiveAvgAmount = correctiveAvgAmount;
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
