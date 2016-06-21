package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamHydraulicFacility {
	
	private String facilityID;
	private String facilityName;
	private double latitude;
	private double longitude;
	private double altitude;
	private String catchment;
	private String catchmentDescription;
	private String closestWeatherSt;
	private String closestWeatherStDescription;
	private String damType;
	private String damTypeDescription;
	private String damUse;
	private String damUseDescription;
	private double closestCity;
	
	public pojoDamHydraulicFacility(){
		facilityID = "";
		facilityName = "";
		latitude = 0.0;
		longitude = 0.0;
		altitude = 0.0;
		catchment = "";
		catchmentDescription = "";
		closestWeatherSt = "";
		closestWeatherStDescription = "";
		damType = "";
		damTypeDescription = "";
		damUse = "";
		damUseDescription = "";
		closestCity = 0.0;
	}

	/**
	 * @return the facilityID
	 */
	public String getFacilityID() {
		return facilityID;
	}

	/**
	 * @param facilityID the facilityID to set
	 */
	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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
	 * @return the longitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
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
	 * @return the catchment
	 */
	public String getCatchment() {
		return catchment;
	}

	/**
	 * @param catchment the catchment to set
	 */
	public void setCatchment(String catchment) {
		this.catchment = catchment;
	}

	/**
	 * @return the catchment
	 */
	public String getCatchmentDescription() {
		return catchmentDescription;
	}

	/**
	 * @param catchment the catchment to set
	 */
	public void setCatchmentDescription(String catchmentDescription) {
		this.catchmentDescription = catchmentDescription;
	}

	/**
	 * @return the closestWeatherSt
	 */
	public String getClosestWeatherSt() {
		return closestWeatherSt;
	}

	/**
	 * @param closestWeatherSt the closestWeatherSt to set
	 */
	public void setClosestWeatherSt(String closestWeatherSt) {
		this.closestWeatherSt = closestWeatherSt;
	}

	/**
	 * @return the closestWeatherSt
	 */
	public String getClosestWeatherStDescription() {
		return closestWeatherStDescription;
	}

	/**
	 * @param closestWeatherSt the closestWeatherSt to set
	 */
	public void setClosestWeatherStDescription(String closestWeatherStDescription) {
		this.closestWeatherStDescription = closestWeatherStDescription;
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
	 * @return the damType
	 */
	public String getDamTypeDescription() {
		return damTypeDescription;
	}

	/**
	 * @param damType the damType to set
	 */
	public void setDamTypeDescription(String damTypeDescription) {
		this.damTypeDescription = damTypeDescription;
	}

	/**
	 * @return the damUse
	 */
	public String getDamUse() {
		return damUse;
	}

	/**
	 * @param damUse the damUse to set
	 */
	public void setDamUse(String damUse) {
		this.damUse = damUse;
	}

	/**
	 * @return the damUse
	 */
	public String getDamUseDescription() {
		return damUseDescription;
	}

	/**
	 * @param damUse the damUse to set
	 */
	public void setDamUseDescription(String damUseDescription) {
		this.damUseDescription = damUseDescription;
	}

	/**
	 * @return the closestCity
	 */
	public double getClosestCity() {
		return closestCity;
	}

	/**
	 * @param closestCity the closestCity to set
	 */
	public void setClosestCity(double closestCity) {
		this.closestCity = closestCity;
	}
}
