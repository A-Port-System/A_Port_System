package com.aport.flight.command;

import com.aport.common.command.Command;
import com.aport.flight.domain.Flight;
import com.aport.flight.proxy.FlightServiceProxy;
import com.aport.flight.service.FlightService;

import java.util.List;

public class ViewFlightsCommand implements Command {

    @Override
    public Object execute() {
        List<Flight> flights = FlightServiceProxy.getInstance().getFlights();
        if (flights.isEmpty()) {
            System.out.println("등록된 항공권이 없습니다.");
            return null;
        }
        System.out.println("\n=== 항공편 목록 ===");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightInfo());
        }
        return null;
    }
}
