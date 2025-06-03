package com.aport.flight.command;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;
import com.aport.common.command.Undoable;

public class ModifyFlightCommand implements Command, Undoable {
    @Override
    public Object execute() {
        System.out.println("=== 항공권 수정 ===");
        String flightNumber = InputUtil.readLine("수정할 항공편 번호 입력: ");

        Flight flight = FlightService.getInstance().getFlight(flightNumber);
        if (flight == null) {
            System.out.println("항공편 번호를 찾을 수 없습니다.");
            return null;
        }

        String newDeparture = InputUtil.readLine("새 출발지 입력 (현재: " + flight.getDeparture() + "): ");
        String newDestination = InputUtil.readLine("새 도착지 입력 (현재: " + flight.getDestination() + "): ");
        int newPrice = InputUtil.readInt("새 가격 입력 (현재: " + flight.getPrice() + "): ");

        if (newDeparture.isEmpty() || newDestination.isEmpty() || newPrice <= 0) {
            System.out.println("입력값이 올바르지 않습니다. 다시 시도해주세요.");
            return null;
        }
        Flight result = flight.copy();
        flight.setDeparture(newDeparture);
        flight.setDestination(newDestination);
        flight.setPrice(newPrice);
        System.out.println("항공권이 성공적으로 수정되었습니다.");
        return result;
    }

    @Override
    public void undo(Object result) {
        if (result instanceof Flight) {
            FlightService service = FlightService.getInstance();
            Flight originalFlight = service.getFlight(((Flight) result).getFlightNumber());
            if (originalFlight != null) {
                Flight newFlight = service.getFlight(originalFlight.getFlightNumber());
                if (newFlight != null) {
                    newFlight.setDeparture(originalFlight.getDeparture());
                    newFlight.setDestination(originalFlight.getDestination());
                    newFlight.setPrice(originalFlight.getPrice());
                    System.out.println("항공권 수정이 취소되었습니다.");
                } else {
                    System.out.println("항공편 번호를 찾을 수 없습니다. 취소에 실패했습니다.");
                }
            } else {
                System.out.println("항공편 번호를 찾을 수 없습니다. 취소에 실패했습니다.");
            }
        } else {
            System.out.println("잘못된 항공권 정보입니다.");
        }
    }
}
