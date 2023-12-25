package com.multithreading;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/*
Benefits:
1. A synchronized block is fully contained within a method. While Lock APIs lock() and unlock() operation can be in separate methods.
2. We can achieve fairness within the Lock APIs by specifying the fairness property. It makes sure that the longest waiting thread is given
access to the lock.
3. A thread gets blocked if it can’t get access to the synchronized block. The Lock API provides tryLock() method.
The thread acquires lock only if it’s available and not held by any other thread. This reduces blocking time of thread waiting for the lock.
4. A thread that is in waiting state to acquire the access to synchronized block can’t be interrupted. The Lock API provides a method
lockInterruptibly() that can be used to interrupt the thread when it’s waiting for the lock.
5. In addition to the Lock interface, we have a ReadWriteLock interface that maintains a pair of locks, one for read-only operations and
one for the write operation. The read lock may be simultaneously held by multiple threads as long as there is no write.
 */
public class Locks {
    //--------Reentrant lock-------------------------------------------
    Lock reentrantLock = new ReentrantLock(); // we can provide fairness true/false here but that's not guarantee because higher priority thread can get CPU by native thread scheduler.
    int counter = 0;
    //------- Read and Write lock------------------------------------------
    Map<String, String> syncHashMap = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();//read lock may be held simultaneously by multiple reader threads, so long as there are no writers.

    //------------------------------------------------------------
    public static void main(String[] args) {
        //Create threads
    }

    public void perform() throws InterruptedException {
        reentrantLock.lock(); //Acquire the lock if it's available. If the lock isn't available, a thread gets blocked until the lock is released.

        reentrantLock.lockInterruptibly();//Acquires the lock unless the current thread is interrupted. Acquires the lock if it is available and returns immediately. If lock is not available then wait until ether lock not acquired or not interrupted by some other thread.
        try {
            counter++;
        } finally {
            reentrantLock.unlock(); // wrapping the lock() and the unlock() calls in the try-finally block to avoid the deadlock situations.
        }
    }

    public void performTryLock() throws InterruptedException {
        boolean isLockAcquired = reentrantLock.tryLock(1, TimeUnit.SECONDS);//Acquires the lock if it is free within the given waiting time and the current thread has not been interrupted.

        if (isLockAcquired) {
            try {
                //Critical section here
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void put(String key, String value) {
        try {
            writeLock.lock();
            syncHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        try {
            readLock.lock();
            return syncHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }
}

/*
Condition class provides the ability for a thread to wait for some condition to occur while executing the critical section.
This can occur when a thread acquires the access to the critical section but doesn’t have the necessary condition to perform its operation.
For example, a reader thread can get access to the lock of a shared queue that still doesn’t have any data to consume.

Traditionally Java provides wait(), notify() and notifyAll() methods for thread intercommunication.

Conditions have similar mechanisms, but we can also specify multiple conditions:
 */
class ReentrantLockWithCondition {

    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item) throws InterruptedException {
        try {
            lock.lock();
            while (stack.size() == CAPACITY) {
                stackFullCondition.await();
            }
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() throws InterruptedException {
        try {
            lock.lock();
            while (stack.isEmpty()) {
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}
