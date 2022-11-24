package com.example.celitour.FormularioRestaurante;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

public class RestauranteController {//implements View.OnClickListener{
    RestauranteModel modelo;
    RestauranteView vista;

    public RestauranteController(RestauranteModel u){
        this.modelo=u;
    }

    public void setVista(RestauranteView vista){
        this.vista=vista;
       // vista.showModelo();
    }

   /* //click boton llamar
    @Override
    public void onClick(View view) {
        Intent intentCall =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+String.valueOf(modelo.getTelefono())));
        startActivity(intentCall);
    }*/
}
