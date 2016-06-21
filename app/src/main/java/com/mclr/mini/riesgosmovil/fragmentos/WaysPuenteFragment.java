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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoBridges;
import com.mclr.mini.riesgosmovil.modelos.pojoWays;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.StringOperations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class WaysPuenteFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogPuente dialogAgregarNuevoPuente;
	private ImageButton botonAgregarPuente;
	private ListView listViewPuentes;
	private TextView textTotalPuentesPrincipales;
	private TextView textTotalVRNPuentesPrincipales;
	private ArrayList<pojoBridges> puentes;
	private ListaAdapter adaptadorListaPuentes;
	pojoBridges pojoPuente;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	private boolean esPrincipalSelected;
	private String tipoPuenteSelected;
	private String nombrePuenteSelected;
	private Double longitudPuenteSelected;
	private String VRNPuenteSelected;

	private String selectedRemoveIDs="";

	public WaysPuenteFragment()
	{
		puentes = new ArrayList<pojoBridges>();
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
		View rootView = inflater.inflate(R.layout.ways_puente, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSave_Puente_ways);
		botonGuardar.setOnClickListener(new GuardarPuentesClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBot4_4_healt);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarPuente = (ImageButton) myActivity.findViewById(R.id.buttonAgregarPuente_ways);
		botonAgregarPuente.setOnClickListener(new AgregaPuenteClickListener(this));
		textTotalPuentesPrincipales = (TextView) myActivity.findViewById(R.id.textValorTotalDePuentesPrincipales);
		textTotalVRNPuentesPrincipales = (TextView) myActivity.findViewById(R.id.textValorTotalVRNDePuentesPrincipales);

//		pojoPuente = new pojoBridges();
//		pojoPuente.setBridgeID("3");
//		pojoPuente.setBridgeName("Puente 1");
//		pojoPuente.setBridgeLength(12345);
//		pojoPuente.setRenewalBridgeValue(new BigDecimal(87654321));
//		puentes.add(pojoPuente);
//
//		pojoPuente = new pojoBridges();
//		pojoPuente.setBridgeID("3");
//		pojoPuente.setBridgeName("Puente 2");
//		pojoPuente.setBridgeLength(12345);
//		pojoPuente.setRenewalBridgeValue(new BigDecimal(87654321));
//		puentes.add(pojoPuente);
//
//		pojoPuente = new pojoBridges();
//		pojoPuente.setBridgeID("3");
//		pojoPuente.setBridgeName("Puente 4");
//		pojoPuente.setBridgeLength(12345);
//		pojoPuente.setRenewalBridgeValue(new BigDecimal(87654321));
//		puentes.add(pojoPuente);

		listViewPuentes = (ListView) myActivity.findViewById(R.id.listPuente_ways);
		listViewPuentes.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaPuentes = new ListaAdapter(myActivity, R.layout.fila_puente, puentes) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		        	ImageView imagen_principal = (ImageView) view.findViewById(R.id.imageEsPrincipalFilaPuente);
		            if (imagen_principal != null)
		            	if (((pojoBridges) entrada).isMain())
		            		imagen_principal.setImageResource(R.drawable.ok);
//		            	else
//		            		imagen_principal.setImageResource(R.drawable.rejected);

		            TextView texto_nombre_puente = (TextView) view.findViewById(R.id.textNombreFilaPuente);
		            if (texto_nombre_puente != null) 
		            	texto_nombre_puente.setText(((pojoBridges) entrada).getBridgeName());

		            TextView texto_longitud_puente = (TextView) view.findViewById(R.id.textLongitudFilaPuente);
		            if (texto_longitud_puente != null)
		            	texto_longitud_puente.setText(Double.toString(((pojoBridges) entrada).getBridgeLength()));

		            TextView texto_VRN_puente = (TextView) view.findViewById(R.id.textVRNFilaPuente);
		            if (texto_VRN_puente != null)
		            	texto_VRN_puente.setText(((pojoBridges) entrada).getRenewalBridgeValue().toString());

		            TextView texto_tipo_puente = (TextView) view.findViewById(R.id.textTipoFilaPuente);
		            if (texto_tipo_puente != null)
		            	if (((pojoBridges) entrada).getBridgeTypeName().toString().length() > 0)
		            		texto_tipo_puente.setText(((pojoBridges) entrada).getBridgeTypeName().toString());
		            	else
		            		texto_tipo_puente.setText("Ninguno");

		        }
			}
		};
        listViewPuentes.setAdapter(adaptadorListaPuentes);
        registerForContextMenu(listViewPuentes);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoWays pojo = propiertyVW.getWaysPptyDetail(propertyID, postalCodeID);
			for (pojoBridges pPuente : pojo.getAttachedWays())
			{
				puentes.add(pPuente);
			}
			adaptadorListaPuentes.notifyDataSetChanged();
		}
		calculaTotalPuentesPrincipales();
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaPuenteClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaPuenteClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevoPuente = new DialogPuente(myActivity, fragment, Constants.WAYS);
            dialogAgregarNuevoPuente.setArguments(datosActuales);

            dialogAgregarNuevoPuente.show(fm, "Agregar información del nuevo Túnel");			
		}
	}

	class GuardarPuentesClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoBridges pPuente : puentes)
			{
				process.processBridgeSave(propertyID, pPuente);
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
			esPrincipalSelected = ((pojoBridges)parent.getItemAtPosition(position)).isMain();
			tipoPuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeType();
			nombrePuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeName();
			longitudPuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeLength();
			VRNPuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getRenewalBridgeValue();

			//pojoSeleccionado = (pojoBridges)parent.getItemAtPosition(position);
			//Toast.makeText(myActivity, property_id, Toast.LENGTH_LONG).show();
			return false;
		}
	}

	public void agregarItemList(boolean bEsNuevo, boolean bPrincipal, String nombrePuente, double longitudPuente, 
			String VRNPuente, String tipoPuente, String nombreTipoPuente) {

		if (bEsNuevo)
		{
			pojoPuente = new pojoBridges();
			pojoPuente.setBridgeID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
		} else {
			pojoPuente = puentes.get(posicionElementoSeleccionado);
		}

		if(bPrincipal)
			pojoPuente.setMain(true);
		pojoPuente.setBridgeName(nombrePuente);
		pojoPuente.setBridgeLength(longitudPuente);
		pojoPuente.setBridgeType(tipoPuente);
		pojoPuente.setBridgeTypeName(nombreTipoPuente);
		pojoPuente.setRenewalBridgeValue(VRNPuente);
		if (bEsNuevo)
			puentes.add(pojoPuente);
		adaptadorListaPuentes.notifyDataSetChanged();
		calculaTotalPuentesPrincipales();
	}

	private void calculaTotalPuentesPrincipales()
	{
		int numeroPuentesPrincipales = 0;
		BigDecimal sumaVRN = new BigDecimal("0");
		
		for (pojoBridges pPuente : puentes)
		{
			if(pPuente.isMain())
				numeroPuentesPrincipales++;
			sumaVRN = sumaVRN.add(new BigDecimal(pPuente.getRenewalBridgeValue()));
		}
		textTotalPuentesPrincipales.setText(String.valueOf(numeroPuentesPrincipales));
		textTotalVRNPuentesPrincipales.setText(StringOperations.bigDecimalToString(sumaVRN));
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
			botonAgregarPuente.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Puente") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
			datosActuales.putBoolean("esPrincipalSelected", esPrincipalSelected);
            datosActuales.putString("tipoPuenteSeleccionado", tipoPuenteSelected);
            datosActuales.putString("nombrePuenteSeleccionado", nombrePuenteSelected);
            datosActuales.putDouble("longitudPuenteSeleccionado", longitudPuenteSelected);
            datosActuales.putString("VRNPuenteSelected", VRNPuenteSelected);

            dialogAgregarNuevoPuente = new DialogPuente(myActivity, this, Constants.WAYS);
            dialogAgregarNuevoPuente.setArguments(datosActuales);
            dialogAgregarNuevoPuente.show(fm, "Modificar Puente");	

		} else if (item.getTitle() == "Eliminar Puente") {
			//Toast.makeText(myActivity, "Eliminar Puente: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			selectedRemoveIDs += "'" + puentes.get(posicionElementoSeleccionado).getBridgeID() + "', ";
			puentes.remove(posicionElementoSeleccionado);
			adaptadorListaPuentes.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Puente");
		menu.add("Eliminar Puente");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
