package com.guisfco;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NUMBER_OF_TASKS = 10000;

    static void main() throws InterruptedException {
        var counter = new Counter();
        var synchronizedCounter = new SynchronizedCounter();

        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(counter::increment);
                executorService.submit(synchronizedCounter::increment);
            }

            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }

        IO.println("The counter value is " + counter.getCount());
        IO.println("The counter value for synchronized counter is " + synchronizedCounter.getCount());
    }
}
