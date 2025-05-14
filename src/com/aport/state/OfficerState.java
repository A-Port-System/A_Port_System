package com.aport.state;

import com.aport.app.InputUtil;
import com.aport.command.*;
import com.aport.user.*;
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

    @Override
    public void changeState(User user) {
        if (user instanceof Officer) {
            UserService.getInstance().setState(new OfficerState());
        } else if (user instanceof Customer) {
            UserService.getInstance().setState(new CustomerState());
        } else {
            UserService.getInstance().setState(new GuestState());
        }
    }
}