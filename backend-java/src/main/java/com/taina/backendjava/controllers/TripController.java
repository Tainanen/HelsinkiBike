package com.taina.backendjava.controllers;


import com.taina.backendjava.entities.Trip;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TripController {
    private TripRepository trepo;

    private StationRepository srepo;

    @Autowired
    TripController(TripRepository trepo, StationRepository srepo) {

        this.trepo=trepo;
        this.srepo=srepo;
    }
    @GetMapping(value = "/trips/{id}")
    public Trip getTripById(@PathVariable("id") int id) {
        Trip t = trepo.findById(id).orElse(null);
        if (t == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return t;
    }
    @GetMapping(value="/trips", produces="application/json")
    public Page<Trip> getAllTripsListByPage(@RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Trip> results = trepo.getTripListByPage(pageable);
        return results;
    }

    @GetMapping("/trips/sort")
    public Page<Trip> getTripsAndSort(
            @RequestParam(defaultValue = "departureStationName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "20") int pageSize) {

        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Trip> trips = trepo.findAll(pageable);

        return trips;
    }

    /*  @GetMapping(value="/trips/sort")
    public Page<Trip> getTripsOrderByDepStation (Pageable pageable) {
        Page<Trip> results = trepo.getTripOrderByReturnStation(pageable);
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
    }*/
    @GetMapping(value="/trips/search")
    public Page<Trip> searchTripsByStation (@RequestParam String word, Pageable pageable) {
        Page<Trip> results = trepo.searchTripsByStation(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trips found");
        }
        return results;
    }












    }
