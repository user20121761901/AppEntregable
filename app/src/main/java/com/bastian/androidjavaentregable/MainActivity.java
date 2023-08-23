package com.bastian.androidjavaentregable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private EmpresaDAO objEmpresa;
    EditText txtRazonSocial, txtRUC;
    ArrayList<Empresa> lista = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRazonSocial = (EditText) findViewById(R.id.etRazonS);
        txtRUC = (EditText) findViewById(R.id.etRuc);
        objEmpresa = new EmpresaDAO(this);
        objEmpresa.open();
    }

    public void Grabar(View view){
        String razonSocial = txtRazonSocial.getText().toString();
        String ruc = txtRUC.getText().toString();
        long i = 0;
        i = objEmpresa.Insertar(razonSocial, ruc);

        if(i==0){
            Toast.makeText(getApplicationContext(),"Registro No Insertado", 1000).show();
        } else {
            Toast.makeText(getApplicationContext(),"Registro Insertado", 1000).show();
            txtRazonSocial.getText().clear();
            txtRUC.getText().clear();
            txtRazonSocial.requestFocus();
        }
    }

    public void cargarTabla(View view){
        String acum = "";
        try {
            lista = objEmpresa.ListadoGeneral();
            for(Empresa obj:lista){
                acum += obj.getRazonSocial()+" "+obj.getRuc()+"\n";
            }
            Toast.makeText(getApplicationContext(), acum, 2000).show();
        } catch (Exception e){
        }
    }
}