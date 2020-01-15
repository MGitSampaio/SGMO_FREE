package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Vencimento;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VencimentoDao {

    long dataAtual;
    long dataTermino;
    private final SQLiteDatabase banco;
    private Date date = new Date(System.currentTimeMillis());
    private DataHelper dataHelper = new DataHelper();

    //==============================================================================================

    public VencimentoDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================
    public long inserir(Vencimento vencimento) {
        ContentValues values = new ContentValues();

        values.put("idFuncionario", vencimento.getIdFuncionario());
        values.put("experiencia", vencimento.getExperiencia());
        values.put("ferias", vencimento.getFerias());
        values.put("reciclagem", vencimento.getReciclagem());
        values.put("aso", vencimento.getAso());
        values.put("cracha", vencimento.getCracha());
        values.put("cnv", vencimento.getCnv());
        values.put("psicotecnico", vencimento.getPsicotecnico());
        values.put("obsVencimento", vencimento.getObsVencimento());

        return banco.insert("vencimento", null, values);
    }

    //==============================================================================================

    public long atualizar(Vencimento vencimento) {
        ContentValues values = new ContentValues();

        values.put("idVencimento", vencimento.getIdVencimento());
        values.put("idFuncionario", vencimento.getIdFuncionario());
        values.put("experiencia", vencimento.getExperiencia());
        values.put("ferias", vencimento.getFerias());
        values.put("reciclagem", vencimento.getReciclagem());
        values.put("aso", vencimento.getAso());
        values.put("cracha", vencimento.getCracha());
        values.put("cnv", vencimento.getCnv());
        values.put("psicotecnico", vencimento.getPsicotecnico());
        values.put("obsVencimento", vencimento.getObsVencimento());

        return banco.update("vencimento", values, "idVencimento = ?",
                new String[]{String.valueOf(vencimento.getIdVencimento())});
    }

    //==============================================================================================

    public void excluir(Vencimento vencimento) {

        banco.delete("vencimento", "idVencimento = ?", new String[]
                {String.valueOf(vencimento.getIdVencimento())});
    }

    //==============================================================================================
    public List<Vencimento> listarVencimentos() {
        List<Vencimento> lstVencimentos = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select funcionario.re,funcionario.nomeFuncionario, " +
                "vencimento.idVencimento, vencimento.idFuncionario,vencimento.experiencia," +
                "vencimento.ferias,vencimento.reciclagem,vencimento.cracha,vencimento.cnv," +
                "vencimento.aso,vencimento.psicotecnico, vencimento.obsVencimento " +
                "from funcionario, vencimento " +
                "Where funcionario.idFuncionario == vencimento.idFuncionario", null);

        while (cursor.moveToNext()) {
            Vencimento v = new Vencimento();
            v.setRe(cursor.getString(0));
            v.setNomeFuncionario(cursor.getString(1));
            v.setIdVencimento(cursor.getInt(2));
            v.setIdFuncionario(cursor.getInt(3));
            v.setExperiencia(cursor.getLong(4));
            v.setFerias(cursor.getLong(5));
            v.setReciclagem(cursor.getLong(6));
            v.setCracha(cursor.getLong(7));
            v.setCnv(cursor.getLong(8));
            v.setAso(cursor.getLong(9));
            v.setPsicotecnico(cursor.getLong(10));
            v.setObsVencimento(cursor.getString(11));

            lstVencimentos.add(v);
        }
        cursor.close();
        return lstVencimentos;
    }

    //==============================================================================================
    //Conta as uniformes que vencerão nos próximos 30 dias tendo como base a data
    // do sistema operacional

    public int contaVencimentosAv() {
        int contador = 0;

        contador = contaFeriasAv() + contaExperienciaAv() + contaPsicoAv() + contaCnvAv()
        +contaCrachaAv()+ contaReciclagemAv() + contaAsoAv();

        return contador;
    }

    //==============================================================================================
    // Contar as férias a vencer nos próximos 60 dias a partir da data do Sistema Operacional
    public int contaFeriasAv() {
        int contaFerAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 60L);

        String SQL = "Select * from vencimento where ferias between "+ dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaFerAv = cursor.getCount();
        cursor.close();

        return contaFerAv;
    }

    //==============================================================================================
    //Contagem dos funcionarios com experiencia a vencer nos próximos 15 dias a partir da data do
    // Sistema operacional
    public int contaExperienciaAv() {
        int contaExpAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 15L);

        String SQL = "Select * from vencimento where experiencia between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaExpAv = cursor.getCount();
        cursor.close();

        return contaExpAv;

    }

    //==============================================================================================
    //Contar as Reciclagens que vencerão nos próximos 90 dias a partir da data do sistema operacional
    public int contaReciclagemAv() {
        int contaRecAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 60L);

        String SQL = "Select * from vencimento where reciclagem between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaRecAv = cursor.getCount();
        cursor.close();

        return contaRecAv;
    }

    //==============================================================================================
    //Contar os ASO's que vencerão nos próximos 30 dias a partir da data do sistema operacional
    public int contaAsoAv() {
        int contaAsoAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30L);

        String SQL = "Select * from vencimento where aso between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaAsoAv = cursor.getCount();
        cursor.close();

        return contaAsoAv;

    }
    //==============================================================================================
    //Contar os crachas que vencerão nos próximos 30 dias a partir da data do sistema operacional

    public int contaCrachaAv() {
        int contaCraAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30L);

        String SQL = "Select * from vencimento where cracha between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCraAv = cursor.getCount();
        cursor.close();

        return contaCraAv;
    }


    //==============================================================================================
    // Contar as CNV's que vencerão nos próximos 60 dias
    public int contaCnvAv() {
        int contaCnAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 60L);

        String SQL = "Select * from vencimento where cracha between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCnAv = cursor.getCount();
        cursor.close();

        return contaCnAv;

    }

    //==============================================================================================
    //Contar os psicotécnicos que vencerão nos próximos 30 dias
    public int contaPsicoAv() {
        int contaPsiAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30L);

        String SQL = "Select * from vencimento where experiencia > " + dataAtual + " and " + "experiencia <" + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaPsiAv = cursor.getCount();
        cursor.close();
        return contaPsiAv;

    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //==============================================================================================
    //Conta as uniformes que vencidos tendo como base a data
    // do sistema operacional

    public int contaVencimentosV() {
        int contaVenV = 0;

        contaVenV = contaFeriasV()
                + contaReciclagemV()
                + contaAsoV()
                + contaCrachaV()
                + contaCnvV()
                + contaPsicoV();

        return contaVenV;

    }

    //==============================================================================================
    // Contar as férias ja vencidas
    public int contaFeriasV() {
        long dataAtual = date.getTime();
        int contaFerV;

        String SQL = "Select * from vencimento where ferias > 1000 and ferias <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaFerV = cursor.getCount();
        cursor.close();

        return contaFerV;

    }

    //==============================================================================================
    //Contar as Reciclagens vencidas
    public int contaReciclagemV() {
        int contaRecV;// a vencer
        long dataAtual = date.getTime();

        String SQL = "Select * from vencimento where reciclagem > 1000 and reciclagem <=" + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaRecV = cursor.getCount();
        cursor.close();

        return contaRecV;

    }

    //==============================================================================================
    //Contar os ASO's vencidos
    public int contaAsoV() {
        int contaAsV;
        long dataAtual = date.getTime();

        String SQL = "Select * from vencimento where aso > 1000 and aso <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaAsV = cursor.getCount();
        cursor.close();

        return contaAsV;

    }

    //==============================================================================================
    // Contar os crachas vencidos
    public int contaCrachaV() {
        int contaCraV = 0;
        long dataAtual = date.getTime();

        String SQL = "Select * from vencimento where cracha > 1000 and cracha <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCraV = cursor.getCount();
        cursor.close();

        return contaCraV;
    }

    //==============================================================================================
    // Contar as CNV's vencidas
    public int contaCnvV() {
        int contaCnV = 0;
        long dataAtual = date.getTime();

        String SQL = "Select * from vencimento where cnv > 1000 and cnv <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCnV = cursor.getCount();
        cursor.close();

        return contaCnV;

    }

    //==============================================================================================
    //Contar os psicotécnicos vencidos
    public int contaPsicoV() {
        int contaPsicV;
        long dataAtual = date.getTime();

        String SQL = "Select * from vencimento where psicotecnico > 1000 and psicotecnico <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaPsicV = cursor.getCount();
        cursor.close();

        return contaPsicV;

    }

    //==============================================================================================
    //Gera Relatório com as datas de vencimento
    public void reportVencimentos() throws Exception {

        Cursor cursor = banco.rawQuery("Select funcionario.re,funcionario.nomeFuncionario," +
                "vencimento.aso,vencimento.cracha, vencimento.cnv, vencimento.experiencia, vencimento.ferias," +
                "vencimento.reciclagem, vencimento.psicotecnico " +
                "from funcionario,vencimento " +
                "where funcionario.idFuncionario == vencimento.idFuncionario " +
                "order by nomeFuncionario" , null);




        FileWriter writer = new FileWriter("/storage/emulated/0/Download/vencimentos.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Vencimentos</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        writer.append("</head>");
        writer.append("<body>");

        writer.append("<table class='table table-bordered'>");

        writer.append("<thead >");
        writer.append("<tr><th colspan =\"12\">Relatório de Vencimentos</th></tr>");
        writer.append("<tr>");
        writer.append("<th scope=\"col\" >RE</th>");
        writer.append("<th scope=\"col\">Nome</th>");
        writer.append("<th scope=\"col\">ASO</th>");
        writer.append("<th scope=\"col\">Crachá</th>");
        writer.append("<th scope=\"col\">CNV</th>");
        writer.append("<th scope=\"col\">Experiência</th>");
        writer.append("<th scope=\"col\">Férias</th>");
        writer.append("<th scope=\"col\">Reciclagem</th>");
        writer.append("<th scope=\"col\">Psicotécnico</th>");

        writer.append("</tr>");
        writer.append("</thead>");
        writer.append("<tbody>");

        while (cursor.moveToNext()) {

            writer.append("<tr>");
            writer.append("<td>" + cursor.getString(0) + "</td>");
            writer.append("<td>" + cursor.getString(1)+ "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(2)) + "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(3)) + "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(4)) + "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(5)) + "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(6)) + "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(7)) + "</td>");
            writer.append("<td>" + dataHelper.convertLongEmStringData(cursor.getLong(8)) + "</td>");
            writer.append("</tr>");
            writer.append('\n');
        }
        writer.append("</body");
        writer.append('\n');
        writer.append("</html>");
        writer.flush();
        writer.close();
        cursor.close();

    }

    //==============================================================================================
}
