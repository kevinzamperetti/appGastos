package com.example.contas;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.content.DialogInterface;


public class EditarActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTipo;
    EditText edtValor;
    EditText edtData;
    Button btnSalvar;
    Button btnExcluir;
    AlertDialog.Builder dialog;
    String nomeGasto;
    String tipoGasto;
    String dataGasto;
    String valorGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //esconde actionBar com nome do projeto

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        edtNome = findViewById(R.id.edtNome);
        edtData = findViewById(R.id.edtData);
        edtTipo = findViewById(R.id.edtTipo);
        edtValor = findViewById(R.id.edtValor);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        nomeGasto = edtNome.getText().toString();
        tipoGasto = edtTipo.getText().toString();
        dataGasto = edtData.getText().toString();
        valorGasto = edtValor.getText().toString();

        Bundle extra = getIntent().getExtras();
        // metodo em aula

        Intent intent = getIntent();
        final Contas c = (Contas)intent.getSerializableExtra("conta");
        if (extra != null) {
            int chave = extra.getInt("id");
            edtNome.setText( c.getNome() );
            edtTipo.setText( c.getTipo() );
            edtData.setText(  c.getData() );
            edtValor.setText( c.getValor() );
        }

        btnSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getBaseContext());

                ContasDao contasDao = new ContasDao( getBaseContext() );

                nomeGasto = edtNome.getText().toString();
                tipoGasto = edtTipo.getText().toString();
                dataGasto = edtData.getText().toString();
                valorGasto = edtValor.getText().toString();

                if( nomeGasto.equals("") || tipoGasto.equals("")|| dataGasto.equals("") || valorGasto.equals("") ){

                    Toast.makeText( getBaseContext(),R.string.falta_entradas,Toast.LENGTH_LONG ).show();

                }else{
                    c.setNome( edtNome.getText().toString() );
                    c.setTipo( edtTipo.getText().toString() );
                    c.setData( edtData.getText().toString() );
                    c.setValor( edtValor.getText().toString() );

                    String msg = contasDao.editar( c );
                    //Toast.makeText( getBaseContext(),msg,Toast.LENGTH_LONG ).show();

                    dialog = new AlertDialog.Builder(EditarActivity.this);
                    dialog.setTitle("Edição concluída");
                    dialog.setMessage(R.string.msg_edit);
                    dialog.setCancelable(false);

                    dialog.setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(EditarActivity.this, "Retornando.", Toast.LENGTH_SHORT).show();
                                }
                            });
                    dialog.create();
                    dialog.show();
                    Intent it = new Intent( EditarActivity.this, MainActivity.class );
                    startActivity( it );
                }
            }
        } );
        //teste
        btnExcluir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getBaseContext());
                ContasDao contasDao = new ContasDao(getBaseContext());
                nomeGasto = edtNome.getText().toString();
                tipoGasto = edtTipo.getText().toString();
                dataGasto = edtData.getText().toString();
                valorGasto = edtValor.getText().toString();


                String msg = contasDao.deletar(c);
                    //Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

                dialog = new AlertDialog.Builder(EditarActivity.this);
                dialog.setTitle("Remoção concluída");
                dialog.setMessage(R.string.msg_delete);
                dialog.setCancelable(false);

                dialog.setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(EditarActivity.this, "Retornando.", Toast.LENGTH_SHORT).show(); }
                            });
                dialog.create();
                dialog.show();
                Intent it = new Intent( EditarActivity.this, MainActivity.class );
                startActivity( it );


            }
        } );
    }
}