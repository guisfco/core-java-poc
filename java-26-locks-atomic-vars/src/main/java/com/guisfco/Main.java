package com.guisfco;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NUMBER_OF_TASKS = 10000;

    static void main() throws InterruptedException {
        var counter = new Counter();
        var synchronizedCounter = new SynchronizedCounter();
        var reentrantLockCounter = new ReentrantLockCounter();
        var atomicCounter = new AtomicCounter();

        try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(counter::increment);
                executorService.submit(synchronizedCounter::increment);
                executorService.submit(reentrantLockCounter::increment);
                executorService.submit(atomicCounter::increment);
            }

            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }

        IO.println("Unsafe (no lock) counter: " + counter.getCount());
        IO.println("Synchronized counter: " + synchronizedCounter.getCount());
        IO.println("ReentrantLock counter: " + reentrantLockCounter.getCount());
        IO.println("Atomic counter: " + atomicCounter.getCount());
    }
}
