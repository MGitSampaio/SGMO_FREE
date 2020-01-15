package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Cargo implements Serializable {

    private int idCargo;
    private String cargo;
    private String obsCargo;

    public int getIdCargo()
    {
        return idCargo;
    }

    public void setIdCargo(int idCargo)
    {
        this.idCargo = idCargo;
    }

    public String getCargo()
    {
        return cargo;
    }

    public void setCargo(String cargo)
    {
        this.cargo = cargo;
    }

    public String getObsCargo()
    {
        return obsCargo;
    }

    public void setObsCargo(String obsCargo)
    {
        this.obsCargo = obsCargo;
    }

    @Override
    public String toString() {
        return cargo;
    }
}