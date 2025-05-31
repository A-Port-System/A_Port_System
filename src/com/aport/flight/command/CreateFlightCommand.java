package com.aport.flight.command;

import com.aport.app.InputUtil;
import com.aport.common.command.*;
import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;

public class CreateFlightCommand implements Command, Undoable {
    @Override
    public Object execute() {
        System.out.println("=== 항공권 생성 ===");
        String flightNumber = InputUtil.readLine("항공편 번호 입력: ");
        String departure = InputUtil.readLine("출발지 입력: ");
        String destination = InputUtil.readLine("도착지 입력: ");
        String departureTime = InputUtil.readLine("출발 시간 입력 (예: 2025-06-01 10:00): ");
        String arrivalTime = InputUtil.readLine("도착 시간 입력 (예: 2025-06-01 22:00): ");
        int price = InputUtil.readInt("가격 입력: ");

        Flight flight = new Flight(flightNumber, departure, destination, departureTime, arrivalTime, price);
        FlightService.getInstance().addFlight(flight);

        System.out.println("항공권이 성공적으로 생성되었습니다.");
        return flight;
    }

    @Override
    public void undo(Object result) {
        if (result instanceof Flight) {
            FlightService service = FlightService.getInstance();
            if (service.removeFlight(((Flight) result).getFlightNumber())) {
                System.out.println("항공권 생성이 취소되었습니다.");
            } else {
                System.out.println("항공권 취소에 실패했습니다. 항공편 번호를 확인하세요.");
            }
        } else {
            System.out.println("잘못된 항공권 정보입니다.");
        }

    }
}
