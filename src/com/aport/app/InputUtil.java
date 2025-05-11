package com.aport.app;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner;

    public static void setScanner(Scanner sc) {
        scanner = sc;
    }

    public static int readInt() {
        try {
            int num = scanner.nextInt();
            scanner.nextLine(); // 개행 제거
            return num;
        } catch (Exception e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
            return -1;
        }
    }
    
    public static int readInt(String prompt) {
        try {
            int num = scanner.nextInt();
            scanner.nextLine(); // 개행 제거
            return num;
        } catch (Exception e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
            return -1;
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