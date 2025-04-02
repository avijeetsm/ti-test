/*
 * User account management class
 */
package org.example;

public class UserAccount {
    private String username;
    private String email;
    private double balance;
    
    public UserAccount(String username, String email) {
        this.username = username;
        this.email = email;
        this.balance = 0.0;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        this.balance -= amount;
    }
    
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }
}
