package com.mclr.mini.riesgosmovil.fragmentos;

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
import com.mclr.mini.riesgosmovil.modelos.pojoAquaculture;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

/**
 * Created by mini on 05/06/16.
 */
public class AquacultureFragment extends Fragment {
    private MainActivity myActivity = null;
    VWProperties propierty;
    CodigoPostalDialogFragment selectCP;
    String propertyID;
    String postalCodeID; // Este se inicializara en la selección del CP
    LocationManager locationManager;

    ImageView imagenBien;

    ImageButton buttonSaveUp1_2_aquaculture;
    ImageButton buttonOutUp1_2_aquaculture;
    ImageButton buttonCP_aquaculture;
    ImageButton buttonUbicacion_aquaculture;
    ImageButton buttonSaveBot1_2_aquaculture;
    ImageButton buttonOutBot1_2_aquaculture;

    EditText editNombre_aquaculture;
    EditText editCp_aquaculture;
    EditText editCalle_aquaculture;
    EditText editColonia_aquaculture;
    EditText editMunicipio_aquaculture;
    EditText editEstado_aquaculture;
    EditText editLongitud_aquaculture;
    EditText editLatitud_aquaculture;
    EditText editAltitud_aquaculture;
    EditText editInstaEspe_aquaculture;
    EditText editImporte2013_aquaculture;
    EditText editDef2013_aquaculture;
    EditText editImporte2012_aquaculture;
    EditText editDef2012_aquaculture;
    EditText editImporte2011_aquaculture;
    EditText editDef2011_aquaculture;
    EditText editImporte2010_aquaculture;
    EditText editDef2010_aquaculture;
    EditText editImporte2009_aquaculture;
    EditText editDef2009_aquaculture;
    EditText editImporte2008_aquaculture;
    EditText editDef2008_aquaculture;
    EditText editImporte2007_aquaculture;
    EditText editDef2007_aquaculture;
    EditText editImporte2006_aquaculture;
    EditText editDef2006_aquaculture;
    EditText editImporte2005_aquaculture;
    EditText editDef2005_aquaculture;
    EditText editImporte2004_aquaculture;
    EditText editDef2004_aquaculture;

    Spinner spinnerTipoCenAcu_aquaculture;
    List<CatalogItem> listSpinnerTipoCenAcu;
    Spinner spinnerTipoEstru_aquaculture;
    List<CatalogItem> listSpinnerTipoEstru;
    Spinner spinnerTipoCime_aquaculture;
    List<CatalogItem> listSpinnerTipoCime;
    Spinner spinnerTipoHerre_aquaculture;
    List<CatalogItem> listSpinnerTipoHerre;
    Spinner spinnerTipoVidri_aquaculture;
    List<CatalogItem> listSpinnerTipoVidri;
    Spinner spinnerTipoFacha_aquaculture;
    List<CatalogItem> listSpinnerTipoFacha;
    Spinner spinnerMuros_aquaculture;
    List<CatalogItem> listSpinnerMuros;

    Switch switchImporte2013_aquaculture;
    Switch switchImporte2012_aquaculture;
    Switch switchImporte2011_aquaculture;
    Switch switchImporte2010_aquaculture;
    Switch switchImporte2009_aquaculture;
    Switch switchImporte2008_aquaculture;
    Switch switchImporte2007_aquaculture;
    Switch switchImporte2006_aquaculture;
    Switch switchImporte2005_aquaculture;
    Switch switchImporte2004_aquaculture;

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
        View rootView = inflater.inflate(R.layout.aquaculture, container, false);
        //myActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        DatabaseHandler db = new DatabaseHandler(myActivity);

        imagenBien = (ImageView) myActivity.findViewById(R.id.imageBien_aquaculture);
        buttonUbicacion_aquaculture = (ImageButton) myActivity.findViewById(R.id.buttonUbicacion_aquaculture);
        buttonUbicacion_aquaculture.setOnClickListener(new OnClickListener() {
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
        buttonSaveUp1_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp1_2_aquaculture);
        buttonSaveUp1_2_aquaculture.setOnClickListener(new SaveClickListener());
        buttonOutUp1_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonOutUp1_2_aquaculture);
        buttonOutUp1_2_aquaculture.setOnClickListener(new OutClickListener());

