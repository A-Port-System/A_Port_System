package com.aport.command;

import com.aport.service.UserService;
import com.aport.state.GuestState;

public class LogoutCommand implements Command {

    @Override
    public void execute() {
        if (UserService.getInstance().getState() instanceof GuestState) {
            System.out.println("현재 로그인 상태가 아닙니다.");
        } else {
            UserService.getInstance().setCurrentUser(null);
            UserService.getInstance().setState(new GuestState());
            System.out.println("로그아웃 되었습니다.");
        }
    }
}