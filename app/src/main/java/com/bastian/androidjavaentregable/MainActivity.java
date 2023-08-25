package com.bastian.androidjavaentregable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener {
    private static final int rucJuridica=20;
    private static final int rucNatural=10;
    private EmpresaDAO objEmpresa;
    EditText txtRazonSocial, txtRUC;
    Button btnRegistrar, btnListar;
    RadioButton rbJuridica, rbNatural;

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
        rbJuridica = (RadioButton) findViewById(R.id.rbJuridica);
        rbNatural = (RadioButton) findViewById(R.id.rbNatural);
    }

    @Override
    public void onClick(View view) {
        if (btnRegistrar == view) {
            grabarEmpresa();
        }
        if (btnListar == view) {
            if (rbJuridica.isChecked() || rbNatural.isChecked()) {
                int seleccionRadioButton = rbJuridica.isChecked() ? rucJuridica : (rbNatural.isChecked() ? rucNatural : 0);
                cargarTabla(seleccionRadioButton);
            } else {
                showToast("Por favor, seleccione persona Juridíca o Nataural.");
            }
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

    public void cargarTabla(int seleccionRadioButton) {
        ArrayList<Empresa> lista = objEmpresa.ListadoGeneral();

        if (seleccionRadioButton == rucJuridica) {
            // Filtrar por RUC jurídica (20)
            lista = filtrarEmpresasPorRuc(lista, "20");
        } else if (seleccionRadioButton == rucNatural) {
            // Filtrar por RUC natural (10)
            lista = filtrarEmpresasPorRuc(lista, "10");
        }

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

    private ArrayList<Empresa> filtrarEmpresasPorRuc(ArrayList<Empresa> empresas, String rucFiltro) {
        ArrayList<Empresa> listaFiltrada = new ArrayList<>();

        for (Empresa empresa : empresas) {
            if (empresa.getRuc().startsWith(rucFiltro)) {
                listaFiltrada.add(empresa);
            }
        }
        return listaFiltrada;
    }

}