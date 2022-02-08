package com.it_academy.bank.models;

public class Account {
    private int accountId;
    private User user;
    private double balance;
    private String currency;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "com.it_academy.bank.models.Account{" +
                "accountId=" + accountId +
                ", user=" + user +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
