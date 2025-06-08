package com.aport.flight.command;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.flight.builder.DelayedFlightNoticeBuilder;
import com.aport.flight.builder.FlightNoticeDirector;
import com.aport.flight.builder.PreDepartureFlightNoticeBuilder;
import com.aport.flight.domain.Flight;
import com.aport.flight.domain.FlightNotice;
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

        System.out.println("양식 선택");
        System.out.println("1. 지연 공지");
        System.out.println("2. 출발 전 공지");
        System.out.println("0. 자유");
        int choice = InputUtil.readInt("선택: ");

        if (choice < 0 || choice > 2) {
            System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            return null;
        }
        FlightNoticeDirector director = new FlightNoticeDirector();
        FlightNotice notice = null;
        switch (choice) {
            case 1:
                director.setFlightNoticeBuilder(new DelayedFlightNoticeBuilder());
                notice = director.construct();
                break;
            case 2:
                director.setFlightNoticeBuilder(new PreDepartureFlightNoticeBuilder());
                notice = director.construct();
                break;
            case 0:
                String title = InputUtil.readLine("제목: ");
                String message = InputUtil.readLine("내용: ");
                notice = new FlightNotice(title, message);
                
                break;
        }
        flight.notifyObservers(notice);
        return null;
    }
}
