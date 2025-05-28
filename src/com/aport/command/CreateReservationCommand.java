package com.aport.command;

import com.aport.service.ReservationService;
import com.aport.service.UserService;
import com.aport.flight.Flight;
import com.aport.service.FlightService;
import com.aport.reservation.Reservation;
import com.aport.app.InputUtil;
import java.util.List;

public class CreateReservationCommand implements Command {

    public CreateReservationCommand() { }

    @Override
    public void execute() {
        if (!UserService.getInstance().validateLogin(UserService.getInstance().getCurrentUser())) return;

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
        Reservation reservation = new Reservation(UserService.getInstance().getCurrentUser(), selectedFlight);
        ReservationService.getInstance().createReservation(reservation);
    }
}
