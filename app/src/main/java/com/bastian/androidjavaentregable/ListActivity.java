package com.bastian.androidjavaentregable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Empresa> empresas = getIntent().getParcelableArrayListExtra("empresas");
        ListView listView = findViewById(R.id.lvLista);
        EmpresaAdapter adapter = new EmpresaAdapter(this, empresas);
        listView.setAdapter(adapter);
    }
}