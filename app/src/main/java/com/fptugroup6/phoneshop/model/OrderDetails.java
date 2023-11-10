package com.fptugroup6.phoneshop.model;

import java.io.Serializable;

public class OrderDetails {
        private int orderDetailId;
        private int orderId;
        private String modelName;
        private long price;
        private int phoneId;
        private int quantity;
    
        public OrderDetails() {
        }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetails(int orderDetailId, int orderId, String modelName, long price, int phoneId, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.modelName = modelName;
        this.price = price;
        this.phoneId = phoneId;
        this.quantity = quantity;
    }
}
