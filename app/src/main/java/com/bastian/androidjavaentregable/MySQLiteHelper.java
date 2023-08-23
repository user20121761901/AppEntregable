package com.bastian.androidjavaentregable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLA = "empresa";
    private static final String DATABASE = "AndroidEntregable.db";
    private static final int VERSION = 1;
    private static final String QUERYSQL = "CREATE TABLE " + TABLA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, razonSocial TEXT NOT NULL, ruc TEXT NOT NULL);";

    public MySQLiteHelper(Context contexto) {
        super(contexto, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERYSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        String mensaje = "Actualizando la version de la base de datos numero: " + versionAntigua + " a la " + versionNueva;
        Log.w(MySQLiteHelper.class.getName(), mensaje);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
        onCreate(db);
    }
}
