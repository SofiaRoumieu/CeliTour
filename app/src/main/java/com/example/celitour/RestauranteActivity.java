package com.example.celitour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.celitour.FormularioRestaurante.RestauranteController;
import com.example.celitour.FormularioRestaurante.RestauranteModel;
import com.example.celitour.FormularioRestaurante.RestauranteView;

public class RestauranteActivity extends AppCompatActivity {

    public static RestauranteView restauranteRetorno;
    public static Boolean restoNuevo;
    RestauranteView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        ActionBar actionBar = super.getSupportActionBar();

        RestauranteModel modelo = new RestauranteModel();
        RestauranteController controller = new RestauranteController(modelo);
        view = new RestauranteView(modelo, this, controller, actionBar);
        controller.setVista(view);

    }

    //fecha para atras del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            RestauranteActivity.restauranteRetorno = null;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}