package com.mclr.mini.riesgosmovil.modelos;

import java.math.BigDecimal;

public class pojoHealt {
	private String propertyID;
	private String name;
	private String constructionDate;
	private String levels;
	private String closestWaterBody;
	private double latitude;
	private double longitude;
	private double altitude;
	private String address;
	private String stateName;
	private String town;
	private String city;
	private String colony;
	private String postalCodeID;
	private String postalCode;
	private String shortestWterbody;
	private BigDecimal builningRenewalValue;
	private BigDecimal medicalRenewalValue;
	private BigDecimal labMaterialRenewalValue;
	private BigDecimal electronicRenewalValue;
	private BigDecimal itemRenewalValue;
	private String foundingType;
	private String structuraltype;
	private String wallsType;
	private String soilType;
	private String coverType;
	private String coverShape;
	private String elevation;
	private boolean geometryRegular;
	private boolean geometryIrregular;
	private String windowProtection;
	private String domeProtection;
	private String fachadeGlasserySize;
	private String fachadeGlasseryType;
	private String windowsProtectionType;
	private String domeProtectionType;
	private String fachadeType;
	private String fachadeMaterial;
	private String conservationState;
	private boolean objectsInRoof;
	private boolean dome;
	private boolean shortWalls;
	private boolean hitting;
	private boolean sinkingPresence;
	private boolean closeToPoles;
	private boolean closeToAds;
	private boolean closeToTrees;
	private int sinkingSeverity;
	private boolean reinforcement;
	private String reinformcementDate;
	private String[] closeTo;
	private String[] siniestralityValues;
	private String[] siniestralityDescription;
	private boolean earthQuakeRisk;
	private boolean floodRisk;
	private boolean collapseRisk;
	private boolean landSlideRisk;
	private boolean hurricaneRisk;
	private boolean earthQuakeFormer;
	private boolean floodFormer;
	private boolean collapseFormer;
	private boolean landSlideFormer;
	private boolean hurricaneFormer;

	private int status;
	private int finding;

