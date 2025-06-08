package com.aport.seat.domain;

public class BusinessSeatCreator extends SeatCreator {
    @Override
    public Seat createSeat(String seatNumber, int index) {
        return new BusinessSeat(seatNumber, index);
    }
}
