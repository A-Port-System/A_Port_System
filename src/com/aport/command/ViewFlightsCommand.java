package com.aport.command;

import com.aport.service.FlightService;

public class ViewFlightsCommand implements Command {
    private final FlightService flightService;

    public ViewFlightsCommand(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public void execute() {
        flightService.viewFlights();
    }
}
