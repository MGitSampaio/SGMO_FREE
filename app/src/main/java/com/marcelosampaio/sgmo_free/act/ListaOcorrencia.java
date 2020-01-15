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
import com.marcelosampaio.sgmo_free.dao.OcorrenciaDao;
import com.marcelosampaio.sgmo_free.model.Ocorrencia;

import java.util.ArrayList;
import java.util.List;

public class ListaOcorrencia extends AppCompatActivity
{

    private ListView listView;
    private OcorrenciaDao dao;
    private List<Ocorrencia> ocorrencias;
    private final List<Ocorrencia> ocorrenciasFiltrado = new ArrayList<>();
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ocorrencia);
        listView = findViewById(R.id.lvOcorrencias);
        dao = new OcorrenciaDao(this);
        ocorrencias = dao.listarOcorrencias();
        ocorrenciasFiltrado.addAll(ocorrencias);
        ArrayAdapter<Ocorrencia> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ocorrenciasFiltrado);
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

                buscarOcorrencia(s);
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

    private void buscarOcorrencia(String nome)
    {
        ocorrenciasFiltrado.clear();
        for (Ocorrencia c : ocorrencias) {
            if (c.getTipoOcorrencia().toLowerCase().contains(nome.toLowerCase()))
            {
                ocorrenciasFiltrado.add(c);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(this, CadastroOcorrencia.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Ocorrencia ocorrenciaAtualizar = ocorrenciasFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroOcorrencia.class);
        i.putExtra("bOcorrencia", ocorrenciaAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Ocorrencia ocorrenciaExcluir = ocorrenciasFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Ocorrencia?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        ocorrenciasFiltrado.remove(ocorrenciaExcluir);
                        ocorrencias.remove(ocorrenciaExcluir);
                        dao.excluir(ocorrenciaExcluir);
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
        ocorrencias = dao.listarOcorrencias();
        ocorrenciasFiltrado.clear();
        ocorrenciasFiltrado.addAll(ocorrencias);
        listView.invalidateViews();
    }

    //==============================================================================================

}
