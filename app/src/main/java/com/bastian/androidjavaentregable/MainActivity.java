package com.bastian.androidjavaentregable;

import android.app.Activity;
import android.content.Intent;
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
    Button btnRegistrar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        objEmpresa = new EmpresaDAO(this);
        objEmpresa.open();
    }

    private void initializeViews() {
        txtRazonSocial = (EditText) findViewById(R.id.etRazonS);
        txtRUC = (EditText) findViewById(R.id.etRuc);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnRegistrar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (btnRegistrar == view) {
            grabarEmpresa();
        }
        if (btnListar == view) {
            cargarTabla();
        }
    }

    public void grabarEmpresa() {
        String razonSocial = txtRazonSocial.getText().toString();
        String ruc = txtRUC.getText().toString();

        // Validación: Si los campos están vacíos
        if (razonSocial.isEmpty() || ruc.isEmpty()) {
            showToast("Por favor, complete todos los campos.");
            return;
        }

        // Validación: Si el RUC tiene 11 dígitos
        if (ruc.length() != 11) {
            showToast("El RUC debe contener exactamente 11 dígitos.");
            return;
        }

        // Validación: Si el RUC no comienza con "20" o "10"
        if (!ruc.startsWith("20") && !ruc.startsWith("10")) {
            showToast("El RUC no es válido.");
            return;
        }

        long i = 0;
        i = objEmpresa.Insertar(razonSocial, ruc);
        if (i == 0) {
            showToast("Registro No Insertado.");
        } else {
            showToast("Registro Insertado.");
            clearInputFields();
        }
    }

    public void cargarTabla() {
        ArrayList<Empresa> lista = objEmpresa.ListadoGeneral();
        Intent intent = new Intent(this, ListActivity.class);
        intent.putParcelableArrayListExtra("empresas", lista);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void clearInputFields() {
        txtRazonSocial.getText().clear();
        txtRUC.getText().clear();
        txtRazonSocial.requestFocus();
    }
}