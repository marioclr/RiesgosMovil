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
import com.mclr.mini.riesgosmovil.modelos.pojoVenting;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamOtrosDesfoguesFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogOtrosDesfogues dialogAgregarNuevoDesfogue;
	private ImageButton botonAgregarDesfogue;
	private ListView listViewDesfogue;
	private ArrayList<pojoVenting> desfogue;
	private ListaAdapter adaptadorListaDesfogue;
	pojoVenting pojoDesfogues;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	String nombreDesfogue;
	String propositoDesfogue;
	String tipoDesfogue;
	double capacidadDesfogue;
	double elevacionDesfogue;
	double dimensionDesfogue;
	String comentariosDesfogue;

	private String selectedRemoveIDs="";

	public DamOtrosDesfoguesFragment()
	{
		desfogue = new ArrayList<pojoVenting>();
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
		View rootView = inflater.inflate(R.layout.dams_desfogues, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotDesfogues_dams);
		botonGuardar.setOnClickListener(new GuardarOtrosDesfoguesClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotDesfogues_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarDesfogue = (ImageButton) myActivity.findViewById(R.id.buttonAgregarDesfogues_dams);
		botonAgregarDesfogue.setOnClickListener(new AgregaGaleriaClickListener(this));

//		pojoDesfogues = new pojoVenting();
//		pojoDesfogues.setVentingName("1");
//		pojoDesfogues.setVentingType("1");
//		pojoDesfogues.setVentingCapacity(1234);
//		pojoDesfogues.setThresholdElevation(4321);
//		pojoDesfogues.setComments("1");
//		pojoDesfogues.setVentingDimension(25471);
//		desfogue.add(pojoDesfogues);
//
//		pojoDesfogues = new pojoVenting();
//		pojoDesfogues.setVentingName("hola");
//		pojoDesfogues.setVentingType("1");
//		pojoDesfogues.setVentingCapacity(1234);
//		pojoDesfogues.setThresholdElevation(4321);
//		pojoDesfogues.setComments("1");
//		pojoDesfogues.setVentingDimension(25471);
//		desfogue.add(pojoDesfogues);
//
//		pojoDesfogues = new pojoVenting();
//		pojoDesfogues.setVentingName("cuatro");
//		pojoDesfogues.setVentingType("1");
//		pojoDesfogues.setVentingCapacity(1234);
//		pojoDesfogues.setThresholdElevation(4321);
//		pojoDesfogues.setComments("1");
//		pojoDesfogues.setVentingDimension(25471);
//		desfogue.add(pojoDesfogues);

		listViewDesfogue = (ListView) myActivity.findViewById(R.id.listDesfogues_dams);
		listViewDesfogue.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaDesfogue = new ListaAdapter(myActivity, R.layout.fila_desfogues, desfogue) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_nombe_desfogue = (TextView) view.findViewById(R.id.textNombreFilaDesfogues);
		            if (texto_nombe_desfogue != null) 
		            	texto_nombe_desfogue.setText(((pojoVenting) entrada).getVentingName());
		            
		            TextView texto_proposito_desfogue = (TextView) view.findViewById(R.id.textPropositoFilaDesfogues);
		            if (texto_proposito_desfogue != null) 
		            	texto_proposito_desfogue.setText(((pojoVenting) entrada).getVentingPorpouse());

		            TextView texto_tipo_desfogue = (TextView) view.findViewById(R.id.textTipoFilaDesfogues);
		            if (texto_tipo_desfogue != null) 
		            	texto_tipo_desfogue.setText(((pojoVenting) entrada).getVentingType());
		            
		            TextView texto_capacidad_desfogue = (TextView) view.findViewById(R.id.textCapacidadFilaDesfogues);
		            if (texto_capacidad_desfogue != null)
		            	texto_capacidad_desfogue.setText(String.valueOf(((pojoVenting) entrada).getVentingCapacity()));
		            
		            TextView texto_elevacion_desfogue = (TextView) view.findViewById(R.id.textElevacionFilaDesfogues);
		            if (texto_elevacion_desfogue != null)
		            	texto_elevacion_desfogue.setText(String.valueOf(((pojoVenting) entrada).getThresholdElevation()));

		            TextView texto_diemension_desfogue = (TextView) view.findViewById(R.id.textDimensionesFilaDesfogues);
		            if (texto_diemension_desfogue != null)
		            	texto_diemension_desfogue.setText(String.valueOf(((pojoVenting) entrada).getVentingDimension()));

		            TextView texto_comentarios_desfogue = (TextView) view.findViewById(R.id.textComentariosFilaDesfogues);
		            if (texto_comentarios_desfogue != null) 
		            	texto_comentarios_desfogue.setText(((pojoVenting) entrada).getComments());
		            
		        }
			}
		};

        listViewDesfogue.setAdapter(adaptadorListaDesfogue);
        registerForContextMenu(listViewDesfogue);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoVenting pDesfogue : pojo.getDamVentings())
			{
				desfogue.add(pDesfogue);
			}
			adaptadorListaDesfogue.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaGaleriaClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaGaleriaClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevoDesfogue = new DialogOtrosDesfogues(myActivity, fragment, Constants.DAM);
            dialogAgregarNuevoDesfogue.setArguments(datosActuales);

            dialogAgregarNuevoDesfogue.show(fm, "Agregar información de la nueva Galería");			
		}
	}

	class GuardarOtrosDesfoguesClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoVenting pDesfogue : desfogue)
			{
				process.processOtrosDesfoguesSave(propertyID, pDesfogue);
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
			nombreDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getVentingName();
			propositoDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getVentingPorpouse();
			tipoDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getVentingType();
			capacidadDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getVentingCapacity();
			elevacionDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getThresholdElevation();
			dimensionDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getVentingDimension();
			comentariosDesfogue = ((pojoVenting)parent.getItemAtPosition(position)).getComments();
			
			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo,
			String nombreDesfogue,
			String propositoDesfogue,
			String tipoDesfogue,
			double capacidadDesfogue,
			double elevacionDesfogue,
			double dimensionDesfogue,
			String comentariosDesfogue) 
	{
		if (validaCampos(
				nombreDesfogue,
				propositoDesfogue,
				tipoDesfogue,
				capacidadDesfogue,
				elevacionDesfogue,
				dimensionDesfogue,
				comentariosDesfogue))
		{
			if (bEsNuevo)
			{
				pojoDesfogues = new pojoVenting();
				pojoDesfogues.setVentingID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			} else {
				pojoDesfogues = desfogue.get(posicionElementoSeleccionado);
			}
			pojoDesfogues.setVentingID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			pojoDesfogues.setVentingName(nombreDesfogue);
			pojoDesfogues.setVentingPorpouse(propositoDesfogue);
			pojoDesfogues.setVentingType(tipoDesfogue);
			pojoDesfogues.setVentingCapacity(capacidadDesfogue);
			pojoDesfogues.setThresholdElevation(elevacionDesfogue);
			pojoDesfogues.setVentingDimension(dimensionDesfogue);
			pojoDesfogues.setComments(comentariosDesfogue);
	
			if (bEsNuevo)
				desfogue.add(pojoDesfogues);
			adaptadorListaDesfogue.notifyDataSetChanged();
		}
		else
		{
			DialogAlert alerta = new DialogAlert(myActivity, "Existen campos vacios", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
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
			botonGuardar.setEnabled(true);
			botonAgregarDesfogue.setEnabled(true);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Dique") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("nombreDesfogue", nombreDesfogue);
            datosActuales.putString("propositoDesfogue", propositoDesfogue);
            datosActuales.putString("tipoDesfogue", tipoDesfogue);
            datosActuales.putDouble("capacidadDesfogue", capacidadDesfogue);
            datosActuales.putDouble("elevacionDesfogue", elevacionDesfogue);
            datosActuales.putDouble("dimensionDesfogue", dimensionDesfogue);
            datosActuales.putString("comentariosDesfogue", comentariosDesfogue);

            dialogAgregarNuevoDesfogue = new DialogOtrosDesfogues(myActivity, this, Constants.DAM);
            dialogAgregarNuevoDesfogue.setArguments(datosActuales);
            dialogAgregarNuevoDesfogue.show(fm, "Modificar Otros Desfogues");

		} else if (item.getTitle() == "Eliminar Otros Desfogues") {
			//Toast.makeText(myActivity, "Eliminar Dique: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			desfogue.remove(posicionElementoSeleccionado);
			adaptadorListaDesfogue.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Otros Desfogues");
		menu.add("Eliminar Otros Desfogues");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean validaCampos(
			String nombreDesfogue,
			String propositoDesfogue,
			String tipoDesfogue,
			double capacidadDesfogue,
			double elevacionDesfogue,
			double dimensionDesfogue,
			String comentariosDesfogue)
	{
		if (nombreDesfogue.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		if (propositoDesfogue.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el propósito del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		if (tipoDesfogue.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el tipo del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		if (capacidadDesfogue==0) {
			Toast.makeText(myActivity, "Se requiere indicar la capacidad del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		if (elevacionDesfogue==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		if (dimensionDesfogue==0) {
			Toast.makeText(myActivity, "Se requiere indicar las dimensiones del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		if (comentariosDesfogue.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar los comentarios del desfogue", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
}
