package com.raf.airportflightservice.controller;

import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.FlightRepository;
import com.raf.airportflightservice.service.impl.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @CrossOrigin
    @GetMapping("/all/{page}")
    public ResponseEntity<List<Flight>> availableFlights(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(0, 3);
        for(int i=1; i<page; i++)
            pageable = pageable.next();
        return new ResponseEntity<>(flightService.getAvailableFlights(pageable), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @PostMapping("/search")
    public ResponseEntity<List<Flight>> searchBy(@RequestBody Flight flight) {
        try {
            List<Flight> flightsWithPlaneId = flightService.searchFlights(flight);
            return new ResponseEntity<>(flightsWithPlaneId, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<List<Flight>> addFlight(@RequestBody Flight flight) {
        try {
            Boolean isSaved = flightService.addFlight(flight);
            if(isSaved)
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/cancel/{flightId}")
    public ResponseEntity cancelFlight(@PathVariable Long flightId) {
        try {
            flightService.cancelFlight(flightId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
