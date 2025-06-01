package com.aport.reservation.decorator;

import com.aport.common.Decorator;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.user.domain.User; 
import com.aport.user.service.UserService;
import com.aport.common.command.Command;

import java.util.List;

public class ViewReservationsDecorator extends Decorator {
    public ViewReservationsDecorator(Command command) {
        super(command);
    }

    @Override
    public Object execute() {
        view();
        return super.execute();
    }

    public void view() {
        User currentUser = UserService.getInstance().getCurrentUser();
        if (currentUser == null) {
            System.out.println("[오류] 현재 사용자가 없습니다. 로그인 후 다시 시도하세요.");
            return;
        }
        List<Reservation> reservations = ReservationService.getInstance().getReservations(currentUser);
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
