package com.aport.service;

import com.aport.reservation.Reservation;
import com.aport.user.User;

import java.util.List;
import java.util.Scanner;

public class PaymentService extends BaseService {
    private static PaymentService instance = new PaymentService();
    private Scanner scanner = new Scanner(System.in);

    private PaymentService() {}

    public static PaymentService getInstance() {
        return instance;
    }

    public boolean processPayment(User user, Reservation reservation) {
        if (!validateLogin(user)) return false;

        if (reservation.isPaid()) {
            System.out.println("이미 결제된 예약입니다.");
            return true;
        }

        System.out.print("결제를 진행하시겠습니까? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();

        if (!choice.equals("Y")) {
            System.out.println("결제가 취소되었습니다. 티켓을 출력할 수 없습니다.");
            return false;
        }
        
        while (true) {
            System.out.println("\n=== 결제 옵션 ===");
            System.out.println("1. 카드 등록");
            System.out.println("2. 등록된 카드 보기");
            System.out.println("3. 마일리지 확인");
            System.out.println("4. 마일리지 사용");
            System.out.println("5. 바로 결제하기");
            System.out.println("0. 결제 취소");
            System.out.print("선택: ");
            int option = readIntInput();

            switch (option) {
                case 1:
                	registerPayment(user);
                    break;
                case 2:
                    viewRegisteredCards(user);
                    break;
                case 3:
                    viewMileage(user);
                    break;
                case 4:
                    if (useMileage(user, reservation)) {
                        reservation.setPaid(true);
                        return true;
                    }
                    break;
                case 5:
                    return directPayment(user, reservation);
                case 0:
                    System.out.println("결제가 취소되었습니다.");
                    return false;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void registerPayment(User user) {
        System.out.print("카드 번호를 입력하세요: ");
        String cardNumber = scanner.nextLine().trim();
        user.addCard(cardNumber);
        System.out.println("카드가 성공적으로 등록되었습니다.");
    }

    private void viewRegisteredCards(User user) {
        System.out.println("\n=== 등록된 카드 목록 ===");
        List<String> cards = user.getRegisteredCards();
        if (cards.isEmpty()) {
            System.out.println("등록된 카드가 없습니다.");
        } else {
            for (int i = 0; i < cards.size(); i++) {
                System.out.println((i + 1) + ". " + cards.get(i));
            }
        }
    }

    private void viewMileage(User user) {
        System.out.println("현재 보유 마일리지: " + user.getMileage() + "점");
    }

    private boolean useMileage(User user, Reservation reservation) {
        int mileage = user.getMileage();
        int price = reservation.getFlight().getPrice();

        System.out.println("현재 마일리지: " + mileage + " 포인트");
        System.out.println("항공권 가격: " + price + "원");

        if (mileage >= price) {
            user.setMileage(mileage - price);
            System.out.println("마일리지로 결제가 완료되었습니다!");
            return true;
        } else {
            System.out.println("마일리지가 부족합니다.");
            return false;
        }
    }
    
    private void getMileage(User user, int price) {
        final double MILEAGE_RATE = 0.01;
        int earnedMileage = (int) (price * MILEAGE_RATE);
        int newMileage = user.getMileage() + earnedMileage;
        user.setMileage(newMileage);
        System.out.println("마일리지 " + earnedMileage + "점이 적립되었습니다. 현재 마일리지: " + newMileage + "점");
    }


    private boolean directPayment(User user, Reservation reservation) {
        List<String> cards = user.getRegisteredCards();

        if (cards.isEmpty()) {
            System.out.println("등록된 카드가 없습니다. 먼저 카드를 등록하세요.");
            return false;
        }

        System.out.println("사용할 카드: " + cards.get(0)); // 가장 먼저 등록된 카드 사용
        System.out.print("결제를 진행하시겠습니까? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();

        if (choice.equals("Y")) {
            System.out.println("결제를 진행 중입니다...");
            reservation.setPaid(true);
            System.out.println("결제가 완료되었습니다!");

            // ⭐ 마일리지 적립
            getMileage(user, reservation.getFlight().getPrice());

            return true;
        } else {
            System.out.println("결제가 취소되었습니다.");
            return false;
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
