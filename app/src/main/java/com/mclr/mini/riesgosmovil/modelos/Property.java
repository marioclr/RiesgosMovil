package com.mclr.mini.riesgosmovil.modelos;

public class Property {
	private String propertyID;
	private String propertyType;
	private String propertyNumber;
	private String propertyUse;
	private String name;
	private String address;
	private int lock;
	private int assigned;
	private int finding;
	private int status;
	private String registerDate;
	private String registerUser;
	private int deleted;
	private String justification;
	private String propertyParentID;
	private String postalCodeID;
	private int buildingDate;
	private int levels;

	public Property(){
		
	}
	
	public Property(String p_propertyType,
					String p_propertyNumber,
					String p_propertyUse,
					String p_name,
					String p_address,
					int p_lock,
					int p_assigned,
					int p_finding,
					int p_status,
					String p_registerDate,
					String p_registerUser,
					int p_deleted,
					String p_justification,
					String p_propertyParentID,
					String p_postalCodeID,
					int p_buildingDate,
					int p_levels){
		setPropertyType(p_propertyType);
		setPropertyNumber(p_propertyNumber);
		setPropertyUse(p_propertyUse);
		setName(p_name);
		setAddress(p_address);
		setLock(p_lock);
		setAssigned(p_assigned);
		setFinding(p_finding);
		setStatus(p_status);
		setRegisterDate(p_registerDate);
		setRegisterUser(p_registerUser);
		setDeleted(p_deleted);
		setJustification(p_justification);
		setPropertyParentID(p_propertyParentID);
		setPostalCodeID(p_postalCodeID);
		setBuildingDate(p_buildingDate);
		setLevels(p_levels);
	}

	public Property(String p_propertyID,
					String p_propertyType,
					String p_propertyNumber,
					String p_propertyUse,
					String p_name,
					String p_address,
					int p_lock,
					int p_assigned,
					int p_finding,
					int p_status,
					String p_registerDate,
					String p_registerUser,
					int p_deleted,
					String p_justification,
					String p_propertyParentID,
					String p_postalCodeID,
					int p_buildingDate,
					int p_levels){
		setPropertyID(p_propertyID);
		setPropertyType(p_propertyType);
		setPropertyNumber(p_propertyNumber);
		setPropertyUse(p_propertyUse);
		setName(p_name);
		setAddress(p_address);
		setLock(p_lock);
		setAssigned(p_assigned);
		setFinding(p_finding);
		setStatus(p_status);
		setRegisterDate(p_registerDate);
		setRegisterUser(p_registerUser);
		setDeleted(p_deleted);
		setJustification(p_justification);
		setPropertyParentID(p_propertyParentID);
		setPostalCodeID(p_postalCodeID);
		setBuildingDate(p_buildingDate);
		setLevels(p_levels);
	}

	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyNumber() {
		return propertyNumber;
	}
	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}
	public String getPropertyUse() {
		return propertyUse;
	}
	public void setPropertyUse(String propertyUse) {
		this.propertyUse = propertyUse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLock() {
		return lock;
	}
	public void setLock(int lock) {
		this.lock = lock;
	}
	public int getAssigned() {
		return assigned;
	}
	public void setAssigned(int assigned) {
		this.assigned = assigned;
	}
	public int getFinding() {
		return finding;
	}
	public void setFinding(int finding) {
		this.finding = finding;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getPropertyParentID() {
		return propertyParentID;
	}
	public void setPropertyParentID(String propertyParentID) {
		this.propertyParentID = propertyParentID;
	}
	public String getPostalCodeID() {
		return postalCodeID;
	}
	public void setPostalCodeID(String postalCodeID) {
		this.postalCodeID = postalCodeID;
	}
	public int getBuildingDate() {
		return buildingDate;
	}
	public void setBuildingDate(int buildingDate) {
		this.buildingDate = buildingDate;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
}
