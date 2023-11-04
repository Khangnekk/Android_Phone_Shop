package com.fptugroup6.phoneshop.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Phone implements Serializable {
    private int phoneID;
    private String modelName;
    private String manufacturer;
    private double price;
    private String description;
    private boolean inStock;
    private String imageURL;
    private ArrayList<OrderDetails> orderDetails; // Quan hệ một-nhiều với OrderDetail

    public Phone() {
        orderDetails = new ArrayList<>();
    }

    public Phone(int phoneID, String modelName, String manufacturer, double price, String description, boolean inStock, String imageURL) {
        this.phoneID = phoneID;
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.inStock = inStock;
        this.imageURL = imageURL;
        orderDetails = new ArrayList<>();
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
