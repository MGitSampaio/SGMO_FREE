package com.marcelosampaio.sgmo_free.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dao.RondaDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Ronda;

public class CadastroRonda extends AppCompatActivity
{

    private int idRonda =0;
    private TextInputEditText etDataInicial;
    private TextInputEditText etHoraInicial;
    private TextInputEditText etkmInicial;
    private TextInputEditText etDataAbastecimento;
    private TextInputEditText etHoraAbastecimento;
    private TextInputEditText etVtr;
    private TextInputEditText etKmAbastecimento;
    private TextInputEditText etValorAbastecimento;
    private TextInputEditText etNotaAbastecimento;
    private TextInputEditText etDataFinal;
    private TextInputEditText etHoraFinal;
    private TextInputEditText etKmFinal;
    private TextInputEditText etObsRonda;


    private Ronda rondaModel = null;
    private RondaDao rondaDao;

    private DataHelper dataHelper = new DataHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ronda);

        rondaDao = new RondaDao(this);

        etDataInicial = findViewById(R.id.etDataInicial);
        etHoraInicial = findViewById(R.id.etHoraInicial);
        etkmInicial = findViewById(R.id.etKmInicial);
        etDataAbastecimento = findViewById(R.id.etDataAbastecimento);
        etHoraAbastecimento = findViewById(R.id.etHoraAbastecimento);
        etVtr = findViewById(R.id.etVtr);
        etKmAbastecimento = findViewById(R.id.etKmAbastecimento);
        etValorAbastecimento = findViewById(R.id.etValorAbastecimento);
        etNotaAbastecimento = findViewById(R.id.etNotaAbastecimento);
        etDataFinal = findViewById(R.id.etDataFinal);
        etHoraFinal = findViewById(R.id.etHoraFinal);
        etKmFinal = findViewById(R.id.etKmFinal);
        etObsRonda = findViewById(R.id.etObsRonda);


        Intent i = getIntent();
        if (i.hasExtra("bRonda"))
        {
            rondaModel = (Ronda) i.getSerializableExtra("bRonda");
            idRonda = rondaModel.getIdRonda();
            //======================================================================================
            if (rondaModel.getDataInicial() > 1000L) {
                etDataInicial.setText(dataHelper.convertLongEmStringData
                        (rondaModel.getDataInicial()));
            }
            //======================================================================================

            if (rondaModel.getDataFinal() > 1000L) {
                etDataFinal.setText(dataHelper.convertLongEmStringData
                        (rondaModel.getDataFinal()));
            }
            //======================================================================================
            if (rondaModel.getDataAbastecimento() > 1000L) {
                etDataAbastecimento.setText(dataHelper.convertLongEmStringData
                        (rondaModel.getDataAbastecimento()));
            }
            //======================================================================================

            etHoraAbastecimento.setText(rondaModel.getHoraAbastecimento());
            etHoraInicial.setText(rondaModel.getHoraInicial());
            etkmInicial.setText(rondaModel.getKmInicial());
            etKmAbastecimento.setText(rondaModel.getKmAbastecimento());
            etHoraAbastecimento.setText(rondaModel.getHoraAbastecimento());
            etVtr.setText(rondaModel.getVtr());
            etValorAbastecimento.setText(rondaModel.getValorAbastecimento());
            etNotaAbastecimento.setText(rondaModel.getNotaAbastecimento());
            etHoraFinal.setText(rondaModel.getHoraFinal());
            etKmFinal.setText(rondaModel.getKmFinal());
            etObsRonda.setText(rondaModel.getObsRonda());
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

        Ronda rondaSalvar = getDataForm();
        if(idRonda == 0)
        {
            long id = rondaDao.inserir(rondaSalvar);
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
            long id = rondaDao.atualizar(rondaSalvar);
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
    private Ronda getDataForm()
    {
        this.rondaModel = new Ronda();
        this.rondaModel.setIdRonda(this.idRonda);
        this.rondaModel.setDataInicial(
                dataHelper.converteStringDataEmLong(this.etDataInicial.getText().toString().trim()));
        this.rondaModel.setHoraInicial(this.etHoraInicial.getText().toString().trim());
        this.rondaModel.setKmInicial(this.etkmInicial.getText().toString().trim());
        this.rondaModel.setDataAbastecimento(
                dataHelper.converteStringDataEmLong(this.etDataAbastecimento.getText()
                        .toString().trim()));
        this.rondaModel.setHoraAbastecimento(this.etHoraAbastecimento.getText().toString().trim());
        this.rondaModel.setVtr(this.etVtr.getText().toString().trim());
        this.rondaModel.setKmAbastecimento(this.etKmAbastecimento.getText().toString().trim());
        this.rondaModel.setValorAbastecimento(this.etValorAbastecimento.getText().toString().trim());
        this.rondaModel.setNotaAbastecimento(this.etNotaAbastecimento.getText().toString().trim());
        this.rondaModel.setDataFinal(dataHelper.converteStringDataEmLong(
                this.etDataFinal.getText().toString().trim()));
        this.rondaModel.setHoraFinal(this.etHoraFinal.getText().toString().trim());
        this.rondaModel.setKmFinal(this.etKmFinal.getText().toString().trim());
        this.rondaModel.setObsRonda(this.etObsRonda.getText().toString().trim());

        return rondaModel;
    }
    //==============================================================================================
}
