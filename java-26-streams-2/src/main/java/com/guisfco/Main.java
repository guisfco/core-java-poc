package com.guisfco;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {

    static void main() {
        IO.println("==== Supplier ====");
        Supplier<String> greeting = () -> "Hello, Guilherme!";
        IO.println(greeting.get());

        // Might be useful for generating mock data
        Supplier<Person> randomPerson = () -> new Person(UUID.randomUUID());
        var people = Stream.generate(randomPerson)
                .limit(5)
                .toList();

        IO.println(people);

        IO.println("==== Consumer ====");
        Consumer<String> printUpperName = name -> IO.println(name.toUpperCase());
        printUpperName.accept("Guilherme");

        // forEach internally uses a Consumer<T>
        List.of("Guilherme", "Yasmin").forEach(IO::println);
    }
}
