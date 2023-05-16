package com.taina.backendjava.controllers;

import com.taina.backendjava.entities.RequestInfo;
import com.taina.backendjava.entities.SingleStation;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.entities.Trip;
import com.taina.backendjava.repositories.StationRepository;

import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge = 10600)
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

  /*  @GetMapping(value = "/stations")
    public List<Station> getStations() {
        List<Station> stations = srepo.findAll();
        if (stations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
        return stations;
    }
*/

    @GetMapping(value="/stations", produces="application/json")
    public Page<Station> getAllStations (@RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Station> results = srepo.getAllStations(pageable);
        return results;
    }
    @GetMapping(value = "/stations/{id}")
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
