package com.marcelosampaio.sgmo_free.model;

public class Status {

    private int idStatus;
    private String status;

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() { return  status;}
}
