package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int idCliente;
    private String nomeCliente;
    private String obsCliente;


    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente()
    {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente)
    {
        this.nomeCliente = nomeCliente;
    }

    public String getObsCliente()
    {
        return obsCliente;
    }

    public void setObsCliente(String obsCliente)
    {
        this.obsCliente = obsCliente;
    }

    @Override
    public String toString()
    {
        return  nomeCliente;
    }
}
