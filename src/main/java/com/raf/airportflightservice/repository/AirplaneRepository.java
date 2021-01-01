package com.raf.airportflightservice.repository;

import com.raf.airportflightservice.domain.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
