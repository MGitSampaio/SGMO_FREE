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
import com.marcelosampaio.sgmo_free.dao.DatasDao;
import com.marcelosampaio.sgmo_free.dao.OcorrenciaDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Ocorrencia;

public class CadastroOcorrencia extends AppCompatActivity {

    private long idOcorrencia;
    private TextInputEditText etCliente;
    private TextInputEditText etUnidade;
    private TextInputEditText etBo;
    private TextInputEditText etTipoOcorrencia;
    private TextInputEditText etData;
    private TextInputEditText etHora;
    private TextInputEditText etRelato;

    private OcorrenciaDao ocorrenciaDao;
    private Ocorrencia ocorrenciaModel = null;
    private DataHelper dataHelper = new DataHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ocorrencia);
        DatasDao diaDao = new DatasDao(this);
        ocorrenciaDao = new OcorrenciaDao(this);

        etCliente = findViewById(R.id.etClienteOcorrencia);
        etUnidade = findViewById(R.id.etUnidadeOcorrencia);
        etBo = findViewById(R.id.etBo);
        etTipoOcorrencia = findViewById(R.id.etTipoOcorrencia);
        etData = findViewById(R.id.etDataOcorrencia);
        etHora = findViewById(R.id.etHoraOcorrencia);
        etRelato = findViewById(R.id.etRelato);

        Intent i = getIntent();
        if (i.hasExtra("bOcorrencia")) {
            ocorrenciaModel = (Ocorrencia) i.getSerializableExtra("bOcorrencia");
            idOcorrencia = ocorrenciaModel.getIdOcorrencia();
            etCliente.setText(ocorrenciaModel.getCliente());
            etUnidade.setText(ocorrenciaModel.getUnidade());
            etBo.setText(ocorrenciaModel.getBo());
            etTipoOcorrencia.setText(ocorrenciaModel.getTipoOcorrencia());

            if(ocorrenciaModel.getData() >1000L){
                etData.setText(dataHelper.convertLongEmStringData(ocorrenciaModel.getData()));
            }

            etHora.setText(ocorrenciaModel.getHora());
            etRelato.setText(ocorrenciaModel.getRelato());

        } else {
           // etData.setText(diaDao.dataApp());
            etRelato.setText("Quem ?"+"\n"+"Quando ?"+"\n"+"Como ?"+"\n"+"O que ?"+"\n"+"Onde ?"+"\n"+
                    "Porque ?"+"\n"+"Quanto ?"+"\n"+"Funcionarios Envolvidos");
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

        Ocorrencia ocorrenciaSalvar = getDataForm();


        if (idOcorrencia == 0) {
            long id = ocorrenciaDao.inserir(ocorrenciaSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = ocorrenciaDao.atualizar(ocorrenciaSalvar);
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
    private Ocorrencia getDataForm() {
        this.ocorrenciaModel = new Ocorrencia();
        this.ocorrenciaModel.setIdOcorrencia((int) this.idOcorrencia);
        this.ocorrenciaModel.setCliente(this.etCliente.getText().toString().trim());
        this.ocorrenciaModel.setUnidade(this.etUnidade.getText().toString().trim());
        this.ocorrenciaModel.setBo(this.etBo.getText().toString().trim());
        this.ocorrenciaModel.setTipoOcorrencia(this.etTipoOcorrencia.getText().toString().trim());
        this.ocorrenciaModel.setData(dataHelper.converteStringDataEmLong(etData.getText()
                .toString().trim()));
        this.ocorrenciaModel.setHora(this.etHora.getText().toString().trim());
        this.ocorrenciaModel.setRelato(this.etRelato.getText().toString().trim());

        return ocorrenciaModel;
    }
    //==============================================================================================

}

