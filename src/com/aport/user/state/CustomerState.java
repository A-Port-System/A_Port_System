package com.aport.user.state;

import com.aport.common.command.UndoCommandFactory;
import com.aport.flight.command.SearchFlightsCommandFactory;
import com.aport.flight.command.ViewFlightsCommandFactory;
import com.aport.payment.command.PaymentCommandFactory;
import com.aport.reservation.command.CancelReservationCommandFactory;
import com.aport.reservation.command.CreateReservationCommandFactory;
import com.aport.reservation.command.ModifyReservationCommandFactory;
import com.aport.reservation.command.ViewReservationsCommandFactory;
import com.aport.seat.command.SeatCommandFactory;
import com.aport.user.command.LogoutCommandFactory;


public class CustomerState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new ViewFlightsCommandFactory().create());
        commands.put(2, new SearchFlightsCommandFactory().create());
        commands.put(3, new CreateReservationCommandFactory().create());
        commands.put(4, new ModifyReservationCommandFactory().create());
        commands.put(5, new CancelReservationCommandFactory().create());
        commands.put(6, new ViewReservationsCommandFactory().create());
        commands.put(7, new PaymentCommandFactory().create());
        commands.put(8, new SeatCommandFactory().create());
        commands.put(9, new UndoCommandFactory().create());
        commands.put(10, new LogoutCommandFactory().create());
    }


    @Override
    public void displayMenu() {
        System.out.println("1. 항공편 조회");
        System.out.println("2. 항공편 검색");
        System.out.println("3. 예약 생성");
        System.out.println("4. 예약 수정");
        System.out.println("5. 예약 삭제");
        System.out.println("6. 내 예약 조회");
        System.out.println("7. 티켓 발권");
        System.out.println("8. 자리 선택");
        System.out.println("9. 뒤로가기");
        System.out.println("10. 로그아웃");
    }
}
