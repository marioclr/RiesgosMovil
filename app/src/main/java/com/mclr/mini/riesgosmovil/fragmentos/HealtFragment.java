package com.mclr.mini.riesgosmovil.fragmentos;

import java.util.List;

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
import com.mclr.mini.riesgosmovil.modelos.pojoHealt;
import com.mclr.mini.riesgosmovil.utilitarios.AplicaDateChangedListener;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

public class HealtFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID; // Este se inicializara en la selección del CP
	// Acceso a GPS
	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonSaveUp1_2_healt;
	ImageButton buttonOutUp1_2_healt;
	ImageButton buttonSaveBot1_2_healt;
	ImageButton buttonOutBot1_2_healt;
	ImageButton buttonCP_healt;
	ImageButton buttonUbicacion_healt;

	DatePicker pickerFechaConst_healt;
	DatePicker pickerFechaRefu_healt;

	EditText editNombre_healt;
	EditText editNumPisos_healt;
	EditText editCp_healt;
	EditText editCalle_healt;
	EditText editColonia_healt;
	EditText editMunicipio_healt;
	EditText editEstado_healt;
	EditText editLongitud_healt;
	EditText editLatitud_healt;
	EditText editAltitud_healt;
	EditText editTipoVent_healt;
	EditText editTipoDomo_healt;
	EditText editImporte2013_healt;
	EditText editDef2013_healt;
	EditText editImporte2012_healt;
	EditText editDef2012_healt;
	EditText editImporte2011_healt;
	EditText editDef2011_healt;
	EditText editImporte2010_healt;
	EditText editDef2010_healt;
	EditText editImporte2009_healt;
	EditText editDef2009_healt;
	EditText editImporte2008_healt;
	EditText editDef2008_healt;
	EditText editImporte2007_healt;
	EditText editDef2007_healt;
	EditText editImporte2006_healt;
	EditText editDef2006_healt;
	EditText editImporte2005_healt;
	EditText editDef2005_healt;
	EditText editImporte2004_healt;
	EditText editDef2004_healt;
	EditText editFechaCons_healt;
	EditText editFechaRefu_healt;

	RadioButton radioMar_healt;
	RadioButton radioLago_healt;
	RadioButton radioLaguna_healt;
	RadioButton radioRio_healt;
	RadioButton radioLigera_healt;
	RadioButton radioPesada_healt;
	RadioButton radioIrrePla_healt;
	RadioButton radioIrreEle_healt;
	RadioButton radioNinguna_healt;
	RadioButton radioPostes_healt;
	RadioButton radioAnunEspe_healt;
	RadioButton radioArboles_healt;
	RadioButton radioRegularGC_healt;
	RadioButton radioIrregularGC_healt;
	RadioButton radioChicos_healt;
	RadioButton radioMedianos_healt;
	RadioButton radioGrandes_healt;
	RadioButton radioTablaroca_healt;
	RadioButton radioPlastico_healt;
	RadioButton radioLamina_healt;
	RadioButton radioExcelenteEC_healt;
	RadioButton radioRegularEC_healt;
	RadioButton radioMaloEC_healt;
	RadioButton radioImperceptibles_healt;
	RadioButton radioLigeros_healt;
	RadioButton radioMuyGrandes_healt;
	
	Spinner spinnerTipoSuelo_healt;
	List<CatalogItem> listSpinnerTipoSuelo;
	Spinner spinnerTipoEstruc_healt;
	List<CatalogItem> listSpinnerTipoEstruc;
	Spinner spinnerMuros_healt;
	List<CatalogItem> listSpinnerMuros;
	Spinner spinnerTipoCime_healt;
	List<CatalogItem> listSpinnerTipoCime;
	Spinner spinnerEstruEntre_healt; // TODO
	Spinner spinnerTipoFormaCubi_healt;
	List<CatalogItem> listSpinnerTipoFormaCubi;

	CheckBox checkboxInundacion_healt;
	CheckBox checkboxHuracan_healt;
	CheckBox checkboxTerremoto_healt;
	CheckBox checkboxDeslave_healt;
	CheckBox checkboxDerrumbe_healt;

	CheckBox checkboxInundacionExpo_healt;
	CheckBox checkboxHuracanExpo_healt;
	CheckBox checkboxTerremotoExpo_healt;
	CheckBox checkboxDeslaveExpo_healt;
	CheckBox checkboxDerrumbeExpo_healt;

	Switch switchCueDomo_healt;
	Switch switchPreGolpe_healt;
	Switch switchExiObj_healt;
	Switch switchPreMuros_healt;
	Switch switchTipoVent_healt;
	Switch switchTipoDomo_healt;
	Switch switchPreHundi_healt;
	Switch switchEstruRefor_healt;
	Switch switchImporte2013_healt;
	Switch switchImporte2004_healt;
	Switch switchImporte2005_healt;
	Switch switchImporte2006_healt;
	Switch switchImporte2007_healt;
	Switch switchImporte2008_healt;
	Switch switchImporte2009_healt;
	Switch switchImporte2010_healt;
	Switch switchImporte2011_healt;
	Switch switchImporte2012_healt;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_healt);

		buttonUbicacion_healt = (ImageButton) myActivity.findViewById(R.id.buttonUbicacion_healt);
		buttonUbicacion_healt.setOnClickListener(new OnClickListener() {
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

		buttonSaveUp1_2_healt = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_healt);
		buttonSaveUp1_2_healt.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_healt = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_healt);
		buttonOutUp1_2_healt.setOnClickListener(new OutClickListener());
		
		buttonSaveBot1_2_healt = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_healt);
		buttonSaveBot1_2_healt.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_healt = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_healt);
		buttonOutBot1_2_healt.setOnClickListener(new OutClickListener());

		buttonCP_healt = (ImageButton) myActivity.findViewById(R.id.buttonCP_healt);
		buttonCP_healt.setOnClickListener(new SearchClickListener(this));


		editNombre_healt = (EditText)myActivity.findViewById(R.id.editNombre_healt);
		editNombre_healt.setText("Salud");

		// Tipo de suelo
		spinnerTipoSuelo_healt = (Spinner)myActivity.findViewById(R.id.spinnerTipoSuelo_healt);
		listSpinnerTipoSuelo = db.getCatalogList(Constants.SOIL_TYPE);
		spinnerTipoSuelo_healt.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoSuelo));
		
		editNumPisos_healt = (EditText)myActivity.findViewById(R.id.editNumPisos_healt);

		checkboxInundacionExpo_healt = (CheckBox)myActivity.findViewById(R.id.checkboxInundacionExpo_healt);
		checkboxHuracanExpo_healt = (CheckBox)myActivity.findViewById(R.id.checkboxHuracanExpo_healt);
		checkboxTerremotoExpo_healt = (CheckBox)myActivity.findViewById(R.id.checkboxTerremotoExpo_healt);
		checkboxDeslaveExpo_healt = (CheckBox)myActivity.findViewById(R.id.checkboxDeslaveExpo_healt);
		checkboxDerrumbeExpo_healt = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbeExpo_healt);

		checkboxInundacion_healt = (CheckBox)myActivity.findViewById(R.id.checkboxInundacionExist_healt);
		checkboxHuracan_healt = (CheckBox)myActivity.findViewById(R.id.checkboxHuracanExist_healt);
		checkboxTerremoto_healt = (CheckBox)myActivity.findViewById(R.id.checkboxTerremotoExist_healt);
		checkboxDeslave_healt = (CheckBox)myActivity.findViewById(R.id.checkboxDeslaveExist_healt);
		checkboxDerrumbe_healt = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbeExist_healt);

		pickerFechaRefu_healt = (DatePicker)myActivity.findViewById(R.id.pickerFechaRefu_healt);

		editCp_healt = (EditText)myActivity.findViewById(R.id.editCp_healt);
		editCalle_healt = (EditText)myActivity.findViewById(R.id.editCalle_healt);
		editCalle_healt.setText("Ote 166");
		editColonia_healt = (EditText)myActivity.findViewById(R.id.editColonia_healt);
		editMunicipio_healt = (EditText)myActivity.findViewById(R.id.editMunicipio_healt);
		editEstado_healt = (EditText)myActivity.findViewById(R.id.editEstado_healt);

		radioMar_healt = (RadioButton)myActivity.findViewById(R.id.radioMar_healt);
		radioLago_healt = (RadioButton)myActivity.findViewById(R.id.radioLago_healt);
		radioLaguna_healt = (RadioButton)myActivity.findViewById(R.id.radioLaguna_healt);
		radioRio_healt = (RadioButton)myActivity.findViewById(R.id.radioRio_healt);

		editLongitud_healt = (EditText)myActivity.findViewById(R.id.editLongitud_healt);
		editLongitud_healt.setText("111");
		editLatitud_healt = (EditText)myActivity.findViewById(R.id.editLatitud_healt);
		editLatitud_healt.setText("222");
		editAltitud_healt = (EditText)myActivity.findViewById(R.id.editAltitud_healt);
		editAltitud_healt.setText("333");

		editTipoVent_healt = (EditText)myActivity.findViewById(R.id.editTipoVent_healt);
		editTipoDomo_healt = (EditText)myActivity.findViewById(R.id.editTipoDomo_healt);
		editImporte2013_healt = (EditText) myActivity.findViewById(R.id.editImporte2013_healt);
		editDef2013_healt = (EditText) myActivity.findViewById(R.id.editDef2013_healt);
		editImporte2012_healt = (EditText) myActivity.findViewById(R.id.editImporte2012_healt);
		editDef2012_healt = (EditText) myActivity.findViewById(R.id.editDef2012_healt);
		editImporte2011_healt = (EditText) myActivity.findViewById(R.id.editImporte2011_healt);
		editDef2011_healt = (EditText) myActivity.findViewById(R.id.editDef2011_healt);
		editImporte2010_healt = (EditText) myActivity.findViewById(R.id.editImporte2010_healt);
		editDef2010_healt = (EditText) myActivity.findViewById(R.id.editDef2010_healt);
		editImporte2009_healt = (EditText) myActivity.findViewById(R.id.editImporte2009_healt);
		editDef2009_healt = (EditText) myActivity.findViewById(R.id.editDef2009_healt);
		editImporte2008_healt = (EditText) myActivity.findViewById(R.id.editImporte2008_healt);
		editDef2008_healt = (EditText) myActivity.findViewById(R.id.editDef2008_healt);
		editImporte2007_healt = (EditText) myActivity.findViewById(R.id.editImporte2007_healt);
		editDef2007_healt = (EditText) myActivity.findViewById(R.id.editDef2007_healt);
		editImporte2006_healt = (EditText) myActivity.findViewById(R.id.editImporte2006_healt);
		editDef2006_healt = (EditText) myActivity.findViewById(R.id.editDef2006_healt);
		editImporte2005_healt = (EditText) myActivity.findViewById(R.id.editImporte2005_healt);
		editDef2005_healt = (EditText) myActivity.findViewById(R.id.editDef2005_healt);
		editImporte2004_healt = (EditText) myActivity.findViewById(R.id.editImporte2004_healt);
		editDef2004_healt = (EditText) myActivity.findViewById(R.id.editDef2004_healt);
		
		editFechaCons_healt = (EditText) myActivity.findViewById(R.id.editFechaCons_healt);
		pickerFechaConst_healt = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_healt);
		pickerFechaConst_healt.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaCons_healt));
		editFechaRefu_healt = (EditText) myActivity.findViewById(R.id.editFechaRefu_healt);
		pickerFechaRefu_healt = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_healt);
		pickerFechaRefu_healt.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaRefu_healt));

		spinnerTipoEstruc_healt = (Spinner)myActivity.findViewById(R.id.spinnerTipoEstruc_healt);
		listSpinnerTipoEstruc = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipoEstruc_healt.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstruc));

		spinnerMuros_healt = (Spinner)myActivity.findViewById(R.id.spinnerMuros_healt);
		listSpinnerMuros = db.getCatalogList(Constants.WALL_TYPE);
		spinnerMuros_healt.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMuros));

		spinnerTipoCime_healt = (Spinner)myActivity.findViewById(R.id.spinnerTipoCime_healt);
		listSpinnerTipoCime = db.getCatalogList(Constants.FOUNDATION_TYPE);
		spinnerTipoCime_healt.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCime));

		spinnerTipoFormaCubi_healt = (Spinner)myActivity.findViewById(R.id.spinnerTipoFormaCubi_healt);
		listSpinnerTipoFormaCubi = db.getCatalogList(Constants.COVER_TYPES);
		spinnerTipoFormaCubi_healt.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoFormaCubi));

		radioLigera_healt = (RadioButton)myActivity.findViewById(R.id.radioLigera_healt);
		radioPesada_healt = (RadioButton)myActivity.findViewById(R.id.radioPesada_healt);
		radioIrrePla_healt = (RadioButton)myActivity.findViewById(R.id.radioIrrePla_healt);
		radioIrreEle_healt = (RadioButton)myActivity.findViewById(R.id.radioIrreEle_healt);
		radioNinguna_healt = (RadioButton)myActivity.findViewById(R.id.radioNinguna_healt);
		radioPostes_healt = (RadioButton)myActivity.findViewById(R.id.radioPostes_healt);
		radioAnunEspe_healt = (RadioButton)myActivity.findViewById(R.id.radioAnunEspe_healt);
		radioArboles_healt = (RadioButton)myActivity.findViewById(R.id.radioArboles_healt);
		radioRegularGC_healt = (RadioButton)myActivity.findViewById(R.id.radioRegularGC_healt);
		radioIrregularGC_healt = (RadioButton)myActivity.findViewById(R.id.radioIrregularGC_healt);

		radioChicos_healt = (RadioButton)myActivity.findViewById(R.id.radioChicos_healt);
		radioMedianos_healt = (RadioButton)myActivity.findViewById(R.id.radioMedianos_healt);
		radioGrandes_healt = (RadioButton)myActivity.findViewById(R.id.radioGrandes_healt);
		radioTablaroca_healt = (RadioButton)myActivity.findViewById(R.id.radioTablaroca_healt);
		radioPlastico_healt = (RadioButton)myActivity.findViewById(R.id.radioPlastico_healt);
		radioLamina_healt = (RadioButton)myActivity.findViewById(R.id.radioLamina_healt);
		radioImperceptibles_healt = (RadioButton)myActivity.findViewById(R.id.radioImperceptibles_healt);
		radioLigeros_healt = (RadioButton)myActivity.findViewById(R.id.radioLigeros_healt);
		radioMuyGrandes_healt = (RadioButton)myActivity.findViewById(R.id.radioMuyGrandes_healt);

		switchCueDomo_healt = (Switch)myActivity.findViewById(R.id.switchCueDomo_healt);
		switchPreGolpe_healt = (Switch)myActivity.findViewById(R.id.switchPreGolpe_healt);
		switchExiObj_healt = (Switch)myActivity.findViewById(R.id.switchExiObj_healt);
		switchPreMuros_healt = (Switch)myActivity.findViewById(R.id.switchPreMuros_healt);
		switchTipoVent_healt = (Switch)myActivity.findViewById(R.id.switchTipoVent_healt);
		switchTipoDomo_healt = (Switch)myActivity.findViewById(R.id.switchTipoDomo_healt);
		switchPreHundi_healt = (Switch)myActivity.findViewById(R.id.switchPreHun_healt);
		switchEstruRefor_healt = (Switch)myActivity.findViewById(R.id.switchEstruRefor_healt);

		switchImporte2013_healt = (Switch)myActivity.findViewById(R.id.switchImporte2013_healt);
		switchImporte2004_healt = (Switch)myActivity.findViewById(R.id.switchImporte2004_healt);
		switchImporte2005_healt = (Switch)myActivity.findViewById(R.id.switchImporte2005_healt);
		switchImporte2006_healt = (Switch)myActivity.findViewById(R.id.switchImporte2006_healt);
		switchImporte2007_healt = (Switch)myActivity.findViewById(R.id.switchImporte2007_healt);
		switchImporte2008_healt = (Switch)myActivity.findViewById(R.id.switchImporte2008_healt);
		switchImporte2009_healt = (Switch)myActivity.findViewById(R.id.switchImporte2009_healt);
		switchImporte2010_healt = (Switch)myActivity.findViewById(R.id.switchImporte2010_healt);
		switchImporte2011_healt = (Switch)myActivity.findViewById(R.id.switchImporte2011_healt);
		switchImporte2012_healt = (Switch)myActivity.findViewById(R.id.switchImporte2012_healt);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoHealt pojo = propierty.getHealtPptyDetail(propertyID, postalCodeID);
			editNombre_healt.setText(pojo.getName());
