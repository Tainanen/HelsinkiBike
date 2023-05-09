package com.taina.backendjava.controllers;

import com.taina.backendjava.entities.Station;
import com.taina.backendjava.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {
    private StationRepository repo;

    @Autowired
    StationController(StationRepository repo) {

        this.repo = repo;
    }

    @GetMapping(value = "/stations")
    public List<Station> getStations() {
        return repo.findAll();

    }
}
