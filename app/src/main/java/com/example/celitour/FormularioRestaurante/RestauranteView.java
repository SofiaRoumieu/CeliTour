package com.example.celitour.FormularioRestaurante;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.example.celitour.R;

public class RestauranteView implements View.OnClickListener{
    RestauranteModel modelo;
    Activity a;
    ActionBar actionBar;
    TextView txtNombre;
    TextView txtDireccion;
    TextView txtTelefono;
    TextView txtLocalidad;
    TextView txtWeb;
    TextView txtInstagram;
    TextView txtHorario;
    Button btnLlamar;
    Boolean usuarioNuevo;

    public RestauranteView(RestauranteModel modelo, Activity a, RestauranteController controller,ActionBar actionBar){
        this.a=a;
        this.modelo=modelo;
        txtDireccion= a.findViewById(R.id.txtDireccion);
        txtTelefono=a.findViewById(R.id.txtTelefono);
        txtNombre=a.findViewById(R.id.txtNombre);
        txtLocalidad=a.findViewById(R.id.txtLocalidad);
        txtWeb=a.findViewById(R.id.txtWeb);
        txtInstagram=a.findViewById(R.id.txtInstagram);
        txtHorario=a.findViewById(R.id.txtHorario);
        btnLlamar = a.findViewById(R.id.btnLlamar);
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnLlamar.setOnClickListener(this);

        //Obtengo los extras que recibo desde el main activity
        Bundle bundle = a.getIntent().getExtras();
        if (bundle!=null){
            usuarioNuevo=false;
            RestauranteModel restaurante = (RestauranteModel) bundle.getSerializable("restaurante");
            actionBar.setTitle(a.getString(R.string.details));
            txtNombre.setText(restaurante.getNombre());
            txtTelefono.setText(restaurante.getTelefono());
            txtLocalidad.setText(restaurante.getLocalidad());
            txtHorario.setText(restaurante.getHorario());
            txtWeb.setText(restaurante.getWeb());
            txtInstagram.setText(restaurante.getInstagram());
            txtDireccion.setText(restaurante.getCalle().concat(" ").concat(restaurante.getAltura().concat(", ").concat(restaurante.getLocalidad())));

        }
        else{
            usuarioNuevo=true;
            actionBar.setTitle("");//a.getString(R.string.menu_nuevo));
        }
    }
    //click boton llamar
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnLlamar){
            Intent intentCall =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+String.valueOf(modelo.getTelefono())));
            a.startActivity(intentCall);
        }
        if(view.getId()==R.id.btnCompartir){
            Intent share =new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_SUBJECT,"Mirá este restaurante!!");
            share.putExtra(Intent.EXTRA_TEXT, "modelo.getWeb()");
            a.startActivity(Intent.createChooser(share,"Compartir"));
        }

    }
}
