package com.aport.command;

import com.aport.service.UserService;

import com.aport.app.InputUtil;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

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
        }

        if (userType != null) {
            userService.login(userType);
        }
    }

    
}
