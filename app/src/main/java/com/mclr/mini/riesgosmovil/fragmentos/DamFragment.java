package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.AplicaDateChangedListener;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

public class DamFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID; // Este se inicializara en la selección del CP
	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonSaveUp1_5_dam;
	ImageButton buttonOutUp1_5_dam;
	ImageButton buttonCP_dam;
	ImageButton buttonUbicacion_dam;
	ImageButton buttonSaveBot1_5_dam;
	ImageButton buttonOutBot1_5_dam;

	EditText editNumeroId_dam;
	EditText editNomOf_dam;
	EditText editNomCom_dam;
	EditText editRegCna_dam;
	EditText editRegHidro_dam;
	EditText editCuenca_dam;
	EditText editCorriente_dam;
	EditText editAfluente_dam;
	EditText editVolumNAMO_dam;
	EditText editDisenador_dam;
	EditText editConstructor_dam;
	EditText editOrgaResp_dam;
	EditText editArea_dam;
	EditText editCarta_dam;
	EditText editVolumenMed_dam;
	EditText editTotalCort_dam;
	EditText editVolumenMax_dam;
	EditText editCp_dam;
	EditText editCalle_dam;
	EditText editColonia_dam;
	EditText editMunicipio_dam;
	EditText editEstado2_dam;
	EditText editLongitud_dam;
	EditText editLatitud_dam;
	EditText editAltitud_dam;
	EditText editEspe_dam;
	EditText editNiveles_dam;
	EditText editGastos_dam;
	EditText editFechaRefu_dam;
	EditText editFechaCons_dam;

	DatePicker pickerFechaRefu_dam;
	DatePicker pickerFechaConst_dam;

	RadioButton radioBuenoEC_dam;
	RadioButton radioRegularEC_dam;
	RadioButton radioMaloEC_dam;
	RadioButton radioAlta_dam;
	RadioButton radioMedia_dam;
	RadioButton radioBaja_dam;
	RadioButton radioCarretera_dam;
	RadioButton radioBrecha_dam;
	RadioButton radioTerraceria_dam;
	RadioButton radioMar_dam;
	RadioButton radioLago_dam;
	RadioButton radioLaguna_dam;
	RadioButton radioRio_dam;
	RadioButton radioRegular_dam;
	RadioButton radioIrregular_dam;
	RadioButton radioIrrePla_dam;
	RadioButton radioIrreEle_dam;
	RadioButton radioNinguna_dam;
	RadioButton radioImperceptibles_dam;
	RadioButton radioLigeros_dam;
	RadioButton radioMuyGrande_dam;

	Spinner spinnerPropCons_dam;
	List<CatalogItem> listSpinnerPropCons;
	Spinner spinnerUsoAgua_dam;
	List<CatalogItem> listSpinnerUsoAgua;
	Spinner spinnerTipoCime_dam;
	List<CatalogItem> listSpinnerTipoCime;
	Spinner spinnerTipoEstru_dam;
	List<CatalogItem> listSpinnerTipoEstru;
	Spinner spinnerTipoCorr_dam;
	List<CatalogItem> listSpinnerTipoCorr;

	CheckBox checkboxSprinkler_dam;
	CheckBox checkboxHidratantes_dam;
	CheckBox checkboxCisterna_dam;
	CheckBox checkboxExtinguidores_dam;

	Switch switchPreseMuros_dam;
	Switch switchPreseHun_dam;
	Switch switchPreGol_dam;
	Switch switchProtecIncen_dam;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_dam);
		buttonUbicacion_dam = (ImageButton) myActivity.findViewById(R.id.buttonUbicacion_dam);
		buttonUbicacion_dam.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            MuestraPosicion(location);
				        }
				        public void onProviderDisabled(String provider){
				        	Toast.makeText(myActivity, "El GPS se ha deshabilitado", Toast.LENGTH_LONG).show();
				        }
				        public void onProviderEnabled(String provider){
				        	Toast.makeText(myActivity, "El GPS se ha habilitado", Toast.LENGTH_LONG).show();
				        }
				        public void onStatusChanged(String provider, int status, Bundle extras){
				        }
				    };
				    //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
				}
			}
		});

		buttonSaveUp1_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_5_dam);
		buttonSaveUp1_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutUp1_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_5_dam);
		buttonOutUp1_5_dam.setOnClickListener(new OutClickListener());
		buttonSaveBot1_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_5_dam);
		buttonSaveBot1_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutBot1_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_5_dam);
		buttonOutBot1_5_dam.setOnClickListener(new OutClickListener());

		buttonCP_dam = (ImageButton) myActivity.findViewById(R.id.buttonCP_dam);
		buttonCP_dam.setOnClickListener(new SearchClickListener(this));

		editNumeroId_dam = (EditText) myActivity.findViewById(R.id.editNumeroId_dam);
		//editNumeroId_dam.setText("Presa 1234");
		editNomOf_dam = (EditText) myActivity.findViewById(R.id.editNomOf_dam);
		//editNomOf_dam.setText("Presa 5678");
		editNomCom_dam = (EditText) myActivity.findViewById(R.id.editNomCom_dam);
		//editNomCom_dam.setText("Presa 91011");
		radioBuenoEC_dam = (RadioButton) myActivity.findViewById(R.id.radioBuenoEC_dam);
		radioRegularEC_dam = (RadioButton) myActivity.findViewById(R.id.radioRegularEC_dam);
		radioMaloEC_dam = (RadioButton) myActivity.findViewById(R.id.radioMaloEC_dam);
		editRegCna_dam = (EditText) myActivity.findViewById(R.id.editRegCna_dam);
		editRegHidro_dam = (EditText) myActivity.findViewById(R.id.editRegHidro_dam);
		//editRegHidro_dam.setText("Region 1");
		editCuenca_dam = (EditText) myActivity.findViewById(R.id.editCuenca_dam);
		//editCuenca_dam.setText("Cuenca 1");
		editCorriente_dam = (EditText) myActivity.findViewById(R.id.editCorriente_dam);
		//editCorriente_dam.setText("Corriente 1");
		editAfluente_dam = (EditText) myActivity.findViewById(R.id.editAfluente_dam);
		//editAfluente_dam.setText("Afluente 1");
		editVolumNAMO_dam = (EditText) myActivity.findViewById(R.id.editVolumNAMO_dam);
		//editVolumNAMO_dam.setText("98765");
		editDisenador_dam = (EditText) myActivity.findViewById(R.id.editDisenador_dam);
		editConstructor_dam = (EditText) myActivity.findViewById(R.id.editConstructor_dam);
		editOrgaResp_dam = (EditText) myActivity.findViewById(R.id.editOrgaResp_dam);
		//editOrgaResp_dam.setText("Organo Resp. 1"); //TODO
		editArea_dam = (EditText) myActivity.findViewById(R.id.editArea_dam);
		//editArea_dam.setText("32323232"); //TODO
		editCarta_dam = (EditText) myActivity.findViewById(R.id.editCarta_dam);
		editTotalCort_dam = (EditText) myActivity.findViewById(R.id.editTotalCort_dam);
		//editTotalCort_dam.setText("9876");
		editVolumenMed_dam = (EditText) myActivity.findViewById(R.id.editVolumenMed_dam);
		editVolumenMax_dam = (EditText) myActivity.findViewById(R.id.editVolumenMax_dam);
		editCp_dam = (EditText) myActivity.findViewById(R.id.editCp_dam);
		editCalle_dam = (EditText) myActivity.findViewById(R.id.editCalle_dam);
		//editCalle_dam.setText("Oriente 166 No. 338");
		editColonia_dam = (EditText) myActivity.findViewById(R.id.editColonia_dam);
		editMunicipio_dam = (EditText) myActivity.findViewById(R.id.editMunicipio_dam);
		editEstado2_dam = (EditText) myActivity.findViewById(R.id.editEstadoGeo_dam);
		editLongitud_dam = (EditText) myActivity.findViewById(R.id.editLongitud_dam);
		//editLongitud_dam.setText("999999"); //TODO
		editLatitud_dam = (EditText) myActivity.findViewById(R.id.editLatitud_dam);
		//editLatitud_dam.setText("888888"); //TODO
		editAltitud_dam = (EditText) myActivity.findViewById(R.id.editAltitud_dam);
		//editAltitud_dam.setText("777777"); //TODO
		editEspe_dam = (EditText) myActivity.findViewById(R.id.editEspe_dam);
		//editEspe_dam.setText("444444");
		editNiveles_dam = (EditText) myActivity.findViewById(R.id.editNiveles_dam);
		//editNiveles_dam.setText("987654"); //TODO
		editGastos_dam = (EditText) myActivity.findViewById(R.id.editGastos_dam);
		//editGastos_dam.setText("87878787"); //TODO
		editFechaRefu_dam = (EditText) myActivity.findViewById(R.id.editFechaRefu_dam);
		pickerFechaRefu_dam  = (DatePicker)myActivity.findViewById(R.id.pickerFechaRefu_dam);
		pickerFechaRefu_dam.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaRefu_dam));
		editFechaCons_dam = (EditText) myActivity.findViewById(R.id.editFechaCons_dam);
		pickerFechaConst_dam  = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_dam);
		pickerFechaConst_dam.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaCons_dam));

		radioAlta_dam = (RadioButton) myActivity.findViewById(R.id.radioAlta_dam);
		radioMedia_dam = (RadioButton) myActivity.findViewById(R.id.radioMedia_dam);
		radioBaja_dam = (RadioButton) myActivity.findViewById(R.id.radioBaja_dam);
		radioCarretera_dam = (RadioButton) myActivity.findViewById(R.id.radioCarretera_dam);
		radioBrecha_dam = (RadioButton) myActivity.findViewById(R.id.radioBrecha_dam);
		radioTerraceria_dam = (RadioButton) myActivity.findViewById(R.id.radioTerraceria_dam);
		radioMar_dam = (RadioButton) myActivity.findViewById(R.id.radioMar_dam);
		radioLago_dam = (RadioButton) myActivity.findViewById(R.id.radioLago_dam);
		radioLaguna_dam = (RadioButton) myActivity.findViewById(R.id.radioLaguna_dam);
		radioRio_dam = (RadioButton) myActivity.findViewById(R.id.radioRio_dam);
		radioRegular_dam = (RadioButton) myActivity.findViewById(R.id.radioRegular_dam);
		radioIrregular_dam = (RadioButton) myActivity.findViewById(R.id.radioIrregular_dam);
		radioIrrePla_dam = (RadioButton) myActivity.findViewById(R.id.radioIrrePla_dam);
		radioIrreEle_dam = (RadioButton) myActivity.findViewById(R.id.radioIrreEle_dam);
		radioImperceptibles_dam = (RadioButton) myActivity.findViewById(R.id.radioImperceptibles_dam);
		radioLigeros_dam = (RadioButton) myActivity.findViewById(R.id.radioLigeros_dam);
		radioMuyGrande_dam = (RadioButton) myActivity.findViewById(R.id.radioMuyGrande_dam);

		spinnerPropCons_dam = (Spinner) myActivity.findViewById(R.id.spinnerPropCons_dam);
		listSpinnerPropCons = db.getCatalogList(Constants.CONSTRUCTION_PORPUSE);
		spinnerPropCons_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerPropCons));

		spinnerUsoAgua_dam = (Spinner) myActivity.findViewById(R.id.spinnerUsoAgua_dam);
		listSpinnerUsoAgua = db.getCatalogList(Constants.WATER_USE);
		spinnerUsoAgua_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerUsoAgua));

		spinnerTipoCime_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoCime_dam);
		listSpinnerTipoCime = db.getCatalogList(Constants.DAM_FOUNDATION_TYPE);
		spinnerTipoCime_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCime));

		spinnerTipoEstru_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoEstru_dam);
		listSpinnerTipoEstru = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipoEstru_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstru));

		spinnerTipoCorr_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoCorr_dam);
		listSpinnerTipoCorr = db.getCatalogList(Constants.FLOW_TYPE);
		spinnerTipoCorr_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCorr));

		checkboxSprinkler_dam = (CheckBox)myActivity.findViewById(R.id.checkboxSprinkler_dam);
		checkboxHidratantes_dam = (CheckBox)myActivity.findViewById(R.id.checkboxHidratantes_dam);
		checkboxCisterna_dam = (CheckBox)myActivity.findViewById(R.id.checkboxCisterna_dam);
		checkboxExtinguidores_dam = (CheckBox)myActivity.findViewById(R.id.checkboxExtinguidores_dam);

		switchPreseMuros_dam = (Switch)myActivity.findViewById(R.id.switchPreseMuros_dam);
		switchPreseHun_dam = (Switch)myActivity.findViewById(R.id.switchPreseHun_dam);
		switchPreGol_dam = (Switch)myActivity.findViewById(R.id.switchPreGol_dam);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propierty.getDamPptyDetail(propertyID, postalCodeID);
			editNumeroId_dam.setText(pojo.getDamID());
			editNomOf_dam.setText(pojo.getOfficialNumber());
			editNomCom_dam.setText(pojo.getDamName());
			if (pojo.getCondition().compareTo(Constants.ConservationState_Good) == 0) {
				radioBuenoEC_dam.setChecked(true);
			} else if (pojo.getCondition().compareTo(Constants.ConservationState_Bad) == 0) {
				radioRegularEC_dam.setChecked(true);
			} else if (pojo.getCondition().compareTo(Constants.ConservationState_Poor) == 0) {
				radioMaloEC_dam.setChecked(true);
			}
			editRegCna_dam.setText(pojo.getCNARegion());
			editRegHidro_dam.setText(pojo.getHidrologicRegion());
			editCuenca_dam.setText(pojo.getPumb());
			editCorriente_dam.setText(pojo.getCurrent());
			editAfluente_dam.setText(pojo.getAfluent());
			editVolumNAMO_dam.setText(String.valueOf(pojo.getNAMOVolume()));
			editDisenador_dam.setText(pojo.getDeseigner());
			editConstructor_dam.setText(pojo.getConstructor());
			editOrgaResp_dam.setText(pojo.getResponsibleOrg());
			editArea_dam.setText(String.valueOf(pojo.getSurface()));
			editCarta_dam.setText(pojo.getINEGILetter());
			editTotalCort_dam.setText(String.valueOf(pojo.getCurtainFeatures().size()));
			editVolumenMed_dam.setText(String.valueOf(pojo.getAvgAnnualRunoff()));
			editVolumenMax_dam.setText(String.valueOf(pojo.getMaxAnnualRunoff()));

			spinnerPropCons_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerPropCons, pojo.getContructionPurpouse()));
			spinnerUsoAgua_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerUsoAgua, pojo.getWaterUser()));

			if (pojo.getSismicity().compareTo(Constants.Sismicidad_Alta) == 0) {
				radioAlta_dam.setChecked(true);
			} else if (pojo.getSismicity().compareTo(Constants.Sismicidad_Media) == 0) {
				radioMedia_dam.setChecked(true);
			} else if (pojo.getSismicity().compareTo(Constants.Sismicidad_Baja) == 0) {
				radioBaja_dam.setChecked(true);
			}

			if (pojo.getAccessWay().compareTo(Constants.Vias_De_Acceso_Carretera) == 0) {
				radioCarretera_dam.setChecked(true);
			} else if (pojo.getAccessWay().compareTo(Constants.Vias_De_Acceso_Brecha) == 0) {
				radioBrecha_dam.setChecked(true);
			} else if (pojo.getAccessWay().compareTo(Constants.Vias_De_Acceso_Terraceria) == 0) {
				radioTerraceria_dam.setChecked(true);
			}
			editMunicipio_dam.setText(pojo.getTown());
			editColonia_dam.setText(pojo.getColony());
			editCp_dam.setText(pojo.getPostalCode());
			editCalle_dam.setText(pojo.getAddress());
			editEstado2_dam.setText(pojo.getStateName());

			if (pojo.getClosesWaterBody().compareTo(Constants.Mar) == 0) {
				radioMar_dam.setChecked(true);
			} else if (pojo.getClosesWaterBody().compareTo(Constants.Lago) == 0) {
				radioLago_dam.setChecked(true);
			} else if (pojo.getClosesWaterBody().compareTo(Constants.Laguna) == 0) {
				radioLaguna_dam.setChecked(true);
			} else if (pojo.getClosesWaterBody().compareTo(Constants.Rio) == 0) {
				radioRio_dam.setChecked(true);
			}

			editLongitud_dam.setText(String.valueOf(pojo.getLongitude()));
			editLatitud_dam.setText(String.valueOf(pojo.getLatitude()));
			editAltitud_dam.setText(String.valueOf(pojo.getAltitude()));

			try
			{
				pickerFechaRefu_dam.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getReinforcementDate())),
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getReinforcementDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getReinforcementDate())), new AplicaDateChangedListener(editFechaRefu_dam));
			}
			catch(Exception ex)
			{
			}
			try
			{
				pickerFechaConst_dam.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getConstructionDate())),
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getConstructionDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getConstructionDate())), new AplicaDateChangedListener(editFechaCons_dam));
			}
			catch(Exception ex)
			{
			}

			spinnerTipoCime_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getFoundationType()));
			spinnerTipoEstru_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstru, pojo.getStructuralType()));
			if (pojo.isGeometryRegular()) {
				radioRegular_dam.setChecked(true);
			}
			if (pojo.isGeometryIrregular()) {
				radioIrregular_dam.setChecked(true);
			}

			spinnerTipoCorr_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCorr, pojo.getCurrentsTypes()));
			switchPreseMuros_dam.setChecked(pojo.isShortWallsbeneathColumns());
			editNiveles_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			editGastos_dam.setText(String.valueOf(pojo.getCurrentWaterQtty()));
			if (pojo.isSinkingPresence())
			{
				switchPreseHun_dam.setChecked(true);
	
				if (pojo.getSinkingSeverity() == 1) {
					radioImperceptibles_dam.setChecked(true);
				} else if (pojo.getSinkingSeverity() == 2) {
					radioLigeros_dam.setChecked(true);
				} else if (pojo.getSinkingSeverity() == 3) {
					radioMuyGrande_dam.setChecked(true);
				}	
			} else {
				radioImperceptibles_dam.setChecked(false);
				radioLigeros_dam.setChecked(false);
				radioMuyGrande_dam.setChecked(false);
			}

			switchPreGol_dam.setChecked(pojo.isPounding());

			if (pojo.isSprinkler()) {
				checkboxSprinkler_dam.setChecked(true);
			}
			if (pojo.isHydrant()) {
				checkboxHidratantes_dam.setChecked(true);
			}
			if (pojo.isTank()) {
				checkboxCisterna_dam.setChecked(true);
			}
			if (pojo.isExtintor()) {
				checkboxExtinguidores_dam.setChecked(true);
			}

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.aquaculture_blue);
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
		propertyID = myActivity.propertyID;
		postalCodeID = myActivity.postalCodeID;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dams, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	private class SaveClickListener implements OnClickListener {
		pojoDams pojoDam;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoDam = new pojoDams();
			//Toast.makeText(myActivity, "Boton salvar", Toast.LENGTH_LONG).show();
			if (validaCampos()) {

				pojoDam.setPropertyID(myActivity.propertyID);
				SetPojoDamP1(pojoDam);
				DamFragment2 presas2 = (DamFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				presas2.SetPojoDamP2(pojoDam);

				try
				{
					process.processDamSave(pojoDam);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoDam.getPropertyID();
					myActivity.postalCodeID = pojoDam.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	public void CerrarBien()
	{
		buttonSaveUp1_5_dam.setEnabled(false);
		//buttonOutUp1_5_dam.setEnabled(false);
		buttonCP_dam.setEnabled(false);
		buttonUbicacion_dam.setEnabled(false);
		buttonSaveBot1_5_dam.setEnabled(false);
		//buttonOutBot1_5_dam.setEnabled(false);

		editNumeroId_dam.setEnabled(false);
		editNomOf_dam.setEnabled(false);
		editNomCom_dam.setEnabled(false);
		editRegCna_dam.setEnabled(false);
		editRegHidro_dam.setEnabled(false);
		editCuenca_dam.setEnabled(false);
		editCorriente_dam.setEnabled(false);
		editAfluente_dam.setEnabled(false);
		editVolumNAMO_dam.setEnabled(false);
		editDisenador_dam.setEnabled(false);
		editConstructor_dam.setEnabled(false);
		editOrgaResp_dam.setEnabled(false);
		editArea_dam.setEnabled(false);
		editCarta_dam.setEnabled(false);
		editVolumenMed_dam.setEnabled(false);
		editTotalCort_dam.setEnabled(false);
		editVolumenMax_dam.setEnabled(false);
		editCp_dam.setEnabled(false);
		editCalle_dam.setEnabled(false);
		editColonia_dam.setEnabled(false);
		editMunicipio_dam.setEnabled(false);
		editEstado2_dam.setEnabled(false);
		editLongitud_dam.setEnabled(false);
		editLatitud_dam.setEnabled(false);
		editAltitud_dam.setEnabled(false);
		editEspe_dam.setEnabled(false);
		editNiveles_dam.setEnabled(false);
		editGastos_dam.setEnabled(false);
		editFechaRefu_dam.setEnabled(false);
		editFechaCons_dam.setEnabled(false);

		pickerFechaRefu_dam.setEnabled(false);
		pickerFechaConst_dam.setEnabled(false);

		radioBuenoEC_dam.setEnabled(false);
		radioRegularEC_dam.setEnabled(false);
		radioMaloEC_dam.setEnabled(false);
		radioAlta_dam.setEnabled(false);
		radioMedia_dam.setEnabled(false);
		radioBaja_dam.setEnabled(false);
		radioCarretera_dam.setEnabled(false);
		radioBrecha_dam.setEnabled(false);
		radioTerraceria_dam.setEnabled(false);
		radioMar_dam.setEnabled(false);
		radioLago_dam.setEnabled(false);
		radioLaguna_dam.setEnabled(false);
		radioRio_dam.setEnabled(false);
		radioRegular_dam.setEnabled(false);
		radioIrregular_dam.setEnabled(false);
		radioIrrePla_dam.setEnabled(false);
		radioIrreEle_dam.setEnabled(false);
		radioNinguna_dam.setEnabled(false);
		radioImperceptibles_dam.setEnabled(false);
		radioLigeros_dam.setEnabled(false);
		radioMuyGrande_dam.setEnabled(false);

		spinnerPropCons_dam.setEnabled(false);
		spinnerUsoAgua_dam.setEnabled(false);
		spinnerTipoCime_dam.setEnabled(false);
		spinnerTipoEstru_dam.setEnabled(false);
		spinnerTipoCorr_dam.setEnabled(false);

		checkboxSprinkler_dam.setEnabled(false);
		checkboxHidratantes_dam.setEnabled(false);
		checkboxCisterna_dam.setEnabled(false);
		checkboxExtinguidores_dam.setEnabled(false);

		buttonSaveUp1_5_dam.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_5_dam.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_dam.setImageResource(R.drawable.guardar_blocked);
		buttonUbicacion_dam.setImageResource(R.mipmap.ubicacion_blocked);
		buttonSaveBot1_5_dam.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_5_dam.setImageResource(R.mipmap.salir_blocked);
	}

	private void MuestraPosicion(Location loc) {
	    if(loc != null)
	    {
	        editLatitud_dam.setText(String.valueOf(loc.getLatitude()));
	        editLongitud_dam.setText(String.valueOf(loc.getLongitude()));
	        editAltitud_dam.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatitud_dam.setText("(sin_datos)");
	    	editLongitud_dam.setText("(sin_datos)");
	    	editAltitud_dam.setText("(sin_datos)");
	    }
	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_dam.setText(colonia);
		editMunicipio_dam.setText(municipio);
		editEstado2_dam.setText(estado);
		editCp_dam.setText(CP);
		postalCodeID = p_postalCodeID;
	
		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

	public boolean validaCampos(){
		if (editNumeroId_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar Número de identificación del bien", Toast.LENGTH_LONG).show();
			editNumeroId_dam.requestFocus();
			return false;
		}
		if (editNomOf_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar Nombre oficial del bien", Toast.LENGTH_LONG).show();
			editNomOf_dam.requestFocus();
			return false;
		}
		if (editNomCom_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar Nombre común del bien", Toast.LENGTH_LONG).show();
			editNomCom_dam.requestFocus();
			return false;
		}
		/*if (pojo.getSismicity().compareTo(Constants.Sismicidad_Alta) == 0) {
				radioAlta_dam.setChecked(true);
			} else if (pojo.getSismicity().compareTo(Constants.Sismicidad_Media) == 0) {
				radioMedia_dam.setChecked(true);
			} else if (pojo.getSismicity().compareTo(Constants.Sismicidad_Baja) == 0) {
				radioBaja_dam.setChecked(true);
			}*/
		
		if (!radioBuenoEC_dam.isChecked() && !radioRegularEC_dam.isChecked()&& !radioMaloEC_dam.isChecked()){
			Toast.makeText(myActivity, "Se debe indicar el estado de conservación del bien", Toast.LENGTH_LONG).show();
			radioBuenoEC_dam.requestFocus();
			return false;
		}
		if (editRegHidro_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la región hidrológica del bien", Toast.LENGTH_LONG).show();
			editRegHidro_dam.requestFocus();
			return false;
		}
		if (editCuenca_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la cuenca del bien", Toast.LENGTH_LONG).show();
			editCuenca_dam.requestFocus();
			return false;
		}
		if (editCorriente_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la corriente del bien", Toast.LENGTH_LONG).show();
			editCorriente_dam.requestFocus();
			return false;
		}
		if (editAfluente_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el afluente del bien", Toast.LENGTH_LONG).show();
			editAfluente_dam.requestFocus();
			return false;
		}
		if (editVolumNAMO_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar Volumen al NAMO bien", Toast.LENGTH_LONG).show();
			editVolumNAMO_dam.requestFocus();
			return false;
		}
		if (editOrgaResp_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el organismo responsable bien", Toast.LENGTH_LONG).show();
			editOrgaResp_dam.requestFocus();
			return false;
		}
		if(spinnerPropCons_dam.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar propósito de construcción", Toast.LENGTH_LONG).show();
			spinnerPropCons_dam.requestFocus();
			return false;
		}
		if(spinnerUsoAgua_dam.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar uso de agua", Toast.LENGTH_LONG).show();
			spinnerUsoAgua_dam.requestFocus();
			return false;
		}
		if (editArea_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el área del bien", Toast.LENGTH_LONG).show();
			editArea_dam.requestFocus();
			return false;
		}
		if (editTotalCort_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el Total de cortinas", Toast.LENGTH_LONG).show();
			editTotalCort_dam.requestFocus();
			return false;
		}
		if (editCp_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_dam.requestFocus();
			return false;
		}
		if (editCalle_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la calle correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCalle_dam.requestFocus();
			return false;
		}
		if (editColonia_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_dam.requestFocus();
			return false;
		}
		if (editMunicipio_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_dam.requestFocus();
			return false;
		}
		if (editEstado2_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado2_dam.requestFocus();
			return false;
		}
		if (editLongitud_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongitud_dam.requestFocus();
			return false;
		}
		if (editLatitud_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatitud_dam.requestFocus();
			return false;
		}
		if (editAltitud_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltitud_dam.requestFocus();
			return false;
		}
		if(spinnerTipoCorr_dam.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de corrientes", Toast.LENGTH_LONG).show();
			spinnerTipoCorr_dam.requestFocus();
			return false;
		}
		if (editNiveles_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar los niveles", Toast.LENGTH_LONG).show();
			editNiveles_dam.requestFocus();
			return false;
		}
		if (editGastos_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar los gastos de agua", Toast.LENGTH_LONG).show();
			editGastos_dam.requestFocus();
			return false;
		}
		if(!radioIrregular_dam.isChecked()&&!radioRegular_dam.isChecked())
		{
			Toast.makeText(myActivity, "Se debe seleccionar la geometría de la construcción", Toast.LENGTH_LONG).show();
			radioIrregular_dam.requestFocus();
		}
		if (editEspe_dam.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la geometría de la construcción bien", Toast.LENGTH_LONG).show();
			editEspe_dam.requestFocus();
			return false;
		}
		
		return true;
	}

	private class OutClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
			try {
				KeyOperations.DispachBackKey(myActivity);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	private class SearchClickListener implements OnClickListener {
		Fragment fragment;
		Bundle args = new Bundle();

		public SearchClickListener(Fragment p_fragment) {
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_dam.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_dam.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_dam.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);

            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.DAM);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}
	
	public void SetPojoDamP1(pojoDams pojo)
	{
		pojo.setPostalCodeID(postalCodeID);
		pojo.setDamID(editNumeroId_dam.getText().toString());
		pojo.setOfficialNumber(editNomOf_dam.getText().toString());
		pojo.setDamName(editNomCom_dam.getText().toString());
		if (radioBuenoEC_dam.isChecked()){
			pojo.setCondition(Constants.ConservationState_Good);
		}else if (radioRegularEC_dam.isChecked()){
			pojo.setCondition(Constants.ConservationState_Bad);
		}else if (radioMaloEC_dam.isChecked()){
			pojo.setCondition(Constants.ConservationState_Poor);
		}
		pojo.setCNARegion(editRegCna_dam.getText().toString());
		pojo.setHidrologicRegion(editRegHidro_dam.getText().toString());
		pojo.setPumb(editCuenca_dam.getText().toString());
		pojo.setCurrent(editCorriente_dam.getText().toString());
		pojo.setAfluent(editAfluente_dam.getText().toString());
		pojo.setNAMOVolume(Double.valueOf(editVolumNAMO_dam.getText().toString()));
		pojo.setDeseigner(editDisenador_dam.getText().toString());
		pojo.setConstructor(editConstructor_dam.getText().toString());
		pojo.setResponsibleOrg(editOrgaResp_dam.getText().toString());
		pojo.setSurface(Double.valueOf(editArea_dam.getText().toString()));
		pojo.setINEGILetter(editCarta_dam.getText().toString());
		// Total de cortinas NO set
		if (editVolumenMed_dam.getText().toString().length() > 0)
			pojo.setAvgAnnualRunoff(Double.valueOf(editVolumenMed_dam.getText().toString()));
		if (editVolumenMax_dam.getText().toString().length() > 0)
			pojo.setMaxAnnualRunoff(Double.valueOf(editVolumenMax_dam.getText().toString()));

		pojo.setContructionPurpouse(listSpinnerPropCons.get(spinnerPropCons_dam.getSelectedItemPosition()).getId());
		pojo.setWaterUser(listSpinnerUsoAgua.get(spinnerUsoAgua_dam.getSelectedItemPosition()).getId());

		if (radioAlta_dam.isChecked()){
			pojo.setSismicity(Constants.Sismicidad_Alta);
		}else if (radioMedia_dam.isChecked()){
			pojo.setSismicity(Constants.Sismicidad_Media);
		}else if (radioBaja_dam.isChecked()){
			pojo.setSismicity(Constants.Sismicidad_Baja);
		}
		if (radioCarretera_dam.isChecked()){
			pojo.setAccessWay(Constants.Vias_De_Acceso_Carretera);
		}else if (radioBrecha_dam.isChecked()){
			pojo.setAccessWay(Constants.Vias_De_Acceso_Brecha);
		}else if (radioTerraceria_dam.isChecked()){
			pojo.setAccessWay(Constants.Vias_De_Acceso_Terraceria);
		}

		pojo.setTown(editMunicipio_dam.getText().toString());
		pojo.setColony(editColonia_dam.getText().toString());
		pojo.setPostalCode(editCp_dam.getText().toString());
		pojo.setAddress(editCalle_dam.getText().toString());
		pojo.setStateName(editEstado2_dam.getText().toString());

		if (radioMar_dam.isChecked()){
			pojo.setClosesWaterBody(Constants.Mar);
		}else if (radioLago_dam.isChecked()){
			pojo.setClosesWaterBody(Constants.Lago);
		}else if (radioLaguna_dam.isChecked()){
			pojo.setClosesWaterBody(Constants.Laguna);
		}else if (radioRio_dam.isChecked()){
			pojo.setClosesWaterBody(Constants.Rio);
		}

		pojo.setLongitude(Double.valueOf(editLongitud_dam.getText().toString()));
		pojo.setLatitude(Double.valueOf(editLatitud_dam.getText().toString()));
		pojo.setAltitude(Double.valueOf(editAltitud_dam.getText().toString()));

		pojo.setConstructionDate(editFechaCons_dam.getText().toString());
		pojo.setReinforcementDate(editFechaRefu_dam.getText().toString());
		pojo.setFoundationType(listSpinnerTipoCime.get(spinnerTipoCime_dam.getSelectedItemPosition()).getId());
		pojo.setStructuralType(listSpinnerTipoEstru.get(spinnerTipoEstru_dam.getSelectedItemPosition()).getId());
		pojo.setCurrentsTypes(listSpinnerTipoCorr.get(spinnerTipoCorr_dam.getSelectedItemPosition()).getId());

		//TODO
//		if (radioIrrePla_dam.isChecked()) {
//			pojo.setGeometryRegular(true);
//		}
//		if (radioIrreEle_dam.isChecked()) {
//			pojo.setGeometryIrregular(true);
//		}

		if (radioRegular_dam.isChecked()) {
			pojo.setGeometryRegular(true);
		}
		if (radioIrregular_dam.isChecked()) {
			pojo.setGeometryIrregular(true);
		}

		if (switchPreseMuros_dam.isChecked())
			pojo.setShortWallsbeneathColumns(true);

		pojo.setCurrentLevels(Double.valueOf(editNiveles_dam.getText().toString()));
		pojo.setCurrentWaterQtty(Double.valueOf(editGastos_dam.getText().toString()));

		if (switchPreseHun_dam.isChecked()) {
			pojo.setSinkingPresence(true);
		}
		if (radioImperceptibles_dam.isChecked()){
			pojo.setSinkingSeverity(1);
		}else if (radioLigeros_dam.isChecked()){
			pojo.setSinkingSeverity(2);
		}else if (radioMuyGrande_dam.isChecked()){
			pojo.setSinkingSeverity(3);
		}
		if (switchPreGol_dam.isChecked()) {
			pojo.setPounding(true);
		}
		if (checkboxSprinkler_dam.isChecked()) {
			pojo.setSprinkler(true);
		}
		if (checkboxHidratantes_dam.isChecked()) {
			pojo.setHydrant(true);
		}
		if (checkboxCisterna_dam.isChecked()) {
			pojo.setTank(true);
		}
		if (checkboxExtinguidores_dam.isChecked()) {
			pojo.setExtintor(true);
		}
	}
}