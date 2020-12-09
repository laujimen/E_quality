package com.example.e_quality.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.ScoresSQLite;
import com.example.e_quality.Tablas.TimeSQLite;


public class TotalScoreQuimicaActivity extends AppCompatActivity {

    private TextView score1, score2, score3;
    private TextView time1, time2, time3;
    private TextView message, percentage;
    private TimeSQLite timeSQLite;
    private ScoresSQLite scoresSQLite;
    private ProgressBar progressBar;
    private static String getScoreQ1 = null, getScoreQ2 = null, getScoreQ3 = null;
    private static String getTimeQ1 = null, getTimeQ2 = null, getTimeQ3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_score_quimica);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        scoresSQLite = new ScoresSQLite(this);
        timeSQLite = new TimeSQLite(this);
        progressBar = new ProgressBar(this);

        progressBar = (ProgressBar) findViewById(R.id.progressQ);
        message = (TextView) findViewById(R.id.MessageQ);
        percentage = (TextView) findViewById(R.id.PercentageQ);

        score1 = (TextView) findViewById(R.id.PuntuacionQ1);
        score2 = (TextView) findViewById(R.id.PuntuacionQ2);
        score3 = (TextView) findViewById(R.id.PuntuacionQ3);

        time1 = (TextView) findViewById(R.id.TiempoTestQ1);
        time2 = (TextView) findViewById(R.id.TiempoTestQ2);
        time3 = (TextView) findViewById(R.id.TiempoTestQ3);

        getScoreQ1 = scoresSQLite.getScore ("4");
        getScoreQ2 = scoresSQLite.getScore ("5");
        getScoreQ3 = scoresSQLite.getScore ("6");

        setScore();
        setTime();
        setProgressBar();
    }

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


    public void setScore() {
        if ((getScoreQ1 != null)) {
            score1.setText(getResources().getString(R.string.puntuacion)+": " + getScoreQ1 + "/3");
        }
        else{
            score1.setText(getResources().getString(R.string.puntuacion)+": -/3");
        }

        if ((getScoreQ2 != null)) {
            score2.setText(getResources().getString(R.string.puntuacion)+": " + getScoreQ2 + "/3");
        }
        else{
            score2.setText(getResources().getString(R.string.puntuacion)+": -/3");
        }

        if ((getScoreQ3 != null)) {
            score3.setText(getResources().getString(R.string.puntuacion)+": "+ getScoreQ3 + "/3");
        }
        else{
            score3.setText(getResources().getString(R.string.puntuacion)+": -/3");
        }
    }

    public void setTime() {
        getTimeQ1 = timeSQLite.getTime ("4");
        getTimeQ2 = timeSQLite.getTime ("5");
        getTimeQ3 = timeSQLite.getTime ("6");

        if ((getTimeQ1 != null)) {
            time1.setText( getResources().getString(R.string.duracion)+": " + getTimeQ1 +" "+ getResources().getString(R.string.seg));
        }
        else {
            time1.setText(getResources().getString(R.string.duracion)+": - "+getResources().getString(R.string.seg)+": ");
        }

        if ((getTimeQ2 != null)) {
            time2.setText( getResources().getString(R.string.duracion)+": "+ getTimeQ2 + " "+getResources().getString(R.string.seg));
        }
        else {
            time2.setText(getResources().getString(R.string.duracion)+": - "+getResources().getString(R.string.seg)+": ");
        }

        if ((getTimeQ3 != null)) {
            time3.setText(getResources().getString(R.string.duracion)+": "+ getTimeQ3 + " "+getResources().getString(R.string.seg));
        }
        else {
            time3.setText(getResources().getString(R.string.duracion)+": - "+getResources().getString(R.string.seg)+": ");
        }
    }

    public void setProgressBar() {
        int finalScore;
        int score1;
        int score2;
        int score3;

        if (getScoreQ1 != null) {
            score1 = Integer.parseInt(getScoreQ1);
        }
        else {
            score1 = 0;
        }
        if (getScoreQ2 != null) {
            score2 = Integer.parseInt(getScoreQ2);
        }
        else {
            score2 = 0;
        }
        if (getScoreQ3 != null) {
            score3 = Integer.parseInt(getScoreQ3);
        }
        else {
            score3 = 0;
        }

        finalScore = score1 + score2 + score3;

        switch(finalScore){
            case 0:
                progressBar.setProgress(0);
                percentage.setText("0%");
                message.setText(getResources().getString(R.string.caso_0));
                break;
            case 1:
                progressBar.setProgress(11);
                percentage.setText("11%");
                message.setText(getResources().getString(R.string.caso_1));
                break;
            case 2:
                progressBar.setProgress(22);
                percentage.setText("22%");
                message.setText(getResources().getString(R.string.caso_1));
                break;
            case 3:
                progressBar.setProgress(33);
                percentage.setText("33%");
                message.setText(getResources().getString(R.string.caso_1));
                break;
            case 4:
                progressBar.setProgress(44);
                percentage.setText("44%");
                message.setText(getResources().getString(R.string.caso_2));
                break;
            case 5:
                progressBar.setProgress(55);
                percentage.setText("55%");
                message.setText(getResources().getString(R.string.caso_2));
                break;
            case 6:
                progressBar.setProgress(66);
                percentage.setText("66%");
                message.setText(getResources().getString(R.string.caso_2));
                break;
            case 7:
                progressBar.setProgress(77);
                percentage.setText("77%");
                message.setText(getResources().getString(R.string.caso_3));
                break;
            case 8:
                progressBar.setProgress(88);
                percentage.setText("88%");
                message.setText(getResources().getString(R.string.caso_3));
                break;
            case 9:
                progressBar.setProgress(100);
                percentage.setText("100%");
                message.setText(getResources().getString(R.string.caso_4));
                break;
            default:
                break;
        }

    }


}