package com.aport.user.state;

import com.aport.common.command.UndoCommand;
import com.aport.flight.command.SearchFlightsCommand;
import com.aport.flight.command.ViewFlightsCommand;
import com.aport.payment.command.PaymentCommand;
import com.aport.reservation.command.CancelReservationCommand;
import com.aport.reservation.command.CreateReservationCommand;
import com.aport.reservation.command.ModifyReservationCommand;
import com.aport.reservation.command.ViewReservationsCommand;
import com.aport.user.command.LogoutCommand;
import com.aport.user.decorator.ValidateDecorator;
import com.aport.user.service.UserService;
import com.aport.flight.decorator.ViewFlightNoticesDecorator;
import com.aport.reservation.decorator.ViewReservationsDecorator;
import com.aport.seat.command.SeatCommand;

public class CustomerState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new ViewFlightsCommand());
        commands.put(2, new SearchFlightsCommand());
        commands.put(3, new ValidateDecorator(new CreateReservationCommand()));
        commands.put(4, new ValidateDecorator(new ViewReservationsDecorator(new ModifyReservationCommand())));
        commands.put(5, new ValidateDecorator(new ViewReservationsDecorator(new CancelReservationCommand())));
        commands.put(6, new ValidateDecorator(new ViewFlightNoticesDecorator(new ViewReservationsCommand())));
        commands.put(7, new ValidateDecorator(new ViewReservationsDecorator(new PaymentCommand())));
        commands.put(8, new ValidateDecorator(new ViewReservationsDecorator(new SeatCommand())));
        commands.put(9, new ValidateDecorator(new UndoCommand()));
        commands.put(10, new ValidateDecorator(new LogoutCommand()));
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

    @Override
    protected boolean isExitChoice(int choice) {
        return choice == 9;
    }

    @Override
    protected void handleExit() {
        System.out.println("로그아웃합니다.");
        UserService.getInstance().setState(new GuestState());
    }
}