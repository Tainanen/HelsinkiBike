package com.taina.backendjava;
import com.taina.backendjava.entities.Station;
import com.taina.backendjava.entities.Trip;
import com.taina.backendjava.repositories.StationRepository;
import com.taina.backendjava.repositories.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestControllers {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StationRepository stationRepository;

    @Mock
    private TripRepository tripRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStationById() throws Exception {
            Station station = new Station();
            station.setId(1);
            station.setNameSwe("Brunnsparken");
            station.setNameEn("Kaivopuisto");
            station.setAddressFin("Meritori 1");
            station.setAddressSwe("Havstorget 1");
            station.setCityFin("Helsinki");
            station.setCitySwe("Helsingfors");
            station.setOperator("");
            station.setCapacity(30);
            station.setY(60.155369615074);
            station.setX(24.9502114714031);
            when(stationRepository.findById(1)).thenReturn(Optional.of(station));


        mockMvc.perform(get("/stations/allInfo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nameFin").value("Kaivopuisto"))
                .andExpect(jsonPath("$.nameSwe").value("Brunnsparken"))
                .andExpect(jsonPath("$.nameEn").value("Kaivopuisto"))
                .andExpect(jsonPath("$.addressFin").value("Meritori 1"))
                .andExpect(jsonPath("$.addressSwe").value("Havstorget 1"))
                .andExpect(jsonPath("$.cityFin").value("Helsinki"))
                .andExpect(jsonPath("$.citySwe").value("Helsingfors"))
                .andExpect(jsonPath("$.operator").value(""))
                .andExpect(jsonPath("$.capacity").value(30))
                .andExpect(jsonPath("$.y").value(60.155369615074))
                .andExpect(jsonPath("$.x").value(24.9502114714031));
    }

    @Test
    public void testGetTripById() throws Exception {
        Trip trip = new Trip();
        trip.setId(1);
        String departureTimeString = "2021-05-31T23:57:25";
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeString);
        trip.setDepartureTime(departureTime);
        String returnTimeString = "2021-06-01T00:05:46";
        LocalDateTime returnTime = LocalDateTime.parse(returnTimeString);
        trip.setReturnTime(returnTime);
        trip.setDepartureStationId(94);
        trip.setDepartureStationName("Laajalahden aukio");
        trip.setReturnStationId(100);
        trip.setReturnStationName("Teljäntie");
        trip.setDistanceInMetres(2043);
        trip.setDurationInSeconds(500);
        when(tripRepository.findById(1)).thenReturn(Optional.of(trip));

        mockMvc.perform(get("/trips/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.departureTime").value(departureTime.toString()))
                .andExpect(jsonPath("$.returnTime").value(returnTime.toString()))
                .andExpect(jsonPath("$.departureStationId").value(94))
                .andExpect(jsonPath("$.departureStationName").value("Laajalahden aukio"))
                .andExpect(jsonPath("$.returnStationId").value(100))
                .andExpect(jsonPath("$.returnStationName").value("Teljäntie"))
                .andExpect(jsonPath("$.distanceInMetres").value(2043))
                .andExpect(jsonPath("$.durationInSeconds").value(500));

    }
}





