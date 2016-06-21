package com.mclr.mini.riesgosmovil.modelos;

public class pojoOtherStructures {
	private String otherID;
	private String concept;
	private String value;
	
	public pojoOtherStructures(){
		otherID = "";
		concept = "";
		value = "";
	}

	/**
	 * @return the otherID
	 */
	public String getOtherID() {
		return otherID;
	}

	/**
	 * @param otherID the otherID to set
	 */
	public void setOtherID(String otherID) {
		this.otherID = otherID;
	}

	/**
	 * @return the concept
	 */
	public String getConcept() {
		return concept;
	}

	/**
	 * @param concept the concept to set
	 */
	public void setConcept(String concept) {
		this.concept = concept;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
