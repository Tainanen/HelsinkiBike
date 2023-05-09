package com.taina.backendjava.repositories;

import com.taina.backendjava.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {
}
