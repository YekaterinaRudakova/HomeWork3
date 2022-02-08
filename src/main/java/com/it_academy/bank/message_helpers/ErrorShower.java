package com.it_academy.bank.message_helpers;

import com.it_academy.bank.Navigators;

public class ErrorShower {
    public void showErrorMessage(Navigators error) {
        if (error == Navigators.ERROR) {
            System.out.println("You entered invalid data. Please try again.");
            return;
        }
        if (error == Navigators.WHOOPS) {
            System.out.println("Whoops! Something went wrong...");
        }
    }
}
