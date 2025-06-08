package com.aport.seat.factory;

import com.aport.seat.domain.BusinessSeat;
import com.aport.seat.domain.EconomySeat;
import com.aport.seat.domain.FirstSeat;
import com.aport.seat.domain.Seat;

public class SeatFactory {
    public static Seat createSeat(String seatNumber) {
        int index = getSeatIndex(seatNumber); 

        int number = Integer.parseInt(seatNumber.substring(1));
        if (number >= 1 && number <= 10) {
            return new FirstSeat(seatNumber, index);
        } else if (number >= 11 && number <= 20) {
            return new BusinessSeat(seatNumber, index);
        } else if (number >= 21 && number <= 30) {
            return new EconomySeat(seatNumber, index);
        } else {
            throw new IllegalArgumentException("좌석 번호는 1~30번까지만 유효합니다.");
        }
    }

    public static int getSeatIndex(String seatNumber) {
        char row = seatNumber.charAt(0); // A~F
        int col = Integer.parseInt(seatNumber.substring(1)); // 1~30
        int rowIndex = row - 'A'; // A=0, B=1, ..., F=5
        return rowIndex * 30 + (col - 1); // 인덱스 계산
    }
}

