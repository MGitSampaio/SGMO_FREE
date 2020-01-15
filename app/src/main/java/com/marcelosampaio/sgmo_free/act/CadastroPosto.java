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

import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaCargoAdapter;
import com.marcelosampaio.sgmo_free.adp.ListaEscalaAdapter;
import com.marcelosampaio.sgmo_free.adp.ListaFolguistaAdapter;
import com.marcelosampaio.sgmo_free.adp.ListaFuncionarioAdapter;
import com.marcelosampaio.sgmo_free.adp.ListaUnidadeAdapter;
import com.marcelosampaio.sgmo_free.dao.CargoDao;
import com.marcelosampaio.sgmo_free.dao.EscalaDao;
import com.marcelosampaio.sgmo_free.dao.FuncionarioDao;
import com.marcelosampaio.sgmo_free.dao.PostoDao;
import com.marcelosampaio.sgmo_free.dao.UnidadeDao;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;
import com.marcelosampaio.sgmo_free.model.Cargo;
import com.marcelosampaio.sgmo_free.model.Escala;
import com.marcelosampaio.sgmo_free.model.Funcionario;
import com.marcelosampaio.sgmo_free.model.Posto;
import com.marcelosampaio.sgmo_free.model.Unidade;

import java.util.List;


public class CadastroPosto extends AppCompatActivity {

    private int idPosto;
    private AppCompatSpinner spUnidade;
    private AppCompatSpinner spCargo;
    private AppCompatSpinner spEscala;
    private AppCompatSpinner spFuncionario;
    private AppCompatSpinner spFolguista;
    private TextInputEditText etNomePosto;
    private TextInputEditText etTurno;
    private TextInputEditText etEntrada1;
    private TextInputEditText etSaida1;
    private TextInputEditText etEntrada2;
    private TextInputEditText etSaida2;
    private TextInputEditText etObs;


