package com.aport.command;

import com.aport.service.UserService;

public class LogoutCommand implements Command {

    @Override
    public void execute() {
        if (UserService.getInstance().isLogined()) {
            UserService.getInstance().setCurrentUser(null);
            System.out.println("로그아웃 되었습니다.");
        } else {
            System.out.println("현재 로그인 상태가 아닙니다.");
        }
    }
}