package com.it_academy.bank;

import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.menus.IMenu;
import com.it_academy.bank.menus.MainMenu;
import com.it_academy.bank.menus.MenuLoop;

import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws SQLException {
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader();
        propertiesFileReader.initialize();
        ApplicationContainer.getInstance().propertiesFileReader = propertiesFileReader;

        DatabaseAdmin databaseAdmin = new DatabaseAdmin();
        ApplicationContainer.getInstance().databaseAdmin = databaseAdmin;

        IMenu menu = new MainMenu();
        new MenuLoop().runLoop(menu);

        propertiesFileReader.finish();
        databaseAdmin.finish();
    }
}
