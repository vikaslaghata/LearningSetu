package com.algo;

/*
Strings are anagram if they are created by same characters.
Solution: sort characters and compare them.
Another solution: assign prime number to each character for example: A-2, B-3, C-5 and so on. Then multiply those.
Results should be same in both the Strings. Take care of int overflow.
*/

import java.util.Arrays;

public class AnagramCheck {
    public static void main(String[] args) {
        String input1 = "stop";
        String input2 = "pots";
        var charArray1 = input1.toLowerCase().toCharArray();
        var charArray2 = input2.toLowerCase().toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        input1 = new String(charArray1);
        input2 = new String(charArray2);
        String result = (input1.equals(input2)) ? "Both the strings are anagram" : "Strings are not anagram";
        System.out.println(result);

    }
}
