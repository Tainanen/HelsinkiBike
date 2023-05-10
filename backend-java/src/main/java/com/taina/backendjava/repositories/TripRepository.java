package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TripRepository extends JpaRepository<Trip, Integer>, PagingAndSortingRepository <Trip, Integer> {

    @Query("SELECT t.departureStationName, t.returnStationName, t.Distance_m / 1000.0 as Distance_km, t.Duration_s / 60.0 as Duration_min FROM Trip t")
    Page<Trip> getTripListByPage (Pageable pageable);

    @Query("SELECT t.departureStationName, t.returnStationName, t.Distance_m / 1000.0 as Distance_km, t.Duration_s / 60.0 as Duration_min FROM Trip t ORDER BY t.departureStationName")
    Page<Trip> getTripOrderByDepStation(Pageable pageable);

    @Query("SELECT t.departureStationName, t.returnStationName, t.Distance_m / 1000.0 as Distance_km, t.Duration_s / 60.0 as Duration_min FROM Trip t ORDER BY t.returnStationName")
    Page<Trip> getTripOrderByReturnStation(Pageable pageable);

    @Query("SELECT t.departureStationName, t.returnStationName, t.Distance_m / 1000.0 as Distance_km, t.Duration_s / 60.0 as Duration_min FROM Trip t ORDER BY t.Distance_m / 1000.0")
    Page<Trip> getTripOrderByDistance(Pageable pageable);


    @Query("SELECT t.departureStationName, t.returnStationName, t.Distance_m / 1000.0 as Distance_km, t.Duration_s / 60.0 as Duration_min FROM Trip t ORDER BY t.Duration_s / 60.0")
    Page<Trip> getTripOrderByDuration(Pageable pageable);

    @Query("SELECT t FROM Trip t WHERE t.departureStationName LIKE %:word% OR t.returnStationName LIKE %:word%")
    Page<Trip> searchTripsByStation(@Param("word") String word, Pageable pageable);
}
