package com.marcelosampaio.sgmo_free.act;


import android.annotation.SuppressLint;
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
import com.marcelosampaio.sgmo_free.dao.CargoDao;
import com.marcelosampaio.sgmo_free.model.Cargo;

public class CadastroCargo extends AppCompatActivity {
    private long idCargo = 0;
    private TextInputEditText etCargo;
    private TextInputEditText etObsCargo;
    private Cargo cargoModel = null;
    private CargoDao cargoDao;

    private AdView bannerCargo;

    //==============================================================================================
    //Funcão que cria e seta os valores da activity
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cargo);

        bannerCargo = findViewById(R.id.bannerCargo);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerCargo.loadAd(adRequest);

        cargoDao = new CargoDao(this);
        etCargo = findViewById(R.id.etCargo);
        etObsCargo = findViewById(R.id.etObsCargo);


        Intent i = getIntent();
        if (i.hasExtra("bCargo")) {
            cargoModel = (Cargo) i.getSerializableExtra("bCargo");
            idCargo = cargoModel.getIdCargo();
            etCargo.setText(cargoModel.getCargo());
            etObsCargo.setText(cargoModel.getObsCargo());
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
        Cargo cargoSalvar = getDataForm();

        if (idCargo == 0) {
            long id = cargoDao.inserir(cargoSalvar);
            if (id > 0) {
                Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        } else {
            long id = cargoDao.atualizar(cargoSalvar);
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
    private Cargo getDataForm() {
        this.cargoModel = new Cargo();
        this.cargoModel.setIdCargo((int) this.idCargo);
        this.cargoModel.setCargo(this.etCargo.getText().toString().trim());
        this.cargoModel.setObsCargo(this.etObsCargo.getText().toString().trim());
        return cargoModel;
    }
    //==============================================================================================

}

