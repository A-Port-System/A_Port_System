package com.aport.user.command;

import com.aport.app.InputUtil;
import com.aport.common.Command;
import com.aport.user.service.UserService;

public class LoginCommand implements Command {

    @Override
    public void execute() {
        System.out.println("로그인");
        System.out.println("아이디와 비밀번호를 입력하세요.");
        String id = InputUtil.readLine("아이디(이메일): ");
        String password = InputUtil.readLine("비밀번호: ");
        
        if (UserService.getInstance().isUser(id, password)){
            UserService.getInstance().setCurrentUser(UserService.getInstance().getUser(id));
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
