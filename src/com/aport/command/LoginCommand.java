package com.aport.command;

import com.aport.service.UserService;
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
        String userType = null;
        switch (type) {
            case 1:
                userType = "customer";
                break;
            case 2:
                userType = "officer";
                break;
            case 3:
                userType = "agency";
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                return;
        }

        String id = InputUtil.readLine("아이디(이메일): ");
        String password = InputUtil.readLine("비밀번호: ");

        if (UserService.getInstance().isUser(id, userType)) {
            User user = UserService.getInstance().getUserMap().get(id);
            if (user.getPassword().equals(password)) {
                UserService.getInstance().setCurrentUser(user);
                System.out.println(user.getName() + "님 환영합니다!");
            } else {
                System.out.println("로그인 실패: 비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("로그인 실패: 해당 유형의 사용자가 존재하지 않습니다.");
        }
    }
}
