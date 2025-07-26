package com.multithreading.story;

class OldHayExchangeBooth {
    public static final int CAPACITY = 5;
    private final String[] hayBuffer = new String[CAPACITY];
    private int count = 0;
    private int putIndex = 0;
    private int takeIndex = 0;

    public synchronized void putHay(String hay) throws InterruptedException {
        while (count == CAPACITY) {
            wait();
        }
        hayBuffer[putIndex] = hay;
        putIndex = (putIndex + 1) % CAPACITY;
        count++;
        System.out.println(Thread.currentThread().getName() + " produced: " + hay);
        notifyAll();
    }

    public synchronized String takeHay() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        String hay = hayBuffer[takeIndex];
        takeIndex = (takeIndex + 1) % CAPACITY;
        count--;
        notifyAll();
        return hay;
    }

    public static void main(String[] args) {
        OldHayExchangeBooth booth = new OldHayExchangeBooth();
        Thread producerSheep = new Thread(booth.new ProducerSheep(booth), "ProducerSheep");
        Thread consumerSheep = new Thread(booth.new ConsumerSheep(booth), "ConsumerSheep");

        producerSheep.start();
        consumerSheep.start();
    }

    class ConsumerSheep implements Runnable {
        private final OldHayExchangeBooth booth;

        public ConsumerSheep(OldHayExchangeBooth booth) {
            this.booth = booth;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String hay = booth.takeHay();
                    System.out.println(Thread.currentThread().getName() + " munched: " + hay);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    class ProducerSheep implements Runnable {
        private final OldHayExchangeBooth booth;

        public ProducerSheep(OldHayExchangeBooth booth) {
            this.booth = booth;
        }

        @Override
        public void run() {
            int hayCount = 0;
            try {
                while (true) {
                    String hay = "Hay#" + (++hayCount);
                    booth.putHay(hay);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}




