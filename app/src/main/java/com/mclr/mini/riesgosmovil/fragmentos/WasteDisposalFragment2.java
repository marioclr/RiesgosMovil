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
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoWasteDisposal;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class WasteDisposalFragment2 extends Fragment {
	private MainActivity myActivity = null;
	public static final String ARG_WASTE_DISPOSAL_NUMBER = "waste_disposal_number";
	public static final String ARG_WASTE_DISPOSAL_POSTAL_CODE = "waste_disposal_postal_code";
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	//String propertyID;
	//String postalCode;
	//String postalCodeID; // Este se inicializara en la selección del CP

	ImageButton buttonSaveBot2_2_waste_disposal;
	ImageButton buttonOutBot2_2_waste_disposal;
	
	EditText editValRepNue_waste_disposal;
	EditText editValObraCiv_waste_disposal;
	EditText editValMaqEqu_waste_disposal;
	EditText editMontoDestMant_waste_disposal;
	
	Switch switchValRepNue_waste_disposal;
	Switch switchValObraCiv_waste_disposal;
	Switch switchValMaqEqu_waste_disposal;
	Switch switchMontoDestMant_waste_disposal;

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.waste2, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		buttonSaveBot2_2_waste_disposal = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_waste_disposal);
		buttonSaveBot2_2_waste_disposal.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_waste_disposal = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_waste_disposal);
		buttonOutBot2_2_waste_disposal.setOnClickListener(new OutClickListener());
		
		editValRepNue_waste_disposal = (EditText)myActivity.findViewById(R.id.editValRepNue_waste_disposal);
		switchValRepNue_waste_disposal = (Switch)myActivity.findViewById(R.id.switchValRepNue_waste_disposal);
		editValObraCiv_waste_disposal = (EditText)myActivity.findViewById(R.id.editValObraCiv_waste_disposal);
		switchValObraCiv_waste_disposal = (Switch)myActivity.findViewById(R.id.switchValObraCiv_waste_disposal);
		editValMaqEqu_waste_disposal = (EditText)myActivity.findViewById(R.id.editValMaqEqu_waste_disposal);
		switchValMaqEqu_waste_disposal = (Switch)myActivity.findViewById(R.id.switchValMaqEqu_waste_disposal);
		editMontoDestMant_waste_disposal = (EditText)myActivity.findViewById(R.id.editMontoDestMant_waste_disposal);
		switchMontoDestMant_waste_disposal = (Switch)myActivity.findViewById(R.id.switchMontoDestMant_waste_disposal);
		
		propierty = new VWProperties(myActivity);
		if (!myActivity.propertyID.equals(Constants.GENERICO)) {
			pojoWasteDisposal pojo = propierty.getWasteDisposalPptyDetail(myActivity.propertyID, myActivity.postalCodeID);
			editValRepNue_waste_disposal.setText(String.valueOf(pojo.getRenewalValue()));
			//switchValRepNue_waste_disposal
			editValObraCiv_waste_disposal.setText(String.valueOf(pojo.getCivilWorksValue()));
			//switchValObraCiv_waste_disposal
			editValMaqEqu_waste_disposal.setText(String.valueOf(pojo.getMachineryEquipmentValue()));
			//switchValMaqEqu_waste_disposal
			editMontoDestMant_waste_disposal.setText(String.valueOf(pojo.getAnnualMaintenanceAmount()));
			//switchMontoDestMant_waste_disposal
			
		}
	}

	public void CerrarBien()
	{
		buttonSaveBot2_2_waste_disposal.setEnabled(false);
		//buttonOutBot2_2_waste_disposal.setEnabled(false);

		editValRepNue_waste_disposal.setEnabled(false);
		editValObraCiv_waste_disposal.setEnabled(false);
		editValMaqEqu_waste_disposal.setEnabled(false);
		editMontoDestMant_waste_disposal.setEnabled(false);

		buttonSaveBot2_2_waste_disposal.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_waste_disposal.setImageResource(R.mipmap.salir_blocked);
	}

	public boolean validaCampos() {
		return true;
	}

	private class SaveClickListener implements OnClickListener {
		pojoWasteDisposal pojoWaste;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoWaste = new pojoWasteDisposal();

			if (validaCampos()) {

				pojoWaste.setPropertyID(myActivity.propertyID);
				SetPojoWasteP2(pojoWaste);
				WasteDisposalFragment waste = (WasteDisposalFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				waste.SetPojoWasteP1(pojoWaste);
				try
				{
					process.processWasteSave(pojoWaste);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoWaste.getPropertyID();
					myActivity.postalCodeID = pojoWaste.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	private class OutClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			//Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
			try {
				KeyOperations.DispachBackKey(myActivity);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void SetPojoWasteP2(pojoWasteDisposal pojo) {
		if (editValRepNue_waste_disposal.getText().length()>0) {
			pojo.setRenewalValue(editValRepNue_waste_disposal.getText().toString());
		}if (editValObraCiv_waste_disposal.getText().length()>0) {
			pojo.setCivilWorksValue(editValObraCiv_waste_disposal.getText().toString());
		}if (editValMaqEqu_waste_disposal.getText().length()>0) {
			pojo.setMachineryEquipmentValue(editValMaqEqu_waste_disposal.getText().toString());
		}if (editMontoDestMant_waste_disposal.getText().length()>0) {
			pojo.setAnnualMaintenanceAmount(editMontoDestMant_waste_disposal.getText().toString());
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
			buttonSaveBot2_2_waste_disposal.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar fotos del bien debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}
}

