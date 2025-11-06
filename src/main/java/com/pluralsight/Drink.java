package com.pluralsight;

public class Drink implements OrderItem{
    private String name;
    private Size size;

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
