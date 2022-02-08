package com.it_academy.bank;

import com.it_academy.bank.containers.ApplicationContainer;
import com.it_academy.bank.models.Account;
import com.it_academy.bank.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdmin {
    private final PropertiesFileReader propertiesFileReader;
    private final Connection connection;
    private final Statement statement;

    public DatabaseAdmin() throws SQLException {
        propertiesFileReader = ApplicationContainer.getInstance().propertiesFileReader;
        connection = DriverManager.getConnection(propertiesFileReader.readProperties("DATABASE_URL"));
        statement = connection.createStatement();
    }

    public boolean isJdBSDriverExist() {
        try {
            Class.forName(propertiesFileReader.readProperties("JDBC_DRIVER"));
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage() + " JDBС driver is not found.");
            return false;
        }
    }

    public void finish() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
        }
    }

    public void inputNewUserInDatabase(int userId, String userName, String userAddress) {
        try {
            if (isJdBSDriverExist()) {
                String str = "INSERT INTO Users(userId,name, address) VALUES ( '" + userId + "', '" + userName + "', '" + userAddress + "' )";
                statement.executeUpdate(str);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
        }
    }

    public Account[] getUserAccounts(User user) {
        try {
            List<Account> accountsList = new ArrayList<>();
            if (isJdBSDriverExist()) {
                String str = "SELECT accountId, currency, balance FROM Accounts WHERE userId='" + user.getUserId() + "'";
                ResultSet resultSet = statement.executeQuery(str);
                getUserAccountData(user, accountsList, resultSet);
            }
            if (accountsList.isEmpty()) {
                return null;
            }
            return accountsList.toArray(new Account[0]);
        } catch (SQLException | ClassCastException ex) {
            System.out.println("SQL Exception or ClassCastException message: " + ex.getMessage());
            return null;
        }
    }

    private void getUserAccountData(User user, List<Account> accountsList, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("accountId"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCurrency(resultSet.getString("currency"));
                account.setUser(user);
                accountsList.add(account);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
        }
    }

    public boolean updateUserAccount(Account account) {
        try {
            if (isJdBSDriverExist()) {
                String str = "UPDATE Accounts SET balance='" + account.getBalance() + "' WHERE accountId='" + account.getAccountId() + "'";
                statement.executeUpdate(str);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
            return false;
        }
    }

    public boolean makeTransactionInDatabase(Account account, int transactionId, double amount) {
        try {
            if (isJdBSDriverExist()) {
                String str = "INSERT INTO Transactions(transactionId, accountId, amount) VALUES ('" + transactionId + "', '" + account.getAccountId() + "', '" + amount + "') ";
                statement.executeUpdate(str);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
            return false;
        }
    }

    public boolean createUserAccount(int accountId, int userId, String currency) {
        try {
            if (isJdBSDriverExist()) {
                String str = "INSERT INTO Accounts(accountId, userId, currency) VALUES ('" + accountId + "', '" + userId + "', '" + currency + "')";
                statement.executeUpdate(str);
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
            return false;
        }
    }

    public boolean isUserAlreadyRegistered(User user) {
        try {
            if (isJdBSDriverExist()) {
                String str = "SELECT name FROM Users WHERE name='" + user.getUserName() + "'";
                ResultSet resultSet = statement.executeQuery(str);
                return resultSet.next();
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
        }
        return false;
    }

    public boolean isUserIdExist(int userId) {
        try {
            if (isJdBSDriverExist()) {
                String str = "SELECT userId FROM Users WHERE userId='" + userId + "'";
                ResultSet resultSet = statement.executeQuery(str);
                return resultSet.next();
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("SQL Exception message: " + ex.getMessage());
        }
        return false;
    }
}
