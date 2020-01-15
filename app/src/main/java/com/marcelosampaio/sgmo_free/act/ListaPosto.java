package com.marcelosampaio.sgmo_free.act;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dao.PostoDao;
import com.marcelosampaio.sgmo_free.model.Posto;

import java.util.ArrayList;
import java.util.List;

public class ListaPosto extends AppCompatActivity
{
    private ListView listView;
    private PostoDao dao;
    private List<Posto> listaPostos;
    private final List<Posto> postosFiltrado = new ArrayList<>();
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_posto);

        listView = findViewById(R.id.lvPostos);
        dao = new PostoDao(this);

        listaPostos = dao.listarPostos();
        postosFiltrado.addAll(listaPostos);

        ArrayAdapter<Posto> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, postosFiltrado);

        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    //==============================================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_lista, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {

                buscarPosto(s);
                return false;
            }
        });
        return true;
    }

    //==============================================================================================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    //==============================================================================================

    private void buscarPosto(String nome)
    {
        postosFiltrado.clear();
        for (Posto c : listaPostos)
        {
            if (c.getNomePosto().toLowerCase().contains(nome.toLowerCase()))
            {
                postosFiltrado.add(c);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent i = new Intent(ListaPosto.this, CadastroPosto.class);
        startActivity(i);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Posto postoAtualizar = postosFiltrado.get(menuInfo.position);
        Intent i = new Intent(this, CadastroPosto.class);
        i.putExtra("atualizarPosto", postoAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Posto postoExcluir = postosFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Posto?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        postosFiltrado.remove(postoExcluir);
                        listaPostos.remove(postoExcluir);
                        dao.excluir(postoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    @Override
    public void onResume()
    {  super.onResume();

        listaPostos = dao.listarPostos();
        postosFiltrado.clear();
        postosFiltrado.addAll(listaPostos);
        listView.invalidateViews();
    }

    //==============================================================================================

}
