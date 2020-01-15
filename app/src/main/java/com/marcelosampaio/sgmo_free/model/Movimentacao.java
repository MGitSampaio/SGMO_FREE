package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;


public class Movimentacao implements Serializable {

    private int idMovimentacao;
    private long dia;
    private String cliente;
    private String unidade;
    private String reAusente;
    private String nomeAusente;
    private String motivo;
    private String reCobertura;
    private String nomeCobertura;
    private String obsMovimentacao;


    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public long getDia() { return dia;}

    public void setDia(long dia) {
        this.dia = dia;
    }

    public String getReAusente() {
        return reAusente;
    }

    public void setReAusente(String reAusente) {
        this.reAusente = reAusente;
    }

    public String getNomeAusente() {
        return nomeAusente;
    }

    public void setNomeAusente(String nomeAusente) {
        this.nomeAusente = nomeAusente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReCobertura() {
        return reCobertura;
    }

    public void setReCobertura(String reCobertura) {
        this.reCobertura = reCobertura;
    }

    public String getNomeCobertura() {
        return nomeCobertura;
    }

    public void setNomeCobertura(String nomeCobertura) {
        this.nomeCobertura = nomeCobertura;
    }

    public String getObsMovimentacao() { return obsMovimentacao;}

    public void setObsMovimentacao(String obsMovimentacao) {this.obsMovimentacao = obsMovimentacao;}
}
