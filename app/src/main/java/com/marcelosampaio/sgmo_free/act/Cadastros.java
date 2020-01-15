package com.marcelosampaio.sgmo_free.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.marcelosampaio.sgmo_free.R;

public class Cadastros extends AppCompatActivity {
    private AdView bannerCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastros);

        bannerCadastro = findViewById(R.id.bannerCadastros);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerCadastro.loadAd(adRequest);

        ImageView imgUsuario = findViewById(R.id.imgUsuario);
        ImageView imgCargos = findViewById(R.id.imgCargos);
        ImageView imgFuncionarios = findViewById(R.id.imgFuncionario);
        ImageView imgEscalas = findViewById(R.id.imgEscalas);
        ImageView imgClientes = findViewById(R.id.imgCliente);
        ImageView imgUnidades = findViewById(R.id.imgUnidade);
        ImageView imgPostos = findViewById(R.id.imgPosto);
        ImageView imgMaterial = findViewById(R.id.imgMaterial);
        ImageView imgMovimentacao = findViewById(R.id.imgMovimentacao);
        ImageView imgRonda = findViewById(R.id.imgRonda);
        ImageView imgTarefas = findViewById(R.id.imgTarefas);
        ImageView imgOcorrencias = findViewById(R.id.imgOcorrencias);
        ImageView imgUniformes = findViewById(R.id.imgUniforme);
        ImageView imgVencimentos = findViewById(R.id.imgVencimento);

        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, CadastroUsuario.class);
                startActivity(intent);
            }
        });

        imgCargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaCargo.class);
                startActivity(intent);
            }
        });

        imgFuncionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaFuncionario.class);
                startActivity(intent);
            }
        });

        imgEscalas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaEscala.class);
                startActivity(intent);
            }
        });

        imgClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaCliente.class);
                startActivity(intent);
            }
        });

        imgUnidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaUnidade.class);
                startActivity(intent);
            }
        });

        imgPostos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaPosto.class);
                startActivity(intent);
            }
        });

        imgMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaMaterial.class);
                startActivity(intent);
            }
        });

        imgMovimentacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaMovimentacao.class);
                startActivity(intent);
            }
        });

        imgRonda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaRonda.class);
                startActivity(intent);
            }
        });

        imgTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaTarefa.class);
                startActivity(intent);
            }
        });

        imgOcorrencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaOcorrencia.class);
                startActivity(intent);
            }
        });
        imgUniformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaUniforme.class);
                startActivity(intent);
            }
        });

        imgVencimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastros.this, ListaVencimento.class);
                startActivity(intent);
            }
        });

    }
}