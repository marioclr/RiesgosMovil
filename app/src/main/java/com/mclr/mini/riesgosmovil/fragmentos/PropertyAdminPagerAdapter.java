package com.mclr.mini.riesgosmovil.fragmentos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;

import com.mclr.mini.riesgosmovil.MainActivity;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

import java.util.HashMap;
import java.util.Map;

public class PropertyAdminPagerAdapter extends FragmentStatePagerAdapter implements OnPageChangeListener {
	private MainActivity myActivity = null;
	private Map<Integer, Fragment> mPageReferenceMap = new HashMap<Integer, Fragment>();
	String propertyTypeID;
	String propertyID;
	String postalCodeID;

	public PropertyAdminPagerAdapter(FragmentManager fm, Activity act) {
		super(fm);
		myActivity = (MainActivity) act;
		this.propertyTypeID = myActivity.propertyTypeID;
		this.propertyID = myActivity.propertyID;
		this.postalCodeID = myActivity.postalCodeID;
	}

	@Override
	public Fragment getItem(int pag) {
		Fragment fragment = null;
		Bundle args = new Bundle();

		if (propertyTypeID.equals(Constants.AQUACULTURE)) {
			switch (pag) {
				case 0:
					fragment = new AquacultureFragment();
                    myActivity.fragmentCP = fragment;
                    break;
				case 1:
					fragment = new AquacultureFragment2();
					break;
				case 2:
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.DAM)) {
			switch (pag) {
				case 0:
					fragment = new DamFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new DamFragment2();
					break;
				case 2:
					fragment = new DamFragment3();
					break;
				case 3:
					fragment = new DamCortinaFragment();
					break;
				case 4:
					fragment = new DamDiqueFragment();
					break;
				case 5:
					//fragment = new DamAtaguiaFragment();
					fragment = new DamExtensionsFragment();
					break;
				case 6:
					fragment = new DamGaleriaFragment();
					break;
				case 7:
					fragment = new DamObraTomaFragment();
					break;
				case 8:
					fragment = new DamOtrosDesfoguesFragment();
					break;
				case 9:
					fragment = new DamUbicacionFragment();
					break;
				case 10:
					fragment = new DamVertederoFragment();
					break;
				case 11:
					fragment = new PhotosFragment();
					break;
				case 12:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.EDUCATION)) {
			switch (pag) {
				case 0:
					fragment = new EducationFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new EducationFragment2();
					break;
				case 2:
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HEALT)) {
			switch (pag) {
				case 0:
					fragment = new HealtFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new HealtFragment2();
					break;
				case 2:
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HIDRAULIC)) {
			switch (pag) {
				case 0:
					fragment = new HydraulicFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new HydraulicFragment2();
					break;
				case 2:
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HISTORICAL)) {
			switch (pag) {
				case 0:
					fragment = new HistoricalFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new HistoricalFragment2();
					break;
				case 2:
					//fragment = new ImagesFragment();
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.URBAN)) {
			switch (pag) {
				case 0:
					fragment = new UrbanFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new UrbanFragment2();
					break;
				case 2:
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.WASTE_DISPOSAL)) {
			switch (pag) {
				case 0:
					fragment = new WasteDisposalFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new WasteDisposalFragment2();
					break;
				case 2:
					fragment = new PhotosFragment();
					break;
				case 3:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.WAYS)) {
			switch (pag) {
				case 0:
					fragment = new WaysFragment();
                    myActivity.fragmentCP = fragment;
					break;
				case 1:
					fragment = new WaysPuenteFragment();
					break;
				case 2:
					fragment = new WaysTunelFragment();
					break;
				case 3:
					fragment = new WaysHundimientoFragment();
					break;
				case 4:
					fragment = new PhotosFragment();
					break;
				case 5:
					fragment = new PruebaFragment();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.LIST_DIALOG)) {
			switch (pag) {
				case 0:
					fragment = new ListsFragment();
					break;
				case 1:
					fragment = new ListsFragment2();
					break;
				case 2:
					fragment = new ListsFragment3();
					break;
			}
		}
		fragment.setArguments(args);
		mPageReferenceMap.put(Integer.valueOf(pag), fragment);
		return fragment;
	}

