package com.it_academy.bank.menus;

import com.it_academy.bank.*;
import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.message_helpers.NewUserRegister;
import com.it_academy.bank.models.User;
import com.it_academy.bank.utils.ScannerReal;
import com.it_academy.bank.utils.UniqueIdGenerator;

public class RegistrationMenu implements IMenu {
    private final NewUserRegister newUserRegister;
    private final DatabaseAdmin databaseAdmin;
    private final User user;

    public RegistrationMenu() {
        user = new User();
        newUserRegister = new NewUserRegister();
        databaseAdmin = ApplicationContainer.getInstance().databaseAdmin;
    }

    @Override
    public Navigators runMenu() {
        System.out.println("Choose option for continue:");
        System.out.println("1-Input personal data");
        System.out.println("2-Back");

        String resultString = ScannerReal.doScanning();
        int result = Integer.parseInt(resultString);

        if (result == 1) {
            newUserRegister.askNewUserName();
            String inputUserName = ScannerReal.doScanning().trim();
            user.setUserName(inputUserName);
            if (databaseAdmin.isUserAlreadyRegistered(user)) {
                System.out.println("You are already in our system!\n ");
                return Navigators.BACK;
            }

            newUserRegister.askNewUserAddress();
            String inputUserAddress = ScannerReal.doScanning().trim();
            if (newUserRegister.isUserSkipToEnterAddress(inputUserAddress)) {
                user.setUserAddress(null);
            }
            user.setUserAddress(inputUserAddress);
            int generatedUserId = UniqueIdGenerator.generateUniqueId();
            user.setUserId(generatedUserId);

            databaseAdmin.inputNewUserInDatabase(generatedUserId, user.getUserName(), user.getUserAddress());
            System.out.println("Greeting! " + user.getUserName() + " are our client now! Your userId in our system is " + generatedUserId);
            ApplicationContainer.getInstance().currentUser = user;
            return Navigators.SYSTEM_MENU;
        }
        if (result == 2) {
            return Navigators.BACK;
        }
        return Navigators.ERROR;
    }
}
