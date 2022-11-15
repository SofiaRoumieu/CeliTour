package com.example.celitour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.celitour.FormularioRestaurante.RestauranteModel;

import java.util.ArrayList;
public class ListenerSearchView implements SearchView.OnQueryTextListener {
    private ArrayList<RestauranteModel> restaurantes;
    private AppCompatActivity activity;

    public ListenerSearchView(ArrayList<RestauranteModel> restaurantes, AppCompatActivity activity) {
        this.restaurantes = restaurantes;
        this.activity = activity;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        for (int i = 0; i < this.restaurantes.size(); i++) {
            RestauranteModel restaurante = this.restaurantes.get(i);

            if (query.equals(restaurante.getNombre())) {
                String mensaje = "El rol del usuario es ".concat(restaurante.getCalle());
                Ventana dialog = new Ventana();//("Usuario encontrado", mensaje, "Cerrar", null, null, null, null);
                dialog.show(this.activity.getSupportFragmentManager(), "Dialog encontró usuario");
                return false;
            }
        }

        Ventana dialog = new Ventana();//("Usuario no encontrado", "El usuario ".concat(query).concat(" no esta dentro de la lista"), "Cerrar", null, null, null, null );
        dialog.show(this.activity.getSupportFragmentManager(), "Dialog NO encontró usuario");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
