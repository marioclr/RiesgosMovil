package com.mclr.mini.riesgosmovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.utilitarios.CatalogItemSpinnerAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Catalogs extends Activity {
	DatabaseHandler db;
	List<CatalogItem> listaCatalogItems;
	List<CatalogItem> listaStatesItems;
	private Spinner spinner;
	private Spinner spinner1;
	private Button botonRespaldo;
	private Button botonQR;
	private Button buttonArchivo;
	private EditText editTextArchivo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalogs);
		db = new DatabaseHandler(this);
		listaCatalogItems = db.getCatalogList("75B17471-ED30-467C-BA2A-8F8D1D988282");
		listaStatesItems = db.getPostalCodesList();
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(new CatalogItemSpinnerAdapter(this, listaCatalogItems));
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				Toast.makeText(adapterView.getContext(), ((CatalogItem) adapterView.getItemAtPosition(position)).getName(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setAdapter(new CatalogItemSpinnerAdapter(this, listaStatesItems));
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				Toast.makeText(adapterView.getContext(), ((CatalogItem) adapterView.getItemAtPosition(position)).getName(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
		botonRespaldo = (Button) findViewById(R.id.button1);
		botonRespaldo.setOnClickListener(new SaveClickListener());
		botonQR = (Button) findViewById(R.id.buttonQR);
		botonQR.setOnClickListener(new QRClickListener());
		buttonArchivo = (Button) findViewById(R.id.buttonArchivo);
		buttonArchivo.setOnClickListener(new ArchivoClickListener());
		editTextArchivo = (EditText) findViewById(R.id.editTextArchivo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.catalogs, menu);
		return true;
	}

	public void BD_backup() throws IOException {

		String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
		final String inFileName = "/data/data/com.jibda.riesgosmovil/databases/prueba";

		File dbFile = new File(inFileName);
		FileInputStream fis = null;
		fis = new FileInputStream(dbFile);
		String directorio = Environment
        		.getExternalStorageDirectory()
        		.getAbsolutePath() + "/BaseDatos";

		File d = new File(directorio);

		if (!d.exists()) {
			d.mkdir();
		}

		String outFileName = directorio + "/prueba_" + timeStamp;
		OutputStream output = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = fis.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}

		output.flush();
		output.close();
		fis.close();
	}

	private class SaveClickListener implements OnClickListener {
//		pojoHistorical pojoHist = new pojoHistorical();
//		ProcessPropertiesActions process = new ProcessPropertiesActions(myActivity);

		@Override
		public void onClick(View v) {

			try
			{
				BD_backup();
				Toast.makeText(v.getContext(), "Backup terminado", Toast.LENGTH_LONG).show();
			}
			catch (Exception ex)
			{
				//Toast.makeText(this, "Error al guardar", Toast.LENGTH_LONG).show();
			}
		}
	}

	private class QRClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//Toast.makeText(v.getContext(), "Error al guardar", Toast.LENGTH_LONG).show();

			try
			{
				Intent intent = new Intent("com.jibda.riesgosmovil.SCAN");
//				switch(v.getId())
//				{
//					case R.id.buttonQR:
//						intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//						break;
//				}
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			}
			catch (Exception ex)
			{
				//Toast.makeText(this, "Error al guardar", Toast.LENGTH_LONG).show();
			}
		}
	}

	private class ArchivoClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			//Toast.makeText(v.getContext(), "Error al guardar", Toast.LENGTH_LONG).show();

		    InputStream is = null;
		    //int i=0;
		    try {
		         is = getAssets().open("importPC.sql");
		         if (is != null) {

		             BufferedReader reader = new BufferedReader(new InputStreamReader(is, "Cp1252"));
		             String line;// = reader.readLine();
		             while ((line = reader.readLine()) != null)
		            	 db.ExecuteInsertaPostalCodes(line);
		         }
		    } catch (Exception ex) {
		        // Muestra log             
		    } finally {
		        if (is != null) {
		            try {
		                is.close();
		            } catch (IOException e) {
		                // Muestra log
		            }                
		        }
		    }
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		if (requestCode == 0)
		{
			if (resultCode == RESULT_OK)
			{
				String qrStr = intent.getStringExtra("SCAN_RESULT");
				Toast.makeText(this, "QR " + qrStr, Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
	            // Si se cancelo la captura.
	        }
			//Toast.makeText(this, "QR " + , Toast.LENGTH_LONG).show();
		}
		//super.onActivityResult(requestCode, resultCode, data);
	}
	
	

}
