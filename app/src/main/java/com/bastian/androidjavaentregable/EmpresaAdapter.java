package com.bastian.androidjavaentregable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class EmpresaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Empresa> empresas;

    public EmpresaAdapter(Context context, ArrayList<Empresa> empresas) {
        this.context = context;
        this.empresas = empresas;
    }

    @Override
    public int getCount() {
        return empresas.size();
    }

    @Override
    public Object getItem(int position) {
        return empresas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Empresa empresa = empresas.get(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.lista_empresa_item, parent, false);
        }
        TextView txtRazonSocial = convertView.findViewById(R.id.txtRazonSocial);
        TextView txtRUC = convertView.findViewById(R.id.txtRUC);
        txtRazonSocial.setText(empresa.getRazonSocial());
        txtRUC.setText(empresa.getRuc());
        return convertView;
    }
}
