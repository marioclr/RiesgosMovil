package com.mclr.mini.riesgosmovil.fragmentos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.database.DatabaseHandler;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.FilaAsignacionAdapter;

/**
 * Created by mini on 07/06/16.
 */
public class AsignacionesFragment extends Fragment {
    private MainActivity myActivity = null;
    public static final String ARG_ASIGNACION_NUMBER = "asignacion_number";
    public static final String ARG_ASIGNACION_POSTAL_CODE = "asignacion_postal_code";
    private DatabaseHandler baseDatos;
    private ListView listViewPropierties;
    private FilaAsignacionAdapter adaptador;
    private String idSelectedItem;
    boolean isOpen=false;
    int indexNavigationItemSelected=0;
    TextView titulo;
    private ActionBar actionbar = null;

    public AsignacionesFragment(){

    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        myActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        final android.app.ActionBar actionBar = getActivity().getSupportActionBar();
        View rootView = inflater.inflate(R.layout.asignaciones, container, false);
        int i = getArguments().getInt(ARG_ASIGNACION_NUMBER);

        myActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionbar = myActivity.getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(myActivity, R.array.spinner_property_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionbar.setListNavigationCallbacks(adapter, new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                String type, desc="";
                indexNavigationItemSelected = itemPosition;
                int num=0;
                switch (indexNavigationItemSelected)
                {
                    case 0:
                        type = "0";
                        desc="";
                        break;
                    case 1:
                        type = Constants.AQUACULTURE;
                        desc = "Acuicolas";
                        break;
                    case 2:
                        type = Constants.DAMS;
                        desc = "Presas";
                        break;
                    case 3:
                        type = Constants.EDUCATION;
                        desc = "Educacion";
                        break;
                    case 4:
                        type = Constants.HEALT;
                        desc = "Salud";
                        break;
                    case 5:
                        type = Constants.HIDRAULIC;
                        desc = "Hidraulica";
                        break;
                    case 6:
                        type = Constants.HISTORICAL;
                        desc = "Historicos";
                        break;
                    case 7:
                        type = Constants.URBAN;
                        desc = "Infraestructura Urbana";
                        break;
                    case 8:
                        type = Constants.WASTE_DISPOSAL;
                        desc = "Disposicion de residuos";
                        break;
                    case 9:
                        type = Constants.WAY_TYPE;
                        desc = "Caminos";
                        break;
                    default:
                        type = "0";
                        break;
                }
                //onActivityCreated(null);
                if (!type.equals("0")){
                    titulo.setText("Lista de Asignaciones: " + desc + " de " + myActivity.pojoUsuario.getUserName());
                } else {
                    titulo.setText("Lista de Asignaciones de " + myActivity.pojoUsuario.getUserName());
                }
                num = recuperarAsignaciones(type);
                if (num == 0){
                    Toast.makeText(myActivity, "No existen asignaciones asignadas", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(myActivity, "Registros: " + num, Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewPropierties = (ListView) getActivity().findViewById(R.id.listView_asignacion);
        registerForContextMenu(listViewPropierties);
        listViewPropierties.setOnItemClickListener(new SeleccionaElemento());
        listViewPropierties.setOnItemLongClickListener(new PresionaElemento());
        //recuperarAsignaciones();
        titulo = (TextView) myActivity.findViewById(R.id.UserFullName_asignacion);
        //Log.d("MapAct", "Mario onActivityCreated");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        Log.d("Asigna", "Mario onCreateContextMenu");
        //MenuInflater inflater = myActivity.getMenuInflater();
        //inflater.inflate(R.menu.opciones_asignacion, menu);
        if (isOpen) {
            menu.add("Cerrar asignación");
            menu.add("Omitir asignación");
        } else {
            menu.add("Abrir asignación");
            menu.add("Omitir asignación");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private int recuperarAsignaciones(String type)
    {
        int num=0;
        try{
            baseDatos = new DatabaseHandler(this.getActivity());
            Cursor cursor = baseDatos.obtenerPropiedades(type);
            num = cursor.getCount();

            String[] from = new String[]{
                    "_id",
                    "Name",
                    "RegisterDate"
            };

            int[] to = new int[]{
                    R.id.imageAsignacion,
                    R.id.textAsigna_Superior,
                    R.id.textAsigna_Inferior
            };
            adaptador = new FilaAsignacionAdapter(this.getActivity(), new AbrirCerrarElemento(), cursor, from, to);
            listViewPropierties.setAdapter(adaptador);
        }catch(Exception e){
            Log.d("Error", "El mensaje de error es: " + e.getMessage());
        }finally{
            baseDatos.cerrar();
        }
        return num;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class SeleccionaElemento implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {

            String property_id = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("_id"));
            String property_type = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("PropertyType"));
            String postalCodeID = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("PostalCodeID"));

            idSelectedItem = property_id;
            myActivity.selectPropertyItem(property_type, property_id, postalCodeID);
        }
    }

    class PresionaElemento implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       int position, long id) {

            String property_id = ((Cursor)parent.getItemAtPosition(position)).getString(((Cursor)parent.getItemAtPosition(position)).getColumnIndex("_id"));
            idSelectedItem = property_id;
            //Toast.makeText(myActivity, property_id, Toast.LENGTH_LONG).show();
            Log.d("Asigna", "Mario onItemLongClick");
            return false;
        }

    }

    class AbrirCerrarElemento implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String property_id = (String) view.getTag();
            idSelectedItem = property_id;
            if ( ((Switch)view).isChecked())
                isOpen = true;
            else
                isOpen = false;

            DialogConfirmaOperacion dialogoConfirma = new DialogConfirmaOperacion();
            dialogoConfirma.show(myActivity.getSupportFragmentManager(), "Confirmacion");
            Toast.makeText(myActivity, "Property " + property_id + " checked " + isOpen, Toast.LENGTH_LONG).show();
        }

//		@Override
//		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//	    	String property_id = (String) buttonView.getTag();
//	    	idSelectedItem = property_id;
//	    	isOpen = isChecked;
//	    	//Toast.makeText(myActivity, item, Toast.LENGTH_LONG).show();
////	    	DialogConfirmaOperacion dialogoConfirma = new DialogConfirmaOperacion();
////	    	dialogoConfirma.show(myActivity.getSupportFragmentManager(), "Confirmacion");
//		}

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Log.d("Asigna", "Mario onContextItemSelected");
        if (item.getTitle() == "Cerrar asignación") {
            Toast.makeText(myActivity, "Cerrar " + idSelectedItem, Toast.LENGTH_LONG).show();
        } else if (item.getTitle() == "Omitir asignación") {
            Toast.makeText(myActivity, "Omitir " + idSelectedItem, Toast.LENGTH_LONG).show();
        } else if (item.getTitle() == "Abrir asignación") {
            Toast.makeText(myActivity, "Abrir " + idSelectedItem, Toast.LENGTH_LONG).show();
        } else {
            return false;
        }
        return true;
//	    switch(item.getItemId()) {
//	    	case R.id.Omitir:
//	        	Toast.makeText(myActivity, "Omitir " + idSelectedItem, Toast.LENGTH_LONG).show();
//	        	return true;
//	    	case R.id.Cerrar:
//	        	Toast.makeText(myActivity, "Cerrar " + idSelectedItem, Toast.LENGTH_LONG).show();
//	        	return true;
//        	default:
//        		return super.onContextItemSelected(item);
//		}
    }

