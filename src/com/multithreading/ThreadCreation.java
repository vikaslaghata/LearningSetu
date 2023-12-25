package com.multithreading;

import java.util.List;
import java.util.concurrent.*;

public class ThreadCreation {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //---------------------------------------
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Inside t1 thread");
            }
        };

        t1.start();
        //---------------------------------------
        var t2 = new MyTask();
        new Thread(t2).start();

        //---------------------------------------
        Runnable t3 = () -> { //var will not work here.
            System.out.println("Inside t3 thread");
        };
        new Thread(t3).start();
        //---------------------------------------

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.submit(() -> System.out.println("Hello from " + Thread.currentThread().getName()));
            // executor.shutdown(); // try with resources should take care of this.
        }

        //---------------------------------------
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        try (ExecutorService executor = Executors.newFixedThreadPool(1)) {
            Future<Integer> future = executor.submit(task);

            System.out.println("future done? " + future.isDone());

            Integer result = future.get();

            System.out.println("future done? " + future.isDone());
            System.out.println("result: " + result);

            executor.shutdown();
        }

        //---------------------------------------
        List<Callable<String>> callables = List.of(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        try (ExecutorService executor = Executors.newWorkStealingPool()) {
            executor.invokeAll(callables)// invokeAny() also can be use, only difference is instead of returning future objects invokeAny blocks until the first callable terminates and returns the result of that callable.
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        }

        //---------------------------------------

        Runnable task1 = () -> System.out.println("Scheduling task: " + System.nanoTime());
        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(1)) {
            ScheduledFuture<?> future = executor.schedule(task1, 3, TimeUnit.SECONDS);

            TimeUnit.MILLISECONDS.sleep(1337);

            long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS); //After this delay has elapsed the task will be executed concurrently.
            System.out.printf("Remaining Delay: %sms \n", remainingDelay);

            //to schedule tasks to be executed periodically, executors provide the two methods
            int initialDelay = 0;
            int period = 1;
            executor.scheduleAtFixedRate(task1, initialDelay, period, TimeUnit.SECONDS); //This method doesn't take into account the actual duration of the task. So if you specify a period of one second but the task needs 2 seconds to be executed then the thread pool will work to capacity very soon.

            executor.scheduleWithFixedDelay(task1, 1, period, TimeUnit.SECONDS); //This method is handy if you cannot predict the duration of the scheduled tasks.
        }
    }
}

class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Doing some task");
    }
}
