package com.guisfco;

import java.util.List;
import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Main {

    static void main() {
        IO.println("==== Supplier ====");
        supplier();

        IO.println("\n==== Consumer ====");
        consumer();

        IO.println("\n==== UnaryOperator & BinaryOperator ====");
        unaryAndBinaryOperators();
    }

    private static void unaryAndBinaryOperators() {
        // Transform one thing into another of the same type (T -> T)
        UnaryOperator<Integer> square = x -> x * x;
        System.out.printf("The square of %d is %d\n", 5, square.apply(5));

        // Transform two things into another of the same type (T, T -> T)
        int num1 = 10, num2 = 5;
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.printf("The sum of %d and %d is %d\n", num1, num2, sum.apply(num1, num2));

        UnaryOperator<Integer> multiplyBy2 = x -> x * 2;
        UnaryOperator<Integer> combinedOperation = x -> square.andThen(multiplyBy2).apply(x);
        IO.println("Combined operations: " + combinedOperation.apply(5));

        // Using BinaryOperator with Streams
        var numbers = List.of(1,2,3,4,5);
        int result = numbers.stream().reduce(0, sum);
        IO.println("Sum of " + numbers + ": " + result);
    }

    private static void consumer() {
        Consumer<String> printUpperName = name -> IO.println(name.toUpperCase());
        printUpperName.accept("Guilherme");

        // forEach internally uses a Consumer<T>
        List.of("Guilherme", "Yasmin").forEach(IO::println);
    }

    private static void supplier() {
        Supplier<String> greeting = () -> "Hello, Guilherme!";
        IO.println(greeting.get());

        // Might be useful for generating mock data
        Supplier<Person> randomPerson = () -> new Person(UUID.randomUUID());
        var people = Stream.generate(randomPerson)
                .limit(5)
                .toList();

        IO.println(people);
    }
}
