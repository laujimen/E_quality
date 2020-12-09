package com.example.e_quality.Tablas;

import android.content.ContentValues;
import android.content.Context;
import android.content.RestrictionEntry;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.appcompat.app.AppCompatCallback;

import com.example.e_quality.R;

import com.example.e_quality.R;


//Clase que crea la base de datos necesaria para cargar la información a mostrar en el apartado autoevaluación
public class QuizSQLite extends SQLiteOpenHelper {

    //Nombre de la base de datos
    public static final String TABLE_QUIZ = "CUESTIONARIO";

    //Columnas de la base de datos CUESTIONARIO
    public static final String COL_ID = "_id";
    public static final String COL_NUMBER = "NUMERO";
    public static final String COL_TOPIC = "TEMA";
    public static final String COL_TYPE = "TIPO";
    public static final String COL_QUESTION = "PREGUNTA";
    public static final String COL_CORRECT_ANSWER = "RESPUESTA_CORRECTA";
    public static final String COL_INCORRECT_ANSWER1 = "RESPUESTA_INCORRECTA_1";
    public static final String COL_INCORRECT_ANSWER2 = "RESPUESTA_INCORRECTA_2";
    private final Context mCtx;
    private SQLiteDatabase dbWrite = this.getWritableDatabase();


     //Constructor de la clase QuizSQLite
    public QuizSQLite(Context ctx) {
        super(ctx, TABLE_QUIZ, null, 2);
        this.mCtx = ctx;
    }
    //Se crea un db de lectura
    SQLiteDatabase dbRead = this.getReadableDatabase();




