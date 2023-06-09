package com.taina.backendjava.controllers;

import com.taina.backendjava.DTOs.StationFinDTO;
import com.taina.backendjava.DTOs.StationSweDTO;
import com.taina.backendjava.DTOs.StationViewDTO;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.Services.StationService;
import com.taina.backendjava.Utils.RequestInfo;
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
import java.util.stream.Collectors;

import static com.taina.backendjava.Utils.ResponseEntityUtils.createResponseEntity;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge = 10600)
//This allows the frontend to connect to the backend in the cloud
//@CrossOrigin(origins = "https://frontend-react-final-z6f6oef7xq-lz.a.run.app", allowedHeaders = "*", maxAge = 10600)
@RestController
@RequestMapping("/stations")
public class StationController {
    private StationService stationService;
    private StationRepository srepo;
    private TripRepository trepo;

    @Autowired
    StationController(StationRepository srepo, TripRepository trepo, StationService stationService) {

        this.srepo = srepo;
        this.trepo = trepo;
        this.stationService = stationService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<StationViewDTO> getSingleStationById(@PathVariable("id") int id) {
        StationViewDTO station = stationService.getStationById(id);
        if (station == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return createResponseEntity(station);
    }

    @GetMapping(value = "/allInfo/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") int id) {
        Station station = srepo.findById(id).orElse(null);
        if (station == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return createResponseEntity(station);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<StationFinDTO>> searchStationByName(
            @RequestParam String word,
            @RequestParam(defaultValue = "nameFin") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<StationFinDTO> results = srepo.searchStationByName(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
        List<StationFinDTO> dtofi = results.getContent();

        Page<StationFinDTO> dtoPage = new PageImpl<>(dtofi, pageable, results.getTotalElements());

        return createResponseEntity(dtoPage);
    }

    @GetMapping(value = "/searchSwe")
    public ResponseEntity<Page<StationSweDTO>> searchStationByNameSwe(
            @RequestParam String word,
            @RequestParam(defaultValue = "nameSwe") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<StationSweDTO> results = srepo.searchStationByNameSwe(word, pageable);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stations found");
        }
            List<StationSweDTO> dtoswe = results.getContent();

            Page<StationSweDTO> dtoPage = new PageImpl<>(dtoswe, pageable, results.getTotalElements());

            return createResponseEntity(dtoPage);
        }

    @PostMapping(value = "/addStation")
    public ResponseEntity<Station> createStation(@RequestBody Station s) {
        srepo.saveAndFlush(s);
        return createResponseEntity(s);
    }

    @PutMapping("/updateStation/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable int id, @RequestBody Station s) {
        srepo.saveAndFlush(s);
        return createResponseEntity(s);
    }

    @DeleteMapping("/deleteStation/{id}")
    public ResponseEntity<RequestInfo> deleteStation(@PathVariable int id) {
        Optional<Station> optionalStation = srepo.findById(id);
        if (optionalStation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found");
        }
        srepo.deleteById(id);
        RequestInfo requestInfo = new RequestInfo("Station with ID " + id + " has been deleted");
        return createResponseEntity(requestInfo);
    }

    @GetMapping("/{Id}/averageDistanceDeparture")
    public ResponseEntity<Double> getAverageDistanceDeparture(@PathVariable int Id) {
        Double averageDistance = trepo.findAverageDistanceByDepartureStationId(Id);
        return createResponseEntity(averageDistance);
    }

    @GetMapping("/{Id}/averageDistanceReturn")
    public ResponseEntity<Double> getAverageDistanceReturn(@PathVariable int Id) {
        Double averageDistance = trepo.findAverageDistanceByReturnStationId(Id);
        return createResponseEntity(averageDistance);
    }

    @GetMapping("/{Id}/popularReturnStations")
    public ResponseEntity<List<Object[]>> getPopularReturnStations(@PathVariable int Id) {
            Pageable pageable = PageRequest.of(0, 5);
            List<Object[]> result = srepo.findPopularReturnStations(Id, pageable);

            List<Object[]> popularStations = result.stream()
                    .map(row -> new Object[]{row[0], row[1]})
                    .collect(Collectors.toList());

            return createResponseEntity(popularStations);
        }

    @GetMapping("/{Id}/popularDepartureStations")
    public ResponseEntity<List<Object[]>> getPopularDepartureStations(@PathVariable int Id) {
            Pageable pageable = PageRequest.of(0, 5);
            List<Object[]> result = srepo.findPopularDepartureStations(Id, pageable);

            List<Object[]> popularStations = result.stream()
                    .map(row -> new Object[]{row[0], row[1]})
                    .collect(Collectors.toList());

            return createResponseEntity(popularStations);
    }
    }

