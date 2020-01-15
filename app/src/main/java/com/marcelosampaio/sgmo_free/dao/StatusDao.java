package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusDao {

    private final SQLiteDatabase banco;
    //==============================================================================================

    public StatusDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================
    public List<Status> listarStatus()
    {
        List<Status> lstStatus = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from status Order by status",null);

        while (cursor.moveToNext())
        {
            Status status = new Status();
            status.setIdStatus(cursor.getInt(0));
            status.setStatus(cursor.getString(1));

            lstStatus.add(status);
        }
        cursor.close();
        return lstStatus;

    }

    //==============================================================================================

    public int contarStatus(){

        Cursor cursor = banco.rawQuery("Select * from status ",null);

        cursor.moveToFirst();
        int count = cursor.getCount();
        if(count == 0){
           inserirStatus();
        }

        return count;
    }

    private void inserirStatus(){
        ContentValues values = new ContentValues();
        String status[] = new String[3];
        status[0]="Efetivo";
        status[1]="Ferista";
        status[2]="Folguista";

        for (int i = 0; i < 3; i++)
        {
            values.put("status",status[i]);
            banco.insert("status",null,values);
            System.out.println(status[i]);
        }
        banco.close();
    }

}
