package com.example.contas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class CustomActivity extends AppCompatActivity {
   public ContasAdapter adapter;
    ArrayList<Contas> listaContas;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        getSupportActionBar().hide(); //esconde actionBar com nome do projeto

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_custom );

        listaContas = new ArrayList<>();

        final ContasDao contasDao = new ContasDao( getApplicationContext() );
        listaContas = contasDao.list();

        adapter = new ContasAdapter( this, listaContas );
        ListView list = findViewById( R.id.listaContas );
        list.setAdapter( adapter );


        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contas a = ( Contas ) parent.getItemAtPosition( position );


                Intent intent = new Intent(getApplicationContext(), EditarActivity.class);

                // Identificador do dado a ser passado,
                intent.putExtra( "id", a.get_id() );

                // Dados a serem passados
                intent.putExtra( "nome", a.getNome().toString() );
                intent.putExtra( "tipo", a.getTipo().toString() );
                intent.putExtra( "data", a.getData().toString() );
                intent.putExtra( "valor", a.getValor().toString() );
                intent.putExtra( "conta", a );
                startActivity( intent );
            }
        } );
    }
}
