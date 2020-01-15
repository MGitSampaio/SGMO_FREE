package com.marcelosampaio.sgmo_free.model;

import com.marcelosampaio.sgmo_free.dataHelper.DataHelper;

import java.io.Serializable;


public class Ocorrencia implements Serializable {
    private int idOcorrencia;
    private String cliente;
    private String unidade;
    private String bo;
    private String tipoOcorrencia;
    private long data;
    private String hora;
    private String relato;

    public int getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(int idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
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

    public String getBo() {
        return bo;
    }

    public void setBo(String bo) {
        this.bo = bo;
    }

    public String getTipoOcorrencia()
    {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia)
    {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRelato()
    {
        return relato;
    }

    public void setRelato(String relato)
    {
        this.relato = relato;
    }

    DataHelper dataHelper = new DataHelper();
    @Override
    public String toString()
    {
        return  cliente +" - "+unidade+" - "+ dataHelper.convertLongEmStringData(data);
    }
}
