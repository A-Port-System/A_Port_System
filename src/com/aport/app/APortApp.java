package com.aport.app;

import com.aport.command.*;
import com.aport.context.CommandContext;
import com.aport.command.Invoker;
import com.aport.service.UserService;

import java.util.Scanner;

public class APortApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static CommandContext context;
    private static Invoker invoker;

    public static void run() {
        setup();

        while (true) {
            printHeader();

            if (context.isGuest()) {
                handleGuestMenu();
            } else {
                String userType = UserService.getInstance().getCurrentUser().getClass().getSimpleName();
                switch (userType) {
                    case "Customer":
                        handleCustomerMenu();
                        break;
                    case "Officer":
                        handleOfficerMenu();
                        break;
                    case "Agency":
                        System.out.println("미구현된 기능입니다.");
                        break;
                    default:
                        System.out.println("알 수 없는 사용자 유형입니다.");
                        break;
                }
            }
        }
    }

    private static void setup() {
        context = new CommandContext(); // 수정된 부분
        invoker = new Invoker();
        
        InputUtil.setScanner(scanner);
    }

    private static void printHeader() {
        System.out.println("\n=== A Port 시스템 ===");
    }

    private static void handleGuestMenu() {
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();
        if (choice == 0) System.exit(0);

        Command command = context.getGuestCommand(choice);
        runCommand(command);
    }

    private static void handleCustomerMenu() {
        System.out.println("1. 항공편 검색/선택");
        System.out.println("2. 예약하기");
        System.out.println("3. 내 예약 조회");
        System.err.println("4. 티켓 발급");
        System.out.println("5. 로그아웃");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();
        Command command = context.getCustomerCommand(choice);
        runCommand(command);
    }

    private static void handleOfficerMenu() {
        System.out.println("1. 항공권 생성");
        System.out.println("2. 항공권 조회");
        System.out.println("3. 항공권 취소");
        System.out.println("4. 항공권 수정");
        System.out.println("5. 로그아웃");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();
        Command command = context.getOfficerCommand(choice);
        runCommand(command);
    }
    
    private static void runCommand(Command command) {
        if (command != null) {
            invoker.setCommand(command);
            invoker.run();
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }
}