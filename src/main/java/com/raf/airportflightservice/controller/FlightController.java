package com.raf.airportflightservice.controller;

import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.FlightRepository;
import com.raf.airportflightservice.service.impl.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<Flight>> availableFlights(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(0, 1);
        for(int i=1; i<page; i++)
            pageable = pageable.next();
        return new ResponseEntity<>(flightService.getAvailableFlights(pageable), HttpStatus.ACCEPTED);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Flight>> searchBy(@RequestBody Flight flight) {
        try {
            System.out.println(flight);
            List<Flight> flightsWithPlaneId = flightService.searchFlights(flight);
            return new ResponseEntity<>(flightsWithPlaneId, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
