package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

import java.util.List;

public class DamFragment4 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCode;
	String postalCodeID; // Este se inicializara en la selección del CP

	ImageButton buttonSaveUp4_5_dam;
	ImageButton buttonOutUp4_5_dam;
	ImageButton buttonSaveBot4_5_dam;
	ImageButton buttonOutBot4_5_dam;
	
	EditText editProposito_dam;
	EditText editTipo_dam;
	EditText editCapaM3sOtros_dam;
	EditText editElevaUm_dam;
	EditText editDimensiones_dam;
	EditText editComeOtros_dam;
	EditText editNumObra_dam;
	EditText editTomaNum_dam;
	EditText editCapaM3s_dam;
	EditText editEleM3s_dam;
	EditText editNumCompM3s_dam;
	EditText editAnchoCompObra_dam;
	EditText editAltuComObra_dam;
	EditText editNumVal_dam;
	EditText editNumCond_dam;
	EditText editDimenCond_dam;
	EditText editComenObras_dam;
	EditText editImporte2013_dam;
	EditText editDef2013_dam;
	EditText editImporte2012_dam;
	EditText editDef2012_dam;
	EditText editImporte2011_dam;
	EditText editDef2011_dam;
	EditText editImporte2010_dam;
	EditText editDef2010_dam;
	EditText editImporte2009_dam;
	EditText editDef2009_dam;
	EditText editImporte2008_dam;
	EditText editDef2008_dam;
	EditText editImporte2007_dam;
	EditText editDef2007_dam;
	EditText editImporte2006_dam;
	EditText editDef2006_dam;
	EditText editImporte2005_dam;
	EditText editDef2005_dam;
	EditText editImporte2004_dam;
	EditText editDef2004_dam;

	Spinner spinnerTipoObras_dam;
	List<CatalogItem> listSpinnerTipoObras;
	Spinner spinnerTipoCompObras_dam;
	List<CatalogItem> listSpinnerTipoCompObras;
	Spinner spinnerTipoVal_dam;
	List<CatalogItem> listSpinnerTipoVal;
	Spinner spinnerTipoCond_dam;
	List<CatalogItem> listSpinnerTipoCond;

	DatePicker pickerFechaConst_dam;
	DatePicker pickerFechaRefu_dam;

	Switch switchCueRej_dam;
	Switch switchImporte2013_dam;
	Switch switchImporte2012_dam;
	Switch switchImporte2011_dam;
	Switch switchImporte2010_dam;
	Switch switchImporte2009_dam;
	Switch switchImporte2008_dam;
	Switch switchImporte2007_dam;
	Switch switchImporte2006_dam;
	Switch switchImporte2005_dam;
	Switch switchImporte2004_dam;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		buttonSaveUp4_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp4_5_dam);
		buttonSaveUp4_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutUp4_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutUp4_5_dam);
		buttonOutUp4_5_dam.setOnClickListener(new OutClickListener());

		buttonSaveBot4_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot4_5_dam);
		buttonSaveBot4_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutBot4_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutBot4_5_dam);
		buttonOutBot4_5_dam.setOnClickListener(new OutClickListener());

