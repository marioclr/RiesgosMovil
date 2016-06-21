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
import com.mclr.mini.riesgosmovil.modelos.pojoDamGalery;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

@SuppressLint("ValidFragment")
public class DialogCortina extends DialogFragment {
	Activity myActivity = null;
	Fragment fragment;
	String propertyType;
	pojoDamGalery pojoGaleriaCreado;

	private EditText edit_identificadorCortina;
	private Spinner spiner_tamañoCortina;
	List<CatalogItem> listSpinerTamañoCortina;
	private Spinner spiner_comportamientoCortina;
	List<CatalogItem> listSpinerComportamientoCortina;
	private Spinner spiner_tipoCortina;
	List<CatalogItem> listSpinerTipoCortina;
	private Spinner spiner_materialCortina;
	List<CatalogItem> listSpinerMaterialCortina;
	private EditText edit_alturaMaximaCortina;
	private EditText edit_elevacionCoronaCortina;
	private EditText edit_longitudCortina;
	private EditText edit_anchoCortina;
	private EditText edit_taludesAguasArribaCortina;
	private EditText edit_taludesAguasAbajoCortina;
	private EditText edit_alturaParapetoCortina;
	private EditText edit_volumenCuerpoCortina;
	private EditText edit_alturaSobreCalceCortina;
	private EditText edit_otrasCaracteristicasCortina;

	private String identificadorCortinaSeleccionado="";
	private String tamañoCortinaSeleccionado="";
	private String comportamientoCortinaSeleccionado="";
	private String tipoCortinaSeleccionado="";
	private String materialCortinaSeleccionado="";
	private double alturaMaximaCortinaSeleccionado=0;
	private double elevacionCoronaCortinaSeleccionado=0;
	private double longitudCortinaSeleccionado=0;
	private double anchoCortinaSeleccionado=0;
	private String taludesAguasArribaCortinaSeleccionado="";
	private String taludesAguasAbajoCortinaSeleccionado="";
	private double alturaParapetoCortinaSeleccionado=0;
	private double volumenCuerpoCortinaSeleccionado=0;
	private double alturaSobreCalceCortinaSeleccionado=0;
	private String otrasCaracteristicasCortinaSeleccionado="";
	private boolean bEsNuevo = false;

	public DialogCortina(Activity p_activity, Fragment p_fragment, String p_propertyType) {
		myActivity = p_activity;
		fragment = p_fragment;
		propertyType = p_propertyType;
		if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
			this.fragment = (AquacultureFragment) p_fragment;
		} else if(propertyType.compareTo(Constants.DAM) == 0){
			this.fragment = (DamCortinaFragment) p_fragment;
		}
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	DatabaseHandler db = new DatabaseHandler(myActivity);

    	//Obtenemos los parametros enviados desde el Fragment
    	if (getArguments() != null){
    		if (getArguments().containsKey("esNuevo"))
    			bEsNuevo = getArguments().getBoolean("esNuevo");

    		if (getArguments().containsKey("identificadorCortinaSeleccionado"))
    			identificadorCortinaSeleccionado = getArguments().getString("identificadorCortinaSeleccionado");
    		if (getArguments().containsKey("tamañoCortinaSeleccionado"))
    			tamañoCortinaSeleccionado = getArguments().getString("tamañoCortinaSeleccionado");
    		if (getArguments().containsKey("tipoCortinaSeleccionado"))
    			tipoCortinaSeleccionado = getArguments().getString("tipoCortinaSeleccionado");
    		if (getArguments().containsKey("materialCortinaSeleccionado"))
    			materialCortinaSeleccionado = getArguments().getString("materialCortinaSeleccionado");

    		if (getArguments().containsKey("alturaMaximaCortinaSeleccionado"))
    			alturaMaximaCortinaSeleccionado = getArguments().getDouble("alturaMaximaCortinaSeleccionado");
    		if (getArguments().containsKey("elevacionCoronaCortinaSeleccionado"))
    			elevacionCoronaCortinaSeleccionado = getArguments().getDouble("elevacionCoronaCortinaSeleccionado");
    		if (getArguments().containsKey("longitudCortinaSeleccionado"))
    			longitudCortinaSeleccionado = getArguments().getDouble("longitudCortinaSeleccionado");
    		if (getArguments().containsKey("anchoCortinaSeleccionado"))
    			anchoCortinaSeleccionado = getArguments().getDouble("anchoCortinaSeleccionado");

    		if (getArguments().containsKey("taludesAguasArribaCortinaSeleccionado"))
    			taludesAguasArribaCortinaSeleccionado = getArguments().getString("taludesAguasArribaCortinaSeleccionado");
    		if (getArguments().containsKey("taludesAguasAbajoCortinaSeleccionado"))
    			taludesAguasAbajoCortinaSeleccionado = getArguments().getString("taludesAguasAbajoCortinaSeleccionado");

    		if (getArguments().containsKey("alturaParapetoCortinaSeleccionado"))
    			alturaParapetoCortinaSeleccionado = getArguments().getDouble("alturaParapetoCortinaSeleccionado");
    		if (getArguments().containsKey("volumenCuerpoCortinaSeleccionado"))
    			volumenCuerpoCortinaSeleccionado = getArguments().getDouble("volumenCuerpoCortinaSeleccionado");
    		if (getArguments().containsKey("alturaSobreCalceCortinaSeleccionado"))
    			alturaSobreCalceCortinaSeleccionado = getArguments().getDouble("alturaSobreCalceCortinaSeleccionado");

    		if (getArguments().containsKey("otrasCaracteristicasCortinaSeleccionado"))
    			otrasCaracteristicasCortinaSeleccionado = getArguments().getString("otrasCaracteristicasCortinaSeleccionado");
    	}

