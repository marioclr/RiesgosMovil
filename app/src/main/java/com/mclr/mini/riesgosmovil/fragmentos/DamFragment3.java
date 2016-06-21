package com.mclr.mini.riesgosmovil.fragmentos;

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
import android.widget.Switch;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;

public class DamFragment3 extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;

	ImageButton buttonSaveUp3_5_dam;
	ImageButton buttonOutUp3_5_dam;
	ImageButton buttonSaveBot3_5_dam;
	ImageButton buttonOutBot3_5_dam;

	CheckBox checkboxInundacion_dam;
	CheckBox checkboxHuracan_dam;
	CheckBox checkboxTerremoto_dam;
	CheckBox checkboxDeslave_dam;
	CheckBox checkboxDerrumbe_dam;

	CheckBox checkboxInundacionDef_dam;
	CheckBox checkboxHuracanDef_dam;
	CheckBox checkboxTerremotoDef_dam;
	CheckBox checkboxDeslaveDef_dam;
	CheckBox checkboxDerrumbeDef_dam;

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

		buttonSaveUp3_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveUp3_5_dam);
		buttonSaveUp3_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutUp3_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutUp3_5_dam);
		buttonOutUp3_5_dam.setOnClickListener(new OutClickListener());

		buttonSaveBot3_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonSaveBot3_5_dam);
		buttonSaveBot3_5_dam.setOnClickListener(new SaveClickListener());
		buttonOutBot3_5_dam = (ImageButton)myActivity.findViewById(R.id.buttonOutBot3_5_dam);
		buttonOutBot3_5_dam.setOnClickListener(new OutClickListener());

		checkboxInundacion_dam = (CheckBox)myActivity.findViewById(R.id.checkboxInundacion_dam);
		checkboxHuracan_dam = (CheckBox)myActivity.findViewById(R.id.checkboxHuracan_dam);
		checkboxTerremoto_dam = (CheckBox)myActivity.findViewById(R.id.checkboxTerremoto_dam);
		checkboxDeslave_dam = (CheckBox)myActivity.findViewById(R.id.checkboxDeslave_dam);
		checkboxDerrumbe_dam = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbe_dam);

		checkboxInundacionDef_dam = (CheckBox)myActivity.findViewById(R.id.checkboxInundacionDef_dam);
		checkboxHuracanDef_dam = (CheckBox)myActivity.findViewById(R.id.checkboxHuracanDef_dam);
		checkboxTerremotoDef_dam = (CheckBox)myActivity.findViewById(R.id.checkboxTerremotoDef_dam);
		checkboxDeslaveDef_dam = (CheckBox)myActivity.findViewById(R.id.checkboxDeslaveDef_dam);
		checkboxDerrumbeDef_dam = (CheckBox)myActivity.findViewById(R.id.checkboxDerrumbeDef_dam);

		editImporte2013_dam = (EditText) myActivity.findViewById(R.id.editImporte2013_dam);
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
		editDef2006_dam = (EditText) myActivity.findViewById(R.id.editDef2006_dam);
		editImporte2005_dam = (EditText) myActivity.findViewById(R.id.editImporte2005_dam);
		editDef2005_dam = (EditText) myActivity.findViewById(R.id.editDef2005_dam);
		editImporte2004_dam = (EditText) myActivity.findViewById(R.id.editImporte2004_dam);
		editDef2004_dam = (EditText) myActivity.findViewById(R.id.editDef2004_dam);

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
		if (!myActivity.propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propierty.getDamPptyDetail(myActivity.propertyID, myActivity.postalCodeID);

			if (pojo.isFleedFormer()) {
				checkboxInundacion_dam.setChecked(true);
			}
			if (pojo.isHurricaneFormer()) {
				checkboxHuracan_dam.setChecked(true);
			}
			if (pojo.isEarthQuakeFormer()) {
				checkboxTerremoto_dam.setChecked(true);
			}
			if (pojo.isLandSlideFormer()) {
				checkboxDeslave_dam.setChecked(true);
			}
			if (pojo.isCollapseFormer()) {
				checkboxDerrumbe_dam.setChecked(true);
			}

			if (pojo.isFleedRisk()) {
				checkboxInundacionDef_dam.setChecked(true);
			}
			if (pojo.isHurricaneRisk()) {
				checkboxHuracanDef_dam.setChecked(true);
			}
			if (pojo.isEarthQuakeRisk()) {
				checkboxTerremotoDef_dam.setChecked(true);
			}
			if (pojo.isLandSlideRisk()) {
				checkboxDeslaveDef_dam.setChecked(true);
			}
			if (pojo.isCollapseRisk()) {
				checkboxDerrumbeDef_dam.setChecked(true);
			}

			editImporte2013_dam.setText(pojo.getSiniestralityValue()[0].toString());
			//switchImporte2013_dam
			editDef2013_dam.setText(pojo.getSiniestralityDescription()[0].toString());

			editImporte2012_dam.setText(pojo.getSiniestralityValue()[1].toString());
			//switchImporte2012_dam
			editDef2012_dam.setText(pojo.getSiniestralityDescription()[1].toString());

			editImporte2011_dam.setText(pojo.getSiniestralityValue()[2].toString());
			//switchImporte2011_dam
			editDef2011_dam.setText(pojo.getSiniestralityDescription()[2].toString());

			editImporte2010_dam.setText(pojo.getSiniestralityValue()[3].toString());
			//switchImporte2010_dam
			editDef2010_dam.setText(pojo.getSiniestralityDescription()[3].toString());

			editImporte2009_dam.setText(pojo.getSiniestralityValue()[4].toString());
			//switchImporte2009_dam
			editDef2009_dam.setText(pojo.getSiniestralityDescription()[4].toString());

			editImporte2008_dam.setText(pojo.getSiniestralityValue()[5].toString());
			//switchImporte2008_dam
			editDef2008_dam.setText(pojo.getSiniestralityDescription()[5].toString());

			editImporte2007_dam.setText(pojo.getSiniestralityValue()[6].toString());
			//switchImporte2007_dam
			editDef2007_dam.setText(pojo.getSiniestralityDescription()[6].toString());

			editImporte2006_dam.setText(pojo.getSiniestralityValue()[7].toString());
			//switchImporte2006_dam
			editDef2006_dam.setText(pojo.getSiniestralityDescription()[7].toString());

			editImporte2005_dam.setText(pojo.getSiniestralityValue()[8].toString());
			//switchImporte2005_dam
			editDef2005_dam.setText(pojo.getSiniestralityDescription()[8].toString());

			editImporte2004_dam.setText(pojo.getSiniestralityValue()[9].toString());
			//switchImporte2004_dam
			editDef2004_dam.setText(pojo.getSiniestralityDescription()[9].toString());

		}
		super.onActivityCreated(savedInstanceState);
	}

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        myActivity = (MainActivity) activity;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dams3, container, false);
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
				SetPojoDamP3(pojoDam);

				try
				{
					process.processDamSiniestrFormerAndRiskSave(pojoDam);
					Toast.makeText(myActivity, "La información fué guardada", Toast.LENGTH_LONG).show();
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

	public void CerrarBien()
	{
		buttonSaveUp3_5_dam.setEnabled(false);
		//buttonOutUp3_5_dam.setEnabled(false);
		buttonSaveBot3_5_dam.setEnabled(false);
		//buttonOutBot3_5_dam.setEnabled(false);

		checkboxInundacion_dam.setEnabled(false);
		checkboxHuracan_dam.setEnabled(false);
		checkboxTerremoto_dam.setEnabled(false);
		checkboxDeslave_dam.setEnabled(false);
		checkboxDerrumbe_dam.setEnabled(false);

		checkboxInundacionDef_dam.setEnabled(false);
		checkboxHuracanDef_dam.setEnabled(false);
		checkboxTerremotoDef_dam.setEnabled(false);
		checkboxDeslaveDef_dam.setEnabled(false);
		checkboxDerrumbeDef_dam.setEnabled(false);

		editImporte2013_dam.setEnabled(false);
		editDef2013_dam.setEnabled(false);
		editImporte2012_dam.setEnabled(false);
		editDef2012_dam.setEnabled(false);
		editImporte2011_dam.setEnabled(false);
		editDef2011_dam.setEnabled(false);
		editImporte2010_dam.setEnabled(false);
		editDef2010_dam.setEnabled(false);
		editImporte2009_dam.setEnabled(false);
		editDef2009_dam.setEnabled(false);
		editImporte2008_dam.setEnabled(false);
		editDef2008_dam.setEnabled(false);
		editImporte2007_dam.setEnabled(false);
		editDef2007_dam.setEnabled(false);
		editImporte2006_dam.setEnabled(false);
		editDef2006_dam.setEnabled(false);
		editImporte2005_dam.setEnabled(false);
		editDef2005_dam.setEnabled(false);
		editImporte2004_dam.setEnabled(false);
		editDef2004_dam.setEnabled(false);

		buttonSaveUp3_5_dam.setImageResource(R.mipmap.guardar_blocked);
		buttonOutUp3_5_dam.setImageResource(R.mipmap.salir_blocked);
		buttonSaveBot3_5_dam.setImageResource(R.mipmap.guardar_blocked);
		buttonOutBot3_5_dam.setImageResource(R.mipmap.salir_blocked);
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
			buttonSaveUp3_5_dam.setEnabled(false);
			buttonSaveBot3_5_dam.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}



	public void SetPojoDamP3(pojoDams pojo) {

		if (checkboxInundacion_dam.isChecked()) {
			pojo.setFleedFormer(true);
		}
		if (checkboxHuracan_dam.isChecked()) {
			pojo.setHurricaneFormer(true);
		}
		if (checkboxTerremoto_dam.isChecked()) {
			pojo.setEarthQuakeFormer(true);
		}
		if (checkboxDeslave_dam.isChecked()) {
			pojo.setLandSlideFormer(true);
		}
		if (checkboxDerrumbe_dam.isChecked()) {
			pojo.setCollapseFormer(true);
		}

		if (checkboxInundacionDef_dam.isChecked()) {
			pojo.setFleedRisk(true);
		}
		if (checkboxHuracanDef_dam.isChecked()) {
			pojo.setHurricaneRisk(true);
		}
		if (checkboxTerremotoDef_dam.isChecked()) {
			pojo.setEarthQuakeRisk(true);
		}
		if (checkboxDeslaveDef_dam.isChecked()) {
			pojo.setLandSlideRisk(true);
		}
		if (checkboxDerrumbeDef_dam.isChecked()) {
			pojo.setCollapseRisk(true);
		}

		if (editImporte2013_dam.getText().length() > 0 && editImporte2013_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[0] = editImporte2013_dam.getText().toString();
			pojo.getSiniestralityDescription()[0] = editDef2013_dam.getText().toString();
		}

		if (editImporte2012_dam.getText().length() > 0 && editImporte2012_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[1] = editImporte2012_dam.getText().toString();
			pojo.getSiniestralityDescription()[1] = editDef2012_dam.getText().toString();
		}

		if (editImporte2011_dam.getText().length() > 0 && editImporte2011_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[2] = editImporte2011_dam.getText().toString();
			pojo.getSiniestralityDescription()[2] = editDef2011_dam.getText().toString();
		}

		if (editImporte2010_dam.getText().length() > 0 && editImporte2010_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[3] = editImporte2010_dam.getText().toString();
			pojo.getSiniestralityDescription()[3] = editDef2010_dam.getText().toString();
		}

		if (editImporte2009_dam.getText().length() > 0 && editImporte2009_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[4] = editImporte2009_dam.getText().toString();
			pojo.getSiniestralityDescription()[4] = editDef2009_dam.getText().toString();
		}

		if (editImporte2008_dam.getText().length() > 0 && editImporte2008_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[5] = editImporte2008_dam.getText().toString();
			pojo.getSiniestralityDescription()[5] = editDef2008_dam.getText().toString();
		}

		if (editImporte2007_dam.getText().length() > 0 && editImporte2007_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[6] = editImporte2007_dam.getText().toString();
			pojo.getSiniestralityDescription()[6] = editDef2007_dam.getText().toString();
		}

		if (editImporte2006_dam.getText().length() > 0 && editImporte2006_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[7] = editImporte2006_dam.getText().toString();
			pojo.getSiniestralityDescription()[7] = editDef2006_dam.getText().toString();
		}

		if (editImporte2005_dam.getText().length() > 0 && editImporte2005_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[8] = editImporte2005_dam.getText().toString();
			pojo.getSiniestralityDescription()[8] = editDef2005_dam.getText().toString();
		}

		if (editImporte2004_dam.getText().length() > 0 && editImporte2004_dam.getText().toString().compareTo("0.0") != 0) {
			pojo.getSiniestralityValue()[9] = editImporte2004_dam.getText().toString();
			pojo.getSiniestralityDescription()[9] = editDef2004_dam.getText().toString();
		}

	}
}
