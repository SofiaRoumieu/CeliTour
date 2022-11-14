package com.example.celitour;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RestauranteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyOnClickItem listener;
    private int position;
    TextView tvNombreRestaurante;
    TextView tvDireccion;
    Button btnLlamar;


    public RestauranteViewHolder(View item, MyOnClickItem listener) {
        super(item);
        this.tvDireccion= item.findViewById(R.id.tvDireccion);
        this.tvNombreRestaurante = item.findViewById(R.id.tvNombreRestaurante);
        this.btnLlamar = item.findViewById(R.id.btnLlamar);
        this.listener=listener;
        this.btnLlamar.setOnClickListener(this);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        listener.onClickItem(position);
    }
}
