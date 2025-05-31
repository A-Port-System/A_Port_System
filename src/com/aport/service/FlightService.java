package com.aport.service;

import com.aport.flight.Flight;
import com.aport.strategy.file.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private static FlightService instance;
    private final List<Flight> flightList = new ArrayList<>();

    private FlightService() {
        initializeFlights();
    }

    public static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    private void initializeFlights() {
        flightList.add(new Flight("KE123", "Seoul", "New York", "2025-06-01 10:00", "2025-06-01 22:00", 1200000));
        flightList.add(new Flight("KE456", "Seoul", "Tokyo", "2025-06-02 09:00", "2025-06-02 12:00", 500000));
        flightList.add(new Flight("KE789", "Busan", "Los Angeles", "2025-06-03 15:00", "2025-06-03 23:00", 1500000));
    }

    public List<Flight> getFlights() {
        return new ArrayList<>(flightList);
    }

    public Flight getFlight(int index) {
        return (index >= 0 && index < flightList.size()) ? flightList.get(index) : null;
    }

    public Flight getFlight(String flightNumber) {
        return flightList.stream()
                .filter(flight -> flight.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }

    public void addFlight(Flight flight) {
        flightList.add(flight);

        FileStrategy flightFileStrategy = new FlightFileStrategy();
        FileService fileService = FileService.getInstance(flightFileStrategy);
        fileService.save(new File("data/flights.dat").getAbsolutePath());
    }

    public boolean removeFlight(String flightNumber) {
        boolean removed = flightList.removeIf(flight -> flight.getFlightNumber().equals(flightNumber));
        if (removed) {
            FileStrategy flightFileStrategy = new FlightFileStrategy();
            FileService fileService = FileService.getInstance(flightFileStrategy);
            fileService.save(new File("data/flights.dat").getAbsolutePath());
        }
        return removed;
    }
}
