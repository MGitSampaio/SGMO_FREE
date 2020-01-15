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
import com.marcelosampaio.sgmo_free.dao.UniformeDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Funcionario;
import com.marcelosampaio.sgmo_free.model.Uniforme;

import java.util.Date;
import java.util.List;

public class CadastroUniforme extends AppCompatActivity {
    private Date date = new Date(System.currentTimeMillis());
    long dataAtual = date.getTime();
    long dataFinal = dataAtual;
    private int idUniforme;
    private AppCompatSpinner spFuncionarios;
    private TextInputEditText etNrCobertura;
    private TextInputEditText etQtdCobertura;
    private TextInputEditText etDtCobertura;
    private TextInputEditText etNrBlusa;
    private TextInputEditText etQtdBlusa;
    private TextInputEditText etDtBlusa;
    private TextInputEditText etNrCamisa;
    private TextInputEditText etQtdCamisa;
    private TextInputEditText etDtCamisa;
    private TextInputEditText etNrCalca;
    private TextInputEditText etQtdCalca;
    private TextInputEditText etDtCalca;
    private TextInputEditText etNrCalcado;
    private TextInputEditText etQtdCalcado;
    private TextInputEditText etDtCalcado;
    private TextInputEditText etObsUniforme;
    private UniformeDao uniformeDao;
    private Uniforme uniformeModel = null;
    FuncionarioDao funcionarioDao;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_uniforme);

        uniformeDao = new UniformeDao(this);
        funcionarioDao = new FuncionarioDao(this);
        dataHelper = new DataHelper();
        List<Funcionario> listarFuncionarios = funcionarioDao.listarFuncionarios();

        spFuncionarios = findViewById(R.id.spFuncionarioUniforme);
        etNrCobertura = findViewById(R.id.etNrCobertura);
        etQtdCobertura = findViewById(R.id.etQtdCobertura);
        etDtCobertura = findViewById(R.id.etDtCobertura);
        etNrBlusa = findViewById(R.id.etNrBlusa);
        etQtdBlusa = findViewById(R.id.etQtdBlusa);
        etDtBlusa = findViewById(R.id.etDtBlusa);
        etNrCamisa = findViewById(R.id.etNrCamisa);
        etQtdCamisa = findViewById(R.id.etQtdCamisa);
        etDtCamisa = findViewById(R.id.etDtCamisa);
        etNrCalca = findViewById(R.id.etNrCalca);
        etQtdCalca = findViewById(R.id.etQtdCalca);
        etDtCalca = findViewById(R.id.etDtCalca);
        etNrCalcado = findViewById(R.id.etNrCalcado);
        etQtdCalcado = findViewById(R.id.etQtdCalcado);
        etDtCalcado = findViewById(R.id.etDtCalcado);
        etObsUniforme = findViewById(R.id.etObsUniforme);


        ListaFuncionarioAdapter spnListaFuncionarioAdapter = new ListaFuncionarioAdapter(this, listarFuncionarios);
        this.spFuncionarios.setAdapter(spnListaFuncionarioAdapter);

        Intent i = getIntent();
        if (i.hasExtra("bUniforme")) {

            uniformeModel = (Uniforme) i.getSerializableExtra("bUniforme");

            idUniforme = uniformeModel.getIdUniforme();
            setSpFuncionarios(spFuncionarios, uniformeModel.getIdFuncionario());
            //======================================================================================
            etNrCalcado.setText(uniformeModel.getNrCalcado());
            etQtdCalcado.setText(uniformeModel.getQtdCalcado());

            if (uniformeModel.getDtCalcado() > 1000L) {
                etDtCalcado.setText(dataHelper.convertLongEmStringData
                        (uniformeModel.getDtCalcado()));
            }

            if (uniformeModel.getDtCalcado() > 1000L && uniformeModel.getDtCalcado()
                    <= dataAtual) etDtCalcado.setTextColor(Color.parseColor("#D81B60"));

            if (uniformeModel.getDtCalcado() >= dataAtual && uniformeModel.getDtCalcado()
                    < dataFinal + (30 * 86400000L)) etDtCalcado.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            etNrCalca.setText(uniformeModel.getNrCalca());
            etQtdCalca.setText(uniformeModel.getQtdCalca());

            if (uniformeModel.getDtCalca() > 1000L) {
                etDtCalca.setText(dataHelper.convertLongEmStringData
                        (uniformeModel.getDtCalca()));
            }

            if (uniformeModel.getDtCalca() > 1000L && uniformeModel.getDtCalca()
                    <= dataAtual) etDtCalca.setTextColor(Color.parseColor("#D81B60"));

            if (uniformeModel.getDtCalca() >= dataAtual && uniformeModel.getDtCalca()
                    < dataFinal + (30 * 86400000L)) etDtCalca.setTextColor
                    (Color.parseColor("#FF673AB7"));



            //======================================================================================

            etNrCamisa.setText(uniformeModel.getNrCamisa());
            etQtdCamisa.setText(uniformeModel.getQtdCamisa());

            if (uniformeModel.getDtCamisa() > 1000L) {
                etDtCamisa.setText(dataHelper.convertLongEmStringData
                        (uniformeModel.getDtCamisa()));
            }

            if (uniformeModel.getDtCamisa() > 1000L && uniformeModel.getDtCamisa()
                    <= dataAtual) etDtCamisa.setTextColor(Color.parseColor("#D81B60"));

            if (uniformeModel.getDtCamisa() >= dataAtual && uniformeModel.getDtCamisa()
                    < dataFinal + (30 * 86400000L)) etDtCamisa.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            etNrBlusa.setText(uniformeModel.getNrBlusa());
            etQtdBlusa.setText(uniformeModel.getQtdBlusa());

            if (uniformeModel.getDtBlusa() > 1000L) {
                etDtBlusa.setText(dataHelper.convertLongEmStringData
                        (uniformeModel.getDtBlusa()));
            }

            if (uniformeModel.getDtBlusa() > 1000L && uniformeModel.getDtBlusa()
                    <= dataAtual) etDtBlusa.setTextColor(Color.parseColor("#D81B60"));

            if (uniformeModel.getDtBlusa() >= dataAtual && uniformeModel.getDtBlusa()
                    < dataFinal + (30 * 86400000L)) etDtBlusa.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            etNrCobertura.setText(uniformeModel.getNrCobertura());
            etQtdCobertura.setText(uniformeModel.getQtdCobertura());

            if (uniformeModel.getDtCobertura() > 1000L)
                etDtCobertura.setText(dataHelper.convertLongEmStringData
                        (uniformeModel.getDtCobertura()));

            if (uniformeModel.getDtCobertura() > 1000L && uniformeModel.getDtCobertura()
                    <= dataAtual) etDtCobertura.setTextColor(Color.parseColor("#D81B60"));

            if (uniformeModel.getDtCobertura() >= dataAtual && uniformeModel.getDtCobertura()
                    < dataFinal + (30 * 86400000L)) etDtCobertura.setTextColor
                    (Color.parseColor("#FF673AB7"));

            //======================================================================================

            etObsUniforme.setText(uniformeModel.getObsUniforme());
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
        Uniforme uniformeSalvar = getDataForm();

        if (idUniforme == 0) {
            long id = uniformeDao.inserir(uniformeSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = uniformeDao.atualizar(uniformeSalvar);
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
    private Uniforme getDataForm() {
        uniformeModel = new Uniforme();
        uniformeModel.setIdUniforme(idUniforme);
        Funcionario funcionarioSelecionado = (Funcionario) this.spFuncionarios.getSelectedItem();
        uniformeModel.setIdFuncionario(funcionarioSelecionado.getIdFuncionario());
        uniformeModel.setNrCobertura(etNrCobertura.getText().toString().trim());
        uniformeModel.setQtdCobertura(etQtdCobertura.getText().toString().trim());
        uniformeModel.setDtCobertura(dataHelper.converteStringDataEmLong(etDtCobertura.getText().toString().trim()));
        uniformeModel.setNrBlusa(etNrBlusa.getText().toString().trim());
        uniformeModel.setQtdBlusa(etQtdBlusa.getText().toString().trim());
        uniformeModel.setDtBlusa(dataHelper.converteStringDataEmLong(etDtBlusa.getText().toString().trim()));
        uniformeModel.setNrCamisa(etNrCamisa.getText().toString().trim());
        uniformeModel.setQtdCamisa(etQtdCamisa.getText().toString().trim());
        uniformeModel.setDtCamisa(dataHelper.converteStringDataEmLong(etDtCamisa.getText().toString().trim()));
        uniformeModel.setNrCalca(etNrCalca.getText().toString().trim());
        uniformeModel.setQtdCalca(etQtdCalca.getText().toString().trim());
        uniformeModel.setDtCalca(dataHelper.converteStringDataEmLong(etDtCalca.getText().toString().trim()));
        uniformeModel.setNrCalcado(etNrCalcado.getText().toString().trim());
        uniformeModel.setQtdCalcado(etQtdCalcado.getText().toString().trim());
        uniformeModel.setDtCalcado(dataHelper.converteStringDataEmLong(etDtCalcado.getText().toString().trim()));
        uniformeModel.setObsUniforme(etObsUniforme.getText().toString().trim());

        return uniformeModel;
    }

    //==============================================================================================
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
