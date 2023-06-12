package com.taina.backendjava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="trips")
public class Trip {
    @Id
    private int id;
    @Column(name = "Departure")
    private LocalDateTime departureTime;
    @Column(name = "Return")
    private LocalDateTime returnTime;
    @Column(name = "Departure_station_id")
    private int departureStationId;
    @Column(name = "Departure_station_name")
    private String departureStationName;
    @Column(name = "Return_station_id")
    private int returnStationId;

    @Column(name = "Return_station_name")
    private String returnStationName;


    @Column(name="Distance_m")
    private int distanceInMetres;
    @Column(name="Duration_s")
    private int durationInSeconds;


    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public String getReturnStationName() {
        return returnStationName;
    }

    public void setReturnStationName(String returnStationName) {
        this.returnStationName = returnStationName;
    }

    public int getDistanceInMetres() {
        return distanceInMetres;
    }

    public void setDistanceInMetres(int distanceInMetres) {
        this.distanceInMetres = distanceInMetres;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getReturnStationId() {
        return returnStationId;
    }

    public void setReturnStationId(int returnStationId) {
        this.returnStationId = returnStationId;
    }

    public String getFormattedDepartureTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.departureTime.format(formatter);

    }


}

