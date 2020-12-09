package com.example.e_quality.Eventos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

class Tiempo {
    private String dia;
    private String descripcionDiaria;
    private double temp;
    private double sensacionCalor;
    private double tempeMin;
    private double tempeMax;


    public Tiempo() {
        this.dia= "";
        this.descripcionDiaria = "";
        this.temp = 0.00;
        this.sensacionCalor = 0.00;
        this.tempeMin = 0.00;
        this.tempeMax = 0.00;
    }
    public String getDia(){
        return dia;
    }

    public void setDia(String dia){
        this.dia = dia;
    }

    public String getDescripcionDiaria() {
        return descripcionDiaria;
    }

    public void setDescripcionDiaria(String descripcionDiaria) {
        this.descripcionDiaria = descripcionDiaria;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getSensacionCalor() {
        return sensacionCalor;
    }

    public void setSensacionCalor(double sensacionCalor) {
        this.sensacionCalor = sensacionCalor;
    }

    public double getTempeMin() {
        return tempeMin;
    }

    public void setTempeMin(double tempeMin) {
        this.tempeMin = tempeMin;
    }

    public double getTempeMax() {
        return tempeMax;
    }

    public void setTempeMax(double tempeMax) {
        this.tempeMax = tempeMax;
    }

}

public class WeatherActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION=1;
    final String WEATHER_KEY = "ba79658e9f5f2b65d6faa2466bfda89e";
    private String idioma = "";


    private ListView m_listview;
    double lat;
    double lng;
    private TextView mTextView;




    public static String getImage(String icon){
        return String.format("http://openweathermap.org/img/%s.png", icon);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        idioma = getResources().getString(R.string.idioma);

        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("HELLOLOCATION: Tenemos permisos...");
        } else {
            // no tiene permiso, solicitarlo
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
            // cuando se nos conceda el permiso se llamará a onRequestPermissionsResult()
        }
        LocationManager servicioLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean soloActivos = true;
        List<String> proveedores = servicioLoc.getProviders(soloActivos);
        // Vemos si está disponible el proveedor de localización que queremos usar

        String serviceString = Context.LOCATION_SERVICE;
        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(serviceString);


        String proveedorElegido = LocationManager.GPS_PROVIDER;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Ultima ubicacion conocida por el proveedor
            Location location = locationManager.getLastKnownLocation(proveedorElegido);

