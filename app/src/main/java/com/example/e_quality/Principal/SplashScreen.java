package com.example.e_quality.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_quality.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Introducimos un tiempo de delay para que la actividad se ejecute durante pocos segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            //Ejecuta lo que hay a continuación despues de cierto tiempo
            public void run() {
                Intent intent = new Intent (SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        },2000); //4 segundos
    }
}
