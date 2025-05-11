package com.aport.app;
import com.aport.service.UserService;
import com.aport.state.UserState;

import java.util.Scanner;

public class APortApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        setup();
        while (true) {
            printHeader();
            UserState state = UserService.getInstance().getState();
            state.handleMenu();
        }
    }

    private static void setup() {
        InputUtil.setScanner(scanner);
    }

    private static void printHeader() {
        System.out.println("\n=== A Port 시스템 ===");
    }
}