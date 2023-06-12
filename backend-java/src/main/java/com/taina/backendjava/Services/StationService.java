package com.taina.backendjava.Services;

import com.taina.backendjava.DTOs.StationDTO;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {
    private StationRepository srepo;
    private TripRepository trepo;

    @Autowired
    public StationService(StationRepository srepo, TripRepository trepo) {
        this.srepo = srepo;
        this.trepo = trepo;
    }

    public StationDTO getStationById(int id) {
        Station s = srepo.findById(id).orElse(null);
        if (s == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return new StationDTO(
                s.getNameFin(),
                s.getAddressFin(),
                trepo.departureTripCount(s.getId()),
                trepo.returnTripCount(s.getId())
        );
    }

}
