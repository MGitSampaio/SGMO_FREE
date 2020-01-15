package com.marcelosampaio.sgmo_free.act;

import android.Manifest.permission;
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
import com.marcelosampaio.sgmo_free.adp.ListaCargoAdapter;
import com.marcelosampaio.sgmo_free.adp.ListaEscalaAdapter;
import com.marcelosampaio.sgmo_free.adp.ListaStatusAdapter;
import com.marcelosampaio.sgmo_free.dao.CargoDao;
import com.marcelosampaio.sgmo_free.dao.EscalaDao;
import com.marcelosampaio.sgmo_free.dao.FuncionarioDao;
import com.marcelosampaio.sgmo_free.dao.StatusDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Cargo;
import com.marcelosampaio.sgmo_free.model.Escala;
import com.marcelosampaio.sgmo_free.model.Funcionario;
import com.marcelosampaio.sgmo_free.model.Status;

import java.util.List;

public class CadastroFuncionario extends AppCompatActivity {

    private int idFuncionario;
    private AppCompatSpinner spCargo;
    private AppCompatSpinner spStatus;
    private TextInputEditText etRe;
    private TextInputEditText etNomeFuncionario;
    private TextInputEditText etTelefoneFuncionario;
    private AppCompatSpinner spEscala;
    private TextInputEditText etFolga1;
    private TextInputEditText etFolga2;
    private TextInputEditText etRg;
    private TextInputEditText etCpf;
    private TextInputEditText etEnderecoFuncionario;
    private TextInputEditText etBairroFuncionario;
    private TextInputEditText etCidadeFuncionario;
    private TextInputEditText etUfFuncionario;
    private TextInputEditText etObsFuncionario;

    private FuncionarioDao funcionarioDao;
    private Funcionario funcionarioModel = null;
    DataHelper dataHelper = new DataHelper();


    //==============================================================================================
    //Funcão que cria e seta os valores da activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        funcionarioDao = new FuncionarioDao(this);
        CargoDao cargoDao = new CargoDao(this);
        StatusDao statusDao = new StatusDao(this);
        EscalaDao escalaDao = new EscalaDao(this);
        List<Status> listarStatus = statusDao.listarStatus();
        List<Cargo> listarCargos = cargoDao.listarCargos();
        List<Escala> listarEscalas = escalaDao.listarEscalas();

        spCargo = findViewById(R.id.spCargo);
        spStatus = findViewById(R.id.spStatus);
        etRe = findViewById(R.id.etRe);
        etNomeFuncionario = findViewById(R.id.etNomeFuncionario);
        etTelefoneFuncionario = findViewById(R.id.etTelefoneFuncionario);
        ImageButton btnCall = findViewById(R.id.btnCallFunc);
        spEscala = findViewById(R.id.spEscala);
        etFolga1 = findViewById(R.id.etFolga1);
        etFolga2 = findViewById(R.id.etFolga2);
        etRg = findViewById(R.id.etRg);
        etCpf = findViewById(R.id.etCpf);
        etEnderecoFuncionario = findViewById(R.id.etEnderecoFuncionario);
        etBairroFuncionario = findViewById(R.id.etBairroFuncionario);
        etCidadeFuncionario = findViewById(R.id.etCidadeFuncionario);
        etUfFuncionario = findViewById(R.id.etUfFuncionario);
        etObsFuncionario = findViewById(R.id.etObsFuncionario);

        ListaCargoAdapter spnListaCargoAdapter = new ListaCargoAdapter(this, listarCargos);
        this.spCargo.setAdapter(spnListaCargoAdapter);

        ListaStatusAdapter spnListaStatusAdapter = new ListaStatusAdapter(this, listarStatus);
        this.spStatus.setAdapter(spnListaStatusAdapter);

        ListaEscalaAdapter spnListaEscalaAdapter = new ListaEscalaAdapter(this, listarEscalas);
        this.spEscala.setAdapter(spnListaEscalaAdapter);