	@Override
	public int getCount() {
		int iCount = 3;
		if (propertyTypeID.equals(Constants.AQUACULTURE)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.DAM)) {
			iCount = 13;
		} else if (propertyTypeID.equals(Constants.EDUCATION)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.HEALT)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.HIDRAULIC)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.HISTORICAL)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.URBAN)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.WASTE_DISPOSAL)) {
			iCount = 4;
		} else if (propertyTypeID.equals(Constants.WAYS)) {
			iCount = 6;
		} else if (propertyTypeID.equals(Constants.LIST_DIALOG)) {
			iCount = 3;
		}
		return iCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String title = "X";
		if (propertyTypeID.equals(Constants.AQUACULTURE)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Imagenes registradas";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.DAM)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Siniestralidad";
					break;
				case 3:
					title = "Cortinas";
					break;
				case 4:
					title = "Diques";
					break;
				case 5:
					title = "Extensiones";
					break;
				case 6:
					title = "Galerias";
					break;
				case 7:
					title = "Obras de toma";
					break;
				case 8:
					title = "Otros desfogues";
					break;
				case 9:
					title = "Ubicacion inst. hidraulicas";
					break;
				case 10:
					title = "Vertederos";
					break;
				case 11:
					title = "Imagenes registradas";
					break;
				case 12:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.EDUCATION)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Imagenes registradas";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HEALT)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Imagenes registradas";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HIDRAULIC)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Imagenes registradas";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HISTORICAL)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "P.2 Información general";
					break;
				case 2:
					title = "Imagenes";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.URBAN)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Imagenes registradas";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.WASTE_DISPOSAL)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Imagenes registradas";
					break;
				case 3:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.WAYS)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Puentes";
					break;
				case 2:
					title = "Tuneles";
					break;
				case 3:
					title = "Hundimiento";
					break;
				case 4:
					title = "Imagenes registradas";
					break;
				case 5:
					title = "Mapa del bien";
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.LIST_DIALOG)) {
			switch (position) {
				case 0:
					title = "Información general";
					break;
				case 1:
					title = "Información complementaria";
					break;
				case 2:
					title = "Paneles";
					break;
			}
		}
		return title;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//		int fId = getFragment(position).getId();
//		Fragment fragment = (myActivity.getSupportFragmentManager().findFragmentById(fId));   
//		FragmentTransaction ft = myActivity.getSupportFragmentManager().beginTransaction();
//		ft.remove(fragment);
//		ft.commit();
		
