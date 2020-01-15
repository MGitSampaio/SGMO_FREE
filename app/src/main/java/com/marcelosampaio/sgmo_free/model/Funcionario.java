package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;


public class Funcionario implements Serializable {
    private int idFuncionario;
    private int idCargo;
    private int idStatus;
    private int idEscala;
    private long folga1;
    private long folga2;
    private String re;
    private String nomeFuncionario;
    private String telefone;
    private String rg;
    private String cpf;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;


    private String obsFuncionario;

    public Funcionario() {
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public long getFolga1() {
        return folga1;
    }

    public void setFolga1(long folga1) {
        this.folga1 = folga1;
    }

    public long getFolga2() {
        return folga2;
    }

    public void setFolga2(long folga2) {
        this.folga2 = folga2;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getObsFuncionario() {
        return obsFuncionario;
    }

    public void setObsFuncionario(String obsFuncionario) {
        this.obsFuncionario = obsFuncionario;
    }

    @Override
    public String toString() {
        return re +" - "+nomeFuncionario;
    }
}
