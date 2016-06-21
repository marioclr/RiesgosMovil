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
import com.mclr.mini.riesgosmovil.modelos.pojoWays;
import com.mclr.mini.riesgosmovil.utilitarios.AplicaDateChangedListener;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DateOperations;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

public class WaysFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCode;
	String postalCodeID; // Este se inicializara en la selección del CP
	// Acceso a GPS
	LocationManager locationManager;

	ImageView imagenBien;

	ImageButton buttonSaveUp1_2_ways;
	ImageButton buttonOutUp1_2_ways;
	ImageButton buttonSaveBot1_2_ways;
	ImageButton buttonOutBot1_2_ways;
	ImageButton buttonCP_ways;
	ImageButton buttonUbicacionIni_ways;
	ImageButton buttonUbicacionFin_ways;
	ImageButton buttonUbicacionIniCA_ways;
	ImageButton buttonUbicacionFinCA_ways;

	Spinner spinnerTipoCamino_ways;
	List<CatalogItem> listSpinnerTipoCamino;
	Spinner spinnerTipoRecu_ways;
	List<CatalogItem> listSpinnerTipoRecu;

	EditText editNombre_ways;
	EditText editNumCue_ways;
	EditText editTotalKm;
	EditText editValRepPuenTune_ways;
	EditText editAnVia_ways;
	EditText editAlcanta_ways;
	EditText editCp_ways;
	EditText editColonia_ways;
	EditText editMunicipio_ways;
	EditText editEstado_ways;
	EditText editLongIni_ways;
	EditText editLatIni_ways;
	EditText editAltIni_ways;
	EditText editLongFin_ways;
	EditText editLatFin_ways;
	EditText editAltFin_ways;
	EditText editLongIniCue_ways;
	EditText editLatIniCue_ways;
	EditText editAltIniCue_ways;
	EditText editLonFinCue_ways;
	EditText editLatFinCue_ways;
	EditText editAltFinCue_ways;
	EditText editDistancia_ways;
	EditText editNumPasosSub_ways;
	EditText editNumPasosSup_ways;
	EditText editImporte2013_ways;
	EditText editDef2013_ways;
	EditText editImporte2012_ways;
	EditText editDef2012_ways;
	EditText editImporte2011_ways;
	EditText editDef2011_ways;
	EditText editImporte2010_ways;
	EditText editDef2010_ways;
	EditText editImporte2009_ways;
	EditText editDef2009_ways;
	EditText editImporte2008_ways;
	EditText editDef2008_ways;
	EditText editImporte2007_ways;
	EditText editDef2007_ways;
	EditText editImporte2006_ways;
	EditText editDef2006_ways;
	EditText editImporte2005_ways;
	EditText editDef2005_ways;
	EditText editImporte2004_ways;
	EditText editDef2004_ways;
	
	EditText editFechaCons_ways;
	DatePicker dpFecha;
	
	RadioButton radioMar_ways;
	RadioButton radioLago_ways;
	RadioButton radioLaguna_ways;
	RadioButton radioRio_ways;
	RadioButton radioBuenoEC_ways;
	RadioButton radioRegularEC_ways;
	RadioButton radioMaloEC_ways;

	Switch switchCruces_ways;
	Switch switchExiPue_ways;
	Switch switchExiTun_ways;
	Switch switchImporte2013_ways;
	Switch switchImporte2012_ways;
	Switch switchImporte2011_ways;
	Switch switchImporte2010_ways;
	Switch switchImporte2009_ways;
	Switch switchImporte2008_ways;
	Switch switchImporte2007_ways;
	Switch switchImporte2006_ways;
	Switch switchImporte2005_ways;
	Switch switchImporte2004_ways;

	CheckBox checkboxInundacion_ways;
	CheckBox checkboxHuracan_ways;
	CheckBox checkboxTerremoto_ways;
	CheckBox checkboxDeslave_ways;
	CheckBox checkboxDerrumbe_ways;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_ways);

		buttonUbicacionIni_ways = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionIni_ways);
		buttonUbicacionIni_ways.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            MuestraPosicionIni(location);
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
		buttonUbicacionFin_ways = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionFin_ways);
		buttonUbicacionFin_ways.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            MuestraPosicionFin(location);
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
		buttonUbicacionIniCA_ways = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionIniCA_ways);
		buttonUbicacionIniCA_ways.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            MuestraPosicionIni(location);
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
		buttonUbicacionFinCA_ways = (ImageButton) myActivity.findViewById(R.id.buttonUbicacionFinCA_ways);
		buttonUbicacionFinCA_ways.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locationManager = (LocationManager) myActivity.getSystemService(Context.LOCATION_SERVICE);
				//Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					Toast.makeText(myActivity, "El GPS no está habilitado", Toast.LENGTH_LONG).show();
				} else {
					LocationListener locationListener = new LocationListener() {
				        public void onLocationChanged(Location location) {
				            MuestraPosicionFin(location);
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

		buttonSaveUp1_2_ways = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_ways);
		buttonSaveUp1_2_ways.setOnClickListener(new SaveClickListener());
		buttonOutUp1_2_ways = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_ways);
		buttonOutUp1_2_ways.setOnClickListener(new OutClickListener());

		buttonSaveBot1_2_ways = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_ways);
		buttonSaveBot1_2_ways.setOnClickListener(new SaveClickListener());
		buttonOutBot1_2_ways = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_ways);
		buttonOutBot1_2_ways.setOnClickListener(new OutClickListener());

		editNombre_ways = (EditText) myActivity.findViewById(R.id.editNombre_ways);

		buttonCP_ways = (ImageButton) myActivity.findViewById(R.id.buttonCP_ways);
		buttonCP_ways.setOnClickListener(new SearchClickListener(this));

		dpFecha = (DatePicker) myActivity.findViewById(R.id.pickerFechaConst_ways);

		spinnerTipoCamino_ways = (Spinner)myActivity.findViewById(R.id.spinnerTipoCamino_ways);
		listSpinnerTipoCamino = db.getCatalogList(Constants.WAY_TYPE);
		spinnerTipoCamino_ways.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCamino));

		spinnerTipoRecu_ways = (Spinner)myActivity.findViewById(R.id.spinnerTipoRecu_ways);
		listSpinnerTipoRecu = db.getCatalogList(Constants.COATING_TYPE);
		spinnerTipoRecu_ways.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoRecu));

		editNumCue_ways = (EditText)myActivity.findViewById(R.id.editNumCue_ways);
		//editNumCue_ways.setText("123");
		editTotalKm = (EditText)myActivity.findViewById(R.id.editTotalKm);
		//editTotalKm.setText("987654");
		editValRepPuenTune_ways = (EditText)myActivity.findViewById(R.id.editValRepPuenTune_ways);
		//editValRepPuenTune_ways.setText("745634712");
		editAnVia_ways = (EditText)myActivity.findViewById(R.id.editAnVia_ways);
		//editAnVia_ways.setText("84");
		editAlcanta_ways = (EditText)myActivity.findViewById(R.id.editAlcanta_ways);
		//editAlcanta_ways.setText("45");
		editCp_ways = (EditText)myActivity.findViewById(R.id.editCp_ways);
		editColonia_ways = (EditText)myActivity.findViewById(R.id.editColonia_ways);
		editMunicipio_ways = (EditText)myActivity.findViewById(R.id.editMunicipio_ways);
		editEstado_ways = (EditText)myActivity.findViewById(R.id.editEstado_ways);
		editLongIni_ways = (EditText)myActivity.findViewById(R.id.editLongIni_ways);
		//editLongIni_ways.setText("45782");
		editLatIni_ways = (EditText)myActivity.findViewById(R.id.editLatIni_ways);
		//editLatIni_ways.setText("9828");
		editAltIni_ways = (EditText)myActivity.findViewById(R.id.editAltIni_ways);
		//editAltIni_ways.setText("8239");
		editLongFin_ways = (EditText)myActivity.findViewById(R.id.editLongFin_ways);
		//editLongFin_ways.setText("72239");
		editLatFin_ways = (EditText)myActivity.findViewById(R.id.editLatFin_ways);
		//editLatFin_ways.setText("76261");
		editAltFin_ways = (EditText)myActivity.findViewById(R.id.editAltFin_ways);
		//editAltFin_ways.setText("516321");
		editLongIniCue_ways = (EditText)myActivity.findViewById(R.id.editLongIniCue_ways);
		//editLongIniCue_ways.setText("83123");
		editLatIniCue_ways = (EditText)myActivity.findViewById(R.id.editLatIniCue_ways);
		//editLatIniCue_ways.setText("98238");
		editAltIniCue_ways = (EditText)myActivity.findViewById(R.id.editAltIniCue_ways);
		//editAltIniCue_ways.setText("51241");
		editLonFinCue_ways = (EditText)myActivity.findViewById(R.id.editLonFinCue_ways);
		//editLonFinCue_ways.setText("32389");
		editLatFinCue_ways = (EditText)myActivity.findViewById(R.id.editLatFinCue_ways);
		//editLatFinCue_ways.setText("12929");
		editAltFinCue_ways = (EditText)myActivity.findViewById(R.id.editAltFinCue_ways);
		//editAltFinCue_ways.setText("283234");
		editDistancia_ways = (EditText)myActivity.findViewById(R.id.editDistancia_ways);
		editNumPasosSub_ways = (EditText)myActivity.findViewById(R.id.editNumPasosSub_ways);
		//editNumPasosSub_ways.setText("123");
		editNumPasosSup_ways = (EditText)myActivity.findViewById(R.id.editNumPasosSup_ways);
		//editNumPasosSup_ways.setText("321");
		editImporte2013_ways = (EditText) myActivity.findViewById(R.id.editImporte2013_ways);
		editDef2013_ways = (EditText) myActivity.findViewById(R.id.editDef2013_ways);
		editImporte2012_ways = (EditText) myActivity.findViewById(R.id.editImporte2012_ways);
		editDef2012_ways = (EditText) myActivity.findViewById(R.id.editDef2012_ways);
		editImporte2011_ways = (EditText) myActivity.findViewById(R.id.editImporte2011_ways);
		editDef2011_ways = (EditText) myActivity.findViewById(R.id.editDef2011_ways);
		editImporte2010_ways = (EditText) myActivity.findViewById(R.id.editImporte2010_ways);
		editDef2010_ways = (EditText) myActivity.findViewById(R.id.editDef2010_ways);
		editImporte2009_ways = (EditText) myActivity.findViewById(R.id.editImporte2009_ways);
		editDef2009_ways = (EditText) myActivity.findViewById(R.id.editDef2009_ways);
		editImporte2008_ways = (EditText) myActivity.findViewById(R.id.editImporte2008_ways);
		editDef2008_ways = (EditText) myActivity.findViewById(R.id.editDef2008_ways);
		editImporte2007_ways = (EditText) myActivity.findViewById(R.id.editImporte2007_ways);
		editDef2007_ways = (EditText) myActivity.findViewById(R.id.editDef2007_ways);
		editImporte2006_ways = (EditText) myActivity.findViewById(R.id.editImporte2006_ways);
		editDef2006_ways = (EditText) myActivity.findViewById(R.id.editDef2006_ways);
		editImporte2005_ways = (EditText) myActivity.findViewById(R.id.editImporte2005_ways);
		editDef2005_ways = (EditText) myActivity.findViewById(R.id.editDef2005_ways);
		editImporte2004_ways = (EditText) myActivity.findViewById(R.id.editImporte2004_ways);
		editDef2004_ways = (EditText) myActivity.findViewById(R.id.editDef2004_ways);
		editFechaCons_ways = (EditText) myActivity.findViewById(R.id.editFechaCons_ways);
		dpFecha  = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_ways);
		dpFecha.init(DateOperations.getCurrentYear(), DateOperations.getCurrentMonth(), DateOperations.getCurrentDay(), new AplicaDateChangedListener(editFechaCons_ways));

		radioMar_ways = (RadioButton)myActivity.findViewById(R.id.radioMar_ways);
		radioLago_ways = (RadioButton)myActivity.findViewById(R.id.radioLago_ways);
		radioLaguna_ways = (RadioButton)myActivity.findViewById(R.id.radioLaguna_ways);
		radioRio_ways = (RadioButton)myActivity.findViewById(R.id.radioRio_ways);
		radioBuenoEC_ways = (RadioButton)myActivity.findViewById(R.id.radioBuenoEC_ways);
		radioRegularEC_ways = (RadioButton)myActivity.findViewById(R.id.radioRegularEC_ways);
		radioMaloEC_ways = (RadioButton)myActivity.findViewById(R.id.radioMaloEC_ways);

		switchCruces_ways = (Switch)myActivity.findViewById(R.id.switchCruces_ways);
		switchImporte2013_ways = (Switch)myActivity.findViewById(R.id.switchImporte2013_ways);
		switchImporte2012_ways = (Switch)myActivity.findViewById(R.id.switchImporte2012_ways);
		switchImporte2011_ways = (Switch)myActivity.findViewById(R.id.switchImporte2011_ways);
		switchImporte2010_ways = (Switch)myActivity.findViewById(R.id.switchImporte2010_ways);
		switchImporte2009_ways = (Switch)myActivity.findViewById(R.id.switchImporte2009_ways);
		switchImporte2008_ways = (Switch)myActivity.findViewById(R.id.switchImporte2008_ways);
		switchImporte2007_ways = (Switch)myActivity.findViewById(R.id.switchImporte2007_ways);
		switchImporte2006_ways = (Switch)myActivity.findViewById(R.id.switchImporte2006_ways);
		switchImporte2005_ways = (Switch)myActivity.findViewById(R.id.switchImporte2005_ways);
		switchImporte2004_ways = (Switch)myActivity.findViewById(R.id.switchImporte2004_ways);

		checkboxInundacion_ways = (CheckBox)myActivity.findViewById(R.id.checkboxInundacion_ways);
		checkboxHuracan_ways = (CheckBox)myActivity.findViewById(R.id.checkboxHuracan_ways);
		checkboxTerremoto_ways = (CheckBox)myActivity.findViewById(R.id.checkboxTerremoto_ways);
		checkboxDeslave_ways = (CheckBox)myActivity.findViewById(R.id.checkboxDeslave_ways);
		checkboxDerrumbe_ways = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbe_ways);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoWays pojo = propierty.getWaysPptyDetail(propertyID, postalCode);
			editNombre_ways.setText(pojo.getName());
			spinnerTipoCamino_ways.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCamino, pojo.getWayType()));
			editNumCue_ways.setText(String.valueOf(pojo.getLinesNumber()));
			editTotalKm.setText(String.valueOf(pojo.getTotalKms()));
			editValRepPuenTune_ways.setText(String.valueOf(pojo.getRenewalWayValue()));
			editAnVia_ways.setText(String.valueOf(pojo.getWidthLine()));
			editAlcanta_ways.setText(String.valueOf(pojo.getSewers()));

			editMunicipio_ways.setText(pojo.getTown());
			editColonia_ways.setText(pojo.getColony());
			editCp_ways.setText(pojo.getPostalCode());
			editEstado_ways.setText(pojo.getStateName());

			editLongIni_ways.setText(String.valueOf(pojo.getStartLongitude()));
			editLongFin_ways.setText(String.valueOf(pojo.getEndLongitude()));
			editLatIni_ways.setText(String.valueOf(pojo.getStartLatitude()));

			editLatFin_ways.setText(String.valueOf(pojo.getEndLatitude()));
			editAltIni_ways.setText(String.valueOf(pojo.getStartAltitude()));
			editAltFin_ways.setText(String.valueOf(pojo.getEndAltitude()));

			if (pojo.getClosestWaterBodyType().compareTo(Constants.Mar) == 0) {
				radioMar_ways.setChecked(true);
			} else if (pojo.getClosestWaterBodyType().compareTo(Constants.Lago) == 0) {
				radioLago_ways.setChecked(true);
			} else if (pojo.getClosestWaterBodyType().compareTo(Constants.Laguna) == 0) {
				radioLaguna_ways.setChecked(true);
			} else if (pojo.getClosestWaterBodyType().compareTo(Constants.Rio) == 0) {
				radioRio_ways.setChecked(true);
			}

			//if (pojo.getClosestWaterBodyStartLongitude() != 0)
				editLongIniCue_ways.setText(String.valueOf(pojo.getClosestWaterBodyStartLongitude()));
			//if (pojo.getClosestWaterBodyStartLatitude() != 0)
				editLatIniCue_ways.setText(String.valueOf(pojo.getClosestWaterBodyStartLatitude()));
			//if (pojo.getClosestWaterBodyStartAltitude() != 0)
				editAltIniCue_ways.setText(String.valueOf(pojo.getClosestWaterBodyStartAltitude()));
			//if (pojo.getClosestWaterBodyEndLongitude() != 0)
				editLonFinCue_ways.setText(String.valueOf(pojo.getClosestWaterBodyEndLongitude()));
			//if (pojo.getClosestWaterBodyEndLatitude() != 0)
				editLatFinCue_ways.setText(String.valueOf(pojo.getClosestWaterBodyEndLatitude()));
			//if (pojo.getClosestWaterBodyEndAltitude() != 0)
				editAltFinCue_ways.setText(String.valueOf(pojo.getClosestWaterBodyEndAltitude()));

			switchCruces_ways.setChecked(pojo.isClosestWaterBodyCross());
			editDistancia_ways.setText(String.valueOf(pojo.getClosestWaterBodyDistanceCross()));
			try
			{
			dpFecha.init(Integer.valueOf(DateOperations.getYearOfDate(pojo.getConstructionDate())), 
					Integer.valueOf(DateOperations.getMonthOfDate(pojo.getConstructionDate()))-1,
					Integer.valueOf(DateOperations.getDayOfDate(pojo.getConstructionDate())), new AplicaDateChangedListener(editFechaCons_ways));
			}
			catch(Exception e)
			{
				
			}

			spinnerTipoRecu_ways.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoRecu, pojo.getCoverType()));
			editNumPasosSub_ways.setText(String.valueOf(pojo.getUnderWaysNumber()));
			editNumPasosSup_ways.setText(String.valueOf(pojo.getOverWaysNumber()));

			if (pojo.getCondition().compareTo(Constants.ConservationState_Good) == 0) {
				radioBuenoEC_ways.setChecked(true);
			} else if (pojo.getCondition().compareTo(Constants.ConservationState_Bad) == 0) {
				radioRegularEC_ways.setChecked(true);
			} else if (pojo.getCondition().compareTo(Constants.ConservationState_Poor) == 0) {
				radioMaloEC_ways.setChecked(true);
			}

			if (pojo.isFloodFormer()) {
				checkboxInundacion_ways.setChecked(true);
			}
			if (pojo.isHurricanFormer()) {
				checkboxHuracan_ways.setChecked(true);
			}
			if (pojo.isEarthQuakeFormer()) {
				checkboxTerremoto_ways.setChecked(true);
			}
			if (pojo.isSlidingFormer()) {
				checkboxDeslave_ways.setChecked(true);
			}
			if (pojo.isRockFallFormer()) {
				checkboxDerrumbe_ways.setChecked(true);
			}

			editImporte2013_ways.setText(pojo.getSiniestralityValues()[0].toString());
			editDef2013_ways.setText(pojo.getSiniestralityDescription()[0].toString());

			editImporte2012_ways.setText(pojo.getSiniestralityValues()[1].toString());
			editDef2012_ways.setText(pojo.getSiniestralityDescription()[1].toString());

			editImporte2011_ways.setText(pojo.getSiniestralityValues()[2].toString());
			editDef2011_ways.setText(pojo.getSiniestralityDescription()[2].toString());

			editImporte2010_ways.setText(pojo.getSiniestralityValues()[3].toString());
			editDef2010_ways.setText(pojo.getSiniestralityDescription()[3].toString());

			editImporte2009_ways.setText(pojo.getSiniestralityValues()[4].toString());
			editDef2009_ways.setText(pojo.getSiniestralityDescription()[4].toString());

			editImporte2008_ways.setText(pojo.getSiniestralityValues()[5].toString());
			editDef2008_ways.setText(pojo.getSiniestralityDescription()[5].toString());

			editImporte2007_ways.setText(pojo.getSiniestralityValues()[6].toString());
			editDef2007_ways.setText(pojo.getSiniestralityDescription()[6].toString());

			editImporte2006_ways.setText(pojo.getSiniestralityValues()[7].toString());
			editDef2006_ways.setText(pojo.getSiniestralityDescription()[7].toString());

			editImporte2005_ways.setText(pojo.getSiniestralityValues()[8].toString());
			editDef2005_ways.setText(pojo.getSiniestralityDescription()[8].toString());

			editImporte2004_ways.setText(pojo.getSiniestralityValues()[9].toString());
			editDef2004_ways.setText(pojo.getSiniestralityDescription()[9].toString());

			if (pojo.getFinding()==1)
				imagenBien.setImageResource(R.mipmap.ways_blue);
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
		View rootView = inflater.inflate(R.layout.ways, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	public void CerrarBien()
	{
		buttonSaveUp1_2_ways.setEnabled(false);
		//buttonOutUp1_2_ways.setEnabled(false);
		buttonSaveBot1_2_ways.setEnabled(false);
		//buttonOutBot1_2_ways.setEnabled(false);
		buttonCP_ways.setEnabled(false);
		buttonUbicacionIni_ways.setEnabled(false);
		buttonUbicacionFin_ways.setEnabled(false);
		buttonUbicacionIniCA_ways.setEnabled(false);
		buttonUbicacionFinCA_ways.setEnabled(false);

		spinnerTipoCamino_ways.setEnabled(false);
		spinnerTipoRecu_ways.setEnabled(false);

		editNombre_ways.setEnabled(false);
		editNumCue_ways.setEnabled(false);
		editTotalKm.setEnabled(false);
		editValRepPuenTune_ways.setEnabled(false);
		editAnVia_ways.setEnabled(false);
		editAlcanta_ways.setEnabled(false);
		editCp_ways.setEnabled(false);
		editColonia_ways.setEnabled(false);
		editMunicipio_ways.setEnabled(false);
		editEstado_ways.setEnabled(false);
		editLongIni_ways.setEnabled(false);
		editLatIni_ways.setEnabled(false);
		editAltIni_ways.setEnabled(false);
		editLongFin_ways.setEnabled(false);
		editLatFin_ways.setEnabled(false);
		editAltFin_ways.setEnabled(false);
		editLongIniCue_ways.setEnabled(false);
		editLatIniCue_ways.setEnabled(false);
		editAltIniCue_ways.setEnabled(false);
		editLonFinCue_ways.setEnabled(false);
		editLatFinCue_ways.setEnabled(false);
		editAltFinCue_ways.setEnabled(false);
		editDistancia_ways.setEnabled(false);
		editNumPasosSub_ways.setEnabled(false);
		editNumPasosSup_ways.setEnabled(false);
		editImporte2013_ways.setEnabled(false);
		editDef2013_ways.setEnabled(false);
		editImporte2012_ways.setEnabled(false);
		editDef2012_ways.setEnabled(false);
		editImporte2011_ways.setEnabled(false);
		editDef2011_ways.setEnabled(false);
		editImporte2010_ways.setEnabled(false);
		editDef2010_ways.setEnabled(false);
		editImporte2009_ways.setEnabled(false);
		editDef2009_ways.setEnabled(false);
		editImporte2008_ways.setEnabled(false);
		editDef2008_ways.setEnabled(false);
		editImporte2007_ways.setEnabled(false);
		editDef2007_ways.setEnabled(false);
		editImporte2006_ways.setEnabled(false);
		editDef2006_ways.setEnabled(false);
		editImporte2005_ways.setEnabled(false);
		editDef2005_ways.setEnabled(false);
		editImporte2004_ways.setEnabled(false);
		editDef2004_ways.setEnabled(false);

		editFechaCons_ways.setEnabled(false);
		dpFecha.setEnabled(false);

		radioMar_ways.setEnabled(false);
		radioLago_ways.setEnabled(false);
		radioLaguna_ways.setEnabled(false);
		radioRio_ways.setEnabled(false);
		radioBuenoEC_ways.setEnabled(false);
		radioRegularEC_ways.setEnabled(false);
		radioMaloEC_ways.setEnabled(false);

		checkboxInundacion_ways.setEnabled(false);
		checkboxHuracan_ways.setEnabled(false);
		checkboxTerremoto_ways.setEnabled(false);
		checkboxDeslave_ways.setEnabled(false);
		checkboxDerrumbe_ways.setEnabled(false);

		buttonSaveUp1_2_ways.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp1_2_ways.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot1_2_ways.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot1_2_ways.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_ways.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacionIni_ways.setImageResource(R.mipmap.ubicacion_inicial_blocked);
		buttonUbicacionFin_ways.setImageResource(R.mipmap.ubicacion_final_blocked);
		buttonUbicacionIniCA_ways.setImageResource(R.mipmap.cuerpo_inicial_blocked);
		buttonUbicacionFinCA_ways.setImageResource(R.mipmap.cuerpo_final_blocked);
	}

	private class SaveClickListener implements OnClickListener {
		pojoWays pojo;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojo = new pojoWays();

			if (validaCampos()) {
				pojo.setPropertyID(myActivity.propertyID);
				SetPojoWaysP1(pojo);
//				WaysFragment2 ways2 = (WaysFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
//				ways2.SetPojoWaysP2(pojo);				

				try
				{
					process.processWaysSave(pojo);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojo.getPropertyID();
					myActivity.postalCodeID = pojo.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	public boolean validaCampos(){
		if (editNombre_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el nombre correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editNombre_ways.requestFocus();
			return false;
		}
		if(spinnerTipoCamino_ways.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de camino", Toast.LENGTH_LONG).show();
			spinnerTipoCamino_ways.requestFocus();
			return false;
		}
		if (editNumCue_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de cuerpos", Toast.LENGTH_LONG).show();
			editNumCue_ways.requestFocus();
			return false;
		}
		if (editTotalKm.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el total de kilómetros", Toast.LENGTH_LONG).show();
			editTotalKm.requestFocus();
			return false;
		}
		if (editAnVia_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar ancho de vía", Toast.LENGTH_LONG).show();
			editAnVia_ways.requestFocus();
			return false;
		}
		if (editCp_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editCp_ways.requestFocus();
			return false;
		}
		if (editColonia_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editColonia_ways.requestFocus();
			return false;
		}
		if (editMunicipio_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editMunicipio_ways.requestFocus();
			return false;
		}
		if (editEstado_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editEstado_ways.requestFocus();
			return false;
		}
		if (editLongIni_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongIni_ways.requestFocus();
			return false;
		}
		if (editLongFin_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLongFin_ways.requestFocus();
			return false;
		}
		if (editLatIni_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatIni_ways.requestFocus();
			return false;
		}
		if (editLatFin_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Latitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editLatFin_ways.requestFocus();
			return false;
		}
		if (editAltIni_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud inicial correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltIni_ways.requestFocus();
			return false;
		}
		if (editAltFin_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la Altitud final correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
			editAltFin_ways.requestFocus();
			return false;
		}
		if (editFechaCons_ways.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la fecha de construcción del bien", Toast.LENGTH_LONG).show();
			editFechaCons_ways.requestFocus();
			return false;
		}
		if(spinnerTipoRecu_ways.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de recubrimiento", Toast.LENGTH_LONG).show();
			spinnerTipoRecu_ways.requestFocus();
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
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_ways.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_ways.getText().toString());
			args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_ways.getText().toString());
			//selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.DAM);
            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.WAYS);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
			selectCP.show(myActivity.getSupportFragmentManager(), "CP");
		}
	}

	public void SetPojoWaysP1(pojoWays pojo)
	{
		pojo.setPostalCodeID(postalCodeID);
		pojo.setName(editNombre_ways.getText().toString());
		pojo.setWayType(listSpinnerTipoCamino.get(spinnerTipoCamino_ways.getSelectedItemPosition()).getId());

		pojo.setLinesNumber(Integer.valueOf(editNumCue_ways.getText().toString()));
		pojo.setTotalKms(Double.valueOf(editTotalKm.getText().toString()));
		pojo.setRenewalWayValue(editValRepPuenTune_ways.getText().toString());
		pojo.setWidthLine(Double.valueOf(editAnVia_ways.getText().toString()));
		pojo.setSewers(Integer.valueOf(editAlcanta_ways.getText().toString()));

		pojo.setTown(editMunicipio_ways.getText().toString());
		pojo.setColony(editColonia_ways.getText().toString());
		pojo.setPostalCode(editCp_ways.getText().toString());
		pojo.setStateName(editEstado_ways.getText().toString());

		pojo.setConstructionDate(editFechaCons_ways.getText().toString());	

		pojo.setStartLongitude(Double.valueOf(editLongIni_ways.getText().toString()));
		pojo.setEndLongitude(Double.valueOf(editLongFin_ways.getText().toString()));
		pojo.setStartLatitude(Double.valueOf(editLatIni_ways.getText().toString()));
		pojo.setEndLatitude(Double.valueOf(editLatFin_ways.getText().toString()));
		pojo.setStartAltitude(Double.valueOf(editAltIni_ways.getText().toString()));
		pojo.setEndAltitude(Double.valueOf(editAltFin_ways.getText().toString()));

		if (radioMar_ways.isChecked())
			pojo.setClosestWaterBodyType(Constants.Mar);
		else if (radioLago_ways.isChecked())
			pojo.setClosestWaterBodyType(Constants.Lago);
		else if (radioLaguna_ways.isChecked())
			pojo.setClosestWaterBodyType(Constants.Laguna);
		else if (radioRio_ways.isChecked())
			pojo.setClosestWaterBodyType(Constants.Rio);

		if (editLongIniCue_ways.getText().toString().length() > 0)
			pojo.setClosestWaterBodyStartLongitude(Double.valueOf(editLongIniCue_ways.getText().toString()));
		if (editLatIniCue_ways.getText().toString().length() > 0)
			pojo.setClosestWaterBodyStartLatitude(Double.valueOf(editLatIniCue_ways.getText().toString()));
		if (editAltIniCue_ways.getText().toString().length() > 0)
			pojo.setClosestWaterBodyStartAltitude(Double.valueOf(editAltIniCue_ways.getText().toString()));
		if (editLonFinCue_ways.getText().toString().length() > 0)
			pojo.setClosestWaterBodyEndLongitude(Double.valueOf(editLonFinCue_ways.getText().toString()));
		if (editLatFinCue_ways.getText().toString().length() > 0)
			pojo.setClosestWaterBodyEndLatitude(Double.valueOf(editLatFinCue_ways.getText().toString()));
		if (editAltFinCue_ways.getText().toString().length() > 0)
			pojo.setClosestWaterBodyEndAltitude(Double.valueOf(editAltFinCue_ways.getText().toString()));

		if (switchCruces_ways.isChecked()) {
			pojo.setClosestWaterBodyCross(true);
		}

		if (editDistancia_ways.getText().length() > 0 && editDistancia_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.setClosestWaterBodyDistanceCross(Double.valueOf(editDistancia_ways.getText().toString()));
		}

		//dpFecha.init(2013, 11, 20, null);
		pojo.setConstructionDate(editFechaCons_ways.getText().toString());

		pojo.setCoverType(listSpinnerTipoRecu.get(spinnerTipoRecu_ways.getSelectedItemPosition()).getId());
		pojo.setUnderWaysNumber(Double.valueOf(editNumPasosSub_ways.getText().toString()));
		pojo.setOverWaysNumber(Double.valueOf(editNumPasosSup_ways.getText().toString()));

		if (radioBuenoEC_ways.isChecked()) {
			pojo.setCondition(Constants.ConservationState_Good);
		} else if (radioRegularEC_ways.isChecked()) {
			pojo.setCondition(Constants.ConservationState_Bad);
		} else if (radioMaloEC_ways.isChecked()) {
			pojo.setCondition(Constants.ConservationState_Poor);
		}

		if (checkboxInundacion_ways.isChecked()) {
			pojo.setFloodFormer(true);
		}
		if (checkboxHuracan_ways.isChecked()) {
			pojo.setHurricanFormer(true);
		}
		if (checkboxTerremoto_ways.isChecked()) {
			pojo.setEarthQuakeFormer(true);
		}
		if (checkboxDeslave_ways.isChecked()) {
			pojo.setSlidingFormer(true);
		}
		if (checkboxDerrumbe_ways.isChecked()) {
			pojo.setRockFallFormer(true);
		}

		if (editImporte2013_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[0] = editImporte2013_ways.getText().toString();
			pojo.getSiniestralityDescription()[0] = editDef2013_ways.getText().toString();
		}

		if (editImporte2012_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[1] = editImporte2012_ways.getText().toString();
			pojo.getSiniestralityDescription()[1] = editDef2012_ways.getText().toString();
		}

		if (editImporte2011_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[2] = editImporte2011_ways.getText().toString();
			pojo.getSiniestralityDescription()[2] = editDef2011_ways.getText().toString();
		}

		if (editImporte2010_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[3] = editImporte2010_ways.getText().toString();
			pojo.getSiniestralityDescription()[3] = editDef2010_ways.getText().toString();
		}

		if (editImporte2009_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[4] = editImporte2009_ways.getText().toString();
			pojo.getSiniestralityDescription()[4] = editDef2009_ways.getText().toString();
		}

		if (editImporte2008_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[5] = editImporte2008_ways.getText().toString();
			pojo.getSiniestralityDescription()[5] = editDef2008_ways.getText().toString();
		}

		if (editImporte2007_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[6] = editImporte2007_ways.getText().toString();
			pojo.getSiniestralityDescription()[6] = editDef2007_ways.getText().toString();
		}

		if (editImporte2006_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[7] = editImporte2006_ways.getText().toString();
			pojo.getSiniestralityDescription()[7] = editDef2006_ways.getText().toString();
		}

		if (editImporte2005_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[8] = editImporte2005_ways.getText().toString();
			pojo.getSiniestralityDescription()[8] = editDef2005_ways.getText().toString();
		}

		if (editImporte2004_ways.getText().length() > 0 && editImporte2013_ways.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValues()[9] = editImporte2004_ways.getText().toString();
			pojo.getSiniestralityDescription()[9] = editDef2004_ways.getText().toString();
		}

	}

	private void MuestraPosicionIni(Location loc) {
	    if(loc != null)
	    {
	    	editLatIni_ways.setText(String.valueOf(loc.getLatitude()));
	    	editLongIni_ways.setText(String.valueOf(loc.getLongitude()));
	        editAltIni_ways.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatIni_ways.setText("(sin_datos)");
	    	editLongIni_ways.setText("(sin_datos)");
	    	editAltIni_ways.setText("(sin_datos)");
	    }
	}

	private void MuestraPosicionFin(Location loc) {
	    if(loc != null)
	    {
	    	editLatFin_ways.setText(String.valueOf(loc.getLatitude()));
	    	editLongFin_ways.setText(String.valueOf(loc.getLongitude()));
	    	editAltFin_ways.setText(String.valueOf(loc.getAltitude()));
//	        Log.i("LocAndroid", String.valueOf(
//	            loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
	    }
	    else
	    {
	    	editLatFin_ways.setText("(sin_datos)");
	    	editLongFin_ways.setText("(sin_datos)");
	    	editAltFin_ways.setText("(sin_datos)");
	    }
	}

	public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
		editColonia_ways.setText(colonia);
		editMunicipio_ways.setText(municipio);
		editEstado_ways.setText(estado);
		editCp_ways.setText(CP);
		postalCodeID = p_postalCodeID;

		selectCP.dismiss();
		Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
	}
}
