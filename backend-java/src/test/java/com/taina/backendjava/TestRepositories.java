package com.taina.backendjava;

import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class TestRepositories {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TripRepository tripRepository;
    @Test
    public void testCategoryRepository(){
        assertThat(this.stationRepository).isNotNull();
    }

    @Test
    public void testProgramRepository(){
        assertThat(this.tripRepository).isNotNull();
    }

}

