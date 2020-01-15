package com.marcelosampaio.sgmo_free.act;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaVencimentoAdapter;
import com.marcelosampaio.sgmo_free.dao.VencimentoDao;
import com.marcelosampaio.sgmo_free.model.Vencimento;

import java.util.ArrayList;
import java.util.List;

public class ListaVencimento extends AppCompatActivity {

    private ListView listView;
    private VencimentoDao dao;
    private List<Vencimento> vencimentos;

    private final List<Vencimento> vencimentosFiltrado = new ArrayList<>();
    private ListaVencimentoAdapter listaVencimentoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vencimento);

        listView = findViewById(R.id.lvVencimentos);

        dao = new VencimentoDao(this);
        vencimentos = dao.listarVencimentos();

        vencimentosFiltrado.addAll(vencimentos);

        listaVencimentoAdapter = new ListaVencimentoAdapter(ListaVencimento.this, vencimentosFiltrado);
        listView.setAdapter(listaVencimentoAdapter);


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
                buscarVencimento(s);
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

    private void buscarVencimento(String nome)
    {
        vencimentosFiltrado.clear();
        for (Vencimento v : vencimentos)
        {
            if (v.getNomeFuncionario().toLowerCase().contains(nome.toLowerCase()))
            {
                vencimentosFiltrado.add(v);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(this, CadastroVencimento.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Vencimento vencimentoAtualizar = vencimentosFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroVencimento.class);
        i.putExtra("bVencimento", vencimentoAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Vencimento vencimentoExcluir = vencimentosFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Vencimento?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        vencimentosFiltrado.remove(vencimentoExcluir);
                        vencimentos.remove(vencimentoExcluir);
                        dao.excluir(vencimentoExcluir);
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
        vencimentos = dao.listarVencimentos();
        vencimentosFiltrado.clear();
        vencimentosFiltrado.addAll(vencimentos);
        listView.invalidateViews();
    }

    //==============================================================================================
}
