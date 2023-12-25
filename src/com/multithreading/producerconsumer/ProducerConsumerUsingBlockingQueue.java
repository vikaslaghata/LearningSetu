package com.multithreading.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;
import java.util.concurrent.*;

import static com.multithreading.producerconsumer.ThreadUtil.sleep;

public class ProducerConsumerUsingBlockingQueue {
    private static final Logger log = Logger.getLogger(ProducerConsumerUsingBlockingQueue.class.getCanonicalName());
    BlockingQueue<Double> blockingQueue = new LinkedBlockingDeque<>(5);

    private void produce() {
        while (true) {
            double value = generateValue();
            try {
                blockingQueue.put(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            log.info(String.format("[%s] Value produced: %f%n", Thread.currentThread().getName(), value));
        }
    }

    private void consume() {
        while (true) {
            Double value;
            try {
                value = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            // Consume value
            log.info(String.format("[%s] Value consumed: %f%n", Thread.currentThread().getName(), value));
        }
    }

    private double generateValue() {
        return Math.random();
    }

    private void runProducerConsumer() {
        for (int i = 0; i < 2; i++) {
            Thread producerThread = new Thread(this::produce);
            producerThread.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread consumerThread = new Thread(this::consume);
            consumerThread.start();
        }
    }

    private void runProducerConsumerUsingExecutor() throws InterruptedException {
        try (var producers = Executors.newFixedThreadPool(2); var consumers = Executors.newFixedThreadPool(3)) {
            producers.submit(this::produce);

            consumers.submit(this::consume); //3 consumers
            consumers.submit(this::consume);
            consumers.submit(this::consume);

            System.out.println("stopping 001 " + producers.awaitTermination(2, TimeUnit.SECONDS));
            producers.shutdownNow(); //TODO: Instead of shutdownNow, better solution is to pass stop message to the Thread.
            System.out.println("stopping 002 " + consumers.awaitTermination(2, TimeUnit.SECONDS));
            consumers.shutdownNow();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var demo = new ProducerConsumerUsingBlockingQueue();
        //demo.runProducerConsumer();
        demo.runProducerConsumerUsingExecutor();
        // sleep(200);
        //System.exit(0);
    }
}
