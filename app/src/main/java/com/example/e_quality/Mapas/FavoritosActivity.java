package com.example.e_quality.Mapas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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


public class FavoritosActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    double lat;
    double lng;
    FavoritesSQLite dbAdapter;
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fav_fragment);
        mapFragment.getMapAsync( this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapa=map;
        //Solicitud de instancia LOCATION SERVICE
        String serviceString= Context.LOCATION_SERVICE;
        LocationManager locationManager;
        locationManager= (LocationManager) getSystemService(serviceString);

        String provider= LocationManager.GPS_PROVIDER;


        //Actualizar localización
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Location location = locationManager.getLastKnownLocation(provider);

            lat = location.getLatitude();
            lng= location.getLongitude();
            LatLng myLatLng= new LatLng(lat,lng);

            CameraPosition myPosition = new CameraPosition.Builder()
                    .target(myLatLng).zoom(17).bearing(0).tilt(30).build();
            mapa.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
        }


        //Comprobamos si tenemos permiso
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_ACCESS_FINE_LOCATION );
        } else {
            //Muestra un punto azul en la ubicacion actual
            mapa.setMyLocationEnabled(true);
        }

        // Añadimos botones de zoom en el mapa
        mapa.getUiSettings().setZoomControlsEnabled(true);
        dbAdapter= new FavoritesSQLite(this);
        Cursor favorito=dbAdapter.getFavorito();

       if(favorito.getCount() != 0){

           favorito.moveToFirst();

           do{
               System.out.println("EL NOMBRE : " + favorito.getString(0));

               LatLng fav_position = new LatLng(Double.parseDouble(favorito.getString(1)), Double.parseDouble(favorito.getString(2)));
               mapa.addMarker(new MarkerOptions().position(fav_position).title(favorito.getString(0))).showInfoWindow();

           } while(favorito.moveToNext());
       }


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
}
