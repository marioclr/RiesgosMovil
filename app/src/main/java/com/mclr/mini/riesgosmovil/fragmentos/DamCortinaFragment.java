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
import com.mclr.mini.riesgosmovil.modelos.pojoDamCurtain;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamCortinaFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogCortina dialogAgregarNuevaCortina;
	private ImageButton botonAgregarCortina;
	private ListView listViewCortina;
	private ArrayList<pojoDamCurtain> cortinas;
	private ListaAdapter adaptadorListaCortinas;
	pojoDamCurtain pojoCortina;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;

	private String identificadorCortinaSeleccionado;
	private String tamañoCortinaSeleccionado;
	private String comportamientoCortinaSeleccionado;
	private String tipoCortinaSeleccionado;
	private String materialCortinaSeleccionado;
	private double alturaMaximaCortinaSeleccionado;
	private double elevacionCoronaCortinaSeleccionado;
	private double longitudCortinaSeleccionado;
	private double anchoCortinaSeleccionado;
	private String taludesAguasArribaCortinaSeleccionado;
	private String taludesAguasAbajoCortinaSeleccionado;
	private double alturaParapetoCortinaSeleccionado;
	private double volumenCuerpoCortinaSeleccionado;
	private double alturaSobreCalceCortinaSeleccionado;
	private String otrasCaracteristicasCortinaSeleccionado;

	private String selectedRemoveIDs="";

	public DamCortinaFragment()
	{
		cortinas = new ArrayList<pojoDamCurtain>();
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
		View rootView = inflater.inflate(R.layout.dams_curtains, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotCortinas_dams);
		botonGuardar.setOnClickListener(new GuardarCortinasClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotCortinas_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarCortina = (ImageButton) myActivity.findViewById(R.id.buttonAgregarCortinas_dams);
		botonAgregarCortina.setOnClickListener(new AgregaCortinaClickListener(this));

//		pojoCortina = new pojoDamCurtain();
//		pojoCortina.setCurtainID("1");
//		pojoCortina.setCurtainName("Cortina 1");
//		pojoCortina.setSize("X");
//		pojoCortina.setSizeDescription("XXX");
//		pojoCortina.setBehaivor("X");
//		pojoCortina.setBehaivorDescription("MMM");
//		pojoCortina.setType("X");
//		pojoCortina.setTypeDescription("NNN");
//		pojoCortina.setMaterial("X");
//		pojoCortina.setMaterialDescription("NNN");
//		pojoCortina.setMaxHeight(Double.valueOf("49804980"));
//		pojoCortina.setCrownElevation(6429409);
//		pojoCortina.setLength(981239034);
//		pojoCortina.setWidth(9243098);
//		pojoCortina.setOverwaterSlope("9956842");
//		pojoCortina.setUnderwaterSlope("5689589");
//		pojoCortina.setShimHeight(9984);
//		pojoCortina.setVolumeBody(88745);
//		pojoCortina.setBreastwallHeight(39892);
//		pojoCortina.setFeatures("Saludos");
//		cortinas.add(pojoCortina);
//
//		pojoCortina = new pojoDamCurtain();
//		pojoCortina.setCurtainID("2");
//		pojoCortina.setCurtainName("Cortina 2");
//		pojoCortina.setSize("Y");
//		pojoCortina.setSizeDescription("YYY");
//		pojoCortina.setBehaivor("ZZZ");
//		pojoCortina.setBehaivorDescription("ZZZ");
//		pojoCortina.setType("V");
//		pojoCortina.setTypeDescription("VVV");
//		pojoCortina.setMaterial("W");
//		pojoCortina.setMaterialDescription("WWW");
//		pojoCortina.setMaxHeight(Double.valueOf("49804980"));
//		pojoCortina.setCrownElevation(6429409);
//		pojoCortina.setLength(981239034);
//		pojoCortina.setWidth(9243098);
//		pojoCortina.setOverwaterSlope("9956842");
//		pojoCortina.setUnderwaterSlope("5689589");
//		pojoCortina.setShimHeight(9984);
//		pojoCortina.setVolumeBody(88745);
//		pojoCortina.setBreastwallHeight(39892);
//		pojoCortina.setFeatures("Saludos a todos mis compañeros de partido.");
//		cortinas.add(pojoCortina);
//
//		pojoCortina = new pojoDamCurtain();
//		pojoCortina.setCurtainID("3");
//		pojoCortina.setCurtainName("Cortina 3");
//		pojoCortina.setSize("X");
//		pojoCortina.setSizeDescription("XXX");
//		pojoCortina.setBehaivor("X");
//		pojoCortina.setBehaivorDescription("MMM");
//		pojoCortina.setType("X");
//		pojoCortina.setTypeDescription("NNN");
//		pojoCortina.setMaterial("X");
//		pojoCortina.setMaterialDescription("NNN");
//		pojoCortina.setMaxHeight(Double.valueOf("49804980"));
//		pojoCortina.setCrownElevation(6429409);
//		pojoCortina.setLength(981239034);
//		pojoCortina.setWidth(9243098);
//		pojoCortina.setOverwaterSlope("9956842");
//		pojoCortina.setUnderwaterSlope("5689589");
//		pojoCortina.setShimHeight(9984);
//		pojoCortina.setVolumeBody(88745);
//		pojoCortina.setBreastwallHeight(39892);
//		pojoCortina.setFeatures("Saludos, este es un día especial para mi y para todos.");
//		cortinas.add(pojoCortina);
//
//		pojoCortina = new pojoDamCurtain();
//		pojoCortina.setCurtainID("4");
//		pojoCortina.setCurtainName("Cortina 4");
//		pojoCortina.setSize("Y");
//		pojoCortina.setSizeDescription("YYY");
//		pojoCortina.setBehaivor("ZZZ");
//		pojoCortina.setBehaivorDescription("ZZZ");
//		pojoCortina.setType("V");
//		pojoCortina.setTypeDescription("VVV");
//		pojoCortina.setMaterial("W");
//		pojoCortina.setMaterialDescription("WWW");
//		pojoCortina.setMaxHeight(Double.valueOf("49804980"));
//		pojoCortina.setCrownElevation(6429409);
//		pojoCortina.setLength(981239034);
//		pojoCortina.setWidth(9243098);
//		pojoCortina.setOverwaterSlope("9956842");
//		pojoCortina.setUnderwaterSlope("5689589");
//		pojoCortina.setShimHeight(9984);
//		pojoCortina.setVolumeBody(88745);
//		pojoCortina.setBreastwallHeight(39892);
//		pojoCortina.setFeatures("Saludos a todos mis compañeros de partido.");
//		cortinas.add(pojoCortina);

		listViewCortina = (ListView) myActivity.findViewById(R.id.listCortinas_dams);
		listViewCortina.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaCortinas = new ListaAdapter(myActivity, R.layout.fila_cortina, cortinas) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_identificador_cortina = (TextView) view.findViewById(R.id.textIdentificadorFilaCortina);
		            if (texto_identificador_cortina != null) 
		            	texto_identificador_cortina.setText(((pojoDamCurtain) entrada).getCurtainName());

		            TextView texto_tamaño_cortina = (TextView) view.findViewById(R.id.textTamañoFilaCortina);
		            if (texto_tamaño_cortina != null)
		            	if (((pojoDamCurtain) entrada).getSizeDescription().toString().length() > 0)
		            		texto_tamaño_cortina.setText(((pojoDamCurtain) entrada).getSizeDescription().toString());
		            	else
		            		texto_tamaño_cortina.setText("Ninguno");

		            TextView texto_comportamiento_cortina = (TextView) view.findViewById(R.id.textComportamientoFilaCortina);
		            if (texto_comportamiento_cortina != null)
		            	if (((pojoDamCurtain) entrada).getBehaivorDescription().toString().length() > 0)
		            		texto_comportamiento_cortina.setText(((pojoDamCurtain) entrada).getBehaivorDescription().toString());
		            	else
		            		texto_comportamiento_cortina.setText("Ninguno");

		            TextView texto_tipo_cortina = (TextView) view.findViewById(R.id.textTipoFilaCortina);
		            if (texto_tipo_cortina != null)
		            	if (((pojoDamCurtain) entrada).getTypeDescription().toString().length() > 0)
		            		texto_tipo_cortina.setText(((pojoDamCurtain) entrada).getTypeDescription().toString());
		            	else
		            		texto_tipo_cortina.setText("Ninguno");

		            TextView texto_material_cortina = (TextView) view.findViewById(R.id.textMaterialFilaCortina);
		            if (texto_material_cortina != null)
		            	if (((pojoDamCurtain) entrada).getMaterialDescription().toString().length() > 0)
		            		texto_material_cortina.setText(((pojoDamCurtain) entrada).getMaterialDescription().toString());
		            	else
		            		texto_material_cortina.setText("Ninguno");


		            TextView texto_altura_maxima_cortina = (TextView) view.findViewById(R.id.textAlturaMaximaFilaCortina);
		            if (texto_altura_maxima_cortina != null)
		            	texto_altura_maxima_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getMaxHeight()));

		            TextView texto_elevacion_corona_cortina = (TextView) view.findViewById(R.id.textElevacionCoronaFilaCortina);
		            if (texto_elevacion_corona_cortina != null)
		            	texto_elevacion_corona_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getCrownElevation()));

		            TextView texto_longitud_cortina = (TextView) view.findViewById(R.id.textLongitudFilaCortina);
		            if (texto_longitud_cortina != null)
		            	texto_longitud_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getLength()));

		            TextView texto_ancho_cortina = (TextView) view.findViewById(R.id.textAnchoFilaCortina);
		            if (texto_ancho_cortina != null)
		            	texto_ancho_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getWidth()));

		            TextView texto_taludes_aguas_arriba_cortina = (TextView) view.findViewById(R.id.textTaludesAguasArribaFilaCortina);
		            if (texto_taludes_aguas_arriba_cortina != null)
		            	texto_taludes_aguas_arriba_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getOverwaterSlope()));

		            TextView texto_taludes_aguas_abajo_cortina = (TextView) view.findViewById(R.id.textTaludesAguasAbajoFilaCortina);
		            if (texto_taludes_aguas_abajo_cortina != null)
		            	texto_taludes_aguas_abajo_cortina.setText(((pojoDamCurtain) entrada).getUnderwaterSlope());

		            TextView texto_altura_parapeto_cortina = (TextView) view.findViewById(R.id.textAlturaParapetoFilaCortina);
		            if (texto_altura_parapeto_cortina != null)
		            	texto_altura_parapeto_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getShimHeight()));

		            TextView texto_volumen_cuerpo_cortina = (TextView) view.findViewById(R.id.textVolumenCuerpoFilaCortina);
		            if (texto_volumen_cuerpo_cortina != null)
		            	texto_volumen_cuerpo_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getVolumeBody()));

		            TextView texto_altura_sobre_calce_cortina = (TextView) view.findViewById(R.id.textAlturaSobreCalceFilaCortina);
		            if (texto_altura_sobre_calce_cortina != null)
		            	texto_altura_sobre_calce_cortina.setText(String.valueOf(((pojoDamCurtain) entrada).getBreastwallHeight()));

		            TextView texto_otras_caracteristicas_cortina = (TextView) view.findViewById(R.id.textOtrasCaracteristicasFilaCortina);
		            if (texto_otras_caracteristicas_cortina != null)
		            	texto_otras_caracteristicas_cortina.setText(((pojoDamCurtain) entrada).getFeatures());

		        }
			}
		};
        listViewCortina.setAdapter(adaptadorListaCortinas);
        registerForContextMenu(listViewCortina);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoDamCurtain pCortina : pojo.getCurtainFeatures())
			{
				cortinas.add(pCortina);
			}
			adaptadorListaCortinas.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaCortinaClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaCortinaClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevaCortina = new DialogCortina(myActivity, fragment, Constants.DAMS);
            dialogAgregarNuevaCortina.setArguments(datosActuales);

            dialogAgregarNuevaCortina.show(fm, "Agregar información de la nueva Cortina");			
		}
	}

	class GuardarCortinasClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoDamCurtain pCortina : cortinas)
			{
				process.processCourtainSave(propertyID, pCortina);
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

			identificadorCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getCurtainName();
			tamañoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getSize();
			comportamientoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getBehaivor();
			tipoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getType();
			materialCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getMaterial();
			alturaMaximaCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getMaxHeight();
			elevacionCoronaCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getCrownElevation();
			longitudCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getLength();
			anchoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getWidth();
			taludesAguasArribaCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getOverwaterSlope();
			taludesAguasAbajoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getUnderwaterSlope();
			alturaParapetoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getShimHeight();
			volumenCuerpoCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getVolumeBody();
			alturaSobreCalceCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getBreastwallHeight();
			otrasCaracteristicasCortinaSeleccionado = ((pojoDamCurtain)parent.getItemAtPosition(position)).getFeatures();

			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo,
			String identificadorCortinaSeleccionado,
			String tamañoCortinaSeleccionado,
			String descTamañoCortinaSeleccionado,
			String comportamientoCortinaSeleccionado,
			String descComportamientoCortinaSeleccionado,
			String tipoCortinaSeleccionado,
			String descTipoCortinaSeleccionado,
			String materialCortinaSeleccionado,
			String descMaterialCortinaSeleccionado,
			double alturaMaximaCortinaSeleccionado,
			double elevacionCoronaCortinaSeleccionado,
			double longitudCortinaSeleccionado,
			double anchoCortinaSeleccionado,
			String taludesAguasArribaCortinaSeleccionado,
			String taludesAguasAbajoCortinaSeleccionado,
			double alturaParapetoCortinaSeleccionado,
			double volumenCuerpoCortinaSeleccionado,
			double alturaSobreCalceCortinaSeleccionado,
			String otrasCaracteristicasCortinaSeleccionado) 
	{
		if (validaCampos(identificadorCortinaSeleccionado, tamañoCortinaSeleccionado, comportamientoCortinaSeleccionado,
				tipoCortinaSeleccionado, materialCortinaSeleccionado, alturaMaximaCortinaSeleccionado, elevacionCoronaCortinaSeleccionado,
				longitudCortinaSeleccionado, anchoCortinaSeleccionado, taludesAguasArribaCortinaSeleccionado, taludesAguasAbajoCortinaSeleccionado, 
				alturaParapetoCortinaSeleccionado, volumenCuerpoCortinaSeleccionado, alturaSobreCalceCortinaSeleccionado,
				otrasCaracteristicasCortinaSeleccionado))
		{
			if (bEsNuevo)
			{
				pojoCortina = new pojoDamCurtain();
				pojoCortina.setCurtainID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			} else {
				pojoCortina = cortinas.get(posicionElementoSeleccionado);
			}
	
			pojoCortina.setCurtainName(identificadorCortinaSeleccionado);
			pojoCortina.setSize(tamañoCortinaSeleccionado);
			pojoCortina.setSizeDescription(descTamañoCortinaSeleccionado);
			pojoCortina.setBehaivor(comportamientoCortinaSeleccionado);
			pojoCortina.setBehaivorDescription(descComportamientoCortinaSeleccionado);
			pojoCortina.setType(tipoCortinaSeleccionado);
			pojoCortina.setTypeDescription(descTipoCortinaSeleccionado);
			pojoCortina.setMaterial(materialCortinaSeleccionado);
			pojoCortina.setMaterialDescription(descMaterialCortinaSeleccionado);
			pojoCortina.setMaxHeight(alturaMaximaCortinaSeleccionado);
			pojoCortina.setCrownElevation(elevacionCoronaCortinaSeleccionado);
			pojoCortina.setLength(longitudCortinaSeleccionado);
			pojoCortina.setWidth(anchoCortinaSeleccionado);
			pojoCortina.setOverwaterSlope(taludesAguasArribaCortinaSeleccionado);
			pojoCortina.setUnderwaterSlope(taludesAguasAbajoCortinaSeleccionado);
			pojoCortina.setShimHeight(alturaParapetoCortinaSeleccionado);
			pojoCortina.setVolumeBody(volumenCuerpoCortinaSeleccionado);
			pojoCortina.setBreastwallHeight(alturaSobreCalceCortinaSeleccionado);
			pojoCortina.setFeatures(otrasCaracteristicasCortinaSeleccionado);
	
			if (bEsNuevo)
				cortinas.add(pojoCortina);
			adaptadorListaCortinas.notifyDataSetChanged();
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
			botonAgregarCortina.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Cortina") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("identificadorCortinaSeleccionado", identificadorCortinaSeleccionado);
            datosActuales.putString("tamañoCortinaSeleccionado", tamañoCortinaSeleccionado);
            datosActuales.putString("comportamientoCortinaSeleccionado", comportamientoCortinaSeleccionado);            
            datosActuales.putString("tipoCortinaSeleccionado", tipoCortinaSeleccionado);
            datosActuales.putString("materialCortinaSeleccionado", materialCortinaSeleccionado);
            datosActuales.putDouble("alturaMaximaCortinaSeleccionado", alturaMaximaCortinaSeleccionado);
            datosActuales.putDouble("elevacionCoronaCortinaSeleccionado", elevacionCoronaCortinaSeleccionado);
            datosActuales.putDouble("longitudCortinaSeleccionado", longitudCortinaSeleccionado);
            datosActuales.putDouble("anchoCortinaSeleccionado", anchoCortinaSeleccionado);
            datosActuales.putString("taludesAguasArribaCortinaSeleccionado", taludesAguasArribaCortinaSeleccionado);
            datosActuales.putString("taludesAguasAbajoCortinaSeleccionado", taludesAguasAbajoCortinaSeleccionado);
            datosActuales.putDouble("alturaParapetoCortinaSeleccionado", alturaParapetoCortinaSeleccionado);
            datosActuales.putDouble("volumenCuerpoCortinaSeleccionado", volumenCuerpoCortinaSeleccionado);
            datosActuales.putDouble("alturaSobreCalceCortinaSeleccionado", alturaSobreCalceCortinaSeleccionado);
            datosActuales.putString("otrasCaracteristicasCortinaSeleccionado", otrasCaracteristicasCortinaSeleccionado);

            dialogAgregarNuevaCortina = new DialogCortina(myActivity, this, Constants.DAMS);
            dialogAgregarNuevaCortina.setArguments(datosActuales);
            dialogAgregarNuevaCortina.show(fm, "dialog1");	

		} else if (item.getTitle() == "Eliminar Cortina") {
			//Toast.makeText(myActivity, "Eliminar Puente: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			cortinas.remove(posicionElementoSeleccionado);
			adaptadorListaCortinas.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Cortina");
		menu.add("Eliminar Cortina");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean validaCampos(
			String identificadorCortinaSeleccionado, 
			String tamañoCortinaSeleccionado,
			String comportamientoCortinaSeleccionado,
			String tipoCortinaSeleccionado,
			String materialCortinaSeleccionado,
			double alturaMaximaCortinaSeleccionado,
			double elevacionCoronaCortinaSeleccionado,
			double longitudCortinaSeleccionado,
			double anchoCortinaSeleccionado,
			String taludesAguasArribaCortinaSeleccionado,
			String taludesAguasAbajoCortinaSeleccionado,
			double alturaParapetoCortinaSeleccionado,
			double volumenCuerpoCortinaSeleccionado,
			double alturaSobreCalceCortinaSeleccionado,
			String otrasCaracteristicasCortinaSeleccionado)
	{
		if (identificadorCortinaSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el identificador de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tamañoCortinaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar tamaño de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if(comportamientoCortinaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar comportamiento de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if(tipoCortinaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar tipo de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if(materialCortinaSeleccionado.compareTo("-1")==0){
			Toast.makeText(myActivity, "Se requiere indicar material de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if (alturaMaximaCortinaSeleccionado==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if (elevacionCoronaCortinaSeleccionado==0) {
			Toast.makeText(myActivity, "Se debe indicar la elevación de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if (longitudCortinaSeleccionado==0) {
			Toast.makeText(myActivity, "Se debe indicar la longitud de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if (taludesAguasArribaCortinaSeleccionado.compareTo("Seleccione opción")==0){
			Toast.makeText(myActivity, "Se debe indicar ancho de la cortina", Toast.LENGTH_LONG).show();
			return false;
		}
		if (taludesAguasArribaCortinaSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se debe indicar taludes aguas arriba", Toast.LENGTH_LONG).show();
			return false;
		}
		if (taludesAguasAbajoCortinaSeleccionado.length()==0) {
			Toast.makeText(myActivity, "Se debe indicar taludes aguas abajo", Toast.LENGTH_LONG).show();
			return false;
		}
//		if (edit_alturaParapetoCortina.getText().length()==0) {
//			Toast.makeText(myActivity, "Se debe indicar altura del parapeto", Toast.LENGTH_LONG).show();
//			edit_alturaParapetoCortina.requestFocus();
//			return false;
//		}
//		if (edit_volumenCuerpoCortina.getText().length()==0) {
//			Toast.makeText(myActivity, "Se debe indicar volumen cuerpo", Toast.LENGTH_LONG).show();
//			edit_volumenCuerpoCortina.requestFocus();
//			return false;
//		}
//		if (edit_alturaSobreCalceCortina.getText().length()==0) {
//			Toast.makeText(myActivity, "Se debe indicar la altura sobre el calce", Toast.LENGTH_LONG).show();
//			edit_alturaSobreCalceCortina.requestFocus();
//			return false;
//		}
//		if (edit_otrasCaracteristicasCortina.getText().length()==0) {
//			Toast.makeText(myActivity, "Se debe indicar otras caracteristicas", Toast.LENGTH_LONG).show();
//			edit_otrasCaracteristicasCortina.requestFocus();
//			return false;
//		}
		return true;
	}
}
