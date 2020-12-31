package com.raf.airportflightservice.repository;

import com.raf.airportflightservice.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    public List<Flight> findByDestinion(String destination);
}