//		editProposito_dam = (EditText) myActivity.findViewById(R.id.editProposito_dam);
//		editTipo_dam = (EditText) myActivity.findViewById(R.id.editTipo_dam);
//		editCapaM3sOtros_dam = (EditText) myActivity.findViewById(R.id.editCapaM3sOtros_dam);
//		editElevaUm_dam = (EditText) myActivity.findViewById(R.id.editElevaUm_dam);
//		editDimensiones_dam = (EditText) myActivity.findViewById(R.id.editDimensiones_dam);
//		editComeOtros_dam = (EditText) myActivity.findViewById(R.id.editComeOtros_dam);
//		editNumObra_dam = (EditText) myActivity.findViewById(R.id.editNumObra_dam);
//		editTomaNum_dam = (EditText) myActivity.findViewById(R.id.editTomaNum_dam);
//		editCapaM3s_dam = (EditText) myActivity.findViewById(R.id.editCapaM3s_dam);
//		editEleM3s_dam = (EditText) myActivity.findViewById(R.id.editEleM3s_dam);
//		editNumCompM3s_dam = (EditText) myActivity.findViewById(R.id.editNumCompM3s_dam);
//		editAnchoCompObra_dam = (EditText) myActivity.findViewById(R.id.editAnchoCompObra_dam);
//		editAltuComObra_dam = (EditText) myActivity.findViewById(R.id.editAltuComObra_dam);
//		editNumCond_dam = (EditText) myActivity.findViewById(R.id.editNumCond_dam);
//		editDimenCond_dam = (EditText) myActivity.findViewById(R.id.editDimenCond_dam);
//		editComenObras_dam = (EditText) myActivity.findViewById(R.id.editComenObras_dam);
//		editImporte2013_dam = (EditText) myActivity.findViewById(R.id.editImporte2013_dam);
		editDef2013_dam = (EditText) myActivity.findViewById(R.id.editDef2013_dam);
		editImporte2012_dam = (EditText) myActivity.findViewById(R.id.editImporte2012_dam);
		editDef2012_dam = (EditText) myActivity.findViewById(R.id.editDef2012_dam);
		editImporte2011_dam = (EditText) myActivity.findViewById(R.id.editImporte2011_dam);
		editDef2011_dam = (EditText) myActivity.findViewById(R.id.editDef2011_dam);
		editImporte2010_dam = (EditText) myActivity.findViewById(R.id.editImporte2010_dam);
		editDef2010_dam = (EditText) myActivity.findViewById(R.id.editDef2010_dam);
		editImporte2009_dam = (EditText) myActivity.findViewById(R.id.editImporte2009_dam);
		editDef2009_dam = (EditText) myActivity.findViewById(R.id.editDef2009_dam);
		editImporte2008_dam = (EditText) myActivity.findViewById(R.id.editImporte2008_dam);
		editDef2008_dam = (EditText) myActivity.findViewById(R.id.editDef2008_dam);
		editImporte2007_dam = (EditText) myActivity.findViewById(R.id.editImporte2007_dam);
		editDef2007_dam = (EditText) myActivity.findViewById(R.id.editDef2007_dam);
		editImporte2006_dam = (EditText) myActivity.findViewById(R.id.editImporte2006_dam);
		editDef2013_dam = (EditText) myActivity.findViewById(R.id.editDef2013_dam);
		editImporte2005_dam = (EditText) myActivity.findViewById(R.id.editImporte2005_dam);
		editDef2013_dam = (EditText) myActivity.findViewById(R.id.editDef2013_dam);
		editImporte2004_dam = (EditText) myActivity.findViewById(R.id.editImporte2004_dam);
		editDef2013_dam = (EditText) myActivity.findViewById(R.id.editDef2013_dam);
		
//		spinnerTipoObras_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoObras_dam);
//		listSpinnerTipoObras = db.getCatalogList(Constants.AQUACULTURE_CENTER_TYPE);
//		spinnerTipoObras_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoObras));
//
//		spinnerTipoCompObras_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoCompObras_dam);
//		listSpinnerTipoCompObras = db.getCatalogList(Constants.AQUACULTURE_CENTER_TYPE);
//		spinnerTipoCompObras_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCompObras));
//
//		spinnerTipoVal_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoVal_dam);
//		listSpinnerTipoVal = db.getCatalogList(Constants.AQUACULTURE_CENTER_TYPE);
//		spinnerTipoVal_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoVal));
//
//		spinnerTipoCond_dam = (Spinner) myActivity.findViewById(R.id.spinnerTipoCond_dam);
//		listSpinnerTipoCond = db.getCatalogList(Constants.AQUACULTURE_CENTER_TYPE);
//		spinnerTipoCond_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerTipoCond));

		pickerFechaConst_dam = (DatePicker)myActivity.findViewById(R.id.pickerFechaConst_dam);
		pickerFechaRefu_dam = (DatePicker)myActivity.findViewById(R.id.pickerFechaRefu_dam);