     //Generar la tabla en la dbRead --> es llamado cuando cuando el dbRead se crea por primera vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Sentencia SQL para crear las tablas de las bases de datos
        String CREATE_TABLE_QUIZ = "CREATE TABLE " + TABLE_QUIZ + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NUMBER + " TEXT, " +
                COL_TOPIC + " TEXT, " +
                COL_TYPE + " TEXT, " +
                COL_QUESTION + " TEXT, " +
                COL_CORRECT_ANSWER + " TEXT, " +
                COL_INCORRECT_ANSWER1 + " TEXT, " +
                COL_INCORRECT_ANSWER2 + " TEXT);" ;
        db.execSQL(CREATE_TABLE_QUIZ);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
    onCreate(db);
    }

    //Recoge la informacion de la tabla correspondfiente a las distintas asignaturas y temas (personalización del quiz1)
    public Cursor getQuiz (String id){
        String query = " SELECT * FROM " + TABLE_QUIZ +
                " WHERE " + COL_NUMBER + "=" + "\"" + id + "\"" + ";";
        Cursor questions = dbRead.rawQuery(query, null);
        return questions;
    }

    //Inserta datos en las filas de la tabla Cuestionario
    public void insertData (){

        ContentValues values = new ContentValues();



        if (dbWrite!=null) {
             dbWrite.delete(TABLE_QUIZ, null, null);

             //--------------------------- F I S I C A -----------------------------//
             /////////  T E M A  1 //////////
             values.put(COL_NUMBER, "1");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Fisica1");

             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.setentaOcho));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.noventa));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.dosUno));

             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "2");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Fisica1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.asignatura));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.nuclear));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.cuantica));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.matematicas));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "3");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Fisica1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.descubrimiento));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.bomba));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.nucleares));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.elemento));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  2 //////////
             values.put(COL_NUMBER, "4");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Fisica2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.cincuenta));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.treintayseis));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.sesentaycinco));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "5");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Fisica2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.estudio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.particulas));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.gravedad));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.fisicaCuantica));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "6");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Fisica2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.clases));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.mecanica));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.cuerdas));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.teorica));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  3 //////////
             values.put(COL_NUMBER, "7");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Fisica3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.cincuentaUno));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.nueve));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.cincuentaSeis));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "8");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Fisica3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.investigacion));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.agujerosNegros));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.estrellas));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.bigbang));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "9");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Fisica3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.objetivo));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.identificar));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.explicar));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.encontrar));
             dbWrite.insert(TABLE_QUIZ, null, values);

             //--------------------------- Q U I M I C A -----------------------------//
             /////////  T E M A  1 //////////
             values.put(COL_NUMBER, "10");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Quimica1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.queEstudio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.ingenieriaBilogica));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.ingenieriaQuimica));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.ingenieriaIndustriales));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "11");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Quimica1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.demostro));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.virus));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.cuerdas));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.gravedad));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "12");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Quimica1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.tipoVirus));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.M13));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.A14));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.N15));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  2 //////////
             values.put(COL_NUMBER, "13");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Quimica2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.sesentaSiete));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.sesentaNueve));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.ochentaNueve));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "14");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Quimica2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.elementoDesc));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.radio));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.estonio));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.niquel));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "15");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Quimica2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.premioNobel));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.quimica));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.fisica));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.mates));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  3 //////////
             values.put(COL_NUMBER, "16");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Quimica3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.novecientos56));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.sesentaNueve));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.ochentaNueve));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "17");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Quimica3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.libro));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.marcha));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.ozono));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.atmosfera));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "18");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Quimica3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.glaciar));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.solomon));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.herodes));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.dios));
             dbWrite.insert(TABLE_QUIZ, null, values);

             //--------------------------- B I O L O G I A -----------------------------//
             /////////  T E M A  1 //////////
             values.put(COL_NUMBER, "19");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Bilogia1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.treintaDos));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.ochocientos32));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.novecientos56));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "20");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Bilogia1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.investigacionCampo));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.zoologia));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.bio));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.genetica));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "21");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Bilogia1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.carrera));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.gorilas));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.leones));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.elefantes));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  2 //////////
             values.put(COL_NUMBER, "22");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Bilogia2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.sesentaOcho));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.sesentaTres));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.noventaOcho));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "23");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Bilogia2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.especializacion));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.micro));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.bioquimica));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.biotecnologia));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "24");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Bilogia2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.nobel));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.konex));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.nobel));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.goya));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  3 //////////
             values.put(COL_NUMBER, "25");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Bilogia3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.noventaTres));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.cincuentaTres));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.ochentaNueve));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "26");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Bilogia3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.especializacion));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.estructural));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.biotecnologia));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.bioquimica));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "27");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Bilogia3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.primera));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.europeo));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.turco));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.americano));
             dbWrite.insert(TABLE_QUIZ, null, values);

             //--------------------------- I G U A L D A D -----------------------------//
             /////////  T E M A  1 //////////
             values.put(COL_NUMBER, "28");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Igualdad1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.cuarentaCinco));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.nueve));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.cincuenta));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "29");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Igualdad1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.feminismo));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.Fradical));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.Fliberal));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.Fabolicionista));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "30");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Igualdad1");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.escribio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.sexo));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.torpes));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.habitacion));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  2 //////////
             values.put(COL_NUMBER, "31");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Igualdad2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.noventaSiete));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.sesentaOcho));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.novecientos56));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "32");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Igualdad2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.fue));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.abolicionista));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.socialista));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.marxista));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "33");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Igualdad2");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.religion));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.cristiana));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.judia));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.musulmana));
             dbWrite.insert(TABLE_QUIZ, null, values);

             /////////  T E M A  3 //////////
             values.put(COL_NUMBER, "34");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question1));
             values.put(COL_TYPE, "Igualdad3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.anio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.quince));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.cincuentaSeis));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.treintaDos));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "35");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question2));
             values.put(COL_TYPE, "Igualdad3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.moviento));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.sufragio));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.liberacion));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.representacion));
             dbWrite.insert(TABLE_QUIZ, null, values);

             values.put(COL_NUMBER, "36");
             values.put(COL_TOPIC, this.mCtx.getResources().getString(R.string.question3));
             values.put(COL_TYPE, "Igualdad3");
             values.put(COL_QUESTION, this.mCtx.getResources().getString(R.string.escribio));
             values.put(COL_CORRECT_ANSWER, this.mCtx.getResources().getString(R.string.biblia));
             values.put(COL_INCORRECT_ANSWER1, this.mCtx.getResources().getString(R.string.mujer));
             values.put(COL_INCORRECT_ANSWER2, this.mCtx.getResources().getString(R.string.mas));
             dbWrite.insert(TABLE_QUIZ, null, values);

         }

    }
}
