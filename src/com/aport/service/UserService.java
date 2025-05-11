package com.aport.service;

import com.aport.user.*;
import com.aport.strategy.signup.SignupStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService extends BaseService {
    private static UserService instance = new UserService();
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;
    private Scanner scanner = new Scanner(System.in);

    private SignupStrategy signupStrategy;

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public void setSignupStrategy(SignupStrategy signupStrategy) {
        this.signupStrategy = signupStrategy;
    }

    public void login(String userType) {
        System.out.print("아이디(이메일): ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        User user = userMap.get(id);
        if (user != null && user.getPassword().equals(password) && matchUserType(userType, user)) {
            currentUser = user;
            System.out.println(user.getName() + "님 환영합니다!");
        } else {
            System.out.println("로그인 실패: 아이디나 비밀번호를 확인하세요.");
        }
    }

    private boolean matchUserType(String type, User user) {
        switch (type) {
            case "customer":
                return user instanceof com.aport.user.Customer;
            case "officer":
                return user instanceof com.aport.user.Officer;
            case "agency":
                return user instanceof com.aport.user.Agency;
            default:
                return false;
        }
    }

    public void signUp() {
        if (signupStrategy != null) signupStrategy.signUp();
        else System.out.println("Signup 전략이 설정되지 않았습니다.");
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void logout() {
        currentUser = null;
        System.out.println("로그아웃 완료.");
    }
}
