package com.example.e_quality.Mapas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.FavoritesSQLite;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/*Código basado en el ejemplo de aula global GooglePlacesJsonAppMov
* Se ha usado https://stackoverflow.com/questions/39543304/how-to-open-a-new-activity-by-using-double-click-marker-for-android-google-map-a/39543436
* para gestionar el doble click en un marcador*/

class GooglePlace{
    private String nombre;
    private String latitude;
    private String longitude;

    public GooglePlace(){
        this.nombre="";
        this.latitude="";
        this.longitude="";
    }

    public String getNombre(){
        return nombre;
    }
    public String getLat(){
        return latitude;
    }

    public String getLng() {
        return longitude;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLat(String latitude) {
        this.latitude = latitude;
    }

    public void setLng(String longitude) {
        this.longitude = longitude;
    }
}
public class MapasActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mapa;

    double lat;
    double lng;
    private int API_KEY=R.string.google_maps_key;
    private int radio=3000;
    private String type="";
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1;

    private FavoritesSQLite favoritesSQLite;
    String DBnombre;
    double DBlat;
    double DBlng;

    boolean doubleClick=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync( this);


    }


    @Override
    public void onMapReady(GoogleMap map) {
        mapa = map;

        // Añadimos botones de zoom en el mapa
        mapa.getUiSettings().setZoomControlsEnabled(true);


        //Solicitud de instancia LOCATION SERVICE
        String serviceString = Context.LOCATION_SERVICE;
        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(serviceString);


        // String provider = locationManager.getBestProvider(criteria, true);

        String provider = LocationManager.GPS_PROVIDER;

        //OBTIENE LA UBICACIÓN ACTUAL Y LA ACTUALIZA CUANDO CAMBIA
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Ultima ubicacion conocida por el proveedor
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                lat=location.getLatitude();
                lng=location.getLongitude();

            } else{
                //En caso de no conocer una ubicación, utiliza la ubicación de la UC3M por defecto
                lat=40.3318;
                lng=-3.7670;
            }

            LatLng myLatLng = new LatLng(lat, lng);

            CameraPosition myPosition = new CameraPosition.Builder()
                    .target(myLatLng).zoom(17).bearing(0).tilt(30).build();
            mapa.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));

            //Tiempo mínimo de escuchas de nueva posicion en milisegundos
            int tiempo = 1000;
            //Distancia mínima entre escuchas de nueva posición en metros
            int dist = 10;

            // ACTUALIZAR LA UBICACION SI CAMBIA CON LOCATION LISTENER
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    if (location != null) {
                        lat = location.getLatitude();
                        lng = location.getLongitude();

                        // Muestra los lugares cercanos

                        type = getType();
                        new MiNearbyPlaces().execute();
                    }

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            locationManager.requestLocationUpdates(provider, tiempo, dist, locationListener);

        }

        //Comprobamos si tenemos permiso
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_ACCESS_FINE_LOCATION );
        } else {
            //Muestra un punto azul en la ubicacion actual
            mapa.setMyLocationEnabled(true);
        }


        type=getType();
        new MiNearbyPlaces().execute();


        mapa.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

            }
        });


        // Listener para guardar el marcador como favorito
        mapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                if(doubleClick) {
                    LatLng markerLocation = marker.getPosition();
                    DBnombre = marker.getTitle();
                    DBlat = markerLocation.latitude;
                    DBlng = markerLocation.longitude;
                    Toast.makeText(MapasActivity.this, getResources().getString(R.string.favoritoGuardado), Toast.LENGTH_LONG).show();

                    String lat_fav = Double.toString(DBlat);
                    String lng_fav = Double.toString(DBlng);


                    // GUARDAMOS EN LA BASE DE DATOS

                    favoritesSQLite = new FavoritesSQLite(MapasActivity.this);
                    favoritesSQLite.insertFavorito(DBnombre, lat_fav, lng_fav);


                } else {
                    doubleClick=true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleClick=false;
                        }
                    },2000);
                }
                return false;
            }
        });


    }

    private String getType(){

        Intent miIntent = getIntent();
        int boton = miIntent.getIntExtra("ButtonMap", 0);

        if(boton == R.id.biblioteca){
            type= "library";
        } else if(boton == R.id.universidad){
            type= "university";
        } else if(boton == R.id.female){
            type= "police";
        }
        return type;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);
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


    public class MiNearbyPlaces extends AsyncTask<View,Void, ArrayList<GooglePlace>> {


        @Override
        protected ArrayList<GooglePlace> doInBackground(View... urls) {
            ArrayList<GooglePlace> llamadaUrl;
            System.out.println("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lat + "," + lng
                    + "&radius=" + radio + "&type=" + type + "&sensor=true&key=" + API_KEY);
            llamadaUrl=makeCall("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lat + "," + lng
                    + "&radius=" + radio + "&type=" + type + "&sensor=true&key=" + API_KEY);
            return llamadaUrl;
        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onPostExecute(ArrayList<GooglePlace> result) {
            Log.d("msg", "entra en postexecute");

            for (int i = 0; i < result.size(); i++) {

                double lat=Double.parseDouble(result.get(i).getLat());
                double lng=Double.parseDouble(result.get(i).getLng());
                String placeName = result.get(i).getNombre();
                System.out.println(lat);
                System.out.println(lng);
                System.out.println(placeName);
                LatLng latLng = new LatLng(lat, lng);
                System.out.println(latLng);
                mapa.addMarker(new MarkerOptions().position(latLng).title(placeName)).showInfoWindow();

            }
        }

    }

    public static ArrayList<GooglePlace> makeCall(String stringURL){

        URL url = null;
        BufferedInputStream is = null;
        JsonReader jsonReader;
        ArrayList<GooglePlace> temp = new ArrayList<GooglePlace>();

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
                    // Busca la cadena "results"
                    if (name.equals("results")) {
                        // comienza un array de objetos
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            GooglePlace poi = new GooglePlace();
                            jsonReader.beginObject();
                            // comienza un objeto
                            while (jsonReader.hasNext()) {
                                name = jsonReader.nextName();
                                if (name.equals("name")) {
                                    // si clave "name" guarda el valor
                                    poi.setNombre(jsonReader.nextString());
                                    System.out.println("PLACE NAME:" + poi.getNombre());
                                } else if (name.equals("geometry")) {
                                    // Si clave "geometry" empieza un objeto
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()) {
                                        name = jsonReader.nextName();
                                        if (name.equals("location")) {
                                            // dentro de "geometry", si clave "location" empieza un objeto
                                            jsonReader.beginObject();
                                            while (jsonReader.hasNext()) {
                                                name = jsonReader.nextName();
                                                // se queda con los valores de "lat" y "long" de ese objeto
                                                if (name.equals("lat")) {
                                                    poi.setLat(jsonReader.nextString());
                                                    System.out.println("PLACE LATITUDE:" + poi.getLat());
                                                } else if (name.equals("lng")) {
                                                    poi.setLng(jsonReader.nextString());
                                                    System.out.println("PLACE LONGITUDE:" + poi.getLng());
                                                } else {
                                                    jsonReader.skipValue();
                                                }
                                            }
                                            jsonReader.endObject();
                                        } else {
                                            jsonReader.skipValue();
                                        }
                                    }
                                    jsonReader.endObject();
                                } else{
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                            temp.add(poi);
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            } catch (Exception e) {
                System.out.println("Exception");
                return new ArrayList<GooglePlace>();
            }
        }

        return temp;}
}

