package com.example.e_quality.Alarmas;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.R;

import java.util.Calendar;

/*
* CODIGO PRINCIPALMENTE BASADO EN EL EJEMPLO DE AULA GLOBAL HelloAlarmAppMov
* Método cancelarAlarma() basado en: https://steakrecords.com/es/13556-how-to-cancel-alarm-from-alarmmanager-android.html
* Uso del método setInexactReapiting() basado en: https://developer.android.com/training/scheduling/alarms?hl=es-419#ejemplos-de-alarmas-de-reloj-en-tiempo-real
* Uso de los datos de TimePicker en el método setTime() basado en método onTimeSet() de:https://www.youtube.com/watch?v=REJ3pDLGTmA
*/

public class AlarmaActivity extends AppCompatActivity {

    public Calendar calendar = Calendar.getInstance();
    Calendar alarmCalendar= Calendar.getInstance();
    TextView textAlarma;

    private int hora=calendar.get(Calendar.HOUR_OF_DAY);
    private int minuto=calendar.get(Calendar.MINUTE);
    EditText textoHora;
    public long tiempoAlarma;


    Switch switch_15, switch_hora, switch_dia ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textoHora= findViewById(R.id.id_seleccion_hora);


    }

    public Calendar setTime(View view) {

        TimePickerDialog timerPickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int horaSeleccionada, int minutoSeleccionado) {
                textoHora.setText(horaSeleccionada + ":" + minutoSeleccionado);
                alarmCalendar.set(Calendar.HOUR_OF_DAY, horaSeleccionada);
                alarmCalendar.set(Calendar.MINUTE,minutoSeleccionado);
                alarmCalendar.set(Calendar.SECOND,0);
            }
        }, hora, minuto, true);

        timerPickerDialog.show();
        return alarmCalendar;
    }

    public void setAlarm(Calendar alarmCalendar){

        textAlarma=findViewById(R.id.text_alarma);
        textAlarma.setText(getResources().getString(R.string.alarmaActivada)+":" + alarmCalendar.get(Calendar.HOUR_OF_DAY) + ":" +alarmCalendar.get(Calendar.MINUTE));
        Toast.makeText(this, getResources().getString(R.string.alarmaOn), Toast.LENGTH_SHORT).show();
        Intent intentToFire= new Intent(this,MyBroadcastReceiver.class);
        final PendingIntent alarmPendingIntent=PendingIntent.getBroadcast(this,0,intentToFire, 0);
        AlarmManager alarma= (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int alarmType= AlarmManager.RTC_WAKEUP;

        //ALARMA CADA 15 MINUTOS
       // alarma.setInexactRepeating(alarmType, alarmCalendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmPendingIntent);

        //ALARMA CADA DÍA
        alarma.setInexactRepeating(alarmType, alarmCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);

    }

    public void activarAlarma(View view){
        System.out.println(tiempoAlarma);
        setAlarm(alarmCalendar);
    }

    public void cancelarAlarma(View view){

        Toast.makeText(this, getResources().getString(R.string.alarmaDescactivada), Toast.LENGTH_SHORT).show();
        textAlarma=findViewById(R.id.text_alarma);
        textAlarma.setText(getResources().getString(R.string.alarmaDescactivada));

        Intent intentToFire= new Intent(this,MyBroadcastReceiver.class);
        final PendingIntent alarmPendingIntent=PendingIntent.getBroadcast(this,0,intentToFire, 0);
        AlarmManager alarma= (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarma.cancel(alarmPendingIntent);

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
    public void onDestroy() {
        super.onDestroy();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }


}
