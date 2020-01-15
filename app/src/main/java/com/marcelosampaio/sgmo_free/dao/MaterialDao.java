package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Material;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MaterialDao {
    private final SQLiteDatabase banco;
    //==============================================================================================

    public MaterialDao(Context context) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Material material) {
        ContentValues values = new ContentValues();

        values.put("codigo", material.getCodigo());
        values.put("descricao", material.getDescricao());
        values.put("obsMaterial", material.getObsMaterial());

        return banco.insert("material", null, values);
    }

    //==============================================================================================

    public long atualizar(Material material) {

        ContentValues values = new ContentValues();

        values.put("idMaterial",material.getIdMaterial());
        values.put("codigo", material.getCodigo());
        values.put("descricao", material.getDescricao());
        values.put("obsMaterial", material.getObsMaterial());
        values.put("idUnidade",material.getIdUnidade());

        return banco.update("material", values, "idMaterial = ?", new String[]
                {String.valueOf(material.getIdMaterial())});

    }

    //==============================================================================================

    public List<Material> listarMateriais() {
        final String sql;
        sql = "SELECT * FROM material";
        List<Material> lstMaterials = new ArrayList<>();
        Cursor cursor = banco.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Material material = new Material();
            material.setIdMaterial(cursor.getInt(0));
            material.setCodigo(cursor.getString(1));
            material.setDescricao(cursor.getString(2));
            material.setObsMaterial(cursor.getString(3));
            material.setIdUnidade(cursor.getInt(4));
            lstMaterials.add(material);
        }
        cursor.close();
        return lstMaterials;
    }

    //==============================================================================================

    public void excluir(Material material) {

        banco.delete("material", "idMaterial = ?", new String[]{String.valueOf
                (material.getIdMaterial())});
    }

    //==============================================================================================

    public void importarMateriais(Material material) {
        ContentValues values = new ContentValues();

        values.put("codigo", material.getCodigo());
        values.put("descricao", material.getDescricao());
        values.put("obsMaterial", material.getObsMaterial());

        banco.insert("material", null, values);
    }
    //==============================================================================================
    // contagem dos materiais  alocados
    public int contarMaterial(){
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from material",null);
        contador = cursor.getCount();

        return contador;
    }
    //==============================================================================================
    // contagem dos materiais não alocados
    public int contarMaterialNa(){
        int contador = 0;

        String sql = ("select * from material join unidade on material.idUnidade=" +
                "unidade.idUnidade where material.idUnidade = ?");
        Cursor cursor = banco.rawQuery(sql,new String[]{String.valueOf(1)});
        contador = cursor.getCount() ;

        return contador;
    }

    public void reportMateriais() throws Exception{
        Cursor cursor = banco.rawQuery("Select cliente.nomeCliente,unidade.nomeUnidade," +
                "material.codigo, material.descricao from cliente,unidade,material where " +
                "cliente.idCliente = unidade.idUnidade and material.idUnidade = unidade.idUnidade" +
                " ",null);

        FileWriter writer = new FileWriter("/storage/emulated/0/Download/materiais.html");

        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<meta charset='utf-8'>");
        writer.append("<title>Relatório de Material</title>");

        writer.append("<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>");
        writer.append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
        writer.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        writer.append("</head>");
        writer.append("<body>");

        writer.append("<table class='table table-bordered'>");

        writer.append("<thead >");
        writer.append("<tr><th colspan =\"12\">Relatório de Materiais</th></tr>");
        writer.append("<tr>");
        writer.append("<th scope=\"col\" >Cliente</th>");
        writer.append("<th scope=\"col\">Unidade</th>");
        writer.append("<th scope=\"col\">Código</th>");
        writer.append("<th scope=\"col\">Descrição</th>");

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
