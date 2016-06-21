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
import com.mclr.mini.riesgosmovil.modelos.ProcessPropertiesActions;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.modelos.pojoIrrigationChannel;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamExtensionsFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogExtension dialogAgregarNuevaExtension;
	private ImageButton botonAgregarExtension;
	private ListView listViewExtension;
	private ArrayList<pojoIrrigationChannel> extensiones;
	private ListaAdapter adaptadorListaExtension;
	pojoIrrigationChannel pojoExtension;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;

	private String identificacionExtensionSeleccionado;
	private double extensionExtensionSeleccionado;
	private double caudalExtensionSeleccionado;
	private String valorExtensionSeleccionado;

	private String selectedRemoveIDs="";

	public DamExtensionsFragment()
	{
		extensiones = new ArrayList<pojoIrrigationChannel>();
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
		View rootView = inflater.inflate(R.layout.dams_extension, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotExtension_dams);
		botonGuardar.setOnClickListener(new GuardarPuentesClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotExtension_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarExtension = (ImageButton) myActivity.findViewById(R.id.buttonAgregarExtension_dams);
		botonAgregarExtension.setOnClickListener(new AgregaExtensionClickListener(this));

//		pojoExtension = new pojoIrrigationChannel();
//		pojoExtension.setChannelName("Ext 01");
//		pojoExtension.setExtension(29082);
//		pojoExtension.setFlow(23098);
//		pojoExtension.setValue("X");
//		extensiones.add(pojoExtension);
//
//		pojoExtension = new pojoIrrigationChannel();
//		pojoExtension.setChannelName("Ext 02");
//		pojoExtension.setExtension(79822);
//		pojoExtension.setFlow(5181);
//		pojoExtension.setValue("YY");
//		extensiones.add(pojoExtension);
//
//		pojoExtension = new pojoIrrigationChannel();
//		pojoExtension.setChannelName("Ext 03");
//		pojoExtension.setExtension(51235);
//		pojoExtension.setFlow(8213821);
//		pojoExtension.setValue("ZZZ");
//		extensiones.add(pojoExtension);

		listViewExtension = (ListView) myActivity.findViewById(R.id.listExtension_dams);
		listViewExtension.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaExtension = new ListaAdapter(myActivity, R.layout.fila_extension, extensiones) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_identificacion_extension = (TextView) view.findViewById(R.id.editTextoIdentificadorExtension);
		            if (texto_identificacion_extension != null) 
		            	texto_identificacion_extension.setText(((pojoIrrigationChannel) entrada).getChannelName());

		            TextView texto_texto_extension_extension = (TextView) view.findViewById(R.id.editTextoExtensionExtension);
		            if (texto_texto_extension_extension != null)
		            	texto_texto_extension_extension.setText(String.valueOf(((pojoIrrigationChannel) entrada).getExtension()));

		            TextView texto_caudal_extension = (TextView) view.findViewById(R.id.editTextoCaudalExtension);
		            if (texto_caudal_extension != null)
		            	texto_caudal_extension.setText(String.valueOf(((pojoIrrigationChannel) entrada).getFlow()));

		            TextView texto_valor_extension = (TextView) view.findViewById(R.id.editTextoValorExtension);
		            if (texto_valor_extension != null)
		            	texto_valor_extension.setText(String.valueOf(((pojoIrrigationChannel) entrada).getValue()));

		        }
			}
		};

        listViewExtension.setAdapter(adaptadorListaExtension);
        registerForContextMenu(listViewExtension);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoIrrigationChannel pDique : pojo.getDamIrrigation())
			{
				extensiones.add(pDique);
			}
			adaptadorListaExtension.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaExtensionClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaExtensionClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevaExtension = new DialogExtension(myActivity, fragment, Constants.DAM);
            dialogAgregarNuevaExtension.setArguments(datosActuales);

            dialogAgregarNuevaExtension.show(fm, "Agregar información de la nueva Extensión...");			
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
			for (pojoIrrigationChannel pExtension : extensiones)
			{
				process.processExtensionSave(propertyID, pExtension);
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

			identificacionExtensionSeleccionado = ((pojoIrrigationChannel)parent.getItemAtPosition(position)).getChannelName();
			extensionExtensionSeleccionado = ((pojoIrrigationChannel)parent.getItemAtPosition(position)).getExtension();
			caudalExtensionSeleccionado = ((pojoIrrigationChannel)parent.getItemAtPosition(position)).getFlow();
			valorExtensionSeleccionado = ((pojoIrrigationChannel)parent.getItemAtPosition(position)).getValue();

			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo,
			String identificacionExtensionSeleccionado, 
			double extensionExtensionSeleccionado, 
			double caudalExtensionSeleccionado,
			String valorExtensionSeleccionado) 
	{
		if (bEsNuevo)
		{
			pojoExtension = new pojoIrrigationChannel();
			pojoExtension.setChannelID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
		} else {
			pojoExtension = extensiones.get(posicionElementoSeleccionado);
		}

		pojoExtension.setChannelName(identificacionExtensionSeleccionado);
		pojoExtension.setExtension(extensionExtensionSeleccionado);
		pojoExtension.setFlow(caudalExtensionSeleccionado);
		pojoExtension.setValue(valorExtensionSeleccionado);
		if (bEsNuevo)
			extensiones.add(pojoExtension);
		adaptadorListaExtension.notifyDataSetChanged();
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
			botonAgregarExtension.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Extensión...") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();

            datosActuales.putString("identificacionExtensionSeleccionado", identificacionExtensionSeleccionado);
            datosActuales.putDouble("extensionExtensionSeleccionado", extensionExtensionSeleccionado);
            datosActuales.putDouble("caudalExtensionSeleccionado", caudalExtensionSeleccionado);
            datosActuales.putString("valorExtensionSeleccionado", valorExtensionSeleccionado);

            dialogAgregarNuevaExtension = new DialogExtension(myActivity, this, Constants.DAM);
            dialogAgregarNuevaExtension.setArguments(datosActuales);
            dialogAgregarNuevaExtension.show(fm, "Modificar Extensión, caudal y valores...");

		} else if (item.getTitle() == "Eliminar Extensión...") {
			//Toast.makeText(myActivity, "Eliminar Extensión...: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			extensiones.remove(posicionElementoSeleccionado);
			adaptadorListaExtension.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Extensión...");
		menu.add("Eliminar Extensión...");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
}
