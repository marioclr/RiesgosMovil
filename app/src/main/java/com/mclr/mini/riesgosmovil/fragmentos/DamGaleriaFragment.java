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
import com.mclr.mini.riesgosmovil.modelos.pojoDamGalery;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamGaleriaFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogGaleria dialogAgregarNuevaGaleria;
	private ImageButton botonAgregarGaleria;
	private ListView listViewGaleria;
	private ArrayList<pojoDamGalery> galerias;
	private ListaAdapter adaptadorListaGaleria;
	pojoDamGalery pojoGaleria;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	private String numeroGaleriaSeleccionado;
	private String seccionGaleriaSeleccionado;
	private Double longitudGaleriaSeleccionado;
	private Double anchoGaleriaSeleccionado;
	private Double altoGaleriaSeleccionado;
	private Double elevacionGaleriaSeleccionado;

	private String selectedRemoveIDs="";

	public DamGaleriaFragment()
	{
		galerias = new ArrayList<pojoDamGalery>();
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
		View rootView = inflater.inflate(R.layout.dams_galerias, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotGalerias_dams);
		botonGuardar.setOnClickListener(new GuardarGaleriaClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotGalerias_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarGaleria = (ImageButton) myActivity.findViewById(R.id.buttonAgregarGalerias_dams);
		botonAgregarGaleria.setOnClickListener(new AgregaGaleriaClickListener(this));

//		pojoGaleria = new pojoDamGalery();
//		pojoGaleria.setGaleryID("1");
//		pojoGaleria.setGaleryNumber("1");
//		pojoGaleria.setSection("Sec1");
//		pojoGaleria.setLength(123);
//		pojoGaleria.setWidth(321);
//		pojoGaleria.setHeight(231);
//		pojoGaleria.setElevation(213);
//		galerias.add(pojoGaleria);
//
//		pojoGaleria = new pojoDamGalery();
//		pojoGaleria.setGaleryID("2");
//		pojoGaleria.setGaleryNumber("2");
//		pojoGaleria.setSection("Sec2");
//		pojoGaleria.setLength(123);
//		pojoGaleria.setWidth(321);
//		pojoGaleria.setHeight(231);
//		pojoGaleria.setElevation(213);
//		galerias.add(pojoGaleria);
//
//		pojoGaleria = new pojoDamGalery();
//		pojoGaleria.setGaleryID("3");
//		pojoGaleria.setGaleryNumber("3");
//		pojoGaleria.setSection("Sec3");
//		pojoGaleria.setLength(123);
//		pojoGaleria.setWidth(321);
//		pojoGaleria.setHeight(231);
//		pojoGaleria.setElevation(213);
//		galerias.add(pojoGaleria);

		listViewGaleria = (ListView) myActivity.findViewById(R.id.listGalerias_dams);
		listViewGaleria.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaGaleria = new ListaAdapter(myActivity, R.layout.fila_galeria, galerias) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_numero_galeria = (TextView) view.findViewById(R.id.textNumeroFilaGaleria);
		            if (texto_numero_galeria != null) 
		            	texto_numero_galeria.setText(((pojoDamGalery) entrada).getGaleryNumber());

		            TextView texto_seccion_galeria = (TextView) view.findViewById(R.id.textSeccionFilaGaleria);
		            if (texto_seccion_galeria != null)
		            	texto_seccion_galeria.setText(((pojoDamGalery) entrada).getSection());

		            TextView texto_longitud_galeria = (TextView) view.findViewById(R.id.textLongitudFilaGaleria);
		            if (texto_longitud_galeria != null)
		            	texto_longitud_galeria.setText(String.valueOf(((pojoDamGalery) entrada).getLength()));

		            TextView texto_ancho_galeria = (TextView) view.findViewById(R.id.textAnchoFilaGaleria);
		            if (texto_ancho_galeria != null)
		            	texto_ancho_galeria.setText(String.valueOf(((pojoDamGalery) entrada).getWidth()));

		            TextView texto_alto_galeria = (TextView) view.findViewById(R.id.textAltoFilaGaleria);
		            if (texto_alto_galeria != null)
		            	texto_alto_galeria.setText(String.valueOf(((pojoDamGalery) entrada).getHeight()));

		            TextView texto_elevacion_galeria = (TextView) view.findViewById(R.id.textElevacionFilaGaleria);
		            if (texto_elevacion_galeria != null)
		            	texto_elevacion_galeria.setText(String.valueOf(((pojoDamGalery) entrada).getElevation()));

		        }
			}
		};

        listViewGaleria.setAdapter(adaptadorListaGaleria);
        registerForContextMenu(listViewGaleria);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoDamGalery pPuente : pojo.getDamGaleries())
			{
				galerias.add(pPuente);
			}
			adaptadorListaGaleria.notifyDataSetChanged();
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
            dialogAgregarNuevaGaleria = new DialogGaleria(myActivity, fragment, Constants.DAM);
            dialogAgregarNuevaGaleria.setArguments(datosActuales);

            dialogAgregarNuevaGaleria.show(fm, "Agregar información de la nueva Galería");			
		}
	}

	class GuardarGaleriaClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoDamGalery pGaleria : galerias)
			{
				process.processGaleriesSave(propertyID, pGaleria);
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
			numeroGaleriaSeleccionado = ((pojoDamGalery)parent.getItemAtPosition(position)).getGaleryNumber();
			seccionGaleriaSeleccionado = ((pojoDamGalery)parent.getItemAtPosition(position)).getSection();
			longitudGaleriaSeleccionado = ((pojoDamGalery)parent.getItemAtPosition(position)).getLength();
			anchoGaleriaSeleccionado = ((pojoDamGalery)parent.getItemAtPosition(position)).getWidth();
			altoGaleriaSeleccionado = ((pojoDamGalery)parent.getItemAtPosition(position)).getHeight();
			elevacionGaleriaSeleccionado = ((pojoDamGalery)parent.getItemAtPosition(position)).getElevation();

			return false;
		}
	}

	public void agregarItemList(
			boolean bEsNuevo, 
			String numeroGaleria, 
			String seccionGaleria, 
			double longitudGaleria, 
			double anchoGaleria, 
			double altoGaleria, 
			double elevacionGaleria) 
	{
		if (validaCampos(
				numeroGaleria, 
				seccionGaleria, 
				longitudGaleria, 
				anchoGaleria, 
				altoGaleria, 
				elevacionGaleria
				))
		{
			if (bEsNuevo)
			{
				pojoGaleria = new pojoDamGalery();
				pojoGaleria.setGaleryID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
			} else {
				pojoGaleria = galerias.get(posicionElementoSeleccionado);
			}
	
			pojoGaleria.setGaleryNumber(numeroGaleria);
			pojoGaleria.setSection(seccionGaleria);
			pojoGaleria.setLength(longitudGaleria);
			pojoGaleria.setWidth(anchoGaleria);
			pojoGaleria.setHeight(altoGaleria);
			pojoGaleria.setElevation(elevacionGaleria);
	
			if (bEsNuevo)
				galerias.add(pojoGaleria);
			adaptadorListaGaleria.notifyDataSetChanged();
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
			botonAgregarGaleria.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Galería") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putString("numeroGaleriaSeleccionado", numeroGaleriaSeleccionado);
            datosActuales.putString("seccionGaleriaSeleccionado", seccionGaleriaSeleccionado);
            datosActuales.putDouble("longitudGaleriaSeleccionado", longitudGaleriaSeleccionado);
            datosActuales.putDouble("anchoGaleriaSeleccionado", anchoGaleriaSeleccionado);
            datosActuales.putDouble("altoGaleriaSeleccionado", altoGaleriaSeleccionado);
            datosActuales.putDouble("elevacionGaleriaSeleccionado", elevacionGaleriaSeleccionado);

            dialogAgregarNuevaGaleria = new DialogGaleria(myActivity, this, Constants.DAM);
            dialogAgregarNuevaGaleria.setArguments(datosActuales);
            dialogAgregarNuevaGaleria.show(fm, "Modificar Galería");	

		} else if (item.getTitle() == "Eliminar Galería") {
			//Toast.makeText(myActivity, "Eliminar Galería: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			galerias.remove(posicionElementoSeleccionado);
			adaptadorListaGaleria.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Galería");
		menu.add("Eliminar Galería");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean validaCampos(
			String numeroGaleria, 
			String seccionGaleria, 
			double longitudGaleria, 
			double anchoGaleria, 
			double altoGaleria, 
			double elevacionGaleria)
	{
		if (numeroGaleria.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar el número de la galería", Toast.LENGTH_LONG).show();
			return false;
		}
		if (seccionGaleria.length()==0) {
			Toast.makeText(myActivity, "Se requiere indicar la sección de la galería", Toast.LENGTH_LONG).show();
			return false;
		}
		if (longitudGaleria==0) {
			Toast.makeText(myActivity, "Se requiere indicar la longitud de la galería", Toast.LENGTH_LONG).show();
			return false;
		}
		if (anchoGaleria==0) {
			Toast.makeText(myActivity, "Se requiere indicar la ancho de la galería", Toast.LENGTH_LONG).show();
			return false;
		}
		if (altoGaleria==0) {
			Toast.makeText(myActivity, "Se requiere indicar la altura de la galería", Toast.LENGTH_LONG).show();
			return false;
		}
		if (elevacionGaleria==0) {
			Toast.makeText(myActivity, "Se requiere indicar la elevación de la galería", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
}
