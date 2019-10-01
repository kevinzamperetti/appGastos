package com.example.contas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastroActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTipo;
    EditText edtValor;
    EditText edtData;
    Button btnSalvar;
    String nomeGasto;
    String tipoGasto;
    String dataGasto;
    String valorGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //esconde actionBar com nome do projeto

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cadastro );

        edtNome = findViewById( R.id.edtNome );
        edtData = findViewById( R.id.edtData );
        edtTipo = findViewById( R.id.edtTipo );
        edtValor = findViewById( R.id.edtValor );
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edtData, smf);
        edtData.addTextChangedListener(mtw);

        btnSalvar = findViewById( R.id.btnSalvar );

        nomeGasto = edtNome.getText().toString();
        tipoGasto = edtTipo.getText().toString();
        dataGasto = edtData.getText().toString();
        valorGasto = edtValor.getText().toString();



        btnSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nomeGasto = edtNome.getText().toString();
                tipoGasto = edtTipo.getText().toString();
                dataGasto = edtData.getText().toString();
                valorGasto = edtValor.getText().toString();
                DbHelper db = new DbHelper( getBaseContext() );

                if( nomeGasto.equals("") || tipoGasto.equals("")|| dataGasto.equals("") || valorGasto.equals("")
                ){
                    Toast.makeText( getBaseContext(),R.string.falta_entradas,Toast.LENGTH_LONG ).show();

                }else{

                    Contas conta = null;
                    conta = new Contas( edtNome.getText().toString(), edtData.getText().toString(),
                            edtTipo.getText().toString(), edtValor.getText().toString() );
                    ContasDao contasDao = new ContasDao( getBaseContext() );

                    String msg = contasDao.save( conta );
                    Toast.makeText( getBaseContext(),msg,Toast.LENGTH_LONG ).show();
                    Intent it = new Intent( CadastroActivity.this, MainActivity.class );
                    startActivity( it );
                }

            }
        });
    }
}
