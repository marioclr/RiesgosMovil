package com.mclr.mini.riesgosmovil.navdrawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mclr.mini.riesgosmovil.R;

/**
 * Created by mini on 07/06/16.
 */
public class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {
    AppCompatActivity actividad;
    DrawerLayout drawer;
    // Idea: Crear parametro que indique el titulo de la opción seleccionada para
    // pintarlo al cerrar el drawer

    public CustomActionBarDrawerToggle(AppCompatActivity mActivity, DrawerLayout mDrawerLayout){
        super(
                mActivity,
                mDrawerLayout,
                R.string.ns_menu_open,
                R.string.ns_menu_close);
        actividad = mActivity;
        drawer = mDrawerLayout;
    }

    @Override
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        actividad.getSupportActionBar().setTitle(actividad.getString(R.string.ns_menu_close));
        actividad.supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        actividad.getSupportActionBar().setTitle(actividad.getString(R.string.ns_menu_open));
        actividad.supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        super.onDrawerStateChanged(newState);
        //Update the options menu
        actividad.supportInvalidateOptionsMenu();
    }
}
