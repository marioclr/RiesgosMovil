package com.mclr.mini.riesgosmovil.utilitarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;

import java.util.List;

public class CatalogItemSpinnerAdapter extends ArrayAdapter<CatalogItem> {

	private Context context;
	private List<CatalogItem> datos = null;

	public CatalogItemSpinnerAdapter(Context p_context, List<CatalogItem> p_datos)
	{
		super(p_context, R.layout.spinner_selected_item, p_datos);
		this.context = p_context;
		this.datos = p_datos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
		{
			convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spinner_selected_item, null);
		}
		if (datos.get(position).getIcon() != 0)
			((ImageView)convertView.findViewById(R.id.icono)).setBackgroundResource(datos.get(position).getIcon());
		((TextView)convertView.findViewById(R.id.texto)).setText(datos.get(position).getName());
		return convertView;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row==null)
		{
			LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = layoutInflater.inflate(R.layout.spinner_list_item, parent, false);
		}
		
		if(row.getTag() == null)
		{
			CatalogItemHolder catalogItemHolder = new CatalogItemHolder();
			catalogItemHolder.setIcono((ImageView) row.findViewById(R.id.icono));
			catalogItemHolder.setName((TextView) row.findViewById(R.id.texto));
			row.setTag(catalogItemHolder);
		}
		CatalogItem catalogItem = datos.get(position);
		((CatalogItemHolder) row.getTag()).getIcono().setImageResource(catalogItem.getIcon());
		((CatalogItemHolder) row.getTag()).getName().setText(catalogItem.getName());
		
		return row;
	}
	
	private class CatalogItemHolder
	{
		private TextView id;
		private TextView key;
		private TextView name;
		private ImageView icono;

		public TextView getId() {
			return id;
		}

		public void setId(TextView id) {
			this.id = id;
		}

		public TextView getKey() {
			return key;
		}

		public void setKey(TextView key) {
			this.key = key;
		}

		public TextView getName() {
			return name;
		}

		public void setName(TextView name) {
			this.name = name;
		}

		public ImageView getIcono() {
			return icono;
		}

		public void setIcono(ImageView icono) {
			this.icono = icono;
		}
		
		
	}
}
