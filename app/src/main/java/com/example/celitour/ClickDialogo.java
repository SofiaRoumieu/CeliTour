package com.example.celitour;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

public class ClickDialogo implements DialogInterface.OnClickListener {
    View v;
    Activity activity;
    public ClickDialogo(View v, Activity activity){
        v=v;
        activity=activity;
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i==DialogInterface.BUTTON_NEGATIVE){
            Log.d("Click", "En el negativo");
        }
        else if(i==DialogInterface.BUTTON_NEUTRAL){

        }else{
            Log.d("Click", "En el positivo");
        }
    }
}
