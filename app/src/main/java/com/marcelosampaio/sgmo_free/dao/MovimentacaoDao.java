package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Movimentacao;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDao
{

    private final SQLiteDatabase banco;
    private DataHelper dataHelper = new DataHelper();

    public MovimentacaoDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Movimentacao movimentacao)
    {
        ContentValues values = new ContentValues();

        values.put("dia", movimentacao.getDia());
        values.put("cliente",movimentacao.getCliente());
        values.put("unidade",movimentacao.getUnidade());
        values.put("reAusente", movimentacao.getReAusente());
        values.put("nomeAusente", movimentacao.getNomeAusente());
        values.put("motivo", movimentacao.getMotivo());
        values.put("reCobertura", movimentacao.getReCobertura());
        values.put("nomeCobertura", movimentacao.getNomeCobertura());
        values.put("obsMovimentacao", movimentacao.getObsMovimentacao());

        return banco.insert("movimentacao", null, values);
    }

    //==============================================================================================

    public long atualizar(Movimentacao movimentacao)
    {
        ContentValues values = new ContentValues();

        values.put("dia", movimentacao.getDia());
        values.put("cliente",movimentacao.getCliente());
        values.put("unidade",movimentacao.getUnidade());
        values.put("reAusente", movimentacao.getReAusente());
        values.put("nomeAusente", movimentacao.getNomeAusente());
        values.put("motivo", movimentacao.getMotivo());
        values.put("reCobertura", movimentacao.getReCobertura());
        values.put("nomeCobertura", movimentacao.getNomeCobertura());
        values.put("obsMovimentacao", movimentacao.getObsMovimentacao());


        return banco.update("movimentacao", values, "idMovimentacao = ?", new String[]
                {String.valueOf(movimentacao.getIdMovimentacao())});
    }

    //==============================================================================================

    public List<Movimentacao> listarMovimentacoes()
    {
        List<Movimentacao> lstMovimentacaos = new ArrayList<>();
        Cursor cursor = banco.rawQuery("select * from movimentacao Order By dia DESC,cliente,unidade ", new String[]{});

        while (cursor.moveToNext())
        {
            Movimentacao mo = new Movimentacao();
            mo.setIdMovimentacao(cursor.getInt(0));
            mo.setDia(cursor.getLong(1));
            mo.setCliente(cursor.getString(2));
            mo.setUnidade(cursor.getString(3));
            mo.setMotivo(cursor.getString(4));
            mo.setReAusente(cursor.getString(5));
            mo.setNomeAusente(cursor.getString(6));
            mo.setReCobertura(cursor.getString(7));
            mo.setNomeCobertura(cursor.getString(8));
            mo.setObsMovimentacao(cursor.getString(9));
            lstMovimentacaos.add(mo);
        }
        cursor.close();
        return lstMovimentacaos;
    }

    //==============================================================================================

    public void excluir(Movimentacao movimentacao)
    {

        banco.delete("movimentacao", "idMovimentacao = ?", new String[]
                {String.valueOf(movimentacao.getIdMovimentacao())});
    }

    //==============================================================================================
    // gera o relatório de movimentacao
    public void reportMovimentacao(String dia) throws Exception {

        FileWriter writer = new FileWriter("/storage/emulated/0/Download/movimentacao.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Movimentações</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous>\"");
        writer.append("</head>");
        writer.append("<body>");

        Cursor cursor = banco.rawQuery("Select * from usuario ", null);
        cursor.moveToNext();
        writer.append("<table class='table table-bordered'>");
        writer.append("<thead >");
        writer.append("<tr >");
        writer.append("<th colspan =\"3\" >Relatório de Movimentações</th>");
        writer.append("<th colspan =\"3\" >" + cursor.getString(1) + "</th>");
        writer.append("<th colspan =\"3\" >" + cursor.getString(2) + "</th>");
        writer.append("<th colspan =\"3\" >" + dia + "</th>");
        writer.append("</tr>");
        writer.append("</thead>");
        cursor.close();

        writer.append("<tr>");
        writer.append("<th colspan = 4>Cliente/Unidade</th>");
        writer.append("<th colspan = 3>Ausente</th>");
        writer.append("<th colspan = 2>motivo</th>");
        writer.append("<th colspan = 3>Cobertura</th>");
        ;
        writer.append("</tr>");

        Cursor cursor1 = banco.rawQuery("Select * from movimentacao where dia = ?",
                new String[]{String.valueOf(dataHelper.converteStringDataEmLong(dia))});

        while (cursor1.moveToNext()) {
            writer.append("<tr>");
            writer.append("<td colspan = 4>"+cursor1.getString(2)+" - "+cursor1.getString(3)+"</td>");
            writer.append("<td colspan = 3>"+cursor1.getString(5)+" - "+cursor1.getString(6)+"</td>");
            writer.append("<td colspan = 2>"+cursor1.getString(4)+"</td>");
            writer.append("<td colspan = 4>"+cursor1.getString(7)+" - "+ cursor1.getString(8)+"</td>");

            writer.append("</tr>");

        }
        cursor1.close();
        writer.append("</body");
        writer.append('\n');
        writer.append("</html>");
        writer.flush();

    }

    //==============================================================================================
}
