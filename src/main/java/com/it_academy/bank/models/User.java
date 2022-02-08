package com.it_academy.bank.models;

public class User {
    private int userId;
    private String name;
    private String address;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return name;
    }

    public void setUserAddress(String address) {
        this.address = address;
    }

    public String getUserAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "com.it_academy.bank.models.User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
