package com.fptugroup6.phoneshop.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Phone implements Serializable {
    private int phoneId;
    private String modelName;
    private String manufacturer;
    private double price;
    private String description;
    private boolean inStock;
    private String imageUrl;
    private ArrayList<OrderDetails> orderDetails;

    public Phone() {
        orderDetails = new ArrayList<>();
    }

    public Phone(int phoneID, String modelName, String manufacturer, double price, String description, boolean inStock, String imageURL) {
        this.phoneId = phoneID;
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.inStock = inStock;
        this.imageUrl = imageURL;
        orderDetails = new ArrayList<>();
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
