package com.mclr.mini.riesgosmovil.fragmentos;

import java.math.BigDecimal;
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
import com.mclr.mini.riesgosmovil.modelos.pojoHidraulic;
import com.mclr.mini.riesgosmovil.utilitarios.AplicaDateChangedListener;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

public class HydraulicFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID; // Este se inicializara en la selección del CP

	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonCP_hydarulic;
	ImageButton buttonUbicacion_hydraulic;
	ImageButton buttonSaveUp1_2_hydraulic;
	ImageButton buttonOutUp1_2_hydraulic;
	ImageButton buttonSaveBot1_2_hydraulic;
	ImageButton buttonOutBot1_2_hydraulic;
	
	RadioButton radioRoca_hydraulic;
	RadioButton radioFirme_hydraulic;
	RadioButton radioBlando_hydraulic;
	RadioButton radioMar_hydraulic;
	RadioButton radioLago_hydraulic;
	RadioButton radioLaguna_hydraulic;
	RadioButton radioRio_hydraulic;
	RadioButton radioTipoCapta_hydraulic;
	RadioButton radioPozos_hydraulic;
	RadioButton radioBocatomas_hydraulic;
	RadioButton radioGalFil_hydraulic;
	RadioButton radioPrimarias_hydraulic;
	RadioButton radioSecundarias_hydraulic;
	RadioButton radioTerciarias_hydraulic;
	RadioButton radioCanalRiegoCon_hydraulic;
	RadioButton radioCanalRiegoO_hydraulic;
	RadioButton radioUni_hydraulic;
	RadioButton radioSepa_hydraulic;
	RadioButton radioEntro_hydraulic;
	RadioButton radioArqueArra_hydraulic;
	RadioButton radioAlba_hydraulic;

	Spinner spinnerTipoSuelo_hydraulic;
	List<CatalogItem> listSpinnerTipoSuelo;
	Spinner spinnerRedesPotable_hydraulic;
	List<CatalogItem> listSpinnerRedesPotable;
	Spinner spinnerRedesDesague_hydraulic;
	List<CatalogItem> listSpinnerRedesDesague;
	Spinner spinnerTipoEstruc_hydraulic;
	List<CatalogItem> listSpinnerTipoEstruc;
	Spinner spinnerMuros_hydraulic;
	List<CatalogItem> listSpinnerMuros;
	Spinner spinnerTipoCimen_hydraulic;
	List<CatalogItem> listSpinnerTipoCimen;
	Spinner spinnerPlantaAguaPotable_hydraulic;
	List<CatalogItem> listSpinnerPlantaAguaPotable;
	Spinner spinnerAlmaAguaTrata_hydraulic;
	List<CatalogItem> listSpinnerAlmaAguaTrata;
	Spinner spinnerCanalesCons_hydraulic;
	List<CatalogItem> listSpinnerCanalesCons;
	Spinner spinnerRepresas_hydraulic;
	List<CatalogItem> listSpinnerRepresas;
	Spinner spinnerEstaBombeo_hydraulic;
	List<CatalogItem> listSpinnerEstaBombeo;
	Spinner spinnerEsclusas_hydraulic;
	List<CatalogItem> listSpinnerEsclusas;
	Spinner spinnerCompHidra_hydraulic;
	List<CatalogItem> listSpinnerCompHidra;
	Spinner spinnerCompNoHidra_hydraulic;
	List<CatalogItem> listSpinnerCompNoHidra;
	Spinner spinnerMedEstru_hydraulic;
	List<CatalogItem> listSpinnerMedEstru;

	CheckBox checkboxInundacion_hydraulic;
	CheckBox checkboxHuracan_hydraulic;
	CheckBox checkboxTerremoto_hydraulic;
	CheckBox checkboxDeslave_hydraulic;
	CheckBox checkboxDerrumbe_hydraulic;

	EditText editNombre_hydraulic;
	EditText editCp_hydraulic;
	EditText editCalle_hydraulic;
	EditText editColonia_hydraulic;
	EditText editMunicipio_hydraulic;
	EditText editEstado_hydraulic;
	EditText editLongitud_hydraulic;
	EditText editLatitud_hydraulic;
	EditText editAltitud_hydraulic;
	EditText editAlca_hydraulic;
	EditText editColectores_hydraulic;
	EditText editInterceptores_hydraulic;
	EditText editEstaDepu_hydraulic;
	EditText editCuneRigo_hydraulic;
	EditText editTraga_hydraulic;
	EditText editVerte_hydraulic;
	EditText editPozosInspe_hydraulic;
	EditText editEstaBombeo_hydraulic;
	EditText editLinImpul_hydraulic;
	EditText editDepTan_hydraulic;
	EditText editImporte2013_hydraulic;
	EditText editDef2013_hydraulic;
	EditText editImporte2012_hydraulic;
	EditText editDef2012_hydraulic;
	EditText editImporte2011_hydraulic;
	EditText editDef2011_hydraulic;
	EditText editImporte2010_hydraulic;
	EditText editDef2010_hydraulic;
	EditText editImporte2009_hydraulic;
	EditText editDef2009_hydraulic;
	EditText editImporte2008_hydraulic;
	EditText editDef2008_hydraulic;
	EditText editImporte2007_hydraulic;
	EditText editDef2007_hydraulic;
	EditText editImporte2006_hydraulic;
	EditText editDef2006_hydraulic;
	EditText editImporte2005_hydraulic;
	EditText editDef2005_hydraulic;
	EditText editImporte2004_hydraulic;
	EditText editDef2004_hydraulic;
	
	DatePicker pickerFechaConst_hydraulic;
	EditText editFechaCons_hydraulic;

	Switch switchImporte2013_hydraulic;
	Switch switchImporte2012_hydraulic;
	Switch switchImporte2011_hydraulic;
	Switch switchImporte2010_hydraulic;
	Switch switchImporte2009_hydraulic;
	Switch switchImporte2008_hydraulic;
	Switch switchImporte2007_hydraulic;
	Switch switchImporte2006_hydraulic;
	Switch switchImporte2005_hydraulic;
	Switch switchImporte2004_hydraulic;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_hydraulic);

		buttonUbicacion_hydraulic = (ImageButton) myActivity.findViewById(R.id.buttonUbicacion_hydraulic);
		buttonUbicacion_hydraulic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            muestraPosicion(location);
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

		buttonSaveUp1_2_hydraulic = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_hydraulic);
		buttonSaveUp1_2_hydraulic.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_hydraulic = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_hydraulic);
		buttonOutUp1_2_hydraulic.setOnClickListener(new OutClickListener());
		
		buttonSaveBot1_2_hydraulic = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_hydraulic);
		buttonSaveBot1_2_hydraulic.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_hydraulic = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_hydraulic);
		buttonOutBot1_2_hydraulic.setOnClickListener(new OutClickListener());

		buttonCP_hydarulic = (ImageButton) myActivity.findViewById(R.id.buttonCP_hydarulic);
		buttonCP_hydarulic.setOnClickListener(new SearchClickListener(this));

		pickerFechaConst_hydraulic = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_hydraulic);

        spinnerTipoSuelo_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerTipoSuelo_hydraulic);
        listSpinnerTipoSuelo = db.getCatalogList(Constants.SOIL_TYPE);
        spinnerTipoSuelo_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoSuelo));

		spinnerRedesPotable_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerRedesPotable_hydraulic);
		listSpinnerRedesPotable = db.getCatalogList(Constants.DRINKING_WATER_NETWARE);
		spinnerRedesPotable_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerRedesPotable));

		spinnerRedesDesague_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerRedesDesague_hydraulic);
		listSpinnerRedesDesague = db.getCatalogList(Constants.DRAIN_NETWARE);
		spinnerRedesDesague_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerRedesDesague));

		radioMar_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioMar_hydraulic);
		radioLago_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioLago_hydraulic);
		radioLaguna_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioLaguna_hydraulic);
		radioRio_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioRio_hydraulic);

		checkboxInundacion_hydraulic = (CheckBox)myActivity.findViewById(R.id.checkboxInundacion_hydraulic);
		checkboxHuracan_hydraulic = (CheckBox)myActivity.findViewById(R.id.checkboxHuracan_hydraulic);
		checkboxTerremoto_hydraulic = (CheckBox)myActivity.findViewById(R.id.checkboxTerremoto_hydraulic);
		checkboxDeslave_hydraulic = (CheckBox)myActivity.findViewById(R.id.checkboxDeslave_hydraulic);
		checkboxDerrumbe_hydraulic = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbe_hydraulic);

		editCp_hydraulic = (EditText)myActivity.findViewById(R.id.editCp_hydraulic);
		editCalle_hydraulic = (EditText)myActivity.findViewById(R.id.editCalle_hydraulic);
		editColonia_hydraulic = (EditText)myActivity.findViewById(R.id.editColonia_hydraulic);
		editMunicipio_hydraulic = (EditText)myActivity.findViewById(R.id.editMunicipio_hydraulic);
		editEstado_hydraulic = (EditText)myActivity.findViewById(R.id.editEstado_hydraulic);
		editLongitud_hydraulic = (EditText)myActivity.findViewById(R.id.editLongitud_hydraulic);
		editLatitud_hydraulic = (EditText)myActivity.findViewById(R.id.editLatitud_hydraulic);
		editAltitud_hydraulic = (EditText)myActivity.findViewById(R.id.editAltitud_hydraulic);

		spinnerTipoEstruc_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerTipoEstruc_hydraulic);
		listSpinnerTipoEstruc = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipoEstruc_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstruc));

		spinnerMuros_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerMuros_hydraulic);
		listSpinnerMuros = db.getCatalogList(Constants.WALL_TYPE);
		spinnerMuros_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMuros));

		spinnerTipoCimen_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerTipoCimen_hydraulic);
		listSpinnerTipoCimen = db.getCatalogList(Constants.FOUNDATION_TYPE);
		spinnerTipoCimen_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCimen));

		radioPozos_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioPozos_hydraulic);
		radioBocatomas_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioBocatomas_hydraulic);
		radioGalFil_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioGalFil_hydraulic);

		spinnerPlantaAguaPotable_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerPlantaAguaPotable_hydraulic);
		listSpinnerPlantaAguaPotable = db.getCatalogList(Constants.DRINKING_WATER_NETWARE);
		spinnerPlantaAguaPotable_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerPlantaAguaPotable));

		spinnerAlmaAguaTrata_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerAlmaAguaTrata_hydraulic);
		listSpinnerAlmaAguaTrata = db.getCatalogList(Constants.TREATED_WATER_STORAGE_TYPE);
		spinnerAlmaAguaTrata_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerAlmaAguaTrata));

		radioPrimarias_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioPrimarias_hydraulic);
		radioSecundarias_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioSecundarias_hydraulic);
		radioTerciarias_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioTerciarias_hydraulic);

		spinnerCanalesCons_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerCanalesCons_hydraulic);
		listSpinnerCanalesCons = db.getCatalogList(Constants.CHANNEL_TYPE);
		spinnerCanalesCons_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerCanalesCons));

		radioCanalRiegoCon_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioCanalRiegoCon_hydraulic);
		radioCanalRiegoO_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioCanalRiegoO_hydraulic);

		spinnerRepresas_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerRepresas_hydraulic);
		listSpinnerRepresas = db.getCatalogList(Constants.DAM_TYPE);
		spinnerRepresas_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerRepresas));

		spinnerEstaBombeo_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerEstaBombeo_hydraulic);
		listSpinnerEstaBombeo = db.getCatalogList(Constants.PUMPING_STATIONS);
		spinnerEstaBombeo_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerEstaBombeo));

		spinnerEsclusas_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerEsclusas_hydraulic);
		listSpinnerEsclusas = db.getCatalogList(Constants.GATE_TYPES);
		spinnerEsclusas_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerEsclusas));

		spinnerCompHidra_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerCompHidra_hydraulic);
		listSpinnerCompHidra = db.getCatalogList(Constants.HIDRAULIC_COMPONENTS);
		spinnerCompHidra_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerCompHidra));

		spinnerCompNoHidra_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerCompNoHidra_hydraulic);
		listSpinnerCompNoHidra = db.getCatalogList(Constants.NON_HIDRAULIC_COMPONENTS);
		spinnerCompNoHidra_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerCompNoHidra));

		editAlca_hydraulic = (EditText)myActivity.findViewById(R.id.editAlca_hydraulic);
		editColectores_hydraulic = (EditText)myActivity.findViewById(R.id.editColectores_hydraulic);
		editInterceptores_hydraulic = (EditText)myActivity.findViewById(R.id.editInterceptores_hydraulic);
		editEstaDepu_hydraulic = (EditText)myActivity.findViewById(R.id.editEstaDepu_hydraulic);
		editCuneRigo_hydraulic = (EditText)myActivity.findViewById(R.id.editCuneRigo_hydraulic);
		editTraga_hydraulic = (EditText)myActivity.findViewById(R.id.editTraga_hydraulic);
		editVerte_hydraulic = (EditText)myActivity.findViewById(R.id.editVerte_hydraulic);
		editPozosInspe_hydraulic = (EditText)myActivity.findViewById(R.id.editPozosInspe_hydraulic);
		editEstaBombeo_hydraulic = (EditText)myActivity.findViewById(R.id.editEstaBombeo_hydraulic);
		editLinImpul_hydraulic = (EditText)myActivity.findViewById(R.id.editLinImpul_hydraulic);
		editDepTan_hydraulic = (EditText)myActivity.findViewById(R.id.editDepTan_hydraulic);

		radioUni_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioUni_hydraulic);
		radioSepa_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioSepa_hydraulic);

		radioEntro_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioEntro_hydraulic);
		radioArqueArra_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioArqueArra_hydraulic);
		radioAlba_hydraulic = (RadioButton)myActivity.findViewById(R.id.radioAlba_hydraulic);

		spinnerMedEstru_hydraulic = (Spinner)myActivity.findViewById(R.id.spinnerMedEstru_hydraulic);
		listSpinnerMedEstru = db.getCatalogList(Constants.STRUCTURAL_MEDIA_TYPE);
		spinnerMedEstru_hydraulic.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMedEstru));

		editImporte2013_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2013_hydraulic);
		editDef2013_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2013_hydraulic);
		editImporte2012_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2012_hydraulic);
		editDef2012_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2012_hydraulic);
		editImporte2011_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2011_hydraulic);
		editDef2011_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2011_hydraulic);
		editImporte2010_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2010_hydraulic);
		editDef2010_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2010_hydraulic);
		editImporte2009_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2009_hydraulic);
		editDef2009_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2009_hydraulic);
		editImporte2008_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2008_hydraulic);
		editDef2008_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2008_hydraulic);
		editImporte2007_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2007_hydraulic);
		editDef2007_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2007_hydraulic);
		editImporte2006_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2006_hydraulic);
		editDef2006_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2006_hydraulic);
		editImporte2005_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2005_hydraulic);
		editDef2005_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2005_hydraulic);
		editImporte2004_hydraulic = (EditText) myActivity.findViewById(R.id.editImporte2004_hydraulic);
		editDef2004_hydraulic = (EditText) myActivity.findViewById(R.id.editDef2004_hydraulic);
		
		editFechaCons_hydraulic = (EditText) myActivity.findViewById(R.id.editFechaCons_hydraulic);
		pickerFechaConst_hydraulic  = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_hydraulic);
		pickerFechaConst_hydraulic.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaCons_hydraulic));
	
		switchImporte2013_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2013_hydraulic);
		switchImporte2012_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2012_hydraulic);
		switchImporte2011_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2011_hydraulic);
		switchImporte2010_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2010_hydraulic);
		switchImporte2009_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2009_hydraulic);
		switchImporte2008_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2008_hydraulic);
		switchImporte2007_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2007_hydraulic);
		switchImporte2006_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2006_hydraulic);
		switchImporte2005_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2005_hydraulic);
		switchImporte2004_hydraulic = (Switch)myActivity.findViewById(R.id.switchImporte2004_hydraulic);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoHidraulic pojo = propierty.getHidraulicPptyDetail(propertyID, postalCodeID);

			try
			{
				pickerFechaConst_hydraulic.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getBuildingDate())), 
						Integer.valueOf(DateOperations.getMonthOfDate(pojo.getBuildingDate()))-1,
						Integer.valueOf(DateOperations.getDayOfDate(pojo.getBuildingDate())), new AplicaDateChangedListener(editFechaCons_hydraulic));
			}
			catch(Exception ex)
			{
			}

			spinnerTipoSuelo_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoSuelo, pojo.getSoilType()));
			spinnerRedesPotable_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerRedesPotable, pojo.getDrinkingWaterNet()));
			spinnerRedesDesague_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerRedesDesague, pojo.getDrainNet()));
			if (pojo.getClosestWaterBody().compareTo(Constants.Mar) == 0) {
				radioMar_hydraulic.setChecked(true);
			} else if (pojo.getClosestWaterBody().compareTo(Constants.Lago) == 0) {
				radioLago_hydraulic.setChecked(true);
			} else if (pojo.getClosestWaterBody().compareTo(Constants.Laguna) == 0) {
				radioLaguna_hydraulic.setChecked(true);
			} else if (pojo.getClosestWaterBody().compareTo(Constants.Rio) == 0) {
				radioRio_hydraulic.setChecked(true);
			}
			if (pojo.isFloodFormer()) {
				checkboxInundacion_hydraulic.setChecked(true);
			}
			if (pojo.isHurricaneFormer()) {
				checkboxHuracan_hydraulic.setChecked(true);
			}
			if (pojo.isEarthQuackeFormer()) {
				checkboxTerremoto_hydraulic.setChecked(true);
			}
			if (pojo.isLandSlideFormer()) {
				checkboxDeslave_hydraulic.setChecked(true);
			}
			if (pojo.isCollapseFormer()) {
				checkboxDerrumbe_hydraulic.setChecked(true);
			}
			editCp_hydraulic.setText(pojo.getPostalCode());
			editCalle_hydraulic.setText(pojo.getAddress());
			editColonia_hydraulic.setText(pojo.getColony());
			editMunicipio_hydraulic.setText(pojo.getTown());
			editEstado_hydraulic.setText(pojo.getStateName());
			editLongitud_hydraulic.setText(String.valueOf(pojo.getLongitude()));
			editLatitud_hydraulic.setText(String.valueOf(pojo.getLatidude()));
			editAltitud_hydraulic.setText(String.valueOf(pojo.getAltitude()));

			spinnerTipoEstruc_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstruc, pojo.getSturcturaltype()));
			spinnerMuros_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerMuros, pojo.getWallsType()));
			spinnerTipoCimen_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCimen, pojo.getFoundationType()));

			if (pojo.getCatchmentType().compareTo(Constants.Tipo_Captacion_Pozos) == 0) {
				radioPozos_hydraulic.setChecked(true);
			} else if (pojo.getCatchmentType().compareTo(Constants.Tipo_Captacion_Bocatomas) == 0) {
				radioBocatomas_hydraulic.setChecked(true);
			} else if (pojo.getCatchmentType().compareTo(Constants.Tipo_Captacion_Galerias_filtrantes) == 0) {
				radioGalFil_hydraulic.setChecked(true);
			}
			
			spinnerPlantaAguaPotable_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerRedesPotable, pojo.getDrinkingWaterNet()));
			spinnerAlmaAguaTrata_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerRedesPotable, pojo.getTreatedWaterStorageType()));

			if (pojo.getPipesType().compareTo(Constants.Tipo_Tuberias_Primarias) == 0) {
				radioPrimarias_hydraulic.setChecked(true);
			} else if (pojo.getPipesType().compareTo(Constants.Tipo_Tuberias_Secundarias) == 0) {
				radioSecundarias_hydraulic.setChecked(true);
			} else if (pojo.getPipesType().compareTo(Constants.Tipo_Tuberias_Terciarias) == 0) {
				radioTerciarias_hydraulic.setChecked(true);
			}

			spinnerCanalesCons_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerCanalesCons, pojo.getChannelsType()));

			if (pojo.getCrossType().compareTo(Constants.Cruces_Canal_Puente_canal) == 0) {
				radioCanalRiegoCon_hydraulic.setChecked(true);
			} else if (pojo.getCrossType().compareTo(Constants.Cruces_Canal_Caminos_rurales) == 0) {
				radioCanalRiegoO_hydraulic .setChecked(true);
			}

			spinnerRepresas_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerRepresas, pojo.getDamType()));
			spinnerEstaBombeo_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerEstaBombeo, pojo.getPumpingStation()));
			spinnerEsclusas_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerEsclusas, pojo.getGatesTypes()));
			spinnerCompHidra_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerCompHidra, pojo.getHidraulicComponents()));
			spinnerCompNoHidra_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerCompNoHidra, pojo.getNonHidraulicComponents()));

			editAlca_hydraulic.setText(String.valueOf(pojo.getSewers()));
			editColectores_hydraulic.setText(String.valueOf(pojo.getColectors()));
			editInterceptores_hydraulic.setText(String.valueOf(pojo.getInterceptors()));
			editEstaDepu_hydraulic.setText(String.valueOf(pojo.getWasteWaterTreamtentPlants()));
			editCuneRigo_hydraulic.setText(String.valueOf(pojo.getGuttersTrenchCaceres()));
			editTraga_hydraulic.setText(String.valueOf(pojo.getHoppers()));
			editVerte_hydraulic.setText(String.valueOf(pojo.getSpouts()));
			editPozosInspe_hydraulic.setText(String.valueOf(pojo.getManholes()));
			editEstaBombeo_hydraulic.setText(String.valueOf(pojo.getSewersPumpingStation()));
			editLinImpul_hydraulic.setText(String.valueOf(pojo.getDriveLines()));
			editDepTan_hydraulic.setText(String.valueOf(pojo.getSewerContainer()));

			spinnerMedEstru_hydraulic.setSelection(ListOperations.getItemPositionByID(listSpinnerMedEstru, pojo.getStructuralMedia()));

			if (pojo.getSewerNet().compareTo(Constants.Red_Unitaria) == 0) {
				radioUni_hydraulic.setChecked(true);
			} else if (pojo.getSewerNet().compareTo(Constants.Red_Separativa) == 0) {
				radioSepa_hydraulic .setChecked(true);
			}

			if (pojo.getIntakeType().compareTo(Constants.Acometidas_Entronques) == 0) {
				radioEntro_hydraulic.setChecked(true);
			} else if (pojo.getIntakeType().compareTo(Constants.Acometidas_Arquetas_de_arranque) == 0) {
				radioArqueArra_hydraulic .setChecked(true);
			} else if (pojo.getIntakeType().compareTo(Constants.Acometidas_Albañales) == 0) {
				radioAlba_hydraulic .setChecked(true);
			}

			editImporte2013_hydraulic.setText(pojo.getSiniestralityValues()[0].toString());
			editDef2013_hydraulic.setText(pojo.getSiniestralityDesccriptions()[0].toString());

			editImporte2012_hydraulic.setText(pojo.getSiniestralityValues()[1].toString());
			editDef2012_hydraulic.setText(pojo.getSiniestralityDesccriptions()[1].toString());

			editImporte2011_hydraulic.setText(pojo.getSiniestralityValues()[2].toString());
			editDef2011_hydraulic.setText(pojo.getSiniestralityDesccriptions()[2].toString());

			editImporte2010_hydraulic.setText(pojo.getSiniestralityValues()[3].toString());
			editDef2010_hydraulic.setText(pojo.getSiniestralityDesccriptions()[3].toString());

			editImporte2009_hydraulic.setText(pojo.getSiniestralityValues()[4].toString());
			editDef2009_hydraulic.setText(pojo.getSiniestralityDesccriptions()[4].toString());

			editImporte2008_hydraulic.setText(pojo.getSiniestralityValues()[5].toString());
			editDef2008_hydraulic.setText(pojo.getSiniestralityDesccriptions()[5].toString());

			editImporte2007_hydraulic.setText(pojo.getSiniestralityValues()[6].toString());
			editDef2007_hydraulic.setText(pojo.getSiniestralityDesccriptions()[6].toString());

			editImporte2006_hydraulic.setText(pojo.getSiniestralityValues()[7].toString());
			editDef2006_hydraulic.setText(pojo.getSiniestralityDesccriptions()[7].toString());

			editImporte2005_hydraulic.setText(pojo.getSiniestralityValues()[8].toString());
			editDef2005_hydraulic.setText(pojo.getSiniestralityDesccriptions()[8].toString());

			editImporte2004_hydraulic.setText(pojo.getSiniestralityValues()[9].toString());
			editDef2004_hydraulic.setText(pojo.getSiniestralityDesccriptions()[9].toString());

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.hydraulic_infrastructure_blue);
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
		View rootView = inflater.inflate(R.layout.hydraulic, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	public void CerrarBien()
	{
		buttonCP_hydarulic.setEnabled(false);
		buttonUbicacion_hydraulic.setEnabled(false);
		buttonSaveUp1_2_hydraulic.setEnabled(false);
		//buttonOutUp1_2_hydraulic.setEnabled(false);
		buttonSaveBot1_2_hydraulic.setEnabled(false);
		//buttonOutBot1_2_hydraulic.setEnabled(false);

		radioRoca_hydraulic.setEnabled(false);
		radioFirme_hydraulic.setEnabled(false);
		radioBlando_hydraulic.setEnabled(false);
		radioMar_hydraulic.setEnabled(false);
		radioLago_hydraulic.setEnabled(false);
		radioLaguna_hydraulic.setEnabled(false);
		radioRio_hydraulic.setEnabled(false);
		radioTipoCapta_hydraulic.setEnabled(false);
		radioPozos_hydraulic.setEnabled(false);
		radioBocatomas_hydraulic.setEnabled(false);
		radioGalFil_hydraulic.setEnabled(false);
		radioPrimarias_hydraulic.setEnabled(false);
		radioSecundarias_hydraulic.setEnabled(false);
		radioTerciarias_hydraulic.setEnabled(false);
		radioCanalRiegoCon_hydraulic.setEnabled(false);
		radioCanalRiegoO_hydraulic.setEnabled(false);
		radioUni_hydraulic.setEnabled(false);
		radioSepa_hydraulic.setEnabled(false);
		radioEntro_hydraulic.setEnabled(false);
		radioArqueArra_hydraulic.setEnabled(false);
		radioAlba_hydraulic.setEnabled(false);

        spinnerTipoSuelo_hydraulic.setEnabled(false);
		spinnerRedesPotable_hydraulic.setEnabled(false);
		spinnerRedesDesague_hydraulic.setEnabled(false);
		spinnerTipoEstruc_hydraulic.setEnabled(false);
		spinnerMuros_hydraulic.setEnabled(false);
		spinnerTipoCimen_hydraulic.setEnabled(false);
		spinnerPlantaAguaPotable_hydraulic.setEnabled(false);
		spinnerAlmaAguaTrata_hydraulic.setEnabled(false);
		spinnerCanalesCons_hydraulic.setEnabled(false);
		spinnerRepresas_hydraulic.setEnabled(false);
		spinnerEstaBombeo_hydraulic.setEnabled(false);
		spinnerEsclusas_hydraulic.setEnabled(false);
		spinnerCompHidra_hydraulic.setEnabled(false);
		spinnerCompNoHidra_hydraulic.setEnabled(false);
		spinnerMedEstru_hydraulic.setEnabled(false);

		checkboxInundacion_hydraulic.setEnabled(false);
		checkboxHuracan_hydraulic.setEnabled(false);
		checkboxTerremoto_hydraulic.setEnabled(false);
		checkboxDeslave_hydraulic.setEnabled(false);
		checkboxDerrumbe_hydraulic.setEnabled(false);

		editNombre_hydraulic.setEnabled(false);
		editCp_hydraulic.setEnabled(false);
		editCalle_hydraulic.setEnabled(false);
		editColonia_hydraulic.setEnabled(false);
		editMunicipio_hydraulic.setEnabled(false);
		editEstado_hydraulic.setEnabled(false);
		editLongitud_hydraulic.setEnabled(false);
		editLatitud_hydraulic.setEnabled(false);
		editAltitud_hydraulic.setEnabled(false);
		editAlca_hydraulic.setEnabled(false);
		editColectores_hydraulic.setEnabled(false);
		editInterceptores_hydraulic.setEnabled(false);
		editEstaDepu_hydraulic.setEnabled(false);
		editCuneRigo_hydraulic.setEnabled(false);
		editTraga_hydraulic.setEnabled(false);
		editVerte_hydraulic.setEnabled(false);
		editPozosInspe_hydraulic.setEnabled(false);
		editEstaBombeo_hydraulic.setEnabled(false);
		editLinImpul_hydraulic.setEnabled(false);
		editDepTan_hydraulic.setEnabled(false);
		editImporte2013_hydraulic.setEnabled(false);
		editDef2013_hydraulic.setEnabled(false);
		editImporte2012_hydraulic.setEnabled(false);
		editDef2012_hydraulic.setEnabled(false);
		editImporte2011_hydraulic.setEnabled(false);
		editDef2011_hydraulic.setEnabled(false);
		editImporte2010_hydraulic.setEnabled(false);
		editDef2010_hydraulic.setEnabled(false);
		editImporte2009_hydraulic.setEnabled(false);
		editDef2009_hydraulic.setEnabled(false);
		editImporte2008_hydraulic.setEnabled(false);
		editDef2008_hydraulic.setEnabled(false);
		editImporte2007_hydraulic.setEnabled(false);
		editDef2007_hydraulic.setEnabled(false);
		editImporte2006_hydraulic.setEnabled(false);
		editDef2006_hydraulic.setEnabled(false);
		editImporte2005_hydraulic.setEnabled(false);
		editDef2005_hydraulic.setEnabled(false);
		editImporte2004_hydraulic.setEnabled(false);
		editDef2004_hydraulic.setEnabled(false);

		pickerFechaConst_hydraulic.setEnabled(false);
		editFechaCons_hydraulic.setEnabled(false);

		//buttonCP_hydarulic.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacion_hydraulic.setImageResource(R.mipmap.guardar_blocked);
		buttonSaveUp1_2_hydraulic.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_hydraulic.setImageResource(R.mipmap.guardar_blocked);
		buttonSaveBot1_2_hydraulic.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_hydraulic.setImageResource(R.mipmap.guardar_blocked);
	}

	private class SaveClickListener implements OnClickListener {
		pojoHidraulic pojoHydr;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoHydr = new pojoHidraulic();

			if (validaCampos()) {
				pojoHydr.setPropertyID(myActivity.propertyID);
				SetPojoHydraulicP1(pojoHydr);
				HydraulicFragment2 hidraulico2 = (HydraulicFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				hidraulico2.SetPojoHydraulicP2(pojoHydr);

				try
				{
					process.processHydraulicSave(pojoHydr);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoHydr.getPropertyID();
					myActivity.postalCodeID = pojoHydr.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	private void muestraPosicion(Location loc) {
	    if(loc != null)
	    {
	        editLatitud_hydraulic.setText(String.valueOf(loc.getLatitude()));
	        editLongitud_hydraulic.setText(String.valueOf(loc.getLongitude()));
	        editAltitud_hydraulic.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatitud_hydraulic.setText("(sin_datos)");
	    	editLongitud_hydraulic.setText("(sin_datos)");
	    	editAltitud_hydraulic.setText("(sin_datos)");
	    }
	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_hydraulic.setText(colonia);
		editMunicipio_hydraulic.setText(municipio);
		editEstado_hydraulic.setText(estado);
		editCp_hydraulic.setText(CP);
		postalCodeID = p_postalCodeID;
	
		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

	public boolean validaCampos(){
		if (editNombre_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el nombre del bien", Toast.LENGTH_LONG).show();
			editNombre_hydraulic.requestFocus();
			return false;
		}
		if (editFechaCons_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la fecha de construcción del bien", Toast.LENGTH_LONG).show();
			editFechaCons_hydraulic.requestFocus();
			return false;
		}
		if(spinnerTipoSuelo_hydraulic.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de suelo", Toast.LENGTH_LONG).show();
			spinnerTipoSuelo_hydraulic.requestFocus();
			return false;
		}
		if(spinnerRedesPotable_hydraulic.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar Red de agua potable", Toast.LENGTH_LONG).show();
			spinnerRedesPotable_hydraulic.requestFocus();
			return false;
		}
		if(spinnerRedesDesague_hydraulic.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar Red de desagüe", Toast.LENGTH_LONG).show();
			spinnerRedesDesague_hydraulic.requestFocus();
			return false;
		}
		if (editCp_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_hydraulic.requestFocus();
			return false;
		}
		if (editCalle_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la calle correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCalle_hydraulic.requestFocus();
			return false;
		}
		if (editColonia_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_hydraulic.requestFocus();
			return false;
		}
		if (editMunicipio_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_hydraulic.requestFocus();
			return false;
		}
		if (editEstado_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado_hydraulic.requestFocus();
			return false;
		}
		if (editLongitud_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongitud_hydraulic.requestFocus();
			return false;
		}
		if (editLatitud_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatitud_hydraulic.requestFocus();
			return false;
		}
		if (editAltitud_hydraulic.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltitud_hydraulic.requestFocus();
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
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_hydraulic.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_hydraulic.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_hydraulic.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);

            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.HIDRAULIC);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

	public void SetPojoHydraulicP1(pojoHidraulic pojo)
	{
		pojo.setPostalCodeID(postalCodeID);
        pojo.setSoilType(listSpinnerTipoSuelo.get(spinnerTipoSuelo_hydraulic.getSelectedItemPosition()).getId());
		pojo.setDrinkingWaterNet(listSpinnerRedesPotable.get(spinnerRedesPotable_hydraulic.getSelectedItemPosition()).getId());
		pojo.setDrainNet(listSpinnerRedesDesague.get(spinnerRedesDesague_hydraulic.getSelectedItemPosition()).getId());

		pojo.setBuildingDate(editFechaCons_hydraulic.getText().toString());
		if (radioMar_hydraulic.isChecked())
			pojo.setClosestWaterBody(Constants.Mar);
		else if (radioLago_hydraulic.isChecked())
			pojo.setClosestWaterBody(Constants.Lago);
		else if (radioLaguna_hydraulic.isChecked())
			pojo.setClosestWaterBody(Constants.Laguna);
		else if (radioRio_hydraulic.isChecked())
			pojo.setClosestWaterBody(Constants.Rio);

		if (checkboxInundacion_hydraulic.isChecked()) {
			pojo.setFloodFormer(true);
		}
		if (checkboxHuracan_hydraulic.isChecked()) {
			pojo.setHurricaneFormer(true);
		}
		if (checkboxTerremoto_hydraulic.isChecked()) {
			pojo.setEarthQuackeFormer(true);
		}
		if (checkboxDeslave_hydraulic.isChecked()) {
			pojo.setLandSlideFormer(true);
		}
		if (checkboxDerrumbe_hydraulic.isChecked()) {
			pojo.setCollapseFormer(true);
		}

		pojo.setPostalCode(editCp_hydraulic.getText().toString());
		pojo.setAddress(editCalle_hydraulic.getText().toString());
		pojo.setColony(editColonia_hydraulic.getText().toString());
		pojo.setTown(editMunicipio_hydraulic.getText().toString());
		pojo.setStateName(editEstado_hydraulic.getText().toString());
		pojo.setLongitude(Double.valueOf(editLongitud_hydraulic.getText().toString()));
		pojo.setLatidude(Double.valueOf(editLatitud_hydraulic.getText().toString()));
		pojo.setAltitude(Double.valueOf(editAltitud_hydraulic.getText().toString()));

		pojo.setSturcturaltype(listSpinnerTipoEstruc.get(spinnerTipoEstruc_hydraulic.getSelectedItemPosition()).getId());
		pojo.setWallsType(listSpinnerMuros.get(spinnerMuros_hydraulic.getSelectedItemPosition()).getId());
		pojo.setFoundationType(listSpinnerTipoCimen.get(spinnerTipoCimen_hydraulic.getSelectedItemPosition()).getId());

		if (radioPozos_hydraulic.isChecked())
			pojo.setCatchmentType(Constants.Tipo_Captacion_Pozos);
		else if (radioBocatomas_hydraulic.isChecked())
			pojo.setCatchmentType(Constants.Tipo_Captacion_Bocatomas);
		else if (radioGalFil_hydraulic.isChecked())
			pojo.setCatchmentType(Constants.Tipo_Captacion_Galerias_filtrantes);

		pojo.setDrinkingWaterNet(listSpinnerRedesPotable.get(spinnerPlantaAguaPotable_hydraulic.getSelectedItemPosition()).getId());
		pojo.setTreatedWaterStorageType(listSpinnerRedesPotable.get(spinnerAlmaAguaTrata_hydraulic.getSelectedItemPosition()).getId());

		if (radioPrimarias_hydraulic.isChecked())
			pojo.setPipesType(Constants.Tipo_Tuberias_Primarias);
		else if (radioSecundarias_hydraulic.isChecked())
			pojo.setPipesType(Constants.Tipo_Tuberias_Secundarias);
		else if (radioTerciarias_hydraulic.isChecked())
			pojo.setPipesType(Constants.Tipo_Tuberias_Terciarias);

		pojo.setChannelsType(listSpinnerCanalesCons.get(spinnerCanalesCons_hydraulic.getSelectedItemPosition()).getId());

		if (radioCanalRiegoCon_hydraulic.isChecked())
			pojo.setCrossType(Constants.Cruces_Canal_Puente_canal);
		else if (radioCanalRiegoO_hydraulic.isChecked())
			pojo.setCrossType(Constants.Cruces_Canal_Caminos_rurales);

		pojo.setDamType(listSpinnerRepresas.get(spinnerRepresas_hydraulic.getSelectedItemPosition()).getId());
		pojo.setPumpingStation(listSpinnerEstaBombeo.get(spinnerEstaBombeo_hydraulic.getSelectedItemPosition()).getId());
		pojo.setGatesTypes(listSpinnerEsclusas.get(spinnerEsclusas_hydraulic.getSelectedItemPosition()).getId());
		pojo.setHidraulicComponents(listSpinnerCompHidra.get(spinnerCompHidra_hydraulic.getSelectedItemPosition()).getId());
		pojo.setNonHidraulicComponents(listSpinnerCompNoHidra.get(spinnerCompNoHidra_hydraulic.getSelectedItemPosition()).getId());

		pojo.setSewers(Double.valueOf(editAlca_hydraulic.getText().toString()));
		pojo.setColectors(Double.valueOf(editColectores_hydraulic.getText().toString()));
		pojo.setInterceptors(Double.valueOf(editInterceptores_hydraulic.getText().toString()));
		pojo.setWasteWaterTreamtentPlants(Double.valueOf(editEstaDepu_hydraulic.getText().toString()));
		pojo.setGuttersTrenchCaceres(Double.valueOf(editCuneRigo_hydraulic.getText().toString()));
		pojo.setHoppers(Double.valueOf(editTraga_hydraulic.getText().toString()));
		pojo.setSpouts(Double.valueOf(editVerte_hydraulic.getText().toString()));
		pojo.setManholes(Double.valueOf(editPozosInspe_hydraulic.getText().toString()));
		pojo.setSewersPumpingStation(Double.valueOf(editEstaBombeo_hydraulic.getText().toString()));
		pojo.setDriveLines(Double.valueOf(editLinImpul_hydraulic.getText().toString()));
		pojo.setSewerContainer(Double.valueOf(editDepTan_hydraulic.getText().toString()));

		pojo.setStructuralMedia(listSpinnerMedEstru.get(spinnerMedEstru_hydraulic.getSelectedItemPosition()).getId());

		if (radioUni_hydraulic.isChecked())
			pojo.setSewerNet(Constants.Red_Unitaria);
		else if (radioSepa_hydraulic.isChecked())
			pojo.setSewerNet(Constants.Red_Separativa);

		if (radioEntro_hydraulic.isChecked())
			pojo.setIntakeType(Constants.Acometidas_Entronques);
		else if (radioArqueArra_hydraulic.isChecked())
			pojo.setIntakeType(Constants.Acometidas_Arquetas_de_arranque);
		else if (radioAlba_hydraulic.isChecked())
			pojo.setIntakeType(Constants.Acometidas_Albañales);

		if (editImporte2013_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[0] = new BigDecimal(editImporte2013_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[0] = editDef2013_hydraulic.getText().toString();
		}

		if (editImporte2012_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[1] = new BigDecimal(editImporte2012_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[1] = editDef2012_hydraulic.getText().toString();
		}

		if (editImporte2011_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[2] = new BigDecimal(editImporte2011_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[2] = editDef2011_hydraulic.getText().toString();
		}

		if (editImporte2010_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[3] = new BigDecimal(editImporte2010_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[3] = editDef2010_hydraulic.getText().toString();
		}

		if (editImporte2009_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[4] = new BigDecimal(editImporte2009_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[4] = editDef2009_hydraulic.getText().toString();
		}

		if (editImporte2008_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[5] = new BigDecimal(editImporte2008_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[5] = editDef2008_hydraulic.getText().toString();
		}

		if (editImporte2007_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[6] = new BigDecimal(editImporte2007_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[6] = editDef2007_hydraulic.getText().toString();
		}

		if (editImporte2006_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[7] = new BigDecimal(editImporte2006_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[7] = editDef2006_hydraulic.getText().toString();
		}

		if (editImporte2005_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[8] = new BigDecimal(editImporte2005_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[8] = editDef2005_hydraulic.getText().toString();
		}

		if (editImporte2004_hydraulic.getText().length() > 0) {
			pojo.getSiniestralityValues()[9] = new BigDecimal(editImporte2004_hydraulic.getText().toString());
			pojo.getSiniestralityDesccriptions()[9] = editDef2004_hydraulic.getText().toString();
		}

	}

}
