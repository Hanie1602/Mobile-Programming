package com.example.se1735_duonglnt_se171916.model;

public class Sinhvien {
    private int id;
    private String name;
    private String date;
    private String gender;
    private String address;
    private int idNganh;
    private String phone;

    // Constructor
    public Sinhvien(int id, String name, String date, String gender, String address, int idNganh, String phone) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.idNganh = idNganh;
        this.phone = phone;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
