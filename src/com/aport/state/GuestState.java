package com.aport.state;

import com.aport.command.SignupCommand;
import com.aport.command.LoginCommand;

public class GuestState extends AbstractUserState {

    @Override
    public void initializeCommands() {
        commands.put(1, new SignupCommand());
        commands.put(2, new LoginCommand());
    }

    @Override
    protected void displayMenu() {
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
    }

    @Override
    protected boolean isExitChoice(int choice) {
    	return choice == 0;
    }
    
    @Override
    protected void handleExit() {
    	System.out.println("프로그램을 종료합니다.");
    	System.exit(0);
    }
}
