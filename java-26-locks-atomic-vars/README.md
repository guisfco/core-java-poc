# Java 26 Concurrency: Locks and Atomic Variables POC

This POC demonstrates various techniques for handling thread safety and synchronization in Java. It uses a simple counter increment problem to compare different approaches, ranging from intrinsic synchronization to advanced lock management and lock-free atomic variables.

## Topics

- [Unsafe Counter (Race Conditions)](https://github.com/guisfco/java-pocs/blob/main/java-26-locks-atomic-vars/src/main/java/com/guisfco/UnsafeCounter.java#L16)
- [Synchronized Methods](https://github.com/guisfco/java-pocs/blob/main/java-26-locks-atomic-vars/src/main/java/com/guisfco/SynchronizedCounter.java#L9)
- [ReentrantLock (Manual Management)](https://github.com/guisfco/java-pocs/blob/main/java-26-locks-atomic-vars/src/main/java/com/guisfco/ReentrantLockCounter.java#L19)
- [AtomicInteger (Lock-free CAS)](https://github.com/guisfco/java-pocs/blob/main/java-26-locks-atomic-vars/src/main/java/com/guisfco/AtomicCounter.java#L29)
- [ReadWriteLock (Separation of Concerns)](https://github.com/guisfco/java-pocs/blob/main/java-26-locks-atomic-vars/src/main/java/com/guisfco/ReadWriteCounter.java#L18)
- [Comparing Performance and Correctness](https://github.com/guisfco/java-pocs/blob/main/java-26-locks-atomic-vars/src/main/java/com/guisfco/CounterRunner.java#L11)
