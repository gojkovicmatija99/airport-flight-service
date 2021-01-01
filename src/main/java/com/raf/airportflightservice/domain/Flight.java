package com.raf.airportflightservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long airplaneId;
    private String startDestination;
    private String endDestination;
    private String time;
    private Long price;
    private Long currentPassengers;

    public Flight(Long airplaneId, String startDestination, String endDestination, String time, Long price) {
        this.airplaneId = airplaneId;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.time = time;
        this.price = price;
        this.currentPassengers = 0l;
    }

    public Flight() {

    }

    void addPassenger() {
        this.currentPassengers += 1;
    }

    public Long getId() {
        return id;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public Long getCurrentPassengers() {
        return currentPassengers;
    }

    public Long getPrice() {
        return price;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public String getTime() {
        return time;
    }

}