    @SuppressLint("ValidFragment")
    public class DialogConfirmaOperacion extends DialogFragment {
        RadioGroup grupoOmitirCerrar;
        RadioButton botonOmitir;
        RadioButton botonCerrar;
        String sTitulo = "";

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            grupoOmitirCerrar = new RadioGroup(myActivity);
            botonOmitir = new RadioButton(myActivity);
            botonCerrar = new RadioButton(myActivity);
            if (!isOpen)
            {
                sTitulo = "Si esta seguro de cerrar la captura del bien, seleccione la opción adecuada";
                botonOmitir.setText("Omitir");
                botonCerrar.setText("Cerrar");
                botonCerrar.setChecked(true);

                grupoOmitirCerrar.addView(botonOmitir);
                grupoOmitirCerrar.addView(botonCerrar);
            }
            else
                sTitulo = "¿Esta seguro de abrir la captura del bien?";

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(myActivity);

            builder.setMessage(sTitulo)
                    .setTitle("Confirmar operación")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            int iStatus=0;
                            if (!isOpen)
                            {
                                if (botonOmitir.isChecked())
                                    iStatus=1;
                                else
                                    iStatus=2;
                            }
                            //Toast.makeText(myActivity, "Afirmativo " + iStatus, Toast.LENGTH_LONG).show();
                            actualizaStatus(iStatus);
                            //onNavigationItemSelected(indexNavigationItemSelected, 0);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            //Toast.makeText(myActivity, "Negativo " + isOpen, Toast.LENGTH_LONG).show();
                            dialog.cancel();
                            //onNavigationItemSelected(indexNavigationItemSelected, 0);
                        }
                    });

            if (!isOpen)
                builder.setView(grupoOmitirCerrar);
            return builder.create();
        }

    }

    private void actualizaStatus(int iStatus)
    {
        //baseDatos.actualizarOpenClose(idSelectedItem, iStatus);
    }
}
