package com.aport.command;

import java.util.List;
import com.aport.flight.Flight;
import com.aport.service.FlightService;

public class ViewFlightCommand implements Command {
    @Override
    public void execute() {
        System.out.println("=== 항공권 조회 ===");
        List<Flight> flights = FlightService.getInstance().getAllFlights();

        if (flights.isEmpty()) {
            System.out.println("등록된 항공권이 없습니다.");
            return;
        }

        for (Flight flight : flights) {
            System.out.println(flight.getFlightInfo());
        }
    }
}
