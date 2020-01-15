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
import com.marcelosampaio.sgmo_free.dao.CargoDao;
import com.marcelosampaio.sgmo_free.model.Cargo;

import java.util.ArrayList;
import java.util.List;

public class ListaCargo extends AppCompatActivity
{

    private ListView lvCargos;
    private CargoDao cargoDao;
    private List<Cargo> listaCargos;
    private final List<Cargo> cargosFiltrado = new ArrayList<>();
    //==============================================================================================
    //Função que cria e seta os valores da activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cargo);

        lvCargos = findViewById(R.id.lvCargos);

        cargoDao = new CargoDao(this);
        listaCargos = cargoDao.listarCargos();
        cargosFiltrado.addAll(listaCargos);

        ArrayAdapter<Cargo> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, cargosFiltrado);
        lvCargos.setAdapter(adaptador);
        registerForContextMenu(lvCargos);
    }

    //==============================================================================================
    //Função que cria o menu da activity
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

                buscarCargos(s);
                return false;
            }
        });
        return true;
    }

    //==============================================================================================
    //Função que cria o menu de dialogo Atualizar/Excluir
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    //==============================================================================================
    //Função que pesquisa um cargo dentro da LISTVIEW
    private void buscarCargos(String nome)
    {
        cargosFiltrado.clear();
        for (Cargo c : listaCargos)
        {
            if (c.getCargo().toLowerCase().contains(nome.toLowerCase()))
            {
                cargosFiltrado.add(c);
            }
        }
        lvCargos.invalidateViews();

    }

    //==============================================================================================
    //Função que abre o cadastro de Cargos sem parametros
    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(ListaCargo.this, CadastroCargo.class);
        startActivity(intent);
    }

    //==============================================================================================
    //Função que abre o cadasatro de cargos com parametros
    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Cargo cargoAtualizar = cargosFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroCargo.class);
        i.putExtra("bCargo", cargoAtualizar);
        startActivity(i);

    }

    //==============================================================================================
    //Função que exclui um Cargo
    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Cargo cargoExcluir = cargosFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Cargo?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        cargosFiltrado.remove(cargoExcluir);
                        listaCargos.remove(cargoExcluir);
                        cargoDao.excluir(cargoExcluir);
                        lvCargos.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    // Função que atualiza a lista de cargos
    @Override
    public void onResume()
    {
        super.onResume();
        listaCargos = cargoDao.listarCargos();
        cargosFiltrado.clear();
        cargosFiltrado.addAll(listaCargos);
        lvCargos.invalidateViews();
    }

    //==============================================================================================

}

