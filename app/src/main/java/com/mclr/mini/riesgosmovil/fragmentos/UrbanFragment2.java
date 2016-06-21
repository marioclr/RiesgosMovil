package com.mclr.mini.riesgosmovil.fragmentos;

import java.math.BigDecimal;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoUrban;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;


public class UrbanFragment2 extends Fragment {
	private MainActivity myActivity = null;
	public static final String ARG_URBAN_NUMBER = "urban_number";
	public static final String ARG_URBAN_POSTAL_CODE = "urban_postal_code";
	VWProperties propierty;
	// Elementos graficos
	
	ImageButton buttonSaveBot2_2_urban;
	ImageButton buttonOutBot2_2_urban;
	
	
	EditText editValPromedio_urban;
	EditText editValUni_urban;
	EditText editValPromRep_urban;
	EditText editMontoPre_urban;
	EditText editMontoCorre_urban;
	
	Switch switchValPromedio_urban;
	Switch switchValUni_urban;
	Switch switchValPromRep_urban;
	Switch switchMontoPre_urban;
	Switch switchMontoCorre_urban;

	public UrbanFragment2() {

	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.urban2, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		buttonSaveBot2_2_urban = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot2_2_urban);
		buttonSaveBot2_2_urban.setOnClickListener(new SaveClickListener());
		buttonOutBot2_2_urban = (ImageButton)myActivity.findViewById(R.id.buttonOutBot2_2_urban);
		buttonOutBot2_2_urban.setOnClickListener(new OutClickListener());

		editValPromedio_urban = (EditText) myActivity.findViewById(R.id.editValPromedio_urban);
		switchValPromedio_urban = (Switch) myActivity.findViewById(R.id.switchValPromedio_urban);
		editValUni_urban = (EditText) myActivity.findViewById(R.id.editValUni_urban);
		switchValUni_urban = (Switch) myActivity.findViewById(R.id.switchValUni_urban);
		editValPromRep_urban = (EditText) myActivity.findViewById(R.id.editValPromRep_urban);
		switchValPromRep_urban = (Switch) myActivity.findViewById(R.id.switchValPromRep_urban);
		editMontoPre_urban = (EditText) myActivity.findViewById(R.id.editMontoPre_urban);
		switchMontoPre_urban = (Switch) myActivity.findViewById(R.id.switchMontoPre_urban);
		editMontoCorre_urban = (EditText) myActivity.findViewById(R.id.editMontoCorre_urban);
		switchMontoCorre_urban = (Switch) myActivity.findViewById(R.id.switchMontoCorre_urban);

		propierty = new VWProperties(myActivity);
		if (!myActivity.propertyID.equals(Constants.GENERICO)) {
			pojoUrban pojo = propierty.getUrbanPptyDetail(myActivity.propertyID, myActivity.postalCodeID);
			editValPromedio_urban.setText(String.valueOf(pojo.getAvgKmReconstructionValue()));
			//switchValPromedio_urban.setChecked(pojo.());
			editValUni_urban.setText(String.valueOf(pojo.getStmtReposicionValue()));
			//switchValUni_urban.setChecked(pojo.());
			editValPromRep_urban.setText(String.valueOf(pojo.getMinorBridgesAvgRepositionValue()));
			//switchValPromRep_urban.setChecked(pojo.());
			editMontoPre_urban.setText(String.valueOf(pojo.getPreventionAvgAmount()));
			//switchMontoPre_urban.setChecked(pojo.());
			editMontoPre_urban.setText(String.valueOf(pojo.getCorrectiveAvgAmount()));
			//switchMontoCorre_urban.setChecked(pojo.isDomes());
		}
	}

	public void CerrarBien()
	{
		buttonSaveBot2_2_urban.setEnabled(false);
		//buttonOutBot2_2_urban.setEnabled(false);

		editValPromedio_urban.setEnabled(false);
		editValUni_urban.setEnabled(false);
		editValPromRep_urban.setEnabled(false);
		editMontoPre_urban.setEnabled(false);
		editMontoCorre_urban.setEnabled(false);

		buttonSaveBot2_2_urban.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot2_2_urban.setImageResource(R.mipmap.salir_blocked);
	}

	public boolean validaCampos() {
		return true;
	}

	private class SaveClickListener implements OnClickListener {
		com.mclr.mini.riesgosmovil.modelos.pojoUrban pojoUrban;
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {
			pojoUrban = new pojoUrban();

			if (validaCampos()) {
				pojoUrban.setPropertyID(myActivity.propertyID);
				SetPojoUrbanP2(pojoUrban);
				UrbanFragment urban1 = (UrbanFragment)((PropertyAdminFragment)myActivity.fragment).propertyAdminPager.getFragment(0);
				urban1.SetPojoUrbanP1(pojoUrban);

				try
				{
					process.processUrbanSave(pojoUrban);
					Toast.makeText(myActivity, "El registro fué guardado", Toast.LENGTH_LONG).show();
				}
				catch (Exception ex)
				{
					Toast.makeText(myActivity, "Error al guardar", Toast.LENGTH_LONG).show();
				}
				finally
				{
					myActivity.propertyID = pojoUrban.getPropertyID();
					myActivity.postalCodeID = pojoUrban.getPostalCodeID();
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

	public void SetPojoUrbanP2(pojoUrban pojo) {
		
		if (editValPromedio_urban.getText().length()>0) {
			pojo.setAvgKmReconstructionValue(new BigDecimal(editValPromedio_urban.getText().toString()));
		}if (editValUni_urban.getText().length()>0) {
			pojo.setStmtReposicionValue(new BigDecimal(editValUni_urban.getText().toString()));
		}if (editValPromRep_urban.getText().length()>0) {
			pojo.setMinorBridgesAvgRepositionValue(new BigDecimal(editValPromRep_urban.getText().toString()));
		}if (editMontoPre_urban.getText().length()>0) {
			pojo.setPreventionAvgAmount(new BigDecimal(editMontoPre_urban.getText().toString()));
		}if (editMontoPre_urban.getText().length()>0) {
			pojo.setCorrectiveAvgAmount(new BigDecimal(editMontoPre_urban.getText().toString()));
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
			buttonSaveBot2_2_urban.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar fotos del bien debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}
}