        Intent i = getIntent();
        if (i.hasExtra("bFuncionario")) {

            funcionarioModel = (Funcionario) i.getSerializableExtra("bFuncionario");


            idFuncionario = funcionarioModel.getIdFuncionario();
            setSpCargo(spCargo, funcionarioModel.getIdCargo());
            setSpStatus(spStatus,funcionarioModel.getIdStatus());
            setSpEscala(spEscala, funcionarioModel.getIdEscala());
            etRe.setText(funcionarioModel.getRe());
            etNomeFuncionario.setText(funcionarioModel.getNomeFuncionario());
            etTelefoneFuncionario.setText(funcionarioModel.getTelefone());
            etFolga1.setText(dataHelper.convertLongEmStringData(funcionarioModel.getFolga1()));
            etFolga2.setText(dataHelper.convertLongEmStringData(funcionarioModel.getFolga2()));
            etRg.setText(funcionarioModel.getRg());
            etCpf.setText(funcionarioModel.getCpf());
            etEnderecoFuncionario.setText(funcionarioModel.getEndereco());
            etBairroFuncionario.setText(funcionarioModel.getBairro());
            etCidadeFuncionario.setText(funcionarioModel.getCidade());
            etUfFuncionario.setText(funcionarioModel.getUf());
            etObsFuncionario.setText(funcionarioModel.getObsFuncionario());
            //======================================================================================
        }
        btnCall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String numero = etTelefoneFuncionario.getText().toString();
                Uri uri = Uri.parse("tel:" + numero);
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (checkSelfPermission(permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CadastroFuncionario.this,
                            new String[]{permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intent);
            }
        });
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
        Funcionario funcionarioSalvar = getDataForm();

        if (idFuncionario == 0) {
            long id = funcionarioDao.inserir(funcionarioSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = funcionarioDao.atualizar(funcionarioSalvar);
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
    private Funcionario getDataForm() {
        funcionarioModel = new Funcionario();
        funcionarioModel.setIdFuncionario(idFuncionario);
        Cargo cargoSelecionado = (Cargo) this.spCargo.getSelectedItem();
        funcionarioModel.setIdCargo(cargoSelecionado.getIdCargo());
        Status statusSelecionado = (Status) this.spStatus.getSelectedItem();
        funcionarioModel.setIdStatus(statusSelecionado.getIdStatus());
        Escala escalaSelecionada = (Escala) this.spEscala.getSelectedItem();
        funcionarioModel.setIdEscala(escalaSelecionada.getIdEscala());
        funcionarioModel.setFolga1(dataHelper.converteStringDataEmLong(etFolga1.getText().toString().trim()));
        funcionarioModel.setFolga2(dataHelper.converteStringDataEmLong(etFolga2.getText().toString().trim()));
        funcionarioModel.setRe(etRe.getText().toString().trim());
        funcionarioModel.setNomeFuncionario(etNomeFuncionario.getText().toString().trim());
        funcionarioModel.setTelefone(etTelefoneFuncionario.getText().toString().trim());
        funcionarioModel.setRg(etRg.getText().toString().trim());
        funcionarioModel.setCpf(etCpf.getText().toString().trim());
        funcionarioModel.setEndereco(etEnderecoFuncionario.getText().toString().trim());
        funcionarioModel.setBairro(etBairroFuncionario.getText().toString().trim());
        funcionarioModel.setCidade(etCidadeFuncionario.getText().toString().trim());
        funcionarioModel.setUf(etUfFuncionario.getText().toString().trim());
        funcionarioModel.setObsFuncionario(etObsFuncionario.getText().toString().trim());

        return funcionarioModel;
    }

    //==============================================================================================
    //Função para setar o valor de spCargo nos casos de atualização
    private static void setSpCargo(Spinner spnr, long value) {
        ListaCargoAdapter adapter = (ListaCargoAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }
    //==============================================================================================

    //Função para setar o valor de spStatus nos casos de atualização
    private static void setSpStatus(Spinner spnr, long value) {
        ListaStatusAdapter adapter = (ListaStatusAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }
    //==============================================================================================

    //Função para setar o valor de spEscala nos casos de atualização
    private static void setSpEscala(Spinner spnr, long value) {
        ListaEscalaAdapter adapter = (ListaEscalaAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }
    //==============================================================================================

}

