package com.pluralsight;

public abstract class Topping {
    private String name;

    public String getName() {
        return name;
    }

    public Topping(String name) {
        this.name = name;
    }
}
