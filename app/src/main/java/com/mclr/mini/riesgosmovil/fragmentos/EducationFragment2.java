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
import com.mclr.mini.riesgosmovil.modelos.pojoEducation;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;

public class EducationFragment2 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;

//	String propertyID;
//	String postalCode;

	ImageButton buttonSaveUp2_2_education;
	ImageButton	buttonOutUp2_2_education;
	ImageButton buttonSaveBot2_2_education;
	ImageButton buttonOutBot2_2_education;

	
	EditText editEdificiosVR_education;
	EditText editEdificiosVRN_education;
	EditText editEquiCompVR_education;
	EditText editEquiCompVRN_education;
	EditText editEquiElecVR_education;
	EditText editEquiElecVRN_education;
	EditText editInvLibrVR_education;
	EditText editInvLibrVRN_education;
	EditText editCDsVR_education;
	EditText editCDsVRN_education;
	EditText editOtrosMedVR_education;
	EditText editOtrosMedVRN_education;
	EditText editMueblesVR_education;
	EditText editMueblesVRN_education;
	EditText editEnseresVR_education;
	EditText editEnseresVRN_education;
	EditText editTotalVR_education;
	EditText editTotalVRN_education;
	
	Switch switchEdificiosVR_education;
	Switch switchEdificiosVRN_education;
	Switch switchEquiCompVR_education;
	Switch switchEquiCompVRN_education;
	Switch switchEquiElecVR_education;
	Switch switchEquiElecVRN_education;
	Switch switchInvLibrVR_education;
	Switch switchInvLibrVRN_education;
	Switch switchCDsVR_education;
	Switch switchCDsVRN_education;
	Switch switchOtrosMedVR_education;
	Switch switchOtrosMedVRN_education;
	Switch switchMueblesVR_education;
	Switch switchMueblesVRN_education;
	Switch switchEnseresVR_education;
	Switch switchEnseresVRN_education;

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		buttonSaveUp2_2_education = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp2_2_education);
		buttonSaveUp2_2_education.setOnClickListener(new SaveClickListener());
		buttonOutUp2_2_education = (ImageButton)myActivity.findViewById(R.id.buttonOutUp2_2_education);
		buttonOutUp2_2_education.setOnClickListener(new OutClickListener());

		buttonSaveBot2_2_education = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_education);
		buttonSaveBot2_2_education.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_education = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_education);
		buttonOutBot2_2_education.setOnClickListener(new OutClickListener());

		editEdificiosVR_education = (EditText)myActivity.findViewById(R.id.editEdificiosVR_education);
		editEdificiosVRN_education = (EditText)myActivity.findViewById(R.id.editEdificiosVRN_education);
		editEquiCompVR_education = (EditText)myActivity.findViewById(R.id.editEquiCompVR_education);
		editEquiCompVRN_education = (EditText)myActivity.findViewById(R.id.editEquiCompVRN_education);
		editEquiElecVR_education = (EditText)myActivity.findViewById(R.id.editEquiElecVR_education);
		editEquiElecVRN_education = (EditText)myActivity.findViewById(R.id.editEquiElecVRN_education);
		editInvLibrVR_education = (EditText)myActivity.findViewById(R.id.editInvLibrVR_education);
		editInvLibrVRN_education = (EditText)myActivity.findViewById(R.id.editInvLibrVRN_education);
		editTotalVRN_education = (EditText)myActivity.findViewById(R.id.editTotalVRN_education);
		editCDsVR_education = (EditText)myActivity.findViewById(R.id.editCDsVR_education);
		editCDsVRN_education = (EditText)myActivity.findViewById(R.id.editCDsVRN_education);
		editOtrosMedVR_education = (EditText)myActivity.findViewById(R.id.editOtrosMedVR_education);
		editOtrosMedVRN_education = (EditText)myActivity.findViewById(R.id.editOtrosMedVRN_education);
		editMueblesVR_education = (EditText)myActivity.findViewById(R.id.editMueblesVR_education);
		editMueblesVRN_education = (EditText)myActivity.findViewById(R.id.editMueblesVRN_education);
		editEnseresVR_education = (EditText)myActivity.findViewById(R.id.editEnseresVR_education);
		editEnseresVRN_education = (EditText)myActivity.findViewById(R.id.editEnseresVRN_education);
		editTotalVR_education = (EditText)myActivity.findViewById(R.id.editTotalVR_education);
		editTotalVRN_education = (EditText)myActivity.findViewById(R.id.editTotalVRN_education);

		switchEdificiosVR_education = (Switch) myActivity.findViewById(R.id.switchEdificiosVR_education);
		switchEdificiosVRN_education = (Switch) myActivity.findViewById(R.id.switchEdificiosVRN_education);
		switchEquiCompVR_education = (Switch) myActivity.findViewById(R.id.switchEquiCompVR_education);
		switchEquiCompVRN_education = (Switch) myActivity.findViewById(R.id.switchEquiCompVRN_education);
		switchEquiElecVR_education = (Switch) myActivity.findViewById(R.id.switchEquiElecVR_education);
		switchEquiElecVRN_education = (Switch) myActivity.findViewById(R.id.switchEquiElecVRN_education);
		switchInvLibrVR_education = (Switch) myActivity.findViewById(R.id.switchInvLibrVR_education);
		switchInvLibrVRN_education = (Switch) myActivity.findViewById(R.id.switchInvLibrVRN_education);
		switchCDsVR_education = (Switch) myActivity.findViewById(R.id.switchCDsVR_education);
		switchCDsVRN_education = (Switch) myActivity.findViewById(R.id.switchCDsVRN_education);
		switchOtrosMedVR_education = (Switch) myActivity.findViewById(R.id.switchOtrosMedVR_education);
		switchMueblesVR_education = (Switch) myActivity.findViewById(R.id.switchMueblesVR_education);
		switchEnseresVR_education = (Switch) myActivity.findViewById(R.id.switchEnseresVR_education);
		propierty = new VWProperties(myActivity);
		if (myActivity.propertyID != null){
			pojoEducation pojo = propierty.getEcuationPptyDetail(myActivity.propertyID, myActivity.postalCodeID);

			if (editEdificiosVR_education.getText().length()>0) {
				editEdificiosVR_education.setText(String.valueOf(pojo.getRlBuildingValue()));
			}
			if (editEdificiosVRN_education.getText().length()>0) {
				editEdificiosVRN_education.setText(String.valueOf(pojo.getRnBuildingValue()));
			}
			//switchEdificio_historical.setChecked(true);
	
			editEquiCompVR_education.setText(String.valueOf(pojo.getRlComputingValue()));
			editEquiCompVRN_education.setText(String.valueOf(pojo.getRnComputingValue()));
			//switchEquipos_historical.setChecked(true);
	
			editEquiElecVR_education.setText(String.valueOf(pojo.getRlElectronicValue()));
			editEquiElecVRN_education.setText(String.valueOf(pojo.getRnElectronicValue()));
			//switchAuxiliares_historical.setChecked(true);
	
			editInvLibrVR_education.setText(String.valueOf(pojo.getRlBooksValue()));
			editInvLibrVRN_education.setText(String.valueOf(pojo.getRnBooksValue()));
			//switchInventario_historical.setChecked(true);
	
			editCDsVR_education.setText(String.valueOf(pojo.getRlCDsValue()));
			editCDsVRN_education.setText(String.valueOf(pojo.getRnCDsValue()));
			//switchCds_historical.setChecked(true);
	
			editOtrosMedVR_education.setText(String.valueOf(pojo.getRlStorageValue()));
			editOtrosMedVRN_education.setText(String.valueOf(pojo.getRnStorageValue()));
			//switchOtros_historical.setChecked(true);
	
			editMueblesVR_education.setText(String.valueOf(pojo.getRlFurnituresValue()));
			editMueblesVRN_education.setText(String.valueOf(pojo.getRnFurnituresValue()));
			//switchMuebles_historical.setChecked(true);
	
			editEnseresVR_education.setText(String.valueOf(pojo.getRlItemsValue()));
			editEnseresVRN_education.setText(String.valueOf(pojo.getRnItemsValue()));
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.educacion_2_2, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	private class SaveClickListener implements OnClickListener {
		pojoEducation pojoEdu;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoEdu = new pojoEducation();

			if (validaCampos()) {

				pojoEdu.setPropertyID(myActivity.propertyID);
				SetPojoEducationP2(pojoEdu);
				EducationFragment education1 = (EducationFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				education1.SetPojoEducationP1(pojoEdu);

				try
				{
					process.processEducationSave(pojoEdu);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoEdu.getPropertyID();
					myActivity.postalCodeID = pojoEdu.getPostalCodeID();
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
			Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
			try {
				myActivity.finish();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
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
			buttonSaveUp2_2_education.setEnabled(false);
			buttonSaveBot2_2_education.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	public void CerrarBien()
	{
		buttonSaveUp2_2_education.setEnabled(false);
		//buttonOutUp2_2_education.setEnabled(false);
		buttonSaveBot2_2_education.setEnabled(false);
		//buttonOutBot2_2_education.setEnabled(false);
		
		editEdificiosVR_education.setEnabled(false);
		editEdificiosVRN_education.setEnabled(false);
		editEquiCompVR_education.setEnabled(false);
		editEquiCompVRN_education.setEnabled(false);
		editEquiElecVR_education.setEnabled(false);
		editEquiElecVRN_education.setEnabled(false);
		editInvLibrVR_education.setEnabled(false);
		editInvLibrVRN_education.setEnabled(false);
		editCDsVR_education.setEnabled(false);
		editCDsVRN_education.setEnabled(false);
		editOtrosMedVR_education.setEnabled(false);
		editOtrosMedVRN_education.setEnabled(false);
		editMueblesVR_education.setEnabled(false);
		editMueblesVRN_education.setEnabled(false);
		editEnseresVR_education.setEnabled(false);
		editEnseresVRN_education.setEnabled(false);
		editTotalVR_education.setEnabled(false);
		editTotalVRN_education.setEnabled(false);

		buttonSaveUp2_2_education.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp2_2_education.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot2_2_education.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_education.setImageResource(R.mipmap.salir_blocked);
	}

	public boolean validaCampos(){
//		if (editName_historical.getText().length()==0) {
//			Toast.makeText(myActivity, "Se requiere indicar el nombre del bien", Toast.LENGTH_LONG).show();
//			editName_historical.requestFocus();
//			return false;
//		}
		return true;
	}

	public void SetPojoEducationP2(pojoEducation pojo)
	{
		if (editEdificiosVR_education.getText().length()>0) {
			pojo.setRlBuildingValue(editEdificiosVR_education.getText().toString());
		}

		if (editEdificiosVRN_education.getText().length()>0) {
			pojo.setRnBuildingValue(editEdificiosVRN_education.getText().toString());
		}

		if (editEquiCompVR_education.getText().length()>0) {
			pojo.setRlComputingValue(editEquiCompVR_education.getText().toString());
		}

		if (editEquiCompVRN_education.getText().length()>0) {
			pojo.setRnComputingValue(editEquiCompVRN_education.getText().toString());
		}
		//switchEquipos_historical.setChecked(true);

		if (editEquiElecVR_education.getText().length()>0) {
			pojo.setRlElectronicValue(editEquiElecVR_education.getText().toString());
		}

		if (editEquiElecVRN_education.getText().length()>0) {
			pojo.setRnElectronicValue(editEquiElecVRN_education.getText().toString());
		}
		//switchAuxiliares_historical.setChecked(true);

		if (editInvLibrVR_education.getText().length()>0) {
			pojo.setRlBooksValue(editInvLibrVR_education.getText().toString());
		}

		if (editInvLibrVRN_education.getText().length()>0) {
			pojo.setRnBooksValue(editInvLibrVRN_education.getText().toString());
		}
		//switchInventario_historical.setChecked(true);

		if (editCDsVR_education.getText().length()>0) {
			pojo.setRlCDsValue(editCDsVR_education.getText().toString());
		}

		if (editCDsVRN_education.getText().length()>0) {
			pojo.setRnCDsValue(editCDsVRN_education.getText().toString());
		}
		//switchCds_historical.setChecked(true);

		if (editOtrosMedVR_education.getText().length()>0) {
			pojo.setRlStorageValue(editOtrosMedVR_education.getText().toString());
		}

		if (editOtrosMedVRN_education.getText().length()>0) {
			pojo.setRnStorageValue(editOtrosMedVRN_education.getText().toString());
		}
		//switchOtros_historical.setChecked(true);

		if (editMueblesVR_education.getText().length()>0) {
			pojo.setRlFurnituresValue(editMueblesVR_education.getText().toString());
		}

		if (editMueblesVRN_education.getText().length()>0) {
			pojo.setRnFurnituresValue(editMueblesVRN_education.getText().toString());
		}
		//switchMuebles_historical.setChecked(true);

		if (editEnseresVR_education.getText().length()>0) {
			pojo.setRlItemsValue(editEnseresVR_education.getText().toString());
		}

		if (editEnseresVRN_education.getText().length()>0) {
			pojo.setRnItemsValue(editEnseresVRN_education.getText().toString());
		}
	}
}
