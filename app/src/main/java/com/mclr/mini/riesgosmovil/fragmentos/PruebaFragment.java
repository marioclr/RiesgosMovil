package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

import java.util.ArrayList;

/**
 * Created by mini on 08/06/16.
 */
public class PruebaFragment extends Fragment {
    private MainActivity myActivity = null;
    private ActionBar actionbar = null;
    private final LatLng JIBDA = new LatLng(19.361326, -99.177437);
    String propertyTypeID;

    MapView mapView;
    GoogleMap map;
    CameraPosition.Builder mBuilder;
    ArrayList<MarkerOptions> mMarkers = new ArrayList<MarkerOptions>();
    LatLngBounds.Builder builder = new LatLngBounds.Builder();
    MarkerOptions unMarker;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        myActivity = (MainActivity) activity;
        propertyTypeID = myActivity.propertyTypeID;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recuperarMarkers(myActivity.propertyTypeID);
        //map.stopAnimation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.some_layout, container, false);

        actionbar = myActivity.getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        //map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        // Updates the location and zoom of the MapView
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
//        map.animateCamera(cameraUpdate);

//        map.addMarker(new MarkerOptions()
//                .position(JIBDA)
//                .title("JIBDA")
//                .snippet("Jibda & Asociados S.A. de C.V.")
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
//                .anchor(0.5f, 0.5f)
//        );
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(JIBDA, 15));

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public int recuperarMarkers(String type) {
        int count=0;
        map.clear();
        mMarkers.clear();
        builder = new LatLngBounds.Builder();
        switch (type)
        {
            case "0":
                muestraAqua();
                muestraDam();
                muestraEdu();
                muestraHealth();
                muestraHydr();
                muestraHist();
                muestraUrb();
                muestraWay();
//                map.addMarker(new MarkerOptions()
//                        .position(JIBDA)
//                        .title("JIBDA")
//                        .snippet("Jibda & Asociados S.A. de C.V.")
//                        .icon(BitmapDescriptorFactory
//                                .fromResource(R.mipmap.ic_launcher))
//                        .anchor(0.5f, 0.5f));
                count=15;
                break;
            case Constants.AQUACULTURE:
                muestraAqua();
                count=3;
                break;
            case Constants.DAM:
                muestraDam();
                count=4;
                break;
            case Constants.EDUCATION:
                muestraEdu();
                count=5;
                break;
            case Constants.HEALT:
                muestraHealth();
                count=6;
                break;
            case Constants.HIDRAULIC:
                muestraHydr();
                count=7;
                break;
            case Constants.HISTORICAL:
                muestraHist();
                count=8;
                break;
            case Constants.URBAN:
                muestraUrb();
                count=9;
                break;
            case Constants.WASTE_DISPOSAL:
                count=0;
                break;
            case Constants.WAYS:
                muestraWay();
                count=10;
                break;
            default:
                break;
        }
        for (MarkerOptions marker : mMarkers) {
            builder.include(marker.getPosition());
        }
        if (mMarkers.size() > 0) {
            LatLngBounds bounds = builder.build();
            int padding = 10;
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            //map.animateCamera(cu);
            try{
                cu = CameraUpdateFactory.newLatLngBounds(bounds,10);
                // This line will cause the exception first times
                // when map is still not "inflated"
                map.animateCamera(cu);
                System.out.println("Set with padding");
            } catch(IllegalStateException e) {
                e.printStackTrace();
                cu = CameraUpdateFactory.newLatLngBounds(bounds,500,500,0);
                map.animateCamera(cu);
                System.out.println("Set with wh");
            }
        } else {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(JIBDA, 5));
            //map.animateCamera();
        }
        return count;
    }

    private void muestraAqua(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.36205471406648, -99.19580474495888))
                .title("Aqua 1")
                .snippet("Bien acuicola 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.aqua_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.36731808947483, -99.18348804116249))
                .title("Aqua 2")
                .snippet("Bien acuicola 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.aqua_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.37079992134221, -99.17880993336439))
                .title("Aqua 3")
                .snippet("Bien acuicola 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.aqua_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }

    private void muestraDam(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.366670298595352, -99.17760830372572))
                .title("Presa 1")
                .snippet("Bien presa 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dams_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.359584601483018, -99.16327457875013))
                .title("Presa 2")
                .snippet("Bien presa 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dams_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.35962509024806, -99.18829455971718))
                .title("Presa 3")
                .snippet("Bien presa 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dams_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.359584601483018, -99.18829455971718))
                .title("Presa 4")
                .snippet("Bien presa 4")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dams_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }

    private void muestraEdu(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.356669383981256, -99.1873075067997))
                .title("Educación 1")
                .snippet("Bien educación 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.educ_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.370314088802992, -99.17263012379408))
                .title("Educación 2")
                .snippet("Bien educación 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.educ_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }

    private void muestraHealth(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.358977268800984, -99.18301563709974))
                .title("Salud 1")
                .snippet("Bien salud 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.heal_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.360151443278795, -99.17177181690931))
                .title("Salud 2")
                .snippet("Bien salud 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.heal_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.351770081645597, -99.18271522969007))
                .title("Salud 3")
                .snippet("Bien salud 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.heal_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }

    private void muestraHydr(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.35723623591009, -99.177479557693))
                .title("Hidraulica 1")
                .snippet("Bien hidraulica 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hidr_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.356385957278015, -99.17391758412123))
                .title("Hidraulica 2")
                .snippet("Bien hidraulica 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hidr_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }

    private void muestraHist(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.3665893245544, -99.16825275868177))
                .title("Historica 1")
                .snippet("Bien historica 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hist_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.35780308586871, -99.19271484017372))
                .title("Historica 2")
                .snippet("Bien historica 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hist_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.35926069100075, -99.15537815541029))
                .title("Historica 3")
                .snippet("Bien historica 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hist_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }

    private void muestraUrb(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.351770081645597, -99.1672657057643))
                .title("Urbana 1")
                .snippet("Bien urbana 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.urba_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.36800636446342, -99.15906887501478))
                .title("Urbana 2")
                .snippet("Bien urbana 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.urba_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

        unMarker = new MarkerOptions()
                .position(new LatLng(19.3555761639834, -99.16224461048841))
                .title("Urbana 3")
                .snippet("Bien urbana 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.urba_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);

    }
    private void muestraWay(){
        unMarker = new MarkerOptions()
                .position(new LatLng(19.357114767805474, -99.16928272694349))
                .title("Camino 1")
                .snippet("Bien camino 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ways_marker))
                .anchor(0.5f, 0.5f);
        map.addMarker(unMarker);
        mMarkers.add(unMarker);
    }
}
