package com.it_academy.bank.menus;

import com.it_academy.bank.*;
import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.models.Account;
import com.it_academy.bank.models.User;
import com.it_academy.bank.utils.ScannerReal;
import com.it_academy.bank.utils.UniqueIdGenerator;

public class BankAccountMenu implements IMenu {
    private final User user;
    private final DatabaseAdmin databaseAdmin;

    public BankAccountMenu(User user) {
        this.user = user;
        databaseAdmin = ApplicationContainer.getInstance().databaseAdmin;
    }

    @Override
    public Navigators runMenu() {
        System.out.println("Please enter currency for new account.");
        String inputCurrency = ScannerReal.doScanning().trim().toUpperCase();
        Account[] userAccounts = databaseAdmin.getUserAccounts(user);
        Account accountInSelectedCurrency = null;
        if (userAccounts != null) {
            for (Account userAccount : userAccounts) {
                if (userAccount.getCurrency().equals(inputCurrency)) {
                    accountInSelectedCurrency = userAccount;
                    break;
                }
            }
        }
        if (accountInSelectedCurrency == null) {
            int accountId = UniqueIdGenerator.generateUniqueId();
            if (databaseAdmin.createUserAccount(accountId, user.getUserId(), inputCurrency)) {
                System.out.println("Your account in " + inputCurrency + " is ready!");
                System.out.println("Your account ID is " + accountId);
                return Navigators.BACK;
            }
            return Navigators.ERROR;
        } else {
            System.out.println("You already have bank account in " + inputCurrency);
            return Navigators.BACK;
        }
    }
}
