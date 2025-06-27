package com.example.pe.model;

public class Nganh {
    private int idNganh;
    private String nameNganh;

    public Nganh(int id, String name) {
        this.idNganh = id;
        this.nameNganh = name;
    }

    public int getId() {
        return idNganh;
    }

    public String getName() {
        return nameNganh;
    }
}
