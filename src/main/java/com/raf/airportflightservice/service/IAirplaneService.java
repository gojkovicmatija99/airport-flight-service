package com.raf.airportflightservice.service;

import com.raf.airportflightservice.domain.Airplane;

public interface IAirplaneService {
    void addAirplane(Airplane airplane);
    void deleteAirplane(Long id);
}
