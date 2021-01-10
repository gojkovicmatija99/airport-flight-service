package com.raf.airportflightservice.service;

import com.raf.airportflightservice.domain.Flight;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFlightService {
    List<Flight> getAvailableFlights(Pageable pageable);
    List<Flight> searchFlights(Flight flight);
    Boolean addFlight(Flight flight, String token);
    Boolean cancelFlight(Long flightId, String token);
    Long getDistance(Long flightId);
    Boolean incrementCurrentPassengers(Long flightId);
}
