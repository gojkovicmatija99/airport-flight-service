package com.raf.airportflightservice.service;

import com.raf.airportflightservice.domain.Airplane;

public interface IAirplaneService {
    Boolean addAirplane(Airplane airplane, String token);
    Boolean deleteAirplane(Long id, String token);
}
