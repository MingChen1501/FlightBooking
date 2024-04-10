package org.ronin.FlightBooking.controller;

import org.ronin.FlightBooking.model.Airport;
import org.ronin.FlightBooking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airport-service/api/v1/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;
    @GetMapping()
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }
}
