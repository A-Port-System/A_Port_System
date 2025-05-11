package com.aport.command;

import com.aport.service.UserService;
import com.aport.strategy.signup.*;
import com.aport.app.InputUtil;

public class SignupCommand implements Command {
    private final UserService userService;

    public SignupCommand(UserService userService) {
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
        switch (type) {
            case 1:
                userService.setSignupStrategy(new CustomerSignupStrategy(userService.getUserMap()));
                break;
            case 2:
                userService.setSignupStrategy(new OfficerSignupStrategy(userService.getUserMap()));
                break;
            case 3:
                userService.setSignupStrategy(new AgencySignupStrategy(userService.getUserMap()));
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                return;
        }

        userService.signUp();
    }
}
