package com.aport.reservation.command;

import com.aport.user.service.UserService;

import java.util.List;

import com.aport.common.Command;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;

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
