package com.aport.command;

import com.aport.app.InputUtil;
import com.aport.service.FlightService;

public class CancelFlightCommand implements Command {
    @Override
    public void execute() {
        System.out.println("=== 항공권 취소 ===");
        String flightNumber = InputUtil.readLine("취소할 항공편 번호 입력: ");

        boolean success = FlightService.getInstance().cancelFlight(flightNumber);
        if (success) {
            System.out.println("항공권이 성공적으로 취소되었습니다.");
        } else {
            System.out.println("항공편 번호를 찾을 수 없습니다.");
        }
    }
}
