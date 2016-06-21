package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;
import com.mclr.mini.riesgosmovil.utilitarios.ListaAdapter;
import com.mclr.mini.riesgosmovil.utilitarios.ListaDatos;

import java.util.ArrayList;

/**
 * Created by mini on 07/06/16.
 */
public class HallazgosFragment extends Fragment {
    private MainActivity myActivity = null;
    public static final String ARG_HALLAZGO_NUMBER = "hallazgo_number";
    private ListView lista;
    private String[] mPropertyTitles;
    private String[] mPropertyDesc;
    private ArrayList<ListaDatos> propertyTypes;

    public HallazgosFragment() {

    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        myActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hallazgos, container, false);
        mPropertyTitles = getResources().getStringArray(R.array.property_types);
        mPropertyDesc = getResources().getStringArray(R.array.property_types_description);

        propertyTypes = new ArrayList<ListaDatos>();
        propertyTypes.add(new ListaDatos(R.mipmap.aquaculture_blue, mPropertyTitles[0], mPropertyDesc[0], Constants.AQUACULTURE));
        propertyTypes.add(new ListaDatos(R.mipmap.dams_blue, mPropertyTitles[1], mPropertyDesc[1], Constants.DAM));
        propertyTypes.add(new ListaDatos(R.mipmap.education_blue, mPropertyTitles[2], mPropertyDesc[2], Constants.EDUCATION));
        propertyTypes.add(new ListaDatos(R.mipmap.health_blue, mPropertyTitles[3], mPropertyDesc[3], Constants.HEALT));
        propertyTypes.add(new ListaDatos(R.mipmap.hydraulic_infrastructure_blue, mPropertyTitles[4], mPropertyDesc[4], Constants.HIDRAULIC));
        propertyTypes.add(new ListaDatos(R.mipmap.historical_and_archaeological_blue, mPropertyTitles[5], mPropertyDesc[5], Constants.HISTORICAL));
        propertyTypes.add(new ListaDatos(R.mipmap.urban_infraestructure_blue, mPropertyTitles[6], mPropertyDesc[6], Constants.URBAN));
        propertyTypes.add(new ListaDatos(R.mipmap.waste_disposal_blue, mPropertyTitles[7], mPropertyDesc[7], Constants.WASTE_DISPOSAL));
        propertyTypes.add(new ListaDatos(R.mipmap.ways_blue, mPropertyTitles[8], mPropertyDesc[8], Constants.WAYS));

        int i = getArguments().getInt(ARG_HALLAZGO_NUMBER);
        String seleccion = getResources().getStringArray(R.array.menu_array)[i];
        myActivity.setTitle(seleccion);
        myActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myActivity.getSupportActionBar().setHomeButtonEnabled(true);
        myActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lista = (ListView) getActivity().findViewById(R.id.propertyTypes_hallazgo);
        lista.setAdapter(new ListaAdapter(getActivity(), R.layout.fila_hallazgo, propertyTypes){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textHallazgo_Superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((ListaDatos) entrada).getTitulo());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textHallazgo_Inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((ListaDatos) entrada).getDescripcion());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageHallazgo);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((ListaDatos) entrada).getIdImagen());
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListaDatos selection = (ListaDatos) parent.getItemAtPosition(position);
                String propertyTypeIDselection = selection.getPropertyTypeID();
                myActivity.selectPropertyItem(propertyTypeIDselection, "Nuevo", "Nuevo");
            }

        });
    }
}
