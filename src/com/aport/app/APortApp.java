package com.aport.app;

import com.aport.service.UserService;
import com.aport.service.FlightService;
import com.aport.service.ReservationService;

import java.util.Scanner;

public class APortApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void run() {
        UserService userService = UserService.getInstance();
        ReservationService reservationService = ReservationService.getInstance();

        while (true) {
            System.out.println("\n=== A Port 시스템 ===");
            if (userService.getCurrentUser() == null) {
                System.out.println("1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("0. 종료");
                System.out.print("선택: ");
                int choice = readIntInput();

                switch (choice) {
                    case 1:
                        userService.signUp();
                        break;
                    case 2:
                        userService.login();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            } else {
            	System.out.println("1. 항공편 검색/선택");
            	System.out.println("2. 예약하기");
            	System.out.println("3. 내 예약 조회"); // 추가
            	System.out.println("4. 로그아웃");
            	System.out.print("선택: ");
            	int choice = readIntInput();

            	switch (choice) {
            	    case 1:
            	        FlightService.getInstance().listFlights();
            	        break;
            	    case 2:
            	        reservationService.createReservation(userService.getCurrentUser());
            	        break;
            	    case 3:
            	        reservationService.viewMyReservations(userService.getCurrentUser());
            	        break;
            	    case 4:
            	        userService.logout();
            	        break;
            	    default:
            	        System.out.println("잘못된 입력입니다.");
            	        break;
            	}
            }
        }
    }

    private static int readIntInput() {
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
