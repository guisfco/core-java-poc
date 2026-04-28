package com.guisfco;

@FunctionalInterface
public interface PaymentStep {

    Payment process(Payment payment);
}
