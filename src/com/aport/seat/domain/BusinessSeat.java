package com.aport.seat.domain;

public class BusinessSeat implements Seat {
    private final String seatNumber;
    private final int index;

    public BusinessSeat(String seatNumber, int index) {
        this.seatNumber = seatNumber;
        this.index = index;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return "Business";
    }

	public int getIndex() {
		return index;
	}
}