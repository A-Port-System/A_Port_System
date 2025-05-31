package com.aport.payment.command;

import com.aport.reservation.domain.Reservation;
import com.aport.reservation.service.ReservationService;
import com.aport.user.domain.User;
import com.aport.user.service.UserService;
import com.aport.app.InputUtil;
import com.aport.common.command.Command;
import com.aport.payment.service.PaymentService;


import java.util.List;

public class PaymentCommand implements Command {

    @Override
    public Object execute() {
        User user = UserService.getInstance().getCurrentUser();

        Reservation reservation = null;
        System.out.println("결제할 예약을 선택하세요.");
        List<Reservation> reservations = ReservationService.getInstance().getReservations(user);
        for (int i = 0; i < reservations.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, reservations.get(i).getReservationInfo());
        }

        int selected = InputUtil.readInt("예약 번호 선택: ");
        if (selected < 1 || selected > reservations.size()) {
            System.out.println("잘못된 입력입니다.");
            return null;
        }

        reservation = reservations.get(selected - 1);
        System.out.println("결제할 예약: " + reservation.getReservationInfo());
        System.out.println("결제 수단을 선택하세요.");

        PaymentService paymentService = PaymentService.getInstance();
        List<String> cards = user.getRegisteredCards();

        if (cards.isEmpty()) {
            System.out.println("등록된 카드가 없습니다. 카드를 먼저 등록합니다.");
            String cardNumber = InputUtil.readLine("카드 번호 입력: ");
            paymentService.addCard(user, cardNumber);
            cards = user.getRegisteredCards();
        }

        String selectedCard = null;
        while (true) {
            System.out.println("등록된 카드 목록:");
            for (int i = 0; i < cards.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, cards.get(i));
            }
            System.out.printf("%d. 새 카드 등록\n", cards.size() + 1);
            System.out.println("0. 취소");
            selected = InputUtil.readInt("사용할 카드 번호 선택: ");

            if (selected == 0) {
                System.out.println("결제를 취소합니다.");
                return null;
            } else if (selected >= 1 && selected <= cards.size()) {
                selectedCard = cards.get(selected - 1);
                break;
            } else if (selected == cards.size() + 1) {
                String newCard = InputUtil.readLine("새 카드 번호 입력: ");
                paymentService.addCard(user, newCard);
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

        //티켓 정보
        System.out.println("\n=== [티켓 내용] - " + reservation.getReservationId()+ " ===");
        System.out.printf("항공편: %s\n", reservation.getFlight().getFlightNumber());
        System.out.printf("탑승자: %s\n", reservation.getUser().getName());
        //탑승지 정보 헤더
        System.out.println("\n[탑승지 정보]");
        System.out.printf("탑승지: %s\n", reservation.getFlight().getDeparture());
        System.out.printf("탑승일: %s\n", reservation.getFlight().getDepartureTime());
        //도착지 정보 헤더
        System.out.println("\n[도착지 정보]");
        System.out.printf("도착지: %s\n", reservation.getFlight().getArrival());
        System.out.printf("도착일: %s\n", reservation.getFlight().getArrivalTime());
        return null;
    }
}