    	// Seleccionar y mostrar el layout a mostrar
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    	final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_cortina, null);
    	alertDialogBuilder.setView(view);
    	alertDialogBuilder.setTitle("Ingrese una nueva Cortina");
    	
    	edit_identificadorCortina = (EditText) view.findViewById(R.id.editTextoIdentificadorCortina);
    	spiner_tamañoCortina = (Spinner) view.findViewById(R.id.spinnerTamañoCortina);
		listSpinerTamañoCortina = db.getCatalogList(Constants.DAM_SIZE);
		spiner_tamañoCortina.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTamañoCortina));
		spiner_comportamientoCortina = (Spinner) view.findViewById(R.id.spinnerComportamientoCortina);
		listSpinerComportamientoCortina = db.getCatalogList(Constants.DAM_BEHAVIOR);
		spiner_comportamientoCortina.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerComportamientoCortina));
		spiner_tipoCortina = (Spinner) view.findViewById(R.id.spinnerTipoCortina);
		listSpinerTipoCortina = db.getCatalogList(Constants.DAM_TYPE);
		spiner_tipoCortina.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerTipoCortina));
		spiner_materialCortina = (Spinner) view.findViewById(R.id.spinnerMaterialCortina);
		listSpinerMaterialCortina = db.getCatalogList(Constants.DAM_MATERIAL);
		spiner_materialCortina.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinerMaterialCortina));
    	edit_alturaMaximaCortina = (EditText) view.findViewById(R.id.editTextoAlturaMaximaCortina);
    	edit_elevacionCoronaCortina = (EditText) view.findViewById(R.id.editTextoElevacionCortina);
    	edit_longitudCortina = (EditText) view.findViewById(R.id.editTextoLongitudCortina);
    	edit_anchoCortina = (EditText) view.findViewById(R.id.editTextoAnchoCortina);
    	edit_taludesAguasArribaCortina = (EditText) view.findViewById(R.id.editTextoTaludesAguasArribaCortina);
    	edit_taludesAguasAbajoCortina = (EditText) view.findViewById(R.id.editTextoTaludesAguasAbajoCortina);
    	edit_alturaParapetoCortina = (EditText) view.findViewById(R.id.editTextoAlturaParapetoCortina);
    	edit_volumenCuerpoCortina = (EditText) view.findViewById(R.id.editTextoVolumenCuerpoCortina);
    	edit_alturaSobreCalceCortina = (EditText) view.findViewById(R.id.editTextoAlturaSobreCalceCortina);
    	edit_otrasCaracteristicasCortina = (EditText) view.findViewById(R.id.editTextoOtrasCaracteristicasCortina);

    	edit_identificadorCortina.setText(identificadorCortinaSeleccionado);
    	spiner_tamañoCortina.setSelection(ListOperations.getItemPositionByID(listSpinerTamañoCortina, tamañoCortinaSeleccionado));
		spiner_comportamientoCortina.setSelection(ListOperations.getItemPositionByID(listSpinerComportamientoCortina, comportamientoCortinaSeleccionado));
		spiner_tipoCortina.setSelection(ListOperations.getItemPositionByID(listSpinerTipoCortina, tipoCortinaSeleccionado));
		spiner_materialCortina.setSelection(ListOperations.getItemPositionByID(listSpinerMaterialCortina, materialCortinaSeleccionado));
    	edit_alturaMaximaCortina.setText(String.valueOf(alturaMaximaCortinaSeleccionado));
    	edit_elevacionCoronaCortina.setText(String.valueOf(elevacionCoronaCortinaSeleccionado));
    	edit_longitudCortina.setText(String.valueOf(longitudCortinaSeleccionado));
    	edit_anchoCortina.setText(String.valueOf(anchoCortinaSeleccionado));
    	edit_taludesAguasArribaCortina.setText(taludesAguasArribaCortinaSeleccionado);
    	edit_taludesAguasAbajoCortina.setText(taludesAguasAbajoCortinaSeleccionado);
    	edit_alturaParapetoCortina.setText(String.valueOf(alturaParapetoCortinaSeleccionado));
    	edit_volumenCuerpoCortina.setText(String.valueOf(volumenCuerpoCortinaSeleccionado));
    	edit_alturaSobreCalceCortina.setText(String.valueOf(alturaSobreCalceCortinaSeleccionado));
    	edit_otrasCaracteristicasCortina.setText(otrasCaracteristicasCortinaSeleccionado);

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
				((DamCortinaFragment) fragment).agregarItemList(
						bEsNuevo,
						edit_identificadorCortina.getText().toString(),
				    	listSpinerTamañoCortina.get(spiner_tamañoCortina.getSelectedItemPosition()).getId(),
				    	listSpinerTamañoCortina.get(spiner_tamañoCortina.getSelectedItemPosition()).getName(),
				    	listSpinerComportamientoCortina.get(spiner_comportamientoCortina.getSelectedItemPosition()).getId(),
				    	listSpinerComportamientoCortina.get(spiner_comportamientoCortina.getSelectedItemPosition()).getName(),
				    	listSpinerTipoCortina.get(spiner_tipoCortina.getSelectedItemPosition()).getId(),
				    	listSpinerTipoCortina.get(spiner_tipoCortina.getSelectedItemPosition()).getName(),
				    	listSpinerMaterialCortina.get(spiner_materialCortina.getSelectedItemPosition()).getId(),
				    	listSpinerMaterialCortina.get(spiner_materialCortina.getSelectedItemPosition()).getName(),
				    	Double.valueOf(edit_alturaMaximaCortina.getText().toString()),
		    			Double.valueOf(edit_elevacionCoronaCortina.getText().toString()),
    					Double.valueOf(edit_longitudCortina.getText().toString()),
						Double.valueOf(edit_anchoCortina.getText().toString()),
				    	edit_taludesAguasArribaCortina.getText().toString(),
				    	edit_taludesAguasAbajoCortina.getText().toString(),
				    	Double.valueOf(edit_alturaParapetoCortina.getText().toString()),
				    	Double.valueOf(edit_volumenCuerpoCortina.getText().toString()),
		    			Double.valueOf(edit_alturaSobreCalceCortina.getText().toString()),
				    	edit_otrasCaracteristicasCortina.getText().toString());
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

		if (edit_identificadorCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el identificador de la cortina", Toast.LENGTH_LONG).show();
			edit_identificadorCortina.requestFocus();
			return false;
		}
		if(spiner_tamañoCortina.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tamaño de la cortina", Toast.LENGTH_LONG).show();
			spiner_tamañoCortina.requestFocus();
			return false;
		}
		if(spiner_comportamientoCortina.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar comportamiento de la cortina", Toast.LENGTH_LONG).show();
			spiner_comportamientoCortina.requestFocus();
			return false;
		}
		if(spiner_tipoCortina.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de la cortina", Toast.LENGTH_LONG).show();
			spiner_tipoCortina.requestFocus();
			return false;
		}
		if(spiner_materialCortina.getSelectedItemPosition()==0){
			Toast.makeText(myActivity, "Se requiere indicar material de la cortina", Toast.LENGTH_LONG).show();
			spiner_materialCortina.requestFocus();
			return false;
		}
		if (edit_alturaMaximaCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de la cortina", Toast.LENGTH_LONG).show();
			edit_alturaMaximaCortina.requestFocus();
			return false;
		}
		if (edit_elevacionCoronaCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la elevación de la cortina", Toast.LENGTH_LONG).show();
			edit_elevacionCoronaCortina.requestFocus();
			return false;
		}
		if (edit_longitudCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la longitud de la cortina", Toast.LENGTH_LONG).show();
			edit_longitudCortina.requestFocus();
			return false;
		}
		if (edit_anchoCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar ancho de la cortina", Toast.LENGTH_LONG).show();
			edit_anchoCortina.requestFocus();
			return false;
		}
		if (edit_taludesAguasArribaCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar taludes aguas arriba", Toast.LENGTH_LONG).show();
			edit_taludesAguasArribaCortina.requestFocus();
			return false;
		}
		if (edit_taludesAguasAbajoCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar taludes aguas abajo", Toast.LENGTH_LONG).show();
			edit_taludesAguasAbajoCortina.requestFocus();
			return false;
		}
		if (edit_alturaParapetoCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar altura del parapeto", Toast.LENGTH_LONG).show();
			edit_alturaParapetoCortina.requestFocus();
			return false;
		}
		if (edit_volumenCuerpoCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar volumen cuerpo", Toast.LENGTH_LONG).show();
			edit_volumenCuerpoCortina.requestFocus();
			return false;
		}
		if (edit_alturaSobreCalceCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar la altura sobre el calce", Toast.LENGTH_LONG).show();
			edit_alturaSobreCalceCortina.requestFocus();
			return false;
		}
		if (edit_otrasCaracteristicasCortina.getText().length()==0) {
			Toast.makeText(myActivity, "Se debe indicar otras caracteristicas", Toast.LENGTH_LONG).show();
			edit_otrasCaracteristicasCortina.requestFocus();
			return false;
		}
		return true;
	}
}
