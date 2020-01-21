package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Funcionario;
import com.marcelosampaio.sgmo_free.model.Uniforme;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionarioDao {
    private final SQLiteDatabase banco;
    DataHelper dataHelper = new DataHelper();


    //==============================================================================================

    public FuncionarioDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }


    //==============================================================================================
    public long inserir(Funcionario funcionario) {
        ContentValues values = new ContentValues();

        values.put("idCargo", funcionario.getIdCargo());
        values.put("idStatus", funcionario.getIdStatus());
        values.put("re", funcionario.getRe());
        values.put("nomeFuncionario", funcionario.getNomeFuncionario());
        values.put("telefone", funcionario.getTelefone());
        values.put("idEscala", funcionario.getIdEscala());
        values.put("folga1", funcionario.getFolga1());
        values.put("folga2", funcionario.getFolga2());
        values.put("rg", funcionario.getRg());
        values.put("cpf", funcionario.getCpf());
        values.put("endereco", funcionario.getEndereco());
        values.put("bairro", funcionario.getBairro());
        values.put("cidade", funcionario.getCidade());
        values.put("uf", funcionario.getUf());
        values.put("obsFuncionario", funcionario.getObsFuncionario());

        return banco.insert("funcionario", null, values);
    }

    //==============================================================================================

    public long atualizar(Funcionario funcionario) {
        ContentValues values = new ContentValues();
        values.put("idFuncionario", funcionario.getIdFuncionario());
        values.put("idCargo", funcionario.getIdCargo());
        values.put("idStatus", funcionario.getIdStatus());
        values.put("re", funcionario.getRe());
        values.put("nomeFuncionario", funcionario.getNomeFuncionario());
        values.put("telefone", funcionario.getTelefone());
        values.put("idEscala", funcionario.getIdEscala());
        values.put("folga1", funcionario.getFolga1());
        values.put("folga2", funcionario.getFolga2());
        values.put("rg", funcionario.getRg());
        values.put("cpf", funcionario.getCpf());
        values.put("endereco", funcionario.getEndereco());
        values.put("bairro", funcionario.getBairro());
        values.put("cidade", funcionario.getCidade());
        values.put("uf", funcionario.getUf());
        values.put("obsFuncionario", funcionario.getObsFuncionario());

        return banco.update("funcionario", values, "idFuncionario = ?",
                new String[]{String.valueOf(funcionario.getIdFuncionario())});

    }

    //==============================================================================================

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> lstFuncionarios = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from funcionario  where idFuncionario > ? " +
                        "order by nomeFuncionario"
                , new String[]{"3"});

        while (cursor.moveToNext()) {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(cursor.getInt(0));
            f.setIdCargo(cursor.getInt(1));
            f.setIdStatus(cursor.getInt(2));
            f.setIdEscala(cursor.getInt(3));
            f.setFolga1(cursor.getLong(4));
            f.setFolga2(cursor.getLong(5));
            f.setRe(cursor.getString(6));
            f.setNomeFuncionario(cursor.getString(7));
            f.setTelefone(cursor.getString(8));
            f.setRg(cursor.getString(9));
            f.setCpf(cursor.getString(10));
            f.setEndereco(cursor.getString(11));
            f.setBairro(cursor.getString(12));
            f.setCidade(cursor.getString(13));
            f.setUf(cursor.getString(14));
            f.setObsFuncionario(cursor.getString(15));
            lstFuncionarios.add(f);
        }
        cursor.close();
        return lstFuncionarios;
    }

    public List<Funcionario> listarFuncionariosEfetivos() {
        List<Funcionario> lstFuncionarios = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from funcionario where idStatus < 3", null);

        while (cursor.moveToNext()) {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(cursor.getInt(0));
            f.setIdCargo(cursor.getInt(1));
            f.setIdStatus(cursor.getInt(2));
            f.setIdEscala(cursor.getInt(3));
            f.setFolga1(cursor.getLong(4));
            f.setFolga2(cursor.getLong(5));
            f.setRe(cursor.getString(6));
            f.setNomeFuncionario(cursor.getString(7));
            f.setTelefone(cursor.getString(8));
            f.setRg(cursor.getString(9));
            f.setCpf(cursor.getString(10));
            f.setEndereco(cursor.getString(11));
            f.setBairro(cursor.getString(12));
            f.setCidade(cursor.getString(13));
            f.setUf(cursor.getString(14));
            f.setObsFuncionario(cursor.getString(15));
            lstFuncionarios.add(f);
        }
        cursor.close();
        return lstFuncionarios;
    }


    public List<Funcionario> listarFolguista() {
        List<Funcionario> lstFolguistas = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select * from funcionario where idStatus = 3 ", null);

        while (cursor.moveToNext()) {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(cursor.getInt(0));
            f.setIdCargo(cursor.getInt(1));
            f.setIdStatus(cursor.getInt(2));
            f.setIdEscala(cursor.getInt(3));
            f.setFolga1(cursor.getLong(4));
            f.setFolga2(cursor.getLong(5));
            f.setRe(cursor.getString(6));
            f.setNomeFuncionario(cursor.getString(7));
            f.setTelefone(cursor.getString(8));
            f.setRg(cursor.getString(9));
            f.setCpf(cursor.getString(10));
            f.setEndereco(cursor.getString(11));
            f.setBairro(cursor.getString(12));
            f.setCidade(cursor.getString(13));
            f.setUf(cursor.getString(14));
            f.setObsFuncionario(cursor.getString(15));
            lstFolguistas.add(f);
        }
        cursor.close();
        return lstFolguistas;
    }

    //==============================================================================================

    public void excluir(Funcionario funcionario) {

        banco.delete("funcionario", "idFuncionario = ?", new String[]
                {String.valueOf(funcionario.getIdFuncionario())});
    }


    //==============================================================================================
    //Contagem dos funcionarios menos o criado na inicialização
    public int contarFuncionarios() {
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from funcionario", null);

        contador = cursor.getCount();

        if (contador == 0) {
            inserirFuncionario();
        } else {
            contador = contador - 3;
        }

        return contador;
    }

    //==============================================================================================
    //Contagem dos funcionarios não alocados
    public int contarFuncionariosNa() {
        int contador = 0;

        String sqlEfetivos;

        sqlEfetivos = ("Select idFuncionario from funcionario where idFuncionario > 3 Except " +
                "Select idFuncionario from posto");
        Cursor cursor = banco.rawQuery(sqlEfetivos, null);
        contador = cursor.getCount();

        cursor.close();

        return contador;
    }


    //==============================================================================================
    //Insere um funcionario na inicialização do sistema
    private void inserirFuncionario() {

        ContentValues values = new ContentValues();
        values.put("re", "0");
        values.put("nomeFuncionario", "Posto Vago");
        values.put("idStatus", 0);
        banco.insert("funcionario", null, values);

        values.put("re", "-2");
        values.put("nomeFuncionario", "Sem - Folguista");
        values.put("idStatus", 3);
        banco.insert("funcionario", null, values);

        values.put("re", "-1");
        values.put("nomeFuncionario", "Falta - Folguista");
        values.put("idStatus", 3);
        banco.insert("funcionario", null, values);

    }

    public void reportFuncionarios() throws Exception {

        Cursor cursor = banco.rawQuery("Select * from funcionario", null);


        FileWriter writer = new FileWriter("/storage/emulated/0/Download/funcionarios.html");

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

        writer.append("<table class='table table-bordered'>");

        writer.append("<thead >");
        writer.append("<tr><th colspan =\"12\">Relatório de Funcionários</th></tr>");
        writer.append("<tr>");
        writer.append("<th scope=\"col\" >RE</th>");
        writer.append("<th scope=\"col\">Nome</th>");
        writer.append("<th scope=\"col\">Cargo</th>");
        writer.append("<th scope=\"col\">Telefone</th>");
        writer.append("<th scope=\"col\">RG</th>");
        writer.append("<th scope=\"col\">CPF</th>");
        writer.append("<th scope=\"col\">Endereço</th>");
        writer.append("<th scope=\"col\">Bairro</th>");
        writer.append("<th scope=\"col\">Cidade</th>");
        writer.append("<th scope=\"col\">UF</th>");
        writer.append("<th scope=\"col\">1ª Folga</th>");
        writer.append("<th scope=\"col\">2ª Folga</th>");

        writer.append("</tr>");
        writer.append("</thead>");
        writer.append("<tbody>");

        while (cursor.moveToNext()) {

            writer.append("<tr>");
            writer.append("<td >" + cursor.getString(0) + "</td>");
            writer.append("<td >" + cursor.getString(1) + "</td>");
            writer.append("<td >" + cursor.getString(1) + "</td>");
            writer.append("<td >" + cursor.getString(2) + "</td>");
            writer.append("<td >" + cursor.getString(3) + "</td>");
            writer.append("<td >" + cursor.getString(4) + "</td>");
            writer.append("<td >" + cursor.getString(5) + "</td>");
            writer.append("<td >" + cursor.getString(6) + "</td>");
            writer.append("<td >" + cursor.getString(7) + "</td>");
            writer.append("<td >" + cursor.getString(8) + "</td>");
            writer.append("<td >" + dataHelper.convertLongEmStringData(cursor.getLong(10)) + "</td>");
            writer.append("<td >" + dataHelper.convertLongEmStringData(cursor.getLong(11)) + "</td>");
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