            if (location != null) {
                lat = location.getLatitude();
                lng = location.getLongitude();

            } else {
                //En caso de no conocer una ubicación, utiliza la ubicación de la UC3M por defecto
                lat = 40.3318;
                lng = -3.7670;
            }
        }

        boolean disponible = proveedores.contains(proveedorElegido);

        // Otra opción es utilizar uno cualquiera de la lista (por ejemplo, el primero)
        if (!disponible) {
            proveedorElegido = proveedores.get(0);
        }
        Location localizacion = servicioLoc.getLastKnownLocation(proveedorElegido);

        //Mostramos la información en la pantalla del terminal
        latLng(localizacion);
        // Creamos un listview que va a contener los resultados de las consulta a Google Places
        m_listview = (ListView) findViewById(R.id.id_list_view);
        new MiTiempo().execute();

    }
    public void latLng(Location localizacion) {
        if(localizacion != null) { //Si tenemos datos de la localización

            lat = localizacion.getLatitude();
            lng = localizacion.getLongitude();
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

    public class MiTiempo extends AsyncTask<View, Void, ArrayList<Tiempo>>{
        @Override
        protected ArrayList<Tiempo> doInBackground(View... urls) {
            ArrayList<Tiempo> objeto;
            //print the call in the console
           // http://api.openweathermap.org/data/2.5/forecast?q=Madrid&APPID=ba79658e9f5f2b65d6faa2466bfda89e&units=metric&cnt=6
            System.out.println("https://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lng+"&APPID="+WEATHER_KEY+"&units=metric&cnt=6&lang="+idioma);
            // make Call to the url
            //objeto = makeCall("http://api.openweathermap.org/data/2.5/weather?q=Madrid&APPID=ba79658e9f5f2b65d6faa2466bfda89e"); lat={lat}&lon={lon}
            objeto = makeCall("https://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lng+"&APPID="+WEATHER_KEY+"&units=metric&cnt=6&lang="+idioma);
            return objeto;
        }


        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(ArrayList<Tiempo> result) {
            // Aquí se actualiza el interfaz de usuario
            List<String> listTitle = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                // make a list of the venus that are loaded in the list.
                // show the name, the category and the
                listTitle.add(i, getResources().getString(R.string.dia)+": "+result.get(i).getDia()+"\n\n"+getResources().getString(R.string.descripcionDiaria)+": " +result.get(i).getDescripcionDiaria()+ "\n\n"+getResources().getString(R.string.temp)+": "+result.get(i).getTemp()+"ºC"+"\n\n"+getResources().getString(R.string.sensacion)+": "+result.get(i).getSensacionCalor()+"ºC"+"\n\n"+getResources().getString(R.string.tempmin)+": "+result.get(i).getTempeMin()+"ºC"+"\n\n"+getResources().getString(R.string.tempmax)+": "+result.get(i).getTempeMax()+"ºC");
            }
            // set the results to the list
            // and show them in the xml
            ArrayAdapter<String> myAdapter;
            myAdapter = new ArrayAdapter<String>(WeatherActivity.this, R.layout.row_layout_weather, R.id.listText, listTitle);
            m_listview.setAdapter(myAdapter);
        }

    }


    public static ArrayList<Tiempo> makeCall(String stringURL) {

        URL url = null;
        BufferedInputStream is = null;
        JsonReader jsonReader;
        ArrayList<Tiempo> objeto = new ArrayList<>();

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

        if (is != null) {
            try {
                jsonReader = new JsonReader(new InputStreamReader(is, "UTF-8"));
                jsonReader.beginObject();

                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    // Busca la cadena "list"
                    if(name.equals("list")){
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()){
                            Tiempo tiempo = new Tiempo();
                            jsonReader.beginObject();
                            while (jsonReader.hasNext()){
                                name = jsonReader.nextName();
                                if (name.equals("main")) {
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()) {
                                        name = jsonReader.nextName();
                                        if (name.equals("temp")) {
                                            tiempo.setTemp(jsonReader.nextDouble());
                                            System.out.println("Temperatura: " + tiempo.getTemp());
                                        } else if (name.equals("feels_like")) {
                                            tiempo.setSensacionCalor(jsonReader.nextDouble());
                                            System.out.println("Sensación de calor: " + tiempo.getSensacionCalor());
                                        } else if (name.equals("temp_min")) {
                                            tiempo.setTempeMin(jsonReader.nextDouble());
                                            System.out.println("Temperatura mínima: " + tiempo.getTempeMin());
                                        } else if (name.equals("temp_max")) {
                                            tiempo.setTempeMax(jsonReader.nextDouble());
                                            System.out.println("Temperatura máxima: " + tiempo.getTempeMax());
                                        } else {
                                            jsonReader.skipValue();
                                        }
                                    }
                                    jsonReader.endObject();
                                }else if (name.equals("weather")) {
                                    // comienza un array de objetos
                                    jsonReader.beginArray();
                                    while (jsonReader.hasNext()) {
                                        jsonReader.beginObject();
                                        // comienza un objeto
                                        while (jsonReader.hasNext()) {
                                            name = jsonReader.nextName();
                                            if (name.equals("description")) {
                                                tiempo.setDescripcionDiaria(jsonReader.nextString());
                                                System.out.println("Descripcion diaria:" + tiempo.getDescripcionDiaria());
                                            } else {
                                                jsonReader.skipValue();
                                            }
                                        }
                                        jsonReader.endObject();
                                    }
                                    jsonReader.endArray();
                                }else if (name.equals("dt_txt")){
                                    tiempo.setDia(jsonReader.nextString());
                                    System.out.println("Dia:" + tiempo.getDia());

                                }else{
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                            objeto.add(tiempo);
                        }
                        jsonReader.endArray();
                    }else{
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            } catch (Exception exc) {
                System.out.println("Excepcion en try del json");
                return new ArrayList<Tiempo>();
            }
        }
        return objeto;
    }
}
