package com.mclr.mini.riesgosmovil.modelos;

import android.content.Context;

import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;

public class ProcessPropertiesActions {
	Context context;
	DatabaseHandler dataBase;

	public ProcessPropertiesActions(Context p_context){
		context = p_context;
	}

	public String[] processHistoricalActions(String propertyID, pojoHistorical p_pojoHistorical)
	{
		String generalFeatures="";
		String features="";
		String damageRisk="";
		String annualSiniestrality="";

		String use="";
		String isFinding="";
		String userID="";
		String buildingDate="";
		String levels="";
		int currentYear = 0;

		if (propertyID == null) {
			UUID guid = UUID.randomUUID();

			// ---------- Armado de cadena para tabla TBL_Properties  ----------
			generalFeatures = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?><ROOT><generalFeatures>";
			generalFeatures += ("<propertyType>" + Constants.HISTORICAL + "</propertyType>" +
							"<propertyID>" + propertyID + "</propertyID>" +
							"<propertyNumber>" + p_pojoHistorical.getBuildingNumber() + "</propertyNumber>" + 
							"<propertyUse>" + use + "</propertyUse>" +
							"<propertyName>" + p_pojoHistorical.getBuildingName() + "</propertyName>" + 
							"<address>" + p_pojoHistorical.getAddress() + "</address>" +
							"<finding>" + isFinding + "</finding>" +
							"<registerUser>" + userID + "</registerUser>" + 
							"<buildingDate>" + buildingDate + "</buildingDate>" +
							"<levels>" + levels + "</levels>" +
							"<propertyParentID>00000000-0000-0000-0000-000000000000</propertyParentID>" +
							"<postalCodeID>" + p_pojoHistorical.getPostalCodeID() + "</postalCodeID>"
							);
			generalFeatures += "</generalFeatures></ROOT>";
			//Cierre de la cadena XML para registro principal
			
			// ---------- Armado de cadena para tabla TBL_PropertyFeatures  ----------			
			if (p_pojoHistorical.getManagedBy() != null)
				features += ("<FEATURES Record='" + p_pojoHistorical.getManagedBy() + "'><AMOUNT Values='0'/></FEATURES>");
			if (p_pojoHistorical.getWaterBody().compareTo("0") != 0)
				features += ("<FEATURES Record='" +  p_pojoHistorical.getWaterBody() + "'><AMOUNT Values='0'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_BUILDING + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlBuildingValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_BUILDING + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnBuildingValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_COMPUTE_EQUIPMENT + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlComputingValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_COMPUTE_EQUIPMENT + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnComputingValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_ELECTRONIC_EQUIPMENT + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlElectronicValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_ELECTRONIC_EQUIPMENT + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnElectronicValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_BOOKS_INVENTORY + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlBooksValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_BOOKS_INVENTORY + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnBooksValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_CDS + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlCDsValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_CDS + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnCDsValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_MEDIA_STORAGE + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlStorageValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_MEDIA_STORAGE + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnStorageValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_FURNITURES + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlFurnituresValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_FURNITURES + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnFurnituresValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RL_ITEMS + "'><AMOUNT Values='" +
					p_pojoHistorical.getRlItemsValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.RN_ITEMS + "'><AMOUNT Values='" +
					p_pojoHistorical.getRnItemsValue() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.LATITUDE + "'><AMOUNT Values='" +
					p_pojoHistorical.getLatitude() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.LONGITUDE + "'><AMOUNT Values='" +
					p_pojoHistorical.getLongitude() + "'/></FEATURES>");
			features += ("<FEATURES Record='" + Constants.ALTITUDE + "'><AMOUNT Values='" +
					p_pojoHistorical.getAltitude() + "'/></FEATURES>");
			if (features.length()!=0)
				features = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?><ROOT>" + features + "</ROOT>";
			//Cierre de la cadena XML para Características del bien

			// ---------- Armado de cadena para tabla TBL_FormerRiskAndDamages  ----------
			damageRisk += "<ROOT>";
				if (p_pojoHistorical.hasFloodRisk()) {
					damageRisk += "<RISK Flag='R'><VALUE Record='" + Constants.FLOOD + "'/></RISK>";
				}
				if (p_pojoHistorical.hasHurricanRisk()) {
					damageRisk += "<RISK Flag='R'><VALUE Record='" + Constants.HURRICANE + "'/></RISK>";
				}
				if (p_pojoHistorical.hasFloodRisk()) {
					damageRisk += "<RISK Flag='R'><VALUE Record='" + Constants.COLLAPSE + "'/></RISK>";
				}
				if (p_pojoHistorical.hasEarthQuakeRisk()) {
					damageRisk += "<RISK Flag='R'><VALUE Record='" + Constants.EARTHQUACKE + "'/></RISK>";
				}
				if (p_pojoHistorical.hasSlidingRisk()) {
					damageRisk += "<RISK Flag='R'><VALUE Record='" + Constants.LANDSLIDE+ "'/></RISK>";
				}
			damageRisk += "</ROOT>";
			//Cierre de la cadena XML para TBL_FormerRiskAndDamages
			
			// ---------- Armado de cadena para tabla TBL_AnnualAmount  ----------
			//DateOperations oDateOperations = new DateOperations();
			currentYear = DateOperations.getCurrentYear();
			for (int y=2013; y>=2003; y--) {
				if (p_pojoHistorical.getSiniestrality()[y-2003].compareTo("0.0") != 0) {
					annualSiniestrality += ("<SINIESTRALITY Year='" + String.valueOf(currentYear-y) + "'>" +
										"<AMOUNT Value='" + p_pojoHistorical.getSiniestrality()[y-2003] +
										"' Description='" + p_pojoHistorical.getSiniestralityDesc()[y-2003] + "'/>"
										+ "</SINIESTRALITY>");
				}
			}
			if (annualSiniestrality.length()!=0)
				annualSiniestrality = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?><ROOT>" + annualSiniestrality + "</ROOT>"; 
			//Cierre de la cadena XML para siniestralidad

			//Inserción del nuevo registro
			//errMsg = oVWProperties.insertNewProperty(generalFeatures, damageRisk, annualSiniestrality, features);
			String[] var = {generalFeatures, damageRisk, annualSiniestrality, features} ;
			return var;

		} else {
			return null;
		}
	}

	public void processHistoricalSave(pojoHistorical p_pojoHistorical)
	{
		dataBase = new DatabaseHandler(context);

		if (p_pojoHistorical.getPropertyID().equals(Constants.GENERICO)){
			p_pojoHistorical.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(p_pojoHistorical.getPropertyID(), Constants.HISTORICAL, p_pojoHistorical.getBuildingNumber(), p_pojoHistorical.getManagedBy(), p_pojoHistorical.getBuildingName(), p_pojoHistorical.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, p_pojoHistorical.getPostalCodeID(), "", 1);
		} else {
			dataBase.actualizarProperty(p_pojoHistorical.getPropertyID(), p_pojoHistorical.getBuildingNumber(), p_pojoHistorical.getManagedBy(), p_pojoHistorical.getBuildingName(), p_pojoHistorical.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, p_pojoHistorical.getPostalCodeID(), "", 1);
			dataBase.eliminaPropiertyFeatures(p_pojoHistorical.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(p_pojoHistorical.getPropertyID());
			dataBase.eliminaAnnualAmount(p_pojoHistorical.getPropertyID());
		}
		// Validación de todas las características
		if (p_pojoHistorical.getManagedBy().length()>0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), p_pojoHistorical.getManagedBy(), "0");
		if (p_pojoHistorical.getWaterBody().length()>0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), p_pojoHistorical.getWaterBody(), "0");

