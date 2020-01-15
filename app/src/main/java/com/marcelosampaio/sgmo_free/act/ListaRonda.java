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
import com.marcelosampaio.sgmo_free.adp.ListaRondaAdapter;
import com.marcelosampaio.sgmo_free.dao.RondaDao;
import com.marcelosampaio.sgmo_free.model.Ronda;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.AdapterContextMenuInfo;

public class ListaRonda extends AppCompatActivity {
    private ListView listView;
    private RondaDao dao;
    private List<Ronda> rondas;
    private final List<Ronda> rondasFiltrado = new ArrayList<>();
    private ListaRondaAdapter listaRondaAdapter;


    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ronda);

        listView = findViewById(R.id.lvRondas);
        dao = new RondaDao(this);

        rondas = dao.listarRondas();
        rondasFiltrado.addAll(rondas);

        listaRondaAdapter = new ListaRondaAdapter(ListaRonda.this, rondasFiltrado);

        listView.setAdapter(listaRondaAdapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Ronda ronda = rondas.get(position);

                Intent i = new Intent(ListaRonda.this, ListaVisita.class);
                i.putExtra("bRonda", ronda);
                startActivity(i);

            }
        });


    }

    //==============================================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                //buscarRonda(s);
                return false;
            }
        });
        return true;
    }

    //==============================================================================================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    //==============================================================================================

    public void buscarRonda(String nome) {
        rondasFiltrado.clear();
        for (Ronda r : rondas) {

        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item) {
        Intent intent = new Intent(this, CadastroRonda.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item) {
        AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)
                item.getMenuInfo();

        final Ronda rondaAtualizar = rondasFiltrado.get(menuInfo.position);

        Intent i = new Intent(this, CadastroRonda.class);
        i.putExtra("bRonda", rondaAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item) {
        AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)
                item.getMenuInfo();
        final Ronda rondaExcluir = rondasFiltrado.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Ronda?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rondasFiltrado.remove(rondaExcluir);
                        rondas.remove(rondaExcluir);
                        dao.excluir(rondaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    //==============================================================================================
    @Override
    public void onResume() {
        super.onResume();
        rondas = dao.listarRondas();
        rondasFiltrado.clear();
        rondasFiltrado.addAll(rondas);
        listView.invalidateViews();
    }

    //==============================================================================================
}
