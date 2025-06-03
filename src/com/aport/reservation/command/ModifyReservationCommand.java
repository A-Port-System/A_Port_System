package com.aport.reservation.command;

import java.util.List;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.flight.domain.Flight;
import com.aport.flight.service.FlightService;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

public class ModifyReservationCommand implements Command {

    @Override
    public Object execute() {

        if (!UserService.getInstance().validateLogin(UserService.getInstance().getCurrentUser())) return null;

        User user = UserService.getInstance().getCurrentUser();

        if (ReservationService.getInstance().getReservations(user).isEmpty()) {
            System.out.println("예약이 없습니다.");
            return null;
        }

        System.out.println("=== 예약 수정 ===");
        String reservationId = InputUtil.readLine("수정할 예약 번호 입력: ");

        Reservation reservation = ReservationService.getInstance().getReservation(reservationId);
        if (reservation == null) {
            System.out.println("예약 번호를 찾을 수 없습니다.");
            return null;
        }

        List<Flight> flights = FlightService.getInstance().getFlights();
        int flightIndex = InputUtil.readInt("예약할 항공편 번호 입력: ") - 1;
        if (flightIndex < 0 || flightIndex >= flights.size()) {
            System.out.println("잘못된 선택입니다.");
            return null;
        }

        Flight selectedFlight = flights.get(flightIndex);
        Reservation res = reservation.copy();
        reservation.setFlight(selectedFlight);
        
        System.out.println("예약이 성공적으로 수정되었습니다.");
        return res;
    }
}

