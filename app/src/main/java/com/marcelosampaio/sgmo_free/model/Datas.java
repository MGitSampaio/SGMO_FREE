package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;
public class Datas implements Serializable{

    private long dia;

    public long getDia() {
        return dia;
    }

    public void setDia(long dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return ""+dia;
    }
}
