package com.mclr.mini.riesgosmovil.fragmentos;

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
import com.mclr.mini.riesgosmovil.modelos.pojoSinking;
import com.mclr.mini.riesgosmovil.modelos.pojoWays;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

public class WaysHundimientoFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogHundimiento dialogAgregarNuevaHundimiento;
	private ImageButton botonAgregarHundimiento;
	private ListView listViewHundimiento;
	private ArrayList<pojoSinking> hundimientos;
	private ListaAdapter adaptadorListaHundimiento;
	pojoSinking pojoHundimiento;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	private double ubicacionHundimientoSeleccionado;
	private int gravedadHundimientoSeleccionado;
	//private String descGravedadHundimientoSeleccionado;

	private String selectedRemoveIDs="";

	public WaysHundimientoFragment()
	{
		hundimientos = new ArrayList<pojoSinking>();
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
		View rootView = inflater.inflate(R.layout.ways_hundimiento, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotHundimiento_ways);
		botonGuardar.setOnClickListener(new GuardarGaleriaClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotHundimiento_ways);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarHundimiento = (ImageButton) myActivity.findViewById(R.id.buttonAgregarHundimiento_ways);
		botonAgregarHundimiento.setOnClickListener(new AgregaGaleriaClickListener(this));

//		pojoHundimiento = new pojoSinking();
//		pojoHundimiento.setSinkingID("1");
//		pojoHundimiento.setSeverity(1);
//		pojoHundimiento.setPosition(12345);
//		hundimientos.add(pojoHundimiento);
//
//		pojoHundimiento = new pojoSinking();
//		pojoHundimiento.setSinkingID("2");
//		pojoHundimiento.setSeverity(2);
//		pojoHundimiento.setPosition(54321);
//		hundimientos.add(pojoHundimiento);
//
//		pojoHundimiento = new pojoSinking();
//		pojoHundimiento.setSinkingID("3");
//		pojoHundimiento.setSeverity(3);
//		pojoHundimiento.setPosition(98765);
//		hundimientos.add(pojoHundimiento);
//
//		pojoHundimiento = new pojoSinking();
//		pojoHundimiento.setSinkingID("4");
//		pojoHundimiento.setSeverity(1);
//		pojoHundimiento.setPosition(67890);
//		hundimientos.add(pojoHundimiento);

		listViewHundimiento = (ListView) myActivity.findViewById(R.id.listHundimiento_ways);
		listViewHundimiento.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaHundimiento = new ListaAdapter(myActivity, R.layout.fila_hundimiento, hundimientos) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_ubicacionHundimiento = (TextView) view.findViewById(R.id.textUbicacionFilaHundimiento); 
		            if (texto_ubicacionHundimiento != null) 
		            	texto_ubicacionHundimiento.setText(Double.toString(((pojoSinking) entrada).getPosition()));

		            TextView texto_gravedadHundimiento = (TextView) view.findViewById(R.id.textGravedadFilaHundimiento); 
		            if (texto_gravedadHundimiento != null)
		            	if (((pojoSinking) entrada).getSeverity() == 1)
		            		texto_gravedadHundimiento.setText("Imperceptibles");
		            	if (((pojoSinking) entrada).getSeverity() == 2)
		            		texto_gravedadHundimiento.setText("Lígeros");
		            	if (((pojoSinking) entrada).getSeverity() == 3)
		            		texto_gravedadHundimiento.setText("Muy grandes");
		        }
			}
		};

        listViewHundimiento.setAdapter(adaptadorListaHundimiento);
        registerForContextMenu(listViewHundimiento);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoWays pojo = propiertyVW.getWaysPptyDetail(propertyID, postalCodeID);
			for (pojoSinking pPuente : pojo.getSinkings())
			{
				hundimientos.add(pPuente);
			}
			adaptadorListaHundimiento.notifyDataSetChanged();
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
            dialogAgregarNuevaHundimiento = new DialogHundimiento(myActivity, fragment, Constants.WAYS);
            dialogAgregarNuevaHundimiento.setArguments(datosActuales);

            dialogAgregarNuevaHundimiento.show(fm, "Agregar información del nuevo Hundimiento");			
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
			for (pojoSinking pHundimiento : hundimientos)
			{
				process.processHundimientoSave(propertyID, pHundimiento);
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
			ubicacionHundimientoSeleccionado = ((pojoSinking)parent.getItemAtPosition(position)).getPosition();
			gravedadHundimientoSeleccionado = ((pojoSinking)parent.getItemAtPosition(position)).getSeverity();

			return false;
		}
	}

	public void agregarItemList(boolean bEsNuevo, double ubicacion, int gravedad) 
	{
		if (bEsNuevo)
		{
			pojoHundimiento = new pojoSinking();
			pojoHundimiento.setSinkingID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
		} else {
			pojoHundimiento = hundimientos.get(posicionElementoSeleccionado);
		}

		pojoHundimiento.setPosition(ubicacion);
		pojoHundimiento.setSeverity(gravedad);

		if (bEsNuevo)
			hundimientos.add(pojoHundimiento);
		adaptadorListaHundimiento.notifyDataSetChanged();
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
			//botonAgregarGaleria.setEnabled(false);
			Toast.makeText(myActivity, "Para realizar registros en esta pantalla debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Modificar Hundimiento") {
			FragmentManager fm = myActivity.getSupportFragmentManager();

			Bundle datosActuales = new Bundle();
            datosActuales.putDouble("ubicacionHundimientoSeleccionado", ubicacionHundimientoSeleccionado);
            datosActuales.putInt("gravedadHundimientoSeleccionado", gravedadHundimientoSeleccionado);

            dialogAgregarNuevaHundimiento = new DialogHundimiento(myActivity, this, Constants.WAYS);
            dialogAgregarNuevaHundimiento.setArguments(datosActuales);
            dialogAgregarNuevaHundimiento.show(fm, "Modificar Galería");	

		} else if (item.getTitle() == "Eliminar Hundimiento") {
			//Toast.makeText(myActivity, "Eliminar Hundimiento: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			hundimientos.remove(posicionElementoSeleccionado);
			adaptadorListaHundimiento.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Hundimiento");
		menu.add("Eliminar Hundimiento");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
}
