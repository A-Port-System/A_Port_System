package com.aport.reservation.command;

import com.aport.user.service.UserService;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;
import com.aport.common.command.Undoable;

import java.util.List;

public class CreateReservationCommand implements Command, Undoable {

    public CreateReservationCommand() { }

    @Override
    public Object execute() {
        if (!UserService.getInstance().validateLogin(UserService.getInstance().getCurrentUser())) return null;

        List<Flight> flights = FlightService.getInstance().getFlights();
        System.out.println("\n=== 항공편 목록 ===");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightInfo());
        }

        int flightIndex = InputUtil.readInt("예약할 항공편 번호 입력: ") - 1;
        if (flightIndex < 0 || flightIndex >= flights.size()) {
            System.out.println("잘못된 선택입니다.");
            return null;
        }

        Flight selectedFlight = flights.get(flightIndex);
        Reservation reservation = new Reservation(UserService.getInstance().getCurrentUser(), selectedFlight);
        ReservationService.getInstance().addReservation(reservation);
        System.out.println("예약이 성공적으로 생성되었습니다: " + reservation.getReservationInfo());
        System.out.println("예약 번호: " + reservation.getReservationId());
        return reservation;
    }

    @Override
    public void undo(Object result) {
        if (result instanceof Reservation) {
            ReservationService service = ReservationService.getInstance();
            Reservation createdReservation = (Reservation) result;
            service.removeReservation(createdReservation);
            System.out.println("예약 생성이 되돌려졌습니다.");
        } else {
            System.out.println("잘못된 예약 정보입니다.");
        }
    }
}
