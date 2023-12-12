package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository=paymentRepository;
    }
}
