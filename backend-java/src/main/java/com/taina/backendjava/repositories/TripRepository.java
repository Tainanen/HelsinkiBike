package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TripRepository extends JpaRepository<Trip, Integer>, PagingAndSortingRepository <Trip, Integer> {

    @Query("SELECT t.id, t.departureStationName, t.returnStationName, t.distanceInMetres / 1000.0 as DistanceKm, t.durationInSeconds / 60.0 as DurationMin FROM Trip t")
    Page<Trip> getTripListByPage (Pageable pageable);

    @Query("SELECT t FROM Trip t WHERE t.departureStationName LIKE %:word% OR t.returnStationName LIKE %:word%")
    Page<Trip> searchTripsByStation(@Param("word") String word, Pageable pageable);

    @Query("SELECT COUNT(t) FROM Trip t WHERE t.departureStationId = :stationId")
    int departureTripCount(@Param("stationId") Integer stationId);

    @Query("SELECT COUNT(t) FROM Trip t WHERE t.returnStationId = :stationId")
    int returnTripCount(@Param("stationId") Integer stationId);

    @Query("SELECT AVG(t.distanceInMetres / 1000.0) FROM Trip t WHERE t.departureStationId = :Id")
    Double findAverageDistanceByDepartureStationId(@Param("Id") int Id);

    @Query("SELECT AVG(t.distanceInMetres / 1000.0) FROM Trip t WHERE t.returnStationId = :Id")
    Double findAverageDistanceByReturnStationId(@Param("Id") int Id);

//    @Query("SELECT s.nameFin, s.tripCount from PopularStation s JOIN Trip t ON s.nameFin = t.departureStationName WHERE t.returnStationId = :Id GROUP BY s.nameFin ORDER BY s.tripCount DESC ")
//List<PopularStation> findPopularDepartureStations(@Param("Id") int Id, Pageable pageable);

//    @Query("SELECT s.nameFin AS nameFin, COUNT(t) AS tripCount FROM Trip t JOIN Station s ON t.departureStationId = s.id WHERE t.returnStationId = :Id GROUP BY s.nameFin ORDER BY COUNT(t) DESC")
//List<Object[]> findPopularDepartureStations(@Param("Id") int Id, Pageable pageable);
}
