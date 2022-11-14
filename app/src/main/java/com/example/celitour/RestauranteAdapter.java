package com.example.celitour;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celitour.FormularioRestaurante.RestauranteModel;

import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteViewHolder> {
    List<RestauranteModel> restaurantes;
    private MyOnClickItem listener;
    public RestauranteAdapter(List<RestauranteModel> restaurantes,MyOnClickItem listener){
        this.restaurantes=restaurantes;
        this.listener=listener;
    }
    public List<RestauranteModel> getRestaurantes() {
        return this.restaurantes;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new RestauranteViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder  holder, int position) {
        RestauranteModel r= this.restaurantes.get(position);
        holder.tvNombreRestaurante.setText(r.getNombre());
        holder.tvDireccion.setText(r.getCalle()+" "+r.getAltura() );
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return this.restaurantes.size();
    }
}