	public pojoHealt(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		name = "";
		constructionDate = "";
		levels = "";
		closestWaterBody = "";
		address = "";
		stateName = "";
		town = "";
		city = "";
		colony = "";
		postalCodeID = "";
		postalCode = "";
		shortestWterbody = "";
		foundingType = "";
		structuraltype = "";
		wallsType = "";
		soilType = "";
		coverType = "";
		coverShape = "";
		elevation = "0";
		geometryRegular = true;
		geometryIrregular = false;
		windowProtection = "";
		domeProtection = "";
		fachadeGlasserySize = "";
		fachadeGlasseryType = "";
		windowsProtectionType = "";
		domeProtectionType = "";
		fachadeType = "";
		fachadeMaterial = "";
		conservationState = "";
		sinkingSeverity = 0;
		reinformcementDate = "";
		latitude = 0;
		longitude = 0;
		altitude = 0;
		builningRenewalValue = new BigDecimal("0");
		medicalRenewalValue = new BigDecimal("0");
		labMaterialRenewalValue = new BigDecimal("0");
		electronicRenewalValue = new BigDecimal("0");
		itemRenewalValue = new BigDecimal("0");
		objectsInRoof = false;
		dome = false;
		shortWalls = false;
		hitting = false;
		sinkingPresence = false;
		reinforcement = false;
		earthQuakeRisk = false;
		floodRisk = false;
		collapseRisk = false;
		landSlideRisk = false;
		hurricaneRisk = false;
		earthQuakeFormer = false;
		floodFormer = false;
		collapseFormer = false;
		landSlideFormer = false;
		hurricaneFormer = false;
		closeToPoles = false;
		closeToAds = false;
		closeToTrees = false;
		closeTo = new String[10];
		siniestralityValues = new String[10];
		siniestralityDescription = new String[10];

		for (int i=0;i<10;i++){
			closeTo[i] = "";
			siniestralityValues[i] = "0.0";
			siniestralityDescription[i] = "";
		}
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
	 * @return the contructionDate
	 */
	public String getContructionDate() {
		return constructionDate;
	}

	/**
	 * @param contructionDate the contructionDate to set
	 */
	public void setConstructionDate(String contructionDate) {
		this.constructionDate = contructionDate;
	}

	/**
	 * @return the levels
	 */
	public String getLevels() {
		return levels;
	}

	/**
	 * @param levels the levels to set
	 */
	public void setLevels(String levels) {
		this.levels = levels;
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
	 * @return the shortestWterbody
	 */
	public String getShortestWterbody() {
		return shortestWterbody;
	}

	/**
	 * @param shortestWterbody the shortestWterbody to set
	 */
	public void setShortestWterbody(String shortestWterbody) {
		this.shortestWterbody = shortestWterbody;
	}

	/**
	 * @return the builningRenewalValue
	 */
	public BigDecimal getBuilningRenewalValue() {
		return builningRenewalValue;
	}

	/**
	 * @param builningRenewalValue the builningRenewalValue to set
	 */
	public void setBuilningRenewalValue(BigDecimal builningRenewalValue) {
		this.builningRenewalValue = builningRenewalValue;
	}

	/**
	 * @return the medicalRenewalValue
	 */
	public BigDecimal getMedicalRenewalValue() {
		return medicalRenewalValue;
	}

	/**
	 * @param medicalRenewalValue the medicalRenewalValue to set
	 */
	public void setMedicalRenewalValue(BigDecimal medicalRenewalValue) {
		this.medicalRenewalValue = medicalRenewalValue;
	}

	/**
	 * @return the labMaterialRenewalValue
	 */
	public BigDecimal getLabMaterialRenewalValue() {
		return labMaterialRenewalValue;
	}

	/**
	 * @param labMaterialRenewalValue the labMaterialRenewalValue to set
	 */
	public void setLabMaterialRenewalValue(BigDecimal labMaterialRenewalValue) {
		this.labMaterialRenewalValue = labMaterialRenewalValue;
	}

	/**
	 * @return the electronicRenewalValue
	 */
	public BigDecimal getElectronicRenewalValue() {
		return electronicRenewalValue;
	}

	/**
	 * @param electronicRenewalValue the electronicRenewalValue to set
	 */
	public void setElectronicRenewalValue(BigDecimal electronicRenewalValue) {
		this.electronicRenewalValue = electronicRenewalValue;
	}

	/**
	 * @return the itemRenewalValue
	 */
	public BigDecimal getItemRenewalValue() {
		return itemRenewalValue;
	}

	/**
	 * @param itemRenewalValue the itemRenewalValue to set
	 */
	public void setItemRenewalValue(BigDecimal itemRenewalValue) {
		this.itemRenewalValue = itemRenewalValue;
	}

	/**
	 * @return the foundingType
	 */
	public String getFoundingType() {
		return foundingType;
	}

	/**
	 * @param foundingType the foundingType to set
	 */
	public void setFoundingType(String foundingType) {
		this.foundingType = foundingType;
	}

	/**
	 * @return the structuraltype
	 */
	public String getStructuraltype() {
		return structuraltype;
	}

	/**
	 * @param structuraltype the structuraltype to set
	 */
	public void setStructuraltype(String structuraltype) {
		this.structuraltype = structuraltype;
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
	 * @return the coverShape
	 */
	public String getCoverShape() {
		return coverShape;
	}

	/**
	 * @param coverShape the coverShape to set
	 */
	public void setCoverShape(String coverShape) {
		this.coverShape = coverShape;
	}

	/**
	 * @return the elevation
	 */
	public String getElevation() {
		return elevation;
	}

	/**
	 * @param elevation the elevation to set
	 */
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the fachadaGlasserySize
	 */
	public String getFachadaGlasserySize() {
		return fachadeGlasserySize;
	}

	/**
	 * @param fachadaGlasserySize the fachadaGlasserySize to set
	 */
	public void setFachadaGlasserySize(String fachadaGlasserySize) {
		this.fachadeGlasserySize = fachadaGlasserySize;
	}

	/**
	 * @return the windowsProtectionType
	 */
	public String getWindowsProtectionType() {
		return windowsProtectionType;
	}

	/**
	 * @param windowsProtectionType the windowsProtectionType to set
	 */
	public void setWindowsProtectionType(String windowsProtectionType) {
		this.windowsProtectionType = windowsProtectionType;
	}

	/**
	 * @return the domeProtectionType
	 */
	public String getDomeProtectionType() {
		return domeProtectionType;
	}

	/**
	 * @param domeProtectionType the domeProtectionType to set
	 */
	public void setDomeProtectionType(String domeProtectionType) {
		this.domeProtectionType = domeProtectionType;
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
	 * @return the conservationState
	 */
	public String getConservationState() {
		return conservationState;
	}

	/**
	 * @param conservationState the conservationState to set
	 */
	public void setConservationState(String conservationState) {
		this.conservationState = conservationState;
	}

	/**
	 * @return the objectsInRoof
	 */
	public boolean isObjectsInRoof() {
		return objectsInRoof;
	}

	/**
	 * @param objectsInRoof the objectsInRoof to set
	 */
	public void setObjectsInRoof(boolean objectsInRoof) {
		this.objectsInRoof = objectsInRoof;
	}

	/**
	 * @return the dome
	 */
	public boolean isDome() {
		return dome;
	}

	/**
	 * @param dome the dome to set
	 */
	public void setDome(boolean dome) {
		this.dome = dome;
	}

	/**
	 * @return the shortWalls
	 */
	public boolean isShortWalls() {
		return shortWalls;
	}

	/**
	 * @param shortWalls the shortWalls to set
	 */
	public void setShortWalls(boolean shortWalls) {
		this.shortWalls = shortWalls;
	}

	/**
	 * @return the hitting
	 */
	public boolean isHitting() {
		return hitting;
	}

	/**
	 * @param hitting the hitting to set
	 */
	public void setHitting(boolean hitting) {
		this.hitting = hitting;
	}

	/**
	 * @return the sinkingPresence
	 */
	public boolean isSinkingPresence() {
		return sinkingPresence;
	}

	/**
	 * @param sinkingPresence the sinkingPresence to set
	 */
	public void setSinkingPresence(boolean sinkingPresence) {
		this.sinkingPresence = sinkingPresence;
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
	 * @return the reinforcement
	 */
	public boolean isReinforcement() {
		return reinforcement;
	}

	/**
	 * @param reinforcement the reinforcement to set
	 */
	public void setReinforcement(boolean reinforcement) {
		this.reinforcement = reinforcement;
	}

	/**
	 * @return the reinformcementDate
	 */
	public String getReinformcementDate() {
		return reinformcementDate;
	}

	/**
	 * @param reinformcementDate the reinformcementDate to set
	 */
	public void setReinformcementDate(String reinformcementDate) {
		this.reinformcementDate = reinformcementDate;
	}

	/**
	 * @return the closeTo
	 */
	public String[] getCloseTo() {
		return closeTo;
	}

	/**
	 * @param closeTo the closeTo to set
	 */
	public void setCloseTo(String[] closeTo) {
		this.closeTo = closeTo;
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
	 * @return the closesWaterBody
	 */
	public String getClosestWaterBody() {
		return closestWaterBody;
	}

	/**
	 * @param closesWaterBody the closesWaterBody to set
	 */
	public void setClosestWaterBody(String closesWaterBody) {
		this.closestWaterBody = closesWaterBody;
	}

	/**
	 * @return the constructionDate
	 */
	public String getConstructionDate() {
		return constructionDate;
	}

	/**
	 * @return the geometryRegular
	 */
	public boolean isGeometryRegular() {
		return geometryRegular;
	}

	/**
	 * @param geometryRegular the geometryRegular to set
	 */
	public void setGeometryRegular(boolean geometryRegular) {
		this.geometryRegular = geometryRegular;
	}

	/**
	 * @return the geometryIrregular
	 */
	public boolean isGeometryIrregular() {
		return geometryIrregular;
	}

	/**
	 * @param geometryIrregular the geometryIrregular to set
	 */
	public void setGeometryIrregular(boolean geometryIrregular) {
		this.geometryIrregular = geometryIrregular;
	}

	/**
	 * @return the fachadeGlasserySize
	 */
	public String getFachadeGlasserySize() {
		return fachadeGlasserySize;
	}

	/**
	 * @param fachadeGlasserySize the fachadeGlasserySize to set
	 */
	public void setFachadeGlasserySize(String fachadeGlasserySize) {
		this.fachadeGlasserySize = fachadeGlasserySize;
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
	 * @return the fachadeMaterial
	 */
	public String getFachadeMaterial() {
		return fachadeMaterial;
	}

	/**
	 * @param fachadeMaterial the fachadeMaterial to set
	 */
	public void setFachadeMaterial(String fachadeMaterial) {
		this.fachadeMaterial = fachadeMaterial;
	}

	/**
	 * @return the windowProtection
	 */
	public String getWindowProtection() {
		return windowProtection;
	}

	/**
	 * @param windowProtection the windowProtection to set
	 */
	public void setWindowProtection(String windowProtection) {
		this.windowProtection = windowProtection;
	}

	/**
	 * @return the domeProtection
	 */
	public String getDomeProtection() {
		return domeProtection;
	}

	/**
	 * @param domeProtection the domeProtection to set
	 */
	public void setDomeProtection(String domeProtection) {
		this.domeProtection = domeProtection;
	}

	/**
	 * @return the closeToPoles
	 */
	public boolean isCloseToPoles() {
		return closeToPoles;
	}

	/**
	 * @param closeToPoles the closeToPoles to set
	 */
	public void setCloseToPoles(boolean closeToPoles) {
		this.closeToPoles = closeToPoles;
	}

	/**
	 * @return the closeToAdds
	 */
	public boolean isCloseToAds() {
		return closeToAds;
	}

	/**
	 * @param closeToAdds the closeToAdds to set
	 */
	public void setCloseToAds(boolean closeToAds) {
		this.closeToAds = closeToAds;
	}

	/**
	 * @return the closeToTrees
	 */
	public boolean isCloseToTrees() {
		return closeToTrees;
	}

	/**
	 * @param closeToTrees the closeToTrees to set
	 */
	public void setCloseToTrees(boolean closeToTrees) {
		this.closeToTrees = closeToTrees;
	}

	/**
	 * @return the fachadeGlasseryType
	 */
	public String getFachadeGlasseryType() {
		return fachadeGlasseryType;
	}

	/**
	 * @param fachadeGlasseryType the fachadeGlasseryType to set
	 */
	public void setFachadeGlasseryType(String fachadeGlasseryType) {
		this.fachadeGlasseryType = fachadeGlasseryType;
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