package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.FilaCodigoPostalAdapter;

public class CodigoPostalDialogFragment extends DialogFragment {
	// String filter_CityHall, String filter_Township, String filter_PostalCode
	public static final String ARG_POSTAL_CODE_FILTER_CITY = "postal_code_filter_city";
	public static final String ARG_POSTAL_CODE_FILTER_TOWN = "postal_code_filter_town";
	public static final String ARG_POSTAL_CODE_FILTER_POST = "postal_code_filter_post";
	MainActivity activity;
	Fragment fragment;
	String message;
	String title;
	String propertyType;
	String seleccion;
	private FilaCodigoPostalAdapter adaptador;
	private DatabaseHandler baseDatos;
	private ListView listViewPostalCodes;

	public CodigoPostalDialogFragment() {

	}

	public static CodigoPostalDialogFragment newInstance (String p_message, String p_title, String p_propertyType) {
        CodigoPostalDialogFragment dialog = new CodigoPostalDialogFragment();
        Bundle args = new Bundle();
        args.putString("message", p_message);
        args.putString("title", p_title);
        args.putString("propertyType", p_propertyType);
        dialog.setArguments(args);
        return dialog;
    }

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		int num=0;

		String postalCodeFilter_CityHall = getArguments().getString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_CITY);
		String postalCodeFilter_Township = getArguments().getString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_TOWN);
		String postalCodeFilter_PostalCode = getArguments().getString(CodigoPostalDialogFragment.ARG_POSTAL_CODE_FILTER_POST);

        // Nuevo

        this.message = getArguments().getString("message");
        this.title = getArguments().getString("title");
        this.propertyType = getArguments().getString("propertyType");

        if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
            this.fragment = (AquacultureFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.DAM) == 0){
            this.fragment = (DamFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.EDUCATION) == 0){
            this.fragment = (EducationFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.HEALT) == 0){
            this.fragment = (HealtFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.HIDRAULIC) == 0){
            this.fragment = (HydraulicFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.HISTORICAL) == 0){
            this.fragment = (HistoricalFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.URBAN) == 0){
            this.fragment = (UrbanFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.WASTE_DISPOSAL) == 0){
            this.fragment = (WasteDisposalFragment) activity.fragmentCP;
        } else if(propertyType.compareTo(Constants.WAYS) == 0){
            this.fragment = (WaysFragment) activity.fragmentCP;
        } else {
            this.fragment = null;
        }

        // Fin Nuevo



        AlertDialog.Builder builder =
                new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View PopupLayout = inflater.inflate(R.layout.dialog_postal_code, null);
 
        builder.setView(PopupLayout)
        		.setMessage(message)
        		.setTitle(title)
        		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int id) {
        				dialog.cancel();
        			}
        		});
        listViewPostalCodes = (ListView) PopupLayout.findViewById(R.id.listView_postal_code);
        listViewPostalCodes.setOnItemClickListener(new SeleccionaElemento());
        num = selectPostalCodes(postalCodeFilter_CityHall, postalCodeFilter_Township, postalCodeFilter_PostalCode);
        Log.i("CP", "CP... onCreateDialog ");
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    private int selectPostalCodes(String postalCodeFilter_CityHall, String postalCodeFilter_Township, String postalCodeFilter_PostalCode){
		int num=0;
		try
		{
			baseDatos = new DatabaseHandler(this.getActivity());
			Cursor cursor = baseDatos.selectPostalCodes(postalCodeFilter_CityHall, postalCodeFilter_Township, postalCodeFilter_PostalCode);
			num = cursor.getCount();

			String[] from = new String[]{
					"StateID",
					"Township",
					"CityHall",
					"PostalCode"
			};
			
			int[] to = new int[]{
					R.id.imageEscudoEstado,
					R.id.textTipoColonia,
					R.id.textMunicipioCiudad,
					R.id.textCodigoPostal
			};
			adaptador = new FilaCodigoPostalAdapter(activity, cursor, from, to);
			listViewPostalCodes.setAdapter(adaptador);
		} catch(Exception e) {
			Log.d("Error", "El mensaje de error es: " + e.getMessage());
		}finally{
			baseDatos.cerrar();
		}
		return num;
	}

	class SeleccionaElemento implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {

			String state="";
			String postalCodeID = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("_id"));
			String colonia = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("Township"));
			String municipio = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("CityHall"));
			String stateID = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("StateID"));
			String postalCode = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("PostalCode"));

			if(stateID.compareTo(Constants.AGUASCALIENTES) == 0){
				state = "AGUASCALIENTES";
			} else if(stateID.compareTo(Constants.BAJA_CALIFORNIA) == 0){
				state = "BAJA_CALIFORNIA";
			} else if(stateID.compareTo(Constants.BAJA_CALIFORNIA_SUR) == 0){
				state = "BAJA_CALIFORNIA_SUR";
			} else if(stateID.compareTo(Constants.CAMPECHE) == 0){
				state = "CAMPECHE";
			} else if(stateID.compareTo(Constants.CHIAPAS) == 0){
				state = "CHIAPAS";
			} else if(stateID.compareTo(Constants.CHIHUAHUA) == 0){
				state = "CHIHUAHUA";
			} else if(stateID.compareTo(Constants.COAHUILA) == 0){
				state = "COAHUILA";
			} else if(stateID.compareTo(Constants.COLIMA) == 0){
				state = "COLIMA";
			} else if(stateID.compareTo(Constants.DISTRITO_FEDERAL) == 0){
				state = "DISTRITO_FEDERAL";
			} else if(stateID.compareTo(Constants.DURANGO) == 0){
				state = "DURANGO";
			} else if(stateID.compareTo(Constants.ESTADO_DE_MEXICO) == 0){
				state = "ESTADO_DE_MEXICO";
			} else if(stateID.compareTo(Constants.GUANAJUATO) == 0){
				state = "GUANAJUATO";
			} else if(stateID.compareTo(Constants.GUERRERO) == 0){
				state = "GUERRERO";
			} else if(stateID.compareTo(Constants.HIDALGO) == 0){
				state = "HIDALGO";
			} else if(stateID.compareTo(Constants.JALISCO) == 0){
				state = "JALISCO";
			} else if(stateID.compareTo(Constants.MICHOACAN) == 0){
				state = "MICHOACAN";
			} else if(stateID.compareTo(Constants.MORELOS) == 0){
				state = "MORELOS";
			} else if(stateID.compareTo(Constants.NAYARIT) == 0){
				state = "NAYARIT";
			} else if(stateID.compareTo(Constants.NUEVO_LEON) == 0){
				state = "NUEVO_LEON";
			} else if(stateID.compareTo(Constants.OAXACA) == 0){
				state = "OAXACA";
			} else if(stateID.compareTo(Constants.PUEBLA) == 0){
				state = "PUEBLA";
			} else if(stateID.compareTo(Constants.QUERETARO) == 0){
				state = "QUERETARO";
			} else if(stateID.compareTo(Constants.QUINTANA_ROO) == 0){
				state = "QUINTANA_ROO";
			} else if(stateID.compareTo(Constants.SAN_LUIS_POTOSI) == 0){
				state = "SAN_LUIS_POTOSI";
			} else if(stateID.compareTo(Constants.SINALOA) == 0){
				state = "SINALOA";
			} else if(stateID.compareTo(Constants.SONORA) == 0){
				state = "SONORA";
			} else if(stateID.compareTo(Constants.TABASCO) == 0){
				state = "TABASCO";
			} else if(stateID.compareTo(Constants.TAMAULIPAS) == 0){
				state = "TAMAULIPAS";
			} else if(stateID.compareTo(Constants.TLAXCALA) == 0){
				state = "TLAXCALA";
			} else if(stateID.compareTo(Constants.VERACRUZ) == 0){
				state = "VERACRUZ";
			} else if(stateID.compareTo(Constants.YUCATAN) == 0){
				state = "YUCATAN";
			} else if(stateID.compareTo(Constants.ZACATECAS) == 0){
				state = "ZACATECAS";
			}

			//((HistoricalFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			//myActivity.selectPropertyItem(property_type, property_id, postalCodeID);
			if(propertyType.compareTo(Constants.AQUACULTURE) == 0){
				((AquacultureFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.DAM) == 0){
				((DamFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.EDUCATION) == 0){
				((EducationFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.HEALT) == 0){
				((HealtFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.HIDRAULIC) == 0){
				((HydraulicFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.HISTORICAL) == 0){
				((HistoricalFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.URBAN) == 0){
				((UrbanFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.WASTE_DISPOSAL) == 0){
				((WasteDisposalFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else if(propertyType.compareTo(Constants.WAYS) == 0){
				((WaysFragment) fragment).MuestraSeleccion(colonia, municipio, state, postalCode, postalCodeID);
			} else {
				return;
			}
		}
	}

	public String getSeleccion(){
		return seleccion;
	}
}
