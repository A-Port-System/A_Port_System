package com.aport.state;

import com.aport.command.*;
import com.aport.service.UserService;


public class CustomerState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new ViewFlightsCommand());
        commands.put(2, new SearchFlightsCommand());
        commands.put(3, new CreateReservationCommand());
        commands.put(4, new ModifyReservationCommand());
        commands.put(5, new CancelReservationCommand());
        commands.put(6, new ViewReservationsCommand());
        commands.put(7, new PaymentCommand());
        commands.put(8, new LogoutCommand());
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
        System.out.println("8. 로그아웃");
    }

    @Override
    protected boolean isExitChoice(int choice) {
        return choice == 8;
    }

    @Override
    protected void handleExit() {
        System.out.println("로그아웃합니다.");
        UserService.getInstance().setState(new GuestState());
    }
}