package com.aport.flight.command;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.common.command.Undoable;
import com.aport.flight.service.FlightService;
import com.aport.flight.domain.Flight;

public class CancelFlightCommand implements Command, Undoable {
    @Override
    public Object execute() {
        Flight result = null;
        System.out.println("=== 항공권 취소 ===");
        String flightNumber = InputUtil.readLine("취소할 항공편 번호 입력: ");
        result = FlightService.getInstance().getFlight(flightNumber).copy();
        boolean success = FlightService.getInstance().removeFlight(flightNumber);
        if (success) {
            System.out.println("항공권이 성공적으로 취소되었습니다.");
        } else {
            System.out.println("항공편 번호를 찾을 수 없습니다.");
        }
        return result;
    }

    @Override
    public void undo(Object result) {
        FlightService service = FlightService.getInstance();
        service.addFlight((Flight) result);
        System.out.println("항공권 취소가 되돌려졌습니다.");
    }
}
