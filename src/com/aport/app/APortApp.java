package com.aport.app;

import com.aport.command.*;
import com.aport.context.CommandContext;
import com.aport.command.Invoker;

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
                handleUserMenu();
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

    private static void handleUserMenu() {
        System.out.println("1. 항공편 검색/선택");
        System.out.println("2. 예약하기");
        System.out.println("3. 내 예약 조회");
        System.out.println("4. 로그아웃");
        System.out.print("선택: ");

        int choice = InputUtil.readInt();
        Command command = context.getUserCommand(choice);
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