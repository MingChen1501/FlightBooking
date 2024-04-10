package org.ronin.FlightBooking.service.impl;

import org.ronin.FlightBooking.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.ronin.FlightBooking.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    private static final String CACHE_KEY = "AIRPORTS";
    @Autowired
    private RedisTemplate<String, List<Airport>> redisTemplate;
    @Override

    public List<Airport> getAllAirports() {
        String cacheKey = CACHE_KEY;
        ListOperations<String, List<Airport>> operations = redisTemplate.opsForList();

        List<Airport> airports = operations.index(cacheKey, 0);
        if (airports == null) {
            airports = fetchAirportsFromDB();
            System.out.println("Cache miss");
            operations.leftPush(cacheKey, airports);
        } else {
            System.out.println("Cache hit");
        }
        return airports;
    }
    private List<Airport> fetchAirportsFromDB() {
        List<Airport> airports = new ArrayList<>();

        airports.add(new Airport("SFO", "San Francisco International Airport"));
        airports.add(new Airport("LAX", "Los Angeles International Airport"));
        airports.add(new Airport("JFK", "John F Kennedy International Airport"));
        airports.add(new Airport("ORD", "O'Hare International Airport"));
        airports.add(new Airport("DFW", "DallasFort Worth International Airport"));
        airports.add(new Airport("ATL", "Hartsfield Jackson Atlanta International Airport"));
        airports.add(new Airport("DEN", "Denver International Airport"));
        airports.add(new Airport("LAS", "McCarran International Airport"));
        airports.add(new Airport("PHX", "Phoenix Sky Harbor International Airport"));
        try {
            Thread.sleep(1000); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        return airports;
    }
}
