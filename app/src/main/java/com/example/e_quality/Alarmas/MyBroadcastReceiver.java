package com.example.e_quality.Alarmas;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.e_quality.Principal.MainActivity;
import com.example.e_quality.R;

//ESTA CLASE CREA LA NOTIFICACIÓN QUE APARECE CUANDO SALTA LA ALARMA
// Basada en el ejemplo de aula global HelloAlarmAppMov

public class MyBroadcastReceiver extends BroadcastReceiver {


   @Override
    public void onReceive(Context context, Intent intent){

           //Creamos notificación
           NotificationManager notificationManager;

           // crea canal de notificaciones
           NotificationCompat.Builder mBuilder =
                   new NotificationCompat.Builder(context, "com.example.e_quality.notify_001");

           //pendingIntent para abrir la actividad cuando se pulse la notificación
           Intent ii = new Intent(context, MainActivity.class);
           PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, ii, 0);
           mBuilder.setContentIntent(pendingIntent);
           mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
           mBuilder.setContentTitle("E_QUALITY");
           mBuilder.setContentText("HORA DE APRENDER");
           mBuilder.setAutoCancel(true);

           notificationManager =
                   (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               String channelId = "YOUR_CHANNEL_ID";
               NotificationChannel channel = new NotificationChannel(channelId,
                       "Canal de e_quality",
                       NotificationManager.IMPORTANCE_DEFAULT);
               notificationManager.createNotificationChannel(channel);
               mBuilder.setChannelId(channelId);
           }

           notificationManager.notify(1, mBuilder.build());


    }


}
