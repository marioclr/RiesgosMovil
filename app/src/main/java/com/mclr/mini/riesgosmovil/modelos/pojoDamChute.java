package com.mclr.mini.riesgosmovil.modelos;

public class pojoDamChute {
	private String chuteNumber;
	private double chuteCapacity;
	private String chuteType;
	private String chuteOperation;
	private double chuteCrestLength;
	private double chuteCrestheight;
	private double chuteGatesNumber;
	private String chuteGatestype;
	private double chuteGatesHeight;
	private double chuteGatesWidth;
	private String chuteGateControl;
	private double topFloodCurrent;
	private double LSCElevation;
	private String dissipativeStructure;
	private boolean waterPresence;
	private double waterHeight;
	private String comments;
	
	public pojoDamChute(){
		chuteNumber ="";
		chuteCapacity=0;
		chuteType ="";
		chuteOperation ="";
		chuteCrestLength=0;
		chuteCrestheight=0;
		chuteGatesNumber=0;
		chuteGatestype ="";
		chuteGatesHeight=0;
		chuteGatesWidth=0;
		chuteGateControl ="";
		topFloodCurrent=0;
		LSCElevation=0;
		dissipativeStructure ="";
		waterPresence=false;
		waterHeight=0;
		comments ="";
	}

	/**
	 * @return the chuteNumber
	 */
	public String getChuteNumber() {
		return chuteNumber;
	}

	/**
	 * @param chuteNumber the chuteNumber to set
	 */
	public void setChuteNumber(String chuteNumber) {
		this.chuteNumber = chuteNumber;
	}

	/**
	 * @return the chuteCapacity
	 */
	public double getChuteCapacity() {
		return chuteCapacity;
	}

	/**
	 * @param chuteCapacity the chuteCapacity to set
	 */
	public void setChuteCapacity(double chuteCapacity) {
		this.chuteCapacity = chuteCapacity;
	}

	/**
	 * @return the chuteType
	 */
	public String getChuteType() {
		return chuteType;
	}

	/**
	 * @param chuteType the chuteType to set
	 */
	public void setChuteType(String chuteType) {
		this.chuteType = chuteType;
	}

	/**
	 * @return the chuteOperation
	 */
	public String getChuteOperation() {
		return chuteOperation;
	}

	/**
	 * @param chuteOperation the chuteOperation to set
	 */
	public void setChuteOperation(String chuteOperation) {
		this.chuteOperation = chuteOperation;
	}

	/**
	 * @return the chuteCrestLength
	 */
	public double getChuteCrestLength() {
		return chuteCrestLength;
	}

	/**
	 * @param chuteCrestLength the chuteCrestLength to set
	 */
	public void setChuteCrestLength(double chuteCrestLength) {
		this.chuteCrestLength = chuteCrestLength;
	}

	/**
	 * @return the chuteCrestheight
	 */
	public double getChuteCrestheight() {
		return chuteCrestheight;
	}

	/**
	 * @param chuteCrestheight the chuteCrestheight to set
	 */
	public void setChuteCrestheight(double chuteCrestheight) {
		this.chuteCrestheight = chuteCrestheight;
	}

	/**
	 * @return the chuteGatesNumber
	 */
	public double getChuteGatesNumber() {
		return chuteGatesNumber;
	}

	/**
	 * @param chuteGatesNumber the chuteGatesNumber to set
	 */
	public void setChuteGatesNumber(double chuteGatesNumber) {
		this.chuteGatesNumber = chuteGatesNumber;
	}

	/**
	 * @return the chuteGatestype
	 */
	public String getChuteGatestype() {
		return chuteGatestype;
	}

	/**
	 * @param chuteGatestype the chuteGatestype to set
	 */
	public void setChuteGatestype(String chuteGatestype) {
		this.chuteGatestype = chuteGatestype;
	}

	/**
	 * @return the chuteGatesHeight
	 */
	public double getChuteGatesHeight() {
		return chuteGatesHeight;
	}

	/**
	 * @param chuteGatesHeight the chuteGatesHeight to set
	 */
	public void setChuteGatesHeight(double chuteGatesHeight) {
		this.chuteGatesHeight = chuteGatesHeight;
	}

	/**
	 * @return the chuteGatesWidth
	 */
	public double getChuteGatesWidth() {
		return chuteGatesWidth;
	}

	/**
	 * @param chuteGatesWidth the chuteGatesWidth to set
	 */
	public void setChuteGatesWidth(double chuteGatesWidth) {
		this.chuteGatesWidth = chuteGatesWidth;
	}

	/**
	 * @return the chuteGateControl
	 */
	public String getChuteGateControl() {
		return chuteGateControl;
	}

	/**
	 * @param chuteGateControl the chuteGateControl to set
	 */
	public void setChuteGateControl(String chuteGateControl) {
		this.chuteGateControl = chuteGateControl;
	}

	/**
	 * @return the topFloodCurrent
	 */
	public double getTopFloodCurrent() {
		return topFloodCurrent;
	}

	/**
	 * @param topFloodCurrent the topFloodCurrent to set
	 */
	public void setTopFloodCurrent(double topFloodCurrent) {
		this.topFloodCurrent = topFloodCurrent;
	}

	/**
	 * @return the lSCElevation
	 */
	public double getLSCElevation() {
		return LSCElevation;
	}

	/**
	 * @param lSCElevation the lSCElevation to set
	 */
	public void setLSCElevation(double lSCElevation) {
		LSCElevation = lSCElevation;
	}

	/**
	 * @return the dissipativeStructure
	 */
	public String getDissipativeStructure() {
		return dissipativeStructure;
	}

	/**
	 * @param dissipativeStructure the dissipativeStructure to set
	 */
	public void setDissipativeStructure(String dissipativeStructure) {
		this.dissipativeStructure = dissipativeStructure;
	}

	/**
	 * @return the waterPresence
	 */
	public boolean isWaterPresence() {
		return waterPresence;
	}

	/**
	 * @param waterPresence the waterPresence to set
	 */
	public void setWaterPresence(boolean waterPresence) {
		this.waterPresence = waterPresence;
	}

	/**
	 * @return the waterHeight
	 */
	public double getWaterHeight() {
		return waterHeight;
	}

	/**
	 * @param waterHeight the waterHeight to set
	 */
	public void setWaterHeight(double waterHeight) {
		this.waterHeight = waterHeight;
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
	
}
