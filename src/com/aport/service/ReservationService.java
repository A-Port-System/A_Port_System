package com.aport.service;

import com.aport.reservation.Reservation;
import com.aport.user.User;
import com.aport.flight.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService extends BaseService {
    private static ReservationService instance = new ReservationService();
    private List<Reservation> reservationList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private ReservationService() {}

    public static ReservationService getInstance() {
        return instance;
    }

    public void createReservation(User user) {
    	if (!validateLogin(user)) return;

        FlightService flightService = FlightService.getInstance();
        flightService.viewFlights();
        System.out.print("예약할 항공편 번호 입력 (1~" + flightService.getFlightCount() + "): ");
        int flightIndex = readIntInput() - 1;

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

        // 티켓 출력 여부 질문
        System.out.print("\n티켓을 출력하시겠습니까? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();

        if (choice.equals("Y")) {
            System.out.print("출력할 예약번호를 입력하세요 (예: RSV1): ");
            String reservationId = scanner.nextLine().trim();
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

        // 결제 여부 확인
        if (!reservation.isPaid()) {
            boolean paymentSuccess = paymentService.processPayment(user, reservation);
            if (!paymentSuccess) return; // 결제 실패 시 종료
        }

        // 결제 완료된 경우 티켓 출력
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

    private int readIntInput() {
        try {
            int num = scanner.nextInt();
            scanner.nextLine();
            return num;
        } catch (Exception e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
            return -1;
        }
    }
}
