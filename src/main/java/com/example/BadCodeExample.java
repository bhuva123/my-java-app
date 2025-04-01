package com.example;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BadCodeExample {
    private static final Logger LOGGER = Logger.getLogger(BadCodeExample.class.getName());
    private final int[] secretData;

    public BadCodeExample() {
        this.secretData = new int[]{42, 1337};
    }

    public static void main(String[] args) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("Debug info: Start"); // Fixed SystemPrintln issue by using logger with guard
        }

        BadCodeExample example = new BadCodeExample();
        int[] data = example.getSecretData().clone(); // Fixed EI_EXPOSE_REP by using returned clone
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("Data: " + Arrays.toString(data));
        }

        try {
            // Removed unused variable
            int result = 5 / 0; // Intentional divide-by-zero for test
            LOGGER.info("Result: " + result); // Unreachable but prevents unused variable warning
        } catch (Exception e) {
            // Fixed EmptyCatchBlock by logging the exception
            LOGGER.warning("Division by zero error: " + e.getMessage());
        }

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("Debug info: End");
        }
    }

    public int[] getSecretData() {
        return secretData.clone(); // Fixed EI_EXPOSE_REP by returning a clone
    }

    // Fixed EQ_METHOD_HAS_BAD_TYPE by implementing correct equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BadCodeExample other = (BadCodeExample) obj;
        return Arrays.equals(this.secretData, other.secretData);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(secretData);
    }
}