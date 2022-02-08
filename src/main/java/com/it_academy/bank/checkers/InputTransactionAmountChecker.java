package com.it_academy.bank.checkers;

public class InputTransactionAmountChecker {
    public static boolean isTransactionAmountMoreThenMax(double inputTransactionAmount) {
        return inputTransactionAmount >= 100000000;
    }
}
