package com.aport.user.command;

import com.aport.user.service.UserService;
import com.aport.user.strategy.AgencySignupStrategy;
import com.aport.user.strategy.CustomerSignupStrategy;
import com.aport.user.strategy.OfficerSignupStrategy;
import com.aport.app.InputUtil;
import com.aport.common.command.Command;

public class SignupCommand implements Command {

    @Override
    public Object execute() {
        System.out.println("[회원 유형 선택]");
        System.out.println("1. 고객");
        System.out.println("2. 직원");
        System.out.println("3. 대행사");
        System.out.print("선택: ");

        int type = InputUtil.readInt();
        switch (type) {
            case 1:
                UserService.getInstance().setSignupStrategy(new CustomerSignupStrategy());
                break;
            case 2:
                UserService.getInstance().setSignupStrategy(new OfficerSignupStrategy());
                break;
            case 3:
                UserService.getInstance().setSignupStrategy(new AgencySignupStrategy());
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                return null;
        }

        UserService.getInstance().addUser();
        return null;
    }
}
