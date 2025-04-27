package com.aport.service;

import com.aport.flight.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private static FlightService instance = new FlightService();
    private List<Flight> flightList = new ArrayList<>();

    private FlightService() {
        initializeFlights();
    }

    public static FlightService getInstance() {
        return instance;
    }

    private void initializeFlights() {
        flightList.add(new Flight("KE123", "Seoul", "New York", "2025-06-01 10:00", "2025-06-01 22:00", 1200000));
        flightList.add(new Flight("KE456", "Seoul", "Tokyo", "2025-06-02 09:00", "2025-06-02 12:00", 500000));
        flightList.add(new Flight("KE789", "Busan", "Los Angeles", "2025-06-03 15:00", "2025-06-03 23:00", 1500000));
    }

    public void listFlights() {
        System.out.println("\n=== 항공편 목록 ===");
        for (int i = 0; i < flightList.size(); i++) {
            System.out.println((i + 1) + ". " + flightList.get(i).getFlightInfo());
        }
    }

    public Flight getFlightByIndex(int index) {
        if (index >= 0 && index < flightList.size()) {
            return flightList.get(index);
        } else {
            return null;
        }
    }

    public int getFlightCount() {
        return flightList.size();
    }
}
