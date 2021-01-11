package com.raf.airportflightservice.service.impl;

import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.IFlightRepository;
import com.raf.airportflightservice.service.IFlightService;
import com.raf.airportflightservice.utils.UtilsMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.List;

@Service
public class FlightService implements IFlightService {
    private IFlightRepository flightRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue ticketsQueue;

    public FlightService(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private Boolean checkIfAdmin(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        ResponseEntity<Object> responseEntity = UtilsMethods.sendGetHeader("http://localhost:8081/check_admin", headers);
        Boolean isAdmin = (Boolean) responseEntity.getBody();
        return isAdmin;
    }

    @Override
    public List<Flight> getAvailableFlights(Pageable pageable) {
        return flightRepository.getAvailableFlights(pageable);
    }

    @Override
    public Integer getNumberOfAvailableFlights() {
        return flightRepository.getNumberOfAvailableFlights();
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
    public Boolean addFlight(Flight flight, String token) {
        if(checkIfAdmin(token)) {
            flightRepository.saveAndFlush(flight);
            return true;
        }
        return false;
    }

    @Override
    public Boolean cancelFlight(Long flightId, String token) {
        if(checkIfAdmin(token)) {
            flightRepository.setCanceled(flightId);
            jmsTemplate.convertAndSend(ticketsQueue, flightId.toString());
            return true;
        }
        return false;
    }

    @Override
    public Long getDistance(Long flightId) {
        Flight flight = flightRepository.findById(flightId).get();
        return flight.getDistance();
    }

    @Override
    public Long getPrice(Long flightId) {
        Flight flight = flightRepository.findById(flightId).get();
        return flight.getPrice();
    }

    @Override
    public Boolean incrementCurrentPassengers(Long flightId) {
        Long currentPassengers = (flightRepository.findById(flightId)).get().getCurrentPassengers();
        flightRepository.updatePassengers(flightId, currentPassengers + 1);
        return true;
    }


}
