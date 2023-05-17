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

    @GetMapping(value="/trips/sort", produces="application/json")
    public Page<Trip> getTripsAndSort(
            @RequestParam(defaultValue = "departureStationName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Trip> trips = trepo.getTripListByPage(pageable);

        return trips;
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
