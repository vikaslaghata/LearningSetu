package com.multithreading.story;

public class DreamyPastureVirtualSheep {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("💤 Javin drifts into a dreamy pasture...");

        // Summoning a massive virtual flock
        for (int i = 1; i <= 10_000; i++) {
            Thread.startVirtualThread(() -> {
                System.out.println("🫧 VirtualSheep is grazing without stomping...");
            });
        }

        // Let the sheep graze for a bit
        Thread.sleep(2000);
        System.out.println("🌙 Moonlight glows over the silent pasture...");
    }
}
