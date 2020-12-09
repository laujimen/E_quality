package com.example.e_quality.Noticias;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.R;


/* Se ha hecho uso del video: https://www.youtube.com/watch?v=iDQgED4uKhQ&list=PLyvsggKtwbLX06iMtXnRGX5lyjiiMaT2y&index=27&t=0s
*  para la linea 51 del codigo con la cual conseguimos que la aplicacion no acceda al navegador*/
public class Web extends AppCompatActivity {

    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        WebView webView = findViewById(R.id.webview);


        Intent miIntent = getIntent();
        int boton = miIntent.getIntExtra("ButtonWeb", 0);

        if(boton == R.id.news_hipertextual){
            URL= "https://hipertextual.com/";
        } else if(boton == R.id.news_quo){
            URL= "https://www.quo.es/";
        } else if(boton == R.id.news_xataka){
            URL= "https://www.xataka.com/";
        } else if(boton == R.id.news_pikara){
            URL= "https://www.pikaramagazine.com/";
        }

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);

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
