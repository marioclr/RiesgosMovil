package com.mclr.mini.riesgosmovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.fragmentos.AsignacionesFragment;
import com.mclr.mini.riesgosmovil.fragmentos.HallazgosFragment;
import com.mclr.mini.riesgosmovil.fragmentos.PropertyAdminFragment;
import com.mclr.mini.riesgosmovil.fragmentos.PruebaFragment;
import com.mclr.mini.riesgosmovil.fragmentos.PruebaFragment2;
import com.mclr.mini.riesgosmovil.modelos.pojoUsers;
import com.mclr.mini.riesgosmovil.navdrawer.CustomActionBarDrawerToggle;
import com.mclr.mini.riesgosmovil.navdrawer.DrawerItemClickListener;
import com.mclr.mini.riesgosmovil.navdrawer.NsMenuAdapter;
import com.mclr.mini.riesgosmovil.navdrawer.NsMenuItemModel;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String[] ITEMS =
            {"White", "Red", "Green", "Blue"};
    private static final int[] COLORS =
            {Color.WHITE, 0xffe51c23, 0xff259b24, 0xff5677fc};
    final int TREINTA_SEGUNDOS=10*1000;

    private DrawerLayout mDrawerContainer;
    /* Root content pane in layout */
    private View mMainContent;
    /* Main (left) sliding drawer */
    private ListView mDrawerContent;
    /* Toggle object for ActionBar */
    private CustomActionBarDrawerToggle mDrawerToggle;

    private ActionBar actionbar = null;
    private Toolbar toolbar = null;
    private String[] category=null;
    private String[] menuItems;
    private ListView mDrawerList;
    private ProgressDialog mDialog;


    public Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    private boolean bFirsFragment;

    public String propertyTypeID;
    public String propertyID;
    public String postalCodeID;
    public pojoUsers pojoUsuario;
    private int PropertyPag;
    public Fragment fragmentCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pojoUsuario = (pojoUsers)getIntent().getExtras().getParcelable("myObjectPassed");
        setContentView(R.layout.activity_main);

        //Enable home button actions in the ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerContainer = (DrawerLayout) findViewById(R.id.container_drawer);
        mDrawerContent = (ListView) findViewById(R.id.drawer_main);
        //mMainContent = findViewById(R.id.container_root);

        //Toggle indicator must also be the drawer listener,
        // so we extend it to listen for the events ourselves.
        mDrawerToggle  = new CustomActionBarDrawerToggle(
                this,                 //Host Activity
                mDrawerContainer);

        _initMenu();

