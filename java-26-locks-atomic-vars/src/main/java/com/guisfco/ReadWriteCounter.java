package com.guisfco;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter implements Counter {

    private int count;

    // ReentrantReadWriteLock is useful when reads are much more frequent than writes
    private final ReadWriteLock lock;

    public ReadWriteCounter() {
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void increment() {
        lock.writeLock().lock(); // Only one thread can write at a time

        try {
            this.count++;
        } finally {
            lock.writeLock().unlock(); // Always releases the write lock
        }
    }

    @Override
    public int getCount() {
        // Multiple threads can read simultaneously, as long as no thread is writing
        // If some thread is writing, the reader BLOCKS and waits
        lock.readLock().lock();

        try {
            return this.count;
        } finally {
            lock.readLock().unlock(); // Always releases the read lock
        }
    }
}
