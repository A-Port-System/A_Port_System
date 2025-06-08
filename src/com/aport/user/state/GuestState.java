package com.aport.user.state;

import com.aport.user.command.ExitCommand;
import com.aport.user.command.LoginCommand;
import com.aport.user.command.SignupCommand;

public class GuestState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new SignupCommand());
        commands.put(2, new LoginCommand());
        commands.put(0, new ExitCommand());
    }

    @Override
    protected void displayMenu() {
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
    }
}
