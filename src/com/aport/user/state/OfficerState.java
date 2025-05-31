package com.aport.user.state;

import com.aport.flight.command.CancelFlightCommand;
import com.aport.flight.command.CreateFlightCommand;
import com.aport.flight.command.ModifyFlightCommand;
import com.aport.flight.command.ViewFlightsCommand;
import com.aport.common.command.UndoCommand;
import com.aport.user.command.LogoutCommand;
import com.aport.user.service.UserService;

public class OfficerState extends AbstractUserState {
    @Override
    public void initializeCommands() {
        commands.put(1, new CreateFlightCommand());
        commands.put(2, new ViewFlightsCommand());
        commands.put(3, new CancelFlightCommand());
        commands.put(4, new ModifyFlightCommand());
        commands.put(5, new UndoCommand());
        commands.put(6, new LogoutCommand());
    }


    @Override
    protected void displayMenu() {
        System.out.println("1. 항공권 생성");
        System.out.println("2. 항공권 조회");
        System.out.println("3. 항공권 취소");
        System.out.println("4. 항공권 수정");
        System.out.println("5. 뒤로가기기");
        System.out.println("6. 로그아웃");
    }

    @Override
    protected boolean isExitChoice(int choice) {
    	return choice == 6;
    }
    
    @Override
    protected void handleExit() {
    	System.out.println("로그아웃합니다.");
    	UserService.getInstance().setState(new GuestState());
    }
}