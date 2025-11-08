package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.Size;

public class Drink implements OrderItem {
    private String name;
    private Size size;
    public Drink(String name, Size size) { this.name = name; this.size = size; }
    @Override
    public double getCost() {
        return switch(size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
            default -> 2.00;
        };
    }
    @Override public String getDisplayName() { return name + " (" + size + ")"; }
}