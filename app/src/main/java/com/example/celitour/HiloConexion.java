package com.example.celitour;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class HiloConexion implements Runnable{
    public static final int IMG=10;
    public static final int TEXT=100;
    Handler handler;
    boolean img;
    public HiloConexion(Handler handler1, boolean img1) {
        this.handler=handler1;
        this.img=img1;
    }

    @Override
    public void run() {
        HTTPConexionInternet http= new HTTPConexionInternet();
        if(img==false){
            String respuesta= http.consultarRestaurantes("https://6355acf4da523ceadc05bdbf.mockapi.io/restaurantes");
            Message mensaje = new Message();
            mensaje.arg1=TEXT;
            mensaje.obj=respuesta;
            Log.d("respuesta run", respuesta);
            handler.sendMessage(mensaje);
        }
        else{
            byte[] respuesta= http.consultarImg("https://static.wikia.nocookie.net/mario/images/e/e3/MPS_Mario.png/revision/latest?cb=20220814154953");
            Message mensaje = new Message();
            mensaje.obj=respuesta;
            mensaje.arg1=IMG;
            handler.sendMessage(mensaje);
        }
    }
}
