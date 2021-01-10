package com.raf.airportflightservice.repository;

import com.raf.airportflightservice.domain.Flight;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT f.* FROM flight f INNER JOIN airplane a ON a.id = f.airplane_id WHERE f.current_passengers < a.number_of_seats", nativeQuery = true)
    List<Flight> getAvailableFlights(Pageable pageable);
    List<Flight> findByAirplaneId(Long airplaneId);
    List<Flight> findByStartDestination(String startDestination);
    List<Flight> findByEndDestination(String endDestination);
    List<Flight> findByPrice(Long price);
    List<Flight> findByDistance(Long distance);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE flight SET canceled=1 WHERE id = :flightId", nativeQuery = true)
    Integer setCanceled(@Param("flightId") Long flightId);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE flight SET current_passengers=:currPlusOne WHERE id = :flightId", nativeQuery = true)
    Integer updatePassengers(@Param("flightId") Long flightId, @Param("currPlusOne") Long currPlusOne);
}
