package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Station;
import com.taina.backendjava.entities.SingleStation;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Integer>, PagingAndSortingRepository<Station, Integer> {

    @Query("SELECT s.id, s.nameFin, s.nameSwe, s.nameEn, s.addressFin, s.addressSwe, s.cityFin, s.citySwe, s.operator, s.capacity, s.x, s.y from Station s WHERE s.nameFin LIKE %:word% OR s.nameSwe LIKE %:word% OR s.nameEn LIKE %:word%")
    Page<Station> searchStationByName(@Param("word") String word, Pageable pageable);

    @Query("SELECT t.departureStationName, COUNT(t) FROM Trip t WHERE t.returnStationId = :Id GROUP BY t.departureStationName ORDER BY COUNT(t) DESC")
    List<Object[]> findPopularDepartureStations(@Param("Id") int Id, Pageable pageable);

    @Query("SELECT t.returnStationName, COUNT(t) FROM Trip t WHERE t.departureStationId = :Id GROUP BY t.returnStationName ORDER BY COUNT(t) DESC")
    List<Object[]> findPopularReturnStations(@Param("Id") int Id, Pageable pageable);
}

