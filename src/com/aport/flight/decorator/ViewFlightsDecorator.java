package com.aport.flight.decorator;

import com.aport.common.Decorator;
import com.aport.common.command.Command;
import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;

import java.util.List;

public class ViewFlightsDecorator extends Decorator {

    public ViewFlightsDecorator(Command command) {
        super(command);
    }

    @Override
    public Object execute() {
        view();
        return super.execute();
    }

    public void view() {
        System.out.println("\n=== 항공편 목록 ===");
        FlightService service = FlightService.getInstance();
        List<Flight> flights = service.getFlights();
        if (flights.isEmpty()) {
            System.out.println("등록된 항공편이 없습니다.");
            return;
        }
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightInfo());
        }
    }
}
