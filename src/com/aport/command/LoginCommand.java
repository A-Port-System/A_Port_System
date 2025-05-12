package com.aport.command;

import com.aport.service.UserService;
import com.aport.state.*;
import com.aport.app.InputUtil;
import com.aport.user.User;

public class LoginCommand implements Command {

    @Override
    public void execute() {
        System.out.println("[회원 유형 선택]");
        System.out.println("1. 고객");
        System.out.println("2. 직원");
        System.out.println("3. 대행사");
        System.out.print("선택: ");

        int type = InputUtil.readInt();

        if(type < 1 || type > 3) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        String id = InputUtil.readLine("아이디(이메일): ");
        String password = InputUtil.readLine("비밀번호: ");

        login(id, password);

        switch(type) {
            case 1: UserService.getInstance().setState(new CustomerState()); break;
            case 2: UserService.getInstance().setState(new OfficerState()); break;
            case 3: System.out.println("미구현"); break;
        }
        
    }

    private void login(String id, String password) {
        User user = UserService.getInstance().getUserMap().get(id);
        if (user != null && user.getPassword().equals(password)) {
            UserService.getInstance().setCurrentUser(user);
            System.out.println("로그인 성공!");
        } else {
            System.out.println("로그인 실패! 아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
