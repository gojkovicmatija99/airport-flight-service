package com.raf.airportflightservice.service.impl;

import com.raf.airportflightservice.domain.Airplane;
import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.repository.IAirplaneRepository;
import com.raf.airportflightservice.service.IAirplaneService;
import com.raf.airportflightservice.utils.UtilsMethods;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService implements IAirplaneService {
    private IAirplaneRepository airplaneRepository;

    public AirplaneService(IAirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    private Boolean checkIfAdmin(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        System.out.println(headers);
        ResponseEntity<Object> responseEntity = UtilsMethods.sendGetHeader("http://localhost:8762/rest-airport-user-service/is_admin", headers);
        Boolean isAdmin = (Boolean) responseEntity.getBody();
        return isAdmin;
    }

    @Override
    public Boolean addAirplane(Airplane airplane, String token) {
        if(checkIfAdmin(token)) {
            airplaneRepository.saveAndFlush(airplane);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAirplane(Long id, String token) {
        Flight flight = new Flight();
        flight.setAirplaneId(id);
        ResponseEntity<Object> responseEntity = UtilsMethods.sendPost("http://localhost:8762/rest-airport-flight-service/flight/search", flight);
        List<Flight> flights = (List<Flight>) responseEntity.getBody();
        if(flights.size() == 0 && checkIfAdmin(token)) {
            airplaneRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }
}