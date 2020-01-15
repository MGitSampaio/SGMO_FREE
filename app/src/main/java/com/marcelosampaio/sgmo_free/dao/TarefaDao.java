package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDao
{
    private final SQLiteDatabase banco;
    private DataHelper dh;
    //==============================================================================================

    public TarefaDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Tarefa tarefa)
    {
        ContentValues values = new ContentValues();

        values.put("tarefa", tarefa.getTarefa());
        values.put("data", tarefa.getData());
        values.put("hora", tarefa.getHora());
        values.put("status",tarefa.getStatus());
        values.put("descricao", tarefa.getDescricao());

        return banco.insert("tarefa", null, values);
    }

    //==============================================================================================

    public long atualizar(Tarefa tarefa)
    {
        ContentValues values = new ContentValues();

        values.put("tarefa", tarefa.getTarefa());
        values.put("data", tarefa.getData());
        values.put("hora", tarefa.getHora());
        values.put("status",tarefa.getStatus());
        values.put("descricao", tarefa.getDescricao());

        return banco.update("tarefa", values, "idTarefa = ?", new String[]
                {String.valueOf(tarefa.getIdTarefa())});
    }

    //==============================================================================================
    public void excluir(Tarefa tarefa)
    {

        banco.delete("tarefa", "idTarefa = ?", new String[]
                {String.valueOf(tarefa.getIdTarefa())});
    }



    //==============================================================================================
    public List<Tarefa> listarTarefas()
    {
        List<Tarefa> lstTarefas = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * From tarefa", new String[]{});


        while (cursor.moveToNext())
        {
            Tarefa tarefa = new Tarefa();
            tarefa.setIdTarefa(cursor.getInt(0));
            tarefa.setTarefa(cursor.getString(1));
            tarefa.setData(cursor.getLong(2));
            tarefa.setHora(cursor.getString(3));
            tarefa.setStatus(cursor.getString(4));
            tarefa.setDescricao(cursor.getString(5));
            lstTarefas.add(tarefa);
        }
        cursor.close();
        return lstTarefas;
    }

    //==============================================================================================


    //Conta o total de tarefas
    public int contarTarefas(){
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from tarefa",null);
        contador = cursor.getCount();

        return contador;
    }

    //==============================================================================================
    //Conta o total de tarefas Não Concluidas
    public int contarTarefasNe(){
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from tarefa where status = ?",new String[]{"False"});
        contador = cursor.getCount();

        return contador;
    }
}
