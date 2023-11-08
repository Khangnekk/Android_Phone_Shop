package com.fptugroup6.phoneshop.model;

public class Product_CartDetail {

    private String imageUrl;
    private String modelName;
    private long price;
    private String description;
    private int quantity;

    public Product_CartDetail(String imageUrl, String modelName, long price, String description, int quantity) {
        this.imageUrl = imageUrl;
        this.modelName = modelName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
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
