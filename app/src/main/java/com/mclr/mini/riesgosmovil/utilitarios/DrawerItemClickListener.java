package com.mclr.mini.riesgosmovil.utilitarios;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DrawerItemClickListener implements OnItemClickListener {
	private ListView mDrawerList;
	private DrawerLayout mDrawer;

	public DrawerItemClickListener(ListView mDrawerL, DrawerLayout mDrawLayout){
		mDrawerList = mDrawerL;
		mDrawer = mDrawLayout;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerList.setItemChecked(position, true);
        //String text= "menu click... should be implemented";
        //Toast.makeText(MainActivity.this, text , Toast.LENGTH_LONG).show();
        //You should reset item counter 
        mDrawer.closeDrawer(mDrawerList);
	}

}
