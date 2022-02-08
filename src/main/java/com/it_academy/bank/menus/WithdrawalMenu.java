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

public class WithdrawalMenu implements IMenu {
    private final User user;
    private final DatabaseAdmin databaseAdmin;

    public WithdrawalMenu(User user) {
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
            String inputCurrency = ScannerReal.doScanning().trim().toUpperCase();
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
            if (BalanceChecker.isBalanceEqualOrLessThenZero(selectedAccount.getBalance())) {
                Informer.informThatUserDoesNotHaveEnoughMoney(selectedAccount.getCurrency());
                return Navigators.BACK;
            } else {
                Informer.informThatTransactionAmountShouldNotExceedMaxValue(selectedAccount.getCurrency());
                String inputWithdrawalAmount = ScannerReal.doScanning();
                double withdrawalAmount = Math.abs(Double.parseDouble(inputWithdrawalAmount));
                if (InputTransactionAmountChecker.isTransactionAmountMoreThenMax(withdrawalAmount)) {
                    Informer.informThatTransactionAmountExceedsMaxValue(selectedAccount.getCurrency());
                    return Navigators.BACK;
                }
                double balanceAfter = selectedAccount.getBalance() - withdrawalAmount;
                if (BalanceChecker.isBalanceEqualOrLessThenZero(balanceAfter)) {
                    Informer.informThatUserDoesNotHaveEnoughMoney(selectedAccount.getCurrency());
                    return Navigators.BACK;
                }
                selectedAccount.setBalance(balanceAfter);
                databaseAdmin.updateUserAccount(selectedAccount);
                int transactionId = UniqueIdGenerator.generateUniqueId();
                if (databaseAdmin.makeTransactionInDatabase(selectedAccount, transactionId, withdrawalAmount)) {
                    if (databaseAdmin.updateUserAccount(selectedAccount)) {
                        System.out.println("Ready! You have got your money.\n");
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
