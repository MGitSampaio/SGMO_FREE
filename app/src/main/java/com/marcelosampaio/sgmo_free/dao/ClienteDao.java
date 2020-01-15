package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Cliente;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private final SQLiteDatabase banco;
    //==============================================================================================

    public ClienteDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nomeCliente", cliente.getNomeCliente());
        values.put("obsCliente", cliente.getObsCliente());
        return banco.insert("cliente", null, values);
    }

    //==============================================================================================

    public long atualizar(Cliente cliente) {
        ContentValues values = new ContentValues();

        values.put("idCliente", cliente.getIdCliente());
        values.put("nomeCliente", cliente.getNomeCliente());
        values.put("obsCliente", cliente.getObsCliente());

        return banco.update("cliente", values, "idCliente = ?", new String[]
                {String.valueOf(cliente.getIdCliente())});
    }

    //==============================================================================================

    public List<Cliente> listarClientes() {
        List<Cliente> lstClientes = new ArrayList<>();

        Cursor cursor = banco.rawQuery("Select cliente.idCliente, " +
                        "cliente.nomeCliente, " +
                        "cliente.obsCliente " +
                        "From cliente " +
                        "Order By nomeCliente"
                , new String[]{});

        while (cursor.moveToNext()) {
            Cliente c = new Cliente();
            c.setIdCliente(cursor.getInt(0));
            c.setNomeCliente(cursor.getString(1));
            c.setObsCliente(cursor.getString(2));

            lstClientes.add(c);

        }
        cursor.close();
        return lstClientes;
    }

    //==============================================================================================

    public void excluir(Cliente cliente) {

        banco.delete("cliente", "idCliente = ?", new String[]
                {String.valueOf(cliente.getIdCliente())});
    }

    //==============================================================================================

    public void importaCliente(Cliente cliente) {
        ContentValues values = new ContentValues();

        values.put("nomeCliente", cliente.getNomeCliente());
        values.put("obsCliente", cliente.getObsCliente());

        banco.insert("cliente", null, values);
    }

    //==============================================================================================
    // usado pelo sistema para criar setup inicial do app
    public int contarClientes() {
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from cliente", null);

        contador = cursor.getCount();
        if (contador == 0) {
            inserirCliente();
        } else {
            contador = contador - 1;
        }

        return contador;

    }
    //==============================================================================================
    private void inserirCliente() {
        ContentValues values = new ContentValues();

        values.put("nomeCliente", "MinhaEmpresa");
        values.put("obsCliente", "Altere o nome do Cliente para o nome de sua empresa");

        banco.insert("cliente", null, values);


    }
    //==============================================================================================

    public void reportClientes()throws IOException {

        Cursor cursor = banco.rawQuery("Select cliente.nomeCliente,unidade.nomeUnidade," +
                "posto.nomePosto from cliente,unidade,posto where cliente.idCliente = unidade.idCliente " +
                "and unidade.idUnidade = posto.idUnidade group by nomeCliente,nomeUnidade,nomePosto",null);


        FileWriter writer = new FileWriter("/storage/emulated/0/Download/clientes.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Área</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        writer.append("</head>");
        writer.append("<body>");

        writer.append("<table class='table table-bordered'>");

        writer.append("<thead >");
        writer.append("<tr><th colspan =\"12\">Relatório de Clientes</th></tr>");
        writer.append("<tr>");
        writer.append("<th scope=\"col\" >Cliente</th>");
        writer.append("<th scope=\"col\">Unidade</th>");
        writer.append("<th scope=\"col\">Posto</th>");
        writer.append("</tr>");
        writer.append("</thead>");
        writer.append("<tbody>");

        while (cursor.moveToNext())
        {

            writer.append("<tr>");
            writer.append("<td >"+cursor.getString(0)+"</td>");
            writer.append("<td >"+cursor.getString(1)+"</td>");
            writer.append("<td >"+cursor.getString(2)+"</td>");
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

