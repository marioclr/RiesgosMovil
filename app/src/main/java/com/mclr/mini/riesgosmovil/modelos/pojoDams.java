package com.mclr.mini.riesgosmovil.modelos;

import java.util.Vector;

public class pojoDams {
	private String officialNumber;
	private String damName;
	private String stateID;
	private String CNARegion;
	private String hidrologicRegion;
	private String constructionDate;
	private String pumb;
	private String current;
	private String afluent;
	private String condition;
	private String NAMOVolumen;
	private String sismicity;
	private String deseigner;
	private String constructor;
	private String responsibleOrg;
	private String accessWay;
	private String INEGILetter;
	private String contructionPurpouse;
	private String waterUser;
	private double surface;
	private double maxAnnualRunoff;
	private double avgAnnualRunoff;
	private int curtainsQtty;
	private String propertyID;
	private String damID;
	private double latitude;
	private double longitude;
	private double altitude;
	private String ZIP;
	private String colony;
	private String state;
	private String stateName;
	private String city;
	private String address;
	private String postalCodeID;
	private String postalCode;
	private String town;
	private String closesWaterBody;
	private String soilType;
	private String contructionDate;
	private String structuralType;
	private String foundationType;
	private String elevation;
	private String geometry;
	private String geometySpecitication;
	private boolean geometryRegular;
	private boolean geometryIrregular;
	private boolean shortWallsbeneathColumns;
	private boolean pounding;
	private boolean sinkingPresence;
	private int sinkingSeverity;
	private boolean reinforcements;
	private String reinforcementDate;
	private boolean fireProtection;
	private String fireProtectionDesciption;
	private boolean earthQuakeFormer;
	private boolean fleedFormer;
	private boolean collapseFormer;
	private boolean landSlideFormer;
	private boolean hurricaneFormer;
	private boolean sprinkler;
	private boolean hydrant;
	private boolean tank;
	private boolean extintor;
	private String subSoilType;
	private String currentsTypes;
	private double currentLevels;
	private double currentWaterQtty;
	private double damLength;
	private double damHeight;
	private double baseWidth;
	private double crownWidth;
	private double upstreamSlope;
	private double downstreamSlope;
	private double damUpStreamSlope;
	private double damDownStreamSlope;
	private String dumpLocation;
	private String machineRoomeLocation;
	private double hidraulicFeaturesLongitude;
	private double hidraulicFeaturesLatitude;
	private String hidrologicBasins;
	private String closestWeatherStation;
	private String damType;
	private String damUse;
	private double closestTownDistance;
	private boolean fleedRisk;
	private boolean earthQuakeRisk;
	private boolean collapseRisk;
	private boolean landSlideRisk;
	private boolean hurricaneRisk;
	private double maxAnnualPrecipitation;
	private double maxMonthlyPrecipitation;
	private double maxDailyPrecipitaion;
	private String workShopValue;
	private String warehouseValue;
	private String conductionTunnelValue;
	private String damBodyValue;
	private String spillwayValue;
	private String intakeWorksValue;
	private String machineRoomsValue;
	private String EMFacilitiesValue;
	private String electricDuctsValue;
	private double other1Value;
	private double other2Value;
	private double other3Value;
	private String other1Description;
	private String other2Description;
	private String other3Desccription;
	private double irrigationChannelLength;
	private double irrigationChannelWidth;
	private String irrigationChannelValue;
	private boolean cofferDam;
	private String cofferdamCapacity;
	private double deviationChannelCapacity;
	private String[] siniestralityValue;
	private String[] siniestralityDescription;
	private String recontructionValue;
	private double NAME;
	private double NAMO;
	private double NAMINO;
	private double NAMEVolume;
	private double NAMOVolume;
	private double NAMINOVolume;
	private double sedimentationVolume;
	private double usefulVolume;
	private double superStorage;
	private double conservationVolume;
	private double floodControlVolume;
	private double deseignMaxCurrent;
	private double returnPeriod;
	private double deseignFloodVolume;
	private double maxFloodRegistered;
	private double maxCurrentRegistered;
	private double waterDownChannelCapacity;
	private int waterdownDamsNumber;
	private Vector<String> damsNames;
	private Vector<pojoDamCurtain> curtainFeatures;
	private Vector<pojoDamGalery> damGaleries;
	private Vector<pojoDamDikes> damDikes;
	private Vector<pojoDamChute> damChites;
	private Vector<pojoDamHeadworks> damHeadworks;
	private Vector<pojoVenting> damVentings;
	private Vector<pojoDamSpillway> damSpillWays;
	private Vector<pojoDamHydraulicFacility> damHydroFacility;
	private Vector<pojoOtherStructures> damOther;
	private Vector<pojoIrrigationChannel> damIrrigation;
	private String specialOperationInstructions;
	private String comments;

	private int status;
	private int finding;

