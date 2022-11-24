package com.example.celitour;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
    private RestauranteModel restaurante;


    public Ventana(String titulo, String mensaje, String btnPositivo, String btnNegativo,
                   String btnNeutral, Boolean view, RestauranteModel restaurante, Activity activity) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.btnPositivo = btnPositivo;
        this.btnNegativo = btnNegativo;
        this.btnNeutral = btnNeutral;
        this.view = view;
        this.restaurante=restaurante;
        this.activity=activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        //un pedacito de xml mio
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ventana, null);
       // builder.setView(view);

        Log.d("restaurante recibido",restaurante.getNombre());
        /*TextView txtDireccion=view.findViewById(R.id.txtDireccion);
        TextView txtNombre = view.findViewById(R.id.txtNombre);
        txtDireccion.setText(restaurante.getCalle().concat(" ").concat(restaurante.getAltura()));
        txtNombre.setText(restaurante.getNombre());*/
        ClickDialogo click = new ClickDialogo(view, activity);

        if (!("".equals(this.titulo)) && this.titulo != null)
            builder.setTitle(this.titulo);
        if (!("".equals(this.mensaje)) && this.mensaje != null)
            builder.setMessage(this.mensaje);
        //if (!("".equals(this.btnPositivo)) && this.btnPositivo != null)
       // if (!("".equals(this.btnNeutral)) && this.btnPositivo != null)
       //     builder.setNeutralButton(this.btnNeutral,click);
        builder.setPositiveButton("Cerrar", click);

        if(this.view)
            builder.setView(view);

        TextView txtNombre= view.findViewById(R.id.tvNombre);
        TextView txtDireccion= view.findViewById(R.id.tvDire);
        txtNombre.setText(restaurante.getNombre());
        txtDireccion.setText(restaurante.getCalle().concat(" ").concat(restaurante.getAltura()));
        return builder.create();
    }
}
