package com.aport.app;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner;

    public static void setScanner(Scanner sc) {
        scanner = sc;
    }

    public static int readInt() {
        while (true) {
            try {
                int num = scanner.nextInt();
                scanner.nextLine(); // 개행 제거
                return num;
            } catch (Exception e) {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }
    }
    
    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                int num = scanner.nextInt();
                scanner.nextLine(); // 개행 제거
                return num;
            } catch (Exception e) {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.nextLine(); // 잘못된 입력 제거
            }
        }
    }
    
    public static String readLine() {
        return scanner.nextLine().trim();
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}