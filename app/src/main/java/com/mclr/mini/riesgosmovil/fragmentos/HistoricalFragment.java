package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoHistorical;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class HistoricalFragment extends Fragment {
	//private PropertyAdmin myActivity = null;
	private MainActivity myActivity = null;
	public static final String ARG_HISTORICAL_NUMBER = "historical_number";
	public static final String ARG_HISTORICAL_POSTAL_CODE = "historical_postal_code";
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID; // Este se inicializara en la selección del CP
	// Acceso a GPS
	LocationManager locationManager;

	ImageView imagenBien;

	// Elementos graficos
	// Buttons
	ImageButton buttonSaveUp1_2_historical;
	ImageButton buttonOutUp1_2_historical;
	ImageButton buttonCP_historical;
	ImageButton buttonUbicacion_historical;
	ImageButton buttonSaveBot1_2_historical;
	ImageButton buttonOutBot1_2_historical;
	// Info
	EditText editName_historical;
	EditText editNumber_historical;
	RadioButton radioEstatal_historical;
	RadioButton radioMunicipal_historical;
	EditText editCp_historical;
	EditText editCalle_historical;
	EditText editColonia_historical;
	EditText editMunicipio_historical;
	EditText editEstado_historical;
	RadioButton radioMar_historical;
	RadioButton radioLago_historical;
	RadioButton radioLaguna_historical;
	RadioButton radioRio_historical;
	EditText editLongitud_historical;
	EditText editLatitud_historical;
	EditText editAltitud_historical;
	CheckBox checkboxInundacion_historical;
	CheckBox checkboxHuracan_historical;
	CheckBox checkboxTerremoto_historical;
	CheckBox checkboxDeslave_historical;
	CheckBox checkboxDerrumbe_historical;

	EditText editImporte2013_historical;
	EditText editDef2013_historical;
	Switch switchImporte2013_historical;

	EditText editImporte2012_historical;
	EditText editDef2012_historical;
	Switch switchImporte2012_historical;

	EditText editImporte2011_historical;
	EditText editDef2011_historical;
	Switch switchImporte2011_historical;

	EditText editImporte2010_historical;
	EditText editDef2010_historical;
	Switch switchImporte2010_historical;

	EditText editImporte2009_historical;
	EditText editDef2009_historical;
	Switch switchImporte2009_historical;

	EditText editImporte2008_historical;
	EditText editDef2008_historical;
	Switch switchImporte2008_historical;

	EditText editImporte2007_historical;
	EditText editDef2007_historical;
	Switch switchImporte2007_historical;

	EditText editImporte2006_historical;
	EditText editDef2006_historical;
	Switch switchImporte2006_historical;

	EditText editImporte2005_historical;
	EditText editDef2005_historical;
	Switch switchImporte2005_historical;

	EditText editImporte2004_historical;
	EditText editDef2004_historical;
	Switch switchImporte2004_historical;

	public HistoricalFragment() {

	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
		propertyID = myActivity.propertyID;
		postalCodeID = myActivity.postalCodeID;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.historical, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Obtención de referencias a recursos gráficos
		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_historical);

		buttonUbicacion_historical = (ImageButton) myActivity.findViewById(R.id.buttonUbicacion_historical);
		buttonUbicacion_historical.setOnClickListener(new OnClickListener() {
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
		buttonSaveUp1_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_historical);
		buttonSaveUp1_2_historical.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_historical);
		buttonOutUp1_2_historical.setOnClickListener(new OutClickListener());

		buttonSaveBot1_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_historical);
		buttonSaveBot1_2_historical.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_historical);
		buttonOutBot1_2_historical.setOnClickListener(new OutClickListener());

		buttonCP_historical = (ImageButton) myActivity.findViewById(R.id.buttonCP_historical);
		buttonCP_historical.setOnClickListener(new SearchClickListener(this));

		editName_historical = (EditText) myActivity.findViewById(R.id.editName_historical);
		editName_historical.setText("Mario");
		editNumber_historical = (EditText) myActivity.findViewById(R.id.editNumero_historical);
		editNumber_historical.setText("1905");
		radioEstatal_historical = (RadioButton) myActivity.findViewById(R.id.radioEstatal_historical);
		radioMunicipal_historical = (RadioButton) myActivity.findViewById(R.id.radioMunicipal_historical);
		editCp_historical = (EditText) myActivity.findViewById(R.id.editCp_historical);
		editCalle_historical = (EditText) myActivity.findViewById(R.id.editCalle_historical);
		editCalle_historical.setText("Oriente 166 338");
		editColonia_historical = (EditText) myActivity.findViewById(R.id.editColonia_historical);
		editMunicipio_historical = (EditText) myActivity.findViewById(R.id.editMunicipio_historical);
		editEstado_historical = (EditText) myActivity.findViewById(R.id.editEstado_historical);
		radioMar_historical = (RadioButton) myActivity.findViewById(R.id.radioMar_historical);
		radioLago_historical = (RadioButton) myActivity.findViewById(R.id.radioLago_historical);
		radioLaguna_historical = (RadioButton) myActivity.findViewById(R.id.radioLaguna_historical);
		radioRio_historical = (RadioButton) myActivity.findViewById(R.id.radioRio_historical);
		editLongitud_historical = (EditText) myActivity.findViewById(R.id.editLongitud_historical);
		editLongitud_historical.setText("19.361326");
		editLatitud_historical = (EditText) myActivity.findViewById(R.id.editLatitud_historical);
		editLatitud_historical.setText("-99.177437");
		editAltitud_historical = (EditText) myActivity.findViewById(R.id.editAltitud_historical);
		editAltitud_historical.setText("-59.177437");
		checkboxInundacion_historical = (CheckBox) myActivity.findViewById(R.id.checkboxInundacion_historical);
		checkboxHuracan_historical = (CheckBox) myActivity.findViewById(R.id.checkboxHuracan_historical);
		checkboxTerremoto_historical = (CheckBox) myActivity.findViewById(R.id.checkboxTerremoto_historical);
		checkboxDeslave_historical = (CheckBox) myActivity.findViewById(R.id.checkboxDeslave_historical);
		checkboxDerrumbe_historical = (CheckBox) myActivity.findViewById(R.id.checkboxDerrumbe_historical);

		editImporte2004_historical = (EditText) myActivity.findViewById(R.id.editImporte2004_historical);
		editDef2004_historical = (EditText) myActivity.findViewById(R.id.editDef2004_historical);
		switchImporte2004_historical = (Switch) myActivity.findViewById(R.id.switchImporte2004_historical);
		switchImporte2004_historical.setEnabled(false);

		editImporte2005_historical = (EditText) myActivity.findViewById(R.id.editImporte2005_historical);
		editDef2005_historical = (EditText) myActivity.findViewById(R.id.editDef2005_historical);
		switchImporte2005_historical = (Switch) myActivity.findViewById(R.id.switchImporte2005_historical);
		switchImporte2005_historical.setEnabled(false);

		editImporte2006_historical = (EditText) myActivity.findViewById(R.id.editImporte2006_historical);
		editDef2006_historical = (EditText) myActivity.findViewById(R.id.editDef2006_historical);
		switchImporte2006_historical = (Switch) myActivity.findViewById(R.id.switchImporte2006_historical);
		switchImporte2006_historical.setEnabled(false);

		editImporte2007_historical = (EditText) myActivity.findViewById(R.id.editImporte2007_historical);
		editDef2007_historical = (EditText) myActivity.findViewById(R.id.editDef2007_historical);
		switchImporte2007_historical = (Switch) myActivity.findViewById(R.id.switchImporte2007_historical);
		switchImporte2007_historical.setEnabled(false);

		editImporte2008_historical = (EditText) myActivity.findViewById(R.id.editImporte2008_historical);
		editDef2008_historical = (EditText) myActivity.findViewById(R.id.editDef2008_historical);
		switchImporte2008_historical = (Switch) myActivity.findViewById(R.id.switchImporte2008_historical);
		switchImporte2008_historical.setEnabled(false);

		editImporte2009_historical = (EditText) myActivity.findViewById(R.id.editImporte2009_historical);
		editDef2009_historical = (EditText) myActivity.findViewById(R.id.editDef2009_historical);
		switchImporte2009_historical = (Switch) myActivity.findViewById(R.id.switchImporte2009_historical);
		switchImporte2009_historical.setEnabled(false);

		editImporte2010_historical = (EditText) myActivity.findViewById(R.id.editImporte2010_historical);
		editDef2010_historical = (EditText) myActivity.findViewById(R.id.editDef2010_historical);
		switchImporte2010_historical = (Switch) myActivity.findViewById(R.id.switchImporte2010_historical);
		switchImporte2010_historical.setEnabled(false);

		editImporte2011_historical = (EditText) myActivity.findViewById(R.id.editImporte2011_historical);
		editDef2011_historical = (EditText) myActivity.findViewById(R.id.editDef2011_historical);
		switchImporte2011_historical = (Switch) myActivity.findViewById(R.id.switchImporte2011_historical);
		switchImporte2011_historical.setEnabled(false);

		editImporte2012_historical = (EditText) myActivity.findViewById(R.id.editImporte2012_historical);
		editDef2012_historical = (EditText) myActivity.findViewById(R.id.editDef2012_historical);
		switchImporte2012_historical = (Switch) myActivity.findViewById(R.id.switchImporte2012_historical);
		switchImporte2012_historical.setEnabled(false);

		editImporte2013_historical = (EditText) myActivity.findViewById(R.id.editImporte2013_historical);
		editImporte2013_historical.setText("1");
		editDef2013_historical = (EditText) myActivity.findViewById(R.id.editDef2013_historical);
		editDef2013_historical.setText("1D");
		switchImporte2013_historical = (Switch) myActivity.findViewById(R.id.switchImporte2013_historical);
		switchImporte2013_historical.setEnabled(false);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoHistorical pojo = propierty.getHistoricalPptyDetail(propertyID, postalCodeID);
			//Asignación de valores a recursos gráficos
			editName_historical.setText(pojo.getBuildingName());
			editNumber_historical.setText(pojo.getBuildingNumber());
			if (pojo.getManagedBy().compareTo(Constants.Federal_Administration) == 0) {
				radioEstatal_historical.setChecked(true);
			} else if (pojo.getManagedBy().compareTo(Constants.Local_Administration) == 0) {
				radioMunicipal_historical.setChecked(true);
			}
			editCp_historical.setText(pojo.getPostalCode());
			editCalle_historical.setText(pojo.getAddress());
			editColonia_historical.setText(pojo.getColony());
			editCalle_historical.setText(pojo.getAddress());
			editMunicipio_historical.setText(pojo.getTown());
			editEstado_historical.setText(pojo.getStateName());
			if (pojo.getWaterBody().compareTo(Constants.Mar) == 0) {
				radioMar_historical.setChecked(true);
			} else if (pojo.getWaterBody().compareTo(Constants.Lago) == 0) {
				radioLago_historical.setChecked(true);
			} else if (pojo.getWaterBody().compareTo(Constants.Laguna) == 0) {
				radioLaguna_historical.setChecked(true);
			} else if (pojo.getWaterBody().compareTo(Constants.Rio) == 0) {
				radioRio_historical.setChecked(true);
			}
			editLongitud_historical.setText(String.valueOf(pojo.getLongitude()));
			editLatitud_historical.setText(String.valueOf(pojo.getLatitude()));
			editAltitud_historical.setText(String.valueOf(pojo.getAltitude()));
			if (pojo.hasFloodRisk()) {
				checkboxInundacion_historical.setChecked(true);
			}
			if (pojo.hasHurricanRisk()) {
				checkboxHuracan_historical.setChecked(true);
			}
			if (pojo.hasEarthQuakeRisk()) {
				checkboxTerremoto_historical.setChecked(true);
			}
			if (pojo.hasSlidingRisk()) {
				checkboxDeslave_historical.setChecked(true);
			}
			if (pojo.hasRockFallRisk()) {
				checkboxDerrumbe_historical.setChecked(true);
			}

			editImporte2013_historical.setText(pojo.getSiniestrality()[0].toString());
			editDef2013_historical.setText(pojo.getSiniestralityDesc()[0].toString());

			editImporte2012_historical.setText(pojo.getSiniestrality()[1].toString());
			editDef2012_historical.setText(pojo.getSiniestralityDesc()[1].toString());

			editImporte2011_historical.setText(pojo.getSiniestrality()[2].toString());
			editDef2011_historical.setText(pojo.getSiniestralityDesc()[2].toString());

			editImporte2010_historical.setText(pojo.getSiniestrality()[3].toString());
			editDef2010_historical.setText(pojo.getSiniestralityDesc()[3].toString());

			editImporte2009_historical.setText(pojo.getSiniestrality()[4].toString());
			editDef2009_historical.setText(pojo.getSiniestralityDesc()[4].toString());

			editImporte2008_historical.setText(pojo.getSiniestrality()[5].toString());
			editDef2008_historical.setText(pojo.getSiniestralityDesc()[5].toString());

			editImporte2007_historical.setText(pojo.getSiniestrality()[6].toString());
			editDef2007_historical.setText(pojo.getSiniestralityDesc()[6].toString());

			editImporte2006_historical.setText(pojo.getSiniestrality()[7].toString());
			editDef2006_historical.setText(pojo.getSiniestralityDesc()[7].toString());

			editImporte2005_historical.setText(pojo.getSiniestrality()[8].toString());
			editDef2005_historical.setText(pojo.getSiniestralityDesc()[8].toString());

			editImporte2004_historical.setText(pojo.getSiniestrality()[9].toString());
			editDef2004_historical.setText(pojo.getSiniestralityDesc()[9].toString());

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.historical_and_archaeological_blue);

		}
		//Toast.makeText(myActivity, "PostalCode: " + postalCode, Toast.LENGTH_LONG).show();
		//Toast.makeText(myActivity, "item: " + pojo.getAddress(), Toast.LENGTH_LONG).show();
	}

	private void MuestraPosicion(Location loc) {
	    if(loc != null)
	    {
	        editLatitud_historical.setText(String.valueOf(loc.getLatitude()));
	        editLongitud_historical.setText(String.valueOf(loc.getLongitude()));
	        editAltitud_historical.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatitud_historical.setText("(sin_datos)");
	    	editLongitud_historical.setText("(sin_datos)");
	    	editAltitud_historical.setText("(sin_datos)");
	    }
	}

	private class CambioValores implements OnKeyListener {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			CalculaTotal();
			return false;
		}

	}

	private class SaveClickListener implements OnClickListener {
		pojoHistorical pojoHist;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoHist = new pojoHistorical();

			if (validaCampos()) {

				pojoHist.setPropertyID(myActivity.propertyID);
				SetPojoHistoricalP1(pojoHist);
				HistoricalFragment2 historico2 = (HistoricalFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				historico2.SetPojoHistoricalP2(pojoHist);

				try
				{
					process.processHistoricalSave(pojoHist);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoHist.getPropertyID();
					myActivity.postalCodeID = pojoHist.getPostalCodeID();
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
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_historical.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_historical.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_historical.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);

            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.HISTORICAL);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

	public void CerrarBien()
	{
		buttonSaveUp1_2_historical.setEnabled(false);
		buttonOutUp1_2_historical.setEnabled(false);
		buttonCP_historical.setEnabled(false);
		buttonUbicacion_historical.setEnabled(false);
		buttonSaveBot1_2_historical.setEnabled(false);
		buttonOutBot1_2_historical.setEnabled(false);
		// Info
		editName_historical.setEnabled(false);
		editNumber_historical.setEnabled(false);
		radioEstatal_historical.setEnabled(false);
		radioMunicipal_historical.setEnabled(false);
		editCp_historical.setEnabled(false);
		editCalle_historical.setEnabled(false);
		editColonia_historical.setEnabled(false);
		editMunicipio_historical.setEnabled(false);
		editEstado_historical.setEnabled(false);
		radioMar_historical.setEnabled(false);
		radioLago_historical.setEnabled(false);
		radioLaguna_historical.setEnabled(false);
		radioRio_historical.setEnabled(false);
		editLongitud_historical.setEnabled(false);
		editLatitud_historical.setEnabled(false);
		editAltitud_historical.setEnabled(false);
		checkboxInundacion_historical.setEnabled(false);
		checkboxHuracan_historical.setEnabled(false);
		checkboxTerremoto_historical.setEnabled(false);
		checkboxDeslave_historical.setEnabled(false);
		checkboxDerrumbe_historical.setEnabled(false);

		editImporte2013_historical.setEnabled(false);
		editDef2013_historical.setEnabled(false);

		editImporte2012_historical.setEnabled(false);
		editDef2012_historical.setEnabled(false);

		editImporte2011_historical.setEnabled(false);
		editDef2011_historical.setEnabled(false);

		editImporte2010_historical.setEnabled(false);
		editDef2010_historical.setEnabled(false);

		editImporte2009_historical.setEnabled(false);
		editDef2009_historical.setEnabled(false);

		editImporte2008_historical.setEnabled(false);
		editDef2008_historical.setEnabled(false);

		editImporte2007_historical.setEnabled(false);
		editDef2007_historical.setEnabled(false);

		editImporte2006_historical.setEnabled(false);
		editDef2006_historical.setEnabled(false);

		editImporte2005_historical.setEnabled(false);
		editDef2005_historical.setEnabled(false);

		editImporte2004_historical.setEnabled(false);
		editDef2004_historical.setEnabled(false);

		buttonSaveUp1_2_historical.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_historical.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_historical.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacion_historical.setImageResource(R.mipmap.ubicacion_blocked);
		buttonSaveBot1_2_historical.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_historical.setImageResource(R.mipmap.salir_blocked);

	}

	public boolean validaCampos(){
		if (editName_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre del bien", Toast.LENGTH_LONG).show();
			editName_historical.requestFocus();
			return false;
		}
		if (editNumber_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número del bien", Toast.LENGTH_LONG).show();
			editNumber_historical.requestFocus();
			return false;
		}
		if (!radioEstatal_historical.isChecked() && !radioMunicipal_historical.isChecked()){
			Toast.makeText(myActivity, "Se debe indicar por parte de que gobierno esta administrado el bien", Toast.LENGTH_LONG).show();
			radioEstatal_historical.requestFocus();
			return false;
		}
		if (editCp_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_historical.requestFocus();
			return false;
		}
		if (editCalle_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la calle correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCalle_historical.requestFocus();
			return false;
		}
		if (editColonia_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_historical.requestFocus();
			return false;
		}
		if (editMunicipio_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_historical.requestFocus();
			return false;
		}
		if (editEstado_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado_historical.requestFocus();
			return false;
		}
		if (!radioMar_historical.isChecked() && !radioLago_historical.isChecked() && !radioLaguna_historical.isChecked() && !radioRio_historical.isChecked()){
			Toast.makeText(myActivity, "Se debe indicar el cuerpo de agua más cercano al bien", Toast.LENGTH_LONG).show();
			radioMar_historical.requestFocus();
			return false;
		}

		if (editLongitud_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongitud_historical.requestFocus();
			return false;
		}
		if (editLatitud_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatitud_historical.requestFocus();
			return false;
		}
		if (editAltitud_historical.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltitud_historical.requestFocus();
			return false;
		}
		return true;
	}

	public void SetPojoHistoricalP1(pojoHistorical pojoHist)
	{
		pojoHist.setPostalCodeID(postalCodeID);
		pojoHist.setBuildingName(editName_historical.getText().toString());
		pojoHist.setBuildingNumber(editNumber_historical.getText().toString());
		if (radioEstatal_historical.isChecked())
			pojoHist.setManagedBy(Constants.Federal_Administration);
		else if (radioMunicipal_historical.isChecked()){
			pojoHist.setManagedBy(Constants.Local_Administration);
		}
		pojoHist.setPostalCode(editCp_historical.getText().toString());
		//pojoHist.setAddress(editCalle_historical.getText().toString() + " " + editNumero_historical.getText().toString()); // Adress es Calle y numero
		pojoHist.setAddress(editCalle_historical.getText().toString());
		pojoHist.setColony(editColonia_historical.getText().toString());
		pojoHist.setTown(editMunicipio_historical.getText().toString());
		pojoHist.setStateName(editEstado_historical.getText().toString());

		if (radioMar_historical.isChecked())
			pojoHist.setWaterBody(Constants.Mar);
		else if (radioLago_historical.isChecked())
			pojoHist.setWaterBody(Constants.Lago);
		else if (radioLaguna_historical.isChecked())
			pojoHist.setWaterBody(Constants.Laguna);
		else if (radioRio_historical.isChecked())
			pojoHist.setWaterBody(Constants.Rio);

		pojoHist.setLongitude(Double.valueOf(editLongitud_historical.getText().toString()));
		pojoHist.setLatitude(Double.valueOf(editLatitud_historical.getText().toString()));
		pojoHist.setAltitude(Double.valueOf(editAltitud_historical.getText().toString()));

		if (checkboxInundacion_historical.isChecked()) {
			pojoHist.setFloodRisk(true);
		}
		if (checkboxHuracan_historical.isChecked()) {
			pojoHist.setHurricanRisk(true);
		}
		if (checkboxTerremoto_historical.isChecked()) {
			pojoHist.setEarthQuakeRisk(true);
		}
		if (checkboxDeslave_historical.isChecked()) {
			pojoHist.setSlidingRisk(true);
		}
		if (checkboxDerrumbe_historical.isChecked()) {
			pojoHist.setRockFallRisk(true);
		}

		if (editImporte2013_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[0] = editImporte2013_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[0] = editDef2013_historical.getText().toString();
		}

		if (editImporte2012_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[1] = editImporte2012_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[1] = editDef2012_historical.getText().toString();
		}

		if (editImporte2011_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[2] = editImporte2011_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[2] = editDef2011_historical.getText().toString();
		}

		if (editImporte2010_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[3] = editImporte2010_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[3] = editDef2010_historical.getText().toString();
		}

		if (editImporte2009_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[4] = editImporte2009_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[4] = editDef2009_historical.getText().toString();
		}

		if (editImporte2008_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[5] = editImporte2008_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[5] = editDef2008_historical.getText().toString();
		}

		if (editImporte2007_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[6] = editImporte2007_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[6] = editDef2007_historical.getText().toString();
		}

		if (editImporte2006_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[7] = editImporte2006_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[7] = editDef2006_historical.getText().toString();
		}

		if (editImporte2005_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[8] = editImporte2005_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[8] = editDef2005_historical.getText().toString();
		}

		if (editImporte2004_historical.getText().length() > 0) {
			pojoHist.getSiniestrality()[9] = editImporte2004_historical.getText().toString();
			pojoHist.getSiniestralityDesc()[9] = editDef2004_historical.getText().toString();
		}

	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_historical.setText(colonia);
		editMunicipio_historical.setText(municipio);
		editEstado_historical.setText(estado);
		editCp_historical.setText(CP);
		postalCodeID = p_postalCodeID;
		
		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

	private void CalculaTotal() {
//		BigDecimal totalVR;
//		BigDecimal totalVN;
//
//		totalVR = new BigDecimal(editEdificiovr_historical.getText().toString())
//			.add(new BigDecimal(editEquiposvr_historical.getText().toString()))
//			.add(new BigDecimal(editAuxiliaresvr_historical.getText().toString()))
//			.add(new BigDecimal(editInventariovr_historical.getText().toString()))
//			.add(new BigDecimal(editCdsvr_historical.getText().toString()))
//			.add(new BigDecimal(editOtrosvr_historical.getText().toString()))
//			.add(new BigDecimal(editMueblesvr_historical.getText().toString()))
//			.add(new BigDecimal(editEnseresvr_historical.getText().toString()));
//		
//		totalVN = new BigDecimal(editEdificiovrep_historical.getText().toString())
//			.add(new BigDecimal(editEquiposvrep_historical.getText().toString()))
//			.add(new BigDecimal(editAuxiliaresvrep_historical.getText().toString()))
//			.add(new BigDecimal(editInventariovrep_historical.getText().toString()))
//			.add(new BigDecimal(editCdsvrep_historical.getText().toString()))
//			.add(new BigDecimal(editOtrosvrep_historical.getText().toString()))
//			.add(new BigDecimal(editMueblesvrep_historical.getText().toString()))
//			.add(new BigDecimal(editEnseresvrep_historical.getText().toString()));
//
//		editTotalVR.setText(totalVR.toString());
//		editTotalVN.setText(totalVN.toString());
	}

}