		if (p_pojoHistorical.getRlBuildingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_BUILDING, p_pojoHistorical.getRlBuildingValue().toString());
		if (p_pojoHistorical.getRnBuildingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_BUILDING, p_pojoHistorical.getRnBuildingValue().toString());
		if (p_pojoHistorical.getRlComputingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_COMPUTE_EQUIPMENT, p_pojoHistorical.getRlComputingValue().toString());
		if (p_pojoHistorical.getRnComputingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_COMPUTE_EQUIPMENT, p_pojoHistorical.getRnComputingValue().toString());
		if (p_pojoHistorical.getRlElectronicValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_ELECTRONIC_EQUIPMENT, p_pojoHistorical.getRlElectronicValue().toString());
		if (p_pojoHistorical.getRnElectronicValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_ELECTRONIC_EQUIPMENT, p_pojoHistorical.getRnElectronicValue().toString());
		if (p_pojoHistorical.getRlBooksValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_BOOKS_INVENTORY, p_pojoHistorical.getRlBooksValue().toString());
		if (p_pojoHistorical.getRnBooksValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_BOOKS_INVENTORY, p_pojoHistorical.getRnBooksValue().toString());
		if (p_pojoHistorical.getRlCDsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_CDS, p_pojoHistorical.getRlCDsValue().toString());
		if (p_pojoHistorical.getRnCDsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_CDS, p_pojoHistorical.getRnCDsValue().toString());
		if (p_pojoHistorical.getRlStorageValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_MEDIA_STORAGE, p_pojoHistorical.getRlStorageValue().toString());
		if (p_pojoHistorical.getRnStorageValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_MEDIA_STORAGE, p_pojoHistorical.getRnStorageValue().toString());
		if (p_pojoHistorical.getRlFurnituresValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_FURNITURES, p_pojoHistorical.getRlFurnituresValue().toString());
		if (p_pojoHistorical.getRnFurnituresValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_FURNITURES, p_pojoHistorical.getRnFurnituresValue().toString());
		if (p_pojoHistorical.getRlItemsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RL_ITEMS, p_pojoHistorical.getRlItemsValue().toString());
		if (p_pojoHistorical.getRnItemsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.RN_ITEMS, p_pojoHistorical.getRnItemsValue().toString());
		if (p_pojoHistorical.getLatitude() != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.LATITUDE, Double.toString(p_pojoHistorical.getLatitude()));
		if (p_pojoHistorical.getLongitude() != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.LONGITUDE, Double.toString(p_pojoHistorical.getLongitude()));
		if (p_pojoHistorical.getAltitude() != 0)
			dataBase.insertPropertyFeatures(p_pojoHistorical.getPropertyID(), Constants.ALTITUDE, Double.toString(p_pojoHistorical.getAltitude()));

