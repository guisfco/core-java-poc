package com.guisfco;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static void main() {
        IO.println("==== Lists ====");
        lists();
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
