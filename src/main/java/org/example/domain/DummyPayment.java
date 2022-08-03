package org.example.domain;

import org.example.ports.PaymentService;

public class DummyPayment implements PaymentService {
    @Override
    public PaymentStatus pay(float aFloat) {
        return null;
    }
}
