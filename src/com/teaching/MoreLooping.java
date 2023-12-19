package com.teaching;

//More looping. camel case
public class MoreLooping {
    public static void main(String[] args) {
        System.out.println("Calculating x to power y : ");
        int x = 2;
        int y = 3;
        int result = 1;
        for (int i = 1; i <= y; i++) {
            result *= x;
        }
        System.out.println("Result= " + result);
        //-------------------------------------------------------------------
        System.out.println("Calculating sum of 10 natural numbers : ");
        int naturalNumberSum = 0;
        for (int i = 1; i <= 10; i++) {
            naturalNumberSum += i;
        }
        System.out.println("Natural number sum is= " + naturalNumberSum);

        //-------------------------------------------------------------------
        System.out.println("Creating star pyramid of given height : ");
        int height = 5;
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }

        //-------------------------------------------------------------------
        System.out.println("Creating star pyramid of given height in opposite direction: ");
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j < (height - i); j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }

    }
}
