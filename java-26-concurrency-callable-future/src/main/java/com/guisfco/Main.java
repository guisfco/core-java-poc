package com.guisfco;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    static void main() throws InterruptedException, ExecutionException {
        try (var executorService = Executors.newFixedThreadPool(2)) {
            IO.println("==== invokeAll() ====");
            invokeAll(executorService);

            IO.println("\n==== submit() ====");
            submit(executorService);

            IO.println("\n==== Multiple submit() ====");
            multipleSubmit(executorService);
        }
    }

    /*
    * Example of multiple submit() usage.
    * The submit() itself is non-blocking. Synchronization happens later through Future#get().
    * */
    private static void multipleSubmit(ExecutorService executorService) throws InterruptedException, ExecutionException {
        var callableTask = createCallableTask();
        var callableTasks = List.of(callableTask, callableTask, callableTask, callableTask, callableTask);

        var futuresNonBlocking = callableTasks.stream()
                .map(executorService::submit)
                .toList();

        IO.println("This print doesn't wait for the tasks to finish");

        // Way to wait for all tasks to finish
        for (Future<String> f : futuresNonBlocking) {
            f.get();
        }
    }

    /*
     * The submit() schedules the task execution (the execution might be instantly or not), and returns a Future for
     * tracking the result. We use it when we want asynchronous behavior, and we don't need all tasks immediately.
     *
     * Future is used basically for tracking the execution, and then you can use it for:
     *   - wait for completion (get())
     *   - cancel it (cancel())
     *   - check status (isDone())
     * */
    private static void submit(ExecutorService executorService) throws ExecutionException, InterruptedException {
        var future = executorService.submit(createCallableTask());

        IO.println("The flow continues");
        IO.println("Is done? " + future.isDone());
        IO.println(future.get()); // Waits the task to finish if necessary
    }

    /*
     * The invokeAll() submits multiple tasks at once and waits for all of them to finish.
     * Used when we have a batch of tasks, and we need all results together.
     * */
    private static void invokeAll(ExecutorService executorService) throws InterruptedException, ExecutionException {
        var callableTask = createCallableTask();
        var callableTasks = new ArrayList<Callable<String>>();

        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        var futures = executorService.invokeAll(callableTasks);
        IO.println("This print was waiting for the list of tasks to finish");

        for (Future<String> future : futures) {
            IO.println(future.get());
        }
    }

    private static Callable<String> createCallableTask() {
        return () -> {
            Thread.sleep(1000);
            IO.println("Executing task...");
            return "Task executed";
        };
    }
}
