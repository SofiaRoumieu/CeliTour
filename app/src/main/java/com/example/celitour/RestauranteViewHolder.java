package com.example.celitour;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RestauranteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyOnClickItem listener;
    private int position;
    TextView tvNombreRestaurante;
    TextView tvDireccion;
    Button btnLlamar;
    CardView crdCardView;


    public RestauranteViewHolder(View item, MyOnClickItem listener) {
        super(item);
        this.tvDireccion= item.findViewById(R.id.tvDireccion);
        this.tvNombreRestaurante = item.findViewById(R.id.tvNombreRestaurante);
        this.btnLlamar = item.findViewById(R.id.btnLlamar);
        this.listener=listener;
        this.btnLlamar.setOnClickListener(this);
        this.crdCardView=item.findViewById(R.id.card_view);
        this.crdCardView.setOnClickListener(this);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        listener.onClickItem(position);
        listener.onClickCardResto(position);
    }
}
