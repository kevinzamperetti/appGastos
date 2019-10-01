package com.example.contas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class ContasDao {

    private SQLiteDatabase db;
    private DbHelper banco;


    public ContasDao(Context context){
        banco = new DbHelper(context);
    }

    public String save( Contas conta ){

        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        try{
            valores = new ContentValues();
            valores.put( banco.NOME, conta.getNome().toString() );
            valores.put( banco.TIPO, conta.getTipo().toString() );
            valores.put( banco.VALOR, conta.getValor().toString() );
            valores.put( banco.DATA, conta.getData().toString() );
            resultado = db.insert( banco.TABELA,null, valores );
            System.out.printf("resultado insert: " + resultado);
            db.close();
            if ( resultado != -1 ) {
                return "Gasto incluído ="+ conta.getNome();
            }
        } catch ( SQLException e ) {
            Log.e( "ERRO - ", e.getMessage() );
        }
        return "ERRO!";
    }

    public ArrayList<Contas> list() {
        ArrayList<Contas> lista = new ArrayList<>();
        Cursor cursor;
        String[] campos = { DbHelper.ID, DbHelper.NOME, DbHelper.TIPO,DbHelper.DATA,DbHelper.VALOR };
        db = banco.getReadableDatabase();
        cursor = db.query( DbHelper.TABELA, campos,null,null,null,null,null );
        if ( cursor.moveToFirst()  ) {
            do {
                int id = cursor.getInt(0) ;
                String nome = cursor.getString(1) ;
                String tipo = cursor.getString(2) ;
                String data = cursor.getString(3) ;
                String valor = cursor.getString(4) ;
                Contas conta = new Contas( id, nome, data, tipo, valor );
                lista.add( conta );
            } while ( cursor.moveToNext());
            return lista;
        }
          return null;
    }

    public String deletar( Contas c ) {
        String where = DbHelper.ID + "= " + c.getId();
        db = banco.getReadableDatabase();
        db.delete( DbHelper.TABELA, where, null );
        db.close();
        return "Gasto Removido!";
    }

    public String editar( Contas c ) {
        ContentValues valores;

        valores = new ContentValues();
        valores.put( banco.NOME, c.getNome() );
        valores.put( banco.TIPO, c.getTipo() );
        valores.put( banco.VALOR, c.getValor() );
        valores.put( banco.DATA, c.getData() );
        String where = DbHelper.ID + "= " + c.getId();

        db = banco.getReadableDatabase();
        db.update( DbHelper.TABELA, valores, where, null );
        db.close();
        return "Gasto Editado!";
    }


}
