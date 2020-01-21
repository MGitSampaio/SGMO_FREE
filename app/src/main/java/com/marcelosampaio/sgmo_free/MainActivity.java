package com.marcelosampaio.sgmo_free;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marcelosampaio.sgmo_free.act.Cadastros;
import com.marcelosampaio.sgmo_free.act.ListaCliente;
import com.marcelosampaio.sgmo_free.act.ListaFuncionario;
import com.marcelosampaio.sgmo_free.act.ListaMaterial;
import com.marcelosampaio.sgmo_free.act.ListaPosto;
import com.marcelosampaio.sgmo_free.act.ListaTarefa;
import com.marcelosampaio.sgmo_free.act.ListaUnidade;
import com.marcelosampaio.sgmo_free.act.ListaUniforme;
import com.marcelosampaio.sgmo_free.act.ListaVencimento;
import com.marcelosampaio.sgmo_free.act.Relatorios;
import com.marcelosampaio.sgmo_free.act.Sobre;
import com.marcelosampaio.sgmo_free.dao.CargoDao;
import com.marcelosampaio.sgmo_free.dao.ClienteDao;
import com.marcelosampaio.sgmo_free.dao.DatasDao;
import com.marcelosampaio.sgmo_free.dao.EscalaDao;
import com.marcelosampaio.sgmo_free.dao.FuncionarioDao;
import com.marcelosampaio.sgmo_free.dao.MaterialDao;
import com.marcelosampaio.sgmo_free.dao.PostoDao;
import com.marcelosampaio.sgmo_free.dao.StatusDao;
import com.marcelosampaio.sgmo_free.dao.TarefaDao;
import com.marcelosampaio.sgmo_free.dao.UnidadeDao;
import com.marcelosampaio.sgmo_free.dao.UniformeDao;
import com.marcelosampaio.sgmo_free.dao.VencimentoDao;
import com.marcelosampaio.sgmo_free.dataHelper.ConexaoSQLite;
import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CargoDao cargoDao;
    private ClienteDao clienteDao;
    private DatasDao datasDao;
    private DataHelper dataHelper = new DataHelper();
    private static Date hoje = new Date();
    private EditText etDtSo;
    private EditText etDtApp;
    private EscalaDao escalaDao;
    private StatusDao statusDao;
    private FuncionarioDao funcionarioDao;
    private VencimentoDao vencimentoDao;
    private UniformeDao uniformeDao;
    private MaterialDao materialDao;
    private PostoDao postoDao;
    private TarefaDao tarefaDao;
    private UnidadeDao unidadeDao;

    private final String[] appPermissoes = {
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET

    };

    private ImageView imgClienteHome;
    private TextView qtdeCliente;
    private ImageView imgUnidadeHome;
    private TextView qtdeUnidade;
    private ImageView imgPostoHome;
    private TextView qtdePosto;
    private TextView qtdePV;
    private ImageView imgFuncionarioHome;
    private TextView qtdeFuncionario;
    private TextView qtdeFuncionarioNa;
    private ImageView imgMaterialHome;
    private TextView qtdeMaterial;
    private TextView qtdeMaterialNa;
    private ImageView imgTarefasHome;
    private TextView qtdeTarefas;
    private TextView qtdeTarefasNe;
    private ImageView imgVencimentoHome;
    private TextView qtdeVencimentoAv;
    private TextView qtdeVencimentoV;
    private ImageView imgUniHome;
    private TextView qtdeUniAv;
    private TextView qtdeUniV;

    private AdView bannermain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        bannermain = findViewById(R.id.bannerMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannermain.loadAd(adRequest);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        verificarPermissoes();
        ConexaoSQLite.getInstancia(this);

        cargoDao = new CargoDao(this);
        clienteDao = new ClienteDao(this);
        datasDao = new DatasDao(MainActivity.this);
        escalaDao = new EscalaDao(MainActivity.this);
        funcionarioDao = new FuncionarioDao(this);
        materialDao = new MaterialDao(this);
        postoDao = new PostoDao(this);
        statusDao = new StatusDao(this);
        tarefaDao = new TarefaDao(this);
        unidadeDao = new UnidadeDao(this);
        uniformeDao = new UniformeDao(this);
        vencimentoDao = new VencimentoDao(this);

        //chama o método contarEscalas em EscalaDao se não houver escalas chama o método inserir

        escalaDao.contarEscalas();
        statusDao.contarStatus();
        cargoDao.contarCargos();
        datasDao.contarDatas();

        // Mapeia as views do xml
        imgClienteHome = findViewById(R.id.imgClienteHome);
        qtdeCliente = findViewById(R.id.tvQtdeClientes);
        imgUnidadeHome = findViewById(R.id.imgUnidadeHome);
        qtdeUnidade = findViewById(R.id.tvQtdeUnidades);
        imgPostoHome = findViewById(R.id.imgPostosHome);
        qtdePosto = findViewById(R.id.tvQtdePostos);
        qtdePV = findViewById(R.id.tvQtdePV);
        imgFuncionarioHome = findViewById(R.id.imgFuncionariosHome);
        qtdeFuncionario = findViewById(R.id.tvQtdeFuncionarios);
        qtdeFuncionarioNa = findViewById(R.id.tvQtdeFuncionariosNa);
        imgMaterialHome = findViewById(R.id.imgMaterialHome);
        qtdeMaterial = findViewById(R.id.tvQtdeMaterial);
        qtdeMaterialNa = findViewById(R.id.tvQtdeMaterialNa);
        imgTarefasHome = findViewById(R.id.imgTarefasHome);
        qtdeTarefas = findViewById(R.id.tvQtdeTarefas);
        qtdeTarefasNe = findViewById(R.id.tvQtdeTarefasNe);
        imgVencimentoHome = findViewById(R.id.imgVencimentoHome);
        qtdeVencimentoAv = findViewById(R.id.tvQtdeVencimentoAv);
        qtdeVencimentoV = findViewById(R.id.tvQtdeVencimentoV);
        imgUniHome = findViewById(R.id.imgUniHome);
        qtdeUniAv = findViewById(R.id.tvQtdeUniAv);
        qtdeUniV = findViewById(R.id.tvQtdeUniV);
        etDtSo = findViewById(R.id.etDtSo);
        etDtApp = findViewById(R.id.etDtApp);

        setaContadores();

        etDtSo.setText(dataHelper.convertLongEmStringData(hoje.getTime()));
        etDtApp.setText(dataHelper.convertLongEmStringData(datasDao.dataApp()));

        imgClienteHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaCliente.class);
                startActivity(intent);
            }
        });

        imgUnidadeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaUnidade.class);
                startActivity(intent);
            }
        });

        imgPostoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaPosto.class);
                startActivity(intent);
            }
        });


        imgFuncionarioHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaFuncionario.class);
                startActivity(intent);
            }
        });

        imgMaterialHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaMaterial.class);
                startActivity(intent);
            }
        });

        imgTarefasHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaTarefa.class);
                startActivity(intent);
            }
        });

        imgVencimentoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaVencimento.class);
                startActivity(intent);
            }
        });


        imgUniHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaUniforme.class);
                startActivity(intent);
            }
        });

    }

    //==============================================================================================
    private void setaContadores() {

        // seta os valores dos textView contadores
        qtdeFuncionario.setText(String.valueOf(funcionarioDao.contarFuncionarios()));
        qtdeFuncionarioNa.setText(String.valueOf(funcionarioDao.contarFuncionariosNa()));
        qtdeCliente.setText(String.valueOf(clienteDao.contarClientes()));
        qtdeUnidade.setText(String.valueOf(unidadeDao.contarUnidades()));
        qtdePosto.setText(String.valueOf(postoDao.contarPostos()));
        qtdePV.setText(String.valueOf(postoDao.contarPV()));
        qtdeMaterial.setText(String.valueOf(materialDao.contarMaterial()));
        qtdeMaterialNa.setText(String.valueOf(materialDao.contarMaterialNa()));
        qtdeTarefas.setText(String.valueOf(tarefaDao.contarTarefas()));
        qtdeTarefasNe.setText(String.valueOf(tarefaDao.contarTarefasNe()));
        qtdeVencimentoAv.setText(String.valueOf(vencimentoDao.contaVencimentosAv()));
        qtdeVencimentoV.setText(String.valueOf(vencimentoDao.contaVencimentosV()));
        qtdeUniAv.setText(String.valueOf(uniformeDao.contaUniformesAv()));
        qtdeUniV.setText(String.valueOf(uniformeDao.contaUniformesV()));
    }

    //==============================================================================================
    private void verificarPermissoes() {
        List<String> permissoesRequeridas = new ArrayList<>();

        for (String permissao : appPermissoes) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, permissao) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissoesRequeridas.add(permissao);
            }
        }

        if (!permissoesRequeridas.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this, permissoesRequeridas.toArray(new String[
                    permissoesRequeridas.size()]), 1);
        }

    }

    //==============================================================================================

    // Função que cria o menu de cadastro
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_main, menu);
        return true;
    }

    //==============================================================================================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Atualizar) {
            atualizaEscalas();
        }

        if (id == R.id.Cadastros) {
            Intent intent = new Intent(this, Cadastros.class);
            startActivity(intent);
        }

        if (id == R.id.Relatorios) {
            Intent intent = new Intent(this, Relatorios.class);
            startActivity(intent);
        }

        if (id == R.id.Sobre) {
            Intent intent = new Intent(this, Sobre.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //==============================================================================================
    private void atualizaEscalas() {

        Date App = dataHelper.converteStringEmData(etDtApp.getText().toString());
        Date Sys= dataHelper.converteStringEmData(etDtSo.getText().toString());




        if (App.compareTo(Sys) == 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("ERRO")
                    .setMessage("Impossível Consolidar")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {

            escalaDao.verificaEscala();

            long dia = dataHelper.converteStringDataEmLong(etDtApp.getText().toString());
            datasDao.inserir(dia + 86400000L);

            Toast.makeText(this, "Escalas Atualizadas", Toast.LENGTH_LONG).show();
        }



        onResume();
    }
    //==============================================================================================

    @Override
    protected void onResume() {
        super.onResume();
        etDtApp.setText(dataHelper.convertLongEmStringData(datasDao.dataApp()));
        setaContadores();
    }

    //==============================================================================================
}



