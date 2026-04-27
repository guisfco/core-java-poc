package com.guisfco;

@FunctionalInterface
public interface Retryable<T> {

    T execute() throws Exception;
}
