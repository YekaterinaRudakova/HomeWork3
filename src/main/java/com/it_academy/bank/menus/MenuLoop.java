package com.it_academy.bank.menus;

import com.it_academy.bank.message_helpers.ErrorShower;
import com.it_academy.bank.Navigators;

public class MenuLoop {
    public Navigators runLoop(IMenu menu) {
        for (; ; ) {
            try {
                Navigators navigators = menu.runMenu();
                if (navigators == Navigators.CONTINUE) {
                    continue;
                }
                if (navigators == Navigators.ERROR) {
                    new ErrorShower().showErrorMessage(navigators);
                    continue;
                }
                if (navigators == Navigators.BACK) {
                    return navigators;
                }
                return navigators;
            } catch (Exception exception) {
                new ErrorShower().showErrorMessage(Navigators.WHOOPS);
            }
        }
    }
}
