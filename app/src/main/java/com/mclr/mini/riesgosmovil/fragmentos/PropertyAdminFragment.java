package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.R;


public class PropertyAdminFragment extends Fragment implements android.support.v7.app.ActionBar.TabListener{
	private MainActivity myActivity = null;
	public PropertyAdminPagerAdapter propertyAdminPager;
	ViewPager viewPager;

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		myActivity = (MainActivity) activity;
	}

	@Override
	public void onDestroy() {
		Log.d("Mario ", "onDestroyView");
//		Fragment f = getActivity()
//				.getSupportFragmentManager().findFragmentById(R.id.map);
//		if (f != null) {
//			myActivity.getSupportFragmentManager()
//					.beginTransaction().remove(f).commit();
//		}
		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.property_admin, container, false);

		propertyAdminPager = new PropertyAdminPagerAdapter(myActivity.getSupportFragmentManager(), myActivity);
		final ActionBar actionBar = myActivity.getSupportActionBar();
		//actionBar.setDisplayHomeAsUpEnabled(true);
		//actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		viewPager = (ViewPager) rootView.findViewById(R.id.pager);
		viewPager.setAdapter(propertyAdminPager);
		viewPager.setOnPageChangeListener(propertyAdminPager);
		//actionBar.addTab(actionBar.newTab().setText("Hola 1").setTabListener(this));

		for (int i = 0; i < propertyAdminPager.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by the adapter.
			// Also specify this Activity object, which implements the TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(
					actionBar.newTab()
							.setText(propertyAdminPager.getPageTitle(i))
							.setTabListener(this));
		}

		return rootView;
	}

	@Override
	public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

	}

	public void updatePhotoFragment(String photoPath)
	{
		propertyAdminPager.updatePhoto(photoPath);
	}

	@Override
	public void onDestroyView() {
		myActivity.getSupportActionBar().removeAllTabs();
		super.onDestroyView();
	}

}
