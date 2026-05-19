package com.guisfco;

public class Main {

    static void main() throws InterruptedException {
        CounterRunner.run(new UnsafeCounter());
        CounterRunner.run(new SynchronizedCounter());
        CounterRunner.run(new ReentrantLockCounter());
        CounterRunner.run(new AtomicCounter());
        CounterRunner.run(new ReadWriteCounter());
    }
}
