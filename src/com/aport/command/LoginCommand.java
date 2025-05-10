package com.aport.command;

import com.aport.service.UserService;

import java.util.Scanner;

public class LoginCommand implements Command {
    private final UserService userService;
    private final Scanner scanner;

    public LoginCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("[회원 유형 선택]");
        System.out.println("1. 고객");
        System.out.println("2. 직원");
        System.out.println("3. 대행사");
        System.out.print("선택: ");

        int type = readIntInput();
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

    private int readIntInput() {
        try {
            int num = scanner.nextInt();
            scanner.nextLine();
            return num;
        } catch (Exception e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
            return -1;
        }
    }
}
