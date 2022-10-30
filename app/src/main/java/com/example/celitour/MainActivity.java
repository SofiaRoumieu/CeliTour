package com.example.celitour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler = new Handler(this);
        Thread t1= new Thread(new HiloConexion(handler, false));
        t1.start();
        Thread t2= new Thread(new HiloConexion(handler, true));
        t2.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1==HiloConexion.TEXT) {
            Log.d("handle message!!", message.obj.toString());
            TextView txt = findViewById(R.id.respuesta);
            txt.setText(message.obj.toString());
        }else if(message.arg1==HiloConexion.IMG){
            ImageView img=findViewById(R.id.fotoResto);
            byte[] imgByte= (byte[])message.obj;
            img.setImageBitmap(BitmapFactory.decodeByteArray(imgByte,0,imgByte.length));
        }

        return false;
    }
}