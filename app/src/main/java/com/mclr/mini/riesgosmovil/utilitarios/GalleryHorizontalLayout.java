package com.mclr.mini.riesgosmovil.utilitarios;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mclr.mini.riesgosmovil.MainActivity;

import java.util.ArrayList;

public class GalleryHorizontalLayout extends LinearLayout {
	Context myContext;
	ArrayList<String> itemList = new ArrayList<String>();

	public GalleryHorizontalLayout(Context context)
	{
		super(context);
		myContext = context;
	}

	public GalleryHorizontalLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		myContext = context;
	}

	public GalleryHorizontalLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		myContext = context;
	}

	public void add(String path){
		int newIdx = itemList.size();
		itemList.add(path);
		addView(getImageView(newIdx));
	}

	public ImageView getImageView(final int i){
		Bitmap bm = null;
		if (i < itemList.size()){
			bm = BitmapUtils.decodeSampledBitmapFromUri(itemList.get(i), 140, 140);
		}
		
		ImageView imageView = new ImageView(myContext);
    	imageView.setLayoutParams(new LayoutParams(140, 140));
    	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    	imageView.setImageBitmap(bm);

    	imageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//PropertyAdmin myActivity = (PropertyAdmin) myContext;
				MainActivity myActivity = (MainActivity) myContext;
				myActivity.updatePhotoFragment(itemList.get(i));
				Toast.makeText(myContext, 
						"Clicked - " + itemList.get(i), 
						Toast.LENGTH_LONG).show();
			}});

		return imageView;
	}

//    public int calculateInSampleSize(
//    		
//    	BitmapFactory.Options options, int reqWidth, int reqHeight) {
//    	// Raw height and width of image
//    	final int height = options.outHeight;
//    	final int width = options.outWidth;
//    	int inSampleSize = 1;
//        
//    	if (height > reqHeight || width > reqWidth) {
//    		if (width > height) {
//    			inSampleSize = Math.round((float)height / (float)reqHeight);  	
//    		} else {
//    			inSampleSize = Math.round((float)width / (float)reqWidth);  	
//    		}  	
//    	}
//    	
//    	return inSampleSize;  	
//    }
}
