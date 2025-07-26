package com.multithreading.story;

public class DeadlockDilemma {
    private static final PastureToken pastureAccessKeyOne = new PastureToken();
    private static final PastureToken pastureAccessKeyTwo = new PastureToken();

    public static void main(String[] args) {
        Thread sheep1 = new Thread(() -> {
            synchronized (pastureAccessKeyOne) {
                System.out.println("Sheep #1 grabbed pastureAccessKeyOne and wants pastureAccessKeyTwo...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (pastureAccessKeyTwo) {
                    System.out.println("Sheep #1 now has both tokens and enters the pasture.");
                }
            }
        });

        Thread sheep2 = new Thread(() -> {
            synchronized (pastureAccessKeyTwo) {
                System.out.println("Sheep #2 grabbed pastureAccessKeyTwo and wants pastureAccessKeyOne...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (pastureAccessKeyOne) {
                    System.out.println("Sheep #2 now has both tokens and enters the pasture.");
                }
            }
        });

        sheep1.start();
        sheep2.start();
    }
}

class PastureToken{}
