package com.guisfco;

public class UnsafeCounter implements Counter {

    /*
     * This is not thread safe, since it is not atomic:
     *   1. Read current value
     *   2. Increment value
     *   3. Write updated value
     *
     * In this case, multiple threads may overwrite each other's updates.
     */
    private int count;

    @Override
    public void increment() {
        this.count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
