package com.example.celitour;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPConexionInternet {

    public String consultarRestaurantes(String endpoint){
        Log.d("PROBANDOOO", "entro a consultar restaurantes de HTTPConexionInternet");
        try {
            URL url= new URL(endpoint);
            HttpURLConnection connection= (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int respuestaCodigo=connection.getResponseCode();
            if(respuestaCodigo==200){
                InputStream is =connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer= new byte[1024];
                int cantidadLeido=0;

                while((cantidadLeido=is.read(buffer))!=-1){
                    baos.write(buffer,0, cantidadLeido);
                }

                is.close();

                return new String(baos.toByteArray(),"UTF-8");
            }else{
                Log.d("ERRORRRRR", "error en consultar restaurantes de conexion");
                throw new IOException();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] consultarImg(String endpoint) {
        try {
            URL url= new URL(endpoint);
            HttpURLConnection connection= (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int respuestaCodigo=connection.getResponseCode();
            if(respuestaCodigo==200){
                InputStream is =connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer= new byte[1024];
                int cantidadLeido=0;

                while((cantidadLeido=is.read(buffer))!=-1){
                    baos.write(buffer,0, cantidadLeido);
                }

                is.close();

                return baos.toByteArray();
            }else{
                throw new IOException();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
