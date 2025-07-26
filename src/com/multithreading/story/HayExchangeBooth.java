package com.multithreading.story;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

// HayExchangeBooth handles the queue
class HayExchangeBooth {
    public static final int CAPACITY = 5;
    public static BlockingQueue<String> hayQueue = new ArrayBlockingQueue<>(CAPACITY);

    public static void main(String[] args) {
        Thread producerSheep = new Thread(new ProducerSheep(), "ProducerSheep");
        Thread consumerSheep = new Thread(new ConsumerSheep(), "ConsumerSheep");

        producerSheep.start();
        consumerSheep.start();
    }
}

// ProducerSheep produces hay
class ProducerSheep implements Runnable {
    @Override
    public void run() {
        int hayCount = 0;
        try {
            while (true) {
                String hay = "Hay#" + (++hayCount);
                HayExchangeBooth.hayQueue.put(hay);
                System.out.println(Thread.currentThread().getName() + " produced: " + hay);
                Thread.sleep(1000); // Simulate time to harvest
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// ConsumerSheep munches hay
class ConsumerSheep implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                String hay = HayExchangeBooth.hayQueue.take();
                System.out.println(Thread.currentThread().getName() + " munched: " + hay);
                Thread.sleep(1500); // Simulate munching time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
