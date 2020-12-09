package com.example.e_quality.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;

import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Asignaturas.AsignaturasActivity;
import com.example.e_quality.Eventos.EventoActivity;
import com.example.e_quality.Mapas.MapsActivity;
import com.example.e_quality.Noticias.NoticiasActivity;
import com.example.e_quality.R;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

/*Para el acceso a las opciones del menú lateral con el navigationView
* y como ejemplo de utilización y creación de un menú lateralse ha usado:
* https://www.desarrollolibre.net/blog/android/creando-un-navigation-drawer-menu-lateral-en-android#.XmOHCqhKiUm */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NavigationView navigationView=findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.opcion_asignaturas:
                                Asignaturas(navigationView);
                                break;
                            case R.id.opcion_mapas:
                                Mapas(navigationView);
                                break;
                            case R.id.opcion_noticias:
                                Noticias(navigationView);
                                break;
                            case R.id.opcion_eventos:
                                Eventos(navigationView);
                                break;

                            case R.id.opcion_alarma:
                                Alarma(navigationView);
                                break;
                            case R.id.option_about:
                                About(navigationView);
                                break;
                        }
                        return false;
                    }
        });
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setIcon(R.mipmap.ic_launcher_foreground);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Método para el botón asignaturas
    public void Asignaturas(View view){
        Intent asignaturas = new Intent(this, AsignaturasActivity.class);
        startActivity(asignaturas);

    }

    //Método para el botón Sitios de Interés
    public void Mapas(View view){
        Intent mapas= new Intent(this, MapsActivity.class);
        startActivity(mapas);
    }

    //Método para el botón de eventos
    public void Eventos(View view) {
        Intent eventos = new Intent(this, EventoActivity.class);
        startActivity(eventos);
    }

    // Método para el botón noticias
    public void Noticias(View view){
        Intent noticias= new Intent(this, NoticiasActivity.class);
        startActivity(noticias);

    }


    //Intent para entrar en alarmas
    public void Alarma(View view){
        Intent alarma= new Intent(this, AlarmaActivity.class);
        startActivity(alarma);
    }
    //Método intent para about
    public void About(View view){
        Intent about=new Intent(this, AboutActivity.class);
        startActivity(about);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer=findViewById(R.id.drawer_layout);

        if(item.getItemId() == android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }

        if(item.getItemId()==R.id.opcion_alarma){
            Intent alarma= new Intent(this,AlarmaActivity.class);
            startActivity(alarma);
        }
        if(item.getItemId()==R.id.option_about){
            Intent about=new Intent(this, AboutActivity.class);
            startActivity(about);
        }


        return super.onOptionsItemSelected(item);
    }

}
