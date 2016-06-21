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
import com.mclr.mini.riesgosmovil.modelos.pojoIrrigationChannel;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

@SuppressLint("ValidFragment")
public class DialogExtension extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoIrrigationChannel pojoGaleriaCreado;

	private EditText edit_identificacionExtension;
	private EditText edit_extensionExtension;
	private EditText edit_caudalExtension;
	private EditText edit_valorExtension;

	private String identificacionExtensionSeleccionado;
	private double extensionExtensionSeleccionado;
	private double caudalExtensionSeleccionado;
	private String valorExtensionSeleccionado;

	private boolean bEsNuevo = false;

	public DialogExtension(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamExtensionsFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("identificacionExtensionSeleccionado"))
    			identificacionExtensionSeleccionado = getArguments().getString("identificacionExtensionSeleccionado");
    		if (getArguments().containsKey("extensionExtensionSeleccionado"))
    			extensionExtensionSeleccionado = getArguments().getDouble("extensionExtensionSeleccionado");
    		if (getArguments().containsKey("caudalExtensionSeleccionado"))
    			caudalExtensionSeleccionado = getArguments().getDouble("caudalExtensionSeleccionado");
    		if (getArguments().containsKey("valorExtensionSeleccionado"))
    			valorExtensionSeleccionado = getArguments().getString("valorExtensionSeleccionado");
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_extension, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese un nuevo Extensión, caudal y valores...");

    	edit_identificacionExtension = (EditText) view.findViewById(R.id.editTextoIdentificadorExtension);
    	edit_extensionExtension = (EditText) view.findViewById(R.id.editTextoExtensionExtension);
    	edit_caudalExtension = (EditText) view.findViewById(R.id.editTextoCaudalExtension);
    	edit_valorExtension = (EditText) view.findViewById(R.id.editTextoValorExtension);

    	edit_identificacionExtension.setText(identificacionExtensionSeleccionado);
    	edit_extensionExtension.setText(Double.toString(extensionExtensionSeleccionado));
    	edit_caudalExtension.setText(Double.toString(caudalExtensionSeleccionado));
    	edit_valorExtension.setText(valorExtensionSeleccionado);


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
				((DamExtensionsFragment) fragment).agregarItemList(
						bEsNuevo,
						edit_identificacionExtension.getText().toString(),
						Double.valueOf(edit_extensionExtension.getText().toString()),
						Double.valueOf(edit_caudalExtension.getText().toString()),
						edit_valorExtension.getText().toString());
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

		if (edit_identificacionExtension.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la identificación de la extensión", Toast.LENGTH_LONG).show();
			edit_identificacionExtension.requestFocus();
			return false;
		}
		if(edit_extensionExtension.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar la extensión (km)", Toast.LENGTH_LONG).show();
			edit_extensionExtension.requestFocus();
			return false;
		}
		if(edit_caudalExtension.getText().length()==0){
			Toast.makeText(myActivity, "Se requiere indicar el caudal (lts/s)", Toast.LENGTH_LONG).show();
			edit_caudalExtension.requestFocus();
			return false;
		}
		if (edit_valorExtension.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el valor de la extensión", Toast.LENGTH_LONG).show();
			edit_valorExtension.requestFocus();
			return false;
		}
		return true;
	}
}
