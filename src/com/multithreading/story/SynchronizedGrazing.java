package com.multithreading.story;

import java.util.concurrent.Semaphore;
class Sheep implements Runnable {
    private final int id;
    private final Semaphore pastureAccessKey;

    public Sheep(int id, Semaphore pastureAccessKey) {
        this.id = id;
        this.pastureAccessKey = pastureAccessKey;
    }

    @Override
    public void run() {
        try {
            System.out.println("Sheep #" + id + " is waiting at the tile...");
            pastureAccessKey.acquire(); // Wait for access (synchronized tile)
            System.out.println("Sheep #" + id + " is grazing peacefully.");
            Thread.sleep(1000); // Simulate grazing time
            System.out.println("Sheep #" + id + " leaves the pasture.");
        } catch (InterruptedException e) {
            System.out.println("Sheep #" + id + " got interrupted.");
        } finally {
            pastureAccessKey.release(); // Release access
        }
    }
}

public class SynchronizedGrazing {
    public static void main(String[] args) {
        Semaphore pastureAccess = new Semaphore(1); // Only one sheep at a time

        for (int i = 1; i <= 10; i++) {
            Thread sheepThread = new Thread(new Sheep(i, pastureAccess));
            sheepThread.start();
        }
    }
}
