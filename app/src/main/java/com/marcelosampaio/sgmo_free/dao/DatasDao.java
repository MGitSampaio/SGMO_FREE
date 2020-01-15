package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Datas;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatasDao {


    private final SQLiteDatabase banco;
    //==============================================================================================

    public DatasDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================
    public long dataApp() {
        long dia = 0;
        Cursor cursor = banco.rawQuery("Select * from datas", null);
        cursor.moveToNext();
        try{
        dia = cursor.getLong(0);
        }catch(CursorIndexOutOfBoundsException e){
            DataHelper dataHelper = new DataHelper();
            Date date = new Date();
            String x = dataHelper.converteDataEmString(date);
            dia = dataHelper.converteStringDataEmLong(x);
        }
        cursor.close();
        return dia;
    }

    public int contarDatas() {

        long dia = System.currentTimeMillis();
        int contador = 0;
        Cursor cursor = banco.rawQuery("Select * from datas", null);
        cursor.moveToNext();
        contador = cursor.getCount();

        if (contador == 0) {
            inserir(dia);
        }


        return contador;
    }
    //==============================================================================================

    public void inserir(Long dia) {
        ContentValues values = new ContentValues();
        values.put("dia", dia);
        banco.insert("datas", null, values);
    }

    //==============================================================================================
}
