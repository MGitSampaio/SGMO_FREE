package com.marcelosampaio.sgmo_free.model;

import java.io.Serializable;

public class Material implements Serializable {

    private int idMaterial;
    private String codigo;
    private String descricao;
    private String obsMaterial;
    private int idUnidade;

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getObsMaterial() {
        return obsMaterial;
    }

    public void setObsMaterial(String obsMaterial) {
        this.obsMaterial = obsMaterial;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    @Override
    public String toString()
    {
        return codigo+" - "+ descricao;
    }
}
