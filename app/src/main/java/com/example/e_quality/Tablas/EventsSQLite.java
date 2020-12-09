package com.example.e_quality.Tablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//CÓDIGO BASADO: https://www.youtube.com/watch?v=tCptJSP-mCY&list=PLO4xvhqnWKL0R5ZO7L831NxKNet_aOSfz
public class EventsSQLite extends SQLiteOpenHelper {

   //nombre de la base de datos
    public static final String EVENTOS = "EVENTOS";

    //Columnas de la base de datos EVENTOS
    public static final String COL_ID = "_id";
    public static final String COL_NUMBER = "NUMERO";
    public static final String COL_NAME = "NOMBRE";
    public static final String COL_LOCATION = "UBICACION";
    public static final String COL_DATE_FROM = "FECHA_DESDE";
    public static final String COL_DATE_TO = "FECHA_HASTA";
    public static final String COL_DESCRIPTION = "DESCRIPCION";

    private final Context mCtx;
    private SQLiteDatabase dbWrite = this.getWritableDatabase();
    SQLiteDatabase dbRead = this.getReadableDatabase();
    static int numb = 0;

    //Constructor de la clase QuizSQLite
    public EventsSQLite(Context ctx) {
        super(ctx, EVENTOS, null, 2);
        this.mCtx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTOS= "CREATE TABLE " + EVENTOS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NUMBER + " TEXT, " +
                COL_NAME + " TEXT, " +
                COL_LOCATION + " TEXT, " +
                COL_DATE_FROM + " TEXT, " +
                COL_DATE_TO + " TEXT, " +
                COL_DESCRIPTION + " TEXT);";
        db.execSQL(CREATE_EVENTOS);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENTOS);
        onCreate(db);

    }

    public void insertEvents (String number, String name, String location, String dateFrom, String dateTo, String description) {
        ContentValues values = new ContentValues();
        numb++;

        if (numb != Integer.parseInt(number)) {
            dbWrite.delete(EVENTOS, COL_NUMBER + "=" + number, null);
        }
        values.put(COL_NAME, name);
        values.put(COL_NUMBER, number);
        values.put(COL_LOCATION, location);
        values.put(COL_DATE_FROM, dateFrom);
        values.put(COL_DATE_TO, dateTo);
        values.put(COL_DESCRIPTION, description);
        dbWrite.insert(EVENTOS, null, values);
    }

    public Cursor getEvents (String string) {
        String query = " SELECT * FROM " + EVENTOS +
                " WHERE " + COL_DATE_FROM + "=" + "\"" + string + "\"" + ";";
        Cursor events = dbRead.rawQuery(query, null);
        return events;
    }

    public void deleteEvent (String name) {
        String query = " SELECT " + COL_NUMBER + " FROM " + EVENTOS +
                " WHERE " + COL_NAME + "=" + "\"" + name + "\"" + ";";
        Cursor events = dbRead.rawQuery(query, null);

        String number;
        if (events.getCount() != 0) {
            String [] arrayString;
            String string = "";
            events.moveToFirst();

            do {
                string = string + events.getString(events.getColumnIndex(COL_NUMBER)) + "#";
            } while(events.moveToNext());

            arrayString = string.split("#");
            if (string.indexOf("null") != -1){
                number = null;
            }
            else {
                number = arrayString[0];
            }
        }
        else {
            number = null;
        }

        if (number != null) {
            dbWrite.delete(EVENTOS, COL_NUMBER + "=" + number, null);
        }
    }
}
