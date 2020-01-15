package com.marcelosampaio.sgmo_free.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaFuncionarioAdapter;
import com.marcelosampaio.sgmo_free.dao.FuncionarioDao;
import com.marcelosampaio.sgmo_free.dao.VencimentoDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Funcionario;
import com.marcelosampaio.sgmo_free.model.Vencimento;

import java.util.Date;
import java.util.List;


public class CadastroVencimento extends AppCompatActivity {

    private Date date = new Date(System.currentTimeMillis());
    long dataAtual = date.getTime();
    long dataFinal = dataAtual;

    private int idVencimento;
    private AppCompatSpinner spFuncionarios;
    private TextInputEditText dtExperiencia;
    private TextInputEditText dtFerias;
    private TextInputEditText dtReciclagem;
    private TextInputEditText dtAso;
    private TextInputEditText dtCracha;
    private TextInputEditText dtCnv;
    private TextInputEditText dtPsicotecnico;
    private TextInputEditText obsVencimento;
    private VencimentoDao vencimentoDao;
    private Vencimento vencimentoModel = null;
    FuncionarioDao funcionarioDao;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_vencimentos);

        vencimentoDao = new VencimentoDao(this);
        funcionarioDao = new FuncionarioDao(this);
        dataHelper = new DataHelper();
        List<Funcionario> listarFuncionarios = funcionarioDao.listarFuncionarios();

        spFuncionarios = findViewById(R.id.spFuncionarioVencimento);
        dtAso = findViewById(R.id.etAso);
        dtCnv = findViewById(R.id.etCnv);
        dtCracha = findViewById(R.id.etCracha);
        dtExperiencia = findViewById(R.id.etExperiencia);
        dtFerias = findViewById(R.id.etFerias);
        dtPsicotecnico = findViewById(R.id.etPsicotecnico);
        dtReciclagem = findViewById(R.id.etReciclagem);
        obsVencimento = findViewById(R.id.etObsVencimento);

        ListaFuncionarioAdapter spnListaFuncionarioAdapter = new ListaFuncionarioAdapter(this, listarFuncionarios);
        this.spFuncionarios.setAdapter(spnListaFuncionarioAdapter);

        Intent i = getIntent();
        if (i.hasExtra("bVencimento")) {

            vencimentoModel = (Vencimento) i.getSerializableExtra("bVencimento");

            idVencimento = vencimentoModel.getIdVencimento();
            setSpFuncionarios(spFuncionarios, vencimentoModel.getIdFuncionario());

            //======================================================================================
            if (vencimentoModel.getAso() > 1000L) {
                dtAso.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getAso()));
            }

            if (vencimentoModel.getAso() > 1000L && vencimentoModel.getAso()
                    <= dataAtual) dtAso.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getAso() >= dataAtual && vencimentoModel.getAso()
                    < dataFinal + (30 * 86400000L)) dtAso.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            if (vencimentoModel.getCnv() > 1000L) {
                dtCnv.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getCnv()));
            }

            if (vencimentoModel.getCnv() > 1000L && vencimentoModel.getCnv()
                    <= dataAtual) dtCnv.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getCnv() >= dataAtual && vencimentoModel.getCnv()
                    < dataFinal + (30 * 86400000L)) dtCnv.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            if (vencimentoModel.getCracha() > 1000L) {
                dtCracha.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getCracha()));
            }

            if (vencimentoModel.getCracha() > 1000L && vencimentoModel.getCracha()
                    <= dataAtual) dtCracha.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getCracha() >= dataAtual && vencimentoModel.getCracha()
                    < dataFinal + (30 * 86400000L)) dtCracha.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            if (vencimentoModel.getExperiencia() > 1000L) {
                dtExperiencia.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getExperiencia()));
            }

            if (vencimentoModel.getExperiencia() > 1000L && vencimentoModel.getExperiencia()
                    <= dataAtual) dtExperiencia.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getExperiencia() >= dataAtual && vencimentoModel.getExperiencia()
                    < dataFinal + (30 * 86400000L)) dtExperiencia.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            if (vencimentoModel.getFerias() > 1000L) {
                dtFerias.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getFerias()));
            }

            if (vencimentoModel.getFerias() > 1000L && vencimentoModel.getFerias()
                    <= dataAtual) dtFerias.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getFerias() >= dataAtual && vencimentoModel.getFerias()
                    < dataFinal + (30 * 86400000L)) dtFerias.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================
            if (vencimentoModel.getPsicotecnico() > 1000L) {
                dtPsicotecnico.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getPsicotecnico()));
            }

            if (vencimentoModel.getPsicotecnico() > 1000L && vencimentoModel.getPsicotecnico()
                    <= dataAtual) dtPsicotecnico.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getPsicotecnico() >= dataAtual && vencimentoModel.getPsicotecnico()
                    < dataFinal + (30 * 86400000L)) dtPsicotecnico.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================
            if (vencimentoModel.getReciclagem() > 1000L) {
                dtReciclagem.setText(dataHelper.convertLongEmStringData
                        (vencimentoModel.getReciclagem()));
            }

            if (vencimentoModel.getReciclagem() > 1000L && vencimentoModel.getReciclagem()
                    <= dataAtual) dtReciclagem.setTextColor(Color.parseColor("#D81B60"));

            if (vencimentoModel.getReciclagem() >= dataAtual && vencimentoModel.getReciclagem()
                    < dataFinal + (30 * 86400000L)) dtReciclagem.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

        }
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

        Vencimento vencimentoSalvar = getDataForm();

        if (idVencimento == 0) {
            long id = vencimentoDao.inserir(vencimentoSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = vencimentoDao.atualizar(vencimentoSalvar);
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
    private Vencimento getDataForm() {
        vencimentoModel = new Vencimento();
        vencimentoModel.setIdVencimento(idVencimento);
        Funcionario funcionarioSelecionado = (Funcionario) this.spFuncionarios.getSelectedItem();
        vencimentoModel.setIdFuncionario(funcionarioSelecionado.getIdFuncionario());
        vencimentoModel.setAso(dataHelper.converteStringDataEmLong(dtAso.getText().toString().trim()));
        vencimentoModel.setCnv(dataHelper.converteStringDataEmLong(dtCnv.getText().toString().trim()));
        vencimentoModel.setCracha(dataHelper.converteStringDataEmLong(dtCracha.getText().toString().trim()));
        vencimentoModel.setExperiencia(dataHelper.converteStringDataEmLong(dtExperiencia.getText().toString().trim()));
        vencimentoModel.setFerias(dataHelper.converteStringDataEmLong(dtFerias.getText().toString().trim()));
        vencimentoModel.setPsicotecnico(dataHelper.converteStringDataEmLong(dtPsicotecnico.getText().toString().trim()));
        vencimentoModel.setReciclagem(dataHelper.converteStringDataEmLong(dtReciclagem.getText().toString().trim()));
        vencimentoModel.setObsVencimento(obsVencimento.getText().toString().trim());

        return vencimentoModel;
    }
    //==========================================================================================
    //Função para setar o valor de spFuncionario nos casos de atualização
    private void setSpFuncionarios(Spinner spnr, long value) {
        ListaFuncionarioAdapter adapter = (ListaFuncionarioAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }


}
