package com.example.celitour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import com.example.celitour.FormularioRestaurante.RestauranteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnClickItem{
    ArrayList<RestauranteModel> restaurantes =new ArrayList<>();
    RestauranteAdapter adapter;
    Intent i;
    private int itemSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //para
        SharedPreferences sp= getSharedPreferences("misRestaurantes",Context.MODE_PRIVATE );
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("clave","valor");
        ed.putInt("edad",36);
        ed.commit();

        Handler handler = new Handler(this);
        Thread t1= new Thread(new HiloConexion(handler, false));
        t1.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu, menu);

        //
        // Armo el menú de la toolbar según el "menu_principal.xml"
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

       /* if(item.getItemId()==R.id.itmBuscar){
           // i = new Intent(this,UsuarioActivity.class);
           // startActivity(i);
            Ventana v=new Ventana();
            v.show(getSupportFragmentManager(),"ventana");

            //Group g= super.findViewById(R.id.group2);
            //g.setVisible(false);  no funciona
        }
        return super.onOptionsItemSelected(item);*/
        return true;
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1 == HiloConexion.TEXT) {
            this.restaurantes = this.parserJsonRestaurantes(message.obj.toString());

            adapter = new RestauranteAdapter(restaurantes, this);
            RecyclerView rv = findViewById(R.id.rvRestaurantes);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);

        }else if(message.arg1==HiloConexion.IMG){
           /* ImageView img=findViewById(R.id.fotoResto);
            byte[] imgByte= (byte[])message.obj;
            img.setImageBitmap(BitmapFactory.decodeByteArray(imgByte,0,imgByte.length));*/
        }

        return false;
    }
    private ArrayList<RestauranteModel> parserJsonRestaurantes(String string) {
        ArrayList<RestauranteModel> restaurantes = new ArrayList<>();

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
                String email =(jsonObject.getString("email")!=null)?jsonObject.getString("email"):"";
                String instagram = "";//(jsonObject.getString("instagram")!=null)?jsonObject.getString("instagram"):"";
                String facebook ="";//(jsonObject.getString("facebook")!=null)?jsonObject.getString("facebook"):"";
                String twitter = "";//(jsonObject.getString("twitter")!=null)?jsonObject.getString("twitter"):"";
                String menu ="";//(jsonObject.getString("menu")!=null)?jsonObject.getString("menu"):"";
                Double latitud =0.0;//(Double.valueOf(jsonObject.getString("latitud"))!=null)?Double.valueOf(jsonObject.getString("latitud")):0.0;
                Double longitud = 0.0;//(Double.valueOf(jsonObject.getString("longitud"))!=null)?Double.valueOf(jsonObject.getString("longitud")):0.0;
                Boolean activo =true;//(Boolean.valueOf(jsonObject.getString("activo"))!=null)?Boolean.valueOf(jsonObject.getString("activo")):false;
                String web ="";//(jsonObject.getString("web")!=null)?jsonObject.getString("web"):"";
                String horario ="";//(jsonObject.getString("horario")!=null)?jsonObject.getString("horario"):"";



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
}