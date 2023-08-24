package com.bastian.androidjavaentregable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener {
    private EmpresaDAO objEmpresa;
    EditText txtRazonSocial, txtRUC;
    ArrayList<Empresa> lista = null;
    Button btnRegistrar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRazonSocial = (EditText) findViewById(R.id.etRazonS);
        txtRUC = (EditText) findViewById(R.id.etRuc);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnRegistrar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

        objEmpresa = new EmpresaDAO(this);
        objEmpresa.open();
    }

    public void Grabar() {
        String razonSocial = txtRazonSocial.getText().toString();
        String ruc = txtRUC.getText().toString();
        long i = 0;
        i = objEmpresa.Insertar(razonSocial, ruc);

        if (i == 0) {
            Toast.makeText(getApplicationContext(), "Registro No Insertado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Registro Insertado", Toast.LENGTH_LONG).show();
            txtRazonSocial.getText().clear();
            txtRUC.getText().clear();
            txtRazonSocial.requestFocus();
        }
    }

    public void cargarTabla() {
        String acum = "";
        try {
            lista = objEmpresa.ListadoGeneral();
            for (Empresa obj : lista) {
                acum += obj.getRazonSocial() + " " + obj.getRuc() + "\n";
            }
            Toast.makeText(getApplicationContext(), acum, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View view) {
        if (btnRegistrar == view) {
            Grabar();
        }
        if (btnRegistrar == view) {
            cargarTabla();
        }
    }
}