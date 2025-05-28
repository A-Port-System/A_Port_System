package com.aport.state;


import com.aport.command.ViewFlightsCommand;
import com.aport.command.CreateReservationCommand;
import com.aport.command.ViewReservationsCommand;
import com.aport.service.UserService;
import com.aport.command.LogoutCommand;
import com.aport.command.PaymentCommand;


public class CustomerState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new ViewFlightsCommand());
        commands.put(2, new CreateReservationCommand());
        commands.put(3, new ViewReservationsCommand());
        commands.put(4, new PaymentCommand());
        commands.put(5, new LogoutCommand());
    }


    @Override
    public void displayMenu() {
        System.out.println("1. 항공편 검색/선택");
        System.out.println("2. 예약하기");
        System.out.println("3. 내 예약 조회");
        System.out.println("4. 티켓 발권");
        System.out.println("5. 로그아웃");
    }

    @Override
    protected boolean isExitChoice(int choice) {
        return choice == 5;
    }

    @Override
    protected void handleExit() {
        System.out.println("로그아웃합니다.");
        UserService.getInstance().setState(new GuestState());
    }
}