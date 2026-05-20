# Java 26 Concurrency: Callable and Future POC

This POC explores the `Callable` and `Future` APIs in Java, demonstrating how to handle tasks that return results and manage asynchronous execution flows. It highlights the differences between batch submission and individual task tracking.

## Topics

- [Callable Task Definition](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-callable-future/src/main/java/com/guisfco/Main.java#L85)
- [invokeAll() (Batch Execution)](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-callable-future/src/main/java/com/guisfco/Main.java#L67)
- [submit() (Asynchronous Execution)](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-callable-future/src/main/java/com/guisfco/Main.java#L55)
- [Future Tracking (isDone, get)](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-callable-future/src/main/java/com/guisfco/Main.java#L59)
- [Multiple submit() with non-blocking flow](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-callable-future/src/main/java/com/guisfco/Main.java#L30)
- [Waiting for multiple Futures](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-callable-future/src/main/java/com/guisfco/Main.java#L41)
