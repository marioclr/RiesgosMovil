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
import com.mclr.mini.riesgosmovil.modelos.pojoDamHeadworks;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamObraTomaFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogObraToma dialogAgregarNuevaObraToma;
	private ImageButton botonAgregarObraToma;
	private ListView listViewObraToma;
	private ArrayList<pojoDamHeadworks> obrastoma;
	private ListaAdapter adaptadorListaObraToma;
	pojoDamHeadworks pojoObraToma;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	private String nombreObraTomaSeleccionado;
	private String tipoObraTomaSeleccionado;
	private double capacidadObraTomaSeleccionado;
	private double elevacionObraTomaSeleccionado;
	private int numerocompObraTomaSeleccionado;
	private String tipocompObraTomaSeleccionado;
	private double anchocompObraTomaSeleccionado;
	private double alturacompObraTomaSeleccionado;
	private int numerovalvulasObraTomaSeleccionado;
	private String tipovalObraTomaSeleccionado;
	private int numerocondObraTomaSeleccionado;
	private String tipocondObraTomaSeleccionado;
	private double dimensionObraTomaSeleccionado;
	private boolean rejillasObraToma;
	private String comentariosObraToma;

	private String selectedRemoveIDs="";

	public DamObraTomaFragment()
	{
		obrastoma = new ArrayList<pojoDamHeadworks>();
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
		View rootView = inflater.inflate(R.layout.dams_obras, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotObras_dams);
		botonGuardar.setOnClickListener(new GuardarObraTomaClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotObras_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarObraToma = (ImageButton) myActivity.findViewById(R.id.buttonAgregarObras_dams);
		botonAgregarObraToma.setOnClickListener(new AgregaObraTomaClickListener(this));

//		pojoObraToma = new pojoDamHeadworks();
//		pojoObraToma.setHeadworkID("1");
//		pojoObraToma.setHeadworkNumber("823189712");
//		pojoObraToma.setHeadworkType("Sec1");
//		pojoObraToma.setCapacity(3);
//		pojoObraToma.setElevation(4);
//		pojoObraToma.setGatesNumber(897);
//		pojoObraToma.setGatesType("Agua");
//		pojoObraToma.setGatesWidth(5);
//		pojoObraToma.setGatesHeight(6);
//		pojoObraToma.setValvesNumber(2);
//		pojoObraToma.setDuctNumber(78);
//		pojoObraToma.setDuctType("Ducto");
//		pojoObraToma.setDuctDimension(3);
//		pojoObraToma.setGridExistence(true);
//		pojoObraToma.setComments("Prueba");
//		obrastoma.add(pojoObraToma);
//
//		pojoObraToma = new pojoDamHeadworks();
//		pojoObraToma.setHeadworkID("2");
//		pojoObraToma.setHeadworkNumber("238931098");
//		pojoObraToma.setHeadworkType("Sec2");
//		pojoObraToma.setCapacity(3);
//		pojoObraToma.setElevation(4);
//		pojoObraToma.setGatesNumber(43);
//		pojoObraToma.setGatesType("Agua");
//		pojoObraToma.setGatesWidth(5);
//		pojoObraToma.setGatesHeight(6);
//		pojoObraToma.setValvesNumber(2);
//		pojoObraToma.setDuctNumber(78);
//		pojoObraToma.setDuctType("Ducto");
//		pojoObraToma.setDuctDimension(3);
//		pojoObraToma.setGridExistence(false);
//		pojoObraToma.setComments("Prueba");
//		obrastoma.add(pojoObraToma);
//
//		pojoObraToma = new pojoDamHeadworks();
//		pojoObraToma.setHeadworkID("3");
//		pojoObraToma.setHeadworkNumber("2738931");
//		pojoObraToma.setHeadworkType("Sec3");
//		pojoObraToma.setCapacity(3);
//		pojoObraToma.setElevation(4);
//		pojoObraToma.setGatesNumber(112);
//		pojoObraToma.setGatesType("Agua");
//		pojoObraToma.setGatesWidth(5);
//		pojoObraToma.setGatesHeight(6);
//		pojoObraToma.setValvesNumber(2);
//		pojoObraToma.setDuctNumber(78);
//		pojoObraToma.setDuctType("Ducto");
//		pojoObraToma.setDuctDimension(3);
//		pojoObraToma.setGridExistence(true);
//		pojoObraToma.setComments("Prueba");
//		obrastoma.add(pojoObraToma);

		listViewObraToma = (ListView) myActivity.findViewById(R.id.listObras_dams);
		listViewObraToma.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaObraToma = new ListaAdapter(myActivity, R.layout.fila_obras, obrastoma) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_nombre_obra_toma = (TextView) view.findViewById(R.id.textNombreFilaObras);
		            if (texto_nombre_obra_toma != null) 
		            	texto_nombre_obra_toma.setText(((pojoDamHeadworks) entrada).getHeadworkNumber());
		            
		            TextView texto_tipo_obra_toma = (TextView) view.findViewById(R.id.textTipoFilaObras);
		            if (texto_tipo_obra_toma != null)
		            	if (((pojoDamHeadworks) entrada).getHeadworkType().toString().length() > 0)
		            		texto_tipo_obra_toma.setText(((pojoDamHeadworks) entrada).getHeadworkTypeDescription().toString());
		            	else
		            		texto_tipo_obra_toma.setText("Ninguno");
		            
		            TextView texto_capacidad_obra_toma = (TextView) view.findViewById(R.id.textCapacidadFilaObras);
		            if (texto_capacidad_obra_toma != null)
		            	texto_capacidad_obra_toma.setText(Double.toString(((pojoDamHeadworks) entrada).getCapacity()));

		            TextView texto_elevacion_obra_toma = (TextView) view.findViewById(R.id.textElevacionFilaObras);
		            if (texto_elevacion_obra_toma != null)
		            	texto_elevacion_obra_toma.setText(Double.toString(((pojoDamHeadworks) entrada).getElevation()));
		            
		            TextView texto_numero_compuerta_obra_toma = (TextView) view.findViewById(R.id.textNumeroFilaObras);
		            if (texto_numero_compuerta_obra_toma != null)
		            	texto_numero_compuerta_obra_toma.setText(Integer.toString(((pojoDamHeadworks) entrada).getGatesNumber()));

		            TextView texto_tipo_compuerta_obra_toma = (TextView) view.findViewById(R.id.textTipoComFilaObras);
		            if (texto_tipo_compuerta_obra_toma != null)
		            	if (((pojoDamHeadworks) entrada).getGatesType().toString().length() > 0)
		            		texto_tipo_compuerta_obra_toma.setText(((pojoDamHeadworks) entrada).getGatesTypeDescription().toString());
		            	else
		            		texto_tipo_compuerta_obra_toma.setText("Ninguno");

		            TextView texto_ancho_compuerta_obra_toma = (TextView) view.findViewById(R.id.textAnchoComFilaObras);
		            if (texto_ancho_compuerta_obra_toma != null)
		            	texto_ancho_compuerta_obra_toma.setText(Double.toString(((pojoDamHeadworks) entrada).getGatesWidth()));

		            TextView texto_altura_compuerta_obra_toma = (TextView) view.findViewById(R.id.textAltoComFilaObras);
		            if (texto_altura_compuerta_obra_toma != null)
		            	texto_altura_compuerta_obra_toma.setText(Double.toString(((pojoDamHeadworks) entrada).getGatesHeight()));
		            
		            TextView texto_numero_valvulas_obra_toma = (TextView) view.findViewById(R.id.textNumValFilaObras);
		            if (texto_numero_valvulas_obra_toma != null)
		            	texto_numero_valvulas_obra_toma.setText(Integer.toString(((pojoDamHeadworks) entrada).getValvesNumber()));

		            TextView texto_tipo_valvulas_obra_toma = (TextView) view.findViewById(R.id.textTipoValFilaObras);
		            if (texto_tipo_valvulas_obra_toma != null)
		            	if (((pojoDamHeadworks) entrada).getValveType().toString().length() > 0)
		            		texto_tipo_valvulas_obra_toma.setText(((pojoDamHeadworks) entrada).getValveTypeDescription().toString());
		            	else
		            		texto_tipo_compuerta_obra_toma.setText("Ninguno");
		            
		            TextView texto_numero_conductos_obra_toma = (TextView) view.findViewById(R.id.textNumConFilaObras);
		            if (texto_numero_conductos_obra_toma != null)
		            	texto_numero_conductos_obra_toma.setText(Integer.toString(((pojoDamHeadworks) entrada).getDuctNumber()));

		            TextView texto_tipo_conductos_obra_toma = (TextView) view.findViewById(R.id.textTipoCondFilaObras);
		            if (texto_tipo_conductos_obra_toma != null)
		            	if (((pojoDamHeadworks) entrada).getDuctType().toString().length() > 0)
		            		texto_tipo_conductos_obra_toma.setText(((pojoDamHeadworks) entrada).getDuctTypeDescription().toString());
		            	else
		            		texto_tipo_compuerta_obra_toma.setText("Ninguno");
		            
		            TextView texto_dimensiones_obra_toma = (TextView) view.findViewById(R.id.textDimCondFilaObras);
		            if (texto_dimensiones_obra_toma != null)
		            	texto_dimensiones_obra_toma.setText(Double.toString(((pojoDamHeadworks) entrada).getDuctDimension()));

		            ImageView imagen_principal = (ImageView) view.findViewById(R.id.imageCuentaRejillasFilaObras);
		            if (imagen_principal != null)
		            	if (((pojoDamHeadworks) entrada).isGridExistence())
		            		imagen_principal.setImageResource(R.drawable.ok);

		            TextView texto_comentarios_obra_toma = (TextView) view.findViewById(R.id.textComentariosFilaObras);
		            if (texto_comentarios_obra_toma != null) 
		            	texto_comentarios_obra_toma.setText(((pojoDamHeadworks) entrada).getComments());
		            
		        }
			}
		};

        listViewObraToma.setAdapter(adaptadorListaObraToma);
        registerForContextMenu(listViewObraToma);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoDamHeadworks pPuente : pojo.getDamHeadworks())
			{
				obrastoma.add(pPuente);
			}
			adaptadorListaObraToma.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaObraTomaClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaObraTomaClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevaObraToma = new DialogObraToma(myActivity, fragment, Constants.DAM);
            dialogAgregarNuevaObraToma.setArguments(datosActuales);

            dialogAgregarNuevaObraToma.show(fm, "Agregar información de la nueva Obra de Toma");			
		}
	}

	class GuardarObraTomaClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoDamHeadworks pObraToma : obrastoma)
			{
				process.processObraTomaSave(propertyID, pObraToma);
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
			
			nombreObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getHeadworkNumber();
			tipoObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getHeadworkType();
			capacidadObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getCapacity();
			elevacionObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getElevation();
			numerocompObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getGatesNumber();
			tipocompObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getGatesType();
			anchocompObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getGatesWidth();
			alturacompObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getGatesHeight();
			numerovalvulasObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getValvesNumber();
			tipovalObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getValveType();
			numerocondObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getDuctNumber();
			tipocondObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getDuctType();
			dimensionObraTomaSeleccionado = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getDuctDimension();
			rejillasObraToma = ((pojoDamHeadworks)parent.getItemAtPosition(position)).isGridExistence();
			comentariosObraToma = ((pojoDamHeadworks)parent.getItemAtPosition(position)).getComments();

			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo,
			String nombreObraTomaSeleccionado,
			String tipoObraTomaSeleccionado,
			String descTipoObraTomaSeleccionado,
			double capacidadObraTomaSeleccionado,
			double elevacionObraTomaSeleccionado,
			int numerocompObraTomaSeleccionado,
			String tipocompObraTomaSeleccionado,
			String descTipocompObraTomaSeleccionado,
			double anchocompObraTomaSeleccionado,
			double alturacompObraTomaSeleccionado,
			int numerovalvulasObraTomaSeleccionado,
			String tipovalObraTomaSeleccionado,
			String descTipovalObraTomaSeleccionado,
			int numerocondObraTomaSeleccionado,
			String tipocondObraTomaSeleccionado,
			String descTipocondObraTomaSeleccionado,
			double dimensionObraTomaSeleccionado,
			boolean rejillasObraToma,
			String comentariosObraToma) 
	{
		if (validaCampos(
				nombreObraTomaSeleccionado,
				tipoObraTomaSeleccionado,
				descTipoObraTomaSeleccionado,
				capacidadObraTomaSeleccionado,
				elevacionObraTomaSeleccionado,
				numerocompObraTomaSeleccionado,
				tipocompObraTomaSeleccionado,
				descTipocompObraTomaSeleccionado,
				anchocompObraTomaSeleccionado,
				alturacompObraTomaSeleccionado,
				numerovalvulasObraTomaSeleccionado,
				tipovalObraTomaSeleccionado,
				descTipovalObraTomaSeleccionado,
				numerocondObraTomaSeleccionado,
				tipocondObraTomaSeleccionado,
				descTipocondObraTomaSeleccionado,
				dimensionObraTomaSeleccionado,
				rejillasObraToma,
				comentariosObraToma
				))
		{
			if (bEsNuevo)
			{
				pojoObraToma = new pojoDamHeadworks();
				pojoObraToma.setHeadworkID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			} else {
				pojoObraToma = obrastoma.get(posicionElementoSeleccionado);
			}
			pojoObraToma.setHeadworkNumber(nombreObraTomaSeleccionado);
			pojoObraToma.setHeadworkType(tipoObraTomaSeleccionado);
			pojoObraToma.setHeadworkTypeDescription(descTipoObraTomaSeleccionado);
			pojoObraToma.setCapacity(capacidadObraTomaSeleccionado);
			pojoObraToma.setElevation(elevacionObraTomaSeleccionado);
			pojoObraToma.setGatesNumber(numerocompObraTomaSeleccionado);
			pojoObraToma.setGatesType(tipocompObraTomaSeleccionado);
			pojoObraToma.setGatesTypeDescription(descTipocompObraTomaSeleccionado);
			pojoObraToma.setGatesWidth(anchocompObraTomaSeleccionado);
			pojoObraToma.setGatesHeight(alturacompObraTomaSeleccionado);
			pojoObraToma.setValvesNumber(numerovalvulasObraTomaSeleccionado);
			pojoObraToma.setValveType(tipovalObraTomaSeleccionado);
			pojoObraToma.setValveTypeDescription(descTipovalObraTomaSeleccionado);
			pojoObraToma.setDuctNumber(numerocondObraTomaSeleccionado);
			pojoObraToma.setDuctType(tipocondObraTomaSeleccionado);
			pojoObraToma.setDuctTypeDescription(descTipocondObraTomaSeleccionado);
			pojoObraToma.setDuctDimension(dimensionObraTomaSeleccionado);
			pojoObraToma.setGridExistence(rejillasObraToma);
			pojoObraToma.setComments(comentariosObraToma);
			if (bEsNuevo)
				obrastoma.add(pojoObraToma);
			adaptadorListaObraToma.notifyDataSetChanged();
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
			botonGuardar.setEnabled(false);
			botonAgregarObraToma.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Obra de Toma") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("nombreObraTomaSeleccionado", nombreObraTomaSeleccionado);
            datosActuales.putString("tipoObraTomaSeleccionado", tipoObraTomaSeleccionado);
            datosActuales.putDouble("capacidadObraTomaSeleccionado", capacidadObraTomaSeleccionado);
            datosActuales.putDouble("elevacionObraTomaSeleccionado", elevacionObraTomaSeleccionado);
            datosActuales.putInt("numerocompObraTomaSeleccionado", numerocompObraTomaSeleccionado);
            datosActuales.putString("tipocompObraTomaSeleccionado", tipocompObraTomaSeleccionado);
            datosActuales.putDouble("anchocompObraTomaSeleccionado", anchocompObraTomaSeleccionado);
            datosActuales.putDouble("alturacompObraTomaSeleccionado", alturacompObraTomaSeleccionado);
            datosActuales.putInt("numerovalvulasObraTomaSeleccionado", numerovalvulasObraTomaSeleccionado);
            datosActuales.putString("tipovalObraTomaSeleccionado", tipovalObraTomaSeleccionado);
            datosActuales.putInt("numerocondObraTomaSeleccionado", numerocondObraTomaSeleccionado);
            datosActuales.putString("tipocondObraTomaSeleccionado", tipocondObraTomaSeleccionado);
            datosActuales.putDouble("dimensionObraTomaSeleccionado", dimensionObraTomaSeleccionado);
            datosActuales.putBoolean("rejillasObraToma", rejillasObraToma);
            datosActuales.putString("comentariosObraToma", comentariosObraToma);

            dialogAgregarNuevaObraToma = new DialogObraToma(myActivity, this, Constants.DAM);
            dialogAgregarNuevaObraToma.setArguments(datosActuales);
            dialogAgregarNuevaObraToma.show(fm, "Modificar Obras");	

		} else if (item.getTitle() == "Eliminar Obra de Toma") {
			//Toast.makeText(myActivity, "Eliminar Galería: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			obrastoma.remove(posicionElementoSeleccionado);
			adaptadorListaObraToma.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Obra de Toma");
		menu.add("Eliminar Obra de Toma");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean validaCampos(
			String nombreObraTomaSeleccionado,
			String tipoObraTomaSeleccionado,
			String descTipoObraTomaSeleccionado,
			double capacidadObraTomaSeleccionado,
			double elevacionObraTomaSeleccionado,
			int numerocompObraTomaSeleccionado,
			String tipocompObraTomaSeleccionado,
			String descTipocompObraTomaSeleccionado,
			double anchocompObraTomaSeleccionado,
			double alturacompObraTomaSeleccionado,
			int numerovalvulasObraTomaSeleccionado,
			String tipovalObraTomaSeleccionado,
			String descTipovalObraTomaSeleccionado,
			int numerocondObraTomaSeleccionado,
			String tipocondObraTomaSeleccionado,
			String descTipocondObraTomaSeleccionado,
			double dimensionObraTomaSeleccionado,
			boolean rejillasObraToma,
			String comentariosObraToma)
	{
		if (nombreObraTomaSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el nombre de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipoObraTomaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (capacidadObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la capacidad de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (elevacionObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevacion de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (numerocompObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de compuertas de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipocompObraTomaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de compuertas de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (anchocompObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el ancho de compuertas de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (alturacompObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el alto de compuertas de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (numerovalvulasObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de válvulas de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipovalObraTomaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de válvulas de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (numerocondObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de conductos de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipocondObraTomaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de conductos de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (dimensionObraTomaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la dimensión de conductos de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		if (comentariosObraToma.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar los comentarios de la obra de toma", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
}
