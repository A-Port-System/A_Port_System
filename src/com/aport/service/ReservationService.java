package com.aport.service;

import com.aport.reservation.Reservation;
import com.aport.user.User;
import com.aport.flight.Flight;
import com.aport.app.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class ReservationService extends BaseService {
    private static ReservationService instance;
    private final List<Reservation> reservationList = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public void createReservation(User user) {
        if (!validateLogin(user)) return;

        FlightService flightService = FlightService.getInstance();
        flightService.viewFlights();
        int flightIndex = InputUtil.readInt("예약할 항공편 번호 입력: ") - 1;

        Flight selectedFlight = flightService.selectFlight(flightIndex);
        if (selectedFlight != null) {
            Reservation reservation = new Reservation(user, selectedFlight);
            reservationList.add(reservation);
            System.out.println("예약 성공!");
            System.out.println(reservation.getReservationInfo());
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

    public void viewReservations(User user) {
        if (!validateLogin(user)) return;

        boolean hasReservation = false;

        System.out.println("\n=== 내 예약 목록 ===");
        for (Reservation reservation : reservationList) {
            if (reservation.getUser().getID().equals(user.getID())) {
                System.out.println(reservation.getReservationInfo());
                hasReservation = true;
            }
        }

        if (!hasReservation) {
            System.out.println("예약 내역이 없습니다.");
            return;
        }

        String choice = InputUtil.readLine("\n티켓을 출력하시겠습니까? (Y/N): ").toUpperCase();
        if (choice.equals("Y")) {
            String reservationId = InputUtil.readLine("출력할 예약번호를 입력하세요 (예: RSV1): ");
            selectReservation(user, reservationId);
        } else {
            System.out.println("티켓 출력이 취소되었습니다.");
        }
    }

    private void selectReservation(User user, String reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)
                    && reservation.getUser().getID().equals(user.getID())) {
                receiveTicket(user, reservation);
                return;
            }
        }
        System.out.println("해당 예약번호를 찾을 수 없습니다.");
    }

    private void receiveTicket(User user, Reservation reservation) {
        PaymentService paymentService = PaymentService.getInstance();

        if (!reservation.isPaid()) {
            boolean paymentSuccess = paymentService.processPayment(user, reservation);
            if (!paymentSuccess) return;
        }

        System.out.println("\n===== [전자 항공권] =====");
        System.out.println("예약번호 : " + reservation.getReservationId());
        System.out.println("탑승자명 : " + reservation.getUser().getName());
        System.out.println("항공편   : " + reservation.getFlight().getFlightNumber());
        System.out.println("출발지   : " + reservation.getFlight().getDeparture());
        System.out.println("도착지   : " + reservation.getFlight().getArrival());
        System.out.println("출발시간 : " + reservation.getFlight().getDepartureTime());
        System.out.println("도착시간 : " + reservation.getFlight().getArrivalTime());
        System.out.println("가격     : " + reservation.getFlight().getPrice() + "원");
        System.out.println("======================\n");
    }
}
