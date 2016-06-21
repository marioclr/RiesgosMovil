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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListOperations;

import java.util.List;

public class DamFragment2 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;
	CodigoPostalDialogFragment selectCP;
	String propertyID;
	String postalCodeID;

	ImageButton buttonSaveUp2_5_dam;
	ImageButton buttonOutUp2_5_dam;
	ImageButton buttonSaveBot2_5_dam;
	ImageButton buttonOutBot2_5_dam;

	EditText editLongiM_dam;
	EditText editAltuM_dam;
	EditText editAnchoBase_dam;
	EditText editAnchoCoro_dam;
	EditText editTaluArri_dam;
	EditText editTaluAba_dam;
	EditText editPreciAnu_dam;
	EditText editPreciMen_dam;
	EditText editPreciDia_dam;
	EditText editCapaCanal_dam;
	//
	EditText editGasMax_dam;
	EditText editPeriRet_dam;
	EditText editVolAve_dam;
	EditText editVolAveMax_dam;
	EditText editGasMaxReg_dam;
	
	EditText editCapaCauce_dam;
	EditText editNumPresas_dam;
	EditText editNomPresas_dam;
	EditText editInstEspe_dam;
	EditText editComenCauces_dam;
	EditText editName_dam;
	EditText editNamo_dam;
	EditText editNamino_dam;
	EditText editVolName_dam;
	EditText editVolNamo_dam;
	EditText editVolNamino_dam;
	EditText editVolAzol_dam;
	EditText editVolUtil_dam;
	EditText editSuperalmacenamiento_dam;
	EditText editVolConser_dam;
	EditText editVolConAve_dam;

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

	Spinner spinnerLocaTipo_dam;
	List<CatalogItem> listSpinnerLocaTipo;
	Spinner spinnerLocaCasa_dam;
	List<CatalogItem> listSpinnerLocaCasa;

	Switch switchPreciAnu_dam;
	Switch switchPreciMen_dam;
	Switch switchPreciDia_dam;
	Switch switchAtaguia_dam;

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

		buttonSaveUp2_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp2_5_dam);
		buttonSaveUp2_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutUp2_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutUp2_5_dam);
		buttonOutUp2_5_dam.setOnClickListener(new OutClickListener());

		buttonSaveBot2_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_5_dam);
		buttonSaveBot2_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutBot2_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_5_dam);
		buttonOutBot2_5_dam.setOnClickListener(new OutClickListener());

		editLongiM_dam = (EditText) myActivity.findViewById(R.id.editLongiM_dam);
		editAltuM_dam = (EditText) myActivity.findViewById(R.id.editAltuM_dam);
		editAnchoBase_dam = (EditText) myActivity.findViewById(R.id.editAnchoBase_dam);
		editAnchoCoro_dam = (EditText) myActivity.findViewById(R.id.editAnchoCoro_dam);
		editTaluArri_dam = (EditText) myActivity.findViewById(R.id.editTaluArri_dam);
		editTaluAba_dam = (EditText) myActivity.findViewById(R.id.editTaluAba_dam);
		editPreciAnu_dam = (EditText) myActivity.findViewById(R.id.editPreciAnu_dam);
		editPreciMen_dam = (EditText) myActivity.findViewById(R.id.editPreciMen_dam);
		editPreciDia_dam = (EditText) myActivity.findViewById(R.id.editPreciDia_dam);
		
		editGasMax_dam = (EditText) myActivity.findViewById(R.id.editGasMax_dam);
		editPeriRet_dam = (EditText) myActivity.findViewById(R.id.editPeriRet_dam);
		editVolAve_dam = (EditText) myActivity.findViewById(R.id.editVolAve_dam);
		editVolAveMax_dam = (EditText) myActivity.findViewById(R.id.editVolAveMax_dam);
		editGasMaxReg_dam = (EditText) myActivity.findViewById(R.id.editGasMaxReg_dam);
		editCapaCauce_dam = (EditText) myActivity.findViewById(R.id.editCapaCauce_dam);
		editNumPresas_dam = (EditText) myActivity.findViewById(R.id.editNumPresas_dam);
		editNomPresas_dam = (EditText) myActivity.findViewById(R.id.editNomPresas_dam);
		editInstEspe_dam = (EditText) myActivity.findViewById(R.id.editInstEspe_dam);
		editComenCauces_dam = (EditText) myActivity.findViewById(R.id.editComenCauces_dam);
		editName_dam = (EditText) myActivity.findViewById(R.id.editName_dam);
		editNamo_dam = (EditText) myActivity.findViewById(R.id.editNamo_dam);
		editNamino_dam = (EditText) myActivity.findViewById(R.id.editNamino_dam);
		editVolName_dam = (EditText) myActivity.findViewById(R.id.editVolName_dam);
		editVolNamo_dam = (EditText) myActivity.findViewById(R.id.editVolNamo_dam);
		editVolNamino_dam = (EditText) myActivity.findViewById(R.id.editVolNamino_dam);
		editVolAzol_dam = (EditText) myActivity.findViewById(R.id.editVolAzol_dam);
		editVolUtil_dam = (EditText) myActivity.findViewById(R.id.editVolUtil_dam);
		editSuperalmacenamiento_dam = (EditText) myActivity.findViewById(R.id.editSuperalmacenamiento_dam);
		editVolConser_dam = (EditText) myActivity.findViewById(R.id.editVolConser_dam);
		editVolConAve_dam = (EditText) myActivity.findViewById(R.id.editVolConAve_dam);
		editCapaCanal_dam = (EditText) myActivity.findViewById(R.id.editCapaCanal_dam);

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

		spinnerLocaTipo_dam = (Spinner) myActivity.findViewById(R.id.spinnerLocaTipo_dam);
		listSpinnerLocaTipo = db.getCatalogList(Constants.SPLLWAY_TYPE_LOCATION);
		spinnerLocaTipo_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerLocaTipo));

		spinnerLocaCasa_dam = (Spinner) myActivity.findViewById(R.id.spinnerLocaCasa_dam);
		listSpinnerLocaCasa = db.getCatalogList(Constants.MACHINE_ROOM_LOCATIION);
		spinnerLocaCasa_dam.setAdapter(new CatalogItemSpinnerAdapter(myActivity, listSpinnerLocaCasa));

		switchPreciAnu_dam = (Switch)myActivity.findViewById(R.id.switchPreciAnu_dam);
		switchPreciMen_dam = (Switch)myActivity.findViewById(R.id.switchPreciMen_dam);
		switchPreciDia_dam = (Switch)myActivity.findViewById(R.id.switchPreciDia_dam);
		switchAtaguia_dam = (Switch)myActivity.findViewById(R.id.switchAtaguia_dam);

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
			pojoDams pojo = propierty.getDamPptyDetail(propertyID, postalCodeID);
			editLongiM_dam.setText(String.valueOf(pojo.getDamLength()));
			editAltuM_dam.setText(String.valueOf(pojo.getDamHeight()));
			editAnchoBase_dam.setText(String.valueOf(pojo.getBaseWidth()));
			editAnchoCoro_dam.setText(String.valueOf(pojo.getCrownWidth()));
			editTaluArri_dam.setText(String.valueOf(pojo.getDamUpStreamSlope()));
			editTaluAba_dam.setText(String.valueOf(pojo.getDamDownStreamSlope()));
			spinnerLocaTipo_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerLocaTipo, pojo.getDumpLocation()));
			spinnerLocaCasa_dam.setSelection(ListOperations.getItemPositionByID(listSpinnerLocaCasa, pojo.getMachineRoomeLocation()));
			editGasMax_dam.setText(String.valueOf(pojo.getDeseignMaxCurrent()));
			editPeriRet_dam.setText(String.valueOf(pojo.getReturnPeriod()));
			editVolAve_dam.setText(String.valueOf(pojo.getDeseignFloodVolume()));
			editVolAveMax_dam.setText(String.valueOf(pojo.getMaxCurrentRegistered()));
			editGasMaxReg_dam.setText(String.valueOf(pojo.getMaxFloodRegistered()));
			editCapaCauce_dam.setText(String.valueOf(pojo.getWaterDownChannelCapacity()));
			//TODO falta en consola
			//editNumPresas_dam.setText(String.valueOf(pojo.getWaterdownDamsNumber()));
			//editNomPresas_dam.setText(String.valueOf(pojo.getWaterdownDamsNumber()));
			editInstEspe_dam.setText(pojo.getSpecialOperationInstructions());
			editComenCauces_dam.setText(pojo.getComments());
			editName_dam.setText(String.valueOf(pojo.getNAME()));
			editNamo_dam.setText(String.valueOf(pojo.getNAMO()));
			editNamino_dam.setText(String.valueOf(pojo.getNAMINO()));
			editVolName_dam.setText(String.valueOf(pojo.getNAMEVolume()));
			editVolNamo_dam.setText(String.valueOf(pojo.getNAMOVolume()));
			editVolNamino_dam.setText(String.valueOf(pojo.getNAMINOVolume()));
			editVolAzol_dam.setText(String.valueOf(pojo.getSedimentationVolume()));
			editVolUtil_dam.setText(String.valueOf(pojo.getUsefulVolume()));
			editSuperalmacenamiento_dam.setText(String.valueOf(pojo.getSuperStorage()));
			editVolConser_dam.setText(String.valueOf(pojo.getConservationVolume()));
			editVolConAve_dam.setText(String.valueOf(pojo.getFloodControlVolume()));

			editPreciAnu_dam.setText(String.valueOf(pojo.getMaxAnnualPrecipitation()));
			editPreciMen_dam.setText(String.valueOf(pojo.getMaxMonthlyPrecipitation()));
			editPreciDia_dam.setText(String.valueOf(pojo.getMaxDailyPrecipitaion()));
			editCapaCanal_dam.setText(String.valueOf(pojo.getCofferdamCapacity()));

			editTall_dam.setText(String.valueOf(pojo.getWorkShopValue()));
			//switchTall_dam
			editAlma_dam.setText(String.valueOf(pojo.getWarehouseValue()));
			//switchAlma_dam
			editAli_dam.setText(String.valueOf(pojo.getSpillwayValue()));
			//switchAli_dam
			editCuePre_dam.setText(String.valueOf(pojo.getDamBodyValue()));
			//switchCuePre_dam
			editObraToma_dam.setText(String.valueOf(pojo.getIntakeWorksValue()));
			//switchObraToma_dam
			editCasaMaq_dam.setText(String.valueOf(pojo.getMachineRoomsValue()));
			//switchCasaMaq_dam
			editSubDuct_dam.setText(String.valueOf(pojo.getEMFacilitiesValue()));
			//switchSubDuct_dam
			editInstaEle_dam.setText(String.valueOf(pojo.getElectricDuctsValue()));
			//switchInstaEle_dam
			editTuneDuct_dam.setText(String.valueOf(pojo.getConductionTunnelValue()));
			//switchTuneDuct_dam
			editOtros1_dam.setText(String.valueOf(pojo.getOther1Value()));
			//switchOtros1_dam
			editEspe1_dam.setText(String.valueOf(pojo.getOther1Description()));
			editOtros2_dam.setText(String.valueOf(pojo.getOther2Value()));
			//switchOtros2_dam
			editEspe2_dam.setText(String.valueOf(pojo.getOther2Description()));
			editOtros3_dam.setText(String.valueOf(pojo.getOther3Value()));
			//switchOtros3_dam
			editEspe3_dam.setText(String.valueOf(pojo.getOther3Desccription()));
			editTotalVal_dam.setText(String.valueOf(pojo.getCurrentLevels()));
			//switchTotalVal_dam

			if (pojo.isCofferDam()) {
				switchAtaguia_dam.setChecked(true);
			}
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
		View rootView = inflater.inflate(R.layout.dams2, container, false);
		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	private class SaveClickListener implements OnClickListener {
		pojoDams pojoDam;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		
		@Override
		public void onClick(View v) {
			pojoDam = new pojoDams();
			if (validaCampos()) {
				pojoDam.setPropertyID(myActivity.propertyID);
				SetPojoDamP2(pojoDam);
				DamFragment dam1 = (DamFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				dam1.SetPojoDamP1(pojoDam);

				try
				{
					process.processDamSave(pojoDam);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoDam.getPropertyID();
					myActivity.postalCodeID = pojoDam.getPostalCodeID();
				}
			} else {
				DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
				alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
			}
		}
	}

	public void CerrarBien()
	{
		buttonSaveUp2_5_dam.setEnabled(false);
		//buttonOutUp2_5_dam.setEnabled(false);
		buttonSaveBot2_5_dam.setEnabled(false);
		//buttonOutBot2_5_dam.setEnabled(false);

		editLongiM_dam.setEnabled(false);
		editAltuM_dam.setEnabled(false);
		editAnchoBase_dam.setEnabled(false);
		editAnchoCoro_dam.setEnabled(false);
		editTaluArri_dam.setEnabled(false);
		editTaluAba_dam.setEnabled(false);
		editPreciAnu_dam.setEnabled(false);
		editPreciMen_dam.setEnabled(false);
		editPreciDia_dam.setEnabled(false);
		editCapaCanal_dam.setEnabled(false);
		//
		editGasMax_dam.setEnabled(false);
		editPeriRet_dam.setEnabled(false);
		editVolAve_dam.setEnabled(false);
		editVolAveMax_dam.setEnabled(false);
		editGasMaxReg_dam.setEnabled(false);
		
		editCapaCauce_dam.setEnabled(false);
		editNumPresas_dam.setEnabled(false);
		editNomPresas_dam.setEnabled(false);
		editInstEspe_dam.setEnabled(false);
		editComenCauces_dam.setEnabled(false);
		editName_dam.setEnabled(false);
		editNamo_dam.setEnabled(false);
		editNamino_dam.setEnabled(false);
		editVolName_dam.setEnabled(false);
		editVolNamo_dam.setEnabled(false);
		editVolNamino_dam.setEnabled(false);
		editVolAzol_dam.setEnabled(false);
		editVolUtil_dam.setEnabled(false);
		editSuperalmacenamiento_dam.setEnabled(false);
		editVolConser_dam.setEnabled(false);
		editVolConAve_dam.setEnabled(false);

		editTall_dam.setEnabled(false);
		editAlma_dam.setEnabled(false);
		editAli_dam.setEnabled(false);
		editTuneDuct_dam.setEnabled(false);
		editCuePre_dam.setEnabled(false);
		editObraToma_dam.setEnabled(false);
		editCasaMaq_dam.setEnabled(false);
		editInstaEle_dam.setEnabled(false);
		editSubDuct_dam.setEnabled(false);
		editOtros1_dam.setEnabled(false);
		editOtros2_dam.setEnabled(false);
		editOtros3_dam.setEnabled(false);
		editEspe1_dam.setEnabled(false);
		editEspe2_dam.setEnabled(false);
		editEspe3_dam.setEnabled(false);
		editTotalVal_dam.setEnabled(false);

		spinnerLocaTipo_dam.setEnabled(false);
		spinnerLocaCasa_dam.setEnabled(false);

		buttonSaveUp2_5_dam.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp2_5_dam.setImageResource(R.mipmap.ubicacion_blocked);
		buttonSaveBot2_5_dam.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_5_dam.setImageResource(R.mipmap.ubicacion_blocked);
	}

	public boolean validaCampos(){
		if (editLongiM_dam.getText().length() == 0 || editLongiM_dam.getText().toString().compareTo("0.0") == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Longitud", Toast.LENGTH_LONG).show();
			editLongiM_dam.requestFocus();
			return false;
		}
		if (editAltuM_dam.getText().toString().compareTo("0.0") == 0 || editAltuM_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Altura", Toast.LENGTH_LONG).show();
			editAltuM_dam.requestFocus();
			return false;
		}
		if (editAnchoBase_dam.getText().toString().compareTo("0.0") == 0 || editAnchoBase_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Ancho de la base", Toast.LENGTH_LONG).show();
			editAnchoBase_dam.requestFocus();
			return false;
		}
		if (editAnchoCoro_dam.getText().toString().compareTo("0.0") == 0 || editAnchoCoro_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Ancho de la corona", Toast.LENGTH_LONG).show();
			editAnchoCoro_dam.requestFocus();
			return false;
		}
		if (editTaluArri_dam.getText().toString().compareTo("0.0") == 0 || editTaluArri_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar Taludes aguas arriba", Toast.LENGTH_LONG).show();
			editTaluArri_dam.requestFocus();
			return false;
		}
		if (editPreciAnu_dam.getText().toString().compareTo("0.0") == 0 || editPreciAnu_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Precipitación máxima anual", Toast.LENGTH_LONG).show();
			editPreciAnu_dam.requestFocus();
			return false;
		}
		if (editPreciMen_dam.getText().toString().compareTo("0.0") == 0 || editPreciMen_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Precipitación máxima mensual", Toast.LENGTH_LONG).show();
			editPreciMen_dam.requestFocus();
			return false;
		}
		if (editPreciDia_dam.getText().toString().compareTo("0.0") == 0 || editPreciDia_dam.getText().length() == 0) {
			Toast.makeText(myActivity, "Se debe indicar la Precipitación máxima diaria", Toast.LENGTH_LONG).show();
			editPreciDia_dam.requestFocus();
			return false;
		}
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
			buttonSaveUp2_5_dam.setEnabled(false);
			buttonSaveBot2_5_dam.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	public void SetPojoDamP2(pojoDams pojo) {
		if (editLongiM_dam.getText().toString().length() > 0) {
			pojo.setDamLength(Double.valueOf(editLongiM_dam.getText().toString()));
		}
		if (editAltuM_dam.getText().toString().length() > 0) {
			pojo.setDamHeight(Double.valueOf(editAltuM_dam.getText().toString()));
		}
		if (editAnchoBase_dam.getText().toString().length() > 0) {
			pojo.setBaseWidth(Double.valueOf(editAnchoBase_dam.getText().toString()));
		}
		if (editAnchoCoro_dam.getText().toString().length() > 0) {
			pojo.setCrownWidth(Double.valueOf(editAnchoCoro_dam.getText().toString()));
		}
		if (editTaluArri_dam.getText().toString().length() > 0) {
			pojo.setDamUpStreamSlope(Double.valueOf(editTaluArri_dam.getText().toString()));
		}
		if (editTaluAba_dam.getText().toString().length() > 0) {
			pojo.setDamDownStreamSlope(Double.valueOf(editTaluAba_dam.getText().toString()));
		}

		pojo.setDumpLocation(listSpinnerLocaTipo.get(spinnerLocaTipo_dam.getSelectedItemPosition()).getId());
		pojo.setMachineRoomeLocation(listSpinnerLocaCasa.get(spinnerLocaCasa_dam.getSelectedItemPosition()).getId());

		if (editGasMax_dam.getText().toString().length() > 0) {
			pojo.setDeseignMaxCurrent(Double.valueOf(editGasMax_dam.getText().toString()));
		}
		if (editPeriRet_dam.getText().toString().length() > 0) {
			pojo.setReturnPeriod(Double.valueOf(editPeriRet_dam.getText().toString()));
		}
		if (editVolAve_dam.getText().toString().length() > 0) {
			pojo.setDeseignFloodVolume(Double.valueOf(editVolAve_dam.getText().toString()));
		}
		if (editVolAveMax_dam.getText().toString().length() > 0) {
			pojo.setMaxCurrentRegistered(Double.valueOf(editVolAveMax_dam.getText().toString()));
		}
		if (editGasMaxReg_dam.getText().toString().length() > 0) {
			pojo.setMaxFloodRegistered(Double.valueOf(editGasMaxReg_dam.getText().toString()));
		}
		if (editCapaCauce_dam.getText().toString().length() > 0) {
			pojo.setWaterDownChannelCapacity(Double.valueOf(editCapaCauce_dam.getText().toString()));
		}
		pojo.setSpecialOperationInstructions(editInstEspe_dam.getText().toString());
		pojo.setComments(editComenCauces_dam.getText().toString());
		pojo.setSpecialOperationInstructions(editName_dam.getText().toString());
		if (editName_dam.getText().toString().length() > 0) {
			pojo.setNAME(Double.valueOf(editName_dam.getText().toString()));
		}
		if (editNamo_dam.getText().toString().length() > 0) {
			pojo.setNAMO(Double.valueOf(editNamo_dam.getText().toString()));
		}
		if (editNamino_dam.getText().toString().length() > 0) {
			pojo.setNAMINO(Double.valueOf(editNamino_dam.getText().toString()));
		}
		if (editVolName_dam.getText().toString().length() > 0) {
			pojo.setNAMOVolume(Double.valueOf(editVolName_dam.getText().toString()));
		}
		if (editVolAzol_dam.getText().toString().length() > 0) {
			pojo.setSedimentationVolume(Double.valueOf(editVolAzol_dam.getText().toString()));
		}
		if (editVolUtil_dam.getText().toString().length() > 0) {
			pojo.setUsefulVolume(Double.valueOf(editVolUtil_dam.getText().toString()));
		}
		if (editSuperalmacenamiento_dam.getText().toString().length() > 0) {
			pojo.setSuperStorage(Double.valueOf(editSuperalmacenamiento_dam.getText().toString()));
		}
		if (editVolConser_dam.getText().toString().length() > 0) {
			pojo.setConservationVolume(Double.valueOf(editVolConser_dam.getText().toString()));
		}
		if (editVolConAve_dam.getText().toString().length() > 0) {
			pojo.setFloodControlVolume(Double.valueOf(editVolConAve_dam.getText().toString()));
		}

		if (editPreciAnu_dam.getText().toString().length() > 0) {
			pojo.setMaxAnnualPrecipitation(Double.valueOf(editPreciAnu_dam.getText().toString()));
		}
		if (editPreciMen_dam.getText().toString().length() > 0) {
			pojo.setMaxMonthlyPrecipitation(Double.valueOf(editPreciMen_dam.getText().toString()));
		}
		if (editPreciDia_dam.getText().toString().length() > 0) {
			pojo.setMaxDailyPrecipitaion(Double.valueOf(editPreciDia_dam.getText().toString()));
		}
		pojo.setCofferDam(Boolean.valueOf(editCapaCanal_dam.getText().toString())); //TODO

		pojo.setWorkShopValue(editTall_dam.getText().toString());
		pojo.setWarehouseValue(editAlma_dam.getText().toString());
		pojo.setSpillwayValue(editAli_dam.getText().toString());
		pojo.setDamBodyValue(editCuePre_dam.getText().toString());
		pojo.setIntakeWorksValue(editObraToma_dam.getText().toString());
		pojo.setMachineRoomsValue(editCasaMaq_dam.getText().toString());
		pojo.setEMFacilitiesValue(editSubDuct_dam.getText().toString());
		pojo.setElectricDuctsValue(editInstaEle_dam.getText().toString());
		pojo.setConductionTunnelValue(editTuneDuct_dam.getText().toString());
		if (editOtros1_dam.getText().toString().length() > 0) {
			pojo.setOther1Value(Double.valueOf(editOtros1_dam.getText().toString()));
		}
		pojo.setOther1Description(editEspe1_dam.getText().toString());
		if (editOtros2_dam.getText().toString().length() > 0) {
			pojo.setOther2Value(Double.valueOf(editOtros2_dam.getText().toString()));
		}
		pojo.setOther2Description(editEspe2_dam.getText().toString());
		if (editOtros3_dam.getText().toString().length() > 0) {
			pojo.setOther3Value(Double.valueOf(editOtros3_dam.getText().toString()));
		}
		pojo.setOther3Desccription(editEspe3_dam.getText().toString());
		//pojo.setCurrentLevels(Double.valueOf(editTotalVal_dam.getText().toString())); //TODO

		if (switchAtaguia_dam.isChecked()) {
			pojo.setCofferDam(true);
		}

	}
}
