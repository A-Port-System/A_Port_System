package com.aport.command;

import com.aport.service.FlightService;
import com.aport.flight.Flight;
import java.util.List;

public class ViewFlightsCommand implements Command {

    @Override
    public void execute() {
        List<Flight> flights = FlightService.getInstance().getFlights();
        if (flights.isEmpty()) {
            System.out.println("등록된 항공권이 없습니다.");
            return;
        }
        System.out.println("\n=== 항공편 목록 ===");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightInfo());
        }
    }
}
