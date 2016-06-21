package com.mclr.mini.riesgosmovil.modelos;

import java.util.Vector;

public class pojoWays {
	private String name;
	private String propertyID;
	private String constructionDate;
	private String wayType;
	private String colony;
	private String state;
	private String city;
	private String stateName;
	private String town;
	private String postalCodeID;
	private String postalCode;
	private String condition;
	private double startLatitude;
	private double startLongitude;
	private double startAltitude;
	private double endLatitude;
	private double endLongitude;
	private double endAltitude;
	private boolean floodRisk;
	private boolean hurricanRisk;
	private boolean rockFallRisk;
	private boolean earthQuakeRisk;
	private boolean slidingRisk;
	private boolean floodFormer;
	private boolean hurricanFormer;
	private boolean rockFallFormer;
	private boolean earthQuakeFormer;
	private boolean slidingFormer;
	private boolean Sinkings;
	private int sinkingSeverity;
	private Vector<pojoSinking> sinkings;
	private String closestWaterBodyType;
	private double closestWaterBodyStartLatitude;
	private double closestWaterBodyStartLongitude;
	private double closestWaterBodyStartAltitude;
	private double closestWaterBodyEndLatitude;
	private double closestWaterBodyEndLongitude;
	private double closestWaterBodyEndAltitude;
	private boolean closestWaterBodyCross;
	private double closestWaterBodyDistanceCross;
	private String coverType;
	private double underWaysNumber;
	private double overWaysNumber;
	private int linesNumber;
	private double totalKms;
	private double widthLine;
	private int sewers;
	private String renewalWayValue;
	private String renewalBridgesValue;
	private String renewalTunnelsValue;
	private String[] siniestralityValues;
	private String[] siniestralityDescription;
	private Vector<pojoBridges> attachedWays;
	private Vector<pojoTunnel> attachedTunnels;

	private int status;
	private int finding;

