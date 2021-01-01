package com.raf.airportflightservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long numberOfSeats;

    public Airplane(String name, Long numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    public Airplane() {

    }

    public Long getId() {
        return id;
    }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getName() {
        return name;
    }
}
