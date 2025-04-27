package com.aport.service;

import com.aport.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private static UserService instance = new UserService();
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;
    private Scanner scanner = new Scanner(System.in);

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public void signUp() {
        String ID;
        while (true) {
            System.out.print("아이디(이메일): ");
            ID = scanner.nextLine();
            if (!isValidEmail(ID)) {
                System.out.println("잘못된 이메일 형식입니다. 다시 입력해주세요.");
            } else {
                break;
            }
        }

        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();

        if (!validateUser(ID, null)) {
            User user = new User(ID, password, name);
            userMap.put(user.getID(), user);
            System.out.println("회원가입 완료!");
        } else {
            System.out.println("이미 존재하는 아이디입니다. 다시 시도해주세요.");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public void login() {
        System.out.print("아이디(이메일): ");
        String ID = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        if (validateUser(ID, password)) {
            currentUser = userMap.get(ID);
            System.out.println(currentUser.getName() + "님 환영합니다!");
        } else {
            System.out.println("로그인 실패: 아이디나 비밀번호를 확인하세요.");
        }
    }

    public void logout() {
        currentUser = null;
        System.out.println("로그아웃 완료.");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private boolean validateUser(String ID, String password) {
        User user = userMap.get(ID);
        if (password == null) {
            return user != null;
        } else {
            return user != null && user.getPassword().equals(password);
        }
    }
}
