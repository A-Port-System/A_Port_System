package com.aport.reservation.command;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;
import com.aport.common.command.Undoable;
import com.aport.flight.domain.Flight;
import com.aport.common.Observer;

public class CancelReservationCommand implements Command, Undoable {
    
    @Override
    public Object execute() {
        User user = UserService.getInstance().getCurrentUser();
        if (ReservationService.getInstance().getReservations(user).isEmpty()) {
            System.out.println("예약이 없습니다.");
            return null;
        }
        System.out.println("=== 예약 취소 ===");
        
        String reservationId = InputUtil.readLine("취소할 예약 번호 입력: ");

        Reservation reservation = ReservationService.getInstance().getReservation(reservationId);
        if (reservation == null) {
            System.out.println("예약 번호를 찾을 수 없습니다.");
            return null;
        }

        Reservation res = reservation.copy();
        Flight selectedFlight = reservation.getFlight();
        Observer observer = (Observer) user;
        selectedFlight.removeObserver(observer);
        ReservationService.getInstance().removeReservation(reservation);
        System.out.println("예약이 성공적으로 취소되었습니다.");
        return res;
    }

    @Override
    public void undo(Object result) {
        if (result instanceof Reservation) {
            ReservationService service = ReservationService.getInstance();
            Reservation canceledReservation = (Reservation) result;
            service.addReservation(canceledReservation);
            Flight flight = canceledReservation.getFlight();
            Observer observer = (Observer) UserService.getInstance().getCurrentUser();
            flight.addObserver(observer);
            System.out.println("예약 취소가 되돌려졌습니다.");
        } else {
            System.out.println("잘못된 예약 정보입니다.");
        }
    }

}
