package com.it_academy.bank.message_helpers;

public class Informer {
    public static void informToCreateAccount() {
        System.out.println("You do not have account. Please create account for continue.\n");
    }

    public static void informToChooseAccount() {
        System.out.println("Please choose bank account by entering currency:");
    }

    public static void informThatAccountIsNotFound() {
        System.out.println("Account is not found. Please try again.\n");
    }

    public static void informThatUserCannotMakeTransaction(String currency) {
        System.out.println("You cannot make transaction on this bank account!");
        System.out.println("Balance exceeds 2.000.000.000 " + currency);
        System.out.println("Please try again.\n");
    }

    public static void informThatTransactionAmountShouldNotExceedMaxValue(String currency) {
        System.out.println("Please enter transaction amount. It should not exceed 100.000.000 " + currency);
    }

    public static void informThatTransactionAmountExceedsMaxValue(String currency) {
        System.out.println("Transaction amount exceeds 100.000.000 " + currency);
    }

    public static void informThatBalanceExceedsMaxValue(String currency) {
        System.out.println("You cannot make transaction on this bank account!");
        System.out.println("Balance will exceed 2.000.000.000 " + currency);
        System.out.println("Please try again.\n");
    }

    public static void informThatUserDoesNotHaveEnoughMoney(String currency) {
        System.out.println("You cannot make transaction on this bank account!");
        System.out.println("You do not have enough money on the account in " + currency);
        System.out.println("Please try again.\n");
    }
}
