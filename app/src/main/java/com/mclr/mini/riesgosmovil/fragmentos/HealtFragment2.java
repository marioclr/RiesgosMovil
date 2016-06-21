package com.mclr.mini.riesgosmovil.fragmentos;

import java.math.BigDecimal;

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
import com.mclr.mini.riesgosmovil.modelos.pojoHealt;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class HealtFragment2 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
//	String propertyID;
//	String postalCode;
	String postalCodeID; // Este se inicializara en la selección del CP

	ImageButton buttonSaveBot2_2_healt;
	ImageButton buttonOutBot2_2_healt;
	
	EditText editValRepNueEdi_healt;
	EditText editValRepNueCon_healt;
	EditText editValRepNueEquiDiag_healt;
	EditText editValRepNueEquiCom_healt;
	EditText editTotalValRepNue_healt;
	EditText editValRepMueEns_healt;
	
	Switch switchValRepNueEdi_healt;
	Switch switchValRepNueCon_healt;
	Switch switchValRepNueEquiDiag_healt;
	Switch switchValRepNueEquiCom_healt;
	Switch switchTotalValRepNue_healt;
	Switch switchValRepMueEns_healt;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		buttonSaveBot2_2_healt = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_healt);
		buttonSaveBot2_2_healt.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_healt = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_healt);
		buttonOutBot2_2_healt.setOnClickListener(new OutClickListener());
	
		editValRepNueEdi_healt = (EditText)myActivity.findViewById(R.id.editValRepNueEdi_healt);
		editValRepNueCon_healt = (EditText)myActivity.findViewById(R.id.editValRepNueCon_healt);
		editValRepNueEquiDiag_healt = (EditText)myActivity.findViewById(R.id.editValRepNueEquiDiag_healt);
		editValRepNueEquiCom_healt = (EditText)myActivity.findViewById(R.id.editValRepNueEquiCom_healt);
		editTotalValRepNue_healt = (EditText)myActivity.findViewById(R.id.editTotalValRepNue_healt);
		editValRepMueEns_healt = (EditText)myActivity.findViewById(R.id.editValRepMueEns_healt);

		switchValRepNueEdi_healt = (Switch)myActivity.findViewById(R.id.switchValRepNueEdi_healt);
		switchValRepNueCon_healt = (Switch)myActivity.findViewById(R.id.switchValRepNueCon_healt);
		switchValRepNueEquiDiag_healt = (Switch)myActivity.findViewById(R.id.switchValRepNueEquiDiag_healt);
		switchValRepNueEquiCom_healt = (Switch)myActivity.findViewById(R.id.switchValRepNueEquiCom_healt);
		switchTotalValRepNue_healt = (Switch)myActivity.findViewById(R.id.switchTotalValRepNue_healt);
		switchValRepMueEns_healt = (Switch)myActivity.findViewById(R.id.switchValRepMueEns_healt);

		propierty = new VWProperties(myActivity);
		if (!myActivity.propertyID.equals(Constants.GENERICO)) {
			pojoHealt pojo = propierty.getHealtPptyDetail(myActivity.propertyID, myActivity.postalCodeID);

			//editLongitud_healt.setText(String.valueOf(pojo.getLongitude()));
			editValRepNueEdi_healt.setText(String.valueOf(pojo.getBuilningRenewalValue()));
			editValRepNueCon_healt.setText(String.valueOf(pojo.getMedicalRenewalValue()));
			editValRepNueEquiDiag_healt.setText(String.valueOf(pojo.getLabMaterialRenewalValue()));
			editValRepNueEquiCom_healt.setText(String.valueOf(pojo.getElectronicRenewalValue()));
			editTotalValRepNue_healt.setText(String.valueOf(pojo.getItemRenewalValue()));

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
		View rootView = inflater.inflate(R.layout.healt2, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	private class SaveClickListener implements OnClickListener {
		pojoHealt pojoHeal;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoHeal = new pojoHealt();

			if (validaCampos()) {

				pojoHeal.setPropertyID(myActivity.propertyID);
				SetPojoHealtP2(pojoHeal);
				HealtFragment education1 = (HealtFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				education1.SetPojoHealtP1(pojoHeal);

				try
				{
					process.processHealtSave(pojoHeal);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoHeal.getPropertyID();
					myActivity.postalCodeID = pojoHeal.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	public void CerrarBien()
	{
		buttonSaveBot2_2_healt.setEnabled(false);
		//buttonOutBot2_2_healt.setEnabled(false);

		editValRepNueEdi_healt.setEnabled(false);
		editValRepNueCon_healt.setEnabled(false);
		editValRepNueEquiDiag_healt.setEnabled(false);
		editValRepNueEquiCom_healt.setEnabled(false);
		editTotalValRepNue_healt.setEnabled(false);
		editValRepMueEns_healt.setEnabled(false);

		buttonSaveBot2_2_healt.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_healt.setImageResource(R.mipmap.salir_blocked);
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
			buttonSaveBot2_2_healt.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	public void SetPojoHealtP2(pojoHealt pojo)
	{
		pojo.setBuilningRenewalValue(new BigDecimal(editValRepNueEdi_healt.getText().toString()));
		pojo.setMedicalRenewalValue(new BigDecimal(editValRepNueCon_healt.getText().toString()));
		pojo.setLabMaterialRenewalValue(new BigDecimal(editValRepNueEquiDiag_healt.getText().toString()));
		pojo.setElectronicRenewalValue(new BigDecimal(editValRepNueEquiCom_healt.getText().toString()));
		pojo.setItemRenewalValue(new BigDecimal(editTotalValRepNue_healt.getText().toString()));
	}

}
