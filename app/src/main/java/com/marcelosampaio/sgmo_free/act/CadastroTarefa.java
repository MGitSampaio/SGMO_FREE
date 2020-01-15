package com.marcelosampaio.sgmo_free.act;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dao.TarefaDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Tarefa;

public class CadastroTarefa extends AppCompatActivity
{
    private int idTarefa =0;
    private TextInputEditText etTarefa;
    private TextInputEditText etData;
    private TextInputEditText etHora;
    private TextInputEditText etDescricao;
    private CheckBox cbConcluida;

    private Tarefa tarefaModel = null;
    private TarefaDao tarefaDao;
    private DataHelper dataHelper = new DataHelper();

    private AdView bannerTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        bannerTarefa = findViewById(R.id.bannerTarefas);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerTarefa.loadAd(adRequest);

        tarefaDao=new TarefaDao(this);

        etTarefa = findViewById(R.id.etTarefa);
        etData = findViewById(R.id.etDataTarefa);
        etHora = findViewById(R.id.etHoraTarefa);
        etDescricao =findViewById(R.id.etDescricaoTarefa);
        cbConcluida = findViewById(R.id.cbConcluida);

        Intent i = getIntent();
        if (i.hasExtra("bTarefa"))
        {
            tarefaModel = (Tarefa) i.getSerializableExtra("bTarefa");
            idTarefa = tarefaModel.getIdTarefa();
            etTarefa.setText(tarefaModel.getTarefa());
            //======================================================================================
            if (tarefaModel.getData() > 1000L) {
                etData.setText(dataHelper.convertLongEmStringData
                        (tarefaModel.getData()));
            }
            //======================================================================================

            etHora.setText(tarefaModel.getHora());
            etDescricao.setText(tarefaModel.getDescricao());
            if(tarefaModel.getStatus().equals("True")){
                cbConcluida.setChecked(true);
                cbConcluida.setText("Concluida!!!");
            }else{
                cbConcluida.setChecked(false);
                cbConcluida.setText("Concluida?");
            }



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

        Tarefa tarefaSalvar = getDataForm();



        if(idTarefa == 0)
        {
            long id = tarefaDao.inserir(tarefaSalvar);
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
            long id = tarefaDao.atualizar(tarefaSalvar);
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
    private Tarefa getDataForm()
    {
        this.tarefaModel = new Tarefa();
        this.tarefaModel.setIdTarefa( this.idTarefa);
        this.tarefaModel.setTarefa(this.etTarefa.getText().toString().trim());
        this.tarefaModel.setData(dataHelper.converteStringDataEmLong(this.etData.getText().toString().trim()));
        this.tarefaModel.setHora(this.etHora.getText().toString().trim());
        this.tarefaModel.setDescricao(this.etDescricao.getText().toString().trim());
        if(cbConcluida.isChecked()){
            this.tarefaModel.setStatus("True");
        } else{this.tarefaModel.setStatus("False");}

        return tarefaModel;
    }
    //==============================================================================================
}
