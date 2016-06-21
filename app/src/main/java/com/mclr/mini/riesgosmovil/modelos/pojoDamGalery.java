package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamGalery {
	private String galeryID;
	private String galeryNumber;
	private String section;
	private double length;
	private double width;
	private double height;
	private double elevation;
	
	public pojoDamGalery(){
		galeryID = "";
		galeryNumber = "";
		section = "";
		length = 0;
		width  = 0;
		height = 0;
		elevation = 0;
	}

	/**
	 * @return the galeryNumber
	 */
	public String getGaleryNumber() {
		return galeryNumber;
	}

	/**
	 * @param galeryNumber the galeryNumber to set
	 */
	public void setGaleryNumber(String galeryNumber) {
		this.galeryNumber = galeryNumber;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
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
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
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
	 * @return the galeryID
	 */
	public String getGaleryID() {
		return galeryID;
	}

	/**
	 * @param galeryID the galeryID to set
	 */
	public void setGaleryID(String galeryID) {
		this.galeryID = galeryID;
	}
}
