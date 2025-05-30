package com.aport.command;

import com.aport.app.InputUtil;
import com.aport.flight.Flight;
import com.aport.service.FlightService;

public class ModifyFlightCommand implements Command {
    @Override
    public void execute() {
        System.out.println("=== 항공권 수정 ===");
        String flightNumber = InputUtil.readLine("수정할 항공편 번호 입력: ");

        Flight flight = FlightService.getInstance().getFlight(flightNumber);
        if (flight == null) {
            System.out.println("항공편 번호를 찾을 수 없습니다.");
            return;
        }

        String newDeparture = InputUtil.readLine("새 출발지 입력 (현재: " + flight.getDeparture() + "): ");
        String newDestination = InputUtil.readLine("새 도착지 입력 (현재: " + flight.getDestination() + "): ");
        int newPrice = InputUtil.readInt("새 가격 입력 (현재: " + flight.getPrice() + "): ");

        FlightService.getInstance().modifyFlight(flight, newDeparture, newDestination, newPrice);

        System.out.println("항공권이 성공적으로 수정되었습니다.");
    }
}
