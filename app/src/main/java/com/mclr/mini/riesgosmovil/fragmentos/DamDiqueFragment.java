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
import com.mclr.mini.riesgosmovil.modelos.pojoDamDikes;
import com.mclr.mini.riesgosmovil.modelos.pojoDams;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.KeyOperations;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class DamDiqueFragment extends Fragment {
	private MainActivity myActivity = null;
	VWProperties propiertyVW;
	DialogDique dialogAgregarNuevoDique;
	private ImageButton botonAgregarDique;
	private ListView listViewDiques;
	private ArrayList<pojoDamDikes> diques;
	private ListaAdapter adaptadorListaDique;
	pojoDamDikes pojoDique;

	private ImageButton botonGuardar;
	private ImageButton botonSalir;
	String propertyID;
	String postalCodeID;

	private int posicionElementoSeleccionado;
	private String numeroDiqueSeleccionado;
	private double alturaDiqueSeleccionado;
	private double anchoBaseDiqueSeleccionado;
	private double nivelInundacionDiqueSeleccionado;

	private String selectedRemoveIDs="";

	public DamDiqueFragment()
	{
		diques = new ArrayList<pojoDamDikes>();
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
		View rootView = inflater.inflate(R.layout.dams_diques, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonGuardar = (ImageButton) myActivity.findViewById(R.id.buttonSaveBotDiques_dams);
		botonGuardar.setOnClickListener(new GuardarDiqueClickListener());
		botonSalir = (ImageButton) myActivity.findViewById(R.id.buttonOutBotDiques_dams);
		botonSalir.setOnClickListener(new OutClickListener());
		botonAgregarDique = (ImageButton) myActivity.findViewById(R.id.buttonAgregarDiques_dams);
		botonAgregarDique.setOnClickListener(new AgregaDiqueClickListener(this));

//		pojoDique = new pojoDamDikes();
//		pojoDique.setDikeID("1");
//		pojoDique.setDikeNumber("1");
//		pojoDique.setDikeHeight(5412);
//		pojoDique.setDikeBaseWidth(1234);
//		pojoDique.setDikeFloodLevel(4321);
//		diques.add(pojoDique);
//
//		pojoDique = new pojoDamDikes();
//		pojoDique.setDikeID("2");
//		pojoDique.setDikeNumber("2");
//		pojoDique.setDikeHeight(8723);
//		pojoDique.setDikeBaseWidth(5678);
//		pojoDique.setDikeFloodLevel(8765);
//		diques.add(pojoDique);
//
//		pojoDique = new pojoDamDikes();
//		pojoDique.setDikeID("3");
//		pojoDique.setDikeNumber("3");
//		pojoDique.setDikeHeight(6523);
//		pojoDique.setDikeBaseWidth(9999);
//		pojoDique.setDikeFloodLevel(9999);
//		diques.add(pojoDique);

		listViewDiques = (ListView) myActivity.findViewById(R.id.listDiques_dams);
		listViewDiques.setOnItemLongClickListener(new PresionaElementoLista());
		adaptadorListaDique = new ListaAdapter(myActivity, R.layout.fila_diques, diques) {

			@Override
			public void onEntrada(Object entrada, View view) {
		        if (entrada != null) {

		            TextView texto_numero_dique = (TextView) view.findViewById(R.id.textNumeroFilaDique);
		            if (texto_numero_dique != null) 
		            	texto_numero_dique.setText(((pojoDamDikes) entrada).getDikeNumber());

		            TextView texto_altura_dique = (TextView) view.findViewById(R.id.textAlturaFilaDique);
		            if (texto_altura_dique != null)
		            	texto_altura_dique.setText(String.valueOf(((pojoDamDikes) entrada).getDikeHeight()));

		            TextView texto_ancho_base_dique = (TextView) view.findViewById(R.id.textAnchoBaseFilaDique);
		            if (texto_ancho_base_dique != null)
		            	texto_ancho_base_dique.setText(String.valueOf(((pojoDamDikes) entrada).getDikeBaseWidth()));

		            TextView texto_nivel_inundacion_dique = (TextView) view.findViewById(R.id.textNivelInundacionFilaDique);
		            if (texto_nivel_inundacion_dique != null)
		            	texto_nivel_inundacion_dique.setText(String.valueOf(((pojoDamDikes) entrada).getDikeFloodLevel()));

		        }
			}
		};

        listViewDiques.setAdapter(adaptadorListaDique);
        registerForContextMenu(listViewDiques);

		propiertyVW = new VWProperties(myActivity);
		if (!propertyID.equals(Constants.GENERICO)){
			pojoDams pojo = propiertyVW.getDamPptyDetail(propertyID, postalCodeID);
			for (pojoDamDikes pDique : pojo.getDamDikes())
			{
				diques.add(pDique);
			}
			adaptadorListaDique.notifyDataSetChanged();
		}
		super.onActivityCreated(savedInstanceState);
	}

	class AgregaDiqueClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaDiqueClickListener(Fragment p_fragment)
		{
			fragment = p_fragment;
		}

		@Override
		public void onClick(View v) {
			FragmentManager fm = myActivity.getSupportFragmentManager();
            Bundle datosActuales = new Bundle();
            datosActuales.putBoolean("esNuevo", true);
            dialogAgregarNuevoDique = new DialogDique(myActivity, fragment, Constants.DAM);
            dialogAgregarNuevoDique.setArguments(datosActuales);

            dialogAgregarNuevoDique.show(fm, "Agregar información del nuevo Dique");			
		}
	}

	class GuardarDiqueClickListener implements OnClickListener
	{
		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);
		@Override
		public void onClick(View v) {
			if (selectedRemoveIDs.length() > 0)
				process.processPropertiesForRemove(selectedRemoveIDs);
			selectedRemoveIDs="";
			for (pojoDamDikes pDique : diques)
			{
				process.processDikeSave(propertyID, pDique);
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
			numeroDiqueSeleccionado = ((pojoDamDikes)parent.getItemAtPosition(position)).getDikeNumber();
			alturaDiqueSeleccionado = ((pojoDamDikes)parent.getItemAtPosition(position)).getDikeHeight();
			anchoBaseDiqueSeleccionado = ((pojoDamDikes)parent.getItemAtPosition(position)).getDikeBaseWidth();
			nivelInundacionDiqueSeleccionado = ((pojoDamDikes)parent.getItemAtPosition(position)).getDikeFloodLevel();

			return false;
		}
	}

	public void agregarItemList(boolean bEsNuevo, String numeroDique, double alturaDique, double anchoBaseDique, double nivelInundacionDique) 
	{
		if (bEsNuevo)
		{
			pojoDique = new pojoDamDikes();
			pojoDique.setDikeID(UUID.randomUUID().toString().toUpperCase(Locale.ENGLISH));
		} else {
			pojoDique = diques.get(posicionElementoSeleccionado);
		}
		pojoDique.setDikeNumber(numeroDique);
		pojoDique.setDikeHeight(alturaDique);
		pojoDique.setDikeBaseWidth(anchoBaseDique);
		pojoDique.setDikeFloodLevel(nivelInundacionDique);

		if (bEsNuevo)
		diques.add(pojoDique);
		adaptadorListaDique.notifyDataSetChanged();
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
			botonAgregarDique.setEnabled(false);
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
            datosActuales.putString("numeroDiqueSeleccionado", numeroDiqueSeleccionado);
            datosActuales.putDouble("alturaDiqueSeleccionado", alturaDiqueSeleccionado);
            datosActuales.putDouble("anchoBaseDiqueSeleccionado", anchoBaseDiqueSeleccionado);
            datosActuales.putDouble("nivelInundacionDiqueSeleccionado", nivelInundacionDiqueSeleccionado);

            dialogAgregarNuevoDique = new DialogDique(myActivity, this, Constants.DAM);
            dialogAgregarNuevoDique.setArguments(datosActuales);
            dialogAgregarNuevoDique.show(fm, "Modificar Dique");

		} else if (item.getTitle() == "Eliminar Dique") {
			//Toast.makeText(myActivity, "Eliminar Dique: " + posicionElementoSeleccionado + "En array: " + puentes.get(posicionElementoSeleccionado).getBridgeName(), Toast.LENGTH_LONG).show();
			diques.remove(posicionElementoSeleccionado);
			adaptadorListaDique.notifyDataSetChanged();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Modificar Dique");
		menu.add("Eliminar Dique");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
}
