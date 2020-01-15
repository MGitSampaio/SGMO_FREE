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
import com.marcelosampaio.sgmo_free.dao.ClienteDao;
import com.marcelosampaio.sgmo_free.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ListaCliente extends AppCompatActivity
{

    private ListView lvClientes;
    private ClienteDao clienteDao;
    private List<Cliente> listaClientes;
    private final List<Cliente> clientesFiltrado = new ArrayList<>();
    //==============================================================================================
    //Função que cria e seta os valores da activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        lvClientes = findViewById(R.id.lvClientes);

        clienteDao = new ClienteDao(this);
        listaClientes = clienteDao.listarClientes();
        clientesFiltrado.addAll(listaClientes);

        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, clientesFiltrado);

        lvClientes.setAdapter(adaptador);
        registerForContextMenu(lvClientes);

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

                buscarClientes(s);
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
    //Função que pesquisa um cliente dentro da LISTVIEW
    private void buscarClientes(String nome)
    {
        clientesFiltrado.clear();
        for (Cliente c : listaClientes)
        {
            if (c.getNomeCliente().toLowerCase().contains(nome.toLowerCase()))
            {
                clientesFiltrado.add(c);
            }
        }
        lvClientes.invalidateViews();

    }

    //==============================================================================================
    //Função que abre o cadastro de Clientes sem parametros
    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(this, CadastroCliente.class);
        startActivity(intent);
    }

    //==============================================================================================
    //Função que abre o cadastro de clientes com parametros

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Cliente clienteAtualizar = clientesFiltrado.get(menuInfo.position);

        Intent i = new Intent(ListaCliente.this, CadastroCliente.class);
        i.putExtra("bCliente", clienteAtualizar);
        startActivity(i);

    }

    //==============================================================================================
    //Função que exclui um Cliente
    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Cliente clienteExcluir = clientesFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Cliente?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        clientesFiltrado.remove(clienteExcluir);
                        listaClientes.remove(clienteExcluir);
                        clienteDao.excluir(clienteExcluir);
                        lvClientes.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    // Função que atualiza a lista de clientes
    @Override
    public void onResume()
    {
        super.onResume();

        listaClientes = clienteDao.listarClientes();
        clientesFiltrado.clear();
        clientesFiltrado.addAll(listaClientes);
        lvClientes.invalidateViews();
    }

    //==============================================================================================

}
