package com.mclr.mini.riesgosmovil.modelos;

import android.content.Context;
import android.database.Cursor;

import com.mclr.mini.riesgosmovil.database.procedures.DatabaseProcedures;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;

import java.math.BigDecimal;
import java.util.Vector;

public class VWProperties {
	Context context;

	public VWProperties(Context p_context) {
		context = p_context;
	}

	public pojoAquaculture getAquaculturalPptyDetail(String propertyID, String postalCode){
		BigDecimal tmpValue;
		pojoAquaculture oAquaculturalItem = new pojoAquaculture();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		//DBProperties oDBProperties = new DBProperties();
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		Cursor cursor = null;
		//ResultSet rs = null;
		//CallableStatement cstmt = null;
		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oAquaculturalItem.setBuildingName(cursor.getString(cursor.getColumnIndex("Name")));
					oAquaculturalItem.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					oAquaculturalItem.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oAquaculturalItem.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oAquaculturalItem.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oAquaculturalItem.setState(cursor.getString(cursor.getColumnIndex("StateCode")));
					oAquaculturalItem.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oAquaculturalItem.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oAquaculturalItem.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oAquaculturalItem.setStatus(cursor.getInt(cursor.getColumnIndex("Status")));
					oAquaculturalItem.setFinding(cursor.getInt(cursor.getColumnIndex("Finding")));
					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							//switch (rs.getString("RecordID")){
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_BUILDING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlBuildingValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_BUILDING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnBuildingValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.BUILDINGDESC)) {
								oAquaculturalItem.setRnBuildingDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_REHAB_TANKS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlTankRehabValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_REHAB_TANKS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnTankRehabValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.TANKREHABDESC)) {
								oAquaculturalItem.setRnTankRehabDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_CANCEL)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlCancelValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_CANCEL)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnCancelValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CANCELDESC)) {
								oAquaculturalItem.setRnCancelDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MESH)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlMeshValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_MESH)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnMeshValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.MESHDESC)) {
								oAquaculturalItem.setRnCancelDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_PAINTING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlPaintingValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_PAINTING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnPaintingValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.PAINTINGDESC)) {
								oAquaculturalItem.setRnPaintingDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ROOFS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlRoofsValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_ROOFS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnRoofsValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ROOFSDESC)) {
								oAquaculturalItem.setRnRoofsDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_FEED_NETWARE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlFeedNetCDValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_FEED_NETWARE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnFeedNetCDValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.FEEDNETDESC)) {
								oAquaculturalItem.setRnFeedNetCDDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_CONDUCTION_NETWARE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlWaterNetCDValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_CONDUCTION_NETWARE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnWaterNetCDValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CONDUCTIONNETDESC)) {
								oAquaculturalItem.setRnWaterNetCDDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_EM_NET_CONDUCTION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlElectricNetCDValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_EM_NET_CONDUCTION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnElectricNetCDValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELECTRICNETCDDESC)) {
								oAquaculturalItem.setRnElectricNetCDDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_SANIARY_NET_SANITATION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlSanitaryNetValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_SANIARY_NET_SANITATION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnSanitaryNetValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SANITARYNETDESC)) {
								oAquaculturalItem.setSanitaryNetDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_SANITARY_INSTALATION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlSanitaryIntallValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_SANITARY_INSTALATION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnSanitaryIntallValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SANITARYINSTALDESC)) {
								oAquaculturalItem.setSanitaryInstalDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_EM_SANITATION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRlSanitaryEMNetValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_EM_SANITATION)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oAquaculturalItem.setRnSanitaryEMNetValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.EMNETDESC)) {
								oAquaculturalItem.setEMNetDescription(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								oAquaculturalItem.setLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								oAquaculturalItem.setLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE)) {
								oAquaculturalItem.setAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPECIALFACILITIES)) {
								oAquaculturalItem.setSpecialFacilities(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.AQUACULTURE_CENTER_TYPE)) {
								oAquaculturalItem.setCenterType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE)) {
								oAquaculturalItem.setStructureType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FOUNDATION_TYPE)) {
								oAquaculturalItem.setFoundationType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WALL_TYPE)) {
								oAquaculturalItem.setWallsType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.BLACKSMITH_TYPE)) {
								oAquaculturalItem.setBlackSmithType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.GLASSWARE_TYPE)) {
								oAquaculturalItem.setGlasseryType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FACHADE_TYPE)) {
								oAquaculturalItem.setFachadeType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
						} while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						
						//DateOperations oDateOperations = new DateOperations();
						curYear = DateOperations.getCurrentYear();
						//cursor.next();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
									siniestralityAmount[i] = "0.0";
									siniestralityDescr[i] = "";
								}else{
									siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								//throw new Exception(e);
								siniestralityAmount[i] = "0.0";
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = "";
							siniestralityDescr[i] = "";
						}
					}
					oAquaculturalItem.setSiniestrality(siniestralityAmount);
					oAquaculturalItem.setSiniestralityDesc(siniestralityDescr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oAquaculturalItem;
	}

	public pojoDams getDamPptyDetail(String propertyID, String postalCode){
		pojoDams oItemDam = new pojoDams();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		String strTmp="";
		Vector<pojoDamCurtain> oVectorCurtains = new Vector<pojoDamCurtain>();
		Vector<pojoDamGalery> oVectorGaleries = new Vector<pojoDamGalery>();
		Vector<pojoDamDikes> oVectorDikes = new Vector<pojoDamDikes>();
		Vector<pojoDamSpillway> oVectorSpills = new Vector<pojoDamSpillway>();
		Vector<pojoDamHydraulicFacility> oVectorHydroFac = new Vector<pojoDamHydraulicFacility>();
		Vector<pojoVenting> oVectorVentings = new Vector<pojoVenting>();
		Vector<pojoOtherStructures> oVectorOther = new Vector<pojoOtherStructures>();
		Vector<pojoIrrigationChannel> oVectorIChannel = new Vector<pojoIrrigationChannel>();
		Vector<pojoDamHeadworks> oVectorHeadworks = new Vector<pojoDamHeadworks>();
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		Cursor cursor = null;

		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){

					oItemDam.setStateID(cursor.getString(cursor.getColumnIndex("StateCode")));
					oItemDam.setDamID(cursor.getString(cursor.getColumnIndex("PropertyNumber")));
					oItemDam.setOfficialNumber(cursor.getString(cursor.getColumnIndex("Name")));
					oItemDam.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oItemDam.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oItemDam.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oItemDam.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					oItemDam.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oItemDam.setState(cursor.getString(cursor.getColumnIndex("StateCode")));
					oItemDam.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oItemDam.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oItemDam.setConstructionDate(cursor.getString(cursor.getColumnIndex("RegisterDate")));
					oItemDam.setReinforcementDate(cursor.getString(cursor.getColumnIndex("BuildingDate")));
					oItemDam.setStatus(cursor.getInt(cursor.getColumnIndex("Status")));
					oItemDam.setFinding(cursor.getInt(cursor.getColumnIndex("Finding")));

					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								oItemDam.setLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								oItemDam.setLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE)) {
								oItemDam.setAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.COMUN_NAME)) {
								oItemDam.setDamName(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CNA_REGION)) {
								oItemDam.setCNARegion(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HIDROLOGIC_ZONE)) {
								oItemDam.setHidrologicRegion(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CATCHMENT_AREA)) {
								oItemDam.setPumb(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.FLOW)) {
								oItemDam.setCurrent(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.TRIBUTARY)) {
								oItemDam.setAfluent(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DESIGNER)) {
								oItemDam.setDeseigner(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CONSTRUCTOR)) {
								oItemDam.setConstructor(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RESPONSIBLE)) {
								oItemDam.setResponsibleOrg(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SURFACE)) {
								oItemDam.setSurface(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ANUAL_MAX_RUNOFF_VOL)) {
								oItemDam.setMaxAnnualRunoff(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ANUAL_AVG_RUNOFF_VOL)) {
								oItemDam.setAvgAnnualRunoff(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ANUAL_PRECIPITATION)) {
								oItemDam.setMaxAnnualPrecipitation(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.MONTHLY_PRECIPITATION)) {
								oItemDam.setMaxMonthlyPrecipitation(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAILY_PRECIPITATION)) {
								oItemDam.setMaxDailyPrecipitaion(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELEVATION_FLAT_IRREGULARITY)) {
								oItemDam.setElevation(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.NAME)) {
								oItemDam.setNAME(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.NAMO)) {
								oItemDam.setNAMO(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.NAMINO)) {
								oItemDam.setNAMINO(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.NAME_VOL)) {
								oItemDam.setNAMEVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.NAMO_VOL)) {
								oItemDam.setNAMOVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.NAMINO_VOL)) {
								oItemDam.setNAMINOVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SEDIMENTATION_VOL)) {
								oItemDam.setSedimentationVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.USEFUL_VOL)) {
								oItemDam.setUsefulVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.OVER_STORAGE)) {
								oItemDam.setSuperStorage(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CONSERVATION_VOL)) {
								oItemDam.setConservationVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CURRENT_CONTROL_VOL)) {
								oItemDam.setFloodControlVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DESIGN_MAX_FLOW)) {
								oItemDam.setDeseignMaxCurrent(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RETURN_PERIOD)) {
								oItemDam.setReturnPeriod(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DESIGN_FLOW_VOL)) {
								oItemDam.setDeseignFloodVolume(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.MAX_FLOW_VOL_REGISTERED)) {
								oItemDam.setMaxCurrentRegistered(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.MAX_FLOW_REGISTERED)) {
								oItemDam.setMaxFloodRegistered(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.FLOW_CAPACITY)) {
								oItemDam.setWaterDownChannelCapacity(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPECIAL_OP_INSTRUCTIONS)) {
								oItemDam.setSpecialOperationInstructions(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.COMMENTS)) {
								oItemDam.setComments(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_WORKSHOP)) {
								oItemDam.setWorkShopValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_WAREHOUSE)) {
								oItemDam.setWarehouseValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_CONDUTION_DUCTS)) {
								oItemDam.setConductionTunnelValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_DAM_BODY)) {
								oItemDam.setDamBodyValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_SPILLWAY)) {
								oItemDam.setSpillwayValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_INOUT_WORKS)) {
								oItemDam.setIntakeWorksValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MACHINEROOM)) {
								oItemDam.setMachineRoomsValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_EM_INSTALATION)) {
								oItemDam.setEMFacilitiesValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ELECTRICAL_DUCTS)) {
								oItemDam.setElectricDuctsValue(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.REGULAR_GEOMETRY)) {
								oItemDam.setGeometryRegular(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.IRREGULAR_GEOMETRY)) {
								oItemDam.setGeometryIrregular(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.IRREGULAR_DETAIL)) {
								oItemDam.setGeometySpecitication(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SINKING_SEVERITY)) {
								oItemDam.setSinkingSeverity(cursor.getInt(cursor.getColumnIndex("Value")));
								oItemDam.setSinkingPresence(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.EXTERNAL_STRUCTURE_POUNDING)) {
								oItemDam.setPounding(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SHORT_WALLS_BETWEEN_COLUMNS)) {
								oItemDam.setShortWallsbeneathColumns(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPRINKLERS)) {
								oItemDam.setSprinkler(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HYDRANTS)) {
								oItemDam.setHydrant(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.TANK)) {
								oItemDam.setTank(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.EXTINTORES)) {
								oItemDam.setExtintor(true);
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_LENGTH)) {
								oItemDam.setDamLength(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_HEIGHT)) {
								oItemDam.setDamHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_BASE_WIDTH)) {
								oItemDam.setBaseWidth(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_CROWN_WIDTH)) {
								oItemDam.setCrownWidth(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_UPSTREAM_SLOPE)) {
								oItemDam.setDamUpStreamSlope(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_DOWNSTREAM_SLOPE)) {
								oItemDam.setDamDownStreamSlope(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.INEGI_LETTER)) {
								oItemDam.setINEGILetter(cursor.getString(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_FLOW_LEVEL)) {
								oItemDam.setCurrentLevels(cursor.getDouble(cursor.getColumnIndex("Value")));
							} else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DAM_FLOW_QTTY)) {
								oItemDam.setCurrentWaterQtty(cursor.getDouble(cursor.getColumnIndex("Value")));
							}

							else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SEISMICITY)) {
								oItemDam.setSismicity(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.ACCESS_ROUTES)) {
								oItemDam.setAccessWay(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CONSTRUCTION_PORPUSE)) {
								oItemDam.setContructionPurpouse(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WATER_USE)) {
								oItemDam.setWaterUser(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WATERBODIES)) {
								oItemDam.setClosesWaterBody(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SOIL_DAM_TYPE)) {
								oItemDam.setSoilType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE)) {
								oItemDam.setStructuralType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DAM_FOUNDATION_TYPE)) {
								oItemDam.setFoundationType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FLOW_TYPE)) {
								oItemDam.setCurrentsTypes(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.UNDERGROUND_TYPE)) {
								oItemDam.setSubSoilType(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CONDITION)) {
								oItemDam.setCondition(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SPLLWAY_TYPE_LOCATION)) {
								oItemDam.setDumpLocation(cursor.getString(cursor.getColumnIndex("RecordID")));
							} else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.MACHINE_ROOM_LOCATIION)) {
								oItemDam.setMachineRoomeLocation(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
						} while (cursor.moveToNext());
					}
					
					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE)) {
								if (cursor.getString(cursor.getColumnIndex("DataType")).compareTo("D")==0){
									oItemDam.setEarthQuakeFormer(true);
								}else{
									oItemDam.setEarthQuakeRisk(true);
								}
							}
							else if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD)) {
								if (cursor.getString(cursor.getColumnIndex("DataType")).compareTo("D")==0){
									oItemDam.setFleedFormer(true);
								}else{
									oItemDam.setFleedRisk(true);
								}
							}
							else if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE)) {
								if (cursor.getString(cursor.getColumnIndex("DataType")).compareTo("D")==0){
									oItemDam.setHurricaneFormer(true);
								}else{
									oItemDam.setHurricaneRisk(true);
								}
							}
							else if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE)) {
								if (cursor.getString(cursor.getColumnIndex("DataType")).compareTo("D")==0){
									oItemDam.setCollapseFormer(true);
								}else{
									oItemDam.setCollapseRisk(true);
								}
							}
							else if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE)) {
								if (cursor.getString(cursor.getColumnIndex("DataType")).compareTo("D")==0){
									oItemDam.setLandSlideFormer(true);
								}else{
									oItemDam.setLandSlideRisk(true);
								}
							}
						} while (cursor.moveToNext());
					}
					
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();

					if (cursor.getCount() > 0) {
						int curYear;
						
						//DateOperations oDateOperations = new DateOperations();
						curYear = DateOperations.getCurrentYear();
						//cursor.next();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
									siniestralityAmount[i] = "0.0";
									siniestralityDescr[i] = "";
								}else{
									//cursor.getLong(columnIndex);
									//cursor.getFloat(columnIndex);
									siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								//throw new Exception(e);
								siniestralityAmount[i] = "0.0";
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = "";
							siniestralityDescr[i] = "";
						}
					}
					oItemDam.setSiniestralityValue(siniestralityAmount);
					oItemDam.setSiniestralityDescription(siniestralityDescr);

					cursor = dbProcedures.getPropertyChildren(propertyID, Constants.CURTAIN_STRUCTURE);
					pojoDamCurtain oItemCurtain = new pojoDamCurtain();
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemCurtain.setCurtainID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						oItemCurtain.setCurtainName(cursor.getString(cursor.getColumnIndex("Name")));
						do {
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemCurtain.getCurtainName())!=0){
								
								oVectorCurtains.add(oItemCurtain);
								oItemCurtain = new pojoDamCurtain();
								oItemCurtain.setCurtainID(cursor.getString(cursor.getColumnIndex("PropertyID")));
								oItemCurtain.setCurtainName(cursor.getString(cursor.getColumnIndex("Name")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DAM_TYPE)) {
								oItemCurtain.setType(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemCurtain.setTypeDescription(cursor.getString(cursor.getColumnIndex("Description")));
							}
							else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DAM_BEHAVIOR)) {
								oItemCurtain.setBehaivor(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemCurtain.setBehaivorDescription(cursor.getString(cursor.getColumnIndex("Description")));
							}
							else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DAM_MATERIAL)) {
								oItemCurtain.setMaterial(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemCurtain.setMaterialDescription(cursor.getString(cursor.getColumnIndex("Description")));
							}
							else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DAM_SIZE)) {
								oItemCurtain.setSize(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemCurtain.setSizeDescription(cursor.getString(cursor.getColumnIndex("Description")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.MAX_HEIGHT)) {
								oItemCurtain.setMaxHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CROWN_ELEVATION)) {
								oItemCurtain.setCrownElevation(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LENGHT)) {
								oItemCurtain.setLength(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WEIGHT)) {
								oItemCurtain.setWidth(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WATER_UP_SLOPE)) {
								oItemCurtain.setOverwaterSlope(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WATER_DOWN_SLOPE)) {
								oItemCurtain.setUnderwaterSlope(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.BREAST_WALL_HEIGHT)) {
								oItemCurtain.setBreastwallHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.BODY_VOLUME)) {
								oItemCurtain.setVolumeBody(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HEIGHT_OVER_SHIM)) {
								oItemCurtain.setShimHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.OTHER_CURTAIN_FEATURES)) {
								oItemCurtain.setFeatures(cursor.getString(cursor.getColumnIndex("Value")));
							}
						} while (cursor.moveToNext());
						oVectorCurtains.add(oItemCurtain);
						oItemDam.setCurtainFeatures(oVectorCurtains);
					}

					cursor = dbProcedures.getPropertyChildren(propertyID, 
							Constants.GALERY_STRUCTURE);
					pojoDamGalery oItemGalery = new pojoDamGalery();
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemGalery.setGaleryNumber(cursor.getString(cursor.getColumnIndex("Name")));
						oItemGalery.setGaleryID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						
						do{
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemGalery.getGaleryNumber())!=0){
								oVectorGaleries.add(oItemGalery);
								oItemGalery = new pojoDamGalery();
								oItemGalery.setGaleryNumber(cursor.getString(cursor.getColumnIndex("Name")));
								oItemGalery.setGaleryID(cursor.getString(cursor.getColumnIndex("PropertyID")));
							}

							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.GALERY_SECTION)) {
								oItemGalery.setSection(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LENGHT)) {
								oItemGalery.setLength(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WEIGHT)) {
								oItemGalery.setWidth(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HEIGHT)) {
								oItemGalery.setHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELEVATION)) {
								oItemGalery.setElevation(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
						} while (cursor.moveToNext());
						oVectorGaleries.add(oItemGalery);
						oItemDam.setDamGaleries(oVectorGaleries);
					}
					
					cursor = dbProcedures.getPropertyChildren(propertyID, 
							Constants.VENTING_STRUCTURE);
					pojoVenting oItemVenting = new pojoVenting();
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemVenting.setVentingName(cursor.getString(cursor.getColumnIndex("Name")));
						oItemVenting.setVentingID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						oItemVenting.setVentingPorpouse(cursor.getString(cursor.getColumnIndex("PropertyUse")));
						
						do{
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemVenting.getVentingName())!=0){
								oVectorVentings.add(oItemVenting);
								oItemVenting = new pojoVenting();
								oItemVenting.setVentingName(cursor.getString(cursor.getColumnIndex("Name")));
								oItemVenting.setVentingID(cursor.getString(cursor.getColumnIndex("PropertyID")));
								oItemVenting.setVentingPorpouse(cursor.getString(cursor.getColumnIndex("PropertyUse")));
							}
							
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.FLOW_CAPACITY)) {
								oItemVenting.setVentingCapacity(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELEVATION)) {
								oItemVenting.setThresholdElevation(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LENGHT)) {
								oItemVenting.setVentingDimension(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.VENTING_TYPE_STR)) {
								oItemVenting.setVentingType(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.COMMENTS)) {
								oItemVenting.setComments(cursor.getString(cursor.getColumnIndex("Value")));
							}
						} while (cursor.moveToNext());
						oVectorVentings.add(oItemVenting);
						oItemDam.setDamVentings(oVectorVentings);
					}
					
					cursor = dbProcedures.getPropertyChildren(propertyID, 
							Constants.DIKE_STRUCTURE);
					pojoDamDikes oItemDike = new pojoDamDikes();
					
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemDike.setDikeNumber(cursor.getString(cursor.getColumnIndex("Name")));
						oItemDike.setDikeID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						
						do{
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemDike.getDikeNumber())!=0){
								oVectorDikes.add(oItemDike);
								oItemDike = new pojoDamDikes();
								oItemDike.setDikeNumber(cursor.getString(cursor.getColumnIndex("Name")));
								oItemDike.setDikeID(cursor.getString(cursor.getColumnIndex("PropertyID")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HEIGHT)) {
								oItemDike.setDikeHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.BASE_WIDTH)) {
								oItemDike.setDikeBaseWidth(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.FLOOD_LEVEL)) {
								oItemDike.setDikeFloodLevel(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
						} while (cursor.moveToNext());
						oVectorDikes.add(oItemDike);
						oItemDam.setDamDikes(oVectorDikes);							
					}
					
					cursor = dbProcedures.getPropertyChildren(propertyID, Constants.REINFORCE_STRUCTURE);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						if (strTmp.length() == 0)
							strTmp = cursor.getString(cursor.getColumnIndex("Value"));
						else
							strTmp += "," + cursor.getString(cursor.getColumnIndex("Value"));
					}
					//oItemDam.setReinforcementDate(strTmp); //TODO
					
					cursor = dbProcedures.getPropertyChildren(propertyID, 
							Constants.CHUTE_STRUCTURE);
					pojoDamSpillway oItemSpill = new pojoDamSpillway();

					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemSpill.setSpillWayID(cursor.getString(cursor.getColumnIndex("PropertyID"))); 
						oItemSpill.setSpillNumber(cursor.getString(cursor.getColumnIndex("Name")));
						do{
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemSpill.getSpillNumber())!=0){
								oVectorSpills.add(oItemSpill);
								oItemSpill = new pojoDamSpillway();
								oItemSpill.setSpillNumber(cursor.getString(cursor.getColumnIndex("Name")));
								oItemSpill.setSpillWayID(cursor.getString(cursor.getColumnIndex("PropertyID")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_CAPACITY)) {
								oItemSpill.setCapacity(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_OP)) {
								oItemSpill.setOperation(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_CREST_HEIGHT)) {
								oItemSpill.setCrestHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_CREST_LENGTH)) {
								oItemSpill.setCrestLength(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_GATES_NUMBER)) {
								oItemSpill.setGatesNumber(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_GATE_HEIGHT)) {
								oItemSpill.setGateHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_GATE_CONTROL)) {
								oItemSpill.setGateControl(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_MAX_FLOW)) {
								oItemSpill.setMaxFlow(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELEVATION_LSC)) {
								oItemSpill.setElevationLSC(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DISSIPATIVE_STRUCTURE)) {
								oItemSpill.setDissipativStr(cursor.getString(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPILL_SPIRE)) {
								oItemSpill.setSpire(true);
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPIRE_HEIGHT)) {
								oItemSpill.setSpireHeight(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							else if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.COMMENTS)) {
								oItemSpill.setComments(cursor.getString(cursor.getColumnIndex("Value")));
							}

							else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SPILL_TYPE)) {
								oItemSpill.setSpilltype(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemSpill.setSpilltypeDescription(cursor.getString(cursor.getColumnIndex("Description")));
							}
							else if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SPILL_TYPE)) {
								oItemSpill.setGateType(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemSpill.setGateTypeDescription(cursor.getString(cursor.getColumnIndex("Description")));
							}
						} while (cursor.moveToNext());
						oVectorSpills.add(oItemSpill);
						oItemDam.setDamSpillWays(oVectorSpills);	
					}
					
					Cursor rsHydroFac = dbProcedures.getPropertyChildren(propertyID, 
							Constants.HYDRAULIC_FACILITIES);
					pojoDamHydraulicFacility oItemHydroFacility = new pojoDamHydraulicFacility();

					rsHydroFac.moveToFirst();
					if (rsHydroFac.getCount() > 0){
						oItemHydroFacility.setFacilityName(rsHydroFac.getString(rsHydroFac.getColumnIndex("Name")));
						oItemHydroFacility.setFacilityID(rsHydroFac.getString(rsHydroFac.getColumnIndex("PropertyID")));
						
						do{
							if (rsHydroFac.getString(rsHydroFac.getColumnIndex("Name")).compareTo(oItemHydroFacility.getFacilityName())!=0){
								oVectorHydroFac.add(oItemHydroFacility);
								oItemHydroFacility = new pojoDamHydraulicFacility();
								oItemHydroFacility.setFacilityName(rsHydroFac.getString(rsHydroFac.getColumnIndex("Name")));
								oItemHydroFacility.setFacilityID(rsHydroFac.getString(rsHydroFac.getColumnIndex("PropertyID")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								oItemHydroFacility.setLongitude(cursor.getDouble(rsHydroFac.getColumnIndex("Value")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								oItemHydroFacility.setLatitude(cursor.getDouble(rsHydroFac.getColumnIndex("Value")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")).equals(Constants.CLOSEST_TOWN_WATERDOWN)) {
								oItemHydroFacility.setClosestCity(cursor.getDouble(rsHydroFac.getColumnIndex("Value")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")).equals(Constants.CLOSEST_WEATHER_STATION)) {
								oItemHydroFacility.setClosestWeatherSt(rsHydroFac.getString(rsHydroFac.getColumnIndex("Value")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("CatalogID")).equals(Constants.DAM_TYPE)) {
								oItemHydroFacility.setDamType(rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")));
								oItemHydroFacility.setDamTypeDescription(rsHydroFac.getString(rsHydroFac.getColumnIndex("Description")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("CatalogID")).equals(Constants.DAM_USE)) {
								oItemHydroFacility.setDamUse(rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")));
								oItemHydroFacility.setDamUseDescription(rsHydroFac.getString(rsHydroFac.getColumnIndex("Description")));
							}
							else if (rsHydroFac.getString(rsHydroFac.getColumnIndex("CatalogID")).equals(Constants.CATCHMENT_TYPE)) {
								oItemHydroFacility.setCatchment(rsHydroFac.getString(rsHydroFac.getColumnIndex("RecordID")));
								oItemHydroFacility.setCatchmentDescription(rsHydroFac.getString(rsHydroFac.getColumnIndex("Description")));
							}
						} while (rsHydroFac.moveToNext());
						oVectorHydroFac.add(oItemHydroFacility);
						oItemDam.setDamHydroFacility(oVectorHydroFac);							
					}
					
					Cursor rsHeadworks = dbProcedures.getPropertyChildren(propertyID, 
							Constants.INTAKE_STRUCTURE);
					pojoDamHeadworks oItemHeadworks = new pojoDamHeadworks();
					
					rsHeadworks.moveToFirst();
					if (rsHeadworks.getCount() > 0){
						oItemHeadworks.setHeadworkNumber(rsHeadworks.getString(rsHeadworks.getColumnIndex("Name")));
						oItemHeadworks.setHeadworkID(rsHeadworks.getString(rsHeadworks.getColumnIndex("PropertyID")));
						
						do{
							if (rsHeadworks.getString(rsHeadworks.getColumnIndex("Name")).compareTo(oItemHeadworks.getHeadworkNumber())!=0){
								oVectorHeadworks.add(oItemHeadworks);
								oItemHeadworks = new pojoDamHeadworks();
								oItemHeadworks.setHeadworkNumber(rsHeadworks.getString(rsHeadworks.getColumnIndex("Name")));
								oItemHeadworks.setHeadworkID(rsHeadworks.getString(rsHeadworks.getColumnIndex("PropertyID")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.FLOW_CAPACITY)) {
								oItemHeadworks.setCapacity(rsHeadworks.getDouble(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.ELEVATION)) {
								oItemHeadworks.setElevation(rsHeadworks.getDouble(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.SPILL_GATES_NUMBER)) {
								oItemHeadworks.setGatesNumber(rsHeadworks.getInt(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.SPILL_GATE_WIDTH)) {
								oItemHeadworks.setGatesWidth(rsHeadworks.getDouble(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.SPILL_GATE_HEIGHT)) {
								oItemHeadworks.setGatesHeight(rsHeadworks.getDouble(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.VALVESNR)) {
								oItemHeadworks.setValvesNumber(rsHeadworks.getInt(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.DUCTNR)) {
								oItemHeadworks.setDuctNumber(rsHeadworks.getInt(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.COMMENTS)) {
								oItemHeadworks.setComments(rsHeadworks.getString(rsHeadworks.getColumnIndex("Value")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")).equals(Constants.HAS_GRIDS)) {
								oItemHeadworks.setGridExistence(true);
							}

							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("CatalogID")).equals(Constants.INTAKE_WORK_TYPE)) {
								oItemHeadworks.setHeadworkType(rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")));
								oItemHeadworks.setHeadworkTypeDescription(rsHeadworks.getString(rsHeadworks.getColumnIndex("Description")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("CatalogID")).equals(Constants.GATE_TYPES)) {
								oItemHeadworks.setGatesType(rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")));
								oItemHeadworks.setGatesTypeDescription(rsHeadworks.getString(rsHeadworks.getColumnIndex("Description")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("CatalogID")).equals(Constants.VALVE_TYPE)) {
								oItemHeadworks.setValveType(rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")));
								oItemHeadworks.setValveTypeDescription(rsHeadworks.getString(rsHeadworks.getColumnIndex("Description")));
							}
							else if (rsHeadworks.getString(rsHeadworks.getColumnIndex("CatalogID")).equals(Constants.DUCT_TYPE)) {
								oItemHeadworks.setDuctType(rsHeadworks.getString(rsHeadworks.getColumnIndex("RecordID")));
								oItemHeadworks.setDuctTypeDescription(rsHeadworks.getString(rsHeadworks.getColumnIndex("Description")));
							}

						} while (rsHeadworks.moveToNext());
						oVectorHeadworks.add(oItemHeadworks);
						oItemDam.setDamHeadworks(oVectorHeadworks);

					}

					Cursor rsExtension = dbProcedures.getPropertyChildren(propertyID, 
							Constants.IRRIGATION_CHANNEL_STRUCTURE);
					pojoIrrigationChannel oItemIChannel = new pojoIrrigationChannel();
					rsExtension.moveToFirst();
					if (rsExtension.getCount() > 0) {
						oItemIChannel.setChannelID(rsExtension.getString(rsExtension.getColumnIndex("PropertyID")));
						oItemIChannel.setChannelName(rsExtension.getString(rsExtension.getColumnIndex("Name")));
						
						do{
							if (rsExtension.getString(rsExtension.getColumnIndex("Name")).compareTo(oItemIChannel.getChannelName())!=0){
								oVectorIChannel.add(oItemIChannel);
								oItemIChannel = new pojoIrrigationChannel();
								oItemIChannel.setChannelID(rsExtension.getString(rsExtension.getColumnIndex("PropertyID")));
								oItemIChannel.setChannelName(rsExtension.getString(rsExtension.getColumnIndex("Name")));
							}
							else if (rsExtension.getString(rsExtension.getColumnIndex("RecordID")).equals(Constants.LENGHT)) {
								oItemIChannel.setExtension(rsExtension.getDouble(rsExtension.getColumnIndex("Value")));
							}
							else if (rsExtension.getString(rsExtension.getColumnIndex("RecordID")).equals(Constants.FLOW)) {
								oItemIChannel.setFlow(rsExtension.getDouble(rsExtension.getColumnIndex("Value")));
							}
							else if (rsExtension.getString(rsExtension.getColumnIndex("RecordID")).equals(Constants.RL_RECONSTRUCTION_UNIT_VALUE)) {
								oItemIChannel.setValue(rsExtension.getString(rsExtension.getColumnIndex("Value")));
							}
						} while (rsExtension.moveToNext());
						oVectorIChannel.add(oItemIChannel);
						oItemDam.setDamIrrigation(oVectorIChannel);
					}
					
					Cursor rsOtrasEstructuras = dbProcedures.getPropertyChildren(propertyID, 
							Constants.RL_OTHER_DETAIL);
					pojoOtherStructures oItemOther = new pojoOtherStructures();
					
					rsOtrasEstructuras.moveToFirst();
					if (rsOtrasEstructuras.getCount() > 0) {
						oItemOther.setConcept(rsOtrasEstructuras.getString(rsOtrasEstructuras.getColumnIndex("Name")));
						oItemOther.setOtherID(rsOtrasEstructuras.getString(rsOtrasEstructuras.getColumnIndex("PropertyID")));
						
						do{
							if (rsOtrasEstructuras.getString(rsExtension.getColumnIndex("Name")).compareTo(oItemOther.getConcept())!=0){
								oVectorOther.add(oItemOther);
								oItemOther = new pojoOtherStructures();
								oItemOther.setConcept(rsOtrasEstructuras.getString(rsOtrasEstructuras.getColumnIndex("Name")));
								oItemOther.setOtherID(rsOtrasEstructuras.getString(rsOtrasEstructuras.getColumnIndex("PropertyID")));
							}
							else if (rsOtrasEstructuras.getString(rsOtrasEstructuras.getColumnIndex("RecordID")).equals(Constants.RL_OTHER)) {
								oItemOther.setValue(rsOtrasEstructuras.getString(rsOtrasEstructuras.getColumnIndex("Value")));
							}
						}while(rsOtrasEstructuras.moveToNext());
						oVectorOther.add(oItemOther);
						oItemDam.setDamOther(oVectorOther);
					}

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oItemDam;
	}

	public pojoEducation getEcuationPptyDetail(String propertyID, String postalCode){
		pojoEducation oItemEducation = new pojoEducation();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		//DBProperties oDBProperties = new DBProperties(); por línea que sigue:
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
//		Vector<pojoPropertyAttachment> oVectorPhotos = new Vector<pojoPropertyAttachment>();
//		Vector<pojoPropertyAttachment> oVectorDocs = new Vector<pojoPropertyAttachment>();
//		BigDecimal tmpValue;
		Cursor cursor = null;
		//CallableStatement cstmt = null;
		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
//			if (cstmt != null){
//				rs = cstmt.getResultSet();
//				if (rs!=null){
//					rs.next(); Cuatro lineas anteriores por tres siguientes:
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oItemEducation.setBuildingName(cursor.getString(cursor.getColumnIndex("Name")));
					oItemEducation.setBuildingNumber(cursor.getString(cursor.getColumnIndex("PropertyNumber")));
					oItemEducation.setPropertyUse(cursor.getString(cursor.getColumnIndex("PropertyUse")));
					oItemEducation.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oItemEducation.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					oItemEducation.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oItemEducation.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oItemEducation.setState(cursor.getString(cursor.getColumnIndex("StateCode")));
					oItemEducation.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oItemEducation.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oItemEducation.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oItemEducation.setReinformcementDate(cursor.getString(cursor.getColumnIndex("RegisterDate")));

					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								oItemEducation.setLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								oItemEducation.setLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE)) {
								oItemEducation.setAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.REGULAR_GEOMETRY)) {
								oItemEducation.setRegularGeometr(true);
							}
							
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.IRREGULAR_GEOMETRY)) {
								oItemEducation.setIrregularGeometry(true);
							}
							
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_BUILDING)) {
								//tmpValue = cursor.getString(cursor.getColumnIndex("Value"));
								oItemEducation.setRnBuildingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_BUILDING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlBuildingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_COMPUTE_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnComputingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_COMPUTE_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlComputingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_ELECTRONIC_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnElectronicValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ELECTRONIC_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlElectronicValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_BOOKS_INVENTORY)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlBooksValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_BOOKS_INVENTORY)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnBooksValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_CDS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlCDsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
								
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_CDS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnCDsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MEDIA_STORAGE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlStorageValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_MEDIA_STORAGE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnStorageValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_FURNITURES)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlFurnituresValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_FURNITURES)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnFurnituresValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ITEMS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRlItemsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_ITEMS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemEducation.setRnItemsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPECIALFACILITIES)) {
								oItemEducation.setSpecialFacilities(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELEVATION_FLAT_IRREGULARITY)) {
								oItemEducation.setElevation(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.PHONE_ELECTRIC_POLE)) {
								oItemEducation.setCloseToPole(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPECTACULAR_ADS)) {
								oItemEducation.setCloseToAds(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.TREES)) {
								oItemEducation.setCloseToTrees(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WINDOWS_PROTECTION)) {
								oItemEducation.setWindowProtectionType(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DOMES_PROTECTION)) {
								oItemEducation.setDomeProtectionType(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.OBJECTS_ON_ROOFS)) {
								oItemEducation.setRoofItems(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HAS_DOMES)) {
								oItemEducation.setDomes(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.EXTERNAL_STRUCTURE_POUNDING)) {
								oItemEducation.setExternalStrPounding(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SHORT_WALLS_BETWEEN_COLUMNS)) {
								oItemEducation.setShortWalls(true);
							}

							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.EDUCATION_INFRASTRUCTURE_TYPE)) {
								oItemEducation.setEducationType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE)) {
								oItemEducation.setStructureType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FOUNDATION_TYPE)) {
								oItemEducation.setFoundationType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WALL_TYPE)) {
								oItemEducation.setWalls(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STOREYS_STRUCTURE)) {
								oItemEducation.setStoryStructure(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STOREYS_MATERIAL)) {
								oItemEducation.setStoryMaterial(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FACHADE_TYPE)) {
								oItemEducation.setFachadeType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FACHADE_MATERIAL_TYPE)) {
								oItemEducation.setFachadeMaterial(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.GLASSWARE_TYPE)) {
								oItemEducation.setGlassWareType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.EXPOSED_GLASS_SIZE)) {
								oItemEducation.setFachadeGlassSize(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.BLACKSMITH_TYPE)) {
								oItemEducation.setBlacksmithType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.COVER_TYPES)) {
								oItemEducation.setCoverType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CANOPY_SHAPE)) {
								oItemEducation.setCoverShape(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.REGULAR_GEOMETRY)) {
								oItemEducation.setRegularGeometr(true);
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.IRREGULAR_GEOMETRY)) {
								oItemEducation.setIrregularGeometry(true);
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.External_CS)) {
								oItemEducation.setCnStExternal(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Internal_CS)) {
								oItemEducation.setCnStInternal(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Ceiling_CS)) {
								oItemEducation.setCnStCeiling(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Trimming_CS)) {
								oItemEducation.setCnStTrimmings(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Floor_CS)) {
								oItemEducation.setCnStFloors(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Electric_CS)) {
								oItemEducation.setCnStElectricInst(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.BaseBoard_CS)) {
								oItemEducation.setCnStBaseboards(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Carpintery_CS)) {
								oItemEducation.setCnStCarpintery(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Moulding_CS)) {
								oItemEducation.setCnStCancel(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Painting_CS)) {
								oItemEducation.setCnStPainting(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.BthmFxt_CS)) {
								oItemEducation.setCnStSanitaryFurn(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Plumbing_CS)) {
								oItemEducation.setCnStPlumbing(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
						} while (cursor.moveToNext());
					}

					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							//switch(cursor.getString(cursor.getColumnIndex("ConceptID"))){
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemEducation.setEarthQuakeRisk(true);
								} else {
									oItemEducation.setEarthQuakeFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemEducation.setFloodRisk(true);
								} else {
									oItemEducation.setFloodFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemEducation.setHurricanRisk(true);
								} else {
									oItemEducation.setHurricanFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemEducation.setRockFallRisk(true);
								} else {
									oItemEducation.setRockFallFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemEducation.setSlidingRisk(true);
								} else {
									oItemEducation.setSlidingFormer(true);
								}
							}
						} while (cursor.moveToNext());
					}

					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						
						//DateOperations oDateOperations = new DateOperations();
						curYear = DateOperations.getCurrentYear();
						//cursor.next();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
									siniestralityAmount[i] = "0.0";
									siniestralityDescr[i] = "";
								}else{
									//cursor.getLong(columnIndex);
									//cursor.getFloat(columnIndex);
									siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								//throw new Exception(e);
								siniestralityAmount[i] = "0.0";
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = "";
							siniestralityDescr[i] = "";
						}
					}
					oItemEducation.setSiniestralityValue(siniestralityAmount);
					oItemEducation.setSiniestralityDescription(siniestralityDescr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oItemEducation;
	}

	public pojoHistorical getHistoricalPptyDetail(String propertyID, String postalCode){
		pojoHistorical oHistoricalItem = new pojoHistorical();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		Cursor cursor = null;
		BigDecimal tmpValue;
		//CallableStatement cstmt = null;
		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oHistoricalItem.setPropertyID(propertyID);
					oHistoricalItem.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					oHistoricalItem.setBuildingName(cursor.getString(cursor.getColumnIndex("Name")));
					oHistoricalItem.setBuildingNumber(cursor.getString(cursor.getColumnIndex("PropertyNumber")));
					oHistoricalItem.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oHistoricalItem.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oHistoricalItem.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oHistoricalItem.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oHistoricalItem.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oHistoricalItem.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oHistoricalItem.setState(cursor.getString(cursor.getColumnIndex("StateCode")));
					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_BUILDING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));	
								oHistoricalItem.setRlBuildingValue(cursor.getString(cursor.getColumnIndex("Value")));								
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_BUILDING)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnBuildingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_COMPUTE_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlComputingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_COMPUTE_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnComputingValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ELECTRONIC_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlElectronicValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_ELECTRONIC_EQUIPMENT)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnElectronicValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_BOOKS_INVENTORY)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlBooksValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_BOOKS_INVENTORY)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnBooksValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_CDS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlCDsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_CDS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnCDsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MEDIA_STORAGE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlStorageValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_MEDIA_STORAGE)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnStorageValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_FURNITURES)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlFurnituresValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_FURNITURES)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnFurnituresValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ITEMS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRlItemsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_ITEMS)) {
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oHistoricalItem.setRnItemsValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								oHistoricalItem.setLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								oHistoricalItem.setLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE)) {
								oHistoricalItem.setAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WATERBODIES)) {
								oHistoricalItem.setWaterBody(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.Managed_By)) {
								oHistoricalItem.setManagedBy(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
						} while (cursor.moveToNext());
					}

					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							//switch(cursor.getString(cursor.getColumnIndex("ConceptID"))){
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE)) {
								oHistoricalItem.setEarthQuakeRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD)) {
								oHistoricalItem.setFloodRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE)) {
								oHistoricalItem.setHurricanRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE)) {
								oHistoricalItem.setRockFallRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE)) {
								oHistoricalItem.setSlidingRisk(true);
							}
						} while (cursor.moveToNext());
					}

					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						
						//DateOperations oDateOperations = new DateOperations();
						curYear = DateOperations.getCurrentYear();
						//cursor.next();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
									siniestralityAmount[i] = "0.0";
									siniestralityDescr[i] = "";
								}else{
									siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								//throw new Exception(e);
								siniestralityAmount[i] = "0.0";
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = "0.0";
							siniestralityDescr[i] = "";
						}
					}
					oHistoricalItem.setSiniestrality(siniestralityAmount);
					oHistoricalItem.setSiniestralityDesc(siniestralityDescr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oHistoricalItem;
	}

	public pojoHidraulic getHidraulicPptyDetail(String propertyID, String postalCode){
		pojoHidraulic oItemHidraulic = new pojoHidraulic();
		BigDecimal[] siniestralityAmount = new BigDecimal[10];
		String[] siniestralityDescr = new String[10];
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		
		BigDecimal tempValue;
		Cursor cursor = null;
		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oItemHidraulic.setName(cursor.getString(cursor.getColumnIndex("Name")));
					oItemHidraulic.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oItemHidraulic.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					//oItemHidraulic.setPropertyID(cursor.getString(cursor.getColumnIndex("PropertyID")));
					oItemHidraulic.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oItemHidraulic.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oItemHidraulic.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oItemHidraulic.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oItemHidraulic.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oItemHidraulic.setBuildingDate(cursor.getString(cursor.getColumnIndex("BuildingDate")));

					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE))
							{
								oItemHidraulic.setLatidude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE))
							{
								oItemHidraulic.setLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}	
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE))
							{
								oItemHidraulic.setAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_COLECTORS))
							{
								oItemHidraulic.setColectors(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_COLECTORS))
							{
								oItemHidraulic.setInterceptors(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_TREATMENT_STATIONS))
							{
								oItemHidraulic.setWasteWaterTreamtentPlants(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_LANDFILLS))
							{
								oItemHidraulic.setSpouts(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_GUTTER_TRENCH))
							{
								oItemHidraulic.setGuttersTrenchCaceres(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_HOPPERS))
							{
								oItemHidraulic.setHoppers(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SEWER_NUMBER))
							{
								oItemHidraulic.setSewers(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.INSPECTION_WELL))
							{
								oItemHidraulic.setManholes(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_PUMPING_STATIONS))
							{
								oItemHidraulic.setSewersPumpingStation(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SW_RETENTION_TANKS))
							{
								oItemHidraulic.setSewerContainer(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_BUILDING))
							{
								tempValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHidraulic.setRenewalValue(tempValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_MAINTENANCE_AMOUNT))
							{
								tempValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHidraulic.setAnnualMaintenance(tempValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_RENEWAL_VALUE))
							{
								tempValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHidraulic.setRenewalValue(tempValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_CIVIL_WORKS_VALUE))
							{
								tempValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHidraulic.setCivilWorksValue(tempValue);	
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_MACHINERY_VALUE))
							{
								tempValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHidraulic.setEquipmentMachineryValue(tempValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SEWER_DRIVE_LINE))
							{
								oItemHidraulic.setDriveLines(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_OTHER))
							{
								tempValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHidraulic.setItemsRenualValue(tempValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_OTHER_DETAIL))
							{
								oItemHidraulic.setItemsDetail(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WATERBODIES))
							{
								oItemHidraulic.setClosestWaterBody(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE))
							{
								oItemHidraulic.setSturcturaltype(cursor.getString(cursor.getColumnIndex("RecordID")));	
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FOUNDATION_TYPE))
							{
								oItemHidraulic.setFoundationType(cursor.getString(cursor.getColumnIndex("RecordID")));	
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WALL_TYPE))
							{
								oItemHidraulic.setWallsType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SOIL_TYPE))
							{
								oItemHidraulic.setSoilType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DRINKING_WATER_NETWARE))
							{
								oItemHidraulic.setDrinkingWaterNet(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DRAIN_NETWARE))
							{
								oItemHidraulic.setDrainNet(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CATCHMENT_TYPE))
							{
								oItemHidraulic.setCatchmentType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WATER_TREATMENT_PLANT_TYPE))
							{
								oItemHidraulic.setWaterTreatmentPlantType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.TREATED_WATER_STORAGE_TYPE))
							{
								oItemHidraulic.setTreatedWaterStorageType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.PIPE_TYPE))
							{
								oItemHidraulic.setPipesType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CHANNEL_TYPE))
							{
								oItemHidraulic.setChannelsType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.DAM_TYPE))
							{
								oItemHidraulic.setDamType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.PUMPING_STATIONS))
							{
								oItemHidraulic.setPumpingStation(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.GATE_TYPES))
							{
								oItemHidraulic.setGatesTypes(cursor.getString(cursor.getColumnIndex("RecordID")));	
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SEWER_NET))
							{
								oItemHidraulic.setSewerNet(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.INTAKE_TYPE))
							{
								oItemHidraulic.setIntakeType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CROSS_TYPE))
							{
								oItemHidraulic.setCrossType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.HIDRAULIC_COMPONENTS))
							{
								oItemHidraulic.setHidraulicComponents(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.NON_HIDRAULIC_COMPONENTS))
							{
								oItemHidraulic.setNonHidraulicComponents(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURAL_MEDIA_TYPE))
							{
								oItemHidraulic.setStructuralMedia(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
						} while (cursor.moveToNext());
					}

					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE))
							{
								oItemHidraulic.setEarthQuackeFormer(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD))
							{
								oItemHidraulic.setFloodFormer(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE))
							{
								oItemHidraulic.setHurricaneFormer(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE))
							{
								oItemHidraulic.setCollapseFormer(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE))
							{
								oItemHidraulic.setLandSlideFormer(true);
							}
						
							
						}while (cursor.moveToNext());
					}
					
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						curYear = DateOperations.getCurrentYear();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
									siniestralityAmount[i] = new BigDecimal("0");
									siniestralityDescr[i] = "";
								}else{
									siniestralityAmount[i] = new BigDecimal(cursor.getString(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								//throw new Exception(e);
								siniestralityAmount[i] = new BigDecimal("0");
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = new BigDecimal("0");
							siniestralityDescr[i] = "";
						}
					}
					oItemHidraulic.setSiniestralityValues(siniestralityAmount);
					oItemHidraulic.setSiniestralityDesccriptions(siniestralityDescr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oItemHidraulic;
	}

	public pojoWasteDisposal getWasteDisposalPptyDetail(String propertyID, String postalCode){
		pojoWasteDisposal oItemWaste = new pojoWasteDisposal();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		BigDecimal tmpValue;
		Cursor cursor = null;
		try{
		cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
		if (cursor != null){
			cursor.moveToFirst();
			if (cursor.getCount() > 0){
					oItemWaste.setName(cursor.getString(cursor.getColumnIndex("Name")));
					oItemWaste.setStateID(cursor.getString(cursor.getColumnIndex("StateCode")));
					oItemWaste.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					//oItemWaste.setPropertyID(cursor.getString(cursor.getColumnIndex("PropertyID")));
					oItemWaste.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {

							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE))
							{
								if (oItemWaste.getStartLatitude() == 0)
									oItemWaste.setStartLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oItemWaste.setEndLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE))
							{
								if (oItemWaste.getStartLongitude() == 0)
									oItemWaste.setStartLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oItemWaste.setEndLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE))
							{
								if (oItemWaste.getStartAltitude() == 0)
									oItemWaste.setStartAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oItemWaste.setEndAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_RENEWAL_VALUE))
							{
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemWaste.setRenewalValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_CIVIL_WORKS_VALUE))
							{
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemWaste.setCivilWorksValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MACHINERY_VALUE))
							{
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemWaste.setMachineryEquipmentValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MAINTENANCE_AMOUNT))
							{
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemWaste.setAnnualMaintenanceAmount(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.EXTENSION))
							{
								oItemWaste.setExtension(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ACCESS_WAYS))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setAccessWays(true);
								else
									oItemWaste.setAccessWays(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.INNER_WAYS))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setInnerWays(true);
								else
									oItemWaste.setInnerWays(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.PERIMETER_FENCE))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setPerimeterFence(true);
								else
									oItemWaste.setPerimeterFence(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SECURITY_ROOM))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setSecurityRoom(true);
								else
									oItemWaste.setSecurityRoom(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.BASCULE))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setBascule(true);
								else
									oItemWaste.setBascule(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WATER_ELECTRICITY_DRAIN))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setWaterElectricityDrain(true);
								else
									oItemWaste.setWaterElectricityDrain(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SANITARY_SERVICES))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setSanitaryServices(true);
								else
									oItemWaste.setSanitaryServices(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.CUSHIONING_STRIP))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setCushioningStrip(true);
								else
									oItemWaste.setCushioningStrip(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.OFFICES))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setOffices(true);
								else
									oItemWaste.setOffices(false);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.MEDICAL_SERVICE))
							{
								if (cursor.getInt(cursor.getColumnIndex("Value"))==1)
									oItemWaste.setMedicalServices(true);
								else
									oItemWaste.setMedicalServices(false);
							}

							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WASTE_DISPOSA_INFRASTRUCTURE_TYPE))
							{
								oItemWaste.setWasteDisposalType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE))
							{
								oItemWaste.setStructuralType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FOUNDATION_TYPE))
							{
								oItemWaste.setFoundationType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WALL_TYPE))
							{
								oItemWaste.setWallsType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STATEID))
							{
								oItemWaste.setStateID(cursor.getString(cursor.getColumnIndex("Description")));
							}	

						}while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE))
							{
								oItemWaste.setEarthQuakeRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD))
							{
								oItemWaste.setFleedRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE))
							{
								oItemWaste.setHurricaneRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE))
							{
								oItemWaste.setCollapseRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE))
							{
								oItemWaste.setLandSlideRisk(true);
							}
						}while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						curYear = DateOperations.getCurrentYear();
						for (int i=0;i<10;i++){
							curYear--;
							if (cursor.getInt(cursor.getColumnIndex("Year")) < curYear){
								siniestralityAmount[i] = "0";
								siniestralityDescr[i] = "";
							}else{
								siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
								if (cursor.getString(cursor.getColumnIndex("Description")) != null)
									siniestralityDescr[i] =cursor.getString(cursor.getColumnIndex("Description"));
								else
									siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					oItemWaste.setSiniestralityValues(siniestralityAmount);
					oItemWaste.setSiniestralityDescription(siniestralityDescr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oItemWaste;
	}

	public pojoWays getWaysPptyDetail(String propertyID, String postalCode){
//		BigDecimal tmpValue = new BigDecimal("0");
		String cst;
		pojoWays oWayItem = new pojoWays();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		BigDecimal tmpValue;
		Cursor cursor = null;
		Vector<pojoBridges> oVectorBridges = new Vector<pojoBridges>();
		Vector<pojoTunnel> oVectorTunnel = new Vector<pojoTunnel>();
		Vector<pojoSinking> oVectorSinkings = new Vector<pojoSinking>();
		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oWayItem.setName(cursor.getString(cursor.getColumnIndex("Name")));
					oWayItem.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oWayItem.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oWayItem.setState(cursor.getString(cursor.getColumnIndex("StateCode")));
					oWayItem.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oWayItem.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oWayItem.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oWayItem.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oWayItem.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oWayItem.setConstructionDate(cursor.getString(cursor.getColumnIndex("BuildingDate")));
					oWayItem.setPropertyTypeID(cursor.getString(cursor.getColumnIndex("_id")));
					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE))
							{
								if (oWayItem.getStartLatitude() == 0)
									oWayItem.setStartLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oWayItem.setEndLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE))
							{
								if (oWayItem.getStartLongitude()==0)
									oWayItem.setStartLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oWayItem.setEndLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE))
							{
								if(oWayItem.getStartAltitude()==0)
									oWayItem.setStartAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oWayItem.setEndAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WBLATITUDE))
							{
								if(oWayItem.getClosestWaterBodyStartLatitude()==0)
									oWayItem.setClosestWaterBodyStartLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oWayItem.setClosestWaterBodyEndLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WBLONGITUDE))
							{
								if(oWayItem.getClosestWaterBodyStartLongitude()==0)
									oWayItem.setClosestWaterBodyStartLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oWayItem.setClosestWaterBodyEndLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WB_CROSS_WAY))
							{
								if(cursor.getString(cursor.getColumnIndex("Value")).compareTo("0")==0)
									oWayItem.setClosestWaterBodyCross(false);
								else
									oWayItem.setClosestWaterBodyCross(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DISTANCE))
							{
								oWayItem.setClosestWaterBodyDistanceCross(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RAILS_NUMBER))
							{
								oWayItem.setLinesNumber(cursor.getInt(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.KILOMETERS))
							{
								oWayItem.setTotalKms(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.OVERWAYSNR))
							{
								oWayItem.setOverWaysNumber(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.UNDERWAYSNR))
							{
								oWayItem.setUnderWaysNumber(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WIDTH_RAIL))
							{
								oWayItem.setWidthLine(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SEWER_NUMBER))
							{
								oWayItem.setSewers(cursor.getInt(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_RENEWAL_VALUE))
							{
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oWayItem.setRenewalWayValue(cursor.getString(cursor.getColumnIndex("Value")));
							}

							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WATERBODIES))
							{
								oWayItem.setClosestWaterBodyType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.COATING_TYPE))
							{
								oWayItem.setCoverType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WAY_TYPE))
							{
								oWayItem.setWayType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CONDITION))
							{
								oWayItem.setCondition(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
						} while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							//switch(cursor.getString(cursor.getColumnIndex("ConceptID"))){
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oWayItem.setEarthQuakeRisk(true);
								} else {
									oWayItem.setEarthQuakeFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oWayItem.setFloodRisk(true);
								} else {
									oWayItem.setFloodFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oWayItem.setHurricanRisk(true);
								} else {
									oWayItem.setHurricanFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oWayItem.setRockFallRisk(true);
								} else {
									oWayItem.setRockFallFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oWayItem.setSlidingRisk(true);
								} else {
									oWayItem.setSlidingFormer(true);
								}
							}
						} while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						
						//DateOperations oDateOperations = new DateOperations();
						curYear = DateOperations.getCurrentYear();
						//cursor.next();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (cursor.getInt(cursor.getColumnIndex("Year")) < curYear){
									siniestralityAmount[i] = "0.0";
									siniestralityDescr[i] = "";
								}else{
									siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								//throw new Exception(e);
								siniestralityAmount[i] = "0.0";
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = "";
							siniestralityDescr[i] = "";
						}
					}
					oWayItem.setSiniestralityValues(siniestralityAmount);
					oWayItem.setSiniestralityDescription(siniestralityDescr);

					cursor = dbProcedures.getPropertyChildren(propertyID, Constants.BRIDGE_STRUCTURE);
					pojoBridges oItemBridges = new pojoBridges();
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemBridges.setBridgeID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						oItemBridges.setBridgeName(cursor.getString(cursor.getColumnIndex("Name")));
						do{
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemBridges.getBridgeName())!=0){
								oVectorBridges.add(oItemBridges);
								oItemBridges = new pojoBridges();
								oItemBridges.setBridgeID(cursor.getString(cursor.getColumnIndex("PropertyID")));
								oItemBridges.setBridgeName(cursor.getString(cursor.getColumnIndex("Name")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.BRIDGES_TYPE))
							{
								oItemBridges.setBridgeType(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemBridges.setBridgeTypeName(cursor.getString(cursor.getColumnIndex("Description")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.IS_MAIN_BRIDGE))
							{
								if(cursor.getString(cursor.getColumnIndex("Value")).compareTo("0")==0)
									oItemBridges.setMain(false);
								else
									oItemBridges.setMain(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DISTANCE))
							{
								oItemBridges.setBridgeLength(Double.parseDouble(cursor.getString(cursor.getColumnIndex("Value"))));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_RENEWAL_VALUE))
							{
								//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemBridges.setRenewalBridgeValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
						} while (cursor.moveToNext());
						oVectorBridges.add(oItemBridges);
						oWayItem.setAttachedWays(oVectorBridges);
					}

					cursor = dbProcedures.getPropertyChildren(propertyID, Constants.TUNNEL_STRUCTURE);
					pojoTunnel oItemTunnel = new pojoTunnel();
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemTunnel.setTunnelID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						oItemTunnel.setTunnelName(cursor.getString(cursor.getColumnIndex("Name")));
						do{
							if (cursor.getString(cursor.getColumnIndex("Name")).compareTo(oItemTunnel.getTunnelName())!=0)
							{
								oVectorTunnel.add(oItemTunnel);
								oItemTunnel = new pojoTunnel();
								oItemTunnel.setTunnelID(cursor.getString(cursor.getColumnIndex("PropertyID")));
								oItemTunnel.setTunnelName(cursor.getString(cursor.getColumnIndex("Name")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.TUNNELS_TYPE))
							{
								cst = dbProcedures.getDescriptionFromRecordID(cursor.getString(cursor.getColumnIndex("RecordID")));
								oItemTunnel.setTunnelTypeName(cst);
								oItemTunnel.setTunnelType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DISTANCE))
							{
									oItemTunnel.setTunnelLength(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_RENEWAL_VALUE))
							{
									//tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
									oItemTunnel.setRenewalTunnelValue(cursor.getString(cursor.getColumnIndex("Value")));
							}
						} while (cursor.moveToNext());
						oVectorTunnel.add(oItemTunnel);
						oWayItem.setAttachedTunnels(oVectorTunnel);
					}

					cursor = dbProcedures.getPropertyChildren(propertyID, Constants.SINKING_STRUCTURE);
					pojoSinking oItemHund = new pojoSinking();
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						oItemHund.setSinkingID(cursor.getString(cursor.getColumnIndex("PropertyID")));
						oItemHund.setPosition(cursor.getDouble(cursor.getColumnIndex("Name")));
						do{
							if (oItemHund.getPosition() != Double.parseDouble(cursor.getString(cursor.getColumnIndex("Name")))) {
								oVectorSinkings.add(oItemHund);
								oItemHund = new pojoSinking();
								oItemHund.setSinkingID(cursor.getString(cursor.getColumnIndex("PropertyID")));
								oItemHund.setPosition(Double.parseDouble(cursor.getString(cursor.getColumnIndex("Name"))));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SINKING_SEVERITY))
							{
								oItemHund.setSeverity(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Value"))));
							}
						} while (cursor.moveToNext());
						oVectorSinkings.add(oItemHund);
						oWayItem.setSinkings(oVectorSinkings);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oWayItem;
	}

	public pojoHealt getHealtPptyDetail(String propertyID, String postalCode){
		pojoHealt oItemHealt = new pojoHealt();
		String[] siniestralityAmount = new String[10];
		String[] siniestralityDescr = new String[10];
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		BigDecimal tmpValue;
		Cursor cursor = null;

		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oItemHealt.setName(cursor.getString(cursor.getColumnIndex("Name")));
					oItemHealt.setConstructionDate(cursor.getString(cursor.getColumnIndex("BuildingDate")));
					oItemHealt.setLevels(cursor.getString(cursor.getColumnIndex("Levels")));
					oItemHealt.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oItemHealt.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					oItemHealt.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oItemHealt.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oItemHealt.setCity(cursor.getString(cursor.getColumnIndex("City")));
					oItemHealt.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oItemHealt.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));

					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								oItemHealt.setLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								oItemHealt.setLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE)) {
								oItemHealt.setAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.REGULAR_GEOMETRY)) {
								oItemHealt.setGeometryRegular(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.IRREGULAR_GEOMETRY)) {
								oItemHealt.setGeometryIrregular(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.STRUCTURE_TYPE)) {
								oItemHealt.setStructuraltype(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_BUILDING)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHealt.setBuilningRenewalValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_MEDICAL_INSTUMENTS)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHealt.setMedicalRenewalValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_LAB_EQUIPMENT)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHealt.setLabMaterialRenewalValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_COMPUTE_EQUIPMENT)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHealt.setElectronicRenewalValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RL_ITEMS)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemHealt.setItemRenewalValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ELEVATION_FLAT_IRREGULARITY)) {
								oItemHealt.setElevation(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.OBJECTS_ON_ROOFS)) {
								oItemHealt.setObjectsInRoof(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.HAS_DOMES)) {
								oItemHealt.setDome(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.EXTERNAL_STRUCTURE_POUNDING)) {
								oItemHealt.setHitting(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SHORT_WALLS_BETWEEN_COLUMNS)) {
								oItemHealt.setShortWalls(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.WINDOWS_PROTECTION)) {
								oItemHealt.setWindowProtection(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.DOMES_PROTECTION)) {
								oItemHealt.setDomeProtection(cursor.getString(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.PHONE_ELECTRIC_POLE)) {
								oItemHealt.setCloseToPoles(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.SPECTACULAR_ADS)) {
								oItemHealt.setCloseToAds(true);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.TREES)) {
								oItemHealt.setCloseToTrees(true);
							}

							//switch (rs.getString("CatalogID")){
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE)) {
								oItemHealt.setStructuraltype(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FOUNDATION_TYPE)) {
								oItemHealt.setFoundingType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WALL_TYPE)) {
								oItemHealt.setWallsType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FACHADE_TYPE)) {
								oItemHealt.setFachadeType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FACHADE_MATERIAL_TYPE)) {
								oItemHealt.setFachadeMaterial(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.GLASSWARE_TYPE)) {
								oItemHealt.setFachadeGlasseryType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.EXPOSED_GLASS_SIZE)) {
								oItemHealt.setFachadeGlasserySize(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.COVER_TYPES)) {
								oItemHealt.setCoverType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.CANOPY_SHAPE)) {
								oItemHealt.setCoverShape(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.SOIL_TYPE)) {
								oItemHealt.setSoilType(cursor.getString(cursor.getColumnIndex("RecordID")));
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.REGULAR_GEOMETRY)) {
								oItemHealt.setGeometryRegular(true);
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.IRREGULAR_GEOMETRY)) {
								oItemHealt.setGeometryIrregular(true);
							}

						} while (cursor.moveToNext());
					}
					
					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemHealt.setEarthQuakeRisk(true);
								}else{
									oItemHealt.setEarthQuakeFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemHealt.setFloodRisk(true);
								}else{
									oItemHealt.setFloodFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemHealt.setHurricaneRisk(true);
								}else{
									oItemHealt.setHurricaneFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE)) {

								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemHealt.setCollapseRisk(true);
								}else{
									oItemHealt.setCollapseFormer(true);
								}
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0){
									oItemHealt.setLandSlideRisk(true);
								}else{
									oItemHealt.setLandSlideFormer(true);
								}
							}
						} while (cursor.moveToNext());
					}
					
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						int curYear;
						curYear = DateOperations.getCurrentYear();
						for (int i=0;i<10;i++){
							try
							{
								curYear--;
								if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
									siniestralityAmount[i] = "0.0";
									siniestralityDescr[i] = "";
								}else{
									siniestralityAmount[i] = Long.toString(cursor.getLong(cursor.getColumnIndex("Amount")));
									if (cursor.getString(cursor.getColumnIndex("Description")) != null)
										siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
									else
										siniestralityDescr[i] = "";
									cursor.moveToNext();
								}
							}
							catch(Exception e)
							{
								siniestralityAmount[i] = "0.0";
								siniestralityDescr[i] = "";
								cursor.moveToNext();
							}
						}
					}
					else
					{
						for (int i=0;i<10;i++) {
							siniestralityAmount[i] = "";
							siniestralityDescr[i] = "";
						}
					}
					oItemHealt.setSiniestralityValues(siniestralityAmount);
					oItemHealt.setSiniestralityDescription(siniestralityDescr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return oItemHealt;
	}

	public pojoUrban getUrbanPptyDetail(String propertyID, String postalCode){
		pojoUrban oItemUrban = new pojoUrban();
		BigDecimal[] siniestralityAmount = new BigDecimal[10];
		String[] siniestralityDescr = new String[10];
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		BigDecimal tmpValue;
		Cursor cursor = null;
		try{
			cursor = dbProcedures.getPropertyLocation(propertyID, postalCode);
			if (cursor != null){
				cursor.moveToFirst();
				if (cursor.getCount() > 0){
					oItemUrban.setPropertyName(cursor.getString(cursor.getColumnIndex("Name")));
					oItemUrban.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oItemUrban.setPostalCodeID(cursor.getString(cursor.getColumnIndex("PostalCodeID")));
					oItemUrban.setPostalCode(cursor.getString(cursor.getColumnIndex("PostalCode")));
					oItemUrban.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
					oItemUrban.setColony(cursor.getString(cursor.getColumnIndex("Township")));
					oItemUrban.setState(cursor.getString(cursor.getColumnIndex("StateCode")));
					oItemUrban.setStateName(cursor.getString(cursor.getColumnIndex("StateName")));
					oItemUrban.setTown(cursor.getString(cursor.getColumnIndex("CityHall")));
					oItemUrban.setCity(cursor.getString(cursor.getColumnIndex("City")));
					
					cursor = dbProcedures.getPropertyFeatures(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do{
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LATITUDE)) {
								if (oItemUrban.getStartLatitude() == 0)
									oItemUrban.setStartLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oItemUrban.setEndLatitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.LONGITUDE)) {
								if (oItemUrban.getStartLongitude() == 0)
									oItemUrban.setStartLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oItemUrban.setEndLongitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.ALTITUDE)) {
								if (oItemUrban.getStartAltitude() == 0)
									oItemUrban.setStartAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								else
									oItemUrban.setEndAltitude(cursor.getDouble(cursor.getColumnIndex("Value")));
								}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RAILS_NUMBER)) {
								oItemUrban.setLanesNumber(cursor.getInt(cursor.getColumnIndex("Value")));
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_RECONSTRUCTION_VALUE_KM)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemUrban.setAvgKmReconstructionValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_RECONSTRUCTION_UNIT_VALUE)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemUrban.setStmtReposicionValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_MINOR_BRIDGES_VALUE)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemUrban.setMinorBridgesAvgRepositionValue(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_PREVENTIVE_MAINTENANCE_AMOUNT)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemUrban.setPreventionAvgAmount(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("RecordID")).equals(Constants.RN_CORRECTIVE_MAINTENANCE_AMOUNT)) {
								tmpValue = new BigDecimal(cursor.getString(cursor.getColumnIndex("Value")));
								oItemUrban.setCorrectiveAvgAmount(tmpValue);
							}
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.URBAN_INFRASTRUCTURE_TYPE))
								oItemUrban.setInfrastructureType(cursor.getString(cursor.getColumnIndex("RecordID")));
							
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.STRUCTURE_TYPE))
								oItemUrban.setStructuralType(cursor.getString(cursor.getColumnIndex("RecordID")));
							
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.FOUNDATION_TYPE))
								oItemUrban.setFoundationType(cursor.getString(cursor.getColumnIndex("RecordID")));
							
							if (cursor.getString(cursor.getColumnIndex("CatalogID")).equals(Constants.WALL_TYPE))
								oItemUrban.setWallsType(cursor.getString(cursor.getColumnIndex("RecordID")));
							
						}
						while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertyDamagesRisks(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){
						do {
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.EARTHQUACKE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0)
									oItemUrban.setEarthQuakeRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.FLOOD)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0)
									oItemUrban.setFleedRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.HURRICANE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0)
									oItemUrban.setHurricaneRisk(true);
							}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.COLLAPSE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0)
									oItemUrban.setRockFallingRisk(true);
								}
							if (cursor.getString(cursor.getColumnIndex("ConceptID")).equals(Constants.LANDSLIDE)) {
								if(cursor.getString(cursor.getColumnIndex("DataType")).compareTo("R")==0)
									oItemUrban.setLandSlidingRisk(true);
								}
							}while (cursor.moveToNext());
					}
					cursor = dbProcedures.getPropertySiniestrality(propertyID);
					cursor.moveToFirst();
					if (cursor.getCount() > 0){ 
						int curYear;
						curYear = DateOperations.getCurrentYear();
						for (int i=0;i<10;i++){
							curYear--;
							if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("Year"))) < curYear){
								siniestralityAmount[i] = new BigDecimal("0");
								siniestralityDescr[i] = "";
								}
							else{
								siniestralityAmount[i] = new BigDecimal(cursor.getString(cursor.getColumnIndex("Amount")));
								if (cursor.getString(cursor.getColumnIndex("Description")) != null)
									siniestralityDescr[i] = cursor.getString(cursor.getColumnIndex("Description"));
								else
									siniestralityDescr[i] = "";
								cursor.moveToNext();
								}
							}
						}
					oItemUrban.setSiniestralityAmount(siniestralityAmount);
					oItemUrban.setSiniestralityDescription(siniestralityDescr);
				}
			}
		}catch(Exception e){
					e.printStackTrace();
				}
				return oItemUrban;
	}

	public pojoUsers getUserDetail(String propertyID) {
		pojoUsers pojoUsuario = new pojoUsers();
		Cursor cursor = null;
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		try{
			cursor = dbProcedures.getUser(propertyID);
			if (cursor != null) {
				cursor.moveToFirst();
				if (cursor.getCount() > 0) {
					pojoUsuario.setActive(cursor.getInt(cursor.getColumnIndex("Active")));
					pojoUsuario.setDeleted(cursor.getInt(cursor.getColumnIndex("Deleted")));
					pojoUsuario.setGuid(cursor.getString(cursor.getColumnIndex("_id")));
					pojoUsuario.setLock(cursor.getInt(cursor.getColumnIndex("Lock")));
					pojoUsuario.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
					pojoUsuario.setQrCode(cursor.getString(cursor.getColumnIndex("QrCode")));
					pojoUsuario.setUserID(cursor.getString(cursor.getColumnIndex("_id")));
					pojoUsuario.setUserName(cursor.getString(cursor.getColumnIndex("UserName")));
				} else {
					pojoUsuario = null;
				}
			} else {
				pojoUsuario = null;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return pojoUsuario;
	}

	public void checkDB()
	{
		Cursor cursor = null;
		DatabaseProcedures dbProcedures = new DatabaseProcedures(context);
		try{
			cursor = dbProcedures.getResult();
			if (cursor != null) {
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
