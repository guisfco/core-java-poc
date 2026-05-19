package com.guisfco;

public class SynchronizedCounter implements Counter {

    private int count;

    // When using synchronized, Java manages the lock automatically
    @Override
    public synchronized void increment() {
        this.count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
