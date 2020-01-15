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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dao.VisitaDao;
import com.marcelosampaio.sgmo_free.model.Ronda;
import com.marcelosampaio.sgmo_free.model.Visita;

import java.util.ArrayList;
import java.util.List;

public class ListaVisita extends AppCompatActivity {

    private int idRonda;
    private ListView listView;
    private VisitaDao dao;
    private List<Visita> visitas;
    private final List<Visita>visitaFiltrada = new ArrayList<>();

    private Ronda rondaModel = null;

    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visita);
        Intent i = getIntent();

        if (i.hasExtra("bRonda"))
            rondaModel = (Ronda) i.getSerializableExtra("bRonda");
        {
            idRonda=rondaModel.getIdRonda();
        }

        listView = findViewById(R.id.lvVisitas);
        dao = new VisitaDao(this);
        visitas = dao.listarVisitas(idRonda);
        visitaFiltrada.addAll(visitas);

        ArrayAdapter<Visita> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, visitaFiltrada);
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
                buscarVisita(s);
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

    private void buscarVisita(String nome)
    {
        visitaFiltrada.clear();
        for (Visita c : visitaFiltrada)
        {
            if (c.getUnidade().toLowerCase().contains(nome.toLowerCase()))
            {
                visitaFiltrada.add(c);
            }
        }
        listView.invalidateViews();

    }

    //==============================================================================================

    public void abrirCadastro(MenuItem item)
    {

        Intent intent = new Intent(ListaVisita.this, CadastroVisita.class);
        startActivity(intent);
    }

    //==============================================================================================

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Visita visitaAtualizar = visitaFiltrada.get(menuInfo.position);
        Intent i = new Intent(this, CadastroVisita.class);
        i.putExtra("bVisita", visitaAtualizar);
        startActivity(i);

    }

    //==============================================================================================

    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();

        final Visita visitaExcluir = visitaFiltrada.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Confirma a exclusão do Visita?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        visitaFiltrada.remove(visitaExcluir);
                        visitas.remove(visitaExcluir);
                        dao.excluir(visitaExcluir);
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
        visitas = dao.listarVisitas(rondaModel.getIdRonda());
        visitaFiltrada.clear();
        visitaFiltrada.addAll(visitas);
        listView.invalidateViews();

    }

    //==============================================================================================

}
