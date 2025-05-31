package com.aport.command;

import com.aport.flight.Flight;
import com.aport.service.FlightService;
import java.util.List;

public class SearchFlightsCommand implements Command {
    @Override
    public void execute() {
        String keyword = com.aport.app.InputUtil.readLine("검색할 항공편 정보(출발지, 도착지, 항공편명 등) 입력: ");
        List<Flight> flights = FlightService.getInstance().getFlights();
        boolean found = false;
        System.out.println("\n=== 검색 결과 ===");
        for (Flight flight : flights) {
            String info = flight.getFlightInfo();
            if (info.contains(keyword)) {
                System.out.println(info);
                found = true;
            }
        }
        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }
}
