package com.taina.backendjava.repositories;

import com.taina.backendjava.DTOs.StationFinDTO;
import com.taina.backendjava.DTOs.StationSweDTO;
import com.taina.backendjava.entities.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Integer>, PagingAndSortingRepository<Station, Integer> {
    @Query("SELECT new com.taina.backendjava.DTOs.StationFinDTO(s.id, s.nameFin, s.addressFin, s.cityFin, s.capacity) FROM Station s WHERE s.nameFin LIKE %:word%")
//    @Query("SELECT s.id, s.nameFin, s.addressFin, s.cityFin, s.operator, s.capacity, s.x, s.y from Station s WHERE s.nameFin LIKE %:word% ")
    Page<StationFinDTO> searchStationByName(@Param("word") String word, Pageable pageable);

    @Query("SELECT new com.taina.backendjava.DTOs.StationSweDTO(s.id, s.nameSwe, s.addressSwe, s.citySwe, s.capacity) from Station s WHERE s.nameSwe LIKE %:word%")
    Page<StationSweDTO> searchStationByNameSwe(@Param("word") String word, Pageable pageable);

    @Query("SELECT t.departureStationName, COUNT(t) FROM Trip t WHERE t.returnStationId = :Id GROUP BY t.departureStationName ORDER BY COUNT(t) DESC")
    List<Object[]> findPopularDepartureStations(@Param("Id") int Id, Pageable pageable);

    @Query("SELECT t.returnStationName, COUNT(t) FROM Trip t WHERE t.departureStationId = :Id GROUP BY t.returnStationName ORDER BY COUNT(t) DESC")
    List<Object[]> findPopularReturnStations(@Param("Id") int Id, Pageable pageable);
}

