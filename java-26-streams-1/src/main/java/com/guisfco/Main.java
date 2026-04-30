package com.guisfco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static void main() {
        IO.println("==== Lists ====");
        lists();

        IO.println("\n==== Arrays ====");
        arrays();
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
