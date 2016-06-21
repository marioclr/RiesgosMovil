package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.utilitarios.ExpandibleListViewAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.GrupoDeItems;

public class ListsFragment2 extends Fragment {
	private MainActivity myActivity = null;
	SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		crearDatos();
		ExpandableListView listView = (ExpandableListView) myActivity.findViewById(R.id.lista_tuneles2);
		ExpandibleListViewAdapter adapter = new ExpandibleListViewAdapter(myActivity, grupos);
		listView.setAdapter(adapter);
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
		View rootView = inflater.inflate(R.layout.listas_dialogos2, container, false);
		return rootView;
	}

	public void crearDatos() {
		GrupoDeItems grupo0 = new GrupoDeItems("Lechon");
		grupo0.children.add("Al horno");
		grupo0.children.add("A la parrilla");
		grupos.append(0, grupo0);
		GrupoDeItems grupo1 = new GrupoDeItems("Pescado");
		grupo1.children.add("Paella");
		grupo1.children.add("A la parrilla");
		grupo1.children.add("Frito");
		grupos.append(1, grupo1);
		GrupoDeItems grupo2 = new GrupoDeItems("Sandwichs");
		grupo2.children.add("Jamón, queso y ananá");
		grupo2.children.add("Pollo, morrones y aceitunas");
		grupo2.children.add("Carlitos");
		grupos.append(2, grupo2);
   }

}
