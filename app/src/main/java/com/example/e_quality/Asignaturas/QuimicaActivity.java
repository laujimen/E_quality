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
import com.example.e_quality.Quiz.QuizQuimica;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.QuizSQLite;
import com.example.e_quality.Tablas.ScoresSQLite;
import com.example.e_quality.Quiz.TotalScoreQuimicaActivity;

public class QuimicaActivity extends AppCompatActivity {

    private LinearLayout MarieCurie, SusanSolomon;
    private static TextView scoreQ1, scoreQ2, scoreQ3;
    private QuizSQLite quizSQLite;
    private ScoresSQLite scoresSQLite;
    private static String getScoreQ1 = null, getScoreQ2 = null, getScoreQ3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quimica);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        quizSQLite = new QuizSQLite(this);
        scoresSQLite = new ScoresSQLite(this);

        scoreQ1 = (TextView) findViewById(R.id.scoreViewQ1);
        scoreQ2 = (TextView) findViewById(R.id.scoreViewQ2);
        scoreQ3 = (TextView) findViewById(R.id.scoreViewQ3);

        MarieCurie = (LinearLayout) findViewById(R.id.MarieCurie);
        SusanSolomon = (LinearLayout) findViewById(R.id.SusanSolomon);

        getScoreQ1 = scoresSQLite.getScore ("4");
        getScoreQ2 = scoresSQLite.getScore ("5");
        getScoreQ3 = scoresSQLite.getScore ("6");

        if ((getScoreQ1 != null)){
            scoreQ1.setText(getScoreQ1 + "/3");
        }
        if ((getScoreQ2 != null)){
            scoreQ2.setText(getScoreQ2 + "/3");
        }
        if ((getScoreQ3 != null)) {
            scoreQ3.setText(getScoreQ3 + "/3");
        }

        if((scoreQ1.getText().equals("2/3"))||(scoreQ1.getText().equals("3/3"))){
            MarieCurie.setAlpha(1);
        }
        if((scoreQ2.getText().equals("2/3"))||(scoreQ2.getText().equals("3/3"))){
            SusanSolomon.setAlpha(1);
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

        if (intValue == R.id.angelaBelcher) {
            startActivity(cientifica);
        } else {

        }
        if ((scoreQ1.getText().equals("2/3")) || (scoreQ1.getText().equals("3/3"))) {
            Intent cientificas = new Intent(this, MujeresActivity.class);
            cientificas.putExtra("ButtonId", view.getId());
            intValue = cientificas.getIntExtra("ButtonId", 0);

            if (intValue == R.id.marieCurie) {
                startActivity(cientificas);
            } else {

            }
        }
        if ((scoreQ2.getText().equals("2/3")) || (scoreQ2.getText().equals("3/3"))) {
            Intent cientificas = new Intent(this, MujeresActivity.class);
            cientificas.putExtra("ButtonId", view.getId());
            intValue = cientificas.getIntExtra("ButtonId", 0);

            if (intValue == R.id.susanSolomon) {
                startActivity(cientificas);
            } else {

            }
        }
    }

        public void ListaMujeres(View view){
            Intent fisicas = new Intent(this, Wikipedia.class);
            fisicas.putExtra("ButtonId", view.getId());
            startActivity(fisicas);
        }

        public void QuizQuimica (View view){
            int intValue;
            Intent QuizQuimica = new Intent(this, com.example.e_quality.Quiz.QuizQuimica.class);
            QuizQuimica.putExtra("ButtonId", view.getId());
            intValue = QuizQuimica.getIntExtra("ButtonId", 0);

            if (intValue == R.id.quizQ1) {
                startActivity(QuizQuimica);
            } else {

            }
            if ((scoreQ1.getText().equals("2/3")) || (scoreQ1.getText().equals("3/3"))) {
                Intent QuizQuimica1 = new Intent(this, QuizQuimica.class);
                QuizQuimica1.putExtra("ButtonId", view.getId());
                intValue = QuizQuimica1.getIntExtra("ButtonId", 0);

                if (intValue == R.id.quizQ2) {
                    startActivity(QuizQuimica1);
                } else {

                }
            }
            if ((scoreQ2.getText().equals("2/3")) || (scoreQ2.getText().equals("3/3"))) {
                Intent QuizQuimica1 = new Intent(this, QuizQuimica.class);
                QuizQuimica1.putExtra("ButtonId", view.getId());
                intValue = QuizQuimica1.getIntExtra("ButtonId", 0);

                if (intValue == R.id.quizQ3) {
                    startActivity(QuizQuimica1);
                } else {

                }
            }
        }

        public void TotalScoreQ (View view){
            Intent scores = new Intent(this, TotalScoreQuimicaActivity.class);
            scores.putExtra("ButtonId", view.getId());
            startActivity(scores);
        }


}
