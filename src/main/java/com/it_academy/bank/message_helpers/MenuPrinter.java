package com.it_academy.bank.message_helpers;

public class MenuPrinter {
    public static void printGreeting() {
        System.out.println("Hello!");
        System.out.println("Enter your name: ");
    }

    public static void printEntranceMenu() {
        System.out.println("1-Log in");
        System.out.println("2-Registration");
        System.out.println("3-Exit");
    }

    public static void printLoginMenu() {
        System.out.println("Choose option for continue:");
        System.out.println("1-Ready to input user ID");
        System.out.println("2-Back");
    }

    public static void printSystemMenu() {
        System.out.println("Please choose the next option:");
        System.out.println("1-Make deposit");
        System.out.println("2-Withdrawal money");
        System.out.println("3-Create bank account");
        System.out.println("4-Check balance");
        System.out.println("5-back");
    }
}