// TODO
//			if (pojo.getSoilType().compareTo(Constants.ROCA) == 0) {
//				radioMar_healt.setChecked(true);
//			} else if (pojo.getSoilType().compareTo(Constants.FIRME) == 0) {
//				radioLago_healt.setChecked(true);
//			} else if (pojo.getSoilType().compareTo(Constants.BLANDO) == 0) {
//				radioLaguna_healt.setChecked(true);
//			}

			editNumPisos_healt.setText(pojo.getLevels());
			spinnerTipoSuelo_healt.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoSuelo, pojo.getSoilType()));
			if (pojo.isFloodRisk()) {
				checkboxInundacionExpo_healt.setChecked(true);
			}
			if (pojo.isHurricaneRisk()) {
				checkboxHuracanExpo_healt.setChecked(true);
			}
			if (pojo.isEarthQuakeRisk()) {
				checkboxTerremotoExpo_healt.setChecked(true);
			}
			if (pojo.isLandSlideRisk()) {
				checkboxDeslaveExpo_healt.setChecked(true);
			}
			if (pojo.isCollapseRisk()) {
				checkboxDerrumbeExpo_healt.setChecked(true);
			}
			editCp_healt.setText(pojo.getPostalCode());
			editCalle_healt.setText(pojo.getAddress());
			editColonia_healt.setText(pojo.getColony());
			editMunicipio_healt.setText(pojo.getTown());
			editEstado_healt.setText(pojo.getStateName());
			if (pojo.getClosestWaterBody().compareTo(Constants.Mar) == 0) {
				radioMar_healt.setChecked(true);
			} else if (pojo.getClosestWaterBody().compareTo(Constants.Lago) == 0) {
				radioLago_healt.setChecked(true);
			} else if (pojo.getClosestWaterBody().compareTo(Constants.Laguna) == 0) {
				radioLaguna_healt.setChecked(true);
			} else if (pojo.getClosestWaterBody().compareTo(Constants.Rio) == 0) {
				radioRio_healt.setChecked(true);
			}
			editLongitud_healt.setText(String.valueOf(pojo.getLongitude()));
			editLatitud_healt.setText(String.valueOf(pojo.getLatitude()));
			editAltitud_healt.setText(String.valueOf(pojo.getAltitude()));

			spinnerTipoEstruc_healt.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstruc, pojo.getStructuraltype()));
			spinnerMuros_healt.setSelection(ListOperations.getItemPositionByID(listSpinnerMuros, pojo.getWallsType()));
			spinnerTipoCime_healt.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getFoundingType()));

			spinnerTipoFormaCubi_healt.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getCoverShape()));

			if (pojo.getCoverType().compareTo(Constants.Ligera_lamina) == 0)
			{
				radioLigera_healt.setChecked(true);
			} else if (pojo.getCoverType().compareTo(Constants.Pesada_Concreto) == 0) {
				radioPesada_healt.setChecked(true);
			}

			if (Integer.parseInt(pojo.getElevation()) == 1) {
				radioIrrePla_healt.setChecked(true);
			} else if (Integer.parseInt(pojo.getElevation()) == 2) {
				radioIrreEle_healt.setChecked(true);
			} else if (Integer.parseInt(pojo.getElevation()) == 3) {
				radioNinguna_healt.setChecked(true);
			}

			if (pojo.isCloseToPoles())
			{
				radioPostes_healt.setChecked(true);
			}
			if (pojo.isCloseToAds())
			{
				radioAnunEspe_healt.setChecked(true);
			}
			if (pojo.isCloseToTrees())
			{
				radioArboles_healt.setChecked(true);
			}

			if (pojo.isGeometryRegular())
			{
				radioRegularGC_healt.setChecked(true);
			}
			if (pojo.isGeometryIrregular())
			{
				radioIrregularGC_healt.setChecked(true);
			}
			if (pojo.getFachadeGlasserySize().compareTo(Constants.Cristales_Chicos) == 0) {
				radioChicos_healt.setChecked(true);
			} else if (pojo.getFachadeGlasserySize().compareTo(Constants.Cristales_Chicos) == 0) {
				radioMedianos_healt.setChecked(true);
			} else if (pojo.getFachadeGlasserySize().compareTo(Constants.Cristales_Chicos) == 0) {
				radioGrandes_healt.setChecked(true);
			}

			if (pojo.getFachadeMaterial().compareTo(Constants.Fachada_Plastico) == 0) {
				radioPlastico_healt.setChecked(true);
			}

			switchCueDomo_healt.setChecked(pojo.isDome());
			switchPreGolpe_healt.setChecked(pojo.isHitting());
			switchExiObj_healt.setChecked(pojo.isObjectsInRoof());
			if(switchTipoVent_healt.isChecked()){
				editTipoVent_healt.setText(pojo.getWindowProtection());
			}
			if(switchTipoDomo_healt.isChecked()){
				editTipoDomo_healt.setText(pojo.getDomeProtection());
			}

			switchPreHundi_healt.setChecked(pojo.isSinkingPresence());

			if (pojo.getSinkingSeverity() == 1) {
				radioChicos_healt.setChecked(true);
			} else if (pojo.getSinkingSeverity() == 2) {
				radioMedianos_healt.setChecked(true);
			} else if (pojo.getSinkingSeverity() == 3) {
				radioGrandes_healt.setChecked(true);
			}

			if (pojo.isFloodFormer()) {
				checkboxInundacion_healt.setChecked(true);
			}
			if (pojo.isHurricaneFormer()) {
				checkboxHuracan_healt.setChecked(true);
			}
			if (pojo.isEarthQuakeFormer()) {
				checkboxTerremoto_healt.setChecked(true);
			}
			if (pojo.isLandSlideFormer()) {
				checkboxDeslave_healt.setChecked(true);
			}
			if (pojo.isCollapseFormer()) {
				checkboxDerrumbe_healt.setChecked(true);
			}

			try
			{
				pickerFechaConst_healt.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getContructionDate())), 
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getContructionDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getContructionDate())), null);
			}
			catch(Exception ex)
			{
			}

			editImporte2013_healt.setText(pojo.getSiniestralityValues()[0].toString());
			editDef2013_healt.setText(pojo.getSiniestralityDescription()[0].toString());

			editImporte2012_healt.setText(pojo.getSiniestralityValues()[1].toString());
			editDef2012_healt.setText(pojo.getSiniestralityDescription()[1].toString());

			editImporte2011_healt.setText(pojo.getSiniestralityValues()[2].toString());
			editDef2011_healt.setText(pojo.getSiniestralityDescription()[2].toString());

			editImporte2010_healt.setText(pojo.getSiniestralityValues()[3].toString());
			editDef2010_healt.setText(pojo.getSiniestralityDescription()[3].toString());

			editImporte2009_healt.setText(pojo.getSiniestralityValues()[4].toString());
			editDef2009_healt.setText(pojo.getSiniestralityDescription()[4].toString());

			editImporte2008_healt.setText(pojo.getSiniestralityValues()[5].toString());
			editDef2008_healt.setText(pojo.getSiniestralityDescription()[5].toString());

			editImporte2007_healt.setText(pojo.getSiniestralityValues()[6].toString());
			editDef2007_healt.setText(pojo.getSiniestralityDescription()[6].toString());

			editImporte2006_healt.setText(pojo.getSiniestralityValues()[7].toString());
			editDef2006_healt.setText(pojo.getSiniestralityDescription()[7].toString());

			editImporte2005_healt.setText(pojo.getSiniestralityValues()[8].toString());
			editDef2005_healt.setText(pojo.getSiniestralityDescription()[8].toString());

			editImporte2004_healt.setText(pojo.getSiniestralityValues()[9].toString());
			editDef2004_healt.setText(pojo.getSiniestralityDescription()[9].toString());

			try {
				pickerFechaRefu_healt.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getReinformcementDate())), 
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getReinformcementDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getReinformcementDate())), new AplicaDateChangedListener(editFechaRefu_healt));
			}
			catch(Exception ex)
			{
			}
			try {
				pickerFechaConst_healt.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getConstructionDate())), 
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getReinformcementDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getReinformcementDate())), new AplicaDateChangedListener(editFechaCons_healt));
			}
			catch(Exception ex)
			{
			}

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.health_blue);

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
		View rootView = inflater.inflate(R.layout.healt, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	private class SaveClickListener implements OnClickListener {
		pojoHealt pojoHeal;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoHeal = new pojoHealt();

			if (validaCampos()) {

				pojoHeal.setPropertyID(myActivity.propertyID);
				SetPojoHealtP1(pojoHeal);
				HealtFragment2 healt2 = (HealtFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				healt2.SetPojoHealtP2(pojoHeal);

				try
				{
					process.processHealtSave(pojoHeal);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoHeal.getPropertyID();
					myActivity.postalCodeID = pojoHeal.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	public void CerrarBien()
	{
		buttonSaveUp1_2_healt.setEnabled(false);
		buttonOutUp1_2_healt.setEnabled(false);
		buttonSaveBot1_2_healt.setEnabled(false);
		buttonOutBot1_2_healt.setEnabled(false);
		buttonCP_healt.setEnabled(false);
		buttonUbicacion_healt.setEnabled(false);

		pickerFechaConst_healt.setEnabled(false);
		pickerFechaRefu_healt.setEnabled(false);

		editNombre_healt.setEnabled(false);
		editNumPisos_healt.setEnabled(false);
		editCp_healt.setEnabled(false);
		editCalle_healt.setEnabled(false);
		editColonia_healt.setEnabled(false);
		editMunicipio_healt.setEnabled(false);
		editEstado_healt.setEnabled(false);
		editLongitud_healt.setEnabled(false);
		editLatitud_healt.setEnabled(false);
		editAltitud_healt.setEnabled(false);
		editTipoVent_healt.setEnabled(false);
		editTipoDomo_healt.setEnabled(false);
		editImporte2013_healt.setEnabled(false);
		editDef2013_healt.setEnabled(false);
		editImporte2012_healt.setEnabled(false);
		editDef2012_healt.setEnabled(false);
		editImporte2011_healt.setEnabled(false);
		editDef2011_healt.setEnabled(false);
		editImporte2010_healt.setEnabled(false);
		editDef2010_healt.setEnabled(false);
		editImporte2009_healt.setEnabled(false);
		editDef2009_healt.setEnabled(false);
		editImporte2008_healt.setEnabled(false);
		editDef2008_healt.setEnabled(false);
		editImporte2007_healt.setEnabled(false);
		editDef2007_healt.setEnabled(false);
		editImporte2006_healt.setEnabled(false);
		editDef2006_healt.setEnabled(false);
		editImporte2005_healt.setEnabled(false);
		editDef2005_healt.setEnabled(false);
		editImporte2004_healt.setEnabled(false);
		editDef2004_healt.setEnabled(false);
		editFechaCons_healt.setEnabled(false);
		editFechaRefu_healt.setEnabled(false);

		radioMar_healt.setEnabled(false);
		radioLago_healt.setEnabled(false);
		radioLaguna_healt.setEnabled(false);
		radioRio_healt.setEnabled(false);
		radioLigera_healt.setEnabled(false);
		radioPesada_healt.setEnabled(false);
		radioIrrePla_healt.setEnabled(false);
		radioIrreEle_healt.setEnabled(false);
		radioNinguna_healt.setEnabled(false);
		radioPostes_healt.setEnabled(false);
		radioAnunEspe_healt.setEnabled(false);
		radioArboles_healt.setEnabled(false);
		radioRegularGC_healt.setEnabled(false);
		radioIrregularGC_healt.setEnabled(false);
		radioChicos_healt.setEnabled(false);
		radioMedianos_healt.setEnabled(false);
		radioGrandes_healt.setEnabled(false);
		radioTablaroca_healt.setEnabled(false);
		radioPlastico_healt.setEnabled(false);
		radioLamina_healt.setEnabled(false);
		radioExcelenteEC_healt.setEnabled(false);
		radioRegularEC_healt.setEnabled(false);
		radioMaloEC_healt.setEnabled(false);
		radioImperceptibles_healt.setEnabled(false);
		radioLigeros_healt.setEnabled(false);
		radioMuyGrandes_healt.setEnabled(false);
		
		spinnerTipoSuelo_healt.setEnabled(false);
		spinnerTipoEstruc_healt.setEnabled(false);
		spinnerMuros_healt.setEnabled(false);
		spinnerTipoCime_healt.setEnabled(false);
		spinnerEstruEntre_healt.setEnabled(false);
		spinnerTipoFormaCubi_healt.setEnabled(false);

		checkboxInundacion_healt.setEnabled(false);
		checkboxHuracan_healt.setEnabled(false);
		checkboxTerremoto_healt.setEnabled(false);
		checkboxDeslave_healt.setEnabled(false);
		checkboxDerrumbe_healt.setEnabled(false);

		checkboxInundacionExpo_healt.setEnabled(false);
		checkboxHuracanExpo_healt.setEnabled(false);
		checkboxTerremotoExpo_healt.setEnabled(false);
		checkboxDeslaveExpo_healt.setEnabled(false);
		checkboxDerrumbeExpo_healt.setEnabled(false);

		buttonSaveUp1_2_healt.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_healt.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot1_2_healt.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_healt.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_healt.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacion_healt.setImageResource(R.mipmap.ubicacion_blocked);
	}

	public boolean validaCampos(){
		if (editNombre_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el nombre del bien", Toast.LENGTH_LONG).show();
			editNombre_healt.requestFocus();
			return false;
		}
		
		if (editCp_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_healt.requestFocus();
			return false;
		}
		if (editColonia_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_healt.requestFocus();
			return false;
		}
		if (editMunicipio_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_healt.requestFocus();
			return false;
		}
		if (editCalle_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la calle correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCalle_healt.requestFocus();
			return false;
		}
		if (editEstado_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado_healt.requestFocus();
			return false;
		}
		if (editLongitud_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongitud_healt.requestFocus();
			return false;
		}
		if (editLatitud_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatitud_healt.requestFocus();
			return false;
		}
		if (editAltitud_healt.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltitud_healt.requestFocus();
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
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_healt.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_healt.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_healt.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);

            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.HEALT);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

	public void SetPojoHealtP1(pojoHealt pojo)
	{
		pojo.setPostalCodeID(postalCodeID);
		pojo.setName(editNombre_healt.getText().toString());

		pojo.setLevels(editNumPisos_healt.getText().toString());
		pojo.setSoilType(listSpinnerTipoSuelo.get(spinnerTipoSuelo_healt.getSelectedItemPosition()).getId());
		if (checkboxInundacionExpo_healt.isChecked()) {
			pojo.setFloodRisk(true);
		}
		if (checkboxHuracanExpo_healt.isChecked()) {
			pojo.setHurricaneRisk(true);
		}
		if (checkboxTerremotoExpo_healt.isChecked()) {
			pojo.setEarthQuakeRisk(true);
		}
		if (checkboxDeslaveExpo_healt.isChecked()) {
			pojo.setLandSlideRisk(true);
		}
		if (checkboxDerrumbeExpo_healt.isChecked()) {
			pojo.setCollapseRisk(true);
		}

		pojo.setPostalCode(editCp_healt.getText().toString());
		pojo.setAddress(editCalle_healt.getText().toString());
		pojo.setColony(editColonia_healt.getText().toString());
		pojo.setTown(editMunicipio_healt.getText().toString());
		pojo.setStateName(editEstado_healt.getText().toString());

		if (radioMar_healt.isChecked())
			pojo.setClosestWaterBody(Constants.Mar);
		else if (radioLago_healt.isChecked())
			pojo.setClosestWaterBody(Constants.Lago);
		else if (radioLaguna_healt.isChecked())
			pojo.setClosestWaterBody(Constants.Laguna);
		else if (radioRio_healt.isChecked())
			pojo.setClosestWaterBody(Constants.Rio);

		pojo.setLongitude(Double.valueOf(editLongitud_healt.getText().toString()));
		pojo.setLatitude(Double.valueOf(editLatitud_healt.getText().toString()));
		pojo.setAltitude(Double.valueOf(editAltitud_healt.getText().toString()));

		pojo.setStructuraltype(listSpinnerTipoEstruc.get(spinnerTipoEstruc_healt.getSelectedItemPosition()).getId());
		pojo.setWallsType(listSpinnerMuros.get(spinnerMuros_healt.getSelectedItemPosition()).getId());
		pojo.setFoundingType(listSpinnerTipoCime.get(spinnerTipoCime_healt.getSelectedItemPosition()).getId());

		pojo.setCoverType(listSpinnerTipoFormaCubi.get(spinnerTipoFormaCubi_healt.getSelectedItemPosition()).getId());

		if (radioLigera_healt.isChecked())
			pojo.setCoverType(Constants.Ligera_lamina);
		else if (radioPesada_healt.isChecked())
			pojo.setCoverType(Constants.Lago);

		if (radioIrrePla_healt.isChecked())
			pojo.setElevation("1");
		else if (radioIrreEle_healt.isChecked())
			pojo.setElevation("2");
		else if (radioNinguna_healt.isChecked())
			pojo.setElevation("3");

		if (radioPostes_healt.isChecked())
			pojo.setCloseToPoles(true);
		else if (radioAnunEspe_healt.isChecked())
			pojo.setCloseToAds(true);
		else if (radioArboles_healt.isChecked())
			pojo.setCloseToTrees(true);

		if (radioRegularGC_healt.isChecked())
			pojo.setGeometryRegular(true);
		else if (radioIrregularGC_healt.isChecked())
			pojo.setGeometryIrregular(true);

		if (radioChicos_healt.isChecked())
			pojo.setFachadeGlasserySize(Constants.Cristales_Chicos);
		else if (radioMedianos_healt.isChecked())
			pojo.setFachadeGlasserySize(Constants.Cristales_Medianos);
		else if (radioGrandes_healt.isChecked())
			pojo.setFachadeGlasserySize(Constants.Cristales_Grandes);


		if (radioPlastico_healt.isChecked()) {
			pojo.setFachadeMaterial("0");
		}

		if (switchCueDomo_healt.isChecked())
			pojo.setDome(true);
		if (switchPreGolpe_healt.isChecked())
			pojo.setHitting(true);
		if (switchExiObj_healt.isChecked())
			pojo.setObjectsInRoof(true);

		if (switchTipoVent_healt.isChecked()) {
			pojo.setWindowProtection(editTipoVent_healt.getText().toString());
		}

		if (switchTipoDomo_healt.isChecked()) {
			pojo.setDomeProtection(editTipoDomo_healt.getText().toString());
		}

		if (switchPreHundi_healt.isChecked()) {
			pojo.setSinkingPresence(true);
		}

		if (radioChicos_healt.isChecked())
			pojo.setSinkingSeverity(1);
		else if (radioMedianos_healt.isChecked())
			pojo.setSinkingSeverity(2);
		else if (radioGrandes_healt.isChecked())
			pojo.setSinkingSeverity(3);

		if (checkboxInundacion_healt.isChecked()) {
			pojo.setFloodFormer(true);
		}
		if (checkboxHuracan_healt.isChecked()) {
			pojo.setHurricaneFormer(true);
		}
		if (checkboxTerremoto_healt.isChecked()) {
			pojo.setEarthQuakeFormer(true);
		}
		if (checkboxDeslave_healt.isChecked()) {
			pojo.setLandSlideFormer(true);
		}
		if (checkboxDerrumbe_healt.isChecked()) {
			pojo.setCollapseFormer(true);
		}



		if (editImporte2013_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[0] = editImporte2013_healt.getText().toString();
			pojo.getSiniestralityDescription()[0] = editDef2013_healt.getText().toString();
		}

		if (editImporte2012_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[1] = editImporte2012_healt.getText().toString();
			pojo.getSiniestralityDescription()[1] = editDef2012_healt.getText().toString();
		}

		if (editImporte2011_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[2] = editImporte2011_healt.getText().toString();
			pojo.getSiniestralityDescription()[2] = editDef2011_healt.getText().toString();
		}

		if (editImporte2010_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[3] = editImporte2010_healt.getText().toString();
			pojo.getSiniestralityDescription()[3] = editDef2010_healt.getText().toString();
		}

		if (editImporte2009_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[4] = editImporte2009_healt.getText().toString();
			pojo.getSiniestralityDescription()[4] = editDef2009_healt.getText().toString();
		}

		if (editImporte2008_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[5] = editImporte2008_healt.getText().toString();
			pojo.getSiniestralityDescription()[5] = editDef2008_healt.getText().toString();
		}

		if (editImporte2007_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[6] = editImporte2007_healt.getText().toString();
			pojo.getSiniestralityDescription()[6] = editDef2007_healt.getText().toString();
		}

		if (editImporte2006_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[7] = editImporte2006_healt.getText().toString();
			pojo.getSiniestralityDescription()[7] = editDef2006_healt.getText().toString();
		}

		if (editImporte2005_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[8] = editImporte2005_healt.getText().toString();
			pojo.getSiniestralityDescription()[8] = editDef2005_healt.getText().toString();
		}

		if (editImporte2004_healt.getText().length() > 0) {
			pojo.getSiniestralityValues()[9] = editImporte2004_healt.getText().toString();
			pojo.getSiniestralityDescription()[9] = editDef2004_healt.getText().toString();
		}

		pojo.setConstructionDate(editFechaCons_healt.getText().toString());
		pojo.setReinformcementDate(editFechaRefu_healt.getText().toString());

	}

	private void MuestraPosicion(Location loc) {
	    if(loc != null)
	    {
	        editLatitud_healt.setText(String.valueOf(loc.getLatitude()));
	        editLongitud_healt.setText(String.valueOf(loc.getLongitude()));
	        editAltitud_healt.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatitud_healt.setText("(sin_datos)");
	    	editLongitud_healt.setText("(sin_datos)");
	    	editAltitud_healt.setText("(sin_datos)");
	    }
	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_healt.setText(colonia);
		editMunicipio_healt.setText(municipio);
		editEstado_healt.setText(estado);
		editCp_healt.setText(CP);
		postalCodeID = p_postalCodeID;

		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

}
