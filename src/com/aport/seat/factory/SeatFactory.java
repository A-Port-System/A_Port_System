package com.aport.seat.factory;

import java.util.HashMap;
import java.util.Map;

import com.aport.seat.domain.BusinessSeat;
import com.aport.seat.domain.BusinessSeatCreator;
import com.aport.seat.domain.EconomySeat;
import com.aport.seat.domain.EconomySeatCreator;
import com.aport.seat.domain.FirstSeat;
import com.aport.seat.domain.FirstSeatCreator;
import com.aport.seat.domain.Seat;
import com.aport.seat.domain.SeatCreator;

public class SeatFactory {
	private static final Map<String, SeatCreator> creators = new HashMap<>();

    static {
        creators.put("FIRST", new FirstSeatCreator());
        creators.put("BUSINESS", new BusinessSeatCreator());
        creators.put("ECONOMY", new EconomySeatCreator());
    }

    public static Seat createSeat(String seatNumber) {
        int index = getSeatIndex(seatNumber);
        int number = Integer.parseInt(seatNumber.substring(1));

        SeatCreator creator;
        if (number >= 1 && number <= 10) {
            creator = creators.get("FIRST");
        } else if (number >= 11 && number <= 20) {
            creator = creators.get("BUSINESS");
        } else if (number >= 21 && number <= 30) {
            creator = creators.get("ECONOMY");
        } else {
            throw new IllegalArgumentException("좌석 번호는 1~30번까지만 유효합니다.");
        }

        return creator.createSeat(seatNumber, index);
    }

    public static int getSeatIndex(String seatNumber) {
        char row = seatNumber.charAt(0); // A~F
        int col = Integer.parseInt(seatNumber.substring(1)); // 1~30
        int rowIndex = row - 'A'; // A=0, B=1, ..., F=5
        return rowIndex * 30 + (col - 1); // 인덱스 계산
    }
}

