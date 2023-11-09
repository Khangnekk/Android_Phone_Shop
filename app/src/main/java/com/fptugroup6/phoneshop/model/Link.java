package com.fptugroup6.phoneshop.model;

public class Link {
    private String name;
    private String value;

    public Link(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Link() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
