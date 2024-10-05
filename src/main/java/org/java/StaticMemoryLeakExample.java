package org.java;

import java.util.HashMap;
import java.util.Map;

public class StaticMemoryLeakExample {
    private static final Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            map.put(i, "value" + i);
            if (i % 10000 == 0) {
                Thread.sleep(100);
                printMemoryUsage(i);
            }
        }
    }

    private static void printMemoryUsage(int count) {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Count: " + count +
            ", Used memory: " + usedMemory / (1024 * 1024) + " MB" +
            ", Free memory: " + runtime.freeMemory() / (1024 * 1024) + " MB" +
            ", Total memory: " + runtime.totalMemory() / (1024 * 1024) + " MB"
        );
    }
}