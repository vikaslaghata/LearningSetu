package com.multithreading.story;

import java.util.concurrent.CyclicBarrier;

public class SheepChorus {

    // CyclicBarrier with 5 sheep required to start the chorus
    private static final int SHEEP_COUNT = 5;
    private static final CyclicBarrier chorusBarrier = new CyclicBarrier(SHEEP_COUNT, () ->
            System.out.println("\n🎶 All sheep are on stage! The chorus begins!\n")
    );

    static class ChorusSheep extends Thread {
        private final String name;

        public ChorusSheep(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println("🐑 " + name + " is enjoying the feast...");
                Thread.sleep((long) (Math.random() * 1000)); // simulate eating

                System.out.println("🚶‍♂️ " + name + " finishes eating and heads to the stage...");
                chorusBarrier.await(); // Wait for others

                System.out.println("🎵 " + name + " sings their part.");
            } catch (Exception e) {
                System.out.println("😱 " + name + " couldn't join the chorus: " + e);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= SHEEP_COUNT; i++) {
            new ChorusSheep("Sheep-" + i).start();
        }
    }
}
