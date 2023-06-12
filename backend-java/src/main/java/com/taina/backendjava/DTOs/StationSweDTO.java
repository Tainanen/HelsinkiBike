package com.taina.backendjava.DTOs;

public class StationSweDTO {

    private String nameSwe;
    private String addressSwe;

    private String citySwe;

    private String operator;
    private int capacity;

    private double y;
    private double x;

    public String getNameSwe() {
        return nameSwe;
    }

    public void setNameSwe(String nameSwe) {
        this.nameSwe = nameSwe;
    }

    public String getAddressSwe() {
        return addressSwe;
    }

    public void setAddressSwe(String addressSwe) {
        this.addressSwe = addressSwe;
    }

    public String getCitySwe() {
        return citySwe;
    }

    public void setCitySwe(String citySwe) {
        this.citySwe = citySwe;
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
