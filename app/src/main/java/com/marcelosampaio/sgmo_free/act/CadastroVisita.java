package com.marcelosampaio.sgmo_free.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaRondaAdapter;
import com.marcelosampaio.sgmo_free.dao.RondaDao;
import com.marcelosampaio.sgmo_free.dao.VisitaDao;
import com.marcelosampaio.sgmo_free.model.Ronda;
import com.marcelosampaio.sgmo_free.model.Visita;

import java.util.List;

public class CadastroVisita extends AppCompatActivity
{
    private AppCompatSpinner spiRonda;
    private int idVisita =0;
    private TextInputEditText etUnidade;
    private TextInputEditText etHoraChegada;
    private TextInputEditText etKmChegada;
    private TextInputEditText etHoraSaida;
    private TextInputEditText etObsVisita;

    private Visita visitaModel = null;
    private VisitaDao visitaDao;

    private AdView bannerVisita;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_visita);

        bannerVisita = findViewById(R.id.bannerVisita);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerVisita.loadAd(adRequest);

        RondaDao rondaDao = new RondaDao(this);
        visitaDao = new VisitaDao(this);

        etUnidade = findViewById(R.id.etUnidadeVisita);
        etHoraChegada = findViewById(R.id.etHoraChegada);
        etKmChegada = findViewById(R.id.etKmChegada);
        etHoraSaida = findViewById(R.id.etHoraSaida);
        etObsVisita = findViewById(R.id.etObsVisita);
        spiRonda = findViewById(R.id.spiRondas);

        List<Ronda>listarRondas = rondaDao.listarRondas();
        ListaRondaAdapter spnListaRondaAdapter = new ListaRondaAdapter(this, listarRondas);
        this.spiRonda.setAdapter(spnListaRondaAdapter);

        Intent i = getIntent();


        if (i.hasExtra("bVisita"))
        {
            visitaModel = (Visita) i.getSerializableExtra("bVisita");
            idVisita =visitaModel.getIdVisita();
            setSpRonda(spiRonda, visitaModel.getIdRonda());
            etUnidade.setText(visitaModel.getUnidade());
            etHoraChegada.setText(visitaModel.getHoraChegada());
            etKmChegada.setText(visitaModel.getKmChegada());
            etHoraSaida.setText(visitaModel.getHoraSaida());
            etObsVisita.setText(visitaModel.getObsVisita());

        }
    }

    //==============================================================================================
    // Função que cria o menu de cadastro
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_cadastro, menu);
        return true;
    }
    //==============================================================================================
    //Função que Salva os dados
    public void salvar(MenuItem item)
    {

        Visita visitaSalvar = getDataForm();

        if(idVisita == 0)
        {
            long id = visitaDao.inserir(visitaSalvar);
            if (id > 0)
            {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        }

        else
        {
            long id = visitaDao.atualizar(visitaSalvar);
            if (id > 0)
            {
                Toast.makeText(this, "Registro Alterado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Falha ao Alterar", Toast.LENGTH_SHORT).show();
            }
        }

        this.finish();
    }
    //==============================================================================================
    //Função para pegar os dados do formulário
    private Visita getDataForm()
    {
        this.visitaModel = new Visita();
        this.visitaModel.setIdVisita( this.idVisita);
        Ronda rondaSelecionada = (Ronda) spiRonda.getSelectedItem();
        visitaModel.setIdRonda(rondaSelecionada.getIdRonda());
        this.visitaModel.setUnidade(this.etUnidade.getText().toString().trim());
        this.visitaModel.setHoraChegada(this.etHoraChegada.getText().toString().trim());
        this.visitaModel.setKmChegada(this.etKmChegada.getText().toString().trim());
        this.visitaModel.setHoraSaida(this.etHoraSaida.getText().toString().trim());
        this.visitaModel.setObsVisita(this.etObsVisita.getText().toString().trim());
        return visitaModel;
    }
    //==============================================================================================

    //==============================================================================================
    //Função para setar o valor de spCargo nos casos de atualização
    private static void setSpRonda(Spinner spnr, long value) {
        ListaRondaAdapter adapter = (ListaRondaAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}