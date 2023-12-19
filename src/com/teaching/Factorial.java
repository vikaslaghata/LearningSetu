package com.teaching;

public class Factorial {
    public static void main(String[] args) {
        int number = 4; // factorial = 4 * 3 * 2 * 1 = 24

        //First solution
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }

        System.out.println("Using for loop, factorial of " + number + " is " + factorial);

        //Second solution using while
        factorial = 1;
        int count = number;
        while (count > 1) {
            factorial *= count;
            count--;
        }
        System.out.println("Using while loop, factorial of " + number + " is " + factorial);

        //Third solution using recursion
        factorial = calculateFactorial(number);
        System.out.println("Using recursion, factorial of " + number + " is " + factorial);

    }

    private static int calculateFactorial(int number) {
        if (number <= 1) {
            return 1;
        }
        return number * calculateFactorial(number - 1);
    }
}
