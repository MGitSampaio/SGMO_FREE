package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Ronda;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class RondaDao {

    private final SQLiteDatabase banco;
    private DataHelper dataHelper = new DataHelper();
    //==============================================================================================

    public RondaDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Ronda ronda) {
        ContentValues values = new ContentValues();

        values.put("dataInicial", ronda.getDataInicial());
        values.put("horaInicial", ronda.getHoraInicial());
        values.put("kmInicial", ronda.getKmInicial());
        values.put("dataAbastecimento", ronda.getDataAbastecimento());
        values.put("horaAbastecimento", ronda.getHoraAbastecimento());
        values.put("vtr", ronda.getVtr());
        values.put("kmAbastecimento", ronda.getKmAbastecimento());
        values.put("valorAbastecimento", ronda.getValorAbastecimento());
        values.put("notaAbastecimento", ronda.getNotaAbastecimento());
        values.put("dataFinal", ronda.getDataFinal());
        values.put("horaFinal", ronda.getHoraFinal());
        values.put("kmFinal", ronda.getKmFinal());
        values.put("obsRonda", ronda.getObsRonda());

        return banco.insert("ronda", null, values);
    }

    //==============================================================================================

    public long atualizar(Ronda ronda) {
        ContentValues values = new ContentValues();

        values.put("dataInicial", ronda.getDataInicial());
        values.put("horaInicial", ronda.getHoraInicial());
        values.put("kmInicial", ronda.getKmInicial());
        values.put("dataAbastecimento", ronda.getDataAbastecimento());
        values.put("horaAbastecimento", ronda.getHoraAbastecimento());
        values.put("vtr", ronda.getVtr());
        values.put("kmAbastecimento", ronda.getKmAbastecimento());
        values.put("valorAbastecimento", ronda.getValorAbastecimento());
        values.put("notaAbastecimento", ronda.getNotaAbastecimento());
        values.put("dataFinal", ronda.getDataFinal());
        values.put("horaFinal", ronda.getHoraFinal());
        values.put("kmFinal", ronda.getKmFinal());
        values.put("obsRonda", ronda.getObsRonda());

        return banco.update("ronda", values, "idRonda = ?", new String[]{String.valueOf
                (ronda.getIdRonda())});
    }

    //==============================================================================================

    public List<Ronda> listarRondas() {
        List<Ronda> lstRondas = new ArrayList<>();

        Cursor cursor = banco.rawQuery("Select * From ronda order by dataInicial Desc", new String[]{});

        while (cursor.moveToNext()) {
            Ronda ronda = new Ronda();

            ronda.setIdRonda(cursor.getInt(0));
            ronda.setDataInicial(cursor.getLong(1));
            ronda.setHoraInicial(cursor.getString(2));
            ronda.setKmInicial(cursor.getString(3));
            ronda.setDataAbastecimento(cursor.getLong(4));
            ronda.setHoraAbastecimento(cursor.getString(5));
            ronda.setVtr(cursor.getString(6));
            ronda.setKmAbastecimento(cursor.getString(7));
            ronda.setValorAbastecimento(cursor.getString(8));
            ronda.setNotaAbastecimento(cursor.getString(9));
            ronda.setDataFinal(cursor.getLong(10));
            ronda.setHoraFinal(cursor.getString(11));
            ronda.setKmFinal(cursor.getString(12));
            ronda.setObsRonda(cursor.getString(13));

            lstRondas.add(ronda);
        }
        cursor.close();
        return lstRondas;
    }

    //==============================================================================================

    public void excluir(Ronda ro) {

        banco.delete("ronda", "idRonda = ?", new String[]
                {String.valueOf(ro.getIdRonda())});
    }

    public void reportRondas(long dia) throws Exception {


        FileWriter writer = new FileWriter("/storage/emulated/0/Download/rondas.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Rondas</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous>\"");
        writer.append("</head>");
        writer.append("<body>");

        Cursor cursor = banco.rawQuery("Select * from usuario", null);
        cursor.moveToNext();
        writer.append("<table class='table table-bordered'>");
        writer.append("<thead >");
        writer.append("<tr >");
        writer.append("<th colspan =\"4\" >Relatório de Rondas</th>");
        writer.append("<th colspan =\"4\" >" + cursor.getString(1) + "</th>");
        writer.append("<th colspan =\"4\" >" + cursor.getString(2) + "</th>");
        writer.append("</tr>");
        writer.append("</thead>");
        cursor.close();

        Cursor cursor1 = banco.rawQuery("Select * from ronda where dataInicial = ?",
                new String[]{String.valueOf(dia)});
        int idRonda = cursor1.getInt(0);
        while(cursor1.moveToNext()) {


            writer.append("<tr>");
            writer.append("<th colspan = 2>Data Inicial</th>");
            writer.append("<th colspan = 2>Hora Inicial</th>");
            writer.append("<th colspan = 2>Data Término</th>");
            writer.append("<th colspan = 2>Hora Término</th>");
            writer.append("<th colspan = 2>Km Inicial</th>");
            writer.append("<th colspan = 2>Km Final</th>");
            writer.append("</tr>");


            writer.append("<tbody>");
            writer.append("<tr>");
            writer.append("<td colspan = 2>" + dataHelper.convertLongEmStringData(cursor1.getLong(1)) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getLong(2) + "</td>");
            writer.append("<td colspan = 2>" + dataHelper.convertLongEmStringData(cursor1.getLong(10)) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getLong(11) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(3) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(12) + "</td>");
            writer.append("</tr>");
            writer.append("</tbody>");

            writer.append("<tr>");
            writer.append("<th colspan = 2>VTR</th>");
            writer.append("<th colspan = 2>Data Abastecimento</th>");
            writer.append("<th colspan = 2>Hora Abastecimento</th>");
            writer.append("<th colspan = 2>KM Abastecimento</th>");
            writer.append("<th colspan = 2>Valor Abastecido</th>");
            writer.append("<th colspan = 2>Nota Fiscal</th>");
            writer.append("</tr>");

            writer.append("<tr>");
            writer.append("<td colspan = 2>" + cursor1.getString(6) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(4) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(5) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(7) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(8) + "</td>");
            writer.append("<td colspan = 2>" + cursor1.getString(9) + "</td>");
            writer.append("</tr>");
        }
        cursor1.close();


        writer.append("<tr>");
        writer.append("<th colspan = 4>Cliente - Unidade</th>");
        writer.append("<th colspan = 2>Chegada</th>");
        writer.append("<th colspan = 2>Km</th>");
        writer.append("<th colspan = 2>Saida</th>");
        writer.append("<th colspan = 2>Obs</th>");
        writer.append("</tr>");

        Cursor cursor2 = banco.rawQuery("Select * from visita where idRonda = ?",
                new String[]{String.valueOf(idRonda)});

        while (cursor2.moveToNext()) {
            writer.append("<tr>");
            writer.append("<td colspan = 4>"+cursor2.getString(2)+"</td>");
            writer.append("<td colspan = 2>"+cursor2.getString(3)+"</td>");
            writer.append("<td colspan = 2>"+cursor2.getString(4)+"</td>");
            writer.append("<td colspan = 2>"+cursor2.getString(5)+"</td>");
            writer.append("<td colspan = 2>"+cursor2.getString(6)+"</td>");
            writer.append("</tr>");

        }
        cursor2.close();
        writer.append("<tr>");
        writer.append("<th colspan = 4>Supervisor</th>");
        writer.append("<th colspan = 4>Coordenador</th>");
        writer.append("<th colspan = 4>Gerente</th>");

        writer.append("</tr>");

        writer.append("<tr>");
        writer.append("<td colspan = 4 ></td>");
        writer.append("<td colspan = 4 ></td>");
        writer.append("<td colspan = 4 ></td>");

        writer.append("</tr>");

        writer.append("</body");
        writer.append('\n');
        writer.append("</html>");
        writer.flush();
        writer.close();


    }

    //==============================================================================================
}

