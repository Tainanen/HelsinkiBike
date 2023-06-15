package com.taina.backendjava.controllers;

import com.taina.backendjava.Utils.RequestInfo;
import com.taina.backendjava.entities.Trip;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.taina.backendjava.Utils.ResponseEntityUtils.createResponseEntity;

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
    public ResponseEntity getTripById(@PathVariable("id") int id) {
        Trip trip = trepo.findById(id).orElse(null);
        if (trip == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return createResponseEntity(trip);
    }

    @GetMapping(produces="application/json")
    public ResponseEntity<Page<Trip>> getAllTripsListByPage(@RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Trip> results = trepo.getTripListByPage(pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
        List<Trip> dtofi = results.getContent();

        Page<Trip> dtoPage = new PageImpl<>(dtofi, pageable, results.getTotalElements());

        return createResponseEntity(dtoPage);
    }

    @GetMapping(value="/sort", produces="application/json")
    public ResponseEntity<Page<Trip>> getTripsAndSort(
            @RequestParam(defaultValue = "departureStationName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Trip> results = trepo.getTripListByPage(pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
        List<Trip> dto = results.getContent();

        Page<Trip> dtoPage = new PageImpl<>(dto, pageable, results.getTotalElements());

        return createResponseEntity(dtoPage);
    }

    @GetMapping(value="/search")
    public ResponseEntity<Page<Trip>>  searchTripsByStation (@RequestParam String word, Pageable pageable) {
        Page<Trip> results = trepo.searchTripsByStation(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trips found");
        }
        List<Trip> dto = results.getContent();

        Page<Trip> dtoPage = new PageImpl<>(dto, pageable, results.getTotalElements());

        return createResponseEntity(dtoPage);
    }

    @PostMapping(value="/addTrip")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip t) {
        trepo.saveAndFlush(t);
        return createResponseEntity(t);
    }

    @PutMapping("/updateTrip/{id}")
    public ResponseEntity<Trip> updateTrip (@PathVariable int id, @RequestBody Trip t) {
        trepo.saveAndFlush(t);
        return createResponseEntity(t);
    }

    @DeleteMapping("/deleteTrip/{id}")
    public ResponseEntity<RequestInfo> deleteTrip (@PathVariable int id) {
        Optional<Trip> optionalTrip = trepo.findById(id);
        if (optionalTrip.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found");
        }
        trepo.deleteById(id);
        RequestInfo requestInfo = new RequestInfo("Trip with ID " + id + " has been deleted");
        return createResponseEntity(requestInfo);
    }
    }


