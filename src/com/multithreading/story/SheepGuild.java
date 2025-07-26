package com.multithreading.story;

import java.util.concurrent.*;

public class SheepGuild {

    // Task performed by sheep
    static class SheepTask implements Runnable {
        private final String sheepName;

        public SheepTask(String sheepName) {
            this.sheepName = sheepName;
        }

        @Override
        public void run() {
            System.out.println("🐑 " + sheepName + " completes chore at " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // ⚔️ Fixed guild – stable troop of 3 sheep
        ExecutorService fixedGuild = Executors.newFixedThreadPool(3);
        System.out.println("🏰 Fixed guild summoned:");
        for (int i = 1; i <= 6; i++) {
            fixedGuild.submit(new SheepTask("FixedSheep-" + i));
        }

        // 🪄 Cached guild – expands as needed
        ExecutorService cachedGuild = Executors.newCachedThreadPool();
        System.out.println("\n🏗️ Cached guild summoned:");
        for (int i = 1; i <= 6; i++) {
            cachedGuild.submit(new SheepTask("CachedSheep-" + i));
        }

        // 🎩 Single guild – one sheep at a time
        ExecutorService singleGuild = Executors.newSingleThreadExecutor();
        System.out.println("\n🎯 Single guild summoned:");
        for (int i = 1; i <= 3; i++) {
            singleGuild.submit(new SheepTask("SoloSheep-" + i));
        }

        // ⏰ Scheduled guild – triggers tasks at dawn (or a delay)
        ScheduledExecutorService scheduledGuild = Executors.newScheduledThreadPool(2);
        System.out.println("\n⏱️ Scheduled guild summoned:");
        scheduledGuild.schedule(() -> System.out.println("🌅 Dawn task: Feed the flock!"), 2, TimeUnit.SECONDS);
        scheduledGuild.scheduleAtFixedRate(() -> System.out.println("🔄 Maintenance sweep..."), 1, 3, TimeUnit.SECONDS);

        // Let scheduled tasks run a bit
        Thread.sleep(7000);

        // Shutdown guilds after chores
        fixedGuild.shutdown();
        cachedGuild.shutdown();
        singleGuild.shutdown();
        scheduledGuild.shutdown();
    }
}
