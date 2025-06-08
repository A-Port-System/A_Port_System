package com.aport.flight.proxy;

import java.util.List;

import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;
import com.aport.flight.service.FlightServiceInterface;

public class FlightServiceProxy implements FlightServiceInterface {
	private static FlightServiceProxy instance;
    private FlightService realService;
    private List<Flight> cachedFlights;

    private FlightServiceProxy() {}

    public static FlightServiceProxy getInstance() {
        if (instance == null) {
            instance = new FlightServiceProxy();
        }
        return instance;
    }

    @Override
    public List<Flight> getFlights() {
        if (cachedFlights == null) {
            System.out.println("Flight list not loaded yet. Loading...");
            realService = FlightService.getInstance();
            cachedFlights = realService.getFlights();
        } else {
            System.out.println("Using cached flight list.");
        }
        return cachedFlights;
    }

    @Override
    public Flight getFlight(int index) {
        return getFlights().get(index);
    }

    @Override
    public Flight getFlight(String flightNumber) {
        return getFlights().stream()
            .filter(f -> f.getFlightNumber().equals(flightNumber))
            .findFirst().orElse(null);
    }

    @Override
    public void addFlight(Flight flight) {
        getRealService().addFlight(flight);
        cachedFlights = null; // invalidate cache
    }

    @Override
    public boolean removeFlight(String flightNumber) {
        boolean removed = getRealService().removeFlight(flightNumber);
        if (removed) cachedFlights = null;
        return removed;
    }

    private FlightService getRealService() {
        if (realService == null) {
            realService = FlightService.getInstance();
        }
        return realService;
    }
}
