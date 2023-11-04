package com.fptugroup6.phoneshop.model;

public class OrderDetails {
        private int orderDetailID;
        private int orderID;
        private int phoneID;
        private int quantity;
    
        public OrderDetails() {
        }
    
        public OrderDetails(int orderDetailID, int orderID, int phoneID, int quantity) {
            this.orderDetailID = orderDetailID;
            this.orderID = orderID;
            this.phoneID = phoneID;
            this.quantity = quantity;
        }
    
        public int getOrderDetailID() {
            return orderDetailID;
        }
    
        public void setOrderDetailID(int orderDetailID) {
            this.orderDetailID = orderDetailID;
        }
    
        public int getOrderID() {
            return orderID;
        }
    
        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }
    
        public int getPhoneID() {
            return phoneID;
        }
    
        public void setPhoneID(int phoneID) {
            this.phoneID = phoneID;
        }
    
        public int getQuantity() {
            return quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    
}
