package com.taina.backendjava.controllers;

import com.taina.backendjava.entities.RequestInfo;
import com.taina.backendjava.entities.SingleStation;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge = 10600)
@RestController
@RequestMapping("/stations")
public class StationController {
    private StationRepository srepo;
    private TripRepository trepo;

    @Autowired
    StationController(StationRepository srepo, TripRepository trepo) {

        this.srepo = srepo;
        this.trepo = trepo;
    }

    @GetMapping(value = "{id}")
    public SingleStation getStationById(@PathVariable("id") int id) {
        Station s = srepo.findById(id).orElse(null);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        SingleStation st = new SingleStation(s.getNameFin(), s.getAddressFin(),
                trepo.departureTripCount(s.getId()),
                trepo.returnTripCount(s.getId()));
        return st;
    }

    @GetMapping(value="/search")
    public Page<Station> searchStationByName (@RequestParam String word, Pageable pageable) {
        Page<Station> results = srepo.searchStationByName(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
        return results;
    }
    @PostMapping
    public Station createStation(@RequestBody Station s) {
        srepo.saveAndFlush(s);
        return s;
    }
    @PutMapping("/{id}")
    public Station updateStation (@PathVariable int id, @RequestBody Station s) {
        srepo.saveAndFlush(s);
        return s;
    }

    @DeleteMapping("{id}")
    RequestInfo delete(@PathVariable int id) {
        Station s = srepo.findById(id).orElse(null);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        srepo.deleteById(id);
        return new RequestInfo("Station with ID " + id + " has been deleted");
    }

    @GetMapping("/{Id}/averageDistanceDeparture")
    public ResponseEntity<Double> getAverageDistanceDeparture(@PathVariable int Id) {
        Double averageDistance = trepo.findAverageDistanceByDepartureStationId(Id);
        if (averageDistance != null) {
            return ResponseEntity.ok(averageDistance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{Id}/averageDistanceReturn")
    public ResponseEntity<Double> getAverageDistanceReturn(@PathVariable int Id) {
        Double averageDistance = trepo.findAverageDistanceByReturnStationId(Id);
        if (averageDistance != null) {
            return ResponseEntity.ok(averageDistance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
