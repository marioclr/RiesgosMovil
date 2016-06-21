package com.mclr.mini.riesgosmovil.fragmentos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.pojoDamHydraulicFacility;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

@SuppressLint("ValidFragment")
public class DialogUbicacion extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamHydraulicFacility pojoUbicacionCreado;

	private EditText editNombreUbicaciones;
	private EditText editLongitudUbicaciones;
	private EditText editLatitudUbicaciones;
	private EditText editAltitudUbicaciones;
	private Spinner spinnerCuencasUbicaciones;
	List<CatalogItem> listSpinnerCuencasUbicaciones;
	private Spinner spinnertEstacionesUbicaciones;
	List<CatalogItem> listSpinnertEstacionesUbicaciones;
	private Spinner spinnerTipoUbicaciones;
	List<CatalogItem> listSpinnerTipoUbicaciones;
	private Spinner spinnerUsoUbicaciones;
	List<CatalogItem> listSpinnerUsoUbicaciones;
	private EditText editDistanciaUbicaciones;

	private String nombreUbicacionSeleccionado="";
	private double latitudUbicacionSeleccionado = Double.valueOf("0.0");
	private double longitudUbicacionSeleccionado = Double.valueOf("0.0");
	private double altitudUbicacionSeleccionado = Double.valueOf("0.0");
	private String cuencasHidrologicasUbicacionSeleccionado="";
	private String estacionMeteorologicaSMNUbicacionSeleccionado="";
	private String tipoPresaUbicacionSeleccionado="";
	private String usosUbicacionSeleccionado="";
	private double distanciaPoblacionUbicacionSeleccionado = Double.valueOf("0.0");

	private boolean bEsNuevo = false;

	public DialogUbicacion(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamUbicacionFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	DatabaseHandler db = new DatabaseHandler(myActivity);

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("nombreUbicacionSeleccionado"))
    			nombreUbicacionSeleccionado = getArguments().getString("nombreUbicacionSeleccionado");
    		if (getArguments().containsKey("latitudUbicacionSeleccionado"))
    			latitudUbicacionSeleccionado = getArguments().getDouble("latitudUbicacionSeleccionado");
    		if (getArguments().containsKey("longitudUbicacionSeleccionado"))
    			longitudUbicacionSeleccionado = getArguments().getDouble("longitudUbicacionSeleccionado");
    		if (getArguments().containsKey("altitudUbicacionSeleccionado"))
    			altitudUbicacionSeleccionado = getArguments().getDouble("altitudUbicacionSeleccionado");
    		if (getArguments().containsKey("cuencasHidrologicasUbicacionSeleccionado"))
    			cuencasHidrologicasUbicacionSeleccionado = getArguments().getString("cuencasHidrologicasUbicacionSeleccionado");
    		if (getArguments().containsKey("estacionMeteorologicaSMNUbicacionSeleccionado"))
    			estacionMeteorologicaSMNUbicacionSeleccionado = getArguments().getString("estacionMeteorologicaSMNUbicacionSeleccionado");
    		if (getArguments().containsKey("tipoPresaUbicacionSeleccionado"))
    			tipoPresaUbicacionSeleccionado = getArguments().getString("tipoPresaUbicacionSeleccionado");
    		if (getArguments().containsKey("usosUbicacionSeleccionado"))
    			usosUbicacionSeleccionado = getArguments().getString("usosUbicacionSeleccionado");
    		if (getArguments().containsKey("distanciaPoblacionUbicacionSeleccionado"))
    			distanciaPoblacionUbicacionSeleccionado = getArguments().getDouble("distanciaPoblacionUbicacionSeleccionado");

    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_ubicaciones, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese una nueva Ubicación");

    	editNombreUbicaciones = (EditText) view.findViewById(R.id.editNombreUbicaciones);
    	editLongitudUbicaciones = (EditText) view.findViewById(R.id.editLongitudUbicaciones);
    	editLatitudUbicaciones = (EditText) view.findViewById(R.id.editLatitudUbicaciones);
    	editAltitudUbicaciones = (EditText) view.findViewById(R.id.editAltitudUbicaciones);

    	spinnerCuencasUbicaciones = (Spinner) view.findViewById(R.id.spinnerCuencasUbicaciones);
    	listSpinnerCuencasUbicaciones = db.getCatalogList(Constants.CATCHMENT_TYPE);
		spinnerCuencasUbicaciones.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerCuencasUbicaciones));

		spinnertEstacionesUbicaciones = (Spinner) view.findViewById(R.id.spinnertEstacionesUbicaciones);
		listSpinnertEstacionesUbicaciones = db.getWeatherList();
		spinnertEstacionesUbicaciones.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnertEstacionesUbicaciones));

		spinnerTipoUbicaciones = (Spinner) view.findViewById(R.id.spinnerTipoUbicaciones);
		listSpinnerTipoUbicaciones = db.getCatalogList(Constants.DAM_TYPE);
		spinnerTipoUbicaciones.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoUbicaciones));

		spinnerUsoUbicaciones = (Spinner) view.findViewById(R.id.spinnerUsoUbicaciones);
		listSpinnerUsoUbicaciones = db.getCatalogList(Constants.DAM_USE);
		spinnerUsoUbicaciones.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerUsoUbicaciones));

		editDistanciaUbicaciones = (EditText) view.findViewById(R.id.editDistanciaUbicaciones);

		editNombreUbicaciones.setText(nombreUbicacionSeleccionado);
		editLongitudUbicaciones.setText(Double.toString(latitudUbicacionSeleccionado));
		editLatitudUbicaciones.setText(Double.toString(longitudUbicacionSeleccionado));
    	editAltitudUbicaciones.setText(Double.toString(altitudUbicacionSeleccionado));
    	spinnerCuencasUbicaciones.setSelection(ListOperations.getItemPositionByID(listSpinnerCuencasUbicaciones, cuencasHidrologicasUbicacionSeleccionado));
    	spinnertEstacionesUbicaciones.setSelection(ListOperations.getItemPositionByID(listSpinnertEstacionesUbicaciones, estacionMeteorologicaSMNUbicacionSeleccionado));
    	spinnerTipoUbicaciones.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoUbicaciones, tipoPresaUbicacionSeleccionado));
    	spinnerUsoUbicaciones.setSelection(ListOperations.getItemPositionByID(listSpinnerUsoUbicaciones, usosUbicacionSeleccionado));
    	editDistanciaUbicaciones.setText(Double.toString(distanciaPoblacionUbicacionSeleccionado));

		//El botón cancelar
		alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();
			}
		});

		//El botón aceptar
		alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				((DamUbicacionFragment) fragment).agregarItemList(
						bEsNuevo,
						editNombreUbicaciones.getText().toString(),
						Double.valueOf(editLongitudUbicaciones.getText().toString()),
						Double.valueOf(editLatitudUbicaciones.getText().toString()),
						Double.valueOf(editAltitudUbicaciones.getText().toString()),
						listSpinnerCuencasUbicaciones.get(spinnerCuencasUbicaciones.getSelectedItemPosition()).getId(),
						listSpinnerCuencasUbicaciones.get(spinnerCuencasUbicaciones.getSelectedItemPosition()).getName(),
						listSpinnertEstacionesUbicaciones.get(spinnertEstacionesUbicaciones.getSelectedItemPosition()).getId(),
						listSpinnertEstacionesUbicaciones.get(spinnertEstacionesUbicaciones.getSelectedItemPosition()).getName(),
						listSpinnerTipoUbicaciones.get(spinnerTipoUbicaciones.getSelectedItemPosition()).getId(),
						listSpinnerTipoUbicaciones.get(spinnerTipoUbicaciones.getSelectedItemPosition()).getName(),
						listSpinnerUsoUbicaciones.get(spinnerUsoUbicaciones.getSelectedItemPosition()).getId(),
						listSpinnerUsoUbicaciones.get(spinnerUsoUbicaciones.getSelectedItemPosition()).getName(),
						Double.valueOf(editDistanciaUbicaciones.getText().toString()));
				dialog.dismiss();
			}
		});
		return alertDialogBuilder.create();
    }

    @Override
	public void onAttach(Activity activity) {
		this.myActivity = activity;
		super.onAttach(activity);
	}
    
    public boolean validaCampos(){

		if (editNombreUbicaciones.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre de la ubicación", Toast.LENGTH_LONG).show();
			editNombreUbicaciones.requestFocus();
			return false;
		}
		if (editLongitudUbicaciones.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la longitud de la ubicación", Toast.LENGTH_LONG).show();
			editLongitudUbicaciones.requestFocus();
			return false;
		}
		if (editLatitudUbicaciones.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la latitud de la ubicación", Toast.LENGTH_LONG).show();
			editLatitudUbicaciones.requestFocus();
			return false;
		}
		if (editAltitudUbicaciones.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altitud de la ubicación", Toast.LENGTH_LONG).show();
			editAltitudUbicaciones.requestFocus();
			return false;
		}
		if(spinnerCuencasUbicaciones.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar la cuenca", Toast.LENGTH_LONG).show();
			spinnerCuencasUbicaciones.requestFocus();
			return false;
		}
		if(spinnertEstacionesUbicaciones.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar la estación", Toast.LENGTH_LONG).show();
			spinnertEstacionesUbicaciones.requestFocus();
			return false;
		}
		if(spinnerTipoUbicaciones.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo", Toast.LENGTH_LONG).show();
			spinnerTipoUbicaciones.requestFocus();
			return false;
		}
		if(spinnerUsoUbicaciones.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el uso", Toast.LENGTH_LONG).show();
			spinnerUsoUbicaciones.requestFocus();
			return false;
		}
		if (editDistanciaUbicaciones.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la distancia", Toast.LENGTH_LONG).show();
			editDistanciaUbicaciones.requestFocus();
			return false;
		}
		return true;
	}
}
