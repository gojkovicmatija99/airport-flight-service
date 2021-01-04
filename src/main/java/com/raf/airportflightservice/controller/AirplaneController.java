package com.raf.airportflightservice.controller;

import com.raf.airportflightservice.domain.Airplane;
import com.raf.airportflightservice.domain.Flight;
import com.raf.airportflightservice.service.impl.AirplaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {
    private AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Flight>> deleteAirplane(@PathVariable Long id) {
        Boolean isDeleted = airplaneService.deleteAirplane(id);
        if(isDeleted)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<List<Flight>> addAirplane(@RequestBody Airplane airplane) {
        try {
            airplaneService.addAirplane(airplane);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}