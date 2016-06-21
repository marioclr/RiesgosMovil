package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamDikes {
	private String dikeID;
	private String dikeNumber;
	private double dikeHeight;
	private double dikeBaseWidth;
	private double dikeFloodLevel;
	
	public pojoDamDikes(){
		dikeID = "";
		dikeNumber = "";
		dikeHeight = 0;
		dikeBaseWidth = 0;
		dikeBaseWidth = 0;
		dikeFloodLevel = 0;
	}

	/**
	 * @return the dikeNumber
	 */
	public String getDikeNumber() {
		return dikeNumber;
	}

	/**
	 * @param dikeNumber the dikeNumber to set
	 */
	public void setDikeNumber(String dikeNumber) {
		this.dikeNumber = dikeNumber;
	}

	/**
	 * @return the dikeHeight
	 */
	public double getDikeHeight() {
		return dikeHeight;
	}

	/**
	 * @param dikeHeight the dikeHeight to set
	 */
	public void setDikeHeight(double dikeHeight) {
		this.dikeHeight = dikeHeight;
	}

	/**
	 * @return the dikeBaseWidth
	 */
	public double getDikeBaseWidth() {
		return dikeBaseWidth;
	}

	/**
	 * @param dikeBaseWidth the dikeBaseWidth to set
	 */
	public void setDikeBaseWidth(double dikeBaseWidth) {
		this.dikeBaseWidth = dikeBaseWidth;
	}

	/**
	 * @return the dikeFloodLevel
	 */
	public double getDikeFloodLevel() {
		return dikeFloodLevel;
	}

	/**
	 * @param dikeFloodLevel the dikeFloodLevel to set
	 */
	public void setDikeFloodLevel(double dikeFloodLevel) {
		this.dikeFloodLevel = dikeFloodLevel;
	}

	/**
	 * @return the dikeID
	 */
	public String getDikeID() {
		return dikeID;
	}

	/**
	 * @param dikeID the dikeID to set
	 */
	public void setDikeID(String dikeID) {
		this.dikeID = dikeID;
	}
}
