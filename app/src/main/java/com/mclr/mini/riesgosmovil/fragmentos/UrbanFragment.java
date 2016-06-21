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
import com.mclr.mini.riesgosmovil.modelos.pojoUrban;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

public class UrbanFragment extends Fragment {
	private MainActivity myActivity = null;
	
	public static final String ARG_URBAN_NUMBER = "urban_number";
	public static final String ARG_URBAN_POSTAL_CODE = "urban_postal_code";
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID;
	// Elementos graficos
	String searchPostalCode;

	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonSaveUp1_2_urban;
	ImageButton buttonOutUp1_2_urban;
	ImageButton buttonSaveBot1_2_urban;
	ImageButton buttonOutBot1_2_urban;
	ImageButton buttonCP_urban;
	ImageButton buttonUbicacionIni_urban;
	ImageButton buttonUbicacionFin_urban;

	EditText editNombre_urban;
	EditText editCp_urban;
	EditText editCalle_urban;
	EditText editColonia_urban;
	EditText editMunicipio_urban;
	EditText editEstado2_urban;
	EditText editLongIni_urban;
	EditText editLongFin_urban;
	EditText editLatIni_urban;
	EditText editLatFin_urban;
	EditText editAltIni_urban;
	EditText editAltFin_urban;
	EditText editImporte2013_urban;
	EditText editDef2013_urban;
	EditText editImporte2012_urban;
	EditText editDef2012_urban;
	EditText editImporte2011_urban;
	EditText editDef2011_urban;
	EditText editImporte2010_urban;
	EditText editDef2010_urban;
	EditText editImporte2009_urban;
	EditText editDef2009_urban;
	EditText editImporte2008_urban;
	EditText editDef2008_urban;
	EditText editImporte2007_urban;
	EditText editDef2007_urban;
	EditText editImporte2006_urban;
	EditText editDef2006_urban;
	EditText editImporte2005_urban;
	EditText editDef2005_urban;
	EditText editImporte2004_urban;
	EditText editDef2004_urban;

	RadioButton radioCalle_urban;
	RadioButton radioViaRap_urban;
	RadioButton radioPuente_urban;
	RadioButton radioTunel_urban;
	RadioButton radio1_urban;
	RadioButton radio2_urban;
	RadioButton radio3_urban;
	RadioButton radio4_urban;
	RadioButton radio5_urban;
	RadioButton radio6_urban;

	CheckBox checkboxInundacion_urban;
	CheckBox checkboxHuracan_urban;
	CheckBox checkboxTerremoto_urban;
	CheckBox checkboxDeslave_urban;
	CheckBox checkboxDerrumbe_urban;

	Spinner spinnerTipoEstru_urban;
	List<CatalogItem> listSpinnerTipoEstru;
	Spinner spinnerTipoCime_urban;
	List<CatalogItem> listSpinnerTipoCime;
	Spinner spinnerMuros_urban;
	List<CatalogItem> listSpinnerMuros;
	
	Switch switchImporte2013_urban;
	Switch switchImporte2004_urban;
	Switch switchImporte2005_urban;
	Switch switchImporte2006_urban;
	Switch switchImporte2007_urban;
	Switch switchImporte2008_urban;
	Switch switchImporte2009_urban;
	Switch switchImporte2010_urban;
	Switch switchImporte2011_urban;
	Switch switchImporte2012_urban;

