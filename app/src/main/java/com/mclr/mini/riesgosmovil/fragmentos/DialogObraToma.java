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
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.pojoDamHeadworks;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

@SuppressLint("ValidFragment")
public class DialogObraToma extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamHeadworks pojoObraTomaCreado;
	
	private EditText edit_nombreObraToma;
	private Spinner spinner_tipoObraToma;
	List<CatalogItem> listSpinerTipoObraToma;
	private EditText edit_capacidadObraToma;
	private EditText edit_elevacionObraToma;
	private EditText edit_numerocompObraToma;
	private Spinner spinner_tipocompObraToma;
	List<CatalogItem> listSpinerTipoCompObraToma;
	private EditText edit_anchocompObraToma;
	private EditText edit_alturacompObraToma;
	private EditText edit_numerovalvulasObraToma;
	private Spinner spinner_tipovalObraToma;
	List<CatalogItem> listSpinerTipoValObraToma;
	private EditText edit_numerocondObraToma;
	private Spinner spinner_tipocondObraToma;
	List<CatalogItem> listSpinerTipoCondObraToma;
	private EditText edit_dimensionObraToma;
	private Switch switch_rejillasObraToma;
	private EditText edit_comentariosObraToma;
	
	private String nombreObraTomaSeleccionado="";
	private String tipoObraTomaSeleccionado="";
	private double capacidadObraTomaSeleccionado = Double.valueOf("0.0");
	private double elevacionObraTomaSeleccionado = Double.valueOf("0.0");
	private int numerocompObraTomaSeleccionado;
	private String tipocompObraTomaSeleccionado="";
	private double anchocompObraTomaSeleccionado = Double.valueOf("0.0");
	private double alturacompObraTomaSeleccionado = Double.valueOf("0.0");
	private int numerovalvulasObraTomaSeleccionado;
	private String tipovalObraTomaSeleccionado="";
	private int numerocondObraTomaSeleccionado;
	private String tipocondObraTomaSeleccionado="";
	private double dimensionObraTomaSeleccionado = Double.valueOf("0.0");
	private boolean rejillasObraToma;
	private String comentariosObraToma="";
	private boolean bEsNuevo = false;

	public DialogObraToma(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamObraTomaFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	DatabaseHandler db = new DatabaseHandler(myActivity);

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("nombreObraTomaSeleccionado"))
    			nombreObraTomaSeleccionado = getArguments().getString("nombreObraTomaSeleccionado");
    		if (getArguments().containsKey("tipoObraTomaSeleccionado"))
    			tipoObraTomaSeleccionado = getArguments().getString("tipoObraTomaSeleccionado");
    		if (getArguments().containsKey("capacidadObraTomaSeleccionado"))
    			capacidadObraTomaSeleccionado = getArguments().getDouble("capacidadObraTomaSeleccionado");
    		if (getArguments().containsKey("elevacionObraTomaSeleccionado"))
    			elevacionObraTomaSeleccionado = getArguments().getDouble("elevacionObraTomaSeleccionado");
    		if (getArguments().containsKey("numerocompObraTomaSeleccionado"))
    			numerocompObraTomaSeleccionado = getArguments().getInt("numerocompObraTomaSeleccionado");
    		if (getArguments().containsKey("tipocompObraTomaSeleccionado"))
    			tipocompObraTomaSeleccionado = getArguments().getString("tipocompObraTomaSeleccionado");
    		if (getArguments().containsKey("anchocompObraTomaSeleccionado"))
    			anchocompObraTomaSeleccionado = getArguments().getDouble("anchocompObraTomaSeleccionado");
    		if (getArguments().containsKey("alturacompObraTomaSeleccionado"))
    			alturacompObraTomaSeleccionado = getArguments().getDouble("alturacompObraTomaSeleccionado");
    		if (getArguments().containsKey("numerovalvulasObraTomaSeleccionado"))
    			numerovalvulasObraTomaSeleccionado = getArguments().getInt("numerovalvulasObraTomaSeleccionado");
    		if (getArguments().containsKey("tipovalObraTomaSeleccionado"))
    			tipovalObraTomaSeleccionado = getArguments().getString("tipovalObraTomaSeleccionado");
    		if (getArguments().containsKey("numerocondObraTomaSeleccionado"))
    			numerocondObraTomaSeleccionado = getArguments().getInt("numerocondObraTomaSeleccionado");
    		if (getArguments().containsKey("dimensionObraTomaSeleccionado"))
    			dimensionObraTomaSeleccionado = getArguments().getDouble("dimensionObraTomaSeleccionado");
    		if (getArguments().containsKey("rejillasObraToma"))
    			rejillasObraToma = getArguments().getBoolean("rejillasObraToma");
    		if (getArguments().containsKey("comentariosObraToma"))
    			comentariosObraToma = getArguments().getString("comentariosObraToma");
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_obras, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese un nuevo Obrade Toma");

    	edit_nombreObraToma = (EditText) view.findViewById(R.id.editTextoNombreObras);

    	spinner_tipoObraToma = (Spinner) view.findViewById(R.id.spinnerTipoObras);
		listSpinerTipoObraToma = db.getCatalogList(Constants.INTAKE_WORK_TYPE);
		spinner_tipoObraToma.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoObraToma));

		edit_capacidadObraToma = (EditText) view.findViewById(R.id.editTextoCapacidadObras);
		edit_elevacionObraToma = (EditText) view.findViewById(R.id.editTextoElevacionObras);
		edit_numerocompObraToma = (EditText) view.findViewById(R.id.editTextoNumeroCompObras);

		spinner_tipocompObraToma = (Spinner) view.findViewById(R.id.spinnerTipoComObras);
		listSpinerTipoCompObraToma = db.getCatalogList(Constants.GATE_TYPES);
		spinner_tipocompObraToma.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoCompObraToma));

		edit_anchocompObraToma = (EditText) view.findViewById(R.id.editTextoAnchoComObras);
		edit_alturacompObraToma = (EditText) view.findViewById(R.id.editTextoAltoComObras);
		edit_numerovalvulasObraToma = (EditText) view.findViewById(R.id.editTextoNumValObras);

		spinner_tipovalObraToma = (Spinner) view.findViewById(R.id.spinnerTipoVal);
		listSpinerTipoValObraToma = db.getCatalogList(Constants.VALVE_TYPE);
		spinner_tipovalObraToma.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoValObraToma));

		edit_numerocondObraToma = (EditText) view.findViewById(R.id.editTextoNumConObras);

		spinner_tipocondObraToma = (Spinner) view.findViewById(R.id.spinnerTipoCondObras);
		listSpinerTipoCondObraToma = db.getCatalogList(Constants.DUCT_TYPE);
		spinner_tipocondObraToma.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoCondObraToma));

		edit_dimensionObraToma = (EditText) view.findViewById(R.id.editTextoDimCondObras);
		switch_rejillasObraToma = (Switch) view.findViewById(R.id.switchCuentaRejillasObras);
		edit_comentariosObraToma = (EditText) view.findViewById(R.id.editTextoComentariosObras);


		edit_nombreObraToma.setText(nombreObraTomaSeleccionado);
		spinner_tipoObraToma.setSelection(ListOperations.getItemPositionByID(listSpinerTipoObraToma, tipoObraTomaSeleccionado));
		edit_capacidadObraToma.setText(Double.toString(capacidadObraTomaSeleccionado));
		edit_elevacionObraToma.setText(Double.toString(elevacionObraTomaSeleccionado));
		edit_numerocompObraToma.setText(Integer.toString(numerocompObraTomaSeleccionado));
		spinner_tipocompObraToma.setSelection(ListOperations.getItemPositionByID(listSpinerTipoCompObraToma, tipocompObraTomaSeleccionado));
		edit_anchocompObraToma.setText(Double.toString(anchocompObraTomaSeleccionado));
		edit_alturacompObraToma.setText(Double.toString(alturacompObraTomaSeleccionado));
		edit_numerovalvulasObraToma.setText(Integer.toString(numerovalvulasObraTomaSeleccionado));
		spinner_tipovalObraToma.setSelection(ListOperations.getItemPositionByID(listSpinerTipoValObraToma, tipovalObraTomaSeleccionado));
		edit_numerocondObraToma.setText(Integer.toString(numerocondObraTomaSeleccionado));
		spinner_tipocondObraToma.setSelection(ListOperations.getItemPositionByID(listSpinerTipoCondObraToma, tipocondObraTomaSeleccionado));
		edit_dimensionObraToma.setText(Double.toString(dimensionObraTomaSeleccionado));
		switch_rejillasObraToma.setChecked(rejillasObraToma);
		edit_comentariosObraToma.setText(comentariosObraToma);


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
				((DamObraTomaFragment) fragment).agregarItemList(
						bEsNuevo,
						edit_nombreObraToma.getText().toString(),
						listSpinerTipoObraToma.get(spinner_tipoObraToma.getSelectedItemPosition()).getId(),
						listSpinerTipoObraToma.get(spinner_tipoObraToma.getSelectedItemPosition()).getName(),
						Double.valueOf(edit_capacidadObraToma.getText().toString()),
						Double.valueOf(edit_elevacionObraToma.getText().toString()),
						Integer.valueOf(edit_numerocompObraToma.getText().toString()),
						listSpinerTipoCompObraToma.get(spinner_tipocompObraToma.getSelectedItemPosition()).getId(),
						listSpinerTipoCompObraToma.get(spinner_tipocompObraToma.getSelectedItemPosition()).getName(),
						Double.valueOf(edit_anchocompObraToma.getText().toString()),
						Double.valueOf(edit_alturacompObraToma.getText().toString()),
						Integer.valueOf(edit_numerovalvulasObraToma.getText().toString()),
						listSpinerTipoValObraToma.get(spinner_tipovalObraToma.getSelectedItemPosition()).getId(),
						listSpinerTipoValObraToma.get(spinner_tipovalObraToma.getSelectedItemPosition()).getName(),
						Integer.valueOf(edit_numerocondObraToma.getText().toString()),
						listSpinerTipoCondObraToma.get(spinner_tipocondObraToma.getSelectedItemPosition()).getId(),
						listSpinerTipoCondObraToma.get(spinner_tipocondObraToma.getSelectedItemPosition()).getName(),
						Double.valueOf(edit_dimensionObraToma.getText().toString()),
						switch_rejillasObraToma.isChecked(),
						edit_comentariosObraToma.getText().toString());
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

		if (edit_nombreObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre de la obra de toma", Toast.LENGTH_LONG).show();
			edit_nombreObraToma.requestFocus();
			return false;
		}
		if(spinner_tipoObraToma.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de obra de toma", Toast.LENGTH_LONG).show();
			spinner_tipoObraToma.requestFocus();
			return false;
		}
		if (edit_capacidadObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la capacidad de la obra de toma", Toast.LENGTH_LONG).show();
			edit_capacidadObraToma.requestFocus();
			return false;
		}
		if (edit_elevacionObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación de la obra de toma", Toast.LENGTH_LONG).show();
			edit_elevacionObraToma.requestFocus();
			return false;
		}
		if (edit_numerocompObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de compuerta de la obra de toma", Toast.LENGTH_LONG).show();
			edit_numerocompObraToma.requestFocus();
			return false;
		}
		if(spinner_tipocompObraToma.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de compuerta obra de toma", Toast.LENGTH_LONG).show();
			spinner_tipocompObraToma.requestFocus();
			return false;
		}
		if (edit_anchocompObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el ancho de compuerta de la obra de toma", Toast.LENGTH_LONG).show();
			edit_anchocompObraToma.requestFocus();
			return false;
		}
		if (edit_alturacompObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de compuerta de la obra de toma", Toast.LENGTH_LONG).show();
			edit_alturacompObraToma.requestFocus();
			return false;
		}
		if (edit_numerovalvulasObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de válvulas", Toast.LENGTH_LONG).show();
			edit_numerovalvulasObraToma.requestFocus();
			return false;
		}
		if(spinner_tipovalObraToma.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de válvula", Toast.LENGTH_LONG).show();
			spinner_tipovalObraToma.requestFocus();
			return false;
		}
		if (edit_numerocondObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de conductos", Toast.LENGTH_LONG).show();
			edit_numerocondObraToma.requestFocus();
			return false;
		}
		if(spinner_tipocondObraToma.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de conductos", Toast.LENGTH_LONG).show();
			spinner_tipocondObraToma.requestFocus();
			return false;
		}
		if (edit_dimensionObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la dimensión de conductos", Toast.LENGTH_LONG).show();
			edit_dimensionObraToma.requestFocus();
			return false;
		}
		if (edit_comentariosObraToma.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar comentarioss", Toast.LENGTH_LONG).show();
			edit_comentariosObraToma.requestFocus();
			return false;
		}
		return true;
	}
}
