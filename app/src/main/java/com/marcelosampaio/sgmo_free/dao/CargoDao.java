package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Cargo;

import java.util.ArrayList;
import java.util.List;

public class CargoDao
{

    private final SQLiteDatabase banco;
    //==============================================================================================

    public CargoDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

//==============================================================================================

    public long inserir(Cargo cargo)
    {
        ContentValues values = new ContentValues();
        values.put("cargo", cargo.getCargo());
        values.put("obsCargo", cargo.getObsCargo());

        return banco.insert("cargo", null, values);
    }

    //==============================================================================================

    public long atualizar(Cargo cargo)
    {
        ContentValues values = new ContentValues();
        values.put("idCargo", cargo.getIdCargo());
        values.put("cargo", cargo.getCargo());
        values.put("obsCargo", cargo.getObsCargo());

        return banco.update("cargo", values, "idCargo = ?", new String[]
                {String.valueOf (cargo.getIdCargo())});

    }

    //==============================================================================================

    public List<Cargo> listarCargos()
    {
        List<Cargo> lstCargos = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from cargo Order by cargo",null);

        while (cursor.moveToNext())
        {
            Cargo cargo = new Cargo();
            cargo.setIdCargo(cursor.getInt(0));
            cargo.setCargo(cursor.getString(1));
            cargo.setObsCargo(cursor.getString(2));
            lstCargos.add(cargo);
        }
        cursor.close();
        return lstCargos;

    }

    //==============================================================================================

    public void excluir(Cargo cargo)
    {

        banco.delete("cargo", "idCargo = ?", new String[]
                {String.valueOf(cargo.getIdCargo())});
    }

    //==============================================================================================

    public void importaCargo(Cargo cargo)
    {
        ContentValues values = new ContentValues();
        values.put("cargo", cargo.getCargo());
        values.put("obsCargo", cargo.getObsCargo());

        banco.insert("cargo", null, values);


    }

    public int contarCargos(){
         int contador = 0;

         Cursor cursor = banco.rawQuery("Select * from cargo",null);
        contador = cursor.getCount();

        if (contador == 0){

            inserirCargo();
        }

        return contador;
    }

    private void inserirCargo() {
        ContentValues values = new ContentValues();
        values.put("cargo","Vigilante");
        banco.insert("status",null,values);
        banco.close();
    }
}

