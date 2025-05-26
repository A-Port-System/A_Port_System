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

        String id = InputUtil.readLine("아이디(이메일): ");
        if (userMap.containsKey(id)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }
        String password = InputUtil.readLine("비밀번호: ");
        String name = InputUtil.readLine("이름: ");
        String email = InputUtil.readLine("이메일: ");
        String phoneNumber = InputUtil.readLine("전화번호: ");
        String address = InputUtil.readLine("주소: ");
        String birthDate = InputUtil.readLine("생년월일: ");
        String gender = InputUtil.readLine("성별: ");
        String nationality = InputUtil.readLine("국적: ");
        boolean isVip = InputUtil.readLine("VIP 여부(Y/N): ").equalsIgnoreCase("Y");

        User user = new Customer.Builder()
            .username(id)
            .password(password)
            .name(name)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .birthDate(birthDate)
            .gender(gender)
            .nationality(nationality)
            .isVip(isVip)
            .build();
        userMap.put(id, user);
        System.out.println("회원가입 완료!");
    }
}