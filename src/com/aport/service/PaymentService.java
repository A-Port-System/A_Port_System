package com.aport.service;

import com.aport.reservation.Reservation;
import com.aport.user.User;
import com.aport.app.InputUtil;

import java.util.List;

public class PaymentService extends BaseService {
    private static PaymentService instance;

    private PaymentService() {}

    public static PaymentService getInstance() {
        if (instance == null) {
            instance = new PaymentService();
        }
        return instance;
    }

    public boolean processPayment(User user, Reservation reservation) {
        List<String> cards = user.getRegisteredCards();

        // 1. 카드가 없으면 바로 등록
        if (cards.isEmpty()) {
            System.out.println("등록된 카드가 없습니다. 카드를 먼저 등록합니다.");
            String cardNumber = InputUtil.readLine("카드 번호 입력: ");
            user.addCard(cardNumber);
            cards = user.getRegisteredCards();
        }

        // 2. 카드 선택 또는 추가 등록
        String selectedCard = null;
        while (true) {
            System.out.println("등록된 카드 목록:");
            for (int i = 0; i < cards.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, cards.get(i));
            }
            System.out.printf("%d. 새 카드 등록\n", cards.size() + 1);
            int selected = InputUtil.readInt("사용할 카드 번호 선택: ");

            if (selected >= 1 && selected <= cards.size()) {
                selectedCard = cards.get(selected - 1);
                break;
            } else if (selected == cards.size() + 1) {
                String newCard = InputUtil.readLine("새 카드 번호 입력: ");
                user.addCard(newCard);
                cards = user.getRegisteredCards();
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }

        // 3. 마일리지 사용 여부
        int total = reservation.getFlight().getPrice();
        int mileage = user.getMileage();
        int usedMileage = 0;
        int remaining = total;

        boolean useMileage = false;
        if (mileage > 0) {
            String answer = InputUtil.readLine("마일리지를 사용하시겠습니까? (Y/N): ");
            useMileage = answer.equalsIgnoreCase("Y");
        }

        if (useMileage) {
            if (mileage >= total) {
                usedMileage = total;
                remaining = 0;
                user.setMileage(mileage - total);
            } else {
                usedMileage = mileage;
                remaining = total - mileage;
                user.setMileage(0);
            }
        }

        // 4. 결제 명세 출력
        System.out.println("\n[결제 내역]");
        System.out.printf("총 금액: %d원\n", total);
        System.out.printf("사용한 마일리지: %d원\n", usedMileage);
        System.out.printf("카드 결제 금액: %d원\n", remaining);
        System.out.printf("사용한 카드: %s\n", selectedCard);

        return true;
    }
}
