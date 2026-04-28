package com.guisfco;

import java.util.List;

public class PaymentProcessor {

    public static Payment process(Payment payment, List<PaymentStep> steps) {
        var processedPayment = payment;

        for (PaymentStep step : steps) {
            processedPayment = step.process(processedPayment);
        }

        IO.println("Payment processed successfuly: " + processedPayment + "\n");
        return processedPayment;
    }
}
