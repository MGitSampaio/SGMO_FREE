package com.marcelosampaio.sgmo_free.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.model.Usuario;

public class UsuarioDao {

    private final SQLiteDatabase banco;


    //==============================================================================================

    public UsuarioDao(Context context)
    {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        banco = conexaoSQLite.getWritableDatabase();
    }

    //==============================================================================================

    public long inserir(Usuario usuario)
    {
        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("area", usuario.getArea());

        return banco.insert("usuario", null, values);
    }

    //==============================================================================================

    public long atualizar(Usuario usuario)
    {
        ContentValues values = new ContentValues();
        values.put("idUsuario", usuario.getIdUsuario());
        values.put("usuario", usuario.getUsuario());
        values.put("area", usuario.getArea());

       return banco.update("usuario", values, "idUsuario = ?", new String[]
                {String.valueOf(usuario.getIdUsuario())});
    }

    //==============================================================================================

    public Usuario BuscaUsuario()
    {
        Usuario usuario = new Usuario();
        Cursor cursor = banco.rawQuery("Select * from usuario",null);

        while (cursor.moveToNext())
        {
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setUsuario(cursor.getString(1));
            usuario.setArea(cursor.getString(2));
        }
        cursor.close();
        return usuario;
    }

    //==============================================================================================

    public void excluir(Usuario usuario)
    {

        banco.delete("usuario", "idUsuario = ?", new String[]
                {String.valueOf(usuario.getIdUsuario())});
    }

    //==============================================================================================
}

