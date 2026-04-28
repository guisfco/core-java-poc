package com.guisfco;

public class RetryExecutor {

    public static <T> T run(Retryable<T> action, int maxAttempts) {
        int attempts = 1;

        while (true) {
            try {
                IO.println("Attempt " + attempts);
                return action.execute();
            } catch (Exception e) {
                attempts++;

                if (attempts > maxAttempts) {
                    IO.println("ERROR - Max attempts reached: " + e);
                    return null;
                }
            }
        }
    }
}
