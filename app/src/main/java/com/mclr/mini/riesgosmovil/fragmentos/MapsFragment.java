package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;

public class MapsFragment extends Fragment implements OnMapClickListener {
    //private PropertyAdmin myActivity = null;
    private MainActivity myActivity = null;
    private final LatLng JIBDA = new LatLng(19.361326,-99.177437);
    private SupportMapFragment mapFragment;
    private GoogleMap mapa;

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d("MapAct", "Mario onDestroyView");
		Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.some_layout, container, false);
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

		//myActivity.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

//	    mapa = ((SupportMapFragment) myActivity.getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//        //mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(JIBDA, 15));
//		//mapa.setMyLocationEnabled(true);
//		mapa.getUiSettings().setZoomControlsEnabled(false);
//		mapa.getUiSettings().setCompassEnabled(true);
//		mapa.addMarker(new MarkerOptions()
//			.position(JIBDA)
//			.title("JIBDA")
//			.snippet("Jibda & Asociados S.A. de C.V.")
//			.icon(BitmapDescriptorFactory
//			.fromResource(R.mipmap.ic_launcher))
//			.anchor(0.5f, 0.5f));
		//mapa.setOnMapClickListener(myActivity);
	}

	public void moveCamera(View view) {
	      mapa.moveCamera(CameraUpdateFactory.newLatLng(JIBDA));
	}

	public void animateCamera(View view) {
	   if (mapa.getMyLocation() != null)
	      mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(
	         new LatLng( mapa.getMyLocation().getLatitude(), mapa.getMyLocation().getLongitude()), 15));
	}

	public void addMarker(View view) {
	   mapa.addMarker(new MarkerOptions().position(
	        new LatLng(mapa.getCameraPosition().target.latitude,
	   mapa.getCameraPosition().target.longitude)));
	}

	@Override
	public void onMapClick(LatLng puntoPulsado) {
	      mapa.addMarker(new MarkerOptions().position(puntoPulsado).
	    	         icon(BitmapDescriptorFactory
	    	            .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
	}

}
