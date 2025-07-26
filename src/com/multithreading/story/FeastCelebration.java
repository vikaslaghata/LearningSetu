package com.multithreading.story;

import java.util.concurrent.CountDownLatch;

public class FeastCelebration {

    // Javin's magical feast latch
    private static final CountDownLatch eldersLatch = new CountDownLatch(3);

    // Elder sheep who return one by one
    static class ElderSheep extends Thread {
        private final String name;

        public ElderSheep(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println("🐑 " + name + " is wandering the field...");
                Thread.sleep((long)(Math.random() * 1000)); // simulate delay
                System.out.println("🎉 " + name + " returns to the pasture!");
                eldersLatch.countDown(); // lowers the latch
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Javin waits patiently for all elders to return
    static class Javin extends Thread {
        @Override
        public void run() {
            System.out.println("🧙 Javin sets the feast table and awaits elder sheep...");
            try {
                eldersLatch.await(); // waits until latch hits zero
                System.out.println("🍽️ All elders are home — feast begins!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void main(String[] args) {
        new Javin().start();

        // Summon the elders one by one
        new ElderSheep("ElderSheep-A").start();
        new ElderSheep("ElderSheep-B").start();
        new ElderSheep("ElderSheep-C").start();
    }
}
