package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamSpillway {

	private String spillWayID;
	private String spillNumber;
	private double capacity;
	private String spilltype;
	private String spilltypeDescription;
	private String operation;
	private Double spillLength;
	private Double CrestHeight;
	private Double CrestLength;
	private Double GatesNumber;
	private String gateType;
	private String gateTypeDescription;
	private Double gateHeight;
	private Double gateWidth;
	private String gateControl;
	private String maxFlow;
	private String elevationLSC;
	private String dissipativStr;
	private boolean spire;
	private Double spireHeight;
	private String comments;
	
	public pojoDamSpillway(){
		spillWayID = "";
		spillNumber = "";
		capacity =  0;
		spilltype = "";
		spilltypeDescription = "";
		operation = "";
		spillLength = 0.0;
		CrestHeight =  0.0;
		CrestLength = 0.0;
		GatesNumber =  0.0;
		gateType = "";
		gateTypeDescription = "";
		gateHeight =  0.0;
		gateWidth =  0.0;
		gateControl = "";
		maxFlow = "";
		elevationLSC = "";
		dissipativStr = "";
		spire = false;
		spireHeight =  0.0;
		comments = "";
	}

	/**
	 * @return the spillNumber
	 */
	public String getSpillNumber() {
		return spillNumber;
	}

	/**
	 * @param spillNumber the spillNumber to set
	 */
	public void setSpillNumber(String spillNumber) {
		this.spillNumber = spillNumber;
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
	 * @return the spilltype
	 */
	public String getSpilltype() {
		return spilltype;
	}

	/**
	 * @param spilltype the spilltype to set
	 */
	public void setSpilltype(String spilltype) {
		this.spilltype = spilltype;
	}

	/**
	 * @return the spilltypeDescription
	 */
	public String getSpilltypeDescription() {
		return spilltypeDescription;
	}

	/**
	 * @param spilltypeDescription the spilltype to set
	 */
	public void setSpilltypeDescription(String spilltypeDescription) {
		this.spilltypeDescription = spilltypeDescription;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the spillLength
	 */
	public Double getSpillLength() {
		return spillLength;
	}

	/**
	 * @param spillLength the spillLength to set
	 */
	public void setSpillLength(Double spillLength) {
		this.spillLength = spillLength;
	}

	/**
	 * @return the crestHeight
	 */
	public Double getCrestHeight() {
		return CrestHeight;
	}

	/**
	 * @param crestHeight the crestHeight to set
	 */
	public void setCrestHeight(Double crestHeight) {
		CrestHeight = crestHeight;
	}

	/**
	 * @return the gatesNumber
	 */
	public Double getGatesNumber() {
		return GatesNumber;
	}

	/**
	 * @param gatesNumber the gatesNumber to set
	 */
	public void setGatesNumber(Double gatesNumber) {
		GatesNumber = gatesNumber;
	}

	/**
	 * @return the gateType
	 */
	public String getGateType() {
		return gateType;
	}

	/**
	 * @param gateType the gateType to set
	 */
	public void setGateType(String gateType) {
		this.gateType = gateType;
	}

	/**
	 * @return the gateTypeDescription
	 */
	public String getGateTypeDescription() {
		return gateTypeDescription;
	}

	/**
	 * @param gateTypeDescription the gateType to set
	 */
	public void setGateTypeDescription(String gateTypeDescription) {
		this.gateTypeDescription = gateTypeDescription;
	}

	/**
	 * @return the gateHeight
	 */
	public Double getGateHeight() {
		return gateHeight;
	}

	/**
	 * @param gateHeight the gateHeight to set
	 */
	public void setGateHeight(Double gateHeight) {
		this.gateHeight = gateHeight;
	}

	/**
	 * @return the gateWidth
	 */
	public Double getGateWidth() {
		return gateWidth;
	}

	/**
	 * @param gateWidth the gateWidth to set
	 */
	public void setGateWidth(Double gateWidth) {
		this.gateWidth = gateWidth;
	}

	/**
	 * @return the gateControl
	 */
	public String getGateControl() {
		return gateControl;
	}

	/**
	 * @param gateControl the gateControl to set
	 */
	public void setGateControl(String gateControl) {
		this.gateControl = gateControl;
	}

	/**
	 * @return the maxFlow
	 */
	public String getMaxFlow() {
		return maxFlow;
	}

	/**
	 * @param maxFlow the maxFlow to set
	 */
	public void setMaxFlow(String maxFlow) {
		this.maxFlow = maxFlow;
	}

	/**
	 * @return the elevationLSC
	 */
	public String getElevationLSC() {
		return elevationLSC;
	}

	/**
	 * @param elevationLSC the elevationLSC to set
	 */
	public void setElevationLSC(String elevationLSC) {
		this.elevationLSC = elevationLSC;
	}

	/**
	 * @return the dissipativStr
	 */
	public String getDissipativStr() {
		return dissipativStr;
	}

	/**
	 * @param dissipativStr the dissipativStr to set
	 */
	public void setDissipativStr(String dissipativStr) {
		this.dissipativStr = dissipativStr;
	}

	/**
	 * @return the spire
	 */
	public boolean isSpire() {
		return spire;
	}

	/**
	 * @param spire the spire to set
	 */
	public void setSpire(boolean spire) {
		this.spire = spire;
	}

	/**
	 * @return the spireHeight
	 */
	public Double getSpireHeight() {
		return spireHeight;
	}

	/**
	 * @param spireHeight the spireHeight to set
	 */
	public void setSpireHeight(Double spireHeight) {
		this.spireHeight = spireHeight;
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
	 * @return the crestLength
	 */
	public Double getCrestLength() {
		return CrestLength;
	}

	/**
	 * @param crestLength the crestLength to set
	 */
	public void setCrestLength(Double crestLength) {
		CrestLength = crestLength;
	}

	/**
	 * @return the spillWayID
	 */
	public String getSpillWayID() {
		return spillWayID;
	}

	/**
	 * @param spillWayID the spillWayID to set
	 */
	public void setSpillWayID(String spillWayID) {
		this.spillWayID = spillWayID;
	}
}
