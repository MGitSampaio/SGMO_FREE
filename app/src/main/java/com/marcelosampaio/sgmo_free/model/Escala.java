package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Escala implements Serializable {

    private int idEscala;
    private String escala;

    public int getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    @Override
    public String toString() {
        return  escala;
    }
}
