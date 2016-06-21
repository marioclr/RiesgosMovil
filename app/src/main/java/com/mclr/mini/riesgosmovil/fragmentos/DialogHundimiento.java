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
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.pojoDamGalery;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class DialogHundimiento extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamGalery pojoGaleriaCreado;

	private EditText edit_Ubicacion;
	private Spinner spinerGravedad;
	private List<CatalogItem> listSpinerGravedad = new ArrayList<CatalogItem>();

	private double ubicacionHundimientoSeleccionado;
	private int gravedadHundimientoSeleccionado;
	private boolean bEsNuevo = false;

	public DialogHundimiento(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.WAYS) == 0){
			this.fragment = (WaysHundimientoFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("ubicacionHundimientoSeleccionado"))
    			ubicacionHundimientoSeleccionado = getArguments().getDouble("numeroGaleriaSeleccionado");
    		if (getArguments().containsKey("gravedadHundimientoSeleccionado"))
    			gravedadHundimientoSeleccionado = getArguments().getInt("seccionGaleriaSeleccionado");

    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_hundimiento, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese un nuevo Dique");

    	// Aquí
    	edit_Ubicacion = (EditText) view.findViewById(R.id.editTextoUbicacionHundimiento);
    	spinerGravedad = (Spinner) view.findViewById(R.id.spinnerGravedadHundimiento);

    	listSpinerGravedad.add(new CatalogItem("-1", "Ninguno", "Seleccione opción", 0));
    	listSpinerGravedad.add(new CatalogItem("1", "Imperceptibles", "Imperceptibles", 0));
    	listSpinerGravedad.add(new CatalogItem("2", "Lígeros", "Lígeros", 0));
    	listSpinerGravedad.add(new CatalogItem("3", "Muy grandes", "Muy grandes", 0));

    	spinerGravedad.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerGravedad));

    	edit_Ubicacion.setText(Double.toString(ubicacionHundimientoSeleccionado));
    	spinerGravedad.setSelection(ListOperations.getItemPositionByID(listSpinerGravedad, Integer.toString(gravedadHundimientoSeleccionado)));

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
				((WaysHundimientoFragment) fragment).agregarItemList(
						bEsNuevo,
						Double.valueOf(edit_Ubicacion.getText().toString()),
						Integer.valueOf(listSpinerGravedad.get(spinerGravedad.getSelectedItemPosition()).getId()));
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

		if (edit_Ubicacion.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de la galería", Toast.LENGTH_LONG).show();
			edit_Ubicacion.requestFocus();
			return false;
		}
		if(spinerGravedad.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo del puente", Toast.LENGTH_LONG).show();
			spinerGravedad.requestFocus();
			return false;
		}
		return true;
	}
}
