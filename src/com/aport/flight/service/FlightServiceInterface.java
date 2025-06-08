package com.aport.flight.service;

import java.util.List;

import com.aport.flight.domain.Flight;

public interface FlightServiceInterface {
    List<Flight> getFlights();
    Flight getFlight(int index);
    Flight getFlight(String flightNumber);
    void addFlight(Flight flight);
    boolean removeFlight(String flightNumber);
}
