package com.aport.strategy.signup;

import java.util.Scanner;
import com.aport.user.Agency;
import com.aport.user.User;
import java.util.Map;

public class AgencySignupStrategy implements SignupStrategy {
    private Map<String, User> userMap;
    private Scanner scanner;

    public AgencySignupStrategy(Map<String, User> userMap, Scanner scanner) {
        this.userMap = userMap;
        this.scanner = scanner;
    }

    public void signUp() {
        System.out.print("아이디(이메일): ");
        String ID = scanner.nextLine();
        if (userMap.containsKey(ID)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();

        User user = new Agency(ID, password, name);
        userMap.put(ID, user);
        System.out.println("회원가입 완료!");
    }
}