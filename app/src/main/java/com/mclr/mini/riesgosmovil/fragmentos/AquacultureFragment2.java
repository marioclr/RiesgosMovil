package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
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
import com.mclr.mini.riesgosmovil.modelos.pojoAquaculture;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class AquacultureFragment2 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;

	ImageButton buttonSaveUp2_2_aquaculture;
	ImageButton buttonOutUp2_2_aquaculture;
	ImageButton buttonSaveBot2_2_aquaculture;
	ImageButton buttonOutBot2_2_aquaculture;

	EditText editEdificio_aquaculture;
	EditText editValRealE_aquaculture;
	EditText editValReNuE_aquaculture;
	EditText editReha_aquaculture;
	EditText editValRealR_aquaculture;
	EditText editValReNuR_aquaculture;
	EditText editCance_aquaculture;
	EditText editValRealC_aquaculture;
	EditText editValReNuC_aquaculture;
	EditText editMaCiclo_aquaculture;
	EditText editValRealMC_aquaculture;
	EditText editValReNuMC_aquaculture;
	EditText editPintura_aquaculture;
	EditText editValRealP_aquaculture;
	EditText editValReNuP_aquaculture;
	EditText editTechu_aquaculture;
	EditText editValRealT_aquaculture;
	EditText editValReNuT_aquaculture;
	EditText editTotalObras_aquaculture;
	EditText editValRealTO_aquaculture;
	EditText editRedAli_aquaculture;
	EditText editValRealRA_aquaculture;
	EditText editValReNuRA_aquaculture;
	EditText editRedCond_aquaculture;
	EditText editValRealRC_aquaculture;
	EditText editValReNuRC_aquaculture;
	EditText editRedEle_aquaculture;
	EditText editValRealRE_aquaculture;
	EditText editValReNuRE_aquaculture;
	EditText editTotalObrasCond_aquaculture;
	EditText editValRealTOC_aquaculture;
	EditText editRedSani_aquaculture;
	EditText editValRealRS_aquaculture;
	EditText editValReNuRS_aquaculture;
	EditText editInstaSani_aquaculture;
	EditText editValRealIS_aquaculture;
	EditText editValReNuIS_aquaculture;
	EditText editRedEleS_aquaculture;
	EditText editValRealRES_aquaculture;
	EditText editValReNuRES_aquaculture;
	EditText editTVR_aquaculture;
	EditText editTVRN_aquaculture;
	EditText editVRST_aquaculture;
	EditText editVRNST_aquaculture;

	Switch switchEdificio_aquaculture;
	Switch switchReha_aquaculture;
	Switch switchCance_aquaculture;
	Switch switchMaCilo_aquaculture;
	Switch switchPintura_aquaculture;
	Switch switchTechu_aquaculture;
	Switch switchRedAli_aquaculture;
	Switch switchRedCond_aquaculture;
	Switch switchRedEle_aquaculture;
	Switch switchRedSani_aquaculture;
	Switch switchInstaSani_aquaculture;
	Switch switchRedEleS_aquaculture;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.aquaculture2, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		buttonSaveUp2_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp2_2_aquaculture);
		buttonSaveUp2_2_aquaculture.setOnClickListener(new SaveClickListener());
		buttonOutUp2_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonOutUp2_2_aquaculture);
		buttonOutUp2_2_aquaculture.setOnClickListener(new OutClickListener());

		buttonSaveBot2_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_aquaculture);
		buttonSaveBot2_2_aquaculture.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_aquaculture = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_aquaculture);
		buttonOutBot2_2_aquaculture.setOnClickListener(new OutClickListener());

		editEdificio_aquaculture = (EditText) myActivity.findViewById(R.id.editEdificio_aquaculture);
		editValRealE_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealE_aquaculture);
		editValReNuE_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuE_aquaculture);
		editReha_aquaculture = (EditText) myActivity.findViewById(R.id.editReha_aquaculture);
		editValRealR_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealR_aquaculture);
		editValReNuR_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuR_aquaculture);
		editCance_aquaculture = (EditText) myActivity.findViewById(R.id.editCance_aquaculture);
		editValRealC_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealC_aquaculture);
		editValReNuC_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuC_aquaculture);
		editMaCiclo_aquaculture = (EditText) myActivity.findViewById(R.id.editMaCiclo_aquaculture);
		editValRealMC_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealMC_aquaculture);
		editValReNuMC_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuMC_aquaculture);
		editPintura_aquaculture = (EditText) myActivity.findViewById(R.id.editPintura_aquaculture);
		editValRealP_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealP_aquaculture);
		editValReNuP_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuP_aquaculture);
		editTechu_aquaculture = (EditText) myActivity.findViewById(R.id.editTechu_aquaculture);
		editValRealT_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealT_aquaculture);
		editValReNuT_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuT_aquaculture);
		editTotalObras_aquaculture = (EditText) myActivity.findViewById(R.id.editTVRORR_aquaculture);
		editValRealTO_aquaculture = (EditText) myActivity.findViewById(R.id.editVRNTORR_aquaculture);
		editRedAli_aquaculture = (EditText) myActivity.findViewById(R.id.editRedAli_aquaculture);
		editValRealRA_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealRA_aquaculture);
		editValReNuRA_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuRA_aquaculture);
		editRedCond_aquaculture = (EditText) myActivity.findViewById(R.id.editRedCond_aquaculture);
		editValRealRC_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealRC_aquaculture);
		editValReNuRC_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuRC_aquaculture);
		editRedEle_aquaculture = (EditText) myActivity.findViewById(R.id.editRedEle_aquaculture);
		editValRealRE_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealRE_aquaculture);
		editValReNuRE_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuRE_aquaculture);
		editTotalObrasCond_aquaculture = (EditText) myActivity.findViewById(R.id.editVRTOCD_aquaculture);
		editValRealTOC_aquaculture = (EditText) myActivity.findViewById(R.id.editVRNTOCD_aquaculture);
		editRedSani_aquaculture = (EditText) myActivity.findViewById(R.id.editRedSani_aquaculture);
		editValRealRS_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealRS_aquaculture);
		editValReNuRS_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuRS_aquaculture);
		editInstaSani_aquaculture = (EditText) myActivity.findViewById(R.id.editInstaSani_aquaculture);
		editValRealIS_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealIS_aquaculture);
		editValReNuIS_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuIS_aquaculture);
		editRedEleS_aquaculture = (EditText) myActivity.findViewById(R.id.editRedEleS_aquaculture);
		editValRealRES_aquaculture = (EditText) myActivity.findViewById(R.id.editValRealRES_aquaculture);
		editValReNuRES_aquaculture = (EditText) myActivity.findViewById(R.id.editValReNuRES_aquaculture);
		editTVR_aquaculture = (EditText) myActivity.findViewById(R.id.editTVR_aquaculture);
		editTVRN_aquaculture = (EditText) myActivity.findViewById(R.id.editTVRN_aquaculture);
		editVRST_aquaculture = (EditText) myActivity.findViewById(R.id.editVRST_aquaculture);
		editVRNST_aquaculture = (EditText) myActivity.findViewById(R.id.editVRNST_aquaculture);

		switchEdificio_aquaculture = (Switch) myActivity.findViewById(R.id.switchEdificio_aquaculture);
		switchReha_aquaculture = (Switch) myActivity.findViewById(R.id.switchReha_aquaculture);
		switchCance_aquaculture = (Switch) myActivity.findViewById(R.id.switchCance_aquaculture);
		switchMaCilo_aquaculture = (Switch) myActivity.findViewById(R.id.switchMaCilo_aquaculture);
		switchPintura_aquaculture = (Switch) myActivity.findViewById(R.id.switchPintura_aquaculture);
		switchTechu_aquaculture = (Switch) myActivity.findViewById(R.id.switchTechu_aquaculture);
		switchRedAli_aquaculture = (Switch) myActivity.findViewById(R.id.switchRedAli_aquaculture);
		switchRedCond_aquaculture = (Switch) myActivity.findViewById(R.id.switchRedCond_aquaculture);
		switchRedEle_aquaculture = (Switch) myActivity.findViewById(R.id.switchRedEle_aquaculture);
		switchRedSani_aquaculture = (Switch) myActivity.findViewById(R.id.switchRedSani_aquaculture);
		switchInstaSani_aquaculture = (Switch) myActivity.findViewById(R.id.switchInstaSani_aquaculture);
		switchRedEleS_aquaculture = (Switch) myActivity.findViewById(R.id.switchRedEleS_aquaculture);
		propierty = new VWProperties(myActivity);
		if (!myActivity.propertyID.equals(Constants.GENERICO)){
			pojoAquaculture pojo = propierty.getAquaculturalPptyDetail(myActivity.propertyID, myActivity.postalCodeID);
			editEdificio_aquaculture.setText(pojo.getRnBuildingDescription());
			editValRealE_aquaculture.setText(String.valueOf(pojo.getRlBuildingValue()));
			editValReNuE_aquaculture.setText(String.valueOf(pojo.getRnBuildingValue()));

			editReha_aquaculture.setText(pojo.getRnTankRehabDescription());
			editValRealR_aquaculture.setText(String.valueOf(pojo.getRlTankRehabValue()));
			editValReNuR_aquaculture.setText(String.valueOf(pojo.getRnTankRehabValue()));

			editCance_aquaculture.setText(pojo.getRnCancelDescription());
			editValRealC_aquaculture.setText(String.valueOf(pojo.getRlCancelValue()));
			editValReNuC_aquaculture.setText(String.valueOf(pojo.getRnCancelValue()));

			editMaCiclo_aquaculture.setText(pojo.getRnMeshDescription());
			editValRealMC_aquaculture.setText(String.valueOf(pojo.getRlMeshValue()));
			editValReNuMC_aquaculture.setText(String.valueOf(pojo.getRnMeshValue()));

			editPintura_aquaculture.setText(pojo.getRnPaintingDescription());
			editValRealP_aquaculture.setText(String.valueOf(pojo.getRlPaintingValue()));
			editValReNuP_aquaculture.setText(String.valueOf(pojo.getRnPaintingValue()));

			editTechu_aquaculture.setText(pojo.getRnRoofsDescription());
			editValRealT_aquaculture.setText(String.valueOf(pojo.getRlRoofsValue()));
			editValReNuT_aquaculture.setText(String.valueOf(pojo.getRnRoofsValue()));

//			editTotalObras_aquaculture.setText(pojo.getBuildingName());
//			editValRealTO_aquaculture = (EditText) myActivity.findViewById(R.id.editVRNTORR_aquaculture);

			editRedAli_aquaculture.setText(pojo.getRnRoofsDescription());
			editValRealRA_aquaculture.setText(String.valueOf(pojo.getRlRoofsValue()));
			editValReNuRA_aquaculture.setText(String.valueOf(pojo.getRnRoofsValue()));

			editRedCond_aquaculture.setText(pojo.getRnWaterNetCDDescription());
			editValRealRC_aquaculture.setText(String.valueOf(pojo.getRlWaterNetCDValue()));
			editValReNuRC_aquaculture.setText(String.valueOf(pojo.getRnWaterNetCDValue()));

			editRedEle_aquaculture.setText(pojo.getRnElectricNetCDDescription());
			editValRealRE_aquaculture.setText(String.valueOf(pojo.getRlElectricNetCDValue()));
			editValReNuRE_aquaculture.setText(String.valueOf(pojo.getRnElectricNetCDValue()));

//			editTotalObrasCond_aquaculture.setText(pojo.getBuildingName());
//			editValRealTOC_aquaculture.setText(pojo.getBuildingName());


			editRedSani_aquaculture.setText(pojo.getSanitaryNetDescription());
			editValRealRS_aquaculture.setText(String.valueOf(pojo.getRlSanitaryNetValue()));
			editValReNuRS_aquaculture.setText(String.valueOf(pojo.getRnSanitaryNetValue()));

			editInstaSani_aquaculture.setText(pojo.getSanitaryInstalDescription());
			editValRealIS_aquaculture.setText(String.valueOf(pojo.getRlSanitaryIntallValue()));
			editValReNuIS_aquaculture.setText(String.valueOf(pojo.getRnSanitaryIntallValue()));

			editRedEleS_aquaculture.setText(pojo.getEMNetDescription());
			editValRealRES_aquaculture.setText(String.valueOf(pojo.getRlSanitaryEMNetValue()));
			editValReNuRES_aquaculture.setText(String.valueOf(pojo.getRnSanitaryEMNetValue()));

//			editTVR_aquaculture.setText(pojo.getBuildingName());
//			editTVRN_aquaculture.setText(pojo.getBuildingName());
//
//			editVRST_aquaculture.setText(pojo.getBuildingName());
//			editVRNST_aquaculture.setText(pojo.getBuildingName());
			if (pojo.getStatus()!=0)
				CerrarBien();
		}
		super.onActivityCreated(savedInstanceState);
	}

	public void CerrarBien()
	{
		buttonSaveUp2_2_aquaculture.setEnabled(false);
		buttonOutUp2_2_aquaculture.setEnabled(false);
		buttonSaveBot2_2_aquaculture.setEnabled(false);
		buttonOutBot2_2_aquaculture.setEnabled(false);

		editEdificio_aquaculture.setEnabled(false);
		editValRealE_aquaculture.setEnabled(false);
		editValReNuE_aquaculture.setEnabled(false);
		editReha_aquaculture.setEnabled(false);
		editValRealR_aquaculture.setEnabled(false);
		editValReNuR_aquaculture.setEnabled(false);
		editCance_aquaculture.setEnabled(false);
		editValRealC_aquaculture.setEnabled(false);
		editValReNuC_aquaculture.setEnabled(false);
		editMaCiclo_aquaculture.setEnabled(false);
		editValRealMC_aquaculture.setEnabled(false);
		editValReNuMC_aquaculture.setEnabled(false);
		editPintura_aquaculture.setEnabled(false);
		editValRealP_aquaculture.setEnabled(false);
		editValReNuP_aquaculture.setEnabled(false);
		editTechu_aquaculture.setEnabled(false);
		editValRealT_aquaculture.setEnabled(false);
		editValReNuT_aquaculture.setEnabled(false);
		editTotalObras_aquaculture.setEnabled(false);
		editValRealTO_aquaculture.setEnabled(false);
		editRedAli_aquaculture.setEnabled(false);
		editValRealRA_aquaculture.setEnabled(false);
		editValReNuRA_aquaculture.setEnabled(false);
		editRedCond_aquaculture.setEnabled(false);
		editValRealRC_aquaculture.setEnabled(false);
		editValReNuRC_aquaculture.setEnabled(false);
		editRedEle_aquaculture.setEnabled(false);
		editValRealRE_aquaculture.setEnabled(false);
		editValReNuRE_aquaculture.setEnabled(false);
		editTotalObrasCond_aquaculture.setEnabled(false);
		editValRealTOC_aquaculture.setEnabled(false);
		editRedSani_aquaculture.setEnabled(false);
		editValRealRS_aquaculture.setEnabled(false);
		editValReNuRS_aquaculture.setEnabled(false);
		editInstaSani_aquaculture.setEnabled(false);
		editValRealIS_aquaculture.setEnabled(false);
		editValReNuIS_aquaculture.setEnabled(false);
		editRedEleS_aquaculture.setEnabled(false);
		editValRealRES_aquaculture.setEnabled(false);
		editValReNuRES_aquaculture.setEnabled(false);
		editTVR_aquaculture.setEnabled(false);
		editTVRN_aquaculture.setEnabled(false);
		editVRST_aquaculture.setEnabled(false);
		editVRNST_aquaculture.setEnabled(false);

		buttonSaveUp2_2_aquaculture.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp2_2_aquaculture.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot2_2_aquaculture.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_aquaculture.setImageResource(R.mipmap.salir_blocked);
	}

	private class SaveClickListener implements OnClickListener {
		pojoAquaculture pojoAqua;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoAqua = new pojoAquaculture();
			if (validaCampos()) {

				pojoAqua.setPropertyID(myActivity.propertyID);
				SetPojoAquacultureP2(pojoAqua);
				AquacultureFragment aqua1 = (AquacultureFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				aqua1.SetPojoAquacultureP1(pojoAqua);

				try
				{
					process.processAquacultureSave(pojoAqua);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoAqua.getPropertyID();
					myActivity.postalCodeID = pojoAqua.getPostalCodeID();
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
			Toast.makeText(myActivity, "Boton salir", Toast.LENGTH_LONG).show();
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
			buttonSaveUp2_2_aquaculture.setEnabled(false);
			buttonSaveBot2_2_aquaculture.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	public void SetPojoAquacultureP2(pojoAquaculture pojo)
	{
		pojo.setRnBuildingDescription(editEdificio_aquaculture.getText().toString());
		if (editEdificio_aquaculture.getText().length()>0) {
			pojo.setRlBuildingValue(editEdificio_aquaculture.getText().toString());
		}
		if (editValReNuE_aquaculture.getText().length()>0) {
			pojo.setRnBuildingValue(editValReNuE_aquaculture.getText().toString());
		}

		pojo.setRnTankRehabDescription(editReha_aquaculture.getText().toString());
		if (editValRealR_aquaculture.getText().length()>0) {
			pojo.setRlTankRehabValue(editValRealR_aquaculture.getText().toString());
		}
		if (editValReNuR_aquaculture.getText().length()>0) {
			pojo.setRnTankRehabValue(editValReNuR_aquaculture.getText().toString());
		}

		pojo.setRnCancelDescription(editCance_aquaculture.getText().toString());
		if (editValRealR_aquaculture.getText().length()>0) {
			pojo.setRlCancelValue(editValRealR_aquaculture.getText().toString());
		}
		if (editValReNuC_aquaculture.getText().length()>0) {
			pojo.setRnCancelValue(editValReNuC_aquaculture.getText().toString());
		}

		pojo.setRnMeshDescription(editMaCiclo_aquaculture.getText().toString());
		if (editValRealMC_aquaculture.getText().length()>0) {
			pojo.setRlMeshValue(editValRealMC_aquaculture.getText().toString());
		}
		if (editValReNuMC_aquaculture.getText().length()>0) {
			pojo.setRnMeshValue(editValReNuMC_aquaculture.getText().toString());
		}

		pojo.setRnPaintingDescription(editPintura_aquaculture.getText().toString());
		if (editValRealP_aquaculture.getText().length()>0) {
			pojo.setRlPaintingValue(editValRealP_aquaculture.getText().toString());
		}
		if (editValReNuP_aquaculture.getText().length()>0) {
			pojo.setRnPaintingValue(editValReNuP_aquaculture.getText().toString());
		}

		pojo.setRnRoofsDescription(editTechu_aquaculture.getText().toString());
		if (editValRealT_aquaculture.getText().length()>0) {
			pojo.setRlRoofsValue(editValRealT_aquaculture.getText().toString());
		}
		if (editValReNuT_aquaculture.getText().length()>0) {
			pojo.setRnRoofsValue(editValReNuT_aquaculture.getText().toString());
		}

//		editTotalObras_aquaculture.setText(pojo.getBuildingName());
//		editValRealTO_aquaculture = (EditText) myActivity.findViewById(R.id.editVRNTORR_aquaculture);

		pojo.setRnRoofsDescription(editRedAli_aquaculture.getText().toString());
		if (editValRealRA_aquaculture.getText().length()>0) {
			pojo.setRlRoofsValue(editValRealRA_aquaculture.getText().toString());
		}
		if (editValReNuRA_aquaculture.getText().length()>0) {
			pojo.setRnRoofsValue(editValReNuRA_aquaculture.getText().toString());
		}

		pojo.setRnWaterNetCDDescription(editRedCond_aquaculture.getText().toString());
		if (editValRealRC_aquaculture.getText().length()>0) {
			pojo.setRlWaterNetCDValue(editValRealRC_aquaculture.getText().toString());
		}
		if (editValReNuRC_aquaculture.getText().length()>0) {
			pojo.setRnWaterNetCDValue(editValReNuRC_aquaculture.getText().toString());
		}

		pojo.setRnElectricNetCDDescription(editRedEle_aquaculture.getText().toString());
		if (editValRealRE_aquaculture.getText().length()>0) {
			pojo.setRlElectricNetCDValue(editValRealRE_aquaculture.getText().toString());
		}
		if (editValReNuRE_aquaculture.getText().length()>0) {
			pojo.setRnElectricNetCDValue(editValReNuRE_aquaculture.getText().toString());
		}

//		editTotalObrasCond_aquaculture.setText(pojo.getBuildingName());
//		editValRealTOC_aquaculture.setText(pojo.getBuildingName());

		pojo.setSanitaryNetDescription(editRedSani_aquaculture.getText().toString());
		if (editValRealRS_aquaculture.getText().length()>0) {
			pojo.setRlSanitaryNetValue(editValRealRS_aquaculture.getText().toString());
		}
		if (editValReNuRS_aquaculture.getText().length()>0) {
			pojo.setRnSanitaryNetValue(editValReNuRS_aquaculture.getText().toString());
		}

		pojo.setSanitaryInstalDescription(editInstaSani_aquaculture.getText().toString());
		if (editValRealIS_aquaculture.getText().length()>0) {
			pojo.setRlSanitaryIntallValue(editValRealIS_aquaculture.getText().toString());
		}
		if (editValReNuIS_aquaculture.getText().length()>0) {
			pojo.setRnSanitaryIntallValue(editValReNuIS_aquaculture.getText().toString());
		}

		pojo.setEMNetDescription(editRedEleS_aquaculture.getText().toString());
		if (editValRealRES_aquaculture.getText().length()>0) {
			pojo.setRlSanitaryEMNetValue(editValRealRES_aquaculture.getText().toString());
		}
		if (editValReNuRES_aquaculture.getText().length()>0) {
			pojo.setRnSanitaryEMNetValue(editValReNuRES_aquaculture.getText().toString());
		}

	}

}
