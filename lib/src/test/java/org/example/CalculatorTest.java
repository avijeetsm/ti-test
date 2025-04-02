/*
 * Test file for Calculator class
 */
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();
    
    @Test
    public void testAddition() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-5, 5));
        assertEquals(-8, calculator.add(-5, -3));
    }
    
    @Test
    public void testSubtraction() {
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(-8, calculator.subtract(2, 10));
        assertEquals(0, calculator.subtract(5, 5));
    }
    
    @Test
    public void testMultiplication() {
        assertEquals(15, calculator.multiply(3, 5));
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(-12, calculator.multiply(4, -3));
    }
    
    @Test
    public void testDivision() {
        assertEquals(2.5, calculator.divide(5, 2), 0.001);
        assertEquals(0.0, calculator.divide(0, 5), 0.001);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        calculator.divide(5, 0);
    }
}
