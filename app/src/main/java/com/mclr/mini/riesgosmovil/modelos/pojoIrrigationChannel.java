package com.mclr.mini.riesgosmovil.modelos;

public class pojoIrrigationChannel {
	private String channelID;
	private String channelName;
	private double extension;
	private double flow;
	private String value;
	
	public pojoIrrigationChannel(){
		channelID = "";
		channelName = "";
		extension = 0.0;
		flow = 0.0;
		value = "0.0";
	}

	/**
	 * @return the channelID
	 */
	public String getChannelID() {
		return channelID;
	}

	/**
	 * @param channelID the channelID to set
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	/**
	 * @return the extension
	 */
	public double getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(double extension) {
		this.extension = extension;
	}

	/**
	 * @return the flow
	 */
	public double getFlow() {
		return flow;
	}

	/**
	 * @param flow the flow to set
	 */
	public void setFlow(double flow) {
		this.flow = flow;
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

	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
}
