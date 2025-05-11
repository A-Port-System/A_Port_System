package com.aport.state;

import com.aport.app.InputUtil;
import com.aport.command.Command;
import com.aport.command.CreateFlightCommand;
import com.aport.command.ViewFlightCommand;
import com.aport.command.CancelFlightCommand;
import com.aport.command.ModifyFlightCommand;
import com.aport.command.LogoutCommand;
import com.aport.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class OfficerState implements UserState {
    private final Map<Integer, Command> officerCommands = new HashMap<>();

    public OfficerState() {
        initializeCommands();
    }

    @Override
    public void initializeCommands() {
        officerCommands.put(1, new CreateFlightCommand());
        officerCommands.put(2, new ViewFlightCommand());
        officerCommands.put(3, new CancelFlightCommand());
        officerCommands.put(4, new ModifyFlightCommand());
        officerCommands.put(5, new LogoutCommand());
    }

    @Override
    public Command getCommand(int key) {
        return officerCommands.get(key);
    }

    @Override
    public void handleMenu() {
        System.out.println("1. 항공권 생성");
        System.out.println("2. 항공권 조회");
        System.out.println("3. 항공권 취소");
        System.out.println("4. 항공권 수정");
        System.out.println("5. 로그아웃");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();
        if (choice == 5) {
            System.out.println("로그아웃합니다.");
            UserService.getInstance().setState(new GuestState());
            return;
        }

        Command command = getCommand(choice);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }
}