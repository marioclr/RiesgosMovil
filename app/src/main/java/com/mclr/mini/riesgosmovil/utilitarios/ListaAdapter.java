package com.mclr.mini.riesgosmovil.utilitarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class ListaAdapter extends BaseAdapter {
	private ArrayList<?> datos;
	private int R_layout_idView;
	private Context contexto;

	public ListaAdapter(Context p_contexto, int p_R_Layout_IdView, ArrayList<?> p_datos){
		super();
		this.contexto = p_contexto;
		this.R_layout_idView = p_R_Layout_IdView;
		this.datos = p_datos;
	}

	@Override
	public int getCount() {
		return datos.size();
	}

	@Override
	public Object getItem(int position) {
		return datos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R_layout_idView, null);
		}
		onEntrada(datos.get(position), view);
		return view;
	}
	
	public abstract void onEntrada (Object entrada, View view);
}
