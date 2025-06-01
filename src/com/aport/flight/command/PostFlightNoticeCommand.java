package com.aport.flight.command;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.flight.domain.FlightNotice;
import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;

public class PostFlightNoticeCommand implements Command {

    @Override
    public Object execute() {
        // Notify observers with the flight notice
        System.out.println("=== 항공권 공지사항 게시 ===");
        int flightId = InputUtil.readInt("항공권 ID: ");
        if (flightId <= 0) {
            System.out.println("잘못된 항공권 ID입니다. 다시 시도해주세요.");
            return null;
        }
        Flight flight = FlightService.getInstance().getFlight(flightId-1);
        String title = InputUtil.readLine("제목: ");
        String message = InputUtil.readLine("내용: ");
        FlightNotice notice = new FlightNotice(title, message);
        flight.notifyObservers(notice);
        return null;
    }
}
