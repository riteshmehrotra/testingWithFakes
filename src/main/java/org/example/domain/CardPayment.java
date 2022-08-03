package org.example.domain;

import org.example.domain.PaymentStatus;
import org.example.ports.PaymentService;

public class CardPayment implements PaymentService {

    @Override
    public PaymentStatus pay(float aFloat) {
        return PaymentStatus.SUCCESS;
    }
}
