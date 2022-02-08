package com.it_academy.bank.menus;

import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.message_helpers.MenuPrinter;
import com.it_academy.bank.Navigators;
import com.it_academy.bank.utils.ScannerReal;

public class MainMenu implements IMenu {
    private boolean isFirstView = true;
    private String scannedName;

    @Override
    public Navigators runMenu() {
        if (isFirstView) {
            MenuPrinter.printGreeting();
            scannedName = ScannerReal.doScanning();
            isFirstView = false;
        }

        System.out.println(scannedName + " please select option:");
        MenuPrinter.printEntranceMenu();

        String userInput = ScannerReal.doScanning();
        int menuNumber = Integer.parseInt(userInput);

        Navigators navigators;
        if (menuNumber == 1) {
            navigators = new MenuLoop().runLoop(new LoginMenu());
            if (navigators == Navigators.SYSTEM_MENU) {
                menuNumber = 421;
            } else {
                return Navigators.CONTINUE;
            }
        }
        if (menuNumber == 2) {
            RegistrationMenu menu = new RegistrationMenu();
            navigators = new MenuLoop().runLoop(menu);
            if (navigators == Navigators.SYSTEM_MENU) {
                menuNumber = 421;
            } else {
                return Navigators.CONTINUE;
            }
        }
        if (menuNumber == 3) {
            return Navigators.BACK;
        }
        if (menuNumber == 421) {
            new MenuLoop().runLoop(new SystemMenu(ApplicationContainer.getInstance().currentUser));
            ApplicationContainer.getInstance().currentUser = null;
            return Navigators.CONTINUE;
        }
        return Navigators.ERROR;
    }
}
