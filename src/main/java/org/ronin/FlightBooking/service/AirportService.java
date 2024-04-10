package org.ronin.FlightBooking.service;

import org.ronin.FlightBooking.model.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {
    public List<Airport> getAllAirports();
}
