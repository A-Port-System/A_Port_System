package com.aport.command;

import com.aport.app.InputUtil;
import com.aport.reservation.Reservation;
import com.aport.service.ReservationService;
import com.aport.service.UserService;
import com.aport.user.User;


public class CancelReservationCommand implements Command {
    
    @Override
    public void execute() {

        if (!UserService.getInstance().validateLogin(UserService.getInstance().getCurrentUser())) return;

        User user = UserService.getInstance().getCurrentUser();

        if (ReservationService.getInstance().getReservations(user).isEmpty()) {
            System.out.println("예약이 없습니다.");
            return;
        }

        System.out.println("=== 예약 취소 ===");

        System.out.println("예약 목록:");
        for (Reservation reservation : ReservationService.getInstance().getReservations(user)) {
            System.out.println(reservation.getReservationInfo());
        }
        
        String reservationId = InputUtil.readLine("취소할 예약 번호 입력: ");

        Reservation reservation = ReservationService.getInstance().getReservation(reservationId);
        if (reservation == null) {
            System.out.println("예약 번호를 찾을 수 없습니다.");
            return;
        }

        if (ReservationService.getInstance().removeReservation(reservation)) {
            System.out.println("예약이 성공적으로 취소되었습니다.");
        } else {
            System.out.println("예약 취소에 실패했습니다. 다시 시도해주세요.");
        }
    }

}
