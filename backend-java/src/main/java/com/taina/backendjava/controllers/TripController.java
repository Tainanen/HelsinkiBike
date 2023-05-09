package com.taina.backendjava.controllers;


import com.taina.backendjava.entities.Trip;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return trepo.findById(id).get();
    }
    @GetMapping(value="/trips")
    public Page<Trip> getTrips (Pageable pageable) {
        Page<Trip> results = trepo.findAll(pageable);
        return results;


    }

}
