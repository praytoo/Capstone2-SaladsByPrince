package com.pluralsight.foodCourt;

public abstract class Item implements OrderItem {
    protected String name;
    public String getDisplayName() {
        return name;
    }
}