//		switchCueRej_dam = (Switch)myActivity.findViewById(R.id.switchCueRej_dam);
		switchImporte2013_dam = (Switch)myActivity.findViewById(R.id.switchImporte2013_dam);
		switchImporte2012_dam = (Switch)myActivity.findViewById(R.id.switchImporte2012_dam);
		switchImporte2011_dam = (Switch)myActivity.findViewById(R.id.switchImporte2011_dam);
		switchImporte2010_dam = (Switch)myActivity.findViewById(R.id.switchImporte2010_dam);
		switchImporte2009_dam = (Switch)myActivity.findViewById(R.id.switchImporte2009_dam);
		switchImporte2008_dam = (Switch)myActivity.findViewById(R.id.switchImporte2008_dam);
		switchImporte2007_dam = (Switch)myActivity.findViewById(R.id.switchImporte2007_dam);
		switchImporte2006_dam = (Switch)myActivity.findViewById(R.id.switchImporte2006_dam);
		switchImporte2005_dam = (Switch)myActivity.findViewById(R.id.switchImporte2005_dam);
		switchImporte2004_dam = (Switch)myActivity.findViewById(R.id.switchImporte2004_dam);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propierty.getDamPptyDetail(propertyID, postalCode);
			editProposito_dam.setText(pojo.getDamVentings().get(0).getVentingPorpouse());
			editTipo_dam.setText(pojo.getDamVentings().get(0).getVentingType());
			editCapaM3sOtros_dam.setText(String.valueOf(pojo.getDamVentings().get(0).getVentingCapacity()));
			editElevaUm_dam.setText(String.valueOf(pojo.getDamVentings().get(0).getThresholdElevation()));
			editDimensiones_dam.setText(String.valueOf(pojo.getDamVentings().get(0).getVentingDimension()));
			editComeOtros_dam.setText(pojo.getDamVentings().get(0).getComments());
			//editNumObra_dam
			editTomaNum_dam.setText(pojo.getDamHeadworks().get(0).getHeadworkNumber());
			spinnerTipoObras_dam.setSelection(getItemPositionByID(listSpinnerTipoObras, pojo.getDamHeadworks().get(0).getHeadworkType()));
			editCapaM3s_dam.setText(String.valueOf(pojo.getDamHeadworks().get(0).getCapacity()));
			editEleM3s_dam.setText(String.valueOf(pojo.getDamHeadworks().get(0).getElevation()));
			editNumCompM3s_dam.setText(pojo.getDamHeadworks().get(0).getGatesNumber());
			spinnerTipoCompObras_dam.setSelection(getItemPositionByID(listSpinnerTipoCompObras, pojo.getDamHeadworks().get(0).getGatesType()));
			editAnchoCompObra_dam.setText(String.valueOf(pojo.getDamHeadworks().get(0).getGatesWidth()));
			editAltuComObra_dam.setText(String.valueOf(pojo.getDamHeadworks().get(0).getGatesHeight()));
			editNumVal_dam.setText(pojo.getDamHeadworks().get(0).getValvesNumber());
			spinnerTipoVal_dam.setSelection(getItemPositionByID(listSpinnerTipoVal, pojo.getDamHeadworks().get(0).getValveType()));
			editNumCond_dam.setText(pojo.getDamHeadworks().get(0).getDuctNumber());
			spinnerTipoCond_dam.setSelection(getItemPositionByID(listSpinnerTipoCond, pojo.getDamHeadworks().get(0).getDuctType()));
			editDimenCond_dam.setText(String.valueOf(pojo.getDamHeadworks().get(0).getDuctDimension()));
			switchCueRej_dam.setChecked(pojo.getDamHeadworks().get(0).isGridExistence());
			editComenObras_dam.setText(pojo.getDamHeadworks().get(0).getComments());

			editImporte2013_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2013_dam
			editDef2013_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2012_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2012_dam
			editDef2012_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2011_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2011_dam
			editDef2011_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2010_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2010_dam
			editDef2010_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2009_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2009_dam
			editDef2009_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2008_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2008_dam
			editDef2008_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2007_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2007_dam
			editDef2007_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2006_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2006_dam
			editDef2006_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2005_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2005_dam
			editDef2005_dam.setText(pojo.getSiniestralityDescription()[0].toString());
			editImporte2004_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2004_dam
			editDef2004_dam.setText(pojo.getSiniestralityDescription()[0].toString());

		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
		propertyID = myActivity.propertyID;
		postalCodeID = myActivity.postalCodeID;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dams4, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	private int getItemPositionByID(List<CatalogItem> p_listaCatalogItems, String id)
	{
		for (CatalogItem c : p_listaCatalogItems)
		{
			if (c.getId().equals(id))
			{
				return p_listaCatalogItems.indexOf(c);
			}
		}
		return 0;
	}

	private class SaveClickListener implements OnClickListener {
//		pojoHistorical pojoHist = new pojoHistorical();
//		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			Toast.makeText(myActivity, "Boton salvar", Toast.LENGTH_LONG).show();
			if (validaCampos()) {


				try
				{
					//process.processHistoricalSave(pojoHist);
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	public boolean validaCampos(){
//		if (editName_historical.getText().length()==0) {
//			Toast.makeText(myActivity, "Se requiere indicar el nombre del bien", Toast.LENGTH_LONG).show();
//			editName_historical.requestFocus();
//			return false;
//		}
		return true;
	}

	private class OutClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
			try {
				KeyOperations.DispachBackKey(myActivity);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public void AnalizaID()
	{
		if (myActivity.propertyID.equals(Constants.GENERICO))
		{
			Toast.makeText(myActivity, "Tienes un ID, puedes continuar...", Toast.LENGTH_LONG).show();
		}
		else
		{
			buttonSaveUp4_5_dam.setEnabled(false);
			buttonSaveBot4_5_dam.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

}
