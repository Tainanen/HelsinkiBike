package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StationRepository extends JpaRepository<Station, Integer>, PagingAndSortingRepository<Station, Integer> {
}
