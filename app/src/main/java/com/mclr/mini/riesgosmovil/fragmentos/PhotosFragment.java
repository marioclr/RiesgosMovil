package com.mclr.mini.riesgosmovil.fragmentos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.utilitarios.DialogAlert;
import com.mclr.mini.riesgosmovil.utilitarios.GalleryHorizontalLayout;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PhotosFragment extends Fragment {
	//private PropertyAdmin myActivity = null;
	private MainActivity myActivity = null;
	private GalleryHorizontalLayout mGalleryHorizontalLayout;
	public ImageView imagenSeleccionada;
	public static final String ARG_HISTORICAL_NUMBER = "historical_number";
	public static final String ARG_HISTORICAL_POSTAL_CODE = "historical_postal_code";
	
	private Button camera_button;
	String propertyID;
	String postalCode;
	
	String phat;
	int photoCount;

	@Override
	public void onAttach(Context activity) {
		Log.i("Mario", "Mario: Dummy " + "onAttach");
		myActivity = (MainActivity) activity;
		propertyID = myActivity.propertyID;
		postalCode = myActivity.postalCodeID;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.photos, container, false);
		//myActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		//propertyID = getArguments().getString(ARG_HISTORICAL_NUMBER);
		//postalCode = getArguments().getString(ARG_HISTORICAL_POSTAL_CODE);
        verifyCameraPermissions(myActivity);

        return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i("Mario", "Mario: Dummy " + "onActivityCreated");
		phat = "";
		photoCount = 0;
		
		super.onActivityCreated(savedInstanceState);
		mGalleryHorizontalLayout = (GalleryHorizontalLayout) myActivity.findViewById(R.id.mygallery);
		camera_button = (Button) myActivity.findViewById(R.id.camera_button);
		camera_button.setOnClickListener(new CameraClickListener());

		imagenSeleccionada = (ImageView) myActivity.findViewById(R.id.photo_seleccionada);
        String ExternalStorageDirectoryPath = Environment
        		.getExternalStorageDirectory()
        		.getAbsolutePath();

        String targetPath = ExternalStorageDirectoryPath + "/ImagenesRiesgos/";
        targetPath = targetPath + propertyID + "/";
        File basePath = new File(targetPath);
        boolean b = false;
        if (!basePath.exists()) {
            b = basePath.mkdirs();
        }

        String nomolestar;
        //Toast.makeText(myActivity, targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);

//        try {
//            String []fileList= myActivity.getAssets().list("img");
//            for (String file: fileList)
//            {
//                copia (file, targetPath);
//                //File f = new File("//assets/" + file);
//                //nomolestar = f.getAbsolutePath();
//                //mGalleryHorizontalLayout.add(nomolestar);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (targetDirector.length() > 0)
        {
	        File[] files = targetDirector.listFiles();
	        for (File file : files)
	        {
	        	mGalleryHorizontalLayout.add(file.getAbsolutePath());
	        }
	        photoCount = files.length;
        }
        photoCount = photoCount + 1;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
//    		Toast toast1 =
//    	            Toast.makeText(myActivity,
//    	                    "Hola soy una peticion nueva", Toast.LENGTH_SHORT);
//    	 
//    	        toast1.show();
    		
    		if (data != null) {
    			
    			if (data.hasExtra("data")) { 
    				ImageView iv = (ImageView)myActivity.findViewById(R.id.photo_seleccionada);
    				iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
    			}
    			
    		} else {
    			
    			ImageView iv = (ImageView)myActivity.findViewById(R.id.photo_seleccionada);
    			iv.setImageBitmap(BitmapFactory.decodeFile(phat));
    			
    			new MediaScannerConnectionClient() {
    				private MediaScannerConnection msc = null; {
    					msc = new MediaScannerConnection(myActivity, this); msc.connect();
    				}
    				public void onMediaScannerConnected() { 
    					msc.scanFile(phat, null);
    				}
    				public void onScanCompleted(String path, Uri uri) { 
    					msc.disconnect();
    				}
    			};
    		}
    	} else if (requestCode == 2){
    		Uri selectedImage = data.getData();
    		InputStream is;
    		try {
    			is = myActivity.getContentResolver().openInputStream(selectedImage);
    	    	BufferedInputStream bis = new BufferedInputStream(is);
    	    	Bitmap bitmap = BitmapFactory.decodeStream(bis);            
    	    	ImageView iv = (ImageView)myActivity.findViewById(R.id.photo_seleccionada);
    	    	iv.setImageBitmap(bitmap);						
    		} catch (FileNotFoundException e) {}
    	}
	}

	private class CameraClickListener implements OnClickListener {
		String name = "";
		@Override
		public void onClick(View v) {
			Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			name = propertyID + photoCount;
			String ExternalStorageDirectoryPath = Environment
	        		.getExternalStorageDirectory()
	        		.getAbsolutePath();
			String targetPath = ExternalStorageDirectoryPath + "/ImagenesRiesgos/" + propertyID + "/";
            File file = new File(targetPath);
            boolean b = false;
            if (!file.exists()) {
                b = file.mkdirs();
            }
			//phat = Environment.getExternalStorageDirectory()+"/ImagenesRiesgos"+ "/"+ name + ".png";
			phat = targetPath + photoCount + ".jpg";
			Uri output = Uri.fromFile(new File(phat));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
			startActivityForResult(intent, 1);
		}
	}

	public void AnalizaID()
	{
		if (myActivity.propertyID != null)
		{
			Toast.makeText(myActivity, "Tienes un ID, puedes continuar...", Toast.LENGTH_LONG).show();
		}
		else
		{
			camera_button.setEnabled(false);
			//Toast.makeText(myActivity, "Para realizar fotos del bien debe guardar la información general del bien.", Toast.LENGTH_LONG).show();
			DialogAlert alerta = new DialogAlert(myActivity, "Para realizar fotos del bien debe guardar la información general del bien.", "Alerta");
			alerta.show(myActivity.getSupportFragmentManager(), "Validacion");
		}
	}

	public static void verifyCameraPermissions(Activity activity) {
		// Verificar si se cuenta con permisos de escritura
		int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			// Si no se cuenta con permiso, solicitarlo al usuario
			ActivityCompat.requestPermissions(activity,
					new String[]{Manifest.permission.CAMERA},
					1);
		} else {
            int i = 0;
        }
	}

    void copia (String file, String toDir) {
        try
        {
            InputStream stream = myActivity.getAssets().open("img/"+file);
            OutputStream output = new BufferedOutputStream(new FileOutputStream(toDir + file, false));

            byte data[] = new byte[1024];
            int count;

            while((count = stream.read(data)) != -1)
            {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            stream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
