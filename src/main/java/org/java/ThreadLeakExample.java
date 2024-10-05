package org.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLeakExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    System.out.println("Teste " + finalI);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        System.out.println("Fim #####");
//        executor.shutdown();
    }
}