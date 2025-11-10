package com.pluralsight.foodCourt;

public class Side implements OrderItem {
    private String name;
    private double price;

    public Side(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getCost() {
        return price;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
