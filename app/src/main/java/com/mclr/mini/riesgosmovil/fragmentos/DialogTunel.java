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
import com.mclr.mini.riesgosmovil.fragmentos.lists.TunelFragment;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.pojoTunnel;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

@SuppressLint("ValidFragment")
public class DialogTunel extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoTunnel pojoTunelCreado;

	private Spinner spinerTipoTunel;
	List<CatalogItem> listSpinerTipoTunel;
	private EditText edit_nombreTunel;
	private EditText edit_longitudTunel;
	private EditText edit_VRNTunel;

	String nombreTunelSeleccionado; 
	double longitudTunelSeleccionado; 
	String VRNTunelSeleccionado;
	String tipoTunelSeleccionado;
	private boolean bEsNuevo = false;

	public DialogTunel(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.EDUCATION) == 0){
			this.fragment = (EducationFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.HEALT) == 0){
			this.fragment = (HealtFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.HIDRAULIC) == 0){
			this.fragment = (HydraulicFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.HISTORICAL) == 0){
			this.fragment = (HistoricalFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.URBAN) == 0){
			this.fragment = (UrbanFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.WASTE_DISPOSAL) == 0){
			this.fragment = (WasteDisposalFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.WAYS) == 0){
			this.fragment = (WaysTunelFragment) p_fragment;
		} else {
			this.fragment = (TunelFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	DatabaseHandler db = new DatabaseHandler(myActivity);

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("nombreTunelSeleccionado"))
    			nombreTunelSeleccionado = getArguments().getString("nombreTunelSeleccionado");
    		if (getArguments().containsKey("longitudTunelSeleccionado"))
    			longitudTunelSeleccionado = getArguments().getDouble("longitudTunelSeleccionado");
    		if (getArguments().containsKey("VRNTunelSeleccionado"))
    			VRNTunelSeleccionado = getArguments().getString("VRNTunelSeleccionado");
    		if (getArguments().containsKey("tipoTunelSeleccionado"))
    			tipoTunelSeleccionado = getArguments().getString("tipoTunelSeleccionado");

    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_tunel, null);
    	alertDialogBuilder.setView(view);
    	//Seteamos el título
    	alertDialogBuilder.setTitle("Ingrese un tunel");

		spinerTipoTunel = (Spinner) view.findViewById(R.id.spinnerTipoDialogTunel);
		listSpinerTipoTunel = db.getCatalogList(Constants.AQUACULTURE_CENTER_TYPE);
		spinerTipoTunel.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoTunel));

		edit_nombreTunel = (EditText) view.findViewById(R.id.editTextoNombreDialogTunel);
		edit_longitudTunel = (EditText) view.findViewById(R.id.editTextoLongitudDialogTunel);
		edit_VRNTunel = (EditText) view.findViewById(R.id.editTextoVRNDialogTunel);

		edit_nombreTunel.setText(nombreTunelSeleccionado);
		edit_longitudTunel.setText(Double.toString(longitudTunelSeleccionado));
		edit_VRNTunel.setText(VRNTunelSeleccionado);
	    spinerTipoTunel.setSelection(ListOperations.getItemPositionByID(listSpinerTipoTunel, tipoTunelSeleccionado));

	    //El botón cancelar
		alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
               // Pasar un valor nulo al método en la actividad principal
               // y cerrar el dialogo
               //((MainActivity) getActivity()).actualizarNombre("");
               dialog.cancel();
           }
		});

       //El botón aceptar
       alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
    	   @Override
    	   public void onClick(DialogInterface dialog, int which) {
			   ((WaysTunelFragment) fragment).agregarItemList(
					   bEsNuevo,
					   edit_nombreTunel.getText().toString(),
					   Double.valueOf(edit_longitudTunel.getText().toString()),
					   edit_VRNTunel.getText().toString(),
					   listSpinerTipoTunel.get(spinerTipoTunel.getSelectedItemPosition()).getId(),
					   listSpinerTipoTunel.get(spinerTipoTunel.getSelectedItemPosition()).getName());
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

		if (edit_nombreTunel.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre del túnel", Toast.LENGTH_LONG).show();
			edit_nombreTunel.requestFocus();
			return false;
		}
		if(spinerTipoTunel.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo del túnel", Toast.LENGTH_LONG).show();
			spinerTipoTunel.requestFocus();
			return false;
		}
		if (edit_longitudTunel.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la longitud del túnel", Toast.LENGTH_LONG).show();
			edit_longitudTunel.requestFocus();
			return false;
		}
		if (edit_VRNTunel.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar el VRN del túnel", Toast.LENGTH_LONG).show();
			edit_VRNTunel.requestFocus();
			return false;
		}
		return true;
	}
}
