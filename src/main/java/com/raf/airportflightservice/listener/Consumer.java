package com.raf.airportflightservice.listener;

import com.raf.airportflightservice.service.IFlightService;
import com.raf.airportflightservice.service.impl.FlightService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private IFlightService flightService;

    public Consumer(IFlightService flightService) {
        this.flightService = flightService;
    }

    @JmsListener(destination = "flights.queue")
    public void consume(String flightId) {
        flightService.incrementCurrentPassengers(Long.parseLong(flightId));
    }
}
