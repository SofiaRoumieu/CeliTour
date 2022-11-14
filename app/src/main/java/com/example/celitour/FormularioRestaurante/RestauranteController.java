package com.example.celitour.FormularioRestaurante;

import android.util.Log;
import android.view.View;

public class RestauranteController implements View.OnClickListener{
    @Override
    public void onClick(View view) {

    }
   /* RestauranteModel modelo;
    RestauranteView vista;

    public RestauranteController(RestauranteModel u){
        this.modelo=u;
    }

    public void setVista(RestauranteView vista){
        this.vista=vista;
        vista.showModelo();
    }

    //click boton llamar
    @Override
    public void onClick(View view) {
        this.vista.setModelo();
        if(this.validarModelo()){
            String respuesta=(vista.usuarioNuevo)?"El usuario " +modelo.getNombre() +" se ha creado con éxito" : "El usuario  " +modelo.getNombre() + " se ha modificado con éxito";

            Log.d("Usuario",modelo.toString());
            UsuarioActivity.usuarioRetorno = modelo;
            UsuarioActivity.usuarioNuevo= vista.usuarioNuevo;
            vista.mostrarMensaje(respuesta);
            vista.a.finish();
        }
        else{
            vista.mostrarMensaje("El modelo recibido no es valido, verifique los datos");
            Log.d("error","El modelo recibido no es valido");
            UsuarioActivity.usuarioRetorno = null;
        }
    }

    public boolean validarModelo(){
        // Log.d("tipoUsuario", modelo.getTipoUsuario());
        if(modelo.getNombre()!=null && modelo.getNombre().length()>3 &&
                modelo.getContraseña()!=null && modelo.getContraseña().length()>3
                && modelo.getContraseñaBis().equals(modelo.getContraseña()) && modelo.getTipoUsuario()!=null){
            return true;
        }
        else{
            return false;
        }
    }*/
}
