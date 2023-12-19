package com.teaching;

/*
    From the mid of any palindrome string if we go to the right and left by 1 place, it’s always the same character.
    For example 12321, here mid is 3 and if we keep moving one position on both sides, we get 2 and then 1.
    We will use the same logic in our java program to find out the longest palindrome.
    However, if the palindrome length is even, the mid-size is also even. So we need to make sure in our program that this is also checked.
    For example, 12333321, here mid is 33 and if we keep moving one position in both sides, we get 3, 2 and 1.
*/
public class LongestPalindromeFinder {
    public static void main(String[] args) {
       /* System.out.println(longestPalindromeString("1234"));
        System.out.println(longestPalindromeString("12321"));
        System.out.println(longestPalindromeString("9912321456"));
        System.out.println(longestPalindromeString("9912333321456"));
        System.out.println(longestPalindromeString("12145445499"));
        System.out.println(longestPalindromeString("1223213"));*/
        System.out.println(longestPalindromeString("abb"));
    }

    static public String intermediatePalindrome(String s, int left, int right) {
        if (left > right) return null;

        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    // O(n^2)
    public static String longestPalindromeString(String s) {
        if (s == null) return null;

        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length() - 1; i++) {
            //odd cases like 121
            String palindrome = intermediatePalindrome(s, i, i);
            if (palindrome.length() > longest.length()) {
                longest = palindrome;
            }
            //even cases like 1221
            palindrome = intermediatePalindrome(s, i, i + 1);
            if (palindrome.length() > longest.length()) {
                longest = palindrome;
            }
        }
        return longest;
    }
}
