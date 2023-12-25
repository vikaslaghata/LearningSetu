package com.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumer {
    public static void main(String[] args) {
        Queue<Integer> items = new LinkedList<>();

        var producer = new Producer(items);
        var producerThread = new Thread(producer);
        producerThread.start();

        var consumer = new Consumer(items);
        var consumerThread = new Thread(consumer);
        consumerThread.start();

    }
}

class Producer implements Runnable {
    private final Queue<Integer> items;

    public Producer(Queue<Integer> items) {
        this.items = items;
    }

    private void produce() {
        Integer newItem = ThreadLocalRandom.current().nextInt(0, 100 + 1); //ThreadLocalRandom introduced in java 7
        System.out.println("Producing..." + newItem);
        items.add(newItem);
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}

class Consumer implements Runnable {
    private final Queue<Integer> items;

    public Consumer(Queue<Integer> items) {
        this.items = items;
    }

    private void consume() {
        System.out.println("Consuming..." + items.poll());
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
}
