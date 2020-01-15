package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Unidade extends Cliente implements Serializable {
    private int idUnidade;
    private int idCliente;
    private Posto posto;
    private String nomeUnidade;
    private String nomeContato;
    private String telefone;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String obsUnidade;

    public Unidade() {
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    @Override
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getObsUnidade() {
        return obsUnidade;
    }

    public void setObsUnidade(String obsUnidade) {
        this.obsUnidade = obsUnidade;
    }

    @Override
    public String toString() {
        return getNomeCliente() +" - "+ nomeUnidade;
    }
}
