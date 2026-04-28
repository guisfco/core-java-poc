package com.guisfco;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    static void main() {
        IO.println("==== Existing Functional Interfaces ====");
        basicExamples();
        functionalInterfaceAsParameter();

        IO.println("\n==== Custom Functional Interface ====");
        customInterfaceExample();

        IO.println("\n==== Payment Example ====");
        paymentFlowExample();
    }

    private static void paymentFlowExample() {
        PaymentStep validate = payment -> {
            if (payment.amount() == null) {
                throw new RuntimeException("Amount cannot be null");
            }
            return payment;
        };

        PaymentStep creditCardFee = payment -> new Payment(payment.amount().multiply(new BigDecimal("1.03")));
        PaymentStep internationalFee = payment -> new Payment(payment.amount().multiply(new BigDecimal("1.05")));
        PaymentStep pixFee = payment -> payment;

        PaymentStep audit = payment -> {
            IO.println("Payment stored to audit table: " + payment);
            return payment;
        };

        var creditCardSteps = List.of(validate, creditCardFee, audit);
        var internationalSteps = List.of(validate, internationalFee, audit);
        var pixSteps = List.of(validate, pixFee, audit);

        PaymentProcessor.process(new Payment(BigDecimal.TEN), creditCardSteps);
        PaymentProcessor.process(new Payment(BigDecimal.TEN), internationalSteps);
        PaymentProcessor.process(new Payment(BigDecimal.TEN), pixSteps);
    }

    private static void functionalInterfaceAsParameter() {
        // Using Functional Interfaces as parameter
        ignoreException(() -> {
            IO.println("Calling external service...");
            throw new RuntimeException("Could not call external service");
        });
    }

    static void ignoreException(Callable<Void> callable) {
        try {
            callable.call();
        } catch (Exception e) {
            IO.println("An exception was thrown and it will be ignored: " + e);
        }
    }

    private static void basicExamples() {
        // Using existing Functional Interfaces to apply some behavior
        Function<String, String> toUpperCase = s -> s.toUpperCase();
        Consumer<String> greeting = name -> IO.println("Hello, " + name);
        Predicate<String> isUpperCase = s -> s.equals(s.toUpperCase());

        var name = toUpperCase.apply("Guilherme");
        greeting.accept(name);

        IO.println("Is upper case? " + isUpperCase.test(name));
    }

    private static void customInterfaceExample() {
        var result = RetryExecutor.run(() -> {
            if (Math.random() < 0.8) {
                throw new RuntimeException("Fail");
            }

            return "Success";
        }, 3);

        IO.println("Result: " + result);
    }
}
