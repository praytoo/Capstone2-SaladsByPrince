package com.pluralsight;

public abstract class Topping {
    private String name;

    public String getName() {
        return name;
    }

    public Topping(String name) {
        this.name = name;
    }

    public double getCost(Size size, boolean extra) {
    return 0; //change later
    }
}
