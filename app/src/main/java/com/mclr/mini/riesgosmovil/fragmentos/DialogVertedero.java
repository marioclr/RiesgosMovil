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
import com.mclr.mini.riesgosmovil.modelos.pojoDamSpillway;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

@SuppressLint("ValidFragment")
public class DialogVertedero extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamSpillway pojoVertederoCreado;

	private EditText editTextoNumeroVertedor;
	private EditText editTextoCapacidadAcumulada;
	private Spinner spinnerTipoVertedero;
	List<CatalogItem> listSpinnerTipoVertedero;
	private EditText editTextoOperacionVertedero;
	private EditText editTextoLongitudCrestaVertedero;
	private EditText editElevacionCrestaVertedero;
	private EditText editTextoNumeroCompuertasVertedero;
	private Spinner spinnerTipoCompuertasVertedor;
	List<CatalogItem> listSpinnerTipoCompuertasVertedor;
	private EditText editTextoAlturaCompuertasVertedero;
	private EditText editTextoAnchoCompuertasVertedero;
	private EditText editTextoControlCompuertasVertedero;
	private EditText editTextoGastoPicoVertedero;
	private EditText editTextoElevacionLSCVertedero;
	private EditText editTextoEstructuraDisipadoraVertedero;
	private Switch switchAgujasVertedero;
	private EditText editTextoAlturaAgujasVertedero;
	private EditText editTextoComentariosVertedero;

	private String numeroVertederoSeleccionado = "";
	private double capacidadAcumuladaVertederoSeleccionado = Double.valueOf("0.0");
	private String tipoVertederoSeleccionado = "";
	private String operacionVertederoSeleccionado = "";
	private double longitudCrestaVertederoSeleccionado = Double.valueOf("0.0");
	private double elevacionCrestaVertederoSeleccionado = Double.valueOf("0.0");
	private double numeroCompuertasVertederoSeleccionado = Double.valueOf("0.0");
	private String tipoCompuertasVertederoSeleccionado = "";
	private double alturaCompuertasVertederoSeleccionado = Double.valueOf("0.0");
	private double anchoCompuertasVertederoSeleccionado = Double.valueOf("0.0");
	private String controlCompuertasVertederoSeleccionado = "";
	private String gastoPicoVertederoSeleccionado = "";
	private String elevacionLSCVertederoSeleccionado = "";
	private String estructuraDisipadoraVertederoSeleccionado = "";
	private boolean agujasVertederoSeleccionado;
	private double alturaAgujasVertederoSeleccionado = Double.valueOf("0.0");
	private String comentariosVertederoSeleccionado = "";

	private boolean bEsNuevo = false;

	public DialogVertedero(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamVertederoFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	DatabaseHandler db = new DatabaseHandler(myActivity);

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("numeroVertederoSeleccionado"))
    			numeroVertederoSeleccionado = getArguments().getString("numeroVertederoSeleccionado");
    		if (getArguments().containsKey("capacidadAcumuladaVertederoSeleccionado"))
    			capacidadAcumuladaVertederoSeleccionado = getArguments().getDouble("capacidadAcumuladaVertederoSeleccionado");
    		if (getArguments().containsKey("tipoVertederoSeleccionado"))
    			tipoVertederoSeleccionado = getArguments().getString("tipoVertederoSeleccionado");
    		if (getArguments().containsKey("operacionVertederoSeleccionado"))
    			operacionVertederoSeleccionado = getArguments().getString("operacionVertederoSeleccionado");
    		if (getArguments().containsKey("longitudCrestaVertederoSeleccionado"))
    			longitudCrestaVertederoSeleccionado = getArguments().getDouble("longitudCrestaVertederoSeleccionado");
    		if (getArguments().containsKey("elevacionCrestaVertederoSeleccionado"))
    			elevacionCrestaVertederoSeleccionado = getArguments().getDouble("elevacionCrestaVertederoSeleccionado");
    		if (getArguments().containsKey("numeroCompuertasVertederoSeleccionado"))
    			numeroCompuertasVertederoSeleccionado = getArguments().getDouble("numeroCompuertasVertederoSeleccionado");
    		if (getArguments().containsKey("tipoCompuertasVertederoSeleccionado"))
    			tipoCompuertasVertederoSeleccionado = getArguments().getString("tipoCompuertasVertederoSeleccionado");
    		if (getArguments().containsKey("alturaCompuertasVertederoSeleccionado"))
    			alturaCompuertasVertederoSeleccionado = getArguments().getDouble("alturaCompuertasVertederoSeleccionado");
    		if (getArguments().containsKey("anchoCompuertasVertederoSeleccionado"))
    			anchoCompuertasVertederoSeleccionado = getArguments().getDouble("anchoCompuertasVertederoSeleccionado");
    		if (getArguments().containsKey("controlCompuertasVertederoSeleccionado"))
    			controlCompuertasVertederoSeleccionado = getArguments().getString("controlCompuertasVertederoSeleccionado");
    		if (getArguments().containsKey("gastoPicoVertederoSeleccionado"))
    			gastoPicoVertederoSeleccionado = getArguments().getString("gastoPicoVertederoSeleccionado");
    		if (getArguments().containsKey("elevacionLSCVertederoSeleccionado"))
    			elevacionLSCVertederoSeleccionado = getArguments().getString("elevacionLSCVertederoSeleccionado");
    		if (getArguments().containsKey("estructuraDisipadoraVertederoSeleccionado"))
    			estructuraDisipadoraVertederoSeleccionado = getArguments().getString("estructuraDisipadoraVertederoSeleccionado");
    		if (getArguments().containsKey("agujasVertederoSeleccionado"))
    			agujasVertederoSeleccionado = getArguments().getBoolean("agujasVertederoSeleccionado");
    		if (getArguments().containsKey("alturaAgujasVertederoSeleccionado"))
    			alturaAgujasVertederoSeleccionado = getArguments().getDouble("alturaAgujasVertederoSeleccionado");
    		if (getArguments().containsKey("comentariosVertederoSeleccionado"))
    			comentariosVertederoSeleccionado = getArguments().getString("comentariosVertederoSeleccionado");
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_vertedero, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese un nuevo Vertedero");

    	editTextoNumeroVertedor = (EditText) view.findViewById(R.id.editTextoNumeroVertedor);
    	editTextoCapacidadAcumulada = (EditText) view.findViewById(R.id.editTextoCapacidadAcumulada);
    	spinnerTipoVertedero = (Spinner) view.findViewById(R.id.spinnerTipoVertedero);
    	listSpinnerTipoVertedero = db.getCatalogList(Constants.SPILL_TYPE);
    	spinnerTipoVertedero.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoVertedero));
    	editTextoOperacionVertedero = (EditText) view.findViewById(R.id.editTextoOperacionVertedero);
    	editTextoLongitudCrestaVertedero = (EditText) view.findViewById(R.id.editTextoLongitudCrestaVertedero);
    	editElevacionCrestaVertedero = (EditText) view.findViewById(R.id.editElevacionCrestaVertedero);
    	editTextoNumeroCompuertasVertedero = (EditText) view.findViewById(R.id.editTextoNumeroCompuertasVertedero);
    	spinnerTipoCompuertasVertedor = (Spinner) view.findViewById(R.id.spinnerTipoCompuertasVertedor);
    	listSpinnerTipoCompuertasVertedor = db.getCatalogList(Constants.GATE_TYPES);
    	spinnerTipoCompuertasVertedor.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCompuertasVertedor));
    	editTextoAlturaCompuertasVertedero = (EditText) view.findViewById(R.id.editTextoAlturaCompuertasVertedero);
    	editTextoAnchoCompuertasVertedero = (EditText) view.findViewById(R.id.editTextoAnchoCompuertasVertedero);
    	editTextoControlCompuertasVertedero = (EditText) view.findViewById(R.id.editTextoControlCompuertasVertedero);
    	editTextoGastoPicoVertedero = (EditText) view.findViewById(R.id.editTextoGastoPicoVertedero);
    	editTextoElevacionLSCVertedero = (EditText) view.findViewById(R.id.editTextoElevacionLSCVertedero);
    	editTextoEstructuraDisipadoraVertedero = (EditText) view.findViewById(R.id.editTextoEstructuraDisipadoraVertedero);
    	switchAgujasVertedero = (Switch) view.findViewById(R.id.switchAgujasVertedero);
    	editTextoAlturaAgujasVertedero = (EditText) view.findViewById(R.id.editTextoAlturaAgujasVertedero);
    	editTextoComentariosVertedero = (EditText) view.findViewById(R.id.editTextoComentariosVertedero);

    	editTextoNumeroVertedor.setText(numeroVertederoSeleccionado);
    	editTextoCapacidadAcumulada.setText(Double.toString(capacidadAcumuladaVertederoSeleccionado));
    	spinnerTipoVertedero.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoVertedero, tipoVertederoSeleccionado));
    	editTextoOperacionVertedero.setText(operacionVertederoSeleccionado);
    	editTextoLongitudCrestaVertedero.setText(Double.toString(longitudCrestaVertederoSeleccionado));
    	editElevacionCrestaVertedero.setText(Double.toString(elevacionCrestaVertederoSeleccionado));
    	editTextoNumeroCompuertasVertedero.setText(Double.toString(numeroCompuertasVertederoSeleccionado));
    	spinnerTipoCompuertasVertedor.setSelection(ListOperations.getItemPositionByID(listSpinnerTipoCompuertasVertedor, tipoCompuertasVertederoSeleccionado));
    	editTextoAlturaCompuertasVertedero.setText(Double.toString(alturaCompuertasVertederoSeleccionado));
    	editTextoAnchoCompuertasVertedero.setText(Double.toString(anchoCompuertasVertederoSeleccionado));
    	editTextoControlCompuertasVertedero.setText(controlCompuertasVertederoSeleccionado);
    	editTextoGastoPicoVertedero.setText(gastoPicoVertederoSeleccionado);
    	editTextoElevacionLSCVertedero.setText(elevacionLSCVertederoSeleccionado);
    	editTextoEstructuraDisipadoraVertedero.setText(estructuraDisipadoraVertederoSeleccionado);
    	switchAgujasVertedero.setChecked(agujasVertederoSeleccionado);
    	editTextoAlturaAgujasVertedero.setText(Double.toString(alturaAgujasVertederoSeleccionado));
    	editTextoComentariosVertedero.setText(comentariosVertederoSeleccionado);

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
				((DamVertederoFragment) fragment).agregarItemList(
						bEsNuevo,
						editTextoNumeroVertedor.getText().toString(),
						Double.valueOf(editTextoCapacidadAcumulada.getText().toString()),
						listSpinnerTipoVertedero.get(spinnerTipoVertedero.getSelectedItemPosition()).getId(),
						listSpinnerTipoVertedero.get(spinnerTipoVertedero.getSelectedItemPosition()).getName(),
						editTextoOperacionVertedero.getText().toString(),
						Double.valueOf(editTextoLongitudCrestaVertedero.getText().toString()),
						Double.valueOf(editElevacionCrestaVertedero.getText().toString()),
						Double.valueOf(editTextoNumeroCompuertasVertedero.getText().toString()),
						listSpinnerTipoCompuertasVertedor.get(spinnerTipoCompuertasVertedor.getSelectedItemPosition()).getId(),
						listSpinnerTipoCompuertasVertedor.get(spinnerTipoCompuertasVertedor.getSelectedItemPosition()).getName(),
				    	Double.valueOf(editTextoAlturaCompuertasVertedero.getText().toString()),
		    			Double.valueOf(editTextoAnchoCompuertasVertedero.getText().toString()),
		    			editTextoControlCompuertasVertedero.getText().toString(),
		    			editTextoGastoPicoVertedero.getText().toString(),
		    			editTextoElevacionLSCVertedero.getText().toString(),
		    			editTextoEstructuraDisipadoraVertedero.getText().toString(),
				    	switchAgujasVertedero.isChecked(),
				    	Double.valueOf(editTextoAlturaAgujasVertedero.getText().toString()),
				    	editTextoComentariosVertedero.getText().toString()
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

		if (editTextoNumeroVertedor.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número del vertedor", Toast.LENGTH_LONG).show();
			editTextoNumeroVertedor.requestFocus();
			return false;
		}
		if (editTextoCapacidadAcumulada.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la capacidad del vertedor", Toast.LENGTH_LONG).show();
			editTextoCapacidadAcumulada.requestFocus();
			return false;
		}
		if(spinnerTipoVertedero.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de vertedor", Toast.LENGTH_LONG).show();
			spinnerTipoVertedero.requestFocus();
			return false;
		}
		if (editTextoOperacionVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la operación del vertedor", Toast.LENGTH_LONG).show();
			editTextoOperacionVertedero.requestFocus();
			return false;
		}
		if (editTextoLongitudCrestaVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la longitud de la cresta", Toast.LENGTH_LONG).show();
			editTextoLongitudCrestaVertedero.requestFocus();
			return false;
		}
		if (editElevacionCrestaVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación de la cresta", Toast.LENGTH_LONG).show();
			editElevacionCrestaVertedero.requestFocus();
			return false;
		}
		if (editTextoNumeroCompuertasVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de compuertas", Toast.LENGTH_LONG).show();
			editTextoNumeroCompuertasVertedero.requestFocus();
			return false;
		}
		if(spinnerTipoCompuertasVertedor.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de compuerta", Toast.LENGTH_LONG).show();
			spinnerTipoCompuertasVertedor.requestFocus();
			return false;
		}
		if (editTextoAlturaCompuertasVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de la compuerta", Toast.LENGTH_LONG).show();
			editTextoAlturaCompuertasVertedero.requestFocus();
			return false;
		}
		if (editTextoAnchoCompuertasVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el ancho de la compuerta", Toast.LENGTH_LONG).show();
			editTextoAnchoCompuertasVertedero.requestFocus();
			return false;
		}
		if (editTextoControlCompuertasVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el control de la compuerta", Toast.LENGTH_LONG).show();
			editTextoControlCompuertasVertedero.requestFocus();
			return false;
		}
		if (editTextoGastoPicoVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el gasto pico", Toast.LENGTH_LONG).show();
			editTextoGastoPicoVertedero.requestFocus();
			return false;
		}
		if (editTextoElevacionLSCVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar elevación LSC", Toast.LENGTH_LONG).show();
			editTextoElevacionLSCVertedero.requestFocus();
			return false;
		}
		if (editTextoEstructuraDisipadoraVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la estructura disipadora", Toast.LENGTH_LONG).show();
			editTextoEstructuraDisipadoraVertedero.requestFocus();
			return false;
		}
		//TODO
//		if (editTextoAgujasVertedero.getText().length()==0) {
//			Toast.makeText(myActivity, "Se requiere indicar las agujas del vertedero", Toast.LENGTH_LONG).show();
//			editTextoAgujasVertedero.requestFocus();
//			return false;
//		}
		if (editTextoAlturaAgujasVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de las agujas", Toast.LENGTH_LONG).show();
			editTextoAlturaAgujasVertedero.requestFocus();
			return false;
		}
		if (editTextoComentariosVertedero.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar los comentarios del vertedero", Toast.LENGTH_LONG).show();
			editTextoComentariosVertedero.requestFocus();
			return false;
		}
		return true;
	}
}
