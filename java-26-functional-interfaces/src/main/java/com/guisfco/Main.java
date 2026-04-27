package com.guisfco;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    static void main() {
        IO.println("==== Existing Functional Interfaces ====");

        // Using existing Functional Interfaces to apply some behavior
        Function<String, String> toUpperCase = s -> s.toUpperCase();
        Consumer<String> greeting = name -> IO.println("Hello, " + name);
        Predicate<String> isUpperCase = s -> s.equals(s.toUpperCase());

        var name = toUpperCase.apply("Guilherme");
        greeting.accept(name);

        IO.println("Is upper case? " + isUpperCase.test(name));

        Callable<Void> callExternalService = () -> {
            IO.println("Calling external service...");
            throw new RuntimeException("Could not call external service");
        };

        ignoreException(callExternalService);

        IO.println("\n==== Custom Functional Interface ====");

        var response = RetryExecutor.run(() -> {
            if (Math.random() < 0.8) {
                throw new RuntimeException("Fail");
            }

            return "Success";
        }, 3);

        IO.println("Response: " + response);
    }

    // Using Functional Interfaces as parameter
    static void ignoreException(Callable<Void> callable) {
        try {
            callable.call();
        } catch (Exception e) {
            IO.println("An exception was thrown and it will be ignored: " + e);
        }
    }
}