        buttonSaveBot1_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot1_2_aquaculture);
        buttonSaveBot1_2_aquaculture.setOnClickListener(new SaveClickListener());
        buttonOutBot1_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonOutBot1_2_aquaculture);
        buttonOutBot1_2_aquaculture.setOnClickListener(new OutClickListener());

        buttonCP_aquaculture = (ImageButton) myActivity.findViewById(R.id.buttonCP_aquaculture);
        buttonCP_aquaculture.setOnClickListener(new SearchClickListener(this));

        editNombre_aquaculture = (EditText) myActivity.findViewById(R.id.editNombre_aquaculture);
        editNombre_aquaculture.setText("Aqua");
        editCp_aquaculture = (EditText) myActivity.findViewById(R.id.editCp_aquaculture);
        editCalle_aquaculture = (EditText) myActivity.findViewById(R.id.editCalle_aquaculture);
        editCalle_aquaculture.setText("Oriente 166 338");
        editColonia_aquaculture = (EditText) myActivity.findViewById(R.id.editColonia_aquaculture);
        editMunicipio_aquaculture = (EditText) myActivity.findViewById(R.id.editMunicipio_aquaculture);
        editEstado_aquaculture = (EditText) myActivity.findViewById(R.id.editEstado_aquaculture);
        editLongitud_aquaculture = (EditText) myActivity.findViewById(R.id.editLongitud_aquaculture);
        editLongitud_aquaculture.setText("1111");
        editLatitud_aquaculture = (EditText) myActivity.findViewById(R.id.editLatitud_aquaculture);
        editLatitud_aquaculture.setText("2222");
        editAltitud_aquaculture = (EditText) myActivity.findViewById(R.id.editAltitud_aquaculture);
        editAltitud_aquaculture.setText("3333");
        editInstaEspe_aquaculture = (EditText) myActivity.findViewById(R.id.editInstaEspe_aquaculture);
        editImporte2013_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2013_aquaculture);
        editDef2013_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2013_aquaculture);
        editImporte2012_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2012_aquaculture);
        editDef2012_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2012_aquaculture);
        editImporte2011_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2011_aquaculture);
        editDef2011_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2011_aquaculture);
        editImporte2010_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2010_aquaculture);
        editDef2010_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2010_aquaculture);
        editImporte2009_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2009_aquaculture);
        editDef2009_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2009_aquaculture);
        editImporte2008_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2008_aquaculture);
        editDef2008_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2008_aquaculture);
        editImporte2007_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2007_aquaculture);
        editDef2007_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2007_aquaculture);
        editImporte2006_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2006_aquaculture);
        editDef2006_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2006_aquaculture);
        editImporte2005_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2005_aquaculture);
        editDef2005_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2005_aquaculture);
        editImporte2004_aquaculture = (EditText) myActivity.findViewById(R.id.editImporte2004_aquaculture);
        editDef2004_aquaculture = (EditText) myActivity.findViewById(R.id.editDef2004_aquaculture);

        switchImporte2013_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2013_aquaculture);
        switchImporte2012_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2012_aquaculture);
        switchImporte2011_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2011_aquaculture);
        switchImporte2010_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2010_aquaculture);
        switchImporte2009_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2009_aquaculture);
        switchImporte2008_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2008_aquaculture);
        switchImporte2007_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2007_aquaculture);
        switchImporte2006_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2006_aquaculture);
        switchImporte2005_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2005_aquaculture);
        switchImporte2004_aquaculture = (Switch) myActivity.findViewById(R.id.switchImporte2004_aquaculture);

        spinnerTipoCenAcu_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerTipoCenAcu_aquaculture);
        listSpinnerTipoCenAcu = db.getCatalogList(Constants.AQUACULTURE_CENTER_TYPE);
        spinnerTipoCenAcu_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCenAcu));

        spinnerTipoEstru_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerTipoEstru_aquaculture);
        listSpinnerTipoEstru = db.getCatalogList(Constants.STRUCTURE_TYPE);
        spinnerTipoEstru_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoEstru));

        spinnerTipoCime_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerTipoCime_aquaculture);
        listSpinnerTipoCime = db.getCatalogList(Constants.FOUNDATION_TYPE);
        spinnerTipoCime_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCime));

        spinnerTipoHerre_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerTipoHerre_aquaculture);
        listSpinnerTipoHerre = db.getCatalogList(Constants.BLACKSMITH_TYPE);
        spinnerTipoHerre_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoHerre));

        spinnerTipoVidri_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerTipoVidri_aquaculture);
        listSpinnerTipoVidri = db.getCatalogList(Constants.GLASSWARE_TYPE);
        spinnerTipoVidri_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoVidri));

        spinnerTipoFacha_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerTipoFacha_aquaculture);
        listSpinnerTipoFacha = db.getCatalogList(Constants.FACHADE_TYPE);
        spinnerTipoFacha_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoFacha));

        spinnerMuros_aquaculture = (Spinner)myActivity.findViewById(R.id.spinnerMuros_aquaculture);
        listSpinnerMuros = db.getCatalogList(Constants.WALL_TYPE);
        spinnerMuros_aquaculture.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerMuros));

        propierty = new VWProperties(myActivity);
        if (!propertyID.equals(Constants.GENERICO)){
            pojoAquaculture pojo = propierty.getAquaculturalPptyDetail(propertyID, postalCodeID);
            editNombre_aquaculture.setText(pojo.getBuildingName());
            spinnerTipoCenAcu_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCenAcu, pojo.getCenterType()));
            editMunicipio_aquaculture.setText(pojo.getTown());
            editColonia_aquaculture.setText(pojo.getColony());
            editCp_aquaculture.setText(pojo.getPostalCode());
            editCalle_aquaculture.setText(pojo.getAddress());
            editEstado_aquaculture.setText(pojo.getStateName());
            editLongitud_aquaculture.setText(String.valueOf(pojo.getLongitude()));
            editLatitud_aquaculture.setText(String.valueOf(pojo.getLatitude()));
            editAltitud_aquaculture.setText(String.valueOf(pojo.getAltitude()));

            spinnerTipoCenAcu_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCenAcu, pojo.getCenterType()));
            spinnerTipoEstru_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoEstru, pojo.getStructureType()));
            spinnerTipoCime_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCime, pojo.getFoundationType()));
            spinnerTipoHerre_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoHerre, pojo.getBlackSmithType()));
            spinnerTipoVidri_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoVidri, pojo.getGlasseryType()));
            spinnerTipoFacha_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoFacha, pojo.getFachadeType()));
            spinnerMuros_aquaculture.setSelection(ListOperations.getItemPositionByID(listSpinnerMuros, pojo.getWallsType()));
            editInstaEspe_aquaculture.setText(String.valueOf(pojo.getSpecialFacilities()));

            editImporte2013_aquaculture.setText(pojo.getSiniestrality()[0].toString());
            editDef2013_aquaculture.setText(pojo.getSiniestralityDesc()[0].toString());

            editImporte2012_aquaculture.setText(pojo.getSiniestrality()[1].toString());
            editDef2012_aquaculture.setText(pojo.getSiniestralityDesc()[1].toString());

            editImporte2011_aquaculture.setText(pojo.getSiniestrality()[2].toString());
            editDef2011_aquaculture.setText(pojo.getSiniestralityDesc()[2].toString());

            editImporte2010_aquaculture.setText(pojo.getSiniestrality()[3].toString());
            editDef2010_aquaculture.setText(pojo.getSiniestralityDesc()[3].toString());

            editImporte2009_aquaculture.setText(pojo.getSiniestrality()[4].toString());
            editDef2009_aquaculture.setText(pojo.getSiniestralityDesc()[4].toString());

            editImporte2008_aquaculture.setText(pojo.getSiniestrality()[5].toString());
            editDef2008_aquaculture.setText(pojo.getSiniestralityDesc()[5].toString());

            editImporte2007_aquaculture.setText(pojo.getSiniestrality()[6].toString());
            editDef2007_aquaculture.setText(pojo.getSiniestralityDesc()[6].toString());

            editImporte2006_aquaculture.setText(pojo.getSiniestrality()[7].toString());
            editDef2006_aquaculture.setText(pojo.getSiniestralityDesc()[7].toString());

            editImporte2005_aquaculture.setText(pojo.getSiniestrality()[8].toString());
            editDef2005_aquaculture.setText(pojo.getSiniestralityDesc()[8].toString());

            editImporte2004_aquaculture.setText(pojo.getSiniestrality()[9].toString());
            editDef2004_aquaculture.setText(pojo.getSiniestralityDesc()[9].toString());

            if (pojo.getFinding()==1)
                imagenBien.setImageResource(R.mipmap.aquaculture_blue);
            if (pojo.getStatus()!=0)
                CerrarBien();
        }
        super.onActivityCreated(savedInstanceState);
    }

    private class SaveClickListener implements OnClickListener {
        pojoAquaculture pojoAqua;
        ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

        @Override
        public void onClick(View v) {
            pojoAqua = new pojoAquaculture();
            if (validaCampos()) {
                pojoAqua.setPropertyID(myActivity.propertyID);
                SetPojoAquacultureP1(pojoAqua);
                AquacultureFragment2 aqua2 = (AquacultureFragment2)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(1);
                aqua2.SetPojoAquacultureP2(pojoAqua);

                try
                {
                    process.processAquacultureSave(pojoAqua);
                    Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
                }
                finally
                {
                    myActivity.propertyID = pojoAqua.getPropertyID();
                    myActivity.postalCodeID = pojoAqua.getPostalCodeID();
                }
            } else {
                DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
                alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
            }
        }
    }

    public void CerrarBien()
    {
        buttonSaveUp1_2_aquaculture.setEnabled(false);
        //buttonOutUp1_2_aquaculture.setEnabled(false);
        buttonCP_aquaculture.setEnabled(false);
        buttonUbicacion_aquaculture.setEnabled(false);
        buttonSaveBot1_2_aquaculture.setEnabled(false);
        //buttonOutBot1_2_aquaculture.setEnabled(false);

        editNombre_aquaculture.setEnabled(false);
        editCp_aquaculture.setEnabled(false);
        editCalle_aquaculture.setEnabled(false);
        editColonia_aquaculture.setEnabled(false);
        editMunicipio_aquaculture.setEnabled(false);
        editEstado_aquaculture.setEnabled(false);
        editLongitud_aquaculture.setEnabled(false);
        editLatitud_aquaculture.setEnabled(false);
        editAltitud_aquaculture.setEnabled(false);
        editInstaEspe_aquaculture.setEnabled(false);
        editImporte2013_aquaculture.setEnabled(false);
        editDef2013_aquaculture.setEnabled(false);
        editImporte2012_aquaculture.setEnabled(false);
        editDef2012_aquaculture.setEnabled(false);
        editImporte2011_aquaculture.setEnabled(false);
        editDef2011_aquaculture.setEnabled(false);
        editImporte2010_aquaculture.setEnabled(false);
        editDef2010_aquaculture.setEnabled(false);
        editImporte2009_aquaculture.setEnabled(false);
        editDef2009_aquaculture.setEnabled(false);
        editImporte2008_aquaculture.setEnabled(false);
        editDef2008_aquaculture.setEnabled(false);
        editImporte2007_aquaculture.setEnabled(false);
        editDef2007_aquaculture.setEnabled(false);
        editImporte2006_aquaculture.setEnabled(false);
        editDef2006_aquaculture.setEnabled(false);
        editImporte2005_aquaculture.setEnabled(false);
        editDef2005_aquaculture.setEnabled(false);
        editImporte2004_aquaculture.setEnabled(false);
        editDef2004_aquaculture.setEnabled(false);

        spinnerTipoCenAcu_aquaculture.setEnabled(false);
        spinnerTipoEstru_aquaculture.setEnabled(false);
        spinnerTipoCime_aquaculture.setEnabled(false);
        spinnerTipoHerre_aquaculture.setEnabled(false);
        spinnerTipoVidri_aquaculture.setEnabled(false);
        spinnerTipoFacha_aquaculture.setEnabled(false);
        spinnerMuros_aquaculture.setEnabled(false);

        buttonSaveUp1_2_aquaculture.setImageResource(R.mipmap.guardar_blocked);
        buttonOutUp1_2_aquaculture.setImageResource(R.mipmap.salir_blocked);
        //buttonCP_aquaculture.setImageResource(R.mipmap.aquaculture_blue); //TODO
        buttonUbicacion_aquaculture.setImageResource(R.mipmap.ubicacion_blocked);
        buttonSaveBot1_2_aquaculture.setImageResource(R.mipmap.guardar_blocked);
        buttonOutBot1_2_aquaculture.setImageResource(R.mipmap.salir_blocked);

    }

    public boolean validaCampos(){

        if(spinnerTipoCenAcu_aquaculture.getSelectedItemPosition()==0){
            Toast.makeText(myActivity, "Se requiere indicar tipo de centro acuícola", Toast.LENGTH_LONG).show();
            spinnerTipoCenAcu_aquaculture.requestFocus();
            return false;
        }
        if (editNombre_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se requiere indicar el nombre del bien", Toast.LENGTH_LONG).show();
            editNombre_aquaculture.requestFocus();
            return false;
        }
        if (editCp_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar el C.P. correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editCp_aquaculture.requestFocus();
            return false;
        }
        if (editCalle_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar la calle correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editCalle_aquaculture.requestFocus();
            return false;
        }
        if (editColonia_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar la colonia correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editColonia_aquaculture.requestFocus();
            return false;
        }
        if (editMunicipio_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar el municipio correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editMunicipio_aquaculture.requestFocus();
            return false;
        }
        if (editEstado_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar el estado correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editEstado_aquaculture.requestFocus();
            return false;
        }
        if (editLongitud_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar la Longitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editLongitud_aquaculture.requestFocus();
            return false;
        }
        if (editLatitud_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar la Latitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editLatitud_aquaculture.requestFocus();
            return false;
        }
        if (editAltitud_aquaculture.getText().length()==0) {
            Toast.makeText(myActivity, "Se debe indicar la Altitud correspondiente a la ubicación del bien", Toast.LENGTH_LONG).show();
            editAltitud_aquaculture.requestFocus();
            return false;
        }
        return true;
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
            args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY, editMunicipio_aquaculture.getText().toString());
            args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN, editColonia_aquaculture.getText().toString());
            args.putString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST, editCp_aquaculture.getText().toString());
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);

            args.putString("message", "Seleccione el Código Postal");
            args.putString("title", "Codigo Postal");
            args.putString("propertyType", Constants.AQUACULTURE);
            //selectCP = new CodigoPostalDialogFragment(myActivity, fragment, "Seleccione el Código Postal", "Codigo Postal", Constants.AQUACULTURE);
            selectCP = new CodigoPostalDialogFragment();
            selectCP.setArguments(args);
            selectCP.show(myActivity.getSupportFragmentManager(), "CP");
        }
    }

    public void AnalizaID()
    {
        if (!myActivity.propertyID.equals(Constants.GENERICO))
        {
            Toast.makeText(myActivity, "Tienes un ID, puedes continuar...", Toast.LENGTH_LONG).show();
        }
        else
        {
            //buttonSaveUp2_2_historical.setEnabled(false);
            Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
        }
    }

    private void MuestraPosicion(Location loc) {
        if(loc != null)
        {
            editLatitud_aquaculture.setText(String.valueOf(loc.getLatitude()));
            editLongitud_aquaculture.setText(String.valueOf(loc.getLongitude()));
            editAltitud_aquaculture.setText(String.valueOf(loc.getAltitude()));
        }
        else
        {
            editLatitud_aquaculture.setText("(sin_datos)");
            editLongitud_aquaculture.setText("(sin_datos)");
            editAltitud_aquaculture.setText("(sin_datos)");
        }
    }

    public void MuestraSeleccion(String colonia, String municipio, String estado, String CP, String p_postalCodeID){
        editColonia_aquaculture.setText(colonia);
        editMunicipio_aquaculture.setText(municipio);
        editEstado_aquaculture.setText(estado);
        editCp_aquaculture.setText(CP);
        postalCodeID = p_postalCodeID;

        selectCP.dismiss();
        Toast.makeText(myActivity, "Codigo Postal " + CP, Toast.LENGTH_LONG).show();
    }

    public void SetPojoAquacultureP1(pojoAquaculture pojo)
    {
        pojo.setPostalCode(postalCodeID);
        pojo.setBuildingName(editNombre_aquaculture.getText().toString());
        pojo.setCenterType(listSpinnerTipoCenAcu.get(spinnerTipoCenAcu_aquaculture.getSelectedItemPosition()).getId());
        pojo.setTown(editMunicipio_aquaculture.getText().toString());
        pojo.setColony(editColonia_aquaculture.getText().toString());
        pojo.setPostalCodeID(postalCodeID.toString());
        pojo.setAddress(editCalle_aquaculture.getText().toString());
        pojo.setStateName(editEstado_aquaculture.getText().toString());
        pojo.setLongitude(Double.valueOf(editLongitud_aquaculture.getText().toString()));
        pojo.setLatitude(Double.valueOf(editLongitud_aquaculture.getText().toString()));
        pojo.setAltitude(Double.valueOf(editAltitud_aquaculture.getText().toString()));

        pojo.setCenterType(listSpinnerTipoCenAcu.get(spinnerTipoCenAcu_aquaculture.getSelectedItemPosition()).getId());
        pojo.setStructureType(listSpinnerTipoEstru.get(spinnerTipoEstru_aquaculture.getSelectedItemPosition()).getId());
        pojo.setFoundationType(listSpinnerTipoCime.get(spinnerTipoCime_aquaculture.getSelectedItemPosition()).getId());
        pojo.setBlackSmithType(listSpinnerTipoHerre.get(spinnerTipoHerre_aquaculture.getSelectedItemPosition()).getId());
        pojo.setGlasseryType(listSpinnerTipoVidri.get(spinnerTipoVidri_aquaculture.getSelectedItemPosition()).getId());
        pojo.setFachadeType(listSpinnerTipoFacha.get(spinnerTipoFacha_aquaculture.getSelectedItemPosition()).getId());
        pojo.setWallsType(listSpinnerMuros.get(spinnerMuros_aquaculture.getSelectedItemPosition()).getId());

        pojo.setSpecialFacilities(editInstaEspe_aquaculture.getText().toString());

        if (editImporte2013_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[0] = editImporte2013_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[0] = editDef2013_aquaculture.getText().toString();
        }

        if (editImporte2012_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[1] = editImporte2012_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[1] = editDef2012_aquaculture.getText().toString();
        }

        if (editImporte2011_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[2] = editImporte2011_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[2] = editDef2011_aquaculture.getText().toString();
        }

        if (editImporte2010_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[3] = editImporte2010_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[3] = editDef2010_aquaculture.getText().toString();
        }

        if (editImporte2009_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[4] = editImporte2009_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[4] = editDef2009_aquaculture.getText().toString();
        }

        if (editImporte2008_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[5] = editImporte2008_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[5] = editDef2008_aquaculture.getText().toString();
        }

        if (editImporte2007_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[6] = editImporte2007_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[6] = editDef2007_aquaculture.getText().toString();
        }

        if (editImporte2006_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[7] = editImporte2006_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[7] = editDef2006_aquaculture.getText().toString();
        }

        if (editImporte2005_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[8] = editImporte2005_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[8] = editDef2005_aquaculture.getText().toString();
        }

        if (editImporte2004_aquaculture.getText().length() > 0) {
            pojo.getSiniestrality()[9] = editImporte2004_aquaculture.getText().toString();
            pojo.getSiniestralityDesc()[9] = editDef2004_aquaculture.getText().toString();
        }

    }
}
