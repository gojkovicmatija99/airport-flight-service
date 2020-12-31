package com.raf.airportflightservice.controller;

import com.raf.airportflightservice.domain.Airplane;
import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.AirplaneRepository;
import com.raf.airportflightservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {
    private AirplaneRepository airplaneRepository;
    private FlightRepository flightRepository;

    @Autowired
    public TestController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/airplane/{id}")
    public Airplane getById(@PathVariable Long id) {
        Optional<Airplane> airplane = airplaneRepository.findById(id);
        return airplane.get();
    }

    @GetMapping("/airplane/save")
    public ResponseEntity save() {
        Airplane airplane = new Airplane( "test3", 1232l);
        airplaneRepository.saveAndFlush(airplane);
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

    @GetMapping("/flight/save")
    public ResponseEntity saveFlight() {
        Flight flight = new Flight(12l, "Kraljevo", "Beograd", "3h", 12000l);
        flightRepository.saveAndFlush(flight);
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }

    @GetMapping("/airplane/{id}")
    public List<Flight> getByDestination(@PathVariable Long id) {
        List<Flight> flights = flightRepository.findByDestinion("Beograd");
        return flights;
    }
}
