package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class DamFragment5 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCode;
	String postalCodeID; // Este se inicializara en la selección del CP

	ImageButton buttonSaveUp5_5_dam;
	ImageButton buttonOutUp5_5_dam;
	ImageButton buttonSaveBot5_5_dam;
	ImageButton buttonOuBot5_5_dam;

	EditText editTall_dam;
	EditText editAlma_dam;
	EditText editAli_dam;
	EditText editTuneDuct_dam;
	EditText editCuePre_dam;
	EditText editObraToma_dam;
	EditText editCasaMaq_dam;
	EditText editInstaEle_dam;
	EditText editSubDuct_dam;
	EditText editOtros1_dam;
	EditText editOtros2_dam;
	EditText editOtros3_dam;
	EditText editEspe1_dam;
	EditText editEspe2_dam;
	EditText editEspe3_dam;
	EditText editTotalVal_dam;

	Switch switchTall_dam;
	Switch switchAlma_dam;
	Switch switchAli_dam;
	Switch switchTuneDuct_dam;
	Switch switchCuePre_dam;
	Switch switchObraToma_dam;
	Switch switchCasaMaq_dam;
	Switch switchInstaEle_dam;
	Switch switchSubDuct_dam;
	Switch switchOtros1_dam;
	Switch switchOtros2_dam;
	Switch switchOtros3_dam;
	Switch switchTotalVal_dam;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		DatabaseHandler db = new DatabaseHandler(myActivity);

		buttonSaveUp5_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp5_5_dam);
		buttonSaveUp5_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutUp5_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutUp5_5_dam);
		buttonOutUp5_5_dam.setOnClickListener(new OutClickListener());

		buttonSaveBot5_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot5_5_dam);
		buttonSaveBot5_5_dam.setOnClickListener(new SaveClickListener());
		buttonOuBot5_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOuBot5_5_dam);
		buttonOuBot5_5_dam.setOnClickListener(new OutClickListener());

		editTall_dam = (EditText) myActivity.findViewById(R.id.editTall_dam);
		editAlma_dam = (EditText) myActivity.findViewById(R.id.editAlma_dam);
		editAli_dam = (EditText) myActivity.findViewById(R.id.editAli_dam);
		editTuneDuct_dam = (EditText) myActivity.findViewById(R.id.editTuneDuct_dam);
		editCuePre_dam = (EditText) myActivity.findViewById(R.id.editCuePre_dam);
		editObraToma_dam = (EditText) myActivity.findViewById(R.id.editObraToma_dam);
		editCasaMaq_dam = (EditText) myActivity.findViewById(R.id.editCasaMaq_dam);
		editInstaEle_dam = (EditText) myActivity.findViewById(R.id.editInstaEle_dam);
		editSubDuct_dam = (EditText) myActivity.findViewById(R.id.editSubDuct_dam);
		editOtros1_dam = (EditText) myActivity.findViewById(R.id.editOtros1_dam);
		editEspe1_dam = (EditText) myActivity.findViewById(R.id.editEspe1_dam);
		editOtros2_dam = (EditText) myActivity.findViewById(R.id.editOtros2_dam);
		editEspe2_dam = (EditText) myActivity.findViewById(R.id.editEspe2_dam);
		editOtros3_dam = (EditText) myActivity.findViewById(R.id.editOtros3_dam);
		editEspe3_dam = (EditText) myActivity.findViewById(R.id.editEspe3_dam);
		editTotalVal_dam = (EditText) myActivity.findViewById(R.id.editTotalVal_dam);

		switchTall_dam = (Switch)myActivity.findViewById(R.id.switchTall_dam);
		switchAlma_dam = (Switch)myActivity.findViewById(R.id.switchAlma_dam);
		switchAli_dam = (Switch)myActivity.findViewById(R.id.switchAli_dam);
		switchTuneDuct_dam = (Switch)myActivity.findViewById(R.id.switchTuneDuct_dam);
		switchCuePre_dam = (Switch)myActivity.findViewById(R.id.switchCuePre_dam);
		switchObraToma_dam = (Switch)myActivity.findViewById(R.id.switchObraToma_dam);
		switchCasaMaq_dam = (Switch)myActivity.findViewById(R.id.switchCasaMaq_dam);
		switchInstaEle_dam = (Switch)myActivity.findViewById(R.id.switchInstaEle_dam);
		switchSubDuct_dam = (Switch)myActivity.findViewById(R.id.switchSubDuct_dam);
		switchOtros1_dam = (Switch)myActivity.findViewById(R.id.switchOtros1_dam);
		switchOtros2_dam = (Switch)myActivity.findViewById(R.id.switchOtros2_dam);
		switchOtros3_dam = (Switch)myActivity.findViewById(R.id.switchOtros3_dam);
		switchTotalVal_dam = (Switch)myActivity.findViewById(R.id.switchTotalVal_dam);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propierty.getDamPptyDetail(propertyID, postalCode);
			editTall_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchTall_dam
			editAlma_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchAlma_dam
			editAli_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchAli_dam
			editCuePre_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchCuePre_dam
			editObraToma_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchObraToma_dam
			editCasaMaq_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchCasaMaq_dam
			editSubDuct_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchSubDuct_dam
			editInstaEle_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchInstaEle_dam
			editTuneDuct_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchTuneDuct_dam
			editOtros1_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchOtros1_dam
			editEspe1_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			editOtros2_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchOtros2_dam
			editEspe2_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			editOtros3_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchOtros3_dam
			editEspe3_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			editTotalVal_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchTotalVal_dam

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
		View rootView = inflater.inflate(R.layout.dams5, container, false);
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
			buttonSaveUp5_5_dam.setEnabled(false);
			buttonSaveBot5_5_dam.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}
}
