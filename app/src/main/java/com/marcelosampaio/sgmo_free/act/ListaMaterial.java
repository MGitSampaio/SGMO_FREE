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
import com.marcelosampaio.sgmo_free.dao.MaterialDao;
import com.marcelosampaio.sgmo_free.model.Material;

import java.util.ArrayList;
import java.util.List;

public class ListaMaterial extends AppCompatActivity
{

    private ListView listView;
    private MaterialDao dao;
    private List<Material> materiais;
    private final List<Material> materiaisFiltrado = new ArrayList<>();
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_material);
        listView = findViewById(R.id.lvMateriais);
        dao = new MaterialDao(this);
        materiais = dao.listarMateriais();
        materiaisFiltrado.addAll(materiais);
        ArrayAdapter<Material> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, materiaisFiltrado);
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

                buscarMaterial(s);
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

    private void buscarMaterial(String nome)
    {
        materiaisFiltrado.clear();
        for (Material material : materiais)
        {
            if (material.getDescricao().toLowerCase().contains(nome.toLowerCase()))
            {
                materiaisFiltrado.add(material);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item) {
        Intent intent = new Intent(this, CadastroMaterial.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Material materialAtualizar = materiaisFiltrado.get(menuInfo.position);
        Intent i = new Intent(this, CadastroMaterial.class);
        i.putExtra("material", materialAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Material materialExcluir = materiaisFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Material?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        materiaisFiltrado.remove(materialExcluir);
                        materiais.remove(materialExcluir);
                        dao.excluir(materialExcluir);
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
        materiais = dao.listarMateriais();
        materiaisFiltrado.clear();
        materiaisFiltrado.addAll(materiais);
        listView.invalidateViews();
    }

    //==============================================================================================

}

