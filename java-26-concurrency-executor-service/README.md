# Java 26 Concurrency: Executor Service POC

This POC explores the `ExecutorService` API in Java, demonstrating different thread pool strategies and the modern approach using Virtual Threads. It covers task submission, execution models, and thread reuse.

## Topics

- [Single Thread Executor](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-executor-service/src/main/java/com/guisfco/Main.java#L31)
- [Fixed Thread Pool](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-executor-service/src/main/java/com/guisfco/Main.java#L49)
- [Cached Thread Pool](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-executor-service/src/main/java/com/guisfco/Main.java#L68)
- [Scheduled Thread Pool](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-executor-service/src/main/java/com/guisfco/Main.java#L81)
- [Virtual Thread Per Task Executor](https://github.com/guisfco/java-pocs/blob/main/java-26-concurrency-executor-service/src/main/java/com/guisfco/Main.java#L96)
