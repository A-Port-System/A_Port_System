package com.aport.seat.domain;

import java.io.Serializable;

public class FirstSeat implements Seat, Serializable {
    private final String seatNumber;
    private int index;

    public FirstSeat(String seatNumber, int index) {
        this.seatNumber = seatNumber;
        this.index = index;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
    public int getIndex() { return index; }

    public String getSeatClass() {
        return "First";
    }
}