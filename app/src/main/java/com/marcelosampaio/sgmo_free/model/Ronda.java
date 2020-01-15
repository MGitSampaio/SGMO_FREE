package com.marcelosampaio.sgmo_free.model;


import java.io.Serializable;

public class Ronda implements Serializable {

    private int idRonda;
    private long dataInicial;
    private String horaInicial;
    private String kmInicial;
    private long dataAbastecimento;
    private String horaAbastecimento;
    private String vtr;
    private String kmAbastecimento;
    private String valorAbastecimento;
    private String notaAbastecimento;
    private long dataFinal;
    private String horaFinal;
    private String kmFinal;
    private String obsRonda;

    public int getIdRonda() {
        return idRonda;
    }

    public void setIdRonda(int idRonda) {
        this.idRonda = idRonda;
    }

    public long getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(long dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(String kmInicial) {
        this.kmInicial = kmInicial;
    }

    public long getDataAbastecimento() {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(long dataAbastecimento) {
        this.dataAbastecimento = dataAbastecimento;
    }

    public String getHoraAbastecimento() {
        return horaAbastecimento;
    }

    public void setHoraAbastecimento(String horaAbastecimento) {
        this.horaAbastecimento = horaAbastecimento;
    }

    public String getVtr() {
        return vtr;
    }

    public void setVtr(String vtr) {
        this.vtr = vtr;
    }

    public String getKmAbastecimento() {
        return kmAbastecimento;
    }

    public void setKmAbastecimento(String kmAbastecimento) {
        this.kmAbastecimento = kmAbastecimento;
    }

    public String getValorAbastecimento() {
        return valorAbastecimento;
    }

    public void setValorAbastecimento(String valorAbastecimento) {
        this.valorAbastecimento = valorAbastecimento;
    }

    public String getNotaAbastecimento() {
        return notaAbastecimento;
    }

    public void setNotaAbastecimento(String notaAbastecimento) {
        this.notaAbastecimento = notaAbastecimento;
    }

    public long getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(long dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(String kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getObsRonda() {
        return obsRonda;
    }

    public void setObsRonda(String obsRonda) {
        this.obsRonda = obsRonda;
    }
}

