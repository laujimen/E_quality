package com.example.e_quality.Principal;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.e_quality.Asignaturas.AsignaturasActivity;
import com.example.e_quality.R;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

   static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        Intent intent= new Intent(context, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(context,0,intent,0);

        Intent intentAs= new Intent(context, AsignaturasActivity.class);
        PendingIntent pendingIntentAs= PendingIntent.getActivity(context,0,intentAs,0);


       // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
        views.setOnClickPendingIntent(R.id.widget_button2,pendingIntentAs);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

      // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

