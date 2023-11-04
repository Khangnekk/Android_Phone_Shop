package com.fptugroup6.phoneshop.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order  implements Serializable {
    private int orderID;
    private String username;
    private Date orderDate;
    private ArrayList<OrderDetails> orderDetails;
    public Order() {
    }

    public Order(int orderID, String username, Date orderDate, ArrayList<OrderDetails> orderDetails) {
        this.orderID = orderID;
        this.username = username;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
