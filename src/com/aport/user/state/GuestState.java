package com.aport.user.state;

import com.aport.user.command.ExitCommandFactory;
import com.aport.user.command.LoginCommandFactory;
import com.aport.user.command.SignupCommandFactory;

public class GuestState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new SignupCommandFactory().create());
        commands.put(2, new LoginCommandFactory().create());
        commands.put(0, new ExitCommandFactory().create());
    }

    @Override
    protected void displayMenu() {
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
    }
}