//		Fragment fragment = mPageReferenceMap.get(Integer.valueOf(position));
//		myActivity.getSupportFragmentManager().find

		mPageReferenceMap.remove(Integer.valueOf(position));
		myActivity.getSupportFragmentManager().beginTransaction().commitAllowingStateLoss();
		super.destroyItem(container, position, object);
	}

	public Fragment getFragment(int key)
	{
		return mPageReferenceMap.get(key);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int pag) {
		if (propertyTypeID.equals(Constants.AQUACULTURE)) {
			switch (pag) {
				case 0:
					//AquacultureFragment aquaFragment = (AquacultureFragment) getFragment(pag);
					break;
				case 1:
					AquacultureFragment2 aquaFragment2 = (AquacultureFragment2) getFragment(pag);
					aquaFragment2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 3:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.DAM)) {
			switch (pag) {
				case 0:
					//DamFragment damFragment = (DamFragment) getFragment(pag);
					break;
				case 1:
					DamFragment2 damFragment2 = (DamFragment2) getFragment(pag);
					damFragment2.AnalizaID();
					break;
				case 2:
					DamFragment3 damFragment3 = (DamFragment3) getFragment(pag);
					damFragment3.AnalizaID();
					break;
				case 3:
					DamCortinaFragment damCortinaFragment = (DamCortinaFragment) getFragment(pag);
					damCortinaFragment.AnalizaID();
					break;
				case 4:
					DamDiqueFragment damDiqueFragment = (DamDiqueFragment) getFragment(pag);
					damDiqueFragment.AnalizaID();
					break;
				case 5:
					DamExtensionsFragment damExtensionsFragment = (DamExtensionsFragment) getFragment(pag);
					damExtensionsFragment.AnalizaID();
					break;
				case 6:
					DamGaleriaFragment damGaleriaFragment = (DamGaleriaFragment) getFragment(pag);
					damGaleriaFragment.AnalizaID();
					break;
				case 7:
					DamObraTomaFragment damObraTomaFragment = (DamObraTomaFragment) getFragment(pag);
					damObraTomaFragment.AnalizaID();
					break;
				case 8:
					DamOtrosDesfoguesFragment damOtrosDesfoguesFragment = (DamOtrosDesfoguesFragment) getFragment(pag);
					damOtrosDesfoguesFragment.AnalizaID();
					break;
				case 9:
					DamUbicacionFragment damUbicacionFragment = (DamUbicacionFragment) getFragment(pag);
					damUbicacionFragment.AnalizaID();
					break;
				case 10:
					DamVertederoFragment damVertederoFragment = (DamVertederoFragment) getFragment(pag);
					damVertederoFragment.AnalizaID();
					break;
				case 11:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 12:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.EDUCATION)) {
			switch (pag) {
				case 0:
					//EducationFragment educFragment = (EducationFragment) getFragment(pag);
					break;
				case 1:
					EducationFragment2 educFragment2 = (EducationFragment2) getFragment(pag);
					educFragment2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 3:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HEALT)) {
			switch (pag) {
				case 0:
					//HealtFragment healFragment = (HealtFragment) getFragment(pag);
					break;
				case 1:
					HealtFragment2 healFragment2 = (HealtFragment2) getFragment(pag);
					healFragment2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 3:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HIDRAULIC)) {
			switch (pag) {
				case 0:
					//HydraulicFragment hydrFragment = (HydraulicFragment) getFragment(pag);
					break;
				case 1:
					HydraulicFragment2 hydrFragment2 = (HydraulicFragment2) getFragment(pag);
					hydrFragment2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 3:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.HISTORICAL)) {
			switch (pag) {
				case 0:
					HistoricalFragment histFragment = (HistoricalFragment) getFragment(pag);
					break;
				case 1:
					HistoricalFragment2 histFragment2 = (HistoricalFragment2) getFragment(pag);
					histFragment2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 3:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.URBAN)) {
			switch (pag) {
				case 0:
					break;
				case 1:
					UrbanFragment2 urban2 = (UrbanFragment2) getFragment(pag);
					urban2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.WASTE_DISPOSAL)) {
			switch (pag) {
				case 0:
					break;
				case 1:
					WasteDisposalFragment2 waste2 = (WasteDisposalFragment2) getFragment(pag);
					waste2.AnalizaID();
					break;
				case 2:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				default:
					break;
			}
		} else if (propertyTypeID.equals(Constants.WAYS)) {
			switch (pag) {
				case 0:
					WaysFragment wayFragment = (WaysFragment) getFragment(pag);
					break;
				case 1:
					WaysPuenteFragment wayFragment2 = (WaysPuenteFragment) getFragment(pag);
					wayFragment2.AnalizaID();
					break;
				case 2:
					WaysTunelFragment wayFragment3 = (WaysTunelFragment) getFragment(pag);
					wayFragment3.AnalizaID();
					break;
				case 3:
					WaysHundimientoFragment wayFragment4 = (WaysHundimientoFragment) getFragment(pag);
					wayFragment4.AnalizaID();
					break;
				case 4:
					PhotosFragment photFragment = (PhotosFragment) getFragment(pag);
					photFragment.AnalizaID();
					break;
				case 5:
					//MapsFragment mapFragment = (MapsFragment) getFragment(pag);
					break;
				default:
					break;
			}
		}
		myActivity.getSupportActionBar().setSelectedNavigationItem(pag);
		myActivity.setPropertyPag(pag);
	}

	public void updatePhoto(String photoPath)
	{
		Bitmap bm = null;
		bm = decodeSampledBitmapFromUri(photoPath, 320, 220);
		PhotosFragment photFragment = (PhotosFragment) getFragment(myActivity.getPropertyPag());
		photFragment.imagenSeleccionada.setImageBitmap(bm);
	}

	public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
    	Bitmap bm = null;
    	
    	// First decode with inJustDecodeBounds=true to check dimensions
    	final BitmapFactory.Options options = new BitmapFactory.Options();
    	options.inJustDecodeBounds = true;
    	BitmapFactory.decodeFile(path, options);
    	
    	// Calculate inSampleSize
    	options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
    	
    	// Decode bitmap with inSampleSize set
    	options.inJustDecodeBounds = false;
    	bm = BitmapFactory.decodeFile(path, options); 
    	
    	return bm; 	
    }

    public int calculateInSampleSize(
    		
    	BitmapFactory.Options options, int reqWidth, int reqHeight) {
    	// Raw height and width of image
    	final int height = options.outHeight;
    	final int width = options.outWidth;
    	int inSampleSize = 1;
        
    	if (height > reqHeight || width > reqWidth) {
    		if (width > height) {
    			inSampleSize = Math.round((float)height / (float)reqHeight);  	
    		} else {
    			inSampleSize = Math.round((float)width / (float)reqWidth);  	
    		}  	
    	}
    	
    	return inSampleSize;  	
    }
}