		// Para TBL_FormerDamageAndRisk
		if (p_pojoHistorical.hasFloodRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoHistorical.getPropertyID(), "R", Constants.FLOOD);
		}
		if (p_pojoHistorical.hasHurricanRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoHistorical.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (p_pojoHistorical.hasRockFallRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoHistorical.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (p_pojoHistorical.hasEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoHistorical.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (p_pojoHistorical.hasSlidingRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoHistorical.getPropertyID(), "R", Constants.LANDSLIDE);
		}

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++){
			if (p_pojoHistorical.getSiniestrality()[y].compareTo("0.0") == 1) 
				dataBase.insertAnnualAmount(p_pojoHistorical.getPropertyID(), String.valueOf(currentYear-y), p_pojoHistorical.getSiniestrality()[y].toString(), p_pojoHistorical.getSiniestralityDesc()[y]);
		}

	}

	public void processEducationSave(pojoEducation p_pojoEducation){
		dataBase = new DatabaseHandler(context);

		if (p_pojoEducation.getPropertyID().equals(Constants.GENERICO)){
			p_pojoEducation.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(p_pojoEducation.getPropertyID(), Constants.EDUCATION, p_pojoEducation.getBuildingNumber(), "Use", p_pojoEducation.getBuildingName(), p_pojoEducation.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, p_pojoEducation.getPostalCodeID(), "", 1);
		} else {
			dataBase.actualizarProperty(p_pojoEducation.getPropertyID(), p_pojoEducation.getBuildingNumber(), "Use", p_pojoEducation.getBuildingName(), p_pojoEducation.getAddress(), 1, 1, 1, 1, p_pojoEducation.getReinformcementDate(), "User", "Deleted", "Justification", Constants.GENERICO, p_pojoEducation.getPostalCodeID(), "1", 1);
			dataBase.eliminaPropiertyFeatures(p_pojoEducation.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(p_pojoEducation.getPropertyID());
			dataBase.eliminaAnnualAmount(p_pojoEducation.getPropertyID());
		}

		if (p_pojoEducation.getStructureType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getStructureType(), "0");
		if (p_pojoEducation.isRegularGeometr())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.REGULAR_GEOMETRY, "0");
		else if (p_pojoEducation.isIrregularGeometry())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.IRREGULAR_GEOMETRY, "0");
		if (p_pojoEducation.getFoundationType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getFoundationType(), "0");
		if (p_pojoEducation.getWalls().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getWalls(), "0");
		if (p_pojoEducation.getStoryMaterial().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getStoryMaterial(), "0");
		if (p_pojoEducation.getStoryStructure().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getStoryStructure(), "0");
		if (p_pojoEducation.getFachadeType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getFachadeType(), "0");
		if (p_pojoEducation.getFachadeMaterial().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getFachadeMaterial(), "0");
		if (p_pojoEducation.getGlassWareType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getGlassWareType(), "0");
		if (p_pojoEducation.getBlacksmithType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getBlacksmithType(), "0");
		if (p_pojoEducation.getCoverType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCoverType(), "0");
		if (p_pojoEducation.getCoverShape().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCoverShape(), "0");
		if (p_pojoEducation.getCnStExternal().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStExternal(), "0");
		if (p_pojoEducation.getCnStInternal().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStInternal(), "0");
		if (p_pojoEducation.getCnStCeiling().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStCeiling(), "0");
		if (p_pojoEducation.getCnStTrimmings().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStTrimmings(), "0");
		if (p_pojoEducation.getCnStFloors().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStFloors(), "0");
		if (p_pojoEducation.getCnStBaseboards().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStBaseboards(), "0");
		if (p_pojoEducation.getCnStCarpintery().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStCarpintery(), "0");
		if (p_pojoEducation.getCnStCancel().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStCancel(), "0");
		if (p_pojoEducation.getCnStPainting().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStPainting(), "0");
		if (p_pojoEducation.getCnStSanitaryFurn().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStSanitaryFurn(), "0");
		if (p_pojoEducation.getCnStPlumbing().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStPlumbing(), "0");
		if (p_pojoEducation.getCnStElectricInst().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getCnStElectricInst(), "0");
		if (p_pojoEducation.getEducationType().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getEducationType(), "0");
		if (p_pojoEducation.getFachadeGlassSize().length()>0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), p_pojoEducation.getFachadeGlassSize(), "0");
		if (p_pojoEducation.isCloseToPole())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.PHONE_ELECTRIC_POLE, "0");
		if (p_pojoEducation.isCloseToAds())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.SPECTACULAR_ADS, "0");
		if (p_pojoEducation.isCloseToTrees())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.TREES, "0");
		if (p_pojoEducation.isRoofItems())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.OBJECTS_ON_ROOFS, "0");
		if (p_pojoEducation.isDomes())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.HAS_DOMES, "0");
		if (p_pojoEducation.isExternalStrPounding())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.EXTERNAL_STRUCTURE_POUNDING, "0");
		if (p_pojoEducation.isShortWalls())
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.SHORT_WALLS_BETWEEN_COLUMNS, "0");
		if (p_pojoEducation.isSinkings())
		{
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.SINKING_STRUCTURE, "0");
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.SINKING_SEVERITY, Integer.toString(p_pojoEducation.getSinkingSeverity()));
		}

		if (p_pojoEducation.getLatitude() != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.LATITUDE, Double.toString(p_pojoEducation.getLatitude()));
		if (p_pojoEducation.getLongitude() != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.LONGITUDE, Double.toString(p_pojoEducation.getLongitude()));
		if (p_pojoEducation.getAltitude() != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.ALTITUDE, Double.toString(p_pojoEducation.getAltitude()));
		if (p_pojoEducation.getSpecialFacilities().length() > 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.SPECIALFACILITIES, p_pojoEducation.getSpecialFacilities().toString());
		if (p_pojoEducation.getRlBuildingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_BUILDING, p_pojoEducation.getRlBuildingValue().toString());
		if (p_pojoEducation.getRnBuildingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_BUILDING, p_pojoEducation.getRnBuildingValue().toString());
		if (p_pojoEducation.getRlComputingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_COMPUTE_EQUIPMENT, p_pojoEducation.getRlComputingValue().toString());
		if (p_pojoEducation.getRnComputingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_COMPUTE_EQUIPMENT, p_pojoEducation.getRnComputingValue().toString());
		if (p_pojoEducation.getRlElectronicValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_ELECTRONIC_EQUIPMENT, p_pojoEducation.getRlElectronicValue().toString());
		if (p_pojoEducation.getRnElectronicValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_ELECTRONIC_EQUIPMENT, p_pojoEducation.getRnElectronicValue().toString());
		if (p_pojoEducation.getRlBooksValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_BOOKS_INVENTORY, p_pojoEducation.getRlBooksValue().toString());
		if (p_pojoEducation.getRnBooksValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_BOOKS_INVENTORY, p_pojoEducation.getRnBooksValue().toString());
		if (p_pojoEducation.getRlCDsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_CDS, p_pojoEducation.getRlCDsValue().toString());
		if (p_pojoEducation.getRnCDsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_CDS, p_pojoEducation.getRnCDsValue().toString());
		if (p_pojoEducation.getRlStorageValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_MEDIA_STORAGE, p_pojoEducation.getRlStorageValue().toString());
		if (p_pojoEducation.getRnStorageValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_MEDIA_STORAGE, p_pojoEducation.getRnStorageValue().toString());
		if (p_pojoEducation.getRlFurnituresValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_FURNITURES, p_pojoEducation.getRlFurnituresValue().toString());
		if (p_pojoEducation.getRnFurnituresValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_FURNITURES, p_pojoEducation.getRnFurnituresValue().toString());
		if (p_pojoEducation.getRlItemsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RL_ITEMS, p_pojoEducation.getRlItemsValue().toString());
		if (p_pojoEducation.getRnItemsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.RN_ITEMS, p_pojoEducation.getRnItemsValue().toString());

		if (Integer.valueOf(p_pojoEducation.getElevation()) > 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.ELEVATION_FLAT_IRREGULARITY, p_pojoEducation.getElevation().toString());
		if (p_pojoEducation.getWindowProtectionType().length() > 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.WINDOWS_PROTECTION, p_pojoEducation.getWindowProtectionType());
		if (p_pojoEducation.getDomeProtectionType().length() > 0)
			dataBase.insertPropertyFeatures(p_pojoEducation.getPropertyID(), Constants.DOMES_PROTECTION, p_pojoEducation.getDomeProtectionType());

		// Para TBL_FormerDamageAndRisk
		if (p_pojoEducation.isFloodFormer()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "D", Constants.FLOOD);
		}
		if (p_pojoEducation.isHurricanFormer()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "D", Constants.HURRICANE);
		}
		if (p_pojoEducation.isFloodFormer()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "D", Constants.COLLAPSE);
		}
		if (p_pojoEducation.isEarthQuakeFormer()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "D", Constants.EARTHQUACKE);
		}
		if (p_pojoEducation.isSlidingFormer()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "D", Constants.LANDSLIDE);
		}

		if (p_pojoEducation.isFloodRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "R", Constants.FLOOD);
		}
		if (p_pojoEducation.isHurricanRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (p_pojoEducation.isFloodRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (p_pojoEducation.isEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (p_pojoEducation.isSlidingRisk()) {
			dataBase.insertFormerDamageAndRisk(p_pojoEducation.getPropertyID(), "R", Constants.LANDSLIDE);
		}

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++) {
			if (p_pojoEducation.getSiniestralityValue()[y].compareTo("0.0") != 0)
				dataBase.insertAnnualAmount(p_pojoEducation.getPropertyID(), String.valueOf(currentYear-y), p_pojoEducation.getSiniestralityValue()[y].toString(), p_pojoEducation.getSiniestralityDescription()[y]);
		}

	}

	public void processDamSave(pojoDams pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(pojo.getPropertyID(), Constants.DAMS, "1", "Use", pojo.getOfficialNumber(), pojo.getAddress(), 1, 1, 1, 1, pojo.getConstructionDate(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), pojo.getReinforcementDate(), 1);
		} else {
			dataBase.actualizarProperty(pojo.getPropertyID(), "1", "Use", pojo.getOfficialNumber(), pojo.getAddress(), 1, 1, 1, 1, pojo.getConstructionDate(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), pojo.getReinforcementDate(), 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}


		if(pojo.getSismicity().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSismicity(), "1");
		if(pojo.getAccessWay().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getAccessWay(), "1");
		if(pojo.getContructionPurpouse().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getContructionPurpouse(), "1");
		if(pojo.getWaterUser().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWaterUser(), "1");
		if(pojo.getClosesWaterBody().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getClosesWaterBody(), "1");
		if(pojo.getSoilType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSoilType(), "1");
		if(pojo.getStructuralType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStructuralType(), "1");
		if(pojo.getFoundationType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFoundationType(), "1");
		if(pojo.getCurrentsTypes().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCurrentsTypes(), "1");
		if(pojo.getSubSoilType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSubSoilType(), "1");
		if(pojo.getSubSoilType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSubSoilType(), "1");
		if(pojo.isGeometryRegular())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.REGULAR_GEOMETRY, "0");
		else if(pojo.isGeometryIrregular())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.IRREGULAR_GEOMETRY, "0");
		if(pojo.isCofferDam())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.COFFERDAM_STRUCTURE, "1");
		if (pojo.isSinkingPresence())
		{
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_STRUCTURE, "0");
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_SEVERITY, Integer.toString(pojo.getSinkingSeverity()));
		}
		if (pojo.getCondition().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCondition(), "0");
		if (pojo.isPounding())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.EXTERNAL_STRUCTURE_POUNDING, "0");
		if(pojo.isShortWallsbeneathColumns())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SHORT_WALLS_BETWEEN_COLUMNS, "1");
		if(pojo.isSprinkler())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SPRINKLERS, "1");
		if(pojo.isHydrant())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.HYDRANTS, "1");
		if(pojo.isTank())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.TANK, "1");
		if(pojo.isExtintor())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.EXTINTORES, "1");
		if(pojo.getDumpLocation().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getDumpLocation(), "0");
		if(pojo.getMachineRoomeLocation().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getMachineRoomeLocation(), "0");

		if (pojo.getDamName().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.COMUN_NAME, pojo.getDamName());
		if (pojo.getCNARegion().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CNA_REGION, pojo.getCNARegion());
		if (pojo.getHidrologicRegion().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.HIDROLOGIC_ZONE, pojo.getHidrologicRegion());
		if (pojo.getPumb().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CATCHMENT_AREA, pojo.getPumb());
		if (pojo.getCurrent().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.FLOW, pojo.getCurrent());
		if (pojo.getAfluent().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.TRIBUTARY, pojo.getAfluent());
		if (pojo.getNAMOVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAMO_VOL, Double.toString(pojo.getNAMOVolume()));
		if (pojo.getDeseigner().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DESIGNER, pojo.getDeseigner());
		if (pojo.getConstructor().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CONSTRUCTOR, pojo.getConstructor());
		if (pojo.getResponsibleOrg().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RESPONSIBLE, pojo.getResponsibleOrg());
		if (pojo.getSurface() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SURFACE, Double.toString(pojo.getSurface()));
		if (pojo.getMaxAnnualRunoff() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ANUAL_MAX_RUNOFF_VOL, Double.toString(pojo.getMaxAnnualRunoff()));
		if (pojo.getAvgAnnualRunoff() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ANUAL_AVG_RUNOFF_VOL, Double.toString(pojo.getAvgAnnualRunoff()));
		if (pojo.getLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getLatitude()));
		if (pojo.getLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getLongitude()));
		if (pojo.getAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getAltitude()));
		if (pojo.getMaxAnnualPrecipitation() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ANUAL_PRECIPITATION, Double.toString(pojo.getMaxAnnualPrecipitation()));
		if (pojo.getMaxMonthlyPrecipitation() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.MONTHLY_PRECIPITATION, Double.toString(pojo.getMaxMonthlyPrecipitation()));
		if (pojo.getMaxDailyPrecipitaion() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAILY_PRECIPITATION, Double.toString(pojo.getMaxDailyPrecipitaion()));
		if (pojo.getElevation().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ELEVATION_FLAT_IRREGULARITY, pojo.getElevation());
		if (pojo.getNAME() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAME, Double.toString(pojo.getNAME()));
		if (pojo.getNAMO() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAMO, Double.toString(pojo.getNAMO()));
		if (pojo.getNAMINO() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAMINO, Double.toString(pojo.getNAMINO()));
		if (pojo.getNAMEVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAME_VOL, Double.toString(pojo.getNAMEVolume()));
		if (pojo.getNAMOVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAMO_VOL, Double.toString(pojo.getNAMOVolume()));
		if (pojo.getNAMINOVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.NAMINO_VOL, Double.toString(pojo.getNAMINOVolume()));
		if (pojo.getSedimentationVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SEDIMENTATION_VOL, Double.toString(pojo.getSedimentationVolume()));
		if (pojo.getUsefulVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.USEFUL_VOL, Double.toString(pojo.getUsefulVolume()));
		if (pojo.getSuperStorage() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OVER_STORAGE, Double.toString(pojo.getSuperStorage()));
		if (pojo.getConservationVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CONSERVATION_VOL, Double.toString(pojo.getConservationVolume()));
		if (pojo.getFloodControlVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CURRENT_CONTROL_VOL, Double.toString(pojo.getFloodControlVolume()));
		if (pojo.getDeseignMaxCurrent() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DESIGN_MAX_FLOW, Double.toString(pojo.getDeseignMaxCurrent()));
		if (pojo.getReturnPeriod() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RETURN_PERIOD, Double.toString(pojo.getReturnPeriod()));
		if (pojo.getDeseignFloodVolume() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DESIGN_FLOW_VOL, Double.toString(pojo.getDeseignFloodVolume()));
		if (pojo.getMaxCurrentRegistered() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.MAX_FLOW_VOL_REGISTERED, Double.toString(pojo.getMaxCurrentRegistered()));
		if (pojo.getMaxFloodRegistered() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.MAX_FLOW_REGISTERED, Double.toString(pojo.getMaxFloodRegistered()));
		if (pojo.getWaterDownChannelCapacity() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.FLOW_CAPACITY, Double.toString(pojo.getWaterDownChannelCapacity()));
		if (pojo.getSpecialOperationInstructions().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SPECIAL_OP_INSTRUCTIONS, pojo.getSpecialOperationInstructions());
		if (pojo.getComments().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.COMMENTS, pojo.getComments());
		if (pojo.getWorkShopValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_WORKSHOP, pojo.getWorkShopValue());
		if (pojo.getWarehouseValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_WAREHOUSE, pojo.getWarehouseValue());
		if (pojo.getConductionTunnelValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_CONDUTION_DUCTS, pojo.getConductionTunnelValue());
		if (pojo.getDamBodyValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_DAM_BODY, pojo.getDamBodyValue());
		if (pojo.getSpillwayValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_SPILLWAY, pojo.getSpillwayValue());
		if (pojo.getIntakeWorksValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_INOUT_WORKS, pojo.getIntakeWorksValue());
		if (pojo.getMachineRoomsValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_MACHINEROOM, pojo.getMachineRoomsValue());
		if (pojo.getEMFacilitiesValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_EM_INSTALATION, pojo.getEMFacilitiesValue());
		if (pojo.getElectricDuctsValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_ELECTRICAL_DUCTS, pojo.getElectricDuctsValue());
		if (pojo.getGeometySpecitication().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.IRREGULAR_DETAIL, pojo.getGeometySpecitication());
		if (pojo.getDamLength() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_LENGTH, Double.toString(pojo.getDamLength()));
		if (pojo.getDamHeight() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_HEIGHT, Double.toString(pojo.getDamHeight()));
		if (pojo.getBaseWidth() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_BASE_WIDTH, Double.toString(pojo.getBaseWidth()));
		if (pojo.getCrownWidth() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_CROWN_WIDTH, Double.toString(pojo.getCrownWidth()));
		if (pojo.getDamUpStreamSlope() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_UPSTREAM_SLOPE, Double.toString(pojo.getDamUpStreamSlope()));
		if (pojo.getDamDownStreamSlope() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_DOWNSTREAM_SLOPE, Double.toString(pojo.getDamDownStreamSlope()));
		if (pojo.getINEGILetter().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.INEGI_LETTER, pojo.getINEGILetter());
		if (pojo.getCurrentLevels() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_FLOW_LEVEL, Double.toString(pojo.getCurrentLevels()));

		if (pojo.getCurrentWaterQtty() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DAM_FLOW_QTTY, Double.toString(pojo.getCurrentWaterQtty()));
