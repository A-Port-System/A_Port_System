package com.aport.state;

import com.aport.command.*;
import com.aport.service.UserService;

public class OfficerState extends AbstractUserState {
    @Override
    public void initializeCommands() {
        commands.put(1, new CreateFlightCommand());
        commands.put(2, new ViewFlightsCommand());
        commands.put(3, new CancelFlightCommand());
        commands.put(4, new ModifyFlightCommand());
        commands.put(5, new LogoutCommand());
    }


    @Override
    protected void displayMenu() {
        System.out.println("1. 항공권 생성");
        System.out.println("2. 항공권 조회");
        System.out.println("3. 항공권 취소");
        System.out.println("4. 항공권 수정");
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