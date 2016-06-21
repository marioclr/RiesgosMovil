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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.pojoTunnel;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

@SuppressLint("ValidFragment")
public class DialogPuente extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoTunnel pojoTunelCreado;

	private Spinner spinerTipoPuente;
	List<CatalogItem> listSpinerTipoPuente;
	private CheckBox check_principal;
	private EditText edit_nombrePuente;
	private EditText edit_longitudPuente;
	private EditText edit_VRNPuente;

	private boolean esPrincipalSelected=false;
	private String TipoPuenteSelected="";
	private String nombrePuenteSelected="";
	private double longitudPuenteSelected = Double.valueOf("0.0");
	private String VRNPuenteSelected = "0.0";

	private boolean bEsNuevo = false;

	public DialogPuente(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (WaysPuenteFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	DatabaseHandler db = new DatabaseHandler(myActivity);

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");
    		if (getArguments().containsKey("esPrincipalSelected"))
    			esPrincipalSelected = getArguments().getBoolean("esPrincipalSelected");
    		if (getArguments().containsKey("tipoPuenteSeleccionado"))
				TipoPuenteSelected = getArguments().getString("tipoPuenteSeleccionado");
    		if (getArguments().containsKey("nombrePuenteSeleccionado"))
				nombrePuenteSelected = getArguments().getString("nombrePuenteSeleccionado");
    		if (getArguments().containsKey("longitudPuenteSeleccionado"))
				longitudPuenteSelected = getArguments().getDouble("longitudPuenteSeleccionado");
    		if (getArguments().containsKey("VRNPuenteSelected"))
		    	VRNPuenteSelected = getArguments().getString("VRNPuenteSelected");
    	}

       // Seleccionar y mostrar el layout a mostrar
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
       final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_puente, null);
       alertDialogBuilder.setView(view);
       //Seteamos el título
       alertDialogBuilder.setTitle("Ingrese un nuevo Túnel");

       spinerTipoPuente = (Spinner) view.findViewById(R.id.spinnerTipoDialogPuente);
       listSpinerTipoPuente = db.getCatalogList(Constants.BRIDGES_TYPE);
       spinerTipoPuente.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoPuente));

       check_principal = (CheckBox) view.findViewById(R.id.checkEsPrincipalDialogPuente);
       edit_nombrePuente = (EditText) view.findViewById(R.id.editTextoNombreDialogPuente);
       edit_longitudPuente = (EditText) view.findViewById(R.id.editTextoLongitudDialogPuente);
       edit_VRNPuente = (EditText) view.findViewById(R.id.editTextoVRNDialogPuente);

       check_principal.setChecked(esPrincipalSelected);
       edit_nombrePuente.setText(nombrePuenteSelected);
       edit_longitudPuente.setText(Double.toString(longitudPuenteSelected));
       spinerTipoPuente.setSelection(ListOperations.getItemPositionByID(listSpinerTipoPuente, TipoPuenteSelected));
       edit_VRNPuente.setText(VRNPuenteSelected);

       //El botón cancelar
       alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
           }
       });

       //El botón aceptar
       alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
    	   @Override
    	   public void onClick(DialogInterface dialog, int which) {
			   ((WaysPuenteFragment) fragment).agregarItemList(
					   bEsNuevo,
					   check_principal.isChecked(),
					   edit_nombrePuente.getText().toString(),
					   Double.valueOf(edit_longitudPuente.getText().toString()),
					   edit_VRNPuente.getText().toString(),
					   listSpinerTipoPuente.get(spinerTipoPuente.getSelectedItemPosition()).getId(),
					   listSpinerTipoPuente.get(spinerTipoPuente.getSelectedItemPosition()).getName()
					   );
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

		if (edit_nombrePuente.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre del puente", Toast.LENGTH_LONG).show();
			edit_nombrePuente.requestFocus();
			return false;
		}
		if(spinerTipoPuente.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo del puente", Toast.LENGTH_LONG).show();
			spinerTipoPuente.requestFocus();
			return false;
		}
		if (edit_longitudPuente.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la longitud del puente", Toast.LENGTH_LONG).show();
			edit_longitudPuente.requestFocus();
			return false;
		}
		if (edit_VRNPuente.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el VRN del puente", Toast.LENGTH_LONG).show();
			edit_VRNPuente.requestFocus();
			return false;
		}
		return true;
	}
}
