package com.guisfco;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    /*
     * The AtomicInteger uses CAS (Compare-And-Set), a form of optimistic locking:
     *   1. Read current value
     *   2. Compute new value
     *   3. Update only if the value did not change
     *   4. Retry if another thread modified it meanwhile
     */
    private final AtomicInteger count;

    public AtomicCounter() {
        this.count = new AtomicInteger();
    }

    public void increment() {
        this.count.incrementAndGet();
    }

    public int getCount() {
        return this.count.get();
    }
}
