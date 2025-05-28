package com.aport.service;

import com.aport.reservation.Reservation;
import com.aport.user.User;

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

    public void createReservation(Reservation reservation) {
        reservationList.add(reservation);
        System.out.println("예약 성공!");
        System.out.println(reservation.getReservationInfo());
    }

    public List<Reservation> getReservationsForUser(User user) {
        if (!validateLogin(user)) return new ArrayList<>();

        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if (reservation.getUser().getId().equals(user.getId())) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    public List<Reservation> getReservations(User user) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if (reservation.getUser().getId().equals(user.getId())) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    public Reservation getReservationById(String reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }

    public boolean cancelReservation(Reservation reservation) {
        return reservationList.removeIf(r -> r.getReservationId().equals(reservation.getReservationId()));
    }
}
