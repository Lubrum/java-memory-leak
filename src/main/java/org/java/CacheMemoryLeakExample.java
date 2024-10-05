package org.java;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheMemoryLeakExample {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        for (int i = 0; i < 1000000; i++) {
            cache.put("key" + i, new Object());
            if (i % 10000 == 0) {
                cache.invalidateAll();
                Thread.sleep(300);
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