//		if (pojo.getCurrentWaterQtty() != 0)
//			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WATER_DOWN_DAM, Double.toString(pojo.getCurrentWaterQtty()));
//		features += ("<FEATURES Record='" + com.risk.console.misc.Constants.WATER_DOWN_DAM + "'><AMOUNT Values='" + 
//				request.getParameter("hdnNewDams") + "'/></FEATURES>");
		if (pojo.getRecontructionValue().compareTo("0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_RECONSTRUCTION_UNIT_VALUE, pojo.getRecontructionValue());
		if (pojo.getCofferdamCapacity().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.COFFERDAM_CAPACITY, pojo.getCofferdamCapacity());

	}

	public void processDamSiniestrFormerAndRiskSave(pojoDams pojo){
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
		dataBase.eliminaAnnualAmount(pojo.getPropertyID());

		if (pojo.isFleedFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.FLOOD);
		}
		if (pojo.isHurricaneFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.HURRICANE);
		}
		if (pojo.isCollapseFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.EARTHQUACKE);
		}
		if (pojo.isLandSlideFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.LANDSLIDE);
		}
	
		if (pojo.isFleedRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.FLOOD);
		}
		if (pojo.isHurricaneRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (pojo.isCollapseRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (pojo.isLandSlideRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.LANDSLIDE);
		}
	
		int currentYear = DateOperations.getCurrentYear();
	
		for (int y=0; y<10; y++) {
			if (pojo.getSiniestralityValue()[y].compareTo("0.0") != 0)
				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestralityValue()[y].toString(), pojo.getSiniestralityDescription()[y]);
		}

	}

	public void processAquacultureSave(pojoAquaculture pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(pojo.getPropertyID(), Constants.AQUACULTURE, "1", "Use", pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), "", 1);
		} else {
			dataBase.actualizarProperty(pojo.getPropertyID(), "1", "Use", pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), "", 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}

		if(pojo.getCenterType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCenterType(), "0");
		if(pojo.getStructureType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStructureType(), "0");
		if(pojo.getFoundationType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFoundationType(), "0");
		if(pojo.getWallsType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getWallsType(), pojo.getWallsType(), "0");
		if(pojo.getFachadeType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getFachadeType(), pojo.getFachadeType(), "0");
		if(pojo.getGlasseryType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getFachadeType(), pojo.getGlasseryType(), "0");
		if(pojo.getBlackSmithType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getFachadeType(), pojo.getBlackSmithType(), "0");

		if (pojo.getSpecialFacilities().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SPECIALFACILITIES, pojo.getSpecialFacilities());
		if (pojo.getRnBuildingDescription().length()>0) // BUILDINGDESC, buildingDescription o getRnBuildingDescription()
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.BUILDINGDESC, pojo.getRnBuildingDescription());
		if (pojo.getRlBuildingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_BUILDING, pojo.getRlBuildingValue().toString());
		if (pojo.getRnBuildingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_BUILDING, pojo.getRnBuildingValue().toString());

		if (pojo.getRnTankRehabDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.TANKREHABDESC, pojo.getRnTankRehabDescription());
		if (pojo.getRlTankRehabValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_REHAB_TANKS, pojo.getRlTankRehabValue().toString());
		if (pojo.getRnTankRehabValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_REHAB_TANKS, pojo.getRnTankRehabValue().toString());

		if (pojo.getRnCancelDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CANCELDESC, pojo.getRnCancelDescription());
		if (pojo.getRlCancelValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_CANCEL, pojo.getRlCancelValue().toString());
		if (pojo.getRnCancelValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_CANCEL, pojo.getRnCancelValue().toString());

		if (pojo.getRnMeshDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.MESHDESC, pojo.getRnMeshDescription());
		if (pojo.getRlMeshValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_MESH, pojo.getRlMeshValue().toString());
		if (pojo.getRnMeshValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_MESH, pojo.getRnMeshValue().toString());

		if (pojo.getRnPaintingDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.PAINTINGDESC, pojo.getRnPaintingDescription());
		if (pojo.getRlPaintingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_PAINTING, pojo.getRlPaintingValue().toString());
		if (pojo.getRnPaintingValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_PAINTING, pojo.getRnPaintingValue().toString());

		if (pojo.getRnRoofsDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ROOFSDESC, pojo.getRnRoofsDescription());
		if (pojo.getRlRoofsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_ROOFS, pojo.getRlRoofsValue().toString());
		if (pojo.getRnRoofsValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_ROOFS, pojo.getRnRoofsValue().toString());

		if (pojo.getRnFeedNetCDDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.FEEDNETDESC, pojo.getRnFeedNetCDDescription());
		if (pojo.getRlFeedNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_FEED_NETWARE, pojo.getRlFeedNetCDValue().toString());
		if (pojo.getRnFeedNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_FEED_NETWARE, pojo.getRnFeedNetCDValue().toString());

		if (pojo.getRnFeedNetCDDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CONDUCTIONNETDESC, pojo.getRnFeedNetCDDescription());
		if (pojo.getRlFeedNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_CONDUCTION_NETWARE, pojo.getRlFeedNetCDValue().toString());
		if (pojo.getRnFeedNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_CONDUCTION_NETWARE, pojo.getRnFeedNetCDValue().toString());

		if (pojo.getRnElectricNetCDDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ELECTRICNETCDDESC, pojo.getRnElectricNetCDDescription());
		if (pojo.getRlElectricNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_EM_NET_CONDUCTION, pojo.getRlElectricNetCDValue().toString());
		if (pojo.getRnElectricNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_EM_NET_CONDUCTION, pojo.getRnElectricNetCDValue().toString());

		if (pojo.getSanitaryNetDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SANITARYNETDESC, pojo.getSanitaryNetDescription());
		if (pojo.getRlSanitaryNetValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_SANIARY_NET_SANITATION, pojo.getRlSanitaryNetValue().toString());
		if (pojo.getRnSanitaryNetValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_SANIARY_NET_SANITATION, pojo.getRnSanitaryNetValue().toString());

		if (pojo.getSanitaryInstalDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SANITARYINSTALDESC, pojo.getSanitaryNetDescription());
		if (pojo.getRlSanitaryIntallValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_SANITARY_INSTALATION, pojo.getRlSanitaryNetValue().toString());
		if (pojo.getRnSanitaryIntallValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_SANITARY_INSTALATION, pojo.getRnSanitaryNetValue().toString());

		if (pojo.getEMNetDescription().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.EMNETDESC, pojo.getEMNetDescription());
		if (pojo.getRlElectricNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_EM_SANITATION, pojo.getRlElectricNetCDValue().toString());
		if (pojo.getRnElectricNetCDValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_EM_SANITATION, pojo.getRnSanitaryNetValue().toString());

		if (pojo.getLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getLatitude()));
		if (pojo.getLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getLongitude()));
		if (pojo.getAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getAltitude()));

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++){
			if (pojo.getSiniestrality()[y].compareTo("0.0") != 0)
				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestrality()[y].toString(), pojo.getSiniestralityDesc()[y]);
		}

	}

	public void processHealtSave(pojoHealt pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(pojo.getPropertyID(), Constants.HEALT, "1", "Use", pojo.getName(), pojo.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), pojo.getConstructionDate(), 1);
		} else {
			//dataBase.actualizarProperty(pojo.getPropertyID(), pojo.getBuildingNumber(), pojo.getManagedBy(), pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, "Fecha", "User", "Deleted", "Justification", "ParentID", pojo.getPostalCodeID(), 1, 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}

		if(pojo.getStructuraltype().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStructuraltype(), "0");
		if(pojo.getFoundingType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFoundingType(), "0");
		if(pojo.getWallsType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWallsType(), "0");
		if(pojo.getFachadeType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFachadeType(), "0");
		if(pojo.getFachadeMaterial().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFachadeMaterial(), "0");
		if(pojo.getConservationState().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getConservationState(), "0");
		if(pojo.getFachadeGlasseryType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFachadeGlasseryType(), "0");		
		if(pojo.getFachadaGlasserySize().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFachadaGlasserySize(), "0");
		if(pojo.getCoverType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCoverType(), "0");
		if(pojo.getCoverShape().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCoverShape(), "0");
		if(pojo.getSoilType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSoilType(), "0");
		if(pojo.isGeometryRegular())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.REGULAR_GEOMETRY, "0");
		else if(pojo.isGeometryIrregular())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.IRREGULAR_GEOMETRY, "0");
		if(pojo.isObjectsInRoof())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OBJECTS_ON_ROOFS, "0");
		if(pojo.isDome())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.HAS_DOMES, "0");
		if(pojo.isHitting())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.EXTERNAL_STRUCTURE_POUNDING, "0");
		if(pojo.isShortWalls())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SHORT_WALLS_BETWEEN_COLUMNS, "0");
		if(pojo.isCloseToPoles())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.PHONE_ELECTRIC_POLE, "0");
		if(pojo.isCloseToAds())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SPECTACULAR_ADS, "0");
		if(pojo.isCloseToTrees())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.TREES, "0");
		if(pojo.isSinkingPresence())
		{
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_STRUCTURE, "0");
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_SEVERITY, Integer.toString(pojo.getSinkingSeverity()));
		}

		if (pojo.getWindowProtection().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WINDOWS_PROTECTION, pojo.getWindowProtection());
		if (pojo.getDomeProtection().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DOMES_PROTECTION, pojo.getDomeProtection());
		if (pojo.getElevation().compareTo("0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ELEVATION_FLAT_IRREGULARITY, pojo.getElevation());
		if (pojo.getLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getLatitude()));
		if (pojo.getLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getLongitude()));
		if (pojo.getAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getAltitude()));

		if (pojo.getBuilningRenewalValue().compareTo( BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_BUILDING, pojo.getBuilningRenewalValue().toString());
		if (pojo.getMedicalRenewalValue().compareTo( BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_MEDICAL_INSTUMENTS, pojo.getMedicalRenewalValue().toString());
		if (pojo.getLabMaterialRenewalValue().compareTo( BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_LAB_EQUIPMENT, pojo.getLabMaterialRenewalValue().toString());
		if (pojo.getElectronicRenewalValue().compareTo( BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_COMPUTE_EQUIPMENT, pojo.getElectronicRenewalValue().toString());
		if (pojo.getItemRenewalValue().compareTo( BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_ITEMS, pojo.getItemRenewalValue().toString());

		if (pojo.isFloodFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.FLOOD);
		}
		if (pojo.isHurricaneFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.HURRICANE);
		}
		if (pojo.isCollapseFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.EARTHQUACKE);
		}
		if (pojo.isLandSlideFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.LANDSLIDE);
		}

		if (pojo.isFloodRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.FLOOD);
		}
		if (pojo.isHurricaneRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (pojo.isCollapseRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (pojo.isLandSlideRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.LANDSLIDE);
		}

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++) {
			if (pojo.getSiniestralityValues()[y].compareTo("0.0") != 0)
				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestralityValues()[y].toString(), pojo.getSiniestralityDescription()[y]);
		}

	}

	public void processHydraulicSave(pojoHidraulic pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(pojo.getPropertyID(), Constants.HIDRAULIC, "1", "Use", pojo.getName(), pojo.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), pojo.getBuildingDate(), 1);
		} else {
			//dataBase.actualizarProperty(pojo.getPropertyID(), pojo.getBuildingNumber(), pojo.getManagedBy(), pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, "Fecha", "User", "Deleted", "Justification", "ParentID", pojo.getPostalCodeID(), 1, 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}

		if (pojo.getClosestWaterBody().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getClosestWaterBody(), "0");
		if(pojo.getSturcturaltype().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSturcturaltype(), "0");
		if(pojo.getFoundationType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFoundationType(), "0");
		if(pojo.getWallsType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWallsType(), "0");
		if(pojo.getSoilType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSoilType(), "0");
		if(pojo.getDrinkingWaterNet().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getDrinkingWaterNet(), "0");
		if(pojo.getDrainNet().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getDrainNet(), "0");
		if(pojo.getCatchmentType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCatchmentType(), "0");
		if(pojo.getWaterTreatmentPlantType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWaterTreatmentPlantType(), "0");
		if(pojo.getTreatedWaterStorageType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getTreatedWaterStorageType(), "0");
		if(pojo.getPipesType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getPipesType(), "0");
		if(pojo.getChannelsType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getChannelsType(), "0");
		if(pojo.getCrossType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCrossType(), "0");
		if(pojo.getDamType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getDamType(), "0");
		if(pojo.getPumpingStation().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getPumpingStation(), "0");
		if(pojo.getGatesTypes().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getGatesTypes(), "0");
		if(pojo.getSewerNet().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getSewerNet(), "0");
		if(pojo.getIntakeType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getIntakeType(), "0");
		if(pojo.getHidraulicComponents().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getHidraulicComponents(), "0");
		if(pojo.getNonHidraulicComponents().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getNonHidraulicComponents(), "0");
		if(pojo.getStructuralMedia().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStructuralMedia(), "0");

		if(pojo.getDriveLines() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SEWER_DRIVE_LINE, Double.toString(pojo.getDriveLines()));
		if(pojo.getDriveLines() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SEWER_DRIVE_LINE, Double.toString(pojo.getDriveLines()));

		if (pojo.getLatidude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getLatidude()));
		if (pojo.getLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getLongitude()));
		if (pojo.getAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getAltitude()));

		if(pojo.getSewers() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SEWER_NUMBER, Double.toString(pojo.getSewers()));
		if(pojo.getColectors() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_COLECTORS, Double.toString(pojo.getColectors()));
		if(pojo.getInterceptors() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_INTERCEPTORS, Double.toString(pojo.getInterceptors()));
		if(pojo.getWasteWaterTreamtentPlants() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_TREATMENT_STATIONS, Double.toString(pojo.getWasteWaterTreamtentPlants()));
		if(pojo.getSpouts() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_LANDFILLS, Double.toString(pojo.getSpouts()));
		if(pojo.getGuttersTrenchCaceres() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_GUTTER_TRENCH, Double.toString(pojo.getGuttersTrenchCaceres()));
		if(pojo.getHoppers() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_HOPPERS, Double.toString(pojo.getHoppers()));
		if(pojo.getManholes() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.INSPECTION_WELL, Double.toString(pojo.getManholes()));
		if(pojo.getSewersPumpingStation() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_PUMPING_STATIONS, Double.toString(pojo.getSewersPumpingStation()));
		if(pojo.getSewerContainer() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SW_RETENTION_TANKS, Double.toString(pojo.getSewerContainer()));

		if(pojo.getAnnualMaintenance().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_MAINTENANCE_AMOUNT, pojo.getAnnualMaintenance().toString());
		if(pojo.getRenewalValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_BUILDING, pojo.getRenewalValue().toString());
		if(pojo.getCivilWorksValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_CIVIL_WORKS_VALUE, pojo.getCivilWorksValue().toString());
		if(pojo.getEquipmentMachineryValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_MACHINERY_VALUE, pojo.getEquipmentMachineryValue().toString());
		if(pojo.getItemsRenualValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_OTHER, pojo.getItemsRenualValue().toString());
		if(pojo.getItemsDetail().length() > 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_OTHER_DETAIL, pojo.getItemsDetail());

		if (pojo.isFloodFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.FLOOD);
		}
		if (pojo.isHurricaneFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.HURRICANE);
		}
		if (pojo.isCollapseFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuackeFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.EARTHQUACKE);
		}
		if (pojo.isLandSlideFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.LANDSLIDE);
		}

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++){
			if (pojo.getSiniestralityValues()[y].compareTo(new BigDecimal("0")) == 1) 
				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestralityValues()[y].toString(), pojo.getSiniestralityDesccriptions()[y]);
		}

	}

	public void processWaysSave(pojoWays pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(pojo.getPropertyID(), Constants.WAYS, "1", "Use", pojo.getName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), pojo.getConstructionDate(), 1);
		} else {
			//dataBase.actualizarProperty(pojo.getPropertyID(), pojo.getBuildingNumber(), pojo.getManagedBy(), pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, "Fecha", "User", "Deleted", "Justification", "ParentID", pojo.getPostalCodeID(), 1, 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}

		if (pojo.getWayType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWayType(), "0");
		//TODO
//		if (pojo.getStateID().length()>0)
//			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStateID(), "0");
		if (pojo.getClosestWaterBodyType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getClosestWaterBodyType(), "0");
		if (pojo.getCoverType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCoverType(), "0");
		if (pojo.getCondition().length()>0) // Este es en vez de StateID
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getCondition(), "0");

		if(pojo.isSinkings())
		{
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_STRUCTURE, "0");
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_SEVERITY, Integer.toString(pojo.getSinkingSeverity()));
		}

		if (pojo.getStartLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getStartLatitude()));
		if (pojo.getEndLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getEndLatitude()));
		if (pojo.getStartLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getStartLongitude()));
		if (pojo.getEndLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getEndLongitude()));
		if (pojo.getStartAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getStartAltitude()));
		if (pojo.getStartAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getStartAltitude()));

		if(pojo.getClosestWaterBodyDistanceCross() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.DISTANCE, Double.toString(pojo.getClosestWaterBodyDistanceCross()));
		if(pojo.getClosestWaterBodyStartLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WBLATITUDE, Double.toString(pojo.getClosestWaterBodyStartLatitude()));
		if(pojo.getClosestWaterBodyEndLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WBLATITUDE, Double.toString(pojo.getClosestWaterBodyEndLatitude()));
		if(pojo.getClosestWaterBodyStartLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WBLONGITUDE, Double.toString(pojo.getClosestWaterBodyStartLongitude()));
		if(pojo.getClosestWaterBodyEndLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WBLONGITUDE, Double.toString(pojo.getClosestWaterBodyEndLongitude()));
		if(pojo.getUnderWaysNumber() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.UNDERWAYSNR, Double.toString(pojo.getUnderWaysNumber()));
		if(pojo.getOverWaysNumber() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OVERWAYSNR, Double.toString(pojo.getOverWaysNumber()));
		if(pojo.getLinesNumber() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RAILS_NUMBER, Double.toString(pojo.getLinesNumber()));
		if(pojo.getTotalKms() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.KILOMETERS, Double.toString(pojo.getTotalKms()));
		if(pojo.getWidthLine() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WIDTH_RAIL, Double.toString(pojo.getWidthLine()));
		if(pojo.getSewers() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SEWER_NUMBER, Double.toString(pojo.getSewers()));
		if(pojo.getRenewalWayValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_RENEWAL_VALUE, pojo.getRenewalWayValue().toString());

		if(pojo.isClosestWaterBodyCross())
		{
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WB_CROSS_WAY, "1");
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SINKING_SEVERITY, Double.toString(pojo.getClosestWaterBodyDistanceCross()));
		}
		else
		{
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WB_CROSS_WAY, "0");
		}

		// Para TBL_FormerDamageAndRisk
		if (pojo.isFloodFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.FLOOD);
		}
		if (pojo.isHurricanFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.HURRICANE);
		}
		if (pojo.isRockFallFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.EARTHQUACKE);
		}
		if (pojo.isSlidingFormer()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "D", Constants.LANDSLIDE);
		}

		if (pojo.isFloodRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.FLOOD);
		}
		if (pojo.isHurricanRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (pojo.isRockFallRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (pojo.isSlidingRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.LANDSLIDE);
		}

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++) {
			if (pojo.getSiniestralityValues()[y].compareTo("0.0") != 0)
				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestralityValues()[y].toString(), pojo.getSiniestralityDescription()[y]);
		}

	}

	public void processUrbanSave(pojoUrban pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			dataBase.insertarProperty(pojo.getPropertyID(), Constants.URBAN, "1", "Use", pojo.getPropertyName(), pojo.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), "", 1);
		} else {
			//dataBase.actualizarProperty(pojo.getPropertyID(), pojo.getBuildingNumber(), pojo.getManagedBy(), pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, "Fecha", "User", "Deleted", "Justification", "ParentID", pojo.getPostalCodeID(), 1, 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}

		if (pojo.getStateID().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStateID(), "0");
		if (pojo.getStructuralType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStructuralType(), "0");
		if (pojo.getFoundationType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFoundationType(), "0");
		if (pojo.getWallsType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWallsType(), "0");

		if (pojo.getLanesNumber() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RAILS_NUMBER, Integer.toString(pojo.getLanesNumber()));

//		features += ("<FEATURES Record='" + com.risk.console.misc.Constants.TOWNNAME + "'><AMOUNT Values='" + 
//				request.getParameter("pptyTownName") + "'/></FEATURES>");

		if (pojo.getStartLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getStartLatitude()));
		if (pojo.getEndLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getEndLatitude()));

		if (pojo.getStartLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getStartLongitude()));
		if (pojo.getEndLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getEndLongitude()));

		if (pojo.getStartAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getStartAltitude()));
		if (pojo.getEndAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getEndAltitude()));

		if(pojo.getAvgKmReconstructionValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_RECONSTRUCTION_VALUE_KM, pojo.getAvgKmReconstructionValue().toString());
		if(pojo.getStmtReposicionValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_RECONSTRUCTION_UNIT_VALUE, pojo.getStmtReposicionValue().toString());
		if(pojo.getMinorBridgesAvgRepositionValue().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_MINOR_BRIDGES_VALUE, pojo.getMinorBridgesAvgRepositionValue().toString());
		if(pojo.getPreventionAvgAmount().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_PREVENTIVE_MAINTENANCE_AMOUNT, pojo.getPreventionAvgAmount().toString());
		if(pojo.getCorrectiveAvgAmount().compareTo(BigDecimal.ZERO) != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_CORRECTIVE_MAINTENANCE_AMOUNT, pojo.getCorrectiveAvgAmount().toString());

		// Para TBL_FormerDamageAndRisk
		if (pojo.hasFleedRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.FLOOD);
		}
		if (pojo.hasHurricaneRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (pojo.hasRockFallingRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (pojo.hasEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (pojo.hasLandSlidingRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.LANDSLIDE);
		}

		int currentYear = DateOperations.getCurrentYear();

		for (int y=0; y<10; y++){
			if (pojo.getSiniestralityAmount()[y].compareTo(new BigDecimal("0")) == 1) 
				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestralityAmount()[y].toString(), pojo.getSiniestralityDescription()[y]);
		}

	}

	public void processWasteSave(pojoWasteDisposal pojo){
		dataBase = new DatabaseHandler(context);

		if (pojo.getPropertyID().equals(Constants.GENERICO)){
			pojo.setPropertyID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			//dataBase.insertarProperty(pojo.getPropertyID(), Constants.URBAN, "1", "Use", pojo.getPropertyName(), pojo.getAddress(), 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", Constants.GENERICO, pojo.getPostalCodeID(), 1, 1);
		} else {
			//dataBase.actualizarProperty(pojo.getPropertyID(), pojo.getBuildingNumber(), pojo.getManagedBy(), pojo.getBuildingName(), pojo.getAddress(), 1, 1, 1, 1, "Fecha", "User", "Deleted", "Justification", "ParentID", pojo.getPostalCodeID(), 1, 1);
			dataBase.eliminaPropiertyFeatures(pojo.getPropertyID());
			dataBase.eliminaFormerDamageAndRisk(pojo.getPropertyID());
			dataBase.eliminaAnnualAmount(pojo.getPropertyID());
		}

		if (pojo.getWasteDisposalType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWasteDisposalType(), "0");
		if (pojo.getStructuralType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getStructuralType(), "0");
		if (pojo.getFoundationType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getFoundationType(), "0");
		if (pojo.getWallsType().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), pojo.getWallsType(), "0");

		if (pojo.getRenewalValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RN_RENEWAL_VALUE, pojo.getRenewalValue());

		if (pojo.getStartLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getStartLatitude()));
		if (pojo.getEndLatitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LATITUDE, Double.toString(pojo.getEndLatitude()));

		if (pojo.getStartLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getStartLongitude()));
		if (pojo.getEndLongitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.LONGITUDE, Double.toString(pojo.getEndLongitude()));

		if (pojo.getStartAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getStartAltitude()));
		if (pojo.getEndAltitude() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ALTITUDE, Double.toString(pojo.getEndAltitude()));

		if (pojo.getCivilWorksValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_CIVIL_WORKS_VALUE, pojo.getCivilWorksValue());
		if (pojo.getMachineryEquipmentValue().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_MACHINERY_VALUE, pojo.getMachineryEquipmentValue());
		if (pojo.getAnnualMaintenanceAmount().length()>0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.RL_MAINTENANCE_AMOUNT, pojo.getAnnualMaintenanceAmount());
		if (pojo.getExtension() != 0)
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.EXTENSION, Double.toString(pojo.getExtension()));

		if(pojo.isAccessWays())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ACCESS_WAYS, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.ACCESS_WAYS, "0");

		if(pojo.isInnerWays())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.INNER_WAYS, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.INNER_WAYS, "0");

		if(pojo.isPerimeterFence())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.PERIMETER_FENCE, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.PERIMETER_FENCE, "0");

		if(pojo.isSecurityRoom())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SECURITY_ROOM, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SECURITY_ROOM, "0");

		if(pojo.isBascule())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.BASCULE, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.BASCULE, "0");

		if(pojo.isWaterElectricityDrain())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WATER_ELECTRICITY_DRAIN, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.WATER_ELECTRICITY_DRAIN, "0");

		if(pojo.isSanitaryServices())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SANITARY_SERVICES, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.SANITARY_SERVICES, "0");

		if(pojo.isCushioningStrip())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CUSHIONING_STRIP, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.CUSHIONING_STRIP, "0");

		if(pojo.isOffices())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OFFICES, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OFFICES, "0");

		if(pojo.isOffices())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OFFICES, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.OFFICES, "0");

		if(pojo.isMedicalServices())
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.MEDICAL_SERVICE, "1");
		else
			dataBase.insertPropertyFeatures(pojo.getPropertyID(), Constants.MEDICAL_SERVICE, "0");


		// Para TBL_FormerDamageAndRisk
		if (pojo.isFleedRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.FLOOD);
		}
		if (pojo.isHurricaneRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.HURRICANE);
		}
		if (pojo.isCollapseRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.COLLAPSE);
		}
		if (pojo.isEarthQuakeRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.EARTHQUACKE);
		}
		if (pojo.isLandSlideRisk()) {
			dataBase.insertFormerDamageAndRisk(pojo.getPropertyID(), "R", Constants.LANDSLIDE);
		}

