package com.example.e_quality.Eventos;
//CÓDIGO BASADO: https://www.youtube.com/watch?v=pJPSFwYQHqk&list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz&index=2
//PLAYLIST DE TODOS LOS VIDEOS: https://www.youtube.com/playlist?list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.R;

public class CalendarActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calendarView =(CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

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



    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence []items = new CharSequence[3];
        items[0] = getResources().getString(R.string.add);
        items[1] = getResources().getString(R.string.view);
        items[2] = getResources().getString(R.string.cancel);

        final int dia, mes, anio;
        dia = i;
        mes = i1+1;
        anio = i2;

        builder.setTitle(getResources().getString(R.string.selecciona))
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            //Agregar eventos
                            Intent intent = new Intent(getApplication(), AddEventActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dia", dia);
                            bundle.putInt("mes", mes);
                            bundle.putInt("anio", anio);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else if (i == 1){
                            //Ver eventos
                            Intent intent = new Intent(getApplication(), ViewEventActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dia", dia);
                            bundle.putInt("mes", mes);
                            bundle.putInt("anio", anio);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            //Cancelar eventos
                            return;
                        }

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
