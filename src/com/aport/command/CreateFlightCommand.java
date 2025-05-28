package com.aport.command;

import com.aport.app.InputUtil;
import com.aport.flight.Flight;
import com.aport.service.FlightService;

public class CreateFlightCommand implements Command {
    @Override
    public void execute() {
        System.out.println("=== 항공권 생성 ===");
        String flightNumber = InputUtil.readLine("항공편 번호 입력: ");
        String departure = InputUtil.readLine("출발지 입력: ");
        String destination = InputUtil.readLine("도착지 입력: ");
        String departureTime = InputUtil.readLine("출발 시간 입력 (예: 2025-06-01 10:00): ");
        String arrivalTime = InputUtil.readLine("도착 시간 입력 (예: 2025-06-01 22:00): ");
        int price = InputUtil.readInt("가격 입력: ");

        Flight flight = new Flight(flightNumber, departure, destination, departureTime, arrivalTime, price);
        FlightService.getInstance().createFlight(flight);

        System.out.println("항공권이 성공적으로 생성되었습니다.");
    }
}
