package com.multithreading.story;

public class ThreadLocalPotions {

    // ThreadLocal scroll — each sheep/thread gets a custom potion
    private static final ThreadLocal<String> potionScroll = ThreadLocal.withInitial(() -> {
        return "🧪 Potion for " + Thread.currentThread().getName();
    });

    // Each sheep prepares its potion
    static class Sheep extends Thread {
        public Sheep(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(getName() + " brews: " + potionScroll.get());

            // Simulate sheep updating their potion recipe
            potionScroll.set("🌿 Allergy-safe potion for " + getName());

            System.out.println(getName() + " updates scroll to: " + potionScroll.get());
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new Sheep("Sheep-" + i).start();
        }
    }
}
