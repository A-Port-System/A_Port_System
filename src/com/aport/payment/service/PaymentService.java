package com.aport.payment.service;

import com.aport.common.BaseService;
import com.aport.user.domain.User;

public class PaymentService extends BaseService {
    private static PaymentService instance;

    private PaymentService() {}

    public static PaymentService getInstance() {
        if (instance == null) {
            instance = new PaymentService();
        }
        return instance;
    }

    public void addCard(User user, String cardNumber) {
        user.addCard(cardNumber);
    }

    public void removeCard(User user, String cardNumber) {
        user.removeCard(cardNumber);
    }
}
