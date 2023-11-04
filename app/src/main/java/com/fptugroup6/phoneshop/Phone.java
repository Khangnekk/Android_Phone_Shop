package com.fptugroup6.phoneshop;

import java.io.Serializable;

public class Phone implements Serializable {
    public String name;
    public String color;
    public String price;
    public String screen;
    public String storage;

    public String description1;
    public String description2;
    public String description3;

    public String presentEvent;

    public Phone(String urlImage, String name, String color, String price, String screen, String storage, String description1, String description2, String description3, String presentEvent) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.screen = screen;
        this.storage = storage;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.presentEvent = presentEvent;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getPresentEvent() {
        return presentEvent;
    }

    public void setPresentEvent(String presentEvent) {
        this.presentEvent = presentEvent;
    }
}
