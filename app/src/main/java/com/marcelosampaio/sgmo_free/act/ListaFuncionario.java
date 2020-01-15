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
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaFuncionarioAdapter;
import com.marcelosampaio.sgmo_free.dao.FuncionarioDao;
import com.marcelosampaio.sgmo_free.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ListaFuncionario extends AppCompatActivity
{

    private ListView listView;
    private FuncionarioDao dao;
    private List<Funcionario> funcionarios;
    private final List<Funcionario> funcionariosFiltrado = new ArrayList<>();
    private ListaFuncionarioAdapter listaFuncionarioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionario);
        listView = findViewById(R.id.lvFuncionarios);
        dao = new FuncionarioDao(this);
        funcionarios = dao.listarFuncionarios();
        funcionariosFiltrado.addAll(funcionarios);


        listaFuncionarioAdapter = new ListaFuncionarioAdapter(ListaFuncionario.this, funcionariosFiltrado);
        listView.setAdapter(listaFuncionarioAdapter);

       registerForContextMenu(listView);


    }

    //==============================================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_lista, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                buscarFuncionario(s);
                return false;
            }
        });
        return true;
    }

    //==============================================================================================
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    //==============================================================================================


    private void buscarFuncionario(String nome)
    {
        funcionariosFiltrado.clear();
        for (Funcionario f : funcionarios) {
            if (f.getNomeFuncionario().toLowerCase().contains(nome.toLowerCase())) {
                funcionariosFiltrado.add(f);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(this, CadastroFuncionario.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Funcionario funcionarioAtualizar = funcionariosFiltrado.get(menuInfo.position);
        Intent i = new Intent(this, CadastroFuncionario.class);
        i.putExtra("bFuncionario", funcionarioAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Funcionario cargoExcluir = funcionariosFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Cargo?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        funcionariosFiltrado.remove(cargoExcluir);
                        funcionarios.remove(cargoExcluir);
                        dao.excluir(cargoExcluir);
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
        funcionarios = dao.listarFuncionarios();
        funcionariosFiltrado.clear();
        funcionariosFiltrado.addAll(funcionarios);
        listView.invalidateViews();
    }

    //==============================================================================================

}

