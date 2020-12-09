package com.example.e_quality.Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Toast;

import com.example.e_quality.Principal.AboutActivity;
import com.example.e_quality.Alarmas.AlarmaActivity;
import com.example.e_quality.R;
import com.example.e_quality.Tablas.QuizSQLite;
import com.example.e_quality.Tablas.ScoresSQLite;
import com.example.e_quality.Tablas.TimeSQLite;

import java.util.Date;
import java.util.Locale;


public class QuizIgualdad extends AppCompatActivity {

    private QuizSQLite quizSQLite;
    private ScoresSQLite scoresSQLite;
    private ListView listView1, listView2, listView3;
    static public Date currentDate;
    static public Date endDate1, endDate2, endDate3;
    private TimeSQLite timeSQLite;

    private int id_answers [] = {R.id.text1_answer1, R.id.text1_answer2, R.id.text1_answer3,
            R.id.text2_answer1, R.id.text2_answer2, R.id.text2_answer3,
            R.id.text3_answer1, R.id.text3_answer2, R.id.text3_answer3};
    private int id_questions [] = {R.id.text_question1, R.id.text_question2, R.id.text_question3};
    private int id_label_question [] = {R.id.label_question1, R.id.label_question2, R.id.label_question3};

    private String id [] = {"28", "29", "30", "31", "32", "33", "34", "35", "36"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        quizSQLite = new QuizSQLite(this);
        scoresSQLite = new ScoresSQLite(this);
        timeSQLite = new TimeSQLite(this);

        listView1 = (ListView) findViewById(R.id.listView1_Quiz);
        listView2 = (ListView) findViewById(R.id.listView2_Quiz);
        listView3 = (ListView) findViewById(R.id.listView3_Quiz);

        //Se cargan los valores en la bbdd
        quizSQLite.insertData();

        //Se obtiene la fecha de entrada al cuestionario
        currentDate = new Date();

        //Seleccionamos tema de Fisica en funcion del boton pulsado
        selectInsert();
    }

