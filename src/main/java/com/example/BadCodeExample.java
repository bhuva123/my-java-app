package com.example;

import java.util.Arrays;

public class BadCodeExample {
    private int[] secretData = {42, 1337};

    public static void main(String[] args) {
        System.out.println("Debug info: Start"); // PMD: SystemPrintln

        BadCodeExample example = new BadCodeExample();
        int[] data = example.getSecretData(); // SpotBugs: EI_EXPOSE_REP
        System.out.println(Arrays.toString(data));

        try {
            int unused = 100; // PMD: UnusedLocalVariable
            int result = 5 / 0; // Intentional divide-by-zero for test
        } catch (Exception e) {
            // PMD: EmptyCatchBlock
        }

        System.out.println("Debug info: End");
    }

    public int[] getSecretData() {
        return secretData; // SpotBugs: exposes internal representation
    }

    // SpotBugs: EQ_METHOD_HAS_BAD_TYPE (wrong equals signature)
    public boolean equals(BadCodeExample other) {
        return Arrays.equals(this.secretData, other.secretData);
    }
}
