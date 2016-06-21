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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoWasteDisposal;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

public class WasteDisposalFragment extends Fragment {
	private MainActivity myActivity = null;
	public static final String ARG_WASTE_DISPOSAL_NUMBER = "waste_disposal_number";
	public static final String ARG_WASTE_DISPOSAL_POSTAL_CODE = "waste_disposal_postal_code";
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCode;
	String postalCodeID; // Este se inicializara en la selección del CP

	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonSaveUp1_2_waste_disposal;
	ImageButton buttonOutUp1_2_waste_disposal;
	ImageButton buttonSaveBot1_2_waste_disposal;
	ImageButton buttonOutBot1_2_waste_disposal;
	ImageButton buttonUbicacionIni_waste;
	ImageButton buttonUbicacionFin_waste;
	ImageButton buttonCP_waste_disposal;

	Spinner spinnerTipo_waste_disposal;
	List<CatalogItem> listSpinnerTipo;
	Spinner spinnerTipoEstruc_waste_disposal;
	List<CatalogItem> listSpinnerTipoEstruc;
	Spinner spinnerMuros_waste_disposal;
	List<CatalogItem> listSpinnerTipoMuros;
	Spinner spinnerTipoCime_waste_disposal;
	List<CatalogItem> listSpinnerTipoCime;
	
	EditText editNombre_waste;
	EditText editExtension_waste_disposal;
	EditText editCp_waste_disposal;
	EditText editCalle_waste_disposal;
	EditText editColonia_waste_disposal;
	EditText editMunicipio_waste_disposal;
	EditText editEstado_waste_disposal;
	EditText editLongIni_waste;
	EditText editLongFin_waste;
	EditText editLatIni_waste;
	EditText editLatFin_waste;
	EditText editAltIni_waste;
	EditText editAltFin_waste;

	CheckBox checkboxInundacion_waste_disposal;
	CheckBox checkboxHuracan_waste_disposal;
	CheckBox checkboxTerremoto_waste_disposal;
	CheckBox checkboxDeslave_waste_disposal;
	CheckBox checkboxDerrumbe_waste_disposal;

	Switch switchCamAcce_waste_disposal;
	Switch switchCamInt_waste_disposal;
	Switch switchCercaPeri_waste_disposal;
	Switch switchCaseVigi_waste_disposal;
	Switch switchBascula_waste_disposal;
	Switch switchAguaPot_waste_disposal;
	Switch switchVestServ_waste_disposal;
	Switch switchOficinas_waste_disposal;
	Switch switchServMed_waste_disposal;
	Switch switchFranja_waste_disposal;

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
		View rootView = inflater.inflate(R.layout.waste, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_waste_disposal);

