package com.mclr.mini.riesgosmovil.modelos;

public class pojoTunnel {
	private String tunnelID;
	private String tunnelType;
	private String tunnelTypeName;
	private String tunnelName;
	private double tunnelLength;
	private String renewalTunnelValue;
	
	public pojoTunnel(){
		tunnelID = "";
		tunnelTypeName = "";
		tunnelType="";
		tunnelName="";
		tunnelLength=0;
		renewalTunnelValue = "0.0";
	}

	/**
	 * @return the tunnelID
	 */
	public String getTunnelID() {
		return tunnelID;
	}

	/**
	 * @param tunnelID the tunnelID to set
	 */
	public void setTunnelID(String tunnelID) {
		this.tunnelID = tunnelID;
	}

	/**
	 * @return the tunnelType
	 */
	public String getTunnelType() {
		return tunnelType;
	}

	/**
	 * @param tunnelType the tunnelType to set
	 */
	public void setTunnelType(String tunnelType) {
		this.tunnelType = tunnelType;
	}

	/**
	 * @return the tunnelTypeName
	 */
	public String getTunnelTypeName() {
		return tunnelTypeName;
	}

	/**
	 * @param tunnelTypeName the tunnelTypeName to set
	 */
	public void setTunnelTypeName(String tunnelTypeName) {
		this.tunnelTypeName = tunnelTypeName;
	}

	/**
	 * @return the tunnelName
	 */
	public String getTunnelName() {
		return tunnelName;
	}

	/**
	 * @param tunnelName the tunnelName to set
	 */
	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}

	/**
	 * @return the tunnelLength
	 */
	public double getTunnelLength() {
		return tunnelLength;
	}

	/**
	 * @param tunnelLength the tunnelLength to set
	 */
	public void setTunnelLength(double tunnelLength) {
		this.tunnelLength = tunnelLength;
	}

	/**
	 * @return the renewalTunnelValue
	 */
	public String getRenewalTunnelValue() {
		return renewalTunnelValue;
	}

	/**
	 * @param renewalTunnelValue the renewalTunnelValue to set
	 */
	public void setRenewalTunnelValue(String renewalTunnelValue) {
		this.renewalTunnelValue = renewalTunnelValue;
	}

}
