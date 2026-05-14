package com.guisfco;

public class SynchronizedCounter {

    private int count;

    // When using synchronized, Java manages the lock automatically
    public synchronized void increment() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }
}
