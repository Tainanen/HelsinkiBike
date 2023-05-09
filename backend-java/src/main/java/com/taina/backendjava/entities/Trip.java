package com.taina.backendjava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(name = "Return_station_name")
    private String returnStationName;
    private int Distance_m;
    private int Duration_s;


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

    public int getDistance_m() {
        return Distance_m;
    }

    public void setDistance_m(int distance_m) {
        Distance_m = distance_m;
    }

    public int getDuration_s() {
        return Duration_s;
    }

    public void setDuration_s(int duration_s) {
        Duration_s = duration_s;
    }

}

