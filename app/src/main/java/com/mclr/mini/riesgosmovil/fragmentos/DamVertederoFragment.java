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
import com.mclr.mini.riesgosmovil.modelos.pojoDamSpillway;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamVertederoFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogVertedero dialogAgregarNuevoVertedero;
	private ImageButton botonAgregarVertedero;
	private ListView listViewVertedero;
	private ArrayList<pojoDamSpillway> vertedero;
	private ListaAdapter adaptadorListaVertederos;
	pojoDamSpillway pojoVertedero;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;

	private String numeroVertederoSeleccionado;
	private double capacidadAcumuladaVertederoSeleccionado;
	private String tipoVertederoSeleccionado;
	private String operacionVertederoSeleccionado;
	private double longitudCrestaVertederoSeleccionado;
	private double elevacionCrestaVertederoSeleccionado;
	private double numeroCompuertasVertederoSeleccionado;
	private String tipoCompuertasVertederoSeleccionado;
	private double alturaCompuertasVertederoSeleccionado;
	private double anchoCompuertasVertederoSeleccionado;
	private String controlCompuertasVertederoSeleccionado;
	private String gastoPicoVertederoSeleccionado;
	private String elevacionLSCVertederoSeleccionado;
	private String estructuraDisipadoraVertederoSeleccionado;
	private boolean agujasVertederoSeleccionado;
	private double alturaAgujasVertederoSeleccionado;
	private String comentariosVertederoSeleccionado;

	private String selectedRemoveIDs="";

	public DamVertederoFragment()
	{
		vertedero = new ArrayList<pojoDamSpillway>();
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
		View rootView = inflater.inflate(R.layout.dams_vertedero, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotVertedero_dams);
		botonGuardar.setOnClickListener(new GuardarVertederoClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotVertedero_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarVertedero = (ImageButton) myActivity.findViewById(R.id.buttonAgregarVertedero_dams);
		botonAgregarVertedero.setOnClickListener(new AgregaVertederoClickListener(this));

//		pojoVertedero = new pojoDamSpillway();
//		pojoVertedero.setSpillNumber("8248924398");
//		pojoVertedero.setCapacity(892438);
//		pojoVertedero.setSpilltype("X");
//		pojoVertedero.setOperation("opsdop");
//		pojoVertedero.setCrestLength(Double.valueOf(9832902));
//		pojoVertedero.setCrestHeight(Double.valueOf(939845));
//		pojoVertedero.setGatesNumber(Double.valueOf(934093984));
//		pojoVertedero.setGateType("Y");
//		pojoVertedero.setGateHeight(Double.valueOf(98398));
//		pojoVertedero.setGateWidth(Double.valueOf(370834));
//		pojoVertedero.setGateControl("Mario");
//		pojoVertedero.setMaxFlow("9830");
//		pojoVertedero.setElevationLSC("98309");
//		pojoVertedero.setDissipativStr("94594");
//		pojoVertedero.setSpire(true);
//		pojoVertedero.setSpireHeight(Double.valueOf(5421342));
//		pojoVertedero.setComments("Saludos");
//		vertedero.add(pojoVertedero);
//
//		pojoVertedero = new pojoDamSpillway();
//		pojoVertedero.setSpillNumber("51224398");
//		pojoVertedero.setCapacity(892438);
//		pojoVertedero.setSpilltype("Y");
//		pojoVertedero.setOperation("ywe asdop");
//		pojoVertedero.setCrestLength(Double.valueOf(9832902));
//		pojoVertedero.setCrestHeight(Double.valueOf(939845));
//		pojoVertedero.setGatesNumber(Double.valueOf(934093984));
//		pojoVertedero.setGateType("M");
//		pojoVertedero.setGateHeight(Double.valueOf(98398));
//		pojoVertedero.setGateWidth(Double.valueOf(370834));
//		pojoVertedero.setGateControl("Mario Cesar");
//		pojoVertedero.setMaxFlow("9830");
//		pojoVertedero.setElevationLSC("98309");
//		pojoVertedero.setDissipativStr("94594");
//		pojoVertedero.setSpire(true);
//		pojoVertedero.setSpireHeight(Double.valueOf(5421342));
//		pojoVertedero.setComments("Hola!!");
//		vertedero.add(pojoVertedero);
//
//		pojoVertedero = new pojoDamSpillway();
//		pojoVertedero.setSpillNumber("1254924398");
//		pojoVertedero.setCapacity(892438);
//		pojoVertedero.setSpilltype("Z");
//		pojoVertedero.setOperation("astrcsacx");
//		pojoVertedero.setCrestLength(Double.valueOf(9832902));
//		pojoVertedero.setCrestHeight(Double.valueOf(939845));
//		pojoVertedero.setGatesNumber(Double.valueOf(934093984));
//		pojoVertedero.setGateType("S");
//		pojoVertedero.setGateHeight(Double.valueOf(98398));
//		pojoVertedero.setGateWidth(Double.valueOf(370834));
//		pojoVertedero.setGateControl("Mario Lima");
//		pojoVertedero.setMaxFlow("9830");
//		pojoVertedero.setElevationLSC("98309");
//		pojoVertedero.setDissipativStr("94594");
//		pojoVertedero.setSpire(false);
//		pojoVertedero.setSpireHeight(Double.valueOf(5421342));
//		pojoVertedero.setComments("Adios");
//		vertedero.add(pojoVertedero);
//
//		pojoVertedero = new pojoDamSpillway();
//		pojoVertedero.setSpillNumber("7634298");
//		pojoVertedero.setCapacity(892438);
//		pojoVertedero.setSpilltype("WW");
//		pojoVertedero.setOperation("astyavxa");
//		pojoVertedero.setCrestLength(Double.valueOf(9832902));
//		pojoVertedero.setCrestHeight(Double.valueOf(939845));
//		pojoVertedero.setGatesNumber(Double.valueOf(934093984));
//		pojoVertedero.setGateType("WW");
//		pojoVertedero.setGateHeight(Double.valueOf(98398));
//		pojoVertedero.setGateWidth(Double.valueOf(370834));
//		pojoVertedero.setGateControl("Mario Rodriguez");
//		pojoVertedero.setMaxFlow("9830");
//		pojoVertedero.setElevationLSC("98309");
//		pojoVertedero.setDissipativStr("94594");
//		pojoVertedero.setSpire(true);
//		pojoVertedero.setSpireHeight(Double.valueOf(5421342));
//		pojoVertedero.setComments("Hola");
//		vertedero.add(pojoVertedero);

		listViewVertedero = (ListView) myActivity.findViewById(R.id.listVertedero_dams);
		listViewVertedero.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaVertederos = new ListaAdapter(myActivity, R.layout.fila_vertedero, vertedero) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {
		        	
		            TextView texto_numero_vertedero = (TextView) view.findViewById(R.id.textNumeroFilaVertedero);
		            if (texto_numero_vertedero != null) 
		            	texto_numero_vertedero.setText(((pojoDamSpillway) entrada).getSpillNumber());

		            TextView texto_capacidad_acumulada_vertedero = (TextView) view.findViewById(R.id.textCapacidadAcumuladaFilaVertedero);
		            if (texto_capacidad_acumulada_vertedero != null) 
		            	texto_capacidad_acumulada_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getCapacity()));

		            TextView texto_tipo_vertedero = (TextView) view.findViewById(R.id.textTipoFilaVertedero);
		            if (texto_tipo_vertedero != null)
		            	if (((pojoDamSpillway) entrada).getSpilltype().toString().length() > 0)
		            		texto_tipo_vertedero.setText(((pojoDamSpillway) entrada).getSpilltypeDescription().toString());
		            	else
		            		texto_tipo_vertedero.setText("Ninguno");

		            TextView texto_operacion_vertedero = (TextView) view.findViewById(R.id.textOperacionFilaVertedero);
		            if (texto_operacion_vertedero != null) 
		            	texto_operacion_vertedero.setText(((pojoDamSpillway) entrada).getOperation());

		            TextView texto_longitud_cresta_vertedero = (TextView) view.findViewById(R.id.textLongitudCrestaFilaVertedero);
		            if (texto_longitud_cresta_vertedero != null) 
		            	texto_longitud_cresta_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getCrestLength()));

		            TextView texto_elevacion_cresta_vertedero = (TextView) view.findViewById(R.id.textElevacionCrestaFilaVertedero);
		            if (texto_elevacion_cresta_vertedero != null) 
		            	texto_elevacion_cresta_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getCrestHeight()));

		            TextView texto_numero_compuertas_vertedero = (TextView) view.findViewById(R.id.textNumeroCompuertasFilaVertedero);
		            if (texto_numero_compuertas_vertedero != null) 
		            	texto_numero_compuertas_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getGatesNumber()));

		            TextView texto_tipo_compuertas_vertedero = (TextView) view.findViewById(R.id.textTipoCompuertasFilaVertedero);
		            if (texto_tipo_compuertas_vertedero != null)
		            	if (((pojoDamSpillway) entrada).getGateType().toString().length() > 0)
		            		texto_tipo_compuertas_vertedero.setText(((pojoDamSpillway) entrada).getGateTypeDescription().toString());
		            	else
		            		texto_tipo_compuertas_vertedero.setText("Ninguno");

		            TextView texto_altura_compuertas_vertedero = (TextView) view.findViewById(R.id.textAlturaCompuertasFilaVertedero);
		            if (texto_altura_compuertas_vertedero != null)
		            	texto_altura_compuertas_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getGateHeight()));

		            TextView texto_ancho_compuertas_vertedero = (TextView) view.findViewById(R.id.textAnchoCompuertasFilaVertedero);
		            if (texto_ancho_compuertas_vertedero != null)
		            	texto_ancho_compuertas_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getGateWidth()));

		            TextView texto_control_compuertas_vertedero = (TextView) view.findViewById(R.id.textControlCompuertasFilaVertedero);
		            if (texto_control_compuertas_vertedero != null)
		            	texto_control_compuertas_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getGateControl()));

		            TextView texto_gasto_pico_vertedero = (TextView) view.findViewById(R.id.textGastoPicoFilaVertedero);
		            if (texto_gasto_pico_vertedero != null)
		            	texto_gasto_pico_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getMaxFlow()));

		            TextView texto_elevacion_LSC_vertedero = (TextView) view.findViewById(R.id.textElevacionLSCFilaVertedero);
		            if (texto_elevacion_LSC_vertedero != null)
		            	texto_elevacion_LSC_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getElevationLSC()));

		            TextView texto_estructura_disipadora_vertedero = (TextView) view.findViewById(R.id.textEstructuraDisipadoraFilaVertedero);
		            if (texto_estructura_disipadora_vertedero != null)
		            	texto_estructura_disipadora_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getDissipativStr()));

		            ImageView imagen_principal = (ImageView) view.findViewById(R.id.imageAgujasFilaVertedero);
		            if (imagen_principal != null)
		            	if (((pojoDamSpillway) entrada).isSpire())
		            		imagen_principal.setImageResource(R.drawable.ok);

		            TextView texto_altura_agujas_vertedero = (TextView) view.findViewById(R.id.textAlturaAgujasFilaVertedero);
		            if (texto_altura_agujas_vertedero != null)
		            	texto_altura_agujas_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getSpireHeight()));

		            TextView texto_comentarios_vertedero = (TextView) view.findViewById(R.id.textComentariosFilaVertedero);
		            if (texto_comentarios_vertedero != null)
		            	texto_comentarios_vertedero.setText(String.valueOf(((pojoDamSpillway) entrada).getComments()));

		        }
			}
		};
        listViewVertedero.setAdapter(adaptadorListaVertederos);
        registerForContextMenu(listViewVertedero);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoDamSpillway pVertedero : pojo.getDamSpillWays())
			{
				vertedero.add(pVertedero);
			}
			adaptadorListaVertederos.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaVertederoClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaVertederoClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevoVertedero = new DialogVertedero(myActivity, fragment, Constants.DAMS);
            dialogAgregarNuevoVertedero.setArguments(datosActuales);

            dialogAgregarNuevoVertedero.show(fm, "Agregar información del nuevo Vertedero");			
		}
	}

	class GuardarVertederoClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoDamSpillway pVertedero : vertedero)
			{
				process.processVertederoSave(propertyID, pVertedero);
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

			numeroVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getSpillNumber();
			capacidadAcumuladaVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getCapacity();
			tipoVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getSpilltype();
			operacionVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getOperation();
			longitudCrestaVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getCrestLength();
			elevacionCrestaVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getCrestHeight();
			numeroCompuertasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getGatesNumber();
			tipoCompuertasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getGateType();
			alturaCompuertasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getGateHeight();
			anchoCompuertasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getGateWidth();
			controlCompuertasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getGateControl();
			gastoPicoVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getMaxFlow();
			elevacionLSCVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getElevationLSC();
			estructuraDisipadoraVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getDissipativStr();
			agujasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).isSpire();
			alturaAgujasVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getSpireHeight();
			comentariosVertederoSeleccionado = ((pojoDamSpillway)parent.getItemAtPosition(position)).getComments();

			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo,
			String numeroVertederoSeleccionado,
			double capacidadAcumuladaVertederoSeleccionado,
			String tipoVertederoSeleccionado,
			String descTipoVertederoSeleccionado,
			String operacionVertederoSeleccionado,
			double longitudCrestaVertederoSeleccionado,
			double elevacionCrestaVertederoSeleccionado,
			double numeroCompuertasVertederoSeleccionado,
			String tipoCompuertasVertederoSeleccionado,
			String descTipoCompuertasVertederoSeleccionado,
			double alturaCompuertasVertederoSeleccionado,
			double anchoCompuertasVertederoSeleccionado,
			String controlCompuertasVertederoSeleccionado,
			String gastoPicoVertederoSeleccionado,
			String elevacionLSCVertederoSeleccionado,
			String estructuraDisipadoraVertederoSeleccionado,
			boolean agujasVertederoSeleccionado,
			double alturaAgujasVertederoSeleccionado,
			String comentariosVertederoSeleccionado)
	{
		if (validaCampos(
				numeroVertederoSeleccionado,
				capacidadAcumuladaVertederoSeleccionado,
				tipoVertederoSeleccionado,
				descTipoVertederoSeleccionado,
				operacionVertederoSeleccionado,
				longitudCrestaVertederoSeleccionado,
				elevacionCrestaVertederoSeleccionado,
				numeroCompuertasVertederoSeleccionado,
				tipoCompuertasVertederoSeleccionado,
				descTipoCompuertasVertederoSeleccionado,
				alturaCompuertasVertederoSeleccionado,
				anchoCompuertasVertederoSeleccionado,
				controlCompuertasVertederoSeleccionado,
				gastoPicoVertederoSeleccionado,
				elevacionLSCVertederoSeleccionado,
				estructuraDisipadoraVertederoSeleccionado,
				agujasVertederoSeleccionado,
				alturaAgujasVertederoSeleccionado,
				comentariosVertederoSeleccionado
				))
		{
			if (bEsNuevo)
			{
				pojoVertedero = new pojoDamSpillway();
				pojoVertedero.setSpillWayID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			} else {
				pojoVertedero = vertedero.get(posicionElementoSeleccionado);
			}
			pojoVertedero.setSpillNumber(numeroVertederoSeleccionado);
			pojoVertedero.setCapacity(capacidadAcumuladaVertederoSeleccionado);
			pojoVertedero.setSpilltype(tipoVertederoSeleccionado);
			pojoVertedero.setSpilltypeDescription(descTipoVertederoSeleccionado);
			pojoVertedero.setOperation(operacionVertederoSeleccionado);
			pojoVertedero.setCrestLength(longitudCrestaVertederoSeleccionado);
			pojoVertedero.setCrestHeight(elevacionCrestaVertederoSeleccionado);
			pojoVertedero.setGatesNumber(numeroCompuertasVertederoSeleccionado);
			pojoVertedero.setGateType(tipoCompuertasVertederoSeleccionado);
			pojoVertedero.setGateTypeDescription(descTipoCompuertasVertederoSeleccionado);
			pojoVertedero.setGateHeight(alturaCompuertasVertederoSeleccionado);
			pojoVertedero.setGateWidth(anchoCompuertasVertederoSeleccionado);
			pojoVertedero.setGateControl(controlCompuertasVertederoSeleccionado);
			pojoVertedero.setMaxFlow(gastoPicoVertederoSeleccionado);
			pojoVertedero.setElevationLSC(elevacionLSCVertederoSeleccionado);
			pojoVertedero.setDissipativStr(estructuraDisipadoraVertederoSeleccionado);
			pojoVertedero.setSpire(agujasVertederoSeleccionado);
			pojoVertedero.setSpireHeight(alturaAgujasVertederoSeleccionado);
			pojoVertedero.setComments(comentariosVertederoSeleccionado);
			if (bEsNuevo)
				vertedero.add(pojoVertedero);
			adaptadorListaVertederos.notifyDataSetChanged();
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
			botonAgregarVertedero.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Vertedero") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("numeroVertederoSeleccionado", numeroVertederoSeleccionado);
            datosActuales.putDouble("capacidadAcumuladaVertederoSeleccionado", capacidadAcumuladaVertederoSeleccionado);
            datosActuales.putString("tipoVertederoSeleccionado", tipoVertederoSeleccionado);            
            datosActuales.putString("operacionVertederoSeleccionado", operacionVertederoSeleccionado);
            datosActuales.putDouble("longitudCrestaVertederoSeleccionado", longitudCrestaVertederoSeleccionado);
            datosActuales.putDouble("elevacionCrestaVertederoSeleccionado", elevacionCrestaVertederoSeleccionado);
            datosActuales.putDouble("numeroCompuertasVertederoSeleccionado", numeroCompuertasVertederoSeleccionado);
            datosActuales.putString("tipoCompuertasVertederoSeleccionado", tipoCompuertasVertederoSeleccionado);
            datosActuales.putDouble("alturaCompuertasVertederoSeleccionado", alturaCompuertasVertederoSeleccionado);
            datosActuales.putDouble("anchoCompuertasVertederoSeleccionado", anchoCompuertasVertederoSeleccionado);
            datosActuales.putString("controlCompuertasVertederoSeleccionado", controlCompuertasVertederoSeleccionado);
            datosActuales.putString("gastoPicoVertederoSeleccionado", gastoPicoVertederoSeleccionado);
            datosActuales.putString("elevacionLSCVertederoSeleccionado", elevacionLSCVertederoSeleccionado);
            datosActuales.putString("estructuraDisipadoraVertederoSeleccionado", estructuraDisipadoraVertederoSeleccionado);
            datosActuales.putBoolean("agujasVertederoSeleccionado", agujasVertederoSeleccionado);
            datosActuales.putDouble("alturaAgujasVertederoSeleccionado", alturaAgujasVertederoSeleccionado);
            datosActuales.putString("comentariosVertederoSeleccionado", comentariosVertederoSeleccionado);

            dialogAgregarNuevoVertedero = new DialogVertedero(myActivity, this, Constants.DAMS);
            dialogAgregarNuevoVertedero.setArguments(datosActuales);
            dialogAgregarNuevoVertedero.show(fm, "Modificar Vertedero");	

		} else if (item.getTitle() == "Eliminar Vertedero") {
			//Toast.makeText(myActivity, "Eliminar Puente: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			vertedero.remove(posicionElementoSeleccionado);
			adaptadorListaVertederos.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Vertedero");
		menu.add("Eliminar Vertedero");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean validaCampos(
			String numeroVertederoSeleccionado,
			double capacidadAcumuladaVertederoSeleccionado,
			String tipoVertederoSeleccionado,
			String descTipoVertederoSeleccionado,
			String operacionVertederoSeleccionado,
			double longitudCrestaVertederoSeleccionado,
			double elevacionCrestaVertederoSeleccionado,
			double numeroCompuertasVertederoSeleccionado,
			String tipoCompuertasVertederoSeleccionado,
			String descTipoCompuertasVertederoSeleccionado,
			double alturaCompuertasVertederoSeleccionado,
			double anchoCompuertasVertederoSeleccionado,
			String controlCompuertasVertederoSeleccionado,
			String gastoPicoVertederoSeleccionado,
			String elevacionLSCVertederoSeleccionado,
			String estructuraDisipadoraVertederoSeleccionado,
			boolean agujasVertederoSeleccionado,
			double alturaAgujasVertederoSeleccionado,
			String comentariosVertederoSeleccionado)
	{
		if (numeroVertederoSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número del vertedor", Toast.LENGTH_LONG).show();
			return false;
		}
		if (capacidadAcumuladaVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la capacidad acumulada del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipoVertederoSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo vertedor del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if (operacionVertederoSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la operación del vertedor", Toast.LENGTH_LONG).show();
			return false;
		}
		if (longitudCrestaVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la longitud acumulada del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if (elevacionCrestaVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación acumulada del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if (numeroCompuertasVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el numero de compuertas del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipoCompuertasVertederoSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar el tipo de compuertas del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if (alturaCompuertasVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de compuertas del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if (anchoCompuertasVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar el ancho de compuertas del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		if (alturaAgujasVertederoSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de agujas del vertedero", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
}