    //Se comprueba a que boton se pulso (tema 1, tema 2, tema 3)
    public void selectInsert() {
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("ButtonId", 0);

        if (intValue == R.id.quizI1){
            insertQuiz1();
        }
        else if (intValue == R.id.quizI2){
            insertQuiz2();
        }
        else if (intValue == R.id.quizI3){
            insertQuiz3();
        }
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



    private void insertQuiz1 (){
        Cursor getQuestion1 = quizSQLite.getQuiz(id[0]);
        Cursor getQuestion2 = quizSQLite.getQuiz(id[1]);
        Cursor getQuestion3 = quizSQLite.getQuiz(id[2]);

        String[] fromColumns1 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews1 = new int[]{id_questions[0], id_label_question[0], id_answers[0], id_answers[2], id_answers[1]};
        SimpleCursorAdapter putQuestions1 = new SimpleCursorAdapter(this, R.layout.quiz1, getQuestion1, fromColumns1, toViews1, 0);
        listView1.setAdapter(putQuestions1);

        String[] fromColumns2 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews2 = new int[]{id_questions[1], id_label_question[1], id_answers[5], id_answers[3], id_answers[4]};
        SimpleCursorAdapter putQuestions2 = new SimpleCursorAdapter(this, R.layout.quiz2, getQuestion2, fromColumns2, toViews2, 0);
        listView2.setAdapter(putQuestions2);

        String[] fromColumns3 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews3 = new int[]{id_questions[2], id_label_question[2], id_answers[7], id_answers[6], id_answers[8]};
        SimpleCursorAdapter putQuestions3 = new SimpleCursorAdapter(this, R.layout.quiz3, getQuestion3, fromColumns3, toViews3, 0);
        listView3.setAdapter(putQuestions3);
    }

    private void insertQuiz2 (){
        Cursor getQuestion1 = quizSQLite.getQuiz(id[3]);
        Cursor getQuestion2 = quizSQLite.getQuiz(id[4]);
        Cursor getQuestion3 = quizSQLite.getQuiz(id[5]);

        String[] fromColumns1 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews1 = new int[]{id_questions[0], id_label_question[0], id_answers[2], id_answers[0], id_answers[1]};
        SimpleCursorAdapter putQuestions1 = new SimpleCursorAdapter(this, R.layout.quiz1, getQuestion1, fromColumns1, toViews1, 0);
        listView1.setAdapter(putQuestions1);

        String[] fromColumns2 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews2 = new int[]{id_questions[1], id_label_question[1], id_answers[4], id_answers[5], id_answers[3]};
        SimpleCursorAdapter putQuestions2 = new SimpleCursorAdapter(this, R.layout.quiz2, getQuestion2, fromColumns2, toViews2, 0);
        listView2.setAdapter(putQuestions2);

        String[] fromColumns3 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews3 = new int[]{id_questions[2], id_label_question[2], id_answers[8], id_answers[6], id_answers[7]};
        SimpleCursorAdapter putQuestions3 = new SimpleCursorAdapter(this, R.layout.quiz3, getQuestion3, fromColumns3, toViews3, 0);
        listView3.setAdapter(putQuestions3);
    }

    private void insertQuiz3 (){
        Cursor getQuestion1 = quizSQLite.getQuiz(id[6]);
        Cursor getQuestion2 = quizSQLite.getQuiz(id[7]);
        Cursor getQuestion3 = quizSQLite.getQuiz(id[8]);

        String[] fromColumns1 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews1 = new int[]{id_questions[0], id_label_question[0], id_answers[1], id_answers[0], id_answers[2]};
        SimpleCursorAdapter putQuestions1 = new SimpleCursorAdapter(this, R.layout.quiz1, getQuestion1, fromColumns1, toViews1, 0);
        listView1.setAdapter(putQuestions1);

        String[] fromColumns2 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews2 = new int[]{id_questions[1], id_label_question[1], id_answers[3], id_answers[5], id_answers[4]};
        SimpleCursorAdapter putQuestions2 = new SimpleCursorAdapter(this, R.layout.quiz2, getQuestion2, fromColumns2, toViews2, 0);
        listView2.setAdapter(putQuestions2);

        String[] fromColumns3 = new String[]{QuizSQLite.COL_QUESTION, QuizSQLite.COL_TOPIC, QuizSQLite.COL_CORRECT_ANSWER, QuizSQLite.COL_INCORRECT_ANSWER1, QuizSQLite.COL_INCORRECT_ANSWER2};
        int[] toViews3 = new int[]{id_questions[2], id_label_question[2], id_answers[6], id_answers[7], id_answers[8]};
        SimpleCursorAdapter putQuestions3 = new SimpleCursorAdapter(this, R.layout.quiz3, getQuestion3, fromColumns3, toViews3, 0);
        listView3.setAdapter(putQuestions3);
    }



    public void clickCheckAnswers (View view){
        boolean [] isCorrect = new boolean[3];
        int corrects = 0, incorrects = 0;
        String result;
        RadioButton r1, r2, r3;
        int score1, score2, score3;
        String finalScore;
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("ButtonId", 0);

        if (intValue == R.id.quizI1){
            //Radiobuttons donde se encuentran las respuestas correctas
            r1 = (RadioButton) findViewById(id_answers[0]);
            r2 = (RadioButton) findViewById(id_answers[5]);
            r3 = (RadioButton) findViewById(id_answers[7]);

            if (r1.isChecked()) {
                isCorrect[0] = true;
                corrects++;
                score1 = 1;
            } else {
                isCorrect[0] = false;
                incorrects++;
                score1 = 0;
            }

            if (r2.isChecked()) {
                isCorrect[1] = true;
                corrects++;
                score2 = 1;
            } else {
                isCorrect[1] = false;
                incorrects++;
                score2 = 0;
            }

            if (r3.isChecked()) {
                isCorrect[2] = true;
                corrects++;
                score3 = 1;
            } else {
                isCorrect[2] = false;
                incorrects++;
                score3 = 0;
            }
            finalScore = Integer.toString(score1 + score2 + score3);
            scoresSQLite.insertPoints(finalScore,"10");
            endDate1 = new Date();
            calculateQuizTime("10", endDate1);
        }

        if (intValue == R.id.quizI2){
            //Radiobuttons donde se encuentran las respuestas correctas
            r1 = (RadioButton) findViewById(id_answers[1]);
            r2 = (RadioButton) findViewById(id_answers[4]);
            r3 = (RadioButton) findViewById(id_answers[8]);

            if (r1.isChecked()) {
                isCorrect[0] = true;
                corrects++;
                score1 = 1;
            } else {
                isCorrect[0] = false;
                incorrects++;
                score1 = 0;
            }

            if (r2.isChecked()) {
                isCorrect[1] = true;
                corrects++;
                score2 = 1;
            } else {
                isCorrect[1] = false;
                incorrects++;
                score2 = 0;
            }

            if (r3.isChecked()) {
                isCorrect[2] = true;
                corrects++;
                score3 = 1;
            } else {
                isCorrect[2] = false;
                incorrects++;
                score3 = 0;
            }
            finalScore = Integer.toString(score1 + score2 + score3);
            scoresSQLite.insertPoints(finalScore, "11");
            endDate2 = new Date();
            calculateQuizTime("11", endDate2);
        }

        if (intValue == R.id.quizI3){
            //Radiobuttons donde se encuentran las respuestas correctas
            r1 = (RadioButton) findViewById(id_answers[1]);
            r2 = (RadioButton) findViewById(id_answers[3]);
            r3 = (RadioButton) findViewById(id_answers[6]);

            if (r1.isChecked()) {
                isCorrect[0] = true;
                corrects++;
                score1 = 1;
            } else {
                isCorrect[0] = false;
                incorrects++;
                score1 = 0;
            }

            if (r2.isChecked()) {
                isCorrect[1] = true;
                corrects++;
                score2 = 1;
            } else {
                isCorrect[1] = false;
                incorrects++;
                score2 = 0;
            }

            if (r3.isChecked()) {
                isCorrect[2] = true;
                corrects++;
                score3 = 1;
            } else {
                isCorrect[2] = false;
                incorrects++;
                score3 = 0;
            }
            finalScore = Integer.toString(score1 + score2 + score3);
            scoresSQLite.insertPoints(finalScore, "12");
            endDate3 = new Date();
            calculateQuizTime("12", endDate3);
        }

        if ((isCorrect[0] == true) && (isCorrect[1] == true) && (isCorrect[2] == true)) {
            result = String.format(Locale.getDefault(), getResources().getString(R.string.perfecto));
            Toast.makeText(QuizIgualdad.this, result, Toast.LENGTH_LONG).show();
        } else if ((isCorrect[0] == false) && (isCorrect[1] == false) && (isCorrect[2] == false)) {
            result = String.format(Locale.getDefault(), getResources().getString(R.string.mal));
            Toast.makeText(QuizIgualdad.this, result, Toast.LENGTH_LONG).show();
        } else {
            result = String.format(Locale.getDefault(), getResources().getString(R.string.correctas)+": %d -- "+getResources().getString(R.string.incorrectas)+": %d", corrects, incorrects);
            Toast.makeText(QuizIgualdad.this, result, Toast.LENGTH_LONG).show();
        }

    }

    public void calculateQuizTime(String number, Date endDate){
        long diff, diffSeconds;
        String duration;
        Date finalTime = endDate, initialTime = currentDate;

        if (number == "10") {
            if (initialTime != null) {
                diff = finalTime.getTime() - initialTime.getTime();
                diffSeconds = diff / 1000;
                duration = String.valueOf(diffSeconds);
                timeSQLite.insertTime(duration, number);
            }
        }

        if (number == "11") {
            if (initialTime != null) {
                diff = finalTime.getTime() - initialTime.getTime();
                diffSeconds = diff / 1000;
                duration = String.valueOf(diffSeconds);
                timeSQLite.insertTime(duration, number);
            }
        }

        if (number == "12") {
            if (initialTime != null) {
                diff = finalTime.getTime() - initialTime.getTime();
                diffSeconds = diff / 1000;
                duration = String.valueOf(diffSeconds);
                timeSQLite.insertTime(duration, number);
            }
        }
    }

}

