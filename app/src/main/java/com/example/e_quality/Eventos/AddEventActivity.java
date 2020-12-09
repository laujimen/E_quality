package com.example.e_quality.Eventos;
//CÓDIGO BASADO: https://www.youtube.com/watch?v=iUVOfImFPuQ&list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz&index=4&t=539s
//PLAYLIST DE TODOS LOS VIDEOS: https://www.youtube.com/playlist?list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.EventsSQLite;

import static java.lang.String.valueOf;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombreEvento, descripcion, ubicacion, fechaDesde, fechaHasta;
    private Button guardar, cancelar;
    private static int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nombreEvento = (EditText) findViewById(R.id.nombreEvento);
        ubicacion = (EditText) findViewById(R.id.ubicacion);
        fechaDesde = (EditText) findViewById(R.id.fechaDesde);
        fechaHasta = (EditText) findViewById(R.id.fechaHasta);
        descripcion =(EditText) findViewById(R.id.descripcion);

        Bundle bundle = getIntent().getExtras();
        int dia=0, mes=0, anio=0;
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        anio = bundle.getInt("anio");
        fechaDesde.setText(dia+" - " +mes+ " - " +anio);
        fechaHasta.setText(dia+" - " +mes+ " - " +anio);

        guardar = (Button) findViewById(R.id.guardar);
        cancelar = (Button) findViewById(R.id.cancelar);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==guardar.getId()){
            //Vamos a guardar los datos de la caja de textos
            EventsSQLite eventsSQLite = new EventsSQLite(this);
            SQLiteDatabase db = eventsSQLite.getWritableDatabase();

            number++;
            String name = valueOf(nombreEvento.getText());
            String location = valueOf(ubicacion.getText());
            String dateFrom = valueOf(fechaDesde.getText());
            String dateTo = valueOf(fechaHasta.getText());
            String description = valueOf(descripcion.getText());
            String numb = valueOf(number);


            try{
                eventsSQLite.insertEvents(numb, name, location, dateFrom, dateTo, description);
                nombreEvento.setText("");
                ubicacion.setText("");
                fechaDesde.setText("");
                fechaHasta.setText("");
                descripcion.setText("");
                this.finish();
            }catch (Exception e){
                Toast.makeText(getApplication(),"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }else{
            this.finish();
            return;
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

}
