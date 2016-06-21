package com.mclr.mini.riesgosmovil.navdrawer;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mclr.mini.riesgosmovil.MainActivity;


/**
 * Created by mini on 05/06/16.
 */
public class DrawerItemClickListener implements OnItemClickListener {
    private MainActivity activity;
    private ListView mDrawerList;
    private DrawerLayout mDrawer;

    public DrawerItemClickListener(Activity actividad, ListView mDrawerL, DrawerLayout mDrawLayout){
        activity = (MainActivity)actividad;
        mDrawerList = mDrawerL;
        mDrawer = mDrawLayout;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerList.setItemChecked(position, true);
        //String text= "menu click... should be implemented";
        //Toast.makeText(activity.selectDrawerItem(position), text , Toast.LENGTH_LONG).show();
        //You should reset item counter
        NsMenuItemModel seleccion = (NsMenuItemModel) parent.getItemAtPosition(position);
        String sOptionTitle = activity.getString(seleccion.getTitle());
        activity.selectDrawerItem(position, sOptionTitle);
        mDrawer.closeDrawer(mDrawerList);
    }
}
