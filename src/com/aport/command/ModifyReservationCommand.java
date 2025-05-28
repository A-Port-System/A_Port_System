package com.aport.command;

import java.util.List;

import com.aport.app.InputUtil;
import com.aport.flight.Flight;
import com.aport.reservation.Reservation;
import com.aport.service.*;
import com.aport.user.User;

public class ModifyReservationCommand implements Command {

    @Override
    public void execute() {
        
        if (!UserService.getInstance().validateLogin(UserService.getInstance().getCurrentUser())) return;

        
        User user = UserService.getInstance().getCurrentUser();

        if (ReservationService.getInstance().getReservationsForUser(user).isEmpty()) {
            System.out.println("예약이 없습니다.");
            return;
        }

        System.out.println("=== 예약 수정 ===");

        System.out.println("예약 목록:");
        for (Reservation reservation : ReservationService.getInstance().getReservationsForUser(user)) {
            System.out.println(reservation.getReservationInfo());
        }

        String reservationId = InputUtil.readLine("수정할 예약 번호 입력: ");

        Reservation reservation = ReservationService.getInstance().getReservationById(reservationId);
        if (reservation == null) {
            System.out.println("예약 번호를 찾을 수 없습니다.");
            return;
        }


        List<Flight> flights = FlightService.getInstance().getAllFlights();
        System.out.println("\n=== 항공편 목록 ===");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightInfo());
        }

        int flightIndex = InputUtil.readInt("예약할 항공편 번호 입력: ") - 1;
        if (flightIndex < 0 || flightIndex >= flights.size()) {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        Flight selectedFlight = flights.get(flightIndex);
        reservation.setFlight(selectedFlight);
        
        System.out.println("예약이 성공적으로 수정되었습니다.");
    }
}