	public pojoDams(){
		specialOperationInstructions = "";
		comments = "";
		damID = "";
		officialNumber = "";
		damName = "";
		stateID = "";
		CNARegion = "";
		hidrologicRegion = "";
		constructionDate = "";
		condition = "";
		pumb = "";
		current = "";
		afluent = "";
		NAMOVolumen = "";
		sismicity = "";
		deseigner = "";
		constructor = "";
		responsibleOrg = "";
		accessWay = "";
		INEGILetter = "";
		contructionPurpouse = "";
		waterUser = "";
		waterDownChannelCapacity = 0;
		surface = 0;
		maxAnnualRunoff = 0;
		avgAnnualRunoff = 0;
		NAME = 0;
		NAMO = 0;
		NAMINO = 0;
		NAMEVolume = 0;
		NAMOVolume = 0;
		NAMINOVolume = 0;
		sedimentationVolume = 0;
		usefulVolume = 0;
		superStorage = 0;
		conservationVolume = 0;
		floodControlVolume = 0;
		deseignMaxCurrent = 0;
		returnPeriod = 0;
		deseignFloodVolume = 0;
		maxFloodRegistered = 0;
		maxCurrentRegistered = 0;
		curtainsQtty = 0;
		waterdownDamsNumber = 0;
		curtainFeatures = new Vector<pojoDamCurtain>();
		damsNames= new Vector<String>();
		damGaleries = new Vector<pojoDamGalery>();
		damDikes = new Vector<pojoDamDikes>();
		damChites = new Vector<pojoDamChute>();
		damHeadworks = new Vector<pojoDamHeadworks>();
		damVentings = new Vector<pojoVenting>();
		propertyID = "00000000-0000-0000-0000-000000000000";
		damID = "";
		town = "";
		other1Description = "";
		other2Description = "";
		other3Desccription = "";
		hidrologicBasins = "";
		closestWeatherStation = "";
		damType = "";
		damUse = "";
		dumpLocation = "";
		machineRoomeLocation = "";
		subSoilType = "";
		currentsTypes = "";
		fireProtectionDesciption = "";
		reinforcementDate = "";
		sinkingSeverity = 0;
		address = "";
		postalCodeID = "";
		postalCode = "";
		ZIP = "";
		colony = "";
		state = "";
		stateName = "";
		city = "";
		closesWaterBody = "";
		soilType = "";
		contructionDate = "";
		structuralType = "";
		foundationType = "";
		elevation = "";
		geometry = "";
		geometySpecitication = "";
		geometryRegular = false;
		geometryIrregular = false;
		shortWallsbeneathColumns = false;
		pounding = false;
		sinkingPresence = false;
		reinforcements = false;
		fireProtection = false;
		earthQuakeFormer = false;
		fleedFormer = false;
		collapseFormer = false;
		landSlideFormer = false;
		hurricaneFormer = false;
		fleedRisk = false;
		earthQuakeRisk = false;
		collapseRisk = false;
		landSlideRisk = false;
		hurricaneRisk = false;
		cofferDam = false;
		cofferdamCapacity = "";
		sprinkler = false;
		hydrant = false;
		tank = false;
		extintor = false;
		latitude = 0;
		longitude = 0;
		altitude = 0;
		currentLevels = 0;
		currentWaterQtty = 0;
		damLength = 0;
		damHeight = 0;
		baseWidth = 0;
		crownWidth = 0;
		upstreamSlope = 0;
		downstreamSlope = 0;
		damUpStreamSlope = 0;
		damDownStreamSlope = 0;
		hidraulicFeaturesLongitude = 0;
		hidraulicFeaturesLatitude = 0;
		closestTownDistance = 0;
		maxAnnualPrecipitation = 0;
		maxMonthlyPrecipitation = 0;
		maxDailyPrecipitaion = 0;
		workShopValue = "0";
		warehouseValue = "0";
		conductionTunnelValue = "0";
		damBodyValue = "0";
		spillwayValue = "0";
		intakeWorksValue = "0";
		machineRoomsValue = "0";
		EMFacilitiesValue = "0";
		electricDuctsValue = "0";
		other1Value = 0;
		other2Value = 0;
		other3Value = 0;
		irrigationChannelLength = 0;
		irrigationChannelWidth = 0;
		irrigationChannelValue = "0";
		deviationChannelCapacity = 0;
		recontructionValue = "0";
		siniestralityValue = new String[10];
		siniestralityDescription = new String[10];
		for (int i=0;i<10;i++){
			siniestralityValue[i] = "0.0";
			siniestralityDescription[i] = "";
		}
		damSpillWays = new Vector<pojoDamSpillway>();
		damHydroFacility = new Vector<pojoDamHydraulicFacility>();
		damOther = new Vector<pojoOtherStructures>();
		damIrrigation = new Vector<pojoIrrigationChannel>();
	}
	
	
	/**
	 * @return the officialNumber
	 */
	public String getOfficialNumber() {
		return officialNumber;
	}
	/**
	 * @param officialNumber the officialNumber to set
	 */
	public void setOfficialNumber(String officialNumber) {
		this.officialNumber = officialNumber;
	}
	/**
	 * @return the damName
	 */
	public String getDamName() {
		return damName;
	}
	/**
	 * @param damName the damName to set
	 */
	public void setDamName(String damName) {
		this.damName = damName;
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
	 * @return the cNARegion
	 */
	public String getCNARegion() {
		return CNARegion;
	}
	/**
	 * @param cNARegion the cNARegion to set
	 */
	public void setCNARegion(String cNARegion) {
		CNARegion = cNARegion;
	}
	/**
	 * @return the hidrologicRegion
	 */
	public String getHidrologicRegion() {
		return hidrologicRegion;
	}
	/**
	 * @param hidrologicRegion the hidrologicRegion to set
	 */
	public void setHidrologicRegion(String hidrologicRegion) {
		this.hidrologicRegion = hidrologicRegion;
	}
	/**
	 * @return the pumb
	 */
	public String getPumb() {
		return pumb;
	}
	/**
	 * @param pumb the pumb to set
	 */
	public void setPumb(String pumb) {
		this.pumb = pumb;
	}
	/**
	 * @return the current
	 */
	public String getCurrent() {
		return current;
	}
	/**
	 * @param current the current to set
	 */
	public void setCurrent(String current) {
		this.current = current;
	}
	/**
	 * @return the afluent
	 */
	public String getAfluent() {
		return afluent;
	}
	/**
	 * @param afluent the afluent to set
	 */
	public void setAfluent(String afluent) {
		this.afluent = afluent;
	}
	/**
	 * @return the nAMOVolumen
	 */
	public String getNAMOVolumen() {
		return NAMOVolumen;
	}
	/**
	 * @param nAMOVolumen the nAMOVolumen to set
	 */
	public void setNAMOVolumen(String nAMOVolumen) {
		NAMOVolumen = nAMOVolumen;
	}
	/**
	 * @return the sismicity
	 */
	public String getSismicity() {
		return sismicity;
	}
	/**
	 * @param sismicity the sismicity to set
	 */
	public void setSismicity(String sismicity) {
		this.sismicity = sismicity;
	}
	/**
	 * @return the deseigner
	 */
	public String getDeseigner() {
		return deseigner;
	}
	/**
	 * @param deseigner the deseigner to set
	 */
	public void setDeseigner(String deseigner) {
		this.deseigner = deseigner;
	}
	/**
	 * @return the constructor
	 */
	public String getConstructor() {
		return constructor;
	}
	/**
	 * @param constructor the constructor to set
	 */
	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}
	/**
	 * @return the responsibleOrg
	 */
	public String getResponsibleOrg() {
		return responsibleOrg;
	}
	/**
	 * @param responsibleOrg the responsibleOrg to set
	 */
	public void setResponsibleOrg(String responsibleOrg) {
		this.responsibleOrg = responsibleOrg;
	}
	/**
	 * @return the accessWay
	 */
	public String getAccessWay() {
		return accessWay;
	}
	/**
	 * @param accessWay the accessWay to set
	 */
	public void setAccessWay(String accessWay) {
		this.accessWay = accessWay;
	}
	/**
	 * @return the iNEGILetter
	 */
	public String getINEGILetter() {
		return INEGILetter;
	}
	/**
	 * @param iNEGILetter the iNEGILetter to set
	 */
	public void setINEGILetter(String iNEGILetter) {
		INEGILetter = iNEGILetter;
	}
	/**
	 * @return the contructionPurpouse
	 */
	public String getContructionPurpouse() {
		return contructionPurpouse;
	}
	/**
	 * @param contructionPurpouse the contructionPurpouse to set
	 */
	public void setContructionPurpouse(String contructionPurpouse) {
		this.contructionPurpouse = contructionPurpouse;
	}
	/**
	 * @return the waterUser
	 */
	public String getWaterUser() {
		return waterUser;
	}
	/**
	 * @param waterUser the waterUser to set
	 */
	public void setWaterUser(String waterUser) {
		this.waterUser = waterUser;
	}
	/**
	 * @return the surface
	 */
	public double getSurface() {
		return surface;
	}
	/**
	 * @param surface the surface to set
	 */
	public void setSurface(double surface) {
		this.surface = surface;
	}
	/**
	 * @return the maxAnnualRunoff
	 */
	public double getMaxAnnualRunoff() {
		return maxAnnualRunoff;
	}
	/**
	 * @param maxAnnualRunoff the maxAnnualRunoff to set
	 */
	public void setMaxAnnualRunoff(double maxAnnualRunoff) {
		this.maxAnnualRunoff = maxAnnualRunoff;
	}
	/**
	 * @return the avgAnnualRunoff
	 */
	public double getAvgAnnualRunoff() {
		return avgAnnualRunoff;
	}
	/**
	 * @param avgAnnualRunoff the avgAnnualRunoff to set
	 */
	public void setAvgAnnualRunoff(double avgAnnualRunoff) {
		this.avgAnnualRunoff = avgAnnualRunoff;
	}
	/**
	 * @return the curtainsQtty
	 */
	public int getCurtainsQtty() {
		return curtainsQtty;
	}
	/**
	 * @param curtainsQtty the curtainsQtty to set
	 */
	public void setCurtainsQtty(int curtainsQtty) {
		this.curtainsQtty = curtainsQtty;
	}
	/**
	 * @return the curtainFeatures
	 */
	public Vector<pojoDamCurtain> getCurtainFeatures() {
		return curtainFeatures;
	}
	/**
	 * @param curtainFeatures the curtainFeatures to set
	 */
	public void setCurtainFeatures(Vector<pojoDamCurtain> curtainFeatures) {
		this.curtainFeatures = curtainFeatures;
	}
	/**
	 * @return the nAME
	 */
	public double getNAME() {
		return NAME;
	}
	/**
	 * @param nAME the nAME to set
	 */
	public void setNAME(double nAME) {
		NAME = nAME;
	}
	/**
	 * @return the nAMO
	 */
	public double getNAMO() {
		return NAMO;
	}
	/**
	 * @param nAMO the nAMO to set
	 */
	public void setNAMO(double nAMO) {
		NAMO = nAMO;
	}
	/**
	 * @return the nAMEVolume
	 */
	public double getNAMEVolume() {
		return NAMEVolume;
	}
	/**
	 * @param nAMEVolume the nAMEVolume to set
	 */
	public void setNAMEVolume(double nAMEVolume) {
		NAMEVolume = nAMEVolume;
	}
	/**
	 * @return the nAMOVolume
	 */
	public double getNAMOVolume() {
		return NAMOVolume;
	}
	/**
	 * @param nAMOVolume the nAMOVolume to set
	 */
	public void setNAMOVolume(double nAMOVolume) {
		NAMOVolume = nAMOVolume;
	}
	/**
	 * @return the nAMINOVolume
	 */
	public double getNAMINOVolume() {
		return NAMINOVolume;
	}
	/**
	 * @param nAMINOVolume the nAMINOVolume to set
	 */
	public void setNAMINOVolume(double nAMINOVolume) {
		NAMINOVolume = nAMINOVolume;
	}
	/**
	 * @return the sedimentationVolume
	 */
	public double getSedimentationVolume() {
		return sedimentationVolume;
	}
	/**
	 * @param sedimentationVolume the sedimentationVolume to set
	 */
	public void setSedimentationVolume(double sedimentationVolume) {
		this.sedimentationVolume = sedimentationVolume;
	}
	/**
	 * @return the usefulVolume
	 */
	public double getUsefulVolume() {
		return usefulVolume;
	}
	/**
	 * @param usefulVolume the usefulVolume to set
	 */
	public void setUsefulVolume(double usefulVolume) {
		this.usefulVolume = usefulVolume;
	}
	/**
	 * @return the superStorage
	 */
	public double getSuperStorage() {
		return superStorage;
	}
	/**
	 * @param superStorage the superStorage to set
	 */
	public void setSuperStorage(double superStorage) {
		this.superStorage = superStorage;
	}
	/**
	 * @return the conservationVolume
	 */
	public double getConservationVolume() {
		return conservationVolume;
	}
	/**
	 * @param conservationVolume the conservationVolume to set
	 */
	public void setConservationVolume(double conservationVolume) {
		this.conservationVolume = conservationVolume;
	}
	/**
	 * @return the floodControlVolume
	 */
	public double getFloodControlVolume() {
		return floodControlVolume;
	}
	/**
	 * @param floodControlVolume the floodControlVolume to set
	 */
	public void setFloodControlVolume(double floodControlVolume) {
		this.floodControlVolume = floodControlVolume;
	}
	/**
	 * @return the deseignMaxCurrent
	 */
	public double getDeseignMaxCurrent() {
		return deseignMaxCurrent;
	}
	/**
	 * @param deseignMaxCurrent the deseignMaxCurrent to set
	 */
	public void setDeseignMaxCurrent(double deseignMaxCurrent) {
		this.deseignMaxCurrent = deseignMaxCurrent;
	}
	/**
	 * @return the returnPeriod
	 */
	public double getReturnPeriod() {
		return returnPeriod;
	}
	/**
	 * @param returnPeriod the returnPeriod to set
	 */
	public void setReturnPeriod(double returnPeriod) {
		this.returnPeriod = returnPeriod;
	}
	/**
	 * @return the deseignFloodVolume
	 */
	public double getDeseignFloodVolume() {
		return deseignFloodVolume;
	}
	/**
	 * @param deseignFloodVolume the deseignFloodVolume to set
	 */
	public void setDeseignFloodVolume(double deseignFloodVolume) {
		this.deseignFloodVolume = deseignFloodVolume;
	}
	/**
	 * @return the maxFloodRegistered
	 */
	public double getMaxFloodRegistered() {
		return maxFloodRegistered;
	}
	/**
	 * @param maxFloodRegistered the maxFloodRegistered to set
	 */
	public void setMaxFloodRegistered(double maxFloodRegistered) {
		this.maxFloodRegistered = maxFloodRegistered;
	}
	/**
	 * @return the maxCurrentRegistered
	 */
	public double getMaxCurrentRegistered() {
		return maxCurrentRegistered;
	}
	/**
	 * @param maxCurrentRegistered the maxCurrentRegistered to set
	 */
	public void setMaxCurrentRegistered(double maxCurrentRegistered) {
		this.maxCurrentRegistered = maxCurrentRegistered;
	}
	/**
	 * @return the waterDownChannelCapacity
	 */
	public double getWaterDownChannelCapacity() {
		return waterDownChannelCapacity;
	}
	/**
	 * @param waterDownChannelCapacity the waterDownChannelCapacity to set
	 */
	public void setWaterDownChannelCapacity(double waterDownChannelCapacity) {
		this.waterDownChannelCapacity = waterDownChannelCapacity;
	}
	/**
	 * @return the waterdownDamsNumber
	 */
	public int getWaterdownDamsNumber() {
		return waterdownDamsNumber;
	}
	/**
	 * @param waterdownDamsNumber the waterdownDamsNumber to set
	 */
	public void setWaterdownDamsNumber(int waterdownDamsNumber) {
		this.waterdownDamsNumber = waterdownDamsNumber;
	}
	/**
	 * @return the damsNames
	 */
	public Vector<String> getDamsNames() {
		return damsNames;
	}
	/**
	 * @param damsNames the damsNames to set
	 */
	public void setDamsNames(Vector<String> damsNames) {
		this.damsNames = damsNames;
	}
	/**
	 * @return the damGaleries
	 */
	public Vector<pojoDamGalery> getDamGaleries() {
		return damGaleries;
	}
	/**
	 * @param damGaleries the damGaleries to set
	 */
	public void setDamGaleries(Vector<pojoDamGalery> damGaleries) {
		this.damGaleries = damGaleries;
	}
	/**
	 * @return the damDikes
	 */
	public Vector<pojoDamDikes> getDamDikes() {
		return damDikes;
	}
	/**
	 * @param damDikes the damDikes to set
	 */
	public void setDamDikes(Vector<pojoDamDikes> damDikes) {
		this.damDikes = damDikes;
	}
	/**
	 * @return the damChites
	 */
	public Vector<pojoDamChute> getDamChites() {
		return damChites;
	}
	/**
	 * @param damChites the damChites to set
	 */
	public void setDamChites(Vector<pojoDamChute> damChites) {
		this.damChites = damChites;
	}
	/**
	 * @return the damHeadworks
	 */
	public Vector<pojoDamHeadworks> getDamHeadworks() {
		return damHeadworks;
	}
	/**
	 * @param damHeadworks the damHeadworks to set
	 */
	public void setDamHeadworks(Vector<pojoDamHeadworks> damHeadworks) {
		this.damHeadworks = damHeadworks;
	}
	/**
	 * @return the specialOperationInstructions
	 */
	public String getSpecialOperationInstructions() {
		return specialOperationInstructions;
	}
	/**
	 * @param specialOperationInstructions the specialOperationInstructions to set
	 */
	public void setSpecialOperationInstructions(String specialOperationInstructions) {
		this.specialOperationInstructions = specialOperationInstructions;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * @return the closesWaterBody
	 */
	public String getClosesWaterBody() {
		return closesWaterBody;
	}

	/**
	 * @param closesWaterBody the closesWaterBody to set
	 */
	public void setClosesWaterBody(String closesWaterBody) {
		this.closesWaterBody = closesWaterBody;
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
	 * @return the contructionDate
	 */
	public String getContructionDate() {
		return contructionDate;
	}

	/**
	 * @param contructionDate the contructionDate to set
	 */
	public void setContructionDate(String contructionDate) {
		this.contructionDate = contructionDate;
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
	 * @return the geometry
	 */
	public String getGeometry() {
		return geometry;
	}

	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	/**
	 * @return the geometySpecitication
	 */
	public String getGeometySpecitication() {
		return geometySpecitication;
	}

	/**
	 * @param geometySpecitication the geometySpecitication to set
	 */
	public void setGeometySpecitication(String geometySpecitication) {
		this.geometySpecitication = geometySpecitication;
	}

	/**
	 * @return the shortWallsbeneathColumns
	 */
	public boolean isShortWallsbeneathColumns() {
		return shortWallsbeneathColumns;
	}

	/**
	 * @param shortWallsbeneathColumns the shortWallsbeneathColumns to set
	 */
	public void setShortWallsbeneathColumns(boolean shortWallsbeneathColumns) {
		this.shortWallsbeneathColumns = shortWallsbeneathColumns;
	}

	/**
	 * @return the pounding
	 */
	public boolean isPounding() {
		return pounding;
	}

	/**
	 * @param pounding the pounding to set
	 */
	public void setPounding(boolean pounding) {
		this.pounding = pounding;
	}

	/**
	 * @return the sinkingPresence
	 */
	public boolean isSinkingPresence() {
		return sinkingPresence;
	}

	/**
	 * @param sinkingPresence the sinkingExistence to set
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
	 * @return the reinforcements
	 */
	public boolean isReinforcements() {
		return reinforcements;
	}

	/**
	 * @param reinforcements the reinforcements to set
	 */
	public void setReinforcements(boolean reinforcements) {
		this.reinforcements = reinforcements;
	}

	/**
	 * @return the reinforcementDate
	 */
	public String getReinforcementDate() {
		return reinforcementDate;
	}

	/**
	 * @param reinforcementDate the reinforcementDate to set
	 */
	public void setReinforcementDate(String reinforcementDate) {
		this.reinforcementDate = reinforcementDate;
	}

	/**
	 * @return the fireProtection
	 */
	public boolean isFireProtection() {
		return fireProtection;
	}

	/**
	 * @param fireProtection the fireProtection to set
	 */
	public void setFireProtection(boolean fireProtection) {
		this.fireProtection = fireProtection;
	}

	/**
	 * @return the fireProtectionDesciption
	 */
	public String getFireProtectionDesciption() {
		return fireProtectionDesciption;
	}

	/**
	 * @param fireProtectionDesciption the fireProtectionDesciption to set
	 */
	public void setFireProtectionDesciption(String fireProtectionDesciption) {
		this.fireProtectionDesciption = fireProtectionDesciption;
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
	 * @return the fleedFormer
	 */
	public boolean isFleedFormer() {
		return fleedFormer;
	}

	/**
	 * @param fleedFormer the fleedFormer to set
	 */
	public void setFleedFormer(boolean fleedFormer) {
		this.fleedFormer = fleedFormer;
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
	 * @return the subSoilType
	 */
	public String getSubSoilType() {
		return subSoilType;
	}

	/**
	 * @param subSoilType the subSoilType to set
	 */
	public void setSubSoilType(String subSoilType) {
		this.subSoilType = subSoilType;
	}

	/**
	 * @return the currentsTypes
	 */
	public String getCurrentsTypes() {
		return currentsTypes;
	}

	/**
	 * @param currentsTypes the currentsTypes to set
	 */
	public void setCurrentsTypes(String currentsTypes) {
		this.currentsTypes = currentsTypes;
	}

	/**
	 * @return the currentLevels
	 */
	public double getCurrentLevels() {
		return currentLevels;
	}

	/**
	 * @param currentLevels the currentLevels to set
	 */
	public void setCurrentLevels(double currentLevels) {
		this.currentLevels = currentLevels;
	}

	/**
	 * @return the currentWaterQtty
	 */
	public double getCurrentWaterQtty() {
		return currentWaterQtty;
	}

	/**
	 * @param currentWaterQtty the currentWaterQtty to set
	 */
	public void setCurrentWaterQtty(double currentWaterQtty) {
		this.currentWaterQtty = currentWaterQtty;
	}

	/**
	 * @return the damLength
	 */
	public double getDamLength() {
		return damLength;
	}

	/**
	 * @param damLength the damLength to set
	 */
	public void setDamLength(double damLength) {
		this.damLength = damLength;
	}

	/**
	 * @return the damHeight
	 */
	public double getDamHeight() {
		return damHeight;
	}

	/**
	 * @param damHeight the damHeight to set
	 */
	public void setDamHeight(double damHeight) {
		this.damHeight = damHeight;
	}

	/**
	 * @return the baseWidth
	 */
	public double getBaseWidth() {
		return baseWidth;
	}

	/**
	 * @param baseWidth the baseWidth to set
	 */
	public void setBaseWidth(double baseWidth) {
		this.baseWidth = baseWidth;
	}

	/**
	 * @return the crownWidth
	 */
	public double getCrownWidth() {
		return crownWidth;
	}

	/**
	 * @param crownWidth the crownWidth to set
	 */
	public void setCrownWidth(double crownWidth) {
		this.crownWidth = crownWidth;
	}

	/**
	 * @return the upstreamSlope
	 */
	public double getUpstreamSlope() {
		return upstreamSlope;
	}

	/**
	 * @param upstreamSlope the upstreamSlope to set
	 */
	public void setUpstreamSlope(double upstreamSlope) {
		this.upstreamSlope = upstreamSlope;
	}

	/**
	 * @return the downstreamSlope
	 */
	public double getDownstreamSlope() {
		return downstreamSlope;
	}

	/**
	 * @param downstreamSlope the downstreamSlope to set
	 */
	public void setDownstreamSlope(double downstreamSlope) {
		this.downstreamSlope = downstreamSlope;
	}

	/**
	 * @return the dumpLocation
	 */
	public String getDumpLocation() {
		return dumpLocation;
	}

	/**
	 * @param dumpLocation the dumpLocation to set
	 */
	public void setDumpLocation(String dumpLocation) {
		this.dumpLocation = dumpLocation;
	}

	/**
	 * @return the machineRoomeLocation
	 */
	public String getMachineRoomeLocation() {
		return machineRoomeLocation;
	}

	/**
	 * @param machineRoomeLocation the machineRoomeLocation to set
	 */
	public void setMachineRoomeLocation(String machineRoomeLocation) {
		this.machineRoomeLocation = machineRoomeLocation;
	}

	/**
	 * @return the hidraulicFeaturesLongitude
	 */
	public double getHidraulicFeaturesLongitude() {
		return hidraulicFeaturesLongitude;
	}

	/**
	 * @param hidraulicFeaturesLongitude the hidraulicFeaturesLongitude to set
	 */
	public void setHidraulicFeaturesLongitude(double hidraulicFeaturesLongitude) {
		this.hidraulicFeaturesLongitude = hidraulicFeaturesLongitude;
	}

	/**
	 * @return the hidraulicFeaturesLatitude
	 */
	public double getHidraulicFeaturesLatitude() {
		return hidraulicFeaturesLatitude;
	}

	/**
	 * @param hidraulicFeaturesLatitude the hidraulicFeaturesLatitude to set
	 */
	public void setHidraulicFeaturesLatitude(double hidraulicFeaturesLatitude) {
		this.hidraulicFeaturesLatitude = hidraulicFeaturesLatitude;
	}

	/**
	 * @return the hidrologicBasins
	 */
	public String getHidrologicBasins() {
		return hidrologicBasins;
	}

	/**
	 * @param hidrologicBasins the hidrologicBasins to set
	 */
	public void setHidrologicBasins(String hidrologicBasins) {
		this.hidrologicBasins = hidrologicBasins;
	}

	/**
	 * @return the closestWeatherStation
	 */
	public String getClosestWeatherStation() {
		return closestWeatherStation;
	}

	/**
	 * @param closestWeatherStation the closestWeatherStation to set
	 */
	public void setClosestWeatherStation(String closestWeatherStation) {
		this.closestWeatherStation = closestWeatherStation;
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
	 * @return the closestTownDistance
	 */
	public double getClosestTownDistance() {
		return closestTownDistance;
	}

	/**
	 * @param closestTownDistance the closestTownDistance to set
	 */
	public void setClosestTownDistance(double closestTownDistance) {
		this.closestTownDistance = closestTownDistance;
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
	 * @return the maxAnnualPrecipitation
	 */
	public double getMaxAnnualPrecipitation() {
		return maxAnnualPrecipitation;
	}

	/**
	 * @param maxAnnualPrecipitation the maxAnnualPrecipitation to set
	 */
	public void setMaxAnnualPrecipitation(double maxAnnualPrecipitation) {
		this.maxAnnualPrecipitation = maxAnnualPrecipitation;
	}

	/**
	 * @return the maxMonthlyPrecipitation
	 */
	public double getMaxMonthlyPrecipitation() {
		return maxMonthlyPrecipitation;
	}

	/**
	 * @param maxMonthlyPrecipitation the maxMonthlyPrecipitation to set
	 */
	public void setMaxMonthlyPrecipitation(double maxMonthlyPrecipitation) {
		this.maxMonthlyPrecipitation = maxMonthlyPrecipitation;
	}

	/**
	 * @return the maxDailyPrecipitaion
	 */
	public double getMaxDailyPrecipitaion() {
		return maxDailyPrecipitaion;
	}

	/**
	 * @param maxDailyPrecipitaion the maxDailyPrecipitaion to set
	 */
	public void setMaxDailyPrecipitaion(double maxDailyPrecipitaion) {
		this.maxDailyPrecipitaion = maxDailyPrecipitaion;
	}

	/**
	 * @return the workShopValue
	 */
	public String getWorkShopValue() {
		return workShopValue;
	}

	/**
	 * @param workShopValue the workShopValue to set
	 */
	public void setWorkShopValue(String workShopValue) {
		this.workShopValue = workShopValue;
	}

	/**
	 * @return the warehouseValue
	 */
	public String getWarehouseValue(){
		return warehouseValue;
	}

	/**
	 * @param warehouseValue the warehouseValue to set
	 */
	public void setWarehouseValue(String warehouseValue) {
		this.warehouseValue = warehouseValue;
	}

	/**
	 * @return the conductionTunnelValue
	 */
	public String getConductionTunnelValue() {
		return conductionTunnelValue;
	}

	/**
	 * @param conductionTunnelValue the conductionTunnelValue to set
	 */
	public void setConductionTunnelValue(String conductionTunnelValue) {
		this.conductionTunnelValue = conductionTunnelValue;
	}

	/**
	 * @return the damBodyValue
	 */
	public String getDamBodyValue() {
		return damBodyValue;
	}

	/**
	 * @param damBodyValue the damBodyValue to set
	 */
	public void setDamBodyValue(String damBodyValue) {
		this.damBodyValue = damBodyValue;
	}

	/**
	 * @return the spillwayValue
	 */
	public String getSpillwayValue() {
		return spillwayValue;
	}

	/**
	 * @param spillwayValue the spillwayValue to set
	 */
	public void setSpillwayValue(String spillwayValue) {
		this.spillwayValue = spillwayValue;
	}

	/**
	 * @return the intakeWorksValue
	 */
	public String getIntakeWorksValue() {
		return intakeWorksValue;
	}

	/**
	 * @param intakeWorksValue the intakeWorksValue to set
	 */
	public void setIntakeWorksValue(String intakeWorksValue) {
		this.intakeWorksValue = intakeWorksValue;
	}

	/**
	 * @return the machineRoomsValue
	 */
	public String getMachineRoomsValue() {
		return machineRoomsValue;
	}

	/**
	 * @param machineRoomsValue the machineRoomsValue to set
	 */
	public void setMachineRoomsValue(String machineRoomsValue) {
		this.machineRoomsValue = machineRoomsValue;
	}

	/**
	 * @return the eMFacilitiesValue
	 */
	public String getEMFacilitiesValue() {
		return EMFacilitiesValue;
	}

	/**
	 * @param eMFacilitiesValue the eMFacilitiesValue to set
	 */
	public void setEMFacilitiesValue(String eMFacilitiesValue) {
		EMFacilitiesValue = eMFacilitiesValue;
	}

	/**
	 * @return the electricDuctsValue
	 */
	public String getElectricDuctsValue() {
		return electricDuctsValue;
	}

	/**
	 * @param electricDuctsValue the electricDuctsValue to set
	 */
	public void setElectricDuctsValue(String electricDuctsValue) {
		this.electricDuctsValue = electricDuctsValue;
	}

	/**
	 * @return the other1Value
	 */
	public double getOther1Value() {
		return other1Value;
	}

	/**
	 * @param other1Value the other1Value to set
	 */
	public void setOther1Value(double other1Value) {
		this.other1Value = other1Value;
	}

	/**
	 * @return the other2Value
	 */
	public double getOther2Value() {
		return other2Value;
	}

	/**
	 * @param other2Value the other2Value to set
	 */
	public void setOther2Value(double other2Value) {
		this.other2Value = other2Value;
	}

	/**
	 * @return the other3Value
	 */
	public double getOther3Value() {
		return other3Value;
	}

	/**
	 * @param other3Value the other3Value to set
	 */
	public void setOther3Value(double other3Value) {
		this.other3Value = other3Value;
	}

	/**
	 * @return the other1Description
	 */
	public String getOther1Description() {
		return other1Description;
	}

	/**
	 * @param other1Description the other1Description to set
	 */
	public void setOther1Description(String other1Description) {
		this.other1Description = other1Description;
	}

	/**
	 * @return the other2Description
	 */
	public String getOther2Description() {
		return other2Description;
	}

	/**
	 * @param other2Description the other2Description to set
	 */
	public void setOther2Description(String other2Description) {
		this.other2Description = other2Description;
	}

	/**
	 * @return the other3Desccription
	 */
	public String getOther3Desccription() {
		return other3Desccription;
	}

	/**
	 * @param other3Desccription the other3Desccription to set
	 */
	public void setOther3Desccription(String other3Desccription) {
		this.other3Desccription = other3Desccription;
	}

	/**
	 * @return the irrigationChannelLength
	 */
	public double getIrrigationChannelLength() {
		return irrigationChannelLength;
	}

	/**
	 * @param irrigationChannelLength the irrigationChannelLength to set
	 */
	public void setIrrigationChannelLength(double irrigationChannelLength) {
		this.irrigationChannelLength = irrigationChannelLength;
	}

	/**
	 * @return the irrigationChannelWidth
	 */
	public double getIrrigationChannelWidth() {
		return irrigationChannelWidth;
	}

	/**
	 * @param irrigationChannelWidth the irrigationChannelWidth to set
	 */
	public void setIrrigationChannelWidth(double irrigationChannelWidth) {
		this.irrigationChannelWidth = irrigationChannelWidth;
	}

	/**
	 * @return the irrigationChannelValue
	 */
	public String getIrrigationChannelValue() {
		return irrigationChannelValue;
	}

	/**
	 * @param irrigationChannelValue the irrigationChannelValue to set
	 */
	public void setIrrigationChannelValue(String irrigationChannelValue) {
		this.irrigationChannelValue = irrigationChannelValue;
	}

	/**
	 * @return the cofferDam
	 */
	public boolean isCofferDam() {
		return cofferDam;
	}

	/**
	 * @param cofferDam the cofferDam to set
	 */
	public void setCofferDam(boolean cofferDam) {
		this.cofferDam = cofferDam;
	}

	/**
	 * @return the deviationChannelCapacity
	 */
	public double getDeviationChannelCapacity() {
		return deviationChannelCapacity;
	}

	/**
	 * @param deviationChannelCapacity the deviationChannelCapacity to set
	 */
	public void setDeviationChannelCapacity(double deviationChannelCapacity) {
		this.deviationChannelCapacity = deviationChannelCapacity;
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
	 * @return the recontructionValue
	 */
	public String getRecontructionValue() {
		return recontructionValue;
	}

	/**
	 * @param recontructionValue the recontructionValue to set
	 */
	public void setRecontructionValue(String recontructionValue) {
		this.recontructionValue = recontructionValue;
	}

	/**
	 * @return the damID
	 */
	public String getDamID() {
		return damID;
	}

	/**
	 * @param damID the damID to set
	 */
	public void setDamID(String damID) {
		this.damID = damID;
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
	 * @return the damVentings
	 */
	public Vector<pojoVenting> getDamVentings() {
		return damVentings;
	}


	/**
	 * @param damVentings the damVentings to set
	 */
	public void setDamVentings(Vector<pojoVenting> damVentings) {
		this.damVentings = damVentings;
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
	 * @return the sprinkler
	 */
	public boolean isSprinkler() {
		return sprinkler;
	}


	/**
	 * @param sprinkler the sprinkler to set
	 */
	public void setSprinkler(boolean sprinkler) {
		this.sprinkler = sprinkler;
	}


	/**
	 * @return the hydrant
	 */
	public boolean isHydrant() {
		return hydrant;
	}


	/**
	 * @param hydrant the hydrant to set
	 */
	public void setHydrant(boolean hydrant) {
		this.hydrant = hydrant;
	}


	/**
	 * @return the tank
	 */
	public boolean isTank() {
		return tank;
	}


	/**
	 * @param tank the tank to set
	 */
	public void setTank(boolean tank) {
		this.tank = tank;
	}


	/**
	 * @return the extintor
	 */
	public boolean isExtintor() {
		return extintor;
	}


	/**
	 * @param extintor the extintor to set
	 */
	public void setExtintor(boolean extintor) {
		this.extintor = extintor;
	}


	/**
	 * @return the damUpStreamSlope
	 */
	public double getDamUpStreamSlope() {
		return damUpStreamSlope;
	}


	/**
	 * @param damUpStreamSlope the damUpStreamSlope to set
	 */
	public void setDamUpStreamSlope(double damUpStreamSlope) {
		this.damUpStreamSlope = damUpStreamSlope;
	}


	/**
	 * @return the damDownStreamSlope
	 */
	public double getDamDownStreamSlope() {
		return damDownStreamSlope;
	}


	/**
	 * @param damDownStreamSlope the damDownStreamSlope to set
	 */
	public void setDamDownStreamSlope(double damDownStreamSlope) {
		this.damDownStreamSlope = damDownStreamSlope;
	}


	/**
	 * @return the nAMINO
	 */
	public double getNAMINO() {
		return NAMINO;
	}


	/**
	 * @param nAMINO the nAMINO to set
	 */
	public void setNAMINO(double nAMINO) {
		NAMINO = nAMINO;
	}


	/**
	 * @return the damSpillWays
	 */
	public Vector<pojoDamSpillway> getDamSpillWays() {
		return damSpillWays;
	}


	/**
	 * @param damSpillWays the damSpillWays to set
	 */
	public void setDamSpillWays(Vector<pojoDamSpillway> damSpillWays) {
		this.damSpillWays = damSpillWays;
	}


	/**
	 * @return the damHydroFacility
	 */
	public Vector<pojoDamHydraulicFacility> getDamHydroFacility() {
		return damHydroFacility;
	}


	/**
	 * @param damHydroFacility the damHydroFacility to set
	 */
	public void setDamHydroFacility(
			Vector<pojoDamHydraulicFacility> damHydroFacility) {
		this.damHydroFacility = damHydroFacility;
	}


	/**
	 * @return the damOther
	 */
	public Vector<pojoOtherStructures> getDamOther() {
		return damOther;
	}


	/**
	 * @param damOther the damOther to set
	 */
	public void setDamOther(Vector<pojoOtherStructures> damOther) {
		this.damOther = damOther;
	}


	/**
	 * @return the damIrrigation
	 */
	public Vector<pojoIrrigationChannel> getDamIrrigation() {
		return damIrrigation;
	}


	/**
	 * @param damIrrigation the damIrrigation to set
	 */
	public void setDamIrrigation(Vector<pojoIrrigationChannel> damIrrigation) {
		this.damIrrigation = damIrrigation;
	}


	/**
	 * @return the cofferdamCapacity
	 */
	public String getCofferdamCapacity() {
		return cofferdamCapacity;
	}


	/**
	 * @param cofferdamCapacity the cofferdamCapacity to set
	 */
	public void setCofferdamCapacity(String cofferdamCapacity) {
		this.cofferdamCapacity = cofferdamCapacity;
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
