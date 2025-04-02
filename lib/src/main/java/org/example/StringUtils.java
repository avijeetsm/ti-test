/*
 * Utility class for string operations
 */
package org.example;

public class StringUtils {
    /**
     * Reverses the given string
     */
    public String reverse(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }
    
    /**
     * Checks if the string is a palindrome (reads the same forward and backward)
     */
    public boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }
        
        String normalized = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        return normalized.equals(reverse(normalized));
    }
    
    /**
     * Counts the number of occurrences of a substring in a string
     */
    public int countOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }
}
