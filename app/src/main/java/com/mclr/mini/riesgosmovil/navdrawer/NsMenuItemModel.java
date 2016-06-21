package com.mclr.mini.riesgosmovil.navdrawer;

/**
 * Created by mini on 05/06/16.
 */
public class NsMenuItemModel {
    private int title;
    public int iconRes;
    public int counter;
    public boolean isHeader;

    public NsMenuItemModel(int title, int iconRes,boolean header,int counter) {
        this.setTitle(title);
        this.iconRes = iconRes;
        this.isHeader=header;
        this.counter=counter;
    }

    public NsMenuItemModel(int title, int iconRes,boolean header){
        this(title,iconRes,header,0);
    }

    public NsMenuItemModel(int title, int iconRes) {
        this(title,iconRes,false);
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
