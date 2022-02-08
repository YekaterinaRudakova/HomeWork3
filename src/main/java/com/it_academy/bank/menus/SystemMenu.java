package com.it_academy.bank.menus;

import com.it_academy.bank.message_helpers.MenuPrinter;
import com.it_academy.bank.Navigators;
import com.it_academy.bank.models.User;
import com.it_academy.bank.utils.ScannerReal;

public class SystemMenu implements IMenu {
    private final User user;

    public SystemMenu(User user) {
        this.user = user;

    }

    @Override
    public Navigators runMenu() {
        MenuPrinter.printSystemMenu();
        String inputString = ScannerReal.doScanning();
        int result = Integer.parseInt(inputString);
        if (result == 1) {
            new MenuLoop().runLoop(new DepositMenu(user));
            return Navigators.CONTINUE;
        }
        if (result == 2) {
            new MenuLoop().runLoop(new WithdrawalMenu(user));
            return Navigators.CONTINUE;
        }
        if (result == 3) {
            new MenuLoop().runLoop(new BankAccountMenu(user));
            return Navigators.CONTINUE;
        }
        if (result == 4) {
            new MenuLoop().runLoop(new BalanceMenu(user));
            return Navigators.CONTINUE;
        }
        if (result == 5) {
            return Navigators.BACK;
        }
        return Navigators.CONTINUE;
    }
}
