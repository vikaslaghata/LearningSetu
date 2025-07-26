package com.multithreading.story;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterSheep {

    // The enchanted counter stone
    private static final AtomicInteger mealCounter = new AtomicInteger(0);

    // Each sheep serves a meal and increments the counter
    static class Sheep extends Thread {
        public Sheep(String name) {
            super(name);
        }

        @Override
        public void run() {
            int count = mealCounter.incrementAndGet(); // safely increments
            System.out.println("🍽️ " + getName() + " served a meal. Total meals: " + count);
        }
    }

    public static void main(String[] args) {
        // Launch multiple sheep
        for (int i = 1; i <= 10; i++) {
            new Sheep("Sheep-" + i).start();
        }
    }
}