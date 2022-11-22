package com.example.celitour;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.celitour.FormularioRestaurante.RestauranteModel;

import java.util.List;

public class Ventana extends DialogFragment {

    private Activity activity;
    private String titulo;
    private String mensaje;
    private String btnPositivo;
    private String btnNegativo;
    private String btnNeutral;
    private Boolean view;
    private List<RestauranteModel> restaurantes;


    public Ventana(String titulo, String mensaje, String btnPositivo, String btnNegativo,
                   String btnNeutral, Boolean view, List<RestauranteModel> restaurantes, Activity activity) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.btnPositivo = btnPositivo;
        this.btnNegativo = btnNegativo;
        this.btnNeutral = btnNeutral;
        this.view = view;
        this.restaurantes=restaurantes;
        this.activity=activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("titulo de mi ventana");
        //builder.setMessage("Esta seguro?");

        //un pedacito de xml mio
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ventana, null);
       // builder.setView(view);

        ClickDialogo click = new ClickDialogo(view);

        if (!("".equals(this.titulo)) && this.titulo != null)
            builder.setTitle(this.titulo);
        if (!("".equals(this.mensaje)) && this.mensaje != null)
            builder.setMessage(this.mensaje);
        if (!("".equals(this.btnPositivo)) && this.btnPositivo != null)
            builder.setPositiveButton(this.btnPositivo, click);
        if (!("".equals(this.btnNegativo)) && this.btnNegativo != null)
            builder.setNegativeButton(this.btnNegativo, click);

        builder.setView(view);
       /* // para mandar lista de opciones, no puede tener setMessage ni setView
        String[] lista=new String[]{"1","2","3"};
        builder.setItems(lista, click);

                //los 3 botones
        builder.setNegativeButton("No", click);
        builder.setPositiveButton("Si", click);
        builder.setNeutralButton("No se", click);*/
        //aca tambien se hacen los finby id


        return builder.create();
    }
}
