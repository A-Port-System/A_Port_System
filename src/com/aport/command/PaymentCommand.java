package com.aport.command;

import com.aport.reservation.Reservation;
import com.aport.service.PaymentService;
import com.aport.user.User;
import com.aport.app.InputUtil;
import java.util.List;

public class PaymentCommand implements Command {
    private User user;
    private Reservation reservation;

    public PaymentCommand(User user, Reservation reservation) {
        this.user = user;
        this.reservation = reservation;
    }

    @Override
    public void execute() {
        PaymentService paymentService = PaymentService.getInstance();
        List<String> cards = user.getRegisteredCards();

        if (cards.isEmpty()) {
            System.out.println("등록된 카드가 없습니다. 카드를 먼저 등록합니다.");
            String cardNumber = InputUtil.readLine("카드 번호 입력: ");
            paymentService.createCard(user, cardNumber);
            cards = user.getRegisteredCards();
        }

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
                paymentService.createCard(user, newCard);
                cards = user.getRegisteredCards();
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }

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

        System.out.println("\n[결제 내역]");
        System.out.printf("총 금액: %d원\n", total);
        System.out.printf("사용한 마일리지: %d원\n", usedMileage);
        System.out.printf("카드 결제 금액: %d원\n", remaining);
        System.out.printf("사용한 카드: %s\n", selectedCard);
    }
}