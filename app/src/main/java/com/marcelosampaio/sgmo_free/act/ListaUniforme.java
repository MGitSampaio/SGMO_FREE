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
import com.marcelosampaio.sgmo_free.adp.ListaUniformeAdapter;
import com.marcelosampaio.sgmo_free.dao.UniformeDao;
import com.marcelosampaio.sgmo_free.model.Uniforme;

import java.util.ArrayList;
import java.util.List;

public class ListaUniforme extends AppCompatActivity {

    private ListView listView;
    private UniformeDao dao;
    private List<Uniforme> uniformes;

    private final List<Uniforme> uniformesFiltrado = new ArrayList<>();
    private ListaUniformeAdapter listaUniformeAdapter;
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_uniforme);

        listView = findViewById(R.id.lvUniformes);

        dao = new UniformeDao(this);
        uniformes = dao.listarUniformes();

        uniformesFiltrado.addAll(uniformes);

        listaUniformeAdapter = new ListaUniformeAdapter(ListaUniforme.this, uniformesFiltrado);
        listView.setAdapter(listaUniformeAdapter);

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
                buscarUniforme(s);
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

    private void buscarUniforme(String nome)
    {
        uniformesFiltrado.clear();
        for (Uniforme u : uniformes)
        {
            if (u.getNomeFuncionario().toLowerCase().contains(nome.toLowerCase()))
            {
                uniformesFiltrado.add(u);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(this, CadastroUniforme.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Uniforme uniformeAtualizar = uniformesFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroUniforme.class);
        i.putExtra("bUniforme", uniformeAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Uniforme uniformeExcluir = uniformesFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Uniforme?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        uniformesFiltrado.remove(uniformeExcluir);
                        uniformes.remove(uniformeExcluir);
                        dao.excluir(uniformeExcluir);
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
        uniformes = dao.listarUniformes();
        uniformesFiltrado.clear();
        uniformesFiltrado.addAll(uniformes);
        listView.invalidateViews();
    }

    //==============================================================================================
}
