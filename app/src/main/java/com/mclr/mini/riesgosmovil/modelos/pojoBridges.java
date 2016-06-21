package com.mclr.mini.riesgosmovil.modelos;

public class pojoBridges {
	private String bridgeID;
	private String bridgeType;
	private String bridgeTypeName;
	private String bridgeName;
	private boolean isMain;
	private double bridgeLength;
	private String renewalBridgeValue;
	
	public pojoBridges(){
		bridgeID = "";
		bridgeTypeName = "";
		bridgeType="";
		isMain=false;
		bridgeName="";
		bridgeLength=0;
		renewalBridgeValue = "0.0";
	}

	/**
	 * @return the bridgeType
	 */
	public String getBridgeType() {
		return bridgeType;
	}

	/**
	 * @param bridgeType the bridgeType to set
	 */
	public void setBridgeType(String bridgeType) {
		this.bridgeType = bridgeType;
	}

	/**
	 * @return the bridgeName
	 */
	public String getBridgeName() {
		return bridgeName;
	}

	/**
	 * @param bridgeName the bridgeName to set
	 */
	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}

	/**
	 * @return the isMain
	 */
	public boolean isMain() {
		return isMain;
	}

	/**
	 * @param isMain the isMain to set
	 */
	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	/**
	 * @return the bridgeLength
	 */
	public double getBridgeLength() {
		return bridgeLength;
	}

	/**
	 * @param bridgeLength the bridgeLength to set
	 */
	public void setBridgeLength(double bridgeLength) {
		this.bridgeLength = bridgeLength;
	}

	/**
	 * @return the renewalBridgeValue
	 */
	public String getRenewalBridgeValue() {
		return renewalBridgeValue;
	}

	/**
	 * @param renewalBridgeValue the renewalBridgeValue to set
	 */
	public void setRenewalBridgeValue(String renewalBridgeValue) {
		this.renewalBridgeValue = renewalBridgeValue;
	}

	/**
	 * @return the bridgeID
	 */
	public String getBridgeID() {
		return bridgeID;
	}

	/**
	 * @param bridgeID the bridgeID to set
	 */
	public void setBridgeID(String bridgeID) {
		this.bridgeID = bridgeID;
	}

	/**
	 * @return the bridgeTypeName
	 */
	public String getBridgeTypeName() {
		return bridgeTypeName;
	}

	/**
	 * @param bridgeTypeName the bridgeTypeName to set
	 */
	public void setBridgeTypeName(String bridgeTypeName) {
		this.bridgeTypeName = bridgeTypeName;
	}
	
}
