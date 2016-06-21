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
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.pojoDamGalery;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

@SuppressLint("ValidFragment")
public class DialogGaleria extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamGalery pojoGaleriaCreado;

	private EditText edit_numeroGaleria;
	private EditText edit_seccionGaleria;
	private EditText edit_longitudGaleria;
	private EditText edit_anchoGaleria;
	private EditText edit_altoGaleria;
	private EditText elevacionGaleri;

	private String numeroGaleriaSeleccionado;
	private String seccionGaleriaSeleccionado;
	private double longitudGaleriaSeleccionado;
	private double anchoGaleriaSeleccionado;
	private double altoGaleriaSeleccionado;
	private double elevacionGaleriaSeleccionado;
	private boolean bEsNuevo = false;

	public DialogGaleria(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamGaleriaFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("numeroGaleriaSeleccionado"))
    			numeroGaleriaSeleccionado = getArguments().getString("numeroGaleriaSeleccionado");
    		if (getArguments().containsKey("seccionGaleriaSeleccionado"))
    			seccionGaleriaSeleccionado = getArguments().getString("seccionGaleriaSeleccionado");
    		if (getArguments().containsKey("longitudGaleriaSeleccionado"))
    			longitudGaleriaSeleccionado = getArguments().getDouble("longitudGaleriaSeleccionado");
    		if (getArguments().containsKey("anchoGaleriaSeleccionado"))
    			anchoGaleriaSeleccionado = getArguments().getDouble("anchoGaleriaSeleccionado");
    		if (getArguments().containsKey("altoGaleriaSeleccionado"))
    			altoGaleriaSeleccionado = getArguments().getDouble("altoGaleriaSeleccionado");
    		if (getArguments().containsKey("elevacionGaleriaSeleccionado"))
    			elevacionGaleriaSeleccionado = getArguments().getDouble("elevacionGaleriaSeleccionado");
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_galerias, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese una nueva Galería");

		edit_numeroGaleria = (EditText) view.findViewById(R.id.editGaleNumDialogGalerias);
		edit_seccionGaleria = (EditText) view.findViewById(R.id.editSeccionDialogGalerias);
		edit_longitudGaleria = (EditText) view.findViewById(R.id.editLongitudMDialogGalerias);
		edit_anchoGaleria = (EditText) view.findViewById(R.id.editAnchoMDialogGalerias);
		edit_altoGaleria = (EditText) view.findViewById(R.id.editAltoMDialogGalerias);
		elevacionGaleri = (EditText) view.findViewById(R.id.editElevaDialogGalerias);

		edit_numeroGaleria.setText(numeroGaleriaSeleccionado);
		edit_seccionGaleria.setText(seccionGaleriaSeleccionado);
		edit_longitudGaleria.setText(String.valueOf(longitudGaleriaSeleccionado));
		edit_anchoGaleria.setText(String.valueOf(anchoGaleriaSeleccionado));
		edit_altoGaleria.setText(String.valueOf(altoGaleriaSeleccionado));
		elevacionGaleri.setText(String.valueOf(elevacionGaleriaSeleccionado));

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
				((DamGaleriaFragment) fragment).agregarItemList(
						bEsNuevo,
						edit_numeroGaleria.getText().toString(),
						edit_seccionGaleria.getText().toString(),
						Double.valueOf(edit_longitudGaleria.getText().toString()),
						Double.valueOf(edit_anchoGaleria.getText().toString()),
						Double.valueOf(edit_altoGaleria.getText().toString()),
						Double.valueOf(elevacionGaleri.getText().toString()));
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
		if (edit_numeroGaleria.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de la galería", Toast.LENGTH_LONG).show();
			edit_numeroGaleria.requestFocus();
			return false;
		}
		if(edit_longitudGaleria.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar la sección de la galería", Toast.LENGTH_LONG).show();
			edit_longitudGaleria.requestFocus();
			return false;
		}
		if(edit_anchoGaleria.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar el ancho de la galería", Toast.LENGTH_LONG).show();
			edit_anchoGaleria.requestFocus();
			return false;
		}
		if (edit_altoGaleria.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar alto de la galería", Toast.LENGTH_LONG).show();
			edit_altoGaleria.requestFocus();
			return false;
		}
		if (elevacionGaleri.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación de la galería", Toast.LENGTH_LONG).show();
			elevacionGaleri.requestFocus();
			return false;
		}
		return true;
	}
}
