package com.it_academy.bank.menus;

import com.it_academy.bank.*;
import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.message_helpers.MenuPrinter;
import com.it_academy.bank.models.User;
import com.it_academy.bank.utils.ScannerReal;

public class LoginMenu implements IMenu {
    private final ApplicationContainer container;
    private final User user;
    private final DatabaseAdmin databaseAdmin;

    public LoginMenu() {
        container = ApplicationContainer.getInstance();
        user = new User();
        databaseAdmin = ApplicationContainer.getInstance().databaseAdmin;
    }

    @Override
    public Navigators runMenu() {
        MenuPrinter.printLoginMenu();
        String resultString = ScannerReal.doScanning();
        int result = Integer.parseInt(resultString);
        if (result == 1) {
            System.out.println("Please enter your user ID");
            String inputId = ScannerReal.doScanning();
            int userId = Integer.parseInt(inputId);
            if (databaseAdmin.isUserIdExist(userId)) {
                user.setUserId(userId);
                container.currentUser = user;
                return Navigators.SYSTEM_MENU;
            } else {
                System.out.println("This user ID does not exist in our system!\n");
                return Navigators.BACK;
            }
        }
        if (result == 2) {
            return Navigators.BACK;
        }
        return Navigators.ERROR;
    }
}
