package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Posto extends Unidade implements Serializable {

    private int idPosto;
    private int idUnidade;
    private int idCargo;
    private int idEscala;
    private String nomePosto;
    private String turno;
    private String entrada1;
    private String saida1;
    private String entrada2;
    private String saida2;
    private int idFuncionario;
    private int idFolguista;
    private String obsPosto;

    public int getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(int idPosto) {
        this.idPosto = idPosto;
    }

    @Override
    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(String entrada1) {
        this.entrada1 = entrada1;
    }

    public String getSaida1() {
        return saida1;
    }

    public void setSaida1(String saida1) {
        this.saida1 = saida1;
    }

    public String getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(String entrada2) {
        this.entrada2 = entrada2;
    }

    public String getSaida2() {
        return saida2;
    }

    public void setSaida2(String saida2) {
        this.saida2 = saida2;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdFolguista() {
        return idFolguista;
    }

    public void setIdFolguista(int idFolguista) {
        this.idFolguista = idFolguista;
    }

    public String getObsPosto() {
        return obsPosto;
    }

    public void setObsPosto(String obsPosto) {
        this.obsPosto = obsPosto;
    }

    @Override
    public String toString()
    {
        return getNomeCliente() +" - "+
                getNomeUnidade()+" - "+
                nomePosto;
    }
}
