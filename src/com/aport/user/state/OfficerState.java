package com.aport.user.state;

import com.aport.common.command.UndoCommandFactory;
import com.aport.flight.command.CancelFlightCommandFactory;
import com.aport.flight.command.CreateFlightCommandFactory;
import com.aport.flight.command.ModifyFlightCommandFactory;
import com.aport.flight.command.PostFlightNoticeCommandFactory;
import com.aport.flight.command.ViewFlightsCommandFactory;
import com.aport.user.command.LogoutCommandFactory;

public class OfficerState extends AbstractUserState {
    @Override
    public void initializeCommands() {
        commands.put(1, new CreateFlightCommandFactory().create());
        commands.put(2, new ViewFlightsCommandFactory().create());
        commands.put(3, new CancelFlightCommandFactory().create());
        commands.put(4, new ModifyFlightCommandFactory().create());
        commands.put(5, new PostFlightNoticeCommandFactory().create());
        commands.put(6, new UndoCommandFactory().create());
        commands.put(7, new LogoutCommandFactory().create());
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