    private PostoDao postoDao;
    private Posto postoModel = null;



    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_posto);

        postoDao = new PostoDao(this);
        EscalaDao escalaDao = new EscalaDao(this);
        CargoDao cargoDao = new CargoDao(this);
        UnidadeDao unidadeDao = new UnidadeDao(this);
        FuncionarioDao funcionarioDao = new FuncionarioDao(this);

        spUnidade = findViewById(R.id.spUnidadePosto);
        spCargo = findViewById(R.id.spCargoPosto);
        spEscala = findViewById(R.id.spEscalaPosto);
        spFuncionario = findViewById(R.id.spFuncionarioPosto);
        spFolguista = findViewById(R.id.spFolguistaPosto);
        etNomePosto = findViewById(R.id.etNomePosto);
        etTurno = findViewById(R.id.etTurno);
        etEntrada1 = findViewById(R.id.etEntrada1);
        etSaida1 = findViewById(R.id.etSaida1);
        etEntrada2 = findViewById(R.id.etEntrada2);
        etSaida2 = findViewById(R.id.etSaida2);
        etObs = findViewById(R.id.etObservacaoPosto);

        List<Cargo> listarCargos = cargoDao.listarCargos();
        List<Escala> listarEscalas = escalaDao.listarEscalas();
        List<Unidade> listarUnidades = unidadeDao.listarUnidades();
        List<Funcionario> listarFuncionarios = funcionarioDao.listarFuncionariosEfetivos();
        List<Funcionario> listarFolguistas = funcionarioDao.listarFolguista();

        ListaCargoAdapter spnListaCargoAdapter = new ListaCargoAdapter(this, listarCargos);
        this.spCargo.setAdapter(spnListaCargoAdapter);

        ListaEscalaAdapter spnListaEscalaAdapter = new ListaEscalaAdapter(this, listarEscalas);
        this.spEscala.setAdapter(spnListaEscalaAdapter);

        ListaUnidadeAdapter spnListaUnidadeAdapter = new ListaUnidadeAdapter(this, listarUnidades);
        this.spUnidade.setAdapter(spnListaUnidadeAdapter);

        ListaFuncionarioAdapter spnListaFuncionarioAdapter = new ListaFuncionarioAdapter(this, listarFuncionarios);
        this.spFuncionario.setAdapter(spnListaFuncionarioAdapter);

        ListaFolguistaAdapter spnFolguistaAdapter = new ListaFolguistaAdapter(this, listarFolguistas);
        this.spFolguista.setAdapter(spnFolguistaAdapter);

        Intent i = getIntent();
        if (i.hasExtra("atualizarPosto")) {
            postoModel = (Posto) i.getSerializableExtra("atualizarPosto");

            idPosto = postoModel.getIdPosto();
            setSpUnidade(spUnidade, postoModel.getIdUnidade());
            setSpCargo(spCargo, postoModel.getIdCargo());
            setSpEscala(spEscala, postoModel.getIdEscala());
            setSpFuncionario(spFuncionario, postoModel.getIdFuncionario());
            setSpFolguista(spFolguista, postoModel.getIdFolguista());
            etNomePosto.setText(postoModel.getNomePosto());
            etTurno.setText(postoModel.getTurno());
            etEntrada1.setText(postoModel.getEntrada1());
            etSaida1.setText(postoModel.getSaida1());
            etEntrada2.setText(postoModel.getEntrada2());
            etSaida2.setText(postoModel.getSaida2());
            etObs.setText(postoModel.getObsPosto());
        }


    }

    //==============================================================================================

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    //==============================================================================================

    public void salvar(MenuItem item) {
        Posto postoSalvar = getDataForm();


        if (idPosto == 0) {
            long id = postoDao.inserir(postoSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo  ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = postoDao.atualizar(postoSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Alterado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Alterar", Toast.LENGTH_SHORT).show();
            }
        }

        this.finish();

    }

    //==============================================================================================
    private Posto getDataForm() {
        postoModel = new Posto();
        postoModel.setIdPosto(idPosto);

        Unidade unidadeSelecionada = (Unidade) spUnidade.getSelectedItem();
        postoModel.setIdUnidade(unidadeSelecionada.getIdUnidade());

        Cargo cargoSelecionado = (Cargo) spCargo.getSelectedItem();
        postoModel.setIdCargo(cargoSelecionado.getIdCargo());

        Escala escalaSelecionada = (Escala) spEscala.getSelectedItem();
        postoModel.setIdEscala(escalaSelecionada.getIdEscala());

        Funcionario funcionarioSelecionado = (Funcionario) spFuncionario.getSelectedItem();
        postoModel.setIdFuncionario(funcionarioSelecionado.getIdFuncionario());

        Funcionario folguistaSelecionado = (Funcionario) spFolguista.getSelectedItem();
        postoModel.setIdFolguista(folguistaSelecionado.getIdFuncionario());

        postoModel.setNomePosto(etNomePosto.getText().toString().trim());
        postoModel.setTurno(etTurno.getText().toString().trim());
        postoModel.setEntrada1(etEntrada1.getText().toString());
        postoModel.setSaida1(etSaida1.getText().toString());
        postoModel.setEntrada2(etEntrada2.getText().toString());
        postoModel.setSaida2(etSaida2.getText().toString());
        postoModel.setObsPosto(etObs.getText().toString().trim());

        return postoModel;

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
    //Função para setar o valor de spEscala nos casos de atualização
    private void setSpEscala(Spinner spnr, long value) {
        ListaEscalaAdapter adapter = (ListaEscalaAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    //==============================================================================================
    //Função para setar o valor de spUnidade nos casos de atualização
    private void setSpUnidade(Spinner spnr, long value) {
        ListaUnidadeAdapter adapter = (ListaUnidadeAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    //==============================================================================================
    //Função para setar o valor de spFuncionario nos casos de atualização
    private void setSpFuncionario(Spinner spnr, long value) {
        ListaFuncionarioAdapter adapter = (ListaFuncionarioAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    //==============================================================================================
    //Função para setar o valor de spFuncionario nos casos de atualização
    private void setSpFolguista(Spinner spnr, long value) {
        ListaFolguistaAdapter adapter = (ListaFolguistaAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}

