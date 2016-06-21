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
public class DialogDique extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamGalery pojoGaleriaCreado;

	private EditText edit_numeroDique;
	private EditText edit_alturaDique;
	private EditText edit_anchoBaseDique;
	private EditText edit_nivelInundacionDique;

	private String numeroDiqueSeleccionado;
	private double alturaDiqueSeleccionado;
	private double anchoBaseDiqueSeleccionado;
	private double nivelInundacionDiqueSeleccionado;

	private boolean bEsNuevo = false;
	private String descTituloDialog;

	public DialogDique(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamDiqueFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    		{
    			bEsNuevo = getArguments().getBoolean("esNuevo");
    			descTituloDialog = "Ingrese el nuevo Dique";
    		}
    		else
    			descTituloDialog = "Modifique datos del Dique";
    		if (getArguments().containsKey("numeroDiqueSeleccionado"))
    			numeroDiqueSeleccionado = getArguments().getString("numeroDiqueSeleccionado");
    		if (getArguments().containsKey("alturaDiqueSeleccionado"))
    			alturaDiqueSeleccionado = getArguments().getDouble("alturaDiqueSeleccionado");
    		if (getArguments().containsKey("anchoBaseDiqueSeleccionado"))
    			anchoBaseDiqueSeleccionado = getArguments().getDouble("anchoBaseDiqueSeleccionado");
    		if (getArguments().containsKey("nivelInundacionDiqueSeleccionado"))
    			nivelInundacionDiqueSeleccionado = getArguments().getDouble("nivelInundacionDiqueSeleccionado");
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_diques, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle(descTituloDialog);

    	edit_numeroDique = (EditText) view.findViewById(R.id.editDiqueNumDialogDiques);
    	edit_alturaDique = (EditText) view.findViewById(R.id.editAlturaDialogDiques);
    	edit_anchoBaseDique = (EditText) view.findViewById(R.id.editAnchoBaseDiDialogDiques);
    	edit_nivelInundacionDique = (EditText) view.findViewById(R.id.editNivelInunDialogDiques);

    	edit_numeroDique.setText(numeroDiqueSeleccionado);
    	edit_alturaDique.setText(String.valueOf(alturaDiqueSeleccionado));
    	edit_anchoBaseDique.setText(String.valueOf(anchoBaseDiqueSeleccionado));
    	edit_nivelInundacionDique.setText(String.valueOf(nivelInundacionDiqueSeleccionado));

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
				((DamDiqueFragment) fragment).agregarItemList(
						bEsNuevo,
						edit_numeroDique.getText().toString(),
						Double.valueOf(edit_alturaDique.getText().toString()),
						Double.valueOf(edit_anchoBaseDique.getText().toString()),
						Double.valueOf(edit_nivelInundacionDique.getText().toString()));
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

		if (edit_numeroDique.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número del dique", Toast.LENGTH_LONG).show();
			edit_numeroDique.requestFocus();
			return false;
		}
		if(edit_alturaDique.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar la altura del dique", Toast.LENGTH_LONG).show();
			edit_alturaDique.requestFocus();
			return false;
		}
		if(edit_anchoBaseDique.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar ancho de la base", Toast.LENGTH_LONG).show();
			edit_anchoBaseDique.requestFocus();
			return false;
		}
		if (edit_nivelInundacionDique.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de la cortina", Toast.LENGTH_LONG).show();
			edit_nivelInundacionDique.requestFocus();
			return false;
		}
		return true;
	}

}
