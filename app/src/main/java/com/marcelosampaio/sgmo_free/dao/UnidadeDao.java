package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Unidade;
import java.util.ArrayList;
import java.util.List;

public class UnidadeDao
{
    private final SQLiteDatabase banco;
    //==============================================================================================

    public UnidadeDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Unidade unidade)
    {
        ContentValues values = new ContentValues();

        values.put("idCliente",unidade.getIdCliente());
        values.put("nomeUnidade", unidade.getNomeUnidade());
        values.put("nomeContato", unidade.getNomeContato());
        values.put("telefone", unidade.getTelefone());
        values.put("endereco", unidade.getEndereco());
        values.put("bairro", unidade.getBairro());
        values.put("cidade", unidade.getCidade());
        values.put("uf", unidade.getUf());
        values.put("obsUnidade", unidade.getObsUnidade());

        return banco.insert("unidade", null, values);
    }

    //==============================================================================================

    public long atualizar(Unidade unidade)
    {
        ContentValues values = new ContentValues();

        values.put("idUnidade",unidade.getIdUnidade());
        values.put("idCliente",unidade.getIdCliente());
        values.put("nomeUnidade", unidade.getNomeUnidade());
        values.put("nomeContato", unidade.getNomeContato());
        values.put("telefone", unidade.getTelefone());
        values.put("endereco", unidade.getEndereco());
        values.put("bairro", unidade.getBairro());
        values.put("cidade", unidade.getCidade());
        values.put("uf", unidade.getUf());
        values.put("obsUnidade", unidade.getObsUnidade());

        return banco.update("unidade", values, "idUnidade = ?", new String[]
                {String.valueOf(unidade.getIdUnidade())});

    }

    //==============================================================================================

    public List<Unidade> listarUnidades()
    {
        List<Unidade> lstUnidades = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select cliente.idCliente," +
                "cliente.nomeCliente," +
                "unidade.idUnidade,unidade.nomeUnidade,unidade.nomeContato,unidade.telefone" +
                ",unidade.endereco,unidade.bairro,unidade.cidade,unidade.uf,unidade.obsUnidade" +
                " from cliente,unidade " +
                "Where cliente.idCliente = unidade.idCliente " +
                "Order By nomeCliente,nomeUnidade" , new String[] {});

        while (cursor.moveToNext())
        {

            Unidade u = new Unidade();

            u.setIdCliente(cursor.getInt(0));
            u.setNomeCliente(cursor.getString(1));
            u.setIdUnidade(cursor.getInt(2));
            u.setNomeUnidade(cursor.getString(3));
            u.setNomeContato(cursor.getString(4));
            u.setTelefone(cursor.getString(5));
            u.setEndereco(cursor.getString(6));
            u.setBairro(cursor.getString(7));
            u.setCidade(cursor.getString(8));
            u.setUf(cursor.getString(9));
            u.setObsUnidade(cursor.getString(10));

            lstUnidades.add(u);
        }
        cursor.close();
        return lstUnidades;
    }

    //==============================================================================================

    public void excluir(Unidade un)
    {

        banco.delete("unidade", "idUnidade = ?", new String[]
                {String.valueOf(un.getIdUnidade())});


    }

    //==============================================================================================

    public void importarUnidade(Unidade unidade)
    {
        ContentValues values = new ContentValues();

        values.put("idCliente",unidade.getIdCliente());
        values.put("nomeUnidade", unidade.getNomeUnidade());
        values.put("nomeContato", unidade.getNomeContato());
        values.put("telefone", unidade.getTelefone());
        values.put("endereco", unidade.getEndereco());
        values.put("bairro", unidade.getBairro());
        values.put("cidade", unidade.getCidade());
        values.put("uf", unidade.getUf());
        values.put("obsUnidade", unidade.getObsUnidade());

        banco.insert("unidade", null, values);
    }
    //==============================================================================================
    // usado pelo sistema para criar setup inicial do app
    public int contarUnidades(){
        int contador = 0;

        Cursor cursor = banco.rawQuery("Select * from unidade",null);
        contador = cursor.getCount();

        if(contador == 0){
            inserirUnidade();
        }
        else{
            contador = contador -1;
        }

        return contador;

    }
    private void inserirUnidade() {

        ContentValues values = new ContentValues();
        values.put("nomeUnidade", "Plantão");
        values.put("idCliente", "1");
        values.put("obsUnidade", "Você pode criar novos departamentos");
        banco.insert("unidade", null, values);
    }
}
