package com.raf.airportflightservice.controller;

import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.FlightRepository;
import com.raf.airportflightservice.service.impl.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all/{page}")
    public List<Flight> availableFlights(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(0, 1);
        for(int i=1; i<page; i++)
            pageable = pageable.next();
        return flightService.getAvailableFlights(pageable);
    }
}
