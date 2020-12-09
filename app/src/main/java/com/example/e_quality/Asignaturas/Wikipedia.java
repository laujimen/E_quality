package com.example.e_quality.Asignaturas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

//Metodo para crear el objeto con los elementos deseados del json
class Wpedia{
    private String nombre;

    public Wpedia(){

        this.nombre = "";
    }

    public void setName(String name) { this.nombre = name; }
    public String getName() {
        return nombre;
    }

}

public class Wikipedia extends AppCompatActivity {
    String categoria ="";

    private ListView m_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflamos el layout
        setContentView(R.layout.activity_wikipedia);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Creamos un listview que va a contener los resultados de las consulta a Google Places
        m_listview = (ListView) findViewById(R.id.id_list_view);

        new Wpedias().execute();
        selectInsert();
    }

    public void selectInsert() {
        Intent miIntent = getIntent();
        int boton = miIntent.getIntExtra("ButtonId", 0);


        if (boton == R.id.masFisicas) {
            categoria = "3AF%C3%ADsicas";

        } else if (boton == R.id.masQuimicas) {
            categoria = "3AQu%C3%ADmicas";

        } else if (boton == R.id.masBiologas) {
            categoria = "3ABi%C3%B3logas";
        }else if (boton == R.id.masFeministas){
            categoria = "3AFeministas";
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.opcion_alarma){
            Intent configuracion=new Intent(this, AlarmaActivity.class);
            startActivity(configuracion);
        }
        if(item.getItemId()==R.id.option_about){
            Intent about=new Intent(this, AboutActivity.class);
            startActivity(about);
        }

        return super.onOptionsItemSelected(item);
    }


    private class Wpedias extends AsyncTask<View, Void, ArrayList<Wpedia>> {

        @Override
        protected ArrayList<Wpedia> doInBackground(View... urls) {
            ArrayList<Wpedia> temp;
            //print the call in the console
            System.out.println("https://es.wikipedia.org/w/api.php?action=query&format=json&list=categorymembers&cmtitle=Category%" + categoria + "&prop=info&cmlimit=500");
            // make Call to the url
            temp = makeCall("https://es.wikipedia.org/w/api.php?action=query&format=json&list=categorymembers&cmtitle=Category%" + categoria + "&prop=info&cmlimit=500");
            return temp;
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(ArrayList<Wpedia> result) {
            // Aquí se actualiza el interfaz de usuario
            List<String> listTitle = new ArrayList<String>();

            for (int i = 0; i < result.size(); i++) {
                // make a list of the venus that are loaded in the list.
                // show the name, the category and the city
                listTitle.add(i, getResources().getString(R.string.nombres) +" " +result.get(i).getName());
            }
            // set the results to the list
            // and show them in the xml
            ArrayAdapter<String> myAdapter;
            myAdapter = new ArrayAdapter<String>(Wikipedia.this, R.layout.row_layout, R.id.listText, listTitle);
            m_listview.setAdapter(myAdapter);
        }
    }

   public static ArrayList<Wpedia> makeCall(String stringURL) {

        URL url = null;
        BufferedInputStream is = null;
        JsonReader jsonReader;
        ArrayList<Wpedia> temp = new ArrayList<Wpedia>();

        try {
            url = new URL(stringURL);
        } catch (Exception ex) {
            System.out.println("Malformed URL");
        }

        try {
            if (url != null) {
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                is = new BufferedInputStream(urlConnection.getInputStream());
            }
        } catch (IOException ioe) {
            System.out.println("IOException");
        }

        if (is != null){
            try{

                jsonReader = new JsonReader(new InputStreamReader(is,"UTF-8")) ;
                jsonReader.beginObject();

                while(jsonReader.hasNext()){
                    String datoBuscado = jsonReader.nextName();

                    if(datoBuscado.equals("query")){ //abrimos object
                        jsonReader.beginObject();
                        while(jsonReader.hasNext()){
                            datoBuscado = jsonReader.nextName(); //pasamos siguiente
                            if(datoBuscado.equals("categorymembers")){
                                jsonReader.beginArray(); //Abrimos array
                                while (jsonReader.hasNext()){
                                    Wpedia mujer = new Wpedia(); //objeto del tipo Wpedia
                                    jsonReader.beginObject();
                                    while(jsonReader.hasNext()){
                                        datoBuscado = jsonReader.nextName();
                                        if(datoBuscado.equals("title")){
                                            mujer.setName(jsonReader.nextString());
                                            System.out.println("Nombre de la científica es: " + mujer.getName());
                                        }else{
                                            jsonReader.skipValue();
                                        }
                                    }
                                    jsonReader.endObject(); //cierre de todos los objects
                                    temp.add(mujer); //Añadir a la lista temp todos las mujeres
                                }
                                jsonReader.endArray(); //los arrays abiertos hay que cerrarlos
                            }else{
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                    }else{
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            }catch (Exception exc){
                System.out.println("Excepcion en try del json");
                return new ArrayList<Wpedia>();
            }
        }
        return temp;
    }



}
