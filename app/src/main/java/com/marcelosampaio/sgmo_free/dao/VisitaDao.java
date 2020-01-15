package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Visita;

import java.util.ArrayList;
import java.util.List;

public class VisitaDao {
    private final SQLiteDatabase banco;

    //==============================================================================================

    public VisitaDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Visita visita) {
        ContentValues values = new ContentValues();

        values.put("idRonda", visita.getIdRonda());
        values.put("unidade", visita.getUnidade());
        values.put("horaChegada", visita.getHoraChegada());
        values.put("kmChegada", visita.getKmChegada());
        values.put("horaSaida", visita.getHoraSaida());
        values.put("obsVisita", visita.getObsVisita());

        return banco.insert("visita", null, values);
    }

    //==============================================================================================

    public long atualizar(Visita visita) {
        ContentValues values = new ContentValues();

        values.put("idRonda", visita.getIdRonda());
        values.put("unidade", visita.getUnidade());
        values.put("horaChegada", visita.getHoraChegada());
        values.put("kmChegada", visita.getKmChegada());
        values.put("horaSaida", visita.getHoraSaida());
        values.put("obsVisita", visita.getObsVisita());

        return banco.update("visita", values, "idVisita = ?", new String[]
                {String.valueOf(visita.getIdVisita())});
    }

    //==============================================================================================

    public List<Visita> listarVisitas(int id) {
        List<Visita> lstVisitas = new ArrayList<>();
        Cursor cursor = banco.rawQuery("select * from visita Where idRonda = ? " +
                "order by horaChegada  ",new String[]{String.valueOf(id)});

        while (cursor.moveToNext()) {
            Visita visita = new Visita();

            visita.setIdVisita(cursor.getInt(0));
            visita.setIdRonda(cursor.getInt(1));
            visita.setUnidade(cursor.getString(2));
            visita.setHoraChegada(cursor.getString(3));
            visita.setKmChegada(cursor.getString(4));
            visita.setHoraSaida(cursor.getString(5));
            visita.setObsVisita(cursor.getString(6));

            lstVisitas.add(visita);
        }
        cursor.close();
        return lstVisitas;
    }

    //==============================================================================================

    public void excluir(Visita visita) {

        banco.delete("visita", "idVisita = ?", new String[]
                {String.valueOf(visita.getIdVisita())});
    }

    //==============================================================================================
}
