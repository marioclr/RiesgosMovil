package com.mclr.mini.riesgosmovil.modelos;

public class pojoSinking {
	private String sinkingID;
	private double position;
	private int severity;
	
	public pojoSinking(){
		sinkingID = "";
		position = 0.0;
		severity = 0;
		
	}

	/**
	 * @return the position
	 */
	public double getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(double position) {
		this.position = position;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}

	/**
	 * @return the sinkingID
	 */
	public String getSinkingID() {
		return sinkingID;
	}

	/**
	 * @param sinkingID the sinkingID to set
	 */
	public void setSinkingID(String sinkingID) {
		this.sinkingID = sinkingID;
	}
}
