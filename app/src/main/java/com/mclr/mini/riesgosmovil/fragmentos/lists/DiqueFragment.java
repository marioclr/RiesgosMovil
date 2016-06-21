package com.mclr.mini.riesgosmovil.fragmentos.lists;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mclr.mini.riesgosmovil.R;

public class DiqueFragment extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dique, container, false);
		return rootView;
	}

//	@Override
//	public void onDestroyView() {
//		Fragment fragment = (getFragmentManager().findFragmentById(R.id.fragmentDique));   
//		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//		ft.remove(fragment);
//		ft.commit();
//		super.onDestroyView();
//	}

}
