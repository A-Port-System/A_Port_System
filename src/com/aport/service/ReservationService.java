package com.aport.service;

import com.aport.reservation.Reservation;
import com.aport.user.User;
import com.aport.flight.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private static ReservationService instance = new ReservationService();
    private List<Reservation> reservationList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private ReservationService() {}

    public static ReservationService getInstance() {
        return instance;
    }

    public void createReservation(User user) {
        if (user == null) {
            System.out.println("로그인 후 예약이 가능합니다.");
            return;
        }

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
        if (user == null) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }

        boolean hasReservation = false;
        for (Reservation reservation : reservationList) {
            if (reservation.getUser().getID().equals(user.getID())) {
                System.out.println(reservation.getReservationInfo());
                hasReservation = true;
            }
        }

        if (!hasReservation) {
            System.out.println("예약 내역이 없습니다.");
        }
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
