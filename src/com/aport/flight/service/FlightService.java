package com.aport.flight.service;

import com.aport.file.service.FileService;
import com.aport.flight.domain.Flight;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlightService implements Serializable{
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

    public static void setInstance(FlightService service) {
        instance = service;
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

        FileService fileService = FileService.getInstance();
        fileService.save();
    }

    public boolean removeFlight(String flightNumber) {
        boolean removed = flightList.removeIf(flight -> flight.getFlightNumber().equals(flightNumber));
        if (removed) {
            FileService fileService = FileService.getInstance();
            fileService.save();
        }
        return removed;
    }
}
