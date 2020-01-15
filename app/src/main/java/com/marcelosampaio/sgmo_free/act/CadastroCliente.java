package com.marcelosampaio.sgmo_free.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.dao.ClienteDao;
import com.marcelosampaio.sgmo_free.model.Cliente;

public class CadastroCliente extends AppCompatActivity {
    private long idCliente;
    private TextInputEditText etCliente;
    private TextInputEditText etObsCliente;
    private ClienteDao clienteDao;
    private Cliente clienteModel = null;

    private AdView bannerCliente;

    //==============================================================================================
    //Funcão que cria e seta os valores da activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        bannerCliente = findViewById(R.id.bannerCliente);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerCliente.loadAd(adRequest);

        clienteDao = new ClienteDao(this);
        etCliente = findViewById(R.id.etNomeCliente);
        etObsCliente = findViewById(R.id.etObsCliente);

        Intent i = getIntent();
        if (i.hasExtra("bCliente")) {
            clienteModel = (Cliente) i.getSerializableExtra("bCliente");
            idCliente = clienteModel.getIdCliente();
            etCliente.setText(clienteModel.getNomeCliente());
            etObsCliente.setText(clienteModel.getObsCliente());
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

        Cliente clienteSalvar = getDataForm();


        if (idCliente == 0) {
            long id = clienteDao.inserir(clienteSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = clienteDao.atualizar(clienteSalvar);
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
    private Cliente getDataForm() {
        this.clienteModel = new Cliente();
        this.clienteModel.setIdCliente((int) this.idCliente);
        this.clienteModel.setNomeCliente(this.etCliente.getText().toString().trim());
        this.clienteModel.setObsCliente(this.etObsCliente.getText().toString().trim());
        return clienteModel;
    }
    //==============================================================================================

}