	public UrbanFragment() {

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
		View rootView = inflater.inflate(R.layout.urban, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_urban);

		buttonUbicacionIni_urban = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionIni_urban);
		buttonUbicacionIni_urban.setOnClickListener(new OnClickListener() {
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
				    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
				}
			}
		});
		
		buttonUbicacionFin_urban = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionFin_urban);
		buttonUbicacionFin_urban.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            muestraPosicion1(location);
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
				    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, locationListener);
				}
			}
		});
		
		buttonSaveUp1_2_urban = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_urban);
		buttonSaveUp1_2_urban.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_urban = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_urban);
		buttonOutUp1_2_urban.setOnClickListener(new OutClickListener());
		
		buttonSaveBot1_2_urban = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_urban);
		buttonSaveBot1_2_urban.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_urban = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_urban);
		buttonOutBot1_2_urban.setOnClickListener(new OutClickListener());
		
		buttonCP_urban = (ImageButton) myActivity.findViewById(R.id.buttonCP_urban);
		buttonCP_urban.setOnClickListener(new SearchClickListener(this));
		
		editNombre_urban = (EditText) myActivity.findViewById(R.id.editNombre_urban);
		radioCalle_urban = (RadioButton) myActivity.findViewById(R.id.radioCalle_urban);
		radioViaRap_urban = (RadioButton) myActivity.findViewById(R.id.radioViaRap_urban);
		radioTunel_urban = (RadioButton) myActivity.findViewById(R.id.radioTunel_urban);
		radioPuente_urban = (RadioButton) myActivity.findViewById(R.id.radioPuente_urban);
		radio1_urban = (RadioButton) myActivity.findViewById(R.id.radio1_urban);
		radio2_urban = (RadioButton) myActivity.findViewById(R.id.radio2_urban);
		radio3_urban = (RadioButton) myActivity.findViewById(R.id.radio3_urban);
		radio4_urban = (RadioButton) myActivity.findViewById(R.id.radio4_urban);
		radio5_urban = (RadioButton) myActivity.findViewById(R.id.radio5_urban);
		radio6_urban = (RadioButton) myActivity.findViewById(R.id.radio6_urban);
		editMunicipio_urban = (EditText) myActivity.findViewById(R.id.editMunicipio_urban);
		editColonia_urban = (EditText) myActivity.findViewById(R.id.editColonia_urban);
		editCp_urban = (EditText) myActivity.findViewById(R.id.editCp_urban);
		editCalle_urban = (EditText) myActivity.findViewById(R.id.editCalle_urban);
		editEstado2_urban = (EditText) myActivity.findViewById(R.id.editEstado2_urban);
		editLongIni_urban = (EditText) myActivity.findViewById(R.id.editLongIni_urban);
		editLongFin_urban = (EditText) myActivity.findViewById(R.id.editLongFin_urban);
		editLatIni_urban = (EditText) myActivity.findViewById(R.id.editLatIni_urban);
		editLatFin_urban = (EditText) myActivity.findViewById(R.id.editLatFin_urban);
		editAltIni_urban = (EditText) myActivity.findViewById(R.id.editAltIni_urban);
		editAltFin_urban = (EditText) myActivity.findViewById(R.id.editAltFin_urban);
		checkboxInundacion_urban = (CheckBox) myActivity.findViewById(R.id.checkboxInundacion_urban);
		checkboxHuracan_urban = (CheckBox) myActivity.findViewById(R.id.checkboxHuracan_urban);
		checkboxTerremoto_urban = (CheckBox) myActivity.findViewById(R.id.checkboxTerremoto_urban);
		checkboxDeslave_urban = (CheckBox) myActivity.findViewById(R.id.checkboxDeslave_urban);
		checkboxDerrumbe_urban = (CheckBox) myActivity.findViewById(R.id.checkboxDerrumbe_urban);
		spinnerTipoEstru_urban = (Spinner) myActivity.findViewById(R.id.spinnerTipoEstru_urban);
		listSpinnerTipoEstru = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipoEstru_urban.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstru));
		spinnerTipoCime_urban = (Spinner) myActivity.findViewById(R.id.spinnerTipoCime_urban);
		listSpinnerTipoCime = db.getCatalogList(Constants.FOUNDATION_TYPE);
		spinnerTipoCime_urban.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCime));
		spinnerMuros_urban = (Spinner) myActivity.findViewById(R.id.spinnerMuros_urban);
		listSpinnerMuros = db.getCatalogList(Constants.WALL_TYPE);
		spinnerMuros_urban.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMuros));
		editImporte2013_urban = (EditText) myActivity.findViewById(R.id.editImporte2013_urban);
		switchImporte2013_urban = (Switch) myActivity.findViewById(R.id.switchImporte2013_urban);
		editDef2013_urban = (EditText) myActivity.findViewById(R.id.editDef2013_urban);
		editImporte2012_urban = (EditText) myActivity.findViewById(R.id.editImporte2012_urban);
		switchImporte2012_urban = (Switch) myActivity.findViewById(R.id.switchImporte2012_urban);
		editDef2012_urban = (EditText) myActivity.findViewById(R.id.editDef2012_urban);
		editImporte2011_urban = (EditText) myActivity.findViewById(R.id.editImporte2011_urban);
		switchImporte2011_urban = (Switch) myActivity.findViewById(R.id.switchImporte2011_urban);
		editDef2011_urban = (EditText) myActivity.findViewById(R.id.editDef2011_urban);
		editImporte2010_urban = (EditText) myActivity.findViewById(R.id.editImporte2010_urban);
		switchImporte2010_urban = (Switch) myActivity.findViewById(R.id.switchImporte2010_urban);
		editDef2010_urban = (EditText) myActivity.findViewById(R.id.editDef2010_urban);
		editImporte2009_urban = (EditText) myActivity.findViewById(R.id.editImporte2009_urban);
		switchImporte2009_urban = (Switch) myActivity.findViewById(R.id.switchImporte2009_urban);
		editDef2009_urban = (EditText) myActivity.findViewById(R.id.editDef2009_urban);
		editImporte2008_urban = (EditText) myActivity.findViewById(R.id.editImporte2008_urban);
		switchImporte2008_urban = (Switch) myActivity.findViewById(R.id.switchImporte2008_urban);
		editDef2008_urban = (EditText) myActivity.findViewById(R.id.editDef2008_urban);
		editImporte2007_urban = (EditText) myActivity.findViewById(R.id.editImporte2007_urban);
		switchImporte2007_urban = (Switch) myActivity.findViewById(R.id.switchImporte2007_urban);
		editDef2007_urban = (EditText) myActivity.findViewById(R.id.editDef2007_urban);
		editImporte2006_urban = (EditText) myActivity.findViewById(R.id.editImporte2006_urban);
		switchImporte2006_urban = (Switch) myActivity.findViewById(R.id.switchImporte2006_urban);
		editDef2006_urban = (EditText) myActivity.findViewById(R.id.editDef2006_urban);
		editImporte2005_urban = (EditText) myActivity.findViewById(R.id.editImporte2005_urban);
		switchImporte2005_urban = (Switch) myActivity.findViewById(R.id.switchImporte2005_urban);
		editDef2005_urban = (EditText) myActivity.findViewById(R.id.editDef2005_urban);
		editImporte2004_urban = (EditText) myActivity.findViewById(R.id.editImporte2004_urban);
		switchImporte2004_urban = (Switch) myActivity.findViewById(R.id.switchImporte2004_urban);
		editDef2004_urban = (EditText) myActivity.findViewById(R.id.editDef2004_urban);
		
		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)) {
			pojoUrban pojo = propierty.getUrbanPptyDetail(propertyID, postalCodeID);
			//Falta en el pojoUrban los radio de tipo de infraestructura y numero de carriles
			editNombre_urban.setText(pojo.getPropertyName());
			editMunicipio_urban.setText(pojo.getTown());
			editColonia_urban.setText(pojo.getColony());
			editCp_urban.setText(pojo.getPostalCode());
			editCalle_urban.setText(pojo.getAddress());
			editEstado2_urban.setText(pojo.getStateName());
			editLongIni_urban.setText(String.valueOf(pojo.getStartLongitude()));
			editLongFin_urban.setText(String.valueOf(pojo.getEndLongitude()));
			editLatIni_urban.setText(String.valueOf(pojo.getStartLatitude()));
			editLatFin_urban.setText(String.valueOf(pojo.getEndLatitude()));
			editAltIni_urban.setText(String.valueOf(pojo.getStartAltitude()));
			editAltFin_urban.setText(String.valueOf(pojo.getEndAltitude()));
			
			if (pojo.getInfrastructureType().compareTo(Constants.Tipo_Infraestructuta_Calle) == 0) {
				radioCalle_urban.setChecked(true);
			} else if (pojo.getInfrastructureType().compareTo(Constants.Tipo_Infraestructuta_Via_rapida) == 0) {
				radioViaRap_urban.setChecked(true);
			} else if (pojo.getInfrastructureType().compareTo(Constants.Tipo_Infraestructuta_Puente) == 0) {
				radioPuente_urban.setChecked(true);
			} else if (pojo.getInfrastructureType().compareTo(Constants.Tipo_Infraestructuta_Tunel) == 0) {
				radioTunel_urban.setChecked(true);
			}
			
			
			if(pojo.getLanesNumber()==1){
				radio1_urban.setChecked(true);
			}
			if(pojo.getLanesNumber()==2){
				radio2_urban.setChecked(true);
			}
			if(pojo.getLanesNumber()==3){
				radio3_urban.setChecked(true);
			}
			if(pojo.getLanesNumber()==4){
				radio4_urban.setChecked(true);
			}
			if(pojo.getLanesNumber()==5){
				radio5_urban.setChecked(true);
			}
			if(pojo.getLanesNumber()==6){
				radio6_urban.setChecked(true);
			}
			
			if (pojo.hasEarthQuakeRisk()) {
				//REVISAR!!!!!
				checkboxInundacion_urban.setChecked(true);
			}
			if (pojo.hasRockFallingRisk()) {
				//REVISAR!!!!!
				checkboxHuracan_urban.setChecked(true);
			}
			if (pojo.hasFleedRisk()) {
				//REVISAR!!!!!
				checkboxTerremoto_urban.setChecked(true);
			}
			if (pojo.hasHurricaneRisk()) {
				//REVISAR!!!!!
				checkboxDeslave_urban.setChecked(true);
			}
			if (pojo.hasLandSlidingRisk()) {
				//REVISAR!!!!!
				checkboxDerrumbe_urban.setChecked(true);
			}

			spinnerTipoEstru_urban.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstru, pojo.getStructuralType()));
			spinnerMuros_urban.setSelection(ListOperations.getItemPositionByID(listSpinnerMuros, pojo.getWallsType()));
			spinnerTipoCime_urban.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getFoundationType()));

			editImporte2013_urban.setText(pojo.getSiniestralityAmount()[0].toString());
			editDef2013_urban.setText(pojo.getSiniestralityDescription()[0].toString());

			editImporte2012_urban.setText(pojo.getSiniestralityAmount()[1].toString());
			editDef2012_urban.setText(pojo.getSiniestralityDescription()[1].toString());

			editImporte2011_urban.setText(pojo.getSiniestralityAmount()[2].toString());
			editDef2011_urban.setText(pojo.getSiniestralityDescription()[2].toString());

			editImporte2010_urban.setText(pojo.getSiniestralityAmount()[3].toString());
			editDef2010_urban.setText(pojo.getSiniestralityDescription()[3].toString());

			editImporte2009_urban.setText(pojo.getSiniestralityAmount()[4].toString());
			editDef2009_urban.setText(pojo.getSiniestralityDescription()[4].toString());

			editImporte2008_urban.setText(pojo.getSiniestralityAmount()[5].toString());
			editDef2008_urban.setText(pojo.getSiniestralityDescription()[5].toString());

			editImporte2007_urban.setText(pojo.getSiniestralityAmount()[6].toString());
			editDef2007_urban.setText(pojo.getSiniestralityDescription()[6].toString());

			editImporte2006_urban.setText(pojo.getSiniestralityAmount()[7].toString());
			editDef2006_urban.setText(pojo.getSiniestralityDescription()[7].toString());

			editImporte2005_urban.setText(pojo.getSiniestralityAmount()[8].toString());
			editDef2005_urban.setText(pojo.getSiniestralityDescription()[8].toString());

			editImporte2004_urban.setText(pojo.getSiniestralityAmount()[9].toString());
			editDef2004_urban.setText(pojo.getSiniestralityDescription()[9].toString());

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.urban_infraestructure_blue);

		}
	}

	public void CerrarBien()
	{
		buttonSaveUp1_2_urban.setEnabled(false);
		//buttonOutUp1_2_urban.setEnabled(false);
		buttonSaveBot1_2_urban.setEnabled(false);
		//buttonOutBot1_2_urban.setEnabled(false);
		buttonCP_urban.setEnabled(false);
		buttonUbicacionIni_urban.setEnabled(false);
		buttonUbicacionFin_urban.setEnabled(false);

		editNombre_urban.setEnabled(false);
		editCp_urban.setEnabled(false);
		editCalle_urban.setEnabled(false);
		editColonia_urban.setEnabled(false);
		editMunicipio_urban.setEnabled(false);
		editEstado2_urban.setEnabled(false);
		editLongIni_urban.setEnabled(false);
		editLongFin_urban.setEnabled(false);
		editLatIni_urban.setEnabled(false);
		editLatFin_urban.setEnabled(false);
		editAltIni_urban.setEnabled(false);
		editAltFin_urban.setEnabled(false);
		editImporte2013_urban.setEnabled(false);
		editDef2013_urban.setEnabled(false);
		editImporte2012_urban.setEnabled(false);
		editDef2012_urban.setEnabled(false);
		editImporte2011_urban.setEnabled(false);
		editDef2011_urban.setEnabled(false);
		editImporte2010_urban.setEnabled(false);
		editDef2010_urban.setEnabled(false);
		editImporte2009_urban.setEnabled(false);
		editDef2009_urban.setEnabled(false);
		editImporte2008_urban.setEnabled(false);
		editDef2008_urban.setEnabled(false);
		editImporte2007_urban.setEnabled(false);
		editDef2007_urban.setEnabled(false);
		editImporte2006_urban.setEnabled(false);
		editDef2006_urban.setEnabled(false);
		editImporte2005_urban.setEnabled(false);
		editDef2005_urban.setEnabled(false);
		editImporte2004_urban.setEnabled(false);
		editDef2004_urban.setEnabled(false);

		radioCalle_urban.setEnabled(false);
		radioViaRap_urban.setEnabled(false);
		radioPuente_urban.setEnabled(false);
		radioTunel_urban.setEnabled(false);
		radio1_urban.setEnabled(false);
		radio2_urban.setEnabled(false);
		radio3_urban.setEnabled(false);
		radio4_urban.setEnabled(false);
		radio5_urban.setEnabled(false);
		radio6_urban.setEnabled(false);

		checkboxInundacion_urban.setEnabled(false);
		checkboxHuracan_urban.setEnabled(false);
		checkboxTerremoto_urban.setEnabled(false);
		checkboxDeslave_urban.setEnabled(false);
		checkboxDerrumbe_urban.setEnabled(false);

		spinnerTipoEstru_urban.setEnabled(false);
		spinnerTipoCime_urban.setEnabled(false);
		spinnerMuros_urban.setEnabled(false);

		buttonSaveUp1_2_urban.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_urban.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot1_2_urban.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_urban.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_urban.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacionIni_urban.setImageResource(R.mipmap.ubicacion_inicial_blocked);
		buttonUbicacionFin_urban.setImageResource(R.mipmap.ubicacion_final_blocked);
	}

	private void muestraPosicion(Location loc) {
	    if(loc != null)
	    {
	    	editLatIni_urban.setText(String.valueOf(loc.getLatitude()));
	    	editLongIni_urban.setText(String.valueOf(loc.getLongitude()));
	    	editAltIni_urban.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatIni_urban.setText("(sin_datos)");
	    	editLongIni_urban.setText("(sin_datos)");
	    	editAltIni_urban.setText("(sin_datos)");
	    }
	}

	private void muestraPosicion1(Location loc) {
	    if(loc != null)
	    {
	    	editLatFin_urban.setText(String.valueOf(loc.getLatitude()));
	    	editLongFin_urban.setText(String.valueOf(loc.getLongitude()));
	    	editAltFin_urban.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatFin_urban.setText("(sin_datos)");
	    	editLongFin_urban.setText("(sin_datos)");
	    	editAltFin_urban.setText("(sin_datos)");
	    }
	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_urban.setText(colonia);
		editMunicipio_urban.setText(municipio);
		editEstado2_urban.setText(estado);
		editCp_urban.setText(CP);
		postalCodeID = p_postalCodeID;
		
		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

	public boolean validaCampos() {
		
		if (editNombre_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el nombre correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editNombre_urban.requestFocus();
			return false;
		}
		if(!radioCalle_urban.isChecked() && !radioPuente_urban.isChecked() && !radioViaRap_urban.isChecked() && !radioTunel_urban.isChecked())
		{
			Toast.makeText(myActivity, "Se debe indicar el tipo de infraestructura urbana municipal", Toast.LENGTH_LONG).show();
			radioCalle_urban.requestFocus();
			return false;
		}
		if(!radio1_urban.isChecked() && !radio2_urban.isChecked() && !radio3_urban.isChecked() && !radio4_urban.isChecked() && !radio5_urban.isChecked() && !radio6_urban.isChecked())
		{
			Toast.makeText(myActivity, "Se debe indicar el número de carriles", Toast.LENGTH_LONG).show();
			radio1_urban.requestFocus();
			return false;
		}
		if (editCp_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_urban.requestFocus();
			return false;
		}
		if (editColonia_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_urban.requestFocus();
			return false;
		}
		if (editMunicipio_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_urban.requestFocus();
			return false;
		}
		if (editEstado2_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado2_urban.requestFocus();
			return false;
		}
		if (editLongIni_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongIni_urban.requestFocus();
			return false;
		}
		if (editLongFin_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongFin_urban.requestFocus();
			return false;
		}
		if (editLatIni_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatIni_urban.requestFocus();
			return false;
		}
		if (editLatFin_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatFin_urban.requestFocus();
			return false;
		}
		if (editAltIni_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltIni_urban.requestFocus();
			return false;
		}
		if (editAltFin_urban.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltFin_urban.requestFocus();
			return false;
		}

		return true;
	}

	private class SaveClickListener implements OnClickListener {
		com.mclr.mini.riesgosmovil.modelos.pojoUrban pojoUrban;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoUrban = new pojoUrban();

			if (validaCampos()) {
				pojoUrban.setPropertyID(myActivity.propertyID);
				SetPojoUrbanP1(pojoUrban);
				UrbanFragment2 urban2 = (UrbanFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				urban2.SetPojoUrbanP2(pojoUrban);

				try
				{
					process.processUrbanSave(pojoUrban);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoUrban.getPropertyID();
					myActivity.postalCodeID = pojoUrban.getPostalCodeID();
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
			//Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
			try {
				KeyOperations.DispachBackKey(myActivity);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void SetPojoUrbanP1(pojoUrban pojo) {
		pojo.setPropertyName(editNombre_urban.getText().toString());
		pojo.setPostalCodeID(postalCodeID);
		pojo.setTown(editMunicipio_urban.getText().toString());
		pojo.setColony(editColonia_urban.getText().toString());
		pojo.setPostalCode(editCp_urban.getText().toString());
		pojo.setAddress(editCalle_urban.getText().toString());
		pojo.setStateName(editEstado2_urban.getText().toString());
		pojo.setStartLongitude(Double.valueOf(editLongIni_urban.getText().toString()));
		pojo.setEndLongitude(Double.valueOf(editLongFin_urban.getText().toString()));
		pojo.setStartLatitude(Double.valueOf(editLatIni_urban.getText().toString()));
		pojo.setEndLatitude(Double.valueOf(editLatFin_urban.getText().toString()));
		pojo.setStartAltitude(Double.valueOf(editAltIni_urban.getText().toString()));
		pojo.setEndAltitude(Double.valueOf(editAltFin_urban.getText().toString()));
		
		if (radioCalle_urban.isChecked())
			pojo.setInfrastructureType(Constants.Tipo_Infraestructuta_Calle);
		else if (radioViaRap_urban.isChecked()){
			pojo.setInfrastructureType(Constants.Tipo_Infraestructuta_Via_rapida);
		}else if (radioPuente_urban.isChecked()){
			pojo.setInfrastructureType(Constants.Tipo_Infraestructuta_Puente);
		}else if (radioTunel_urban.isChecked()){
			pojo.setInfrastructureType(Constants.Tipo_Infraestructuta_Tunel);
		}
		
		if (radio1_urban.isChecked())
			pojo.setLanesNumber(1);
		else if (radio2_urban.isChecked())
			pojo.setLanesNumber(2);
		else if (radio3_urban.isChecked())
			pojo.setLanesNumber(3);
		else if (radio4_urban.isChecked())
			pojo.setLanesNumber(4);
		else if (radio5_urban.isChecked())
			pojo.setLanesNumber(5);
		else if (radio6_urban.isChecked())
			pojo.setLanesNumber(6);
		
		if (checkboxInundacion_urban.isChecked()) {
			pojo.setFleedRisk(true);
		}
		if (checkboxHuracan_urban.isChecked()) {
			pojo.setHurricaneRisk(true);
		}
		if (checkboxTerremoto_urban.isChecked()) {
			pojo.setEarthQuakeRisk(true);
		}
		if (checkboxDeslave_urban.isChecked()) {
			pojo.setLandSlidingRisk(true);
		}
		if (checkboxDerrumbe_urban.isChecked()) {
			pojo.setRockFallingRisk(true);
		}
		
		pojo.setStructuralType(listSpinnerTipoEstru.get(spinnerTipoEstru_urban.getSelectedItemPosition()).getId());
		pojo.setWallsType(listSpinnerMuros.get(spinnerMuros_urban.getSelectedItemPosition()).getId());
		pojo.setFoundationType(listSpinnerTipoCime.get(spinnerTipoCime_urban.getSelectedItemPosition()).getId());
		
		if (editImporte2013_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[0] = new BigDecimal(editImporte2013_urban.getText().toString());
			pojo.getSiniestralityDescription()[0] = editDef2013_urban.getText().toString();
		}

		if (editImporte2012_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[1] = new BigDecimal(editImporte2012_urban.getText().toString());
			pojo.getSiniestralityDescription()[1] = editDef2012_urban.getText().toString();
		}

		if (editImporte2011_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[2] = new BigDecimal(editImporte2011_urban.getText().toString());
			pojo.getSiniestralityDescription()[2] = editDef2011_urban.getText().toString();
		}

		if (editImporte2010_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[3] = new BigDecimal(editImporte2010_urban.getText().toString());
			pojo.getSiniestralityDescription()[3] = editDef2010_urban.getText().toString();
		}

		if (editImporte2009_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[4] = new BigDecimal(editImporte2009_urban.getText().toString());
			pojo.getSiniestralityDescription()[4] = editDef2009_urban.getText().toString();
		}

		if (editImporte2008_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[5] = new BigDecimal(editImporte2008_urban.getText().toString());
			pojo.getSiniestralityDescription()[5] = editDef2008_urban.getText().toString();
		}

		if (editImporte2007_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[6] = new BigDecimal(editImporte2007_urban.getText().toString());
			pojo.getSiniestralityDescription()[6] = editDef2007_urban.getText().toString();
		}

		if (editImporte2006_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[7] = new BigDecimal(editImporte2006_urban.getText().toString());
			pojo.getSiniestralityDescription()[7] = editDef2006_urban.getText().toString();
		}

		if (editImporte2005_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[8] = new BigDecimal(editImporte2005_urban.getText().toString());
			pojo.getSiniestralityDescription()[8] = editDef2005_urban.getText().toString();
		}

		if (editImporte2004_urban.getText().length() > 0) {
			pojo.getSiniestralityAmount()[9] = new BigDecimal(editImporte2004_urban.getText().toString());
			pojo.getSiniestralityDescription()[9] = editDef2004_urban.getText().toString();
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
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_urban.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_urban.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_urban.getText().toString());

			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);
            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.URBAN);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

}
