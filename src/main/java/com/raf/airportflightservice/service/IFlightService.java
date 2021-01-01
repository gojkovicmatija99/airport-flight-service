package com.raf.airportflightservice.service;

import com.raf.airportflightservice.domain.Flight;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFlightService {
    List<Flight> getAvailableFlights(Pageable pageable);
    List<Flight> searchFlights();
    void addFlights(Flight flight);
    void deleteFlights(Long id);
}
