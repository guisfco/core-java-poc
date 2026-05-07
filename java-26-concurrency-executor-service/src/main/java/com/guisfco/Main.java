package com.guisfco;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Main {

    static void main() throws ExecutionException, InterruptedException {
        var executorService = Executors.newFixedThreadPool(10);

        Callable<String> callableTask = () -> {
            Thread.sleep(1000);
            IO.println("Executing task...");
            return "Task executed";
        };

        var callableTasks = new ArrayList<Callable<String>>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        /*
        The submit() already triggers the execution. Future is used basically for tracking the execution,
        and then you can use it for:
            - wait for completion (get())
            - cancel it (cancel())
            - check status (isDone())
        */
        var future = executorService.submit(callableTask);
        IO.println("The flow continues");
        IO.println(future.get());

        // The invokeAll() submits multiple tasks at once and waits for all of them to finish.
        var futures = executorService.invokeAll(callableTasks);
        IO.println("This print was waiting for the list of tasks to finish");
    }
}
