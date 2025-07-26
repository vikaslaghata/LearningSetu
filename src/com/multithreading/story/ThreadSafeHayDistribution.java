package com.multithreading.story;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeHayDistribution {

    // The magical hay baskets 🧺
    private static final ConcurrentHashMap<String, Integer> hayBaskets = new ConcurrentHashMap<>();

    // Each sheep thread tries to grab hay
    static class Sheep extends Thread {
        private final String sheepName;

        public Sheep(String sheepName) {
            this.sheepName = sheepName;
        }
        @Override
        public void run() {
            // Put hay into the basket — thread-safe!
            hayBaskets.put(sheepName, 10);
            System.out.println("🌾 " + sheepName + " grabbed 10 units of hay.");

            // Check if basket is intact and full
            System.out.println("🔍 Basket for " + sheepName + " contains: " + hayBaskets.get(sheepName));
        }
    }
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new ThreadLocalPotions.Sheep("Sheep-" + i).start();
        }
    }
}