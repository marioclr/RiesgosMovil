package com.mclr.mini.riesgosmovil.utilitarios;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mclr.mini.riesgosmovil.R;

public class FilaCodigoPostalAdapter extends SimpleCursorAdapter {
	private Cursor cursor;
	
	public FilaCodigoPostalAdapter(Context contexto, Cursor cursor, String[] from, int[] to) {
		super(contexto, R.layout.fila_codigo_postal, cursor, from, to);
		this.cursor = cursor;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = super.getView(position, convertView, parent);

		// Referencias al xml fila_persona.xml.
		ImageView imagen = (ImageView) row.findViewById(R.id.imageEscudoEstado);

		// Se obtiene la ruta de la imagen.
		String property_type = cursor.getString(cursor.getColumnIndex("StateID"));

		if(property_type.compareTo(Constants.AGUASCALIENTES) == 0){
			imagen.setImageResource(R.drawable.aguascalientes);
		} else if(property_type.compareTo(Constants.BAJA_CALIFORNIA) == 0){
			imagen.setImageResource(R.drawable.baja_california);
		} else if(property_type.compareTo(Constants.BAJA_CALIFORNIA_SUR) == 0){
			imagen.setImageResource(R.drawable.baja_california_sur);
		} else if(property_type.compareTo(Constants.CAMPECHE) == 0){
			imagen.setImageResource(R.drawable.campeche);
		} else if(property_type.compareTo(Constants.CHIAPAS) == 0){
			imagen.setImageResource(R.drawable.chiapas);
		} else if(property_type.compareTo(Constants.CHIHUAHUA) == 0){
			imagen.setImageResource(R.drawable.chihuahua);
		} else if(property_type.compareTo(Constants.COAHUILA) == 0){
			imagen.setImageResource(R.drawable.coahuila);
		} else if(property_type.compareTo(Constants.COLIMA) == 0){
			imagen.setImageResource(R.drawable.colima);
		} else if(property_type.compareTo(Constants.DISTRITO_FEDERAL) == 0){
			imagen.setImageResource(R.drawable.distrito_federal);
		} else if(property_type.compareTo(Constants.DURANGO) == 0){
			imagen.setImageResource(R.drawable.durango);
		} else if(property_type.compareTo(Constants.ESTADO_DE_MEXICO) == 0){
			imagen.setImageResource(R.drawable.estado_de_mexico);
		} else if(property_type.compareTo(Constants.GUANAJUATO) == 0){
			imagen.setImageResource(R.drawable.guanajuato);
		} else if(property_type.compareTo(Constants.GUERRERO) == 0){
			imagen.setImageResource(R.drawable.guerrero);
		} else if(property_type.compareTo(Constants.HIDALGO) == 0){
			imagen.setImageResource(R.drawable.hidalgo);
		} else if(property_type.compareTo(Constants.JALISCO) == 0){
			imagen.setImageResource(R.drawable.jalisco);
		} else if(property_type.compareTo(Constants.MICHOACAN) == 0){
			imagen.setImageResource(R.drawable.michoacan);
		} else if(property_type.compareTo(Constants.MORELOS) == 0){
			imagen.setImageResource(R.drawable.morelos);
		} else if(property_type.compareTo(Constants.NAYARIT) == 0){
			imagen.setImageResource(R.drawable.nayarit);
		} else if(property_type.compareTo(Constants.NUEVO_LEON) == 0){
			imagen.setImageResource(R.drawable.nuevo_leon);
		} else if(property_type.compareTo(Constants.OAXACA) == 0){
			imagen.setImageResource(R.drawable.oaxaca);
		} else if(property_type.compareTo(Constants.PUEBLA) == 0){
			imagen.setImageResource(R.drawable.puebla);
		} else if(property_type.compareTo(Constants.QUERETARO) == 0){
			imagen.setImageResource(R.drawable.queretaro);
		} else if(property_type.compareTo(Constants.QUINTANA_ROO) == 0){
			imagen.setImageResource(R.drawable.quintana_roo);
		} else if(property_type.compareTo(Constants.SAN_LUIS_POTOSI) == 0){
			imagen.setImageResource(R.drawable.san_luis_potosi);
		} else if(property_type.compareTo(Constants.SINALOA) == 0){
			imagen.setImageResource(R.drawable.sinaloa);
		} else if(property_type.compareTo(Constants.SONORA) == 0){
			imagen.setImageResource(R.drawable.sonora);
		} else if(property_type.compareTo(Constants.TABASCO) == 0){
			imagen.setImageResource(R.drawable.tabasco);
		} else if(property_type.compareTo(Constants.TAMAULIPAS) == 0){
			imagen.setImageResource(R.drawable.tamaulipas);
		} else if(property_type.compareTo(Constants.TLAXCALA) == 0){
			imagen.setImageResource(R.drawable.tlaxcala);
		} else if(property_type.compareTo(Constants.VERACRUZ) == 0){
			imagen.setImageResource(R.drawable.veracruz);
		} else if(property_type.compareTo(Constants.YUCATAN) == 0){
			imagen.setImageResource(R.drawable.yucatan);
		} else if(property_type.compareTo(Constants.ZACATECAS) == 0){
			imagen.setImageResource(R.drawable.zacatecas);
		} else {
			imagen.setImageResource(R.mipmap.ic_launcher);
		}
		return (row);
	}
	

}
