package com.raf.airportflightservice.controller;

import com.raf.airportflightservice.domain.Airplane;
import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.service.IAirplaneService;
import com.raf.airportflightservice.service.impl.AirplaneService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {
    private IAirplaneService airplaneService;

    public AirplaneController(IAirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Flight>> deleteAirplane(@PathVariable Long id, @RequestHeader (value = "Authorization") String token) {
        try {
            Boolean isDeleted = airplaneService.deleteAirplane(id, token);
            if(isDeleted)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<List<Flight>> addAirplane(@RequestBody Airplane airplane, @RequestHeader (value = "Authorization") String token) {
        try {
            System.out.println(token);
            Boolean isAdded = airplaneService.addAirplane(airplane, token);
            if(isAdded)
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}