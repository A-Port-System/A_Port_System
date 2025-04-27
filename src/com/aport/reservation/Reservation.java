package com.aport.reservation;

import com.aport.user.User;
import com.aport.flight.Flight;
import java.time.LocalDateTime;

public class Reservation {
    private static int reservationCounter = 1;

    private String reservationId;
    private User user;
    private Flight flight;
    private LocalDateTime reservationDate;

    public Reservation(User user, Flight flight) {
        this.user = user;
        this.flight = flight;
        this.reservationDate = LocalDateTime.now();
        this.reservationId = "RSV" + reservationCounter++;
    }
    
    public String getReservationInfo() {
        return String.format("예약번호: %s, 사용자: %s, 항공편: %s, 예약일자: %s",
            getReservationId(), user.getName(), flight.getFlightNumber(), reservationDate.toString());
    }
    
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}
