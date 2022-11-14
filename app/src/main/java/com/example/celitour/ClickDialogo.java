package com.example.celitour;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

public class ClickDialogo implements DialogInterface.OnClickListener {
    View v;
    public ClickDialogo(View v){
        v=v;
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i==DialogInterface.BUTTON_NEGATIVE){
            Log.d("Click", "En el negativo");
        }
        else if(i==DialogInterface.BUTTON_NEUTRAL){
            Log.d("Click", "En el neutral");
        }else{
            Log.d("Click", "En el positivo");
        }
    }
}
