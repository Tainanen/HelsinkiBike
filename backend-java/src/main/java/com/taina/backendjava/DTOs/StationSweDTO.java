package com.taina.backendjava.DTOs;

public class StationSweDTO {
    private int id;

    private String nameSwe;
    private String addressSwe;

    private String citySwe;

    private int capacity;

    public StationSweDTO(int id, String nameSwe, String addressSwe, String citySwe, int capacity) {
        this.id = id;
        this.nameSwe = nameSwe;
        this.addressSwe = addressSwe;
        this.citySwe = citySwe;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}


