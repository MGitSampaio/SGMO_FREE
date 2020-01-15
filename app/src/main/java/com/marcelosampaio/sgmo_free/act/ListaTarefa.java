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
import com.marcelosampaio.sgmo_free.dao.TarefaDao;
import com.marcelosampaio.sgmo_free.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class ListaTarefa extends AppCompatActivity
{

    private ListView listView;
    private TarefaDao dao;
    private List<Tarefa> tarefas;
    private final List<Tarefa> tarefasFiltrado = new ArrayList<>();
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefa);

        listView = findViewById(R.id.lvTarefas);

        dao = new TarefaDao(this);
        tarefas = dao.listarTarefas();

        tarefasFiltrado.addAll(tarefas);

        ArrayAdapter<Tarefa> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, tarefasFiltrado);

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
                buscarTarefa(s);
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

    private void buscarTarefa(String nome)
    {
        tarefasFiltrado.clear();
        for (Tarefa c : tarefas)
        {
            if (c.getTarefa().toLowerCase().contains(nome.toLowerCase()))
            {
                tarefasFiltrado.add(c);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(this, CadastroTarefa.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Tarefa tarefaAtualizar = tarefasFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroTarefa.class);
        i.putExtra("bTarefa", tarefaAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Tarefa tarefaExcluir = tarefasFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Tarefa?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        tarefasFiltrado.remove(tarefaExcluir);
                        tarefas.remove(tarefaExcluir);
                        dao.excluir(tarefaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    @Override
    public void onResume()
    {
        super.onResume();
        tarefas = dao.listarTarefas();
        tarefasFiltrado.clear();
        tarefasFiltrado.addAll(tarefas);
        listView.invalidateViews();
    }

    //==============================================================================================
}

