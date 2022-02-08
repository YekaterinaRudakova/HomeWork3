package com.it_academy.bank.menus;

import com.it_academy.bank.*;
import com.it_academy.bank.checkers.BalanceChecker;
import com.it_academy.bank.checkers.InputTransactionAmountChecker;
import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.message_helpers.Informer;
import com.it_academy.bank.models.Account;
import com.it_academy.bank.models.User;
import com.it_academy.bank.utils.ScannerReal;
import com.it_academy.bank.utils.UniqueIdGenerator;

public class DepositMenu implements IMenu {

    private final User user;
    private final DatabaseAdmin databaseAdmin;

    public DepositMenu(User user) {
        this.user = user;
        databaseAdmin = ApplicationContainer.getInstance().databaseAdmin;
    }

    @Override
    public Navigators runMenu() {
        Account[] userAccounts = databaseAdmin.getUserAccounts(user);
        if (userAccounts == null || userAccounts.length == 0) {
            Informer.informToCreateAccount();
            return Navigators.BACK;
        }
        Informer.informToChooseAccount();
        for (Account item : userAccounts) {
            System.out.println("Account ID is " + item.getAccountId() + " in " + item.getCurrency());
        }
        try {
            String inputCurrency = ScannerReal.doScanning();
            Account selectedAccount = null;
            for (int i = 0; i <= userAccounts.length; i++) {
                if (userAccounts[i].getCurrency().equals(inputCurrency)) {
                    selectedAccount = userAccounts[i];
                    break;
                }
            }
            if (selectedAccount == null) {
                Informer.informThatAccountIsNotFound();
                return Navigators.CONTINUE;
            }
            if (BalanceChecker.isBalanceMoreThenMax(selectedAccount.getBalance())) {
                Informer.informThatUserCannotMakeTransaction(selectedAccount.getCurrency());
                return Navigators.CONTINUE;
            } else {
                Informer.informThatTransactionAmountShouldNotExceedMaxValue(selectedAccount.getCurrency());
                String inputDepositAmount = ScannerReal.doScanning();
                double depositAmount = Math.abs(Double.parseDouble(inputDepositAmount));
                if (InputTransactionAmountChecker.isTransactionAmountMoreThenMax(depositAmount)) {
                    Informer.informThatTransactionAmountExceedsMaxValue(selectedAccount.getCurrency());
                    return Navigators.CONTINUE;
                }
                double balanceAfter = selectedAccount.getBalance() + depositAmount;
                if (BalanceChecker.isBalanceMoreThenMax(balanceAfter)) {
                    Informer.informThatBalanceExceedsMaxValue(selectedAccount.getCurrency());
                    return Navigators.CONTINUE;
                }
                selectedAccount.setBalance(balanceAfter);
                databaseAdmin.updateUserAccount(selectedAccount);
                int transactionId = UniqueIdGenerator.generateUniqueId();
                if (databaseAdmin.makeTransactionInDatabase(selectedAccount, transactionId, depositAmount)) {
                    if (databaseAdmin.updateUserAccount(selectedAccount)) {
                        System.out.println("Ready! The money is in your account.\n");
                        return Navigators.BACK;
                    }
                    return Navigators.CONTINUE;
                }
            }
        } catch (Exception ex) {
            return Navigators.ERROR;
        }
        return Navigators.CONTINUE;
    }
}
