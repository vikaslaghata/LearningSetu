package com.multithreading.story;

public class DaemonSheepPasture {

    // Daemon sheep that run quietly in the background
    static class DaemonSheep extends Thread {
        public DaemonSheep(String name) {
            super(name);
            setDaemon(true); // 🕯️ Set thread as daemon
        }
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(getName() + " tidies the pasture...");
                    Thread.sleep(500); // Continues to clean or whisper dreams
                }
            } catch (InterruptedException e) {
                System.out.println(getName() + " is interrupted.");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        // Start a few daemon sheep
        for (int i = 1; i <= 3; i++) {
            new DaemonSheep("DaemonSheep-" + i).start();
        }

        System.out.println("🌙 Javin is tending to the pasture for a while...");
        Thread.sleep(2000); // Let daemon sheep work

        System.out.println("😴 Javin goes to sleep. Daemon sheep vanish...");
        // JVM shuts down — daemon threads disappear automatically
    }
}
