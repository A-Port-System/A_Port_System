package com.aport.command;

import com.aport.service.ReservationService;
import com.aport.service.UserService;
import java.util.List;
import com.aport.reservation.Reservation;

public class ViewReservationsCommand implements Command {

    @Override
    public void execute() {
        List<Reservation> reservations = ReservationService.getInstance().getReservations(UserService.getInstance().getCurrentUser());
        if (reservations.isEmpty()) {
            System.out.println("예약 내역이 없습니다.");
            return;
        }

        System.out.println("\n=== 내 예약 목록 ===");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getReservationInfo());
        }
    }
}
