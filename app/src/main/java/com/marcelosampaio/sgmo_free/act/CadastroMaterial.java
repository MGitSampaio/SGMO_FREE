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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputEditText;
import com.marcelosampaio.sgmo_free.R;
import com.marcelosampaio.sgmo_free.adp.ListaUnidadeAdapter;
import com.marcelosampaio.sgmo_free.dao.MaterialDao;
import com.marcelosampaio.sgmo_free.dao.UnidadeDao;
import com.marcelosampaio.sgmo_free.model.Material;
import com.marcelosampaio.sgmo_free.model.Unidade;

import java.util.List;

public class CadastroMaterial extends AppCompatActivity
{
    private long idMaterial = 0;
    private TextInputEditText etCodigoMaterial;
    private TextInputEditText etDesCricaoMaterial;
    private TextInputEditText etObservacaoMaterial;
    private AppCompatSpinner spUnidade;
    private MaterialDao materialDao;
    private Material materialModel = null;

    private AdView bannerMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_material);

        bannerMaterial = findViewById(R.id.bannerMaterial);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerMaterial.loadAd(adRequest);

        materialDao = new MaterialDao(this);
        UnidadeDao unidadeDao = new UnidadeDao(this);

        etCodigoMaterial = findViewById(R.id.etCodigoMaterial);
        etDesCricaoMaterial = findViewById(R.id.etDescricaoMaterial);
        etObservacaoMaterial = findViewById(R.id.etObservacaoMaterial);
        spUnidade = findViewById(R.id.spUnidadeMaterial);

        List<Unidade> listarUnidades = unidadeDao.listarUnidades();
        ListaUnidadeAdapter spnListaUnidadeAdapter = new ListaUnidadeAdapter(this, listarUnidades);
        this.spUnidade.setAdapter(spnListaUnidadeAdapter);

        Intent i = getIntent();
        if (i.hasExtra("material")) {
            materialModel = (Material) i.getSerializableExtra("material");
            idMaterial = materialModel.getIdMaterial();
            etCodigoMaterial.setText(materialModel.getCodigo());
            etDesCricaoMaterial.setText(materialModel.getDescricao());
            etObservacaoMaterial.setText(materialModel.getObsMaterial());
            setSpUnidade(spUnidade,materialModel.getIdUnidade());

        }
    }

    //==============================================================================================

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    //==============================================================================================

    public void salvar(MenuItem item)
    {
        Material materialSalvar = getDataForm();

        if (idMaterial == 0) {
            long id = materialDao.inserir(materialSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = materialDao.atualizar(materialSalvar);
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
    private Material getDataForm() {
        this.materialModel = new Material();
        this.materialModel.setIdMaterial((int) this.idMaterial);
        this.materialModel.setCodigo(this.etCodigoMaterial.getText().toString().trim());
        this.materialModel.setDescricao(this.etDesCricaoMaterial.getText().toString().trim());
        this.materialModel.setObsMaterial(this.etObservacaoMaterial.getText().toString().trim());
        Unidade unidadeSelecionada = (Unidade)this.spUnidade.getSelectedItem();
        this.materialModel.setIdUnidade((int)unidadeSelecionada.getIdUnidade());
        return materialModel;
    }
    //==============================================================================================
    //Função para setar o valor de spUnidade nos casos de atualização
    private void setSpUnidade(Spinner spnr, long value)
    {
        ListaUnidadeAdapter adapter = (ListaUnidadeAdapter)spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++)
        {
            if(adapter.getItemId(position) == value)
            {
                spnr.setSelection(position);
                return;
            }
        }
    }
    //==============================================================================================
}
