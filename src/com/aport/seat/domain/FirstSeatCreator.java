package com.aport.seat.domain;

public class FirstSeatCreator extends SeatCreator {
    @Override
    public Seat createSeat(String seatNumber, int index) {
        return new FirstSeat(seatNumber, index);
    }
}