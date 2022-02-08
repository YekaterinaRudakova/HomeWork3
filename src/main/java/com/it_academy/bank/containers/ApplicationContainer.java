package com.it_academy.bank.containers;

import com.it_academy.bank.DatabaseAdmin;
import com.it_academy.bank.PropertiesFileReader;
import com.it_academy.bank.models.User;

public class ApplicationContainer {
    private static ApplicationContainer applicationContainer;
    public User currentUser;
    public PropertiesFileReader propertiesFileReader;
    public DatabaseAdmin databaseAdmin;

    private ApplicationContainer() {

    }

    public static ApplicationContainer getInstance() {
        if (applicationContainer == null) {
            applicationContainer = new ApplicationContainer();
        }
        return applicationContainer;
    }
}
