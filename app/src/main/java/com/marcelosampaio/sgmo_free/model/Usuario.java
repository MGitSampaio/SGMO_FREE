package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idUsuario;
    private String usuario;
    private String area;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return usuario +" - "+area;
    }
}
