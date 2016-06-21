package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamHeadworks {
	private String headworkID;
	private String headworkNumber;
	private String headworkType;
	private String headworkTypeDescription;
	private double capacity;
	private double elevation;
	private int gatesNumber;
	private String gatesType;
	private String gatesTypeDescription;
	private double gatesWidth;
	private double gatesHeight;
	private int valvesNumber;
	private String valveType;
	private String valveTypeDescription;
	private int ductNumber;
	private String ductType;
	private String ductTypeDescription;
	private double ductDimension;
	private boolean gridExistence;
	private String comments;

	public pojoDamHeadworks(){
		headworkID = "";
		headworkNumber = "";
		headworkType = "";
		headworkTypeDescription = "";
		capacity = 0;
		elevation = 0;
		gatesNumber = 0;
		gatesType = "";
		gatesTypeDescription = "";
		gatesWidth = 0;
		gatesHeight = 0;
		valvesNumber = 0;
		valveType = "";
		valveTypeDescription = "";
		ductNumber = 0;
		ductType = "";
		ductTypeDescription = "";
		ductDimension=0;
		gridExistence = false;
		comments = "";
	}

	/**
	 * @return the headworkID
	 */
	public String getHeadworkID() {
		return headworkID;
	}

	/**
	 * @param headworkID the headworkID to set
	 */
	public void setHeadworkID(String headworkID) {
		this.headworkID = headworkID;
	}

	/**
	 * @return the headworkNumber
	 */
	public String getHeadworkNumber() {
		return headworkNumber;
	}

	/**
	 * @param headworkNumber the headworkNumber to set
	 */
	public void setHeadworkNumber(String headworkNumber) {
		this.headworkNumber = headworkNumber;
	}

	/**
	 * @return the headworkType
	 */
	public String getHeadworkType() {
		return headworkType;
	}

	/**
	 * @param headworkType the headworkType to set
	 */
	public void setHeadworkType(String headworkType) {
		this.headworkType = headworkType;
	}


	/**
	 * @return the headworkType
	 */
	public String getHeadworkTypeDescription() {
		return headworkTypeDescription;
	}

	/**
	 * @param headworkType the headworkType to set
	 */
	public void setHeadworkTypeDescription(String headworkTypeDescription) {
		this.headworkTypeDescription = headworkTypeDescription;
	}

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the elevation
	 */
	public double getElevation() {
		return elevation;
	}

	/**
	 * @param elevation the elevation to set
	 */
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the gatesNumber
	 */
	public int getGatesNumber() {
		return gatesNumber;
	}

	/**
	 * @param gatesNumber the gatesNumber to set
	 */
	public void setGatesNumber(int gatesNumber) {
		this.gatesNumber = gatesNumber;
	}

	/**
	 * @return the gatesType
	 */
	public String getGatesType() {
		return gatesType;
	}

	/**
	 * @param gatesType the gatesType to set
	 */
	public void setGatesType(String gatesType) {
		this.gatesType = gatesType;
	}

	/**
	 * @return the gatesType
	 */
	public String getGatesTypeDescription() {
		return gatesTypeDescription;
	}

	/**
	 * @param gatesType the gatesType to set
	 */
	public void setGatesTypeDescription(String gatesTypeDescription) {
		this.gatesTypeDescription = gatesTypeDescription;
	}

	/**
	 * @return the gatesWidth
	 */
	public double getGatesWidth() {
		return gatesWidth;
	}

	/**
	 * @param gatesWidth the gatesWidth to set
	 */
	public void setGatesWidth(double gatesWidth) {
		this.gatesWidth = gatesWidth;
	}

	/**
	 * @return the gatesHeight
	 */
	public double getGatesHeight() {
		return gatesHeight;
	}

	/**
	 * @param gatesHeight the gatesHeight to set
	 */
	public void setGatesHeight(double gatesHeight) {
		this.gatesHeight = gatesHeight;
	}

	/**
	 * @return the valvesNumber
	 */
	public int getValvesNumber() {
		return valvesNumber;
	}

	/**
	 * @param valvesNumber the valvesNumber to set
	 */
	public void setValvesNumber(int valvesNumber) {
		this.valvesNumber = valvesNumber;
	}

	/**
	 * @return the ductNumber
	 */
	public int getDuctNumber() {
		return ductNumber;
	}

	/**
	 * @param ductNumber the ductNumber to set
	 */
	public void setDuctNumber(int ductNumber) {
		this.ductNumber = ductNumber;
	}

	/**
	 * @return the ductType
	 */
	public String getDuctType() {
		return ductType;
	}

	/**
	 * @param ductType the ductType to set
	 */
	public void setDuctType(String ductType) {
		this.ductType = ductType;
	}

	/**
	 * @return the ductType
	 */
	public String getDuctTypeDescription() {
		return ductTypeDescription;
	}

	/**
	 * @param ductType the ductType to set
	 */
	public void setDuctTypeDescription(String ductTypeDescription) {
		this.ductTypeDescription = ductTypeDescription;
	}

	/**
	 * @return the ductLength
	 */
	public double getDuctDimension() {
		return ductDimension;
	}

	/**
	 * @param ductLength the ductLength to set
	 */
	public void setDuctDimension(double ductDimension) {
		this.ductDimension = ductDimension;
	}

	/**
	 * @return the gridExistence
	 */
	public boolean isGridExistence() {
		return gridExistence;
	}

	/**
	 * @param gridExistence the gridExistence to set
	 */
	public void setGridExistence(boolean gridExistence) {
		this.gridExistence = gridExistence;
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
	 * @return the valveType
	 */
	public String getValveType() {
		return valveType;
	}

	/**
	 * @param valveType the valveType to set
	 */
	public void setValveType(String valveType) {
		this.valveType = valveType;
	}

	/**
	 * @return the valveType
	 */
	public String getValveTypeDescription() {
		return valveTypeDescription;
	}

	/**
	 * @param valveType the valveType to set
	 */
	public void setValveTypeDescription(String valveTypeDescription) {
		this.valveTypeDescription = valveTypeDescription;
	}

}
