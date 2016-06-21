package com.mclr.mini.riesgosmovil.modelos;

public class pojoPostalCodes {

	String _id;
	String StateID;
	String PostalCode;
	String Township =  "";
	String TownshipKind;
	String CityHall;
	String City;

	public pojoPostalCodes(){
		_id = "";
		StateID = "";
		PostalCode = "";
		Township =  "";
		TownshipKind = "";
		CityHall = "";
		City = "";
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getStateID() {
		return StateID;
	}

	public void setStateID(String stateID) {
		StateID = stateID;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getTownship() {
		return Township;
	}

	public void setTownship(String township) {
		Township = township;
	}

	public String getTownshipKind() {
		return TownshipKind;
	}

	public void setTownshipKind(String townshipKind) {
		TownshipKind = townshipKind;
	}

	public String getCityHall() {
		return CityHall;
	}

	public void setCityHall(String cityHall) {
		CityHall = cityHall;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

}
