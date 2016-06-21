package com.mclr.mini.riesgosmovil.modelos;

import java.util.Vector;

public class pojoEducation {
	private String propertyID;
	private String educationType;
	private String buildingName;
	private String buildingNumber;
	private String propertyUse;
	private String specialFacilities;
	private double longitude;
	private double latitude;
	private double altitude;
	private String ZIP;
	private String address;
	private String colony;
	private String state;
	private String stateName;
	private String city;
	private String town;
	private String postalCodeID;
	private String postalCode;
	private String elevation;
	private String windowProtectionType;
	private String domeProtectionType;
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
	private boolean closeToPole;
	private boolean closeToAds;
	private boolean closeToTrees;
	private boolean externalStrPounding;
	private boolean shortWalls;

	private String structureType;
	private String foundationType;
	private String walls;
	private String storyStructure;
	private String storyMaterial;
	private String glassWareType;
	private String blacksmithType;
	private String coverType;
	private String coverShape;
	private String lifting;
	private boolean regularGeometr;
	private boolean irregularGeometry;
	private boolean roofItems;
	private boolean Domes;
	private boolean Sinkings;
	private int sinkingSeverity;
	private Vector<String> closeTo;
	private String fachadeGlassSize;
	private String fachadeType;
	private String fachadeMaterial;
	private boolean isReinforcement;
	private String reinformcementDate;
	private String condition;
	private String CnStExternal;
	private String CnStInternal;
	private String CnStCeiling;
	private String CnStRoofs;
	private String CnStTrimmings;
	private String CnStFloors;
	private String CnStBaseboards;
	private String CnStCarpintery;
	private String CnStCancel;
	private String CnStPainting;
	private String CnStSanitaryFurn;
	private String CnStSanitaryInst;
	private String CnStElectricInst;
	private String CnStBlackSmith;
	private String CnStGlassery;
	private String CnStFachade;
	private String CnStPlumbing;
	private String[] siniestralityDescription;
	private String[] siniestralityValue;
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

	private int status;
	private int finding;