//		int currentYear = DateOperations.getCurrentYear();
//
//		for (int y=0; y<10; y++){
//			if (pojo.getSiniestralityValues()[y].compareTo("0.0") != 0)
//				dataBase.insertAnnualAmount(pojo.getPropertyID(), String.valueOf(currentYear-y), pojo.getSiniestralityValues()[y].toString(), pojo.getSiniestralityDescription()[y]);
//		}

	}

	public void processBridgeSave(String propertyParentID, pojoBridges pPuente)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pPuente.getBridgeID());
		dataBase.eliminaPropiertyFeatures(pPuente.getBridgeID());
		dataBase.insertarProperty(pPuente.getBridgeID(), Constants.BRIDGE_STRUCTURE, "1", "Use", pPuente.getBridgeName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if(pPuente.getBridgeType().length()>0)
			dataBase.insertPropertyFeatures(pPuente.getBridgeID(), pPuente.getBridgeType(), "0");

		if (pPuente.getBridgeLength() != 0)
			dataBase.insertPropertyFeatures(pPuente.getBridgeID(), Constants.DISTANCE, Double.toString(pPuente.getBridgeLength()));
		if (pPuente.getRenewalBridgeValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pPuente.getBridgeID(), Constants.RL_RENEWAL_VALUE, pPuente.getRenewalBridgeValue().toString());
		if (pPuente.isMain())
			dataBase.insertPropertyFeatures(pPuente.getBridgeID(), Constants.IS_MAIN_BRIDGE, "1");
		else
			dataBase.insertPropertyFeatures(pPuente.getBridgeID(), Constants.IS_MAIN_BRIDGE, "0");

	}

	public void processTunnelSave(String propertyParentID, pojoTunnel pTunel)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pTunel.getTunnelID());
		dataBase.eliminaPropiertyFeatures(pTunel.getTunnelID());
		dataBase.insertarProperty(pTunel.getTunnelID(), Constants.TUNNEL_STRUCTURE, "1", "Use", pTunel.getTunnelName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if(pTunel.getTunnelType().length()>0)
			dataBase.insertPropertyFeatures(pTunel.getTunnelID(), pTunel.getTunnelType(), "0");

		if (pTunel.getTunnelLength() != 0)
			dataBase.insertPropertyFeatures(pTunel.getTunnelID(), Constants.DISTANCE, Double.toString(pTunel.getTunnelLength()));
		if (pTunel.getRenewalTunnelValue().compareTo("0.0") != 0)
			dataBase.insertPropertyFeatures(pTunel.getTunnelID(), Constants.RL_RENEWAL_VALUE, pTunel.getRenewalTunnelValue().toString());

	}

	public void processCourtainSave(String propertyParentID, pojoDamCurtain pCortina)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pCortina.getCurtainID());
		dataBase.eliminaPropiertyFeatures(pCortina.getCurtainID());
		dataBase.insertarProperty(pCortina.getCurtainID(), Constants.CURTAIN_STRUCTURE, "1", "Use", pCortina.getCurtainName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if(pCortina.getType().length()>0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), pCortina.getType(), "0");
		if(pCortina.getBehaivor().length()>0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), pCortina.getBehaivor(), "0");
		if(pCortina.getType().length()>0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), pCortina.getType(), "0");
		if(pCortina.getSize().length()>0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), pCortina.getSize(), "0");


		if (pCortina.getMaxHeight() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.MAX_HEIGHT, Double.toString(pCortina.getMaxHeight()));
		if (pCortina.getCrownElevation() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.CROWN_ELEVATION, Double.toString(pCortina.getCrownElevation()));
		if (pCortina.getLength() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.LENGHT, Double.toString(pCortina.getLength()));
		if (pCortina.getWidth() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.WEIGHT, Double.toString(pCortina.getWidth()));
		if (pCortina.getOverwaterSlope().length() > 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.WATER_UP_SLOPE, pCortina.getOverwaterSlope());
		if (pCortina.getUnderwaterSlope().length() > 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.WATER_DOWN_SLOPE, pCortina.getUnderwaterSlope());
		if (pCortina.getBreastwallHeight() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.BREAST_WALL_HEIGHT, Double.toString(pCortina.getBreastwallHeight()));
		if (pCortina.getVolumeBody() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.BODY_VOLUME, Double.toString(pCortina.getVolumeBody()));
		if (pCortina.getShimHeight() != 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.HEIGHT_OVER_SHIM, Double.toString(pCortina.getShimHeight()));
		if (pCortina.getOverwaterSlope().length() > 0)
			dataBase.insertPropertyFeatures(pCortina.getCurtainID(), Constants.OTHER_CURTAIN_FEATURES, pCortina.getOverwaterSlope());

	}

	public void processDikeSave(String propertyParentID, pojoDamDikes pDique)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pDique.getDikeID());
		dataBase.eliminaPropiertyFeatures(pDique.getDikeID());
		dataBase.insertarProperty(pDique.getDikeID(), Constants.CURTAIN_STRUCTURE, "1", "Use", pDique.getDikeNumber(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pDique.getDikeHeight() != 0)
			dataBase.insertPropertyFeatures(pDique.getDikeID(), Constants.HEIGHT, Double.toString(pDique.getDikeHeight()));
		if (pDique.getDikeBaseWidth() != 0)
			dataBase.insertPropertyFeatures(pDique.getDikeID(), Constants.BASE_WIDTH, Double.toString(pDique.getDikeBaseWidth()));
		if (pDique.getDikeFloodLevel() != 0)
			dataBase.insertPropertyFeatures(pDique.getDikeID(), Constants.FLOOD_LEVEL, Double.toString(pDique.getDikeFloodLevel()));
	}

	public void processExtensionSave(String propertyParentID, pojoIrrigationChannel pExtension)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pExtension.getChannelID());
		dataBase.eliminaPropiertyFeatures(pExtension.getChannelID());
		dataBase.insertarProperty(pExtension.getChannelID(), Constants.IRRIGATION_CHANNEL_STRUCTURE, "1", "Use", pExtension.getChannelName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pExtension.getExtension() != 0)
			dataBase.insertPropertyFeatures(pExtension.getChannelID(), Constants.HEIGHT, Double.toString(pExtension.getExtension()));
		if (pExtension.getFlow() != 0)
			dataBase.insertPropertyFeatures(pExtension.getChannelID(), Constants.BASE_WIDTH, Double.toString(pExtension.getFlow()));
		if (pExtension.getValue().length() > 0)
			dataBase.insertPropertyFeatures(pExtension.getChannelID(), Constants.FLOOD_LEVEL, pExtension.getValue());
	}

	public void processGaleriesSave(String propertyParentID, pojoDamGalery pGaleria)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pGaleria.getGaleryID());
		dataBase.eliminaPropiertyFeatures(pGaleria.getGaleryID());
		dataBase.insertarProperty(pGaleria.getGaleryID(), Constants.GALERY_STRUCTURE, "1", "Use", pGaleria.getGaleryNumber(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pGaleria.getSection().length() > 0)
			dataBase.insertPropertyFeatures(pGaleria.getGaleryID(), Constants.FLOOD_LEVEL, pGaleria.getSection());
		if (pGaleria.getLength() != 0)
			dataBase.insertPropertyFeatures(pGaleria.getGaleryID(), Constants.HEIGHT, Double.toString(pGaleria.getLength()));
		if (pGaleria.getWidth() != 0)
			dataBase.insertPropertyFeatures(pGaleria.getGaleryID(), Constants.BASE_WIDTH, Double.toString(pGaleria.getWidth()));
		if (pGaleria.getHeight() != 0)
			dataBase.insertPropertyFeatures(pGaleria.getGaleryID(), Constants.HEIGHT, Double.toString(pGaleria.getHeight()));
		if (pGaleria.getElevation() != 0)
			dataBase.insertPropertyFeatures(pGaleria.getGaleryID(), Constants.BASE_WIDTH, Double.toString(pGaleria.getElevation()));
	}

	public void processHundimientoSave(String propertyParentID, pojoSinking pHundimiento)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pHundimiento.getSinkingID());
		dataBase.eliminaPropiertyFeatures(pHundimiento.getSinkingID());
		dataBase.insertarProperty(pHundimiento.getSinkingID(), Constants.SINKING_STRUCTURE, "1", "Use", String.valueOf(pHundimiento.getPosition()), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pHundimiento.getSeverity() != 0)
			dataBase.insertPropertyFeatures(pHundimiento.getSinkingID(), Constants.SINKING_SEVERITY, String.valueOf(pHundimiento.getSeverity()));
	}

	public void processObraTomaSave(String propertyParentID, pojoDamHeadworks pObraToma)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pObraToma.getHeadworkID());
		dataBase.eliminaPropiertyFeatures(pObraToma.getHeadworkID());
		dataBase.insertarProperty(pObraToma.getHeadworkID(), Constants.INTAKE_STRUCTURE, "1", "Use", pObraToma.getHeadworkNumber(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pObraToma.getCapacity() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.FLOW_CAPACITY, Double.toString(pObraToma.getCapacity()));
		if (pObraToma.getElevation() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.ELEVATION, Double.toString(pObraToma.getElevation()));
		if (pObraToma.getGatesNumber() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.SPILL_GATES_NUMBER, Integer.toString(pObraToma.getGatesNumber()));
		if (pObraToma.getGatesWidth() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.SPILL_GATE_WIDTH, Double.toString(pObraToma.getGatesWidth()));
		if (pObraToma.getGatesHeight() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.SPILL_GATE_HEIGHT, Double.toString(pObraToma.getGatesHeight()));
		if (pObraToma.getValvesNumber() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.VALVESNR, Integer.toString(pObraToma.getValvesNumber()));
		if (pObraToma.getDuctNumber() != 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.DUCTNR, Integer.toString(pObraToma.getDuctNumber()));
		if  (pObraToma.getComments().length() > 0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.COMMENTS, pObraToma.getComments());
		//TODO
//		if (pObraToma.isGridExistence() != 0)
//			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), Constants.SPILL_GATE_HEIGHT, Double.toString(pObraToma.getGatesHeight()));

		if(pObraToma.getHeadworkType().length()>0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), pObraToma.getHeadworkType(), "0");
		if(pObraToma.getGatesType().length()>0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), pObraToma.getGatesType(), "0");
		if(pObraToma.getValveType().length()>0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), pObraToma.getValveType(), "0");
		if(pObraToma.getDuctType().length()>0)
			dataBase.insertPropertyFeatures(pObraToma.getHeadworkID(), pObraToma.getDuctType(), "0");

	}

	public void processOtrosDesfoguesSave(String propertyParentID, pojoVenting pOtrosDesfogues)
	{
		dataBase.eliminaPropierty(pOtrosDesfogues.getVentingID());
		dataBase.eliminaPropiertyFeatures(pOtrosDesfogues.getVentingID());
		dataBase.insertarProperty(pOtrosDesfogues.getVentingID(), Constants.VENTING_STRUCTURE, "1", "Use", pOtrosDesfogues.getVentingName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

//		if (pOtrosDesfogues.getCapacity() != 0)
//			dataBase.insertPropertyFeatures(pOtrosDesfogues.getHeadworkID(), Constants.FLOW_CAPACITY, Double.toString(pOtrosDesfogues.getCapacity()));
	}

	public void processUbicacionSave(String propertyParentID, pojoDamHydraulicFacility pUbicacion)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pUbicacion.getFacilityID());
		dataBase.eliminaPropiertyFeatures(pUbicacion.getFacilityID());
		dataBase.insertarProperty(pUbicacion.getFacilityID(), Constants.HYDRAULIC_FACILITIES, "1", "Use", pUbicacion.getFacilityName(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pUbicacion.getLongitude() != 0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), Constants.MAX_HEIGHT, Double.toString(pUbicacion.getLongitude()));
		if (pUbicacion.getLatitude() != 0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), Constants.CROWN_ELEVATION, Double.toString(pUbicacion.getLatitude()));
		if (pUbicacion.getClosestCity() != 0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), Constants.MAX_HEIGHT, Double.toString(pUbicacion.getClosestCity()));
		if (pUbicacion.getClosestWeatherSt().length() > 0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), Constants.CROWN_ELEVATION, pUbicacion.getClosestWeatherSt());


		if(pUbicacion.getDamType().length()>0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), pUbicacion.getDamType(), "0");
		if(pUbicacion.getDamUse().length()>0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), pUbicacion.getDamUse(), "0");
		if(pUbicacion.getCatchment().length()>0)
			dataBase.insertPropertyFeatures(pUbicacion.getFacilityID(), pUbicacion.getCatchment(), "0");

	}

	public void processVertederoSave(String propertyParentID, pojoDamSpillway pVertedero)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierty(pVertedero.getSpillWayID());
		dataBase.eliminaPropiertyFeatures(pVertedero.getSpillWayID());
		dataBase.insertarProperty(pVertedero.getSpillWayID(), Constants.CHUTE_STRUCTURE, "1", "Use", pVertedero.getSpillNumber(), "Adress", 1, 1, 1, 1, DateOperations.getFechaActual(), "User", "Deleted", "Justification", propertyParentID, "NO CP", "", 1);

		if (pVertedero.getCapacity() != 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_CAPACITY, Double.toString(pVertedero.getCapacity()));
		if (pVertedero.getOperation().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getOperation(), Constants.SPILL_OP, pVertedero.getOperation());
		if (pVertedero.getCrestHeight() != 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_CREST_HEIGHT, Double.toString(pVertedero.getCrestHeight()));
		if (pVertedero.getCrestLength() != 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_CREST_LENGTH, Double.toString(pVertedero.getCrestLength()));
		if (pVertedero.getGatesNumber() != 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_GATES_NUMBER, Double.toString(pVertedero.getGatesNumber()));
		if (pVertedero.getGateHeight() != 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_GATE_HEIGHT, Double.toString(pVertedero.getGateHeight()));
		if (pVertedero.getGateControl().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_GATE_CONTROL, pVertedero.getGateControl());
		if (pVertedero.getMaxFlow().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_MAX_FLOW, pVertedero.getMaxFlow());
		if (pVertedero.getElevationLSC().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.ELEVATION_LSC, pVertedero.getElevationLSC());
		if (pVertedero.getDissipativStr().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_OP, pVertedero.getDissipativStr());
		if (pVertedero.isSpire())
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_SPIRE, "0");
		if (pVertedero.getSpireHeight() != 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPIRE_HEIGHT, Double.toString(pVertedero.getSpireHeight()));
		if (pVertedero.getComments().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), Constants.SPILL_MAX_FLOW, pVertedero.getComments());

		if (pVertedero.getSpilltype().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), pVertedero.getSpilltype(), "0");
		if (pVertedero.getGateType().length() > 0)
			dataBase.insertPropertyFeatures(pVertedero.getSpillWayID(), pVertedero.getGateType(), "0");

	}

	public void processPropertiesForRemove(String propertiesID)
	{
		dataBase = new DatabaseHandler(context);

		dataBase.eliminaPropierties(propertiesID);
		dataBase.eliminaPropiertiesFeatures(propertiesID);
	}

}
