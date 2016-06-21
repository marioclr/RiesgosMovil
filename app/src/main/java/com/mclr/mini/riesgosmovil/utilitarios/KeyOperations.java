package com.mclr.mini.riesgosmovil.utilitarios;

import android.view.KeyEvent;

import com.mclr.mini.riesgosmovil.MainActivity;

public class KeyOperations {
	public static void DispachBackKey(MainActivity act) {
		act.dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
		act.dispatchKeyEvent(new KeyEvent (KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
	}
}
