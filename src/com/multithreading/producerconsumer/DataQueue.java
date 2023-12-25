package com.multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    private final Queue<Message> queue = new LinkedList<>();
    private final int maxSize;
    private final Object IS_NOT_FULL = new Object();
    private final Object IS_NOT_EMPTY = new Object();

    DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull() {
        return queue.size() == maxSize;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    //Call from Producer if the queue is already full and needs to wait
    public void waitIsNotFull() throws InterruptedException {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.wait();
        }
    }

    public void waitIsNotEmpty() throws InterruptedException {
        synchronized (IS_NOT_EMPTY) {
            IS_NOT_EMPTY.wait();
        }
    }

    public void add(Message message) {
        queue.add(message);
        notifyIsNotEmpty();
    }

    public Message poll() {
        Message mess = queue.poll();
        notifyIsNotFull();
        return mess;
    }

    public Integer getSize() {
        return queue.size();
    }

    //notification that queue has space and new item can produce.
    private void notifyIsNotFull() {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.notify();
        }
    }

    private void notifyIsNotEmpty() {
        synchronized (IS_NOT_EMPTY) {
            IS_NOT_EMPTY.notify();
        }
    }
}
