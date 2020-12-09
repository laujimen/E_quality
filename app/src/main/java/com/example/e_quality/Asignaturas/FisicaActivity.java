package com.example.e_quality.Asignaturas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.Quiz.QuizFisica;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.ScoresSQLite;
import com.example.e_quality.Quiz.TotalScoreFisicaActivity;


public class FisicaActivity extends AppCompatActivity {

    private LinearLayout LouiseDolan, RachelWebster;
    private static TextView scoreF1, scoreF2, scoreF3;
    private ScoresSQLite scoresSQLite;
    private static String getScoreF1 = null, getScoreF2 = null, getScoreF3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        scoresSQLite = new ScoresSQLite(this);

        scoreF1 = (TextView) findViewById(R.id.scoreViewF1);
        scoreF2 = (TextView) findViewById(R.id.scoreViewF2);
        scoreF3 = (TextView) findViewById(R.id.scoreViewF3);

        LouiseDolan = (LinearLayout) findViewById(R.id.LouiseDolan);
        RachelWebster = (LinearLayout) findViewById(R.id.RachelWebster);

        getScoreF1 = scoresSQLite.getScore ("1");
        getScoreF2 = scoresSQLite.getScore ("2");
        getScoreF3 = scoresSQLite.getScore ("3");

        if ((getScoreF1 != null)){
            scoreF1.setText(getScoreF1 + "/3");
        }
        if ((getScoreF2 != null)){
            scoreF2.setText(getScoreF2 + "/3");
        }
        if ((getScoreF3 != null)){
            scoreF3.setText(getScoreF3 + "/3");
        }

        if((scoreF1.getText().equals("2/3"))||(scoreF1.getText().equals("3/3"))){
            LouiseDolan.setAlpha(1);
        }
        if((scoreF2.getText().equals("2/3"))||(scoreF2.getText().equals("3/3"))){
            RachelWebster.setAlpha(1);
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




    public void Cientificas(View view) {
        int intValue;
        Intent cientifica = new Intent(this, MujeresActivity.class);
        cientifica.putExtra("ButtonId", view.getId());
        intValue = cientifica.getIntExtra("ButtonId", 0);

        if (intValue == R.id.liseMeitner) {
            startActivity(cientifica);
        }
        else{

        }
        if((scoreF1.getText().equals("2/3"))||(scoreF1.getText().equals("3/3"))){
            Intent cientificas = new Intent(this, MujeresActivity.class);
            cientificas.putExtra("ButtonId", view.getId());
            intValue = cientificas.getIntExtra("ButtonId", 0);

            if (intValue == R.id.louiseDolan) {
                startActivity(cientificas);
            }
            else{

            }
        }
        if((scoreF2.getText().equals("2/3"))||(scoreF2.getText().equals("3/3"))){
            Intent cientificas = new Intent(this, MujeresActivity.class);
            cientificas.putExtra("ButtonId", view.getId());
            intValue = cientificas.getIntExtra("ButtonId", 0);

            if (intValue == R.id.rachelWebster) {
                startActivity(cientificas);
            }
            else{

            }
        }
    }

    public void ListaMujeres(View view) {
        Intent fisicas = new Intent(this, Wikipedia.class);
        fisicas.putExtra("ButtonId", view.getId());
        startActivity(fisicas);
    }

    public void QuizFisica(View view) {
        int intValue;
        Intent QuizFisica = new Intent(this, com.example.e_quality.Quiz.QuizFisica.class);
        QuizFisica.putExtra("ButtonId", view.getId());
        intValue = QuizFisica.getIntExtra("ButtonId", 0);

        if (intValue == R.id.quizF1) {
            startActivity(QuizFisica);
        }
        else{

        }
        if((scoreF1.getText().equals("2/3"))||(scoreF1.getText().equals("3/3"))){
            Intent QuizFisica1 = new Intent(this, QuizFisica.class);
            QuizFisica1.putExtra("ButtonId", view.getId());
            intValue = QuizFisica1.getIntExtra("ButtonId", 0);

            if (intValue == R.id.quizF2) {
                startActivity(QuizFisica1);
            }
            else{

            }
        }
        if((scoreF2.getText().equals("2/3"))||(scoreF2.getText().equals("3/3"))){
            Intent QuizFisica1 = new Intent(this, QuizFisica.class);
            QuizFisica1.putExtra("ButtonId", view.getId());
            intValue = QuizFisica1.getIntExtra("ButtonId", 0);

            if (intValue == R.id.quizF3) {
                startActivity(QuizFisica1);
            }
            else{

            }
        }
    }

    public void TotalScoreF (View view) {
        Intent scores = new Intent(this, TotalScoreFisicaActivity.class);
        scores.putExtra("ButtonId", view.getId());
        startActivity(scores);
    }
}