		buttonSaveUp1_2_waste_disposal = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_waste_disposal);
		buttonSaveUp1_2_waste_disposal.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_waste_disposal = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_waste_disposal);
		buttonOutUp1_2_waste_disposal.setOnClickListener(new OutClickListener());

		buttonSaveBot1_2_waste_disposal = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_waste_disposal);
		buttonSaveBot1_2_waste_disposal.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_waste_disposal = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_waste_disposal);
		buttonOutBot1_2_waste_disposal.setOnClickListener(new OutClickListener());

		buttonCP_waste_disposal = (ImageButton) myActivity.findViewById(R.id.buttonCP_waste_disposal);
		buttonCP_waste_disposal.setOnClickListener(new SearchClickListener(this));
		
		editNombre_waste = (EditText)myActivity.findViewById(R.id.editNombre_waste);
		
		spinnerTipo_waste_disposal = (Spinner)myActivity.findViewById(R.id.spinnerTipo_waste_disposal);
		listSpinnerTipo = db.getCatalogList(Constants.WASTE_DISPOSA_INFRASTRUCTURE_TYPE);
		spinnerTipo_waste_disposal.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipo));

		editExtension_waste_disposal = (EditText)myActivity.findViewById(R.id.editExtension_waste_disposal);
		checkboxInundacion_waste_disposal = (CheckBox)myActivity.findViewById(R.id.checkboxInundacion_waste_disposal);
		checkboxHuracan_waste_disposal = (CheckBox)myActivity.findViewById(R.id.checkboxHuracan_waste_disposal);
		checkboxTerremoto_waste_disposal = (CheckBox)myActivity.findViewById(R.id.checkboxTerremoto_waste_disposal);
		checkboxDeslave_waste_disposal = (CheckBox)myActivity.findViewById(R.id.checkboxDeslave_waste_disposal);
		checkboxDerrumbe_waste_disposal = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbe_waste_disposal);
		editMunicipio_waste_disposal = (EditText)myActivity.findViewById(R.id.editMunicipio_waste_disposal);
		editColonia_waste_disposal = (EditText)myActivity.findViewById(R.id.editColonia_waste_disposal);
		editCp_waste_disposal = (EditText)myActivity.findViewById(R.id.editCp_waste_disposal);
		editCalle_waste_disposal = (EditText)myActivity.findViewById(R.id.editCalle_waste_disposal);
		editEstado_waste_disposal = (EditText)myActivity.findViewById(R.id.editEstado_waste_disposal);

		editLongIni_waste = (EditText)myActivity.findViewById(R.id.editLongIni_waste);
		editLongFin_waste = (EditText)myActivity.findViewById(R.id.editLongFin_waste);
		editLatIni_waste = (EditText)myActivity.findViewById(R.id.editLatIni_waste);
		editLatFin_waste = (EditText)myActivity.findViewById(R.id.editLatFin_waste);
		editAltIni_waste = (EditText)myActivity.findViewById(R.id.editAltIni_waste);
		editAltFin_waste = (EditText)myActivity.findViewById(R.id.editAltFin_waste);

		spinnerTipoEstruc_waste_disposal = (Spinner)myActivity.findViewById(R.id.spinnerTipoEstruc_waste_disposal);
		listSpinnerTipoEstruc = db.getCatalogList(Constants.STRUCTURE_TYPE);
		spinnerTipoEstruc_waste_disposal.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstruc));

		spinnerMuros_waste_disposal = (Spinner)myActivity.findViewById(R.id.spinnerMuros_waste_disposal);
		listSpinnerTipoMuros = db.getCatalogList(Constants.WALL_TYPE);
		spinnerMuros_waste_disposal.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoMuros));

		spinnerTipoCime_waste_disposal = (Spinner)myActivity.findViewById(R.id.spinnerTipoCime_waste_disposal);
		listSpinnerTipoCime = db.getCatalogList(Constants.FOUNDATION_TYPE);
		spinnerTipoCime_waste_disposal.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCime));
		
		buttonUbicacionIni_waste = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionIni_waste);
		buttonUbicacionIni_waste.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            muestraPosicionIni(location);
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

		buttonUbicacionFin_waste = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionFin_waste);
		buttonUbicacionFin_waste.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            muestraPosicionFin(location);
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

		switchCamAcce_waste_disposal = (Switch)myActivity.findViewById(R.id.switchCamAcce_waste_disposal);
		switchCamInt_waste_disposal = (Switch)myActivity.findViewById(R.id.switchCamInt_waste_disposal);
		switchCercaPeri_waste_disposal = (Switch)myActivity.findViewById(R.id.switchCercaPeri_waste_disposal);
		switchCaseVigi_waste_disposal = (Switch)myActivity.findViewById(R.id.switchCaseVigi_waste_disposal);
		switchBascula_waste_disposal = (Switch)myActivity.findViewById(R.id.switchBascula_waste_disposal);
		switchAguaPot_waste_disposal = (Switch)myActivity.findViewById(R.id.switchAguaPot_waste_disposal);
		switchVestServ_waste_disposal = (Switch)myActivity.findViewById(R.id.switchVestServ_waste_disposal);
		switchOficinas_waste_disposal = (Switch)myActivity.findViewById(R.id.switchOficinas_waste_disposal);
		switchServMed_waste_disposal = (Switch)myActivity.findViewById(R.id.switchServMed_waste_disposal);
		switchFranja_waste_disposal = (Switch)myActivity.findViewById(R.id.switchFranja_waste_disposal);
		
		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)) {
			pojoWasteDisposal pojo = propierty.getWasteDisposalPptyDetail(propertyID, postalCode);
			editNombre_waste.setText(String.valueOf(pojo.getName()));
			spinnerTipo_waste_disposal.setSelection(ListOperations.getItemPositionByID(listSpinnerTipo, pojo.getWasteDisposalType()));
			editExtension_waste_disposal.setText(String.valueOf(pojo.getExtension()));
			if (pojo.isFleedRisk()) {
				checkboxInundacion_waste_disposal.setChecked(true);
			}
			if (pojo.isHurricaneRisk()) {
				checkboxHuracan_waste_disposal.setChecked(true);
			}
			if (pojo.isEarthQuakeRisk()) {
				checkboxTerremoto_waste_disposal.setChecked(true);
			}
			if (pojo.isLandSlideRisk()) {
				checkboxDeslave_waste_disposal.setChecked(true);
			}
			if (pojo.isCollapseRisk()) {
				checkboxDerrumbe_waste_disposal.setChecked(true);
			}
			editMunicipio_waste_disposal.setText(pojo.getTown());
			editColonia_waste_disposal.setText(pojo.getColony());
			editCp_waste_disposal.setText(pojo.getPostalCode());
			editCalle_waste_disposal.setText(pojo.getAddress());
			editEstado_waste_disposal.setText(pojo.getStateName());

			editLongIni_waste.setText(String.valueOf(pojo.getStartLongitude()));
			editLongFin_waste.setText(String.valueOf(pojo.getEndLongitude()));
			editLatIni_waste.setText(String.valueOf(pojo.getStartLatitude()));
			editLatFin_waste.setText(String.valueOf(pojo.getEndLatitude()));
			editAltIni_waste.setText(String.valueOf(pojo.getStartAltitude()));
			editAltFin_waste.setText(String.valueOf(pojo.getEndAltitude()));

			spinnerTipo_waste_disposal.setSelection(ListOperations.getItemPositionByID(listSpinnerTipo, pojo.getWasteDisposalType()));
			spinnerTipoEstruc_waste_disposal.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstruc, pojo.getStructuralType()));
			spinnerMuros_waste_disposal.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoMuros, pojo.getWallsType()));
			spinnerTipoCime_waste_disposal.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getFoundationType()));

			switchCamAcce_waste_disposal.setChecked(pojo.isAccessWays());
			switchCamInt_waste_disposal.setChecked(pojo.isInnerWays());
			switchCercaPeri_waste_disposal.setChecked(pojo.isPerimeterFence());
			switchCaseVigi_waste_disposal.setChecked(pojo.isSecurityRoom());
			switchBascula_waste_disposal.setChecked(pojo.isBascule());
			switchAguaPot_waste_disposal.setChecked(pojo.isWaterElectricityDrain());
			switchVestServ_waste_disposal.setChecked(pojo.isSanitaryServices());
			switchOficinas_waste_disposal.setChecked(pojo.isOffices());
			switchServMed_waste_disposal.setChecked(pojo.isMedicalServices());
			switchFranja_waste_disposal.setChecked(pojo.isCushioningStrip());

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.waste_disposal_blue);

		}
	}

	public void CerrarBien()
	{
		buttonSaveUp1_2_waste_disposal.setEnabled(false);
		//buttonOutUp1_2_waste_disposal.setEnabled(false);
		buttonSaveBot1_2_waste_disposal.setEnabled(false);
		//buttonOutBot1_2_waste_disposal.setEnabled(false);
		buttonUbicacionIni_waste.setEnabled(false);
		buttonUbicacionFin_waste.setEnabled(false);
		buttonCP_waste_disposal.setEnabled(false);

		spinnerTipo_waste_disposal.setEnabled(false);
		spinnerTipoEstruc_waste_disposal.setEnabled(false);
		spinnerMuros_waste_disposal.setEnabled(false);
		spinnerTipoCime_waste_disposal.setEnabled(false);
		
		editNombre_waste.setEnabled(false);
		editExtension_waste_disposal.setEnabled(false);
		editCp_waste_disposal.setEnabled(false);
		editCalle_waste_disposal.setEnabled(false);
		editColonia_waste_disposal.setEnabled(false);
		editMunicipio_waste_disposal.setEnabled(false);
		editEstado_waste_disposal.setEnabled(false);
		editLongIni_waste.setEnabled(false);
		editLongFin_waste.setEnabled(false);
		editLatIni_waste.setEnabled(false);
		editLatFin_waste.setEnabled(false);
		editAltIni_waste.setEnabled(false);
		editAltFin_waste.setEnabled(false);

		checkboxInundacion_waste_disposal.setEnabled(false);
		checkboxHuracan_waste_disposal.setEnabled(false);
		checkboxTerremoto_waste_disposal.setEnabled(false);
		checkboxDeslave_waste_disposal.setEnabled(false);
		checkboxDerrumbe_waste_disposal.setEnabled(false);

		buttonSaveUp1_2_waste_disposal.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_waste_disposal.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot1_2_waste_disposal.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_waste_disposal.setImageResource(R.mipmap.salir_blocked);
		buttonUbicacionIni_waste.setImageResource(R.mipmap.ubicacion_inicial_blocked);
		buttonUbicacionFin_waste.setImageResource(R.mipmap.ubicacion_final_blocked);
		//buttonCP_waste_disposal.setImageResource(R.mipmap.guardar_blocked);
	}

	private void muestraPosicionIni(Location loc) {
	    if(loc != null)
	    {
	    	editLatIni_waste.setText(String.valueOf(loc.getLatitude()));
	    	editLongIni_waste.setText(String.valueOf(loc.getLongitude()));
	    	editAltIni_waste.setText(String.valueOf(loc.getAltitude()));
	    }
	    else
	    {
	    	editLatIni_waste.setText("(sin_datos)");
	    	editLongIni_waste.setText("(sin_datos)");
	    	editAltIni_waste.setText("(sin_datos)");
	    }
	}

	private void muestraPosicionFin(Location loc) {
	    if(loc != null)
	    {
	    	editLatFin_waste.setText(String.valueOf(loc.getLatitude()));
	        editLongFin_waste.setText(String.valueOf(loc.getLongitude()));
	        editAltFin_waste.setText(String.valueOf(loc.getAltitude()));
	    }
	    else
	    {
	    	editLatFin_waste.setText("(sin_datos)");
	    	editLongFin_waste.setText("(sin_datos)");
	    	editAltFin_waste.setText("(sin_datos)");
	    }
	}

	public boolean validaCampos() {
		if (editNombre_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el nombre correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editNombre_waste.requestFocus();
			return false;
		}
		if(spinnerTipo_waste_disposal.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo", Toast.LENGTH_LONG).show();
			spinnerTipo_waste_disposal.requestFocus();
			return false;
		}
		if (editExtension_waste_disposal.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar extensión", Toast.LENGTH_LONG).show();
			editExtension_waste_disposal.requestFocus();
			return false;
		}
		if (editCp_waste_disposal.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_waste_disposal.requestFocus();
			return false;
		}
		if (editColonia_waste_disposal.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_waste_disposal.requestFocus();
			return false;
		}
		if (editMunicipio_waste_disposal.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_waste_disposal.requestFocus();
			return false;
		}
		if (editEstado_waste_disposal.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado_waste_disposal.requestFocus();
			return false;
		}
		if (editLongIni_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongIni_waste.requestFocus();
			return false;
		}
		if (editLongFin_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongFin_waste.requestFocus();
			return false;
		}
		if (editLatIni_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatIni_waste.requestFocus();
			return false;
		}
		if (editLatFin_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatFin_waste.requestFocus();
			return false;
		}
		if (editAltIni_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltIni_waste.requestFocus();
			return false;
		}
		if (editAltFin_waste.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltFin_waste.requestFocus();
			return false;
		}
		return true;
	}

	private class SaveClickListener implements OnClickListener {
		pojoWasteDisposal pojoWaste;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoWaste = new pojoWasteDisposal();

			if (validaCampos()) {
				pojoWaste.setPropertyID(myActivity.propertyID);
				SetPojoWasteP1(pojoWaste);
				WasteDisposalFragment2 waste2 = (WasteDisposalFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
				waste2.SetPojoWasteP2(pojoWaste);

				try
				{
					process.processWasteSave(pojoWaste);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoWaste.getPropertyID();
					myActivity.postalCodeID = pojoWaste.getPostalCodeID();
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

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_waste_disposal.setText(colonia);
		editMunicipio_waste_disposal.setText(municipio);
		editEstado_waste_disposal.setText(estado);
		editCp_waste_disposal.setText(CP);
		postalCodeID = p_postalCodeID;
		
		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}

	private class SearchClickListener implements OnClickListener {
		Fragment fragment;
		Bundle args = new Bundle();

		public SearchClickListener(Fragment p_fragment) {
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_waste_disposal.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_waste_disposal.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_waste_disposal.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);
            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.WASTE_DISPOSAL);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

	public void SetPojoWasteP1(pojoWasteDisposal pojo) {
		pojo.setName(editNombre_waste.getText().toString());
		pojo.setPostalCodeID(postalCodeID);
		pojo.setWasteDisposalType(listSpinnerTipo.get(spinnerTipo_waste_disposal.getSelectedItemPosition()).getId());
		pojo.setExtension(Double.valueOf(editExtension_waste_disposal.getText().toString()));
		
		if (checkboxInundacion_waste_disposal.isChecked()) {
			pojo.setHurricaneRisk(true);
		}
		if (checkboxHuracan_waste_disposal.isChecked()) {
			pojo.setHurricaneRisk(true);
		}
		if (checkboxTerremoto_waste_disposal.isChecked()) {
			pojo.setEarthQuakeRisk(true);
		}
		if (checkboxDeslave_waste_disposal.isChecked()) {
			pojo.setFleedRisk(true);
		}
		if (checkboxDerrumbe_waste_disposal.isChecked()) {
			pojo.setLandSlideRisk(true);
		}

		pojo.setTown(editMunicipio_waste_disposal.getText().toString());
		pojo.setColony(editColonia_waste_disposal.getText().toString());
		pojo.setPostalCode(editCp_waste_disposal.getText().toString());
		pojo.setAddress(editCalle_waste_disposal.getText().toString());
		pojo.setStateName(editEstado_waste_disposal.getText().toString());

		pojo.setStartLongitude(Double.valueOf(editLongIni_waste.getText().toString()));
		pojo.setEndLongitude(Double.valueOf(editLongFin_waste.getText().toString()));
		pojo.setStartLatitude(Double.valueOf(editLatIni_waste.getText().toString()));
		pojo.setEndLatitude(Double.valueOf(editLatFin_waste.getText().toString()));
		pojo.setStartAltitude(Double.valueOf(editAltIni_waste.getText().toString()));
		pojo.setEndAltitude(Double.valueOf(editAltFin_waste.getText().toString()));

		pojo.setWasteDisposalType(listSpinnerTipo.get(spinnerTipo_waste_disposal.getSelectedItemPosition()).getId());
		pojo.setStructuralType(listSpinnerTipoEstruc.get(spinnerTipoEstruc_waste_disposal.getSelectedItemPosition()).getId());
		pojo.setWallsType(listSpinnerTipoMuros.get(spinnerMuros_waste_disposal.getSelectedItemPosition()).getId());
		pojo.setStructuralType(listSpinnerTipoCime.get(spinnerTipoCime_waste_disposal.getSelectedItemPosition()).getId());

		if (switchCamAcce_waste_disposal.isChecked()) {
			pojo.setAccessWays(true);
		}
		if (switchCamInt_waste_disposal.isChecked()) {
			pojo.setInnerWays(true);
		}
		if (switchCercaPeri_waste_disposal.isChecked()) {
			pojo.setPerimeterFence(true);
		}
		if (switchCaseVigi_waste_disposal.isChecked()) {
			pojo.setSecurityRoom(true);
		}
		if (switchBascula_waste_disposal.isChecked()) {
			pojo.setBascule(true);
		}
		if (switchAguaPot_waste_disposal.isChecked()) {
			pojo.setWaterElectricityDrain(true);
		}
		if (switchVestServ_waste_disposal.isChecked()) {
			pojo.setSanitaryServices(true);
		}
		if (switchOficinas_waste_disposal.isChecked()) {
			pojo.setOffices(true);
		}
		if (switchServMed_waste_disposal.isChecked()) {
			pojo.setMedicalServices(true);
		}
		if (switchFranja_waste_disposal.isChecked()) {
			pojo.setCushioningStrip(true);
		}

	}
}
