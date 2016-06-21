package com.mclr.mini.riesgosmovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.VWProperties;
import com.mclr.mini.riesgosmovil.modelos.pojoUsers;

public class LoginActivity extends Activity {
	ImageButton botonIngresar;
	ImageButton botonCancelar;
	pojoUsers usuario;
	VWProperties propierty;
	private DatabaseHandler baseDatos = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dialog_login);
		botonIngresar = (ImageButton) findViewById(R.id.butQR);
		botonIngresar.setOnClickListener(new QRClickListener(this));
		botonCancelar = (ImageButton) findViewById(R.id.buttonCancelar_login);
		botonCancelar.setOnClickListener(new OutClickButton());
		//baseDatos.insertaUsuario();
		propierty = new VWProperties(this);
		propierty.checkDB();	

		super.onCreate(savedInstanceState);
	}

	private class QRClickListener implements OnClickListener {
        private Activity mActivity;

        public QRClickListener(Activity myActivity) {
            mActivity = myActivity;
        }

        @Override
		public void onClick(View v) {
            String qrStr = "269E6823-F600-4B8C-866C-80CF47FCE591";
            //Toast.makeText(this, "QR " + qrStr, Toast.LENGTH_LONG).show();
            usuario = propierty.getUserDetail(qrStr);
            if (usuario == null) {
                finish();
            }
            else {
                Intent intentMain = new Intent(mActivity, MainActivity.class);
                intentMain.putExtra("myObjectPassed", usuario);
                startActivity(intentMain);
				//Toast.makeText(mActivity, "QR " + usuario.getUserName(), Toast.LENGTH_LONG).show();
            }
		}
	}

	private class OutClickButton implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			((LoginActivity)v.getContext()).finish();
		}
		
	}
}
