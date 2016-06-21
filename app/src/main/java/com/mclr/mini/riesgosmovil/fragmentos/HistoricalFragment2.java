package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoHistorical;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class HistoricalFragment2 extends Fragment {
	//private PropertyAdmin myActivity = null;
	private MainActivity myActivity = null;
	public static final String ARG_HISTORICAL_NUMBER = "historical_number";
	public static final String ARG_HISTORICAL_POSTAL_CODE = "historical_postal_code";
	VWProperties propierty;

	//String propertyID;
	//String postalCode;

	ImageButton buttonSaveUp2_2_historical;
	ImageButton buttonOutUp2_2_historical;
	ImageButton buttonCP_historical;
	ImageButton buttonUbicacion_historical;	
	ImageButton buttonSaveBot2_2_historical;
	ImageButton buttonOutBot2_2_historical;

	EditText editEdificiovr_historical;
	EditText editEdificiovrep_historical;
	Switch switchEdificiovr_historical;
	Switch switchEdificiovrep_historical;

	EditText editEquiposvr_historical;
	EditText editEquiposvrep_historical;
	Switch switchEquiposvr_historical;
	Switch switchEquiposvrep_historical;

	EditText editAuxiliaresvr_historical;
	EditText editAuxiliaresvrep_historical;
	Switch switchAuxiliaresvr_historical;
	Switch switchAuxiliaresvrep_historical;

	EditText editInventariovr_historical;
	EditText editInventariovrep_historical;
	Switch switchInventariovr_historical;
	Switch switchInventariovrep_historical;

	EditText editCdsvr_historical;
	EditText editCdsvrep_historical;
	Switch switchCdsvr_historical;
	Switch switchCdsvrep_historical;

	EditText editOtrosvr_historical;
	EditText editOtrosvrep_historical;
	Switch switchOtrosvr_historical;
	Switch switchOtrosvrep_historical;

	EditText editMueblesvr_historical;
	EditText editMueblesvrep_historical;
	Switch switchMueblesvr_historical;
	Switch switchMueblesvrep_historical;

	EditText editEnseresvr_historical;
	EditText editEnseresvrep_historical;
	Switch switchEnseresvr_historical;
	Switch switchEnseresvrep_historical;

	EditText editTotalVR;
	EditText editTotalVN;

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
		//Log.i("Mario", "Mario: Dummy " + "onCreateView");
		View rootView = inflater.inflate(R.layout.historical2, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//Log.i("Mario", "Mario: Dummy " + "onActivityCreated");
		
		super.onActivityCreated(savedInstanceState);
		editEdificiovr_historical = (EditText) myActivity.findViewById(R.id.editEdificiosVR_historical);
		editEdificiovr_historical.setOnKeyListener(new CambioValores());

		editEdificiovr_historical.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		      @Override
		      public void onFocusChange(View v, boolean hasFocus) {
		        Toast.makeText(myActivity,
		            ((EditText) v).getId() + " has focus - " + hasFocus,
		            Toast.LENGTH_LONG).show();
		      }
		    });		

		editEdificiovrep_historical = (EditText) myActivity.findViewById(R.id.editEdificiosVRN_historical);
		editEdificiovrep_historical.setOnKeyListener(new CambioValores());

		editEdificiovrep_historical.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		      @Override
		      public void onFocusChange(View v, boolean hasFocus) {
		        Toast.makeText(myActivity,
		            ((EditText) v).getId() + " has focus - " + hasFocus,
		            Toast.LENGTH_LONG).show();
		      }
		    });

		switchEdificiovr_historical = (Switch) myActivity.findViewById(R.id.switchEdificiosVR_historical);
		switchEdificiovr_historical.setEnabled(false);
		switchEdificiovrep_historical = (Switch) myActivity.findViewById(R.id.switchEdificiosVRN_historical);
		switchEdificiovrep_historical.setEnabled(false);

		editEquiposvr_historical = (EditText) myActivity.findViewById(R.id.editEquiCompVR_historical);
		editEquiposvrep_historical = (EditText) myActivity.findViewById(R.id.editEquiCompVRN_historical);

		switchEquiposvr_historical = (Switch) myActivity.findViewById(R.id.switchEquiCompVR_historical);
		switchEquiposvr_historical.setEnabled(false);
		switchEquiposvrep_historical = (Switch) myActivity.findViewById(R.id.switchEquiCompVRN_historical);
		switchEquiposvrep_historical.setEnabled(false);

		editAuxiliaresvr_historical = (EditText) myActivity.findViewById(R.id.editEquiEleVR_historical);
		editAuxiliaresvrep_historical = (EditText) myActivity.findViewById(R.id.editEquiElecVRN_historical);

		switchAuxiliaresvr_historical =  (Switch) myActivity.findViewById(R.id.switchEquiElecVR_historical);
		switchAuxiliaresvr_historical.setEnabled(false);
		switchAuxiliaresvrep_historical =  (Switch) myActivity.findViewById(R.id.switchEquiElecVRN_historical);
		switchAuxiliaresvrep_historical.setEnabled(false);

		editInventariovr_historical = (EditText) myActivity.findViewById(R.id.editInvLibrVR_historical);
		editInventariovrep_historical = (EditText) myActivity.findViewById(R.id.editInvLibrVRN_historical);

		switchInventariovr_historical =  (Switch) myActivity.findViewById(R.id.switchInvLibrVR_historical);
		switchInventariovr_historical.setEnabled(false);
		switchInventariovrep_historical =  (Switch) myActivity.findViewById(R.id.switchInvLibrVRN_historical);
		switchInventariovrep_historical.setEnabled(false);

		editCdsvr_historical = (EditText) myActivity.findViewById(R.id.editCDsVR_historical);
		editCdsvrep_historical = (EditText) myActivity.findViewById(R.id.editCDsVRN_historical);

		switchCdsvr_historical =  (Switch) myActivity.findViewById(R.id.switchCDsVR_historical);
		switchCdsvr_historical.setEnabled(false);
		switchCdsvr_historical =  (Switch) myActivity.findViewById(R.id.switchCDsVRN_historical);
		switchCdsvr_historical.setEnabled(false);

		editOtrosvr_historical = (EditText) myActivity.findViewById(R.id.editOtrosMedVR_historical);
		editOtrosvrep_historical = (EditText) myActivity.findViewById(R.id.editOtrosMedVRN_historical);

		switchOtrosvr_historical =  (Switch) myActivity.findViewById(R.id.switchOtrosMedVR_historical);
		switchOtrosvr_historical.setEnabled(false);
		switchOtrosvrep_historical =  (Switch) myActivity.findViewById(R.id.switchOtrosMedVRN_historical);
		switchOtrosvrep_historical.setEnabled(false);

		editMueblesvr_historical = (EditText) myActivity.findViewById(R.id.editMueblesVR_historical);
		editMueblesvrep_historical = (EditText) myActivity.findViewById(R.id.editMueblesVRN_historical);

		switchMueblesvr_historical =  (Switch) myActivity.findViewById(R.id.switchMueblesVR_historical);
		switchMueblesvr_historical.setEnabled(false);
		switchMueblesvrep_historical =  (Switch) myActivity.findViewById(R.id.switchMueblesVRN_historical);
		switchMueblesvrep_historical.setEnabled(false);

		editEnseresvr_historical = (EditText) myActivity.findViewById(R.id.editEnseresVR_historical);
		editEnseresvrep_historical = (EditText) myActivity.findViewById(R.id.editEnseresVRN_historical);

		switchEnseresvr_historical =  (Switch) myActivity.findViewById(R.id.switchEnseresVR_historical);
		switchEnseresvr_historical.setEnabled(false);
		switchEnseresvrep_historical =  (Switch) myActivity.findViewById(R.id.switchEnseresVRN_historical);
		switchEnseresvrep_historical.setEnabled(false);

		editTotalVR = (EditText) myActivity.findViewById(R.id.editTotalVR_historical);
		editTotalVN = (EditText) myActivity.findViewById(R.id.editTotalVRN_historical);

		buttonSaveUp2_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp2_2_historical);
		buttonSaveUp2_2_historical.setOnClickListener(new SaveClickListener());
		buttonOutUp2_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonOutUp2_2_historical);
		buttonOutUp2_2_historical.setOnClickListener(new OutClickListener());

		buttonSaveBot2_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_historical);
		buttonSaveBot2_2_historical.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_historical = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_historical);
		buttonOutBot2_2_historical.setOnClickListener(new OutClickListener());

		propierty = new VWProperties(myActivity);
		if (!myActivity.propertyID.equals(Constants.GENERICO)) {
			pojoHistorical pojo = propierty.getHistoricalPptyDetail(myActivity.propertyID, myActivity.postalCodeID);

			editEdificiovr_historical.setText(String.valueOf(pojo.getRlBuildingValue()));
			editEdificiovrep_historical.setText(String.valueOf(pojo.getRnBuildingValue()));
			//switchEdificio_historical.setChecked(true);

			editEquiposvr_historical.setText(String.valueOf(pojo.getRlComputingValue()));
			editEquiposvrep_historical.setText(String.valueOf(pojo.getRnComputingValue()));
			//switchEquipos_historical.setChecked(true);

			editAuxiliaresvr_historical.setText(String.valueOf(pojo.getRlElectronicValue()));
			editAuxiliaresvrep_historical.setText(String.valueOf(pojo.getRnElectronicValue()));
			//switchAuxiliares_historical.setChecked(true);

			editInventariovr_historical.setText(String.valueOf(pojo.getRlBooksValue()));
			editInventariovrep_historical.setText(String.valueOf(pojo.getRnBooksValue()));
			//switchInventario_historical.setChecked(true);

			editCdsvr_historical.setText(String.valueOf(pojo.getRlCDsValue()));
			editCdsvrep_historical.setText(String.valueOf(pojo.getRnCDsValue()));
			//switchCds_historical.setChecked(true);

			editOtrosvr_historical.setText(String.valueOf(pojo.getRlStorageValue()));
			editOtrosvrep_historical.setText(String.valueOf(pojo.getRnStorageValue()));
			//switchOtros_historical.setChecked(true);

			editMueblesvr_historical.setText(String.valueOf(pojo.getRlFurnituresValue()));
			editMueblesvrep_historical.setText(String.valueOf(pojo.getRnFurnituresValue()));
			//switchMuebles_historical.setChecked(true);

			editEnseresvr_historical.setText(String.valueOf(pojo.getRlItemsValue()));
			editEnseresvrep_historical.setText(String.valueOf(pojo.getRnItemsValue()));
			//switchEnseres_historical.setChecked(true);
		}

		//Toast.makeText(myActivity, "PostalCode: " + postalCode, Toast.LENGTH_LONG).show();
		//Toast.makeText(myActivity, "item: " + pojo.getAddress(), Toast.LENGTH_LONG).show();
	}

	public void SetPojoHistoricalP2(pojoHistorical pojoHist)
	{
		if (editEdificiovr_historical.getText().length()>0) {
			pojoHist.setRlBuildingValue(editEdificiovr_historical.getText().toString());
		}
		if (editEdificiovrep_historical.getText().length()>0) {
			pojoHist.setRnBuildingValue(editEdificiovrep_historical.getText().toString());
		}
		if (editEquiposvr_historical.getText().length()>0) {
			pojoHist.setRlComputingValue(editEquiposvr_historical.getText().toString());
		}
		if (editEquiposvrep_historical.getText().length()>0) {
			pojoHist.setRnComputingValue(editEquiposvrep_historical.getText().toString());
		}
		if (editAuxiliaresvr_historical.getText().length()>0) {
			pojoHist.setRlElectronicValue(editAuxiliaresvr_historical.getText().toString());
		}
		if (editAuxiliaresvrep_historical.getText().length()>0) {
			pojoHist.setRnElectronicValue(editAuxiliaresvrep_historical.getText().toString());
		}
		if (editInventariovr_historical.getText().length()>0) {
			pojoHist.setRlBooksValue(editInventariovr_historical.getText().toString());
		}
		if (editInventariovrep_historical.getText().length()>0) {
			pojoHist.setRnBooksValue(editInventariovrep_historical.getText().toString());
		}
		if (editCdsvr_historical.getText().length()>0) {
			pojoHist.setRlCDsValue(editCdsvr_historical.getText().toString());
		}
		if (editCdsvrep_historical.getText().length()>0) {
			pojoHist.setRnCDsValue(editCdsvrep_historical.getText().toString());
		}
		if (editOtrosvr_historical.getText().length()>0) {
			pojoHist.setRlStorageValue(editOtrosvr_historical.getText().toString());
		}
		if (editOtrosvrep_historical.getText().length()>0) {
			pojoHist.setRnStorageValue(editOtrosvrep_historical.getText().toString());
		}
		if (editMueblesvr_historical.getText().length()>0) {
			pojoHist.setRlFurnituresValue(editMueblesvr_historical.getText().toString());
		}
		if (editMueblesvrep_historical.getText().length()>0) {
			pojoHist.setRnFurnituresValue(editMueblesvrep_historical.getText().toString());
		}
		if (editEnseresvr_historical.getText().length()>0) {
			pojoHist.setRlItemsValue(editEnseresvr_historical.getText().toString());
		}
		if (editEnseresvrep_historical.getText().length()>0) {
			pojoHist.setRnItemsValue(editEnseresvrep_historical.getText().toString());
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
			buttonSaveUp2_2_historical.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	private class CambioValores implements OnKeyListener {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			CalculaTotal();
			return false;
		}
		
	}

	private void CalculaTotal() {
//		BigDecimal totalVR;
//		BigDecimal totalVN;

//		totalVR = new BigDecimal(editEdificiovr_historical.getText().toString())
//			.add(new BigDecimal(editEquiposvr_historical.getText().toString()))
//			.add(new BigDecimal(editAuxiliaresvr_historical.getText().toString()))
//			.add(new BigDecimal(editInventariovr_historical.getText().toString()))
//			.add(new BigDecimal(editCdsvr_historical.getText().toString()))
//			.add(new BigDecimal(editOtrosvr_historical.getText().toString()))
//			.add(new BigDecimal(editMueblesvr_historical.getText().toString()))
//			.add(new BigDecimal(editEnseresvr_historical.getText().toString()));
//		
//		totalVN = new BigDecimal(editEdificiovrep_historical.getText().toString())
//			.add(new BigDecimal(editEquiposvrep_historical.getText().toString()))
//			.add(new BigDecimal(editAuxiliaresvrep_historical.getText().toString()))
//			.add(new BigDecimal(editInventariovrep_historical.getText().toString()))
//			.add(new BigDecimal(editCdsvrep_historical.getText().toString()))
//			.add(new BigDecimal(editOtrosvrep_historical.getText().toString()))
//			.add(new BigDecimal(editMueblesvrep_historical.getText().toString()))
//			.add(new BigDecimal(editEnseresvrep_historical.getText().toString()));

//		editTotalVR.setText(totalVR.toString());
//		editTotalVN.setText(totalVN.toString());
		editTotalVR.setText("");
		editTotalVN.setText("");
	}

	public void CerrarBien()
	{
		buttonSaveUp2_2_historical.setEnabled(false);
		//buttonOutUp2_2_historical.setEnabled(false);
		buttonCP_historical.setEnabled(false);
		buttonUbicacion_historical.setEnabled(false);	
		buttonSaveBot2_2_historical.setEnabled(false);
		//buttonOutBot2_2_historical.setEnabled(false);

		editEdificiovr_historical.setEnabled(false);
		editEdificiovrep_historical.setEnabled(false);

		editEquiposvr_historical.setEnabled(false);
		editEquiposvrep_historical.setEnabled(false);

		editAuxiliaresvr_historical.setEnabled(false);
		editAuxiliaresvrep_historical.setEnabled(false);

		editInventariovr_historical.setEnabled(false);
		editInventariovrep_historical.setEnabled(false);

		editCdsvr_historical.setEnabled(false);
		editCdsvrep_historical.setEnabled(false);

		editOtrosvr_historical.setEnabled(false);
		editOtrosvrep_historical.setEnabled(false);

		editMueblesvr_historical.setEnabled(false);
		editMueblesvrep_historical.setEnabled(false);

		editEnseresvr_historical.setEnabled(false);
		editEnseresvrep_historical.setEnabled(false);

		editTotalVR.setEnabled(false);
		editTotalVN.setEnabled(false);

		buttonSaveUp2_2_historical.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp2_2_historical.setImageResource(R.mipmap.salir_blocked);
		//buttonCP_historical.setImageResource(R.mipmap.guardar_blocked);
		buttonUbicacion_historical.setImageResource(R.mipmap.ubicacion_blocked);
		buttonSaveBot2_2_historical.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_historical.setImageResource(R.mipmap.salir_blocked);
	}

	private class SaveClickListener implements OnClickListener {
		pojoHistorical pojoHist;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoHist = new pojoHistorical();

			if (validaCampos()) {

				pojoHist.setPropertyID(myActivity.propertyID);
				SetPojoHistoricalP2(pojoHist);
				HistoricalFragment historico1 = (HistoricalFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				historico1.SetPojoHistoricalP1(pojoHist);
				try
				{
					process.processHistoricalSave(pojoHist);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoHist.getPropertyID();
					myActivity.postalCodeID = pojoHist.getPostalCodeID();
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
				KeyOperations.DispachBackKey(myActivity);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean validaCampos(){
		return true;
	}
}
