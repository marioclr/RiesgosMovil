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
import com.mclr.mini.riesgosmovil.modelos.pojoVenting;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

@SuppressLint("ValidFragment")
public class DialogOtrosDesfogues extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoVenting pojoDesfogueCreado;

	EditText edit_nombreDesfogue;
	EditText edit_tipoDesfogue;
	EditText edit_propositoDesfogue;
	EditText edit_capacidadDesfogue;
	EditText edit_elevacionDesfogue;
	EditText edit_dimensionesDesfogue;
	EditText edit_comentariosDesfogue;

	private String nombreDesfogue;
	private String propositoDesfogue;
	private String tipoDesfogue;
	private double capacidadDesfogue;
	private double elevacionDesfogue;
	private double dimensionDesfogue;
	private String comentariosDesfogue;

	private boolean bEsNuevo = false;

	public DialogOtrosDesfogues(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamOtrosDesfoguesFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("nombreDesfogue"))
    			nombreDesfogue = getArguments().getString("nombreDesfogue");
    		if (getArguments().containsKey("propositoDesfogue"))
    			propositoDesfogue = getArguments().getString("propositoDesfogue");
    		if (getArguments().containsKey("tipoDesfogue"))
    			tipoDesfogue = getArguments().getString("tipoDesfogue");
    		if (getArguments().containsKey("capacidadDesfogue"))
    			capacidadDesfogue = getArguments().getDouble("capacidadDesfogue");
    		if (getArguments().containsKey("elevacionDesfogue"))
    			elevacionDesfogue = getArguments().getDouble("elevacionDesfogue");
    		if (getArguments().containsKey("dimensionDesfogue"))
    			dimensionDesfogue = getArguments().getDouble("dimensionDesfogue");
    		if (getArguments().containsKey("comentariosDesfogue"))
    			comentariosDesfogue = getArguments().getString("comentariosDesfogue");
    		
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_desfogues, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese un nuevo Desfogue");

    	edit_nombreDesfogue = (EditText) view.findViewById(R.id.editTextoNombreDesfogues);
    	edit_tipoDesfogue = (EditText) view.findViewById(R.id.editTextoTipoDesfogues);
    	edit_propositoDesfogue = (EditText) view.findViewById(R.id.editTextoPropositoDesfogues);
    	edit_capacidadDesfogue = (EditText) view.findViewById(R.id.editTextoCapacidadDesfogues);
    	edit_elevacionDesfogue = (EditText) view.findViewById(R.id.editTextoElevacionDesfogues);
    	edit_dimensionesDesfogue = (EditText) view.findViewById(R.id.editTextoDimensionesDesfogues);
    	edit_comentariosDesfogue = (EditText) view.findViewById(R.id.editTextoComentariosDesfogues);

    	edit_nombreDesfogue.setText(nombreDesfogue);
    	edit_propositoDesfogue.setText(propositoDesfogue);
    	edit_tipoDesfogue.setText(tipoDesfogue);
    	edit_capacidadDesfogue.setText(String.valueOf(capacidadDesfogue));
    	edit_elevacionDesfogue.setText(String.valueOf(elevacionDesfogue));
    	edit_dimensionesDesfogue.setText(String.valueOf(dimensionDesfogue));
    	edit_comentariosDesfogue.setText(String.valueOf(comentariosDesfogue));
    	
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
				((DamOtrosDesfoguesFragment) fragment).agregarItemList(
						bEsNuevo,
						edit_nombreDesfogue.getText().toString(),
						edit_tipoDesfogue.getText().toString(),
						edit_propositoDesfogue.getText().toString(),
						Double.valueOf(edit_capacidadDesfogue.getText().toString()),
						Double.valueOf(edit_elevacionDesfogue.getText().toString()),
						Double.valueOf(edit_dimensionesDesfogue.getText().toString()),
						edit_comentariosDesfogue.getText().toString());
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

		if (edit_nombreDesfogue.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre del desfogue", Toast.LENGTH_LONG).show();
			edit_nombreDesfogue.requestFocus();
			return false;
		}
		if(edit_tipoDesfogue.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo del desfogue", Toast.LENGTH_LONG).show();
			edit_tipoDesfogue.requestFocus();
			return false;
		}
		if(edit_propositoDesfogue.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar el propósito del desfogue", Toast.LENGTH_LONG).show();
			edit_propositoDesfogue.requestFocus();
			return false;
		}
		if (edit_capacidadDesfogue.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la capacidad del desfogue", Toast.LENGTH_LONG).show();
			edit_capacidadDesfogue.requestFocus();
			return false;
		}
		if (edit_elevacionDesfogue.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación del desfogue", Toast.LENGTH_LONG).show();
			edit_elevacionDesfogue.requestFocus();
			return false;
		}
		if (edit_dimensionesDesfogue.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la deimensión del desfogue", Toast.LENGTH_LONG).show();
			edit_dimensionesDesfogue.requestFocus();
			return false;
		}
		if (edit_comentariosDesfogue.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar los comentarios del desfogue", Toast.LENGTH_LONG).show();
			edit_comentariosDesfogue.requestFocus();
			return false;
		}
		return true;
	}
}
