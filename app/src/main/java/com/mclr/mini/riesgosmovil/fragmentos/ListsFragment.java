package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.pojoTunnel;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;

public class ListsFragment extends Fragment {
	private MainActivity myActivity = null;
	private ArrayList<pojoTunnel> tuneles;
	DialogTunel nuevoDialog1;
	private ListView lista;
	private ListaAdapter adaptadorTunel;
	private Button botonazo;
	private Button botonDialog;
	pojoTunnel tunel;

	public ListsFragment() {
		tuneles = new ArrayList<pojoTunnel>();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonazo = (Button) myActivity.findViewById(R.id.button_agregar_tunel);
		botonDialog = (Button) myActivity.findViewById(R.id.button_dialog_agregar_tunel);

		botonazo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tunel = new pojoTunnel();
				tunel.setTunnelID("3");
				tunel.setTunnelName("Tunel 3");
				tunel.setTunnelLength(12345);
				tunel.setRenewalTunnelValue("87654321");
				tuneles.add(tunel);
				adaptadorTunel.notifyDataSetChanged();
			}
		});

		botonDialog.setOnClickListener(new AgregaTunelClickListener(this));

		lista = (ListView) getActivity().findViewById(R.id.lista_tuneles);
		adaptadorTunel = new ListaAdapter(getActivity(), R.layout.fila_tunel, tuneles) {
			
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

		        }
			}
		};
        lista.setAdapter(adaptadorTunel);
        lista.setOnTouchListener(new ListView.OnTouchListener() {
        	@Override
        	public boolean onTouch(View v, MotionEvent event) {
	        	if (event.getAction() == MotionEvent.ACTION_MOVE)
	        	{
	        		lista.scrollBy(0, 2);
	        	}
	        		return false;
	        	}
        	});
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.listas_dialogos, container, false);
		tunel = new pojoTunnel();
		tunel.setTunnelID("1");
		tunel.setTunnelName("Tunel 1");
		tunel.setTunnelLength(12345);
		tunel.setRenewalTunnelValue("12345678");
		tuneles.add(tunel);

		tunel = new pojoTunnel();
		tunel.setTunnelID("2");
		tunel.setTunnelName("Tunel 2");
		tunel.setTunnelLength(12345);
		tunel.setRenewalTunnelValue("87654321");
		tuneles.add(tunel);

		//return super.onCreateView(inflater, container, savedInstanceState);
		return rootView;
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
            // vamos a pasar los datos actuales
            Bundle datosActuales = new Bundle();
            TextView txtvw = (TextView) myActivity.findViewById (R.id.textNombreFilaTunel);
            datosActuales.putString("comoestaeldia", (String) txtvw.getText());
            nuevoDialog1 = new DialogTunel(myActivity, fragment, "None");
            nuevoDialog1.setArguments(datosActuales);
            //Mostramos el diálogo
            nuevoDialog1.show(fm, "dialog1");			
		}
	}

	public void agregarItemList(String nombreTunel, double longitudTunel, String tipoTunel) {
		tunel = new pojoTunnel();

		tunel.setTunnelID("4");
		tunel.setTunnelName(nombreTunel);
		tunel.setTunnelLength(longitudTunel);
		tunel.setTunnelType(tipoTunel);
		tunel.setRenewalTunnelValue("12345678");
		tuneles.add(tunel);
		adaptadorTunel.notifyDataSetChanged();
	}

}
