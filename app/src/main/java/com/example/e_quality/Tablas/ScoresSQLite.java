package com.example.e_quality.Tablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoresSQLite extends SQLiteOpenHelper {

    //Nombre de la base de datos
    public static final String TABLE_SCORES = "PUNTUACIONES";

    //Columnas de la base de datos PUNTUACIONES
    public static final String COL_ID = "_id";
    public static final String COL_NUMBER = "NUMERO";
    public static final String COL_TOPIC_SCORE = "PUNTUACION_TEMA";

    private final Context mCtx;
    private SQLiteDatabase dbWrite = this.getWritableDatabase();
    SQLiteDatabase dbRead = this.getReadableDatabase();
    static int numb = 0;

    //Constructor de la clase QuizSQLite
    public ScoresSQLite(Context ctx) {
        super(ctx, TABLE_SCORES, null, 2);
        this.mCtx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Sentencia SQL para crear las tablas de las bases de datos
        String CREATE_TABLE_QUIZ = "CREATE TABLE " + TABLE_SCORES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NUMBER + " TEXT, " +
                COL_TOPIC_SCORE + " TEXT);" ;
        db.execSQL(CREATE_TABLE_QUIZ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    public String getScore (String number){
        String query = " SELECT " + COL_TOPIC_SCORE + " FROM " + TABLE_SCORES +
                " WHERE " + COL_NUMBER + "=" + "\"" + number + "\"" + ";";
        Cursor score = dbRead.rawQuery(query, null);

        String result;
        if (score.getCount() != 0) {
            String [] arrayString;
            String string = "";
            score.moveToFirst();

            do {
                string = string + score.getString(score.getColumnIndex(COL_TOPIC_SCORE)) + "#";
            } while(score.moveToNext());

            arrayString = string.split("#");
            if (string.indexOf("null") != -1){
                result = null;
            }
            else {
                result = arrayString[0];
            }
        }
        else {
            result = null;
        }
        return result;
    }

    public void insertPoints (String finalScore, String number) {
        ContentValues values = new ContentValues();
        numb++;

        if (finalScore != null) {
            if (numb != Integer.parseInt(number)) {
                dbWrite.delete(TABLE_SCORES, COL_NUMBER + "=" + number, null);
            }
            values.put(COL_TOPIC_SCORE, finalScore);
            values.put(COL_NUMBER, number);
            dbWrite.insert(TABLE_SCORES, null, values);
        }
    }

}
