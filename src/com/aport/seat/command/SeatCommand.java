package com.aport.seat.command;

import java.util.List;
import java.util.stream.Collectors;

import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.flight.domain.Flight;
import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.seat.domain.Seat;
import com.aport.seat.factory.SeatFactory;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

public class SeatCommand implements Command {

    @Override
    public Object execute() {
        User user = UserService.getInstance().getCurrentUser();
        List<Reservation> reservations = ReservationService.getInstance().getReservations(user);

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

        if (reservation.getSeat() != null) {
            System.out.println("이미 좌석이 지정된 예약입니다: " + reservation.getSeat().getSeatNumber());
            return null;
        }

        printSeatMap(reservation.getFlight());
        String input = InputUtil.readLine("좌석 번호를 입력하세요 (예: A5, F15, B22): ");
        try {
        	Seat seat = SeatFactory.createSeat(input);

        	int index = seat.getIndex();
        	if (reservation.getFlight().isSeatTaken(index)) {
        	    System.out.println("이미 예약된 좌석입니다.");
        	    return null;
        	}

        	// 좌석 등록
        	reservation.setSeat(seat);
        	reservation.getFlight().reserveSeat(index);

            System.out.println("선택된 좌석: " + seat.getSeatNumber());
            System.out.println("좌석 클래스: " + seat.getSeatClass());
            System.out.println("좌석이 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("좌석 생성 오류: " + e.getMessage());
        }

        return null;
    }
    
    private void printSeatMap(Flight flight) {
        boolean[] seats = flight.getSeats();
        String[] rows = {"A", "B", "C", "D", "E", "F"};

        System.out.println("===== 퍼스트 클래스 (1 ~ 10) =====");
        for (int i = 1; i <= 10; i++) printRow(seats, rows, i);

        System.out.println("===== 비즈니스 클래스 (11 ~ 20) =====");
        for (int i = 11; i <= 20; i++) printRow(seats, rows, i);

        System.out.println("===== 이코노미 클래스 (21 ~ 30) =====");
        for (int i = 21; i <= 30; i++) printRow(seats, rows, i);
    }

    private void printRow(boolean[] seats, String[] rows, int col) {
        for (int r = 0; r < rows.length; r++) {
            String seatNo = rows[r] + col;
            int index = r * 30 + (col - 1);
            boolean taken = index < seats.length && seats[index];
            System.out.print((taken ? "[✗] " : "[✓] ") + seatNo + "\t");
        }
        System.out.println();
    }



}
