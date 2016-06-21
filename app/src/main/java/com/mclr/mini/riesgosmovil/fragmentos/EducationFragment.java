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
import com.mclr.mini.riesgosmovil.modelos.pojoEducation;
import com.mclr.mini.riesgosmovil.utilitarios.AplicaDateChangedListener;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

public class EducationFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID; // Este se inicializara en la selección del CP
	// Acceso a GPS
	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonSaveUp1_2_education;
	ImageButton buttonOutUp1_2_education;
	ImageButton buttonSaveBot1_2_education;
	ImageButton buttonOutBot1_2_education;
	ImageButton buttonCP_education;
	ImageButton buttonUbicacion_education;

	EditText editFechaRefu_education;
	DatePicker pickerFechaReforzo_education;

	EditText editNumIde_education;
	EditText editNombre_education;
	EditText editUsoGene_education;
	EditText editCp_education;
	EditText editCalle_education;
	EditText editColonia_education;
	EditText editMunicipio_education;
	EditText editEstado_education;
	EditText editLongitud_education;
	EditText editLatitud_education;
	EditText editAltitud_education;
	EditText editInstaEspe_education;
	EditText editTipoVent_education;
	EditText editTipoDomo_education;
	EditText editImporte2013_education;
	EditText editDef2013_education;
	EditText editImporte2012_education;
	EditText editDef2012_education;
	EditText editImporte2011_education;
	EditText editDef2011_education;
	EditText editImporte2010_education;
	EditText editDef2010_education;
	EditText editImporte2009_education;
	EditText editDef2009_education;
	EditText editImporte2008_education;
	EditText editDef2008_education;
	EditText editImporte2007_education;
	EditText editDef2007_education;
	EditText editImporte2006_education;
	EditText editDef2006_education;
	EditText editImporte2005_education;
	EditText editDef2005_education;
	EditText editImporte2004_education;
	EditText editDef2004_education;
	
	CheckBox checkboxInundacion_education;
	CheckBox checkboxHuracan_education;
	CheckBox checkboxTerremoto_education;
	CheckBox checkboxDeslave_education;
	CheckBox checkboxDerrumbe_education;
	CheckBox checkboxInundacionDef_education;
	CheckBox checkboxTerremotoDef_education;
	CheckBox checkboxDeslaveDef_education;
	CheckBox checkboxDerrumbeDef_education;
	
	Spinner spinnerTipo_education;
	List<CatalogItem> listSpinnerTipo;
	Spinner spinnerTipoEstruc_education;
	List<CatalogItem> listSpinnerTipoEstruc;
	Spinner spinnerMuros_education;
	List<CatalogItem> listSpinnerMuros;
	Spinner spinnerTipoCime_education;
	List<CatalogItem> listSpinnerTipoCime;
	Spinner spinnerEstruEntre_education;
	List<CatalogItem> listSpinnerEstruEntre;
	Spinner spinnerMateEntre_education;
	List<CatalogItem> listSpinnerMateEntre;
	Spinner spinnerTipoHerre_education;
	List<CatalogItem> listSpinnerTipoHerre;
	Spinner spinnerTipoVidri_education;
	List<CatalogItem> listSpinnerTipoVidri;
	Spinner spinnerTipoFacha_education;
	List<CatalogItem> listSpinnerTipoFacha;
	Spinner spinnerTipoFormaCubi_education;
	List<CatalogItem> listSpinnerTipoFormaCubi;

	RadioButton radioLigera_education;
	RadioButton radioPesada_education;
	RadioButton radioIrrePla_education;
	RadioButton radioIrreEle_education;
	RadioButton radioNinguna_education;
	RadioButton radioPostes_education;
	RadioButton radioAnunEspe_education;
	RadioButton radioArboles_education;
	RadioButton radioRegularGC_education;
	RadioButton radioIrregularGC_education;
	RadioButton radioChicos_education;
	RadioButton radioMedianos_education;
	RadioButton radioGrandes_education;
	RadioButton radioTablaroca_education;
	RadioButton radioPlastico_education;
	RadioButton radioLamina_education;
	RadioButton radioExcelenteEC_education;
	RadioButton radioRegularEC_education;
	RadioButton radioMaloEC_education;
	RadioButton radioImperceptibles_education;
	RadioButton radioLigeros_education;
	RadioButton radioMuyGrandes_education;
	RadioButton radioBuenoAE_education;
	RadioButton radioRegularAE_education;
	RadioButton radioMaloAE_education;
	RadioButton radioBuenoP_education;
	RadioButton radioRegularP_education;
	RadioButton radioMaloP_education;
	RadioButton radioBuenoPi_education;
	RadioButton radioRegularPi_education;
	RadioButton radioMaloPi_education;
	RadioButton radioBuenoAI_education;
	RadioButton radioRegularAI_education;
	RadioButton radioMaloAI_education;
	RadioButton radioBuenoZ_education;
	RadioButton radioRegularZ_education;
	RadioButton radioMaloZ_education;
	RadioButton radioBuenoMS_education;
	RadioButton radioRegularMS_education;
	RadioButton radioMaloMS_education;
	RadioButton radioBuenoPla_education;
	RadioButton radioRegularPla_education;
	RadioButton radioMaloPla_education;
	RadioButton radioBuenoL_education;
	RadioButton radioRegularL_education;
	RadioButton radioMaloL_education;
	RadioButton radioBuenoCa_education;
	RadioButton radioRegularCa_education;
	RadioButton radioMaloCa_education;
	RadioButton radioBuenoIS_education;
	RadioButton radioRegularIS_education;
	RadioButton radioMaloIS_education;
	RadioButton radioBuenoCan_education;
	RadioButton radioRegularCan_education;
	RadioButton radioMaloCan_education;
	RadioButton radioBuenoIE_education;
	RadioButton radioRegularIE_education;
	RadioButton radioMaloIE_education;
	
	Switch switchPreHun_education;
	Switch switchCueDomo_education;
	Switch switchPreGolpe_education;
	Switch switchExiObj_education;
	Switch switchPreMuros_education;
	Switch switchTipoVent_education;
	Switch switchTipoDomo_education;
	Switch switchEstruRefor_education;
	Switch switchImporte2013_education;
	Switch switchImporte2012_education;
	Switch switchImporte2011_education;
	Switch switchImporte2010_education;
	Switch switchImporte2009_education;
	Switch switchImporte2008_education;
	Switch switchImporte2007_education;
	Switch switchImporte2006_education;
	Switch switchImporte2005_education;
	Switch switchImporte2004_education;

	//List<CatalogItem> listaCatalogItems;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_education);

		buttonUbicacion_education = (ImageButton) myActivity.findViewById(R.id.buttonUbicacion_education);
		buttonUbicacion_education.setOnClickListener(new OnClickListener() {
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

		buttonSaveUp1_2_education = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_education);
		buttonSaveUp1_2_education.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_education = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_education);
		buttonOutUp1_2_education.setOnClickListener(new OutClickListener());

		buttonSaveBot1_2_education = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_education);
		buttonSaveBot1_2_education.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_education = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_education);
		buttonOutBot1_2_education.setOnClickListener(new OutClickListener());

		buttonCP_education = (ImageButton) myActivity.findViewById(R.id.buttonCP_education);
		buttonCP_education.setOnClickListener(new SearchClickListener(this));

		editNumIde_education = (EditText)myActivity.findViewById(R.id.editNumIde_education);
		editNumIde_education.setText("123456");
		editNombre_education = (EditText)myActivity.findViewById(R.id.editNombre_education);
		editNombre_education.setText("Educ");
		editUsoGene_education = (EditText)myActivity.findViewById(R.id.editUsoGene_education);
		editUsoGene_education.setText("Uso");
		spinnerTipo_education = (Spinner)myActivity.findViewById(R.id.spinnerTipo_education);
		listSpinnerTipo = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipo_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipo));
		editCp_education = (EditText)myActivity.findViewById(R.id.editCp_education);
		editCalle_education = (EditText)myActivity.findViewById(R.id.editCalle_education);
		editCalle_education.setText("Oriente 166");
		editColonia_education = (EditText)myActivity.findViewById(R.id.editColonia_education);
		editMunicipio_education = (EditText)myActivity.findViewById(R.id.editMunicipio_education);
		editEstado_education = (EditText)myActivity.findViewById(R.id.editEstado_education);
		editLongitud_education = (EditText)myActivity.findViewById(R.id.editLongitud_education);
		editLongitud_education.setText("1111");
		editLatitud_education = (EditText)myActivity.findViewById(R.id.editLatitud_education);
		editLatitud_education.setText("2222");
		editAltitud_education = (EditText)myActivity.findViewById(R.id.editAltitud_education);
		editAltitud_education.setText("3333");
		editInstaEspe_education = (EditText)myActivity.findViewById(R.id.editInstaEspe_education);
		editTipoVent_education = (EditText)myActivity.findViewById(R.id.editTipoVent_education);
		editTipoDomo_education = (EditText)myActivity.findViewById(R.id.editTipoDomo_education);
		editImporte2013_education = (EditText) myActivity.findViewById(R.id.editImporte2013_education);
		editDef2013_education = (EditText) myActivity.findViewById(R.id.editDef2013_education);
		editImporte2012_education = (EditText) myActivity.findViewById(R.id.editImporte2012_education);
		editDef2012_education = (EditText) myActivity.findViewById(R.id.editDef2012_education);
		editImporte2011_education = (EditText) myActivity.findViewById(R.id.editImporte2011_education);
		editDef2011_education = (EditText) myActivity.findViewById(R.id.editDef2011_education);
		editImporte2010_education = (EditText) myActivity.findViewById(R.id.editImporte2010_education);
		editDef2010_education = (EditText) myActivity.findViewById(R.id.editDef2010_education);
		editImporte2009_education = (EditText) myActivity.findViewById(R.id.editImporte2009_education);
		editDef2009_education = (EditText) myActivity.findViewById(R.id.editDef2009_education);
		editImporte2008_education = (EditText) myActivity.findViewById(R.id.editImporte2008_education);
		editDef2008_education = (EditText) myActivity.findViewById(R.id.editDef2008_education);
		editImporte2007_education = (EditText) myActivity.findViewById(R.id.editImporte2007_education);
		editDef2007_education = (EditText) myActivity.findViewById(R.id.editDef2007_education);
		editImporte2006_education = (EditText) myActivity.findViewById(R.id.editImporte2006_education);
		editDef2006_education = (EditText) myActivity.findViewById(R.id.editDef2006_education);
		editImporte2005_education = (EditText) myActivity.findViewById(R.id.editImporte2005_education);
		editDef2005_education = (EditText) myActivity.findViewById(R.id.editDef2005_education);
		editImporte2004_education = (EditText) myActivity.findViewById(R.id.editImporte2004_education);
		editDef2004_education = (EditText) myActivity.findViewById(R.id.editDef2004_education);
		
		checkboxInundacion_education = (CheckBox)myActivity.findViewById(R.id.checkboxInundacion_education);
		checkboxHuracan_education = (CheckBox)myActivity.findViewById(R.id.checkboxHuracan_education);
		checkboxTerremoto_education = (CheckBox)myActivity.findViewById(R.id.checkboxTerremoto_education);
		checkboxDeslave_education = (CheckBox)myActivity.findViewById(R.id.checkboxDeslave_education);
		checkboxDerrumbe_education = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbe_education);
		checkboxInundacionDef_education = (CheckBox)myActivity.findViewById(R.id.checkboxInundacionDef_education);
		checkboxTerremotoDef_education = (CheckBox)myActivity.findViewById(R.id.checkboxTerremotoDef_education);
		checkboxDeslaveDef_education = (CheckBox)myActivity.findViewById(R.id.checkboxDeslaveDef_education);
		checkboxDerrumbeDef_education = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbeDef_education);

		spinnerTipoEstruc_education = (Spinner)myActivity.findViewById(R.id.spinnerTipoEstruc_education);
		listSpinnerTipoEstruc = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipoEstruc_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstruc));

		spinnerMuros_education = (Spinner)myActivity.findViewById(R.id.spinnerMuros_education);
		listSpinnerMuros = db.getCatalogList(Constants.WALL_TYPE);
		spinnerMuros_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMuros));

		spinnerTipoCime_education = (Spinner)myActivity.findViewById(R.id.spinnerTipoCime_education);
		listSpinnerTipoCime = db.getCatalogList(Constants.FOUNDATION_TYPE);
		spinnerTipoCime_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCime));

		spinnerEstruEntre_education = (Spinner)myActivity.findViewById(R.id.spinnerEstruEntre_education);
		listSpinnerEstruEntre = db.getCatalogList(Constants.STOREYS_MATERIAL);
		spinnerEstruEntre_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerEstruEntre));

		spinnerMateEntre_education = (Spinner)myActivity.findViewById(R.id.spinnerMateEntre_education);
		listSpinnerMateEntre = db.getCatalogList(Constants.STOREYS_STRUCTURE);
		spinnerMateEntre_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMateEntre));

		spinnerTipoHerre_education = (Spinner)myActivity.findViewById(R.id.spinnerTipoHerre_education);
		listSpinnerTipoHerre = db.getCatalogList(Constants.BLACKSMITH_TYPE);
		spinnerTipoHerre_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoHerre));

		spinnerTipoVidri_education = (Spinner)myActivity.findViewById(R.id.spinnerTipoVidri_education);
		listSpinnerTipoVidri = db.getCatalogList(Constants.GLASSWARE_TYPE);
		spinnerTipoVidri_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoVidri));

		spinnerTipoFacha_education = (Spinner)myActivity.findViewById(R.id.spinnerTipoFacha_education);
		listSpinnerTipoFacha = db.getCatalogList(Constants.FACHADE_TYPE);
		spinnerTipoFacha_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoFacha));

		spinnerTipoFormaCubi_education = (Spinner)myActivity.findViewById(R.id.spinnerTipoFormaCubi_education);
		listSpinnerTipoFormaCubi = db.getCatalogList(Constants.CANOPY_SHAPE);
		spinnerTipoFormaCubi_education.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoFormaCubi));

		radioLigera_education = (RadioButton)myActivity.findViewById(R.id.radioLigera_education);
		radioPesada_education = (RadioButton)myActivity.findViewById(R.id.radioPesada_education);
		radioIrrePla_education = (RadioButton)myActivity.findViewById(R.id.radioIrrePla_education);
		radioIrreEle_education = (RadioButton)myActivity.findViewById(R.id.radioIrreEle_education);
		radioNinguna_education = (RadioButton)myActivity.findViewById(R.id.radioNinguna_education);
		radioPostes_education = (RadioButton)myActivity.findViewById(R.id.radioPostes_education);
		radioAnunEspe_education = (RadioButton)myActivity.findViewById(R.id.radioAnunEspe_education);
		radioArboles_education = (RadioButton)myActivity.findViewById(R.id.radioArboles_education);
		radioRegularGC_education = (RadioButton)myActivity.findViewById(R.id.radioRegularGC_education);
		radioIrregularGC_education = (RadioButton)myActivity.findViewById(R.id.radioIrregularGC_education);
		radioChicos_education = (RadioButton)myActivity.findViewById(R.id.radioChicos_education);
		radioMedianos_education = (RadioButton)myActivity.findViewById(R.id.radioMedianos_education);
		radioGrandes_education = (RadioButton)myActivity.findViewById(R.id.radioGrandes_education);
		radioTablaroca_education = (RadioButton)myActivity.findViewById(R.id.radioTablaroca_education);
		radioPlastico_education = (RadioButton)myActivity.findViewById(R.id.radioPlastico_education);
		radioLamina_education = (RadioButton)myActivity.findViewById(R.id.radioLamina_education);
		radioExcelenteEC_education = (RadioButton)myActivity.findViewById(R.id.radioExcelenteEC_education);
		radioRegularEC_education = (RadioButton)myActivity.findViewById(R.id.radioRegularEC_education);
		radioMaloEC_education = (RadioButton)myActivity.findViewById(R.id.radioMaloEC_education);
		radioImperceptibles_education = (RadioButton)myActivity.findViewById(R.id.radioImperceptibles_education);
		radioLigeros_education = (RadioButton)myActivity.findViewById(R.id.radioLigeros_education);
		radioMuyGrandes_education = (RadioButton)myActivity.findViewById(R.id.radioMuyGrandes_education);

		editFechaRefu_education = (EditText) myActivity.findViewById(R.id.editFechaRefu_education);
		pickerFechaReforzo_education  = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_education);
		pickerFechaReforzo_education.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaRefu_education));

		radioBuenoAE_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoAE_education);
		radioRegularAE_education = (RadioButton)myActivity.findViewById(R.id.radioRegularAE_education);
		radioMaloAE_education = (RadioButton)myActivity.findViewById(R.id.radioMaloAE_education);
		radioBuenoP_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoP_education);
		radioRegularP_education = (RadioButton)myActivity.findViewById(R.id.radioRegularP_education);
		radioMaloP_education = (RadioButton)myActivity.findViewById(R.id.radioMaloP_education);
		radioBuenoPi_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoPi_education);
		radioRegularPi_education = (RadioButton)myActivity.findViewById(R.id.radioRegularPi_education);
		radioMaloPi_education = (RadioButton)myActivity.findViewById(R.id.radioMaloPi_education);
		radioBuenoAI_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoAI_education);
		radioRegularAI_education = (RadioButton)myActivity.findViewById(R.id.radioRegularAI_education);
		radioMaloAI_education = (RadioButton)myActivity.findViewById(R.id.radioMaloAI_education);
		radioBuenoZ_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoZ_education);
		radioRegularZ_education = (RadioButton)myActivity.findViewById(R.id.radioRegularZ_education);
		radioMaloZ_education = (RadioButton)myActivity.findViewById(R.id.radioMaloZ_education);
		radioBuenoMS_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoMS_education);
		radioRegularMS_education = (RadioButton)myActivity.findViewById(R.id.radioRegularMS_education);
		radioMaloMS_education = (RadioButton)myActivity.findViewById(R.id.radioMaloMS_education);
		radioBuenoPla_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoPla_education);
		radioRegularPla_education = (RadioButton)myActivity.findViewById(R.id.radioRegularPla_education);
		radioMaloPla_education = (RadioButton)myActivity.findViewById(R.id.radioMaloPla_education);
		radioBuenoL_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoL_education);
		radioRegularL_education = (RadioButton)myActivity.findViewById(R.id.radioRegularL_education);
		radioMaloL_education = (RadioButton)myActivity.findViewById(R.id.radioMaloL_education);
		radioBuenoCa_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoCa_education);
		radioRegularCa_education = (RadioButton)myActivity.findViewById(R.id.radioRegularCa_education);
		radioMaloCa_education = (RadioButton)myActivity.findViewById(R.id.radioMaloCa_education);
		radioBuenoIS_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoIS_education);
		radioRegularIS_education = (RadioButton)myActivity.findViewById(R.id.radioRegularIS_education);
		radioMaloIS_education = (RadioButton)myActivity.findViewById(R.id.radioMaloIS_education);
		radioBuenoCan_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoCan_education);
		radioRegularCan_education = (RadioButton)myActivity.findViewById(R.id.radioRegularCan_education);
		radioMaloCan_education = (RadioButton)myActivity.findViewById(R.id.radioMaloCan_education);
		radioBuenoIE_education = (RadioButton)myActivity.findViewById(R.id.radioBuenoIE_education);
		radioRegularIE_education = (RadioButton)myActivity.findViewById(R.id.radioRegularIE_education);
		radioMaloIE_education = (RadioButton)myActivity.findViewById(R.id.radioMaloIE_education);
		
		switchPreHun_education = (Switch) myActivity.findViewById(R.id.switchPreHun_education);
		switchCueDomo_education = (Switch) myActivity.findViewById(R.id.switchCueDomo_education);
		switchPreGolpe_education = (Switch) myActivity.findViewById(R.id.switchPreGolpe_education);
		switchExiObj_education = (Switch) myActivity.findViewById(R.id.switchExiObj_education);
		switchPreMuros_education = (Switch) myActivity.findViewById(R.id.switchPreMuros_education);
		switchTipoVent_education = (Switch) myActivity.findViewById(R.id.switchTipoVent_education);
		switchTipoDomo_education = (Switch) myActivity.findViewById(R.id.switchTipoDomo_education);
		switchEstruRefor_education = (Switch) myActivity.findViewById(R.id.switchEstruRefor_education);
		switchImporte2013_education = (Switch)myActivity.findViewById(R.id.switchImporte2013_education);
		switchImporte2012_education = (Switch)myActivity.findViewById(R.id.switchImporte2012_education);
		switchImporte2011_education = (Switch)myActivity.findViewById(R.id.switchImporte2011_education);
		switchImporte2010_education = (Switch)myActivity.findViewById(R.id.switchImporte2010_education);
		switchImporte2009_education = (Switch)myActivity.findViewById(R.id.switchImporte2009_education);
		switchImporte2008_education = (Switch)myActivity.findViewById(R.id.switchImporte2008_education);
		switchImporte2007_education = (Switch)myActivity.findViewById(R.id.switchImporte2007_education);
		switchImporte2006_education = (Switch)myActivity.findViewById(R.id.switchImporte2006_education);
		switchImporte2005_education = (Switch)myActivity.findViewById(R.id.switchImporte2005_education);
		switchImporte2004_education = (Switch)myActivity.findViewById(R.id.switchImporte2004_education);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoEducation pojo = propierty.getEcuationPptyDetail(propertyID, postalCodeID);
			editNombre_education.setText(pojo.getBuildingName());
			editUsoGene_education.setText(pojo.getPropertyUse());
			editNumIde_education.setText(pojo.getBuildingNumber());
			spinnerTipo_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstruc, pojo.getEducationType()));
			if (pojo.isFloodFormer()) {
				checkboxInundacion_education.setChecked(true);
			}
			if (pojo.isHurricanFormer()) {
				checkboxHuracan_education.setChecked(true);
			}
			if (pojo.isEarthQuakeFormer()) {
				checkboxTerremoto_education.setChecked(true);
			}
			if (pojo.isSlidingFormer()) {
				checkboxDeslave_education.setChecked(true);
			}
			if (pojo.isRockFallFormer()) {
				checkboxDerrumbe_education.setChecked(true);
			}

			if (pojo.isFloodRisk()) {
				checkboxInundacionDef_education.setChecked(true);
			}
			if (pojo.isEarthQuakeRisk()) {
				checkboxTerremotoDef_education.setChecked(true);
			}
			if (pojo.isSlidingRisk()) {
				checkboxDeslaveDef_education.setChecked(true);
			}
			if (pojo.isSlidingRisk()) {
				checkboxDerrumbeDef_education.setChecked(true);
			}
			if (pojo.isRockFallFormer()) {
				checkboxDerrumbe_education.setChecked(true);
			}
			editMunicipio_education.setText(pojo.getTown());
			editColonia_education.setText(pojo.getColony());
			editCp_education.setText(pojo.getPostalCode());
			editCalle_education.setText(pojo.getAddress());
			editEstado_education.setText(pojo.getStateName());
			editLongitud_education.setText(String.valueOf(pojo.getLongitude()));
			editLatitud_education.setText(String.valueOf(pojo.getLatitude()));
			editAltitud_education.setText(String.valueOf(pojo.getAltitude()));

			spinnerTipoEstruc_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstruc, pojo.getStructureType()));
			spinnerMuros_education.setSelection(ListOperations.getItemPositionByID(listSpinnerMuros, pojo.getWalls()));
			spinnerTipoCime_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getFoundationType()));
			spinnerEstruEntre_education.setSelection(ListOperations.getItemPositionByID(listSpinnerEstruEntre, pojo.getStoryMaterial()));
			spinnerMateEntre_education.setSelection(ListOperations.getItemPositionByID(listSpinnerMateEntre, pojo.getStoryStructure()));
			spinnerTipoHerre_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoHerre, pojo.getBlacksmithType()));
			spinnerTipoVidri_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoVidri, pojo.getGlassWareType()));
			spinnerTipoFacha_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoFacha, pojo.getFachadeType()));
			editInstaEspe_education.setText(String.valueOf(pojo.getSpecialFacilities()));
			spinnerTipoFormaCubi_education.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoFormaCubi, pojo.getCoverShape()));

			if (pojo.getCoverType().compareTo(Constants.Ligera_lamina) == 0)
			{
				radioLigera_education.setChecked(true);
			} else if (pojo.getCoverType().compareTo(Constants.Pesada_Concreto) == 0) {
				radioPesada_education.setChecked(true);
			}

			if (Integer.parseInt(pojo.getElevation()) == 1) {
				radioIrrePla_education.setChecked(true);
			} else if (Integer.parseInt(pojo.getElevation()) == 2) {
				radioIrreEle_education.setChecked(true);
			} else if (Integer.parseInt(pojo.getElevation()) == 3) {
				radioNinguna_education.setChecked(true);
			}

			if (pojo.isCloseToPole())
			{
				radioPostes_education.setChecked(true);
			}
			if (pojo.isCloseToAds())
			{
				radioAnunEspe_education.setChecked(true);
			}
			if (pojo.isCloseToTrees())
			{
				radioArboles_education.setChecked(true);
			}

			if (pojo.isRegularGeometr())
			{
				radioRegularGC_education.setChecked(true);
			}
			if (pojo.isIrregularGeometry())
			{
				radioIrregularGC_education.setChecked(true);
			}
			if (pojo.getFachadeGlassSize().compareTo(Constants.Cristales_Chicos) == 0) {
				radioChicos_education.setChecked(true);
			} else if (pojo.getFachadeGlassSize().compareTo(Constants.Cristales_Chicos) == 0) {
				radioMedianos_education.setChecked(true);
			} else if (pojo.getFachadeGlassSize().compareTo(Constants.Cristales_Chicos) == 0) {
				radioGrandes_education.setChecked(true);
			}

			if (pojo.getFachadeMaterial().compareTo(Constants.Fachada_Plastico) == 0) {
				radioPlastico_education.setChecked(true);
			}

			switchCueDomo_education.setChecked(pojo.isDomes());
			switchPreGolpe_education.setChecked(pojo.isExternalStrPounding());
			switchExiObj_education.setChecked(pojo.isRoofItems());
			if(switchTipoVent_education.isChecked()){
				editTipoVent_education.setText(pojo.getWindowProtectionType());
			}
			if(switchTipoDomo_education.isChecked()){
				editTipoDomo_education.setText(pojo.getDomeProtectionType());
			}

			switchPreHun_education.setChecked(pojo.isSinkings());
			// TODO Gravedad hundimiento
			// ¿La estructura ha sido reforzada?

			//pojo.setReinformcementDate("");
			try
			{
				pickerFechaReforzo_education.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getReinformcementDate())), 
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getReinformcementDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getReinformcementDate())), new AplicaDateChangedListener(editFechaRefu_education));
			}
			catch(Exception ex)
			{
			}

			// Acabados exteriores
			if (pojo.getCnStExternal().compareTo(Constants.External_CS_Good) == 0) {
				radioBuenoAE_education.setChecked(true);
			} else if (pojo.getCnStExternal().compareTo(Constants.External_CS_Bad) == 0) {
				radioMaloAE_education.setChecked(true);
			} else if (pojo.getCnStExternal().compareTo(Constants.External_CS_Poor) == 0) {
				radioRegularAE_education.setChecked(true);
			}

			// Pisos
			if (pojo.getCnStFloors().compareTo(Constants.Floor_CS_Good) == 0) {
				radioBuenoP_education.setChecked(true);
			} else if (pojo.getCnStFloors().compareTo(Constants.Floor_CS_Bad) == 0) {
				radioMaloP_education.setChecked(true);
			} else if (pojo.getCnStFloors().compareTo(Constants.Floor_CS_Poor) == 0) {
				radioRegularP_education.setChecked(true);
			}

			// Pintura
			if (pojo.getCnStPainting().compareTo(Constants.Painting_CS_Good) == 0) {
				radioBuenoPi_education.setChecked(true);
			} else if (pojo.getCnStPainting().compareTo(Constants.Painting_CS_Bad) == 0) {
				radioMaloPi_education.setChecked(true);
			} else if (pojo.getCnStPainting().compareTo(Constants.Painting_CS_Poor) == 0) {
				radioRegularPi_education.setChecked(true);
			}

			// Acabados interiores
			if (pojo.getCnStInternal().compareTo(Constants.Internal_CS_Good) == 0) {
				radioBuenoAI_education.setChecked(true);
			} else if (pojo.getCnStInternal().compareTo(Constants.Internal_CS_Bad) == 0) {
				radioMaloAI_education.setChecked(true);
			} else if (pojo.getCnStInternal().compareTo(Constants.Internal_CS_Poor) == 0) {
				radioRegularAI_education.setChecked(true);
			}

			// Zoclos
			if (pojo.getCnStBaseboards().compareTo(Constants.BaseBoard_CS_Good) == 0) {
				radioBuenoZ_education.setChecked(true);
			} else if (pojo.getCnStBaseboards().compareTo(Constants.BaseBoard_CS_Bad) == 0) {
				radioMaloZ_education.setChecked(true);
			} else if (pojo.getCnStBaseboards().compareTo(Constants.BaseBoard_CS_Poor) == 0) {
				radioRegularZ_education.setChecked(true);
			}

			// Muebles sanitarios
			if (pojo.getCnStSanitaryFurn().compareTo(Constants.BthmFxt_CS_Good) == 0) {
				radioBuenoMS_education.setChecked(true);
			} else if (pojo.getCnStSanitaryFurn().compareTo(Constants.BaseBoard_CS_Bad) == 0) {
				radioMaloMS_education.setChecked(true);
			} else if (pojo.getCnStSanitaryFurn().compareTo(Constants.BaseBoard_CS_Poor) == 0) {
				radioRegularMS_education.setChecked(true);
			}

			// Plafones
			if (pojo.getCnStCeiling().compareTo(Constants.Ceiling_CS_Good) == 0) {
				radioBuenoPla_education.setChecked(true);
			} else if (pojo.getCnStCeiling().compareTo(Constants.Ceiling_CS_Bad) == 0) {
				radioMaloPla_education.setChecked(true);
			} else if (pojo.getCnStCeiling().compareTo(Constants.Ceiling_CS_Poor) == 0) {
				radioRegularPla_education.setChecked(true);
			}

			// Lambrines
			if (pojo.getCnStTrimmings().compareTo(Constants.Trimming_CS_Good) == 0) {
				radioBuenoL_education.setChecked(true);
			} else if (pojo.getCnStTrimmings().compareTo(Constants.Trimming_CS_Bad) == 0) {
				radioMaloL_education.setChecked(true);
			} else if (pojo.getCnStTrimmings().compareTo(Constants.Trimming_CS_Poor) == 0) {
				radioRegularL_education.setChecked(true);
			}

			// Carpintería
			if (pojo.getCnStCarpintery().compareTo(Constants.Carpintery_CS_Good) == 0) {
				radioBuenoCa_education.setChecked(true);
			} else if (pojo.getCnStCarpintery().compareTo(Constants.Carpintery_CS_Bad) == 0) {
				radioMaloCa_education.setChecked(true);
			} else if (pojo.getCnStCarpintery().compareTo(Constants.Carpintery_CS_Poor) == 0) {
				radioRegularCa_education.setChecked(true);
			}

			// Instalación sanitaría
			if (pojo.getCnStPlumbing().compareTo(Constants.Plumbing_CS_Good) == 0) {
				radioBuenoIS_education.setChecked(true);
			} else if (pojo.getCnStPlumbing().compareTo(Constants.Plumbing_CS_Bad) == 0) {
				radioMaloIS_education.setChecked(true);
			} else if (pojo.getCnStPlumbing().compareTo(Constants.Plumbing_CS_Poor) == 0) {
				radioRegularIS_education.setChecked(true);
			}

			// Cancelería
			if (pojo.getCnStCancel().compareTo(Constants.Moulding_CS_Good) == 0) {
				radioBuenoCan_education.setChecked(true);
			} else if (pojo.getCnStCancel().compareTo(Constants.Moulding_CS_Bad) == 0) {
				radioMaloCan_education.setChecked(true);
			} else if (pojo.getCnStCancel().compareTo(Constants.Moulding_CS_Poor) == 0) {
				radioRegularCan_education.setChecked(true);
			}

			// Instalación eléctrica
			if (pojo.getCnStElectricInst().compareTo(Constants.Electric_CS_Good) == 0) {
				radioBuenoIE_education.setChecked(true);
			} else if (pojo.getCnStElectricInst().compareTo(Constants.Electric_CS_Bad) == 0) {
				radioMaloIE_education.setChecked(true);
			} else if (pojo.getCnStElectricInst().compareTo(Constants.Electric_CS_Poor) == 0) {
				radioRegularIE_education.setChecked(true);
			}

			editImporte2013_education.setText(pojo.getSiniestralityValue()[0].toString());
			editDef2013_education.setText(pojo.getSiniestralityDescription()[0].toString());

			editImporte2012_education.setText(pojo.getSiniestralityValue()[1].toString());
			editDef2012_education.setText(pojo.getSiniestralityDescription()[1].toString());

			editImporte2011_education.setText(pojo.getSiniestralityValue()[2].toString());
			editDef2011_education.setText(pojo.getSiniestralityDescription()[2].toString());

			editImporte2010_education.setText(pojo.getSiniestralityValue()[3].toString());
			editDef2010_education.setText(pojo.getSiniestralityDescription()[3].toString());

			editImporte2009_education.setText(pojo.getSiniestralityValue()[4].toString());
			editDef2009_education.setText(pojo.getSiniestralityDescription()[4].toString());

			editImporte2008_education.setText(pojo.getSiniestralityValue()[5].toString());
			editDef2008_education.setText(pojo.getSiniestralityDescription()[5].toString());

			editImporte2007_education.setText(pojo.getSiniestralityValue()[6].toString());
			editDef2007_education.setText(pojo.getSiniestralityDescription()[6].toString());

			editImporte2006_education.setText(pojo.getSiniestralityValue()[7].toString());
			editDef2006_education.setText(pojo.getSiniestralityDescription()[7].toString());

			editImporte2005_education.setText(pojo.getSiniestralityValue()[8].toString());
			editDef2005_education.setText(pojo.getSiniestralityDescription()[8].toString());

			editImporte2004_education.setText(pojo.getSiniestralityValue()[9].toString());
			editDef2004_education.setText(pojo.getSiniestralityDescription()[9].toString());

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.education_blue);
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
		View rootView = inflater.inflate(R.layout.educacion_1_2, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}
	
	public void CerrarBien()
	{
		buttonSaveUp1_2_education.setEnabled(false);
		//buttonOutUp1_2_education.setEnabled(false);
		buttonSaveBot1_2_education.setEnabled(false);
		//buttonOutBot1_2_education.setEnabled(false);
		buttonCP_education.setEnabled(false);
		buttonUbicacion_education.setEnabled(false);

		editFechaRefu_education.setEnabled(false);
		pickerFechaReforzo_education.setEnabled(false);

		editNumIde_education.setEnabled(false);
		editNombre_education.setEnabled(false);
		editUsoGene_education.setEnabled(false);
		editCp_education.setEnabled(false);
		editCalle_education.setEnabled(false);
		editColonia_education.setEnabled(false);
		editMunicipio_education.setEnabled(false);
		editEstado_education.setEnabled(false);
		editLongitud_education.setEnabled(false);
		editLatitud_education.setEnabled(false);
		editAltitud_education.setEnabled(false);
		editInstaEspe_education.setEnabled(false);
		editTipoVent_education.setEnabled(false);
		editTipoDomo_education.setEnabled(false);
		editImporte2013_education.setEnabled(false);
		editDef2013_education.setEnabled(false);
		editImporte2012_education.setEnabled(false);
		editDef2012_education.setEnabled(false);
		editImporte2011_education.setEnabled(false);
		editDef2011_education.setEnabled(false);
		editImporte2010_education.setEnabled(false);
		editDef2010_education.setEnabled(false);
		editImporte2009_education.setEnabled(false);
		editDef2009_education.setEnabled(false);
		editImporte2008_education.setEnabled(false);
		editDef2008_education.setEnabled(false);
		editImporte2007_education.setEnabled(false);
		editDef2007_education.setEnabled(false);
		editImporte2006_education.setEnabled(false);
		editDef2006_education.setEnabled(false);
		editImporte2005_education.setEnabled(false);
		editDef2005_education.setEnabled(false);
		editImporte2004_education.setEnabled(false);
		editDef2004_education.setEnabled(false);
		
		checkboxInundacion_education.setEnabled(false);
		checkboxHuracan_education.setEnabled(false);
		checkboxTerremoto_education.setEnabled(false);
		checkboxDeslave_education.setEnabled(false);
		checkboxDerrumbe_education.setEnabled(false);
		checkboxInundacionDef_education.setEnabled(false);
		checkboxTerremotoDef_education.setEnabled(false);
		checkboxDeslaveDef_education.setEnabled(false);
		checkboxDerrumbeDef_education.setEnabled(false);
		
		spinnerTipo_education.setEnabled(false);
		spinnerTipoEstruc_education.setEnabled(false);
		spinnerMuros_education.setEnabled(false);
		spinnerTipoCime_education.setEnabled(false);
		spinnerEstruEntre_education.setEnabled(false);
		spinnerMateEntre_education.setEnabled(false);
		spinnerTipoHerre_education.setEnabled(false);
		spinnerTipoVidri_education.setEnabled(false);
		spinnerTipoFacha_education.setEnabled(false);
		spinnerTipoFormaCubi_education.setEnabled(false);

		radioLigera_education.setEnabled(false);
		radioPesada_education.setEnabled(false);
		radioIrrePla_education.setEnabled(false);
		radioIrreEle_education.setEnabled(false);
		radioNinguna_education.setEnabled(false);
		radioPostes_education.setEnabled(false);
		radioAnunEspe_education.setEnabled(false);
		radioArboles_education.setEnabled(false);
		radioRegularGC_education.setEnabled(false);
		radioIrregularGC_education.setEnabled(false);
		radioChicos_education.setEnabled(false);
		radioMedianos_education.setEnabled(false);
		radioGrandes_education.setEnabled(false);
		radioTablaroca_education.setEnabled(false);
		radioPlastico_education.setEnabled(false);
		radioLamina_education.setEnabled(false);
		radioExcelenteEC_education.setEnabled(false);
		radioRegularEC_education.setEnabled(false);
		radioMaloEC_education.setEnabled(false);
		radioImperceptibles_education.setEnabled(false);
		radioLigeros_education.setEnabled(false);
		radioMuyGrandes_education.setEnabled(false);
		radioBuenoAE_education.setEnabled(false);
		radioRegularAE_education.setEnabled(false);
		radioMaloAE_education.setEnabled(false);
		radioBuenoP_education.setEnabled(false);
		radioRegularP_education.setEnabled(false);
		radioMaloP_education.setEnabled(false);
		radioBuenoPi_education.setEnabled(false);
		radioRegularPi_education.setEnabled(false);
		radioMaloPi_education.setEnabled(false);
		radioBuenoAI_education.setEnabled(false);
		radioRegularAI_education.setEnabled(false);
		radioMaloAI_education.setEnabled(false);
		radioBuenoZ_education.setEnabled(false);
		radioRegularZ_education.setEnabled(false);
		radioMaloZ_education.setEnabled(false);
		radioBuenoMS_education.setEnabled(false);
		radioRegularMS_education.setEnabled(false);
		radioMaloMS_education.setEnabled(false);
		radioBuenoPla_education.setEnabled(false);
		radioRegularPla_education.setEnabled(false);
		radioMaloPla_education.setEnabled(false);
		radioBuenoL_education.setEnabled(false);
		radioRegularL_education.setEnabled(false);
		radioMaloL_education.setEnabled(false);
		radioBuenoCa_education.setEnabled(false);
		radioRegularCa_education.setEnabled(false);
		radioMaloCa_education.setEnabled(false);
		radioBuenoIS_education.setEnabled(false);
		radioRegularIS_education.setEnabled(false);
		radioMaloIS_education.setEnabled(false);
		radioBuenoCan_education.setEnabled(false);
		radioRegularCan_education.setEnabled(false);
		radioMaloCan_education.setEnabled(false);
		radioBuenoIE_education.setEnabled(false);
		radioRegularIE_education.setEnabled(false);
		radioMaloIE_education.setEnabled(false);

		buttonSaveUp1_2_education.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_education.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot1_2_education.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_education.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_education.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacion_education.setImageResource(R.mipmap.ubicacion_blocked);
	}

	public boolean validaCampos() {
		if (editNombre_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el nombre correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editNombre_education.requestFocus();
			return false;
		}
		if (editUsoGene_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el uso general del bien", Toast.LENGTH_LONG).show();
			editUsoGene_education.requestFocus();
			return false;
		}
		if (editNumIde_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de identificación del bien", Toast.LENGTH_LONG).show();
			editNumIde_education.requestFocus();
			return false;
		}
		if(spinnerTipo_education.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de centro acuícola", Toast.LENGTH_LONG).show();
			spinnerTipo_education.requestFocus();
			return false;
		}
		
		if (editCp_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_education.requestFocus();
			return false;
		}
		if (editColonia_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_education.requestFocus();
			return false;
		}
		if (editMunicipio_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_education.requestFocus();
			return false;
		}
		if (editCalle_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la calle correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCalle_education.requestFocus();
			return false;
		}
		if (editEstado_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado_education.requestFocus();
			return false;
		}
		if (editLongitud_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongitud_education.requestFocus();
			return false;
		}
		if (editLatitud_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatitud_education.requestFocus();
			return false;
		}
		if (editAltitud_education.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltitud_education.requestFocus();
			return false;
		}
		return true;
	}

	private class SaveClickListener implements OnClickListener {
		pojoEducation pojoEdu;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoEdu = new pojoEducation();
			if (validaCampos()) {
				pojoEdu.setPropertyID(myActivity.propertyID);
				SetPojoEducationP1(pojoEdu);
				EducationFragment2 historico2 = (EducationFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				historico2.SetPojoEducationP2(pojoEdu);

				try
				{
					process.processEducationSave(pojoEdu);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoEdu.getPropertyID();
					myActivity.postalCodeID = pojoEdu.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	private class OutClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
			try {
				myActivity.finish();
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
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_education.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_education.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_education.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);

            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.EDUCATION);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

	public void SetPojoEducationP1(pojoEducation pojo)
	{
		pojo.setPostalCodeID(postalCodeID);
		pojo.setBuildingName(editNombre_education.getText().toString());
		pojo.setPropertyUse(editUsoGene_education.getText().toString());
		pojo.setBuildingNumber(editNumIde_education.getText().toString());
		pojo.setEducationType(listSpinnerTipoEstruc.get(spinnerTipoEstruc_education.getSelectedItemPosition()).getId());

		if (checkboxInundacion_education.isChecked()) {
			pojo.setFloodFormer(true);
		}
		if (checkboxHuracan_education.isChecked()) {
			pojo.setHurricanFormer(true);
		}
		if (checkboxTerremoto_education.isChecked()) {
			pojo.setEarthQuakeFormer(true);
		}
		if (checkboxDeslave_education.isChecked()) {
			pojo.setSlidingFormer(true);
		}
		if (checkboxDerrumbe_education.isChecked()) {
			pojo.setRockFallFormer(true);
		}

		if (checkboxInundacionDef_education.isChecked()) {
			pojo.setFloodRisk(true);
		}
		if (checkboxTerremotoDef_education.isChecked()) {
			pojo.setEarthQuakeRisk(true);
		}
		if (checkboxDeslaveDef_education.isChecked()) {
			pojo.setSlidingRisk(true);
		}
		if (checkboxDerrumbeDef_education.isChecked()) {
			//pojo.setSlidingRisk(true);
		}
		if (checkboxDerrumbe_education.isChecked()) {
			pojo.setRockFallFormer(true);
		}
		pojo.setTown(editMunicipio_education.getText().toString());
		pojo.setColony(editColonia_education.getText().toString());
		pojo.setPostalCode(editCp_education.getText().toString());
		pojo.setAddress(editCalle_education.getText().toString());
		pojo.setStateName(editEstado_education.getText().toString());
		pojo.setLongitude(Double.valueOf(editLongitud_education.getText().toString()));
		pojo.setLatitude(Double.valueOf(editLatitud_education.getText().toString()));
		pojo.setAltitude(Double.valueOf(editAltitud_education.getText().toString()));

		pojo.setStructureType(listSpinnerTipoEstruc.get(spinnerTipoEstruc_education.getSelectedItemPosition()).getId());
		pojo.setWalls(listSpinnerMuros.get(spinnerMuros_education.getSelectedItemPosition()).getId());
		pojo.setFoundationType(listSpinnerTipoCime.get(spinnerTipoCime_education.getSelectedItemPosition()).getId());
		pojo.setStoryMaterial(listSpinnerEstruEntre.get(spinnerEstruEntre_education.getSelectedItemPosition()).getId());
		pojo.setStoryStructure(listSpinnerMateEntre.get(spinnerMateEntre_education.getSelectedItemPosition()).getId());
		pojo.setBlacksmithType(listSpinnerTipoHerre.get(spinnerTipoHerre_education.getSelectedItemPosition()).getId());
		pojo.setGlassWareType(listSpinnerTipoVidri.get(spinnerTipoVidri_education.getSelectedItemPosition()).getId());
		pojo.setFachadeType(listSpinnerTipoFacha.get(spinnerTipoFacha_education.getSelectedItemPosition()).getId());
		pojo.setSpecialFacilities(editInstaEspe_education.getText().toString());
		pojo.setCoverShape(listSpinnerTipoFormaCubi.get(spinnerTipoFormaCubi_education.getSelectedItemPosition()).getId());

		if (radioLigera_education.isChecked())
			pojo.setCoverType(Constants.Ligera_lamina);
		else if (radioPesada_education.isChecked())
			pojo.setCoverType(Constants.Pesada_Concreto);

		if (radioIrrePla_education.isChecked())
			pojo.setElevation("1");
		else if (radioIrreEle_education.isChecked())
			pojo.setElevation("2");
		else if (radioNinguna_education.isChecked())
			pojo.setElevation("3");

		if (radioPostes_education.isChecked())
			pojo.setCloseToPole(true);
		else if (radioAnunEspe_education.isChecked())
			pojo.setCloseToAds(true);
		else if (radioArboles_education.isChecked())
			pojo.setCloseToTrees(true);

		if (radioRegularGC_education.isChecked())
			pojo.setRegularGeometr(true);
		else if (radioIrregularGC_education.isChecked())
			pojo.setIrregularGeometry(true);

		if (radioChicos_education.isChecked())
			pojo.setFachadeGlassSize(Constants.Cristales_Chicos);
		else if (radioMedianos_education.isChecked())
			pojo.setFachadeGlassSize(Constants.Cristales_Medianos);
		else if (radioGrandes_education.isChecked())
			pojo.setFachadeGlassSize(Constants.Cristales_Grandes);

//		if (pojo.getFachadeMaterial().compareTo(Constants.Fachada_Plastico) == 0) {
//			radioPlastico_education.setChecked(true);
//		}

		if (switchCueDomo_education.isChecked())
			pojo.setDomes(true);
		if (switchPreGolpe_education.isChecked())
			pojo.setExternalStrPounding(true);
		if (switchExiObj_education.isChecked())
			pojo.setRoofItems(true);

		if (switchTipoVent_education.isChecked())
		{
			pojo.setWindowProtectionType(editTipoVent_education.getText().toString());
		}

		if (switchTipoDomo_education.isChecked())
		{
			pojo.setDomeProtectionType(editTipoDomo_education.getText().toString());
		}

		if (switchPreHun_education.isChecked())
			pojo.setSinkings(true);

		pojo.setReinformcementDate(editFechaRefu_education.getText().toString());

		// Acabados exteriores
		if (radioBuenoAE_education.isChecked())
			pojo.setCnStExternal(Constants.External_CS_Good);
		else if (radioMaloAE_education.isChecked())
			pojo.setCnStExternal(Constants.External_CS_Bad);
		else if (radioRegularAE_education.isChecked())
			pojo.setCnStExternal(Constants.External_CS_Poor);

		// Pisos
		if (radioBuenoP_education.isChecked())
			pojo.setCnStFloors(Constants.Floor_CS_Good);
		else if (radioMaloP_education.isChecked())
			pojo.setCnStFloors(Constants.Floor_CS_Bad);
		else if (radioRegularP_education.isChecked())
			pojo.setCnStFloors(Constants.Floor_CS_Poor);

		// Pintura
		if (radioBuenoPi_education.isChecked())
			pojo.setCnStPainting(Constants.Painting_CS_Good);
		else if (radioMaloPi_education.isChecked())
			pojo.setCnStPainting(Constants.Painting_CS_Bad);
		else if (radioRegularPi_education.isChecked())
			pojo.setCnStPainting(Constants.Painting_CS_Poor);

		// Acabados interiores
		if (radioBuenoAI_education.isChecked())
			pojo.setCnStInternal(Constants.Internal_CS_Good);
		else if (radioMaloAI_education.isChecked())
			pojo.setCnStInternal(Constants.Internal_CS_Bad);
		else if (radioRegularAI_education.isChecked())
			pojo.setCnStInternal(Constants.Internal_CS_Poor);

		// Zoclos
		if (radioBuenoZ_education.isChecked())
			pojo.setCnStInternal(Constants.Internal_CS_Good);
		else if (radioMaloZ_education.isChecked())
			pojo.setCnStInternal(Constants.Internal_CS_Bad);
		else if (radioRegularZ_education.isChecked())
			pojo.setCnStInternal(Constants.Internal_CS_Poor);

		// Muebles sanitarios
		if (radioBuenoMS_education.isChecked())
			pojo.setCnStSanitaryFurn(Constants.BthmFxt_CS_Good);
		else if (radioMaloMS_education.isChecked())
			pojo.setCnStSanitaryFurn(Constants.BthmFxt_CS_Bad);
		else if (radioRegularMS_education.isChecked())
			pojo.setCnStSanitaryFurn(Constants.BthmFxt_CS_Poor);

		// Plafones
		if (radioBuenoPla_education.isChecked())
			pojo.setCnStCeiling(Constants.Ceiling_CS_Good);
		else if (radioMaloPla_education.isChecked())
			pojo.setCnStCeiling(Constants.Ceiling_CS_Bad);
		else if (radioRegularPla_education.isChecked())
			pojo.setCnStCeiling(Constants.Ceiling_CS_Poor);

		// Lambrines
		if (radioBuenoL_education.isChecked())
			pojo.setCnStTrimmings(Constants.Trimming_CS_Good);
		else if (radioMaloL_education.isChecked())
			pojo.setCnStTrimmings(Constants.Trimming_CS_Bad);
		else if (radioRegularL_education.isChecked())
			pojo.setCnStTrimmings(Constants.Trimming_CS_Poor);

		// Carpintería
		if (radioBuenoCa_education.isChecked())
			pojo.setCnStCarpintery(Constants.Carpintery_CS_Good);
		else if (radioMaloCa_education.isChecked())
			pojo.setCnStCarpintery(Constants.Carpintery_CS_Bad);
		else if (radioRegularCa_education.isChecked())
			pojo.setCnStCarpintery(Constants.Carpintery_CS_Poor);

		// Instalación sanitaría
		if (radioBuenoIS_education.isChecked())
			pojo.setCnStPlumbing(Constants.Plumbing_CS_Good);
		else if (radioMaloIS_education.isChecked())
			pojo.setCnStPlumbing(Constants.Plumbing_CS_Bad);
		else if (radioRegularIS_education.isChecked())
			pojo.setCnStPlumbing(Constants.Plumbing_CS_Poor);

		// Cancelería
		if (radioBuenoCan_education.isChecked())
			pojo.setCnStCancel(Constants.Moulding_CS_Good);
		else if (radioMaloCan_education.isChecked())
			pojo.setCnStCancel(Constants.Moulding_CS_Bad);
		else if (radioRegularCan_education.isChecked())
			pojo.setCnStCancel(Constants.Moulding_CS_Poor);

		// Instalación eléctrica
		if (radioBuenoIE_education.isChecked())
			pojo.setCnStElectricInst(Constants.Electric_CS_Good);
		else if (radioMaloIE_education.isChecked())
			pojo.setCnStElectricInst(Constants.Electric_CS_Bad);
		else if (radioRegularIE_education.isChecked())
			pojo.setCnStElectricInst(Constants.Electric_CS_Poor);

		if (editImporte2013_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[0] = editImporte2013_education.getText().toString();
			pojo.getSiniestralityDescription()[0] = editDef2013_education.getText().toString();
		}

		if (editImporte2012_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[1] = editImporte2012_education.getText().toString();
			pojo.getSiniestralityDescription()[1] = editDef2012_education.getText().toString();
		}

		if (editImporte2011_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[2] = editImporte2011_education.getText().toString();
			pojo.getSiniestralityDescription()[2] = editDef2011_education.getText().toString();
		}

		if (editImporte2010_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[3] = editImporte2010_education.getText().toString();
			pojo.getSiniestralityDescription()[3] = editDef2010_education.getText().toString();
		}

		if (editImporte2009_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[4] = editImporte2009_education.getText().toString();
			pojo.getSiniestralityDescription()[4] = editDef2009_education.getText().toString();
		}

		if (editImporte2008_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[5] = editImporte2008_education.getText().toString();
			pojo.getSiniestralityDescription()[5] = editDef2008_education.getText().toString();
		}

		if (editImporte2007_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[6] = editImporte2007_education.getText().toString();
			pojo.getSiniestralityDescription()[6] = editDef2007_education.getText().toString();
		}

		if (editImporte2006_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[7] = editImporte2006_education.getText().toString();
			pojo.getSiniestralityDescription()[7] = editDef2006_education.getText().toString();
		}

		if (editImporte2005_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[8] = editImporte2005_education.getText().toString();
			pojo.getSiniestralityDescription()[8] = editDef2005_education.getText().toString();
		}

		if (editImporte2004_education.getText().length() > 0) {
			pojo.getSiniestralityValue()[9] = editImporte2004_education.getText().toString();
			pojo.getSiniestralityDescription()[9] = editDef2004_education.getText().toString();
		}

	}

	private void MuestraPosicion(Location loc) {
	    if(loc != null)
	    {
	        editLatitud_education.setText(String.valueOf(loc.getLatitude()));
	        editLongitud_education.setText(String.valueOf(loc.getLongitude()));
	        editAltitud_education.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatitud_education.setText("(sin_datos)");
	    	editLongitud_education.setText("(sin_datos)");
	    	editAltitud_education.setText("(sin_datos)");
	    }
	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_education.setText(colonia);
		editMunicipio_education.setText(municipio);
		editEstado_education.setText(estado);
		editCp_education.setText(CP);
		postalCodeID = p_postalCodeID;
		
		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

}
