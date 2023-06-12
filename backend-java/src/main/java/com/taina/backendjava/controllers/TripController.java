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
//This allows the frontend to connect to the backend in the cloud
//@CrossOrigin(origins = "https://frontend-react-final-z6f6oef7xq-lz.a.run.app", allowedHeaders = "*", maxAge = 10600)
@RestController
@RequestMapping("/trips")
public class TripController {
    private TripRepository trepo;

    private StationRepository srepo;

    @Autowired
    TripController(TripRepository trepo, StationRepository srepo) {

        this.trepo=trepo;
        this.srepo=srepo;
    }
    @GetMapping(value = "{id}")
    public Trip getTripById(@PathVariable("id") int id) {
        Trip t = trepo.findById(id).orElse(null);
        if (t == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return t;
    }
    @GetMapping(produces="application/json")
    public Page<Trip> getAllTripsListByPage(@RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Trip> results = trepo.getTripListByPage(pageable);
        return results;
    }

    @GetMapping(value="/sort", produces="application/json")
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

    @GetMapping(value="/search")
    public Page<Trip> searchTripsByStation (@RequestParam String word, Pageable pageable) {
        Page<Trip> results = trepo.searchTripsByStation(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trips found");
        }
        return results;
    }
    @PostMapping(value="/addTrip")
    public Trip createTrip(@RequestBody Trip t) {
        trepo.saveAndFlush(t);
        return t;
    }
    @PutMapping("/updateTrip/{id}")
    public Trip updateTrip (@PathVariable int id, @RequestBody Trip t) {
        trepo.saveAndFlush(t);
        return t;
    }

    @DeleteMapping("/deleteTrip/{id}")
    RequestInfo delete(@PathVariable int id) {
        Trip t = trepo.findById(id).orElse(null);
        if (t == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        trepo.deleteById(id);
        return new RequestInfo("Trip with ID " + id + " has been deleted");
    }
}

