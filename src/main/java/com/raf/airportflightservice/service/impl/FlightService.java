package com.raf.airportflightservice.service.impl;

import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.FlightRepository;
import com.raf.airportflightservice.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements IFlightService {
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getAvailableFlights(Pageable pageable) {
        return flightRepository.getAvailableFlights(pageable);
    }

    @Override
    public List<Flight> searchFlights() {
        return null;
    }

    @Override
    public void addFlights(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void deleteFlights(Long id) {

    }
}
