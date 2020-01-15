package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Visita extends Ronda implements Serializable {
    private int idVisita;
    private int idRonda;
    private String Unidade;
    private String horaChegada;
    private String kmChegada;
    private String horaSaida;
    private String obsVisita;

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    @Override
    public int getIdRonda() {
        return idRonda;
    }

    @Override
    public void setIdRonda(int idRonda) {
        this.idRonda = idRonda;
    }

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String unidade) {
        Unidade = unidade;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getKmChegada() {
        return kmChegada;
    }

    public void setKmChegada(String kmChegada) {
        this.kmChegada = kmChegada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getObsVisita() {
        return obsVisita;
    }

    public void setObsVisita(String obsVisita) {
        this.obsVisita = obsVisita;
    }

    @Override
    public String toString() {
        return getUnidade() +" - "+getHoraChegada()+" - "+getHoraSaida();
    }
}
