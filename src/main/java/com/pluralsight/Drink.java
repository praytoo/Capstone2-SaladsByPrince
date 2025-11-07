package com.pluralsight;

public class Drink implements OrderItem{
    private String flavor;
    private Size size;

    public Drink(String flavor, Size size) {
        this.flavor = flavor;
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return 0; // change later
    }
}
