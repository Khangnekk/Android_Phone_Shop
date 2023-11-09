    package com.fptugroup6.phoneshop.model;

    import com.google.gson.annotations.SerializedName;

    public class PaymentInformationModel {
        @SerializedName("orderType")
        private String orderType;

        @SerializedName("amount")
        private double amount;

        @SerializedName("orderDescription")
        private String orderDescription;

        @SerializedName("name")
        private String name;

        public PaymentInformationModel() {
        }

        public PaymentInformationModel(String orderType, double amount, String orderDescription, String name) {
            this.orderType = orderType;
            this.amount = amount;
            this.orderDescription = orderDescription;
            this.name = name;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }

        public String getOrderDescription() {
            return orderDescription;
        }

        public void setOrderDescription(String orderDescription) {
            this.orderDescription = orderDescription;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
