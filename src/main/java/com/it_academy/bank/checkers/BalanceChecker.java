package com.it_academy.bank.checkers;

public class BalanceChecker {
    public static boolean isBalanceMoreThenMax(double balance) {
        return balance >= 2000000000;
    }

    public static boolean isBalanceEqualOrLessThenZero(double balance) {
        return balance < 0;
    }
}
