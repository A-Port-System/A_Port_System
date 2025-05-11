package com.aport.state;

import com.aport.app.InputUtil;
import com.aport.command.Command;
import com.aport.command.SignupCommand;
import com.aport.command.LoginCommand;

import java.util.HashMap;
import java.util.Map;

public class GuestState implements UserState {
    private final Map<Integer, Command> commands = new HashMap<>();

    public GuestState() {
        initializeCommands();
    }

    @Override
    public void initializeCommands() {
        commands.put(1, new SignupCommand());
        commands.put(2, new LoginCommand());
    }

    @Override
    public Command getCommand(int key) {
        return commands.get(key);
    }

    @Override
    public void handleMenu() {
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();
        if (choice == 0) {
            System.exit(0);
        }

        Command command = getCommand(choice);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }
}
