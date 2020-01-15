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
import com.marcelosampaio.sgmo_free.adp.ListaMovimentacaoAdapter;
import com.marcelosampaio.sgmo_free.dao.MovimentacaoDao;
import com.marcelosampaio.sgmo_free.model.Movimentacao;

import java.util.ArrayList;
import java.util.List;

public class ListaMovimentacao extends AppCompatActivity {

    private ListView lvMovimentacao;
    private MovimentacaoDao movimentacaoDao;
    private List<Movimentacao> listaMovimentacoes;
    private final List<Movimentacao> movimentacaoFiltrado = new ArrayList<>();
    private ListaMovimentacaoAdapter listaMovimentacaoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_movimentacao);

        lvMovimentacao = findViewById(R.id.lvMovimentacao);

        movimentacaoDao = new MovimentacaoDao(this);
        listaMovimentacoes = movimentacaoDao.listarMovimentacoes();
        movimentacaoFiltrado.addAll(listaMovimentacoes);

        listaMovimentacaoAdapter = new ListaMovimentacaoAdapter(ListaMovimentacao.this, movimentacaoFiltrado);
        lvMovimentacao.setAdapter(listaMovimentacaoAdapter);
        registerForContextMenu(lvMovimentacao);
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

                //buscarCargos(s);
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
    //Função que pesquisa uma movimentação dentro da LISTVIEW
    public void buscarMov(String nome)
    {
        movimentacaoFiltrado.clear();
        for (Movimentacao m : listaMovimentacoes)
        {

        }
        lvMovimentacao.invalidateViews();

    }

    //==============================================================================================
    //Função que abre o cadastro de Movimentações sem parametros
    public void abrirCadastro(MenuItem item)
    {
        Intent intent = new Intent(ListaMovimentacao.this, CadastroMovimentacao.class);
        startActivity(intent);
    }

    //==============================================================================================
    //Função que abre o cadasatro de movimentações com parametros
    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Movimentacao movimentacaoAtualizar = movimentacaoFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroMovimentacao.class);
        i.putExtra("bMovimentacao", movimentacaoAtualizar);
        startActivity(i);

    }

    //==============================================================================================
    //Função que exclui uma Movimentacao
    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Movimentacao movimentacaoExcluir = movimentacaoFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão da Movimentacao?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        movimentacaoFiltrado.remove(movimentacaoExcluir);
                        listaMovimentacoes.remove(movimentacaoExcluir);
                        movimentacaoDao.excluir(movimentacaoExcluir);
                        lvMovimentacao.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    // Função que atualiza a lista de movimentacoes
    @Override
    public void onResume()
    {
        super.onResume();
        listaMovimentacoes = movimentacaoDao.listarMovimentacoes();
        movimentacaoFiltrado.clear();
        movimentacaoFiltrado.addAll(listaMovimentacoes);
        lvMovimentacao.invalidateViews();
    }

    //==============================================================================================

}

