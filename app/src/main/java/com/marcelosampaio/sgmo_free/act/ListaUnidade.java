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
import com.marcelosampaio.sgmo_free.dao.UnidadeDao;
import com.marcelosampaio.sgmo_free.model.Cliente;
import com.marcelosampaio.sgmo_free.model.Unidade;

import java.util.ArrayList;
import java.util.List;

public class ListaUnidade extends AppCompatActivity
{
    private ListView lvUnidades;
    private UnidadeDao unidadeDao;
    private List<Unidade> listaUnidades;
    private final List<Unidade> unidadesFiltrado = new ArrayList<>();

    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_unidade);

        lvUnidades = findViewById(R.id.lvUnidades);

        unidadeDao = new UnidadeDao(this);
        listaUnidades = unidadeDao.listarUnidades();
        unidadesFiltrado.addAll(listaUnidades);

        ArrayAdapter<Unidade> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, unidadesFiltrado);

        lvUnidades.setAdapter(adaptador);
        registerForContextMenu(lvUnidades);
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

                buscarUnidade(s);
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

    private void buscarUnidade(String nome)
    {
        unidadesFiltrado.clear();
        for (Unidade u : listaUnidades)
        {
            if (u.getNomeUnidade().toLowerCase().contains(nome.toLowerCase()))
            {
                unidadesFiltrado.add(u);
            }
        }
        lvUnidades.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(ListaUnidade.this, CadastroUnidade.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Cliente unidadeAtualizar = unidadesFiltrado.get(menuInfo.position);
        Intent i = new Intent(this, CadastroUnidade.class);
        i.putExtra("atualizarUnidade", unidadeAtualizar);
        startActivity(i);
    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Unidade unidadeExcluir = unidadesFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Unidade?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        unidadesFiltrado.remove(unidadeExcluir);
                        listaUnidades.remove(unidadeExcluir);
                        unidadeDao.excluir(unidadeExcluir);
                        lvUnidades.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    @Override
    public void onResume()
    {
        super.onResume();

        listaUnidades = unidadeDao.listarUnidades();
        unidadesFiltrado.clear();
        unidadesFiltrado.addAll(listaUnidades);
        lvUnidades.invalidateViews();
    }

    //==============================================================================================

}