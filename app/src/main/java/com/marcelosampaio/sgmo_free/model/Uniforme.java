package com.marcelosampaio.sgmo_free.model;

public class Uniforme extends Funcionario {

    private int idUniforme;
    private Funcionario funcionario;
    private String nrCobertura;
    private String qtdCobertura;
    private long dtCobertura;
    private String nrBlusa;
    private String qtdBlusa;
    private long dtBlusa;
    private String nrCamisa;
    private String qtdCamisa;
    private long dtCamisa;
    private String nrCalca;
    private String qtdCalca;
    private long dtCalca;
    private String nrCalcado;
    private String qtdCalcado;
    private long dtCalcado;
    private String obsUniforme;

    public int getIdUniforme() {
        return idUniforme;
    }

    public void setIdUniforme(int idUniforme) {
        this.idUniforme = idUniforme;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNrCobertura() {
        return nrCobertura;
    }

    public void setNrCobertura(String nrCobertura) {
        this.nrCobertura = nrCobertura;
    }

    public String getQtdCobertura() {
        return qtdCobertura;
    }

    public void setQtdCobertura(String qtdCobertura) {
        this.qtdCobertura = qtdCobertura;
    }

    public long getDtCobertura() {
        return dtCobertura;
    }

    public void setDtCobertura(long dtCobertura) {
        this.dtCobertura = dtCobertura;
    }

    public String getNrBlusa() {
        return nrBlusa;
    }

    public void setNrBlusa(String nrBlusa) {
        this.nrBlusa = nrBlusa;
    }

    public String getQtdBlusa() {
        return qtdBlusa;
    }

    public void setQtdBlusa(String qtdBlusa) {
        this.qtdBlusa = qtdBlusa;
    }

    public long getDtBlusa() {
        return dtBlusa;
    }

    public void setDtBlusa(long dtBlusa) {
        this.dtBlusa = dtBlusa;
    }

    public String getNrCamisa() {
        return nrCamisa;
    }

    public void setNrCamisa(String nrCamisa) {
        this.nrCamisa = nrCamisa;
    }

    public String getQtdCamisa() {
        return qtdCamisa;
    }

    public void setQtdCamisa(String qtdCamisa) {
        this.qtdCamisa = qtdCamisa;
    }

    public long getDtCamisa() {
        return dtCamisa;
    }

    public void setDtCamisa(long dtCamisa) {
        this.dtCamisa = dtCamisa;
    }

    public String getNrCalca() {
        return nrCalca;
    }

    public void setNrCalca(String nrCalca) {
        this.nrCalca = nrCalca;
    }

    public String getQtdCalca() {
        return qtdCalca;
    }

    public void setQtdCalca(String qtdCalca) {
        this.qtdCalca = qtdCalca;
    }

    public long getDtCalca() {
        return dtCalca;
    }

    public void setDtCalca(long dtCalca) {
        this.dtCalca = dtCalca;
    }

    public String getNrCalcado() {
        return nrCalcado;
    }

    public void setNrCalcado(String nrCalcado) {
        this.nrCalcado = nrCalcado;
    }

    public String getQtdCalcado() {
        return qtdCalcado;
    }

    public void setQtdCalcado(String qtdCalcado) {
        this.qtdCalcado = qtdCalcado;
    }

    public long getDtCalcado() {
        return dtCalcado;
    }

    public void setDtCalcado(long dtCalcado) {
        this.dtCalcado = dtCalcado;
    }

    public String getObsUniforme() {
        return obsUniforme;
    }

    public void setObsUniforme(String obsUniforme) {
        this.obsUniforme = obsUniforme;
    }

    public String toString() {
        return  funcionario.getRe() +" - "+funcionario.getNomeFuncionario();
    }
}