//        ListAdapter adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, ITEMS);
//        mDrawerContent.setAdapter(adapter);
//        mDrawerContent.setOnItemClickListener(this);

        //Set the toggle as the drawer's event listener
        mDrawerContainer.addDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectDrawerItem(0, "Hola");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Synchronize the state of the drawer after any instance
        // state has been restored by the framework
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //Update state on any configuration changes
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create the ActionBar actions
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Display the action options based on main drawer state
        boolean isOpen =
                mDrawerContainer.isDrawerVisible(mDrawerContent);
        menu.findItem(R.id.action_delete).setVisible(!isOpen);
        menu.findItem(R.id.action_settings).setVisible(!isOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Let the drawer have a crack at the event first
        // to handle home button events
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            //If this was a drawer toggle, we need to update the
            // options menu, but we have to wait until the next
            // loop iteration for the drawer state to change.
            mDrawerContainer.post(new Runnable() {
                @Override
                public void run() {
                    //Update the options menu
                    supportInvalidateOptionsMenu();
                }
            });
            return true;
        }
        //...Handle other options selections here as normal...
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_delete:
                Intent intentCatalog = new Intent(this, Catalogs.class);
                startActivity(intentCatalog);
                return true;
            case R.id.action_settings:
                iniciarProgreso();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void selectDrawerItem(int position, String sTitle) {

        //Toast.makeText(this, "Seleccion DrawItem: " + position, Toast.LENGTH_LONG).show();


        //MapFragment fragmentMap;
        Bundle args = new Bundle();
        //GoogleMap map;
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (position) {
            case 0: // TITULO: Asignaciones
                fragment = (Fragment) fragmentManager.findFragmentById(R.id.content_frame);
                if (fragment == null)
                    bFirsFragment = true;
                else
                    bFirsFragment = false;
                // update the main content by replacing fragments
                fragment = new AsignacionesFragment();
                args.putInt(AsignacionesFragment.ARG_ASIGNACION_NUMBER, position);
                fragment.setArguments(args);
                if (!bFirsFragment)
                    ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 1: // TITULO: Crear Hallazgos
                fragment = new HallazgosFragment();
                args.putInt(HallazgosFragment.ARG_HALLAZGO_NUMBER, position);
                fragment.setArguments(args);
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 2:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.AQUACULTURE;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 3:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.DAM;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 4:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.EDUCATION;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 5:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.HEALT;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 6:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.HIDRAULIC;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 7:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.HISTORICAL;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 8:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.URBAN;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 9:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.WASTE_DISPOSAL;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 10:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = Constants.WAYS;
                fragment = new PropertyAdminFragment();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 11:
                propertyID = Constants.GENERICO;
                postalCodeID = null;
                propertyTypeID = "";
                fragment = new PruebaFragment2();
                ft.addToBackStack("content_frame");
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                break;
            case 12:
                iniciarProgreso();
            default:
                break;
        }
    }

    private void _initMenu() {
        NsMenuAdapter mAdapter = new NsMenuAdapter(this);

        mAdapter.addHeader(R.string.ns_menu_main_header);
        mAdapter.addHeader(R.string.ns_menu_main_header2);

        menuItems = getResources().getStringArray(
                R.array.ns_menu_items);
        String[] menuItemsIcon = getResources().getStringArray(
                R.array.ns_menu_items_icon);

        int res = 0;
        for (String item : menuItems) {

            int id_title = getResources().getIdentifier(item, "string",
                    this.getPackageName());
            int id_icon = getResources().getIdentifier(menuItemsIcon[res],
                    "mipmap", this.getPackageName());

            NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
            if (res==1) mItem.counter=12; //it is just an example...
            if (res==3) mItem.counter=3; //it is just an example...
            mAdapter.addItem(mItem);
            res++;
        }
        mAdapter.addHeader(R.string.ns_menu_main_header3);
        mAdapter.addHeader(R.string.ns_menu_main_header4);
        //mAdapter.addHeader(R.string.ns_menu_main_header5);

        mDrawerList = (ListView) findViewById(R.id.drawer_main);
        if (mDrawerList != null)
            mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(this, mDrawerList, mDrawerContainer));

    }

    public void selectPropertyItem(String PropertyTypeID, String PropertyID, String PostalCodeID) {
        this.propertyTypeID = PropertyTypeID;
        this.propertyID = PropertyID;
        this.postalCodeID = PostalCodeID;

        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragment = new PropertyAdminFragment();
        ft.addToBackStack("content_frame");
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void setPropertyPag(int p_PropertyPag)
    {
        PropertyPag = p_PropertyPag;
    }

    public int getPropertyPag()
    {
        return PropertyPag;
    }

    public void updatePhotoFragment(String photoPath)
    {
        ((PropertyAdminFragment)fragment).updatePhotoFragment(photoPath);
        //propertyAdminPager.updatePhoto(photoPath);
    }

    public void iniciarProgreso() {
        mDialog= new ProgressDialog(this);
        mDialog.setIcon(R.mipmap.ic_drawer); //TODO
        mDialog.setMessage("Sincronizando información...");
        mDialog.setCancelable(false);
        mDialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mDialog.dismiss();
            }}, TREINTA_SEGUNDOS);
    }
}
