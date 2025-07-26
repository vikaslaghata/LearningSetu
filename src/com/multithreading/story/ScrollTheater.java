package com.multithreading.story;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class ScrollTheater {

    // 📉 Fail-Fast: Ancient scrolls stored in ArrayList
    public static void performFailFast() {
        List<String> scrolls = new ArrayList<>();
        scrolls.add("Scene 1");
        scrolls.add("Scene 2");
        scrolls.add("Scene 3");

        try {
            Iterator<String> iterator = scrolls.iterator();
            while (iterator.hasNext()) {
                String scene = iterator.next();
                System.out.println("🎬 Sheep reads: " + scene);

                // One mischievous sheep edits mid-performance!
                scrolls.add("New Scene"); // Causes ConcurrentModificationException
            }
        } catch (Exception e) {
            System.out.println("🚨 Panic in amphitheater: " + e);
        }
    }

    // ✅ Fail-Safe: Magical CopyScrolls using CopyOnWriteArrayList
    public static void performFailSafe() {
        CopyOnWriteArrayList<String> scrolls = new CopyOnWriteArrayList<>();
        scrolls.add("Scene A");
        scrolls.add("Scene B");
        scrolls.add("Scene C");

        for (String scene : scrolls) {
            System.out.println("🎭 Sheep performs: " + scene);

            // Edits happen safely — old iterator sees original copy!
            scrolls.add("New Scene");
        }

        System.out.println("✅ No panic! Final scrolls: " + scrolls);
    }
    public static void main(String[] args) {
        System.out.println("🔍 Rehearsal with Fail-Fast scrolls:");
        performFailFast();

        System.out.println("\n✨ Rehearsal with Fail-Safe CopyScrolls:");
        performFailSafe();
    }
}