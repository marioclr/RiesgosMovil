package com.mclr.mini.riesgosmovil.utilitarios;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.mclr.mini.riesgosmovil.R;

public class FilaAsignacionAdapter extends SimpleCursorAdapter {
	private Cursor cursor;
	private OnClickListener listenerSwitch;

	public FilaAsignacionAdapter(Context contexto, OnClickListener listener, Cursor cursor, String[] from, int[] to){
		super(contexto, R.layout.fila_asignacion, cursor, from, to);
		this.cursor = cursor;
		this.listenerSwitch = listener;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		View row = super.getView(position, convertView, parent);
		FilaAsignacionHolder holder = null;
		holder = new FilaAsignacionHolder();

//		holder.botonOmitir = (Button) row.findViewById(R.id.button_omitir_asigna);
//		holder.botonOmitir.setTag(cursor.getString(cursor.getColumnIndex("_id")));
		holder.switchCerrar = (Switch) row.findViewById(R.id.switchAbrirCerrarAsignacion);
		holder.switchCerrar.setTag(cursor.getString(cursor.getColumnIndex("_id")));
		holder.switchCerrar.setOnClickListener(listenerSwitch);

		holder.filaSuperior = (TextView) row.findViewById(R.id.textAsigna_Superior);
		holder.filaInferior = (TextView) row.findViewById(R.id.textAsigna_Inferior);
		holder.filaInferiorStatus = (TextView) row.findViewById(R.id.textAsigna_InferiorStatus);

		// Referencias al xml fila_asignacion.xml
		ImageView imagen = (ImageView) row.findViewById(R.id.imageAsignacion);

		// Se obtiene la ruta de la imagen.
		String property_type = cursor.getString(cursor.getColumnIndex("PropertyType"));
		int finding = cursor.getInt(cursor.getColumnIndex("Finding"));
		int status = cursor.getInt(cursor.getColumnIndex("Status"));

		if(property_type.compareTo(Constants.AQUACULTURE) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.aquaculture);
			else
				imagen.setImageResource(R.mipmap.aquaculture_blue);
		} else if(property_type.compareTo(Constants.DAM) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.dams);
			else
				imagen.setImageResource(R.mipmap.dams_blue);
		} else if(property_type.compareTo(Constants.EDUCATION) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.education);
			else
				imagen.setImageResource(R.mipmap.education_blue);
		} else if(property_type.compareTo(Constants.HEALT) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.health);
			else
				imagen.setImageResource(R.mipmap.health_blue);
		} else if(property_type.compareTo(Constants.HIDRAULIC) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.hydraulic_infrastructure);
			else
				imagen.setImageResource(R.mipmap.hydraulic_infrastructure_blue);
		} else if(property_type.compareTo(Constants.HISTORICAL) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.historical_and_archaeological);
			else
				imagen.setImageResource(R.mipmap.historical_and_archaeological_blue);
		} else if(property_type.compareTo(Constants.URBAN) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.urban_infraestructure);
			else
				imagen.setImageResource(R.mipmap.urban_infraestructure_blue);
		} else if(property_type.compareTo(Constants.WASTE_DISPOSAL) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.waste_disposal);
			else
				imagen.setImageResource(R.mipmap.waste_disposal_blue);
		} else if(property_type.compareTo(Constants.WAYS) == 0){
			if (finding == 0)
				imagen.setImageResource(R.mipmap.ways);
			else
				imagen.setImageResource(R.mipmap.ways_blue);
		} else {
			imagen.setImageResource(R.mipmap.ic_launcher);
		}

		if (status == 0)
			holder.switchCerrar.setChecked(true);
		else
			holder.switchCerrar.setChecked(false);

		switch (status)
		{
			case 0:
				holder.filaInferiorStatus.setText("Abierto");
			break;
			case 1:
				holder.filaInferiorStatus.setText("Omitido");
			break;
			case 2:
				holder.filaInferiorStatus.setText("Cerrado");
			break;
		}
		return (row);
	}

	public static class FilaAsignacionHolder {
		ImageView imageAsignacion;
		TextView filaSuperior;
		TextView filaInferior;
		TextView filaInferiorStatus;
		//Button botonOmitir;
		Switch switchCerrar;
	}
}
