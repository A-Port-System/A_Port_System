package com.aport.seat.domain;

public class EconomySeatCreator extends SeatCreator {
    @Override
    public Seat createSeat(String seatNumber, int index) {
        return new EconomySeat(seatNumber, index);
    }
}