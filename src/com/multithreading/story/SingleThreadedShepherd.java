package com.multithreading.story;

public class SingleThreadedShepherd {
    public void takeCareOfSheep() {
        System.out.println("🌞 Javin wakes up.");
        System.out.println("🚶 Leading sheep through the field...");
        rest();          // taking a break
        feedSheep();     // feeding time
        System.out.println("🛌 Javin rests under the sun.");
    }
    private void rest() {
        System.out.println("😌 Taking a break...");
        sleepForMoment();
    }
    private void feedSheep() {
        System.out.println("🥣 Feeding the sheep...");
        sleepForMoment();
    }
    private void sleepForMoment() {
        try {
            Thread.sleep(1000); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) {
        SingleThreadedShepherd javin = new SingleThreadedShepherd();
        javin.takeCareOfSheep();  // Everything runs sequentially, one step at a time.
    }
}
