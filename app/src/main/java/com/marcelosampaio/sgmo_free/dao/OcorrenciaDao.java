package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Ocorrencia;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaDao {

    private final SQLiteDatabase banco;
    DataHelper dataHelper = new DataHelper();

    //==============================================================================================

    public OcorrenciaDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Ocorrencia ocorrencia) {
        ContentValues values = new ContentValues();
        values.put("cliente", ocorrencia.getCliente());
        values.put("unidade", ocorrencia.getUnidade());
        values.put("bo", ocorrencia.getBo());
        values.put("tipoOcorrencia", ocorrencia.getTipoOcorrencia());
        values.put("data", ocorrencia.getData());
        values.put("hora", ocorrencia.getHora());
        values.put("relato", ocorrencia.getRelato());
        return banco.insert("ocorrencia", null, values);
    }

    //==============================================================================================

    public long atualizar(Ocorrencia ocorrencia) {
        ContentValues values = new ContentValues();

        values.put("idOcorrencia", ocorrencia.getIdOcorrencia());
        values.put("cliente", ocorrencia.getCliente());
        values.put("unidade", ocorrencia.getUnidade());
        values.put("bo", ocorrencia.getBo());
        values.put("tipoOcorrencia", ocorrencia.getTipoOcorrencia());
        values.put("data", ocorrencia.getData());
        values.put("hora", ocorrencia.getHora());
        values.put("relato", ocorrencia.getRelato());

        return banco.update("ocorrencia", values, "idOcorrencia = ?", new String[]
                {String.valueOf(ocorrencia.getIdOcorrencia())});

    }

    //==============================================================================================

    public List<Ocorrencia> listarOcorrencias() {
        List<Ocorrencia> lstOcorrencias = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from ocorrencia Order By cliente,unidade,data"
                , new String[]{});


        while (cursor.moveToNext()) {
            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setIdOcorrencia(cursor.getInt(0));
            ocorrencia.setCliente(cursor.getString(1));
            ocorrencia.setUnidade(cursor.getString(2));
            ocorrencia.setTipoOcorrencia(cursor.getString(3));
            ocorrencia.setTipoOcorrencia(cursor.getString(4));
            ocorrencia.setData(cursor.getLong(5));
            ocorrencia.setHora(cursor.getString(6));
            ocorrencia.setRelato(cursor.getString(7));
            lstOcorrencias.add(ocorrencia);
        }
        cursor.close();
        return lstOcorrencias;
    }

    //==============================================================================================

    public void excluir(Ocorrencia oc) {

        banco.delete("ocorrencia", "idOcorrencia = ?", new String[]
                {String.valueOf(oc.getIdOcorrencia())});
    }

    public void reportOcorrencia(long inicio) throws Exception {

        Cursor cursor = banco.rawQuery("Select ocorrencia.cliente,ocorrencia.unidade," +
                "ocorrencia.bo, ocorrencia.tipoOcorrencia, ocorrencia.data, ocorrencia.hora," +
                "ocorrencia.relato from ocorrencia where data = "+inicio, null);

        Cursor cursor2 = banco.rawQuery("Select usuario.usuario from usuario",null);



        FileWriter writer = new FileWriter("/storage/emulated/0/Download/ocorrencias.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Funcionários</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        writer.append("</head>");
        writer.append("<body>");

        while (cursor.moveToNext()) {

            writer.append("<p>"+cursor.getString(0)+"</p>");
            writer.append("<p>"+cursor.getString(1)+"</p>");
            writer.append("<p>"+cursor.getString(2)+"</p>");
            writer.append("<p>"+cursor.getString(3)+"</p>");
            writer.append("<p>"+cursor.getString(4)+"</p>");
            writer.append("<p>"+cursor.getString(5)+"</p>");
            writer.append("<p>"+cursor.getString(6)+"</p>");
        }
        writer.append("</body");
        writer.append('\n');
        writer.append("</html>");
        writer.flush();
        writer.close();
        cursor.close();
        cursor2.close();
    }

    //==============================================================================================
}
