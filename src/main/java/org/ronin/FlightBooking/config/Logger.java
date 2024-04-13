package org.ronin.FlightBooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Logger implements CommandLineRunner {


    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        String appName = environment.getProperty("spring.application.name");
        String redisHost = environment.getProperty("spring.data.redis.host");
        String redisPort = environment.getProperty("spring.data.redis.port");

        System.out.println("Application Name: " + appName);
        System.out.println("Redis Host: " + redisHost);
        System.out.println("Redis Port: " + redisPort);
    }
}