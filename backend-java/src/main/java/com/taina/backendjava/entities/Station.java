package com.taina.backendjava.entities;

import jakarta.persistence.*;

@Entity
@Table(name="stations")
public class Station {
    @Id
    private int id;
    @Column(name = "name_fin")
    private String nameFin;
    @Column(name = "name_swe")
    private String nameSwe;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "address_fin")
    private String addressFin;
    @Column(name = "address_swe")
    private String addressSwe;
    @Column(name = "city_fin")
    private String cityFin;
    @Column(name = "city_swe")
    private String citySwe;

    private String operator;
    private int capacity;
    private double y;
    private double x;


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

    public String getNameSwe() {
        return nameSwe;
    }

    public void setNameSwe(String nameSwe) {
        this.nameSwe = nameSwe;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getAddressFin() {
        return addressFin;
    }

    public void setAddressFin(String addressFin) {
        addressFin = addressFin;
    }

    public String getAddressSwe() {
        return addressSwe;
    }

    public void setAddressSwe(String addressSwe) {
        addressSwe = addressSwe;
    }

    public String getCityFin() {
        return cityFin;
    }

    public void setCityFin(String cityFin) {
        this.cityFin = cityFin;
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


    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", nameFin='" + nameFin + '\'' +
                ", nameSwe='" + nameSwe + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", addressFin='" + addressFin + '\'' +
                ", addressSwe='" + addressSwe + '\'' +
                ", cityFin='" + cityFin + '\'' +
                ", citySwe='" + citySwe + '\'' +
                ", operator='" + operator + '\'' +
                ", capacity=" + capacity +
                ", y=" + y +
                ", x=" + x +
                '}';

    }
}
