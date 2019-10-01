package com.example.contas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContasAdapter extends ArrayAdapter {
    public ContasAdapter(Context context, List<Contas> objetcts) {
        super(context, 0, objetcts);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView =convertView;

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_contas, parent, false);
        }

        Contas current = (Contas) getItem(position);
        TextView nome =  (TextView) listItemView.findViewById(R.id.txtNome);
        TextView data =  (TextView) listItemView.findViewById(R.id.txtData);
        TextView tipo =  (TextView) listItemView.findViewById(R.id.txtTipo);
        TextView valor = (TextView) listItemView.findViewById(R.id.txtValor);
        nome.setText( current.getNome().toString() );
        data.setText( current.getData().toString() );
        tipo.setText( current.getTipo().toString() );
        valor.setText( current.getValor().toString() );
        return  listItemView;
    }

}
