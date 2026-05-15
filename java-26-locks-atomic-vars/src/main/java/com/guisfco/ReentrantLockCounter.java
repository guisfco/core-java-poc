package com.guisfco;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter {

    private int count;
    private final ReentrantLock lock;

    public ReentrantLockCounter() {
        this.lock = new ReentrantLock();
    }

    /*
     * lock() - WAITS until the lock becomes available
     * tryLock() - tries to acquire the lock immediately without waiting
     */
    public void increment() {
        lock.lock(); // When using ReentrantLock, we manage the lock manually

        try {
            this.count++;
        } finally { // Very important to release the lock, even if an exception happens
            lock.unlock();
        }
    }

    public int getCount() {
        return this.count;
    }
}
