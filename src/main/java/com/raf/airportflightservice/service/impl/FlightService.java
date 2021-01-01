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
    public List<Flight> searchFlights(Flight flight) {
        if(flight.getStartDestination() != null)
            return flightRepository.findByStartDestination(flight.getStartDestination());
        else if(flight.getEndDestination() != null)
            return flightRepository.findByEndDestination(flight.getEndDestination());
        else if (flight.getAirplaneId() != null)
            return flightRepository.findByAirplaneId(flight.getAirplaneId());
        else if (flight.getPrice() != null)
            return flightRepository.findByPrice(flight.getPrice());
        else if (flight.getDistance() != null)
            return flightRepository.findByDistance(flight.getDistance());
        return null;
    }

    @Override
    public Boolean addFlights(Flight flight) {
        try {
            flightRepository.saveAndFlush(flight);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteFlights(Long id) {
        return false;
    }
}
