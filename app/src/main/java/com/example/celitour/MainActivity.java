package com.example.celitour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.celitour.FormularioRestaurante.RestauranteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
public class MainActivity extends AppCompatActivity implements MyOnClickItem, Handler.Callback , SearchView.OnQueryTextListener{
    List<RestauranteModel> restaurantes;
    RestauranteAdapter adapter;
    Intent i;
    private int itemSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PermissionChecker.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},0);
        }
        this.restaurantes = this.recuperarRestaurantes();
        this.actualizarRecyclerView();
    }

    private List<RestauranteModel> recuperarRestaurantes() {

        SharedPreferences prefs = this.getSharedPreferences("sharedSofita", Context.MODE_PRIVATE);
        String restaurantesString = prefs.getString("restaurantes", "sinRestaurantes");

        if ("sinRestaurantes".equals(restaurantesString) ) {
            Handler handler = new Handler(this);
            Thread t1 = new Thread(new HiloConexion(handler, false));
            t1.start();

            return new ArrayList<RestauranteModel>();
        }
        else {
            return this.parserJsonRestaurantes(restaurantesString);
        }
    }

    public void actualizarRecyclerView() {

        adapter = new RestauranteAdapter(restaurantes, this);
        RecyclerView rv = findViewById(R.id.rvRestaurantes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    public void actualizarSharedPreferences() {
        SharedPreferences prefs = this.getSharedPreferences("sharedSofita", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("restaurantes", this.restaurantes.toString());
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater().inflate(R.menu.menu, menu);

        // Creo el SearchView y su respectivo listener
        MenuItem menuItem = menu.findItem(R.id.buscar);
        ListenerSearchView listenerSearchView = new ListenerSearchView(this.restaurantes, this);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(listenerSearchView);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1 == HiloConexion.TEXT) {
            this.restaurantes = this.parserJsonRestaurantes(message.obj.toString());
            this.actualizarRecyclerView();
            Log.d("Sofia - handleMess", String.valueOf(restaurantes));

            this.actualizarSharedPreferences();

        }else if(message.arg1==HiloConexion.IMG){
           /* ImageView img=findViewById(R.id.fotoResto);
            byte[] imgByte= (byte[])message.obj;
            img.setImageBitmap(BitmapFactory.decodeByteArray(imgByte,0,imgByte.length));*/
        }

        return false;
    }
    private ArrayList<RestauranteModel> parserJsonRestaurantes(String string) {
        ArrayList<RestauranteModel> restaurantes = new ArrayList<>();
        Log.d("recibe", string);
        try {
            JSONArray jsonArray = new JSONArray(string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Integer id = Integer.valueOf(jsonObject.getString("id"));
                String nombre = jsonObject.getString("nombre");
                String calle = jsonObject.getString("calle");
                String altura = jsonObject.getString("altura");
                String entre1 = jsonObject.getString("entre1");
                String entre2 = jsonObject.getString("entre2");
                String provincia =jsonObject.getString("provincia");
                String localidad = jsonObject.getString("localidad");
                String telefono = jsonObject.getString("telefono");
                String email =(jsonObject.has("email"))?jsonObject.getString("email"):"";
                String instagram = (jsonObject.has("instagram"))?jsonObject.getString("instagram"):"";
                String facebook =(jsonObject.has("facebook"))?jsonObject.getString("facebook"):"";
                String twitter = (jsonObject.has("twitter"))?jsonObject.getString("twitter"):"";
                String menu =(jsonObject.has("menu"))?jsonObject.getString("menu"):"";
                Double latitud =0.0;//(Double.valueOf(jsonObject.has("email"))!=null)?Double.valueOf(jsonObject.getString("latitud")):0.0;
                Double longitud = 0.0;//(Double.valueOf(jsonObject.getString("longitud"))!=null)?Double.valueOf(jsonObject.getString("longitud")):0.0;
                Boolean activo =true;//(Boolean.valueOf(jsonObject.getString("activo"))!=null)?Boolean.valueOf(jsonObject.getString("activo")):false;
                String web =(jsonObject.has("web"))?jsonObject.getString("web"):"";
                String horario =(jsonObject.has("horario"))?jsonObject.getString("horario"):"";



                restaurantes.add(new RestauranteModel(id, nombre, calle, altura
                        ,entre1,entre2,provincia,localidad,telefono,email,instagram,facebook,twitter,menu,latitud,longitud
                        ,activo,web,horario));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return restaurantes;
    }

    @Override
    public void onClickItem(int position) {
       Intent intentCall =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+String.valueOf(restaurantes.get(position).getTelefono())));
       startActivity(intentCall);
    }

    @Override
    public void onClickCompartir(int position){
        Intent share =new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT,"Mirá este restaurante!!");
        share.putExtra(Intent.EXTRA_TEXT, restaurantes.get(position).getWeb());
        startActivity(Intent.createChooser(share,"Compartir"));
    }
    @Override
    public void onClickCardResto(int position) {

        //abrir intent con detalle del resto
        i = new Intent(this,RestauranteActivity.class);
        i.putExtra("restaurante", this.restaurantes.get(position));

        startActivity(i);
        this.itemSeleccionado = position;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        for (int i = 0; i < this.restaurantes.size(); i++) {
            RestauranteModel resto = this.restaurantes.get(i);

            if (query.equals(resto.getNombre())) {
              //  String mensaje = "El rol del usuario es ".concat(usuario.getRol());
                String mensaje="";
                Ventana dialog = new Ventana("Usuario encontrado", mensaje,  null, "Cerrar",null, false,null, null);
                dialog.show(this.getSupportFragmentManager(), "Dialog encontró usuario");
                return false;
            }
        }

        Ventana dialog = new Ventana("Restaurante no encontrado", "El restaurante ".concat(query).concat(" no esta dentro de la lista"), null, "Cerrar", null, false,null, null );
        dialog.show(this.getSupportFragmentManager(), "Dialog NO encontró usuario");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}