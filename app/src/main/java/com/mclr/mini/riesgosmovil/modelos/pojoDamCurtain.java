package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamCurtain {
	private String curtainID;
	private String curtainName;
	private String size;
	private String sizeDescription;
	private String behaivor;
	private String behaivorDescription;
	private String type;
	private String typeDescription;
	private String material;
	private String materialDescription;
	private double maxHeight;
	private double crownElevation;
	private double length;
	private double width;
	private String overwaterSlope;
	private String underwaterSlope;
	private double breastwallHeight;
	private double volumeBody;
	private double shimHeight;
	private String features;
	
	public pojoDamCurtain(){
		curtainID ="";
		curtainName="";
		size ="";
		sizeDescription="";
		behaivor ="";
		behaivorDescription="";
		type ="";
		typeDescription="";
		material ="";
		materialDescription="";
		maxHeight =0;
		crownElevation =0;
		length =0;
		width =0;
		overwaterSlope ="";
		underwaterSlope ="";
		breastwallHeight =0;
		volumeBody =0;
		shimHeight =0;
		features ="";
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the behaivor
	 */
	public String getBehaivor() {
		return behaivor;
	}

	/**
	 * @param behaivor the behaivor to set
	 */
	public void setBehaivor(String behaivor) {
		this.behaivor = behaivor;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the maxHeight
	 */
	public double getMaxHeight() {
		return maxHeight;
	}

	/**
	 * @param maxHeight the maxHeight to set
	 */
	public void setMaxHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}

	/**
	 * @return the crownElevation
	 */
	public double getCrownElevation() {
		return crownElevation;
	}

	/**
	 * @param crownElevation the crownElevation to set
	 */
	public void setCrownElevation(double crownElevation) {
		this.crownElevation = crownElevation;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @return the overwaterSlope
	 */
	public String getOverwaterSlope() {
		return overwaterSlope;
	}

	/**
	 * @param overwaterSlope the overwaterSlope to set
	 */
	public void setOverwaterSlope(String overwaterSlope) {
		this.overwaterSlope = overwaterSlope;
	}

	/**
	 * @return the underwaterSlope
	 */
	public String getUnderwaterSlope() {
		return underwaterSlope;
	}

	/**
	 * @param underwaterSlope the underwaterSlope to set
	 */
	public void setUnderwaterSlope(String underwaterSlope) {
		this.underwaterSlope = underwaterSlope;
	}

	/**
	 * @return the breastwallHeight
	 */
	public double getBreastwallHeight() {
		return breastwallHeight;
	}

	/**
	 * @param breastwallHeight the breastwallHeight to set
	 */
	public void setBreastwallHeight(double breastwallHeight) {
		this.breastwallHeight = breastwallHeight;
	}

	/**
	 * @return the volumeBody
	 */
	public double getVolumeBody() {
		return volumeBody;
	}

	/**
	 * @param volumeBody the volumeBody to set
	 */
	public void setVolumeBody(double volumeBody) {
		this.volumeBody = volumeBody;
	}

	/**
	 * @return the shimHeight
	 */
	public double getShimHeight() {
		return shimHeight;
	}

	/**
	 * @param shimHeight the shimHeight to set
	 */
	public void setShimHeight(double shimHeight) {
		this.shimHeight = shimHeight;
	}

	/**
	 * @return the features
	 */
	public String getFeatures() {
		return features;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(String features) {
		this.features = features;
	}

	/**
	 * @return the curtainID
	 */
	public String getCurtainID() {
		return curtainID;
	}

	/**
	 * @param curtainID the curtainID to set
	 */
	public void setCurtainID(String curtainID) {
		this.curtainID = curtainID;
	}

	/**
	 * @return the curtainName
	 */
	public String getCurtainName() {
		return curtainName;
	}

	/**
	 * @param curtainName the curtainName to set
	 */
	public void setCurtainName(String curtainName) {
		this.curtainName = curtainName;
	}

	/**
	 * @return the sizeDescription
	 */
	public String getSizeDescription() {
		return sizeDescription;
	}

	/**
	 * @param sizeDescription the sizeDescription to set
	 */
	public void setSizeDescription(String sizeDescription) {
		this.sizeDescription = sizeDescription;
	}

	/**
	 * @return the behaivorDescription
	 */
	public String getBehaivorDescription() {
		return behaivorDescription;
	}

	/**
	 * @param behaivorDescription the behaivorDescription to set
	 */
	public void setBehaivorDescription(String behaivorDescription) {
		this.behaivorDescription = behaivorDescription;
	}

	/**
	 * @return the typeDescription
	 */
	public String getTypeDescription() {
		return typeDescription;
	}

	/**
	 * @param typeDescription the typeDescription to set
	 */
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	/**
	 * @return the materialDescription
	 */
	public String getMaterialDescription() {
		return materialDescription;
	}

	/**
	 * @param materialDescription the materialDescription to set
	 */
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

}
