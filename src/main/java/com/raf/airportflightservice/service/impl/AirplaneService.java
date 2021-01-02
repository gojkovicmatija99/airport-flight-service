package com.raf.airportflightservice.service.impl;

import com.raf.airportflightservice.domain.Airplane;
import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.AirplaneRepository;
import com.raf.airportflightservice.repository.FlightRepository;
import com.raf.airportflightservice.service.IAirplaneService;
import com.raf.airportflightservice.utils.UtilsMethods;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService implements IAirplaneService {
    private AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository, FlightRepository flightRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Boolean addAirplane(Airplane airplane) {
        airplaneRepository.saveAndFlush(airplane);
        return true;
    }

    @Override
    public Boolean deleteAirplane(Long id) {
        Flight flight = new Flight();
        flight.setAirplaneId(id);
        ResponseEntity<Object> responseEntity = UtilsMethods.sendPost("http://localhost:8080/flight/search", flight);
        List<Flight> flights = (List<Flight>) responseEntity.getBody();
        if(flights.size() == 0) {
            airplaneRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }
}