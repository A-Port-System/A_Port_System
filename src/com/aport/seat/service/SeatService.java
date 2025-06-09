package com.aport.seat.service;

import com.aport.flight.domain.Flight;
import com.aport.reservation.domain.Reservation;
import com.aport.seat.domain.Seat;
import com.aport.seat.factory.SeatFactory;
import com.aport.user.domain.User;

import java.util.List;

public class SeatService {

    public List<Reservation> getUserReservations(User user) {
        return com.aport.reservation.service.ReservationService.getInstance().getReservations(user);
    }

    public Reservation selectReservation(List<Reservation> reservations, int selectedIndex) {
        if (selectedIndex < 1 || selectedIndex > reservations.size()) {
            throw new IllegalArgumentException("잘못된 예약 번호입니다.");
        }
        return reservations.get(selectedIndex - 1);
    }

    public boolean hasSeat(Reservation reservation) {
        return reservation.getSeat() != null;
    }

    public void registerSeat(Reservation reservation, String seatInput) {
        Seat seat = SeatFactory.createSeat(seatInput);
        int index = seat.getIndex();

        if (reservation.getFlight().isSeatTaken(index)) {
            throw new IllegalStateException("이미 예약된 좌석입니다.");
        }

        reservation.setSeat(seat);
        reservation.getFlight().reserveSeat(index);
    }

    public void printSeatMap(Flight flight) {
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
