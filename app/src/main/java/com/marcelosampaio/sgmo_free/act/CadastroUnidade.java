package com.marcelosampaio.sgmo_free.act;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaClienteAdapter;
import com.marcelosampaio.sgmo_free.dao.ClienteDao;
import com.marcelosampaio.sgmo_free.dao.UnidadeDao;
import com.marcelosampaio.sgmo_free.model.Cliente;
import com.marcelosampaio.sgmo_free.model.Unidade;

import java.util.List;

public class CadastroUnidade extends AppCompatActivity {
    private int idUnidade;
    private AppCompatSpinner spCliente;
    private TextInputEditText etUnidade;
    private TextInputEditText etContato;
    private TextInputEditText etTelefone;
    private TextInputEditText etEndereco;
    private TextInputEditText etBairro;
    private TextInputEditText etCidade;
    private TextInputEditText etUf;
    private TextInputEditText etObsUnidade;

    private UnidadeDao unidadeDao;
    private Unidade unidadeModel = null;

    //==============================================================================================
    //Funcão que cria e seta os valores da activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_unidade);

        unidadeDao = new UnidadeDao(this);
        ClienteDao clienteDao = new ClienteDao(this);
        List<Cliente> listarClientes = clienteDao.listarClientes();

        spCliente = findViewById(R.id.spClienteUnidade);
        etUnidade = findViewById(R.id.etUnidade);
        etContato = findViewById(R.id.etContato);
        etTelefone = findViewById(R.id.etTelefoneUnidade);
        ImageButton btnCall = findViewById(R.id.btnCallUni);
        etEndereco = findViewById(R.id.etEnderecoUnidade);
        etBairro = findViewById(R.id.etBairroUnidade);
        etCidade = findViewById(R.id.etCidadeUnidade);
        etUf = findViewById(R.id.etUfUnidade);
        etObsUnidade = findViewById(R.id.etObsUnidade);

        ListaClienteAdapter spnListaClienteAdapter = new ListaClienteAdapter(this, listarClientes);
        this.spCliente.setAdapter(spnListaClienteAdapter);

        Intent i = getIntent();
        if (i.hasExtra("atualizarUnidade")) {
            unidadeModel = (Unidade) i.getSerializableExtra("atualizarUnidade");
            idUnidade = unidadeModel.getIdUnidade();
            setSpCliente(spCliente, unidadeModel.getIdCliente());
            etUnidade.setText(unidadeModel.getNomeUnidade());
            etContato.setText(unidadeModel.getNomeContato());
            etTelefone.setText(unidadeModel.getTelefone());
            etEndereco.setText(unidadeModel.getEndereco());
            etBairro.setText(unidadeModel.getBairro());
            etCidade.setText(unidadeModel.getCidade());
            etUf.setText(unidadeModel.getUf());
            etObsUnidade.setText(unidadeModel.getObsUnidade());

        }

        btnCall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String numero = etTelefone.getText().toString();
                Uri uri = Uri.parse("tel:" + numero);
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.
                        PERMISSION_GRANTED) {ActivityCompat.requestPermissions
                        (CadastroUnidade.this,new String[]
                                {Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(intent);
            }
        });

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
        Unidade unidadeSalvar = getDataForm();

        if(idUnidade == 0)
        {
            long id = unidadeDao.inserir(unidadeSalvar);
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
            long id = unidadeDao.atualizar(unidadeSalvar);
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
    private Unidade getDataForm()
    {
        unidadeModel = new Unidade();
        unidadeModel.setIdUnidade(this.idUnidade);
        Cliente clienteSelecionado = (Cliente) this.spCliente.getSelectedItem();
        unidadeModel.setIdCliente(clienteSelecionado.getIdCliente());
        unidadeModel.setNomeUnidade(etUnidade.getText().toString().trim());
        unidadeModel.setNomeContato(etContato.getText().toString().trim());
        unidadeModel.setTelefone(etTelefone.getText().toString().trim());
        unidadeModel.setEndereco(etEndereco.getText().toString().trim());
        unidadeModel.setBairro(etBairro.getText().toString().trim());
        unidadeModel.setCidade(etCidade.getText().toString().trim());
        unidadeModel.setUf(etUf.getText().toString().trim());
        unidadeModel.setObsUnidade(etObsUnidade.getText().toString().trim());
        return  unidadeModel;
    }
    //==============================================================================================
//==============================================================================================
    //Função para setar o valor de spCargo nos casos de atualização
    private static void setSpCliente(Spinner spnr, long value)
    {
        ListaClienteAdapter adapter = (ListaClienteAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++)
        {
            if(adapter.getItemId(position) == value)
            {
                spnr.setSelection(position);
                return;
            }
        }
    }

}
