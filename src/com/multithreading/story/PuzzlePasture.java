package com.multithreading.story;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class PuzzlePasture {

    // The full puzzle consists of numbered tiles
    static class PuzzleTile extends RecursiveTask<String> {
        private final int start, end;

        public PuzzleTile(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected String compute() {
            if (end - start <= 2) {
                // Small lamb solves this fragment
                return solveTiles(start, end);
            } else {
                int mid = (start + end) / 2;

                PuzzleTile left = new PuzzleTile(start, mid);     // fork left half
                PuzzleTile right = new PuzzleTile(mid + 1, end);  // fork right half

                left.fork();                  // start asynchronously
                String rightResult = right.compute();  // solve right side now
                String leftResult = left.join();       // wait for left result

                // Join both results into one grand design
                return leftResult + " + " + rightResult;
            }
        }

        private String solveTiles(int s, int e) {
            StringBuilder builder = new StringBuilder();
            for (int i = s; i <= e; i++) {
                builder.append("[Tile ").append(i).append("] ");
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("🌟 Sheep gather to solve the ancient puzzle...");

        ForkJoinPool pasturePool = new ForkJoinPool();
        PuzzleTile fullPuzzle = new PuzzleTile(1, 10); // 10 tiles total

        String finalMap = pasturePool.invoke(fullPuzzle);

        System.out.println("\n🧩 Puzzle complete! Glowing map: \n" + finalMap);
    }
}
