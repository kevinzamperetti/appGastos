package com.example.contas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO ="AppContas.db";
    public static final String TABELA ="Contas";
    public static final String ID ="_id";
    public static final String NOME ="nome";
    public static final String DATA ="data";
    public static final String TIPO ="tipo";
    public static final String VALOR ="valor";
    public static final int VERSAO =1;

  public DbHelper( Context context ){
      super( context,NOME_BANCO,null, VERSAO );
  }

    @Override
    public void onCreate( SQLiteDatabase db ) {
      String sqlVersao1 = "CREATE TABLE " + TABELA + " (" +
                                                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                NOME + " TEXT NOT NULL, " +
                                                TIPO + " TEXT, " +
                                                VALOR + " TEXT, " +
                                                DATA + " DATE )";
        db.execSQL( sqlVersao1 );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "Drop table if exists "+ TABELA );
        onCreate( db );
    }
}
