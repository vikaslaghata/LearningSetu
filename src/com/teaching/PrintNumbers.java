package com.teaching;

//Learn loop, inner loop, String format, comments
public class PrintNumbers {
    public static void main(String[] args) {
        System.out.println("Printing 1 to 10 numbers");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
        //-------------------------------------------------------------------
        System.out.println("Printing even numbers between 1 to 10 (inclusive) numbers");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        //-------------------------------------------------------------------
        System.out.println("Printing odd numbers between 1 to 10 (inclusive) numbers");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
        //-------------------------------------------------------------------
        System.out.println("Printing Table of 2");
        for (int i = 1; i <= 10; i++) {
            System.out.println(2 + " * " + i + " = " + (2 * i));
            //System.out.format("%d * %d = %d \n", 2, i, (2 * i));
        }
        //-------------------------------------------------------------------
        System.out.println("Printing Tables from 2 to 10");

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
            System.out.println("==========");
        }
    }
}
