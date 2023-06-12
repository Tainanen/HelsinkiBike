package com.taina.backendjava.DTOs;

public class StationFinDTO {
    private String nameFin;
    private String addressFin;
    private String cityFin;
    private String operator;
    private int capacity;
    private double y;
    private double x;


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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;

    }
}
