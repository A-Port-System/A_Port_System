package com.aport.seat.command;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.seat.service.SeatService;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

import java.util.List;

public class SelectSeatCommand implements Command {

    private final SeatService seatService = new SeatService();

    @Override
    public Object execute() {
        User user = UserService.getInstance().getCurrentUser();
        List<Reservation> reservations = ReservationService.getInstance().getReservations(user);

        if (reservations.isEmpty()) {
            System.out.println("예약 내역이 없습니다.");
            return null;
        }

        System.out.println("\n예약을 선택하세요.");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, reservations.get(i).getReservationInfo());
        }

        int selected = InputUtil.readInt("예약 번호 선택: ");
        if (selected < 1 || selected > reservations.size()) {
            System.out.println("잘못된 입력입니다.");
            return null;
        }

        Reservation reservation = reservations.get(selected - 1);

        if (seatService.hasSeat(reservation)) {
            System.out.println("이미 좌석이 지정된 예약입니다: " + reservation.getSeat().getSeatNumber());
            return null;
        }

        seatService.printSeatMap(reservation.getFlight());
        String input = InputUtil.readLine("좌석 번호를 입력하세요 (예: A5, F15, B22): ");

        try {
            seatService.registerSeat(reservation, input);
            System.out.println("선택된 좌석: " + reservation.getSeat().getSeatNumber());
            System.out.println("좌석 클래스: " + reservation.getSeat().getSeatClass());
            System.out.println("좌석이 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("오류: " + e.getMessage());
        }

        return null;
    }
}
