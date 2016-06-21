package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.procedures.DatabaseProcedures;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoDamHydraulicFacility;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamUbicacionFragment extends Fragment {
	private MainActivity myActivity = null;
	private DatabaseProcedures dp;
	VWProperties propiertyVW;
	DialogUbicacion dialogAgregarNuevaUbicacion;
	private ImageButton botonAgregarUbicacion;
	private ListView listViewUbicaciones;
	private ArrayList<pojoDamHydraulicFacility> ubicaciones;
	private ListaAdapter adaptadorListaUbicacion;
	pojoDamHydraulicFacility pojoUbicacion;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;

	private String nombreUbicacionSeleccionado;
	private double latitudUbicacionSeleccionado;
	private double longitudUbicacionSeleccionado;
	private double altitudUbicacionSeleccionado;
	private String cuencasHidrologicasUbicacionSeleccionado;
	private String estacionMeteorologicaSMNUbicacionSeleccionado;
	private String tipoPresaUbicacionSeleccionado;
	private String usosUbicacionSeleccionado;
	private double distanciaPoblacionUbicacionSeleccionado;

	private String selectedRemoveIDs="";

	public DamUbicacionFragment()
	{
		ubicaciones = new ArrayList<pojoDamHydraulicFacility>();
	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
		propertyID = myActivity.propertyID;
		postalCodeID = myActivity.postalCodeID;
		dp = new DatabaseProcedures(myActivity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.dams_ubicaciones, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotUbicaciones_dams);
		botonGuardar.setOnClickListener(new GuardarUbicacionClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotUbicaciones_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarUbicacion = (ImageButton) myActivity.findViewById(R.id.buttonAgregarUbicaciones_dams);
		botonAgregarUbicacion.setOnClickListener(new AgregaUbicacionClickListener(this));

//		pojoUbicacion = new pojoDamHydraulicFacility();
//		pojoUbicacion.setFacilityName("jionwd");
//		pojoUbicacion.setLatitude(8128);
//		pojoUbicacion.setLongitude(9342);
//		pojoUbicacion.setAltitude(128);
//		pojoUbicacion.setCatchment("sdspoi");
//		pojoUbicacion.setClosestWeatherSt("X");
//		pojoUbicacion.setDamType("YS");
//		pojoUbicacion.setDamUse("ijis");
//		pojoUbicacion.setClosestCity(8921809);
//		ubicaciones.add(pojoUbicacion);
//
//		pojoUbicacion = new pojoDamHydraulicFacility();
//		pojoUbicacion.setFacilityName("oujasas");
//		pojoUbicacion.setLatitude(1237412);
//		pojoUbicacion.setLongitude(1874314);
//		pojoUbicacion.setAltitude(128);
//		pojoUbicacion.setCatchment("sdspoi");
//		pojoUbicacion.setClosestWeatherSt("Y");
//		pojoUbicacion.setDamType("YS");
//		pojoUbicacion.setDamUse("ijis");
//		pojoUbicacion.setClosestCity(8921809);
//		ubicaciones.add(pojoUbicacion);
//
//		pojoUbicacion = new pojoDamHydraulicFacility();
//		pojoUbicacion.setFacilityName("Magtsa");
//		pojoUbicacion.setLatitude(737214);
//		pojoUbicacion.setLongitude(5346727);
//		pojoUbicacion.setAltitude(128);
//		pojoUbicacion.setCatchment("sdspoi");
//		pojoUbicacion.setClosestWeatherSt("Z");
//		pojoUbicacion.setDamType("YS");
//		pojoUbicacion.setDamUse("ijis");
//		pojoUbicacion.setClosestCity(8921809);
//		ubicaciones.add(pojoUbicacion);

		listViewUbicaciones = (ListView) myActivity.findViewById(R.id.listUbicaciones_dams);
		listViewUbicaciones.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaUbicacion = new ListaAdapter(myActivity, R.layout.fila_ubicaciones, ubicaciones) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView textNombreFilaUbicaciones = (TextView) view.findViewById(R.id.textNombreFilaUbicaciones);
		            if (textNombreFilaUbicaciones != null) 
		            	textNombreFilaUbicaciones.setText(((pojoDamHydraulicFacility) entrada).getFacilityName());

		            TextView textLongitudFilaUbicaciones = (TextView) view.findViewById(R.id.textLongitudFilaUbicaciones);
		            if (textLongitudFilaUbicaciones != null)
		            	textLongitudFilaUbicaciones.setText(String.valueOf(((pojoDamHydraulicFacility) entrada).getLatitude()));

		            TextView textLatitudFilaUbicaciones = (TextView) view.findViewById(R.id.textLatitudFilaUbicaciones);
		            if (textLatitudFilaUbicaciones != null)
		            	textLatitudFilaUbicaciones.setText(String.valueOf(((pojoDamHydraulicFacility) entrada).getLatitude()));

		            TextView textAltitudFilaUbicaciones = (TextView) view.findViewById(R.id.textAltitudFilaUbicaciones);
		            if (textAltitudFilaUbicaciones != null)
		            	textAltitudFilaUbicaciones.setText(String.valueOf(((pojoDamHydraulicFacility) entrada).getAltitude()));

		            TextView textCuencasFilaUbicaciones = (TextView) view.findViewById(R.id.textCuencasFilaUbicaciones);
		            if (textCuencasFilaUbicaciones != null)
		            	if (((pojoDamHydraulicFacility) entrada).getCatchmentDescription().toString().length() > 0)
		            		textCuencasFilaUbicaciones.setText(((pojoDamHydraulicFacility) entrada).getCatchmentDescription().toString());
		            	else
		            		textCuencasFilaUbicaciones.setText("Ninguno");

		            TextView textEstacionesFilaUbicaciones = (TextView) view.findViewById(R.id.textEstacionesFilaUbicaciones);
		            if (textEstacionesFilaUbicaciones != null)
		            	if (((pojoDamHydraulicFacility) entrada).getClosestWeatherSt().toString().length() > 0)
		            		textEstacionesFilaUbicaciones.setText(dp.getWeatherStationInfo(((pojoDamHydraulicFacility) entrada).getClosestWeatherSt().toString()));
		            	else
		            		textEstacionesFilaUbicaciones.setText("Ninguno");

		            TextView textTipoFilaUbicaciones = (TextView) view.findViewById(R.id.textTipoFilaUbicaciones);
		            if (textTipoFilaUbicaciones != null)
		            	if (((pojoDamHydraulicFacility) entrada).getDamTypeDescription().toString().length() > 0)
		            		textTipoFilaUbicaciones.setText(((pojoDamHydraulicFacility) entrada).getDamTypeDescription().toString());
		            	else
		            		textTipoFilaUbicaciones.setText("Ninguno");

		            TextView textUsoFilaUbicaciones = (TextView) view.findViewById(R.id.textUsoFilaUbicaciones);
		            if (textUsoFilaUbicaciones != null)
		            	if (((pojoDamHydraulicFacility) entrada).getDamUseDescription().toString().length() > 0)
		            		textUsoFilaUbicaciones.setText(((pojoDamHydraulicFacility) entrada).getDamUseDescription().toString());
		            	else
		            		textUsoFilaUbicaciones.setText("Ninguno");

		            TextView textDistanciaFilaUbicaciones = (TextView) view.findViewById(R.id.textDistanciaFilaUbicaciones);
		            if (textDistanciaFilaUbicaciones != null)
		            	textDistanciaFilaUbicaciones.setText(String.valueOf(((pojoDamHydraulicFacility) entrada).getClosestCity()));

		        }
			}
		};

        listViewUbicaciones.setAdapter(adaptadorListaUbicacion);
        registerForContextMenu(listViewUbicaciones);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoDamHydraulicFacility pUbicacion : pojo.getDamHydroFacility())
			{
				ubicaciones.add(pUbicacion);
			}
			adaptadorListaUbicacion.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaUbicacionClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaUbicacionClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevaUbicacion = new DialogUbicacion(myActivity, fragment, Constants.DAM);
            dialogAgregarNuevaUbicacion.setArguments(datosActuales);

            dialogAgregarNuevaUbicacion.show(fm, "Agregarla información a la nueva ubicación");			
		}
	}

	class GuardarUbicacionClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoDamHydraulicFacility pUbicacion : ubicaciones)
			{
				process.processUbicacionSave(propertyID, pUbicacion);
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
				e.printStackTrace();
			}
		}
	}

	class PresionaElementoLista implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {

			posicionElementoSeleccionado = position;

			nombreUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getFacilityName();
			latitudUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getLatitude();
			longitudUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getLongitude();
			altitudUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getAltitude();
			cuencasHidrologicasUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getCatchment();
			estacionMeteorologicaSMNUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getClosestWeatherSt();
			tipoPresaUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getDamType();
			usosUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getDamUse();
			distanciaPoblacionUbicacionSeleccionado = ((pojoDamHydraulicFacility)parent.getItemAtPosition(position)).getClosestCity();

			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo,
			String nombreUbicacionSeleccionado,
			double latitudUbicacionSeleccionado,
			double longitudUbicacionSeleccionado,
			double altitudUbicacionSeleccionado,
			String cuencasHidrologicasUbicacionSeleccionado,
			String descCuencasHidrologicasUbicacionSeleccionado,
			String estacionMeteorologicaSMNUbicacionSeleccionado,
			String descEstacionMeteorologicaSMNUbicacionSeleccionado,
			String tipoPresaUbicacionSeleccionado,
			String descTipoPresaUbicacionSeleccionado,
			String usosUbicacionSeleccionado,
			String descUsosUbicacionSeleccionado,
			double distanciaPoblacionUbicacionSeleccionado)
	{
		if (bEsNuevo)
		{
			pojoUbicacion = new pojoDamHydraulicFacility();
			pojoUbicacion.setFacilityID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
		} else {
			pojoUbicacion = ubicaciones.get(posicionElementoSeleccionado);
		}
		pojoUbicacion.setFacilityName(nombreUbicacionSeleccionado);
		pojoUbicacion.setLatitude(latitudUbicacionSeleccionado);
		pojoUbicacion.setLongitude(longitudUbicacionSeleccionado);
		pojoUbicacion.setAltitude(altitudUbicacionSeleccionado);
		pojoUbicacion.setCatchment(cuencasHidrologicasUbicacionSeleccionado);
		pojoUbicacion.setCatchmentDescription(descCuencasHidrologicasUbicacionSeleccionado);
		pojoUbicacion.setClosestWeatherSt(estacionMeteorologicaSMNUbicacionSeleccionado);
		pojoUbicacion.setClosestWeatherStDescription(descEstacionMeteorologicaSMNUbicacionSeleccionado);
		pojoUbicacion.setDamType(tipoPresaUbicacionSeleccionado);
		pojoUbicacion.setDamTypeDescription(descTipoPresaUbicacionSeleccionado);
		pojoUbicacion.setDamUse(usosUbicacionSeleccionado);
		pojoUbicacion.setDamUseDescription(descUsosUbicacionSeleccionado);
		pojoUbicacion.setClosestCity(distanciaPoblacionUbicacionSeleccionado);
		if (bEsNuevo)
			ubicaciones.add(pojoUbicacion);
		adaptadorListaUbicacion.notifyDataSetChanged();
	}

	public void AnalizaID()
	{
		if (!myActivity.propertyID.equals(Constants.GENERICO))
		{
			Toast.makeText(myActivity, "Tienes un ID, puedes continuar...", Toast.LENGTH_LONG).show();
		}
		else
		{
			botonGuardar.setEnabled(false);
			botonAgregarUbicacion.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Ubicaciones") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("nombreUbicacionSeleccionado", nombreUbicacionSeleccionado);
            datosActuales.putDouble("latitudUbicacionSeleccionado", latitudUbicacionSeleccionado);
            datosActuales.putDouble("longitudUbicacionSeleccionado", longitudUbicacionSeleccionado);
            datosActuales.putDouble("altitudUbicacionSeleccionado", altitudUbicacionSeleccionado);
            datosActuales.putString("cuencasHidrologicasUbicacionSeleccionado", cuencasHidrologicasUbicacionSeleccionado);
            datosActuales.putString("estacionMeteorologicaSMNUbicacionSeleccionado", estacionMeteorologicaSMNUbicacionSeleccionado);
            datosActuales.putString("tipoPresaUbicacionSeleccionado", tipoPresaUbicacionSeleccionado);
            datosActuales.putString("usosUbicacionSeleccionado", usosUbicacionSeleccionado);
            datosActuales.putDouble("distanciaPoblacionUbicacionSeleccionado", distanciaPoblacionUbicacionSeleccionado);

            dialogAgregarNuevaUbicacion = new DialogUbicacion(myActivity, this, Constants.DAM);
            dialogAgregarNuevaUbicacion.setArguments(datosActuales);
            dialogAgregarNuevaUbicacion.show(fm, "Modificar Ubicaciones");

		} else if (item.getTitle() == "Eliminar Ubicaciones") {
			//Toast.makeText(myActivity, "Eliminar Dique: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			ubicaciones.remove(posicionElementoSeleccionado);
			adaptadorListaUbicacion.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Ubicaciones");
		menu.add("Eliminar Ubicaciones");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean validaCampos(
			String nombreUbicacionSeleccionado,
			double latitudUbicacionSeleccionado,
			double longitudUbicacionSeleccionado,
			double altitudUbicacionSeleccionado,
			String cuencasHidrologicasUbicacionSeleccionado,
			String descCuencasHidrologicasUbicacionSeleccionado,
			String estacionMeteorologicaSMNUbicacionSeleccionado,
			String descEstacionMeteorologicaSMNUbicacionSeleccionado,
			String tipoPresaUbicacionSeleccionado,
			String descTipoPresaUbicacionSeleccionado,
			String usosUbicacionSeleccionado,
			String descUsosUbicacionSeleccionado,
			double distanciaPoblacionUbicacionSeleccionado)
	{
		if (nombreUbicacionSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if (latitudUbicacionSeleccionado==0) {
			Toast.makeText(myActivity, "Se debe indicar la latitud de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if (longitudUbicacionSeleccionado==0) {
			Toast.makeText(myActivity, "Se debe indicar la longitud de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if (altitudUbicacionSeleccionado==0) {
			Toast.makeText(myActivity, "Se debe indicar la altitud de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if(cuencasHidrologicasUbicacionSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar las cuencas hidrologicas de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if(estacionMeteorologicaSMNUbicacionSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar las estaciones meteorologicas SMN de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipoPresaUbicacionSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de presas de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if(usosUbicacionSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar el uso de la presa de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		if (distanciaPoblacionUbicacionSeleccionado==0) {
			Toast.makeText(myActivity, "Se debe indicar la distancia hacía la población más cercana aguas abajo de la ubicación", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
}
