package com.teaching;

import java.util.HashSet;

public class Permutation {
    public static void main(String[] args) {
        String input = "abc";
        System.out.println("First solution using iteration :");
        var results = new HashSet<String>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                //TODO: remove redundant iterations
                String result = swap(input.toCharArray(), i, j);
                var buffer = new StringBuilder(result);
                results.add(result);
                results.add(buffer.reverse().toString());
            }
        }
        System.out.println("Result= " + results);
        //-------------------------------------------------------------------
        System.out.println("Second solution using recursive :");
        solve(input.toCharArray(), 0);
    }

    private static String swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;

        return new String(charArray);
    }

    public static void solve(char[] arr, int idx) {
        if (idx == arr.length - 1) { //Base condition of recursion
            System.out.print(String.valueOf(arr) + " ");
        }

        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            solve(arr, idx + 1);
            swap(arr, idx, i);
            //Backtracking: reverting all the elements to their original places
        }
    }
}
