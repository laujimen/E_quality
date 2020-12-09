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
import com.example.e_quality.Quiz.QuizBiologia;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.QuizSQLite;
import com.example.e_quality.Tablas.ScoresSQLite;
import com.example.e_quality.Quiz.TotalScoreBiologaActivity;

public class BiologaActivity extends AppCompatActivity {

    private LinearLayout MariaEugenia, ZehraSayers;
    private static TextView scoreB1, scoreB2, scoreB3;
    private QuizSQLite quizSQLite;
    private ScoresSQLite scoresSQLite;
    private static String getScoreB1 = null, getScoreB2 = null, getScoreB3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biologa);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        quizSQLite = new QuizSQLite(this);
        scoresSQLite = new ScoresSQLite(this);

        scoreB1 = (TextView) findViewById(R.id.scoreViewB1);
        scoreB2 = (TextView) findViewById(R.id.scoreViewB2);
        scoreB3 = (TextView) findViewById(R.id.scoreViewB3);

        MariaEugenia = (LinearLayout) findViewById(R.id.MariaEugenia);
        ZehraSayers = (LinearLayout) findViewById(R.id.ZehraSayers);

        getScoreB1 = scoresSQLite.getScore ("7");
        getScoreB2 = scoresSQLite.getScore ("8");
        getScoreB3 = scoresSQLite.getScore ("9");

        if ((getScoreB1 != null)){
            scoreB1.setText(getScoreB1 + "/3");
        }
        if ((getScoreB2 != null)){
            scoreB2.setText(getScoreB2 + "/3");
        }
        if ((getScoreB3 != null)){
            scoreB3.setText(getScoreB3 + "/3");
        }

        if((scoreB1.getText().equals("2/3"))||(scoreB1.getText().equals("3/3"))){
            MariaEugenia.setAlpha(1);
        }
        if((scoreB2.getText().equals("2/3"))||(scoreB2.getText().equals("3/3"))){
            ZehraSayers.setAlpha(1);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);
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

        if (intValue == R.id.dianFossey) {
            startActivity(cientifica);
        }
        else{

        }
        if((scoreB1.getText().equals("2/3"))||(scoreB1.getText().equals("3/3"))){
            Intent cientificas = new Intent(this, MujeresActivity.class);
            cientificas.putExtra("ButtonId", view.getId());
            intValue = cientificas.getIntExtra("ButtonId", 0);

            if (intValue == R.id.mariaEugenia) {
                startActivity(cientificas);
            }
            else{

            }
        }
        if((scoreB2.getText().equals("2/3"))||(scoreB2.getText().equals("3/3"))){
            Intent cientificas = new Intent(this, MujeresActivity.class);
            cientificas.putExtra("ButtonId", view.getId());
            intValue = cientificas.getIntExtra("ButtonId", 0);

            if (intValue == R.id.zehraSayers) {
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

   public void QuizBiologia(View view) {
       int intValue;
       Intent QuizBiologia = new Intent(this, com.example.e_quality.Quiz.QuizBiologia.class);
       QuizBiologia.putExtra("ButtonId", view.getId());
       intValue = QuizBiologia.getIntExtra("ButtonId", 0);

       if (intValue == R.id.quizB1) {
           startActivity(QuizBiologia);
       }
       else{

       }
       if((scoreB1.getText().equals("2/3"))||(scoreB1.getText().equals("3/3"))){
           Intent QuizBiologia1 = new Intent(this, QuizBiologia.class);
           QuizBiologia1.putExtra("ButtonId", view.getId());
           intValue = QuizBiologia1.getIntExtra("ButtonId", 0);

           if (intValue == R.id.quizB2) {
               startActivity(QuizBiologia1);
           }
           else{

           }
       }
       if((scoreB2.getText().equals("2/3"))||(scoreB2.getText().equals("3/3"))){
           Intent QuizBiologia1 = new Intent(this, QuizBiologia.class);
           QuizBiologia1.putExtra("ButtonId", view.getId());
           intValue = QuizBiologia1.getIntExtra("ButtonId", 0);

           if (intValue == R.id.quizB3) {
               startActivity(QuizBiologia1);
           }
           else{

           }
       }
   }

    public void TotalScoreB (View view) {
        Intent scores = new Intent(this, TotalScoreBiologaActivity.class);
        scores.putExtra("ButtonId", view.getId());
        startActivity(scores);
    }

}
