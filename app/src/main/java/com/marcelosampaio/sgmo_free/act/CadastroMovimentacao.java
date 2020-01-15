package com.marcelosampaio.sgmo_free.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.dao.DatasDao;
import com.marcelosampaio.sgmo_free.dao.MovimentacaoDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Movimentacao;
import com.marcelosampaio.sgmo_free.R;

import java.util.Calendar;
import java.util.Date;


public class CadastroMovimentacao extends AppCompatActivity {

    private long idMovimentacao = 0;
    private TextInputEditText etDataMovimentacao;
    private TextInputEditText etClienteMovimentacao;
    private TextInputEditText etUnidadeMovimentacao;
    private TextInputEditText etReAusente;
    private TextInputEditText etNomeAusente;
    private TextInputEditText etReCobertura;
    private TextInputEditText etNomeCobertura;
    private TextInputEditText etMotivo;
    private TextInputEditText etObsMovimentacao;

    private Movimentacao movimentacaoModel = null;
    private MovimentacaoDao movimentacaoDao;
    private DataHelper dataHelper = new DataHelper();

    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_movimentacao);


        movimentacaoDao = new MovimentacaoDao(this);

        etDataMovimentacao = findViewById(R.id.etDataMovimentacao);
        etClienteMovimentacao = findViewById(R.id.etClienteMovimentacao);
        etUnidadeMovimentacao = findViewById(R.id.etUnidadeMovimentacao);
        etReAusente = findViewById(R.id.etReAusente);
        etNomeAusente = findViewById(R.id.etNomeAusente);
        etReCobertura = findViewById(R.id.etReCobertura);
        etNomeCobertura = findViewById(R.id.etNomeCobertura);
        etMotivo = findViewById(R.id.etMotivo);
        etObsMovimentacao = findViewById(R.id.etObsMovimentacao);

        Intent i = getIntent();
        if (i.hasExtra("bMovimentacao")) {
            movimentacaoModel = (Movimentacao) i.getSerializableExtra("bMovimentacao");
            idMovimentacao = movimentacaoModel.getIdMovimentacao();
            etDataMovimentacao.setText(dataHelper.convertLongEmStringData(movimentacaoModel.getDia()));
            etClienteMovimentacao.setText(movimentacaoModel.getCliente());
            etUnidadeMovimentacao.setText(movimentacaoModel.getUnidade());
            etReAusente.setText(movimentacaoModel.getReAusente());
            etNomeAusente.setText(movimentacaoModel.getNomeAusente());
            etReCobertura.setText(movimentacaoModel.getReCobertura());
            etNomeCobertura.setText(movimentacaoModel.getNomeCobertura());
            etMotivo.setText(movimentacaoModel.getMotivo());
            etObsMovimentacao.setText(movimentacaoModel.getObsMovimentacao());

        }

        etDataMovimentacao.setText(dataHelper.convertLongEmStringData(calendar.getTimeInMillis()));

    }

    //==============================================================================================
    // Função que cria o menu de cadastro
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    //==============================================================================================
    //Função que Salva os dados
    public void salvar(MenuItem item) {

        Movimentacao movimentacaoSalvar = getDataForm();


        if (idMovimentacao == 0) {
            long id = movimentacaoDao.inserir(movimentacaoSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = movimentacaoDao.atualizar(movimentacaoSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Alterado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Alterar", Toast.LENGTH_SHORT).show();
            }
        }

        this.finish();
    }

    //==============================================================================================
    //Função para pegar os dados do formulário
    private Movimentacao getDataForm() {
        this.movimentacaoModel = new Movimentacao();
        this.movimentacaoModel.setIdMovimentacao((int) this.idMovimentacao);
        this.movimentacaoModel.setDia(dataHelper.converteStringDataEmLong(
                etDataMovimentacao.getText().toString().trim()));
        this.movimentacaoModel.setCliente(this.etClienteMovimentacao.getText().toString().trim());
        this.movimentacaoModel.setUnidade(this.etUnidadeMovimentacao.getText().toString().trim());
        this.movimentacaoModel.setReAusente(this.etReAusente.getText().toString().trim());
        this.movimentacaoModel.setNomeAusente(this.etNomeAusente.getText().toString().trim());
        this.movimentacaoModel.setReCobertura(this.etReCobertura.getText().toString().trim());
        this.movimentacaoModel.setNomeCobertura(this.etNomeCobertura.getText().toString().trim());
        this.movimentacaoModel.setMotivo(this.etMotivo.getText().toString().trim());
        this.movimentacaoModel.setObsMovimentacao(this.etObsMovimentacao.getText().toString().trim());
        return movimentacaoModel;
    }
    //==============================================================================================

}
