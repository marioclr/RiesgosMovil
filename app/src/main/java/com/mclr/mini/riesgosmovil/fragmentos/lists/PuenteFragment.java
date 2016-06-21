package com.mclr.mini.riesgosmovil.fragmentos.lists;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.fragmentos.DialogTunel;
import com.mclr.mini.riesgosmovil.modelos.pojoTunnel;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;

import java.util.ArrayList;

public class PuenteFragment extends Fragment {
	private MainActivity myActivity = null;
	DialogTunel nuevoDialog1;
	View rootView1;
	Button botonazo;
	private Button botonDialog;
	ListView lista;
	private ArrayList<pojoTunnel> tuneles;
	private ListaAdapter adaptadorPuente;
	pojoTunnel tunel;

	public PuenteFragment()
	{
		tuneles = new ArrayList<pojoTunnel>();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		botonazo = (Button) rootView1.findViewById(R.id.button_agregar_puente_fragment);
		botonDialog = (Button) rootView1.findViewById(R.id.button_agregar_puente_dialog_fragment);

		botonazo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tunel = new pojoTunnel();
				tunel.setTunnelID("3");
				tunel.setTunnelName("Tunel 3");
				tunel.setTunnelLength(12345);
				tunel.setRenewalTunnelValue("87654321");
				tuneles.add(tunel);
				adaptadorPuente.notifyDataSetChanged();
			}
		});

		botonDialog.setOnClickListener(new AgregaPuenteClickListener(this));

		tunel = new pojoTunnel();
		tunel.setTunnelID("3");
		tunel.setTunnelName("Tunel 1");
		tunel.setTunnelLength(12345);
		tunel.setRenewalTunnelValue("87654321");
		tuneles.add(tunel);

		tunel = new pojoTunnel();
		tunel.setTunnelID("3");
		tunel.setTunnelName("Tunel 2");
		tunel.setTunnelLength(12345);
		tunel.setRenewalTunnelValue("87654321");
		tuneles.add(tunel);

		tunel = new pojoTunnel();
		tunel.setTunnelID("3");
		tunel.setTunnelName("Tunel 4");
		tunel.setTunnelLength(12345);
		tunel.setRenewalTunnelValue("87654321");
		tuneles.add(tunel);

//		tunel = new pojoTunnel();
//		tunel.setTunnelID("3");
//		tunel.setTunnelName("Tunel 5");
//		tunel.setTunnelLength(12345);
//		tunel.setRenewalTunnelValue(new BigDecimal(87654321));
//		tuneles.add(tunel);
//
//		tunel = new pojoTunnel();
//		tunel.setTunnelID("3");
//		tunel.setTunnelName("Tunel 6");
//		tunel.setTunnelLength(12345);
//		tunel.setRenewalTunnelValue(new BigDecimal(87654321));
//		tuneles.add(tunel);
//
//		tunel = new pojoTunnel();
//		tunel.setTunnelID("3");
//		tunel.setTunnelName("Tunel 7");
//		tunel.setTunnelLength(12345);
//		tunel.setRenewalTunnelValue(new BigDecimal(87654321));
//		tuneles.add(tunel);
//
//		tunel = new pojoTunnel();
//		tunel.setTunnelID("3");
//		tunel.setTunnelName("Tunel 8");
//		tunel.setTunnelLength(12345);
//		tunel.setRenewalTunnelValue(new BigDecimal(87654321));
//		tuneles.add(tunel);

		//lista = (ListView) getActivity().findViewById(R.id.lista_tuneles_fragment);
		lista = (ListView) rootView1.findViewById(R.id.lista_puentes_fragment);
		adaptadorPuente = new ListaAdapter(myActivity, R.layout.fila_puente, tuneles) {
			
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
        lista.setAdapter(adaptadorPuente);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		myActivity = (MainActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_puente, container, false);
		rootView1 = rootView;
		return rootView;
	}

	@Override
	public void onDestroyView() {
		Fragment fragment = (getFragmentManager().findFragmentById(R.id.fragmentPuente));
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.commit();
		super.onDestroyView();
	}

	class AgregaPuenteClickListener implements OnClickListener
	{
		Fragment fragment;

		AgregaPuenteClickListener(Fragment p_fragment)
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
		adaptadorPuente.notifyDataSetChanged();
	}
}
