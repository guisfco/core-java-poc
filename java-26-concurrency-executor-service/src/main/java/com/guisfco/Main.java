package com.guisfco;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static void main() {
        IO.println("==== newSingleThreadExecutor ====");
        singleThreadExecutor();

        IO.println("\n==== newFixedThreadPool ====");
        fixedThreadPool();

        IO.println("\n==== newCachedThreadPool ====");
        cachedThreadPool();

        IO.println("\n==== newScheduledThreadPool ====");
        scheduledThreadPool();

        IO.println("\n==== newVirtualThreadPerTaskExecutor ====");
        virtualThreadPerTaskExecutor();
    }

    /*
     * Creates an ExecutorService with only one worker:
     *  - Tasks execute sequentially
     *  - Preserves order
     *  - Reuses the same thread for all tasks
     */
    static void singleThreadExecutor() {
        try (var executor = Executors.newSingleThreadExecutor()) {
            var task = createTask();

            executor.execute(task);
            executor.execute(task);
            executor.execute(task);

            IO.println("singleThreadExecutor submitted all tasks");
        }
    }

    /*
     * Creates an ExecutorService with a fixed number of worker (think of it as queue consumers):
     *  - On the following example, only 2 tasks can run simultaneously
     *  - Following tasks wait the others to finish
     *  - Threads are reused between tasks
     */
    static void fixedThreadPool() {
        try (var executor = Executors.newFixedThreadPool(2)) {
            var task = createTask();

            executor.execute(task);
            executor.execute(task);
            executor.execute(task);
            executor.execute(task);

            IO.println("fixedThreadPool submitted all tasks");
        }
    }

    /*
     * Creates an ExecutorService that dynamically creates threads:
     *  - Creates new threads on demand
     *  - Reuses idle threads when possible
     *  - Has no fixed thread limit (this can cause issues)
     */
    static void cachedThreadPool() {
        try (var executor = Executors.newCachedThreadPool()) {
            var task = createTask();

            executor.execute(task);
            executor.execute(task);
            executor.execute(task);
            executor.execute(task);

            IO.println("cachedThreadPool submitted all tasks");
        }
    }

    static void scheduledThreadPool() {
        // Creates an ExecutorService that works like a scheduler. In this example, the task starts after 2 seconds.
        try (var executor = Executors.newScheduledThreadPool(2)) {
            var task = createTask();
            executor.schedule(task, 2, TimeUnit.SECONDS);
            IO.println("scheduledThreadPool submitted all tasks");
        }
    }

    /*
     * Creates an ExecutorService that uses Virtual Threads execution:
     *  - Creates one virtual thread per task
     *  - Virtual threads are lightweight
     *  - Supports massive concurrency
     */
    static void virtualThreadPerTaskExecutor() {
        /*
        * Using this only to have a name to show when printing the virtual thread name.
        * In normal situations, we could use the newVirtualThreadPerTaskExecutor() directly.
        * */
        var factory = Thread.ofVirtual().name("virtual-thread-", 1).factory();

        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            var task = createTask();

            executor.execute(task);
            executor.execute(task);
            executor.execute(task);
            executor.execute(task);
        }
    }

    private static Runnable createTask() {
        return () -> {
            // Every new instance of an ExecutorService will increment the thread name
            var threadName = Thread.currentThread().getName();
            IO.println("Starting task on: " + threadName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            IO.println("Finishing task on: " + threadName);
        };
    }
}