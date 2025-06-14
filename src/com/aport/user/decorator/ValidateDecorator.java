package com.aport.user.decorator;

import com.aport.common.Decorator;
import com.aport.common.Invoker;
import com.aport.common.command.Command;
import com.aport.user.command.LoginCommand;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;

public class ValidateDecorator extends Decorator {
    public ValidateDecorator(Command command) {
        super(command);
    }

    @Override
    public Object execute() {
        if (validate()) {
            return super.execute();
        } else {
            Invoker invoker = Invoker.getInstance();
            invoker.setCommand(new LoginCommand());
            invoker.run();
            return null;
        }
    }

    public boolean validate() {
        User currentUser = UserService.getInstance().getCurrentUser();
        if (currentUser == null) {
            System.out.println("[오류] 현재 사용자가 없습니다. 로그인 후 다시 시도하세요.");
            return false;
        }
        return true;
    }
}

