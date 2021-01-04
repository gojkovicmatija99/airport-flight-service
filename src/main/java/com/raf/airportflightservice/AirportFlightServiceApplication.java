package com.raf.airportflightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AirportFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportFlightServiceApplication.class, args);
	}

}
