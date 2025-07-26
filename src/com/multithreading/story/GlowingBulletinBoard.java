package com.multithreading.story;

public class GlowingBulletinBoard {

    // The enchanted bulletin post – shared and volatile
    private static volatile String latestUpdate = "🌾 Nothing new yet...";

    // Sheep that read updates
    static class ObserverSheep extends Thread {
        private final String name;

        public ObserverSheep(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((long)(Math.random() * 500)); // simulate delay
                System.out.println("👀 " + name + " sees: " + latestUpdate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Javin posts a new message
    static class JavinPoster extends Thread {
        @Override
        public void run() {
            System.out.println("📢 Javin updates bulletin board...");
            latestUpdate = "🚨 Hay at Zone B! Beware grumpy goat!";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Start some sheep before the update
        new ObserverSheep("Sheep-Early").start();

        // Wait a moment before Javin posts the new message
        Thread.sleep(300);
        new JavinPoster().start();

        // More sheep arrive after the bulletin update
        Thread.sleep(100);
        new ObserverSheep("Sheep-Late").start();
        new ObserverSheep("Sheep-Later").start();
    }
}
