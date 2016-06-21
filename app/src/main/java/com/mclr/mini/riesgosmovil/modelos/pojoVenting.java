package com.mclr.mini.riesgosmovil.modelos;

public class pojoVenting {
	private String ventingID;
	private String ventingName;
	private String ventingPorpouse;
	private String ventingType;
	private double ventingCapacity;
	private double thresholdElevation;
	private double ventingDimension;
	private double ventingLength;
	private double ventingWidth;
	private String comments;
	
	public pojoVenting(){
		ventingID="";
		ventingName="";
		ventingPorpouse="";
		ventingType="";
		ventingCapacity=0;
		thresholdElevation=0;
		ventingLength=0;
		ventingWidth=0;
		ventingDimension = 0;
		comments="";
	}

	/**
	 * @return the ventingID
	 */
	public String getVentingName() {
		return ventingName;
	}

	/**
	 * @param ventingName the ventingName to set
	 */
	public void setVentingName(String ventingName) {
		this.ventingName = ventingName;
	}

	/**
	 * @return the ventingPorpouse
	 */
	public String getVentingPorpouse() {
		return ventingPorpouse;
	}

	/**
	 * @param ventingPorpouse the ventingPorpouse to set
	 */
	public void setVentingPorpouse(String ventingPorpouse) {
		this.ventingPorpouse = ventingPorpouse;
	}

	/**
	 * @return the ventingType
	 */
	public String getVentingType() {
		return ventingType;
	}

	/**
	 * @param ventingType the ventingType to set
	 */
	public void setVentingType(String ventingType) {
		this.ventingType = ventingType;
	}

	/**
	 * @return the ventingCapacity
	 */
	public double getVentingCapacity() {
		return ventingCapacity;
	}

	/**
	 * @param ventingCapacity the ventingCapacity to set
	 */
	public void setVentingCapacity(double ventingCapacity) {
		this.ventingCapacity = ventingCapacity;
	}

	/**
	 * @return the thresholdElevation
	 */
	public double getThresholdElevation() {
		return thresholdElevation;
	}

	/**
	 * @param thresholdElevation the thresholdElevation to set
	 */
	public void setThresholdElevation(double thresholdElevation) {
		this.thresholdElevation = thresholdElevation;
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
	 * @return the ventingLength
	 */
	public double getVentingLength() {
		return ventingLength;
	}

	/**
	 * @param ventingLength the ventingLength to set
	 */
	public void setVentingLength(double ventingLength) {
		this.ventingLength = ventingLength;
	}

	/**
	 * @return the ventingWidth
	 */
	public double getVentingWidth() {
		return ventingWidth;
	}

	/**
	 * @param ventingWidth the ventingWidth to set
	 */
	public void setVentingWidth(double ventingWidth) {
		this.ventingWidth = ventingWidth;
	}

	/**
	 * @return the ventingID
	 */
	public String getVentingID() {
		return ventingID;
	}
	
	/**
	 * @param ventingID the ventingID to set
	 */
	public void setVentingID(String ventingID) {
		this.ventingID = ventingID;
	}

	/**
	 * @return the ventingDimension
	 */
	public double getVentingDimension() {
		return ventingDimension;
	}

	/**
	 * @param ventingDimension the ventingDimension to set
	 */
	public void setVentingDimension(double ventingDimension) {
		this.ventingDimension = ventingDimension;
	}
	
}
