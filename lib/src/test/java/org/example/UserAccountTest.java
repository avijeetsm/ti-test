/*
 * Test file for UserAccount class
 */
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserAccountTest {
    
    @Test
    public void testUserCreation() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals(0.0, user.getBalance(), 0.001);
    }
    
    @Test
    public void testDeposit() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.deposit(100.0);
        assertEquals(100.0, user.getBalance(), 0.001);
        
        user.deposit(50.5);
        assertEquals(150.5, user.getBalance(), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDeposit() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.deposit(-10.0);
    }
    
    @Test
    public void testWithdraw() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.deposit(100.0);
        user.withdraw(40.0);
        assertEquals(60.0, user.getBalance(), 0.001);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testInsufficientFunds() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.deposit(50.0);
        user.withdraw(100.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeWithdrawal() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.withdraw(-10.0);
    }
    
    @Test
    public void testIsValidEmail() {
        UserAccount user1 = new UserAccount("user1", "valid@example.com");
        assertTrue(user1.isValidEmail());
        
        UserAccount user2 = new UserAccount("user2", "invalid@example");
        assertFalse(user2.isValidEmail());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidEmail() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.setEmail("invalid");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyUsername() {
        UserAccount user = new UserAccount("testuser", "test@example.com");
        user.setUsername("");
    }
}
