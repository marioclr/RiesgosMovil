package com.mclr.mini.riesgosmovil.utilitarios;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

@SuppressLint("ValidFragment")
public class DialogAlert extends DialogFragment {
	Activity activity;
	String message;
	String title;
	
	public DialogAlert() {
		
	}

	public DialogAlert(Activity act, String message, String title){
		this.activity = act;
		this.message = message;
		this.title = title;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
        AlertDialog.Builder builder =
                new AlertDialog.Builder(activity);
 
        builder.setMessage(message)
               .setTitle(title)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });
 
        return builder.create();
    }
}