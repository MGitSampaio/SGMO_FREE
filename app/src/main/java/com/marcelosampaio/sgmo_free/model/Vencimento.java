package com.marcelosampaio.sgmo_free.model;

public class Vencimento extends Funcionario{

    private int idVencimento;
    private Funcionario funcionario;
    private long experiencia;
    private long ferias;
    private long reciclagem;
    private long aso;
    private long cracha;
    private long cnv;
    private long psicotecnico;
    private String obsVencimento;

    public int getIdVencimento() {
        return idVencimento;
    }

    public void setIdVencimento(int idVencimento) {
        this.idVencimento = idVencimento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public long getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(long experiencia) {
        this.experiencia = experiencia;
    }

    public long getFerias() {
        return ferias;
    }

    public void setFerias(long ferias) {
        this.ferias = ferias;
    }

    public long getReciclagem() {
        return reciclagem;
    }

    public void setReciclagem(long reciclagem) {
        this.reciclagem = reciclagem;
    }

    public long getAso() {
        return aso;
    }

    public void setAso(long aso) {
        this.aso = aso;
    }

    public long getCracha() {
        return cracha;
    }

    public void setCracha(long cracha) {
        this.cracha = cracha;
    }

    public long getCnv() {
        return cnv;
    }

    public void setCnv(long cnv) {
        this.cnv = cnv;
    }

    public long getPsicotecnico() {
        return psicotecnico;
    }

    public void setPsicotecnico(long psicotecnico) {
        this.psicotecnico = psicotecnico;
    }

    public String getObsVencimento() {
        return obsVencimento;
    }

    public void setObsVencimento(String obsVencimento) {
        this.obsVencimento = obsVencimento;
    }


}
