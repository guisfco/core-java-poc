package com.guisfco;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterRunner {

    private static final int NUMBER_OF_TASKS = 10000;

    public static void run(Counter counter) throws InterruptedException {
        long start = System.nanoTime();

        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(counter::increment);
                executorService.submit(counter::getCount); // To demonstrate concurrent reads
            }

            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }

        long end = System.nanoTime();

        IO.println("\n=== " + counter.getClass().getSimpleName() + " ===");
        IO.println("Expected: " + NUMBER_OF_TASKS);
        IO.println("Actual: " + counter.getCount());
        IO.println("Execution time: " + ((end - start) / 1000000) + " ms");
    }
}
