package com.uninorte.jdmaestre.calculadoradenotas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jose on 09/09/2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CalcNotas.sqlite";
    private static final int DB_SCHEME_VERSION = 1;




    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbManager.CREATE_TABLE_Users);
        db.execSQL(DbManager.CREATE_TABLE_Materias);
        db.execSQL(DbManager.CREATE_TABLE_Materias_Exp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {


    }
}
