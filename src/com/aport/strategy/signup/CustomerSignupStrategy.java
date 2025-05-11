package com.aport.strategy.signup;

import com.aport.app.InputUtil;
import com.aport.service.UserService;
import com.aport.user.Customer;
import com.aport.user.User;
import java.util.Map;

public class CustomerSignupStrategy implements SignupStrategy {

    @Override
    public void signUp() {
        Map<String, User> userMap = UserService.getInstance().getUserMap();

        String ID = InputUtil.readLine("아이디(이메일): ");
        if (userMap.containsKey(ID)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }
        String password = InputUtil.readLine("비밀번호: ");
        String name = InputUtil.readLine("이름: ");

        User user = new Customer(ID, password, name);
        userMap.put(ID, user);
        System.out.println("회원가입 완료!");
    }
}