package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Posto;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PostoDao
{

    private final SQLiteDatabase banco;
    private DataHelper dh;
    //==============================================================================================

    public PostoDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }


    //==============================================================================================

    public long inserir(Posto posto)
    {
        ContentValues values = new ContentValues();

        values.put("idUnidade", posto.getIdUnidade());
        values.put("nomePosto", posto.getNomePosto());
        values.put("idCargo", posto.getIdCargo());
        values.put("idEscala", posto.getIdEscala());
        values.put("turno", posto.getTurno());
        values.put("entrada1", posto.getEntrada1());
        values.put("saida1", posto.getSaida1());
        values.put("entrada2", posto.getEntrada2());
        values.put("saida2", posto.getSaida2());
        values.put("idFuncionario",posto.getIdFuncionario());
        values.put("idFolguista",posto.getIdFolguista());
        values.put("obsPosto", posto.getObsPosto());

        return banco.insert("posto", null, values);
    }

    //==============================================================================================

    public long atualizar(Posto posto)
    {
        ContentValues values = new ContentValues();

        values.put("idPosto", posto.getIdPosto());
        values.put("nomePosto", posto.getNomePosto());
        values.put("idUnidade", posto.getIdUnidade());
        values.put("idCargo", posto.getIdCargo());
        values.put("idEscala", posto.getIdEscala());
        values.put("turno", posto.getTurno());
        values.put("entrada1", posto.getEntrada1());
        values.put("saida1", posto.getSaida1());
        values.put("entrada2", posto.getEntrada2());
        values.put("saida2", posto.getSaida2());
        values.put("idFuncionario",posto.getIdFuncionario());
        values.put("idFolguista",posto.getIdFolguista());
        values.put("obsPosto", posto.getObsPosto());

        return banco.update("posto", values, "idPosto = ?",
                new String[]{String.valueOf(posto.getIdPosto())});

    }

    //==============================================================================================

    public List<Posto> listarPostos()
    {
        List<Posto> lstPostos = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select cliente.idCliente," +
                        "cliente.nomeCliente," +
                        "unidade.idUnidade," +
                        "unidade.nomeUnidade," +
                        "posto.idPosto," +
                        "posto.nomePosto, " +
                        "posto.idCargo,"+
                        "posto.idEscala,"+
                        "posto.turno,"+
                        "posto.entrada1,"+
                        "posto.saida1,"+
                        "posto.entrada2,"+
                        "posto.saida2,"+
                        "posto.idFuncionario,"+
                        "posto.idFolguista,"+
                        "posto.obsPosto " +
                        "From cliente,unidade,posto Where cliente.idCliente = " +
                        "unidade.idCliente and unidade.idUnidade = posto.idUnidade Order by " +
                        "nomeCliente,nomeUnidade,nomePosto"
                , new String[]{});

        while (cursor.moveToNext())
        {
            Posto posto = new Posto();
            posto.setIdCliente(0);
            posto.setNomeCliente(cursor.getString(1));
            posto.setIdUnidade(cursor.getInt(2));
            posto.setNomeUnidade(cursor.getString(3));
            posto.setIdPosto(cursor.getInt(4));
            posto.setNomePosto(cursor.getString(5));
            posto.setIdCargo(cursor.getInt(6));
            posto.setIdEscala(cursor.getInt(7));
            posto.setTurno(cursor.getString(8));
            posto.setEntrada1(cursor.getString(9));
            posto.setSaida1(cursor.getString(10));
            posto.setEntrada2(cursor.getString(11));
            posto.setSaida2(cursor.getString(12));
            posto.setIdFuncionario(cursor.getInt(13));
            posto.setIdFolguista(cursor.getInt(14));
            posto.setObsPosto(cursor.getString(15));

            lstPostos.add(posto);
        }
        cursor.close();
        return lstPostos;
    }

    //==============================================================================================

    public void excluir(Posto po)
    {
        banco.delete("posto", "idPosto = ?", new String[]
                {String.valueOf(po.getIdPosto())});
    }

    //==============================================================================================

    public void importarPosto(Posto posto)
    {
        ContentValues values = new ContentValues();

        values.put("idUnidade", posto.getIdUnidade());
        values.put("nomePosto", posto.getNomePosto());
        values.put("idCargo", posto.getIdCargo());
        values.put("idEscala", posto.getIdEscala());
        values.put("turno", posto.getTurno());
        values.put("entrada1", posto.getEntrada1());
        values.put("saida1", posto.getSaida1());
        values.put("entrada2", posto.getEntrada2());
        values.put("saida2", posto.getSaida2());
        values.put("idFuncionario",posto.getIdFuncionario());
        values.put("idFolgusta",posto.getIdFolguista());
        values.put("obsPosto", posto.getObsPosto());

        banco.insert("posto", null, values);
    }
    //==============================================================================================
    // usado pelo sistema para criar setup inicial do app
    public int contarPostos(){
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from posto",null);
        contador = cursor.getCount();

        if(contador == 0){
            inserirPosto();
        }
        else{
            contador = contador -1;
        }

        return contador;

    }
    private void inserirPosto() {

        ContentValues values = new ContentValues();
        values.put("nomePosto", "Plantão");
        values.put("idPosto", "1");
        values.put("idEscala", "1");
        values.put("obsPosto", "Você pode criar novos Postos");
        banco.insert("posto", null, values);
    }
    // conta o total de postos vagos

    public int contarPV(){
        int contador = 0;
        String sqlEfetivo;

        sqlEfetivo ="Select * from posto where idFuncionario = ?";
        Cursor cursor = banco.rawQuery(sqlEfetivo,new String[]{String.valueOf(1)});
        contador = cursor.getCount()-1;


        return contador;
    }

    public void reportPostos() throws IOException {

        Cursor cursor = banco.rawQuery("Select cliente.nomeCliente,unidade.nomeUnidade," +
                "cargo.cargo,escala.escala,funcionario.re,funcionario.nomefuncionario,posto.entrada1" +
                ",posto.saida1,posto.entrada2,posto.saida2 " +
                "from cliente,unidade,cargo,escala,funcionario,posto where " +
                "cliente.idCliente = unidade.idCliente and " +
                "unidade.idUnidade = posto.idUnidade " +
                "and posto.idCargo = cargo.idCargo " +
                "and posto.idEscala = escala.idEscala " +
                "and posto.idFuncionario = funcionario.idFuncionario",null);

        FileWriter writer = new FileWriter("/storage/emulated/0/Download/area.html");

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
                writer.append("<tr>");
                writer.append("<th scope=\"col\" >Cliente</th>");
                writer.append("<th scope=\"col\">Posto</th>");
                writer.append("<th scope=\"col\">Cargo</th>");
                writer.append("<th scope=\"col\">Escala</th>");
                writer.append("<th scope=\"col\">RE</th>");
                writer.append("<th scope=\"col\">Nome</th>");
                writer.append("<th scope=\"col\">Entrada</th>");
                writer.append("<th scope=\"col\">Int-</th>");
                writer.append("<th scope=\"col\">Int+</th>");
                writer.append("<th scope=\"col\">Saída</th>");

                writer.append("</tr>");
                writer.append("</thead>");
                writer.append("<tbody>");

        while (cursor.moveToNext())
        {

            writer.append("<tr>");
            writer.append("<td >"+cursor.getString(0)+"</td>");
            writer.append("<td >"+cursor.getString(1)+"</td>");
            writer.append("<td >"+cursor.getString(2)+"</td>");
            writer.append("<td >"+cursor.getString(3)+"</td>");
            writer.append("<td >"+cursor.getString(4)+"</td>");
            writer.append("<td >"+cursor.getString(5)+"</td>");
            writer.append("<td >"+cursor.getString(6)+"</td>");
            writer.append("<td >"+cursor.getString(7)+"</td>");
            writer.append("<td >"+cursor.getString(8)+"</td>");
            writer.append("<td >"+cursor.getString(9)+"</td>");
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

    //==============================================================================================
}

