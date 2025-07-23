package com.multithreading.story;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GrazingFieldWithLocks {

    // Shared hay stash with lock protection
    static class HayStack {
        private int hay = 100;
        private final ReentrantLock lock = new ReentrantLock();
        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // One sheep eats at a time (mutual exclusion)
        public void graze(String sheepName) {
            lock.lock(); // ReentrantLock tie the ribbon
            try {
                if (hay >= 10) {
                    System.out.println("🐑 " + sheepName + " is grazing...");
                    hay -= 10;
                    Thread.sleep(500);
                    System.out.println("🌾 " + sheepName + " finished eating. Remaining hay: " + hay);
                } else {
                    System.out.println("🐑 " + sheepName + " finds no hay left!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

        // Read-only grazing spot — sheep can observe hay levels
        public void observe(String sheepName) {
            readWriteLock.readLock().lock();
            try {
                System.out.println("👀 " + sheepName + " observes: hay available = " + hay);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    // Sheep thread that either grazes or observes
    static class Sheep extends Thread {
        private final HayStack hayStack;
        private final boolean isObserver;

        public Sheep(String name, HayStack hayStack, boolean isObserver) {
            super(name);
            this.hayStack = hayStack;
            this.isObserver = isObserver;
        }

        @Override
        public void run() {
            if (isObserver) {
                hayStack.observe(getName());
            } else {
                hayStack.graze(getName());
            }
        }
    }

    public static void main(String[] args) {
        HayStack field = new HayStack();

        // Grazers
        for (int i = 1; i <= 5; i++) {
            new Sheep("GrazingSheep-" + i, field, false).start();
        }

        // Observers
        for (int i = 1; i <= 3; i++) {
            new Sheep("ObservingSheep-" + i, field, true).start();
        }
    }
}
