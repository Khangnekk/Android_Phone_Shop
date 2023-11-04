package com.fptugroup6.phoneshop.model;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private ArrayList<Order> orders;
    public Account() {
    }

    public Account(String username, String password, String email, String fullName, ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.orders = orders;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
