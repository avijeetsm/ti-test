/*
 * Test file for StringUtils class
 */
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {
    private final StringUtils stringUtils = new StringUtils();
    
    @Test
    public void testReverse() {
        assertEquals("olleh", stringUtils.reverse("hello"));
        assertEquals("", stringUtils.reverse(""));
        assertEquals(null, stringUtils.reverse(null));
        assertEquals("a", stringUtils.reverse("a"));
    }
    
    @Test
    public void testIsPalindrome() {
        assertTrue(stringUtils.isPalindrome("madam"));
        assertTrue(stringUtils.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(stringUtils.isPalindrome(""));
        assertTrue(stringUtils.isPalindrome(null));
        assertFalse(stringUtils.isPalindrome("hello"));
        assertTrue(stringUtils.isPalindrome("Able was I ere I saw Elba"));
    }
    
    @Test
    public void testCountOccurrences() {
        assertEquals(3, stringUtils.countOccurrences("banana", "a"));
        assertEquals(3, stringUtils.countOccurrences("hello world", "l"));
        assertEquals(1, stringUtils.countOccurrences("hello world", "world"));
        assertEquals(0, stringUtils.countOccurrences("hello", "z"));
        assertEquals(0, stringUtils.countOccurrences(null, "a"));
        assertEquals(0, stringUtils.countOccurrences("hello", null));
        assertEquals(0, stringUtils.countOccurrences("hello", ""));
    }
}
