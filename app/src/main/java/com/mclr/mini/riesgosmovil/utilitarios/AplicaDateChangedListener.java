package com.mclr.mini.riesgosmovil.utilitarios;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;

public class AplicaDateChangedListener implements OnDateChangedListener {
	EditText fechaSeleccionada;

	public AplicaDateChangedListener(EditText p_fechaSeleccionada) {
		fechaSeleccionada = p_fechaSeleccionada;
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		fechaSeleccionada.setText(DateOperations.getDateString(view.getYear(), view.getMonth()+1, view.getDayOfMonth()));
	}

}
