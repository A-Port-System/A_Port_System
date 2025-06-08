package com.aport.user.command;

import com.aport.common.Invoker;
import com.aport.common.command.Command;
import com.aport.user.service.UserService;
import com.aport.user.state.GuestState;

public class LogoutCommand implements Command {

    @Override
    public Object execute() {
        if (UserService.getInstance().getState() instanceof GuestState) {
            System.out.println("현재 로그인 상태가 아닙니다.");
        } else {
            UserService.getInstance().setCurrentUser(null);
            UserService.getInstance().setState(new GuestState());
            System.out.println("로그아웃 되었습니다.");
            Invoker.getInstance().clearHistory();
        }
        return null;
    }
}