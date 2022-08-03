package org.example.ports;

import org.example.domain.PaymentStatus;

public interface PaymentService {

    PaymentStatus pay(float amount);
}
