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
    private Long distance;
    private Long price;
    private Long currentPassengers;
    private Boolean canceled;

    public Flight(Long airplaneId, String startDestination, String endDestination, Long distance, Long price) {
        this.airplaneId = airplaneId;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.distance = distance;
        this.price = price;
        this.currentPassengers = 0l;
        this.canceled = false;
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

    public Long getDistance() {
        return distance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setCurrentPassengers(Long currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airplaneId=" + airplaneId +
                ", startDestination='" + startDestination + '\'' +
                ", endDestination='" + endDestination + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", currentPassengers=" + currentPassengers +
                '}';
    }
}
