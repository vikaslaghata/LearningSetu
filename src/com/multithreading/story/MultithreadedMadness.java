package com.multithreading.story;

import java.util.Random;

public class MultithreadedMadness {
    static class GrazingField {
        private int hay = 100; // total amount of hay

        public void graze(String sheepName) {
            // Sheep tries to eat some hay
            int amountToEat = new Random().nextInt(20) + 1;
            if (hay >= amountToEat) {
                hay -= amountToEat;
                System.out.println("🐑 " + sheepName + " grazed " + amountToEat + " units of hay. Remaining: " + hay);
            } else {
                System.out.println("🐑 " + sheepName + " found not enough hay! Remaining: " + hay);
            }
        }
    }

    // Each sheep runs as a thread
    static class Sheep extends Thread {
        private final GrazingField field;

        public Sheep(String name, GrazingField field) {
            super(name);
            this.field = field;
        }

        @Override
        public void run() {
            field.graze(getName());
        }
    }

    public static void main(String[] args) {
        GrazingField sharedField = new GrazingField();

        // Simulating multiple sheep threads
        for (int i = 1; i <= 10; i++) {
            new Sheep("Sheep-" + i, sharedField).start();
        }
    }
}
