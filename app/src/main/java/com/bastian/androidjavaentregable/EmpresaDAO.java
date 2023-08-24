package com.bastian.androidjavaentregable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EmpresaDAO {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public EmpresaDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long Insertar(String razonSocial, String ruc){
        long estado = 0;
        try {
            ContentValues valores = new ContentValues();
            valores.put("razonSocial", razonSocial);
            valores.put("ruc", ruc);
            estado = database.insert(MySQLiteHelper.TABLA,null,valores);
        } catch (Exception e) {
            estado = 0;
        }
        return estado;
    }

    public long EliminarRegistro(int id) {
        long estado = 0;
        try {
            estado = database.delete(MySQLiteHelper.TABLA,"id=?", new String[] {String.valueOf(id)});
        } catch (Exception e){
            estado = 0;
        }
        return estado;
    }

    public ArrayList<Empresa>ListadoGeneral(){
        Cursor c;
        ArrayList<Empresa> listado = new ArrayList<Empresa>();
        c = database.rawQuery("SELECT * FROM empresa ", null);
        while(c.moveToNext()) {
            Empresa objEmpresa = new Empresa();
            objEmpresa.setId(c.getInt(0));
            objEmpresa.setRuc(c.getString(1));
            objEmpresa.setRazonSocial(c.getString(2));
            listado.add(objEmpresa);
        }
        c.close();
        return listado;
    }
}
