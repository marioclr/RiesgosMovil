package com.mclr.mini.riesgosmovil.fragmentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

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
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoTunnel;
import com.mclr.mini.riesgosmovil.modelos.pojoWays;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.StringOperations;

public class WaysTunelFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogTunel dialogAgregarNuevoTunel;
	private ImageButton botonAgregarTunel;
	private ListView listViewTuneles;
	private TextView textTotalTuneles;
	private TextView textTotalVRNTuneles;
	private ArrayList<pojoTunnel> tuneles;
	private ListaAdapter adaptadorListaTuneles;
	pojoTunnel pojoTunel;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	String nombreTunelSeleccionado; 
	double longitudTunelSeleccionado; 
	String VRNTunelSeleccionado;
	String tipoTunelSeleccionado;
	private String selectedRemoveIDs="";

	public WaysTunelFragment()
	{
		tuneles = new ArrayList<pojoTunnel>();
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
		View rootView = inflater.inflate(R.layout.ways_tunel, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSave_Tunel_ways);
		botonGuardar.setOnClickListener(new GuardarTunelesClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBot3_4_healt);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarTunel = (ImageButton) myActivity.findViewById(R.id.buttonAgregarTunel_ways);
		botonAgregarTunel.setOnClickListener(new AgregaTunelClickListener(this));
		textTotalTuneles = (TextView) myActivity.findViewById(R.id.textValorTotalDeTuneles);
		textTotalVRNTuneles = (TextView) myActivity.findViewById(R.id.textValorTotalVRNDeTuneles);

//		pojoTunel = new pojoTunnel();
//		pojoTunel.setTunnelID("3");
//		pojoTunel.setTunnelName("Tunel 1");
//		pojoTunel.setTunnelLength(12345);
//		pojoTunel.setRenewalTunnelValue("87654321");
//		tuneles.add(pojoTunel);
//
//		pojoTunel = new pojoTunnel();
//		pojoTunel.setTunnelID("3");
//		pojoTunel.setTunnelName("Tunel 2");
//		pojoTunel.setTunnelLength(654321);
//		pojoTunel.setRenewalTunnelValue("1234567");
//		tuneles.add(pojoTunel);
//
//		pojoTunel = new pojoTunnel();
//		pojoTunel.setTunnelID("3");
//		pojoTunel.setTunnelName("Tunel 4");
//		pojoTunel.setTunnelLength(428631);
//		pojoTunel.setRenewalTunnelValue("87654321");
//		tuneles.add(pojoTunel);

		listViewTuneles = (ListView) myActivity.findViewById(R.id.listTunel_ways);
		listViewTuneles.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaTuneles = new ListaAdapter(myActivity, R.layout.fila_tunel, tuneles) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_nombre_tunel = (TextView) view.findViewById(R.id.textNombreFilaTunel); 
		            if (texto_nombre_tunel != null) 
		            	texto_nombre_tunel.setText(((pojoTunnel) entrada).getTunnelName()); 

		            TextView texto_longitud_tunel = (TextView) view.findViewById(R.id.textLongitudFilaTunel); 
		            if (texto_longitud_tunel != null)
		            	texto_longitud_tunel.setText(Double.toString(((pojoTunnel) entrada).getTunnelLength()));

		            TextView texto_VRN_tunel = (TextView) view.findViewById(R.id.textVRNFilaTunel); 
		            if (texto_VRN_tunel != null)
		            	texto_VRN_tunel.setText(((pojoTunnel) entrada).getRenewalTunnelValue().toString());

		            TextView texto_tipo_tunel = (TextView) view.findViewById(R.id.textTipoFilaTunel); 
		            if (texto_tipo_tunel != null)
		            	texto_tipo_tunel.setText(((pojoTunnel) entrada).getTunnelTypeName().toString());

		        }
			}
		};
        listViewTuneles.setAdapter(adaptadorListaTuneles);
        registerForContextMenu(listViewTuneles);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoWays pojo = propiertyVW.getWaysPptyDetail(propertyID, postalCodeID);
			for (pojoTunnel pTunel : pojo.getAttachedTunnels())
			{
				tuneles.add(pTunel);
			}
			adaptadorListaTuneles.notifyDataSetChanged();
		}
		calculaTotalPuentesPrincipales();
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaTunelClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaTunelClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevoTunel = new DialogTunel(myActivity, fragment, Constants.WAYS);
            dialogAgregarNuevoTunel.setArguments(datosActuales);

            dialogAgregarNuevoTunel.show(fm, "dialog1");			
		}
	}

	class GuardarTunelesClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoTunnel pTunel : tuneles)
			{
				process.processTunnelSave(propertyID, pTunel);
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

			nombreTunelSeleccionado = ((pojoTunnel)parent.getItemAtPosition(position)).getTunnelName();
			longitudTunelSeleccionado = ((pojoTunnel)parent.getItemAtPosition(position)).getTunnelLength();
			VRNTunelSeleccionado = ((pojoTunnel)parent.getItemAtPosition(position)).getRenewalTunnelValue().toString();
			tipoTunelSeleccionado = ((pojoTunnel)parent.getItemAtPosition(position)).getTunnelType();

			return false;
		}
	}

	public void agregarItemList(boolean bEsNuevo, String nombreTunel, double longitudTunel, String VRNTunel, String tipoTunel, String nombreTipoTunel) {
		pojoTunel = new pojoTunnel();

		if (bEsNuevo)
		{
			pojoTunel = new pojoTunnel();
			pojoTunel.setTunnelID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
		} else {
			pojoTunel = tuneles.get(posicionElementoSeleccionado);
		}

		pojoTunel.setTunnelName(nombreTunel);
		pojoTunel.setTunnelLength(longitudTunel);
		pojoTunel.setTunnelType(tipoTunel);
		pojoTunel.setTunnelTypeName(nombreTipoTunel);
		pojoTunel.setRenewalTunnelValue(VRNTunel);
		if (bEsNuevo)
			tuneles.add(pojoTunel);
		adaptadorListaTuneles.notifyDataSetChanged();
		calculaTotalPuentesPrincipales();
	}

	private void calculaTotalPuentesPrincipales()
	{
		BigDecimal sumaVRN = new BigDecimal("0");
		
		for (pojoTunnel pTunel : tuneles)
		{
			sumaVRN = sumaVRN.add(new BigDecimal(pTunel.getRenewalTunnelValue()));
		}
		textTotalTuneles.setText(String.valueOf(tuneles.size()));
		textTotalVRNTuneles.setText(StringOperations.bigDecimalToString(sumaVRN));
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
			botonAgregarTunel.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Túnel") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
			datosActuales.putString("tipoTunelSeleccionado", tipoTunelSeleccionado);
			datosActuales.putString("nombreTunelSeleccionado", nombreTunelSeleccionado);
			datosActuales.putDouble("longitudTunelSeleccionado", longitudTunelSeleccionado);
			datosActuales.putString("VRNTunelSeleccionado", VRNTunelSeleccionado);

			dialogAgregarNuevoTunel = new DialogTunel(myActivity, this, Constants.WAYS);
			dialogAgregarNuevoTunel.setArguments(datosActuales);
			dialogAgregarNuevoTunel.show(fm, "Modificar Túnel");
		} else if (item.getTitle() == "Eliminar Túnel") {
			Toast.makeText(myActivity, "Eliminar Túnel", Toast.LENGTH_LONG).show();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Túnel");
		menu.add("Eliminar Túnel");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
