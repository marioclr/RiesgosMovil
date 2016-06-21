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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoBridges;
import com.mclr.mini.riesgosmovil.modelos.pojoWays;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;

public class DamAtaguiaFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propierty;

	DialogPuente nuevoDialog1;
	Button botonazo;
	private ImageButton botonDialog;
	ListView listaPuentes;
	private ArrayList<pojoBridges> puentes;
	private ListaAdapter adaptadorListaPuentes;
	pojoBridges puente;

	private ImageButton botonGuardar;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	private String bridgeIDSelected;
	private String TipoPuenteSelected;
	private String nombrePuenteSelected;
	private Double longitudPuenteSelected;
	private String VRNPuenteSelected;

	public DamAtaguiaFragment()
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
		View rootView = inflater.inflate(R.layout.dams_ataguia, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotAtaguia_dams);
		botonDialog = (ImageButton) myActivity.findViewById(R.id.buttonAgregarAtaguia_dams);
		botonDialog.setOnClickListener(new AgregaTunelClickListener(this));

		puente = new pojoBridges();
		puente.setBridgeID("3");
		puente.setBridgeName("Puente 1");
		puente.setBridgeLength(12345);
		puente.setRenewalBridgeValue("87654321");
		puentes.add(puente);

		puente = new pojoBridges();
		puente.setBridgeID("3");
		puente.setBridgeName("Puente 2");
		puente.setBridgeLength(12345);
		puente.setRenewalBridgeValue("87654321");
		puentes.add(puente);

		puente = new pojoBridges();
		puente.setBridgeID("3");
		puente.setBridgeName("Puente 4");
		puente.setBridgeLength(12345);
		puente.setRenewalBridgeValue("87654321");
		puentes.add(puente);

		listaPuentes = (ListView) myActivity.findViewById(R.id.listAtaguia_dams);
		listaPuentes.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaPuentes = new ListaAdapter(myActivity, R.layout.fila_ataguia, puentes) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

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
		            	texto_tipo_puente.setText(((pojoBridges) entrada).getBridgeTypeName().toString());

		        }
			}
		};
        listaPuentes.setAdapter(adaptadorListaPuentes);
        registerForContextMenu(listaPuentes);

		propierty = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoWays pojo = propierty.getWaysPptyDetail(propertyID, postalCodeID);
			for (pojoBridges pPuente : pojo.getAttachedWays())
			{
				puentes.add(pPuente);
			}
			adaptadorListaPuentes.notifyDataSetChanged();
		}
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
            nuevoDialog1 = new DialogPuente(myActivity, fragment, Constants.WAYS);
            nuevoDialog1.show(fm, "dialog1");			
		}
	}

	class PresionaElementoLista implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {

			posicionElementoSeleccionado = position;
			bridgeIDSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeID();
			TipoPuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeType();
			nombrePuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeName();
			longitudPuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getBridgeLength();
			VRNPuenteSelected = ((pojoBridges)parent.getItemAtPosition(position)).getRenewalBridgeValue();

			//pojoSeleccionado = (pojoBridges)parent.getItemAtPosition(position);
			//Toast.makeText(myActivity, property_id, Toast.LENGTH_LONG).show();
			return false;
		}

	}

	public void agregarItemList(String nombrePuente, double longitudPuente, String VRNPuente, String tipoPuente) {
		puente = new pojoBridges();

		puente.setBridgeID("4");
		puente.setBridgeName(nombrePuente);
		puente.setBridgeLength(longitudPuente);
		puente.setBridgeType(tipoPuente);
		puente.setRenewalBridgeValue(VRNPuente);
		puentes.add(puente);
		adaptadorListaPuentes.notifyDataSetChanged();
	}

	public void AnalizaID()
	{
		if (!myActivity.propertyID.equals(Constants.GENERICO))
		{
			Toast.makeText(myActivity, "Tienes un ID, puedes continuar...", Toast.LENGTH_LONG).show();
		}
		else
		{
			botonGuardar.setEnabled(true);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Puente") {
			Toast.makeText(myActivity, "Modificar Puente: " + bridgeIDSelected, Toast.LENGTH_LONG).show();

			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("idPuenteSeleccionado", bridgeIDSelected);
            datosActuales.putString("tipoPuenteSeleccionado", TipoPuenteSelected);
            datosActuales.putString("nombrePuenteSeleccionado", nombrePuenteSelected);
            datosActuales.putDouble("longitudPuenteSeleccionado", longitudPuenteSelected);
            //datosActuales.putDouble("longitudPuenteSeleccionado", longitudPuenteSelected);

            nuevoDialog1 = new DialogPuente(myActivity, this, Constants.WAYS);
            nuevoDialog1.setArguments(datosActuales);
            nuevoDialog1.show(fm, "dialog1");	

		} else if (item.getTitle() == "Eliminar Puente") {
			//Toast.makeText(myActivity, "Eliminar Puente: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
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
