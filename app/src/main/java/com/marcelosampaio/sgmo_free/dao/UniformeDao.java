package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Uniforme;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UniformeDao {

    long dataAtual;
    long dataTermino;
    private final SQLiteDatabase banco;
    private Date date = new Date(System.currentTimeMillis());


    //==============================================================================================

    public UniformeDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }


    //==============================================================================================
    public long inserir(Uniforme uniforme) {
        ContentValues values = new ContentValues();

        values.put("idFuncionario", uniforme.getIdFuncionario());
        values.put("nrCobertura", uniforme.getNrCobertura());
        values.put("qtdCobertura", uniforme.getQtdCobertura());
        values.put("dtCobertura", uniforme.getDtCobertura());
        values.put("nrBlusa", uniforme.getNrBlusa());
        values.put("qtdBlusa", uniforme.getQtdBlusa());
        values.put("dtBlusa", uniforme.getDtBlusa());
        values.put("nrCamisa", uniforme.getNrCamisa());
        values.put("qtdCamisa", uniforme.getQtdCamisa());
        values.put("dtCamisa", uniforme.getDtCamisa());
        values.put("nrCalca", uniforme.getNrCalca());
        values.put("qtdCalca", uniforme.getQtdCalca());
        values.put("dtCalca", uniforme.getDtCalca());
        values.put("nrCalcado", uniforme.getNrCalcado());
        values.put("qtdCalcado", uniforme.getQtdCalcado());
        values.put("dtCalcado", uniforme.getDtCalcado());

        return banco.insert("uniforme", null, values);
    }

    //==============================================================================================

    public long atualizar(Uniforme uniforme) {
        ContentValues values = new ContentValues();
        values.put("idFuncionario", uniforme.getIdFuncionario());
        values.put("nrCobertura", uniforme.getNrCobertura());
        values.put("qtdCobertura", uniforme.getQtdCobertura());
        values.put("dtCobertura", uniforme.getDtCobertura());
        values.put("nrBlusa", uniforme.getNrBlusa());
        values.put("qtdBlusa", uniforme.getQtdBlusa());
        values.put("dtBlusa", uniforme.getDtBlusa());
        values.put("nrCamisa", uniforme.getNrCamisa());
        values.put("qtdCamisa", uniforme.getQtdCamisa());
        values.put("dtCamisa", uniforme.getDtCamisa());
        values.put("nrCalca", uniforme.getNrCalca());
        values.put("qtdCalca", uniforme.getQtdCalca());
        values.put("dtCalca", uniforme.getDtCalca());
        values.put("nrCalcado", uniforme.getNrCalcado());
        values.put("qtdCalcado", uniforme.getQtdCalcado());
        values.put("dtCalcado", uniforme.getDtCalcado());

        return banco.update("uniforme", values, "idUniforme = ?",
                new String[]{String.valueOf(uniforme.getIdUniforme())});

    }

    //==============================================================================================
    public void excluir(Uniforme uniforme) {

        banco.delete("uniforme", "idUniforme = ?", new String[]
                {String.valueOf(uniforme.getIdUniforme())});
    }


    //==============================================================================================

    public List<Uniforme> listarUniformes() {
        List<Uniforme> lstUniformes = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select funcionario.re,funcionario.nomeFuncionario, " +
                "uniforme.IdUniforme,uniforme.idFuncionario," +
                "uniforme.nrCobertura,uniforme.qtdCobertura,uniforme.dtCobertura," +
                "uniforme.nrBlusa,uniforme.qtdBlusa,uniforme.dtBlusa," +
                "uniforme.nrCamisa,uniforme.qtdCamisa,uniforme.dtCamisa," +
                "uniforme.nrCalca,uniforme.qtdCalca,uniforme.dtCalca," +
                "uniforme.nrCalcado,uniforme.qtdCalcado,uniforme.dtCalcado," +
                "uniforme.obsUniforme from funcionario, uniforme " +
                "Where funcionario.idFuncionario == uniforme.idFuncionario", null);

        while (cursor.moveToNext()) {
            Uniforme u = new Uniforme();
            u.setRe(cursor.getString(0));
            u.setNomeFuncionario(cursor.getString(1));
            u.setIdUniforme(cursor.getInt(2));
            u.setIdFuncionario(cursor.getInt(3));
            u.setNrCobertura(cursor.getString(4));
            u.setQtdCobertura(cursor.getString(5));
            u.setDtCobertura(cursor.getLong(6));
            u.setNrBlusa(cursor.getString(7));
            u.setQtdBlusa(cursor.getString(8));
            u.setDtBlusa(cursor.getLong(9));
            u.setNrCamisa(cursor.getString(10));
            u.setQtdCamisa(cursor.getString(11));
            u.setDtCamisa(cursor.getLong(12));
            u.setNrCalca(cursor.getString(13));
            u.setQtdCalca(cursor.getString(14));
            u.setDtCalca(cursor.getLong(15));
            u.setNrCalcado(cursor.getString(16));
            u.setQtdCalcado(cursor.getString(17));
            u.setDtCalcado(cursor.getLong(18));
            u.setObsUniforme(cursor.getString(19));
            lstUniformes.add(u);
        }
        cursor.close();
        return lstUniformes;
    }

    //==============================================================================================
    //Conta as uniformes que vencerão nos próximos 30 dias tendo como base a data
    // do sistema operacional

    public int contaUniformesAv() {
        int contador = 0;

        contador = contaCoberturaAv()
                + contaBlusaAv()
                + contaCamisaAv()
                + contaCalcaAv()
                + contaCalcadoAv();


        return contador;
    }

    //==============================================================================================
    private int contaCoberturaAv() {
        int contacobAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30);

        String SQL = "Select * from uniforme where dtCobertura between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contacobAv = cursor.getCount();
        cursor.close();

        return contacobAv;
    }

    //==============================================================================================

    private int contaBlusaAv() {
        int contaBluAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30);

        String SQL = "Select * from uniforme where dtBlusa between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaBluAv = cursor.getCount();
        cursor.close();

        return contaBluAv;
    }

    //==============================================================================================
    private int contaCamisaAv() {
        int contaCamAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30);

        String SQL = "Select * from uniforme where dtCamisa between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCamAv = cursor.getCount();
        cursor.close();

        return contaCamAv;
    }

    //==============================================================================================
    private int contaCalcaAv() {
        int contaCalAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30);

        String SQL = "Select * from uniforme where dtCalca between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCalAv = cursor.getCount();
        cursor.close();

        return contaCalAv;
    }

    //==============================================================================================

    private int contaCalcadoAv() {
        int contaCalcadoAv = 0;
        dataAtual = date.getTime();
        dataTermino = dataAtual + (86400000L * 30);

        String SQL = "Select * from uniforme where dtCalcado between " + dataAtual + " and " + dataTermino;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCalcadoAv = cursor.getCount();
        cursor.close();
        return contaCalcadoAv;
    }

    //==============================================================================================

    //Conta as uniformes que vencidos tendo como base a data
    // do sistema operacional

    public int contaUniformesV() {
        int contadorV = 0;

        contadorV = contaCoberturaV() + contaBlusaV() + contaCamisaV() + contaCalcaV() + contaCalcadoV();

        return contadorV;

    }

    //==============================================================================================

    private int contaCoberturaV() {
        int contaCobV;
        dataAtual = date.getTime();

        String SQL = "Select * from uniforme where dtCobertura > 1000 and dtCobertura <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCobV = cursor.getCount();
        cursor.close();

        return contaCobV;
    }

    //==============================================================================================
    private int contaBlusaV() {
        int contaBluV = 0;
        dataAtual = date.getTime();

        String SQL = "Select * from uniforme where dtBlusa > 1000 and dtBlusa <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaBluV = cursor.getCount();
        cursor.close();

        return contaBluV;
    }

    //==============================================================================================


    private int contaCamisaV() {
        int contaCamV = 0;
        dataAtual = date.getTime();

        String SQL = "Select * from uniforme where dtCamisa > 1000 and dtCamisa <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCamV = cursor.getCount();
        cursor.close();
        return contaCamV;
    }

    //==============================================================================================

    private int contaCalcaV() {
        int contaCalV = 0;
        dataAtual = date.getTime();

        String SQL = "Select * from uniforme where dtCalca > 1000 and dtCalca <= " + dataAtual;

        Cursor cursor = banco.rawQuery(SQL, null);
        contaCalV = cursor.getCount();
        cursor.close();

        return contaCalV;
    }

    //==============================================================================================

    private int contaCalcadoV() {
        int contaCalcadoV = 0;
        dataAtual = date.getTime();

        String SQL = "Select * from uniforme where dtCalcado > 1000 and  dtCalcado <= " + dataAtual;
        Cursor cursor = banco.rawQuery(SQL, null);
        contaCalcadoV = cursor.getCount();
        cursor.close();

        return contaCalcadoV;
    }
    public void reportUniformes() throws Exception {
        Cursor cursor = banco.rawQuery("Select funcionario.re,funcionario.nomeFuncionario," +
                "uniforme.nrCalcado, uniforme.qtdCalcado, uniforme.dtCalcado,uniforme.nrCalca, " +
                "uniforme.qtdCalca, uniforme.dtCalca,uniforme.nrCamisa, uniforme.qtdCamisa, " +
                "uniforme.dtCamisa, uniforme.nrBlusa, uniforme.qtdBlusa, uniforme.dtBlusa, " +
                "uniforme.nrCobertura, uniforme.qtdCobertura, uniforme.dtCobertura " +
                "from funcionario,uniforme where funcionario.idFuncionario == uniforme.idFuncionario" +
                " order by funcionario.nomeFuncionario", null);


        FileWriter writer = new FileWriter("/storage/emulated/0/Download/uniformes.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Uniformes</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        writer.append("</head>");
        writer.append("<body>");

        writer.append("<table class='table table-bordered'>");

        writer.append("<thead >");
        writer.append("<tr><th colspan =\"12\">Relatório de uniformes</th></tr>");
        writer.append("<tr>");
        writer.append("<th scope=\"col\" >RE</th>");
        writer.append("<th scope=\"col\">Nome</th>");
        writer.append("<th scope=\"col\">Calçado</th>");
        writer.append("<th scope=\"col\">QTDE</th>");
        writer.append("<th scope=\"col\">Data</th>");
        writer.append("<th scope=\"col\">Calça</th>");
        writer.append("<th scope=\"col\">QTDE</th>");
        writer.append("<th scope=\"col\">Data</th>");
        writer.append("<th scope=\"col\">Camisa</th>");
        writer.append("<th scope=\"col\">QTDE</th>");
        writer.append("<th scope=\"col\">Data</th>");
        writer.append("<th scope=\"col\">Blusa</th>");
        writer.append("<th scope=\"col\">QTDE</th>");
        writer.append("<th scope=\"col\">Data</th>");
        writer.append("<th scope=\"col\">Cobertura</th>");
        writer.append("<th scope=\"col\">QTDE</th>");
        writer.append("<th scope=\"col\">Data</th>");

        writer.append("</tr>");
        writer.append("</thead>");
        writer.append("<tbody>");

        while (cursor.moveToNext()) {

            writer.append("<tr>");
            writer.append("<td >" + cursor.getString(0) + "</td>");
            writer.append("<td >" + cursor.getString(1) + "</td>");
            writer.append("<td >" + cursor.getString(2) + "</td>");
            writer.append("<td >" + cursor.getString(3) + "</td>");
            writer.append("<td >" + cursor.getString(4) + "</td>");
            writer.append("<td >" + cursor.getString(5) + "</td>");
            writer.append("<td >" + cursor.getString(6) + "</td>");
            writer.append("<td >" + cursor.getString(7) + "</td>");
            writer.append("<td >" + cursor.getString(8) + "</td>");
            writer.append("<td >" + cursor.getString(9) + "</td>");
            writer.append("<td >" + cursor.getString(10) + "</td>");
            writer.append("<td >" + cursor.getString(11) + "</td>");
            writer.append("<td >" + cursor.getString(12) + "</td>");
            writer.append("<td >" + cursor.getString(13) + "</td>");
            writer.append("<td >" + cursor.getString(14) + "</td>");
            writer.append("<td >" + cursor.getString(15) + "</td>");
            writer.append("<td >" + cursor.getString(16) + "</td>");
            writer.append("</tr>");
            writer.append('\n');

        }

        writer.append("<tbody");
        writer.append('\n');
        writer.append("</table");
        writer.append('\n');
        writer.append("</body");
        writer.append('\n');
        writer.append("</html>");
        writer.flush();
        writer.close();
        cursor.close();

    }


}
