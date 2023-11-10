package com.fptugroup6.phoneshop.model;

public class Product_CartDetail {

    private int orderDetailId;
    private int orderId;
    private int phoneId;
    private String modelName;
    private long price;
    private int quantity;
    private String imageUrl;
    private String description;


    public Product_CartDetail(int orderDetailId,int orderId,int phoneId, String imageUrl, String modelName, long price, String description, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.phoneId = phoneId;
        this.imageUrl = imageUrl;
        this.modelName = modelName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Product_CartDetail() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
