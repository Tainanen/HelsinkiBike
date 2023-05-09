package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Trip;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TripRepository extends JpaRepository<Trip, Integer>, PagingAndSortingRepository <Trip, Integer> {

    @Query("SELECT t.departureStationName, t.returnStationName, t.Distance_m / 1000.0 as Distance_km, t.Duration_s / 60.0 as Duration_min FROM Trip t")
    Page<Trip> getTripSummaries(Pageable pageable);
}
