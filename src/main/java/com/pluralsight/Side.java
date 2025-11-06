package com.pluralsight;

public class Side implements OrderItem {
    private String name;
    private double Price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return Price;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
