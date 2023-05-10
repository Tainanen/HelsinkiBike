package com.taina.backendjava.controllers;


import com.taina.backendjava.entities.Trip;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TripController {
    private TripRepository trepo;

    private StationRepository srepo;

    @Autowired
    TripController(TripRepository trepo) {
        this.trepo=trepo;
    }
    @GetMapping(value = "/trips/{id}")
    public Trip getTripById(@PathVariable("id") int id) {
        Trip t = trepo.findById(id).orElse(null);
        if (t == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return t;
    }
    @GetMapping(value="/trips")
    public Page<Trip> getTrips (Pageable pageable) {
        Page<Trip> results = trepo.getTripListByPage(pageable);
        return results;

    }
    @GetMapping(value="/trips/de")
    public Page<Trip> getTripsOrderByDepStation (Pageable pageable) {
        Page<Trip> results = trepo.getTripOrderByDepStation(pageable);
        return results;

    }
    @GetMapping(value="/trips/re")
    public Page<Trip> getTripsOrderByReturnStation (Pageable pageable) {
        Page<Trip> results = trepo.getTripOrderByReturnStation(pageable);
        return results;
    }
    @GetMapping(value="/trips/di")
    public Page<Trip> getTripsOrderByDistance (Pageable pageable) {
        Page<Trip> results = trepo.getTripOrderByDistance(pageable);
        return results;
    }
    @GetMapping(value="/trips/du")
    public Page<Trip> getTripsOrderByDuration (Pageable pageable) {
        Page<Trip> results = trepo.getTripOrderByDuration(pageable);
        return results;
    }
    @GetMapping(value="/trips/search")
    public Page<Trip> searchTripsByStation (@RequestParam String word, Pageable pageable) {
        Page<Trip> results = trepo.searchTripsByStation(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trips found");
        }
        return results;
    }












    }
