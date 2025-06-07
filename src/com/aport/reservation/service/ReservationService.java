package com.aport.reservation.service;

import com.aport.file.service.FileService;
import com.aport.reservation.domain.Reservation;
import com.aport.user.domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements Serializable {
    private static ReservationService instance;
    private final List<Reservation> reservationList = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public static void setInstance(ReservationService reservationService) {
        instance = reservationService;
    }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
        FileService fileService = FileService.getInstance();
        fileService.save();
    }

    public List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservations.add(reservation);
        }
        return reservations;
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

    public Reservation getReservation(String reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }

    public boolean removeReservation(Reservation reservation) {
        boolean removed = reservationList.removeIf(r -> r.getReservationId().equals(reservation.getReservationId()));
        if (removed) {
            FileService fileService = FileService.getInstance();
            fileService.save();
        }
        return removed;
    }
}
