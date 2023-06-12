package com.taina.backendjava.Services;

import com.taina.backendjava.DTOs.StationFinDTO;
import com.taina.backendjava.DTOs.StationViewDTO;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StationService {
    private StationRepository srepo;
    private TripRepository trepo;

    @Autowired
    public StationService(StationRepository srepo, TripRepository trepo) {
        this.srepo = srepo;
        this.trepo = trepo;
    }

    public StationViewDTO getStationById(int id) {
        Station s = srepo.findById(id).orElse(null);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new StationViewDTO(
                s.getNameFin(),
                s.getAddressFin(),
                trepo.departureTripCount(s.getId()),
                trepo.returnTripCount(s.getId())
        );
    }
    public static StationFinDTO mapToStationFinDTO(Station station) {
        return new StationFinDTO(
                station.getId(),
                station.getNameFin(),
                station.getAddressFin(),
                station.getCityFin(),
                station.getCapacity()
        );
    }
    }



