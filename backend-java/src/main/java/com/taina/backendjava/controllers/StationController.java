package com.taina.backendjava.controllers;

import com.taina.backendjava.entities.RequestInfo;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.repositories.StationRepository;

import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {
    private StationRepository srepo;
    private TripRepository trepo;

    @Autowired
    StationController(StationRepository srepo, TripRepository trepo) {

        this.srepo = srepo;
        this.trepo = trepo;
    }

    @GetMapping(value = "/stations")
    public List<Station> getStations() {
        List<Station> stations = srepo.findAll();
        if (stations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
        return stations;
    }

    @GetMapping(value = "/stations/{id}")
    public Station getStationById(@PathVariable("id") int id) {
        Station s = srepo.findById(id).orElse(null);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
     return s;
        }








    @GetMapping(value="/stations/search")
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

    @DeleteMapping("/stations/{id}")
    RequestInfo delete(@PathVariable int id) {
        Station s = srepo.findById(id).orElse(null);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        srepo.deleteById(id);
        return new RequestInfo("Station with ID " + id + " has been deleted");
    }
}