	public pojoWays(){
		coverType = "";
		name = "";
		constructionDate = "";
		wayType = "";
		state = "";
		stateName = "";
		town = "";
		postalCodeID = "";
		closestWaterBodyType = "";
		condition = "";
		propertyID = "00000000-0000-0000-0000-000000000000";
		startLatitude = 0;
		startLongitude = 0;
		startAltitude = 0;
		endLatitude = 0;
		endLongitude = 0;
		endAltitude = 0;
		closestWaterBodyStartLatitude = 0;
		closestWaterBodyStartLongitude = 0;
		closestWaterBodyStartAltitude = 0;
		closestWaterBodyEndLatitude = 0;
		closestWaterBodyEndLongitude = 0;
		closestWaterBodyEndAltitude = 0;
		closestWaterBodyDistanceCross = 0;
		underWaysNumber = 0;
		overWaysNumber = 0;
		totalKms = 0;
		widthLine = 0;
		renewalWayValue = "0.0";
		renewalBridgesValue = "0.0";
		renewalTunnelsValue = "0.0";
		closestWaterBodyCross = false;
		floodRisk = false;
		hurricanRisk = false;
		rockFallRisk = false;
		earthQuakeRisk = false;
		slidingRisk = false;
		floodFormer = false;
		hurricanFormer = false;
		rockFallFormer = false;
		earthQuakeFormer = false;
		slidingFormer = false;
		Sinkings = false;
		sinkingSeverity = 0;
		linesNumber = 0;
		sewers = 0;
		siniestralityValues = new String[10];
		siniestralityDescription = new String[10];
		for (int i=0;i<10;i++){
			siniestralityValues[i] = "0.0";
			siniestralityDescription[i] = "";
		}
		attachedWays = new Vector<pojoBridges>();
		attachedTunnels= new Vector<pojoTunnel>();
		sinkings= new Vector<pojoSinking>();
		
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
	 * @return the constructionDate
	 */
	public String getConstructionDate() {
		return constructionDate;
	}
	/**
	 * @param constructionDate the constructionDate to set
	 */
	public void setConstructionDate(String constructionDate) {
		this.constructionDate = constructionDate;
	}
	/**
	 * @return the wayType
	 */
	public String getWayType() {
		return wayType;
	}
	/**
	 * @param wayType the wayType to set
	 */
	public void setWayType(String wayType) {
		this.wayType = wayType;
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
	 * @return the stateID
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateID the stateID to set
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
	 * @return the closestWaterBodyType
	 */
	public String getClosestWaterBodyType() {
		return closestWaterBodyType;
	}
	/**
	 * @param closestWaterBodyType the closestWaterBodyType to set
	 */
	public void setClosestWaterBodyType(String closestWaterBodyType) {
		this.closestWaterBodyType = closestWaterBodyType;
	}
	/**
	 * @return the closestWaterBodyStartLatitude
	 */
	public double getClosestWaterBodyStartLatitude() {
		return closestWaterBodyStartLatitude;
	}
	/**
	 * @param closestWaterBodyStartLatitude the closestWaterBodyStartLatitude to set
	 */
	public void setClosestWaterBodyStartLatitude(
			double closestWaterBodyStartLatitude) {
		this.closestWaterBodyStartLatitude = closestWaterBodyStartLatitude;
	}
	/**
	 * @return the closestWaterBodyStartLongitude
	 */
	public double getClosestWaterBodyStartLongitude() {
		return closestWaterBodyStartLongitude;
	}
	/**
	 * @param closestWaterBodyStartLongitude the closestWaterBodyStartLongitude to set
	 */
	public void setClosestWaterBodyStartLongitude(
			double closestWaterBodyStartLongitude) {
		this.closestWaterBodyStartLongitude = closestWaterBodyStartLongitude;
	}
	/**
	 * @return the closestWaterBodyStartLongitude
	 */
	public double getClosestWaterBodyStartAltitude() {
		return closestWaterBodyStartAltitude;
	}
	/**
	 * @param closestWaterBodyStartLongitude the closestWaterBodyStartLongitude to set
	 */
	public void setClosestWaterBodyStartAltitude(
			double closestWaterBodyStartAltitude) {
		this.closestWaterBodyStartAltitude = closestWaterBodyStartAltitude;
	}
	/**
	 * @return the closestWaterBodyEndLatitude
	 */
	public double getClosestWaterBodyEndLatitude() {
		return closestWaterBodyEndLatitude;
	}
	/**
	 * @param closestWaterBodyEndLatitude the closestWaterBodyEndLatitude to set
	 */
	public void setClosestWaterBodyEndLatitude(double closestWaterBodyEndLatitude) {
		this.closestWaterBodyEndLatitude = closestWaterBodyEndLatitude;
	}
	/**
	 * @return the closestWaterBodyEndAltitude
	 */
	public double getClosestWaterBodyEndAltitude() {
		return closestWaterBodyEndAltitude;
	}
	/**
	 * @param closestWaterBodyEndLongitude the closestWaterBodyEndLongitude to set
	 */
	public void setClosestWaterBodyEndAltitude(double closestWaterBodyEndAltitude) {
		this.closestWaterBodyEndAltitude = closestWaterBodyEndAltitude;
	}

	/**
	 * @return the closestWaterBodyEndLongitude
	 */
	public double getClosestWaterBodyEndLongitude() {
		return closestWaterBodyEndLongitude;
	}
	/**
	 * @param closestWaterBodyEndLongitude the closestWaterBodyEndLongitude to set
	 */
	public void setClosestWaterBodyEndLongitude(double closestWaterBodyEndLongitude) {
		this.closestWaterBodyEndLongitude = closestWaterBodyEndLongitude;
	}


	/**
	 * @return the closestWaterBodyCross
	 */
	public boolean isClosestWaterBodyCross() {
		return closestWaterBodyCross;
	}
	/**
	 * @param closestWaterBodyCross the closestWaterBodyCross to set
	 */
	public void setClosestWaterBodyCross(boolean closestWaterBodyCross) {
		this.closestWaterBodyCross = closestWaterBodyCross;
	}
	/**
	 * @return the closestWaterBodyDistanceCross
	 */
	public double getClosestWaterBodyDistanceCross() {
		return closestWaterBodyDistanceCross;
	}
	/**
	 * @param closestWaterBodyDistanceCross the closestWaterBodyDistanceCross to set
	 */
	public void setClosestWaterBodyDistanceCross(
			double closestWaterBodyDistanceCross) {
		this.closestWaterBodyDistanceCross = closestWaterBodyDistanceCross;
	}
	/**
	 * @return the coverType
	 */
	public String getCoverType() {
		return coverType;
	}
	/**
	 * @param coverType the coverType to set
	 */
	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	/**
	 * @return the underWaysNumber
	 */
	public double getUnderWaysNumber() {
		return underWaysNumber;
	}
	/**
	 * @param underWaysNumber the underWaysNumber to set
	 */
	public void setUnderWaysNumber(double underWaysNumber) {
		this.underWaysNumber = underWaysNumber;
	}
	/**
	 * @return the overWaysNumber
	 */
	public double getOverWaysNumber() {
		return overWaysNumber;
	}
	/**
	 * @param overWaysNumber the overWaysNumber to set
	 */
	public void setOverWaysNumber(double overWaysNumber) {
		this.overWaysNumber = overWaysNumber;
	}
	/**
	 * @return the linesNumber
	 */
	public int getLinesNumber() {
		return linesNumber;
	}
	/**
	 * @param linesNumber the linesNumber to set
	 */
	public void setLinesNumber(int linesNumber) {
		this.linesNumber = linesNumber;
	}
	/**
	 * @return the totalKms
	 */
	public double getTotalKms() {
		return totalKms;
	}
	/**
	 * @param totalKms the totalKms to set
	 */
	public void setTotalKms(double totalKms) {
		this.totalKms = totalKms;
	}
	/**
	 * @return the widthLine
	 */
	public double getWidthLine() {
		return widthLine;
	}
	/**
	 * @param widthLine the widthLine to set
	 */
	public void setWidthLine(double widthLine) {
		this.widthLine = widthLine;
	}
	/**
	 * @return the sewers
	 */
	public int getSewers() {
		return sewers;
	}
	/**
	 * @param sewers the sewers to set
	 */
	public void setSewers(int sewers) {
		this.sewers = sewers;
	}
	/**
	 * @return the renewalWayValue
	 */
	public String getRenewalWayValue() {
		return renewalWayValue;
	}
	/**
	 * @param renewalWayValue the renewalWayValue to set
	 */
	public void setRenewalWayValue(String renewalWayValue) {
		this.renewalWayValue = renewalWayValue;
	}
	/**
	 * @return the renewalBridgesValue
	 */
	public String getRenewalBridgesValue() {
		return renewalBridgesValue;
	}
	/**
	 * @param renewalBridgesValue the renewalBridgesValue to set
	 */
	public void setRenewalBridgesValue(String renewalBridgesValue) {
		this.renewalBridgesValue = renewalBridgesValue;
	}
	/**
	 * @return the renewalTunnelsValue
	 */
	public String getRenewalTunnelsValue() {
		return renewalTunnelsValue;
	}
	/**
	 * @param renewalTunnelsValue the renewalTunnelsValue to set
	 */
	public void setRenewalTunnelsValue(String renewalTunnelsValue) {
		this.renewalTunnelsValue = renewalTunnelsValue;
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
	 * @return the propertyTypeID
	 */
	public String getPropertyID() {
		return propertyID;
	}
	/**
	 * @param propertyTypeID the propertyTypeID to set
	 */
	public void setPropertyTypeID(String propertyID) {
		this.propertyID = propertyID;
	}
	/**
	 * @return the attachedWays
	 */
	public Vector<pojoBridges> getAttachedWays() {
		return attachedWays;
	}
	/**
	 * @param attachedWays the attachedWays to set
	 */
	public void setAttachedWays(Vector<pojoBridges> attachedWays) {
		this.attachedWays = attachedWays;
	}
	/**
	 * @param propertyID the propertyID to set
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	/**
	 * @return the attachedTunnels
	 */
	public Vector<pojoTunnel> getAttachedTunnels() {
		return attachedTunnels;
	}
	/**
	 * @param attachedTunnels the attachedTunnels to set
	 */
	public void setAttachedTunnels(Vector<pojoTunnel> attachedTunnels) {
		this.attachedTunnels = attachedTunnels;
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
	 * @return the hurricanFormer
	 */
	public boolean isHurricanFormer() {
		return hurricanFormer;
	}
	/**
	 * @param hurricanFormer the hurricanFormer to set
	 */
	public void setHurricanFormer(boolean hurricanFormer) {
		this.hurricanFormer = hurricanFormer;
	}
	/**
	 * @return the rockFallFormer
	 */
	public boolean isRockFallFormer() {
		return rockFallFormer;
	}
	/**
	 * @param rockFallFormer the rockFallFormer to set
	 */
	public void setRockFallFormer(boolean rockFallFormer) {
		this.rockFallFormer = rockFallFormer;
	}
	/**
	 * @return the earthQuakeFormer
	 */
	public boolean isEarthQuakeFormer() {
		return earthQuakeFormer;
	}
	/**
	 * @param earthQuakeFormer the earthQuakeFormer to set
	 */
	public void setEarthQuakeFormer(boolean earthQuakeFormer) {
		this.earthQuakeFormer = earthQuakeFormer;
	}
	/**
	 * @return the slidingFormer
	 */
	public boolean isSlidingFormer() {
		return slidingFormer;
	}
	/**
	 * @param slidingFormer the slidingFormer to set
	 */
	public void setSlidingFormer(boolean slidingFormer) {
		this.slidingFormer = slidingFormer;
	}
	/**
	 * @return the floodRisk
	 */
	public boolean isFloodRisk() {
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
	public boolean isHurricanRisk() {
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
	public boolean isRockFallRisk() {
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
	 * @return the slidingRisk
	 */
	public boolean isSlidingRisk() {
		return slidingRisk;
	}
	/**
	 * @param slidingRisk the slidingRisk to set
	 */
	public void setSlidingRisk(boolean slidingRisk) {
		this.slidingRisk = slidingRisk;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the sinkings
	 */
	public boolean isSinkings() {
		return Sinkings;
	}
	/**
	 * @param sinkings the sinkings to set
	 */
	public void setSinkings(boolean sinkings) {
		Sinkings = sinkings;
	}
	/**
	 * @return the sinkingSeverity
	 */
	public int getSinkingSeverity() {
		return sinkingSeverity;
	}
	/**
	 * @param sinkingSeverity the sinkingSeverity to set
	 */
	public void setSinkingSeverity(int sinkingSeverity) {
		this.sinkingSeverity = sinkingSeverity;
	}
	/**
	 * @return the sinkings
	 */
	public Vector<pojoSinking> getSinkings() {
		return sinkings;
	}
	/**
	 * @param sinkings the sinkings to set
	 */
	public void setSinkings(Vector<pojoSinking> sinkings) {
		this.sinkings = sinkings;
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