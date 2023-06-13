package com.taina.backendjava.DTOs;

public class StationFinDTO {
    private int id;
    private String nameFin;
    private String addressFin;
    private String cityFin;
    private int capacity;

    public StationFinDTO(int id, String nameFin, String addressFin, String cityFin, int capacity) {
        this.id = id;
        this.nameFin = nameFin;
        this.addressFin = addressFin;
        this.cityFin = cityFin;
        this.capacity = capacity;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFin() {
        return nameFin;
    }

    public void setNameFin(String nameFin) {
        this.nameFin = nameFin;
    }

    public String getAddressFin() {
        return addressFin;
    }

    public void setAddressFin(String addressFin) {
        this.addressFin = addressFin;
    }

    public String getCityFin() {
        return cityFin;
    }

    public void setCityFin(String cityFin) {
        this.cityFin = cityFin;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    }

