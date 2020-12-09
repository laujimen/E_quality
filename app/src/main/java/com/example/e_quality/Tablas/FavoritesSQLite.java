package com.example.e_quality.Tablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FavoritesSQLite extends SQLiteOpenHelper {

    //Nombre de la base de datos
    public static final String TABLE_FAVORITOS= "FAVORITOS";

    //Columnas de la base de datos FAVORITOS
    public static final String COL_ID="_id";
    public static final String COL_NOMBRE="NOMBRE";
    public static final String COL_LATITUD="LATITUD";
    public static final String COL_LONGITUD="LONGITUD";

    private final Context mCtx;
    private SQLiteDatabase dbWrite = this.getWritableDatabase();
    SQLiteDatabase dbRead = this.getReadableDatabase();
    static int numb = 0;

    //Constructor de la clase FavoritesSQLite
    public FavoritesSQLite(Context ctx){
        super(ctx, TABLE_FAVORITOS,null,1);
        this.mCtx=ctx;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Sentencia SQL para crear la tabla de la base de datos
        String CREATE_TABLE_FAV= "CREATE TABLE " + TABLE_FAVORITOS + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOMBRE + " TEXT," +
                COL_LATITUD + " TEXT, " +
                COL_LONGITUD + " TEXT);" ;
        db.execSQL(CREATE_TABLE_FAV);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);
        onCreate(db);
    }

    // Metodo para insertar datos en la base de datos
    public void insertFavorito(String name, String lat, String lng){
        ContentValues values= new ContentValues();
        numb ++;

        values.put(COL_NOMBRE, name);
        values.put(COL_LATITUD, lat);
        values.put(COL_LONGITUD, lng);

        dbWrite.insert(TABLE_FAVORITOS, null, values);
    }

    //Método para obtener los datos de la base de datos

    public Cursor getFavorito(){
        String query="SELECT " + COL_NOMBRE + ", "+ COL_LATITUD +"," + COL_LONGITUD +" FROM " + TABLE_FAVORITOS + ";";
        Cursor cursor_favorito= dbRead.rawQuery(query, null);
        return cursor_favorito;
    }

}
