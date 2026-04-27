package com.guisfco;

public class RetryExecutor {

    public static <T> T run(Retryable<T> action, int maxAttempts) {
        int attempts = 0;

        while (true) {
            try {
                IO.println("Attempt " + attempts);
                return action.execute();
            } catch (Exception e) {
                attempts++;

                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Max attempts reached: ", e);
                }
            }
        }
    }
}
