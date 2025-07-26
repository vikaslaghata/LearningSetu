package com.multithreading.story;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OracleOfAsyncMeadows {

    // Simulates asking the oracle if hay will arrive
    private static CompletableFuture<String> askOracle(String sheepName) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🔮 " + sheepName + " asks the Oracle for hay...");
            try {
                Thread.sleep(500); // Simulate delay
            } catch (InterruptedException e) {
                throw new RuntimeException("Oracle fell asleep!");
            }
            return "🌾 Hay ready for " + sheepName;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 🐑 Sheep who plans to eat once hay is ready
        CompletableFuture<String> napAndEat = askOracle("SleepySheep")
                .thenApply(hay -> hay + " — after nap 💤");

        // 🐑 Sheep who combines meals from two oracles
        CompletableFuture<String> mealOne = askOracle("GourmetSheep1");
        CompletableFuture<String> mealTwo = askOracle("GourmetSheep2");

        CompletableFuture<String> feastBouquet = mealOne
                .thenCombine(mealTwo, (m1, m2) -> "🍽️ Feast: [" + m1 + "] & [" + m2 + "]");

        // 🐑 Oracle gets grumpy (simulate error)
        CompletableFuture<String> riskyOracle = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Grumpy Oracle!");
        }).exceptionally(error -> "🧃 Summoned backup berries due to error: " + error.getMessage());

        // 🐑 Brave sheep ventures far into async caves
        CompletableFuture<Void> asyncAdventurer = askOracle("BraveSheep")
                .thenAccept(hay -> System.out.println("🌄 Returned from async caves with: " + hay));

        // 🧾 Print results
        System.out.println(napAndEat.get());
        System.out.println(feastBouquet.get());
        System.out.println(riskyOracle.get());

        // Let async adventurer complete
        asyncAdventurer.get();
    }
}
