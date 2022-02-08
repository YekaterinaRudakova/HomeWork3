package com.it_academy.bank.menus;

import com.it_academy.bank.*;
import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.models.Account;
import com.it_academy.bank.models.User;
import com.it_academy.bank.utils.ScannerReal;

public class BalanceMenu implements IMenu {
    private final User user;
    private final DatabaseAdmin databaseAdmin;

    public BalanceMenu(User user) {
        this.user = user;
        databaseAdmin = ApplicationContainer.getInstance().databaseAdmin;
    }

    @Override
    public Navigators runMenu() {
        Account[] userAccounts = databaseAdmin.getUserAccounts(user);
        if (userAccounts == null) {
            System.out.println("You do not have account. Please create account for continue.\n");
            return Navigators.BACK;
        }
        System.out.println("Please choose bank account:");
        for (Account item : userAccounts) {
            System.out.println("Account ID is " + item.getAccountId() + " in " + item.getCurrency());
        }
        try {
            String inputCurrency = ScannerReal.doScanning().trim().toUpperCase();
            Account selectedAccount = null;
            for (int i = 0; i <= userAccounts.length; i++) {
                if (userAccounts[i].getCurrency().equals(inputCurrency)) {
                    selectedAccount = userAccounts[i];
                    break;
                }
            }
            if (selectedAccount == null) {
                System.out.println("Account is not found. Please try again.\n");
                return Navigators.BACK;
            }
            System.out.printf("Balance is %.3f", selectedAccount.getBalance());
            System.out.print(" " + selectedAccount.getCurrency() + "\n");
        } catch (Exception ex) {
            return Navigators.ERROR;
        }
        return Navigators.BACK;
    }
}
