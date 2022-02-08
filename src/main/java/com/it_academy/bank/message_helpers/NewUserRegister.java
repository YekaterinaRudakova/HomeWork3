package com.it_academy.bank.message_helpers;

public class NewUserRegister {

    public void askNewUserName() {
        System.out.println("Please enter your name for registration: ");
    }

    public void askNewUserAddress() {
        System.out.println("Enter your actual address.");
        System.out.println("Please input \"skip\" if you want to skip this step.");
    }

    public boolean isUserSkipToEnterAddress(String answerAboutAddress) {
        return answerAboutAddress.equals("skip");
    }
}
