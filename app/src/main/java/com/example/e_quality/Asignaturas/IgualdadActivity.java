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
import com.example.e_quality.Quiz.QuizIgualdad;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.ScoresSQLite;
import com.example.e_quality.Quiz.TotalScoreIgualdadActivity;

public class IgualdadActivity extends AppCompatActivity {

    private LinearLayout SojournerTruth, ElizabethCady;
    private static TextView scoreI1, scoreI2, scoreI3;
    private ScoresSQLite scoresSQLite;
    private static String getScoreI1 = null, getScoreI2 = null, getScoreI3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igualdad);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        scoresSQLite = new ScoresSQLite(this);

        scoreI1 = (TextView) findViewById(R.id.scoreViewI1);
        scoreI2 = (TextView) findViewById(R.id.scoreViewI2);
        scoreI3 = (TextView) findViewById(R.id.scoreViewI3);

        SojournerTruth = (LinearLayout) findViewById(R.id.SojournerTruth);
        ElizabethCady = (LinearLayout) findViewById(R.id.ElizabethCady);

        getScoreI1 = scoresSQLite.getScore ("10");
        getScoreI2 = scoresSQLite.getScore ("11");
        getScoreI3 = scoresSQLite.getScore ("12");

        if ((getScoreI1 != null)){
            scoreI1.setText(getScoreI1 + "/3");
        }
        if ((getScoreI2 != null)){
            scoreI2.setText(getScoreI2 + "/3");
        }
        if ((getScoreI3 != null)){
            scoreI3.setText(getScoreI3 + "/3");
        }

        if((scoreI1.getText().equals("2/3"))||(scoreI1.getText().equals("3/3"))){
            SojournerTruth.setAlpha(1);
        }
        if((scoreI2.getText().equals("2/3"))||(scoreI2.getText().equals("3/3"))){
            ElizabethCady.setAlpha(1);
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


    public void Mujeres(View view) {
        int intValue;
        Intent mujeres = new Intent(this, MujeresActivity.class);
        mujeres.putExtra("ButtonId", view.getId());
        intValue = mujeres.getIntExtra("ButtonId", 0);

        if (intValue == R.id.shulamithFirestone) {
            startActivity(mujeres);
        }
        else{

        }
        if((scoreI1.getText().equals("2/3"))||(scoreI1.getText().equals("3/3"))){
            Intent mujeres1 = new Intent(this, MujeresActivity.class);
            mujeres1.putExtra("ButtonId", view.getId());
            intValue = mujeres1.getIntExtra("ButtonId", 0);

            if (intValue == R.id.sojournerTruth) {
                startActivity(mujeres1);
            }
            else{

            }
        }
        if((scoreI2.getText().equals("2/3"))||(scoreI2.getText().equals("3/3"))){
            Intent mujeres1 = new Intent(this, MujeresActivity.class);
            mujeres1.putExtra("ButtonId", view.getId());
            intValue = mujeres1.getIntExtra("ButtonId", 0);

            if (intValue == R.id.elizabethCady) {
                startActivity(mujeres1);
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


    public void QuizIgualdad(View view) {
        int intValue;
        Intent QuizIgualdad = new Intent(this, com.example.e_quality.Quiz.QuizIgualdad.class);
        QuizIgualdad.putExtra("ButtonId", view.getId());
        intValue = QuizIgualdad.getIntExtra("ButtonId", 0);

        if (intValue == R.id.quizI1) {
            startActivity(QuizIgualdad);
        }
        else{

        }
        if((scoreI1.getText().equals("2/3"))||(scoreI1.getText().equals("3/3"))){
            Intent QuizIgualdad1 = new Intent(this, QuizIgualdad.class);
            QuizIgualdad1.putExtra("ButtonId", view.getId());
            intValue = QuizIgualdad1.getIntExtra("ButtonId", 0);

            if (intValue == R.id.quizI2) {
                startActivity(QuizIgualdad1);
            }
            else{

            }
        }
        if((scoreI2.getText().equals("2/3"))||(scoreI2.getText().equals("3/3"))){
            Intent QuizIgualdad1 = new Intent(this, QuizIgualdad.class);
            QuizIgualdad1.putExtra("ButtonId", view.getId());
            intValue = QuizIgualdad1.getIntExtra("ButtonId", 0);

            if (intValue == R.id.quizI3) {
                startActivity(QuizIgualdad1);
            }
            else{

            }
        }
    }

    public void TotalScoreI (View view) {
        Intent scores = new Intent(this, TotalScoreIgualdadActivity.class);
        scores.putExtra("ButtonId", view.getId());
        startActivity(scores);
    }
}
