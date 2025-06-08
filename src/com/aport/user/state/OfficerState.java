package com.aport.user.state;

import com.aport.common.command.UndoCommand;
import com.aport.flight.command.CancelFlightCommand;
import com.aport.flight.command.CreateFlightCommand;
import com.aport.flight.command.ModifyFlightCommand;
import com.aport.flight.command.PostFlightNoticeCommand;
import com.aport.flight.command.ViewFlightsCommand;
import com.aport.flight.decorator.ViewFlightsDecorator;
import com.aport.user.command.LogoutCommand;
import com.aport.user.decorator.ValidateDecorator;

public class OfficerState extends AbstractUserState {
    @Override
    public void initializeCommands() {
        commands.put(1, new ValidateDecorator(new CreateFlightCommand()));
        commands.put(2, new ViewFlightsCommand());
        commands.put(3, new ValidateDecorator(new ViewFlightsDecorator(new CancelFlightCommand())));
        commands.put(4, new ValidateDecorator(new ViewFlightsDecorator(new ModifyFlightCommand())));
        commands.put(5, new ValidateDecorator(new ViewFlightsDecorator(new PostFlightNoticeCommand())));
        commands.put(6, new ValidateDecorator(new UndoCommand()));
        commands.put(7, new ValidateDecorator(new LogoutCommand()));
    }


    @Override
    protected void displayMenu() {
        System.out.println("1. 항공권 생성");
        System.out.println("2. 항공권 조회");
        System.out.println("3. 항공권 취소");
        System.out.println("4. 항공권 수정");
        System.out.println("5. 공지사항 생성");
        System.out.println("6. 뒤로가기");
        System.out.println("7. 로그아웃");
    }
}