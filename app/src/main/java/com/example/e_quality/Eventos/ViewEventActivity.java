package com.example.e_quality.Eventos;
//CÓDIGO BASADO https://www.youtube.com/watch?v=QC8XYx_ywds&list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz&index=5
//PLAYLIST DE TODOS LOS VIDEOS: https://www.youtube.com/playlist?list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.EventsSQLite;

public class ViewEventActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private EventsSQLite eventsSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.listaEventos);
        listView.setOnItemLongClickListener(this);

        Bundle bundle = getIntent().getExtras();
        int dia, mes, anio;
        dia= mes = anio = 0;
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        anio = bundle.getInt("anio");
        String cadena = dia+ " - " +mes+ " - " +anio;

        //Nos conectamos a la base de datos en modo lectura
        eventsSQLite = new EventsSQLite(this);
        //Para almacenar los registros que mos devuevle la consulta
        Cursor getEvents;

        String nombre, fechaDesde, fechaHasta, descripcion, ubicacion;

        try{
           getEvents = eventsSQLite.getEvents(cadena);
           arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
           if (getEvents.moveToFirst()){
               do{
                  nombre = getEvents.getString(2);
                  ubicacion = getEvents.getString(3);
                  fechaDesde = getEvents.getString(4);
                  fechaHasta = getEvents.getString(5);
                  descripcion = getEvents.getString(6);
                  arrayAdapter.add(nombre+ ", " +ubicacion+ ", "+fechaDesde+ ", " +fechaHasta+ ", " +descripcion);
               }while (getEvents.moveToNext());
                listView.setAdapter(arrayAdapter);
           }else{
               this.finish();
           }

        }catch (Exception ex){
            Toast.makeText(getApplication(), "Error"+ex.getMessage(),Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void delete(String dato){
       String [] datos = dato.split(", ");
       String name = datos[0];

       try{
            arrayAdapter.remove(dato);
            listView.setAdapter(arrayAdapter);
            eventsSQLite.deleteEvent(name);
            Toast.makeText(getApplication(), getResources().getString(R.string.eventDelete), Toast.LENGTH_SHORT).show();
       }catch (Exception ex){
            Toast.makeText(getApplication(), "Error"+ex.getMessage(), Toast.LENGTH_SHORT).show();
       }
    }


    @Override
    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int h, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence[]items = new CharSequence[2];
        items[0] = getResources().getString(R.string.delete);
        items[1] = getResources().getString(R.string.cancel);

        builder.setTitle(getResources().getString(R.string.selecciona))
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Eliminar evento
                        if (i == 0){
                            delete(adapterView.getItemAtPosition(h).toString());

                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
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

}
