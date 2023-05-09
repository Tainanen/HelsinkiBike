package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TripRepository extends JpaRepository<Trip, Integer>, PagingAndSortingRepository <Trip, Integer> {
}
