package com.teaching;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter any string :");
        String inputString = input.nextLine();

        //String palindrome check
        String result = inputString + " is a palindrome";
        for (int i = 0; i < (inputString.length() / 2); i++) {
            if (inputString.charAt(i) != inputString.charAt(inputString.length() - 1 - i)) {
                result = inputString + " is not a palindrome";
                break;
            }
        }
        System.out.println(result);

        //-------------------------------------------------------------------
        //String palindrome using recursion
        boolean isPalindrome = checkIfPalindrome(inputString, 0, inputString.length() - 1);
        result = isPalindrome ? inputString + " is a palindrome" : inputString + " is not a palindrome";
        System.out.println(result);
        
        //-------------------------------------------------------------------
        System.out.println("Enter any integer :");
        int inputInt = input.nextInt();
        int original = inputInt;
        int reverse = 0;
        while (original > 0) {
            int lastDigit = original % 10;
            reverse = reverse * 10 + lastDigit;
            original = original / 10;
        }
        result = (inputInt == reverse) ? inputInt + " is a palindrome" : inputInt + " is not a palindrome";
        System.out.println(result);

    }

    private static boolean checkIfPalindrome(String inputString, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (inputString.charAt(start) != inputString.charAt(end)) {
            return false;
        }
        return checkIfPalindrome(inputString, start + 1, end - 1);
    }
}
