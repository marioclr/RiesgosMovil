package com.mclr.mini.riesgosmovil.fragmentos;

import java.math.BigDecimal;

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
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoHidraulic;
import com.mclr.mini.riesgosmovil.modelos.pojoHistorical;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class HydraulicFragment2 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
//	String propertyID;
//	String postalCode;
	String postalCodeID; // Este se inicializara en la selección del CP

	ImageButton buttonSaveBot2_2_hydraulic;
	ImageButton buttonOutBot2_2_hydraulic;

	EditText editMontoMantoRedes_hydraulic;
	EditText editValRepNueRed_hydraulic;
	EditText editValObraCivilRepNue_hydraulic;
	EditText editValMaqEquiRepNu_hydraulic;
	EditText editValOtros_hydraulic;
	EditText editValRealRed_hydraulic;
	
	Switch switchMontoMantoRedes_hydraulic;
	Switch switchValRepNueRed_hydraulic;
	Switch switchValObraCivilRepNue_hydraulic;
	Switch switchValMaqEquiRepNu_hydraulic;
	Switch switchValOtros_hydraulic;
	Switch switchValRealRed_hydraulic;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		buttonSaveBot2_2_hydraulic = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_hydraulic);
		buttonSaveBot2_2_hydraulic.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_hydraulic = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_hydraulic);
		buttonOutBot2_2_hydraulic.setOnClickListener(new OutClickListener());

		editMontoMantoRedes_hydraulic = (EditText)myActivity.findViewById(R.id.editMontoMantoRedes_hydraulic);
		editValRepNueRed_hydraulic = (EditText)myActivity.findViewById(R.id.editValRepNueRed_hydraulic);
		editValObraCivilRepNue_hydraulic = (EditText)myActivity.findViewById(R.id.editValObraCivilRepNue_hydraulic);
		editValMaqEquiRepNu_hydraulic = (EditText)myActivity.findViewById(R.id.editValMaqEquiRepNu_hydraulic);
		editValOtros_hydraulic = (EditText)myActivity.findViewById(R.id.editValOtros_hydraulic);
		editValRealRed_hydraulic = (EditText)myActivity.findViewById(R.id.editValRealRed_hydraulic);

		switchMontoMantoRedes_hydraulic = (Switch)myActivity.findViewById(R.id.switchMontoMantoRedes_hydraulic);
		switchValRepNueRed_hydraulic = (Switch)myActivity.findViewById(R.id.switchValRepNueRed_hydraulic);
		switchValObraCivilRepNue_hydraulic = (Switch)myActivity.findViewById(R.id.switchValObraCivilRepNue_hydraulic);
		switchValMaqEquiRepNu_hydraulic = (Switch)myActivity.findViewById(R.id.switchValMaqEquiRepNu_hydraulic);
		switchValOtros_hydraulic = (Switch)myActivity.findViewById(R.id.switchValOtros_hydraulic);
		switchValRealRed_hydraulic = (Switch)myActivity.findViewById(R.id.switchValRealRed_hydraulic);

		propierty = new VWProperties(myActivity);
		if (!myActivity.propertyID.equals(Constants.GENERICO)){
			pojoHidraulic pojo = propierty.getHidraulicPptyDetail(myActivity.propertyID, myActivity.postalCodeID);

			editMontoMantoRedes_hydraulic.setText(String.valueOf(pojo.getAnnualMaintenance()));
			editValRepNueRed_hydraulic.setText(String.valueOf(pojo.getRenewalValue()));
			editValObraCivilRepNue_hydraulic.setText(String.valueOf(pojo.getCivilWorksValue()));
			editValMaqEquiRepNu_hydraulic.setText(String.valueOf(pojo.getEquipmentMachineryValue()));
			editValOtros_hydraulic.setText(String.valueOf(pojo.getItemsRenualValue()));
			// TODO editValRealRed_hydraulic.setText(String.valueOf(pojo.getItemsDetail()));
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
		//propertyID = myActivity.propertyID;
		//postalCodeID = myActivity.postalCodeID;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.hydraulic2, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	public void CerrarBien()
	{
		buttonSaveBot2_2_hydraulic.setEnabled(false);
		//buttonOutBot2_2_hydraulic.setEnabled(false);

		editMontoMantoRedes_hydraulic.setEnabled(false);
		editValRepNueRed_hydraulic.setEnabled(false);
		editValObraCivilRepNue_hydraulic.setEnabled(false);
		editValMaqEquiRepNu_hydraulic.setEnabled(false);
		editValOtros_hydraulic.setEnabled(false);
		editValRealRed_hydraulic.setEnabled(false);

		buttonSaveBot2_2_hydraulic.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_hydraulic.setImageResource(R.mipmap.salir_blocked);
	}

	private class SaveClickListener implements OnClickListener {
		pojoHidraulic pojoHydr;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoHydr = new pojoHidraulic();

			if (validaCampos()) {

				pojoHydr.setPropertyID(myActivity.propertyID);
				SetPojoHydraulicP2(pojoHydr);
				HydraulicFragment hydraulic1 = (HydraulicFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				hydraulic1.SetPojoHydraulicP1(pojoHydr);

				try
				{
					process.processHydraulicSave(pojoHydr);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoHydr.getPropertyID();
					myActivity.postalCodeID = pojoHydr.getPostalCodeID();
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
		if (!myActivity.propertyID.equals(Constants.GENERICO))
		{
			Toast.makeText(myActivity, "Tienes un ID, puedes continuar...", Toast.LENGTH_LONG).show();
		}
		else
		{
			buttonSaveBot2_2_hydraulic.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	public void SetPojoHydraulicP2(pojoHidraulic pojo)
	{
		pojo.setAnnualMaintenance(new BigDecimal(editMontoMantoRedes_hydraulic.getText().toString()));
		pojo.setRenewalValue(new BigDecimal(editValRepNueRed_hydraulic.getText().toString()));
		pojo.setCivilWorksValue(new BigDecimal(editValObraCivilRepNue_hydraulic.getText().toString()));
		pojo.setEquipmentMachineryValue(new BigDecimal(editValMaqEquiRepNu_hydraulic.getText().toString()));
		pojo.setItemsRenualValue(new BigDecimal(editValOtros_hydraulic.getText().toString()));
		// TODO pojo.setItemsDetail(editValRealRed_hydraulic.getText().toString());
	}
}
