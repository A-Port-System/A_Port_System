package com.aport.flight.command;

import com.aport.app.InputUtil;
import com.aport.common.command.*;
import com.aport.common.command.Undoable;
import com.aport.flight.domain.Flight;
import com.aport.flight.proxy.FlightServiceProxy;

public class CancelFlightCommand implements Command, Undoable {
    @Override
    public Object execute() {
        Flight result = null;
        System.out.println("=== 항공권 취소 ===");
        String flightNumber = InputUtil.readLine("취소할 항공편 번호 입력: ");
        result = FlightServiceProxy.getInstance().getFlight(flightNumber).copy();
        boolean success = FlightServiceProxy.getInstance().removeFlight(flightNumber);
        if (success) {
            System.out.println("항공권이 성공적으로 취소되었습니다.");
        } else {
            System.out.println("항공편 번호를 찾을 수 없습니다.");
        }
        return result;
    }

    @Override
    public void undo(Object result) {
    	FlightServiceProxy service = FlightServiceProxy.getInstance();
        service.addFlight((Flight) result);
        System.out.println("항공권 취소가 되돌려졌습니다.");
    }
}

class CancelFlightCommandFactory extends CommandFactory {
    @Override
    protected Command createCommand() {
        return new CancelFlightCommand();
    }
}