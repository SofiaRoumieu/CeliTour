package com.example.celitour;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.celitour.FormularioRestaurante.RestauranteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListenerSearchView implements SearchView.OnQueryTextListener {
    private List<RestauranteModel> restaurantes;
    private AppCompatActivity activity;

    public ListenerSearchView(List<RestauranteModel> restaurantes, AppCompatActivity activity) {
        this.restaurantes = restaurantes;
        this.activity = activity;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("buscndo por nombre", query);
        Log.d("buscndo por nombre",  restaurantes.toString());
        for (int i = 0; i < this.restaurantes.size(); i++) {
            RestauranteModel restaurante = this.restaurantes.get(i);

            if (query.toLowerCase().equals(restaurante.getNombre().toLowerCase())) {
                Ventana dialog = new Ventana(null, null, "Llamar", "Cerrar", "Compartir", true, restaurante, null);
                dialog.show(this.activity.getSupportFragmentManager(), "Dialog encontró usuario");
                return false;
            }
        }

        Ventana dialog = new Ventana("Restaurante no encontrado", "El restaurante '".concat(query).concat("' no esta dentro de la lista"), null, "Cerrar", null, false, null ,null);
        dialog.show(this.activity.getSupportFragmentManager(), "Dialog NO encontró usuario");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
