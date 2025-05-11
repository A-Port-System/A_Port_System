package com.aport.service;

import com.aport.user.User;

public class PaymentService extends BaseService {
    private static PaymentService instance;

    private PaymentService() {}

    public static PaymentService getInstance() {
        if (instance == null) {
            instance = new PaymentService();
        }
        return instance;
    }

    public void createCard(User user, String cardNumber) {
        user.addCard(cardNumber);
    }

    public void deleteCard(User user, String cardNumber) {
        user.removeCard(cardNumber);
    }
}