	public pojoEducation(){
		propertyID = "00000000-0000-0000-0000-000000000000";
		educationType="";
		specialFacilities="";
		elevation="0";
		windowProtectionType = "";
		domeProtectionType = "";
		CnStPainting = "";
		CnStSanitaryFurn = "";
		CnStSanitaryInst = "";
		CnStElectricInst = "";
		CnStBlackSmith = "";
		CnStGlassery = "";
		CnStFachade = "";
		CnStPlumbing = "";
		buildingName = "";
		buildingNumber = "";
		propertyUse = "";
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
		walls = "";
		storyStructure = "";
		storyMaterial = "";
		glassWareType = "";
		blacksmithType = "";
		coverType = "";
		coverShape = "";
		lifting = "";
		regularGeometr = false;
		irregularGeometry = false;
		roofItems = false;
		Domes = false;
		Sinkings = false;
		closeToPole = false;
		closeToAds = false;
		closeToTrees = false;
		externalStrPounding = false;
		shortWalls = false;
		fachadeGlassSize = "";
		fachadeType = "";
		fachadeMaterial = "";
		reinformcementDate = "";
		CnStExternal = "";
		CnStInternal = "";
		CnStCeiling = "";
		CnStRoofs = "";
		CnStTrimmings = "";
		CnStFloors = "";
		CnStBaseboards = "";
		CnStCarpintery = "";
		CnStCancel = "";
		condition = "";
		longitude = 0;
		latitude = 0;
		altitude = 0;
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
		isReinforcement = false;
		sinkingSeverity = 0;
		closeTo = new Vector<String>();
		siniestralityDescription = new String[10];
		siniestralityValue = new String[10];
		for(int i=0;i<10;i++){
			siniestralityDescription[i] = "";
			siniestralityValue[i] = "0.0";	
		}
//		photoRecord = new Vector<pojoPropertyAttachment>();
//		documentRecord = new Vector<pojoPropertyAttachment>();
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
	 * @return the propertyUse
	 */
	public String getPropertyUse() {
		return propertyUse;
	}

	/**
	 * @param propertyUse the propertyUse to set
	 */
	public void setPropertyUse(String propertyUse) {
		this.propertyUse = propertyUse;
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
	 * @return the walls
	 */
	public String getWalls() {
		return walls;
	}

	/**
	 * @param walls the walls to set
	 */
	public void setWalls(String walls) {
		this.walls = walls;
	}

	/**
	 * @return the storyStructure
	 */
	public String getStoryStructure() {
		return storyStructure;
	}

	/**
	 * @param storyStructure the storyStructure to set
	 */
	public void setStoryStructure(String storyStructure) {
		this.storyStructure = storyStructure;
	}

	/**
	 * @return the storyMaterial
	 */
	public String getStoryMaterial() {
		return storyMaterial;
	}

	/**
	 * @param storyMaterial the storyMaterial to set
	 */
	public void setStoryMaterial(String storyMaterial) {
		this.storyMaterial = storyMaterial;
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
	 * @return the lifting
	 */
	public String getLifting() {
		return lifting;
	}

	/**
	 * @param lifting the lifting to set
	 */
	public void setLifting(String lifting) {
		this.lifting = lifting;
	}

	/**
	 * @return the roofItems
	 */
	public boolean isRoofItems() {
		return roofItems;
	}

	/**
	 * @param roofItems the roofItems to set
	 */
	public void setRoofItems(boolean roofItems) {
		this.roofItems = roofItems;
	}

	/**
	 * @return the domes
	 */
	public boolean isDomes() {
		return Domes;
	}

	/**
	 * @param domes the domes to set
	 */
	public void setDomes(boolean domes) {
		Domes = domes;
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
	 * @return the closeTo
	 */
	public Vector<String> getCloseTo() {
		return closeTo;
	}

	/**
	 * @param closeTo the closeTo to set
	 */
	public void setCloseTo(Vector<String> closeTo) {
		this.closeTo = closeTo;
	}

	/**
	 * @return the fachadeGlassSize
	 */
	public String getFachadeGlassSize() {
		return fachadeGlassSize;
	}

	/**
	 * @param fachadeGlassSize the fachadeGlassSize to set
	 */
	public void setFachadeGlassSize(String fachadeGlassSize) {
		this.fachadeGlassSize = fachadeGlassSize;
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
	 * @return the isReinforcement
	 */
	public boolean isReinforcement() {
		return isReinforcement;
	}

	/**
	 * @param isReinforcement the isReinforcement to set
	 */
	public void setReinforcement(boolean isReinforcement) {
		this.isReinforcement = isReinforcement;
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
	 * @return the cnStExternal
	 */
	public String getCnStExternal() {
		return CnStExternal;
	}

	/**
	 * @param cnStExternal the cnStExternal to set
	 */
	public void setCnStExternal(String cnStExternal) {
		CnStExternal = cnStExternal;
	}

	/**
	 * @return the cnStInternal
	 */
	public String getCnStInternal() {
		return CnStInternal;
	}

	/**
	 * @param cnStInternal the cnStInternal to set
	 */
	public void setCnStInternal(String cnStInternal) {
		CnStInternal = cnStInternal;
	}

	/**
	 * @return the cnStCeiling
	 */
	public String getCnStCeiling() {
		return CnStCeiling;
	}

	/**
	 * @param cnStCeiling the cnStCeiling to set
	 */
	public void setCnStCeiling(String cnStCeiling) {
		CnStCeiling = cnStCeiling;
	}

	/**
	 * @return the cnStRoofs
	 */
	public String getCnStRoofs() {
		return CnStRoofs;
	}

	/**
	 * @param cnStRoofs the cnStRoofs to set
	 */
	public void setCnStRoofs(String cnStRoofs) {
		CnStRoofs = cnStRoofs;
	}

	/**
	 * @return the cnStTrimmings
	 */
	public String getCnStTrimmings() {
		return CnStTrimmings;
	}

	/**
	 * @param cnStTrimmings the cnStTrimmings to set
	 */
	public void setCnStTrimmings(String cnStTrimmings) {
		CnStTrimmings = cnStTrimmings;
	}

	/**
	 * @return the cnStFloors
	 */
	public String getCnStFloors() {
		return CnStFloors;
	}

	/**
	 * @param cnStFloors the cnStFloors to set
	 */
	public void setCnStFloors(String cnStFloors) {
		CnStFloors = cnStFloors;
	}

	/**
	 * @return the cnStBaseboards
	 */
	public String getCnStBaseboards() {
		return CnStBaseboards;
	}

	/**
	 * @param cnStBaseboards the cnStBaseboards to set
	 */
	public void setCnStBaseboards(String cnStBaseboards) {
		CnStBaseboards = cnStBaseboards;
	}

	/**
	 * @return the cnStCarpintery
	 */
	public String getCnStCarpintery() {
		return CnStCarpintery;
	}

	/**
	 * @param cnStCarpintery the cnStCarpintery to set
	 */
	public void setCnStCarpintery(String cnStCarpintery) {
		CnStCarpintery = cnStCarpintery;
	}

	/**
	 * @return the cnStCancel
	 */
	public String getCnStCancel() {
		return CnStCancel;
	}

	/**
	 * @param cnStCancel the cnStCancel to set
	 */
	public void setCnStCancel(String cnStCancel) {
		CnStCancel = cnStCancel;
	}

	/**
	 * @return the cnStPainting
	 */
	public String getCnStPainting() {
		return CnStPainting;
	}

	/**
	 * @param cnStPainting the cnStPainting to set
	 */
	public void setCnStPainting(String cnStPainting) {
		CnStPainting = cnStPainting;
	}

	/**
	 * @return the cnStSanitaryFurn
	 */
	public String getCnStSanitaryFurn() {
		return CnStSanitaryFurn;
	}

	/**
	 * @param cnStSanitaryFurn the cnStSanitaryFurn to set
	 */
	public void setCnStSanitaryFurn(String cnStSanitaryFurn) {
		CnStSanitaryFurn = cnStSanitaryFurn;
	}

	/**
	 * @return the cnStSanitaryInst
	 */
	public String getCnStSanitaryInst() {
		return CnStSanitaryInst;
	}

	/**
	 * @param cnStSanitaryInst the cnStSanitaryInst to set
	 */
	public void setCnStSanitaryInst(String cnStSanitaryInst) {
		CnStSanitaryInst = cnStSanitaryInst;
	}

	/**
	 * @return the cnStElectricInst
	 */
	public String getCnStElectricInst() {
		return CnStElectricInst;
	}

	/**
	 * @param cnStElectricInst the cnStElectricInst to set
	 */
	public void setCnStElectricInst(String cnStElectricInst) {
		CnStElectricInst = cnStElectricInst;
	}

	/**
	 * @return the cnStBlackSmith
	 */
	public String getCnStBlackSmith() {
		return CnStBlackSmith;
	}

	/**
	 * @param cnStBlackSmith the cnStBlackSmith to set
	 */
	public void setCnStBlackSmith(String cnStBlackSmith) {
		CnStBlackSmith = cnStBlackSmith;
	}

	/**
	 * @return the cnStGlassery
	 */
	public String getCnStGlassery() {
		return CnStGlassery;
	}

	/**
	 * @param cnStGlassery the cnStGlassery to set
	 */
	public void setCnStGlassery(String cnStGlassery) {
		CnStGlassery = cnStGlassery;
	}

	/**
	 * @return the cnStFachade
	 */
	public String getCnStFachade() {
		return CnStFachade;
	}

	/**
	 * @param cnStFachade the cnStFachade to set
	 */
	public void setCnStFachade(String cnStFachade) {
		CnStFachade = cnStFachade;
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
	 * @return the siniestralityValue
	 */
	public String[] getSiniestralityValue() {
		return siniestralityValue;
	}

	/**
	 * @param siniestralityValue the siniestralityValue to set
	 */
	public void setSiniestralityValue(String[] siniestralityValue) {
		this.siniestralityValue = siniestralityValue;
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
	 * @return the glassWareType
	 */
	public String getGlassWareType() {
		return glassWareType;
	}

	/**
	 * @param glassWareType the glassWareType to set
	 */
	public void setGlassWareType(String glassWareType) {
		this.glassWareType = glassWareType;
	}

	/**
	 * @return the blacksmithType
	 */
	public String getBlacksmithType() {
		return blacksmithType;
	}

	/**
	 * @param blacksmithType the blacksmithType to set
	 */
	public void setBlacksmithType(String blacksmithType) {
		this.blacksmithType = blacksmithType;
	}

	/**
	 * @return the regularGeometr
	 */
	public boolean isRegularGeometr() {
		return regularGeometr;
	}

	/**
	 * @param regularGeometr the regularGeometr to set
	 */
	public void setRegularGeometr(boolean regularGeometr) {
		this.regularGeometr = regularGeometr;
	}

	/**
	 * @return the irregularGeometry
	 */
	public boolean isIrregularGeometry() {
		return irregularGeometry;
	}

	/**
	 * @param irregularGeometry the irregularGeometry to set
	 */
	public void setIrregularGeometry(boolean irregularGeometry) {
		this.irregularGeometry = irregularGeometry;
	}

	/**
	 * @return the cnStPlumbing
	 */
	public String getCnStPlumbing() {
		return CnStPlumbing;
	}

	/**
	 * @param cnStPlumbing the cnStPlumbing to set
	 */
	public void setCnStPlumbing(String cnStPlumbing) {
		CnStPlumbing = cnStPlumbing;
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
	 * @return the photoRecord
	 */
//	public Vector<pojoPropertyAttachment> getPhotoRecord() {
//		return photoRecord;
//	}
//
//	/**
//	 * @param photoRecord the photoRecord to set
//	 */
//	public void setPhotoRecord(Vector<pojoPropertyAttachment> photoRecord) {
//		this.photoRecord = photoRecord;
//	}
//
//	/**
//	 * @return the documentRecord
//	 */
//	public Vector<pojoPropertyAttachment> getDocumentRecord() {
//		return documentRecord;
//	}
//
//	/**
//	 * @param documentRecord the documentRecord to set
//	 */
//	public void setDocumentRecord(Vector<pojoPropertyAttachment> documentRecord) {
//		this.documentRecord = documentRecord;
//	}

	/**
	 * @return the educationType
	 */
	public String getEducationType() {
		return educationType;
	}

	/**
	 * @param educationType the educationType to set
	 */
	public void setEducationType(String educationType) {
		this.educationType = educationType;
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
	 * @return the closeToPole
	 */
	public boolean isCloseToPole() {
		return closeToPole;
	}

	/**
	 * @param closeToPole the closeToPole to set
	 */
	public void setCloseToPole(boolean closeToPole) {
		this.closeToPole = closeToPole;
	}

	/**
	 * @return the closeToAds
	 */
	public boolean isCloseToAds() {
		return closeToAds;
	}

	/**
	 * @param closeToAds the closeToAds to set
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
	 * @return the windowProtectionType
	 */
	public String getWindowProtectionType() {
		return windowProtectionType;
	}

	/**
	 * @param windowProtectionType the windowProtectionType to set
	 */
	public void setWindowProtectionType(String windowProtectionType) {
		this.windowProtectionType = windowProtectionType;
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
	 * @return the externalStrPounding
	 */
	public boolean isExternalStrPounding() {
		return externalStrPounding;
	}

	/**
	 * @param externalStrPounding the externalStrPounding to set
	 */
	public void setExternalStrPounding(boolean externalStrPounding) {
		this.externalStrPounding = externalStrPounding;
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