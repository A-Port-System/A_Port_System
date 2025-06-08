package com.aport.seat.domain;

public class EconomySeat implements Seat {
    private final String seatNumber;
    private final int index;

    public EconomySeat(String seatNumber, int index) {
        this.seatNumber = seatNumber;
        this.index = index;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return "Economy";
    }

	public int getIndex() {
		return index;
	}
}