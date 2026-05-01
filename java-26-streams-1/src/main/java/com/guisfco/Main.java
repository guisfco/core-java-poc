package com.guisfco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static void main() {
        IO.println("==== Lists ====");
        lists();

        IO.println("\n==== Arrays ====");
        arrays();

        IO.println("\n==== Map ====");
        map();
    }

    private static void map() {
        // Creates an immutable Map, so we cannot put/remove items
        var ages = Map.of(
                "Guilherme", 26,
                "Yasmin", 24,
                "Cristina", 44,
                "Maria", 18
        );

        // Filtering (names only)
        var peopleOlderThan25 = ages.entrySet().stream()
                .filter(e -> e.getValue() > 25)
                .map(Map.Entry::getKey)
                .toList();

        IO.println("People older than 25 years: " + peopleOlderThan25);

        // Transforming values
        var transformed = ages.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue() + 5)
                );

        IO.println("Ages after 5 years: " + transformed);

        // Sum all items
        var totalAges = ages.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        IO.println("Sum of all ages: " + totalAges);

        // Grouping by something
        var grouped = ages.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getValue() > 25 ? "OLDER" : "YOUNGER",
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));

        IO.println("Grouped: " + grouped);

        // Max value
        var oldest = ages.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        IO.println("Oldest person: " + oldest.getKey());
    }

    private static void arrays() {
        // Arrays have a fixed size and it cannot be increased
        var names = new String[]{"Guilherme", "Yasmin", "Kira"};

        // Mutation
        names[0] = "Pink";
        IO.println(Arrays.toString(names));

        // Iterating
        for (var name : names) {
            IO.println(name);
        }

        // When iterating an array using stream, use Arrays.stream(...) instead of Stream.of(...) to avoid issues with primitive types
        var namesContainingA = Arrays.stream(names)
                .filter(name -> name.contains("a"))
                .count();

        IO.println("Names containing A: " + namesContainingA);

        // Converting primitive Array to List
        var numbers = Arrays.stream(new int[]{1, 2, 3})
                .boxed()
                .toList();

        IO.println("Values: " + numbers + ", Value type: " + numbers.getFirst().getClass());

        // Multidimensional Arrays
        var matrix = new int[][]{{1, 2, 3}, {3, 2, 1}, {0, 0, 0}};
        IO.println("Matrix: " + Arrays.deepToString(matrix));
    }

    private static void lists() {
        var immutableList = List.of("Guilherme", "Yasmin", "Kira");
        System.out.println(immutableList);
        tryToAddAndCatchException(immutableList);

        var modifiableList = new ArrayList<>(immutableList);
        modifiableList.add("Other");
        System.out.println(modifiableList);

        // Important to mention that .toList() from stream returns an immutable list
        // Use .collect(Collectors.toList()) instead
        var streamImmutableList = modifiableList.stream().toList();
        tryToAddAndCatchException(streamImmutableList);
    }

    private static void tryToAddAndCatchException(List<String> list) {
        try {
            list.add("Fail");
        } catch (UnsupportedOperationException e) {
            System.out.println("Immutable list cannot be modified");
        }
    }
}
