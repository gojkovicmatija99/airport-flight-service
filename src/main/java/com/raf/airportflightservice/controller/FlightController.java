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
        return new ResponseEntity<>(flightService.getAvailableFlights(pageable), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/search")
    public ResponseEntity<List<Flight>> searchBy(@RequestBody Flight flight) {
        try {
            List<Flight> flightsWithPlaneId = flightService.searchFlights(flight);
            return new ResponseEntity<>(flightsWithPlaneId, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<List<Flight>> addFlight(@RequestBody Flight flight, @RequestHeader (value = "Authorization") String token) {
        try {
            Boolean isSaved = flightService.addFlight(flight, token);
            if(isSaved)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin
    @GetMapping("/cancel/{flightId}")
    public ResponseEntity cancelFlight(@PathVariable Long flightId, @RequestHeader (value = "Authorization") String token) {
        try {
            Boolean isCanceled = flightService.cancelFlight(flightId, token);
            if(isCanceled)
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/distance/{flightId}")
    public ResponseEntity<Long> getDistance(@PathVariable Long flightId) {
        try {
            Long distance = flightService.getDistance(flightId);
            return new ResponseEntity(distance, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
