package com.example.celitour;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Ventana extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("titulo de mi ventana");
        //builder.setMessage("Esta seguro?");

        //un pedacito de xml mio
        View view=LayoutInflater.from(getContext()).inflate(R.layout.ventana, null);
       // builder.setView(view);

        ClickDialogo click = new ClickDialogo(view);

        // para mandar lista de opciones, no puede tener setMessage ni setView
        String[] lista=new String[]{"1","2","3"};
        builder.setItems(lista, click);

                //los 3 botones
        builder.setNegativeButton("No", click);
        builder.setPositiveButton("Si", click);
        builder.setNeutralButton("No se", click);
        //aca tambien se hacen los finby id


        return builder.create();
    }
}
